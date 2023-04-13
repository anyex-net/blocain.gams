package com.fintech.gams.shiro;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.alibaba.fastjson.JSON;
import com.fintech.gams.shiro.model.OAuth2Token;
import com.fintech.gams.shiro.model.UserPrincipal;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>File：OAuth2ClientAuthorizingRealm.java </p>
 * <p>Title: 系统安全认证实现类 </p>
 * <p>Description: OAuth2ClientAuthorizingRealm </p>
 * <p>Copyright: Copyright (c) 2014 08/08/2015 15:42</p>
 * <p>Company: GAMS</p>
 *
 * @author playguy
 * @version 1.0
 */
@Slf4j
@Data
public class OAuth2ClientAuthorizingRealm extends AuthorizingRealm
{
    private String clientId;
    
    private String clientSecret;
    
    private String accessTokenUrl;
    
    private String userInfoUrl;
    
    private String redirectUrl;
    
    @Override
    public boolean supports(AuthenticationToken token)
    {// 此Realm 只支持 OAuth2Token 类型
        return token instanceof OAuth2Token;
    }
    
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }
    
    /**
     * 身份认证
     * */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException
    {
        OAuth2Token oAuth2Token = (OAuth2Token) token;
        String code = oAuth2Token.getAuthCode(); // 获取 auth code
        log.info("客户端Relam中获取到的auth_code=" + code);
        UserPrincipal principal = getUserInfoByCode(code); // 获取用户名
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, code, getName());
        return authenticationInfo;
    }
    
    private UserPrincipal getUserInfoByCode(String code) throws AuthenticationException
    {
        try
        {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
            OAuthClientRequest accessTokenRequest = OAuthClientRequest//
                    .tokenLocation(accessTokenUrl).setClientId(clientId)//
                    .setClientSecret(clientSecret).setGrantType(GrantType.AUTHORIZATION_CODE)//
                    .setCode(code).setScope("read,write").setRedirectURI(redirectUrl)//
                    .setParameter("state", String.valueOf(System.currentTimeMillis())).buildQueryMessage();
            // 获取 access token
            log.info("clientId=" + clientId + " clientsecret=" + clientSecret);
            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
            String accessToken = oAuthResponse.getAccessToken();
            log.info("客户端Realm中获取到accessToken=" + accessToken);
            // 获取 user info
            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(userInfoUrl).setAccessToken(accessToken).buildQueryMessage();
            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.POST, OAuthResourceResponse.class);
            UserPrincipal principal = JSON.parseObject(resourceResponse.getBody(), UserPrincipal.class);
            principal.setAccessToken(accessToken);
            return principal;
        }
        catch (Exception e)
        {
            log.info("获取用户名发生异常e=", e);
            throw new AuthenticationException(e);
        }
    }
}