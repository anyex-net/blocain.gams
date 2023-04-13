/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.Resources;
import com.fintech.gams.system.service.ResourcesService;
import com.fintech.gams.utils.CalendarUtils;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 资源菜单信息表 控制器
 * <p>File：ResourcesController.java </p>
 * <p>Title: ResourcesController </p>
 * <p>Description:ResourcesController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(description = "资源菜单管理")
public class ResourcesController extends GenericController
{
    @Autowired(required = false)
    private ResourcesService resourcesService;
    
    @GetMapping(value = "/resource/findBy")
    @ApiOperation(value = "根据ID取资源信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, resourcesService.selectByPrimaryKey(id));
    }
    
    @RequiresPermissions("system:resource:operator")
    @ApiOperation(value = "操作资源信息", httpMethod = "POST")
    @RequestMapping(value = "/resource/save", method = RequestMethod.POST)
    public JsonMessage save(Resources info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            info.setCreateBy(principal.getId());
            info.setCreateDate(currentDate);
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(currentDate);
        resourcesService.save(info);
        return json;
    }
    
    @GetMapping(value = "/resource/tree")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public JsonMessage tree(Long id) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, resourcesService.findByResources(id));
    }
    
    @RequiresPermissions("system:resource:data")
    @ApiOperation(value = "查询资源信息", httpMethod = "POST")
    @RequestMapping(value = "/resource/data", method = RequestMethod.POST)
    public JsonMessage data(Resources entity) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, resourcesService.findList(entity));
    }
    
    @RequiresPermissions("system:resource:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @RequestMapping(value = "/resource/del", method = RequestMethod.POST)
    public JsonMessage del(String ids) throws BusinessException
    {
        resourcesService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
