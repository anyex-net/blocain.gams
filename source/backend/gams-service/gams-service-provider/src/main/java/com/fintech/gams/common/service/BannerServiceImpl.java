/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Banner;
import com.fintech.gams.common.mapper.BannerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Banner信息 服务实现类
 * <p>File：BannerServiceImpl.java </p>
 * <p>Title: BannerServiceImpl </p>
 * <p>Description:BannerServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class BannerServiceImpl extends GenericServiceImpl<Banner> implements BannerService
{

    protected BannerMapper bannerMapper;

    @Autowired(required = false)
    public BannerServiceImpl(BannerMapper bannerMapper)
    {
        super(bannerMapper);
        this.bannerMapper = bannerMapper;
    }
}
