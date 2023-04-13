package com.fintech.gams.oauth.token;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.fintech.gams.shiro.WebUtils;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.oauth.validator.ClientCredentialsClientDetailsValidator;
import com.fintech.gams.oauth2.entity.AccessToken;

import lombok.extern.slf4j.Slf4j;

/**
 * ClientCredentialsTokenHandler
 * <p>File: ClientCredentialsTokenHandler.java </p>
 * <p>Title: ClientCredentialsTokenHandler </p>
 * <p>Description: ClientCredentialsTokenHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class ClientCredentialsTokenHandler extends AbstractOAuthTokenHandler
{
    @Override
    public boolean support(OAuthTokenRequest tokenRequest) throws OAuthProblemException
    {
        final String grantType = tokenRequest.getGrantType();
        return GrantType.CLIENT_CREDENTIALS.toString().equalsIgnoreCase(grantType);
    }
    
    /**
     * /oauth/token?client_id=credentials-client&client_secret=credentials-secret&grant_type=client_credentials&scope=read write
     * <p/>
     * Response access_token
     * If exist AccessToken and it is not expired, return it
     * otherwise, return a new AccessToken
     * <p/>
     * {"access_token":"38187f32-e4fb-4650-8e4a-99903b66f20e","token_type":"bearer","expires_in":7}
     */
    @Override
    public void handleAfterValidation() throws OAuthProblemException, OAuthSystemException
    {
        AccessToken accessToken = oauthService.retrieveClientCredentialsAccessToken(clientDetails(), tokenRequest.getScopes());
        final OAuthResponse tokenResponse = createTokenResponse(accessToken, false);
        log.debug("'client_credentials' response: {}", tokenResponse);
        WebUtils.writeOAuthJsonResponse(response, tokenResponse);
    }
    
    @Override
    protected AbstractClientDetailsValidator getValidator()
    {
        return new ClientCredentialsClientDetailsValidator(tokenRequest);
    }
}
