/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.mapper.AccountMapper;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.utils.RedisUtils;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import com.ib.client.Contract;
import com.ib.client.Execution;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.ib.entity.IbAccountTrade;
import com.fintech.gams.ib.mapper.IbAccountTradeMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * IB账户交易流水表 服务实现类
 * <p>File：IbAccountTradeServiceImpl.java </p>
 * <p>Title: IbAccountTradeServiceImpl </p>
 * <p>Description:IbAccountTradeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountTradeServiceImpl extends GenericServiceImpl<IbAccountTrade> implements IbAccountTradeService
{
    protected IbAccountTradeMapper ibAccountTradeMapper;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    public IbAccountTradeServiceImpl(IbAccountTradeMapper ibAccountTradeMapper) {
        super(ibAccountTradeMapper);
        this.ibAccountTradeMapper = ibAccountTradeMapper;
    }

    /**
     * @param reqId
     * @param contract
     * @param execution
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessExecDetails(int reqId, Contract contract, Execution execution) {
        log.debug("execDetails ExecDetails. " + reqId + " - [" + contract.symbol() + "], [" + contract.secType() + "], [" + contract.currency() + "], ["
                + execution.execId() + "], [" + execution.orderId() + "], [" + execution.shares() + "]" + ", [" + execution.lastLiquidity() + "]"
                + ", [" + execution.time() + "]");

        // 根据记录reqId对应的accountCode
        String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append(reqId).toString();
        String accountCode = RedisUtils.get(cacheKey);
        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily is not exist or AccountID is null");
        } else {
            if (StringUtils.isNoneEmpty(accountCode)) {
                IbAccountTrade ibAccountTrade = new IbAccountTrade();
                ibAccountTrade.setAccountCode(accountCode);
                ibAccountTrade.setSymbol(contract.symbol());
                ibAccountTrade.setSecType(contract.getSecType());
                ibAccountTrade.setCurrency(contract.currency());
                ibAccountTrade.setExecId(execution.execId());
                ibAccountTrade.setOrderId(String.valueOf(execution.orderId()));
                List<IbAccountTrade> listIbAccountTrade = ibAccountTradeMapper.findList(ibAccountTrade);
                if (null != listIbAccountTrade && listIbAccountTrade.size() <= 0) {
                    // 插入数据
                    ibAccountTrade.setId(SerialnoUtils.buildPrimaryKey());
                    ibAccountTrade.setAccountId(listIbAccountFamily.get(0).getAccountId());
                    ibAccountTrade.setExchange(contract.exchange());
                    ibAccountTrade.setSide(execution.side());
                    ibAccountTrade.setPrice(BigDecimal.valueOf(execution.price()));
                    ibAccountTrade.setShares(BigDecimal.valueOf(execution.shares()));
                    ibAccountTrade.setAvgPrice(BigDecimal.valueOf(execution.avgPrice()));
                    // ibAccountTrade.setExecTime(System.currentTimeMillis());
                    ibAccountTrade.setExecTime(execution.time());
                    ibAccountTrade.setRemark("IB Message Insert");
                    ibAccountTrade.setCreateDate(System.currentTimeMillis());
                    ibAccountTrade.setUpdateDate(System.currentTimeMillis());
                    log.info("ibAccountTrade插入: {}", ibAccountTrade);
                    ibAccountTradeMapper.insert(ibAccountTrade);
                }
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountTrade entity) throws BusinessException
    {
        this.beanValidator(entity);

        // 判断 accountId 是否合法
        Account account = accountMapper.selectByPrimaryKey(entity.getAccountId());
        if (null == account) {
            log.error("ibAccountFamily error: ACCOUNT表中不存在相关的IB代码信息，accountID不合法");
            throw new BusinessException("账户ID不合法，内部不存在相关信息");
        }

        if (null == entity.getId())
        {
            entity.setId(SerialnoUtils.buildPrimaryKey());
            return ibAccountTradeMapper.insert(entity);
        }
        else
        {
            return ibAccountTradeMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
