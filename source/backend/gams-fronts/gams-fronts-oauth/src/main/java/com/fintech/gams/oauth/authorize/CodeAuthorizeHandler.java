package com.fintech.gams.oauth.authorize;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.shiro.SecurityUtils;

import com.fintech.gams.oauth.OAuthAuthxRequest;
import com.fintech.gams.oauth.validator.AbstractClientDetailsValidator;
import com.fintech.gams.oauth.validator.CodeClientDetailsValidator;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.shiro.WebUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * CodeAuthorizeHandler
 * <p>File: CodeAuthorizeHandler.java </p>
 * <p>Title: CodeAuthorizeHandler </p>
 * <p>Description: CodeAuthorizeHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class CodeAuthorizeHandler extends AbstractAuthorizeHandler
{
    public CodeAuthorizeHandler(OAuthAuthxRequest oauthRequest, HttpServletResponse response)
    {
        super(oauthRequest, response);
    }
    
    @Override
    protected AbstractClientDetailsValidator getValidator()
    {
        return new CodeClientDetailsValidator(oauthRequest);
    }
    
    // response code
    @Override
    protected void handleResponse() throws OAuthSystemException, IOException
    {
        final ClientDetails clientDetails = clientDetails();
        final String userName = (String) SecurityUtils.getSubject().getPrincipal();
        final String authCode = oauthService.retrieveAuthCode(userName, clientDetails);
        final OAuthResponse oAuthResponse = OAuthASResponse.authorizationResponse(oauthRequest.request(), HttpServletResponse.SC_OK)
                .location(clientDetails.getRedirectUri()).setCode(authCode).buildQueryMessage();
        log.debug(" 'code' response: {}", oAuthResponse);
        WebUtils.writeOAuthQueryResponse(response, oAuthResponse);
    }
}
