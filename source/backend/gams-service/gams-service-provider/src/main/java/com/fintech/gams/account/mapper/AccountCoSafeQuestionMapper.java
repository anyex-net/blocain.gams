/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoSafeQuestion;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 公司安全问题 持久层接口
 * <p>File：AccountCoSafeQuestionMapper.java </p>
 * <p>Title: AccountCoSafeQuestionMapper </p>
 * <p>Description:AccountCoSafeQuestionMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoSafeQuestionMapper extends GenericMapper<AccountCoSafeQuestion>
{

}
