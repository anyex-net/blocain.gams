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
 * 合伙企业AML/CTF风险问卷 实体对象
 * <p>File：AccountParAmlCtf.java</p>
 * <p>Title: AccountParAmlCtf</p>
 * <p>Description:AccountParAmlCtf</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "合伙企业AML/CTF风险问卷")
public class AccountParAmlCtf extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private Long accountId;

	/**问题（获取字典数据合伙企业aml）*/
	@NotNull(message = "问题（获取字典数据aml问卷）不可为空")
	@ApiModelProperty(value = "问题（获取字典数据aml问卷）", required = true)
	@Size(max = 256)
	private String issue;

	/**状态 0否 1是*/
	@NotNull(message = "状态 0否 1是不可为空")
	@ApiModelProperty(value = "状态 0否 1是", required = true)
	private Boolean status;

	/**补充信息（选了 是 需要填写）*/
	@ApiModelProperty(value = "补充信息（选了 是 需要填写）")
	@Size(max = 256)
	private String supplementaryInfo;

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

