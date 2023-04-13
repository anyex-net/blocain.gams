/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.model.AccountParAddressModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.account.entity.AccountParAddress;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * 合伙企业地址信息 服务接口
 * <p>File：AccountParAddressService.java </p>
 * <p>Title: AccountParAddressService </p>
 * <p>Description:AccountParAddressService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface AccountParAddressService extends GenericService<AccountParAddress>{
    /**
     * 删除合伙企业地址信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     * @throws BusinessException
     */
    int removeByParPersonalId(Long parPersonalId) throws BusinessException;

    /**
     * 获取地址列表中相关联的字典表详情
     * @param accountParAddress
     * @param lang
     * @return
     */
    List<AccountParAddressModel> findList(AccountParAddress accountParAddress,String lang);
}
