/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountCoAddressModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountCoAddress;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 公司地址信息 服务接口
 * <p>File：AccountCoAddressService.java </p>
 * <p>Title: AccountCoAddressService </p>
 * <p>Description:AccountCoAddressService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountCoAddressService extends GenericService<AccountCoAddress>{

    /**
     *  删除公司地址信息  通过 coPersonalId
     * @param coPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByCoPersonalId(Long coPersonalId) throws BusinessException;

    /**
     * 获取地址及其相关联的字典表详情
     * @param accountCoAddress
     * @param lang
     * @return
     */
    List<AccountCoAddressModel> findList(AccountCoAddress accountCoAddress,String lang);
}
