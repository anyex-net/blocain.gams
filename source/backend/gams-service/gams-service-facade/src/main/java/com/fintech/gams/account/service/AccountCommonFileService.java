/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCommonFile;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 公共的文件 服务接口
 * <p>File：AccountCommonFileService.java </p>
 * <p>Title: AccountCommonFileService </p>
 * <p>Description:AccountCommonFileService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCommonFileService extends GenericService<AccountCommonFile> {
    /**
     * 根据指定的 公共的文件的种类 批量查询
     *
     * @param commonFileKindArr
     * @param lang
     * @return
     * @throws BusinessException
     */
    List<AccountCommonFile> findByKindArr(Long[] commonFileKindArr, String lang) throws BusinessException;
}
