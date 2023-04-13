package com.fintech.gams.oauth2.service;

/**
 * AuthenticationIdGenerator
 * <p>File: AuthenticationIdGenerator.java </p>
 * <p>Title: AuthenticationIdGenerator </p>
 * <p>Description: AuthenticationIdGenerator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AuthenticationIdGenerator
{
    /**
     * 生成凭证
     * @param clientId
     * @param userName
     * @param scope
     * @return
     */
    String generate(String clientId, String userName, String scope);
}
