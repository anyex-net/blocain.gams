/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.oauth2.entity.ClientDetails;

/**
 * CLIENT_DETAILS 服务接口
 * <p>File：ClientDetailsService.java </p>
 * <p>Title: ClientDetailsService </p>
 * <p>Description:ClientDetailsService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface ClientDetailsService extends GenericService<ClientDetails>
{
    /**
     * 根据应用ID取应用详情
     * @param clientId
     * @return
     * @throws BusinessException
     */
    ClientDetails findClientDetails(String clientId) throws BusinessException;

}
