package com.fintech.gams.oauth.validator;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.entity.ClientDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * RefreshTokenClientDetailsValidator
 * <p>File: RefreshTokenClientDetailsValidator.java </p>
 * <p>Title: RefreshTokenClientDetailsValidator </p>
 * <p>Description: RefreshTokenClientDetailsValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class RefreshTokenClientDetailsValidator extends AbstractOauthTokenValidator
{
    public RefreshTokenClientDetailsValidator(OAuthTokenRequest oauthRequest)
    {
        super(oauthRequest);
    }
    
    /**
     * /oauth/token?client_id=mobile-client&client_secret=mobile&grant_type=refresh_token&refresh_token=b36f4978-a172-4aa8-af89-60f58abe3ba1
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
        // validate refresh_token
        final String refreshToken = tokenRequest.getRefreshToken();
        AccessToken accessToken = oauthService.loadAccessTokenByRefreshToken(refreshToken, oauthRequest.getClientId());
        if (accessToken == null || accessToken.refreshTokenExpired())
        {
            log.debug("Invalid refresh_token: '{}'", refreshToken);
            return invalidRefreshTokenResponse(refreshToken);
        }
        return null;
    }
    
    private OAuthResponse invalidRefreshTokenResponse(String refreshToken) throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("Invalid refresh_token: " + refreshToken).buildJSONMessage();
    }
}
