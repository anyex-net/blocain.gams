package com.fintech.gams.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

/**
 * @author Shengzhao Li
 */
public abstract class WebUtils
{
    private WebUtils()
    {
    }
    
    public static void writeOAuthJsonResponse(HttpServletResponse response, OAuthResponse oAuthResponse)
    {
        final int responseStatus = oAuthResponse.getResponseStatus();
        try
        {
            final Map<String, String> headers = oAuthResponse.getHeaders();
            for (String key : headers.keySet())
            {
                response.addHeader(key, headers.get(key));
            }
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setContentType(OAuth.ContentType.JSON); // json
            response.setStatus(responseStatus);
            final PrintWriter out = response.getWriter();
            out.print(oAuthResponse.getBody());
            out.flush();
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Write OAuthResponse error", e);
        }
    }
    
    public static void writeOAuthQueryResponse(HttpServletResponse response, OAuthResponse oAuthResponse)
    {
        final String locationUri = oAuthResponse.getLocationUri();
        try
        {
            final Map<String, String> headers = oAuthResponse.getHeaders();
            for (String key : headers.keySet())
            {
                response.addHeader(key, headers.get(key));
            }
            response.setCharacterEncoding("UTF-8");
            response.setStatus(oAuthResponse.getResponseStatus());
            response.sendRedirect(locationUri);
        }
        catch (IOException e)
        {
            throw new IllegalStateException("Write OAuthResponse error", e);
        }
    }
}