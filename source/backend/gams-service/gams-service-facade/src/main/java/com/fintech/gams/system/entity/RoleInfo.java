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
 * 角色信息 实体对象
 * <p>File：RoleInfo.java</p>
 * <p>Title: RoleInfo</p>
 * <p>Description:RoleInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "角色信息")
public class RoleInfo extends GenericEntity
{
    private static final long serialVersionUID = 1L;

    /**角色编码*/
    @NotNull(message = "角色编码不可为空")
    @ApiModelProperty(value = "角色编码", required = true)
    private String            roleCode;
    
    /**角色名称*/
    @NotNull(message = "角色名称不可为空")
    @ApiModelProperty(value = "角色名称", required = true)
    private String            roleName;
    
    /**角色描述*/
    @ApiModelProperty(value = "角色描述")
    private String            roleDest;

    /**需要GA验证 1需要 0不需要*/
    @ApiModelProperty(value = "需要GA验证 1需要 0不需要")
    private Boolean           needga;
    
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
    
    private List<Resources>   resources;
}
