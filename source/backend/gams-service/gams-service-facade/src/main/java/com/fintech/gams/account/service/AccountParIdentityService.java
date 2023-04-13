/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParIdentity;
import com.fintech.gams.exception.BusinessException;

/**
 * 合伙企业身份信息 服务接口
 * <p>File：AccountParIdentityService.java </p>
 * <p>Title: AccountParIdentityService </p>
 * <p>Description:AccountParIdentityService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParIdentityService extends GenericService<AccountParIdentity>{

    /**
     * 删除合伙企业身份信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByParPersonalId(Long parPersonalId) throws BusinessException;
}
