package com.fintech.gams.oauth.validator;

import javax.servlet.http.HttpServletResponse;

import com.fintech.gams.shiro.model.AccountToken;
import org.apache.oltu.oauth2.as.request.OAuthTokenRequest;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import lombok.extern.slf4j.Slf4j;

/**
 * AbstractOauthTokenValidator
 * <p>File: AbstractOauthTokenValidator.java </p>
 * <p>Title: AbstractOauthTokenValidator </p>
 * <p>Description: AbstractOauthTokenValidator </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public abstract class AbstractOauthTokenValidator extends AbstractClientDetailsValidator
{
    protected OAuthTokenRequest tokenRequest;
    
    protected AbstractOauthTokenValidator(OAuthTokenRequest tokenRequest)
    {
        super(tokenRequest);
        this.tokenRequest = tokenRequest;
    }
    
    protected String grantType()
    {
        return tokenRequest.getGrantType();
    }
    
    protected OAuthResponse invalidGrantTypeResponse(String grantType) throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED).setError(OAuthError.TokenResponse.INVALID_GRANT)
                .setErrorDescription("Invalid grant_type '" + grantType + "'").buildJSONMessage();
    }
    
    // true is invalided
    protected boolean invalidUsernamePassword()
    {
        final String username = tokenRequest.getUsername();
        final String password = tokenRequest.getPassword();
        try
        {
            SecurityUtils.getSubject().login(new AccountToken(username, password));
        }
        catch (Exception e)
        {
            log.debug("Login failed by username: " + username, e);
            return true;
        }
        return false;
    }
    
    protected OAuthResponse invalidUsernamePasswordResponse() throws OAuthSystemException
    {
        return OAuthResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST).setError(OAuthError.TokenResponse.INVALID_GRANT).setErrorDescription("Bad credentials")
                .buildJSONMessage();
    }
}
