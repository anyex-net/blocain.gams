/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.entity;

import javax.validation.constraints.NotNull;

import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * OAUTH_CODE 实体对象
 * <p>File：OauthCode.java</p>
 * <p>Title: OauthCode</p>
 * <p>Description:OauthCode</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "OAUTH_CODE")
public class OauthCode extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**code*/
	@NotNull(message = "code不可为空")
	@ApiModelProperty(value = "code", required = true)
	private String code;

	/**userName*/
	@NotNull(message = "userName不可为空")
	@ApiModelProperty(value = "userName", required = true)
	private String userName;

	/**clientId*/
	@NotNull(message = "clientId不可为空")
	@ApiModelProperty(value = "clientId", required = true)
	private String clientId;

	/**createTime*/
	@NotNull(message = "createTime不可为空")
	@ApiModelProperty(value = "createTime", required = true)
	private Long createTime;


}

