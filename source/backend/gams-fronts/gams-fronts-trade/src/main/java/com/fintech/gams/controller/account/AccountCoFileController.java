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
import com.fintech.gams.account.entity.AccountCoFile;
import com.fintech.gams.account.service.AccountCoFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 公司文件信息 控制器
 * <p>File：AccountCoFileController.java </p>
 * <p>Title: AccountCoFileController </p>
 * <p>Description:AccountCoFileController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountCoFile")
@Api(description = "公司文件信息")
public class AccountCoFileController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountCoFileService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountCoFileService accountCoFileService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountCoFile:data")
    @ApiOperation(value = "根据ID取公司文件信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoFileService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountCoFile:operator")
    @ApiOperation(value = "保存公司文件信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountCoFile info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountCoFileService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountCoFile:data")
    @ApiOperation(value = "查询公司文件信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountCoFile entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountCoFile> result = accountCoFileService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountCoFile:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        accountCoFileService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId/fileIdentification")
    @ApiOperation(value = "根据accountId和文件标识取公司文件信息", httpMethod = "GET")
    public JsonMessage findByAccountId(String fileIdentification, Long coPersonalId) throws BusinessException {
        if (null == fileIdentification) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountCoFile accountCoFile = new AccountCoFile();
        accountCoFile.setAccountId(principal.getId());
        accountCoFile.setFileIdentification(fileIdentification);
        if (null != coPersonalId) {
            // 个人股东或者法人股东的ID
            accountCoFile.setCoPersonalId(coPersonalId);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoFileService.findList(accountCoFile));
    }
}
