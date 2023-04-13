/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.UploadFileType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 上传文件类型 持久层接口
 * <p>File：UploadFileTypeMapper.java </p>
 * <p>Title: UploadFileTypeMapper </p>
 * <p>Description:UploadFileTypeMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface UploadFileTypeMapper extends GenericMapper<UploadFileType>
{
    /**
     * 根据指定的 文件类型种类 批量查询
     *
     * @param kindArr
     * @param lang
     * @param accountType
     * @return
     */
    List<UploadFileType> findByKindArr(@Param("kind") Long[] kindArr, @Param("lang") String lang, @Param("accountType") String accountType);
}
