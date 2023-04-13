/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.mapper.ClientDetailsMapper;
import com.fintech.gams.utils.StringUtils;

/**
 * CLIENT_DETAILS 服务实现类
 * <p>File：ClientDetailsServiceImpl.java </p>
 * <p>Title: ClientDetailsServiceImpl </p>
 * <p>Description:ClientDetailsServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class ClientDetailsServiceImpl extends GenericServiceImpl<ClientDetails> implements ClientDetailsService
{
    protected ClientDetailsMapper clientDetailsMapper;
    
    @Autowired(required = false)
    public ClientDetailsServiceImpl(ClientDetailsMapper clientDetailsMapper)
    {
        super(clientDetailsMapper);
        this.clientDetailsMapper = clientDetailsMapper;
    }
    
    @Override
    public ClientDetails findClientDetails(String clientId) throws BusinessException
    {
        return StringUtils.isBlank(clientId) ? null : clientDetailsMapper.loadClientDetails(clientId);
    }
}
