/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 公司协议纰漏信息 实体对象
 * <p>File：AccountCoAgreement.java</p>
 * <p>Title: AccountCoAgreement</p>
 * <p>Description:AccountCoAgreement</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公司协议纰漏信息")
public class AccountCoAgreement extends GenericEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     */
    @NotNull(message = "账户ID不可为空")
    @ApiModelProperty(value = "账户ID", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long accountId;

    /**
     * 协议文件编号（见共同协议表）
     */
    @ApiModelProperty(value = "协议文件编号（见共同协议表）")
    @Size(max = 256)
    private String agreementNumber;

    /**
     * 账户名
     */
    @NotNull(message = "账户名不可为空")
    @ApiModelProperty(value = "账户名", required = true)
    @Size(max = 32)
    private String accountName;

    /**
     * 签名
     */
    @NotNull(message = "签名不可为空")
    @ApiModelProperty(value = "签名", required = true)
    @Size(max = 128)
    private String signature;

    /**
     * 签名时间
     */
    @ApiModelProperty(value = "签名时间")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long signatureTime;

    /**
     * 状态
     */
    @NotNull(message = "状态(1是true 0是false)不可为空")
    @ApiModelProperty(value = "状态(1是true 0是false)", required = true)
    private Boolean status;

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

