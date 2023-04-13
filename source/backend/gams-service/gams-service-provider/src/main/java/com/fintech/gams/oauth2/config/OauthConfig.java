package com.fintech.gams.oauth2.config;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OauthConfig
 * <p>File: OauthConfig.java </p>
 * <p>Title: OauthConfig </p>
 * <p>Description: OauthConfig </p>
 * <p>Copyright: Copyright (c) 2019-07-09</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Configuration
public class OauthConfig
{
    @Bean
    public OAuthIssuer oAuthIssuer()
    {
        return new OAuthIssuerImpl(new MD5Generator());
    }
}
