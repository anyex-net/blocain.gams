/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.account.model.S3PresignedUrlModel;
import com.fintech.gams.bean.*;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.HelpCenter;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.HelpCenterService;
import com.fintech.gams.config.Amazon;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.consts.GlobalConst;
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

/**
 * 帮助中心 控制器
 * <p>File：HelpCenterController.java </p>
 * <p>Title: HelpCenterController </p>
 * <p>Description:HelpCenterController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "帮助中心")
public class HelpCenterController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = HelpCenterService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private HelpCenterService helpCenterService;

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private GlobalProperies properies;

    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @Autowired(required = false)
    private UserInfoService userInfoService;

    @GetMapping(value = "/helpCenter/findBy")
    @RequiresPermissions("common:helpCenter:data")
    @ApiOperation(value = "根据ID取帮助中心", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, helpCenterService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/helpCenter/save")
    @RequiresPermissions("common:helpCenter:operator")
    @ApiOperation(value = "保存帮助中心", httpMethod = "POST")
    public JsonMessage save(@RequestBody HelpCenter info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId()){
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
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
                String fileKey = "BossFile/HelpCenter/" + tempS3Path[tempS3Path.length - 1];
                // s3 正式空间文件key
                String releaseKey = "BossFile/HelpCenter/" + tempS3Path[tempS3Path.length - 1];
                Boolean isTransfered = amazonS3.transferObject(fileKey, releaseKey);
                Amazon amazon = properies.getAmazon();
                info.setPictUrl(amazon.getBucket().getReleaseUrl() + releaseKey);
                if (!isTransfered) {
                    throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
                }
            }
        }
        // 把问题解答内容中的html标签去除
        info.setSearchContent(StringUtils.cleanHtmlElems(info.getProblemSolving()));
        if (beanValidator(json, info))
        {
            helpCenterService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/helpCenter/data")
    @RequiresPermissions("common:helpCenter:data")
    @ApiOperation(value = "查询帮助中心", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute HelpCenter entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<HelpCenter> result = helpCenterService.search(pagin,entity);
        for (HelpCenter helpCenter : result.getList()) {
            Dictionary problemLevelOneInfo = dictionaryService.findDictionary(helpCenter.getProblemType(), helpCenter.getProblemLevelOne(), helpCenter.getLang());
            helpCenter.setProblemLevelOneInfo(problemLevelOneInfo);
            UserInfo userInfo = userInfoService.selectByPrimaryKey(helpCenter.getUpdateBy());
            helpCenter.setCreateUser(userInfo);
        }
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/helpCenter/del")
    @RequiresPermissions("common:helpCenter:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        helpCenterService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @PostMapping(value = "/helpCenter/updateHelpCenterStatus")
    @RequiresPermissions("common:helpCenter:operator")
    @ApiOperation(value = "根据ID修改帮助中心问题下架或者发布", httpMethod = "POST")
    public JsonMessage updateExpressStatus(@ApiParam(required = true, name = "id", value = "帮助中心id") Long id,
                                           @ApiParam(required = true, name = "status", value = "状态0待发布，1发布，2下架")
                                                   Integer status) throws BusinessException {
        if (null == id || null == status) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        HelpCenter helpCenter = helpCenterService.selectByPrimaryKey(id);
        if (1 == status) {
            // 发布
            helpCenter.setStatus(1);
            UserPrincipal principal = OnLineUserUtils.getPrincipal();
            helpCenter.setUpdateBy(principal.getId());
            helpCenter.setUpdateDate(System.currentTimeMillis());
        } else {  // 下架
            helpCenter.setStatus(2);
        }
        helpCenterService.updateByPrimaryKeySelective(helpCenter);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/helpCenter/generateUploadObjectPresignedUrl")
    @RequiresPermissions("common:helpCenter:data")
    @ApiOperation(value = "获取上传预签名URL", httpMethod = "GET")
    public JsonMessage putPresignedUrl(String fileName) throws BusinessException {
        String dir = "BossFile/HelpCenter";
        if (StringUtils.isBlank(fileName)) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        S3PresignedUrlModel model = new S3PresignedUrlModel();
        model.setPresignedUrl(amazonS3.generateUploadObjectPresignedUrl(properies.getAmazon().getBucket().getTempName(), dir, fileName));
        model.setUrl(properies.getAmazon().getBucket().getTempUrl() + dir + "/" + fileName);
        return getJsonMessage(CommonEnums.SUCCESS, model);
    }

    @PostMapping(value = "/helpCenter/richTextPicture")
    @RequiresPermissions("common:helpCenter:operator")
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
            String fileKey = "BossFile/HelpCenter/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "BossFile/HelpCenter/" + tempS3Path[tempS3Path.length - 1];
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
