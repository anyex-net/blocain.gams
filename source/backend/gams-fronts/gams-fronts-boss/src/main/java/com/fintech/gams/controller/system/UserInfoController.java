/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.system;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.UserInfo;
import com.fintech.gams.system.service.UserInfoService;
import com.fintech.gams.utils.CalendarUtils;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 用户基础信息表 控制器
 * <p>File：UserInfoController.java </p>
 * <p>Title: UserInfoController </p>
 * <p>Description:UserInfoController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.SYSTEM)
@Api(description = "用户管理")
public class UserInfoController extends GenericController
{
    @Autowired(required = false)
    private UserInfoService userInfoService;
    
    @GetMapping(value = "/user/info")
    @ApiOperation(value = "取用户信息", httpMethod = "GET")
    public JsonMessage getInfo() throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        UserInfo info = userInfoService.selectByPrimaryKey(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, info);
    }
    
    @GetMapping(value = "/user/findBy")
    @ApiOperation(value = "根据ID取用户信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, userInfoService.selectByPrimaryKey(id));
    }
    
    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "操作用户基础信息表", httpMethod = "POST")
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public JsonMessage save(UserInfo info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        long currentDate = CalendarUtils.getCurrentLong();
        if (null == info.getId())
        {
            info.setCreateDate(currentDate);
        }
        info.setUpdateDate(currentDate);
        userInfoService.save(info);
        return json;
    }
    
    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "修改登陆密码", httpMethod = "POST")
    @RequestMapping(value = "/user/changePwd", method = RequestMethod.POST)
    public JsonMessage changePwd(String oldPwd, String newPwd) throws BusinessException
    {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (StringUtils.isBlank(oldPwd) || StringUtils.isBlank(newPwd))
        { throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR); }
        userInfoService.changePassword(principal.getId(), oldPwd, newPwd);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @RequiresPermissions("system:user:data")
    @ApiOperation(value = "查询用户基础信息表", httpMethod = "POST")
    @RequestMapping(value = "/user/data", method = RequestMethod.POST)
    public JsonMessage data(UserInfo entity, Pagination pagin) throws BusinessException
    {
        PaginateResult<UserInfo> result = userInfoService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @RequestMapping(value = "/user/del", method = RequestMethod.POST)
    public JsonMessage del(String ids) throws BusinessException
    {
        userInfoService.deleteBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @PostMapping(value = "/user/changeStatus")
    @RequiresPermissions("system:user:operator")
    @ApiOperation(value = "启用或停用用户", httpMethod = "POST")
    public JsonMessage changeStatus(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException("操作编号不可为空！"); }
        UserInfo userInfo = userInfoService.selectByPrimaryKey(id);
        userInfo.setActive(!userInfo.getActive());
        userInfoService.updateByPrimaryKey(userInfo);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

}
