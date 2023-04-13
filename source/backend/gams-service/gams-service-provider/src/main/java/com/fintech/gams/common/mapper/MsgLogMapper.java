/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.MsgLog;

/**
 * 消息发送日志 持久层接口
 * <p>File：MsgLogMapper.java </p>
 * <p>Title: MsgLogMapper </p>
 * <p>Description:MsgLogMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface MsgLogMapper extends GenericMapper<MsgLog>
{

}
