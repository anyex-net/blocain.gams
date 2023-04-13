package com.fintech.gams.oauth.token;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.fintech.gams.shiro.WebUtils;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.oauth.validator.AuthorizationCodeClientDetailsValidator;
import com.fintech.gams.oauth2.entity.AccessToken;

import lombok.extern.slf4j.Slf4j;

/**
 * AuthorizationCodeTokenHandler
 * <p>File: AuthorizationCodeTokenHandler.java </p>
 * <p>Title: AuthorizationCodeTokenHandler </p>
 * <p>Description: AuthorizationCodeTokenHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class AuthorizationCodeTokenHandler extends AbstractOAuthTokenHandler
{
    @Override
    public boolean support(OAuthTokenRequest tokenRequest) throws OAuthProblemException
    {
        final String grantType = tokenRequest.getGrantType();
        return GrantType.AUTHORIZATION_CODE.toString().equalsIgnoreCase(grantType);
    }
    
    /**
     * /oauth/token?client_id=unity-client&client_secret=unity&grant_type=authorization_code&code=zLl170&redirect_uri=redirect_uri
     */
    @Override
    public void handleAfterValidation() throws OAuthProblemException, OAuthSystemException
    {
        // response token, always new
        responseToken();
        // remove code lastly
        removeCode();
    }
    
    private void removeCode()
    {
        final String code = tokenRequest.getCode();
        final boolean result = oauthService.removeOauthCode(code, clientDetails());
        log.debug("Remove code: {} result: {}", code, result);
    }
    
    private void responseToken() throws OAuthSystemException
    {
        AccessToken accessToken = oauthService.retrieveAuthorizationCodeAccessToken(clientDetails(), tokenRequest.getCode());
        final OAuthResponse tokenResponse = createTokenResponse(accessToken, false);
        log.debug("'authorization_code' response: {}", tokenResponse);
        WebUtils.writeOAuthJsonResponse(response, tokenResponse);
    }
    
    @Override
    protected AbstractClientDetailsValidator getValidator()
    {
        return new AuthorizationCodeClientDetailsValidator(tokenRequest);
    }
}
