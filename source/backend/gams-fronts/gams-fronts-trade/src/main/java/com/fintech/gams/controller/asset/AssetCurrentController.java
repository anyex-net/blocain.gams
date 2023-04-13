/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.asset.service.AssetCurrentService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.consts.GlobalConst;

import io.swagger.annotations.Api;

/**
 * 资产流水表 控制器
 * <p>File：AssetCurrentController.java </p>
 * <p>Title: AssetCurrentController </p>
 * <p>Description:AssetCurrentController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ASSET)
@Api(description = "资产流水表")
public class AssetCurrentController extends GenericController
{
    @Autowired(required = false)
    private AssetCurrentService assetCurrentService;
}
