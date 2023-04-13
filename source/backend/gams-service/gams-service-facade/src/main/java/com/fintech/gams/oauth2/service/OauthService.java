package com.fintech.gams.oauth2.service;

import java.util.List;
import java.util.Set;

import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.entity.OauthCode;

/**
 * Oauth2.0 核心服务
 * <p>File: OauthService.java </p>
 * <p>Title: OauthService </p>
 * <p>Description: OauthService </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface OauthService
{
    /**
     * 加载应用
     * @param clientId
     * @return {@link ClientDetails}
     */
    ClientDetails loadClientDetails(String clientId);
    
    /**
     * 生成访问凭证
     * @param clientDetails
     * @param userName
     * @param scopes
     * @param includeRefreshToken
     * @return {@link AccessToken}
     * @throws OAuthSystemException
     */
    AccessToken retrieveAccessToken(ClientDetails clientDetails, String userName, Set<String> scopes, boolean includeRefreshToken) throws OAuthSystemException;
    
    /**
     *  Always return new AccessToken, exclude refreshToken
     * @param clientDetails
     * @param userName
     * @param scopes
     * @return {@link AccessToken}
     * @throws OAuthSystemException
     */
    AccessToken retrieveNewAccessToken(ClientDetails clientDetails, String userName, Set<String> scopes) throws OAuthSystemException;
    
    /**
     * Always return new AccessToken, grant_type=authorization_code
     * @param clientDetails
     * @param code
     * @return {@link AccessToken}
     * @throws OAuthSystemException
     */
    AccessToken retrieveAuthorizationCodeAccessToken(ClientDetails clientDetails, String code) throws OAuthSystemException;
    
    /**
     * grant_type=password AccessToken
     * @param clientDetails
     * @param scopes
     * @param username
     * @return {@link AccessToken}
     * @throws OAuthSystemException
     */
    AccessToken retrievePasswordAccessToken(ClientDetails clientDetails, Set<String> scopes, String username) throws OAuthSystemException;
    
    /**
     * grant_type=client_credentials
     * @param clientDetails
     * @param scopes
     * @return {@link AccessToken}
     * @throws OAuthSystemException
     */
    AccessToken retrieveClientCredentialsAccessToken(ClientDetails clientDetails, Set<String> scopes) throws OAuthSystemException;
    
    /**
     * 根据刷新凭证和应用ID取访问凭证
     * @param refreshToken
     * @param clientId
     * @return {@link AccessToken}
     */
    AccessToken loadAccessTokenByRefreshToken(String refreshToken, String clientId);
    
    /**
     * 根据刷新凭证生成新的访问凭证
     * @param refreshToken
     * @param clientId
     * @return {@link AccessToken}
     * @throws OAuthSystemException
     */
    AccessToken changeAccessTokenByRefreshToken(String refreshToken, String clientId) throws OAuthSystemException;
    
    /**
     * 创建授权码
     * @param userName
     * @param clientDetails
     * @return {@link String}
     * @throws OAuthSystemException
     */
    String retrieveAuthCode(String userName, ClientDetails clientDetails) throws OAuthSystemException;
    
    /**
     * 保存授权码
     * @param authCode
     * @param username
     * @param clientDetails
     * @return {@link OauthCode}
     */
    OauthCode saveOauthCode(String authCode, String username, ClientDetails clientDetails);
    
    /**
     * 加载授权
     * @param code
     * @param clientDetails
     * @return {@link OauthCode}
     */
    OauthCode loadOauthCode(String code, ClientDetails clientDetails);
    
    /**
     * 移除授权
     * @param code
     * @param clientDetails
     * @return {@link Boolean}
     */
    boolean removeOauthCode(String code, ClientDetails clientDetails);
    
    /**
     * 判断应用ID是否存在
     * @param clientId
     * @return {@link Boolean}
     */
    boolean isExistedClientId(String clientId);
    
    /**
     * 检查访问凭证
     * @param accessToken
     * @return
     */
    AccessToken loadAccessTokenByTokenId(String accessToken) throws BusinessException;
    
    /**
     * 根据用户名取凭证中的CLIENT_ID
     * @param userName
     * @return
     * @throws BusinessException
     */
    List<String> loadAccessTokenByUserName(String userName) throws BusinessException;
}
