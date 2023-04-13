/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerTradeExp;
import com.fintech.gams.account.model.AccountPerTradeExpModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 个人交易经验信息 服务接口
 * <p>File：AccountPerTradeExpService.java </p>
 * <p>Title: AccountPerTradeExpService </p>
 * <p>Description:AccountPerTradeExpService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountPerTradeExpService extends GenericService<AccountPerTradeExp> {

    /**
     * 取个人交易经验信息及字典信息
     * @param accountPerTradeExp
     * @param lang
     * @return
     */
    List<AccountPerTradeExpModel> findList(AccountPerTradeExp accountPerTradeExp, String lang);

}
