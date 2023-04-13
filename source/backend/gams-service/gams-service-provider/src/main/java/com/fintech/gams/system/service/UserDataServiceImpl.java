/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.system.entity.UserData;
import com.fintech.gams.system.mapper.UserDataMapper;

/**
 * 数据权限信息 服务实现类
 * <p>File：UserDataServiceImpl.java </p>
 * <p>Title: UserDataServiceImpl </p>
 * <p>Description:UserDataServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserDataServiceImpl extends GenericServiceImpl<UserData> implements UserDataService
{
    protected UserDataMapper userDataMapper;

    @Autowired(required = false)
    public UserDataServiceImpl(UserDataMapper userDataMapper)
    {
        super(userDataMapper);
        this.userDataMapper = userDataMapper;
    }
}
