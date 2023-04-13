/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParCrsControl;
import com.fintech.gams.exception.BusinessException;

/**
 * 合伙企业CRS控权人自我证明表格(CRS-CP) 服务接口
 * <p>File：AccountParCrsControlService.java </p>
 * <p>Title: AccountParCrsControlService </p>
 * <p>Description:AccountParCrsControlService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParCrsControlService extends GenericService<AccountParCrsControl>{

    /**
     * 合伙企业CRS控权人自我证明表格 通过 parCrsEntityContrId
     * @param parCrsEntityContrId
     * @return
     * @throws BusinessException
     */
    int removeByParCrsEntityContrId(Long parCrsEntityContrId) throws BusinessException;
}
