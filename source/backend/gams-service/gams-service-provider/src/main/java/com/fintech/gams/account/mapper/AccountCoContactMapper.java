/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.account.entity.AccountCoContact;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 公司联系信息 持久层接口
 * <p>File：AccountCoContactMapper.java </p>
 * <p>Title: AccountCoContactMapper </p>
 * <p>Description:AccountCoContactMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountCoContactMapper extends GenericMapper<AccountCoContact> {
    /**
     * 删除公司联系信息  通过 coPersonalId
     *
     * @param coPersonalId
     * @return
     */
    int removeByCoPersonalId(Long coPersonalId);
}
