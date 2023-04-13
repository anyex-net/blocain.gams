/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoTaxesModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoTaxes;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 公司税务居住地 服务接口
 * <p>File：AccountCoTaxesService.java </p>
 * <p>Title: AccountCoTaxesService </p>
 * <p>Description:AccountCoTaxesService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoTaxesService extends GenericService<AccountCoTaxes> {
    /**
     *  删除公司税务居住地  通过 coPersonalId
     * @param coPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByCoPersonalId(Long coPersonalId) throws BusinessException;

    /**
     * 获取税务居住地及其相关联的字典表详情
     * @param accountCoTaxes
     * @param lang
     * @return
     */
    List<AccountCoTaxesModel> findList(AccountCoTaxes accountCoTaxes, String lang);
}
