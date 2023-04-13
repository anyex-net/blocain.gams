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
 * IB账户当日盈亏 实体对象
 * <p>File：IbAccountDailyPnl.java</p>
 * <p>Title: IbAccountDailyPnl</p>
 * <p>Description:IbAccountDailyPnl</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IB账户当日盈亏")
public class IbAccountDailyPnl extends GenericEntity {
	
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

	/**当日盈亏(期末总资产-期初总资产-期间流入+期间流出)*/
	@ApiModelProperty(value = "当日盈亏(期末总资产-期初总资产-期间流入+期间流出)")
	private java.math.BigDecimal dailyPnl;

	/**未实现盈亏*/
	@ApiModelProperty(value = "未实现盈亏")
	private java.math.BigDecimal unrealizedPnl;

	/**已实现盈亏*/
	@ApiModelProperty(value = "已实现盈亏")
	private java.math.BigDecimal realizedPnl;

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

