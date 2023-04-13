/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.entity;

import javax.validation.constraints.NotNull;

import org.apache.oltu.oauth2.common.message.types.GrantType;

import com.fintech.gams.bean.GenericEntity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * CLIENT_DETAILS 实体对象
 * <p>File：ClientDetails.java</p>
 * <p>Title: ClientDetails</p>
 * <p>Description:ClientDetails</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "CLIENT_DETAILS")
public class ClientDetails extends GenericEntity
{
    private static final long serialVersionUID = 1L;
    
    /**clientId*/
    @NotNull(message = "clientId不可为空")
    @ApiModelProperty(value = "clientId", required = true)
    private String            clientId;
    
    /**clientSecret*/
    @NotNull(message = "clientSecret不可为空")
    @ApiModelProperty(value = "clientSecret", required = true)
    private String            clientSecret;
    
    /**clientName*/
    @NotNull(message = "clientName不可为空")
    @ApiModelProperty(value = "clientName", required = true)
    private String            clientName;
    
    /**clientUri*/
    @NotNull(message = "clientUri不可为空")
    @ApiModelProperty(value = "clientUri", required = true)
    private String            clientUri;
    
    /**clientIconUri*/
    @NotNull(message = "clientIconUri不可为空")
    @ApiModelProperty(value = "clientIconUri", required = true)
    private String            clientIconUri;
    
    /**resourceIds*/
    @NotNull(message = "resourceIds不可为空")
    @ApiModelProperty(value = "resourceIds", required = true)
    private String            resourceIds;
    
    /**scope*/
    @NotNull(message = "scope不可为空")
    @ApiModelProperty(value = "scope", required = true)
    private String            scope;
    
    /**grantTypes*/
    @NotNull(message = "grantTypes不可为空")
    @ApiModelProperty(value = "grantTypes", required = true)
    private String            grantTypes;
    
    /**redirectUri*/
    @NotNull(message = "redirectUri不可为空")
    @ApiModelProperty(value = "redirectUri", required = true)
    private String            redirectUri;
    
    /**logoutUri*/
    @NotNull(message = "logoutUri不可为空")
    @ApiModelProperty(value = "logoutUri", required = true)
    private String            logoutUri;
    
    /**accessTokenValidity*/
    @NotNull(message = "accessTokenValidity不可为空")
    @ApiModelProperty(value = "accessTokenValidity", required = true)
    private Integer           accessTokenValidity;
    
    /**refreshTokenValidity*/
    @NotNull(message = "refreshTokenValidity不可为空")
    @ApiModelProperty(value = "refreshTokenValidity", required = true)
    private Integer           refreshTokenValidity;
    
    /**description*/
    @NotNull(message = "description不可为空")
    @ApiModelProperty(value = "description", required = true)
    private String            description;
    
    /**createTime*/
    @NotNull(message = "createTime不可为空")
    @ApiModelProperty(value = "createTime", required = true)
    private Long              createTime;
    
    /**trusted*/
    @NotNull(message = "trusted不可为空")
    @ApiModelProperty(value = "trusted", required = true)
    private Boolean           trusted;
    
    public boolean supportRefreshToken()
    {
        return this.grantTypes != null && this.grantTypes.contains(GrantType.REFRESH_TOKEN.toString());
    }
}
