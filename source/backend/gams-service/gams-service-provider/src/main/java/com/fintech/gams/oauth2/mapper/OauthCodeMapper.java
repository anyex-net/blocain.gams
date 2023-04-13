/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.mapper;

import com.fintech.gams.exception.BusinessException;
import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.oauth2.entity.OauthCode;
import org.apache.ibatis.annotations.Param;

/**
 * OAUTH_CODE 持久层接口
 * <p>File：OauthCodeMapper.java </p>
 * <p>Title: OauthCodeMapper </p>
 * <p>Description:OauthCodeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface OauthCodeMapper extends GenericMapper<OauthCode>
{
    /**
     * 根据授权码和应用ID取授权信息
     * @param code
     * @param clientId
     * @return
     */
    OauthCode findByCodeClientId(@Param("code") String code, @Param("clientId") String clientId);


    /**
     * 根据用户名和应用ID取授权信息
     * @param userName
     * @param clientId
     * @return
     */
    OauthCode findByUserNameClientId(@Param("userName") String userName, @Param("clientId") String clientId);
}
