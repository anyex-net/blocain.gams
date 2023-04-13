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
 * 合伙企业CRS控权人自我证明控权人编号 实体对象
 * <p>File：AccountParCrsControlNum.java</p>
 * <p>Title: AccountParCrsControlNum</p>
 * <p>Description:AccountParCrsControlNum</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业CRS控权人自我证明控权人编号")
public class AccountParCrsControlNum extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**实体编号*/
	@ApiModelProperty(value = "实体编号")
	@Size(max = 64)
	private String entityNumber;

	/**实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）*/
	@NotNull(message = "实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）不可为空")
	@ApiModelProperty(value = "实体类别（法人,信托,除信托以外的法律安排，见字典数据控权人类型）", required = true)
	@Size(max = 64)
	private String entityCategory;

	/**控权人类别（见字典数据控权人子节点）*/
	@NotNull(message = "控权人类别（见字典数据控权人子节点）不可为空")
	@ApiModelProperty(value = "控权人类别（见字典数据控权人子节点）", required = true)
	@Size(max = 1024)
	private String controlCategory;

	/**第二部分,控权人的实体账户持有人的名称(公司名)*/
	@NotNull(message = "第二部分,控权人的实体账户持有人的名称(公司名)不可为空")
	@ApiModelProperty(value = "第二部分,控权人的实体账户持有人的名称(公司名)", required = true)
	@Size(max = 1024)
	private String entityAccountName;

	/**crs实体（CRS-E）控权人(控权人姓名)信息ID*/
	@ApiModelProperty(value = "crs实体（CRS-E）控权人(控权人姓名)信息ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long parCrsEntityContrId;


}

