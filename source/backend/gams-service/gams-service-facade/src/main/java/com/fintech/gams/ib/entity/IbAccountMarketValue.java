/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IB账户持仓市值表 实体对象
 * <p>File：IbAccountMarketValue.java</p>
 * <p>Title: IbAccountMarketValue</p>
 * <p>Description:IbAccountMarketValue</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IB账户持仓市值表")
public class IbAccountMarketValue extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private java.lang.Long accountId;

	/**IB对应AccountCode*/
	@NotNull(message = "IB对应AccountCode不可为空")
	@ApiModelProperty(value = "IB对应AccountCode", required = true)
	private java.lang.String accountCode;

	/**币种*/
	@NotNull(message = "币种不可为空")
	@ApiModelProperty(value = "币种", required = true)
	private java.lang.String currency;

	/**总计现金*/
	@ApiModelProperty(value = "总计现金")
	private java.math.BigDecimal totalCashBalance;

	/**已结算现金*/
	@ApiModelProperty(value = "已结算现金")
	private java.math.BigDecimal cashBalance;

	/**应计现金*/
	@ApiModelProperty(value = "应计现金")
	private java.math.BigDecimal accruedCash;

	/**股票*/
	@ApiModelProperty(value = "股票")
	private java.math.BigDecimal stockMarketValue;

	/**期权*/
	@ApiModelProperty(value = "期权")
	private java.math.BigDecimal optionMarketValue;

	/**期货期权*/
	@ApiModelProperty(value = "期货期权")
	private java.math.BigDecimal futureOptionValue;

	/**期货*/
	@ApiModelProperty(value = "期货")
	private java.math.BigDecimal futuresPnl;

	/**净清算折算货币*/
	@ApiModelProperty(value = "净清算折算货币")
	private java.math.BigDecimal netLiquidationByCurrency;

	/**基金*/
	@ApiModelProperty(value = "基金")
	private java.math.BigDecimal fundValue;

	/**应计股息*/
	@ApiModelProperty(value = "应计股息")
	private java.math.BigDecimal netDividend;

	/**共同基金*/
	@ApiModelProperty(value = "共同基金")
	private java.math.BigDecimal mutualFundValue;

	/**货币市场基金*/
	@ApiModelProperty(value = "货币市场基金")
	private java.math.BigDecimal moneyMarketFundValue;

	/**公司债*/
	@ApiModelProperty(value = "公司债")
	private java.math.BigDecimal corporateBondValue;

	/**长期国债*/
	@ApiModelProperty(value = "长期国债")
	private java.math.BigDecimal tBondValue;

	/**短期国债*/
	@ApiModelProperty(value = "短期国债")
	private java.math.BigDecimal tBillValue;

	/**权证*/
	@ApiModelProperty(value = "权证")
	private java.math.BigDecimal warrantValue;

	/**外汇现金*/
	@ApiModelProperty(value = "外汇现金")
	private java.math.BigDecimal fxCashBalance;

	/**账户或分组*/
	@ApiModelProperty(value = "账户或分组")
	private java.lang.String accountOrGroup;

	/**实际币种*/
	@ApiModelProperty(value = "实际币种")
	private java.lang.String realCurrency;

	/**发行方期权*/
	@ApiModelProperty(value = "发行方期权")
	private java.math.BigDecimal issuerOptionValue;

	/**未实现盈亏*/
	@ApiModelProperty(value = "未实现盈亏")
	private java.math.BigDecimal unrealizedPnl;

	/**已实现盈亏*/
	@ApiModelProperty(value = "已实现盈亏")
	private java.math.BigDecimal realizedPnl;

	/**汇率*/
	@ApiModelProperty(value = "汇率")
	private java.math.BigDecimal exchangeRate;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createDate;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private java.lang.Long updateDate;

	/*******************查询参数********************/
	private java.lang.String  baseCurrency;

	/***************关联查询数据***************/
	private java.lang.String accountName;
}

