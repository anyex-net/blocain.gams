/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParCrsEntityContr;

/**
 * 合伙企业crs实体（CRS-E）控权人信息 持久层接口
 * <p>File：AccountParCrsEntityContrMapper.java </p>
 * <p>Title: AccountParCrsEntityContrMapper </p>
 * <p>Description:AccountParCrsEntityContrMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParCrsEntityContrMapper extends GenericMapper<AccountParCrsEntityContr>
{

}
