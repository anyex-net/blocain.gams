/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.service.AccountCoCrsControlService;
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
import com.fintech.gams.account.service.AccountCoAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * 公司地址信息 控制器
 * <p>File：AccountCoAddressController.java </p>
 * <p>Title: AccountCoAddressController </p>
 * <p>Description:AccountCoAddressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoAddress")
@Api(description = "公司地址信息")
public class AccountCoAddressController extends GenericController {
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoAddressService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoAddressService accountCoAddressService;

    @Autowired(required = false)
    private AccountCoInstitutionalService accountCoInstitutionalService;

    @Autowired(required = false)
    private AccountCoPersonalService accountCoPersonalService;

    @Autowired(required = false)
    private AccountCoCrsControlService accountCoCrsControlService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoAddress:data")
    @ApiOperation(value = "根据ID取公司地址信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoAddressService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoAddress:operator")
    @ApiOperation(value = "保存公司地址信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoAddress info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountCoAddressService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoAddress:data")
    @ApiOperation(value = "查询公司地址信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoAddress entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountCoAddress> result = accountCoAddressService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoAddress:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountCoAddressService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司地址信息", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoAddress accountCoAddress = new AccountCoAddress();
        accountCoAddress.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoAddressService.findList(accountCoAddress));
    }

    @GetMapping(value = "/findByAccountId/institutional")
    @ApiOperation(value = "根据AccountId和机构信息的id取公司地址信息", httpMethod = "GET")
    public JsonMessage findByAccountIdAndInId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 查出机构信息
        AccountCoInstitutional institutional = accountCoInstitutionalService.findByAccountId(principal.getId());
        if (null == institutional){
            return this.getJsonMessage(CommonEnums.SUCCESS);
        }
        //取公司地址信息
        AccountCoAddress accountCoAddress = new AccountCoAddress();
        accountCoAddress.setAccountId(principal.getId());
        accountCoAddress.setCoPersonalId(institutional.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoAddressService.findList(accountCoAddress));
    }


    @GetMapping(value = "/findByAccountId/personal")
    @ApiOperation(value = "根据AccountId和公司个人信息的id取公司地址信息", httpMethod = "GET")
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
        //取公司地址信息
        AccountCoAddress accountCoAddress = new AccountCoAddress();
        accountCoAddress.setAccountId(principal.getId());
        List<AccountCoAddress> coAddressList = new ArrayList<>();
        for (AccountCoPersonal coPersonal : accountCoPersonalList) {
            accountCoAddress.setCoPersonalId(coPersonal.getId());
            //查不到直接返回
            coAddressList = accountCoAddressService.findList(accountCoAddress);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, coAddressList);
    }

    @GetMapping(value = "/findCrsAddress")
    @ApiOperation(value = "查询CRS-CP控权人的地址", httpMethod = "GET")
    public JsonMessage findCrsAddress(Long coCrsEntityContrId) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == coCrsEntityContrId){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        // CRS-CP 信息
        AccountCoCrsControl accountCoCrsControl = new AccountCoCrsControl();
        accountCoCrsControl.setAccountId(principal.getId());
        accountCoCrsControl.setCoCrsEntityContrId(coCrsEntityContrId);
        List<AccountCoCrsControl> list = accountCoCrsControlService.findList(accountCoCrsControl);
        // 取地址信息
        AccountCoAddress address = new AccountCoAddress();
        address.setAccountId(principal.getId());
        List<AccountCoAddress> coAddressList = new ArrayList<>();
        for (AccountCoCrsControl coPersonal : list) {
            address.setCoPersonalId(coPersonal.getId());
            coAddressList = accountCoAddressService.findList(address);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, coAddressList);
    }
}
