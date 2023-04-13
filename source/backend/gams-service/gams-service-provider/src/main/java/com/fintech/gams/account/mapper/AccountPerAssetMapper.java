/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountPerAsset;

/**
 * 个人资产信息 持久层接口
 * <p>File：AccountPerAssetMapper.java </p>
 * <p>Title: AccountPerAssetMapper </p>
 * <p>Description:AccountPerAssetMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerAssetMapper extends GenericMapper<AccountPerAsset>
{

}
