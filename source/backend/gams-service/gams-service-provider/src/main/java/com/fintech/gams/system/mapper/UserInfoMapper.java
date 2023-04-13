/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.UserInfo;

/**
 * 用户基础信息表 持久层接口
 * <p>File：UserInfoDao.java </p>
 * <p>Title: UserInfoDao </p>
 * <p>Description:UserInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserInfoMapper extends GenericMapper<UserInfo>
{
    /**
     * 根据用户名找用户
     *
     * @param userName
     * @return
     */
    UserInfo findByUserName(String userName);
}
