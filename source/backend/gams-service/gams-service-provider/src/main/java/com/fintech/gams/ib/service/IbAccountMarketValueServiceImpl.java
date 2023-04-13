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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fintech.gams.ib.entity.IbAccountMarketValue;
import com.fintech.gams.ib.mapper.IbAccountMarketValueMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * IB账户持仓市值表 服务实现类
 * <p>File：IbAccountMarketValueServiceImpl.java </p>
 * <p>Title: IbAccountMarketValueServiceImpl </p>
 * <p>Description:IbAccountMarketValueServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountMarketValueServiceImpl extends GenericServiceImpl<IbAccountMarketValue> implements IbAccountMarketValueService
{
    protected IbAccountMarketValueMapper ibAccountMarketValueMapper;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    public IbAccountMarketValueServiceImpl(IbAccountMarketValueMapper ibAccountMarketValueMapper) {
        super(ibAccountMarketValueMapper);
        this.ibAccountMarketValueMapper = ibAccountMarketValueMapper;
    }

    public final static List<String> currencyList = new ArrayList<String>(Arrays.asList("BASE","USD","AUD",
            "GBP","CAD","CZK","DKK","EUR","HKD","HUF","MXN","NZD","NOK","PLN","RUB","SGD","SEK","CHF","CNH","ILS"));

    // 'BASE','USD','AUD','GBP','CAD','CZK','DKK','EUR','HKD','HUF','MXN','NZD','NOK','PLN','RUB','SGD','SEK','CHF','CNH','ILS'

    /**
     * @param reqId
     * @param accountCode
     * @param modelCode
     * @param key
     * @param value
     * @param currency
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessAccountUpdateMulti(int reqId, String accountCode, String modelCode, String key, String value, String currency) {
        log.debug("ibMessageProcessAccountUpdateMulti Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Key: "
                + key + ", Value: " + value + ", Currency: " + currency);

        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        boolean filedMatchFlag = false;
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily is not exist or AccountID is null");
        } else {
            IbAccountMarketValue ibAccountMarketValue = new IbAccountMarketValue();
            ibAccountMarketValue.setAccountCode(accountCode);
            ibAccountMarketValue.setCurrency(currency);
            List<IbAccountMarketValue> listIbAccountMarketValue = ibAccountMarketValueMapper.findList(ibAccountMarketValue);
            if (null != listIbAccountMarketValue && listIbAccountMarketValue.size() <= 0) {
                try {
                    // 插入数据
                    ibAccountMarketValue.setId(SerialnoUtils.buildPrimaryKey());
                    ibAccountMarketValue.setAccountId(listIbAccountFamily.get(0).getAccountId());

                    // Java反射赋值
                    Field[] fields = ibAccountMarketValue.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (!isCurrency(currency)) // currency 不是具体的币种直接忽略 因为实际测试数据发现有错乱
                            {
                                log.error("ibMessageProcessAccountUpdateMulti Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Key: "
                                        + key + ", Value: " + value + ", Currency: " + currency);
                                filedMatchFlag = false;
                                // 啥都不做
                            } else if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountMarketValue, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountMarketValue, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            }
                            // 其他类型不用赋值
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则插入
                    if (filedMatchFlag) {
                        ibAccountMarketValue.setRemark("IB消息推送入库");
                        ibAccountMarketValue.setCreateDate(System.currentTimeMillis());
                        log.debug("ibAccountMarketValue插入: {}", ibAccountMarketValue);
                        ibAccountMarketValueMapper.insert(ibAccountMarketValue);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessAccountUpdateMulti error:{}", ex.getLocalizedMessage());
                }
            } else {
                try {
                    ibAccountMarketValue = listIbAccountMarketValue.get(0);

                    // Java反射赋值
                    Field[] fields = ibAccountMarketValue.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (!isCurrency(currency)) // currency 不是具体的币种直接忽略 因为实际测试数据发现有错乱
                            {
                                log.error("ibMessageProcessAccountUpdateMulti Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Key: "
                                        + key + ", Value: " + value + ", Currency: " + currency);
                                filedMatchFlag = false;
                                // 啥都不做
                            } else if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountMarketValue, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountMarketValue, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            }
                            // 其他类型不用赋值
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则更新
                    if (filedMatchFlag) {
                        ibAccountMarketValue.setRemark("IB消息推送更新");
                        ibAccountMarketValue.setUpdateDate(System.currentTimeMillis());
                        log.debug("ibAccountMarketValue更新: {}", ibAccountMarketValue);
                        ibAccountMarketValueMapper.updateByPrimaryKeySelective(ibAccountMarketValue);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessAccountUpdateMulti error:{}", ex.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessUpdateAccountValue(String key, String value, String currency, String accountCode) {
        log.debug("ibMessageProcessUpdateAccountValue AccountCode: " + accountCode + ", value: " + value + ", Key: "
                + key + ", Value: " + value + ", Currency: " + currency);
        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        boolean filedMatchFlag = false;
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily is not exist or AccountID is null");
        } else {
            IbAccountMarketValue ibAccountMarketValue = new IbAccountMarketValue();
            ibAccountMarketValue.setAccountCode(accountCode);
            ibAccountMarketValue.setCurrency(currency);
            List<IbAccountMarketValue> listIbAccountMarketValue = ibAccountMarketValueMapper.findList(ibAccountMarketValue);
            if (null != listIbAccountMarketValue && listIbAccountMarketValue.size() <= 0) {
                try {
                    // 插入数据
                    ibAccountMarketValue.setId(SerialnoUtils.buildPrimaryKey());
                    ibAccountMarketValue.setAccountId(listIbAccountFamily.get(0).getAccountId());

                    // Java反射赋值
                    Field[] fields = ibAccountMarketValue.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (key.equals("Currency") && !isCurrency(value)) // 字段为Currency 但是值不是具体的币种直接忽略 因为实际测试数据发现有错乱
                            {
                                log.error("ibMessageProcessUpdateAccountValue AccountCode: " + accountCode + ", value: " + value + ", Key: "
                                        + key + ", Value: " + value + ", Currency: " + currency);
                                filedMatchFlag = false;
                                // 啥都不做
                            } else
                            if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountMarketValue, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountMarketValue, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            }
                            // 其他类型不用赋值
                        }
                        field.setAccessible(false);
                    }

                    if (currency == null) {
                        filedMatchFlag = false;
                    }

                    // 字段匹配上则插入
                    if (filedMatchFlag) {
                        ibAccountMarketValue.setRemark("IB消息推送入库");
                        ibAccountMarketValue.setCreateDate(System.currentTimeMillis());
                        log.debug("ibAccountMarketValue插入: {}", ibAccountMarketValue);
                        ibAccountMarketValueMapper.insert(ibAccountMarketValue);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessUpdateAccountValue error:{}", ex.getLocalizedMessage());
                }
            } else {
                try {
                    ibAccountMarketValue = listIbAccountMarketValue.get(0);

                    // Java反射赋值
                    Field[] fields = ibAccountMarketValue.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (key.equals("AccruedCash")) // AccruedCash字段特殊处理 两张表都有这个字段
                            {
                                field.set(ibAccountMarketValue, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            } else if (key.equals("Currency")) // AccruedCash字段特殊处理 两张表都有这个字段
                            {
                                filedMatchFlag = false;
                                // 啥都不做
                            } else {
                                if (field.getType().getName().equals("java.lang.String")) {
                                    field.set(ibAccountMarketValue, value);
                                    filedMatchFlag = true;
                                } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                    field.set(ibAccountMarketValue, BigDecimal.valueOf(Double.valueOf(value)));
                                    filedMatchFlag = true;
                                }
                                // 其他类型不用赋值
                            }
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则更新
                    if (filedMatchFlag) {
                        ibAccountMarketValue.setRemark("IB消息推送更新");
                        ibAccountMarketValue.setUpdateDate(System.currentTimeMillis());
                        log.debug("ibAccountMarketValue更新: {}", ibAccountMarketValue);
                        ibAccountMarketValueMapper.updateByPrimaryKeySelective(ibAccountMarketValue);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessUpdateAccountValue error:{}", ex.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    public List<IbAccountMarketValue> findExchangeRateList(Long accountId) {
        List<IbAccountMarketValue> listIbAccountMarketValue = ibAccountMarketValueMapper.findExchangeRateList(accountId);
        return listIbAccountMarketValue;
    }

    private boolean isCurrency(String currency){
        // 检查currency在数据中是否存在
        return currencyList.stream().anyMatch(s -> Objects.equals(s, currency));
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountMarketValue entity) throws BusinessException
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
            return ibAccountMarketValueMapper.insert(entity);
        }
        else
        {
            return ibAccountMarketValueMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
