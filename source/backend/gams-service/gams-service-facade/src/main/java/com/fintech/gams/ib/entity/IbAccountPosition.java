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
 * IB账户持仓表 实体对象
 * <p>File：IbAccountPosition.java</p>
 * <p>Title: IbAccountPosition</p>
 * <p>Description:IbAccountPosition</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IB账户持仓表")
public class IbAccountPosition extends GenericEntity {
	
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

	/**金融产品代码*/
	@NotNull(message = "金融产品代码不可为空")
	@ApiModelProperty(value = "金融产品代码", required = true)
	private java.lang.String symbol;

	/**金融产品类型*/
	@NotNull(message = "金融产品类型不可为空")
	@ApiModelProperty(value = "金融产品类型", required = true)
	private java.lang.String secType;

	/**币种*/
	@NotNull(message = "币种不可为空")
	@ApiModelProperty(value = "币种", required = true)
	private java.lang.String currency;

	/**金融产品描述*/
	@ApiModelProperty(value = "金融产品描述")
	private java.lang.String financialInstrument;

	/**公司名称*/
	@ApiModelProperty(value = "公司名称")
	private java.lang.String companyName;

	/**交易所*/
	@ApiModelProperty(value = "交易所")
	private java.lang.String exchange;

	/**持仓头寸*/
	@ApiModelProperty(value = "持仓头寸")
	private java.math.BigDecimal position;

	/**未结算持仓头寸*/
	@ApiModelProperty(value = "未结算持仓头寸")
	private java.math.BigDecimal unsettledPosition;

	/**可借数量*/
	@ApiModelProperty(value = "可借数量")
	private java.math.BigDecimal loanableQty;

	/**PrivateLocate*/
	@ApiModelProperty(value = "PrivateLocate")
	private java.math.BigDecimal privateLocate;

	/**Delta美元*/
	@ApiModelProperty(value = "Delta美元")
	private java.math.BigDecimal deltaDollars;

	/**市价*/
	@ApiModelProperty(value = "市价")
	private java.math.BigDecimal marketPrice;

	/**市场价值*/
	@ApiModelProperty(value = "市场价值")
	private java.math.BigDecimal marketValue;

	/**平均价格*/
	@ApiModelProperty(value = "平均价格")
	private java.math.BigDecimal averageCost;

	/**未实现盈亏*/
	@ApiModelProperty(value = "未实现盈亏")
	private java.math.BigDecimal unrealizedPnl;

	/**已实现盈亏*/
	@ApiModelProperty(value = "已实现盈亏")
	private java.math.BigDecimal realizedPnl;

	/**最后清算*/
	@ApiModelProperty(value = "最后清算")
	private java.lang.String liquidateLast;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createDate;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private java.lang.Long updateDate;

	/***************关联查询数据***************/
	private java.lang.String accountName;
}

