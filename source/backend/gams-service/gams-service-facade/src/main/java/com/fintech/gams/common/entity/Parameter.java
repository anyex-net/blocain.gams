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
 * 参数表 实体对象
 * <p>File：Parameter.java</p>
 * <p>Title: Parameter</p>
 * <p>Description:Parameter</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "参数表")
public class Parameter extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**系统名称*/
    @NotNull(message = "系统名称不可为空")
    @ApiModelProperty(value = "系统名称", required = true)
    private String            systemName;
    
    /**参数名称*/
    @NotNull(message = "参数名称不可为空")
    @ApiModelProperty(value = "参数名称", required = true)
    private String            parameterName;
    
    /**参数描述*/
    @NotNull(message = "参数描述不可为空")
    @ApiModelProperty(value = "参数描述", required = true)
    private String            describe;
    
    /**参数大类*/
    @NotNull(message = "参数大类不可为空")
    @ApiModelProperty(value = "参数大类", required = true)
    private String            division;
    
    /**参数类型(文本、单选、多选)*/
    @NotNull(message = "参数类型(文本、单选、多选)不可为空")
    @ApiModelProperty(value = "参数类型(文本、单选、多选)", required = true)
    private String            type;
    
    /**参数值值域*/
    @ApiModelProperty(value = "参数值值域")
    private String            valueBound;
    
    /** 参数值*/
    @NotNull(message = " 参数值不可为空")
    @ApiModelProperty(value = " 参数值", required = true)
    private String            value;
    
    /**备注*/
    @ApiModelProperty(value = "备注")
    private String            remark;
    
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
