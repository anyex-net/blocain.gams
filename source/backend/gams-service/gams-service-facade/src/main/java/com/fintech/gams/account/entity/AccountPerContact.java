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
 * 个人联系方式 实体对象
 * <p>File：AccountPerContact.java</p>
 * <p>Title: AccountPerContact</p>
 * <p>Description:AccountPerContact</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人联系方式")
public class AccountPerContact extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**联系号码*/
	@NotNull(message = "联系号码不可为空")
	@ApiModelProperty(value = "联系号码", required = true)
	@Size(min = 6, max = 11)
	private String contactNumber;

	/**号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)*/
	@NotNull(message = "号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)不可为空")
	@ApiModelProperty(value = "号码类别 手机，电话，传真，家庭，商务(见数据字典电话号码类型)", required = true)
	@Size(max = 32)
	private String contactNumberType;

	/**国家(见数据字典国家类型)*/
	@NotNull(message = "国家(见数据字典国家类型)不可为空")
	@ApiModelProperty(value = "国家(见数据字典国家类型)", required = true)
	@Size(max = 32)
	private String country;

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

