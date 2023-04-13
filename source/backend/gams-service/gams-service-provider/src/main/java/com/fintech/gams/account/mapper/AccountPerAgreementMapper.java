/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import com.fintech.gams.account.entity.AccountPerAgreement;
import com.fintech.gams.bean.GenericMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 个人协议纰漏 持久层接口
 * <p>File：AccountPerAgreementMapper.java </p>
 * <p>Title: AccountPerAgreementMapper </p>
 * <p>Description:AccountPerAgreementMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountPerAgreementMapper extends GenericMapper<AccountPerAgreement>
{

}
