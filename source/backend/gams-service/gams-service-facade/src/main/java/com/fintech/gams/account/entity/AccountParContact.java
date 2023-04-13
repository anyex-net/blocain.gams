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
 * 合伙企业联系信息 实体对象
 * <p>File：AccountParContact.java</p>
 * <p>Title: AccountParContact</p>
 * <p>Description:AccountParContact</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业联系信息")
public class AccountParContact extends GenericEntity {
	
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

	/**国家(见数据字典国家类型)*/
	@NotNull(message = "国家(见数据字典国家类型)不可为空")
	@ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
	@Size(max = 32)
	private String country;

	/**号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)*/
	@NotNull(message = "号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)不可为空")
	@ApiModelProperty(value = "号码类别 手机，电话，传真，家庭，商务(见字典电话号码类型)", required = true)
	@Size(max = 32)
	private String contactNumberType;

	/**联系号码*/
	@NotNull(message = "联系号码不可为空")
	@ApiModelProperty(value = "联系号码", required = true)
	@Size(min = 6, max = 11)
	private String contactNumber;

	/**成立国家（见区域表。法人股东需要）*/
	@ApiModelProperty(value = "成立国家（见区域表。法人股东需要）")
	@Size(max = 32)
	private String foundingCountry;

	/**实体是否在监管交易所公开上市交易（法人股东需要）*/
	@ApiModelProperty(value = "实体是否在监管交易所公开上市交易（法人股东需要）", required = true)
	private Boolean status;

	/**交易所（法人股东需要）*/
	@ApiModelProperty(value = "交易所（法人股东需要）")
	@Size(max = 128)
	private String exchange;

	/**代码（法人股东需要）*/
	@ApiModelProperty(value = "代码（法人股东需要）")
	@Size(max = 128)
	private String code;

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

