/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * FEEDBACK 持久层接口
 * <p>File：FeedbackMapper.java </p>
 * <p>Title: FeedbackMapper </p>
 * <p>Description:FeedbackMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface FeedbackMapper extends GenericMapper<Feedback>
{

    List<Feedback> findInfoList(Feedback entity);
}
