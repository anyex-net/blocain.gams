/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountPerFile;
import com.fintech.gams.account.service.AccountPerFileService;
import com.fintech.gams.bean.*;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 个人文件 控制器
 * <p>File：AccountPerFileController.java </p>
 * <p>Title: AccountPerFileController </p>
 * <p>Description:AccountPerFileController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountPerFile")
@Api(description = "个人文件")
public class AccountPerFileController extends GenericController
{

    @Autowired(required = false)
    private AccountPerFileService accountPerFileService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountPerFile:data")
    @ApiOperation(value = "根据ID取个人文件", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerFileService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountPerFile:operator")
    @ApiOperation(value = "保存个人文件", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountPerFile info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            accountPerFileService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountPerFile:data")
    @ApiOperation(value = "查询个人文件", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountPerFile entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AccountPerFile> result = accountPerFileService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/findByAccountId/fileIdentification")
    @ApiOperation(value = "根据accountId和文件标识取个人文件", httpMethod = "GET")
    public JsonMessage findByAccountId(String fileIdentification) throws BusinessException {
        if (null == fileIdentification) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountPerFile accountPerFile = new AccountPerFile();
        accountPerFile.setAccountId(principal.getId());
        accountPerFile.setFileIdentification(fileIdentification);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerFileService.findList(accountPerFile));
    }



}
