/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.mapper.RegionMapper;

import java.util.List;

/**
 * 区域代码 服务实现类
 * <p>File：RegionServiceImpl.java </p>
 * <p>Title: RegionServiceImpl </p>
 * <p>Description:RegionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class RegionServiceImpl extends GenericServiceImpl<Region> implements RegionService
{

    protected RegionMapper regionMapper;

    @Autowired(required = false)
    public RegionServiceImpl(RegionMapper regionMapper)
    {
        super(regionMapper);
        this.regionMapper = regionMapper;
    }

    @Override
    public Region findBysCode(String sCode) {
        if(StringUtils.isBlank(sCode)){
            return new Region();
        }
        Region region = new Region();
        region.setsCode(sCode);
        List<Region> regions = regionMapper.findList(region);
        if(regions.size()>0){
            region = regions.get(0);
        }
        return region;
    }
}
