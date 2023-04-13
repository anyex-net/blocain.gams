/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 提现出金记录表 实体对象
 * <p>File：AssetWithdraw.java</p>
 * <p>Title: AssetWithdraw</p>
 * <p>Description:AssetWithdraw</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "提现出金记录表")
public class AssetWithdraw extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 accountId;
    
    /**币种(数据字典)*/
    @NotNull(message = "币种(数据字典)不可为空")
    @ApiModelProperty(value = "币种(数据字典)", required = true)
    private String               currency;
    
    /**收款银行(钱包ID)*/
    @NotNull(message = "收款银行(钱包ID)不可为空")
    @ApiModelProperty(value = "收款银行(钱包ID)", required = true)
    private String               beneBank;
    
    /**收款银行账号(钱包地址)*/
    @NotNull(message = "收款银行账号(钱包地址)不可为空")
    @ApiModelProperty(value = "收款银行账号(钱包地址)", required = true)
    @Size(min = 6, max = 64)
    private String               accountNumber;
    
    /**提现出金数量*/
    @NotNull(message = "提现出金数量不可为空")
    @ApiModelProperty(value = "提现出金数量", required = true)
    private java.math.BigDecimal amount;
    
    /**提现手续费*/
    @ApiModelProperty(value = "提现手续费")
    private java.math.BigDecimal fee;

    /**转账方式(支票、电汇，具体见数据字典)*/
    @NotNull(message = "转账方式(支票、电汇，具体见数据字典)不可为空")
    @ApiModelProperty(value = "转账方式(支票、电汇，具体见数据字典)", required = true)
    private String               transferType;

    /**SWIFT码*/
    @NotNull(message = "SWIFT码不可为空")
    @ApiModelProperty(value = "SWIFT码", required = true)
    private String               swiftCode;
    
    /**唯一交易ID*/
    @ApiModelProperty(value = "唯一交易ID")
    private String               transactionId;

    /**提现出金申请时间*/
    @NotNull(message = "申请时间不可为空")
    @ApiModelProperty(value = "提现出金申请时间", required = true)
    private Long                 withdrawDate;
    
    /**状态(unconfirmed未确认、confirmed已确认、canceled已取消)*/
    @NotNull(message = "状态(unconfirmed未确认、confirmed已确认、canceled已取消)不可为空")
    @ApiModelProperty(value = "状态(unconfirmed未确认、confirmed已确认、canceled已取消)", required = true)
    private String               status;

    /**提现入账确认时间*/
    @ApiModelProperty(value = "提现入账确认时间")
    private Long                 withdrawConfirmDate;
    
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String               remark;
    
    /**修改人*/
    @ApiModelProperty(value = "修改人")
    private Long                 updateBy;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long                 updateDate;

    /****************关联查询条件******************/
    private java.lang.String  accountName;
}