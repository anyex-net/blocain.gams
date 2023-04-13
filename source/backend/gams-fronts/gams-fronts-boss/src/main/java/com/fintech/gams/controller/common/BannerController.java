/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.account.model.S3PresignedUrlModel;
import com.fintech.gams.bean.*;
import com.fintech.gams.config.Amazon;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.common.entity.Banner;
import com.fintech.gams.common.service.BannerService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * Banner信息 控制器
 * <p>File：BannerController.java </p>
 * <p>Title: BannerController </p>
 * <p>Description:BannerController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/common/banner")
@Api(description = "Banner信息")
public class BannerController extends GenericController
{
    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private GlobalProperies properies;

    @Autowired(required = false)
    private BannerService bannerService;
    
    @GetMapping(value = "/findBy")
    @RequiresPermissions("common:banner:data")
    @ApiOperation(value = "根据ID取Banner信息", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, bannerService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/save")
    @RequiresPermissions("common:banner:operator")
    @ApiOperation(value = "保存Banner信息", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Banner info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId()) {
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        //
        String[] tempS3Path = info.getPictUrl().split("/");
        // s3 临时空间文件key
        String fileKey = "BossFile/Banner/" + tempS3Path[tempS3Path.length - 1];
        // s3 正式空间文件key
        String releaseKey = "BossFile/Banner/" + tempS3Path[tempS3Path.length - 1];
        Boolean isTransfered = amazonS3.transferObject(fileKey, releaseKey);
        Amazon amazon = properies.getAmazon();
        info.setPictUrl(amazon.getBucket().getReleaseUrl() + releaseKey);
        if (!isTransfered){
            throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
        }
        //
        if (beanValidator(json, info))
        {
            bannerService.save(info);
        }
        return json;
    }
    
    @PostMapping(value = "/data")
    @RequiresPermissions("common:banner:data")
    @ApiOperation(value = "查询Banner信息", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Banner entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Banner> result = bannerService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @PostMapping(value = "/del")
    @RequiresPermissions("common:banner:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        bannerService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/generateUploadObjectPresignedUrl")
    @RequiresPermissions("common:banner:data")
    @ApiOperation(value = "获取上传预签名URL", httpMethod = "GET")
    public JsonMessage putPresignedUrl(String fileName) throws BusinessException
    {
        String dir = "BossFile/Banner";
        if(StringUtils.isBlank(fileName)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        S3PresignedUrlModel model = new S3PresignedUrlModel();
        model.setPresignedUrl(amazonS3.generateUploadObjectPresignedUrl (properies.getAmazon().getBucket().getTempName(),dir,fileName));
        model.setUrl(properies.getAmazon().getBucket().getTempUrl()+dir+"/"+fileName);
        return getJsonMessage(CommonEnums.SUCCESS, model);
    }
}
