/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 字典数据 实体对象
 * <p>File：Dictionary.java</p>
 * <p>Title: Dictionary</p>
 * <p>Description:Dictionary</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "字典数据")
public class Dictionary extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**类型编码*/
    @NotNull(message = "类型编码不可为空")
    @ApiModelProperty(value = "类型编码", required = true)
    private String            dictCode;
    
    /**字典名称*/
    @NotNull(message = "字典名称不可为空")
    @ApiModelProperty(value = "字典名称", required = true)
    private String            dictName;
    
    /**字典键值*/
    @NotNull(message = "字典键值不可为空")
    @ApiModelProperty(value = "字典键值", required = true)
    private String            dictValue;
    
    /**键值描述*/
    @ApiModelProperty(value = "键值描述")
    private String            dictDest;
    
    /**语言（en_US,zh_CN,zh_HK）*/
    @NotNull(message = "语言（en_US,zh_CN,zh_HK）不可为空")
    @ApiModelProperty(value = "语言（en_US,zh_CN,zh_HK）", required = true)
    private String            lang;

    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Long              sortNum;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人")
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
}
