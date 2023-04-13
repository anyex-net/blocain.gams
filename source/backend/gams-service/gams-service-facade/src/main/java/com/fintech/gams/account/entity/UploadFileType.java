/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 上传文件类型 实体对象
 * <p>File：UploadFileType.java</p>
 * <p>Title: UploadFileType</p>
 * <p>Description:UploadFileType</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "上传文件类型")
public class UploadFileType extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**个人股东法人股东的id*/
	@ApiModelProperty(value = "个人股东法人股东的id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long coPersonalId;

	/**类型key*/
	@NotNull(message = "类型key不可为空")
	@ApiModelProperty(value = "类型key", required = true)
	private String typeKey;

	/**类型*/
	@NotNull(message = "类型不可为空")
	@ApiModelProperty(value = "类型", required = true)
	private String type;

	/**语言编码（en_US,zh_CN,zh_HK）*/
	@NotNull(message = "语言编码（en_US,zh_CN,zh_HK）不可为空")
	@ApiModelProperty(value = "语言编码（en_US,zh_CN,zh_HK）", required = true)
	private String lang;

	/**类型内容*/
	@NotNull(message = "类型内容不可为空")
	@ApiModelProperty(value = "类型内容", required = true)
	private String content;

	/**描述*/
	@NotNull(message = "描述不可为空")
	@ApiModelProperty(value = "描述", required = true)
	private String dest;

	/**更多信息的PDF文件*/
	@NotNull(message = "更多信息的PDF文件不可为空")
	@ApiModelProperty(value = "更多信息的PDF文件", required = true)
	private String moreInfoPdf;

	/**帐户类型*/
	@NotNull(message = "帐户类型不可为空")
	@ApiModelProperty(value = "帐户类型", required = true)
	private String accountType;

	/**文件种类*/
	@NotNull(message = "文件种类不可为空")
	@ApiModelProperty(value = "文件种类", required = true)
	private Long kind;


}

