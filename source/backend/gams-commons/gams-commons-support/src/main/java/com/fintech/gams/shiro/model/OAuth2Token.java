package com.fintech.gams.shiro.model;

import org.apache.shiro.authc.AuthenticationToken;

import lombok.Data;

/**
 * OAuth2Token
 * <p>File: OAuth2Token.java </p>
 * <p>Title: OAuth2Token </p>
 * <p>Description: OAuth2Token </p>
 * <p>Copyright: Copyright (c) 2019-07-04</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class OAuth2Token implements AuthenticationToken
{
    private String authCode;
    
    private String principal;
    
    public OAuth2Token(String authCode)
    {
        this.authCode = authCode;
    }
    
    @Override
    public Object getPrincipal()
    {
        return principal;
    }
    
    @Override
    public Object getCredentials()
    {
        return authCode;
    }
}
