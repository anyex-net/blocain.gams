/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色权限信息 实体对象
 * <p>File：RoleRes.java</p>
 * <p>Title: RoleRes</p>
 * <p>Description:RoleRes</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "角色权限信息")
public class RoleRes extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**角色ID*/
    @NotNull(message = "角色ID不可为空")
    @ApiModelProperty(value = "角色ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              roleId;
    
    /**资源ID*/
    @NotNull(message = "资源ID不可为空")
    @ApiModelProperty(value = "资源ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              resId;
}
