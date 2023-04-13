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
 * 证券理财产品信息 实体对象
 * <p>File：StockWmp.java</p>
 * <p>Title: StockWmp</p>
 * <p>Description:StockWmp</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "证券理财产品信息")
public class StockWmp extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**证券信息ID*/
	@NotNull(message = "证券信息ID不可为空")
	@ApiModelProperty(value = "证券信息ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private java.lang.Long stockInfoId;

	/**基金类型(数据字典)*/
	@NotNull(message = "基金类型(数据字典)不可为空")
	@ApiModelProperty(value = "基金类型(数据字典)", required = true)
	private java.lang.String fundType;

	/**预期年化收益率*/
	@ApiModelProperty(value = "预期年化收益率")
	private java.math.BigDecimal expectedAnnualReturn;

	/**产品期限*/
	@ApiModelProperty(value = "产品期限")
	private java.lang.Long productLife;

	/**风险程度等级(数据字典)*/
	@ApiModelProperty(value = "风险程度等级(数据字典)")
	private java.lang.String riskLevel;

	/**起售金额*/
	@ApiModelProperty(value = "起售金额")
	private java.math.BigDecimal startSaleAmt;

	/**上市日期*/
	@ApiModelProperty(value = "上市日期")
	private java.lang.Long listingDate;

	/**到期日期*/
	@ApiModelProperty(value = "到期日期")
	private java.lang.Long expireDate;

	/**是否推荐(0不推荐、1推荐)*/
	@ApiModelProperty(value = "是否推荐(0不推荐、1推荐)")
	private java.lang.Boolean isRecommend;

	/**创建人*/
	@ApiModelProperty(value = "创建人", required = true)
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

	/*******************查询参数********************/
	private String               stockCode;

	private String               stockSymbol;

	private String               stockNameEn;

	private String               stockNameCn;

	private String[] 		 	 baseCurrencyArray;

	private String[]		 	 fundTypeArray;

	private String[] 		 	 riskLevelArray;

	/*******************关联查询对象********************/
	private StockInfo 			stockInfo;
}

