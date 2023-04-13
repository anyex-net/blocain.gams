/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParCrsControl;

/**
 * 合伙企业CRS控权人自我证明表格(CRS-CP) 持久层接口
 * <p>File：AccountParCrsControlMapper.java </p>
 * <p>Title: AccountParCrsControlMapper </p>
 * <p>Description:AccountParCrsControlMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParCrsControlMapper extends GenericMapper<AccountParCrsControl>
{

    /**
     * 合伙企业CRS控权人自我证明表格 通过 parCrsEntityContrId
     * @param parCrsEntityContrId
     * @return
     */
    int removeByParCrsEntityContrId(Long parCrsEntityContrId);
}
