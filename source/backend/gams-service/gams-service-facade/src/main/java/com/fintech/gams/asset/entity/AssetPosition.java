/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.asset.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资产持仓表 实体对象
 * <p>File：AssetPosition.java</p>
 * <p>Title: AssetPosition</p>
 * <p>Description:AssetPosition</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资产持仓表")
public class AssetPosition extends GenericEntity
{
    private static final long    serialVersionUID = 1L;
    
    /**账户ID*/
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long                 accountId;
    
    /**证券信息ID*/
    @NotNull(message = "证券信息ID不可为空")
    @ApiModelProperty(value = "证券信息ID", required = true)
    private Long                 stockinfoId;
    
    /**持仓方向*/
    @NotNull(message = "持仓方向不可为空")
    @ApiModelProperty(value = "持仓方向", required = true)
    private String               positionDirection;
    
    /**开仓均价*/
    @NotNull(message = "开仓均价不可为空")
    @ApiModelProperty(value = "开仓均价", required = true)
    private java.math.BigDecimal openAvgPrice;
    
    /**数量*/
    @NotNull(message = "数量不可为空")
    @ApiModelProperty(value = "数量", required = true)
    private java.math.BigDecimal amount;
    
    /**冻结数量*/
    @NotNull(message = "冻结数量不可为空")
    @ApiModelProperty(value = "冻结数量", required = true)
    private java.math.BigDecimal frozenAmount;
    
    /**期间期初数量*/
    @NotNull(message = "期间期初数量不可为空")
    @ApiModelProperty(value = "期间期初数量", required = true)
    private java.math.BigDecimal periodInitAmount;
    
    /**期间流入数量*/
    @NotNull(message = "期间流入数量不可为空")
    @ApiModelProperty(value = "期间流入数量", required = true)
    private java.math.BigDecimal periodInflowAmount;
    
    /**期间流出数量*/
    @NotNull(message = "期间流出数量不可为空")
    @ApiModelProperty(value = "期间流出数量", required = true)
    private java.math.BigDecimal periodOutflowAmount;
    
    /**已实现盈亏*/
    @NotNull(message = "已实现盈亏不可为空")
    @ApiModelProperty(value = "已实现盈亏", required = true)
    private java.math.BigDecimal realProfitLoss;
    
    /**未实现盈亏*/
    @NotNull(message = "未实现盈亏不可为空")
    @ApiModelProperty(value = "未实现盈亏", required = true)
    private java.math.BigDecimal unrealProfitLoss;
    
    /**杠杆倍数*/
    @NotNull(message = "杠杆倍数不可为空")
    @ApiModelProperty(value = "杠杆倍数", required = true)
    private java.math.BigDecimal leverageMultiples;
    
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String               remark;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long                 updateDate;
}
