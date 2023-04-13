/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.account.model.S3PresignedUrlModel;
import com.fintech.gams.bean.*;
import com.fintech.gams.common.entity.Express;
import com.fintech.gams.common.service.ExpressService;
import com.fintech.gams.config.Amazon;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.UserInfo;
import com.fintech.gams.system.service.UserInfoService;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/common/express")
@Api(description = "快讯信息")
public class ExpressController extends GenericController
{
    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private GlobalProperies properies;

    @Autowired(required = false)
    private ExpressService expressService;

    @Autowired(required = false)
    private UserInfoService userInfoService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("common:express:data")
    @ApiOperation(value = "根据ID取快讯信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, expressService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("common:express:operator")
    @ApiOperation(value = "保存快讯信息", httpMethod = "POST")
    public JsonMessage save(@RequestBody Express info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId()) {
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
            // 阅读量
            info.setReadingQuantity(0L);
            // 点赞数
            info.setThumbsUp(0L);
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        // 图片不是必填
        if (StringUtils.isNotBlank(info.getPictUrl())) {
            String tepmPath = "https://" + info.getPictUrl().split("/")[2] + "/";
            String tepmUrl = properies.getAmazon().getBucket().getTempUrl();
            // 只有等于临时空间的地址的时候才进来（更新或保存图片），不等于说明没有更新图片还是原来的图片
            if (tepmPath.equals(tepmUrl)) {
                // 说明是更新或者新增
                String[] tempS3Path = info.getPictUrl().split("/");
                // s3 临时空间文件key
                String fileKey = "BossFile/MarketNews/" + tempS3Path[tempS3Path.length - 1];
                // s3 正式空间文件key
                String releaseKey = "BossFile/MarketNews/" + tempS3Path[tempS3Path.length - 1];
                Boolean isTransfered = amazonS3.transferObject(fileKey, releaseKey);
                Amazon amazon = properies.getAmazon();
                info.setPictUrl(amazon.getBucket().getReleaseUrl() + releaseKey);
                if (!isTransfered) {
                    throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
                }
            }
        }
        if (beanValidator(json, info))
        {
            expressService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("common:express:data")
    @ApiOperation(value = "查询快讯信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Express entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Express> result = expressService.search(pagin,entity);
        for (Express express : result.getList()) {
            UserInfo userInfo = userInfoService.selectByPrimaryKey(express.getUpdateBy());
            express.setCreateUser(userInfo);
        }
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("common:express:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        expressService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/generateUploadObjectPresignedUrl")
    @RequiresPermissions("common:express:data")
    @ApiOperation(value = "获取上传预签名URL", httpMethod = "GET")
    public JsonMessage putPresignedUrl(String fileName) throws BusinessException
    {
        String dir = "BossFile/MarketNews";
        if(StringUtils.isBlank(fileName)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        S3PresignedUrlModel model = new S3PresignedUrlModel();
        model.setPresignedUrl(amazonS3.generateUploadObjectPresignedUrl (properies.getAmazon().getBucket().getTempName(),dir,fileName));
        model.setUrl(properies.getAmazon().getBucket().getTempUrl()+dir+"/"+fileName);
        return getJsonMessage(CommonEnums.SUCCESS, model);
    }

    @PostMapping(value = "/updateExpressStatus")
    @RequiresPermissions("common:express:operator")
    @ApiOperation(value = "根据ID和状态修改快讯信息下架或者发布", httpMethod = "POST")
    public JsonMessage updateExpressStatus(@ApiParam(required = true, name = "id", value = "快讯信息的id") Long id,
                                           @ApiParam(required = true, name = "status", value = "状态0待发布，1发布，2下架")
                                                   Integer status) throws BusinessException {
        if (null == id || null == status) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Express express = expressService.selectByPrimaryKey(id);
        if (1 == status) {
            // 发布
            express.setStatus(1);
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            express.setUpdateBy(principal.getId());
            express.setUpdateDate(System.currentTimeMillis());
        } else {  // 下架
            express.setStatus(2);
        }
        expressService.updateByPrimaryKeySelective(express);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/richTextPicture")
    @RequiresPermissions("common:express:operator")
    @ApiOperation(value = "存储富文本中的图片", httpMethod = "POST")
    public JsonMessage richTextPicture(@ApiParam(required = true, name = "path", value = "上传的地址路径") String path) {
        if (null == path) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        String tepmPath = "https://" + path.split("/")[2] + "/";
        String tepmUrl = properies.getAmazon().getBucket().getTempUrl();
        // 只有等于临时空间的地址的时候才进来（更新或保存图片），不等于说明没有更新图片还是原来的图片
        String pictUrl = "";
        if (tepmPath.equals(tepmUrl)) {
            // 说明是更新或者新增
            String[] tempS3Path = path.split("/");
            // s3 临时空间文件key
            String fileKey = "BossFile/MarketNews/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "BossFile/MarketNews/" + tempS3Path[tempS3Path.length - 1];
            Boolean isTransfered = amazonS3.transferObject(fileKey, releaseKey);
            Amazon amazon = properies.getAmazon();
            pictUrl = amazon.getBucket().getReleaseUrl() + releaseKey;
            if (!isTransfered) {
                throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
            }
        }
        return getJsonMessage(CommonEnums.SUCCESS, pictUrl);
    }
}
