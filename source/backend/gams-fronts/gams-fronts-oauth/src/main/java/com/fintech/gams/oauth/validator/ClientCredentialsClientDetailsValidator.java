package com.fintech.gams.oauth.validator;

import java.util.Set;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.oauth2.entity.ClientDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * ClientCredentialsClientDetailsValidator
 * <p>File: ClientCredentialsClientDetailsValidator.java </p>
 * <p>Title: ClientCredentialsClientDetailsValidator </p>
 * <p>Description: ClientCredentialsClientDetailsValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class ClientCredentialsClientDetailsValidator extends AbstractOauthTokenValidator
{
    public ClientCredentialsClientDetailsValidator(OAuthTokenRequest oauthRequest)
    {
        super(oauthRequest);
    }
    
    /**
     * /oauth/token?client_id=credentials-client&client_secret=credentials-secret&grant_type=client_credentials&scope=read write
     */
    @Override
    protected OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException
    {
        // validate grant_type
        final String grantType = grantType();
        if (!clientDetails.getGrantTypes().contains(grantType))
        {
            log.debug("Invalid grant_type '{}', client_id = '{}'", grantType, clientDetails.getClientId());
            return invalidGrantTypeResponse(grantType);
        }
        // validate client_secret
        final String clientSecret = oauthRequest.getClientSecret();
        if (clientSecret == null || !clientSecret.equals(clientDetails.getClientSecret()))
        {
            log.debug("Invalid client_secret '{}', client_id = '{}'", clientSecret, clientDetails.getClientId());
            return invalidClientSecretResponse();
        }
        // validate scope
        final Set<String> scopes = oauthRequest.getScopes();
        if (scopes.isEmpty() || excludeScopes(scopes, clientDetails))
        { return invalidScopeResponse(); }
        return null;
    }
}
