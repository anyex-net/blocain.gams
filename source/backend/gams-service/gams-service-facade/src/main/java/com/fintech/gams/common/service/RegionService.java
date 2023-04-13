/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.common.entity.Region;

/**
 * 区域代码 服务接口
 * <p>File：RegionService.java </p>
 * <p>Title: RegionService </p>
 * <p>Description:RegionService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface RegionService extends GenericService<Region>{

    /**
     * 根据sCode获取国家区域信息
     * @param sCode
     * @return
     */
    Region findBysCode(String sCode);

}
