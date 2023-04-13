/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.service;

import java.util.List;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.model.TreeModel;
import com.fintech.gams.system.entity.Organize;

/**
 * 组织机构信息 服务接口
 * <p>File：OrganizeService.java </p>
 * <p>Title: OrganizeService </p>
 * <p>Description:OrganizeService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface OrganizeService extends GenericService<Organize>
{
    List<TreeModel> findByOrganization(Organize organiz) throws BusinessException;
}
