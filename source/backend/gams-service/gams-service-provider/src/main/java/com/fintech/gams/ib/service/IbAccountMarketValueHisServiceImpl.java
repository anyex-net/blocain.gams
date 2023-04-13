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
import com.fintech.gams.ib.entity.IbAccountMarketValueHis;
import com.fintech.gams.ib.mapper.IbAccountMarketValueHisMapper;
import com.fintech.gams.utils.SerialnoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * IB账户市值历史表 服务实现类
 * <p>File：IbAccountMarketValueHisServiceImpl.java </p>
 * <p>Title: IbAccountMarketValueHisServiceImpl </p>
 * <p>Description:IbAccountMarketValueHisServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountMarketValueHisServiceImpl extends GenericServiceImpl<IbAccountMarketValueHis> implements IbAccountMarketValueHisService
{
    protected IbAccountMarketValueHisMapper ibAccountMarketValueHisMapper;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    public IbAccountMarketValueHisServiceImpl(IbAccountMarketValueHisMapper ibAccountMarketValueHisMapper) {
        super(ibAccountMarketValueHisMapper);
        this.ibAccountMarketValueHisMapper = ibAccountMarketValueHisMapper;
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
    public void ibMessageProcessAccountUpdateMultiHis(int reqId, String accountCode, String modelCode, String key, String value, String currency) {
        log.debug("accountUpdateMulti Account Update Multi. Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Key: "
                + key + ", Value: " + value + ", Currency: " + currency);

        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily don't exist or AccountID is null");
        } else {
            IbAccountMarketValueHis ibAccountMarketValueHis = new IbAccountMarketValueHis();
            ibAccountMarketValueHis.setCurrency(currency);
            ibAccountMarketValueHis.setAccountCode(accountCode);
            //将时间转换成YYYYMMDD
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Long currentTime = Long.parseLong(simpleDateFormat.format(System.currentTimeMillis()));
            ibAccountMarketValueHis.setCreateDate(currentTime);
            List<IbAccountMarketValueHis> listIbAccountMarketValue = ibAccountMarketValueHisMapper.findList(ibAccountMarketValueHis);

            boolean filedMatchFlag = false;

            if (null != listIbAccountMarketValue && listIbAccountMarketValue.size() <= 0) {
                try {
                    // Java反射赋值
                    Field[] fields = ibAccountMarketValueHis.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (!isCurrency(currency)) // currency 不是具体的币种直接忽略 因为实际测试数据发现有错乱
                            {
                                log.error("accountUpdateMulti Account Update Multi. Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Key: "
                                        + key + ", Value: " + value + ", Currency: " + currency);
                                filedMatchFlag = false;
                                // 啥都不做
                            } else if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountMarketValueHis, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountMarketValueHis, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            }
                            // 其他类型不用赋值
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则插入
                    if (filedMatchFlag) {
                        ibAccountMarketValueHis.setRemark("IB消息推送入库");
                        ibAccountMarketValueHis.setId(SerialnoUtils.buildPrimaryKey());
                        ibAccountMarketValueHis.setAccountId(listIbAccountFamily.get(0).getAccountId()); // 待调整
                        // log.info("ibAccountMarketValueHis插入: {}", ibAccountMarketValueHis);
                        ibAccountMarketValueHisMapper.insert(ibAccountMarketValueHis);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessAccountUpdateMultiHis error:{}", ex.getLocalizedMessage());
                }
            } else {
                try {
                    ibAccountMarketValueHis = listIbAccountMarketValue.get(0);

                    // Java反射赋值
                    Field[] fields = ibAccountMarketValueHis.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (!isCurrency(currency)) // currency 不是具体的币种直接忽略 因为实际测试数据发现有错乱
                            {
                                log.error("accountUpdateMulti Account Update Multi. Request: " + reqId + ", AccountCode: " + accountCode + ", ModelCode: " + modelCode + ", Key: "
                                        + key + ", Value: " + value + ", Currency: " + currency);
                                filedMatchFlag = false;
                                // 啥都不做
                            } else if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountMarketValueHis, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountMarketValueHis, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            }
                            // 其他类型不用赋值
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则更新
                    if (filedMatchFlag) {
                        ibAccountMarketValueHis.setRemark("IB消息推送更新");
                        ibAccountMarketValueHis.setUpdateDate(System.currentTimeMillis());
                        // log.info("ibAccountMarketValueHis更新: {}", ibAccountMarketValueHis);
                        ibAccountMarketValueHisMapper.updateByPrimaryKeySelective(ibAccountMarketValueHis);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessAccountUpdateMultiHis error:{}", ex.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessUpdateAccountValueHis(String key, String value, String currency, String accountCode) {

        //获取AccountID
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        boolean filedMatchFlag = false;
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getAccountId() == null) {
            log.error("IbAccountFamily don't exist or AccountID is null");
        } else {
            IbAccountMarketValueHis ibAccountMarketValueHis = new IbAccountMarketValueHis();
            ibAccountMarketValueHis.setAccountCode(accountCode);
            ibAccountMarketValueHis.setCurrency(currency);
            //将时间转换成YYYYMMDD
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            Long currentTime = Long.parseLong(simpleDateFormat.format(System.currentTimeMillis()));
            ibAccountMarketValueHis.setCreateDate(currentTime);
            List<IbAccountMarketValueHis> listIbAccountMarketValue = ibAccountMarketValueHisMapper.findList(ibAccountMarketValueHis);
            if (null != listIbAccountMarketValue && listIbAccountMarketValue.size() <= 0) {
                try {
                    // Java反射赋值
                    Field[] fields = ibAccountMarketValueHis.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (key.equals("Currency") && !isCurrency(value)) // 字段为Currency 但是值不是具体的币种直接忽略 因为实际测试数据发现有错乱
                            {
                                log.error("ibMessageProcessUpdateAccountValueHis: AccountCode: " + accountCode + ", Key: "
                                        + key + ", Value: " + value + ", Currency: " + currency);
                                filedMatchFlag = false;
                                // 啥都不做
                            } else if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountMarketValueHis, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountMarketValueHis, BigDecimal.valueOf(Double.valueOf(value)));
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
                        ibAccountMarketValueHis.setRemark("IB消息推送入库");
                        ibAccountMarketValueHis.setId(SerialnoUtils.buildPrimaryKey());
                        ibAccountMarketValueHis.setAccountId(listIbAccountFamily.get(0).getAccountId());
                        log.debug("ibAccountMarketValueHis插入: {}", ibAccountMarketValueHis);
                        ibAccountMarketValueHisMapper.insert(ibAccountMarketValueHis);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessUpdateAccountValueHis error:{}", ex.getLocalizedMessage());
                }
            } else {
                try {
                    ibAccountMarketValueHis = listIbAccountMarketValue.get(0);

                    // Java反射赋值
                    Field[] fields = ibAccountMarketValueHis.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (key.equals("AccruedCash")) // AccruedCash字段特殊处理 两张表都有这个字段
                            {
                                field.set(ibAccountMarketValueHis, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            } else if (key.equals("Currency")) // AccruedCash字段特殊处理 两张表都有这个字段
                            {
                                // 啥都不做
                            } else {
                                if (field.getType().getName().equals("java.lang.String")) {
                                    field.set(ibAccountMarketValueHis, value);
                                    filedMatchFlag = true;
                                } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                    field.set(ibAccountMarketValueHis, BigDecimal.valueOf(Double.valueOf(value)));
                                    filedMatchFlag = true;
                                }
                                // 其他类型不用赋值
                            }
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则更新
                    if (filedMatchFlag) {
                        ibAccountMarketValueHis.setRemark("IB消息推送更新");
                        ibAccountMarketValueHis.setUpdateDate(System.currentTimeMillis());
                        log.debug("ibAccountMarketValueHis更新: {}", ibAccountMarketValueHis);
                        ibAccountMarketValueHisMapper.updateByPrimaryKeySelective(ibAccountMarketValueHis);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcessUpdateAccountValueHis error:{}", ex.getLocalizedMessage());
                }
            }
        }
    }

    private boolean isCurrency(String currency){
        // 检查currency在数据中是否存在
        return currencyList.stream().anyMatch(s -> Objects.equals(s, currency));
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountMarketValueHis entity) throws BusinessException
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
            return ibAccountMarketValueHisMapper.insert(entity);
        }
        else
        {
            return ibAccountMarketValueHisMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
