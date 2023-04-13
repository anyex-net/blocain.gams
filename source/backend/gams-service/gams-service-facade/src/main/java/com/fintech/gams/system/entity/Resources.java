/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.entity;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 资源菜单信息 实体对象
 * <p>File：Resources.java</p>
 * <p>Title: Resources</p>
 * <p>Description:Resources</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "资源菜单信息")
public class Resources extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**上级编号*/
    @ApiModelProperty(value = "上级编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              parentId;
    
    /**资源编码*/
    @NotNull(message = "资源编码不可为空")
    @ApiModelProperty(value = "资源编码", required = true)
    private String            resCode;
    
    /**资源名称*/
    @NotNull(message = "资源名称不可为空")
    @ApiModelProperty(value = "资源名称", required = true)
    private String            resName;
    
    /**资源描述*/
    @ApiModelProperty(value = "资源描述")
    private String            resDest;
    
    /**类型（菜单、权限）*/
    @NotNull(message = "类型不可为空")
    @ApiModelProperty(value = "类型（菜单、权限）")
    private Boolean           resType;
    
    /**图标*/
    @ApiModelProperty(value = "图标")
    private String            resIcon;
    
    /**资源地址*/
    @NotNull(message = "资源地址不可为空")
    @ApiModelProperty(value = "资源地址", required = true)
    private String            resUrl;

    /**排序号*/
    @ApiModelProperty(value = "排序号")
    private Long              sortNum;

    /**创建人*/
    @ApiModelProperty(value = "创建人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              createBy;

    private String            createByName;

    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;

    /**修改人*/
    @ApiModelProperty(value = "修改人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              updateBy;

    private String            updateByName;

    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long              updateDate;
    
    private List<Resources>   children;
}
