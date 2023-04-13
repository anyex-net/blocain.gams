/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.HelpCenter;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 帮助中心 服务接口
 * <p>File：HelpCenterService.java </p>
 * <p>Title: HelpCenterService </p>
 * <p>Description:HelpCenterService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface HelpCenterService extends GenericService<HelpCenter>{

    /**
     * 根据问题类型和问题（一级）查询列表
     * @param pagin
     * @param problemType
     * @param problemLevelOne
     * @param lang
     * @return
     * @throws BusinessException
     */
    PaginateResult<HelpCenter> classifyInfo(Pagination pagin, String problemType, String problemLevelOne, String lang) throws BusinessException;

    /**
     * 搜索帮助中心
     * @param searchValue
     * @param pagin
     * @param lang
     * @return
     * @throws BusinessException
     */
    PaginateResult<HelpCenter> searchHelpCenter(String searchValue, Pagination pagin, String lang) throws BusinessException;
}
