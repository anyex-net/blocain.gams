package com.fintech.gams.oauth.validator;

import java.util.Set;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.oauth2.entity.ClientDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * PasswordClientDetailsValidator
 * <p>File: PasswordClientDetailsValidator.java </p>
 * <p>Title: PasswordClientDetailsValidator </p>
 * <p>Description: PasswordClientDetailsValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class PasswordClientDetailsValidator extends AbstractOauthTokenValidator
{
    public PasswordClientDetailsValidator(OAuthTokenRequest oauthRequest)
    {
        super(oauthRequest);
    }
    
    /*
     * /oauth/token?client_id=mobile-client&client_secret=mobile&grant_type=password&scope=read,write&username=mobile&password=mobile
     */
    @Override
    protected OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException
    {
        // validate grant_type
        final String grantType = grantType();
        if (invalidateGrantType(clientDetails, grantType))
        { return invalidGrantTypeResponse(grantType); }
        // validate client_secret
        if (invalidateClientSecret(clientDetails))
        { return invalidClientSecretResponse(); }
        // validate scope
        if (invalidateScope(clientDetails))
        { return invalidScopeResponse(); }
        // validate username,password
        if (invalidUsernamePassword())
        { return invalidUsernamePasswordResponse(); }
        return null;
    }
    
    private boolean invalidateGrantType(ClientDetails clientDetails, String grantType) throws OAuthSystemException
    {
        if (!clientDetails.getGrantTypes().contains(grantType))
        {
            log.debug("Invalid grant_type '{}', client_id = '{}'", grantType, clientDetails.getClientId());
            return true;
        }
        return false;
    }
    
    private boolean invalidateScope(ClientDetails clientDetails) throws OAuthSystemException
    {
        final Set<String> scopes = oauthRequest.getScopes();
        return scopes.isEmpty() || excludeScopes(scopes, clientDetails);
    }
    
    private boolean invalidateClientSecret(ClientDetails clientDetails) throws OAuthSystemException
    {
        final String clientSecret = oauthRequest.getClientSecret();
        if (clientSecret == null || !clientSecret.equals(clientDetails.getClientSecret()))
        {
            log.debug("Invalid client_secret '{}', client_id = '{}'", clientSecret, clientDetails.getClientId());
            return true;
        }
        return false;
    }
}
