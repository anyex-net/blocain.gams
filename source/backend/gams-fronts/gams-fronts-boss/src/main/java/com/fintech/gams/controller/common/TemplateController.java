/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Template;
import com.fintech.gams.common.service.TemplateService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 消息模版 控制器
 * <p>File：TemplateController.java </p>
 * <p>Title: TemplateController </p>
 * <p>Description:TemplateController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "消息模版")
public class TemplateController extends GenericController
{
    @Autowired(required = false)
    private TemplateService templateService;

    @GetMapping(value = "/template/findBy")
    @RequiresPermissions("common:template:data")
    @ApiOperation(value = "根据ID取消息模版", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, templateService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/template/save")
    @RequiresPermissions("common:template:operator")
    @ApiOperation(value = "保存消息模版", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Template info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId()) {
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        if (beanValidator(json, info))
        {
            templateService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/template/data")
    @RequiresPermissions("common:template:data")
    @ApiOperation(value = "查询消息模版", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Template entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Template> result = templateService.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/template/del")
    @RequiresPermissions("common:template:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        templateService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
