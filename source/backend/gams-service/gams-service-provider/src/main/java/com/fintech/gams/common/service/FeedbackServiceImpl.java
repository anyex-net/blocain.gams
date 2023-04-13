/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Feedback;
import com.fintech.gams.common.mapper.FeedbackMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * FEEDBACK 服务实现类
 * <p>File：FeedbackServiceImpl.java </p>
 * <p>Title: FeedbackServiceImpl </p>
 * <p>Description:FeedbackServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class FeedbackServiceImpl extends GenericServiceImpl<Feedback> implements FeedbackService
{

    protected FeedbackMapper feedbackMapper;

    @Autowired(required = false)
    public FeedbackServiceImpl(FeedbackMapper feedbackMapper)
    {
        super(feedbackMapper);
        this.feedbackMapper = feedbackMapper;
    }

    @Override
    public PaginateResult<Feedback> findInfoList(Pagination pagin, Feedback entity) {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<Feedback> pageInfo = new PageInfo<>();
        //根据更新时间排序
        pageInfo  = PageInfo.of(feedbackMapper.findInfoList(entity));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
