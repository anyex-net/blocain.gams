package com.fintech.gams.controller.account;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.config.Oauth2Config;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.shiro.session.RedisSessionDAO;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * AuthController Introduce
 * <p>File：AuthController.java </p>
 * <p>Title: AuthController </p>
 * <p>Description:AuthController </p>
 * <p>Copyright: Copyright (c) 17/6/21</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Controller
@Api(description = "安全认证")
public class AuthController extends GenericController
{
    @Autowired
    private Oauth2Config oauth2Config;
    
    @GetMapping("/login")
    @ApiOperation(value = "用户登陆认证", httpMethod = "GET")
    public String login()
    {
        Subject subject = SecurityUtils.getSubject();
        StringBuffer loginUrl = new StringBuffer("redirect:");
        if (subject.isAuthenticated())
        { // 如果登陆过就直接进入后台
            loginUrl.append(oauth2Config.getSuccessUrl());
//            loginUrl.append("?sid=");
//            loginUrl.append(subject.getSession().getId());
            return loginUrl.toString();
        }
        loginUrl.append(oauth2Config.getAuthorizeUrl());
        loginUrl.append("?client_id=");
        loginUrl.append(oauth2Config.getClientId());
        loginUrl.append("&response_type=");
        loginUrl.append(OAuth.OAUTH_CODE);
        loginUrl.append("&scope=read,write");
        loginUrl.append("&redirect_uri=");
        loginUrl.append(oauth2Config.getRedirectUrl());
        loginUrl.append("&state=");
        loginUrl.append(System.currentTimeMillis());
        return loginUrl.toString();
    }
    
    @GetMapping("/oauthSuccess")
    @ApiOperation(value = "登陆成功", httpMethod = "GET")
    public String oauthSuccess(HttpServletRequest request)
    {
//        Subject subject = SecurityUtils.getSubject();
        StringBuffer loginUrl = new StringBuffer("redirect:");
        loginUrl.append(oauth2Config.getSuccessUrl());
//        loginUrl.append("?sid=");
//        loginUrl.append(subject.getSession().getId());
        return loginUrl.toString();
    }
    
    @GetMapping("/oauthFailure")
    @ApiOperation(value = "用户失败", httpMethod = "GET")
    public String oauthFailure(HttpServletRequest request)
    {
        StringBuffer loginUrl = new StringBuffer("redirect:");
        loginUrl.append(oauth2Config.getFailureUrl()).append("/");
        loginUrl.append(request.getParameter("error"));
        return loginUrl.toString();
    }
    
    @ResponseBody
    @RequestMapping("/logout")
    @ApiOperation(value = "退出认证", httpMethod = "POST")
    public JsonMessage logout(String accountId) throws Exception
    {
        if (StringUtils.isNotBlank(accountId))
        { // 根据会话ID
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();// 获取当前已登录的用户session列表
            for (Session session : sessions)
            {// 清除该用户的session
                if (null != session.getAttribute(RedisSessionDAO.PRINCIPAL_ID))
                { // 空指针处理
                    String principalId = String.valueOf(session.getAttribute(RedisSessionDAO.PRINCIPAL_ID));
                    if (null != principalId && accountId.equals(principalId))
                    { // 清除该用户以前登录时保存的session
                        sessionManager.getSessionDAO().delete(session);
                    }
                }
            }
        }
        else
        {
            Subject subject = SecurityUtils.getSubject();
            if (null != subject && subject.isAuthenticated())
            {// 退出OAUTH服务
                String accessToken = OnLineUserUtils.getPrincipal().getAccessToken();
                OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
                OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(oauth2Config.getAccessLogoutUrl()).setAccessToken(accessToken).buildQueryMessage();
                oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
                // 退出客户端系统
                subject.logout();
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }
}
