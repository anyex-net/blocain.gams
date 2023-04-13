/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.stock.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import com.fintech.gams.quote.entity.QuoteStock;
import com.fintech.gams.system.entity.UserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 证券热门推荐配置 实体对象
 * <p>File：StockHotConfig.java</p>
 * <p>Title: StockHotConfig</p>
 * <p>Description:StockHotConfig</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "证券热门推荐配置")
public class StockHotConfig extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**证券信息ID*/
    @NotNull(message = "证券信息ID不可为空")
    @ApiModelProperty(value = "证券信息ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              stockinfoId;
    
    /**备注*/
    @NotNull(message = "备注不可为空")
    @ApiModelProperty(value = "备注", required = true)
    private String            remark;
    
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
    private StockInfo         stockInfo;

    private UserInfo          createUser;
    
    private UserInfo          updateUser;
}
