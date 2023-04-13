/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.common.model.ThumbsUpModel;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.utils.NetworkUtils;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Express;
import com.fintech.gams.common.service.ExpressService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;

/**
 * 快讯信息 控制器
 * <p>File：ExpressController.java </p>
 * <p>Title: ExpressController </p>
 * <p>Description:ExpressController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "快讯信息")
public class ExpressController extends GenericController
{
    @Autowired(required = false)
    private ExpressService expressService;
    
    @GetMapping(value = "/express/detail")
    @ApiOperation(value = "根据ID取快讯信息", httpMethod = "GET")
    public JsonMessage detail(Long id) throws BusinessException
    {
        if (null == id) { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, expressService.detail(id));
    }
    
    @PostMapping(value = "/express/data")
    @ApiOperation(value = "查询快讯信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Express entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        // type: 推荐/新闻/资讯
        if (null == entity.getType()) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        entity.setStatus(1);
        PaginateResult<Express> result = expressService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/express/thumbsUpOrCancel")
    @ApiOperation(value = "点赞或取消点赞快讯信息", httpMethod = "POST")
    public JsonMessage thumbsUpOrCancel(HttpServletRequest request, @ModelAttribute ThumbsUpModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        String ip = NetworkUtils.getIpAddr(request);
        if (beanValidator(json, model)) {
            expressService.thumbsUpOrCancel(ip, model);
        }
        return json;
    }

    @PostMapping(value = "/express/reading/quantity")
    @ApiOperation(value = "统计快讯信息阅读量", httpMethod = "POST")
    public JsonMessage readingQuantity(@ApiParam(required = true, name = "id", value = "快讯信息ID")
                                                   Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Express express = expressService.selectByPrimaryKey(id);
        express.setReadingQuantity(express.getReadingQuantity() + 1);
        expressService.updateByPrimaryKeySelective(express);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/express/hotReading")
    @ApiOperation(value = "查询热门阅读的快讯信息", httpMethod = "GET")
    public JsonMessage hotReading(@ApiParam(name = "lang", value = "en_US,zh_CN,zh_HK") String lang) throws BusinessException {
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        return this.getJsonMessage(CommonEnums.SUCCESS, expressService.hotReading(lang));
    }
}
