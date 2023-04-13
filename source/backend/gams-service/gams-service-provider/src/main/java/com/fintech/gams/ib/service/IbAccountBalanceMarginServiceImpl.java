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
import com.fintech.gams.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.ib.entity.IbAccountBalanceMargin;
import com.fintech.gams.ib.mapper.IbAccountBalanceMarginMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

/**
 * IB账户资金保证金表 服务实现类
 * <p>File：IbAccountBalanceMarginServiceImpl.java </p>
 * <p>Title: IbAccountBalanceMarginServiceImpl </p>
 * <p>Description:IbAccountBalanceMarginServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountBalanceMarginServiceImpl extends GenericServiceImpl<IbAccountBalanceMargin> implements IbAccountBalanceMarginService
{
    protected IbAccountBalanceMarginMapper ibAccountBalanceMarginMapper;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    public IbAccountBalanceMarginServiceImpl(IbAccountBalanceMarginMapper ibAccountBalanceMarginMapper) {
        super(ibAccountBalanceMarginMapper);
        this.ibAccountBalanceMarginMapper = ibAccountBalanceMarginMapper;
    }

    /**
     * @param key
     * @param value
     * @param currency
     * @param accountCode
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcess(String key, String value, String currency, String accountCode) {
        //log.debug("updateAccountValue UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountCode: " + accountCode);

        //获取本位币
        IbAccountFamily ibAccountFamily = new IbAccountFamily();
        ibAccountFamily.setIbAccountCode(accountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamily);
        if ((null != listIbAccountFamily && listIbAccountFamily.size() <= 0) || listIbAccountFamily.get(0).getBaseCurrency() == null) {
            log.error("IbAccountFamily is not exist or baseCurrency is null");
        } else {
            IbAccountBalanceMargin ibAccountBalanceMargin = new IbAccountBalanceMargin();
            ibAccountBalanceMargin.setAccountCode(accountCode);
            List<IbAccountBalanceMargin> listIbAccountMarketValue = ibAccountBalanceMarginMapper.findList(ibAccountBalanceMargin);

            // log.info("listIbAccountMarketValue size:{}", listIbAccountMarketValue.size());
            boolean filedMatchFlag = false;
            if (null != listIbAccountMarketValue && listIbAccountMarketValue.size() <= 0) {
                try {
                    // Java反射赋值
                    Field[] fields = ibAccountBalanceMargin.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        if (key.toLowerCase().equals(field.getName().toLowerCase())) {
                            if (field.getType().getName().equals("java.lang.String")) {
                                field.set(ibAccountBalanceMargin, value);
                                filedMatchFlag = true;
                            } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                field.set(ibAccountBalanceMargin, BigDecimal.valueOf(Double.valueOf(value)));
                                filedMatchFlag = true;
                            }
                            // 其他类型不用赋值
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则插入
                    if (filedMatchFlag) {
                        // 插入数据
                        ibAccountBalanceMargin.setId(SerialnoUtils.buildPrimaryKey());
                        ibAccountBalanceMargin.setAccountId(listIbAccountFamily.get(0).getAccountId());
                        ibAccountBalanceMargin.setCurrency(listIbAccountFamily.get(0).getBaseCurrency());
                        ibAccountBalanceMargin.setRemark("IB消息推送入库");
                        ibAccountBalanceMargin.setCreateDate(System.currentTimeMillis());
                        log.debug("ibAccountBalanceMargin插入: {}", ibAccountBalanceMargin);
                        ibAccountBalanceMarginMapper.insert(ibAccountBalanceMargin);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcess error:{}", ex.getLocalizedMessage());
                }
            } else {
                try {
                    ibAccountBalanceMargin = listIbAccountMarketValue.get(0);

                    // Java反射赋值
                    Field[] fields = ibAccountBalanceMargin.getClass().getDeclaredFields();

                    for (Field field : fields) {
                        field.setAccessible(true); //设置访问权限
                        // -S -C特殊处理
                        if (key.toLowerCase().equals(field.getName().toLowerCase()) ||
                                key.substring(0, key.length() - 2).concat(key.substring(key.length() - 1, key.length())).toLowerCase().equals(field.getName().toLowerCase()) ||
                                StringUtils.substring(key, 5, key.length() - 2).concat(key.substring(key.length() - 1, key.length())).toLowerCase().equals(field.getName().toLowerCase())) {
                            if (key.equals("AccruedCash")) // AccruedCash字段特殊处理 两张表都有这个字段
                            {
                                // log.info("ibAccountBalanceMargin:{}", ibAccountBalanceMargin.toString());
                                // 币种与本位币相等或为null
                                if (currency.equals(ibAccountBalanceMargin.getCurrency()) || currency == null) {
                                    field.set(ibAccountBalanceMargin, BigDecimal.valueOf(Double.valueOf(value)));
                                    filedMatchFlag = true;
                                }
                            } else if (key.equals("Currency")) // AccruedCash字段特殊处理 两张表都有这个字段
                            {
                                // 啥都不做
                            } else {
                                if (field.getType().getName().equals("java.lang.String")) {
                                    field.set(ibAccountBalanceMargin, value);
                                    filedMatchFlag = true;
                                } else if (field.getType().getName().equals("java.math.BigDecimal")) {
                                    field.set(ibAccountBalanceMargin, BigDecimal.valueOf(Double.valueOf(value)));
                                    filedMatchFlag = true;
                                }
                                // 其他类型不用赋值
                            }
                        }
                        field.setAccessible(false);
                    }

                    // 字段匹配上则更新
                    if (filedMatchFlag) {
                        if (StringUtils.isNotEmpty(currency)) {
                            ibAccountBalanceMargin.setCurrency(currency);
                        }
                        ibAccountBalanceMargin.setRemark("IB消息推送更新");
                        ibAccountBalanceMargin.setUpdateDate(System.currentTimeMillis());
                        log.debug("ibAccountBalanceMargin更新: {}", ibAccountBalanceMargin);
                        ibAccountBalanceMarginMapper.updateByPrimaryKeySelective(ibAccountBalanceMargin);
                    }
                } catch (Exception ex) {
                    log.error("ibMessageProcess error:{}", ex.getLocalizedMessage());
                }
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountBalanceMargin entity) throws BusinessException
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
            return ibAccountBalanceMarginMapper.insert(entity);
        }
        else
        {
            return ibAccountBalanceMarginMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
