package com.fintech.gams.account.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: hcj
 * @Date: 2019/6/16 19:19
 * @Description  验证账户名/手机号/邮箱是否重复
 */
@Data
@ApiModel(description = "验证账户名/手机号/邮箱是否重复")
public class CheckDuplicateNameModel {

    @NotNull(message = "类型（账户名accountName, 手机号mobile, 邮箱email）不可为空")
    @ApiModelProperty(value = "类型（账户名accountName, 手机号mobile, 邮箱email）不可为空", required = true)
    private java.lang.String type;

    @NotNull(message = "值（手机号，账户名，邮箱）不可为空")
    @ApiModelProperty(value = "值（手机号，账户名，邮箱）不可为空", required = true)
    private java.lang.String value;

    @ApiModelProperty(value = "国家代码（中国86）")
    private java.lang.String region;

    @NotNull(message = "语言（en_US,zh_CN,zh_HK）不可为空")
    @ApiModelProperty(value = "语言（en_US,zh_CN,zh_HK）")
    private java.lang.String lang;
}
