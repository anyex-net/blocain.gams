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
import lombok.NoArgsConstructor;

/**
 * 数据权限信息 实体对象
 * <p>File：UserData.java</p>
 * <p>Title: UserData</p>
 * <p>Description:UserData</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "数据权限信息")
public class UserData extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**用户ID*/
    @NotNull(message = "用户ID不可为空")
    @ApiModelProperty(value = "用户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              userId;
    
    /**机构ID*/
    @NotNull(message = "机构ID不可为空")
    @ApiModelProperty(value = "机构ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              orgId;
    
    public UserData(@NotNull(message = "用户ID不可为空") Long userId)
    {
        this.userId = userId;
    }
}
