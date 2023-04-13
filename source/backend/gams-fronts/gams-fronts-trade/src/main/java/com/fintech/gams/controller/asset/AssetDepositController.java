/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.asset;

import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
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

import com.fintech.gams.asset.entity.AssetDeposit;
import com.fintech.gams.asset.service.AssetDepositService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.OnLineUserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;

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
    
    @PostMapping(value = "/deposit")
    @ApiOperation(value = "充值入金保存", httpMethod = "POST")
    public JsonMessage deposit(@ModelAttribute AssetDeposit assetDeposit) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        assetDeposit.setAccountId(OnLineUserUtils.getId());
        assetDeposit.setDepositDate(System.currentTimeMillis());

        //
        assetDeposit.setTransactionId(SerialnoUtils.depositNum());
        assetDeposit.setStatus(GlobalConst.STATUS_UNCONFIRMED);
        assetDeposit.setFee(BigDecimal.ZERO);
        if (beanValidator(json, assetDeposit))
        {
            assetDepositService.save(assetDeposit);
        }
        return json;
    }

    @PostMapping(value = "/deposit/cancel")
    @ApiOperation(value = "充值入金取消", httpMethod = "POST")
    public JsonMessage cancel(@ModelAttribute AssetDeposit assetDeposit) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        // 分布式redis锁判断
        StringBuilder lockName = new StringBuilder(CacheConst.LOCK_ACCOUNT_ASSETDEPOSIT_PREFIX);
        lockName.append(assetDeposit.getAccountId()).append(assetDeposit.getTransactionId());
        RedisLock redisLock = new RedisLock(redisTemplate, lockName.toString(), 3);
        if (redisLock.lock()) {
            try {
                AssetDeposit assetDepositDB = assetDepositService.selectByPrimaryKey(assetDeposit.getId());
                if(!assetDepositDB.getStatus().equals(GlobalConst.STATUS_UNCONFIRMED)){
                    log.error("充值入金取消失败, assetDepositDB.getStatus():{}", assetDepositDB.getStatus());
                    throw new BusinessException("充值入金取消失败, assetDepositDB.getStatus():" + assetDepositDB.getStatus());
                } else {
                    if (beanValidator(json, assetDepositDB))
                    {
                        assetDepositDB.setStatus(GlobalConst.STATUS_CANCELED);
                        assetDepositDB.setUpdateDate(System.currentTimeMillis());
                        assetDepositService.updateByPrimaryKeySelective(assetDepositDB);
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

    @PostMapping(value = "/deposit/list")
    @ApiOperation(value = "充值入金记录表", httpMethod = "POST")
    public JsonMessage depositList(@ModelAttribute AssetDeposit assetDeposit, Pagination pagin) throws BusinessException
    {
        assetDeposit.setAccountId(OnLineUserUtils.getId());
        PaginateResult<AssetDeposit> result = assetDepositService.search(pagin, assetDeposit);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
