/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.mapper.AccountMapper;
import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import com.ib.client.FamilyCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.ib.mapper.IbAccountFamilyMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IB账户家族表 服务实现类
 * <p>File：IbAccountFamilyServiceImpl.java </p>
 * <p>Title: IbAccountFamilyServiceImpl </p>
 * <p>Description:IbAccountFamilyServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountFamilyServiceImpl extends GenericServiceImpl<IbAccountFamily> implements IbAccountFamilyService
{
    protected IbAccountFamilyMapper ibAccountFamilyMapper;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    public IbAccountFamilyServiceImpl(IbAccountFamilyMapper ibAccountFamilyMapper) {
        super(ibAccountFamilyMapper);
        this.ibAccountFamilyMapper = ibAccountFamilyMapper;
    }

    /**
     * @param familyCodes
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcess(FamilyCode[] familyCodes) {
        for (FamilyCode familyCode : familyCodes) {
            log.debug("familyCodes Family Code. AccountCode: " + familyCode.accountID() + ", FamilyCode: " + familyCode.familyCodeStr());
            IbAccountFamily ibAccountFamily = new IbAccountFamily();
            ibAccountFamily.setIbAccountCode(familyCode.accountID());
            ibAccountFamily.setIbFamilyCode(familyCode.familyCodeStr());
            List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyMapper.findList(ibAccountFamily);
            if (null != listIbAccountFamily && listIbAccountFamily.size() <= 0) {
                // 插入数据
                ibAccountFamily.setId(SerialnoUtils.buildPrimaryKey());
                ibAccountFamily.setRemark("IB消息推送入库");
                ibAccountFamily.setCreateDate(System.currentTimeMillis());
                log.debug("ibAccountFamily插入: {}", ibAccountFamily);
                ibAccountFamilyMapper.insert(ibAccountFamily);
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountFamily entity) throws BusinessException {
        this.beanValidator(entity);

        // 判断 accountId 是否合法
        Account account = accountMapper.selectByPrimaryKey(entity.getAccountId());
        if (null == account) {
            log.error("ibAccountFamily error: ACCOUNT表中不存在相关的IB代码信息，accountID不合法");
            throw new BusinessException("账户ID不合法，内部不存在相关信息");
        }

        if (null == entity.getId()) {
            // 判断插入数据是否重复
            IbAccountFamily ibAccountFamily2 = new IbAccountFamily();
            ibAccountFamily2.setAccountId(entity.getAccountId());
            ibAccountFamily2.setIbAccountCode(entity.getIbAccountCode());
            ibAccountFamily2.setIbFamilyCode(entity.getIbFamilyCode());
            List<IbAccountFamily> ibAccountFamilyList = ibAccountFamilyMapper.findByAccountIdORIbAccountCodeORIbFamilyCode(ibAccountFamily2);
            if (ibAccountFamilyList != null && ibAccountFamilyList.size() > 0) {
                log.error("ibAccountFamily插入 error: 传入数据在数据库中重复");
                throw new BusinessException("IB代码内部已存在，账户ID、IB账户代码、IB家族代码不可重复");
            }
            entity.setId(SerialnoUtils.buildPrimaryKey());
            return ibAccountFamilyMapper.insert(entity);
        } else {
            IbAccountFamily ibAccountFamily3 = ibAccountFamilyMapper.selectByPrimaryKey(entity.getId());
            if (null == ibAccountFamily3.getAccountId()) {
                // 判断更新数据是否重复
                IbAccountFamily ibAccountFamily2 = new IbAccountFamily();
                ibAccountFamily2.setIbFamilyCode(entity.getIbFamilyCode());
                List<IbAccountFamily> ibAccountFamilyList = ibAccountFamilyMapper.findByAccountIdORIbAccountCodeORIbFamilyCode(ibAccountFamily2);
                if (ibAccountFamilyList != null && ibAccountFamilyList.size() > 0 && ibAccountFamilyList.get(0).getAccountId() != null) {
                    log.error("ibAccountFamily插入 error: IB家族代码数据库已存在");
                    throw new BusinessException("账户ID、IB账户代码、IB家族代码已存在");
                }
                try {
                    return ibAccountFamilyMapper.updateByPrimaryKeySelective(entity);
                } catch (Exception e) {
                    // 捕获异常
                    log.error("ibAccountFamily插入 error: 违反唯一约束条件，数据库中已存在改数据");
                    throw new BusinessException("IB代码内部已存在，账户ID、IB账户代码不可重复");
                }
            } else {
                // 检测账户ID、IB账户代码、IB家族代码是否正确修改
                if ((null != ibAccountFamily3.getAccountId() && ibAccountFamily3.getAccountId().longValue() != entity.getAccountId().longValue())
                        || (null != ibAccountFamily3.getIbAccountCode() && !(StringUtils.equals(ibAccountFamily3.getIbAccountCode(), entity.getIbAccountCode())))
                        || (null != ibAccountFamily3.getIbFamilyCode() && !(StringUtils.equals(ibAccountFamily3.getIbFamilyCode(), entity.getIbFamilyCode())))
                        || (null != ibAccountFamily3.getBaseCurrency() && !(StringUtils.equals(ibAccountFamily3.getBaseCurrency(), entity.getBaseCurrency()))))
                {
                    log.error("ibAccountFamily更新 error: IB代码内部已存在，账户ID、IB账户代码、IB家族代码、本位币不可修改");
                    throw new BusinessException("IB代码内部已存在，账户ID、IB账户代码、IB家族代码、本位币不可修改");
                } else if ((null == ibAccountFamily3.getAccountId() && ibAccountFamily3.getAccountId().longValue() != entity.getAccountId().longValue())
                        || (null == ibAccountFamily3.getIbAccountCode() && !(StringUtils.equals(ibAccountFamily3.getIbAccountCode(), entity.getIbAccountCode())))
                        || (null == ibAccountFamily3.getIbFamilyCode() && !(StringUtils.equals(ibAccountFamily3.getIbFamilyCode(), entity.getIbFamilyCode())))
                        || (null == ibAccountFamily3.getBaseCurrency() && !(StringUtils.equals(ibAccountFamily3.getBaseCurrency(), entity.getBaseCurrency()))))
                {
                    // 判断更新数据是否重复
                    IbAccountFamily ibAccountFamily2 = new IbAccountFamily();
                    ibAccountFamily2.setIbFamilyCode(entity.getIbFamilyCode());
                    List<IbAccountFamily> ibAccountFamilyList = ibAccountFamilyMapper.findByAccountIdORIbAccountCodeORIbFamilyCode(ibAccountFamily2);
                    if (ibAccountFamilyList != null && ibAccountFamilyList.size() > 0) {
                        log.error("ibAccountFamily插入 error: IB家族代码已存在");
                        throw new BusinessException("IB家族代码已存在");
                    }
                    return ibAccountFamilyMapper.updateByPrimaryKeySelective(entity);

                }else{
                    return ibAccountFamilyMapper.updateByPrimaryKeySelective(entity);
                }
            }
        }
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int remove(Long id) throws BusinessException
    {
        IbAccountFamily Family = ibAccountFamilyMapper.selectByPrimaryKey(id);
        Account account = accountMapper.selectByPrimaryKey(Family.getAccountId());
        if (null != account) {
            log.error("ibAccountFamily删除 error: IB代码已与内部accountID绑定，不可移除");
            throw new BusinessException("IB代码已绑定，不可删除");
        }
        return ibAccountFamilyMapper.remove(id);
    }
}
