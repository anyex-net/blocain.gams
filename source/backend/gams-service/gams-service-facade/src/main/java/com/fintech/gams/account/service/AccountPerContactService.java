/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerContact;
import com.fintech.gams.account.model.AccountPerContactModel;
import com.fintech.gams.bean.GenericService;

import java.util.List;

/**
 * 个人联系方式 服务接口
 * <p>File：AccountPerContactService.java </p>
 * <p>Title: AccountPerContactService </p>
 * <p>Description:AccountPerContactService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountPerContactService extends GenericService<AccountPerContact>{
    /**
     * 获取联系信息及其相关联的字典表详情
     * @param accountPerContact
     * @param lang
     * @return
     */
    List<AccountPerContactModel> findList(AccountPerContact accountPerContact, String lang);
}
