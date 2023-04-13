/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.entity.OauthCode;
import com.fintech.gams.oauth2.mapper.OauthCodeMapper;
import com.fintech.gams.utils.StringUtils;

/**
 * OAUTH_CODE 服务实现类
 * <p>File：OauthCodeServiceImpl.java </p>
 * <p>Title: OauthCodeServiceImpl </p>
 * <p>Description:OauthCodeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class OauthCodeServiceImpl extends GenericServiceImpl<OauthCode> implements OauthCodeService
{
    protected OauthCodeMapper oauthCodeMapper;
    
    @Autowired(required = false)
    public OauthCodeServiceImpl(OauthCodeMapper oauthCodeMapper)
    {
        super(oauthCodeMapper);
        this.oauthCodeMapper = oauthCodeMapper;
    }
    
    @Override
    public OauthCode findByCodeClientId(String code, ClientDetails clientDetail) throws BusinessException
    {
        if (StringUtils.isBlank(code) || Objects.isNull(clientDetail)) return null;
        return oauthCodeMapper.findByCodeClientId(code, clientDetail.getClientId());
    }
    
    @Override
    public OauthCode findByUserNameClientId(String userName, String clientId) throws BusinessException
    {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(clientId)) return null;
        return oauthCodeMapper.findByUserNameClientId(userName, clientId);
    }
}
