package com.fintech.gams.config;

import java.util.Map;

import javax.servlet.Filter;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.shiro.*;
import com.fintech.gams.shiro.session.RedisSessionDAO;
import com.google.common.collect.Maps;

@Configuration
public class ShiroConfig
{
    @Autowired
    private GlobalProperies globalProperies;
    
    @Autowired
    private Oauth2Config    oauth2Config;
    
    @Bean
    public RedisSessionDAO sessionDAO(ShiroSessionManager shiroSessionManager)
    {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setShiroSessionManager(shiroSessionManager);
        sessionDAO.setSessionPrefix(CacheConst.TRADE_SHIRO_CACHE_PREFIX);
        return sessionDAO;
    }
    
    @Bean
    public SimpleCookie cookie()
    {
        SimpleCookie cookie = new SimpleCookie(GlobalConst.TRADE_SESSION_ID);
        cookie.setDomain(globalProperies.getDomainName());
        cookie.setPath(globalProperies.getCookiePath());
        cookie.setSecure(globalProperies.getCookieSecure());
        return cookie;
    }
    
    @Bean
    public SessionManager sessionManager(SessionDAO sessionDAO, SimpleCookie cookie)
    {
        CustomWebSessionManager sessionManager = new CustomWebSessionManager(cookie.getName());
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionIdCookie(cookie);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionValidationInterval(120000);
        sessionManager.setGlobalSessionTimeout(60 * 60 * 24 * 1000);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    
    @Bean
    public OAuth2ClientAuthorizingRealm clientAuthorizingRealm()
    {
        OAuth2ClientAuthorizingRealm authorizingRealm = new OAuth2ClientAuthorizingRealm();
        authorizingRealm.setClientId(oauth2Config.getClientId());
        authorizingRealm.setClientSecret(oauth2Config.getClientSecret());
        authorizingRealm.setAccessTokenUrl(oauth2Config.getAccessTokenUrl());
        authorizingRealm.setUserInfoUrl(oauth2Config.getUserInfoUrl());
        authorizingRealm.setRedirectUrl(oauth2Config.getRedirectUrl());
        return authorizingRealm;
    }
    
    @Bean
    public SecurityManager securityManager(AuthorizingRealm clientAuthorizingRealm, SessionManager sessionManager)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(clientAuthorizingRealm);
        securityManager.setSessionManager(sessionManager);
        return securityManager;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/oauthSuccess");
        // 获取filters
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        OAuth2AuthenticationFilter oAuth2AuthenticationFilter = new OAuth2AuthenticationFilter();
        oAuth2AuthenticationFilter.setAuthcCode(OAuth.OAUTH_CODE);
        oAuth2AuthenticationFilter.setResponseType(OAuth.OAUTH_CODE);
        oAuth2AuthenticationFilter.setFailureUrl("/oauthFailure");
        filters.put("authc", new CustomShiroLoginFilter());
        filters.put("oauth2", oAuth2AuthenticationFilter);
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        // swagger2
        // filterChainDefinitionMap.put("/swagger/**", "anon");
        // filterChainDefinitionMap.put("/v2/api-docs", "anon");
        // filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        // filterChainDefinitionMap.put("/webjars/**", "anon");
        // filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        // system
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/statics/**", "anon");
        filterChainDefinitionMap.put("/styles/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "anon");
        filterChainDefinitionMap.put("/oauthFailure", "anon");
        filterChainDefinitionMap.put("/forgetPass/**", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/register/**", "anon");
        filterChainDefinitionMap.put("/express/**", "anon");
        filterChainDefinitionMap.put("/stock/**", "anon");
        filterChainDefinitionMap.put("/oauth/check", "oauth2");
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
