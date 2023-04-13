/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import com.fintech.gams.system.entity.UserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 证券信息 实体对象
 * <p>File：StockInfo.java</p>
 * <p>Title: StockInfo</p>
 * <p>Description:StockInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "证券信息")
public class StockInfo extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**证券编码*/
    @NotNull(message = "证券编码不可为空")
    @ApiModelProperty(value = "证券编码", required = true)
    private String               stockCode;
    
    /**证券代码*/
    @NotNull(message = "证券代码不可为空")
    @ApiModelProperty(value = "证券代码", required = true)
    private String               stockSymbol;
    
    /**证券ISIN码*/
    @NotNull(message = "证券ISIN码不可为空")
    @ApiModelProperty(value = "证券ISIN码", required = true)
    private String               stockIsin;
    
    /**证券拼音缩写*/
    @ApiModelProperty(value = "证券拼音缩写")
    private String               stockSpellAbbr;
    
    /**证券英文名称*/
    @NotNull(message = "证券英文名称不可为空")
    @ApiModelProperty(value = "证券英文名称", required = true)
    private String               stockNameEn;
    
    /**证券中文名称*/
    @NotNull(message = "证券中文名称不可为空")
    @ApiModelProperty(value = "证券中文名称", required = true)
    private String               stockNameCn;
    
    /**证券类型(数据字典)*/
    @NotNull(message = "证券类型(数据字典)不可为空")
    @ApiModelProperty(value = "证券类型(数据字典)", required = true)
    private String               stockType;
    
    /**资产类型(数据字典)*/
    @NotNull(message = "资产类型(数据字典)不可为空")
    @ApiModelProperty(value = "资产类型(数据字典)", required = true)
    private String               assetType;
    
    /**上市场所(证券交易所)*/
    @NotNull(message = "上市场所(证券交易所)不可为空")
    @ApiModelProperty(value = "上市场所(证券交易所)", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 exchangeId;
    
    /**基础币种(数据字典)*/
    @NotNull(message = "基础币种(数据字典)不可为空")
    @ApiModelProperty(value = "基础币种(数据字典)", required = true)
    private String               baseCurrency;
    
    /**发行人Code*/
    @ApiModelProperty(value = "发行人Code")
    private String               companyCode;
    
    /**发行人名称*/
    @ApiModelProperty(value = "发行人名称")
    private String               companyName;
    
    /**发行日期*/
    @ApiModelProperty(value = "发行日期")
    private Long                 offerDate;
    
    /**上市日期*/
    @ApiModelProperty(value = "上市日期")
    private Long                 listDate;
    
    /**退市日期*/
    @ApiModelProperty(value = "退市日期")
    private Long                 cancelDate;
    
    /**最新价格*/
    @NotNull(message = "最新价格不可为空")
    @ApiModelProperty(value = "最新价格", required = true)
    private java.math.BigDecimal lastPrice;
    
    /**是否开放交易 0否、1是*/
    @NotNull(message = "是否开放交易 0否、1是不可为空")
    @ApiModelProperty(value = "是否开放交易 0否、1是", required = true)
    private Boolean              isOpentrade;

    /**IB平台代码*/
    @ApiModelProperty(value = "IB平台代码", required = true)
    private String              ibSymbol;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人")
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
    private StockExchange        stockExchange;
    
    private UserInfo             createUser;
    
    private UserInfo             updateUser;
}
