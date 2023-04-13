/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParTradeExperienceModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParTradeExperience;

import java.util.List;

/**
 * 合伙企业交易经验信息 服务接口
 * <p>File：AccountParTradeExperienceService.java </p>
 * <p>Title: AccountParTradeExperienceService </p>
 * <p>Description:AccountParTradeExperienceService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParTradeExperienceService extends GenericService<AccountParTradeExperience>{

    /**
     * 根据accountId取交易经验信息及字典信息
     * @param accountParTradeExperience
     * @param lang
     * @return
     */
    List<AccountParTradeExperienceModel> findList(AccountParTradeExperience accountParTradeExperience, String lang);

}
