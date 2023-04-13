/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerIdentity;
import com.fintech.gams.account.model.AccountPerIdentityModel;
import com.fintech.gams.bean.GenericService;

import java.util.List;

/**
 * 个人身份信息 服务接口
 * <p>File：AccountPerIdentityService.java </p>
 * <p>Title: AccountPerIdentityService </p>
 * <p>Description:AccountPerIdentityService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountPerIdentityService extends GenericService<AccountPerIdentity>{
    /**
     * 获取身份信息及其相关联的字典表详情
     * @param accountPerIdentity
     * @param lang
     * @return
     */
    List<AccountPerIdentityModel> findList(AccountPerIdentity accountPerIdentity, String lang);

}
