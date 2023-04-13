/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import com.fintech.gams.bean.PaginateResult;
import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.common.entity.HelpCenter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 帮助中心 持久层接口
 * <p>File：HelpCenterMapper.java </p>
 * <p>Title: HelpCenterMapper </p>
 * <p>Description:HelpCenterMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface HelpCenterMapper extends GenericMapper<HelpCenter>
{

    /**
     * 根据问题类型和问题（一级）查询列表
     * @param problemType
     * @param problemLevelOne
     * @param lang
     * @return
     */
    List<HelpCenter> classifyInfo(@Param("problemType") String problemType, @Param("problemLevelOne") String problemLevelOne, @Param("lang") String lang);

    /**
     * 搜索帮助中心
     * @param searchValue
     * @param lang
     * @return
     */
    List<HelpCenter> searchHelpCenter(@Param("searchValue") String searchValue, @Param("lang") String lang);
}
