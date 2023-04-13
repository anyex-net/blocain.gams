/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerTaxes;
import com.fintech.gams.account.model.AccountPerTaxesModel;
import com.fintech.gams.bean.GenericService;

import java.util.List;

/**
 * 个人税务居住地 服务接口
 * <p>File：AccountPerTaxesService.java </p>
 * <p>Title: AccountPerTaxesService </p>
 * <p>Description:AccountPerTaxesService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountPerTaxesService extends GenericService<AccountPerTaxes>{
    /**
     * 获取个人税务居住地 及其字典表信息
     * @param accountPerTaxes
     * @param lang
     * @return
     */
    List<AccountPerTaxesModel> findList(AccountPerTaxes accountPerTaxes, String lang );

}
