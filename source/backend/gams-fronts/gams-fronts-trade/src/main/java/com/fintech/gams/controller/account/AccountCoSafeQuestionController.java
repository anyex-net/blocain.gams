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
import com.fintech.gams.account.entity.AccountCoSafeQuestion;
import com.fintech.gams.account.service.AccountCoSafeQuestionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司安全问题 控制器
 * <p>File：AccountCoSafeQuestionController.java </p>
 * <p>Title: AccountCoSafeQuestionController </p>
 * <p>Description:AccountCoSafeQuestionController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoSafeQuestion")
@Api(description = "公司安全问题")
public class AccountCoSafeQuestionController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoSafeQuestionService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoSafeQuestionService accountCoSafeQuestionService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoSafeQuestion:data")
    @ApiOperation(value = "根据ID取公司安全问题", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoSafeQuestionService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoSafeQuestion:operator")
    @ApiOperation(value = "保存公司安全问题", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoSafeQuestion info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoSafeQuestionService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoSafeQuestion:data")
    @ApiOperation(value = "查询公司安全问题", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoSafeQuestion entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoSafeQuestion> result = accountCoSafeQuestionService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoSafeQuestion:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoSafeQuestionService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取公司安全问题", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException{
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoSafeQuestion accountCoSafeQuestion = new AccountCoSafeQuestion();
        accountCoSafeQuestion.setAccountId(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoSafeQuestionService.findList(accountCoSafeQuestion));
    }
}
