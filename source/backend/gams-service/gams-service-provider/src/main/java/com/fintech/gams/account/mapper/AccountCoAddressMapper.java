/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoAddress;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 公司地址信息 持久层接口
 * <p>File：AccountCoAddressMapper.java </p>
 * <p>Title: AccountCoAddressMapper </p>
 * <p>Description:AccountCoAddressMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoAddressMapper extends GenericMapper<AccountCoAddress>
{
    /**
     *  删除公司地址信息  通过 coPersonalId
     * @param coPersonalId
     * @return
     */
    int removeByCoPersonalId(Long coPersonalId);
}
