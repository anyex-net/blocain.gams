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
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: hcj
 * @Date: 2019/7/17 15:30
 * @Description
 */
@RestController
@Api(description = "合伙企业开户控制器")
@RequestMapping(GlobalConst.OPENACCOUNT)
public class AccountPartnerShipController extends GenericController {

    @Autowired(required = false)
    private AccountPartnerShipService accountPartnerShipService;
    @Autowired(required = false)
    private AccountParPersonalService accountParPersonalService;
    @Autowired
    private AmazonS3 amazonS3;
    @Autowired
    private GlobalProperies properies;
    @Autowired(required = false)
    private AccountService accountService;

    @PostMapping("/partnerShip/saveOne")
    @ApiOperation(value = "合伙企业开户第一页插入/更新", httpMethod = "POST")
    public JsonMessage saveOne(@RequestBody PartnerShipInfoModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 机构信息
        model.getAccountParInstitutional().setAccountId(principal.getId());
        model.getAccountParInstitutional().setCreatedBy(principal.getId());
        model.getAccountParInstitutional().setUpdatedBy(principal.getId());
        // 地址信息
        for (AccountParAddress accountParAddress : model.getAccountParAddressList()) {
            accountParAddress.setAccountId(principal.getId());
            accountParAddress.setCreatedBy(principal.getId());
            accountParAddress.setUpdatedBy(principal.getId());
        }
        // 联系信息
        for (AccountParContact accountParContact : model.getAccountParContactList()) {
            accountParContact.setAccountId(principal.getId());
            accountParContact.setCreatedBy(principal.getId());
            accountParContact.setUpdatedBy(principal.getId());
        }
        // 税务居住地
        for (AccountParTaxes accountParTaxes : model.getAccountParTaxesList()) {
            accountParTaxes.setAccountId(principal.getId());
            accountParTaxes.setCreatedBy(principal.getId());
            accountParTaxes.setUpdatedBy(principal.getId());
        }
        // 财富来源
        for (AccountParWealthSource accountParWealthSource : model.getAccountParWealthSourceList()) {
            accountParWealthSource.setAccountId(principal.getId());
            accountParWealthSource.setCreatedBy(principal.getId());
            accountParWealthSource.setUpdatedBy(principal.getId());
        }
        // 帐户组合信息
        model.getAccountParAccountPortfolio().setAccountId(principal.getId());
        model.getAccountParAccountPortfolio().setCreatedBy(principal.getId());
        model.getAccountParAccountPortfolio().setUpdatedBy(principal.getId());
        if (beanValidator(json, model)) {
            accountPartnerShipService.saveOne(model);
            accountPartnerShipService.updateAccountStatus(model.getAccountParInstitutional().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_ONE);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveTwo")
    @ApiOperation(value = "合伙企业开户第二页插入/更新", httpMethod = "POST")
    public JsonMessage saveTwo(@RequestBody PartnerShipAuthorizedModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 个人信息
        model.getAccountParPersonal().setAccountId(principal.getId());
        model.getAccountParPersonal().setCreatedBy(principal.getId());
        model.getAccountParPersonal().setUpdatedBy(principal.getId());
        // 地址信息
        for (AccountParAddress accountParAddress : model.getAccountParAddressList()) {
            accountParAddress.setAccountId(principal.getId());
            accountParAddress.setCreatedBy(principal.getId());
            accountParAddress.setUpdatedBy(principal.getId());
        }
        // 联系信息
        for (AccountParContact accountParContact : model.getAccountParContactList()) {
            accountParContact.setAccountId(principal.getId());
            accountParContact.setCreatedBy(principal.getId());
            accountParContact.setUpdatedBy(principal.getId());
        }
        // 身份信息
        model.getAccountParIdentity().setAccountId(principal.getId());
        model.getAccountParIdentity().setCreatedBy(principal.getId());
        model.getAccountParIdentity().setUpdatedBy(principal.getId());
        // 税务居住地
        for (AccountParTaxes accountParTaxes : model.getAccountParTaxesList()) {
            accountParTaxes.setAccountId(principal.getId());
            accountParTaxes.setCreatedBy(principal.getId());
            accountParTaxes.setUpdatedBy(principal.getId());
        }
        // 安全问题
        for (AccountParSafeQuestion parSafeQuestion : model.getAccountParSafeQuestionList()) {
            parSafeQuestion.setAccountId(principal.getId());
            parSafeQuestion.setCreatedBy(principal.getId());
            parSafeQuestion.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            accountPartnerShipService.saveTwo(model);
            accountPartnerShipService.updateAccountStatus(model.getAccountParPersonal().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TWO);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveThree")
    @ApiOperation(value = "合伙企业开户第三页插入/更新", httpMethod = "POST")
    public JsonMessage saveThree(@RequestBody AccountParTaxClassification accountParTaxClassification) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountParTaxClassification.setAccountId(principal.getId());
        accountParTaxClassification.setCreatedBy(principal.getId());
        accountParTaxClassification.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountParTaxClassification)) {
            // 税收分类
            accountPartnerShipService.saveThree(accountParTaxClassification);
            accountPartnerShipService.updateAccountStatus(accountParTaxClassification.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_THREE);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveFour")
    @ApiOperation(value = "合伙企业开户第四页插入/更新", httpMethod = "POST")
    public JsonMessage saveFour(@RequestBody List<AccountParRegulatory> accountParRegulatoryList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountParRegulatory accountParRegulatory : accountParRegulatoryList) {
            accountParRegulatory.setAccountId(principal.getId());
        }
        if (beanValidator(json, accountParRegulatoryList)) {
            // 合伙企业 规管信息
            accountPartnerShipService.saveFour(accountParRegulatoryList);
            accountPartnerShipService.updateAccountStatus(accountParRegulatoryList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FOUR);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveFive")
    @ApiOperation(value = "合伙企业开户第五页插入/更新", httpMethod = "POST")
    public JsonMessage saveFive(@RequestBody AccountParBrokerPermission accountParBrokerPermission) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountParBrokerPermission.setAccountId(principal.getId());
        accountParBrokerPermission.setCreatedBy(principal.getId());
        accountParBrokerPermission.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountParBrokerPermission)) {
            // 合伙企业经济商监管许可
            accountPartnerShipService.saveFive(accountParBrokerPermission);
            accountPartnerShipService.updateAccountStatus(accountParBrokerPermission.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIVE);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveSix")
    @ApiOperation(value = "合伙企业开户第六页插入/更新", httpMethod = "POST")
    public JsonMessage saveSix(@RequestBody List<AccountParBrokerInfo> accountParBrokerInfoList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountParBrokerInfo accountParBrokerInfo : accountParBrokerInfoList) {
            accountParBrokerInfo.setAccountId(principal.getId());
            accountParBrokerInfo.setCreatedBy(principal.getId());
            accountParBrokerInfo.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountParBrokerInfoList)) {
            // 合伙企业经济商信息
            accountPartnerShipService.saveSix(accountParBrokerInfoList);
            accountPartnerShipService.updateAccountStatus(accountParBrokerInfoList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_SIX);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveSeven")
    @ApiOperation(value = "合伙企业第七页插入/更新", httpMethod = "POST")
    public JsonMessage saveSeven(@RequestBody PartnerShipAssetModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 资产信息
        model.getAccountParAsset().setAccountId(principal.getId());
        model.getAccountParAsset().setCreatedBy(principal.getId());
        model.getAccountParAsset().setUpdatedBy(principal.getId());
        // 投资目标信息
        model.getAccountParInvestmentTarget().setAccountId(principal.getId());
        model.getAccountParInvestmentTarget().setCreatedBy(principal.getId());
        model.getAccountParInvestmentTarget().setUpdatedBy(principal.getId());
        // 交易经验信息
        for (AccountParTradeExperience accountParTradeExperience : model.getAccountParTradeExperienceList()) {
            accountParTradeExperience.setAccountId(principal.getId());
            accountParTradeExperience.setCreatedBy(principal.getId());
            accountParTradeExperience.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            accountPartnerShipService.saveSeven(model);
            accountPartnerShipService.updateAccountStatus(model.getAccountParAsset().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_SEVEN);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveEight")
    @ApiOperation(value = "合伙企业开户第八页插入/更新", httpMethod = "POST")
    public JsonMessage saveEight(@RequestBody PartnerShipRegisteredOwnerModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (PartnerShipRegisteredOwnerInfoModel partnerShipRegisteredOwnerInfoModel : model.getPartnerShipRegisteredOwnerInfoModelList()) {
            // 个人信息
            partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().setAccountId(principal.getId());
            partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().setCreatedBy(principal.getId());
            partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().setUpdatedBy(principal.getId());
            // 地址信息
            for (AccountParAddress accountParAddress : partnerShipRegisteredOwnerInfoModel.getAccountParAddressList()) {
                accountParAddress.setAccountId(principal.getId());
                accountParAddress.setCreatedBy(principal.getId());
                accountParAddress.setUpdatedBy(principal.getId());
            }
            // 联系信息
            for (AccountParContact accountParContact : partnerShipRegisteredOwnerInfoModel.getAccountParContactList()) {
                accountParContact.setAccountId(principal.getId());
                accountParContact.setCreatedBy(principal.getId());
                accountParContact.setUpdatedBy(principal.getId());
            }
            // 身份信息 个人股东才有身份信息 ：individualShareholder
            if (partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getPersonalType().endsWith(AccountConsts.ACCOUNT_PERSONAL_TYPE)) {
                if (null != partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().getIssuingCountry()
                        && null != partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().getIdCardType()) {
                    partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().setAccountId(principal.getId());
                    partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().setCreatedBy(principal.getId());
                    partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().setUpdatedBy(principal.getId());
                }
            }
            // 税务居住地
            for (AccountParTaxes accountParTaxes : partnerShipRegisteredOwnerInfoModel.getAccountParTaxesList()) {
                accountParTaxes.setAccountId(principal.getId());
                accountParTaxes.setCreatedBy(principal.getId());
                accountParTaxes.setUpdatedBy(principal.getId());
            }
        }
        if (beanValidator(json, model)) {
            accountPartnerShipService.saveEight(model);
            accountPartnerShipService.updateAccountStatus(model.getPartnerShipRegisteredOwnerInfoModelList().get(0).getAccountParPersonal().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHT);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveEightPage")
    @ApiOperation(value = "合伙企业开户第八页保存页数", httpMethod = "POST")
    public JsonMessage saveEightPage() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 第八页不保存也行
        accountPartnerShipService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHT);
        return json;
    }

    @PutMapping("/partnerShip/removeEight")
    @ApiOperation(value = "删除合伙企业开户第八页通过合伙企业个人信息的id", httpMethod = "PUT")
    public JsonMessage removeEight(Long id) {
        accountPartnerShipService.removeEight(id);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }


    @PostMapping("/partnerShip/saveNine")
    @ApiOperation(value = "合伙企业开户第九页插入/更新", httpMethod = "POST")
    public JsonMessage saveNine(@RequestBody PartnerShipBeneficiaryOwnersModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (PartnerShipBeneficiaryOwnersInfoModel partnerShipBeneficiaryOwnersInfoModel : model.getPartnerShipBeneficiaryOwnersInfoModelList()) {
            // 个人信息
            partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().setAccountId(principal.getId());
            partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().setCreatedBy(principal.getId());
            partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().setUpdatedBy(principal.getId());
            // 地址信息
            for (AccountParAddress accountParAddress : partnerShipBeneficiaryOwnersInfoModel.getAccountParAddressList()) {
                accountParAddress.setAccountId(principal.getId());
                accountParAddress.setCreatedBy(principal.getId());
                accountParAddress.setUpdatedBy(principal.getId());
            }
            if (null != partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().getIssuingCountry()
                    && null != partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().getIdCardType()) {
                // 身份信息
                partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().setAccountId(principal.getId());
                partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().setCreatedBy(principal.getId());
                partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().setUpdatedBy(principal.getId());
            }
            // 税务居住地
            for (AccountParTaxes accountParTaxes : partnerShipBeneficiaryOwnersInfoModel.getAccountParTaxesList()) {
                accountParTaxes.setAccountId(principal.getId());
                accountParTaxes.setCreatedBy(principal.getId());
                accountParTaxes.setUpdatedBy(principal.getId());
            }
        }
        if (beanValidator(json, model)) {
            if (null == model.getStatus()) {
                accountPartnerShipService.saveNine(model);
            }
        }
        return json;
    }

    @PostMapping("/partnerShip/updatePersonalByPrimaryKeySelective")
    @ApiOperation(value = "合伙企业开户第九页/修改引用经营负责人信息", httpMethod = "POST")
    public JsonMessage updatePersonalByPrimaryKeySelective(Long id, String position, String personalTypeTwo) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (null == id || null == position || null == personalTypeTwo) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        accountPartnerShipService.updatePersonalByPrimaryKeySelective(id, position, personalTypeTwo);
        return json;
    }


    @PostMapping("/partnerShip/saveNinePage")
    @ApiOperation(value = "合伙企业开户第九页保存页数", httpMethod = "POST")
    public JsonMessage saveNinePage() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 至少有一名经营负责人
        AccountParPersonal personal = new AccountParPersonal();
        personal.setAccountId(principal.getId());
        personal.setPersonalType(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL);
        List<AccountParPersonal> personalList = accountParPersonalService.findList(personal);
        if (null == personalList || personalList.size() <= 0) {
            AccountParPersonal personalTwo = new AccountParPersonal();
            personal.setAccountId(principal.getId());
            personal.setPersonalTypeTwo(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL);
            if (null == accountParPersonalService.findList(personalTwo) || accountParPersonalService.findList(personalTwo).size() <= 0) {
                throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
            }
        }
        accountPartnerShipService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINE);
        return json;
    }

    @PutMapping("/partnerShip/removeNine")
    @ApiOperation(value = "删除合伙企业开户第九页通过合伙企业个人信息的id", httpMethod = "PUT")
    public JsonMessage removeNine(Long id) {
        accountPartnerShipService.removeNine(id);
        return this.getJsonMessage(CommonEnums.SUCCESS);
    }


    @PostMapping("/partnerShip/saveTen")
    @ApiOperation(value = "合伙企业开户第十页插入/更新", httpMethod = "POST")
    public JsonMessage saveTen(@RequestBody PartnerShipSecurityOfficerModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountParSecurityOfficer accountParSecurityOfficer : model.getAccountParSecurityOfficerList()) {
            accountParSecurityOfficer.setAccountId(principal.getId());
            accountParSecurityOfficer.setCreatedBy(principal.getId());
            accountParSecurityOfficer.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
                // 合伙企业 安全官
            accountPartnerShipService.saveTen(model);
            accountPartnerShipService.updateAccountStatus(model.getAccountParSecurityOfficerList().get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TEN);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveTenPage")
    @ApiOperation(value = "合伙企业开户第十页保存页数", httpMethod = "POST")
    public JsonMessage saveTenPage() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 第十页不保存也行
        accountPartnerShipService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_TEN);
        return json;
    }

    @PostMapping("/partnerShip/saveEleven")
    @ApiOperation(value = "合伙企业开户第十一页插入/更新", httpMethod = "POST")
    public JsonMessage saveEleven(@RequestBody AccountParWbenContract accountParWbenContract) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountParWbenContract.setAccountId(principal.getId());
        accountParWbenContract.setCreatedBy(principal.getId());
        accountParWbenContract.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountParWbenContract)) {
            // w8Ben 表格
            accountPartnerShipService.saveEleven(accountParWbenContract);
            if (accountParWbenContract.getType().equals(AccountConsts.ACCOUNT_ENTERPRISE)) {
                accountPartnerShipService.updateAccountStatus(accountParWbenContract.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIFTY_ONE);
                return json;
            }
            accountPartnerShipService.updateAccountStatus(accountParWbenContract.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIFTY_TWO);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveTwelve")
    @ApiOperation(value = "合伙企业开户第十二页插入/更新", httpMethod = "POST")
    public JsonMessage saveTwelve(@RequestBody PartnerShipCrsEntityContrModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 合伙企业CRS实体自我证明表格
        model.getAccountParCrsEntity().setAccountId(principal.getId());
        model.getAccountParCrsEntity().setCreatedBy(principal.getId());
        model.getAccountParCrsEntity().setUpdatedBy(principal.getId());
        // 合伙企业crs实体（CRS-E）控权人信息
        for (AccountParCrsEntityContr accountParCrsEntityContr : model.getAccountParCrsEntityContrList()) {
            accountParCrsEntityContr.setAccountId(principal.getId());
            accountParCrsEntityContr.setCreatedBy(principal.getId());
            accountParCrsEntityContr.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            // 共同汇报标准（CRS）实体自我证明表格（CRS-E）
            accountPartnerShipService.saveTwelve(model);
            accountPartnerShipService.updateAccountStatus(model.getAccountParCrsEntity().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_TWELVE);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveThirteen")
    @ApiOperation(value = "合伙企业开户第十三页插入/更新", httpMethod = "POST")
    public JsonMessage saveThirteen(@RequestBody PartnerShipCrsControlModel model) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // CRS控权人自我证明表格
        model.getAccountParCrsControl().setAccountId(principal.getId());
        model.getAccountParCrsControl().setCreatedBy(principal.getId());
        model.getAccountParCrsControl().setUpdatedBy(principal.getId());
        // CRS控权人自我证明控权人编号
        for (AccountParCrsControlNum accountParCrsControlNum : model.getAccountParCrsControlNumList()) {
            accountParCrsControlNum.setAccountId(principal.getId());
        }
        // 地址信息
        for (AccountParAddress accountParAddress : model.getAccountParAddressList()) {
            accountParAddress.setAccountId(principal.getId());
            accountParAddress.setCreatedBy(principal.getId());
            accountParAddress.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, model)) {
            // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
            accountPartnerShipService.saveThirteen(model);
            accountPartnerShipService.updateAccountStatus(model.getAccountParCrsControl().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_THIRTEEN);
            if (null != model.getAccountParCrsControl().getParCrsEntityContrId()) {
                accountPartnerShipService.updateCrsEntityStatus(model.getAccountParCrsControl().getParCrsEntityContrId());
            }
        }
        return json;
    }


    @PostMapping("/partnerShip/saveFourteen")
    @ApiOperation(value = "合伙企业开户第十四页插入/更新", httpMethod = "POST")
    public JsonMessage saveFourteen(@RequestBody List<AccountParAgreement> accountParAgreementList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountParAgreement accountParAgreement : accountParAgreementList) {
            accountParAgreement.setAccountId(principal.getId());
            accountParAgreement.setCreatedBy(principal.getId());
            accountParAgreement.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountParAgreementList)) {
            // 协议纰漏
            accountPartnerShipService.saveFourteen(accountParAgreementList);
            accountPartnerShipService.updateAccountStatus(accountParAgreementList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FOURTEEN);
        }
        return json;
    }

    @PostMapping("/partnerShip/saveFifteen")
    @ApiOperation(value = "合伙企业开户第十五页插入/更新", httpMethod = "POST")
    public JsonMessage saveFifteen(@RequestBody AccountParInvestmentAccount accountParInvestmentAccount) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountParInvestmentAccount.setAccountId(principal.getId());
        accountParInvestmentAccount.setCreatedBy(principal.getId());
        accountParInvestmentAccount.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountParInvestmentAccount)) {
            // 合伙企业一体化投资管理账户
            accountPartnerShipService.saveFifteen(accountParInvestmentAccount);
            accountPartnerShipService.updateAccountStatus(accountParInvestmentAccount.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_FIFTEEN);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveSixteen")
    @ApiOperation(value = "合伙企业开户第十六页保存页数", httpMethod = "POST")
    public JsonMessage saveSixteen() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 为了展示 暂时不保存数据  保存页数
        accountPartnerShipService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_SIXTEEN);
        return json;
    }


    @PostMapping("/partnerShip/saveSeventeen")
    @ApiOperation(value = "合伙企业开户第十七页保存页数", httpMethod = "POST")
    public JsonMessage saveSeventeen() throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        // 为了展示 暂时不保存数据  保存页数
        accountPartnerShipService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_PAGE_SEVENTEEN);
        return json;
    }


    @PostMapping("/partnerShip/saveEighteen")
    @ApiOperation(value = "合伙企业开户第十八页插入/更新", httpMethod = "POST")
    public JsonMessage saveEighteen(@RequestBody List<AccountParAmlCtf> accountParAmlCtfList) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        for (AccountParAmlCtf accountParAmlCtf : accountParAmlCtfList) {
            accountParAmlCtf.setAccountId(principal.getId());
            accountParAmlCtf.setCreatedBy(principal.getId());
            accountParAmlCtf.setUpdatedBy(principal.getId());
        }
        if (beanValidator(json, accountParAmlCtfList)) {
            // 合伙企业 aml/ctf 风险问卷
            accountPartnerShipService.saveEighteen(accountParAmlCtfList);
            accountPartnerShipService.updateAccountStatus(accountParAmlCtfList.get(0).getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_EIGHTEEN);
        }
        return json;
    }


    @PostMapping("/partnerShip/saveNineteen")
    @ApiOperation(value = "合伙企业开户第十九页插入/更新", httpMethod = "POST")
    public JsonMessage saveNineteen(@RequestBody AccountParFile accountParFile) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        accountParFile.setAccountId(principal.getId());
        accountParFile.setCreatedBy(principal.getId());
        accountParFile.setUpdatedBy(principal.getId());
        if (beanValidator(json, accountParFile)) {
            String[] tempS3Path = accountParFile.getFilePath().split("/");
            // s3 临时空间文件key
            String fileKey = "AccountFile/" + tempS3Path[tempS3Path.length - 1];
            // s3 正式空间文件key
            String releaseKey = "AccountFile/" + accountParFile.getAccountId() + "/" + tempS3Path[tempS3Path.length - 1];
            Boolean transfer = amazonS3.transferObject(fileKey, releaseKey);
            Amazon amazon = properies.getAmazon();
            accountParFile.setFilePath(amazon.getBucket().getReleaseUrl() + releaseKey);
            if (!transfer) {
                throw new BusinessException(CommonEnums.ERROR_AWS_FILE_TRANSFER);
            }
            // 保存上传文件
            accountPartnerShipService.saveNineteen(accountParFile);
            accountPartnerShipService.updateAccountStatus(accountParFile.getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINETEEN);
        }
        return json;
    }

    @PostMapping("/partnerShip/openAccountFinish")
    @ApiOperation(value = "合伙企业开户完成待审核", httpMethod = "POST")
    public JsonMessage openAccountFinish(String status) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        Account account = accountService.selectByPrimaryKey(principal.getId());
        if (StringUtils.isNotBlank(status)) {
            // 开户完成 待审核 0
            if (account.getRegisterStatus() != 1) {
                accountPartnerShipService.updateAccountStatus(principal.getId(), AccountConsts.ACCOUNT_STATUS_NORMAL);
            }
        }
        return json;
    }


    @GetMapping(value = "/partnerShip/getFileUploadType")
    @ApiOperation(value = "根据accountId和语言获取合伙企业文件上传类型", httpMethod = "GET")
    public JsonMessage getFileUploadType(String lang) throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        UploadFileTypeModel model = accountPartnerShipService.getFileUploadType(principal.getId(), lang);
        return this.getJsonMessage(CommonEnums.SUCCESS, model);
    }


    @GetMapping(value = "/partnerShip/getAgreement")
    @ApiOperation(value = "根据accountId获取合伙企业协议纰漏", httpMethod = "GET")
    public JsonMessage findByAgreement() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        List<AccountCommonAgreement> agreements = accountPartnerShipService.findByAgreement(principal.getId());
        return this.getJsonMessage(CommonEnums.SUCCESS, agreements);
    }


    @GetMapping(value = "/partnerShip/getPreSignedUrl")
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

    @GetMapping(value = "/partnerShip/generateUploadObjectPresignedUrl")
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
