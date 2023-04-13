/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资产流水表 实体对象
 * <p>File：AssetCurrent.java</p>
 * <p>Title: AssetCurrent</p>
 * <p>Description:AssetCurrent</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资产流水表")
public class AssetCurrent extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**流水时间戳*/
	@NotNull(message = "流水时间戳不可为空")
	@ApiModelProperty(value = "流水时间戳", required = true)
	private java.util.Date currentTimestamp;

	/**业务标志*/
	@NotNull(message = "业务标志不可为空")
	@ApiModelProperty(value = "业务标志", required = true)
	private String businessFlag;

	/**证券信息ID*/
	@NotNull(message = "证券信息ID不可为空")
	@ApiModelProperty(value = "证券信息ID", required = true)
	private Long stockinfoId;

	/**发生方向(增加increase、减少decrease、冻结frozen、解冻unfrozen、解冻并减少frozenDecrease)*/
	@NotNull(message = "发生方向(增加increase、减少decrease、冻结frozen、解冻unfrozen、解冻并减少frozenDecrease)不可为空")
	@ApiModelProperty(value = "发生方向(增加increase、减少decrease、冻结frozen、解冻unfrozen、解冻并减少frozenDecrease)", required = true)
	private String occurDirect;

	/**原始数量*/
	@NotNull(message = "原始数量不可为空")
	@ApiModelProperty(value = "原始数量", required = true)
	private java.math.BigDecimal originalAmount;

	/**发生数量*/
	@NotNull(message = "发生数量不可为空")
	@ApiModelProperty(value = "发生数量", required = true)
	private java.math.BigDecimal occurAmount;

	/**最新数量*/
	@NotNull(message = "最新数量不可为空")
	@ApiModelProperty(value = "最新数量", required = true)
	private java.math.BigDecimal lastAmount;

	/**冻结原始数量*/
	@NotNull(message = "冻结原始数量不可为空")
	@ApiModelProperty(value = "冻结原始数量", required = true)
	private java.math.BigDecimal forzenOriginalAmount;

	/**冻结发生数量*/
	@NotNull(message = "冻结发生数量不可为空")
	@ApiModelProperty(value = "冻结发生数量", required = true)
	private java.math.BigDecimal occurForzenAmount;

	/**冻结最新数量*/
	@NotNull(message = "冻结最新数量不可为空")
	@ApiModelProperty(value = "冻结最新数量", required = true)
	private java.math.BigDecimal forzenLastAmount;

	/**唯一交易ID*/
	@ApiModelProperty(value = "唯一交易ID")
	private String transactionId;

	/**充值入金账号*/
	@ApiModelProperty(value = "充值入金账号")
	private String depositAccount;

	/**提现出金账号*/
	@ApiModelProperty(value = "提现出金账号")
	private String withdrawAccount;

	/**手续费*/
	@NotNull(message = "手续费不可为空")
	@ApiModelProperty(value = "手续费", required = true)
	private java.math.BigDecimal fee;

	/**网络手续费*/
	@ApiModelProperty(value = "网络手续费")
	private java.math.BigDecimal netfee;

	/**状态(0正常、1非正常)*/
	@NotNull(message = "状态(0正常、1非正常)不可为空")
	@ApiModelProperty(value = "状态(0正常、1非正常)", required = true)
	private Boolean status;

	/**原业务ID*/
	@NotNull(message = "原业务ID不可为空")
	@ApiModelProperty(value = "原业务ID", required = true)
	private Long originalBusinessId;

	/**remark*/
	@ApiModelProperty(value = "remark")
	private String remark;
}

