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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.shiro.CustomShiroLoginFilter;
import com.fintech.gams.shiro.CustomWebSessionManager;
import com.fintech.gams.shiro.ShiroSessionManager;
import com.fintech.gams.shiro.session.RedisSessionDAO;
import com.google.common.collect.Maps;

@Configuration
public class ShiroConfig
{
    @Autowired
    private GlobalProperies globalProperies;
    
    @Bean
    public RedisSessionDAO sessionDAO(ShiroSessionManager shiroSessionManager)
    {
        RedisSessionDAO sessionDAO = new RedisSessionDAO();
        sessionDAO.setShiroSessionManager(shiroSessionManager);
        sessionDAO.setSessionPrefix(CacheConst.BOSS_SHIRO_CACHE_PREFIX);
        return sessionDAO;
    }
    
    @Bean
    public SimpleCookie cookie()
    {
        SimpleCookie cookie = new SimpleCookie(GlobalConst.BOSS_SESSION_ID);
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
        sessionManager.setSessionIdUrlRewritingEnabled(true);
        return sessionManager;
    }
    
    @Bean
    public SecurityManager securityManager(AuthorizingRealm bossAuthorizingRealm, SessionManager sessionManager)
    {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(bossAuthorizingRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }
    
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager)
    {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 获取filters
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        // 将自定义 的FormAuthenticationFilter注入shiroFilter中
        filters.put("authc", new CustomShiroLoginFilter());
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        // swagger2
        // filterChainDefinitionMap.put("/swagger/**", "anon");
        // filterChainDefinitionMap.put("/v2/api-docs", "anon");
        // filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        // filterChainDefinitionMap.put("/webjars/**", "anon");
        // filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        // system
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/styles/**", "anon");
        filterChainDefinitionMap.put("/error/**", "anon");
        filterChainDefinitionMap.put("/login/**", "anon");
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
