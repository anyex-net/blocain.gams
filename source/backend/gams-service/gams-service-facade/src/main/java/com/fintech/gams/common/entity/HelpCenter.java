/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fintech.gams.common.model.HelpCenterTreeModel;
import com.fintech.gams.system.entity.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 帮助中心 实体对象
 * <p>File：HelpCenter.java</p>
 * <p>Title: HelpCenter</p>
 * <p>Description:HelpCenter</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "帮助中心")
public class HelpCenter extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**问题(一级)*/
	@NotNull(message = "问题(一级)不可为空")
	@ApiModelProperty(value = "问题(一级)", required = true)
	@Size(max = 128)
	private String problemLevelOne;

	/**问题(二级)*/
	@NotNull(message = "问题(二级)不可为空")
	@ApiModelProperty(value = "问题(二级)", required = true)
	@Size(max = 120)
	private String problemLevelTwo;

	/**问题类型(开户类，交易类..)*/
	@NotNull(message = "问题类型(开户类，交易类..)不可为空")
	@ApiModelProperty(value = "问题类型(开户类，交易类..)", required = true)
	@Size(max = 64)
	private String problemType;

	/**问题解答*/
	@NotNull(message = "问题解答不可为空")
	@ApiModelProperty(value = "问题解答", required = true)
	@Size(max = 4000)
	private String problemSolving;

	/**搜索问题解答内容（纯文本）*/
	@ApiModelProperty(value = "搜索问题解答内容（纯文本）")
	@Size(max = 4000)
	private String searchContent;

	/**图片url*/
	@ApiModelProperty(value = "图片url")
	@Size(max = 512)
	private String pictUrl;

	/**状态(0待发布、1已发布、2已下架)*/
	@NotNull(message = "状态(0待发布、1已发布、2已下架)不可为空")
	@ApiModelProperty(value = "状态(0待发布、1已发布、2已下架)", required = true)
	private Integer status;

	/**语言(en_US,zh_CN,zh_HK)*/
	@ApiModelProperty(value = "语言(en_US,zh_CN,zh_HK)")
	@Size(max = 8)
	private String lang;

	/**是否热门(0否、1是)*/
	@NotNull(message = "是否热门(0否、1是)不可为空")
	@ApiModelProperty(value = "是否热门(0否、1是)", required = true)
	private Boolean isHot;

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

	/**更新时间*/
	@ApiModelProperty(value = "更新时间")
	private Long updateDate;

	/*******************查询参数********************/
	@ApiModelProperty(value = "发布开始时间")
	private Long				 createDateStart;

	@ApiModelProperty(value = "发布结束时间")
	private Long				 createDateEnd;

	/*******************关联查询对象********************/
	@ApiModelProperty(value = "帮助中心分类菜单栏结果集",hidden = true)
	private HelpCenterTreeModel  parent;

	@ApiModelProperty(value = "问题(一级)字典表数据",hidden = true)
	private Dictionary  problemLevelOneInfo;

	private UserInfo    createUser;
}

