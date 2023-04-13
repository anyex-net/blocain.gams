/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.entity;

import java.io.UnsupportedEncodingException;
import java.util.StringJoiner;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fintech.gams.bean.SignableEntity;
import com.fintech.gams.consts.CharsetConst;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 账户表 实体对象
 * <p>File：Account.java</p>
 * <p>Title: Account</p>
 * <p>Description:Account</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "账户表")
public class Account extends SignableEntity
{
    private static final long serialVersionUID = 1L;
    
    /**账号编号*/
    // @NotNull(message = "账号编号不可为空")
    // @ApiModelProperty(value = "账号编号", required = true)
    @ApiModelProperty(value = "账号编号")
    private java.lang.Long    unid;
    
    /**账户类型(数据字典)*/
    @NotNull(message = "账户类型(数据字典)不可为空")
    @ApiModelProperty(value = "账户类型(数据字典)", required = true)
    private java.lang.String  accountType;
    
    /**账户名*/
    @NotNull(message = "账户名不可为空")
    @ApiModelProperty(value = "账户名", required = true)
    @Size(min = 4, max = 16)
    private java.lang.String  accountName;
    
    /**帐户昵称*/
    @ApiModelProperty(value = "帐户昵称")
    private java.lang.String  nickName;
    
    /**邮箱*/
    @Email
    @NotNull(message = "邮箱不可为空")
    @Size(max = 64)
    @ApiModelProperty(value = "邮箱", required = true)
    private java.lang.String  email;
    
    /**手机号*/
    @ApiModelProperty(value = "手机号")
    @Size(min = 6, max = 11)
    private java.lang.String  mobile;
    
    /**注册所在地或所在IP*/
    @ApiModelProperty(value = "注册所在地或所在IP")
    private java.lang.String  location;
    
    /**国家地区(区域表)*/
    @NotNull(message = "国家地区(区域表)不可为空")
    @ApiModelProperty(value = "国家地区(区域表)", required = true)
    private java.lang.String  country;

    /**国家代码*/
    @NotNull(message = "国家代码不可为空")
    @ApiModelProperty(value = "国家代码不可为空",required = true)
    private java.lang.String  region;
    
    /**合法居住国家地区(区域表)*/
    @ApiModelProperty(value = "合法居住国家地区(区域表)")
    private java.lang.String  nationality;
    
    /**语言标识*/
    @ApiModelProperty(value = "语言标识")
    private java.lang.String  lang;
    
    /**登录密码*/
    @NotNull(message = "登录密码不可为空")
    @ApiModelProperty(value = "登录密码", required = true)
    @Size(min = 8, max = 16)
    private java.lang.String  loginPwd;
    
    /**资金密码*/
    @ApiModelProperty(value = "资金密码")
    private java.lang.String  fundPwd;
    
    /**账户状态(0正常、1冻结、2注销)*/
    // @NotNull(message = "账户状态(0正常、1冻结、2注销)不可为空")
    // @ApiModelProperty(value = "账户状态(0正常、1冻结、2注销)", required = true)
    @ApiModelProperty(value = "账户状态(0正常、1冻结、2注销)")
    private java.lang.Integer status;
    
    /**ga私钥*/
    @ApiModelProperty(value = "ga私钥")
    private java.lang.String  authKey;
    
    /**安全验证策略*/
    // @NotNull(message = "安全验证策略不可为空")
    // @ApiModelProperty(value = "安全验证策略", required = true)
    @ApiModelProperty(value = "安全验证策略")
    private java.lang.Integer securityPolicy;
    
    /**交易验证策略*/
    // @NotNull(message = "交易验证策略不可为空")
    // @ApiModelProperty(value = "交易验证策略", required = true)
    @ApiModelProperty(value = "交易验证策略")
    private java.lang.Integer tradePolicy;
    
    /**激活码*/
    @ApiModelProperty(value = "激活码")
    private java.lang.String  activationCode;
    
    /**解冻时间*/
    @ApiModelProperty(value = "解冻时间")
    private java.lang.Long    unforzenDate;
    
    /**注册开户状态(0 表示开户ok 待审核, 1 表示开户OK 并且审核通过,注册过程 写负数(页数))*/
    @ApiModelProperty(value = "注册开户状态(0 表示开户ok 未审核, 1 表示开户OK 并且审核通过,注册过程 写负数(页数))")
    private java.lang.Integer registerStatus;
    
    /**备注*/
    @ApiModelProperty(value = "备注")
    private java.lang.String  remark;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private java.lang.Long    createDate;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private java.lang.Long    updateDate;
    
    @Override
    protected byte[] acquiresSignValue() throws UnsupportedEncodingException
    {
        StringBuffer signValue = new StringBuffer(String.valueOf(this.id));
        signValue.append(this.unid).append(this.accountName);
        signValue.append(this.loginPwd).append(this.fundPwd);
        signValue.append(this.location).append(this.email);
        return signValue.toString().getBytes(CharsetConst.CHARSET_UT);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("unid=" + unid)
                .add("accountType=" + accountType)
                .add("accountName='" + accountName + "'")
                .add("nickName='" + nickName + "'")
                .add("email='" + email + "'")
                .add("mobile='" + mobile + "'")
                .add("location='" + location + "'")
                .add("country='" + country + "'")
                .add("nationality='" + nationality + "'")
                .add("lang='" + lang + "'")
                .add("loginPwd='" + loginPwd + "'")
                .add("fundPwd='" + fundPwd + "'")
                .add("status=" + status)
                .add("authKey='" + authKey + "'")
                .add("securityPolicy=" + securityPolicy)
                .add("tradePolicy=" + tradePolicy)
                .add("activationCode='" + activationCode + "'")
                .add("unforzenDate=" + unforzenDate)
                .add("registerStatus="+registerStatus)
                .add("remark='" + remark + "'")
                .add("createDate=" + createDate)
                .add("updateDate=" + updateDate)
                .add("version=" + version)
                .toString();
    }
}
