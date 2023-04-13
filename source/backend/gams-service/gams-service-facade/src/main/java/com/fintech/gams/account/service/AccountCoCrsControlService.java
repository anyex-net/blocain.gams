/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoCrsControl;
import com.fintech.gams.exception.BusinessException;

/**
 * 公司CRS控权人自我证明表格 服务接口
 * <p>File：AccountCoCrsControlService.java </p>
 * <p>Title: AccountCoCrsControlService </p>
 * <p>Description:AccountCoCrsControlService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoCrsControlService extends GenericService<AccountCoCrsControl>{
    /**
     * 删除CRS控权人自我证明表格 通过 coCrsEntityContrId
     * @param coCrsEntityContrId
     * @return
     * @throws BusinessException
     */
    int removeByCoCrsEntityContrId(Long coCrsEntityContrId) throws BusinessException;
}
