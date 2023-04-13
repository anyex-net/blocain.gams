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
 * FEEDBACK 实体对象
 * <p>File：Feedback.java</p>
 * <p>Title: Feedback</p>
 * <p>Description:Feedback</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "FEEDBACK")
public class Feedback extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**1.离线反馈,2.在线客服问题*/
	@NotNull(message = "1.离线反馈,2.在线客服问题不可为空")
	@ApiModelProperty(value = "1.离线反馈,2.在线客服问题", required = true)
	private String type;

	/**语言(en_US,zh_CN)*/
	@NotNull(message = "语言(en_US,zh_CN)不可为空")
	@ApiModelProperty(value = "语言(en_US,zh_CN)", required = true)
	private String lang;

	/**状态(0待解决、1已解决)*/
	@NotNull(message = "状态(0待解决、1已解决)不可为空")
	@ApiModelProperty(value = "状态(0待解决、1已解决)", required = true)
	private Byte status;

	/**姓名*/
	@ApiModelProperty(value = "姓名")
	private String name;

	/**邮箱*/
	@ApiModelProperty(value = "邮箱")
	private String email;

	/**手机号*/
	@ApiModelProperty(value = "手机号")
	private String mobile;

	/**回答*/
	@ApiModelProperty(value = "回答")
	@Size(max = 1024)
	private String answer;

	/**反馈内容*/
	@NotNull(message = "反馈内容不可为空")
	@ApiModelProperty(value = "反馈内容", required = true)
	@Size(max = 1024)
	private String content;

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


}

