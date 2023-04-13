/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerInvestTarget;
import com.fintech.gams.account.model.AccountPerInvestTargetModel;
import com.fintech.gams.bean.GenericService;

import java.util.List;

/**
 * 个人投资目标 服务接口
 * <p>File：AccountPerInvestTargetService.java </p>
 * <p>Title: AccountPerInvestTargetService </p>
 * <p>Description:AccountPerInvestTargetService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountPerInvestTargetService extends GenericService<AccountPerInvestTarget>{
    /**
     * 获取 投资目标 及其相关联的字典表详情
     * @param accountPerInvestTarget
     * @param lang
     * @return
     */
    List<AccountPerInvestTargetModel> findList(AccountPerInvestTarget accountPerInvestTarget, String lang);
}
