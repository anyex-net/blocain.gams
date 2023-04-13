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
 * IB账户交易流水表 实体对象
 * <p>File：IbAccountTrade.java</p>
 * <p>Title: IbAccountTrade</p>
 * <p>Description:IbAccountTrade</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IB账户交易流水表")
public class IbAccountTrade extends GenericEntity {
	
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

	/**交易所*/
	@ApiModelProperty(value = "交易所")
	private java.lang.String exchange;

	/**成交执行ID*/
	@ApiModelProperty(value = "成交执行ID")
	private java.lang.String execId;

	/**订单ID*/
	@ApiModelProperty(value = "订单ID")
	private java.lang.String orderId;

	/**方向*/
	@ApiModelProperty(value = "方向")
	private java.lang.String side;

	/**价格*/
	@ApiModelProperty(value = "价格")
	private java.math.BigDecimal price;

	/**数量*/
	@ApiModelProperty(value = "数量")
	private java.math.BigDecimal shares;

	/**成交均价*/
	@ApiModelProperty(value = "成交均价")
	private java.math.BigDecimal avgPrice;

	/**执行时间*/
	@ApiModelProperty(value = "执行时间")
	private java.lang.String execTime;

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
	private java.lang.Long execTimeStart;

	private java.lang.Long execTimeEnd;

	/***************关联查询数据***************/
	private java.lang.String accountName;
}

