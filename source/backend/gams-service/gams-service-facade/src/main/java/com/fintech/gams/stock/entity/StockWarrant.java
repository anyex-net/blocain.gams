/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 证券权证信息 实体对象
 * <p>File：StockWarrant.java</p>
 * <p>Title: StockWarrant</p>
 * <p>Description:StockWarrant</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "证券权证信息")
public class StockWarrant extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/**证券信息ID*/
	@NotNull(message = "证券信息ID不可为空")
	@ApiModelProperty(value = "证券信息ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private java.lang.Long stockInfoId;

	/**权证类别(warrant普通权证、cbbc牛熊证)*/
	@NotNull(message = "权证类别(warrant普通权证、cbbc牛熊证)不可为空")
	@ApiModelProperty(value = "权证类别(warrant普通权证、cbbc牛熊证)", required = true)
	private java.lang.String warrantClass;

	/**权证类型(call认购权证、put认沽权证)*/
	@NotNull(message = "权证类型(call认购权证、put认沽权证)不可为空")
	@ApiModelProperty(value = "权证类型(call认购权证、put认沽权证)", required = true)
	private java.lang.String warrantType;

	/**行权价*/
	@ApiModelProperty(value = "行权价", required = true)
	private java.math.BigDecimal exercisePrice;

	/**创建人*/
	@ApiModelProperty(value = "创建人", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private java.lang.Long createBy;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间", required = true)
	private java.lang.Long createDate;

	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private java.lang.Long updateBy;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private java.lang.Long updateDate;

	/*******************关联查询参数********************/
	private String               stockCode;

	private String               stockSymbol;

    /*******************关联查询数据********************/
	private String                ibSymbol;

	private String               baseCurrency;

	private java.math.BigDecimal 	prevClose;

	private java.math.BigDecimal 	bid;

	private java.math.BigDecimal 	ask;

	private java.lang.Long 			bidSize;

	private java.lang.Long 			askSize;

	private Long                 quoteDate;
}

