/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.UserLog;

/**
 * 用户日志 持久层接口
 * <p>File：UserLogMapper.java </p>
 * <p>Title: UserLogMapper </p>
 * <p>Description:UserLogMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserLogMapper extends GenericMapper<UserLog>
{

}
