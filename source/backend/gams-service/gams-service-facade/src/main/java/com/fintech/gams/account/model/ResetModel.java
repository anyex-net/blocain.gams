package com.fintech.gams.account.model;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 重置密码对象
 * <p>File: ResetModel.java </p>
 * <p>Title: ResetModel </p>
 * <p>Description: ResetModel </p>
 * <p>Copyright: Copyright (c) 2019-05-30</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@ApiModel(description = "重置密码对象")
public class ResetModel
{
    @NotNull(message = "新密码不可为空")
    @ApiModelProperty(value = "新密码", required = true)
    private String newPwd;
    
    @NotNull(message = "验证码不可为空")
    @ApiModelProperty(value = "验证码", required = true)
    private String authCode;
}
