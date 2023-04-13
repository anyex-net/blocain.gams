/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.RoleInfo;

/**
 * 角色信息表 持久层接口
 * <p>File：RoleInfoDao.java </p>
 * <p>Title: RoleInfoDao </p>
 * <p>Description:RoleInfoDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RoleInfoMapper extends GenericMapper<RoleInfo>
{
    /**
     * 根据用户ID取角色
     *
     * @param userId
     * @return
     */
    List<RoleInfo> findByUserId(Long userId);
}
