/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.ib;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.ib.service.IbAccountFamilyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * IB账户家族表 控制器
 * <p>File：IbAccountFamilyController.java </p>
 * <p>Title: IbAccountFamilyController </p>
 * <p>Description:IbAccountFamilyController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.IB)
@Api(description = "IB账户家族表")
public class IbAccountFamilyController extends GenericController
{
    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;
}
