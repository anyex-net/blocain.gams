/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.mapper.AccessTokenMapper;
import com.fintech.gams.utils.StringUtils;

/**
 * ACCESS_TOKEN 服务实现类
 * <p>File：AccessTokenServiceImpl.java </p>
 * <p>Title: AccessTokenServiceImpl </p>
 * <p>Description:AccessTokenServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class AccessTokenServiceImpl extends GenericServiceImpl<AccessToken> implements AccessTokenService
{
    protected AccessTokenMapper accessTokenMapper;
    
    @Autowired(required = false)
    public AccessTokenServiceImpl(AccessTokenMapper accessTokenMapper)
    {
        super(accessTokenMapper);
        this.accessTokenMapper = accessTokenMapper;
    }
    
    @Override
    public AccessToken findByRefreshToken(String refreshToken, String clientId) throws BusinessException
    {
        if (StringUtils.isBlank(refreshToken) || StringUtils.isBlank(clientId)) return null;
        return accessTokenMapper.loadAccessTokenByRefreshToken(refreshToken, clientId);
    }
    
    @Override
    public AccessToken findAccessToken(String clientId, String userName, String authenticationId) throws BusinessException
    {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(clientId) || StringUtils.isBlank(authenticationId)) return null;
        return accessTokenMapper.findAccessToken(clientId, userName, authenticationId);
    }
    
    @Override
    public AccessToken loadAccessTokenByTokenId(String accessToken) throws BusinessException
    {
        return accessTokenMapper.loadAccessTokenByTokenId(accessToken);
    }
    
    @Override
    public List<String> loadAccessTokenByUserName(String userName) throws BusinessException
    {
        return accessTokenMapper.loadAccessTokenByUserName(userName);
    }
}
