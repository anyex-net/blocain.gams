/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.asset.service.AssetPositionService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.consts.GlobalConst;

import io.swagger.annotations.Api;

/**
 * 资产持仓表 控制器
 * <p>File：AssetPositionController.java </p>
 * <p>Title: AssetPositionController </p>
 * <p>Description:AssetPositionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ASSET)
@Api(description = "资产持仓表")
public class AssetPositionController extends GenericController
{
    @Autowired(required = false)
    private AssetPositionService assetPositionService;
}
