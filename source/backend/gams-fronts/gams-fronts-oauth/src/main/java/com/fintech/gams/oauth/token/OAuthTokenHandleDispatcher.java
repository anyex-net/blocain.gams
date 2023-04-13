package com.fintech.gams.oauth.token;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import lombok.extern.slf4j.Slf4j;

/**
 * OAuthTokenHandleDispatcher
 * <p>File: OAuthTokenHandleDispatcher.java </p>
 * <p>Title: OAuthTokenHandleDispatcher </p>
 * <p>Description: OAuthTokenHandleDispatcher </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class OAuthTokenHandleDispatcher
{
    private List<OAuthTokenHandler>   handlers = new ArrayList<>();
    
    private final OAuthTokenRequest   tokenRequest;
    
    private final HttpServletResponse response;
    
    public OAuthTokenHandleDispatcher(OAuthTokenRequest tokenRequest, HttpServletResponse response)
    {
        this.tokenRequest = tokenRequest;
        this.response = response;
        initialHandlers();
    }
    
    private void initialHandlers()
    {
        handlers.add(new AuthorizationCodeTokenHandler());
        handlers.add(new PasswordTokenHandler());
        handlers.add(new RefreshTokenHandler());
        handlers.add(new ClientCredentialsTokenHandler());
        log.debug("Initialed '{}' OAuthTokenHandler(s): {}", handlers.size(), handlers);
    }
    
    public void dispatch() throws OAuthProblemException, OAuthSystemException
    {
        for (OAuthTokenHandler handler : handlers)
        {
            if (handler.support(tokenRequest))
            {
                log.debug("Found '{}' handle OAuthTokenxRequest: {}", handler, tokenRequest);
                handler.handle(tokenRequest, response);
                return;
            }
        }
        throw new IllegalStateException("Not found 'OAuthTokenHandler' to handle OAuthTokenxRequest: " + tokenRequest);
    }
}
