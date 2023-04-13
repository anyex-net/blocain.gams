/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Region;

import java.util.List;

/**
 * 区域代码 持久层接口
 * <p>File：RegionMapper.java </p>
 * <p>Title: RegionMapper </p>
 * <p>Description:RegionMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface RegionMapper extends GenericMapper<Region>
{

    /**
     * 根据国家代码取区域
     * @param sCode
     * @return
     */
    Region findRegionBySCode(String sCode);

    /**
     * 根据国际代码取区域
     *
     * @param lCode
     * @return
     */
    List<Region> findRegionByLCode(String lCode);
}
