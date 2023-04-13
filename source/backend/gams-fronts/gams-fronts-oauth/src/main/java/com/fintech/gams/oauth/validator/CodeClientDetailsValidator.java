package com.fintech.gams.oauth.validator;

import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.oauth2.entity.ClientDetails;

import lombok.extern.slf4j.Slf4j;

/**
 * CodeClientDetailsValidator
 * <p>File: CodeClientDetailsValidator.java </p>
 * <p>Title: CodeClientDetailsValidator </p>
 * <p>Description: CodeClientDetailsValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class CodeClientDetailsValidator extends AbstractClientDetailsValidator
{
    public CodeClientDetailsValidator(OAuthAuthzRequest oauthRequest)
    {
        super(oauthRequest);
    }
    
    /**
     * grant_type="authorization_code"
     * ?response_type=code&scope=read,write&client_id=[client_id]&redirect_uri=[redirect_uri]&state=[state]
     */
    @Override
    public OAuthResponse validateSelf(ClientDetails clientDetails) throws OAuthSystemException
    {
        // validate redirect_uri
        final String redirectURI = oauthRequest.getRedirectURI();
        if (redirectURI == null || !redirectURI.equals(clientDetails.getRedirectUri()))
        {
            log.debug("Invalid redirect_uri '{}' by response_type = 'code', client_id = '{}'", redirectURI, clientDetails.getClientId());
            return invalidRedirectUriResponse();
        }
        // validate scope
        final Set<String> scopes = oauthRequest.getScopes();
        if (scopes.isEmpty() || excludeScopes(scopes, clientDetails))
        { return invalidScopeResponse(); }
        // validate state
        final String state = getState();
        if (StringUtils.isEmpty(state))
        {
            log.debug("Invalid 'state', it is required, but it is empty");
            return invalidStateResponse();
        }
        return null;
    }
    
    private String getState()
    {
        return ((OAuthAuthzRequest) oauthRequest).getState();
    }
    
    private OAuthResponse invalidStateResponse() throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.CodeResponse.INVALID_REQUEST)
                .setErrorDescription("Parameter 'state'  is required").buildJSONMessage();
    }
}
