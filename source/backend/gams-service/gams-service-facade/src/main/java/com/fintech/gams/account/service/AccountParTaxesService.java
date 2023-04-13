/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParTaxesModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParTaxes;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 合伙企业税务居住地 服务接口
 * <p>File：AccountParTaxesService.java </p>
 * <p>Title: AccountParTaxesService </p>
 * <p>Description:AccountParTaxesService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParTaxesService extends GenericService<AccountParTaxes>{
    /**
     * 删除合伙企业税务居住地  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByParPersonalId(Long parPersonalId) throws BusinessException;

    /**
     * 获取税务居住地及其字典表详情
     * @param accountParTaxes
     * @param lang
     * @return
     */
    List<AccountParTaxesModel> findList(AccountParTaxes accountParTaxes, String lang);
}
