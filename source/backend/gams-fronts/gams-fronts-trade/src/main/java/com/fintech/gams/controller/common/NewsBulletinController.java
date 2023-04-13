/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.NewsBulletin;
import com.fintech.gams.common.model.NewsBulletinModel;
import com.fintech.gams.common.service.NewsBulletinService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 消息公告 控制器
 * <p>File：NewsAnnounceController.java </p>
 * <p>Title: NewsAnnounceController </p>
 * <p>Description:NewsAnnounceController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON )
@Api(description = "消息公告")
public class NewsBulletinController extends GenericController
{
    @Autowired(required = false)
    private NewsBulletinService newsBulletinService;

    @GetMapping(value = "/newsBulletin/findBy")
    @ApiOperation(value = "根据ID取消息公告", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, newsBulletinService.selectByPrimaryKey(id));
    }


    @PostMapping(value = "/newsBulletin/data")
    @ApiOperation(value = "查询消息公告", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute NewsBulletinModel entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        //只能查询已发布
        entity.setStatus(1);
        PaginateResult<NewsBulletin> result = new PaginateResult<>();
        //        验证参数
        if (entity.getClientType().isEmpty() || entity.getClientType() ==null)
        {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //状态为已下架，根据发布时间排序
        result  =  newsBulletinService.findOrderyReleaseDate(pagin,entity);

        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
