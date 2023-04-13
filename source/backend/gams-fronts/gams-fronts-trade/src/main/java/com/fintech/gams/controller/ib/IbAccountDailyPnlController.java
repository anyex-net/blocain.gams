/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountDailyPnl;
import com.fintech.gams.ib.service.IbAccountDailyPnlService;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * IB账户当日盈亏 控制器
 * <p>File：IbAccountDailyPnlController.java </p>
 * <p>Title: IbAccountDailyPnlController </p>
 * <p>Description:IbAccountDailyPnlController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户当日盈亏")
public class IbAccountDailyPnlController extends GenericController
{
    @Autowired(required = false)
    private IbAccountDailyPnlService ibAccountDailyPnlService;

    @PostMapping(value = "/accountDailyPnl/accountDailyPnl")
    @ApiOperation(value = "IB账户当日盈亏", httpMethod = "POST")
    public JsonMessage accountDailyPnl() throws BusinessException
    {
        IbAccountDailyPnl ibAccountDailyPnl = new IbAccountDailyPnl();
        ibAccountDailyPnl.setAccountId(OnLineUserUtils.getId());
        // ibAccountDailyPnl.setAccountCode("U8138565");
        List<IbAccountDailyPnl> listIbAccountDailyPnl = ibAccountDailyPnlService.findList(ibAccountDailyPnl);
        IbAccountDailyPnl accountDailyPnl = null;
        if(null != listIbAccountDailyPnl && listIbAccountDailyPnl.size() > 0)
        {
            accountDailyPnl = listIbAccountDailyPnl.get(0);
        }
        return getJsonMessage(CommonEnums.SUCCESS, accountDailyPnl);
    }
}
