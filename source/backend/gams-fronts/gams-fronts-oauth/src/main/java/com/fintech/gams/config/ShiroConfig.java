package com.fintech.gams.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fintech.gams.account.service.AccountPolicyService;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.oauth2.service.OauthService;
import com.fintech.gams.shiro.CustomShiroLoginFilter;
import com.fintech.gams.shiro.OAuth2ServerAuthorizingRealm;
import com.fintech.gams.shiro.ShiroSessionManager;
import com.fintech.gams.shiro.session.OAuthSessionDAO;
import com.google.common.collect.Maps;

@Configuration
public class ShiroConfig
{
    @Autowired(required = false)
    OauthService            oauthService;
    
    @Autowired(required = false)
    AccountService          accountService;
    
    @Autowired(required = false)
    AccountPolicyService    accountPolicyService;
    
    @Autowired
    private GlobalProperies globalProperies;
    
    @Bean
    public SessionDAO sessionDAO(ShiroSessionManager shiroSessionManager)
    {
        OAuthSessionDAO sessionDAO = new OAuthSessionDAO();
        sessionDAO.setShiroSessionManager(shiroSessionManager);
        sessionDAO.setSessionPrefix(CacheConst.OAUTH_SHIRO_CACHE_PREFIX);
        return sessionDAO;
    }
    
    @Bean
    public SimpleCookie cookie()
    {
        SimpleCookie cookie = new SimpleCookie(GlobalConst.OAUTH_SESSION_ID);
        cookie.setDomain(globalProperies.getDomainName());
        cookie.setPath(globalProperies.getCookiePath());
        cookie.setSecure(globalProperies.getCookieSecure());
        return cookie;
    }
    
    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO, SimpleCookie cookie)
    {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }
    
    @Bean
    public OAuth2ServerAuthorizingRealm serverAuthorizingRealm()
    {
        OAuth2ServerAuthorizingRealm authorizingRealm = new OAuth2ServerAuthorizingRealm();
        authorizingRealm.setOauthService(oauthService);
        authorizingRealm.setAccountService(accountService);
        authorizingRealm.setAccountPolicyService(accountPolicyService);
        return authorizingRealm;
    }
    
    @Bean
    public SecurityManager securityManager(AuthorizingRealm serverAuthorizingRealm, SessionManager sessionManager)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(serverAuthorizingRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        // 获取filters
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 将自定义 的FormAuthenticationFilter注入shiroFilter中
        filters.put("authc", new CustomShiroLoginFilter());
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
        filterChainDefinitionMap.put("/index", "anon");
        filterChainDefinitionMap.put("/unauthorized", "anon");
        filterChainDefinitionMap.put("/approval", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/oauth/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/*/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
    /**
     * 开启Shiro注解支持
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager)
    {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
