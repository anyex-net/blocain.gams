/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountCoInvestmentAccount;
import com.fintech.gams.account.service.AccountCoInvestmentAccountService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 公司一体化投资管理账户 控制器
 * <p>File：AccountCoInvestmentAccountController.java </p>
 * <p>Title: AccountCoInvestmentAccountController </p>
 * <p>Description:AccountCoInvestmentAccountController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoInvestmentAccount")
@Api(description = "公司一体化投资管理账户")
public class AccountCoInvestmentAccountController extends GenericController {
    @Autowired(required = false)
    private AccountCoInvestmentAccountService accountCoInvestmentAccountService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoInvestmentAccount:data")
    @ApiOperation(value = "根据ID取公司一体化投资管理账户", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoInvestmentAccountService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoInvestmentAccount:operator")
    @ApiOperation(value = "保存公司一体化投资管理账户", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoInvestmentAccount info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountCoInvestmentAccountService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoInvestmentAccount:data")
    @ApiOperation(value = "查询公司一体化投资管理账户", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoInvestmentAccount entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountCoInvestmentAccount> result = accountCoInvestmentAccountService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoInvestmentAccount:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountCoInvestmentAccountService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据accountId取公司一体化投资管理账户", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoInvestmentAccount accountCoInvestmentAccount = new AccountCoInvestmentAccount();
        accountCoInvestmentAccount.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoInvestmentAccountService.findList(accountCoInvestmentAccount));
    }

}
