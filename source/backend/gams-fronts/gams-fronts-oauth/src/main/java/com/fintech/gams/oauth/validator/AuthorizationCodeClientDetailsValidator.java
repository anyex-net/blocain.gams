package com.fintech.gams.oauth.validator;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.entity.OauthCode;

import lombok.extern.slf4j.Slf4j;

/**
 * AuthorizationCodeClientDetailsValidator
 * <p>File: AuthorizationCodeClientDetailsValidator.java </p>
 * <p>Title: AuthorizationCodeClientDetailsValidator </p>
 * <p>Description: AuthorizationCodeClientDetailsValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class AuthorizationCodeClientDetailsValidator extends AbstractOauthTokenValidator
{
    public AuthorizationCodeClientDetailsValidator(OAuthTokenRequest oauthRequest)
    {
        super(oauthRequest);
    }
    
    /**
     * /oauth/token?client_id=unity-client&client_secret=unity&grant_type=authorization_code&code=zLl170&redirect_uri=redirect_uri
     */
    @Override
    protected OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException
    {
        // validate grant_type
        final String grantType = grantType();
        if (!clientDetails.getGrantTypes().contains(grantType))
        {
            log.debug("Invalid grant_type '{}', client_id = '{}'", grantType, clientDetails.getClientId());
            return invalidGrantTypeResponse(grantType);
        }
        // validate client_secret
        final String clientSecret = oauthRequest.getClientSecret();
        if (clientSecret == null || !clientSecret.equals(clientDetails.getClientSecret()))
        {
            log.debug("Invalid client_secret '{}', client_id = '{}'", clientSecret, clientDetails.getClientId());
            return invalidClientSecretResponse();
        }
        // validate redirect_uri
        final String redirectURI = oauthRequest.getRedirectURI();
        if (redirectURI == null || !redirectURI.equals(clientDetails.getRedirectUri()))
        {
            log.debug("Invalid redirect_uri '{}', client_id = '{}'", redirectURI, clientDetails.getClientId());
            return invalidRedirectUriResponse();
        }
        // validate code
        String code = getCode();
        OauthCode oauthCode = oauthService.loadOauthCode(code, clientDetails());
        if (oauthCode == null)
        {
            log.debug("Invalid code '{}', client_id = '{}'", code, clientDetails.getClientId());
            return invalidCodeResponse(code);
        }
        return null;
    }
    
    private OAuthResponse invalidCodeResponse(String code) throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("Invalid code '" + code + "'").buildJSONMessage();
    }
    
    private String getCode()
    {
        return ((OAuthTokenRequest) oauthRequest).getCode();
    }
}
