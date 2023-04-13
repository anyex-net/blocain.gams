package com.fintech.gams.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.account.service.AccountService;
import com.fintech.gams.bean.AliyunAFS;
import com.fintech.gams.bean.AliyunModel;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.MsgLogService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;

import io.swagger.annotations.ApiOperation;

/**
 * CommonController
 * <p>File: CommonController.java </p>
 * <p>Title: CommonController </p>
 * <p>Description: CommonController </p>
 * <p>Copyright: Copyright (c) 2019-05-30</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
public class CommonController extends GenericController
{
    @Autowired(required = false)
    private AliyunAFS         aliyunAFS;
    
    @Autowired(required = false)
    private AccountService    accountService;
    
    @Autowired(required = false)
    private MsgLogService     msgLogService;
    
    @Autowired(required = false)
    private RegionService     regionService;
    
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    
    @GetMapping(value = "/region/findAll")
    @ApiOperation(value = "取所有区域", httpMethod = "GET")
    public JsonMessage getRegion() throws BusinessException
    {
        List<Region> regionList = regionService.selectAll();
        return getJsonMessage(CommonEnums.SUCCESS, regionList);
    }
    
    @GetMapping(value = "/dictionary/findByCode")
    @ApiOperation(value = "根据字典编码和语言取字典项", httpMethod = "GET")
    public JsonMessage findDictionaryByCode(String dictCode, String lang) throws BusinessException
    {
        if (StringUtils.isBlank(dictCode))
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        return this.getJsonMessage(CommonEnums.SUCCESS, dictionaryService.findByCode(dictCode, lang));
    }
    
    @GetMapping(value = "/region/findByCode")
    @ApiOperation(value = "根据区域国际简码取区域信息", httpMethod = "GET")
    public JsonMessage findRegionByCode(String code) throws BusinessException
    {
        if (StringUtils.isBlank(code))
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        Region region = new Region();
        region.setsCode(code);
        return this.getJsonMessage(CommonEnums.SUCCESS, regionService.findList(region));
    }
    
    @PostMapping(value = "/forgetPass/sendMail")
    @ApiOperation(value = "发送找回密码邮件", httpMethod = "POST")
    public JsonMessage sendForgetMail(String mail, AliyunModel model) throws BusinessException
    {
        if (StringUtils.isBlank(mail))
        {// 邮件验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        if (null == accountService.findByEmail(mail))
        {// 判断帐户是否存在
            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
        }
        msgLogService.sendForgetMail(mail, GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }
    
    @PostMapping(value = "/forgetPass/sendSMS")
    @ApiOperation(value = "发送找回密码短信", httpMethod = "POST")
    public JsonMessage sendForgetSMS(String region, String phone, AliyunModel model) throws BusinessException
    {
        if (StringUtils.isBlank(region) || StringUtils.isBlank(phone))
        { // 参数验证
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        if (!aliyunAFS.validParams(model))
        {// 验证滑块
            throw new BusinessException(CommonEnums.ERROR_AFS_VALID_FAILED);
        }
        if (null == accountService.findByPhone(region, phone))
        {// 判断帐户是否存在
            throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT);
        }
        msgLogService.sendForgetSMS(region, phone, GlobalConst.DEFAULT_LANG);
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/server/timestamp")
    @ApiOperation(value = "获取服务器时间戳", httpMethod = "GET")
    public JsonMessage getServerTime() throws BusinessException {
        Long time = System.currentTimeMillis();
        return getJsonMessage(CommonEnums.SUCCESS, time.toString());
    }
}