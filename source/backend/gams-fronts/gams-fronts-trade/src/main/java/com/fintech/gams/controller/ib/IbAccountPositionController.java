/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountPosition;
import com.fintech.gams.ib.service.IbAccountPositionService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * IB账户持仓表 控制器
 * <p>File：IbAccountPositionController.java </p>
 * <p>Title: IbAccountPositionController </p>
 * <p>Description:IbAccountPositionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户持仓表")
public class IbAccountPositionController extends GenericController
{
    @Autowired(required = false)
    private IbAccountPositionService ibAccountPositionService;

    @PostMapping(value = "/accountPosition/list")
    @ApiOperation(value = "IB账户持仓记录查询", httpMethod = "POST")
    public JsonMessage list(@ModelAttribute IbAccountPosition ibAccountPosition, @ModelAttribute Pagination pagin) throws BusinessException
    {
        ibAccountPosition.setAccountId(OnLineUserUtils.getId());
        // ibAccountPosition.setAccountCode("U8138565");
        PaginateResult<IbAccountPosition> result = ibAccountPositionService.search(pagin, ibAccountPosition);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
