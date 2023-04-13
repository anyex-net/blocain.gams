/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParInvestmentTargetModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParInvestmentTarget;

import java.util.List;

/**
 * 合伙企业投资目标信息 服务接口
 * <p>File：AccountParInvestmentTargetService.java </p>
 * <p>Title: AccountParInvestmentTargetService </p>
 * <p>Description:AccountParInvestmentTargetService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParInvestmentTargetService extends GenericService<AccountParInvestmentTarget>{
    /**
     * 获取投资目标及其相关字典信息
     * @param accountParInvestmentTarget
     * @param lang
     * @return
     */
    List<AccountParInvestmentTargetModel> findList(AccountParInvestmentTarget accountParInvestmentTarget, String lang);

}
