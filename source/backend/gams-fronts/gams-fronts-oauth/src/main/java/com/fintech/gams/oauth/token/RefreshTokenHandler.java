package com.fintech.gams.oauth.token;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.fintech.gams.shiro.WebUtils;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.oauth.validator.RefreshTokenClientDetailsValidator;
import com.fintech.gams.oauth2.entity.AccessToken;

import lombok.extern.slf4j.Slf4j;

/**
 * RefreshTokenHandler
 * <p>File: RefreshTokenHandler.java </p>
 * <p>Title: RefreshTokenHandler </p>
 * <p>Description: RefreshTokenHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class RefreshTokenHandler extends AbstractOAuthTokenHandler
{
    @Override
    public boolean support(OAuthTokenRequest tokenRequest) throws OAuthProblemException
    {
        final String grantType = tokenRequest.getGrantType();
        return GrantType.REFRESH_TOKEN.toString().equalsIgnoreCase(grantType);
    }
    
    /**
     * URL demo:
     * /oauth/token?client_id=mobile-client&client_secret=mobile&grant_type=refresh_token&refresh_token=b36f4978-a172-4aa8-af89-60f58abe3ba1
     *
     * @throws org.apache.oltu.oauth2.common.exception.OAuthProblemException
     */
    @Override
    public void handleAfterValidation() throws OAuthProblemException, OAuthSystemException
    {
        final String refreshToken = tokenRequest.getRefreshToken();
        AccessToken accessToken = oauthService.changeAccessTokenByRefreshToken(refreshToken, tokenRequest.getClientId());
        final OAuthResponse tokenResponse = createTokenResponse(accessToken, false);
        log.debug("'refresh_token' response: {}", tokenResponse);
        WebUtils.writeOAuthJsonResponse(response, tokenResponse);
    }
    
    @Override
    protected AbstractClientDetailsValidator getValidator()
    {
        return new RefreshTokenClientDetailsValidator(tokenRequest);
    }
}
