/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 个人一体化投资管理账户功能 实体对象
 * <p>File：AccountPerInvestAccount.java</p>
 * <p>Title: AccountPerInvestAccount</p>
 * <p>Description:AccountPerInvestAccount</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人一体化投资管理账户功能")
public class AccountPerInvestAccount extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户id*/
	@NotNull(message = "账户id不可为空")
	@ApiModelProperty(value = "账户id", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**股票收益增长计划*/
	@ApiModelProperty(value = "股票收益增长计划")
	private Boolean stockIncomeGrowthPlan;

	/**账户名*/
	@NotNull(message = "账户名不可为空")
	@ApiModelProperty(value = "账户名", required = true)
	@Size(max = 128)
	private String accountName;

	/**日期*/
	@NotNull(message = "日期不可为空")
	@ApiModelProperty(value = "日期", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long signatureDate;

	/**签名*/
	@NotNull(message = "签名不可为空")
	@ApiModelProperty(value = "签名", required = true)
	@Size(max = 128)
	private String signature;

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

