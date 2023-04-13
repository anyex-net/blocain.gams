package com.fintech.gams.controller.account;


import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.*;
import com.fintech.gams.account.service.*;
import com.fintech.gams.bean.AmazonS3;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.config.Amazon;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: hcj
 * @Date: 2019/6/15 14:35
 * @Description
 */
@RestController
@Api(description = "公司开户控制器")
@RequestMapping(GlobalConst.OPENACCOUNT)
public class AccountCompanyController extends GenericController {
    @Autowired(required = false)
    private AccountCompanyService accountCompanyService;
    @Autowired(required = false)
    private AccountCoPersonalService accountCoPersonalService;
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private GlobalProperies properies;
    @Autowired(required = false)
    private AccountService accountService;


    @PostMapping("/company/saveOne")
    @ApiOperation(value = "公司开户第一页插入/更新", httpMethod = "POST")
    public JsonMessage saveOne(@RequestBody CompanyInfoModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 机构信息
        model.getAccountCoInstitutional().setAccountId(principal.getId());
        model.getAccountCoInstitutional().setCreatedBy(principal.getId());
        model.getAccountCoInstitutional().setUpdatedBy(principal.getId());
        // 地址信息
        for (AccountCoAddress accountCoAddress : model.getAccountCoAddressList()) {
            accountCoAddress.setAccountId(principal.getId());
            accountCoAddress.setCreatedBy(principal.getId());
            accountCoAddress.setUpdatedBy(principal.getId());
        }
        // 联系信息
        for (AccountCoContact accountCoContact : model.getAccountCoContactList()) {
            accountCoContact.setAccountId(principal.getId());
            accountCoContact.setCreatedBy(principal.getId());
            accountCoContact.setUpdatedBy(principal.getId());
        }
        // 税务居住地
        for (AccountCoTaxes accountCoTaxes : model.getAccountCoTaxesList()) {
            accountCoTaxes.setAccountId(principal.getId());
            accountCoTaxes.setCreatedBy(principal.getId());
            accountCoTaxes.setUpdatedBy(principal.getId());
        }
        // 财富来源
        for (AccountCoWealthSource accountCoWealthSource : model.getAccountCoWealthSourceList()) {
            accountCoWealthSource.setAccountId(principal.getId());
            accountCoWealthSource.setCreatedBy(principal.getId());
            accountCoWealthSource.setUpdatedBy(principal.getId());
        }
        // 帐户组合信息
        model.getAccountCoAccountPortfolio().setAccountId(principal.getId());
        model.getAccountCoAccountPortfolio().setCreatedBy(principal.getId());
        model.getAccountCoAccountPortfolio().setUpdatedBy(principal.getId());
        if (beanValidator(json, model)) {
            accountCompanyService.saveOne(model);
            accountCompanyService.updateAccountStatus(model.getAccountCoInstitutional().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_ONE);
        }
        return json;
    }


    @PostMapping("/company/saveTwo")
    @ApiOperation(value = "公司开户第二页插入/更新", httpMethod = "POST")
    public JsonMessage saveTwo(@RequestBody CompanyAuthorizedModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 个人信息
        model.getAccountCoPersonal().setAccountId(principal.getId());
        model.getAccountCoPersonal().setCreatedBy(principal.getId());
        model.getAccountCoPersonal().setUpdatedBy(principal.getId());
        // 地址信息
        for (AccountCoAddress accountCoAddress : model.getAccountCoAddressList()) {
            accountCoAddress.setAccountId(principal.getId());
            accountCoAddress.setCreatedBy(principal.getId());
            accountCoAddress.setUpdatedBy(principal.getId());
        }
        // 联系信息
        for (AccountCoContact accountCoContact : model.getAccountCoContactList()) {
            accountCoContact.setAccountId(principal.getId());
            accountCoContact.setCreatedBy(principal.getId());
            accountCoContact.setUpdatedBy(principal.getId());
        }
        // 身份信息
        model.getAccountCoIdentity().setAccountId(principal.getId());
        model.getAccountCoIdentity().setCreatedBy(principal.getId());
        model.getAccountCoIdentity().setUpdatedBy(principal.getId());
        // 税务居住地
        for (AccountCoTaxes accountCoTaxes : model.getAccountCoTaxesList()) {
            accountCoTaxes.setAccountId(principal.getId());
            accountCoTaxes.setCreatedBy(principal.getId());
            accountCoTaxes.setUpdatedBy(principal.getId());
        }
        // 安全问题
        for (AccountCoSafeQuestion coSafeQuestion : model.getAccountCoSafeQuestionList()) {
            coSafeQuestion.setAccountId(principal.getId());
            coSafeQuestion.setCreatedBy(principal.getId());
            coSafeQuestion.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            accountCompanyService.saveTwo(model);
            accountCompanyService.updateAccountStatus(model.getAccountCoPersonal().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TWO);
        }
        return json;
    }

