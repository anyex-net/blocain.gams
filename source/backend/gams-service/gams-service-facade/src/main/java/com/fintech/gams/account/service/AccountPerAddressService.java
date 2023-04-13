/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.AccountPerAddress;
import com.fintech.gams.account.model.AccountPerAddressModel;
import com.fintech.gams.bean.GenericService;

import java.util.List;

/**
 * 个人地址 服务接口
 * <p>File：AccountPerAddressService.java </p>
 * <p>Title: AccountPerAddressService </p>
 * <p>Description:AccountPerAddressService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountPerAddressService extends GenericService<AccountPerAddress>{

    /**
     * 获取地址及其中相关联的字典表详情
     * @param accountPerAddress
     * @param lang
     * @return
     */
    List<AccountPerAddressModel> findList(AccountPerAddress accountPerAddress,String lang);
}
