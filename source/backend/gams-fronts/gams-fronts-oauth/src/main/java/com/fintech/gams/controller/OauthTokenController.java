package com.fintech.gams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fintech.gams.oauth.token.OAuthTokenHandleDispatcher;
import com.fintech.gams.shiro.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * OauthTokenController
 * <p>File: OauthTokenController.java </p>
 * <p>Title: OauthTokenController </p>
 * <p>Description: OauthTokenController </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthTokenController
{
    /**
     * Handle grant_types as follows:
     * <p/>
     * grant_type=authorization_code
     * grant_type=password
     * grant_type=refresh_token
     * grant_type=client_credentials
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws OAuthSystemException
     */
    @RequestMapping("/token")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException
    {
        try
        {
            OAuthTokenRequest tokenRequest = new OAuthTokenRequest(request);
            OAuthTokenHandleDispatcher tokenHandleDispatcher = new OAuthTokenHandleDispatcher(tokenRequest, response);
            tokenHandleDispatcher.dispatch();
        }
        catch (OAuthProblemException e)
        {
            OAuthResponse oAuthResponse = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).location(e.getRedirectUri()).error(e).buildJSONMessage();
            WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
        }
    }
}
