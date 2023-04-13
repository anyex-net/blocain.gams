package com.fintech.gams.oauth.authorize;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.PolicyModel;
import com.fintech.gams.common.consts.MessageConst;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.DateConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.exception.AccountPolicyException;
import com.fintech.gams.oauth.OAuthAuthxRequest;
import com.fintech.gams.oauth.OAuthHandler;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.shiro.WebUtils;
import com.fintech.gams.shiro.model.AccountToken;
import com.fintech.gams.utils.*;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractAuthorizeHandler
 * <p>File: AbstractAuthorizeHandler.java </p>
 * <p>Title: AbstractAuthorizeHandler </p>
 * <p>Description: AbstractAuthorizeHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public abstract class AbstractAuthorizeHandler extends OAuthHandler
{
    protected OAuthAuthxRequest   oauthRequest;
    
    protected HttpServletResponse response;
    
    protected boolean             userFirstLogged   = false;
    
    protected boolean             userFirstApproved = false;
    
    public AbstractAuthorizeHandler(OAuthAuthxRequest oauthRequest, HttpServletResponse response)
    {
        this.oauthRequest = oauthRequest;
        this.response = response;
    }
    
    protected boolean validateFailed() throws OAuthSystemException
    {
        AbstractClientDetailsValidator validator = getValidator();
        log.debug("Use [{}] validate client: {}", validator, oauthRequest.getClientId());
        final OAuthResponse oAuthResponse = validator.validate();
        return checkAndResponseValidateFailed(oAuthResponse);
    }
    
    protected abstract AbstractClientDetailsValidator getValidator();
    
    @Override
    protected String clientId()
    {
        return oauthRequest.getClientId();
    }
    
    public void handle() throws OAuthSystemException, ServletException, IOException
    {
        // validate
        if (validateFailed())
        { return; }
        // Check need usr login
        if (goLogin())
        { return; }
        // submit login
        if (submitLogin())
        { return; }
        // Check approval
        if (goApproval())
        { return; }
        // Submit approval
        if (submitApproval())
        { return; }
        // handle response
        handleResponse();
    }
    
    protected boolean checkAndResponseValidateFailed(OAuthResponse oAuthResponse)
    {
        if (oAuthResponse != null)
        {
            log.debug("Validate OAuthAuthzRequest(client_id={}) failed", oauthRequest.getClientId());
            WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
            return true;
        }
        return false;
    }
    
    protected boolean isUserAuthenticated()
    {
        final Subject subject = SecurityUtils.getSubject();
        return subject.isAuthenticated();
    }
    
    private boolean isSubmitLogin()
    {
        return !isUserAuthenticated() && isPost();
    }
    
    protected boolean isPost()
    {
        return RequestMethod.POST.name().equalsIgnoreCase(oauthRequest.request().getMethod());
    }
    
    protected boolean isNeedUserLogin()
    {
        return !isUserAuthenticated() && !isPost();
    }
    
    protected boolean goApproval() throws ServletException, IOException
    {
        if (userFirstLogged && !clientDetails().getTrusted())
        {
            log.debug("Go to oauth_approval, clientId: '{}'", clientDetails().getClientId());
            final HttpServletRequest request = oauthRequest.request();
            request.getRequestDispatcher(GlobalConst.OAUTH_APPROVAL_VIEW).forward(request, response);
            return true;
        }
        return false;
    }
    
    // true is submit failed, otherwise return false
    protected boolean submitApproval() throws IOException, OAuthSystemException
    {
        if (isPost() && !clientDetails().getTrusted())
        {
            // submit approval
            final HttpServletRequest request = oauthRequest.request();
            final String oauthApproval = request.getParameter(GlobalConst.REQUEST_USER_OAUTH_APPROVAL);
            if (!"true".equalsIgnoreCase(oauthApproval))
            {
                // Deny action
                log.debug("User '{}' deny access", SecurityUtils.getSubject().getPrincipal());
                responseApprovalDeny();
                return true;
            }
            else
            {
                userFirstApproved = true;
                return false;
            }
        }
        return false;
    }
    
    protected void responseApprovalDeny() throws IOException, OAuthSystemException
    {
        final OAuthResponse oAuthResponse = OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)//
                .setError(OAuthError.CodeResponse.ACCESS_DENIED).location(clientDetails().getRedirectUri())//
                .setState(oauthRequest.getState()).buildQueryMessage();
        log.debug("'ACCESS_DENIED' response: {}", oAuthResponse);
        WebUtils.writeOAuthQueryResponse(response, oAuthResponse);
        // user logout when deny
        final Subject subject = SecurityUtils.getSubject();
        subject.logout();
        log.debug("After 'ACCESS_DENIED' call logout. user: {}", subject.getPrincipal());
    }
    
    protected boolean goLogin() throws ServletException, IOException
    {
        if (isNeedUserLogin())
        {
            log.debug("Forward to Oauth login by client_id '{}'", oauthRequest.getClientId());
            final HttpServletRequest request = oauthRequest.request();
            request.getRequestDispatcher(GlobalConst.OAUTH_LOGIN_VIEW).forward(request, response);
            return true;
        }
        return false;
    }
    
    // true is login failed, false is successful
    protected boolean submitLogin() throws ServletException, IOException
    {
        final HttpServletRequest request = oauthRequest.request();
        final Subject subject = SecurityUtils.getSubject();
        if (isSubmitLogin())
        {
            AccountToken token = createAccountToken();
            try
            {
                PolicyModel policy = createPolicyModel();
                if (null != policy)
                {// 二次登陆验证
                    this.userFirstLogged = false;
                    StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(subject.getSession().getId());
                    AccountToken cacheToken = (AccountToken) RedisUtils.getObject(key.toString());
                    cacheToken.setPolicy(policy);
                    subject.login(cacheToken);
                    // 登陆成功后清除临时会话
                    RedisUtils.del(key.toString());
                }
                else
                {// 正常登陆验证
                    this.userFirstLogged = true;
                    subject.login(token);
                }
                log.debug("Submit login successful");
                saveLogAndSendRemind(request, (String) subject.getPrincipal());
                return false;
            }
            catch (Exception ex)
            {
                if (ex instanceof AccountPolicyException)
                {
                    StringBuffer key = new StringBuffer(CacheConst.LOGIN_PERFIX).append(subject.getSession().getId());
                    if (userFirstLogged)
                    {
                        RedisUtils.putObject(key.toString(), token, CacheConst.DEFAULT_CACHE_TIME);
                        request.setAttribute("mobile", token.getMobNo());
                        request.setAttribute("ga", token.isGa());
                    }
                    else
                    {
                        AccountToken cacheToken = (AccountToken) RedisUtils.getObject(key.toString());
                        request.setAttribute("mobile", cacheToken.getMobNo());
                        request.setAttribute("ga", cacheToken.isGa());
                        request.setAttribute("error", ex.getMessage());
                    }
                    log.debug("Login success, go to check page too");
                    request.getRequestDispatcher(GlobalConst.OAUTH_LOGIN2_VIEW).forward(request, response);
                }
                else
                {
                    log.debug("Login failed, back to login page too,error:{}", ex.getMessage());
                    request.setAttribute("error", ex.getMessage());
                    request.getRequestDispatcher(GlobalConst.OAUTH_LOGIN_VIEW).forward(request, response);
                }
                return true;
            }
        }
        return false;
    }
    
    private AccountToken createAccountToken()
    {
        final HttpServletRequest request = oauthRequest.request();
        final String username = request.getParameter(GlobalConst.REQUEST_USERNAME);
        final String password = request.getParameter(GlobalConst.REQUEST_PASSWORD);
        return new AccountToken(username, password);
    }
    
    private PolicyModel createPolicyModel()
    {
        final HttpServletRequest request = oauthRequest.request();
        final String sms = request.getParameter(GlobalConst.REQUEST_POLICY_SMS);
        final String ga = request.getParameter(GlobalConst.REQUEST_POLICY_GA);
        if (StringUtils.isNotBlank(sms) || StringUtils.isNotBlank(ga)) return new PolicyModel(ga, sms);
        return null;
    }
    
    /**
     * 保存日志并发送提示邮件
     * @param request
     * @param accountId
     */
    private void saveLogAndSendRemind(HttpServletRequest request, String accountId)
    {
        if (StringUtils.isBlank(accountId)) return;
        Account account = accountService.selectByPrimaryKey(Long.parseLong(accountId));
        String ip = NetworkUtils.getIpAddr(request);
        if (StringUtils.isNotBlank(ip)) ip = ip.split(",")[0];
        String userAgent = request.getHeader("User-Agent");
        String userAgentStrng = UserAgentUtils.getUserAgentInfo(userAgent);
        if (globalProperies.isSwitchRemind())
        {// 邮件提醒
            StringBuffer tradeLogo = new StringBuffer(globalProperies.getTradeUrl()).append("/static/img/logo.2f5a587e.png");
            msgLogService.sendRemindEmail(account.getEmail(), MessageConst.REMIND_LOGIN_EMAIL, GlobalConst.DEFAULT_LANG, tradeLogo.toString(), account.getEmail(),
                    userAgentStrng, CalendarUtils.getCurrentDate(DateConst.DATE_FORMAT_YMDHMS), ip);
        }
    }
    
    // Handle custom response content
    protected abstract void handleResponse() throws OAuthSystemException, IOException;
}