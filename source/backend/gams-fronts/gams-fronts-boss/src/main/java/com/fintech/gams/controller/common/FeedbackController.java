/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.account.model.S3PresignedUrlModel;
import com.fintech.gams.bean.*;
import com.fintech.gams.common.entity.Feedback;
import com.fintech.gams.common.model.FeedbackModel;
import com.fintech.gams.common.service.FeedbackService;
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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * FEEDBACK 控制器
 * <p>File：FeedbackController.java </p>
 * <p>Title: FeedbackController </p>
 * <p>Description:FeedbackController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/common/feedback")
@Api(description = "FEEDBACK")
public class FeedbackController extends GenericController
{
    @Autowired(required = false)
    private FeedbackService feedbackService;
    @Autowired(required = false)
    private UserInfoService userInfoService;
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private GlobalProperies properies;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("common:feedback:data")
    @ApiOperation(value = "根据ID取FEEDBACK", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, feedbackService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("common:feedback:operator")
    @ApiOperation(value = "保存FEEDBACK", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute Feedback info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        info.setType("Online");
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if(info.getId() == null){
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        if (null == info.getId() && beanValidator(json, info)) {
            feedbackService.save(info);
        }
        else {
             feedbackService.updateByPrimaryKeySelective(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("common:feedback:data")
    @ApiOperation(value = "查询FEEDBACK", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute Feedback entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<Feedback> result = feedbackService.findInfoList(pagin,entity);
        List<FeedbackModel> feedbackModels = new ArrayList<>();
        for (Feedback feedback : result.getList()) {
            UserInfo userInfo;
            userInfo = userInfoService.selectByPrimaryKey(feedback.getCreateBy());
            FeedbackModel model = new FeedbackModel()
                    .convertToModel(feedback);
            if(userInfo != null){
                model.setCreateBy(userInfo.getUserName());
            }
            feedbackModels.add(model);
        }
        return getJsonMessage(CommonEnums.SUCCESS, new PaginateResult<>(result.getPage(),feedbackModels));
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("common:feedback:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        feedbackService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/generateUploadObjectPresignedUrl")
    @RequiresPermissions("common:feedback:data")
    @ApiOperation(value = "获取上传预签名URL", httpMethod = "GET")
    public JsonMessage putPresignedUrl(String fileName) throws BusinessException
    {
        String dir = "BossFile/feedback";
        if(StringUtils.isBlank(fileName)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        S3PresignedUrlModel model = new S3PresignedUrlModel();
        model.setPresignedUrl(amazonS3.generateUploadObjectPresignedUrl (properies.getAmazon().getBucket().getTempName(),dir,fileName));
        model.setUrl(properies.getAmazon().getBucket().getTempUrl()+dir+"/"+fileName);
        return getJsonMessage(CommonEnums.SUCCESS, model);
    }
    @PostMapping(value = "/saveRichTextPicture")
    @RequiresPermissions("common:feedback:operator")
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
            String fileKey = "BossFile/feedback/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "BossFile/feedback/" + tempS3Path[tempS3Path.length - 1];
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
