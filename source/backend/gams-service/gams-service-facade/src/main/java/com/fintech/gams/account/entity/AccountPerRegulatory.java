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
 * 个人账户规管信息 实体对象
 * <p>File：AccountPerRegulatory.java</p>
 * <p>Title: AccountPerRegulatory</p>
 * <p>Description:AccountPerRegulatory</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "个人账户规管信息")
public class AccountPerRegulatory extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**雇主*/
	@ApiModelProperty(value = "雇主")
	@Size(max = 1024)
	private String employer;

	/**账户持有人关系*/
	@ApiModelProperty(value = "账户持有人关系")
	@Size(max = 32)
	private String relationshipAccountHolders;

	/**与公司有关联的家庭成员*/
	@ApiModelProperty(value = "与公司有关联的家庭成员")
	@Size(max = 32)
	private String familyMembersCompany;

	/**账户持有人公司股票代码*/
	@ApiModelProperty(value = "账户持有人公司股票代码")
	@Size(max = 300)
	private String accountHolderCompanyStock;

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

