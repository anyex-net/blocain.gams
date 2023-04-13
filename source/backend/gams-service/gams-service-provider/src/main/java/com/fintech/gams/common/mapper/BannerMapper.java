/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Banner;
import org.apache.ibatis.annotations.Mapper;

/**
 * Banner信息 持久层接口
 * <p>File：BannerMapper.java </p>
 * <p>Title: BannerMapper </p>
 * <p>Description:BannerMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface BannerMapper extends GenericMapper<Banner>
{

}
