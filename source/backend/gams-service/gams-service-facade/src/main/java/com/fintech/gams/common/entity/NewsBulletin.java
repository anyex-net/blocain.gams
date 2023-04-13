/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.entity;

import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 快讯信息 实体对象
 * <p>File：NewsBulletin.java</p>
 * <p>Title: NewsBulletin</p>
 * <p>Description:NewsBulletin</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "消息公告")
public class NewsBulletin extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**标题*/
	@NotNull(message = "标题不可为空")
	@ApiModelProperty(value = "标题", required = true)
	@Size(max = 120)
	private String title;

	/**类型(公告）*/
	@NotNull(message = "类型(公告）不可为空")
	@ApiModelProperty(value = "类型(公告）", required = true)
	@Size(max = 16)
	private String type;

	/**内容*/
	@NotNull(message = "内容不可为空")
	@ApiModelProperty(value = "内容", required = true)
	@Size(max = 4000)
	private String content;

	/**语言(en_US,zh_CN,zh_HK)*/
	@NotNull(message = "语言(en_US,zh_CN,zh_HK)不可为空")
	@ApiModelProperty(value = "语言(en_US,zh_CN,zh_HK)", required = true)
	@Size(max = 8)
	private String lang;

	/**状态(0待发布、1已发布、2已下架)*/
	@NotNull(message = "状态(0待发布、1已发布、2已下架)不可为空")
	@ApiModelProperty(value = "状态(0待发布、1已发布、2已下架)", required = true)
	private Integer status;

	/**是否定时任务*/
	@NotNull(message = "是否定时任务不可为空")
	@ApiModelProperty(value = "是否定时任务", required = true)
	private Boolean isTimedJob;

	/**创建人*/
	@NotNull(message = "创建人不可为空")
	@ApiModelProperty(value = "创建人", required = true)
	private Long createBy;

	/**创建时间*/
	@NotNull(message = "创建时间不可为空")
	@ApiModelProperty(value = "创建时间", required = true)
	private Long createDate;

	/**修改人*/
	@ApiModelProperty(value = "修改人")
	private Long updateBy;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private Long updateDate;

	/**定时时间*/
	@ApiModelProperty(value = "定时时间")
	private Long cron;

	/**客户端类型（发布位置）*/
	@NotNull(message = "客户端类型（发布位置）不可为空")
	@ApiModelProperty(value = "客户端类型（发布位置）", required = true)
	private String clientType;

	/**发布时间*/
	@ApiModelProperty(value = "发布时间")
	private Long releaseDate;


}

