package com.fintech.gams.controller.account;


import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.AccountPersonalSaveOneModel;
import com.fintech.gams.account.model.AccountPersonalSaveTwoModel;
import com.fintech.gams.account.model.S3PresignedUrlModel;
import com.fintech.gams.account.service.*;
import com.fintech.gams.bean.AmazonS3;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.config.Amazon;
import com.fintech.gams.config.Bucket;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import com.fintech.gams.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * 个人开户 控制器
 * <p>File：AccountPersonalController.java </p>
 * <p>Title: AccountPersonalController </p>
 * <p>Description:AccountPersonalController </p>
 * <p>Copyright: Copyright (c) 2019-06-13</p>
 * <p>Company: mof</p>
 * @author yangnan
 * @version 1.0
 */

@RestController
@RequestMapping(GlobalConst.OPENACCOUNT)
@Api(description = "个人开户")
public class AccountPersonalController extends GenericController {

    @Autowired(required = false)
    private AccountPersonalService accountPersonalService;
    @Autowired
    private AmazonS3        amazonS3;
    @Autowired
    private GlobalProperies properies;
    @Autowired(required = false)
    private AccountService accountService;

    @PutMapping(value = "/personal/saveOne")
    @ApiOperation(value = "个人开户第一页插入/更新", httpMethod = "PUT")
    public JsonMessage saveOne(@RequestBody AccountPersonalSaveOneModel accountPersonalSaveOneModel) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 个人信息
        accountPersonalSaveOneModel.getAccountPerInfo().setAccountId(principal.getId());
        accountPersonalSaveOneModel.getAccountPerInfo().setCreatedBy(principal.getId());
        accountPersonalSaveOneModel.getAccountPerInfo().setUpdatedBy(principal.getId());
        // 地址
        for (AccountPerAddress accountPerAddress : accountPersonalSaveOneModel.getAccountPerAddressList()) {
            accountPerAddress.setCreatedBy(principal.getId());
            accountPerAddress.setAccountId(principal.getId());
            accountPerAddress.setUpdatedBy(principal.getId());
        }
        // 联系方式
        for (AccountPerContact accountPerContact : accountPersonalSaveOneModel.getAccountPerContactList()) {
            accountPerContact.setAccountId(principal.getId());
            accountPerContact.setCreatedBy(principal.getId());
            accountPerContact.setUpdatedBy(principal.getId());
        }
        // 身份信息
        accountPersonalSaveOneModel.getAccountPerIdentity().setAccountId(principal.getId());
        accountPersonalSaveOneModel.getAccountPerIdentity().setCreatedBy(principal.getId());
        accountPersonalSaveOneModel.getAccountPerIdentity().setUpdatedBy(principal.getId());
        // 税务居住地
        for (AccountPerTaxes accountPerTaxes : accountPersonalSaveOneModel.getAccountPerTaxesList()) {
            accountPerTaxes.setAccountId(principal.getId());
            accountPerTaxes.setCreatedBy(principal.getId());
            accountPerTaxes.setUpdatedBy(principal.getId());
        }
        // 财富来源
        for (AccountPerWealthSource accountPerWealthSource : accountPersonalSaveOneModel.getAccountPerWealthSourceList()) {
            accountPerWealthSource.setAccountId(principal.getId());
            accountPerWealthSource.setCreatedBy(principal.getId());
            accountPerWealthSource.setUpdatedBy(principal.getId());
        }
        // 安全问题
        for (AccountPerSafeQuestion accountPerSafeQuestion : accountPersonalSaveOneModel.getAccountPerSafeQuestionList()) {
            accountPerSafeQuestion.setAccountId(principal.getId());
            accountPerSafeQuestion.setCreatedBy(principal.getId());
            accountPerSafeQuestion.setUpdatedBy(principal.getId());
        }
        // 雇佣(就业)信息
        accountPersonalSaveOneModel.getAccountPerEmployment().setAccountId(principal.getId());
        accountPersonalSaveOneModel.getAccountPerEmployment().setCreatedBy(principal.getId());
        accountPersonalSaveOneModel.getAccountPerEmployment().setUpdatedBy(principal.getId());
        // 账户投资组合
        accountPersonalSaveOneModel.getAccountPerPortfolio().setAccountId(principal.getId());
        accountPersonalSaveOneModel.getAccountPerPortfolio().setCreatedBy(principal.getId());
        accountPersonalSaveOneModel.getAccountPerPortfolio().setUpdatedBy(principal.getId());
        if (beanValidator(json, accountPersonalSaveOneModel)) {
            accountPersonalService.saveOne(accountPersonalSaveOneModel);
            accountPersonalService.updateAccountStatus(accountPersonalSaveOneModel.getAccountPerInfo().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_ONE);
        }
        return json;
    }


    @PutMapping(value = "/personal/saveTwo")
    @ApiOperation(value = "个人开户第二页插入/更新", httpMethod = "PUT")
    public JsonMessage saveTwo(@RequestBody AccountPersonalSaveTwoModel accountPersonalSaveTwoModel) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 资产信息
        accountPersonalSaveTwoModel.getAccountPerAsset().setAccountId(principal.getId());
        accountPersonalSaveTwoModel.getAccountPerAsset().setCreatedBy(principal.getId());
        accountPersonalSaveTwoModel.getAccountPerAsset().setUpdatedBy(principal.getId());
        // 投资目标
        accountPersonalSaveTwoModel.getAccountPerInvestTargets().setAccountId(principal.getId());
        accountPersonalSaveTwoModel.getAccountPerInvestTargets().setCreatedBy(principal.getId());
        accountPersonalSaveTwoModel.getAccountPerInvestTargets().setUpdatedBy(principal.getId());
        // 交易经验
        for (AccountPerTradeExp accountPerTradeExp : accountPersonalSaveTwoModel.getAccountPerTradeExpList()) {
            accountPerTradeExp.setAccountId(principal.getId());
            accountPerTradeExp.setCreatedBy(principal.getId());
            accountPerTradeExp.setUpdatedBy(principal.getId());
        }
        // 规管信息
        accountPersonalSaveTwoModel.getAccountPerRegulatories().setAccountId(principal.getId());
        accountPersonalSaveTwoModel.getAccountPerRegulatories().setCreatedBy(principal.getId());
        accountPersonalSaveTwoModel.getAccountPerRegulatories().setUpdatedBy(principal.getId());
        if (beanValidator(json, accountPersonalSaveTwoModel)) {
            accountPersonalService.saveTwo(accountPersonalSaveTwoModel);
            accountPersonalService.updateAccountStatus(accountPersonalSaveTwoModel.getAccountPerAsset().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TWO);
        }
        return json;
    }

    @PutMapping(value = "/personal/saveThree")
    @ApiOperation(value = "个人开户第三页插入/更新", httpMethod = "PUT")
    public JsonMessage saveThree(@RequestBody AccountPerWbenContract accountPerWbenContract) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountPerWbenContract.setAccountId(principal.getId());
        accountPerWbenContract.setCreatedBy(principal.getId());
        accountPerWbenContract.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountPerWbenContract)) {
            accountPersonalService.saveThree(accountPerWbenContract);
            accountPersonalService.updateAccountStatus(accountPerWbenContract.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_THREE);
        }
        return json;
    }


    @PutMapping(value = "/personal/saveFour")
    @ApiOperation(value = "个人开户第四页插入/更新", httpMethod = "PUT")
    public JsonMessage saveThree(@RequestBody List<AccountPerAgreement> accountPerAgreementList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountPerAgreement accountPerAgreement : accountPerAgreementList) {
            accountPerAgreement.setAccountId(principal.getId());
            accountPerAgreement.setCreatedBy(principal.getId());
            accountPerAgreement.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountPerAgreementList)) {
            accountPersonalService.saveFour(accountPerAgreementList);
            accountPersonalService.updateAccountStatus(accountPerAgreementList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FOUR);
        }
        return json;
    }


    @PutMapping(value = "/personal/saveFive")
    @ApiOperation(value = "个人开户第五页插入/更新", httpMethod = "PUT")
    public JsonMessage saveFive(@RequestBody AccountPerInvestAccount accountPerInvestAccount) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountPerInvestAccount.setAccountId(principal.getId());
        accountPerInvestAccount.setCreatedBy(principal.getId());
        accountPerInvestAccount.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountPerInvestAccount)) {
            accountPersonalService.saveFive(accountPerInvestAccount);
            accountPersonalService.updateAccountStatus(accountPerInvestAccount.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIVE);
        }
        return json;
    }

    @PostMapping(value = "/personal/saveSix")
    @ApiOperation(value = "个人开户第六页插入/更新", httpMethod = "PUT")
    public JsonMessage saveSix() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        accountPersonalService.updateAccountStatus(account.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_SIX);
        return json;
    }

    @PutMapping(value = "/personal/saveSeven")
    @ApiOperation(value = "个人开户第七页插入/更新", httpMethod = "PUT")
    public JsonMessage saveSeven(@RequestBody AccountPerCrs accountPerCrs) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountPerCrs.setAccountId(principal.getId());
        accountPerCrs.setCreatedBy(principal.getId());
        accountPerCrs.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountPerCrs)) {
            accountPersonalService.saveSeven(accountPerCrs);
            accountPersonalService.updateAccountStatus(accountPerCrs.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_SEVEN);
        }
        return json;
    }


    @PostMapping(value = "/personal/saveEight")
    @ApiOperation(value = "个人开户第八页插入/更新", httpMethod = "PUT")
    public JsonMessage saveEight() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        accountPersonalService.updateAccountStatus(account.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHT);
        return json;
    }


    @PutMapping(value = "/personal/saveNine")
    @ApiOperation(value = "个人开户第九页插入/更新", httpMethod = "PUT")
    public JsonMessage saveNine(@RequestBody List<AccountPerAmlCtf> accountPerAmlCtfList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountPerAmlCtf accountPerAmlCtf : accountPerAmlCtfList) {
            accountPerAmlCtf.setAccountId(principal.getId());
            accountPerAmlCtf.setCreatedBy(principal.getId());
            accountPerAmlCtf.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountPerAmlCtfList)) {
            accountPersonalService.saveNine(accountPerAmlCtfList);
            accountPersonalService.updateAccountStatus(accountPerAmlCtfList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINE);
        }
        return json;
    }


    @PutMapping(value = "/personal/saveTen")
    @ApiOperation(value = "个人开户第十页插入/更新", httpMethod = "PUT")
    public JsonMessage saveTen(@RequestBody AccountPerFile accountPerFile) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountPerFile.setAccountId(principal.getId());
        accountPerFile.setCreatedBy(principal.getId());
        accountPerFile.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountPerFile)) {
            String[] tempS3Path = accountPerFile.getFilePath().split("/");
            // s3 临时空间文件key
            String fileKey = "AccountFile/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "AccountFile/" + accountPerFile.getAccountId() + "/" + tempS3Path[tempS3Path.length - 1];
            Boolean transfer = amazonS3.transferObject(fileKey, releaseKey);
            Amazon amazon = properies.getAmazon();
            accountPerFile.setFilePath(amazon.getBucket().getReleaseUrl() + releaseKey);
            if (!transfer) {
                throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
            }
            accountPersonalService.saveTen(accountPerFile);
            accountPersonalService.updateAccountStatus(accountPerFile.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TEN);
        }
        return json;
    }

    @PostMapping("/personal/openAccountFinish")
    @ApiOperation(value = "个人开户完成待审核", httpMethod = "POST")
    public JsonMessage openAccountFinish(String status) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (StringUtils.isNotBlank(status)) {
            // 开户完成 待审核 0
            if (account.getRegisterStatus() != 1) {
                accountPersonalService.updateAccountStatus(account.getId(), AccountConsts.ACCOUNT_STATUS_NORMAL);
            }
        }
        return json;
    }

    @GetMapping(value = "/personal/getAgreement")
    @ApiOperation(value = "根据accountId获取个人协议披露", httpMethod = "GET")
    public JsonMessage findByAgreement() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        List<AccountCommonAgreement> agreements = accountPersonalService.findByAgreement(account.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, agreements);
    }


    @GetMapping(value = "/personal/getFileIdentification")
    @ApiOperation(value = "根据accountId和字典语言获取个人身份类型", httpMethod = "GET")
    public JsonMessage getFileIdentification(String lang) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        Map<String, Object> map = accountPersonalService.getFileUploadType(account.getId(), lang);
        return this.getJsonMessage(CommonEnums.SUCCESS, map);
    }

    @GetMapping(value = "/personal/getPreSignedUrl")
    @ApiOperation(value = "获取预签名URL", httpMethod = "GET")
    @ApiImplicitParam(name = "url", value = "文件地址", required = true, paramType = "form")
    public JsonMessage generatePreSignedUrl(String url) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (StringUtils.isBlank(url)) url = "";
        String amazonaws = ".amazonaws.com/";
        String s3 = ".s3-";
        String http = "https://";
        String bucketName = url.substring(0, url.indexOf(s3)).substring(http.length());
        String fileName = url.substring(url.indexOf(amazonaws) + amazonaws.length(), url.length());
        String[] fileNameArr = fileName.split("/");
        if (!fileNameArr[1].equals(account.getId().toString())) {
            // 不是获取当前登录对象的文件 返回空
            return getJsonMessage(CommonEnums.SUCCESS, url);
        }
        return getJsonMessage(CommonEnums.SUCCESS, amazonS3.generatePresignedUrl(bucketName, fileName));
    }

    @GetMapping(value = "/personal/generateUploadObjectPresignedUrl")
    @ApiOperation(value = "获取上传预签名URL", httpMethod = "GET")
    public JsonMessage putPresignedUrl(String dir, String fileName) throws BusinessException {
        if (StringUtils.isBlank(fileName)) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        S3PresignedUrlModel model = new S3PresignedUrlModel();
        String[] fileNameArr = fileName.split("-");
        if (!fileNameArr[0].equals(account.getId().toString())) {
            // 不是上传当前登录对象的文件 返回空
            return getJsonMessage(CommonEnums.SUCCESS, model);
        }
        model.setPresignedUrl(amazonS3.generateUploadObjectPresignedUrl(properies.getAmazon().getBucket().getTempName(), dir, fileName));
        model.setUrl(properies.getAmazon().getBucket().getTempUrl() + dir + "/" + fileName);
        return getJsonMessage(CommonEnums.SUCCESS, model);
    }
}
