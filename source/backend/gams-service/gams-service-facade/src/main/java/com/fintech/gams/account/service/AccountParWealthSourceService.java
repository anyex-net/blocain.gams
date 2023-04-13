/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParWealthSourceModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParWealthSource;

import java.util.List;

/**
 * 合伙企业财富来源 服务接口
 * <p>File：AccountParWealthSourceService.java </p>
 * <p>Title: AccountParWealthSourceService </p>
 * <p>Description:AccountParWealthSourceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParWealthSourceService extends GenericService<AccountParWealthSource>{
    /**
     * 获取财富来源及其相关字典表信息
     * @param accountParWealthSource
     * @param lang
     * @return
     */
    List<AccountParWealthSourceModel> findList (AccountParWealthSource accountParWealthSource, String lang);

}
