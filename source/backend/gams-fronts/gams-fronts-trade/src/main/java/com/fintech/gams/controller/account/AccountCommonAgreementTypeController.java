/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCommonAgreementType;
import com.fintech.gams.account.service.AccountCommonAgreementTypeService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ACCOUNT_COMMON_AGREEMENT_TYPE 控制器
 * <p>File：AccountCommonAgreementTypeController.java </p>
 * <p>Title: AccountCommonAgreementTypeController </p>
 * <p>Description:AccountCommonAgreementTypeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCommonAgreementType")
@Api(description = "ACCOUNT_COMMON_AGREEMENT_TYPE")
public class AccountCommonAgreementTypeController extends GenericController {
    @Autowired(required = false)
    private AccountCommonAgreementTypeService accountCommonAgreementTypeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCommonAgreementType:data")
    @ApiOperation(value = "根据ID取ACCOUNT_COMMON_AGREEMENT_TYPE", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCommonAgreementTypeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCommonAgreementType:operator")
    @ApiOperation(value = "保存ACCOUNT_COMMON_AGREEMENT_TYPE", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCommonAgreementType info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountCommonAgreementTypeService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCommonAgreementType:data")
    @ApiOperation(value = "查询ACCOUNT_COMMON_AGREEMENT_TYPE", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCommonAgreementType entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountCommonAgreementType> result = accountCommonAgreementTypeService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCommonAgreementType:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountCommonAgreementTypeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }


}
