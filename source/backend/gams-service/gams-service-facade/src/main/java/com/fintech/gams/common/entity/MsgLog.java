/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.entity;

import javax.validation.constraints.NotNull;

import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 消息发送日志 实体对象
 * <p>File：MsgLog.java</p>
 * <p>Title: MsgLog</p>
 * <p>Description:MsgLog</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消息发送日志")
public class MsgLog extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**消息类型(email:邮件、sms:短信)*/
    @NotNull(message = "消息类型(email:邮件、sms:短信)不可为空")
    @ApiModelProperty(value = "消息类型(email:邮件、sms:短信)", required = true)
    private String            type;
    
    /** 发送对象*/
    @NotNull(message = " 发送对象不可为空")
    @ApiModelProperty(value = " 发送对象", required = true)
    private String            object;
    
    /**内容*/
    @NotNull(message = "内容不可为空")
    @ApiModelProperty(value = "内容", required = true)
    private String            content;
    
    /**发送状态1：成功，0：失败）*/
    @NotNull(message = "发送状态（1：成功，0：失败）不可为空")
    @ApiModelProperty(value = "发送状态1：成功，0：失败）", required = true)
    private Boolean           status;

    /**创建时间*/
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    private Long              createDate;
}
