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
 * ACCOUNT_COMMON_TRADE_LOCATION 实体对象
 * <p>File：AccountCommonTradeLocation.java</p>
 * <p>Title: AccountCommonTradeLocation</p>
 * <p>Description:AccountCommonTradeLocation</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "ACCOUNT_COMMON_TRADE_LOCATION")
public class AccountCommonTradeLocation extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**交易地点*/
	@NotNull(message = "交易地点不可为空")
	@ApiModelProperty(value = "交易地点", required = true)
	private String tansactionLocation;

	/**交易类型*/
	@NotNull(message = "交易类型不可为空")
	@ApiModelProperty(value = "交易类型", required = true)
	private String tansactionType;

	/**交易区域*/
	@NotNull(message = "交易区域不可为空")
	@ApiModelProperty(value = "交易区域", required = true)
	private String tansactionRegion;

	/**中文名*/
	@NotNull(message = "中文名不可为空")
	@ApiModelProperty(value = "中文名", required = true)
	private String cnName;

	/**英文名*/
	@NotNull(message = "英文名不可为空")
	@ApiModelProperty(value = "英文名", required = true)
	private String enName;


}

