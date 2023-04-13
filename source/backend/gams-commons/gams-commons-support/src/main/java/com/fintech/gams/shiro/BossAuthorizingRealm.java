package com.fintech.gams.shiro;

import java.util.Collection;
import java.util.List;

import com.fintech.gams.shiro.session.RedisSessionDAO;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fintech.gams.exception.AccountPolicyException;
import com.fintech.gams.google.Authenticator;
import com.fintech.gams.shiro.model.AccountToken;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.Resources;
import com.fintech.gams.system.entity.RoleInfo;
import com.fintech.gams.system.entity.UserData;
import com.fintech.gams.system.entity.UserInfo;
import com.fintech.gams.system.service.ResourcesService;
import com.fintech.gams.system.service.RoleInfoService;
import com.fintech.gams.system.service.UserDataService;
import com.fintech.gams.system.service.UserInfoService;
import com.fintech.gams.utils.EncryptUtils;
import com.fintech.gams.utils.ListUtils;
import com.google.common.collect.Lists;

/**
 * <p>File：BossAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: BossAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: GAMS</p>
 *
 * @author playguy
 * @version 1.0
 */
@Component("bossAuthorizingRealm")
public class BossAuthorizingRealm extends AuthorizingRealm
{
    @Autowired(required = false)
    private UserInfoService  userInfoService;
    
    @Autowired(required = false)
    private RoleInfoService  roleInfoService;
    
    @Autowired(required = false)
    private ResourcesService resourcesService;
    
    @Autowired(required = false)
    private UserDataService  userDataService;
    
    /**
     * 认证回调函数, 登录时调用
     * <p>
     * 一次性将用户认证、操作权限等信息放到用户会话中
     * </p>
     *
     * @param authToken
     * @return {@link AuthenticationInfo}
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException
    {
        AccountToken token = (AccountToken) authToken;
        if (StringUtils.isBlank(token.getUsername()))
        { throw new UnknownAccountException("用户名不可为空!"); }
        UserInfo userInfo = userInfoService.findByUserName(token.getUsername());
        if (null == userInfo)
        { throw new UnknownAccountException("用户不存在!"); }
        if (!userInfo.verifySignature())
        {// 加入冻结的用户筛选
            throw new LockedAccountException("user data error!");
        }
        if (!EncryptUtils.validatePassword(String.valueOf(token.getPassword()), userInfo.getPassword()))
        {
            // 验证帐户密码
            throw new AuthenticationException("用户密码验证失败!");
        }
        if (StringUtils.isNotBlank(userInfo.getAuthKey()))
        {
            // 绑定过GA的用户强制进行GA码校验
            if (!validGaCode(userInfo.getAuthKey(), token.getGaCode()))
            {
                // GA验证不通过后直接执行
                token.setId(userInfo.getId());
                throw new AccountPolicyException("policy valid error!");
            }
        }
        List<RoleInfo> roles = roleInfoService.findByUserId(userInfo.getId());
        if (ListUtils.isNotNull(roles))
        {
            for (RoleInfo role : roles)
            {
                List<Resources> resources = resourcesService.findByRoleId(role.getId());
                role.setResources(resources);
            }
        }
        List<Long> orgIds = Lists.newArrayList();
        List<UserData> userData = userDataService.findList(new UserData(userInfo.getId()));
        for (UserData data : userData)
        {
            orgIds.add(data.getOrgId());
        }
        // 清理其它用户，保障一个帐户同时只能登陆一个
        cleanOtherUsers(userInfo.getId());
        UserPrincipal userPrincipal = new UserPrincipal(userInfo.getId(), userInfo.getUserName(), userInfo.getTrueName(), roles, orgIds);
        return new SimpleAuthenticationInfo(userPrincipal, userInfo.getPassword(), getName());
    }
    
    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     *
     * @param principals
     * @return {@link AuthenticationInfo}
     * @throws AuthenticationException
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        UserPrincipal principal = (UserPrincipal) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<RoleInfo> roles = principal.getRoles();
        if (ListUtils.isNotNull(roles))
        {
            for (RoleInfo role : roles)
            {
                authorizationInfo.addRole(role.getRoleCode());
                List<Resources> resources = role.getResources();
                if (ListUtils.isNotNull(roles))
                {
                    for (Resources res : resources)
                    {
                        authorizationInfo.addStringPermission(res.getResCode());
                    }
                }
            }
        }
        return authorizationInfo;
    }
    
    /**
     * 添加自定义认证器
     *
     * @param credentialsMatcher
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher)
    {
        super.setCredentialsMatcher(new UserCredentialsMatcher());
    }
    
    /**
     * 验证GA码
     *
     * @param authKey
     * @param validCode
     * @return
     */
    protected boolean validGaCode(String authKey, String validCode)
    {
        boolean flag = false;
        if (StringUtils.isBlank(authKey) || StringUtils.isBlank(validCode))
        { return flag; }
        Authenticator authenticator = new Authenticator();
        if (authenticator.checkCode(EncryptUtils.desDecrypt(authKey), Long.valueOf(validCode)))
        {
            flag = true;
        }
        return flag;
    }

    /**
     * 清理其它用户，保障一个帐户同时只能登陆一个
     * @param userId
     */
    void cleanOtherUsers(Long userId)
    {
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        CustomWebSessionManager sessionManager = (CustomWebSessionManager) securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        for (Session session : sessions)
        {
            if(null != session.getAttribute(RedisSessionDAO.PRINCIPAL_ID)){
                if (userId.equals(Long.valueOf(String.valueOf(session.getAttribute(RedisSessionDAO.PRINCIPAL_ID)))))
                { // 清除该用户以前登录时保存的session
                    sessionManager.getSessionDAO().delete(session);
                }
            }
        }
    }
}
