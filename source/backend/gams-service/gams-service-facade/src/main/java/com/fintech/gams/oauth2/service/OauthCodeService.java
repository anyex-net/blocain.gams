/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.entity.OauthCode;

/**
 * OAUTH_CODE 服务接口
 * <p>File：OauthCodeService.java </p>
 * <p>Title: OauthCodeService </p>
 * <p>Description:OauthCodeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface OauthCodeService extends GenericService<OauthCode>
{
    /**
     * 根据授权码和应用ID取授权信息
     * @param code
     * @param clientDetail
     * @return
     * @throws BusinessException
     */
    OauthCode findByCodeClientId(String code, ClientDetails clientDetail) throws BusinessException;
    
    /**
     * 根据用户名和应用ID取授权信息
     * @param userName
     * @param clientId
     * @return
     * @throws BusinessException
     */
    OauthCode findByUserNameClientId(String userName, String clientId) throws BusinessException;
}
