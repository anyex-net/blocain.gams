/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.common.entity.HelpCenter;
import com.fintech.gams.common.mapper.HelpCenterMapper;

import java.util.List;

/**
 * 帮助中心 服务实现类
 * <p>File：HelpCenterServiceImpl.java </p>
 * <p>Title: HelpCenterServiceImpl </p>
 * <p>Description:HelpCenterServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = HelpCenterService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class HelpCenterServiceImpl extends GenericServiceImpl<HelpCenter> implements HelpCenterService
{

    protected HelpCenterMapper helpCenterMapper;

    @Autowired(required = false)
    public HelpCenterServiceImpl(HelpCenterMapper helpCenterMapper)
    {
        super(helpCenterMapper);
        this.helpCenterMapper = helpCenterMapper;
    }

    @Override
    public PaginateResult<HelpCenter> classifyInfo(Pagination pagin, String problemType, String problemLevelOne, String lang) throws BusinessException {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HelpCenter> pageInfo = PageInfo.of(helpCenterMapper.classifyInfo(problemType, problemLevelOne, lang));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<HelpCenter> searchHelpCenter(String searchValue, Pagination pagin, String lang) throws BusinessException {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HelpCenter> pageInfo = PageInfo.of(helpCenterMapper.searchHelpCenter(searchValue, lang));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
