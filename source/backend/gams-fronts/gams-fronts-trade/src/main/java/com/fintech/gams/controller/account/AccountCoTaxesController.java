/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCoContact;
import com.fintech.gams.account.entity.AccountCoInstitutional;
import com.fintech.gams.account.entity.AccountCoPersonal;
import com.fintech.gams.account.service.AccountCoInstitutionalService;
import com.fintech.gams.account.service.AccountCoPersonalService;
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
import com.fintech.gams.account.entity.AccountCoTaxes;
import com.fintech.gams.account.service.AccountCoTaxesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司交易经验信息 控制器
 * <p>File：AccountCoTaxesController.java </p>
 * <p>Title: AccountCoTaxesController </p>
 * <p>Description:AccountCoTaxesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoTaxes")
@Api(description = "公司税务居住地信息")
public class AccountCoTaxesController extends GenericController {
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoTaxesService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoTaxesService accountCoTaxesService;

    @Autowired(required = false)
    private AccountCoInstitutionalService accountCoInstitutionalService;

    @Autowired(required = false)
    private AccountCoPersonalService accountCoPersonalService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoTaxes:data")
    @ApiOperation(value = "根据ID取公司税务居住地信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTaxesService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoTaxes:operator")
    @ApiOperation(value = "保存公司税务居住地信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoTaxes info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountCoTaxesService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoTaxes:data")
    @ApiOperation(value = "查询公司税务居住地信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoTaxes entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountCoTaxes> result = accountCoTaxesService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoTaxes:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountCoTaxesService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司税务居住地信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
        accountCoTaxes.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTaxesService.findList(accountCoTaxes));
    }

    @GetMapping(value = "/findByAccountId/institutional")
    @ApiOperation(value = "根据AccountId和机构信息的id取公司税务居住地信息", httpMethod = "GET")
    public JsonMessage findByAccountIdAndInId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 查出机构信息
        AccountCoInstitutional institutional = accountCoInstitutionalService.findByAccountId(principal.getId());
        if (null == institutional){
            return this.getJsonMessage(CommonEnums.SUCCESS);
        }
        AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
        accountCoTaxes.setAccountId(principal.getId());
        accountCoTaxes.setCoPersonalId(institutional.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTaxesService.findList(accountCoTaxes));
    }


    @GetMapping(value = "/findByAccountId/personal")
    @ApiOperation(value = "根据AccountId和公司个人信息的id取公司税务居住地信息", httpMethod = "GET")
    public JsonMessage findByAccountIdAndPerId(String personalType) throws BusinessException {
        if (null == personalType) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 查出公司个人信息 根据 accountId 和 个人类型personalType
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(principal.getId());
        accountCoPersonal.setPersonalType(personalType);
        List<AccountCoPersonal> accountCoPersonalList = accountCoPersonalService.findList(accountCoPersonal);
        //取公司税务居住地信息
        AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
        accountCoTaxes.setAccountId(principal.getId());
        List<AccountCoTaxes> coTaxesList = new ArrayList<>();
        for (AccountCoPersonal coPersonal : accountCoPersonalList) {
            accountCoTaxes.setCoPersonalId(coPersonal.getId());
            coTaxesList = accountCoTaxesService.findList(accountCoTaxes);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, coTaxesList);
    }
}
