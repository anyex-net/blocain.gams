package com.fintech.gams.oauth2.service;

import java.util.List;
import java.util.Set;

import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.entity.OauthCode;

import lombok.extern.slf4j.Slf4j;

/**
 * OauthServiceImpl
 * <p>File: OauthServiceImpl.java </p>
 * <p>Title: OauthServiceImpl </p>
 * <p>Description: OauthServiceImpl </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class OauthServiceImpl implements OauthService
{
    @Autowired
    private ClientDetailsService      clientDetailsService;
    
    @Autowired
    private AccessTokenService        accessTokenService;
    
    @Autowired
    private OauthCodeService          oauthCodeService;
    
    @Autowired
    private AuthenticationIdGenerator authenticationIdGenerator;
    
    @Autowired
    private OAuthIssuer               oAuthIssuer;
    
    @Override
    public ClientDetails loadClientDetails(String clientId)
    {
        return clientDetailsService.findClientDetails(clientId);
    }
    
    @Override
    public AccessToken retrieveAccessToken(ClientDetails clientDetails, String userName, Set<String> scopes, boolean includeRefreshToken) throws OAuthSystemException
    {
        String scope = OAuthUtils.encodeScopes(scopes);
        final String clientId = clientDetails.getClientId();
        final String authenticationId = authenticationIdGenerator.generate(clientId, userName, scope);
        AccessToken accessToken = accessTokenService.findAccessToken(clientId, userName, authenticationId);
        if (accessToken == null)
        {
            accessToken = createAndSaveAccessToken(clientDetails, includeRefreshToken, userName, authenticationId);
            log.debug("Create a new AccessToken: {}", accessToken);
        }
        return accessToken;
    }
    
    @Override
    public AccessToken retrieveNewAccessToken(ClientDetails clientDetails, String userName, Set<String> scopes) throws OAuthSystemException
    {
        String scope = OAuthUtils.encodeScopes(scopes);
        final String clientId = clientDetails.getClientId();
        final String authenticationId = authenticationIdGenerator.generate(clientId, userName, scope);
        AccessToken accessToken = accessTokenService.findAccessToken(clientId, userName, authenticationId);
        if (accessToken != null)
        {
            log.debug("Delete existed AccessToken: {}", accessToken);
            accessTokenService.remove(accessToken.getId());
        }
        accessToken = createAndSaveAccessToken(clientDetails, false, userName, authenticationId);
        log.debug("Create a new AccessToken: {}", accessToken);
        return accessToken;
    }
    
    @Override
    public AccessToken retrieveAuthorizationCodeAccessToken(ClientDetails clientDetails, String code) throws OAuthSystemException
    {
        final OauthCode oauthCode = loadOauthCode(code, clientDetails);
        String userName = oauthCode.getUserName();
        final String clientId = clientDetails.getClientId();
        final String authenticationId = authenticationIdGenerator.generate(clientId, userName, null);
        AccessToken accessToken = accessTokenService.findAccessToken(clientId, userName, authenticationId);
        if (accessToken != null)
        {
            log.debug("Delete existed AccessToken: {}", accessToken);
            accessTokenService.remove(accessToken.getId());
        }
        accessToken = createAndSaveAccessToken(clientDetails, clientDetails.supportRefreshToken(), userName, authenticationId);
        log.debug("Create a new AccessToken: {}", accessToken);
        return accessToken;
    }
    
    @Override
    public AccessToken retrievePasswordAccessToken(ClientDetails clientDetails, Set<String> scopes, String userName) throws OAuthSystemException
    {
        String scope = OAuthUtils.encodeScopes(scopes);
        final String clientId = clientDetails.getClientId();
        final String authenticationId = authenticationIdGenerator.generate(clientId, userName, scope);
        AccessToken accessToken = accessTokenService.findAccessToken(clientId, userName, authenticationId);
        boolean needCreate = false;
        if (accessToken == null)
        {
            needCreate = true;
            log.debug("Not found AccessToken from repository, will create a new one, client_id: {}", clientId);
        }
        else if (accessToken.tokenExpired())
        {
            log.debug("Delete expired AccessToken: {} and create a new one, client_id: {}", accessToken, clientId);
            accessTokenService.remove(accessToken.getId());
            needCreate = true;
        }
        else
        {
            log.debug("Use existed AccessToken: {}, client_id: {}", accessToken, clientId);
        }
        if (needCreate)
        {
            accessToken = createAndSaveAccessToken(clientDetails, clientDetails.supportRefreshToken(), userName, authenticationId);
            log.debug("Create a new AccessToken: {}", accessToken);
        }
        return accessToken;
    }
    
    @Override
    public AccessToken retrieveClientCredentialsAccessToken(ClientDetails clientDetails, Set<String> scopes) throws OAuthSystemException
    {
        String scope = OAuthUtils.encodeScopes(scopes);
        final String clientId = clientDetails.getClientId();
        final String authenticationId = authenticationIdGenerator.generate(clientId, clientId, scope);
        AccessToken accessToken = accessTokenService.findAccessToken(clientId, clientId, authenticationId);
        boolean needCreate = false;
        if (accessToken == null)
        {
            needCreate = true;
            log.debug("Not found AccessToken from repository, will create a new one, client_id: {}", clientId);
        }
        else if (accessToken.tokenExpired())
        {
            log.debug("Delete expired AccessToken: {} and create a new one, client_id: {}", accessToken, clientId);
            accessTokenService.remove(accessToken.getId());
            needCreate = true;
        }
        else
        {
            log.debug("Use existed AccessToken: {}, client_id: {}", accessToken, clientId);
        }
        if (needCreate)
        {// Ignore refresh_token
            accessToken = createAndSaveAccessToken(clientDetails, false, clientId, authenticationId);
            log.debug("Create a new AccessToken: {}", accessToken);
        }
        return accessToken;
    }
    
    @Override
    public AccessToken loadAccessTokenByRefreshToken(String refreshToken, String clientId)
    {
        return accessTokenService.findByRefreshToken(refreshToken, clientId);
    }
    
    @Override
    public AccessToken changeAccessTokenByRefreshToken(String refreshToken, String clientId) throws OAuthSystemException
    {
        final AccessToken oldToken = loadAccessTokenByRefreshToken(refreshToken, clientId);
        AccessToken newAccessToken = oldToken.cloneMe();
        log.debug("Create new AccessToken: {} from old AccessToken: {}", newAccessToken, oldToken);
        ClientDetails details = clientDetailsService.findClientDetails(clientId);
        newAccessToken.updateByClientDetails(details);
        final String authId = authenticationIdGenerator.generate(clientId, oldToken.getUserName(), null);
        newAccessToken.setAuthenticationId(authId);
        newAccessToken.setTokenId(oAuthIssuer.accessToken());
        newAccessToken.setCreateTime(System.currentTimeMillis());
        accessTokenService.remove(oldToken.getId());
        log.debug("Delete old AccessToken: {}", oldToken);
        accessTokenService.save(newAccessToken);
        log.debug("Save new AccessToken: {}", newAccessToken);
        return newAccessToken;
    }
    
    @Override
    public String retrieveAuthCode(String userName, ClientDetails clientDetails) throws OAuthSystemException
    {
        final String clientId = clientDetails.getClientId();
        OauthCode oauthCode = oauthCodeService.findByUserNameClientId(userName, clientId);
        if (oauthCode != null)
        {// Always delete exist
            log.debug("OauthCode ({}) is existed, remove it and create a new one", oauthCode);
            oauthCodeService.remove(oauthCode.getId());
        }
        // create a new one
        oauthCode = createOauthCode(userName, clientDetails);
        return oauthCode.getCode();
    }
    
    @Override
    public OauthCode saveOauthCode(String authCode, String userName, ClientDetails clientDetails)
    {
        OauthCode oauthCode = new OauthCode();
        oauthCode.setCode(authCode);
        oauthCode.setUserName(userName);
        oauthCode.setClientId(clientDetails.getClientId());
        oauthCode.setCreateTime(System.currentTimeMillis());
        oauthCodeService.save(oauthCode);
        return oauthCode;
    }
    
    @Override
    public OauthCode loadOauthCode(String code, ClientDetails clientDetails)
    {
        return oauthCodeService.findByCodeClientId(code, clientDetails);
    }
    
    @Override
    public boolean removeOauthCode(String code, ClientDetails clientDetails)
    {
        final OauthCode oauthCode = loadOauthCode(code, clientDetails);
        return oauthCodeService.remove(oauthCode.getId()) > 0;
    }
    
    @Override
    public boolean isExistedClientId(String clientId)
    {
        final ClientDetails clientDetails = loadClientDetails(clientId);
        return clientDetails != null;
    }
    
    private OauthCode createOauthCode(String userName, ClientDetails clientDetails) throws OAuthSystemException
    {
        OauthCode oauthCode;
        final String authCode = oAuthIssuer.authorizationCode();
        log.debug("Save authorizationCode '{}' of ClientDetails '{}'", authCode, clientDetails);
        oauthCode = this.saveOauthCode(authCode, userName, clientDetails);
        return oauthCode;
    }
    
    private AccessToken createAndSaveAccessToken(ClientDetails clientDetails, boolean includeRefreshToken, String userName, String authenticationId)
            throws OAuthSystemException
    {
        AccessToken accessToken = new AccessToken();
        accessToken.setUserName(userName);
        accessToken.setTokenId(oAuthIssuer.accessToken());
        accessToken.setAuthenticationId(authenticationId);
        accessToken.updateByClientDetails(clientDetails);
        if (includeRefreshToken)
        {
            accessToken.setRefreshToken(oAuthIssuer.refreshToken());
        }
        accessToken.setCreateTime(System.currentTimeMillis());
        accessTokenService.save(accessToken);
        log.debug("Save AccessToken: {}", accessToken);
        return accessToken;
    }
    
    /**
     * 根据授权ID取授权信息
     * @param accessToken
     * @return
     * @throws BusinessException
     */
    @Override
    public AccessToken loadAccessTokenByTokenId(String accessToken) throws BusinessException
    {
        return accessTokenService.loadAccessTokenByTokenId(accessToken);
    }
    
    @Override
    public List<String> loadAccessTokenByUserName(String userName) throws BusinessException
    {
        return accessTokenService.loadAccessTokenByUserName(userName);
    }
}
