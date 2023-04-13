/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoTradeExperienceModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoTradeExperience;

import java.util.List;

/**
 * 公司交易经验信息 服务接口
 * <p>File：AccountCoTradeExperienceService.java </p>
 * <p>Title: AccountCoTradeExperienceService </p>
 * <p>Description:AccountCoTradeExperienceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoTradeExperienceService extends GenericService<AccountCoTradeExperience> {
    /**
     * 根取交易经验信息及字典信息
     * @param accountCoTradeExperience
     * @param lang
     * @return
     */
    List<AccountCoTradeExperienceModel> findList(AccountCoTradeExperience  accountCoTradeExperience, String lang);
}
