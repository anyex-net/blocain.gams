/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.utils.RedisLock;
import com.fintech.gams.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.asset.entity.AssetWithdraw;
import com.fintech.gams.asset.service.AssetWithdrawService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;

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

    @PostMapping(value = "/withdraw")
    @ApiOperation(value = "提现出金保存", httpMethod = "POST")
    public JsonMessage withdraw(@ModelAttribute AssetWithdraw assetWithdraw) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        assetWithdraw.setAccountId(OnLineUserUtils.getId());
        assetWithdraw.setWithdrawDate(System.currentTimeMillis());

        //
        assetWithdraw.setTransactionId(SerialnoUtils.withdrawNum());
        assetWithdraw.setStatus(GlobalConst.STATUS_UNCONFIRMED);
        assetWithdraw.setFee(BigDecimal.ZERO);
        if (beanValidator(json, assetWithdraw))
        {
            assetWithdrawService.save(assetWithdraw);
        }
        return json;
    }

    @PostMapping(value = "/withdraw/cancel")
    @ApiOperation(value = "提现出金取消", httpMethod = "POST")
    public JsonMessage cancel(@ModelAttribute AssetWithdraw assetWithdraw) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        // 分布式redis锁判断
        StringBuilder lockName = new StringBuilder(CacheConst.LOCK_ACCOUNT_ASSETDEPOSIT_PREFIX);
        lockName.append(assetWithdraw.getAccountId()).append(assetWithdraw.getTransactionId());
        RedisLock redisLock = new RedisLock(redisTemplate, lockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                AssetWithdraw assetWithdrawDB = assetWithdrawService.selectByPrimaryKey(assetWithdraw.getId());
                if(!assetWithdrawDB.getStatus().equals(GlobalConst.STATUS_UNCONFIRMED)){
                    log.error("提现出金取消失败, assetWithdrawDB.getStatus():{}", assetWithdrawDB.getStatus());
                    throw new BusinessException("提现出金取消失败, assetWithdrawDB.getStatus():" + assetWithdrawDB.getStatus());
                } else {
                    if (beanValidator(json, assetWithdrawDB))
                    {
                        assetWithdrawDB.setStatus(GlobalConst.STATUS_CANCELED);
                        assetWithdrawDB.setUpdateDate(System.currentTimeMillis());
                        assetWithdrawService.updateByPrimaryKeySelective(assetWithdrawDB);
                    }
                }
            } catch (BusinessException e) {
                log.error("cancel error:" + e.getLocalizedMessage());
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

    @PostMapping(value = "/withdraw/list")
    @ApiOperation(value = "提现出金记录查询", httpMethod = "POST")
    public JsonMessage withdrawList(@ModelAttribute AssetWithdraw assetWithdraw, @ModelAttribute Pagination pagin) throws BusinessException
    {
        assetWithdraw.setAccountId(OnLineUserUtils.getId());
        PaginateResult<AssetWithdraw> result = assetWithdrawService.search(pagin, assetWithdraw);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
