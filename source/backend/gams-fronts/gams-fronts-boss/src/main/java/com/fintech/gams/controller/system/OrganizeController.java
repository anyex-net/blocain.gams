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
import com.fintech.gams.system.entity.Organize;
import com.fintech.gams.system.service.OrganizeService;
import com.fintech.gams.utils.CalendarUtils;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构信息表 控制器
 * <p>File：OrganizationController.java </p>
 * <p>Title: OrganizationController </p>
 * <p>Description:OrganizationController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(description = "组织机构管理")
public class OrganizeController extends GenericController
{
    @Autowired(required = false)
    private OrganizeService organizeService;
    
    @GetMapping(value = "/organiz/findBy")
    @ApiOperation(value = "根据ID取机构信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, organizeService.selectByPrimaryKey(id));
    }
    
    @GetMapping(value = "/organiz/tree")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public JsonMessage tree(Organize organiz) throws BusinessException
    {
        if (null == organiz)
        {
            organiz = new Organize();
        }
        return getJsonMessage(CommonEnums.SUCCESS, organizeService.findByOrganization(organiz));
    }
    
    @RequiresPermissions("system:organiz:operator")
    @ApiOperation(value = "保存组织信息", httpMethod = "POST")
    @RequestMapping(value = "/organiz/save", method = RequestMethod.POST)
    public JsonMessage save(Organize info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            info.setCreateDate(currentDate);
            info.setCreateBy(principal.getId());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(currentDate);
        organizeService.save(info);
        return json;
    }
    
    @RequiresPermissions("system:organiz:data")
    @ApiOperation(value = "查询组织信息", httpMethod = "POST")
    @RequestMapping(value = "/organiz/data", method = RequestMethod.POST)
    public JsonMessage data(Organize entity) throws BusinessException
    {
        return getJsonMessage(CommonEnums.SUCCESS, organizeService.findList(entity));
    }
    
    @RequiresPermissions("system:organiz:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @RequestMapping(value = "/organiz/del", method = RequestMethod.POST)
    public JsonMessage del(String ids) throws BusinessException
    {
        organizeService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
