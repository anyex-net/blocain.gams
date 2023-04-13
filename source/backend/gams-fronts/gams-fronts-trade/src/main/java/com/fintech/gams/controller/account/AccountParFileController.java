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
import com.fintech.gams.account.entity.AccountParFile;
import com.fintech.gams.account.service.AccountParFileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 合伙企业文件信息 控制器
 * <p>File：AccountParFileController.java </p>
 * <p>Title: AccountParFileController </p>
 * <p>Description:AccountParFileController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParFile")
@Api(description = "合伙企业文件信息")
public class AccountParFileController extends GenericController {
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParFileService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParFileService accountParFileService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParFile:data")
    @ApiOperation(value = "根据ID取合伙企业文件信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParFileService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParFile:operator")
    @ApiOperation(value = "保存合伙企业文件信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParFile info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountParFileService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParFile:data")
    @ApiOperation(value = "查询合伙企业文件信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParFile entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountParFile> result = accountParFileService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParFile:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountParFileService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId/fileIdentification")
    @ApiOperation(value = "根据accountId和文件标识取合伙企业文件信息", httpMethod = "GET")
    public JsonMessage findByAccountId(String fileIdentification, Long coPersonalId) throws BusinessException {
        if (null == fileIdentification) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParFile accountParFile = new AccountParFile();
        accountParFile.setAccountId(principal.getId());
        accountParFile.setFileIdentification(fileIdentification);
        if (null != coPersonalId) {
            // 个人股东或者法人股东的ID
            accountParFile.setParPersonalId(coPersonalId);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParFileService.findList(accountParFile));
    }
}
