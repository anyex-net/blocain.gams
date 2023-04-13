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
 * 合伙企业CRS控权人自我证明表格(CRS-CP) 实体对象
 * <p>File：AccountParCrsControl.java</p>
 * <p>Title: AccountParCrsControl</p>
 * <p>Description:AccountParCrsControl</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业CRS控权人自我证明表格(CRS-CP)")
public class AccountParCrsControl extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**称呼：先生，女士，博士，夫人(见数据字典称呼类型)*/
	@NotNull(message = "称呼：先生，女士，博士，夫人(见数据字典称呼类型)不可为空")
	@ApiModelProperty(value = "称呼：先生，女士，博士，夫人(见数据字典称呼类型)", required = true)
	@Size(max = 32)
	private String appellation;

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

	/**身份证类型(见数据字典证件类型)*/
	@NotNull(message = "身份证类型(见数据字典证件类型)不可为空")
	@ApiModelProperty(value = "身份证类型(见数据字典证件类型)", required = true)
	@Size(max = 32)
	private String idCardType;

	/**身份证件号码*/
	@NotNull(message = "身份证件号码不可为空")
	@ApiModelProperty(value = "身份证件号码", required = true)
	@Size(max = 63)
	private String idNumber;

	/**出生日期*/
	@NotNull(message = "出生日期不可为空")
	@ApiModelProperty(value = "出生日期", required = true)
	private String birthday;

	/**是否地址与上方的不一致*/
	@NotNull(message = "是否地址与上方的不一致不可为空")
	@ApiModelProperty(value = "是否地址与上方的不一致", required = true)
	private Boolean otherAddressStatus;

	/**其他地址*/
	@ApiModelProperty(value = "其他地址")
	@Size(max = 256)
	private String otherAddresses;

	/**其他城市*/
	@ApiModelProperty(value = "其他城市")
	@Size(max = 64)
	private String otherCity;

	/**其他省/市*/
	@ApiModelProperty(value = "其他省/市")
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

	/**第二部分,控权人的实体账户持有人的名称(公司名)*/
	@NotNull(message = "第二部分,控权人的实体账户持有人的名称(公司名)不可为空")
	@ApiModelProperty(value = "第二部分,控权人的实体账户持有人的名称(公司名)", required = true)
	@Size(max = 1024)
	private String entityAccountName;

	/**有税务编号*/
	@ApiModelProperty(value = "有税务编号")
	@Size(max = 64)
	private String yesTaxNumber;

	/**没有税务编号（见字典数据税务编号类型）*/
	@ApiModelProperty(value = "没有税务编号（见字典数据税务编号类型）")
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

	/**同意《税务条例》*/
	@NotNull(message = "同意《税务条例》不可为空")
	@ApiModelProperty(value = "同意《税务条例》", required = true)
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

	/**crs实体（CRS-E）控权人(控权人姓名)信息ID*/
	@ApiModelProperty(value = "crs实体（CRS-E）控权人(控权人姓名)信息ID")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long parCrsEntityContrId;


}

