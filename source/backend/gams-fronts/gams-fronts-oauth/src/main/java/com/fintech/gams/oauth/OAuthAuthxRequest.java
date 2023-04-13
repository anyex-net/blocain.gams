package com.fintech.gams.oauth;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.ResponseType;

/**
 * 扩展默认的 OAuthAuthzRequest ,  增加必要的方法
 * <p>File: OAuthAuthxRequest.java </p>
 * <p>Title: OAuthAuthxRequest </p>
 * <p>Description: OAuthAuthxRequest </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class OAuthAuthxRequest extends OAuthAuthzRequest
{
    public OAuthAuthxRequest(HttpServletRequest request) throws OAuthSystemException, OAuthProblemException
    {
        super(request);
    }
    
    /**
     * 判断响应的类型是否为CODE
     */
    public boolean isCode()
    {
        return ResponseType.CODE.name().equalsIgnoreCase(this.getResponseType());
    }
    
    /**
     * 判断响应的类型是否为TOKEN
     */
    public boolean isToken()
    {
        return ResponseType.TOKEN.name().equalsIgnoreCase(this.getResponseType());
    }
    
    /**
     * 获取 request 对象
     */
    public HttpServletRequest request()
    {
        return this.request;
    }
}