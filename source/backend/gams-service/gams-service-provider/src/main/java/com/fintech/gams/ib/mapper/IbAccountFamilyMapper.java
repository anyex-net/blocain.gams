/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.ib.entity.IbAccountFamily;

import java.util.List;

/**
 * IB账户家族表 持久层接口
 * <p>File：IbAccountFamilyMapper.java </p>
 * <p>Title: IbAccountFamilyMapper </p>
 * <p>Description:IbAccountFamilyMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface IbAccountFamilyMapper extends GenericMapper<IbAccountFamily>
{
    /**
     * 判断AccountId、IbAccountCode、IbFamilyCode是否唯一
     * @param ibAccountFamily
     * @return
     */
    List<IbAccountFamily> findByAccountIdORIbAccountCodeORIbFamilyCode(IbAccountFamily ibAccountFamily);
}
