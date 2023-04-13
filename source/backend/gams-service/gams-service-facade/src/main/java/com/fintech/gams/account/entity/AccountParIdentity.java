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
 * 合伙企业身份信息 实体对象
 * <p>File：AccountParIdentity.java</p>
 * <p>Title: AccountParIdentity</p>
 * <p>Description:AccountParIdentity</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业身份信息")
public class AccountParIdentity extends GenericEntity {
	
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

	/**签发国家*/
	@ApiModelProperty(value = "签发国家")
	@Size(max = 32)
	private String issuingCountry;

	/**身份证类型(见数据字典证件类型)*/
	@ApiModelProperty(value = "身份证类型(见数据字典证件类型)")
	@Size(max = 32)
	private String idCardType;

	/**证件号码*/
	@ApiModelProperty(value = "证件号码")
	@Size(max = 63)
	private String idNumber;

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

