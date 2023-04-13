/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.oauth2.entity.AccessToken;

import java.util.List;

/**
 * ACCESS_TOKEN 持久层接口
 * <p>File：AccessTokenMapper.java </p>
 * <p>Title: AccessTokenMapper </p>
 * <p>Description:AccessTokenMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccessTokenMapper extends GenericMapper<AccessToken>
{
    /**
     * 根据刷新口令和应用ID取授权信息
     * @param refreshToken
     * @param clientId
     * @return
     */
    AccessToken loadAccessTokenByRefreshToken(@Param("refreshToken") String refreshToken, @Param("clientId") String clientId);
    
    /**
     * 根据应用ID、用户名、授权ID取授权信息
     * @param clientId
     * @param userName
     * @param authenticationId
     * @return
     */
    AccessToken findAccessToken(@Param("clientId") String clientId, @Param("userName") String userName, @Param("authenticationId") String authenticationId);

    /**
     * 权限授权ID取授权信息
     * @param tokenId
     * @return
     */
    AccessToken loadAccessTokenByTokenId(String tokenId);

    /**
     * 用户名取授权信息
     * @param userName
     * @return
     */
    List<String> loadAccessTokenByUserName(String userName);

}
