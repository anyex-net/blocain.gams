/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.utils.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.asset.entity.AssetDeposit;
import com.fintech.gams.asset.service.AssetDepositService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * 充值入金记录表 控制器
 * <p>File：AssetDepositController.java </p>
 * <p>Title: AssetDepositController </p>
 * <p>Description:AssetDepositController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.ASSET)
@Api(description = "充值入金记录表")
public class AssetDepositController extends GenericController
{
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private AssetDepositService assetDepositService;

    @PostMapping(value = "/deposit/data")
    @RequiresPermissions("asset:assetDeposit:data")
    @ApiOperation(value = "查询充值入金记录表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AssetDeposit entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AssetDeposit> result = assetDepositService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/deposit/findBy")
    @RequiresPermissions("asset:assetDeposit:data")
    @ApiOperation(value = "根据ID取充值入金记录表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, assetDepositService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/deposit/check")
    @RequiresPermissions("asset:assetDeposit:operator")
    @ApiOperation(value = "审核充值入金记录表", httpMethod = "POST")
    public JsonMessage check(@ModelAttribute AssetDeposit info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        // 分布式redis锁判断
        StringBuilder lockName = new StringBuilder(CacheConst.LOCK_ACCOUNT_ASSETDEPOSIT_PREFIX);
        lockName.append(info.getAccountId()).append(info.getTransactionId());
        RedisLock redisLock = new RedisLock(redisTemplate, lockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                AssetDeposit assetDepositDB = assetDepositService.selectByPrimaryKey(info.getId());
                if(!assetDepositDB.getStatus().equals(GlobalConst.STATUS_UNCONFIRMED)){
                    log.error("充值入金审核失败, assetDepositDB.getStatus():{}", assetDepositDB.getStatus());
                    throw new BusinessException("充值入金审核失败, assetDepositDB.getStatus():" + assetDepositDB.getStatus());
                } else {
                    if (beanValidator(json, assetDepositDB))
                    {
                        assetDepositDB.setStatus(GlobalConst.STATUS_CONFIRMED);
                        assetDepositDB.setDepositConfirmDate(System.currentTimeMillis());
                        assetDepositDB.setUpdateDate(System.currentTimeMillis());
                        assetDepositService.updateByPrimaryKeySelective(assetDepositDB);
                    }
                }
            } catch (BusinessException e) {
                log.error("check error:" + e.getLocalizedMessage());
                throw new BusinessException(e.getLocalizedMessage());
            } finally {
                redisLock.unlock();
            }
        }
        else {
            log.error("check get redisLock lock failed!");
            throw new BusinessException("get redisLock lock failed!");
        }

        return json;
    }
    
    @PostMapping(value = "/deposit/del")
    @RequiresPermissions("asset:assetDeposit:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        assetDepositService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
