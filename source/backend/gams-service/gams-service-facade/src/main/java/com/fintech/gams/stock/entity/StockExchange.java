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
 * 证券交易所信息 实体对象
 * <p>File：StockExchange.java</p>
 * <p>Title: StockExchange</p>
 * <p>Description:StockExchange</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "证券交易所信息")
public class StockExchange extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**交易所国际编码*/
    @NotNull(message = "交易所国际编码不可为空")
    @ApiModelProperty(value = "交易所国际编码", required = true)
    private String            exchangeCode;
    
    /**交易所名称*/
    @NotNull(message = "交易所名称不可为空")
    @ApiModelProperty(value = "交易所名称", required = true)
    private String            exchangeName;
    
    /**所在国家地区(国家地区)*/
    @NotNull(message = "所在国家地区(国家地区)不可为空")
    @ApiModelProperty(value = "所在国家地区(国家地区)", required = true)
    private String            region;
    
    /**所在世界大洲(数据字典)*/
    @NotNull(message = "所在世界大洲(数据字典)不可为空")
    @ApiModelProperty(value = "所在世界大洲(数据字典)", required = true)
    private String            continent;
    
    /**基础币种(数据字典)*/
    @NotNull(message = "基础币种(数据字典)不可为空")
    @ApiModelProperty(value = "基础币种(数据字典)", required = true)
    private String            baseCurrency;
    
    /**交易日类型(数据字典)*/
    @ApiModelProperty(value = "交易日类型(数据字典)")
    private String            tradeDayType;
    
    /**是否开放交易 0否、1是*/
    @NotNull(message = "是否开放交易 0否、1是不可为空")
    @ApiModelProperty(value = "是否开放交易 0否、1是", required = true)
    private Boolean           isOpentrade;
    
    /**创建人*/
    @ApiModelProperty(value = "创建时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;
    
    /**修改人*/
    @ApiModelProperty(value = "修改人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              updateBy;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long              updateDate;
    
    /*******************关联查询对象********************/
    private UserInfo          createUser;
    
    private UserInfo          updateUser;
}
