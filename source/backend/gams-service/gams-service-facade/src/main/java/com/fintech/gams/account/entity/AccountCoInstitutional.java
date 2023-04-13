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
 * 公司机构 实体对象
 * <p>File：AccountCoInstitutional.java</p>
 * <p>Title: AccountCoInstitutional</p>
 * <p>Description:AccountCoInstitutional</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公司机构")
public class AccountCoInstitutional extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**机构名称*/
	@NotNull(message = "机构名称不可为空")
	@ApiModelProperty(value = "机构名称", required = true)
	@Size(max = 1024)
	private String institutionalName;

	/**业务描述*/
	@ApiModelProperty(value = "业务描述")
	@Size(max = 3072)
	private String bsinessDescription;

	/**商业名称*/
	@ApiModelProperty(value = "商业名称")
	@Size(max = 1024)
	private String businessName;

	/**网址*/
	@NotNull(message = "网址不可为空")
	@ApiModelProperty(value = "网址", required = true)
	@Size(max = 1024)
	private String websiteUrl;

	/**机构性质(见数据字典机构性质)*/
	@NotNull(message = "机构性质(见数据字典机构性质)不可为空")
	@ApiModelProperty(value = "机构性质(见数据字典机构性质)", required = true)
	@Size(max = 3072)
	private String istitutionalNature;

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

