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
 * 合伙企业税务居住地 实体对象
 * <p>File：AccountParTaxes.java</p>
 * <p>Title: AccountParTaxes</p>
 * <p>Description:AccountParTaxes</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业税务居住地")
public class AccountParTaxes extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**合伙企业个人信息ID*/
	@NotNull(message = "合伙企业个人信息ID不可为空")
	@ApiModelProperty(value = "合伙企业个人信息ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long parPersonalId;

	/**税务居住国家(见数据字典国家类型)*/
	@ApiModelProperty(value = "税务居住国家(见数据字典国家类型)")
	@Size(max = 32)
	private String taxResidenceCountry;

	/**税务居住地证件号码*/
	@ApiModelProperty(value = "税务居住地证件号码")
	@Size(max = 64)
	private String taxResidenceIdCardNumber;

	/**无纳税识别号的理由选择*/
	@ApiModelProperty(value = "无纳税识别号的理由选择")
	@Size(max = 256)
	private String noTaxIdentificationNumber;

	/**不同的注册号码*/
	@ApiModelProperty(value = "不同的注册号码")
	@Size(max = 32)
	private String differentRegistrationNumber;

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

