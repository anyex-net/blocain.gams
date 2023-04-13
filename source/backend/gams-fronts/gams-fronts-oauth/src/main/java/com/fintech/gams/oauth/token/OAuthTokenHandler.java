package com.fintech.gams.oauth.token;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

/**
 * OAuthTokenHandler
 * <p>File: OAuthTokenHandler.java </p>
 * <p>Title: OAuthTokenHandler </p>
 * <p>Description: OAuthTokenHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface OAuthTokenHandler
{
    boolean support(OAuthTokenRequest tokenRequest) throws OAuthProblemException;
    
    void handle(OAuthTokenRequest tokenRequest, HttpServletResponse response) throws OAuthProblemException, OAuthSystemException;
}
