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
 * 合伙企业规管信息 实体对象
 * <p>File：AccountParRegulatory.java</p>
 * <p>Title: AccountParRegulatory</p>
 * <p>Description:AccountParRegulatory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业规管信息")
public class AccountParRegulatory extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**是否*/
	@NotNull(message = "是否不可为空")
	@ApiModelProperty(value = "是否", required = true)
	private Boolean whether;

	/**信息详情json*/
	@ApiModelProperty(value = "信息详情json")
	@Size(max = 3072)
	private String infoDetails;

	/**类型*/
	@NotNull(message = "类型不可为空")
	@ApiModelProperty(value = "类型", required = true)
	@Size(max = 3072)
	private String infoType;


}

