/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.UserRole;

/**
 * 用户角色权限表 持久层接口
 * <p>File：UserRoleDao.java </p>
 * <p>Title: UserRoleDao </p>
 * <p>Description:UserRoleDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UserRoleMapper extends GenericMapper<UserRole>
{
    /**
     * 根据用户ID删除
     *
     * @param userId
     */
    void removeByUser(Long userId);
}
