package com.fintech.gams.bean;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigRequest;
import com.aliyuncs.afs.model.v20180112.AuthenticateSigResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.utils.NetworkUtils;
import com.fintech.gams.utils.ServletsUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云人机校验
 * <p>File: AliyunAFS.java </p>
 * <p>Title: AliyunAFS </p>
 * <p>Description: AliyunAFS </p>
 * <p>Copyright: Copyright (c) 2019-06-19</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
public class AliyunAFS
{
    public static final String regionId = "cn-hangzhou";
    
    @Autowired
    private GlobalProperies    properies;
    
    private static IAcsClient  client;
    
    /**
     * 初始化调用客户端
     */
    void initAcsClient()
    {
        if (null == client)
        {
            try
            {
                client = new DefaultAcsClient(DefaultProfile.getProfile(regionId, properies.getAliyun().getAccessKey(), properies.getAliyun().getSecretKey()));
                DefaultProfile.addEndpoint(regionId, regionId, "afs", "afs.aliyuncs.com");
            }
            catch (ClientException e)
            {
                log.error(e.getMessage());
            }
        }
    }
    
    /**
     * 判断用户验证码
     *
     * @param model
     * @return {@link Boolean}
     */
    public Boolean validParams(AliyunModel model)
    {
//        if (GlobalConst.DEV.equals(properies.getRunning()))
//        { return true; }
        try
        {
            initAcsClient();
            AuthenticateSigRequest request = new AuthenticateSigRequest();
            request.setSessionId(model.getCsessionid());// 必填参数，从前端获取，不可更改，android和ios只传这个参数即可
            request.setSig(model.getSig());// 必填参数，从前端获取，不可更改
            request.setToken(model.getToken());// 必填参数，从前端获取，不可更改
            request.setScene(model.getScene());// 必填参数，从前端获取，不可更改
            request.setAppKey(properies.getAliyun().getAppKey());// 必填参数，后端填写
            HttpServletRequest clientRequest = ServletsUtils.getRequest();
            request.setRemoteIp(NetworkUtils.getIpAddr(clientRequest));// 必填参数，后端填写
            AuthenticateSigResponse response = client.getAcsResponse(request);
            return response.getCode() == 100;
        }
        catch (ClientException e)
        {
            log.error(e.getMessage());
        }
        return false;
    }
}
