/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoContactModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoContact;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 公司联系信息 服务接口
 * <p>File：AccountCoContactService.java </p>
 * <p>Title: AccountCoContactService </p>
 * <p>Description:AccountCoContactService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoContactService extends GenericService<AccountCoContact> {

    /**
     * 删除公司联系信息  通过 coPersonalId
     * @param coPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByCoPersonalId(Long coPersonalId) throws BusinessException;

    /**
     * 获取联系信息及其相关联的字典表信息
     * @param accountCoContact
     * @param lang
     * @return
     */
    List<AccountCoContactModel> findList(AccountCoContact accountCoContact, String lang);
}
