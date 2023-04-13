/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.entity;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fintech.gams.bean.SignableEntity;
import com.fintech.gams.consts.CharsetConst;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户基础信息 实体对象
 * <p>File：UserInfo.java</p>
 * <p>Title: UserInfo</p>
 * <p>Description:UserInfo</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "用户基础信息")
public class UserInfo extends SignableEntity
{
    private static final long serialVersionUID = 1L;
    
    /**用户名*/
    @NotNull(message = "用户名不可为空")
    @ApiModelProperty(value = "用户名", required = true)
    private String            userName;
    
    /**真实姓名*/
    @ApiModelProperty(value = "真实姓名")
    private String            trueName;
    
    /**密码*/
    @JsonIgnore
    private String            password;
    
    /**性别*/
    @NotNull(message = "性别不可为空")
    @ApiModelProperty(value = "性别:0男，1女")
    private Integer           gender;
    
    /**激活状态*/
    @NotNull(message = "激活状态不可为空")
    @ApiModelProperty(value = "激活状态")
    private Boolean           active;
    
    /**身份证号*/
    @ApiModelProperty(value = "身份证号")
    private String            idCard;
    
    /**头像*/
    @ApiModelProperty(value = "头像")
    private String            logo;
    
    /**描述*/
    @ApiModelProperty(value = "描述")
    private String            dest;
    
    /**职称*/
    @ApiModelProperty(value = "职称")
    private String            jobTitle;
    
    /**所在地址*/
    @ApiModelProperty(value = "所在地址")
    private String            address;
    
    /**GOOGLE验证器私钥*/
    @ApiModelProperty(value = "GOOGLE验证器私钥")
    private String            authKey;
    
    /**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private Long              createDate;
    
    /**修改时间*/
    @ApiModelProperty(value = "修改时间")
    private Long              updateDate;
    
    /** 角色ID**/
    @ApiModelProperty(value = "角色ID")
    private String            roleIds;
    
    /**机构ID*/
    @ApiModelProperty(value = "机构ID")
    private String            orgIds;
    
    private List<RoleInfo>    roleList;
    
    private List<Organize>    organizeList;
    
    @Override
    protected byte[] acquiresSignValue() throws UnsupportedEncodingException
    {
        String sign = new StringBuffer(String.valueOf(id)).append(userName).append(password).append(authKey).toString();
        return sign.getBytes(CharsetConst.CHARSET_UT);
    }
}
