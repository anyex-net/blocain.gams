package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.*;
import com.fintech.gams.account.service.*;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.StringUtils;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author yann
 * @date 2019-09-17 10:29
 * @Description
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "帐户管理")
public class AccountManagementController extends GenericController {

    @Autowired(required = false)
    AccountService accountService;
    @Autowired(required = false)
    RegionService regionService;
    @Autowired(required = false)
    DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountCoInstitutionalService accountCoInstitutionalService;
    @Autowired(required = false)
    private AccountCoAddressService accountCoAddressService;
    @Autowired(required = false)
    private AccountCoWealthSourceService accountCoWealthSourceService;
    @Autowired(required = false)
    private AccountCoContactService accountCoContactService;
    @Autowired(required = false)
    private AccountParInstitutionalService accountParInstitutionalService;
    @Autowired(required = false)
    private AccountParAddressService accountParAddressService;
    @Autowired(required = false)
    private AccountParWealthSourceService accountParWealthSourceService;
    @Autowired(required = false)
    private AccountParContactService accountParContactService;
    @Autowired(required = false)
    private AccountPerInfoService accountPerInfoService;
    @Autowired(required = false)
    private AccountPerAddressService accountPerAddressService;
    @Autowired(required = false)
    private AccountPerContactService accountPerContactService;
    @Autowired(required = false)
    private AccountPerWealthSourceService accountPerWealthSourceService;
    @Autowired(required = false)
    private AccountPerTaxesService accountPerTaxesService;
    @Autowired(required = false)
    private AccountPerAssetService accountPerAssetService;
    @Autowired(required = false)
    private AccountPerTradeExpService accountPerTradeExpService;
    @Autowired(required = false)
    private AccountPerIdentityService accountPerIdentityService;
    @Autowired(required = false)
    private AccountCoTaxesService accountCoTaxesService;
    @Autowired(required = false)
    private AccountCoAssetService accountCoAssetService;
    @Autowired(required = false)
    private AccountCoTradeExperienceService accountCoTradeExperienceService;
    @Autowired(required = false)
    private AccountCoPersonalService accountCoPersonalService;
    @Autowired(required = false)
    private AccountParTaxesService accountParTaxesService;
    @Autowired(required = false)
    private AccountParAssetService accountParAssetService;
    @Autowired(required = false)
    private AccountParTradeExperienceService accountParTradeExperienceService;
    @Autowired(required = false)
    private AccountParPersonalService accountParPersonalService;
    @Autowired(required = false)
    private AccountCoInvestmentTargetService accountCoInvestmentTargetService;
    @Autowired(required = false)
    private AccountParInvestmentTargetService accountParInvestmentTargetService;
    @Autowired(required = false)
    private AccountPerInvestTargetService accountPerInvestTargetService;

