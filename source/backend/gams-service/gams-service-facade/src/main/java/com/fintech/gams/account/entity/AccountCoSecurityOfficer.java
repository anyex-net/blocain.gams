/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公司安全官 实体对象
 * <p>File：AccountCoSecurityOfficer.java</p>
 * <p>Title: AccountCoSecurityOfficer</p>
 * <p>Description:AccountCoSecurityOfficer</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公司安全官")
public class AccountCoSecurityOfficer extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**先生，女士，博士，夫人(见数据字典称呼类型)*/
	@NotNull(message = "先生，女士，博士，夫人(见数据字典称呼类型)不可为空")
	@ApiModelProperty(value = "先生，女士，博士，夫人(见数据字典称呼类型)", required = true)
	@Size(max = 32)
	private String appellation;

	/**姓*/
	@NotNull(message = "姓不可为空")
	@ApiModelProperty(value = "姓", required = true)
	@Size(max = 50)
	private String lastName;

	/**中间名*/
	@ApiModelProperty(value = "中间名")
	@Size(max = 50)
	private String middleName;

	/**名*/
	@NotNull(message = "名不可为空")
	@ApiModelProperty(value = "名", required = true)
	@Size(max = 32)
	private String firstName;

	/**后缀*/
	@ApiModelProperty(value = "后缀")
	@Size(max = 32)
	private String suffix;

	/**电子邮件*/
	@Email
	@NotNull(message = "电子邮件不可为空")
	@ApiModelProperty(value = "电子邮件", required = true)
	@Size(max = 64)
	private String email;

	/**实体关系*/
	@ApiModelProperty(value = "实体关系")
	@Size(max = 32)
	private String etityRelationship;

	/**授权人关系*/
	@ApiModelProperty(value = "授权人关系")
	@Size(max = 32)
	private String authorizedRelationship;

	/**用户名(第八页需要)*/
	@ApiModelProperty(value = "用户名(第八页需要)")
	@Size(min = 4, max = 16)
	private String userName;

	/**密码(第八页)*/
	@ApiModelProperty(value = "密码(第八页)")
	@Size(min = 8, max = 16)
	private String password;

	/**创建人*/
	@NotNull(message = "创建人不可为空")
	@ApiModelProperty(value = "创建人", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createdBy;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long createdDate;

	/**更新人*/
	@ApiModelProperty(value = "更新人")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long updatedBy;

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long updatedDate;


}

