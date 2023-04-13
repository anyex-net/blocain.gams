/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.entity;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户日志 实体对象
 * <p>File：UserLog.java</p>
 * <p>Title: UserLog</p>
 * <p>Description:UserLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户日志")
public class UserLog extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**用户ID*/
    @NotNull(message = "用户ID不可为空")
    @ApiModelProperty(value = "用户ID", required = true)
    private java.lang.Long    userId;
    
    /**用户名称*/
    @ApiModelProperty(value = "用户名称")
    private java.lang.String  userName;
    
    /**来访IP*/
    @ApiModelProperty(value = "来访IP")
    private java.lang.String  ipAddr;
    
    /**请求地址*/
    @ApiModelProperty(value = "请求地址")
    private java.lang.String  url;
    
    /**操作说明*/
    @ApiModelProperty(value = "操作说明")
    private java.lang.String  content;
    
    /**执行时间*/
    @ApiModelProperty(value = "执行时间")
    private BigDecimal        runTime;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private java.lang.Long    createDate;
    
    public UserLog()
    {
    }
    
    public UserLog(@NotNull(message = "用户ID不可为空") Long userId)
    {
        this.userId = userId;
    }
}
