/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IB账户家族表 实体对象
 * <p>File：IbAccountFamily.java</p>
 * <p>Title: IbAccountFamily</p>
 * <p>Description:IbAccountFamily</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IB账户家族表")
public class IbAccountFamily extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private java.lang.Long accountId;

	/**IB对应AccountCode*/
	@NotNull(message = "IB对应AccountCode不可为空")
	@ApiModelProperty(value = "IB对应AccountCode", required = true)
	private java.lang.String ibAccountCode;

	/**IB对应FamilyCode*/
	@ApiModelProperty(value = "IB对应FamilyCode")
	private java.lang.String ibFamilyCode;

	/**本位币*/
	@ApiModelProperty(value = "本位币")
	private java.lang.String  baseCurrency;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createDate;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private java.lang.Long updateDate;

	/***************关联查询数据***************/
	private java.lang.String accountName;
}

