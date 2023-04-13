package com.fintech.gams.oauth.validator;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.bean.SpringContext;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.service.OauthService;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractClientDetailsValidator
 * <p>File: AbstractClientDetailsValidator.java </p>
 * <p>Title: AbstractClientDetailsValidator </p>
 * <p>Description: AbstractClientDetailsValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public abstract class AbstractClientDetailsValidator
{
    protected OauthService oauthService = SpringContext.getBean(OauthService.class);
    
    protected OAuthRequest oauthRequest;
    
    private ClientDetails  clientDetails;
    
    protected AbstractClientDetailsValidator(OAuthRequest oauthRequest)
    {
        this.oauthRequest = oauthRequest;
    }
    
    protected ClientDetails clientDetails()
    {
        if (clientDetails == null)
        {
            clientDetails = oauthService.loadClientDetails(oauthRequest.getClientId());
        }
        return clientDetails;
    }
    
    protected OAuthResponse invalidClientErrorResponse() throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED).setError(OAuthError.TokenResponse.INVALID_CLIENT)
                .setErrorDescription("Invalid client_id '" + oauthRequest.getClientId() + "'").buildJSONMessage();
    }
    
    protected OAuthResponse invalidRedirectUriResponse() throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.CodeResponse.INVALID_REQUEST)
                .setErrorDescription("Invalid redirect_uri '" + oauthRequest.getRedirectURI() + "'").buildJSONMessage();
    }
    
    protected OAuthResponse invalidScopeResponse() throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.CodeResponse.INVALID_SCOPE)
                .setErrorDescription("Invalid scope '" + oauthRequest.getScopes() + "'").buildJSONMessage();
    }
    
    public final OAuthResponse validate() throws OAuthSystemException
    {
        final ClientDetails details = clientDetails();
        if (details == null)
        { return invalidClientErrorResponse(); }
        return validateSelf(details);
    }
    
    protected boolean excludeScopes(Set<String> scopes, ClientDetails clientDetails)
    {
        final String clientDetailsScope = clientDetails.getScope(); // read write
        for (String scope : scopes)
        {
            if (!clientDetailsScope.contains(scope))
            {
                log.debug("Invalid scope - ClientDetails scopes '{}' exclude '{}'", clientDetailsScope, scope);
                return true;
            }
        }
        return false;
    }
    
    protected OAuthResponse invalidClientSecretResponse() throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED).setError(OAuthError.TokenResponse.UNAUTHORIZED_CLIENT)
                .setErrorDescription("Invalid client_secret by client_id '" + oauthRequest.getClientId() + "'").buildJSONMessage();
    }
    
    protected abstract OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException;
}
