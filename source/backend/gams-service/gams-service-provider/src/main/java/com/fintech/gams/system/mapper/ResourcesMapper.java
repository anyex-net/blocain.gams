/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.Resources;

/**
 * 资源菜单信息表 持久层接口
 * <p>File：ResourcesDao.java </p>
 * <p>Title: ResourcesDao </p>
 * <p>Description:ResourcesDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface ResourcesMapper extends GenericMapper<Resources>
{
    /**
     * 根据ID取资源菜单
     *
     * @param roleId
     * @return {@link Resources}
     */
    List<Resources> findByRoleId(Long roleId);
    
    /**
     * 根据上线ID取资源
     *
     * @param parentId
     * @return
     */
    List<Resources> findByParentId(Long parentId);
}
