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
 * 合伙企业w8ben表 实体对象
 * <p>File：AccountParWbenContract.java</p>
 * <p>Title: AccountParWbenContract</p>
 * <p>Description:AccountParWbenContract</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业w8ben表")
public class AccountParWbenContract extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**个人或组织的名称*/
	@NotNull(message = "个人或组织的名称不可为空")
	@ApiModelProperty(value = "个人或组织的名称", required = true)
	@Size(max = 128)
	private String organizationName;

	/**成立或组织的所在国家*/
	@NotNull(message = "成立或组织的所在国家不可为空")
	@ApiModelProperty(value = "成立或组织的所在国家", required = true)
	@Size(max = 32)
	private String organizationCountry;

	/**被忽略实体的名称*/
	@ApiModelProperty(value = "被忽略实体的名称")
	@Size(max = 128)
	private String disregardedEntityName;

	/**第三章状态*/
	@NotNull(message = "第三章状态不可为空")
	@ApiModelProperty(value = "第三章状态", required = true)
	@Size(max = 128)
	private String chapterThreeStatus;

	/**该实体是否为提出条约索赔的混合体*/
	@ApiModelProperty(value = "该实体是否为提出条约索赔的混合体")
	@Size(max = 32)
	private String whether;

	/**第四章状态*/
	@ApiModelProperty(value = "第四章状态")
	@Size(max = 1024)
	private String chapterFourStatus;

	/**美国识别号TIN*/
	@ApiModelProperty(value = "美国识别号TIN")
	@Size(max = 128)
	private String usaTin;

	/**EIN*/
	@ApiModelProperty(value = "EIN")
	@Size(max = 32)
	private String ein;

	/**GIIN (if applicable-"XXXXXX.XXXXX.XX.XXX")*/
	@ApiModelProperty(value = "GIIN (if applicable-XXXXXX.XXXXX.XX.XXX)")
	@Size(max = 32)
	private String giinXxx;

	/**外国TIN*/
	@ApiModelProperty(value = "外国TIN")
	@Size(max = 128)
	private String foreignTin;

	/**参考编号(见说明)*/
	@ApiModelProperty(value = "参考编号(见说明)")
	@Size(max = 128)
	private String referenceNumber;

	/**第四章不予受理的实体*/
	@ApiModelProperty(value = "第四章不予受理的实体")
	@Size(max = 32)
	private String ffi;

	/**分公司地址(街道，公寓或套房号)*/
	@ApiModelProperty(value = "分公司地址(街道，公寓或套房号)")
	@Size(max = 131)
	private String branchAddress;

	/**分公司城市或城镇，州或省*/
	@ApiModelProperty(value = "分公司城市或城镇，州或省")
	@Size(max = 128)
	private String branchCity;

	/**分公司国家*/
	@ApiModelProperty(value = "分公司国家")
	@Size(max = 32)
	private String branchCountry;

	/**GIIN (if any)*/
	@ApiModelProperty(value = "GIIN (if any)")
	@Size(max = 32)
	private String gginIf;

	/**不扣缴*/
	@ApiModelProperty(value = "不扣缴")
	@Size(max = 1024)
	private String nonwithholdingA;

	/**勾选*/
	@ApiModelProperty(value = "勾选")
	@Size(max = 3072)
	private String checked;

	/**签名*/
	@ApiModelProperty(value = "签名")
	@Size(max = 128)
	private String signature;

	/**签名时间*/
	@ApiModelProperty(value = "签名时间")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long signatureTime;

	/**同意*/
	@ApiModelProperty(value = "同意")
	private String status;

	/**类型(公司或者合伙企业) 见税收分类*/
	@NotNull(message = "类型(公司或者合伙企业) 见税收分类不可为空")
	@ApiModelProperty(value = "类型(公司或者合伙企业 见税收分类)", required = true)
	@Size(max = 64)
	private String type;

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

