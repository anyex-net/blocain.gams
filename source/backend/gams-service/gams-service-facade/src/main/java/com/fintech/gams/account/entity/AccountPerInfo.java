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
 * 个人信息 实体对象
 * <p>File：AccountPerInfo.java</p>
 * <p>Title: AccountPerInfo</p>
 * <p>Description:AccountPerInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人信息")
public class AccountPerInfo extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**个人类型（见字典数据个人类型）*/
	@ApiModelProperty(value = "个人类型（见字典数据个人类型）")
	@Size(max = 32)
	private String personalType;

	/**先生，女士，博士，夫人(见数据字典数据称呼类型)*/
	@NotNull(message = "先生，女士，博士，夫人(见数据字典数据称呼类型)不可为空")
	@ApiModelProperty(value = "先生，女士，博士，夫人(见数据字典数据称呼类型)", required = true)
	@Size(max = 32)
	private String appellation;

	/**姓*/
	@NotNull(message = "姓不可为空")
	@ApiModelProperty(value = "姓", required = true)
	@Size(max = 50)
	private String lastName;

	/**名*/
	@NotNull(message = "名不可为空")
	@ApiModelProperty(value = "名", required = true)
	@Size(max = 32)
	private String firstName;

	/**生日*/
	@ApiModelProperty(value = "生日")
	private String birthday;

	/**性别(见数据字典数据性别类型)*/
	@NotNull(message = "性别(见数据字典数据性别类型)不可为空")
	@ApiModelProperty(value = "性别(见数据字典数据性别类型)", required = true)
	@Size(max = 32)
	private String gender;

	/**婚姻状况(见数据字典数据婚姻状况)*/
	@NotNull(message = "婚姻状况(见数据字典数据婚姻状况)不可为空")
	@ApiModelProperty(value = "婚姻状况(见数据字典数据婚姻状况)", required = true)
	@Size(max = 32)
	private String maritalStatus;

	/**家庭成员数目(见数据字典数据家庭成员数)*/
	@NotNull(message = "家庭成员数目(见数据字典数据家庭成员数)不可为空")
	@ApiModelProperty(value = "家庭成员数目(见数据字典数据家庭成员数)", required = true)
	@Size(max = 32)
	private String numberOfFamilyMembers;

	/**其它国籍(见字数据国家类型)*/
	@ApiModelProperty(value = "其它国籍(见字数据国家类型)")
	@Size(max = 32)
	private String otherNationality;

	/**其它出生国家(见字数据国家类型)*/
	@ApiModelProperty(value = "其它出生国家(见字数据国家类型)")
	@Size(max = 32)
	private String otherCountriesOfBirth;

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
