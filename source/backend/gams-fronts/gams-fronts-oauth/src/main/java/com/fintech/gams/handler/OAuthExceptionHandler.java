package com.fintech.gams.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fintech.gams.shiro.WebUtils;

/**
 * OAuthShiroHandlerExceptionResolver
 * <p>File: OAuthShiroHandlerExceptionResolver.java </p>
 * <p>Title: OAuthShiroHandlerExceptionResolver </p>
 * <p>Description: OAuthShiroHandlerExceptionResolver </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Component
public class OAuthExceptionHandler implements HandlerExceptionResolver
{
    public OAuthExceptionHandler()
    {
    }
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        if (ex instanceof UnauthorizedException)
        {
            handleUnauthorizedException(response, ex);
        }
        else if (ex instanceof AuthorizationException)
        {
            handleUnauthorizedException(response, ex);
        }
        // more
        return null;
    }
    
    private void handleUnauthorizedException(HttpServletResponse response, Exception ex)
    {
        OAuthResponse oAuthResponse;
        try
        {
            oAuthResponse = OAuthASResponse.errorResponse(403).setError(OAuthError.ResourceResponse.INVALID_TOKEN).setErrorDescription(ex.getMessage()).buildJSONMessage();
        }
        catch (OAuthSystemException e)
        {
            throw new IllegalStateException(e);
        }
        WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
    }
}
