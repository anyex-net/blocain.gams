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
 * ACCOUNT_COMMON_AGREEMENT 实体对象
 * <p>File：AccountCommonAgreement.java</p>
 * <p>Title: AccountCommonAgreement</p>
 * <p>Description:AccountCommonAgreement</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "ACCOUNT_COMMON_AGREEMENT")
public class AccountCommonAgreement extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**协议英文名*/
	@NotNull(message = "协议英文名不可为空")
	@ApiModelProperty(value = "协议英文名", required = true)
	private String enName;

	/**协议存储路径*/
	@NotNull(message = "协议存储路径不可为空")
	@ApiModelProperty(value = "协议存储路径", required = true)
	private String path;

	/**协议编号*/
	@NotNull(message = "协议编号不可为空")
	@ApiModelProperty(value = "协议编号", required = true)
	private String agreementNumber;

	/**协议描述*/
	@ApiModelProperty(value = "协议描述")
	private String agreementDescription;

	/**协议中文名*/
	@ApiModelProperty(value = "协议中文名")
	private String cnName;


}

