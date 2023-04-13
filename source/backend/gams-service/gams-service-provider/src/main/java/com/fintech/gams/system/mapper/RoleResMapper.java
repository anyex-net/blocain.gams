/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.RoleRes;

/**
 * 角色权限信息表 持久层接口
 * <p>File：RoleResDao.java </p>
 * <p>Title: RoleResDao </p>
 * <p>Description:RoleResDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RoleResMapper extends GenericMapper<RoleRes>
{
    /**
     * 根据角色编号删除权限
     *
     * @param roleId
     * @return
     */
    int removeByRoleId(Long roleId);
}
