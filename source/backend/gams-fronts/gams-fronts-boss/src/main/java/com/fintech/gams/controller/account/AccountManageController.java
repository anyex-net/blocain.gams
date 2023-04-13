/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.asset.entity.AssetDeposit;
import com.fintech.gams.asset.entity.AssetWithdraw;
import com.fintech.gams.asset.service.AssetDepositService;
import com.fintech.gams.asset.service.AssetWithdrawService;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountBalanceMargin;
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.ib.entity.IbAccountMarketValueHis;
import com.fintech.gams.ib.entity.IbAccountTrade;
import com.fintech.gams.ib.service.IbAccountBalanceMarginService;
import com.fintech.gams.ib.service.IbAccountFamilyService;
import com.fintech.gams.ib.service.IbAccountMarketValueHisService;
import com.fintech.gams.ib.service.IbAccountTradeService;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 账户管理核心 控制器
 * <p>File：AccountManageController.java </p>
 * <p>Title: AccountManageController </p>
 * <p>Description:AccountManageController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "账户管理核心")
public class AccountManageController extends GenericController
{
    @Autowired(required = false)
    private AccountService accountService;

    @Autowired(required = false)
    private IbAccountFamilyService ibAccountFamilyService;

    @Autowired(required = false)
    private IbAccountBalanceMarginService ibAccountBalanceMarginService;

    @Autowired(required = false)
    private IbAccountMarketValueHisService ibAccountMarketValueHisService;

    @Autowired(required = false)
    private IbAccountTradeService ibAccountTradeService;

    @Autowired(required = false)
    private AssetDepositService assetDepositService;

    @Autowired(required = false)
    private AssetWithdrawService assetWithdrawService;

