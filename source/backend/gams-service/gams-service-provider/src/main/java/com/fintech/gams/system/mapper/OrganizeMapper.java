/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.system.entity.Organize;

import java.util.List;

/**
 * 组织机构信息 持久层接口
 * <p>File：OrganizeMapper.java </p>
 * <p>Title: OrganizeMapper </p>
 * <p>Description:OrganizeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface OrganizeMapper extends GenericMapper<Organize>
{
    /**
     * 根据用户编号取机构
     * @param userId
     * @return
     */
    List<Organize> findByUserId(Long userId);
}
