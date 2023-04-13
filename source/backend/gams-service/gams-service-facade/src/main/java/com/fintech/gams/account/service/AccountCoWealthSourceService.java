/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoWealthSourceModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoWealthSource;

import java.util.List;


/**
 * 公司财富来源 服务接口
 * <p>File：AccountCoWealthSourceService.java </p>
 * <p>Title: AccountCoWealthSourceService </p>
 * <p>Description:AccountCoWealthSourceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoWealthSourceService extends GenericService<AccountCoWealthSource> {
    /**
     * 获取财富来源相关字典表信息
     * @param accountCoWealthSource
     * @param lang
     * @return
     */
    List<AccountCoWealthSourceModel> findList(AccountCoWealthSource accountCoWealthSource, String lang);
}