    @PostMapping("/company/saveThree")
    @ApiOperation(value = "公司开户第三页插入/更新", httpMethod = "POST")
    public JsonMessage saveThree(@RequestBody AccountCoTaxClassification accountCoTaxClassification) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountCoTaxClassification.setAccountId(principal.getId());
        accountCoTaxClassification.setCreatedBy(principal.getId());
        accountCoTaxClassification.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountCoTaxClassification)) {
            // 税收分类
            accountCompanyService.saveThree(accountCoTaxClassification);
            accountCompanyService.updateAccountStatus(accountCoTaxClassification.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_THREE);
        }
        return json;
    }

    @PostMapping("/company/saveFour")
    @ApiOperation(value = "公司开户第四页插入/更新", httpMethod = "POST")
    public JsonMessage saveFour(@RequestBody List<AccountCoRegulatory> accountCoRegulatoryList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountCoRegulatory accountCoRegulatory : accountCoRegulatoryList) {
            accountCoRegulatory.setAccountId(principal.getId());
        }
        if (beanValidator(json, accountCoRegulatoryList)) {
            // 公司规管信息
            accountCompanyService.saveFour(accountCoRegulatoryList);
            accountCompanyService.updateAccountStatus(accountCoRegulatoryList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FOUR);
        }
        return json;
    }

    @PostMapping("/company/saveSeven")
    @ApiOperation(value = "公司开户第七页插入/更新", httpMethod = "POST")
    public JsonMessage saveSeven(@RequestBody CompanyAssetModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 资产信息
        model.getAccountCoAsset().setAccountId(principal.getId());
        model.getAccountCoAsset().setCreatedBy(principal.getId());
        model.getAccountCoAsset().setUpdatedBy(principal.getId());
        // 投资目标信息
        model.getAccountCoInvestmentTarget().setAccountId(principal.getId());
        model.getAccountCoInvestmentTarget().setCreatedBy(principal.getId());
        model.getAccountCoInvestmentTarget().setUpdatedBy(principal.getId());
        // 交易经验信息
        for (AccountCoTradeExperience accountCoTradeExperience : model.getAccountCoTradeExperiencesList()) {
            accountCoTradeExperience.setAccountId(principal.getId());
            accountCoTradeExperience.setCreatedBy(principal.getId());
            accountCoTradeExperience.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            accountCompanyService.saveSeven(model);
            accountCompanyService.updateAccountStatus(model.getAccountCoAsset().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_SEVEN);
        }
        return json;
    }

    @PostMapping("/company/saveEight")
    @ApiOperation(value = "公司开户第八页插入/更新", httpMethod = "POST")
    public JsonMessage saveEight(@RequestBody CompanyRegisteredOwnerModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (CompanyRegisteredOwnerInfoModel companyRegisteredOwnerInfoModel : model.getCompanyRegisteredOwnerInfoModelList()) {
            // 个人信息
            companyRegisteredOwnerInfoModel.getAccountCoPersonal().setAccountId(principal.getId());
            companyRegisteredOwnerInfoModel.getAccountCoPersonal().setCreatedBy(principal.getId());
            companyRegisteredOwnerInfoModel.getAccountCoPersonal().setUpdatedBy(principal.getId());
            // 地址信息
            for (AccountCoAddress accountCoAddress : companyRegisteredOwnerInfoModel.getAccountCoAddressList()) {
                accountCoAddress.setAccountId(principal.getId());
                accountCoAddress.setCreatedBy(principal.getId());
                accountCoAddress.setUpdatedBy(principal.getId());
            }
            // 联系信息
            for (AccountCoContact accountCoContact : companyRegisteredOwnerInfoModel.getAccountCoContactList()) {
                accountCoContact.setAccountId(principal.getId());
                accountCoContact.setCreatedBy(principal.getId());
                accountCoContact.setUpdatedBy(principal.getId());
            }
            // 身份信息 个人股东才有身份信息 ：individualShareholder
            if (companyRegisteredOwnerInfoModel.getAccountCoPersonal().getPersonalType().endsWith(AccountConsts.ACCOUNT_PERSONAL_TYPE)) {
                if (null != companyRegisteredOwnerInfoModel.getAccountCoIdentity().getIssuingCountry()
                        && null != companyRegisteredOwnerInfoModel.getAccountCoIdentity().getIdCardType()) {
                    companyRegisteredOwnerInfoModel.getAccountCoIdentity().setAccountId(principal.getId());
                    companyRegisteredOwnerInfoModel.getAccountCoIdentity().setCreatedBy(principal.getId());
                    companyRegisteredOwnerInfoModel.getAccountCoIdentity().setUpdatedBy(principal.getId());
                }
            }
            // 税务居住地
            for (AccountCoTaxes accountCoTaxes : companyRegisteredOwnerInfoModel.getAccountCoTaxesList()) {
                accountCoTaxes.setAccountId(principal.getId());
                accountCoTaxes.setCreatedBy(principal.getId());
                accountCoTaxes.setUpdatedBy(principal.getId());
            }
        }
        if (beanValidator(json, model)) {
            accountCompanyService.saveEight(model);
            accountCompanyService.updateAccountStatus(model.getCompanyRegisteredOwnerInfoModelList().get(0).getAccountCoPersonal().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHT);
        }
        return json;
    }


    @PostMapping("/company/saveEightPage")
    @ApiOperation(value = "公司开户第八页保存页数", httpMethod = "POST")
    public JsonMessage saveEightPage() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 第八页不保存也行
        accountCompanyService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHT);
        return json;
    }

    @PutMapping("/company/removeEight")
    @ApiOperation(value = "删除公司开户第八页通过公司个人信息的id", httpMethod = "PUT")
    public JsonMessage removeEight(Long id) {
        accountCompanyService.removeEight(id);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }


    @PostMapping("/company/saveNine")
    @ApiOperation(value = "公司开户第九页插入/更新", httpMethod = "POST")
    public JsonMessage saveNine(@RequestBody CompanyBeneficiaryOwnersModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (CompanyBeneficiaryOwnersInfoModel companyBeneficiaryOwnersInfoModel : model.getCompanyBeneficiaryOwnersInfoModelList()) {
            // 个人信息
            companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().setAccountId(principal.getId());
            companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().setCreatedBy(principal.getId());
            companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().setUpdatedBy(principal.getId());
            // 地址信息
            for (AccountCoAddress accountCoAddress : companyBeneficiaryOwnersInfoModel.getAccountCoAddressList()) {
                accountCoAddress.setAccountId(principal.getId());
                accountCoAddress.setCreatedBy(principal.getId());
                accountCoAddress.setUpdatedBy(principal.getId());
            }
            if (null != companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().getIssuingCountry()
                    && null != companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().getIdCardType()) {
                // 身份信息
                companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().setAccountId(principal.getId());
                companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().setCreatedBy(principal.getId());
                companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().setUpdatedBy(principal.getId());
            }
            // 税务居住地
            for (AccountCoTaxes accountCoTaxes : companyBeneficiaryOwnersInfoModel.getAccountCoTaxesList()) {
                accountCoTaxes.setAccountId(principal.getId());
                accountCoTaxes.setCreatedBy(principal.getId());
                accountCoTaxes.setUpdatedBy(principal.getId());
            }
        }
        if (beanValidator(json, model)) {
            if (null == model.getStatus()) {
                accountCompanyService.saveNine(model);
            }
        }
        return json;
    }

    @PostMapping("/company/updatePersonalByPrimaryKeySelective")
    @ApiOperation(value = "公司开户第九页/修改引用经营负责人信息", httpMethod = "POST")
    public JsonMessage updatePersonalByPrimaryKeySelective(Long id, String position, String personalTypeTwo) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (null == id || null == position || null == personalTypeTwo) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        accountCompanyService.updatePersonalByPrimaryKeySelective(id, position, personalTypeTwo);
        return json;
    }


    @PostMapping("/company/saveNinePage")
    @ApiOperation(value = "公司开户第九页保存页数", httpMethod = "POST")
    public JsonMessage saveNinePage() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 至少有一名经营负责人
        AccountCoPersonal personal = new AccountCoPersonal();
        personal.setAccountId(principal.getId());
        personal.setPersonalType(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL);
        List<AccountCoPersonal> personalList = accountCoPersonalService.findList(personal);
        if (null == personalList || personalList.size() <= 0) {
            AccountCoPersonal personalTwo = new AccountCoPersonal();
            personalTwo.setAccountId(principal.getId());
            personalTwo.setPersonalTypeTwo(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL);
            if (null == accountCoPersonalService.findList(personalTwo) || accountCoPersonalService.findList(personalTwo).size() <= 0) {
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
        }
        accountCompanyService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINE);
        return json;
    }

    @PutMapping("/company/removeNine")
    @ApiOperation(value = "删除公司开户第九页通过公司个人信息的id", httpMethod = "PUT")
    public JsonMessage removeNine(Long id) {
        accountCompanyService.removeNine(id);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }


    @PostMapping("/company/saveTen")
    @ApiOperation(value = "公司开户第十页插入/更新", httpMethod = "POST")
    public JsonMessage saveTen(@RequestBody CompanySecurityOfficerModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountCoSecurityOfficer accountCoSecurityOfficer : model.getAccountCoSecurityOfficerList()) {
            accountCoSecurityOfficer.setAccountId(principal.getId());
            accountCoSecurityOfficer.setCreatedBy(principal.getId());
            accountCoSecurityOfficer.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            // 公司安全官
            accountCompanyService.saveTen(model);
            accountCompanyService.updateAccountStatus(model.getAccountCoSecurityOfficerList().get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TEN);
        }
        return json;
    }


    @PostMapping("/company/saveTenPage")
    @ApiOperation(value = "公司开户第十页保存页数", httpMethod = "POST")
    public JsonMessage saveTenPage() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 第十页不保存也行
        accountCompanyService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_TEN);
        return json;
    }


    @PostMapping("/company/saveEleven")
    @ApiOperation(value = "公司开户第十一页插入/更新", httpMethod = "POST")
    public JsonMessage saveEleven(@RequestBody AccountCoWbenContract accountCoWbenContract) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountCoWbenContract.setAccountId(principal.getId());
        accountCoWbenContract.setCreatedBy(principal.getId());
        accountCoWbenContract.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountCoWbenContract)) {
            accountCompanyService.saveEleven(accountCoWbenContract);
            if (accountCoWbenContract.getType().equals(AccountConsts.ACCOUNT_ENTERPRISE)) {
                accountCompanyService.updateAccountStatus(accountCoWbenContract.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIFTY_ONE);
                return json;
            }
            accountCompanyService.updateAccountStatus(accountCoWbenContract.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIFTY_TWO);
        }
        return json;
    }

    @PostMapping("/company/saveTwelve")
    @ApiOperation(value = "公司开户第十二页插入/更新", httpMethod = "POST")
    public JsonMessage saveTwelve(@RequestBody CompanyCrsEntityContrModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 公司CRS实体自我证明表格
        model.getAccountCoCrsEntity().setAccountId(principal.getId());
        model.getAccountCoCrsEntity().setCreatedBy(principal.getId());
        model.getAccountCoCrsEntity().setUpdatedBy(principal.getId());
        // 公司crs实体（CRS-E）控权人信息
        for (AccountCoCrsEntityContr accountCoCrsEntityContr : model.getAccountCoCrsEntityContrList()) {
            accountCoCrsEntityContr.setAccountId(principal.getId());
            accountCoCrsEntityContr.setCreatedBy(principal.getId());
            accountCoCrsEntityContr.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            // 共同汇报标准（CRS）实体自我证明表格（CRS-E）
            accountCompanyService.saveTwelve(model);
            accountCompanyService.updateAccountStatus(model.getAccountCoCrsEntity().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TWELVE);
        }
        return json;
    }


    @PostMapping("/company/saveThirteen")
    @ApiOperation(value = "公司开户第十三页插入/更新", httpMethod = "POST")
    public JsonMessage saveThirteen(@RequestBody CompanyCrsControlModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // CRS控权人自我证明表格
        model.getAccountCoCrsControl().setAccountId(principal.getId());
        model.getAccountCoCrsControl().setCreatedBy(principal.getId());
        model.getAccountCoCrsControl().setUpdatedBy(principal.getId());
        // CRS控权人自我证明控权人编号
        for (AccountCoCrsControlNum accountCoCrsControlNum : model.getAccountCoCrsControlNumList()) {
            accountCoCrsControlNum.setAccountId(principal.getId());
        }
        // 地址信息
        for (AccountCoAddress accountCoAddress : model.getAccountCoAddressList()) {
            accountCoAddress.setAccountId(principal.getId());
            accountCoAddress.setCreatedBy(principal.getId());
            accountCoAddress.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
            accountCompanyService.saveThirteen(model);
            accountCompanyService.updateAccountStatus(model.getAccountCoCrsControl().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_THIRTEEN);
            if (null != model.getAccountCoCrsControl().getCoCrsEntityContrId()) {
                accountCompanyService.updateCrsEntityStatus(model.getAccountCoCrsControl().getCoCrsEntityContrId());
            }
        }
        return json;
    }


    @PostMapping("/company/saveFourteen")
    @ApiOperation(value = "公司开户第十四页插入/更新", httpMethod = "POST")
    public JsonMessage saveFourteen(@RequestBody List<AccountCoAgreement> accountCoAgreementList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountCoAgreement accountCoAgreement : accountCoAgreementList) {
            accountCoAgreement.setAccountId(principal.getId());
            accountCoAgreement.setCreatedBy(principal.getId());
            accountCoAgreement.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountCoAgreementList)) {
            // 公司协议纰漏信息
            accountCompanyService.saveFourteen(accountCoAgreementList);
            accountCompanyService.updateAccountStatus(accountCoAgreementList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FOURTEEN);
        }
        return json;
    }

    @PostMapping("/company/saveFifteen")
    @ApiOperation(value = "公司开户第十五页插入/更新", httpMethod = "POST")
    public JsonMessage saveFifteen(@RequestBody AccountCoInvestmentAccount accountCoInvestmentAccount) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountCoInvestmentAccount.setAccountId(principal.getId());
        accountCoInvestmentAccount.setCreatedBy(principal.getId());
        accountCoInvestmentAccount.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountCoInvestmentAccount)) {
            // 公司一体化投资管理账户
            accountCompanyService.saveFifteen(accountCoInvestmentAccount);
            accountCompanyService.updateAccountStatus(accountCoInvestmentAccount.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIFTEEN);
        }
        return json;
    }

    @PostMapping("/company/saveSixteen")
    @ApiOperation(value = "公司开户第十六页保存页数", httpMethod = "POST")
    public JsonMessage saveSixteen() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 为了展示 暂时不保存数据  保存页数
        accountCompanyService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_SIXTEEN);
        return json;
    }

    @PostMapping("/company/saveSeventeen")
    @ApiOperation(value = "公司开户第十七页保存页数", httpMethod = "POST")
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JsonMessage saveSeventeen() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 为了展示 暂时不保存数据  保存页数
        accountCompanyService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_SEVENTEEN);
        return json;
    }

    @PostMapping("/company/saveEighteen")
    @ApiOperation(value = "公司开户第十八页插入/更新", httpMethod = "POST")
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JsonMessage saveEighteen(@RequestBody List<AccountCoAmlCtf> accountCoAmlCtfList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountCoAmlCtf accountCoAmlCtf : accountCoAmlCtfList) {
            accountCoAmlCtf.setAccountId(principal.getId());
            accountCoAmlCtf.setCreatedBy(principal.getId());
            accountCoAmlCtf.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountCoAmlCtfList)) {
            // 公司 aml/ctf 风险问卷
            accountCompanyService.saveEighteen(accountCoAmlCtfList);
            accountCompanyService.updateAccountStatus(accountCoAmlCtfList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHTEEN);
        }
        return json;
    }


    @PostMapping("/company/saveNineteen")
    @ApiOperation(value = "公司开户第十九页插入/更新", httpMethod = "POST")
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public JsonMessage saveNineteen(@RequestBody AccountCoFile accountCoFile) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountCoFile.setAccountId(principal.getId());
        accountCoFile.setCreatedBy(principal.getId());
        accountCoFile.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountCoFile)) {
            String[] tempS3Path = accountCoFile.getFilePath().split("/");
            // s3 临时空间文件key
            String fileKey = "AccountFile/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "AccountFile/" + accountCoFile.getAccountId() + "/" + tempS3Path[tempS3Path.length - 1];
            Boolean transfer = amazonS3.transferObject(fileKey, releaseKey);
            Amazon amazon = properies.getAmazon();
            accountCoFile.setFilePath(amazon.getBucket().getReleaseUrl() + releaseKey);
            if (!transfer) {
                throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
            }
            // 保存上传文件
            accountCompanyService.saveNineteen(accountCoFile);
            accountCompanyService.updateAccountStatus(accountCoFile.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINETEEN);
        }
        return json;
    }

    @PostMapping("/company/openAccountFinish")
    @ApiOperation(value = "公司开户完成待审核", httpMethod = "POST")
    public JsonMessage openAccountFinish(String status) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (StringUtils.isNotBlank(status)) {
            // 开户完成 待审核 0
            if (account.getRegisterStatus() != 1) {
                accountCompanyService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_NORMAL);
            }
        }
        return json;
    }

    @GetMapping(value = "/company/getAgreement")
    @ApiOperation(value = "根据accountId获取公司协议纰漏", httpMethod = "GET")
    public JsonMessage findByAgreement() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        List<AccountCommonAgreement> agreements = accountCompanyService.findByAgreement(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, agreements);
    }


    @GetMapping(value = "/company/getFileUploadType")
    @ApiOperation(value = "根据accountId和语言获取公司文件上传类型", httpMethod = "GET")
    public JsonMessage getFileUploadType(String lang) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        UploadFileTypeModel model = accountCompanyService.getFileUploadType(principal.getId(), lang);
        return this.getJsonMessage(CommonEnums.SUCCESS, model);
    }


    @GetMapping(value = "/company/getPreSignedUrl")
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

    @GetMapping(value = "/company/generateUploadObjectPresignedUrl")
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
