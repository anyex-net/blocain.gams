package com.fintech.gams.shiro;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import com.fintech.gams.shiro.model.OAuth2Token;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * OAuth2AuthenticationFilter
 * <p>File: OAuth2AuthenticationFilter.java </p>
 * <p>Title: OAuth2AuthenticationFilter </p>
 * <p>Description: OAuth2AuthenticationFilter </p>
 * <p>Copyright: Copyright (c) 2019-07-04</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@Slf4j
public class OAuth2AuthenticationFilter extends AuthenticatingFilter
{
    // oauth2 authc code参数名
    private String authcCode;
    
    // 客户端id
    private String clientId;
    
    // 服务器端登录成功/失败后重定向到的客户端地址
    private String redirectUrl;
    
    // oauth2服务器响应类型
    private String responseType;
    
    private String failureUrl;
    
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception
    {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String code = httpRequest.getParameter(authcCode);
        log.info("OAuth2AuthenticationFilter中拿到的auth_code = " + code);
        return new OAuth2Token(code);
    }
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
    {
        String error = request.getParameter("error");
        if (!StringUtils.isEmpty(error))
        {
            WebUtils.issueRedirect(request, response, failureUrl + "?error=" + error);
            // OAuthResponse authResponse = OAuthResponse.errorResponse(HttpServletResponse.SC_FORBIDDEN).setError(error)
            // .setErrorDescription(errorDescription).buildJSONMessage();
            // com.fintech.gams.shiro.WebUtils.writeOAuthJsonResponse((HttpServletResponse) response,authResponse);
            return false;
        }
        Subject subject = getSubject(request, response);
        log.info("subject.isAuthenticated() == " + subject.isAuthenticated());
        if (!subject.isAuthenticated())
        {
            if (StringUtils.isEmpty(request.getParameter(authcCode)))
            {
                // 如果没有身份认证，且没有authCode,则重定向到服务端授权
                saveRequestAndRedirectToLogin(request, response);
                return false;
            }
        }
        return executeLogin(request, response);
    }
    
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response)
    {
        Subject subject = getSubject(request, response);
        log.info("是否验证：subject.isAuthenticated() == " + subject.isAuthenticated());
        log.info("是否记住：subject.isRemembered() == " + subject.isRemembered());
        if (subject.isAuthenticated() || subject.isRemembered())
        {
            // 重定向到成功页面
            try
            {
                log.info("重定向到成功页面success。。。。");
                issueSuccessRedirect(request, response);
            }
            catch (Exception e1)
            {
                log.info("重定向到成功页面异常=", e1);
            }
        }
        else
        {
            try
            {
                log.info("重定向到失败页面failure。。。。");
                WebUtils.issueRedirect(request, response, failureUrl);
            }
            catch (IOException e1)
            {
                log.info("重定向到失败页面异常=", e1);
            }
        }
        return false;
    }
    
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception
    {
        WebUtils.issueRedirect(request, response, getSuccessUrl());
        return false;
    }
}
