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
 * 个人雇佣(就业)信息 实体对象
 * <p>File：AccountPerEmployment.java</p>
 * <p>Title: AccountPerEmployment</p>
 * <p>Description:AccountPerEmployment</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人雇佣(就业)信息")
public class AccountPerEmployment extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**就业状态(见数据字典数据雇佣类型)*/
	@NotNull(message = "就业状态(见数据字典数据雇佣类型)不可为空")
	@ApiModelProperty(value = "就业状态(见数据字典数据雇佣类型)", required = true)
	@Size(max = 128)
	private String employmentStatus;

	/**公司名*/
	@ApiModelProperty(value = "公司名")
	@Size(max = 128)
	private String companyName;

	/**商业性质*/
	@ApiModelProperty(value = "商业性质")
	@Size(max = 128)
	private String commercialNature;

	/**职位*/
	@ApiModelProperty(value = "职位")
	@Size(max = 128)
	private String position;

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

