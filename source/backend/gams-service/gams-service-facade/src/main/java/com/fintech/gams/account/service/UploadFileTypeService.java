/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.UploadFileType;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 上传文件类型 服务接口
 * <p>File：UploadFileTypeService.java </p>
 * <p>Title: UploadFileTypeService </p>
 * <p>Description:UploadFileTypeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface UploadFileTypeService extends GenericService<UploadFileType>{

    /**
     * 根据指定的 文件类型种类 批量查询
     *
     * @param kindArr
     * @param lang
     * @param accountType
     * @return
     * @throws BusinessException
     */
    List<UploadFileType> findByKindArr(Long[] kindArr, String lang, String accountType) throws BusinessException;
}
