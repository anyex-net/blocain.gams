package com.fintech.gams.shiro;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.service.AccountPolicyService;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.AccountPolicyException;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.service.OauthService;
import com.fintech.gams.shiro.model.AccountToken;
import com.fintech.gams.shiro.session.OAuthSessionDAO;
import com.fintech.gams.utils.EncryptUtils;
import com.fintech.gams.utils.HttpUtils;
import com.fintech.gams.utils.StringUtils;
import com.google.common.collect.Maps;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * OAuth2ServerAuthorizingRealm
 * <p>File: OAuth2ServerAuthorizingRealm.java </p>
 * <p>Title: OAuth2ServerAuthorizingRealm </p>
 * <p>Description: OAuth2ServerAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2019-07-04</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Setter
@Slf4j
public class OAuth2ServerAuthorizingRealm extends AuthorizingRealm
{
	private AccountService			accountService;

	private OauthService			oauthService;

	private AccountPolicyService	accountPolicyService;

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
		return new SimpleAuthorizationInfo();
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException
	{
		AccountToken	token	= (AccountToken) authToken;
		Account			account	= accountService.findByName(token.getUsername());
		if (null == account)
			throw new UnknownAccountException("account not exists!");
		// if (!AccountConsts.ACCOUNT_STATUS_NORMAL.equals(account.getStatus()))
		if (AccountConsts.ACCOUNT_STATUS_FROZEN.equals(account.getStatus()))
		{// 加入冻结的用户筛选
			throw new LockedAccountException("account has been frozen!");
		}
		if (!account.verifySignature())
		{// 加入数据签名验证
			throw new LockedAccountException("account data signature error!");
		}
		if (!EncryptUtils.validatePassword(String.valueOf(token.getPassword()), account.getLoginPwd()))
		{// 密码连续错误
			throw new IncorrectCredentialsException("account password error!");
		}
		if (account.getSecurityPolicy() > AccountConsts.SECURITY_POLICY_DEFAULT)
		{// 说明用户启用了其它安全验证策略
			try
			{
				accountPolicyService.validSecurityPolicy(account, token.getPolicy());
			} catch (Exception e)
			{
				token.setId(account.getId());
				token.setGa(null != account.getAuthKey());
				token.setPhone(null != account.getMobile());
				token.setLevel(account.getSecurityPolicy());
				token.setMobNo(account.getMobile());
				throw new AccountPolicyException(CommonEnums.PARAMS_VALID_ERR.code, e.getMessage());
			}
		}
		cleanOtherUsers(String.valueOf(account.getId()));
		return new SimpleAuthenticationInfo(String.valueOf(account.getId()), account.getLoginPwd(), getName());
	}

	/**
	 * 清理其它用户，保障一个帐户同时只能登陆一个
	 * @param accountId
	 */
	void cleanOtherUsers(String accountId)
	{
		try
		{
			// 登陆标记
			boolean						loginFlag		= false;
			// 处理session
			DefaultWebSecurityManager	securityManager	= (DefaultWebSecurityManager) SecurityUtils
					.getSecurityManager();
			DefaultWebSessionManager	sessionManager	= (DefaultWebSessionManager) securityManager
					.getSessionManager();
			Collection<Session>			sessions		= sessionManager.getSessionDAO().getActiveSessions();	// 获取当前已登录的用户session列表
			if (null != sessions && sessions.size() > 0)
			{// 清除OAUTH服务器中该用户以前登录时保存的session
				for (Session session : sessions)
				{
					if (null != session.getAttribute(OAuthSessionDAO.PRINCIPAL_ID))
					{ // 空指针处理
						String principalId = (String) session.getAttribute(OAuthSessionDAO.PRINCIPAL_ID);
						if (null != principalId && accountId.equals(principalId))
						{ // 清除该用户以前登录时保存的session
							loginFlag = true;
							sessionManager.getSessionDAO().delete(session);
						}
					}
				}
			}
			if (loginFlag)
			{// 如果在此帐户登陆之前有登陆会话则处理其它子系统会话状态
				List<String> tokens = oauthService.loadAccessTokenByUserName(accountId);
				for (String token : tokens)
				{
					ClientDetails clientDetails = oauthService.loadClientDetails(token);
					if (null != clientDetails && StringUtils.isNotBlank(clientDetails.getLogoutUri()))
					{// 向子系统发起退出请求
						Map<String, String> params = Maps.newHashMap();
						params.put("accountId", accountId);
						HttpUtils.post(clientDetails.getLogoutUri(), params);
					}
				}
			}
		} catch (Exception e)
		{
			log.error("清理其它用户时异常:{}", e.getLocalizedMessage());
		}
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
}
