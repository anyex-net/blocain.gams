/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.system.entity.UserLog;
import com.fintech.gams.system.mapper.UserLogMapper;

/**
 * 用户日志 服务实现类
 * <p>File：UserLogServiceImpl.java </p>
 * <p>Title: UserLogServiceImpl </p>
 * <p>Description:UserLogServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserLogServiceImpl extends GenericServiceImpl<UserLog> implements UserLogService
{
    protected UserLogMapper userLogMapper;
    
    @Autowired(required = false)
    public UserLogServiceImpl(UserLogMapper userLogMapper)
    {
        super(userLogMapper);
        this.userLogMapper = userLogMapper;
    }
}
