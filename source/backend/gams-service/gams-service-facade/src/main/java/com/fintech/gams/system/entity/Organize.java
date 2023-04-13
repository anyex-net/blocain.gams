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
 * 组织机构信息 实体对象
 * <p>File：Organize.java</p>
 * <p>Title: Organize</p>
 * <p>Description:Organize</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "组织机构信息")
public class Organize extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**上级编号*/
    @ApiModelProperty(value = "上级编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              parentId;
    
    /**层级信息*/
    @ApiModelProperty(value = "层级信息")
    private String            pathInfo;
    
    /**机构编码*/
    @NotNull(message = "机构编码不可为空")
    @ApiModelProperty(value = "机构编码", required = true)
    private String            orgCode;
    
    /**机构名称*/
    @NotNull(message = "机构名称不可为空")
    @ApiModelProperty(value = "机构名称", required = true)
    private String            orgName;
    
    /**机构描述*/
    @ApiModelProperty(value = "机构描述")
    private String            orgDest;

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
    
    private List<Organize>    children;
}
