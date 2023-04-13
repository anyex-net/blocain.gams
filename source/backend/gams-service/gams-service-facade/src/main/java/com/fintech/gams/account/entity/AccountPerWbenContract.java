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
 * 个人WBen合约 实体对象
 * <p>File：AccountPerWbenContract.java</p>
 * <p>Title: AccountPerWbenContract</p>
 * <p>Description:AccountPerWbenContract</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人WBen合约")
public class AccountPerWbenContract extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**账户名*/
	@NotNull(message = "账户名不可为空")
	@ApiModelProperty(value = "账户名", required = true)
	@Size(max = 32)
	private String accountName;

	/**签名时间*/
	@NotNull(message = "签名时间不可为空")
	@ApiModelProperty(value = "签名时间", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long signatureTime;

	/**签名*/
	@NotNull(message = "签名不可为空")
	@ApiModelProperty(value = "签名", required = true)
	@Size(max = 128)
	private String signature;

	/**其他邮寄地址*/
	@ApiModelProperty(value = "其他邮寄地址")
	@Size(max = 131)
	private String emailAddress;

	/**其他邮政编码*/
	@ApiModelProperty(value = "其他邮政编码")
	@Size(max = 128)
	private String mailCode;

	/**其他国籍(见数据字典数据国家类型)*/
	@ApiModelProperty(value = "其他国籍(见数据字典数据国家类型)")
	@Size(max = 32)
	private String otherNationality;

	/**美国纳税人*/
	@ApiModelProperty(value = "美国纳税人")
	@Size(max = 32)
	private String usaItin;

	/**海外税收识别号码*/
	@ApiModelProperty(value = "海外税收识别号码")
	@Size(max = 32)
	private String overseasTaxNumber;

	/**参考编号*/
	@ApiModelProperty(value = "参考编号")
	@Size(max = 32)
	private String referenceNumber;

	/**其他出生日期*/
	@ApiModelProperty(value = "其他出生日期")
	private String otherBirthday;

	/**美国与所属国的税收协定下*/
	@ApiModelProperty(value = "美国与所属国的税收协定下")
	@Size(max = 128)
	private String usaCountryAgreement;

	/**条约外的地址解释*/
	@ApiModelProperty(value = "条约外的地址解释")
	@Size(max = 128)
	private String explainAddress;

	/**状态*/
	@ApiModelProperty(value = "状态")
	private String consentStatus;

	/**同意税收*/
	@ApiModelProperty(value = "同意税收")
	private String consentTax;

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

