/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParContactModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParContact;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 合伙企业联系信息 服务接口
 * <p>File：AccountParContactService.java </p>
 * <p>Title: AccountParContactService </p>
 * <p>Description:AccountParContactService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParContactService extends GenericService<AccountParContact>{
    /**
     * 删除合伙企业联系信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByParPersonalId(Long parPersonalId) throws BusinessException;

    /**
     * 获取联系方式及其相关联的字典表详情
     * @param accountParContact
     * @param lang
     * @return
     */
    List<AccountParContactModel> findList(AccountParContact accountParContact, String lang);
}
