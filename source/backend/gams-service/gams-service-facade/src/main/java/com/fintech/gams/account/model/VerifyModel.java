package com.fintech.gams.account.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 找回密码验证对象
 * <p>File: VerifyModel.java </p>
 * <p>Title: VerifyModel </p>
 * <p>Description: VerifyModel </p>
 * <p>Copyright: Copyright (c) 2019-05-30</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "找回密码验证对象")
public class VerifyModel
{
    @NotNull(message = "Validation type cannot be null")
    @ApiModelProperty(value = "验证类型(0：邮件、1：短信)", required = true)
    private boolean authType;

    @NotNull(message = "Validation objects cannot be null")
    @ApiModelProperty(value = "验证对象", required = true)
    private String  object;

    @NotNull(message = "Verification code cannot be null")
    @ApiModelProperty(value = "验证码", required = true)
    private String  authCode;
}
