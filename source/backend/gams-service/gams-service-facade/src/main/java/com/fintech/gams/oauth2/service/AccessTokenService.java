/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.service;

import java.util.List;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.AccessToken;

/**
 * ACCESS_TOKEN 服务接口
 * <p>File：AccessTokenService.java </p>
 * <p>Title: AccessTokenService </p>
 * <p>Description:AccessTokenService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccessTokenService extends GenericService<AccessToken>
{
    /**
     * 根据刷新口令和应用ID取授权信息
     * @param refreshToken
     * @param clientId
     * @return
     * @throws BusinessException
     */
    AccessToken findByRefreshToken(String refreshToken, String clientId) throws BusinessException;
    
    /**
     * 根据应用ID、用户名、授权ID取授权信息
     * @param clientId
     * @param userName
     * @param authenticationId
     * @return
     * @throws BusinessException
     */
    AccessToken findAccessToken(String clientId, String userName, String authenticationId) throws BusinessException;
    
    /**
     * 根据授权ID取授权信息
     * @param accessToken
     * @return
     * @throws BusinessException
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
