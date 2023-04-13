/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoInvestmentTargetModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoInvestmentTarget;
import com.fintech.gams.exception.BusinessException;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 公司投资目标信息 服务接口
 * <p>File：AccountCoInvestmentTargetService.java </p>
 * <p>Title: AccountCoInvestmentTargetService </p>
 * <p>Description:AccountCoInvestmentTargetService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoInvestmentTargetService extends GenericService<AccountCoInvestmentTarget> {

    /**
     * 根据AccountId取公司投资目标信息
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    AccountCoInvestmentTarget findByAccountId(Long accountId) throws BusinessException;

    /**
     * 获取投资目标及其相关联的字典表详情
     * @param accountCoInvestmentTarget
     * @param lang
     * @return
     */
    List<AccountCoInvestmentTargetModel> findList(AccountCoInvestmentTarget accountCoInvestmentTarget, String lang);
}
