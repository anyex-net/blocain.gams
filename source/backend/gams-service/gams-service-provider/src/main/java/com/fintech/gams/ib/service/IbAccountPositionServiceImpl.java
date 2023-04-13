/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.mapper.AccountMapper;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.utils.SerialnoUtils;
import com.ib.client.Contract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.ib.entity.IbAccountPosition;
import com.fintech.gams.ib.mapper.IbAccountPositionMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * IB账户持仓表 服务实现类
 * <p>File：IbAccountPositionServiceImpl.java </p>
 * <p>Title: IbAccountPositionServiceImpl </p>
 * <p>Description:IbAccountPositionServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountPositionServiceImpl extends GenericServiceImpl<IbAccountPosition> implements IbAccountPositionService
{
    protected IbAccountPositionMapper ibAccountPositionMapper;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    public IbAccountPositionServiceImpl(IbAccountPositionMapper ibAccountPositionMapper) {
        super(ibAccountPositionMapper);
        this.ibAccountPositionMapper = ibAccountPositionMapper;
    }

    /**
     * @param contract
     * @param position
     * @param marketPrice
     * @param marketValue
     * @param averageCost
     * @param unrealizedPNL
     * @param realizedPNL
     * @param accountCode
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessPortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost,
                                          double unrealizedPNL, double realizedPNL, String accountCode) {
        log.debug("updatePortfolio UpdatePortfolio. " + contract.symbol() + ", " + contract.secType() + " @ " + contract.exchange() + ": Position: " + position + ", MarketPrice: "
                + marketPrice + ", MarketValue: " + marketValue + ", AverageCost: " + averageCost + ", UnrealizedPNL: " + unrealizedPNL + ", RealizedPNL: " + realizedPNL
                + ", AccountCode: " + accountCode);
        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily is not exist or AccountID is null");
        } else {
            IbAccountPosition ibAccountPosition = new IbAccountPosition();
            ibAccountPosition.setAccountCode(accountCode);
            ibAccountPosition.setSymbol(contract.symbol());
            ibAccountPosition.setSecType(contract.getSecType());
            ibAccountPosition.setCurrency(contract.currency());
            List<IbAccountPosition> listIbAccountPosition = ibAccountPositionMapper.findList(ibAccountPosition);

            if (null != listIbAccountPosition && listIbAccountPosition.size() <= 0) {
                // 插入数据
                ibAccountPosition.setId(SerialnoUtils.buildPrimaryKey());
                ibAccountPosition.setAccountId(listIbAccountFamily.get(0).getAccountId());
                ibAccountPosition.setExchange(contract.exchange());
                ibAccountPosition.setPosition(BigDecimal.valueOf(position));
                ibAccountPosition.setMarketPrice(BigDecimal.valueOf(marketPrice));
                ibAccountPosition.setMarketValue(BigDecimal.valueOf(marketValue));
                ibAccountPosition.setAverageCost(BigDecimal.valueOf(averageCost));
                ibAccountPosition.setUnrealizedPnl(BigDecimal.valueOf(unrealizedPNL));
                ibAccountPosition.setRealizedPnl(BigDecimal.valueOf(realizedPNL));
                ibAccountPosition.setRemark("IB消息推送入库");
                ibAccountPosition.setCreateDate(System.currentTimeMillis());
                log.debug("ibAccountPosition插入: {}", ibAccountPosition);
                ibAccountPositionMapper.insert(ibAccountPosition);
            } else {
                ibAccountPosition = listIbAccountPosition.get(0);

                ibAccountPosition.setPosition(BigDecimal.valueOf(position));
                ibAccountPosition.setMarketPrice(BigDecimal.valueOf(marketPrice));
                ibAccountPosition.setMarketValue(BigDecimal.valueOf(marketValue));
                ibAccountPosition.setAverageCost(BigDecimal.valueOf(averageCost));
                ibAccountPosition.setUnrealizedPnl(BigDecimal.valueOf(unrealizedPNL));
                ibAccountPosition.setRealizedPnl(BigDecimal.valueOf(realizedPNL));
                ibAccountPosition.setRemark("IB消息推送更新");
                ibAccountPosition.setUpdateDate(System.currentTimeMillis());
                log.debug("ibAccountPosition更新: {}", ibAccountPosition);
                ibAccountPositionMapper.updateByPrimaryKeySelective(ibAccountPosition);
            }
        }
    }

    /**
     * @param accountCode
     * @param contract
     * @param position
     * @param averageCost
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessPosition(String accountCode, Contract contract, double position, double averageCost) {
        log.debug("position Position. accountCode: " + accountCode + " - Symbol: " + contract.symbol() + ", SecType: " + contract.secType() + ", Currency: " + contract.currency() + ", Position: "
                + position + ", Avg cost: " + averageCost);
        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily is not exist or AccountID is null");
        } else {
            IbAccountPosition ibAccountPosition = new IbAccountPosition();
            ibAccountPosition.setAccountCode(accountCode);
            ibAccountPosition.setSymbol(contract.symbol());
            ibAccountPosition.setSecType(contract.getSecType());
            ibAccountPosition.setCurrency(contract.currency());
            List<IbAccountPosition> listIbAccountPosition = ibAccountPositionMapper.findList(ibAccountPosition);
            if (null != listIbAccountPosition && listIbAccountPosition.size() <= 0) {
                // 插入数据
                ibAccountPosition.setId(SerialnoUtils.buildPrimaryKey());
                ibAccountPosition.setAccountId(listIbAccountFamily.get(0).getAccountId());
                ibAccountPosition.setExchange(contract.exchange());
                ibAccountPosition.setPosition(BigDecimal.valueOf(position));
                ibAccountPosition.setAverageCost(BigDecimal.valueOf(averageCost));
                ibAccountPosition.setRemark("IB消息推送入库");
                ibAccountPosition.setCreateDate(System.currentTimeMillis());
                log.debug("ibAccountPosition插入: {}", ibAccountPosition);
                ibAccountPositionMapper.insert(ibAccountPosition);
            } else {
                ibAccountPosition = listIbAccountPosition.get(0);

                ibAccountPosition.setExchange(contract.exchange());
                ibAccountPosition.setPosition(BigDecimal.valueOf(position));
                ibAccountPosition.setAverageCost(BigDecimal.valueOf(averageCost));
                ibAccountPosition.setRemark("IB消息推送更新");
                ibAccountPosition.setUpdateDate(System.currentTimeMillis());
                log.debug("ibAccountPosition更新: {}", ibAccountPosition);
                ibAccountPositionMapper.updateByPrimaryKeySelective(ibAccountPosition);
            }
        }
    }

    /**
     * @param reqId
     * @param accountCode
     * @param modelCode
     * @param contract
     * @param position
     * @param averageCost
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessPositionMulti(int reqId, String accountCode, String modelCode, Contract contract, double position, double averageCost) {
        log.debug("positionMulti Position Multi. Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Symbol: " + contract.symbol() + ", SecType: "
                + contract.secType() + ", Currency: " + contract.currency() + ", Position: " + position + ", Avg cost: " + averageCost + "\n");
        log.debug("positionMulti Position Multi. contract: " + contract.toString() + "\n");
        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily is not exist or AccountID is null");
        } else {
            IbAccountPosition ibAccountPosition = new IbAccountPosition();
            ibAccountPosition.setAccountCode(accountCode);
            ibAccountPosition.setSymbol(contract.symbol());
            ibAccountPosition.setSecType(contract.getSecType());
            ibAccountPosition.setCurrency(contract.currency());
            List<IbAccountPosition> listIbAccountPosition = ibAccountPositionMapper.findList(ibAccountPosition);
            if (null != listIbAccountPosition && listIbAccountPosition.size() <= 0) {
                // 插入数据
                ibAccountPosition.setId(SerialnoUtils.buildPrimaryKey());
                ibAccountPosition.setAccountId(listIbAccountFamily.get(0).getAccountId());
                ibAccountPosition.setExchange(contract.exchange());
                ibAccountPosition.setPosition(BigDecimal.valueOf(position));
                ibAccountPosition.setAverageCost(BigDecimal.valueOf(averageCost));
                ibAccountPosition.setRemark("IB消息推送入库");
                ibAccountPosition.setCreateDate(System.currentTimeMillis());
                log.debug("ibAccountPosition插入: {}", ibAccountPosition);
                ibAccountPositionMapper.insert(ibAccountPosition);
            } else {
                ibAccountPosition = listIbAccountPosition.get(0);

                ibAccountPosition.setExchange(contract.exchange());
                ibAccountPosition.setPosition(BigDecimal.valueOf(position));
                ibAccountPosition.setAverageCost(BigDecimal.valueOf(averageCost));
                ibAccountPosition.setRemark("IB消息推送更新");
                ibAccountPosition.setUpdateDate(System.currentTimeMillis());
                log.debug("ibAccountPosition更新: {}", ibAccountPosition);
                ibAccountPositionMapper.updateByPrimaryKeySelective(ibAccountPosition);
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountPosition entity) throws BusinessException
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
            return ibAccountPositionMapper.insert(entity);
        }
        else
        {
            return ibAccountPositionMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
