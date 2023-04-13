/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.system;

import java.util.List;

import com.fintech.gams.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.model.TreeModel;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.Resources;
import com.fintech.gams.system.entity.RoleInfo;
import com.fintech.gams.system.service.ResourcesService;
import com.fintech.gams.system.service.RoleInfoService;
import com.fintech.gams.utils.CalendarUtils;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 角色信息表 控制器
 * <p>File：RoleInfoController.java </p>
 * <p>Title: RoleInfoController </p>
 * <p>Description:RoleInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(description = "角色管理")
public class RoleInfoController extends GenericController
{
    @Autowired(required = false)
    private RoleInfoService  roleInfoService;
    
    @Autowired(required = false)
    private ResourcesService resourcesService;
    
    @GetMapping(value = "/role/findBy")
    @ApiOperation(value = "根据ID取角色信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, roleInfoService.selectByPrimaryKey(id));
    }
    
    @GetMapping(value = "/role/findAll")
    @ApiOperation(value = "根据ID取角色信息", httpMethod = "GET")
    public JsonMessage findAll() throws BusinessException
    {
        return this.getJsonMessage(CommonEnums.SUCCESS, roleInfoService.selectAll());
    }
    
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "操作角色信息表", httpMethod = "POST")
    @RequestMapping(value = "/role/save", method = RequestMethod.POST)
    public JsonMessage save(RoleInfo info) throws BusinessException
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
        roleInfoService.save(info);
        return json;
    }
    
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "保存角色授权信息", httpMethod = "POST")
    @RequestMapping(value = "/role/saveGrant", method = RequestMethod.POST)
    public JsonMessage saveGrant(Long id, String resourceIds) throws BusinessException
    {
        if(null == id || StringUtils.isBlank(resourceIds)){
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        roleInfoService.saveGrant(id, resourceIds);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "查询角色信息", httpMethod = "POST")
    @RequestMapping(value = "/role/data", method = RequestMethod.POST)
    public JsonMessage data(RoleInfo entity, Pagination pagin) throws BusinessException
    {
        PaginateResult<RoleInfo> result = roleInfoService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @RequiresPermissions("system:role:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @RequestMapping(value = "/role/del", method = RequestMethod.POST)
    public JsonMessage del(String ids) throws BusinessException
    {
        roleInfoService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @GetMapping(value = "/role/findByRoleId")
    @RequiresPermissions("system:role:data")
    @ApiOperation(value = "角色授权信息", httpMethod = "GET")
    public JsonMessage findByRoleId(Long roleId) throws BusinessException
    {
        List<Resources> data = resourcesService.findByRoleId(roleId);
        return getJsonMessage(CommonEnums.SUCCESS, data);
    }
    
    @GetMapping(value = "/role/tree")
    @ApiOperation(value = "返回以TREEMODEL对象的所有数据", httpMethod = "GET")
    public List<TreeModel> tree(RoleInfo role) throws BusinessException
    {
        if (null == role)
        {
            role = new RoleInfo();
        }
        return roleInfoService.findByRole(role);
    }
}
