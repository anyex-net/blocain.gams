package com.fintech.gams.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.utils.HttpUtils;
import com.fintech.gams.utils.StringUtils;
import com.google.common.collect.Maps;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.oauth.OAuthAuthxRequest;
import com.fintech.gams.oauth.authorize.CodeAuthorizeHandler;
import com.fintech.gams.oauth.authorize.TokenAuthorizeHandler;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.service.OauthService;
import com.fintech.gams.shiro.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * OauthAuthorizeController
 * <p>File: OauthAuthorizeController.java </p>
 * <p>Title: OauthAuthorizeController </p>
 * <p>Description: OauthAuthorizeController </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/oauth")
public class OauthAuthorizeController extends GenericController
{
    @Autowired(required = false)
    private OauthService oauthService;
    
    /**
     * Must handle the grant_type as follow:
     * grant_type="authorization_code" -> response_type="code"
     * ?response_type=code&scope=read,write&client_id=[client_id]&redirect_uri=[redirect_uri]&state=[state]
     * <p/>
     * grant_type="implicit"   -> response_type="token"
     * ?response_type=token&scope=read,write&client_id=[client_id]&client_secret=[client_secret]&redirect_uri=[redirect_uri]
     * <p/>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @RequestMapping("/authorize")
    public void authorize(HttpServletRequest request, HttpServletResponse response) throws OAuthSystemException, ServletException, IOException
    {
        try
        {
            OAuthAuthxRequest oauthRequest = new OAuthAuthxRequest(request);
            if (oauthRequest.isCode())
            {
                CodeAuthorizeHandler codeAuthorizeHandler = new CodeAuthorizeHandler(oauthRequest, response);
                log.debug("Go to response_type = 'code' handler: {}", codeAuthorizeHandler);
                codeAuthorizeHandler.handle();
            }
            else if (oauthRequest.isToken())
            {
                TokenAuthorizeHandler tokenAuthorizeHandler = new TokenAuthorizeHandler(oauthRequest, response);
                log.debug("Go to response_type = 'token' handler: {}", tokenAuthorizeHandler);
                tokenAuthorizeHandler.handle();
            }
            else
            {
                final String responseType = oauthRequest.getResponseType();
                log.debug("Unsupport response_type '{}' by client_id '{}'", responseType, oauthRequest.getClientId());
                OAuthResponse oAuthResponse = OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.CodeResponse.UNSUPPORTED_RESPONSE_TYPE)
                        .setErrorDescription("Unsupport response_type '" + responseType + "'").buildJSONMessage();
                WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
            }
        }
        catch (OAuthProblemException e)
        {
            OAuthResponse oAuthResponse = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND).location(e.getRedirectUri()).error(e).buildJSONMessage();
            WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
        }
    }
    
    /**
     * 根据accessToken退出
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public JsonMessage logout(HttpServletRequest request) throws Exception
    {
        // 构建 OAuth2 资源请求
        OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
        // 验证Access Token
        AccessToken accessToken = oauthService.loadAccessTokenByTokenId(oauthRequest.getAccessToken());
        if (null != accessToken || !accessToken.tokenExpired())
        {
            String accountId = accessToken.getUserName();
            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager) securityManager.getSessionManager();
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            for (Session session : sessions)
            {
                SimplePrincipalCollection collection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
                String principalId = (String) collection.getPrimaryPrincipal();
                if (null != principalId && accountId.equals(principalId))
                { // 清除该用户以前登录时保存的session
                    sessionManager.getSessionDAO().delete(session);
                }
            }
            // 处理其它子系统会话状态
            List<String> tokens = oauthService.loadAccessTokenByUserName(accountId);
            for (String token : tokens)
            {
                if (!accessToken.getClientId().equals(token))
                {// 排除当前子系统
                    ClientDetails clientDetails = oauthService.loadClientDetails(token);
                    if (null != clientDetails && StringUtils.isNotBlank(clientDetails.getLogoutUri()))
                    {// 向子系统发起退出请求
                        Map<String, String> params = Maps.newHashMap();
                        params.put("accountId", accountId);
                        HttpUtils.post(clientDetails.getLogoutUri(), params);
                    }
                }
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @RequestMapping(value = "/oauth_login")
    public String oauthLogin()
    {
        return "oauth/login";
    }
    
    @RequestMapping(value = "/oauth_check")
    public String oauthCheck()
    {
        return "oauth/check";
    }
    
    @RequestMapping(value = "/oauth_approval")
    public String oauthApproval()
    {
        return "oauth/approval";
    }
}
