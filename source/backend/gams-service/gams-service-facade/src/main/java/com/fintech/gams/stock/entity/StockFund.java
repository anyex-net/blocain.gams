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
 * 证券基金信息 实体对象
 * <p>File：StockFund.java</p>
 * <p>Title: StockFund</p>
 * <p>Description:StockFund</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "证券基金信息")
public class StockFund extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**证券信息ID*/
    @NotNull(message = "证券信息ID不可为空")
    @ApiModelProperty(value = "证券信息ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 stockinfoId;
    
    /**基金管理机构*/
    @ApiModelProperty(value = "基金管理机构")
    private String               managerOrgCode;
    
    /**基金管理机构名称*/
    @ApiModelProperty(value = "基金管理机构名称")
    private String               managerOrgName;
    
    /**基金托管机构*/
    @ApiModelProperty(value = "基金托管机构")
    private String               trustOrgCode;
    
    /**基金托管机构名称*/
    @ApiModelProperty(value = "基金托管机构名称")
    private String               trustOrgName;
    
    /**基金设立规模*/
    @ApiModelProperty(value = "基金设立规模")
    private java.math.BigDecimal fundSize;
    
    /**设立日期*/
    @ApiModelProperty(value = "设立日期")
    private Long                 establishmentDate;
    
    /**上市日期*/
    @ApiModelProperty(value = "上市日期")
    private Long                 listDate;
    
    /**到期日期*/
    @ApiModelProperty(value = "到期日期")
    private Long                 expireDate;
    
    /**基金运作方式(数据字典)*/
    @NotNull(message = "基金运作方式(数据字典)不可为空")
    @ApiModelProperty(value = "基金运作方式(数据字典)", required = true)
    private String               fundOperate;
    
    /**基金类型(数据字典)*/
    @NotNull(message = "基金类型(数据字典)不可为空")
    @ApiModelProperty(value = "基金类型(数据字典)", required = true)
    private String               fundType;
    
    /**基金投资区域(数据字典)*/
    @NotNull(message = "基金投资区域(数据字典)不可为空")
    @ApiModelProperty(value = "基金投资区域(数据字典)", required = true)
    private String               fundInvestArea;
    
    /**基金投资风格(数据字典)*/
    @NotNull(message = "基金投资风格(数据字典)不可为空")
    @ApiModelProperty(value = "基金投资风格(数据字典)", required = true)
    private String               fundInvestStyle;
    
    /**份额结转方式(数据字典)*/
    @ApiModelProperty(value = "份额结转方式(数据字典)")
    private String               shareTransfer;
    
    /**风险程度(数据字典)*/
    @NotNull(message = "风险程度(数据字典)不可为空")
    @ApiModelProperty(value = "风险程度(数据字典)", required = true)
    private String               riskLevel;
    
    /**分红策略(数据字典)*/
    @NotNull(message = "分红策略(数据字典)不可为空")
    @ApiModelProperty(value = "分红策略(数据字典)", required = true)
    private String               dividendPolicy;
    
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
    
    /*******************关联查询对象********************/
    private StockInfo            stockInfo;
    
    private UserInfo             createUser;
    
    private UserInfo             updateUser;
}
