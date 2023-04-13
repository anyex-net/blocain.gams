/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.account.entity.UploadFileType;
import com.fintech.gams.account.service.UploadFileTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 上传文件类型 控制器
 * <p>File：UploadFileTypeController.java </p>
 * <p>Title: UploadFileTypeController </p>
 * <p>Description:UploadFileTypeController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/uploadFileType")
@Api(description = "上传文件类型")
public class UploadFileTypeController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = UploadFileTypeService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private UploadFileTypeService uploadFileTypeService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:uploadFileType:data")
    @ApiOperation(value = "根据ID取上传文件类型", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, uploadFileTypeService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:uploadFileType:operator")
    @ApiOperation(value = "保存上传文件类型", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute UploadFileType info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            uploadFileTypeService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:uploadFileType:data")
    @ApiOperation(value = "查询上传文件类型", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute UploadFileType entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<UploadFileType> result = uploadFileTypeService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:uploadFileType:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        uploadFileTypeService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}