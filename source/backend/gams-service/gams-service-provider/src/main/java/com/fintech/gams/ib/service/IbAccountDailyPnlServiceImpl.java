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
import com.ib.client.EWrapperMsgGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.ib.entity.IbAccountDailyPnl;
import com.fintech.gams.ib.mapper.IbAccountDailyPnlMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * IB账户当日盈亏 服务实现类
 * <p>File：IbAccountDailyPnlServiceImpl.java </p>
 * <p>Title: IbAccountDailyPnlServiceImpl </p>
 * <p>Description:IbAccountDailyPnlServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbAccountDailyPnlServiceImpl extends GenericServiceImpl<IbAccountDailyPnl> implements IbAccountDailyPnlService
{
    protected IbAccountDailyPnlMapper ibAccountDailyPnlMapper;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    public IbAccountDailyPnlServiceImpl(IbAccountDailyPnlMapper ibAccountDailyPnlMapper) {
        super(ibAccountDailyPnlMapper);
        this.ibAccountDailyPnlMapper = ibAccountDailyPnlMapper;
    }

    /**
     * @param reqId
     * @param dailyPnL
     * @param unrealizedPnL
     * @param realizedPnL
     */
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void ibMessageProcessPnL(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL) {
        log.debug("pnl: " + EWrapperMsgGenerator.pnl(reqId, dailyPnL, unrealizedPnL, realizedPnL));

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
                IbAccountDailyPnl ibAccountDailyPnl = new IbAccountDailyPnl();
                ibAccountDailyPnl.setAccountCode(accountCode);
                List<IbAccountDailyPnl> listIbAccountDailyPnl = ibAccountDailyPnlMapper.findList(ibAccountDailyPnl);
                if (null != listIbAccountDailyPnl && listIbAccountDailyPnl.size() <= 0) {
                    // 插入数据
                    ibAccountDailyPnl.setId(SerialnoUtils.buildPrimaryKey());
                    ibAccountDailyPnl.setAccountId(listIbAccountFamily.get(0).getAccountId());
                    ibAccountDailyPnl.setDailyPnl(BigDecimal.valueOf(dailyPnL));
                    ibAccountDailyPnl.setUnrealizedPnl(BigDecimal.valueOf(unrealizedPnL));
                    ibAccountDailyPnl.setRealizedPnl(BigDecimal.valueOf(realizedPnL));
                    ibAccountDailyPnl.setRemark("IB消息推送入库");
                    ibAccountDailyPnl.setCreateDate(System.currentTimeMillis());
                    log.debug("ibAccountDailyPnl插入: {}", ibAccountDailyPnl);
                    ibAccountDailyPnlMapper.insert(ibAccountDailyPnl);
                } else {
                    ibAccountDailyPnl = listIbAccountDailyPnl.get(0);

                    ibAccountDailyPnl.setDailyPnl(BigDecimal.valueOf(dailyPnL));
                    ibAccountDailyPnl.setUnrealizedPnl(BigDecimal.valueOf(unrealizedPnL));
                    ibAccountDailyPnl.setRealizedPnl(BigDecimal.valueOf(realizedPnL));
                    ibAccountDailyPnl.setRemark("IB消息推送更新");
                    ibAccountDailyPnl.setUpdateDate(System.currentTimeMillis());
                    log.debug("ibAccountDailyPnl更新: {}", ibAccountDailyPnl);
                    ibAccountDailyPnlMapper.updateByPrimaryKeySelective(ibAccountDailyPnl);
                }
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(IbAccountDailyPnl entity) throws BusinessException
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
            return ibAccountDailyPnlMapper.insert(entity);
        }
        else
        {
            return ibAccountDailyPnlMapper.updateByPrimaryKeySelective(entity);
        }
    }
}
