/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParCrsControlNum;
import com.fintech.gams.exception.BusinessException;

/**
 * 合伙企业CRS控权人自我证明控权人编号 服务接口
 * <p>File：AccountParCrsControlNumService.java </p>
 * <p>Title: AccountParCrsControlNumService </p>
 * <p>Description:AccountParCrsControlNumService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParCrsControlNumService extends GenericService<AccountParCrsControlNum>{

    /**
     *  删除合伙企业CRS控权人自我证明控权人编号 通过 parrCrsEntityContrId
     * @param parrCrsEntityContrId
     * @return
     * @throws BusinessException
     */
    int removeByParCrsEntityContrId(Long parrCrsEntityContrId) throws BusinessException;

}
