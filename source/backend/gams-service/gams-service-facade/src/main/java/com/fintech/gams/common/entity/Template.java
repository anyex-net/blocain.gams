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
 * 消息模版 实体对象
 * <p>File：Template.java</p>
 * <p>Title: Template</p>
 * <p>Description:Template</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消息模版")
public class Template extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**模版KEY*/
    @NotNull(message = "模版KEY不可为空")
    @ApiModelProperty(value = "模版KEY", required = true)
    private String            tplKey;
    
    /**语言编码（en_US,zh_CN,zh_HK)*/
    @ApiModelProperty(value = "语言编码（en_US,zh_CN,zh_HK)")
    private String            lang;
    
    /**模版类型(email:邮件、sms:短信)*/
    @NotNull(message = "模版类型(email:邮件、sms:短信)不可为空")
    @ApiModelProperty(value = "模版类型(email:邮件、sms:短信)", required = true)
    private String            type;
    
    /**标题（邮件用）*/
    @NotNull(message = "标题（邮件用）不可为空")
    @ApiModelProperty(value = "标题（邮件用）", required = true)
    private String            title;
    
    /**模版内容*/
    @NotNull(message = "模版内容不可为空")
    @ApiModelProperty(value = "模版内容", required = true)
    private String            content;
    
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            dest;
    
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long              createBy;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;
}
