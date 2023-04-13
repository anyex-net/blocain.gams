/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公司地址信息 实体对象
 * <p>File：AccountCoAddress.java</p>
 * <p>Title: AccountCoAddress</p>
 * <p>Description:AccountCoAddress</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公司地址信息")
public class AccountCoAddress extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**公司个人信息ID*/
	@ApiModelProperty(value = "公司个人信息ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long coPersonalId;

	/**国家(见数据字典国家类型)*/
	@NotNull(message = "国家(见数据字典国家类型)不可为空")
	@ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
	@Size(max = 32)
	private String country;

	/**地址*/
	@NotNull(message = "地址不可为空")
	@ApiModelProperty(value = "地址", required = true)
	@Size(max = 131)
	private String address;

	/**城市*/
	@NotNull(message = "城市不可为空")
	@ApiModelProperty(value = "城市", required = true)
	@Size(max = 128)
	private String city;

	/**省*/
	@NotNull(message = "省不可为空")
	@ApiModelProperty(value = "省", required = true)
	@Size(max = 128)
	private String rovince;

	/**邮编*/
	@NotNull(message = "邮编不可为空")
	@ApiModelProperty(value = "邮编", required = true)
	@Size(max = 32)
	private String zipCode;

	/**居住，邮寄,公司(见数据字典地址类型)*/
	@NotNull(message = "居住，邮寄,公司(见数据字典地址类型)不可为空")
	@ApiModelProperty(value = "居住，邮寄,公司(见数据字典地址类型)", required = true)
	@Size(max = 32)
	private String addressType;

	/**其他国家(是否和上方选的国家不同)(见数据字典国家类型)*/
	@ApiModelProperty(value = "其他国家(是否和上方选的国家不同)(见数据字典国家类型)")
	@Size(max = 32)
	private String otherCountry;

	/**其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)*/
	@ApiModelProperty(value = "其他出生国家(是否和上方选的国家不同)(见数据字典国家类型)")
	@Size(max = 32)
	private String otherBirthCountry;

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

