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
 * 公司资产信息 实体对象
 * <p>File：AccountCoAsset.java</p>
 * <p>Title: AccountCoAsset</p>
 * <p>Description:AccountCoAsset</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公司资产信息")
public class AccountCoAsset extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**年度净收入（见字典数据年度净收入）*/
	@NotNull(message = "年度净收入（见字典数据年度净收入)不可为空")
	@ApiModelProperty(value = "年度净收入（见字典数据年度净收入）", required = true)
	@Size(max = 32)
	private String anualNetIncome;

	/**资产净值（见字典数据资产净值）*/
	@NotNull(message = "资产净值（见字典数据资产净值）不可为空")
	@ApiModelProperty(value = "资产净值（见字典数据资产净值）", required = true)
	@Size(max = 32)
	private String netAssetValue;

	/**流动资产净值（见字典数据流动资产净值）*/
	@NotNull(message = "流动资产净值（见字典数据资产净值）不可为空")
	@ApiModelProperty(value = "流动资产净值（见字典数据资产净值）", required = true)
	@Size(max = 32)
	private String netCurrentAssets;

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

