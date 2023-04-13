package com.fintech.gams.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.oauth2.entity.AccessToken;
import com.fintech.gams.oauth2.service.OauthService;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.JSONUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class AccountInfoController
{
    @Autowired(required = false)
    private OauthService   oauthService;
    
    @Autowired(required = false)
    private AccountService accountService;
    
    /**
     * 客户端根据code可获取user信息，这里只返回username
     * */
    @RequestMapping("/userInfo")
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException
    {
        try
        {
            // 构建 OAuth2 资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            // 获取Access Token
            String accessToken = oauthRequest.getAccessToken();
            // 验证Access Token
            AccessToken token = oauthService.loadAccessTokenByTokenId(accessToken);
            if (null == token || token.tokenExpired())
            {
                log.info("accessToken 已过期  accessToken=" + accessToken);
                // 不存在（过期），则返回未验证，需重新验证
                OAuthResponse oauthResponse = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED).setRealm(GlobalConst.RESOURCE_SERVER_NAME)
                        .setError(OAuthError.ResourceResponse.EXPIRED_TOKEN).buildHeaderMessage();
                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }
            // 返回用户名
            // return new ResponseEntity(token.getUserName(), HttpStatus.OK);
            Account account = accountService.selectByPrimaryKey(Long.parseLong(token.getUserName()));
            UserPrincipal userPrincipal = new UserPrincipal(account.getId(), account.getUnid(), account.getAccountName(), account.getAccountName(), account.getMobile(),
                    account.getEmail(), account.getLang(), account.getCountry(), account.getRegion(), account.getAuthKey());
            return new ResponseEntity(JSONUtils.beanToJson(userPrincipal), HttpStatus.OK);
        }
        catch (OAuthProblemException e)
        {
            // 检查是否设置了错误码
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode))
            {
                OAuthResponse oauthResponse = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)//
                        .setRealm(GlobalConst.RESOURCE_SERVER_NAME).buildHeaderMessage();
                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }
            OAuthResponse oauthResponse = OAuthRSResponse.errorResponse(HttpServletResponse.SC_UNAUTHORIZED)//
                    .setRealm(GlobalConst.RESOURCE_SERVER_NAME).setError(e.getError())//
                    .setErrorDescription(e.getDescription()).setErrorUri(e.getUri()).buildHeaderMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
