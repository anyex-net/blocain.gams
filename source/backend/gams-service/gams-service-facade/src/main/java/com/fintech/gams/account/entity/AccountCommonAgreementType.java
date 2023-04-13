/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fintech.gams.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * ACCOUNT_COMMON_AGREEMENT_TYPE 实体对象
 * <p>File：AccountCommonAgreementType.java</p>
 * <p>Title: AccountCommonAgreementType</p>
 * <p>Description:AccountCommonAgreementType</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "ACCOUNT_COMMON_AGREEMENT_TYPE")
public class AccountCommonAgreementType extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**协议类型（字典表交易：股票等）*/
	@NotNull(message = "协议类型（字典表交易：股票等）不可为空")
	@ApiModelProperty(value = "协议类型（字典表交易：股票等）", required = true)
	private String agreementType;

	/**账户类型(字典表账户类型)*/
	@NotNull(message = "账户类型(字典表账户类型)不可为空")
	@ApiModelProperty(value = "账户类型(字典表账户类型)", required = true)
	private String accountType;

	/**协议编号（ACCOUNT_COMMON_AGREEMENT表编号）*/
	@NotNull(message = "协议编号（ACCOUNT_COMMON_AGREEMENT表编号）不可为空")
	@ApiModelProperty(value = "协议编号（ACCOUNT_COMMON_AGREEMENT表编号）", required = true)
	private String agreementNumber;

	/**协议地点（见数据字典表交易地点类型）*/
	@NotNull(message = "协议地点（见数据字典表交易地点类型）不可为空")
	@ApiModelProperty(value = "协议地点（见数据字典表交易地点类型）", required = true)
	private String agreementLocation;

	/**账户投资组合类型（见字典数据账户投资组合类型）*/
	@NotNull(message = "账户投资组合类型（见字典数据账户投资组合类型）不可为空")
	@ApiModelProperty(value = "账户投资组合类型（见字典数据账户投资组合类型）", required = true)
	private String accountPortfolioType;


}

