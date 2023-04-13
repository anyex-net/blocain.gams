package com.fintech.gams.stock.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 热门债券行情
 * <p>File: HotStockQuote.java </p>
 * <p>Title: HotStockQuote </p>
 * <p>Description: HotStockQuote </p>
 * <p>Copyright: Copyright (c) 2019-06-14</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "热门债券行情")
public class HotBondQuote implements Serializable
{
    /**证券信息ID*/
    @ApiModelProperty(value = "证券信息ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 stockinfoId;

    /**证券编码*/
    @ApiModelProperty(value = "证券编码")
    private String               stockCode;

    /**证券名称中文*/
    @ApiModelProperty(value = "证券名称中文")
    private String               stockNameCn;

    /**证券名称英文*/
    @ApiModelProperty(value = "证券名称英文")
    private String               stockNameEn;

    /**行情日期*/
    @ApiModelProperty(value = "行情日期")
    private Long                 quoteDate;

    /**昨收价*/
    @ApiModelProperty(value = "昨收价")
    private java.math.BigDecimal prevClose;

    /**最新价*/
    @ApiModelProperty(value = "最新价")
    private java.math.BigDecimal last;

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

    /*******************查询参数********************/
    private String               baseCurrency;
}
