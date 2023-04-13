package com.fintech.gams.oauth.authorize;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.SecurityUtils;

import com.fintech.gams.oauth.OAuthAuthxRequest;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.oauth.validator.TokenClientDetailsValidator;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.shiro.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * TokenAuthorizeHandler
 * <p>File: TokenAuthorizeHandler.java </p>
 * <p>Title: TokenAuthorizeHandler </p>
 * <p>Description: TokenAuthorizeHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class TokenAuthorizeHandler extends AbstractAuthorizeHandler
{
    public TokenAuthorizeHandler(OAuthAuthxRequest oauthRequest, HttpServletResponse response)
    {
        super(oauthRequest, response);
    }
    
    @Override
    protected AbstractClientDetailsValidator getValidator()
    {
        return new TokenClientDetailsValidator(oauthRequest, false);
    }
    
    /**
     *  response token
     *
     *  If it is the first logged or first approval , always return newly AccessToken
     *  Always exclude refresh_token
     */
    @Override
    protected void handleResponse() throws OAuthSystemException, IOException
    {
        if (forceNewAccessToken())
        {
            forceTokenResponse();
        }
        else
        {
            final String userName = (String) SecurityUtils.getSubject().getPrincipal();
            AccessToken accessToken = oauthService.retrieveAccessToken(clientDetails(), userName, oauthRequest.getScopes(), false);
            if (accessToken.tokenExpired())
            {
                expiredTokenResponse(accessToken);
            }
            else
            {
                normalTokenResponse(accessToken);
            }
        }
    }
    
    private void forceTokenResponse() throws OAuthSystemException
    {
        final String userName = (String) SecurityUtils.getSubject().getPrincipal();
        AccessToken accessToken = oauthService.retrieveNewAccessToken(clientDetails(), userName, oauthRequest.getScopes());
        normalTokenResponse(accessToken);
    }
    
    private void normalTokenResponse(AccessToken accessToken) throws OAuthSystemException
    {
        final OAuthResponse oAuthResponse = createTokenResponse(accessToken, true);
        log.debug("'token' response: {}", oAuthResponse);
        WebUtils.writeOAuthQueryResponse(response, oAuthResponse);
    }
    
    private void expiredTokenResponse(AccessToken accessToken) throws OAuthSystemException
    {
        final ClientDetails clientDetails = clientDetails();
        log.debug("AccessToken {} is expired", accessToken);
        final OAuthResponse oAuthResponse = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).setError(OAuthError.ResourceResponse.EXPIRED_TOKEN)
                .setErrorDescription("access_token '" + accessToken.getTokenId() + "' expired").setErrorUri(clientDetails.getRedirectUri()).buildJSONMessage();
        WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
    }
    
    private boolean forceNewAccessToken()
    {
        final ClientDetails clientDetails = clientDetails();
        if (clientDetails.getTrusted())
        {
            return userFirstLogged;
        }
        else
        {
            return userFirstApproved;
        }
    }
}
