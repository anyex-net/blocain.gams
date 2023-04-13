/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 合伙企业经济商信息 实体对象
 * <p>File：AccountParBrokerInfo.java</p>
 * <p>Title: AccountParBrokerInfo</p>
 * <p>Description:AccountParBrokerInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业经济商监信息")
public class AccountParBrokerInfo extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**
     * 你的组织是否为期权清算公司(OCC)的会员？
     */
    @NotNull(message = "你的组织是否为期权清算公司(OCC)的会员？不可为空")
    @ApiModelProperty(value = "你的组织是否为期权清算公司(OCC)的会员？", required = true)
    private Boolean status;

    /**
     * OCC会员号码
     */
    @ApiModelProperty(value = "OCC会员号码")
    @Size(max = 256)
    private String occMemberNumber;

    /**
     * 监管机构
     */
    @ApiModelProperty(value = "监管机构")
    @Size(max = 64)
    private String regulator;

    /**
     * 监管机构信息
     */
    @ApiModelProperty(value = "监管机构信息")
    @Size(max = 128)
    private String regulatorInfo;

    /**
     * 注册号码
     */
    @ApiModelProperty(value = "注册号码")
    @Size(max = 64)
    private String registerNumber;

    /**
     * 注册职位
     */
    @ApiModelProperty(value = "注册职位")
    @Size(max = 128)
    private String registerPosition;

    /**
     * 创建人
     */
    @NotNull(message = "创建人不可为空")
    @ApiModelProperty(value = "创建人", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdBy;

    /**
     * 创建时间
     */
    @NotNull(message = "创建时间不可为空")
    @ApiModelProperty(value = "创建时间", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long createdDate;

    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long updatedDate;


}

