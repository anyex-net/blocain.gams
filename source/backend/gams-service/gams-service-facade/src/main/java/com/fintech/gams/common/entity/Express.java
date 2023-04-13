/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.entity;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fintech.gams.system.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 快讯信息 实体对象
 * <p>File：Express.java</p>
 * <p>Title: Express</p>
 * <p>Description:Express</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "快讯信息")
public class Express extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**标题*/
	@NotNull(message = "标题不可为空")
	@ApiModelProperty(value = "标题", required = true)
    @Size(max = 120)
	private String title;

	/**类型(资讯、热门、新闻)*/
	@NotNull(message = "类型(资讯、热门、新闻)不可为空")
	@ApiModelProperty(value = "类型(资讯、热门、新闻)", required = true)
    @Size(max = 16)
	private String type;

	/**来源*/
	@ApiModelProperty(value = "来源")
    @Size(max = 30)
	private String source;

	/**摘要*/
	@NotNull(message = "摘要不可为空")
	@ApiModelProperty(value = "摘要", required = true)
    @Size(max = 120)
	private String summary;

	/**图片url*/
	@ApiModelProperty(value = "图片url")
    @Size(max = 512)
	private String pictUrl;

	/**内容*/
	@NotNull(message = "内容不可为空")
	@ApiModelProperty(value = "内容", required = true)
	@Size(max = 4000)
	private String content;

	/**语言(en_US,zh_CN,zh_HK)*/
	@ApiModelProperty(value = "语言(en_US,zh_CN,zh_HK)")
    @Size(max = 8)
	private String lang;

	/**状态(0待发布、1已发布、2已下架)*/
	@NotNull(message = "状态(0待发布、1已发布、2已下架)不可为空")
	@ApiModelProperty(value = "状态(0待发布、1已发布、2已下架)", required = true)
	private Integer status;

	/**是否置顶(0否、1是)*/
	@NotNull(message = "是否置顶(0否、1是)不可为空")
	@ApiModelProperty(value = "是否置顶(0否、1是)", required = true)
	private Integer isTop;

	/**阅读量*/
	@ApiModelProperty(value = "阅读量")
    @JsonSerialize(using = ToStringSerializer.class)
	private Long readingQuantity;

	/**点赞数*/
	@ApiModelProperty(value = "点赞数")
    @JsonSerialize(using = ToStringSerializer.class)
	private Long thumbsUp;

	/**创建人*/
	@ApiModelProperty(value = "创建人")
    @JsonSerialize(using = ToStringSerializer.class)
	private Long createBy;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private Long createDate;

	/**修改人*/
	@ApiModelProperty(value = "修改人")
    @JsonSerialize(using = ToStringSerializer.class)
	private Long updateBy;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private Long updateDate;

	/*******************查询参数********************/
	@ApiModelProperty(value = "发布开始时间")
	private Long				 createDateStart;

	@ApiModelProperty(value = "发布结束时间")
	private Long				 createDateEnd;

	/*******************关联查询对象********************/
	private UserInfo             createUser;


}
