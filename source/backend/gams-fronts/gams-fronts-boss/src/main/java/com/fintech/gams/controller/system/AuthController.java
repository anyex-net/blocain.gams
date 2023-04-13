package com.fintech.gams.controller.system;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.AccountPolicyException;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.model.TreeModel;
import com.fintech.gams.shiro.model.AccountToken;
import com.fintech.gams.utils.NetworkUtils;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * LoginController Introduce
 * <p>File：LoginController.java </p>
 * <p>Title: LoginController </p>
 * <p>Description:LoginController </p>
 * <p>Copyright: Copyright (c) 17/6/21</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@Api(description = "安全认证")
public class AuthController extends GenericController
{
    @PostMapping("/login/submit")
    @ApiOperation(value = "用户登陆认证", httpMethod = "POST")
    public JsonMessage submit(HttpServletRequest request, @ModelAttribute AccountToken token)
    {
        Subject subject = SecurityUtils.getSubject();
        try
        {
            if (SecurityUtils.getSubject().isAuthenticated())
            { // 如果登陆过就直接进入后台
                Serializable sessinId = subject.getSession().getId();
                return this.getJsonMessage(CommonEnums.SUCCESS, sessinId);
            }
            if (StringUtils.isBlank(token.getUsername()) || null == token.getPassword())
            { //必要参数校验
                throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
            }
            token.setHost(NetworkUtils.getIpAddr(request));
            subject.login(token);
        }
        catch (IncorrectCredentialsException ice)
        {
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        catch (UnknownAccountException uae)
        {
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_ACCOUNT);
        }
        catch (ExcessiveAttemptsException eae)
        {
            return this.getJsonMessage(CommonEnums.ERROR_LOGIN_TIMEOUT);
        }
        catch (AccountPolicyException gae)
        {
            return this.getJsonMessage(CommonEnums.NEED_POLICY_CHECK);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, subject.getSession().getId());
    }
    
    @GetMapping("/menuTree")
    @ApiOperation(value = "获取用户权限", httpMethod = "GET")
    public JsonMessage menuTree() throws BusinessException
    {
        List<TreeModel> data = OnLineUserUtils.getUserResources();
        return getJsonMessage(CommonEnums.SUCCESS, data);
    }
    
    @PostMapping("/logout")
    @ApiOperation(value = "退出认证", httpMethod = "POST")
    public JsonMessage logout() throws Exception
    {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject)
        {
            subject.logout();
        }
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }
}
