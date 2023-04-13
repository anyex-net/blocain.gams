package com.fintech.gams.common.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * @Author: hcj
 * @Date: 2019/11/14 15:42
 * @Description 点赞或取消点赞快讯信息对象
 */
@Data
@ApiModel(description = "点赞或取消点赞快讯信息对象")
public class ThumbsUpModel {

    @NotNull(message = "快讯信息ID不可为空")
    @ApiModelProperty(value = "快讯信息ID", required = true)
    private Long id;

    @NotNull(message = "验证类型不可为空")
    @ApiModelProperty(value = "验证类型(0：取消点赞、1：点赞)", required = true)
    private Boolean thumbsUpType;
}