    @GetMapping(value = "/manage/ib/getIbAccountInfo")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "根据账户ID获取IB账户信息", httpMethod = "GET")
    public JsonMessage getIbAccountInfo(Long accountId) throws BusinessException
    {
        if(null == accountId) {
            throw new BusinessException("参数accountId传入为空!");
        }
        IbAccountFamily ibAccountFamilyFiter = new IbAccountFamily();
        ibAccountFamilyFiter.setAccountId(accountId);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamilyFiter);
        //
        IbAccountFamily result = new IbAccountFamily();
        if(null != listIbAccountFamily && listIbAccountFamily.size() > 0){
            result = listIbAccountFamily.get(0);
        }
        //
        return this.getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/manage/ib/updateIbAccountInfo")
    @RequiresPermissions("account:account:operator")
    @ApiOperation(value = "根据账户ID修改IB账户信息", httpMethod = "POST")
    public JsonMessage updateIbAccountInfo(Long accountId, String ibAccountCode, String baseCurrency) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        if(null == accountId || !StringUtils.isNotEmpty(ibAccountCode) || !StringUtils.isNotEmpty(baseCurrency)
                || ibAccountCode.equals("null") || baseCurrency.equals("null")) {
            throw new BusinessException("参数accountId或参数ibAccountCode或参数baseCurrency传入为空!");
        }
        //
        IbAccountFamily ibAccountFamilyFiter = new IbAccountFamily();
        ibAccountFamilyFiter.setIbAccountCode(ibAccountCode);
        List<IbAccountFamily> listIbAccountFamily = ibAccountFamilyService.findList(ibAccountFamilyFiter);
        if(null != listIbAccountFamily && listIbAccountFamily.size() > 0 && null != listIbAccountFamily.get(0).getAccountId()){
            throw new BusinessException("该IB账号已经被绑定,不能重复绑定,请检查!");
        } else if(null != listIbAccountFamily && listIbAccountFamily.size() > 0) {
            IbAccountFamily ibAccountFamily = listIbAccountFamily.get(0);
            ibAccountFamily.setAccountId(accountId);
            ibAccountFamily.setBaseCurrency(baseCurrency);
            ibAccountFamily.setUpdateDate(System.currentTimeMillis());
            ibAccountFamilyService.updateByPrimaryKeySelective(ibAccountFamily);
        } else {
            throw new BusinessException("不存在该IB账号,请检查!");
        }
        //
        return json;
    }

    @PostMapping(value = "/manage/account/frozenAccount")
    @RequiresPermissions("account:account:operator")
    @ApiOperation(value = "冻结账户", httpMethod = "POST")
    public JsonMessage frozenAccount(Long accountId) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        if(null == accountId) {
            throw new BusinessException("参数accountId传入为空!");
        }
        //
        Account accountDB = accountService.selectByPrimaryKey(accountId);
        if(null != accountDB && accountDB.verifySignature()){
            accountDB.setStatus(AccountConsts.ACCOUNT_STATUS_FROZEN);
            accountService.updateByPrimaryKeySelective(accountDB);
        } else {
            throw new BusinessException("该账号异常,不能进行账户冻结,请检查!");
        }
        //
        return json;
    }

    @PostMapping(value = "/manage/account/unfrozenAccount")
    @RequiresPermissions("account:account:operator")
    @ApiOperation(value = "解冻账户", httpMethod = "POST")
    public JsonMessage unfrozenAccount(Long accountId) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);

        if(null == accountId) {
            throw new BusinessException("参数accountId传入为空!");
        }
        //
        Account accountDB = accountService.selectByPrimaryKey(accountId);
        if(null != accountDB && accountDB.verifySignature()){
            accountDB.setStatus(AccountConsts.ACCOUNT_STATUS_NORMAL);
            accountService.updateByPrimaryKeySelective(accountDB);
        } else {
            throw new BusinessException("该账号异常,不能进行账户解冻,请检查!");
        }
        //
        return json;
    }

    @PostMapping(value = "/manage/ib/accountBalanceMargin/cashSummary")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "IB账户资金概览", httpMethod = "POST")
    public JsonMessage cashSummary(Long accountId) throws BusinessException
    {
        if(null == accountId) {
            throw new BusinessException("参数accountId传入为空!");
        }
        IbAccountBalanceMargin ibAccountBalanceMargin = new IbAccountBalanceMargin();
        ibAccountBalanceMargin.setAccountId(accountId);
        List<IbAccountBalanceMargin> listIbAccountBalanceMargin = ibAccountBalanceMarginService.findList(ibAccountBalanceMargin);
        IbAccountBalanceMargin cashSummary = null;
        if(null != listIbAccountBalanceMargin && listIbAccountBalanceMargin.size() > 0)
        {
            cashSummary = listIbAccountBalanceMargin.get(0);
        }
        return getJsonMessage(CommonEnums.SUCCESS, cashSummary);
    }

    @PostMapping(value = "/manage/ib/accountMarketValueHis/accountSummaryList")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "IB账户市值历史4账户概览", httpMethod = "POST")
    public JsonMessage accountMarketValueHis4AccountSummary(@ModelAttribute IbAccountMarketValueHis ibAccountMarketValueHis) throws BusinessException
    {
        if(null == ibAccountMarketValueHis.getAccountId()) {
            throw new BusinessException("参数accountId传入为空!");
        }
        ibAccountMarketValueHis.setCurrency("BASE"); // 本位币
        // 查询3个月内的历史数据
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -3); // 三个月前
        Date m3Before = calendar.getTime();
        String mon3Before = format.format(m3Before);
        ibAccountMarketValueHis.setCreateDateStart(Long.valueOf(mon3Before));

        List<IbAccountMarketValueHis> result = ibAccountMarketValueHisService.findList(ibAccountMarketValueHis);

        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/manage/ib/accountMarketValueHis/investSummaryList")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "IB账户市值历史4投资概览", httpMethod = "POST")
    public JsonMessage accountMarketValueHis4InvestSummary(@ModelAttribute IbAccountMarketValueHis ibAccountMarketValueHis) throws BusinessException
    {
        if(null == ibAccountMarketValueHis.getAccountId()) {
            throw new BusinessException("参数accountId传入为空!");
        }
        ibAccountMarketValueHis.setCurrency("BASE"); // 本位币

        List<IbAccountMarketValueHis> result = ibAccountMarketValueHisService.findList(ibAccountMarketValueHis);

        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/manage/ib/accountTrade/list")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "IB账户交易流水记录查询", httpMethod = "POST")
    public JsonMessage list(@ModelAttribute IbAccountTrade ibAccountTrade, @ModelAttribute Pagination pagin) throws BusinessException
    {
        if(null == ibAccountTrade.getAccountId()) {
            throw new BusinessException("参数accountId传入为空!");
        }
        PaginateResult<IbAccountTrade> result = ibAccountTradeService.search(pagin, ibAccountTrade);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/manage/asset/deposit/list")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "充值入金记录表", httpMethod = "POST")
    public JsonMessage depositList(@ModelAttribute AssetDeposit assetDeposit, Pagination pagin) throws BusinessException
    {
        if(null == assetDeposit.getAccountId()) {
            throw new BusinessException("参数accountId传入为空!");
        }
        PaginateResult<AssetDeposit> result = assetDepositService.search(pagin, assetDeposit);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/manage/asset/withdraw/list")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "提现出金记录查询", httpMethod = "POST")
    public JsonMessage withdrawList(@ModelAttribute AssetWithdraw assetWithdraw, @ModelAttribute Pagination pagin) throws BusinessException
    {
        if(null == assetWithdraw.getAccountId()) {
            throw new BusinessException("参数accountId传入为空!");
        }
        PaginateResult<AssetWithdraw> result = assetWithdrawService.search(pagin, assetWithdraw);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
