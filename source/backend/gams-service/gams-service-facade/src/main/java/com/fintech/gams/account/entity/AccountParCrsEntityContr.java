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
 * 合伙企业crs实体（CRS-E）控权人信息 实体对象
 * <p>File：AccountParCrsEntityContr.java</p>
 * <p>Title: AccountParCrsEntityContr</p>
 * <p>Description:AccountParCrsEntityContr</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业crs实体（CRS-E）控权人信息")
public class AccountParCrsEntityContr extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**姓*/
	@NotNull(message = "姓不可为空")
	@ApiModelProperty(value = "姓", required = true)
	@Size(max = 50)
	private String lastName;

	/**名*/
	@NotNull(message = "名不可为空")
	@ApiModelProperty(value = "名", required = true)
	@Size(max = 32)
	private String firstName;

	/**CRS-E的id*/
	@ApiModelProperty(value = "CRS-E的id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long parCrsEntityId;

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

