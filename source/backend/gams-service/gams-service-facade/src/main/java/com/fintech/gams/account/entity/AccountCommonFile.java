/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 公共的文件 实体对象
 * <p>File：AccountCommonFile.java</p>
 * <p>Title: AccountCommonFile</p>
 * <p>Description:AccountCommonFile</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "公共的文件")
public class AccountCommonFile extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**文件名*/
	@NotNull(message = "文件名不可为空")
	@ApiModelProperty(value = "文件名", required = true)
	private String fileName;

	/**账户类型*/
	@NotNull(message = "账户类型不可为空")
	@ApiModelProperty(value = "账户类型", required = true)
	private String accountType;

	/**文件路径*/
	@NotNull(message = "文件路径不可为空")
	@ApiModelProperty(value = "文件路径", required = true)
	private String filePath;

	/**对应字典数据的dictValue*/
	@NotNull(message = "对应字典数据的dictValue不可为空")
	@ApiModelProperty(value = "对应字典数据的dictValue", required = true)
	private String dictValue;

	/**语言*/
	@NotNull(message = "语言不可为空")
	@ApiModelProperty(value = "语言", required = true)
	private String lang;

	/**协议种类*/
	@NotNull(message = "协议种类不可为空")
	@ApiModelProperty(value = "协议种类", required = true)
	private Long kind;


}

