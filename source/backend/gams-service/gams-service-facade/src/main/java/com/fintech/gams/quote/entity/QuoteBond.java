/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.quote.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import com.fintech.gams.stock.entity.StockExchange;
import com.fintech.gams.stock.entity.StockInfo;
import com.fintech.gams.system.entity.UserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 债券行情 实体对象
 * <p>File：QuoteBond.java</p>
 * <p>Title: QuoteBond</p>
 * <p>Description:QuoteBond</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "债券行情")
public class QuoteBond extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**证券信息ID*/
    @NotNull(message = "证券信息ID不可为空")
    @ApiModelProperty(value = "证券信息ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 stockinfoId;
    
    /**证券编码*/
    @ApiModelProperty(value = "证券编码", required = true)
    private String               stockCode;
    
    /**上市场所ID(证券交易所)*/
    @NotNull(message = "上市场所ID(证券交易所)不可为空")
    @ApiModelProperty(value = "上市场所ID(证券交易所)", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 exchangeId;
    
    /**行情日期*/
    @NotNull(message = "行情日期不可为空")
    @ApiModelProperty(value = "行情日期", required = true)
    private Long                 quoteDate;
    
    /**昨收盘价*/
    @NotNull(message = "昨收盘价不可为空")
    @ApiModelProperty(value = "昨收盘价", required = true)
    @Digits(integer = 6, fraction = 6)
    private java.math.BigDecimal prevClose;
    
    /**最新价*/
    @NotNull(message = "最新价不可为空")
    @ApiModelProperty(value = "最新价", required = true)
    @Digits(integer = 6, fraction = 6)
    private java.math.BigDecimal last;
    
    /**开盘价*/
    @ApiModelProperty(value = "开盘价")
    private java.math.BigDecimal open;
    
    /**最高价*/
    @ApiModelProperty(value = "最高价")
    private java.math.BigDecimal high;
    
    /**最低价*/
    @ApiModelProperty(value = "最低价")
    private java.math.BigDecimal low;
    
    /**涨跌幅*/
    @ApiModelProperty(value = "涨跌幅")
    private java.math.BigDecimal chg;
    
    /**涨跌额*/
    @ApiModelProperty(value = "涨跌额")
    private java.math.BigDecimal change;
    
    /**成交量*/
    @ApiModelProperty(value = "成交量")
    private java.math.BigDecimal volume;
    
    /**成交额*/
    @ApiModelProperty(value = "成交额")
    private java.math.BigDecimal turnover;
    
    /**委比*/
    @ApiModelProperty(value = "委比")
    private java.math.BigDecimal committeeThan;
    
    /**量比*/
    @ApiModelProperty(value = "量比")
    private java.math.BigDecimal quantityRelative;
    
    /**换手率*/
    @ApiModelProperty(value = "换手率")
    private java.math.BigDecimal turnoverRate;
    
    /**买一价*/
    @ApiModelProperty(value = "买一价")
    private java.math.BigDecimal bid;
    
    /**卖一价*/
    @ApiModelProperty(value = "卖一价")
    private java.math.BigDecimal ask;

    /**买入量*/
    @ApiModelProperty(value = "买入量")
    private java.lang.Long       bidSize;

    /**卖出量*/
    @ApiModelProperty(value = "卖出量")
    private java.lang.Long       askSize;
    
    /**创建人*/
    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long                 createDate;
    
    /**修改人*/
    @ApiModelProperty(value = "修改人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 updateBy;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long                 updateDate;
    
    /*******************查询参数********************/
    private String               exchangeName;
    
    /*******************关联查询对象********************/
    private StockInfo            stockInfo;
    
    private StockExchange        stockExchange;
    
    private UserInfo             createUser;
    
    private UserInfo             updateUser;
}
