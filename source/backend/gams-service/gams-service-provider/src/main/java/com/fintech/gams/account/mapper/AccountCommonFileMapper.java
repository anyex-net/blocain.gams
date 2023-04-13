/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCommonFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公共的文件 持久层接口
 * <p>File：AccountCommonFileMapper.java </p>
 * <p>Title: AccountCommonFileMapper </p>
 * <p>Description:AccountCommonFileMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCommonFileMapper extends GenericMapper<AccountCommonFile>
{
    /**
     * 根据指定的 公共的文件的种类 批量查询
     *
     * @param commonFileKindArr
     * @param lang
     * @return
     */
    List<AccountCommonFile> findByKindArr(@Param("kind") Long[] commonFileKindArr, @Param("lang") String lang);
}
