package com.fintech.gams.stock.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 热门基金行情对象
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
@ApiModel(description = "热门基金行情对象")
public class HotFundQuote implements Serializable
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

    /**昨收盘价*/
    @ApiModelProperty(value = "昨收盘价")
    private java.math.BigDecimal prevClose;

    /**最新价(单位净值)*/
    @ApiModelProperty(value = "最新价(单位净值)")
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

//    /**开盘价*/
//    @ApiModelProperty(value = "开盘价")
//    private java.math.BigDecimal open;
//
//    /**最高价*/
//    @ApiModelProperty(value = "最高价")
//    private java.math.BigDecimal high;
//
//    /**最低价*/
//    @ApiModelProperty(value = "最低价")
//    private java.math.BigDecimal low;
//
//    /**涨跌幅*/
//    @ApiModelProperty(value = "涨跌幅")
//    private java.math.BigDecimal chg;
//
//    /**涨跌额*/
//    @ApiModelProperty(value = "涨跌额")
//    private java.math.BigDecimal change;
//
//    /**成交量*/
//    @ApiModelProperty(value = "成交量")
//    private java.math.BigDecimal volume;
//
//    /**成交额*/
//    @ApiModelProperty(value = "成交额")
//    private java.math.BigDecimal turnover;
//
//    /**委比*/
//    @ApiModelProperty(value = "委比")
//    private Integer              committeeThan;
//
//    /**量比*/
//    @ApiModelProperty(value = "量比")
//    private Integer              quantityRelative;
//
//    /**换手率*/
//    @ApiModelProperty(value = "换手率")
//    private Integer              turnoverRate;
//
//    /**买一价*/
//    @ApiModelProperty(value = "买一价")
//    private java.math.BigDecimal bid;
//
//    /**卖一价*/
//    @ApiModelProperty(value = "卖一价")
//    private java.math.BigDecimal ask;

    /**累计净值*/
    @ApiModelProperty(value = "累计净值")
    private java.math.BigDecimal cumulativeNetValue;

    /**日增长率*/
    @ApiModelProperty(value = "日增长率")
    private java.math.BigDecimal returnDailyRate;

    /**近一周收益率*/
    @ApiModelProperty(value = "近一周收益率")
    private java.math.BigDecimal return1week;

    /**近一月收益率*/
    @ApiModelProperty(value = "近一月收益率")
    private java.math.BigDecimal return1month;

    /**近三月收益率*/
    @ApiModelProperty(value = "近三月收益率")
    private java.math.BigDecimal return3month;

    /**近六月收益率*/
    @ApiModelProperty(value = "近六月收益率")
    private java.math.BigDecimal return6month;

    /**近一年收益率*/
    @ApiModelProperty(value = "近一年收益率")
    private java.math.BigDecimal return1year;

    /**近两年收益率*/
    @ApiModelProperty(value = "近两年收益率")
    private java.math.BigDecimal return2year;

    /**近三年收益率*/
    @ApiModelProperty(value = "近三年收益率")
    private java.math.BigDecimal return3year;

    /**今年以来收益率*/
    @ApiModelProperty(value = "今年以来收益率")
    private java.math.BigDecimal returnYieldThisYear;

    /**成立以来收益率*/
    @ApiModelProperty(value = "成立以来收益率")
    private java.math.BigDecimal returnSinceFoundation;

    /*******************查询参数********************/
    private String               baseCurrency;
}
