/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Feedback;

/**
 * FEEDBACK 服务接口
 * <p>File：Feedbackservice.java </p>
 * <p>Title: Feedbackservice </p>
 * <p>Description:Feedbackservice </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface FeedbackService extends GenericService<Feedback>{
    /**
     * 查询反馈信息
     * @param pagin
     * @param entity
     * @return
     */
    PaginateResult<Feedback> findInfoList(Pagination pagin, Feedback entity);
}
