/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.UserData;

/**
 * 数据权限信息 持久层接口
 * <p>File：UserDataMapper.java </p>
 * <p>Title: UserDataMapper </p>
 * <p>Description:UserDataMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserDataMapper extends GenericMapper<UserData>
{
    /**
     * 根据用户编号删除
     * @param userId
     */
    void removeByUser(Long userId);
}
