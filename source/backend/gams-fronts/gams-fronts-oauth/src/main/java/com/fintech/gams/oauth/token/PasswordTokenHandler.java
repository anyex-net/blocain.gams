package com.fintech.gams.oauth.token;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.fintech.gams.shiro.WebUtils;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.oauth.validator.PasswordClientDetailsValidator;
import com.fintech.gams.oauth2.entity.AccessToken;

import lombok.extern.slf4j.Slf4j;

/**
 * PasswordTokenHandler
 * <p>File: PasswordTokenHandler.java </p>
 * <p>Title: PasswordTokenHandler </p>
 * <p>Description: PasswordTokenHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class PasswordTokenHandler extends AbstractOAuthTokenHandler
{
    @Override
    public boolean support(OAuthTokenRequest tokenRequest) throws OAuthProblemException
    {
        final String grantType = tokenRequest.getGrantType();
        return GrantType.PASSWORD.toString().equalsIgnoreCase(grantType);
    }
    
    @Override
    protected AbstractClientDetailsValidator getValidator()
    {
        return new PasswordClientDetailsValidator(tokenRequest);
    }
    
    /**
     * /oauth/token?client_id=mobile-client&client_secret=mobile&grant_type=password&scope=read,write&username=mobile&password=mobile
     * <p/>
     * Response access_token
     * If exist AccessToken and it is not expired, return it
     * otherwise, return a new AccessToken(include refresh_token)
     * <p/>
     * {"token_type":"Bearer","expires_in":33090,"refresh_token":"976aeaf7df1ee998f98f15acd1de63ea","access_token":"7811aff100eb7dadec132f45f1c01727"}
     */
    @Override
    public void handleAfterValidation() throws OAuthProblemException, OAuthSystemException
    {
        AccessToken accessToken = oauthService.retrievePasswordAccessToken(clientDetails(), tokenRequest.getScopes(), tokenRequest.getUsername());
        final OAuthResponse tokenResponse = createTokenResponse(accessToken, false);
        log.debug("'password' response: {}", tokenResponse);
        WebUtils.writeOAuthJsonResponse(response, tokenResponse);
    }
}