    @PostMapping(value = "/management/accountList")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "获取账户列表", httpMethod = "POST")
    public JsonMessage accountList(Pagination pagin, AccountManagementFindAccountListModel accountListModel) throws BusinessException
    {
        return this.getJsonMessage(CommonEnums.SUCCESS, accountService.findAccountList(pagin,accountListModel));
    }

    @GetMapping(value = "/management/basicInformation")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "获取账户基本信息", httpMethod = "GET")
    public JsonMessage basicInformation(Long accountId,Long unid,String lang) throws BusinessException
    {
        if(accountId ==null || unid == null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountManagementBasicInfoModel  basicInfo = new AccountManagementBasicInfoModel();
        //账户信息
        Account account =new Account();
        account.setUnid(unid);
        List<Account> accounts=accountService.findList(account);
        if(accounts.isEmpty()==false){
            //email
            basicInfo.setEmail(accounts.get(0).getEmail());
            //国家
            basicInfo.setCountry(regionService.findBysCode(accounts.get(0).getCountry()));
            //个人
            if(accounts.get(0).getAccountType().endsWith("personal")){
                basicInfo = getPerInfo(accountId,lang,basicInfo);
            }
            //公司
            if(accounts.get(0).getAccountType().endsWith("limitedLiablity")){
                basicInfo = getCoInfo(accountId,lang,basicInfo);
            }
            //合伙企业
            if(accounts.get(0).getAccountType().endsWith("partnerShip")){
                basicInfo = getParInfo(accountId,lang,basicInfo);
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,basicInfo);
    }

    @GetMapping(value = "/management/moreInformation")
    @RequiresPermissions("account:account:data")
    @ApiOperation(value = "获取账户更多信息", httpMethod = "GET")
    public JsonMessage moreInformation(Long accountId, String accountType, String lang) throws BusinessException
    {

        if(accountId == null || StringUtils.isBlank(accountType) || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountManagementMoreInfoModel infoModel =new AccountManagementMoreInfoModel();
        //税务居住地
        List taxes = new ArrayList();
        //资产
        List assets = new ArrayList();
        //交易经验
        List tradeExps = new ArrayList();
        //投资目标
        List investmentTargets = new ArrayList();
        //地址
        List address = new ArrayList();
        //联系信息
        List contacts = new ArrayList();
        //个人
        if(accountType.endsWith("personal")){
            //税务居住地
            AccountPerTaxes accountPerTaxes = new AccountPerTaxes();
            accountPerTaxes.setAccountId(accountId);
            taxes=accountPerTaxesService.findList(accountPerTaxes,lang);
            //资产
            AccountPerAsset accountPerAsset = new AccountPerAsset();
            accountPerAsset.setAccountId(accountId);
            assets = accountPerAssetService.findList(accountPerAsset);
            //交易经验
            AccountPerTradeExp accountPerTradeExp = new AccountPerTradeExp();
            accountPerTradeExp.setAccountId(accountId);
            tradeExps = accountPerTradeExpService.findList(accountPerTradeExp,lang);
            //投资目标
            AccountPerInvestTarget accountPerInvestTarget =new AccountPerInvestTarget();
            accountPerInvestTarget.setAccountId(accountId);
            investmentTargets = accountPerInvestTargetService.findList(accountPerInvestTarget,lang);
            //身份信息
            //根据accountId查询 个人身份信息
            AccountPerIdentity accountPerIdentity = new AccountPerIdentity();
            accountPerIdentity.setAccountId(accountId);
            List identitys = accountPerIdentityService.findList(accountPerIdentity,lang);
            List<AccountManagementIdentityModel> identityList = new ArrayList<>();
            CollectionUtils.addAll(identityList, new Object[identitys.size()]);
            Collections.copy(identityList, identitys);
            infoModel.setIdentityList(identityList);
        }
        //公司
        if(accountType.endsWith("limitedLiablity")){
            // 查出机构信息
            AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
            accountCoInstitutional.setAccountId(accountId);
            List<AccountCoInstitutional> accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
            if(accountCoInstitutionals.isEmpty()==false){
                // 查出机构税务居住地信息
                AccountCoInstitutional institutional = accountCoInstitutionals.get(0);
                AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
                accountCoTaxes.setAccountId(accountId);
                accountCoTaxes.setCoPersonalId(institutional.getId());
                taxes = accountCoTaxesService.findList(accountCoTaxes,lang);
                //资产
                AccountCoAsset accountCoAsset= new AccountCoAsset();
                accountCoAsset.setAccountId(accountId);
                assets = accountCoAssetService.findList(accountCoAsset);
                //交易经验
                AccountCoTradeExperience accountCoTradeExperience = new AccountCoTradeExperience();
                accountCoTradeExperience.setAccountId(accountId);
                tradeExps = accountCoTradeExperienceService.findList(accountCoTradeExperience,lang);
                //投资目标
                AccountCoInvestmentTarget accountCoInvestmentTarget =new AccountCoInvestmentTarget();
                accountCoInvestmentTarget.setAccountId(accountId);
                investmentTargets  = accountCoInvestmentTargetService.findList(accountCoInvestmentTarget,lang);
                //被授权人
                AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
                accountCoPersonal.setAccountId(accountId);
                accountCoPersonal.setPersonalType("authorizedPerson");
                if(accountCoPersonalService.findList(accountCoPersonal).isEmpty()==false){
                    AccountCoPersonalModel perInfo = getCoPerInfo(accountCoPersonalService.findList(accountCoPersonal).get(0),lang);
                    AccountManagementPersonalModel personal = new AccountManagementPersonalModel();
                    BeanUtils.copyProperties(perInfo,personal);
                    infoModel.setPersonal(personal);
                    if(perInfo.getId() != null){
                        //被授权人地址
                        AccountCoAddress accountCoAddress = new AccountCoAddress();
                        accountCoAddress.setAccountId(accountId);
                        accountCoAddress.setCoPersonalId(perInfo.getId());
                        address = accountCoAddressService.findList(accountCoAddress,lang);
                        //被授权人联系信息
                        AccountCoContact accountCoContact = new AccountCoContact();
                        accountCoContact.setAccountId(accountId);
                        accountCoContact.setCoPersonalId(perInfo.getId());
                        contacts = accountCoContactService.findList(accountCoContact,lang);
                    }

                }
            }
        }
        //合伙企业
        if(accountType.endsWith("partnerShip")){
            // 查出机构信息
            AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
            accountParInstitutional.setAccountId(accountId);
            List<AccountParInstitutional> parInstitutionals = accountParInstitutionalService.findList(accountParInstitutional);
            //取合伙企业机构税务居住地
            if (parInstitutionals.isEmpty()==false) {
                AccountParInstitutional institutional = parInstitutionals.get(0);
                AccountParTaxes accountParTaxes = new AccountParTaxes();
                accountParTaxes.setAccountId(accountId);
                accountParTaxes.setParPersonalId(institutional.getId());
                taxes = accountParTaxesService.findList(accountParTaxes,lang);
                //资产
                AccountParAsset accountParAsset = new AccountParAsset();
                accountParAsset.setAccountId(accountId);
                assets = accountParAssetService.findList(accountParAsset);
                //交易经验
                AccountParTradeExperience accountParTradeExperience = new AccountParTradeExperience();
                accountParTradeExperience.setAccountId(accountId);
                tradeExps= accountParTradeExperienceService.findList(accountParTradeExperience,lang);
                //投资目标
                AccountParInvestmentTarget accountParInvestmentTarget = new AccountParInvestmentTarget();
                accountParInvestmentTarget.setAccountId(accountId);
                investmentTargets = accountParInvestmentTargetService.findList(accountParInvestmentTarget,lang);
                //被授权人
                AccountParPersonal accountParPersonal = new AccountParPersonal();
                accountParPersonal.setAccountId(accountId);
                accountParPersonal.setPersonalType("authorizedPerson");
                if(accountParPersonalService.findList(accountParPersonal).isEmpty()==false){
                    AccountParPersonalModel parPersonalModel = getParPerInfo(accountParPersonalService.findList(accountParPersonal).get(0),lang);
                    AccountManagementPersonalModel personalModel = new AccountManagementPersonalModel();
                    BeanUtils.copyProperties(parPersonalModel,personalModel);
                    infoModel.setPersonal(personalModel);
                    if(parPersonalModel.getId() != null){
                        //被授权人地址
                        AccountParAddress accountParAddress = new AccountParAddress();
                        accountParAddress.setAccountId(accountId);
                        accountParAddress.setParPersonalId(parPersonalModel.getId());
                        address = accountParAddressService.findList(accountParAddress,lang);
                        //联系信息
                        AccountParContact accountParContact = new AccountParContact();
                        accountParContact.setAccountId(accountId);
                        accountParContact.setParPersonalId(parPersonalModel.getId());
                        contacts = accountParContactService.findList(accountParContact,lang);
                    }
                }
            }
        }
        //税务居住地
        infoModel.setTaxesList(taxes);
        //资产
        infoModel.setAssetList(assets);
        //交易经验
        infoModel.setTradeExperienceList(tradeExps);
        //投资目标
        infoModel.setInvestmentTargetList(investmentTargets);
        //地址
        infoModel.setAddressList(address);
        //联系信息
        infoModel.setContactList(contacts);
        return this.getJsonMessage(CommonEnums.SUCCESS,infoModel);
    }

    /**
     * 获取个人信息
     * @param accountId
     * @param lang
     * @param basicInfo
     * @return
     */
    private AccountManagementBasicInfoModel getPerInfo(Long accountId,String lang,AccountManagementBasicInfoModel basicInfo) {
        //根据accountId查询个人信息
        AccountPerInfo accountPerInfo = new AccountPerInfo();
        accountPerInfo.setAccountId(accountId);
        List<AccountPerInfo> accountPerInfos= accountPerInfoService.findList(accountPerInfo);
        //查找 个人信息 中关联的字典表信息

        if(accountPerInfos.isEmpty()==false){
            //个人信息
            AccountPerInfo perInfo = accountPerInfos.get(0);
            basicInfo.setAppellation(dictionaryService.findDictionary("appellationType",perInfo.getAppellation(),lang));
            basicInfo.setCustomerName(perInfo.getLastName()+" "+perInfo.getFirstName());
            basicInfo.setBirthday(perInfo.getBirthday());
            basicInfo.setGender(dictionaryService.findDictionary("gender",perInfo.getGender(),lang));
            //地址
            AccountPerAddress accountPerAddress = new AccountPerAddress();
            accountPerAddress.setAccountId(accountId);
            List addressModels=accountPerAddressService.findList(accountPerAddress,lang);
            basicInfo.setAddressList(addressModels);
            //联系信息
            AccountPerContact accountPerContact = new AccountPerContact();
            accountPerContact.setAccountId(accountId);
            List contacts = accountPerContactService.findList(accountPerContact,lang);
            basicInfo.setContactList(contacts);
            //财富
            AccountPerWealthSource accountPerWealthSource =new AccountPerWealthSource();
            accountPerWealthSource.setAccountId(accountId);
            List<AccountPerWealthSource> accountPerWealthSources = accountPerWealthSourceService.findList(accountPerWealthSource);
            //查找 个人财富来源 中关联的字典表信息
            List accountPerWealthSourceModels =new LinkedList<>();
            for(AccountPerWealthSource perIdentity:accountPerWealthSources){
                AccountPerWealthSourceModel accountPerWealthSourceModel = new AccountPerWealthSourceModel();
                BeanUtils.copyProperties(perIdentity,accountPerWealthSourceModel);
                if(null != perIdentity.getIncomeType()){
                    accountPerWealthSourceModel.setIncomeType(dictionaryService.findDictionary("personalWealthSource",perIdentity.getIncomeType(),lang));
                }
                accountPerWealthSourceModels.add(accountPerWealthSourceModel);
            }
            basicInfo.setWealthSourceList(accountPerWealthSourceModels);
        }
        return basicInfo;
    }

    /**
     * 获取公司信息
     * @param accountId
     * @param lang
     * @param basicInfo
     * @return
     */
    private AccountManagementBasicInfoModel getCoInfo(Long accountId,String lang,AccountManagementBasicInfoModel basicInfo) {
        //机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional> institutionals = accountCoInstitutionalService.findList(accountCoInstitutional);
        if (institutionals.isEmpty()==false) {
            basicInfo.setCustomerName(institutionals.get(0).getInstitutionalName());
            basicInfo.setUrl(institutionals.get(0).getWebsiteUrl());
            //机构地址
            AccountCoAddress accountCoAddress = new AccountCoAddress();
            accountCoAddress.setAccountId(accountId);
            accountCoAddress.setCoPersonalId(institutionals.get(0).getId());
            List accountCoAddressModels = accountCoAddressService.findList(accountCoAddress,lang);
            basicInfo.setAddressList(accountCoAddressModels);
            //财富来源
            AccountCoWealthSource accountCoWealthSource = new AccountCoWealthSource();
            accountCoWealthSource.setAccountId(accountId);
            List accountCoWealthSourceModels=accountCoWealthSourceService.findList(accountCoWealthSource,lang);
            basicInfo.setWealthSourceList(accountCoWealthSourceModels);
            //联系信息
            AccountCoContact accountCoContact = new AccountCoContact();
            accountCoContact.setAccountId(accountId);
            List accountCoContactModels = accountCoContactService.findList(accountCoContact,lang);
            basicInfo.setContactList(accountCoContactModels);
        }
        return basicInfo;
    }


    /**
     * 获取企业信息
     * @param accountId
     * @param lang
     * @param basicInfo
     * @return
     */
    private AccountManagementBasicInfoModel getParInfo(Long accountId,String lang,AccountManagementBasicInfoModel basicInfo) {
        //机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> institutionals = accountParInstitutionalService.findList(accountParInstitutional);
        if (institutionals.isEmpty()==false) {
            basicInfo.setCustomerName(institutionals.get(0).getInstitutionalName());
            basicInfo.setUrl(institutionals.get(0).getWebsiteUrl());
            //机构地址
            AccountParAddress accountParAddress = new AccountParAddress();
            accountParAddress.setAccountId(accountId);
            List addressModels=accountParAddressService.findList(accountParAddress,lang);
            accountParAddress.setParPersonalId(institutionals.get(0).getId());
            basicInfo.setAddressList(addressModels);
            //财富来源
            AccountParWealthSource accountParWealthSource = new AccountParWealthSource();
            accountParWealthSource.setAccountId(accountId);
            List wealthSources=accountParWealthSourceService.findList(accountParWealthSource,lang);
            basicInfo.setWealthSourceList(wealthSources);
            //联系信息
            AccountParContact accountParContact = new AccountParContact();
            accountParContact.setAccountId(accountId);
            accountParContact.setParPersonalId(institutionals.get(0).getId());
            List contacts = accountParContactService.findList(accountParContact,lang);
            basicInfo.setContactList(contacts);
        }
        return basicInfo;
    }

    /**
     * 获取公司个人资料
     * @param personal
     * @return
     */
    private AccountCoPersonalModel getCoPerInfo(AccountCoPersonal personal,String lang){
        AccountCoPersonalModel accountPerInfoModel= new AccountCoPersonalModel()
                .convertToModel(personal)
                .setAppellation(dictionaryService.findDictionary("appellationType",personal.getAppellation(),lang))
                .setPersonalType(dictionaryService.findDictionary("personalType",personal.getPersonalType(),lang))
                .setNationality(regionService.findBysCode(personal.getNationality()))
                .setBirthCountry(regionService.findBysCode(personal.getBirthCountry()))
                .setPersonalTypeTwo(dictionaryService.findDictionary("personalType",personal.getPersonalTypeTwo(),lang));
        if(personal.getCompanyPosition() != null && StringUtils.isBlank(personal.getCompanyPosition())==false){
            List<String> stringList = Arrays.asList(personal.getCompanyPosition().split(","));
            List<Dictionary> dictionaries =new ArrayList<>();
            for(String str: stringList){
                dictionaries.add(dictionaryService.findDictionary("otherOfficer",str,lang));
            }
            accountPerInfoModel.setCompanyPosition(dictionaries);
            if(accountPerInfoModel.getCompanyPosition() == null || accountPerInfoModel.getCompanyPosition().get(0) == null){
                Dictionary dictionary =new Dictionary();
                dictionary.setDictDest(personal.getCompanyPosition());
                List<Dictionary> list = Lists.newArrayList(dictionary);
                accountPerInfoModel.setCompanyPosition(list);
            }
        }
        return accountPerInfoModel;
    }

    /**
     * 获取企业个人资料
     * @param personal
     * @return
     */
    private AccountParPersonalModel getParPerInfo(AccountParPersonal personal,String lang){
        AccountParPersonalModel accountPerInfoModel= new AccountParPersonalModel()
                .convertToModel(personal)
                .setAppellation(dictionaryService.findDictionary("appellationType",personal.getAppellation(),lang))
                .setPersonalType(dictionaryService.findDictionary("personalType",personal.getPersonalType(),lang))
                .setNationality(regionService.findBysCode(personal.getNationality()))
                .setBirthCountry(regionService.findBysCode(personal.getBirthCountry()))
                .setPersonalTypeTwo(dictionaryService.findDictionary("personalInfo",personal.getPersonalTypeTwo(),lang));
        if(personal.getCompanyPosition() != null && StringUtils.isBlank( personal.getCompanyPosition())==false){
            List<String> stringList = Arrays.asList(personal.getCompanyPosition().split(","));
            List<Dictionary> dictionaries =new ArrayList<>();
            for(String str: stringList){
                dictionaries.add(dictionaryService.findDictionary("otherOfficer",str,lang));
            }
            accountPerInfoModel.setCompanyPosition(dictionaries);
            if(accountPerInfoModel.getCompanyPosition() == null || accountPerInfoModel.getCompanyPosition().get(0) == null){
                Dictionary dictionary =new Dictionary();
                dictionary.setDictDest(personal.getCompanyPosition());
                List<Dictionary> list = Lists.newArrayList(dictionary);
                accountPerInfoModel.setCompanyPosition(list);
            }
        }
        return accountPerInfoModel;
    }

}
