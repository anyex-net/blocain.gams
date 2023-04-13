/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;




/**
 * 个人交易经验信息 实体对象
 * <p>File：AccountPerTradeExp.java</p>
 * <p>Title: AccountPerTradeExp</p>
 * <p>Description:AccountPerTradeExp</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人交易经验信息")
public class AccountPerTradeExp extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**交易经验(见数据字典数据交易经验)*/
	@ApiModelProperty(value = "交易经验(见数据字典数据交易经验)")
	@Size(max = 256)
	private String tradingExperience;

	/**年交易数(见数据字典数据每年交易笔数)*/
	@ApiModelProperty(value = "年交易数(见数据字典数据每年交易笔数)")
	@Size(max = 256)
	private String yearTransactionsPerNumber;

	/**总交易数*/
	@ApiModelProperty(value = "总交易数")
	@Size(max = 256)
	private String transactionsTotalNumber;

	/**知识水平(见数据字典数据知识水平)*/
	@ApiModelProperty(value = "知识水平(见数据字典数据知识水平)")
	@Size(max = 32)
	private String knowledgeLevel;

	/**交易地点*/
	@ApiModelProperty(value = "交易地点")
	@Size(max = 3072)
	private String tradingLocation;

	/**交易类型(见数据字典数据交易类型)*/
	@ApiModelProperty(value = "交易类型(见数据字典数据交易类型)")
	@Size(max = 32)
	private String tansactionType;

	/**受交易权限*/
	@ApiModelProperty(value = "受交易权限")
	private Boolean transactionAuthority;

	/**创建人*/
	@NotNull(message = "创建人不可为空")
	@ApiModelProperty(value = "创建人", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createdBy;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createdDate;

	/**更新人*/
	@ApiModelProperty(value = "更新人")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long updatedBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long updatedDate;


}

