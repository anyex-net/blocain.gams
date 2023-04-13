/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.oauth2.entity.ClientDetails;

/**
 * CLIENT_DETAILS 持久层接口
 * <p>File：ClientDetailsMapper.java </p>
 * <p>Title: ClientDetailsMapper </p>
 * <p>Description:ClientDetailsMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ClientDetailsMapper extends GenericMapper<ClientDetails>
{
    /**
     * 加载应用
     * @param clientId
     * @return {@link ClientDetails}
     */
    ClientDetails loadClientDetails(String clientId);
}
