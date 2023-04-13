/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParCrsControlNum;

/**
 * 合伙企业CRS控权人自我证明控权人编号 持久层接口
 * <p>File：AccountParCrsControlNumMapper.java </p>
 * <p>Title: AccountParCrsControlNumMapper </p>
 * <p>Description:AccountParCrsControlNumMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParCrsControlNumMapper extends GenericMapper<AccountParCrsControlNum>
{

    /**
     * 删除合伙企业CRS控权人自我证明控权人编号 通过 parrCrsEntityContrId
     * @param parrCrsEntityContrId
     * @return
     */
    int removeByParCrsEntityContrId(Long parrCrsEntityContrId);
}
