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

import com.fintech.gams.asset.entity.AssetWithdraw;
import com.fintech.gams.asset.service.AssetWithdrawService;
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
 * 提现出金记录表 控制器
 * <p>File：AssetWithdrawController.java </p>
 * <p>Title: AssetWithdrawController </p>
 * <p>Description:AssetWithdrawController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping(GlobalConst.ASSET)
@Api(description = "提现出金记录表")
public class AssetWithdrawController extends GenericController
{
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired(required = false)
    private AssetWithdrawService assetWithdrawService;

    @PostMapping(value = "/withdraw/data")
    @RequiresPermissions("asset:assetWithdraw:data")
    @ApiOperation(value = "查询提现出金记录表", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AssetWithdraw entity, @ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<AssetWithdraw> result = assetWithdrawService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @GetMapping(value = "/withdraw/findBy")
    @RequiresPermissions("asset:assetWithdraw:data")
    @ApiOperation(value = "根据ID取提现出金记录表", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        return this.getJsonMessage(CommonEnums.SUCCESS, assetWithdrawService.selectByPrimaryKey(id));
    }
    
    @PostMapping(value = "/withdraw/check")
    @RequiresPermissions("asset:assetWithdraw:operator")
    @ApiOperation(value = "审核提现出金记录表", httpMethod = "POST")
    public JsonMessage check(@ModelAttribute AssetWithdraw info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        // 分布式redis锁判断
        StringBuilder lockName = new StringBuilder(CacheConst.LOCK_ACCOUNT_ASSETWITHDRAW_PREFIX);
        lockName.append(info.getAccountId()).append(info.getTransactionId());
        RedisLock redisLock = new RedisLock(redisTemplate, lockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                AssetWithdraw assetWithdrawDB = assetWithdrawService.selectByPrimaryKey(info.getId());
                if(!assetWithdrawDB.getStatus().equals(GlobalConst.STATUS_UNCONFIRMED)){
                    log.error("提现出金审核失败, assetDepositDB.getStatus():{}", assetWithdrawDB.getStatus());
                    throw new BusinessException("提现出金审核失败, assetDepositDB.getStatus():" + assetWithdrawDB.getStatus());
                } else {
                    if (beanValidator(json, assetWithdrawDB))
                    {
                        assetWithdrawDB.setStatus(GlobalConst.STATUS_CONFIRMED);
                        assetWithdrawDB.setWithdrawConfirmDate(System.currentTimeMillis());
                        assetWithdrawDB.setUpdateDate(System.currentTimeMillis());
                        assetWithdrawService.updateByPrimaryKeySelective(assetWithdrawDB);
                    }
                }
            } catch (BusinessException e) {
                log.error("check save error:" + e.getLocalizedMessage());
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
    
    @PostMapping(value = "/withdraw/del")
    @RequiresPermissions("asset:assetWithdraw:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        assetWithdrawService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
