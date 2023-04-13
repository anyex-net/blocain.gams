/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.oauth2.entity;

import javax.validation.constraints.NotNull;

import com.fintech.gams.bean.GenericEntity;
import com.fintech.gams.consts.GlobalConst;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ACCESS_TOKEN 实体对象
 * <p>File：AccessToken.java</p>
 * <p>Title: AccessToken</p>
 * <p>Description:AccessToken</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "ACCESS_TOKEN")
public class AccessToken extends GenericEntity
{
    private static final long   serialVersionUID           = 1L;
    
    protected static final long THOUSAND                   = 1000L;
    
    /**createTime*/
    @NotNull(message = "createTime不可为空")
    @ApiModelProperty(value = "createTime", required = true)
    private Long                createTime;
    
    /**tokenId*/
    @NotNull(message = "tokenId不可为空")
    @ApiModelProperty(value = "tokenId", required = true)
    private String              tokenId;
    
    /**authenticationId*/
    @NotNull(message = "authenticationId不可为空")
    @ApiModelProperty(value = "authenticationId", required = true)
    private String              authenticationId;
    
    /**userName*/
    @NotNull(message = "userName不可为空")
    @ApiModelProperty(value = "userName", required = true)
    private String              userName;
    
    /**clientId*/
    @NotNull(message = "clientId不可为空")
    @ApiModelProperty(value = "clientId", required = true)
    private String              clientId;
    
    /**tokenType*/
    @NotNull(message = "tokenType不可为空")
    @ApiModelProperty(value = "tokenType", required = true)
    private String              tokenType                  = GlobalConst.BEARER_TYPE;
    
    /**refreshToken*/
    @NotNull(message = "refreshToken不可为空")
    @ApiModelProperty(value = "refreshToken", required = true)
    private String              refreshToken;
    
    /**tokenExpiredSeconds*/
    @NotNull(message = "tokenExpiredSeconds不可为空")
    @ApiModelProperty(value = "tokenExpiredSeconds", required = true)
    private Integer             tokenExpiredSeconds        = GlobalConst.ACCESS_TOKEN_VALIDITY_SECONDS;
    
    /**refreshTokenExpiredSeconds*/
    @NotNull(message = "refreshTokenExpiredSeconds不可为空")
    @ApiModelProperty(value = "refreshTokenExpiredSeconds", required = true)
    private Integer             refreshTokenExpiredSeconds = GlobalConst.REFRESH_TOKEN_VALIDITY_SECONDS;
    
    public boolean tokenExpired()
    {
        final long time = createTime + (this.tokenExpiredSeconds * THOUSAND);
        return time < System.currentTimeMillis();
    }
    
    public boolean refreshTokenExpired()
    {
        final long time = createTime + (this.refreshTokenExpiredSeconds * THOUSAND);
        return time < System.currentTimeMillis();
    }
    
    public long currentTokenExpiredSeconds()
    {
        if (tokenExpired())
        { return -1; }
        final long time = createTime + (this.tokenExpiredSeconds * THOUSAND);
        return (time - System.currentTimeMillis()) / THOUSAND;
    }
    
    public AccessToken updateByClientDetails(ClientDetails clientDetails)
    {
        this.clientId = clientDetails.getClientId();
        final Integer accessTokenValidity = clientDetails.getAccessTokenValidity();
        if (accessTokenValidity != null && accessTokenValidity > 0)
        {
            this.tokenExpiredSeconds = accessTokenValidity;
        }
        final Integer refreshTokenValidity = clientDetails.getRefreshTokenValidity();
        if (refreshTokenValidity != null && refreshTokenValidity > 0)
        {
            this.refreshTokenExpiredSeconds = refreshTokenValidity;
        }
        return this;
    }
    
    public AccessToken cloneMe()
    {
        AccessToken token = new AccessToken();
        token.setUserName(userName);
        token.setClientId(clientId);
        token.setTokenType(tokenType);
        token.setRefreshToken(refreshToken);
        return token;
    }
}
