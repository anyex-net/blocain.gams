/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.account.model.S3PresignedUrlModel;
import com.fintech.gams.bean.*;
import com.fintech.gams.common.entity.NewsBulletin;
import com.fintech.gams.common.model.NewsBulletinModel;
import com.fintech.gams.common.service.NewsBulletinService;
import com.fintech.gams.config.Amazon;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.system.entity.UserInfo;
import com.fintech.gams.system.service.UserInfoService;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @Autowired(required = false)
    private UserInfoService userInfoService;
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private GlobalProperies properies;

    @GetMapping(value = "/newsBulletin/findBy")
    @RequiresPermissions("common:newsBulletin:data")
    @ApiOperation(value = "根据ID取消息公告", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, newsBulletinService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/newsBulletin/save")
    @RequiresPermissions("common:newsBulletin:operator")
    @ApiOperation(value = "保存消息公告", httpMethod = "POST")
    public JsonMessage save(@RequestBody NewsBulletin info) throws BusinessException {
        if(info.getTitle().getBytes().length>120 || info.getContent().getBytes().length>4000){
            throw new BusinessException("word count exceeded");
        }
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        if (null == info.getId()){
            info.setCreateBy(principal.getId());
            info.setCreateDate(System.currentTimeMillis());
        }
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        if(info.getStatus()==null || info.getStatus()==2){
            info.setReleaseDate(null);
        }
        //定时任务 状态为待发布(0)
        if(info.getIsTimedJob()==true){
            info.setStatus(0);
        }else {
            //非定时任务，状态为已发布(1)
            info.setStatus(1);
            info.setReleaseDate(System.currentTimeMillis());
        }
//        验证参数
        if (beanValidator(json, info)==false ||info.getIsTimedJob()==true && info.getCron()==null )
        {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        if(info.getIsTimedJob()==true && info.getCron()!=null){
            info.setCron(info.getCron() / 1000 / 60 );
        }
        if (null == info.getId())
        {
            info.setId(SerialnoUtils.buildPrimaryKey());
             newsBulletinService.insert(info);
        }
        else
        {
             newsBulletinService.updateByPrimaryKey(info);
        }
        return getJsonMessage(CommonEnums.SUCCESS,info);
    }

    @PostMapping(value = "/newsBulletin/updateStatus")
    @RequiresPermissions("common:newsBulletin:operator")
    @ApiOperation(value = "更新消息公告状态", httpMethod = "POST")
    public JsonMessage updateInfo(Long id, @ApiParam(required = true, name = "status", value = "状态0待发布，1发布，2下架") Integer status) throws BusinessException {
        if (null == id || status == null) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //待发布(0)状态
        if(status == 0){
            return  getJsonMessage(CommonEnums.SUCCESS);
        }
        NewsBulletin info = newsBulletinService.selectByPrimaryKey(id);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        info.setUpdateBy(principal.getId());
        info.setUpdateDate(System.currentTimeMillis());
        info = newsBulletinService.updateInfo(info,status);
        return getJsonMessage(CommonEnums.SUCCESS,info);
    }
    @PostMapping(value = "/newsBulletin/data")
    @RequiresPermissions("common:newsBulletin:data")
    @ApiOperation(value = "查询消息公告", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute NewsBulletinModel entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<NewsBulletin> result = new PaginateResult<>();
        if(entity.getStatus() != null && entity.getStatus()==2 ){
            //状态为已下架，根据发布时间排序
            result  =  newsBulletinService.findOrderyReleaseDate(pagin,entity);
        }
        else {
            //根据更新时间排序
            result  =  newsBulletinService.findOrderyUpdateDate(pagin,entity);
        }
        List<NewsBulletinModel> newsBulletinModels = new ManagedList<>();
        for (NewsBulletin bulletinModel : result.getList()) {
            UserInfo userInfo = new UserInfo();
            if(bulletinModel.getUpdateBy() != null){
                userInfo = userInfoService.selectByPrimaryKey(bulletinModel.getUpdateBy());
            }else {
                userInfo = userInfoService.selectByPrimaryKey(bulletinModel.getCreateBy());
            }
            NewsBulletinModel model = new NewsBulletinModel()
                    .convertToModel(bulletinModel)
                    .setPublisher(userInfo);
            newsBulletinModels.add(model);
        }
        return getJsonMessage(CommonEnums.SUCCESS, new PaginateResult<>(result.getPage(),newsBulletinModels));
    }

    @PostMapping(value = "/newsBulletin/del")
    @RequiresPermissions("common:newsBulletin:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        newsBulletinService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/newsBulletin/generateUploadObjectPresignedUrl")
    @RequiresPermissions("common:newsBulletin:data")
    @ApiOperation(value = "获取上传预签名URL", httpMethod = "GET")
    public JsonMessage putPresignedUrl(String fileName) throws BusinessException
    {
        String dir = "BossFile/NewsBulletin";
        if(StringUtils.isBlank(fileName)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        S3PresignedUrlModel model = new S3PresignedUrlModel();
        model.setPresignedUrl(amazonS3.generateUploadObjectPresignedUrl (properies.getAmazon().getBucket().getTempName(),dir,fileName));
        model.setUrl(properies.getAmazon().getBucket().getTempUrl()+dir+"/"+fileName);
        return getJsonMessage(CommonEnums.SUCCESS, model);
    }
    @PostMapping(value = "/newsBulletin/saveRichTextPicture")
    @RequiresPermissions("common:newsBulletin:operator")
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
            String fileKey = "BossFile/NewsBulletin/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "BossFile/NewsBulletin/" + tempS3Path[tempS3Path.length - 1];
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
