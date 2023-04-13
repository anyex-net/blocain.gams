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
 * Banner信息 实体对象
 * <p>File：Banner.java</p>
 * <p>Title: Banner</p>
 * <p>Description:Banner</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "Banner信息")
public class Banner extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**标题*/
    @NotNull(message = "标题不可为空")
    @ApiModelProperty(value = "标题", required = true)
    private String            title;
    
    /**摘要简介*/
    @NotNull(message = "摘要简介不可为空")
    @ApiModelProperty(value = "摘要简介", required = true)
    private String            summary;
    
    /**图片URL中文版*/
    @NotNull(message = "图片URL中文版不可为空")
    @ApiModelProperty(value = "图片URL中文版", required = true)
    private String            pictUrl;
    
    /**详情链接*/
    @ApiModelProperty(value = "详情链接")
    private String            detailLink;
    
    /**语言标识*/
    @ApiModelProperty(value = "语言标识")
    private String            lang;
    
    /**排序顺序*/
    @ApiModelProperty(value = "排序顺序", required = true)
    private Integer           sortNum;
    
    /**状态(0待发布、1已发布)*/
    @NotNull(message = "状态(0待发布、1已发布)不可为空")
    @ApiModelProperty(value = "状态(0待发布、1已发布)", required = true)
    private Boolean           status;
    
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
