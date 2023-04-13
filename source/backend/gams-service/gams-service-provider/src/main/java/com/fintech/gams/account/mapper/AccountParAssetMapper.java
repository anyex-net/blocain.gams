/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountParAsset;

/**
 * 合伙企业资产信息 持久层接口
 * <p>File：AccountParAssetMapper.java </p>
 * <p>Title: AccountParAssetMapper </p>
 * <p>Description:AccountParAssetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountParAssetMapper extends GenericMapper<AccountParAsset>
{

}
