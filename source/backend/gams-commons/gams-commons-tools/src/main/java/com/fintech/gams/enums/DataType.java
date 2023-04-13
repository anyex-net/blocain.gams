package com.fintech.gams.enums;

import lombok.Getter;

/**
 * 数据类型
 * <p>File: DataType.java </p>
 * <p>Title: DataType </p>
 * <p>Description: DataType </p>
 * <p>Copyright: Copyright (c) 2018-12-06</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Getter
public enum DataType
{
    // 平台交易类
    ORDER(0), // 委托下单、委托撤单
    MATCHCANCEL(1), // 撮合撤单
    MATCHDEAL(2), // 撮合成交
    MATCHDEALBUY(3), // 撮合成交买
    MATCHDEALSELL(4), // 撮合成交卖
    MATCHRESIDUE(5), // 撮合吐渣
    // 平台钱包资产类
    ASSET_ADJUSTADD(100), // 资产强增
    ASSET_ADJUSTSUB(101), // 资产强减
    ASSET_ADJUSTFROZENADD(102), // 冻结资产强增
    ASSET_ADJUSTFROZENSUB(103), // 冻结资产强减
    // ASSET_INCREASE(104), // 资产增加
    // ASSET_DECREASE(105), // 资产减少
    // ASSET_FROZEN(106), // 资产冻结
    // ASSET_UNFROZEN(107), // 资产解冻
    // ASSET_UNFROZENDECREASE(108), // 资产解冻并减少
    ASSET_DEPOSIT(104), // 钱包充值资产
    ASSET_WITHDRAWAPPLAY(105), // 钱包提现申请
    ASSET_WITHDRAWCANCEL(106), // 钱包提现取消
    ASSET_WITHDRAWRESUSE(107), // 钱包提现拒绝
    ASSET_WITHDRAWSECCESS(108), // 钱包提现通过
    // 平台钱包资产与平台杠杆现货资产互转类
    ASSET_TRANSFER_IN(150), // 转入
    ASSET_TRANSFER_OUT(151), // 转出
    // 平台杠杆现货资产负债类
    ASSET_DEBITBORROW(202), // 借款
    ASSET_DEBITPAYMENT(203), // 还款
    // 强平爆仓资金转移
    ASSET_CLOSEPOSION_IN(210), // 资产转入
    ASSET_CLOSEPOSION_OUT(211), // 资产转出
    DEBIT_CLOSEPOSION_IN(213), // 负债转入
    DEBIT_CLOSEPOSION_OUT(214) // 负债转出
    ;
    private int code;
    
    DataType(int code)
    {
        this.code = code;
    }
}
