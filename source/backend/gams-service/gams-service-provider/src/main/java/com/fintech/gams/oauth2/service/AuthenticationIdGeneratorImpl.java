package com.fintech.gams.oauth2.service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * AuthenticationIdGeneratorImpl
 * <p>File: AuthenticationIdGeneratorImpl.java </p>
 * <p>Title: AuthenticationIdGeneratorImpl </p>
 * <p>Description: AuthenticationIdGeneratorImpl </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AuthenticationIdGeneratorImpl implements AuthenticationIdGenerator
{
    @Override
    public String generate(String clientId, String userName, String scope)
    {
        Map<String, String> map = new HashMap<>();
        map.put(OAuth.OAUTH_CLIENT_ID, clientId);
        // check it is client only
        if (!clientId.equals(userName))
        {
            map.put(OAuth.OAUTH_USERNAME, userName);
        }
        if (!OAuthUtils.isEmpty(scope))
        {
            map.put(OAuth.OAUTH_SCOPE, scope);
        }
        return digest(map);
    }
    
    protected String digest(Map<String, String> map)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e)
        {
            log.warn("Digest error", e);
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }
        try
        {
            byte[] bytes = digest.digest(map.toString().getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        }
        catch (UnsupportedEncodingException e)
        {
            log.warn("Encoding error", e);
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }
}
