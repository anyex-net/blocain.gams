/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoCrsControlNum;

/**
 * 公司CRS控权人自我证明控权人编号 持久层接口
 * <p>File：AccountCoCrsControlNumMapper.java </p>
 * <p>Title: AccountCoCrsControlNumMapper </p>
 * <p>Description:AccountCoCrsControlNumMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoCrsControlNumMapper extends GenericMapper<AccountCoCrsControlNum>
{

    /**
     * 删除CRS控权人自我证明控权人编号 通过 coCrsEntityContrId
     * @param coCrsEntityContrId
     * @return
     */
    int removeByCoCrsEntityContrId(Long coCrsEntityContrId);

}
