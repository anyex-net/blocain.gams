package com.fintech.gams.oauth;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;

import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.bean.SpringContext;
import com.fintech.gams.common.service.MsgLogService;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.entity.ClientDetails;
import com.fintech.gams.oauth2.service.OauthService;

import lombok.extern.slf4j.Slf4j;

/**
 * 对OAUTH各种流程的操作进行抽象
 * <p>File: OAuthHandler.java </p>
 * <p>Title: OAuthHandler </p>
 * <p>Description: OAuthHandler </p>
 * <p>Copyright: Copyright (c) 2019-07-03</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public abstract class OAuthHandler
{
    private ClientDetails     clientDetails;
    
    protected OauthService    oauthService    = SpringContext.getBean(OauthService.class);
    
    protected AccountService  accountService  = SpringContext.getBean(AccountService.class);
    
    protected MsgLogService   msgLogService   = SpringContext.getBean(MsgLogService.class);
    
    protected GlobalProperies globalProperies = SpringContext.getBean(GlobalProperies.class);
    
    protected ClientDetails clientDetails()
    {
        if (clientDetails == null)
        {
            final String clientId = clientId();
            clientDetails = oauthService.loadClientDetails(clientId);
            log.debug("Load ClientDetails: {} by clientId: {}", clientDetails, clientId);
        }
        return clientDetails;
    }
    
    /**
     * Create  AccessToken response
     *
     * @param accessToken AccessToken
     * @param queryOrJson True is QueryMessage, false is JSON message
     * @return OAuthResponse
     * @throws org.apache.oltu.oauth2.common.exception.OAuthSystemException
     */
    protected OAuthResponse createTokenResponse(AccessToken accessToken, boolean queryOrJson) throws OAuthSystemException
    {
        final ClientDetails tempClientDetails = clientDetails();
        final OAuthASResponse.OAuthTokenResponseBuilder builder = OAuthASResponse.tokenResponse(HttpServletResponse.SC_OK).location(tempClientDetails.getRedirectUri())
                .setAccessToken(accessToken.getTokenId()).setExpiresIn(String.valueOf(accessToken.currentTokenExpiredSeconds())).setTokenType(accessToken.getTokenType());
        final String refreshToken = accessToken.getRefreshToken();
        if (StringUtils.isNotEmpty(refreshToken))
        {
            builder.setRefreshToken(refreshToken);
        }
        return queryOrJson ? builder.buildQueryMessage() : builder.buildJSONMessage();
    }
    
    protected abstract String clientId();
}
