/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 个人CRS 实体对象
 * <p>File：AccountPerCrs.java</p>
 * <p>Title: AccountPerCrs</p>
 * <p>Description:AccountPerCrs</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人CRS")
public class AccountPerCrs extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**其他地址*/
	@ApiModelProperty(value = "其他地址")
	@Size(max = 256)
	private String otherAddresses;

	/**其他城市*/
	@ApiModelProperty(value = "其他城市")
	@Size(max = 64)
	private String otherCity;

	/**其他省份*/
	@ApiModelProperty(value = "其他省份")
	@Size(max = 64)
	private String otherProvince;

	/**其他国家（见区域表）*/
	@ApiModelProperty(value = "其他国家（见区域表）")
	@Size(max = 32)
	private String otherCountry;

	/**其他邮政编码*/
	@ApiModelProperty(value = "其他邮政编码")
	@Size(max = 32)
	private String otherZipCode;

	/**有税务编号*/
	@ApiModelProperty(value = "有税务编号")
	@Size(max = 64)
	private String yesTaxNumber;

	/**没有税务编号*/
	@ApiModelProperty(value = "没有税务编号")
	@Size(max = 1024)
	private String noTaxNumber;

	/**不能取税务编号的原因*/
	@ApiModelProperty(value = "不能取税务编号的原因")
	@Size(max = 256)
	private String reason;

	/**声明所有资料均为真实*/
	@NotNull(message = "声明所有资料均为真实不可为空")
	@ApiModelProperty(value = "声明所有资料均为真实", required = true)
	private Boolean authenticData;

	/**同意税务条例*/
	@NotNull(message = "同意税务条例不可为空")
	@ApiModelProperty(value = "同意税务条例", required = true)
	private Boolean agreeTaxOrdinance;

	/**会更新的自我证明表格*/
	@NotNull(message = "会更新的自我证明表格不可为空")
	@ApiModelProperty(value = "会更新的自我证明表格", required = true)
	private Boolean provenItself;

	/**同意所有税务表格均用过电子形式发送*/
	@NotNull(message = "同意所有税务表格均用过电子形式发送不可为空")
	@ApiModelProperty(value = "同意所有税务表格均用过电子形式发送", required = true)
	private Boolean consent;

	/**签名日期*/
	@NotNull(message = "签名日期不可为空")
	@ApiModelProperty(value = "签名日期", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long signatureDate;

	/**签名*/
	@NotNull(message = "签名不可为空")
	@ApiModelProperty(value = "签名", required = true)
	@Size(max = 128)
	private String signature;

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

