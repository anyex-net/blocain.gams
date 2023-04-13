/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.account.entity.AccountParCrsControl;
import com.fintech.gams.account.service.AccountParCrsControlService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业CRS控权人自我证明表格(CRS-CP) 控制器
 * <p>File：AccountParCrsControlController.java </p>
 * <p>Title: AccountParCrsControlController </p>
 * <p>Description:AccountParCrsControlController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParCrsControl")
@Api(description = "合伙企业CRS控权人自我证明表格(CRS-CP)")
public class AccountParCrsControlController extends GenericController {
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParCrsControlService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParCrsControlService accountParCrsControlService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParCrsControl:data")
    @ApiOperation(value = "根据ID取合伙企业CRS控权人自我证明表格(CRS-CP)", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParCrsControlService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParCrsControl:operator")
    @ApiOperation(value = "保存合伙企业CRS控权人自我证明表格(CRS-CP)", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParCrsControl info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountParCrsControlService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParCrsControl:data")
    @ApiOperation(value = "查询合伙企业CRS控权人自我证明表格(CRS-CP)", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParCrsControl entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountParCrsControl> result = accountParCrsControlService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParCrsControl:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountParCrsControlService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId和crs实体（CRS-E）控权人信息ID取合伙企业CRS控权人自我证明表格", httpMethod = "GET")
    public JsonMessage findByAccountId(Long parCrsEntityContrId) throws BusinessException {
        if (null == parCrsEntityContrId){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParCrsControl accountParCrsControl = new AccountParCrsControl();
        accountParCrsControl.setAccountId(principal.getId());
        accountParCrsControl.setParCrsEntityContrId(parCrsEntityContrId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParCrsControlService.findList(accountParCrsControl));
    }
}
