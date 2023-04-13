package com.fintech.gams.oauth.token;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.oauth.OAuthHandler;
import com.fintech.gams.shiro.WebUtils;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractOAuthTokenHandler
 * <p>File: AbstractOAuthTokenHandler.java </p>
 * <p>Title: AbstractOAuthTokenHandler </p>
 * <p>Description: AbstractOAuthTokenHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public abstract class AbstractOAuthTokenHandler extends OAuthHandler implements OAuthTokenHandler
{
    protected OAuthTokenRequest   tokenRequest;
    
    protected HttpServletResponse response;
    
    @Override
    public final void handle(OAuthTokenRequest tokenRequest, HttpServletResponse response) throws OAuthProblemException, OAuthSystemException
    {
        this.tokenRequest = tokenRequest;
        this.response = response;
        // validate
        if (validateFailed())
        { return; }
        handleAfterValidation();
    }
    
    protected boolean validateFailed() throws OAuthSystemException
    {
        AbstractClientDetailsValidator validator = getValidator();
        log.debug("Use [{}] validate client: {}", validator, tokenRequest.getClientId());
        final OAuthResponse oAuthResponse = validator.validate();
        return checkAndResponseValidateFailed(oAuthResponse);
    }
    
    protected boolean checkAndResponseValidateFailed(OAuthResponse oAuthResponse)
    {
        if (oAuthResponse != null)
        {
            log.debug("Validate OAuthAuthzRequest(client_id={}) failed", tokenRequest.getClientId());
            WebUtils.writeOAuthJsonResponse(response, oAuthResponse);
            return true;
        }
        return false;
    }
    
    protected abstract AbstractClientDetailsValidator getValidator();
    
    protected String clientId()
    {
        return tokenRequest.getClientId();
    }
    
    protected abstract void handleAfterValidation() throws OAuthProblemException, OAuthSystemException;
}
