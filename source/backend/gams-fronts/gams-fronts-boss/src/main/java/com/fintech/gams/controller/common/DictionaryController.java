/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 字典数据 控制器
 * <p>File：DictionaryController.java </p>
 * <p>Title: DictionaryController </p>
 * <p>Description:DictionaryController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "字典数据")
public class DictionaryController extends GenericController
{
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    
    @GetMapping(value = "/dictionary/findById")
    //@RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "根据ID取字典数据", httpMethod = "GET")
    public JsonMessage findById(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, dictionaryService.selectByPrimaryKey(id));
    }
    
    @GetMapping(value = "/dictionary/findByCode")
    //@RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "根据字典编码和语言取字典项", httpMethod = "GET")
    public JsonMessage findByCode(String dictCode, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(dictCode))
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        return this.getJsonMessage(CommonEnums.SUCCESS, dictionaryService.findByCode(dictCode, lang));
    }

    @PostMapping(value = "/dictionary/data")
    //@RequiresPermissions("common:dictionary:data")
    @ApiOperation(value = "查询字典数据", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Dictionary entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Dictionary> result = dictionaryService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/dictionary/save")
    @RequiresPermissions("common:dictionary:operator")
    @ApiOperation(value = "保存字典数据", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Dictionary info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId())
        {
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        if (beanValidator(json, info))
        {
            dictionaryService.save(info);
        }
        return json;
    }
    
    @PostMapping(value = "/dictionary/del")
    @RequiresPermissions("common:dictionary:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        dictionaryService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
