package com.fintech.gams.controller.account;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.*;
import com.fintech.gams.account.service.*;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.PdfUtils;
import com.fintech.gams.utils.StringUtils;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author yann
 * @date 2019-08-07 10:16
 * @Description
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "帐户企业开户管理")
public class AccountParRegisterOpenController extends GenericController {

    @Autowired(required = false)
    private AccountParAccountPortfolioService accountParAccountPortfolioService;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountParAddressService accountParAddressService;
    @Autowired(required = false)
    private AccountParInstitutionalService accountParInstitutionalService;
    @Autowired(required = false)
    private RegionService regionService;
    @Autowired(required = false)
    private AccountParAssetService accountParAssetService;
    @Autowired(required = false)
    private AccountParBrokerInfoService accountParBrokerInfoService;
    @Autowired(required = false)
    private AccountParBrokerPermissionService accountParBrokerPermissionService;
    @Autowired(required = false)
    private AccountParContactService accountParContactService;
    @Autowired(required = false)
    private AccountParCrsControlService accountParCrsControlService;
    @Autowired(required = false)
    private AccountParCrsEntityService accountParCrsEntityService;
    @Autowired(required = false)
    private AccountParFileService accountParFileService;
    @Autowired(required = false)
    private AccountParIdentityService accountParIdentityService;
    @Autowired(required = false)
    private AccountParPersonalService accountParPersonalService;
    @Autowired(required = false)
    private AccountParInvestmentAccountService accountParInvestmentAccountService;
    @Autowired(required = false)
    private AccountParInvestmentTargetService accountParInvestmentTargetService;
    @Autowired(required = false)
    private AccountParTaxesService accountParTaxesService;
    @Autowired(required = false)
    private AccountParRegulatoryService accountParRegulatoryService;
    @Autowired(required = false)
    private AccountParSafeQuestionService accountParSafeQuestionService;
    @Autowired(required = false)
    private AccountParSecurityOfficerService accountParSecurityOfficerService;
    @Autowired(required = false)
    private AccountParTaxClassificationService accountParTaxClassificationService;
    @Autowired(required = false)
    private AccountParTradeExperienceService accountParTradeExperienceService;
    @Autowired(required = false)
    private AccountParWbenContractService accountParWbenContractService;
    @Autowired(required = false)
    private AccountParWealthSourceService accountParWealthSourceService;
    @Autowired(required = false)
    private UploadFileTypeService uploadFileTypeService;
    @Autowired(required = false)
    private AccountParCrsControlNumService accountParCrsControlNumService;
    @Autowired(required = false)
    private AccountParCrsEntityContrService accountParCrsEntityContrService;
    @Autowired(required = false)
    AccountService accountService;
    @Autowired(required = false)
    PdfUtils pdfUtils;

    @RequestMapping(value = "/registerOpen/partnerShip/pdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void pdfDownload(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("Partnership.ftl",parPdf(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/partnerShip/pdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void pdfPreview(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("Partnership.ftl",parPdf(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/partnerShip/w8Pdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void w8PdfDownload(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Map<String,Object> data = W8Pdf(accountId,lang);
        List<AccountParWbenContract> wbenContracts = (List<AccountParWbenContract>) data.get("w8");
        switch (wbenContracts.get(0).getType()){
            case "enterprise":
                pdfUtils.download("PartnershipW8E.ftl",data,response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
                break;
            case "partnership (must have at least 2 owners)":
                pdfUtils.download("PartnershipW8IMY.ftl",data,response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
                break;
        }
    }
    @RequestMapping(value = "/registerOpen/partnerShip/w8Pdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void w8PdfPreview(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Map<String,Object> data = W8Pdf(accountId,lang);
        List<AccountParWbenContract> wbenContracts = (List<AccountParWbenContract>) data.get("w8");
        switch (wbenContracts.get(0).getType()){
            case "enterprise":
                pdfUtils.preview("PartnershipW8E.ftl",data,response,"logo.png","SourceHanSans",".ttf");
                break;
            case "partnership (must have at least 2 owners)":
                pdfUtils.preview("PartnershipW8IMY.ftl",data,response,"logo.png","SourceHanSans",".ttf");
                break;
        }
    }

    @RequestMapping(value = "/registerOpen/partnerShip/crsEntityPdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsEntityPdfPdfDownload(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("PartnershipCrsE.ftl",CrsEntityPdf(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/partnerShip/crsEntityPdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsEntityPdfPdfPreview(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("PartnershipCrsE.ftl",CrsEntityPdf(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/partnerShip/crsCpPdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsCpPdfDownload(Long accountId,Long crsEntityContrId,String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || crsEntityContrId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("PartnershipCrsCp.ftl",crsCpPdf(accountId,crsEntityContrId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/partnerShip/crsCpPdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsCpPdfPreview(Long accountId, Long crsEntityContrId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || crsEntityContrId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("PartnershipCrsCp.ftl",crsCpPdf(accountId,crsEntityContrId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findInstitutional")
    @ApiOperation(value = "根据AccountId取合伙企业账户机构信息", httpMethod = "GET")
    public JsonMessage findParInstitutional(Long accountId , String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParInstitutional(accountParInstitutionalService.findList((accountParInstitutional)),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findInstitutionalContact")
    @ApiOperation(value = "根据AccountId取合伙企业账户机构联系信息", httpMethod = "GET")
    public JsonMessage findParInstitutionalParContact(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        // 查出机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> parInstitutionals = accountParInstitutionalService.findList(accountParInstitutional);
        //取合伙企业机构联系信息
        List<AccountParContactModel> accountParContactModels =new ArrayList<>();
        for(AccountParInstitutional institutional : parInstitutionals){
            if(null != institutional.getId()){
                AccountParContact accountParContact = new AccountParContact();
                accountParContact.setAccountId(accountId);
                accountParContact.setParPersonalId(institutional.getId());
                accountParContactModels.addAll(accountParContactService.findList(accountParContact,lang));
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountParContactModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findInstitutionalAddress")
    @ApiOperation(value = "根据accountId取合伙企业账户机构地址信息", httpMethod = "GET")
    public JsonMessage findParInstitutionalAddress(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        // 查出机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> parInstitutionals = accountParInstitutionalService.findList(accountParInstitutional);
        //取合伙企业机构地址信息
        List<AccountParAddressModel> accountParAddressModels =new ArrayList<>();
        for(AccountParInstitutional institutional : parInstitutionals){
            if(null != institutional.getId()){
                AccountParAddress accountParAddress = new AccountParAddress();
                accountParAddress.setAccountId(accountId);
                accountParAddress.setParPersonalId(institutional.getId());
                accountParAddressModels.addAll(accountParAddressService.findList(accountParAddress,lang));
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountParAddressModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findInstitutionalTaxes")
    @ApiOperation(value = "根据AccountId和合伙企业个人信息的id取合伙企业税务居住地", httpMethod = "GET")
    public JsonMessage findParInstitutionalTaxes(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        // 查出机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> parInstitutionals = accountParInstitutionalService.findList(accountParInstitutional);
        //取合伙企业机构税务居住地
        List<AccountParTaxesModel> accountParTaxesModels = new ArrayList<>();
        for (AccountParInstitutional institutional : parInstitutionals) {
            if(null != institutional.getId()){
                AccountParTaxes accountParTaxes = new AccountParTaxes();
                accountParTaxes.setAccountId(accountId);
                accountParTaxes.setParPersonalId(institutional.getId());
                accountParTaxesModels.addAll(accountParTaxesService.findList(accountParTaxes,lang));
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParTaxesModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findWealthSource")
    @ApiOperation(value = "根据AccountId取合伙企业账户财富来源", httpMethod = "GET")
    public JsonMessage findParWealthSource(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParWealthSource accountParWealthSource = new AccountParWealthSource();
        accountParWealthSource.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParWealthSourceService.findList(accountParWealthSource,lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findAccountPortfolio")
    @ApiOperation(value = "根据accountId取合伙企业账户组合信息", httpMethod = "GET")
    public JsonMessage findParAccountPortfolio(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParAccountPortfolio accountParAccountPortfolio = new AccountParAccountPortfolio();
        accountParAccountPortfolio.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParAccountPortfolio(accountParAccountPortfolioService.findList(accountParAccountPortfolio),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findAsset")
    @ApiOperation(value = "根据AccountId取合伙企业账户资产信息", httpMethod = "GET")
    public JsonMessage findParAsset(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParAsset accountParAsset = new AccountParAsset();
        accountParAsset.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParAssetService.findList(accountParAsset));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findBrokerInfo")
    @ApiOperation(value = "根据accountId取合伙企业账户经济商监信息", httpMethod = "GET")
    public JsonMessage findParBrokerInfo(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParBrokerInfo accountParBrokerInfo = new AccountParBrokerInfo();
        accountParBrokerInfo.setAccountId(accountId);
        return getJsonMessage(CommonEnums.SUCCESS,accountParBrokerInfoService.findList(accountParBrokerInfo));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findBrokerPermission")
    @ApiOperation(value = "根据accountId取合伙企业账户经济商监管许可", httpMethod = "GET")
    public JsonMessage findParBrokerPermission(Long accountId) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParBrokerPermission accountParBrokerPermission = new AccountParBrokerPermission();
        accountParBrokerPermission.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParBrokerPermissionService.findList(accountParBrokerPermission));
    }
    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findCrsEntityContrs")
    @ApiOperation(value = "根据AccountId取合伙企业账户CRS控权人自我证明表数量", httpMethod = "GET")
    public JsonMessage findCrsEntityContrs(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParCrsEntity accountParCrsEntity = new AccountParCrsEntity();
        accountParCrsEntity.setAccountId(accountId);
        List<AccountParCrsEntity> accountParCrsEntities = accountParCrsEntityService.findList(accountParCrsEntity);
        AccountParCrsEntityContr crsEntityContr = new AccountParCrsEntityContr();
        crsEntityContr.setAccountId(accountId);
        List<AccountParCrsEntityContr> accountParCrsEntityContrs = new ArrayList<>();
        for(AccountParCrsEntity parCrsEntity : accountParCrsEntities){
            crsEntityContr.setParCrsEntityId(parCrsEntity.getId());
            accountParCrsEntityContrs.addAll(accountParCrsEntityContrService.findList(crsEntityContr));
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountParCrsEntityContrs);
    }
    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findCrsControl")
    @ApiOperation(value = "根据AccountId取合伙企业账户CRS控权人自我证明表格", httpMethod = "GET")
    public JsonMessage findParCrsControl(Long accountId ,Long crsEntityId, String lang) throws BusinessException {
        if (null == accountId || crsEntityId == null) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParCrsEntityContr accountParCrsEntityContr = new AccountParCrsEntityContr();
        accountParCrsEntityContr.setAccountId(accountId);
        accountParCrsEntityContr.setParCrsEntityId(crsEntityId);
        List<AccountParCrsEntityContr> accountParCrsEntityContrs = accountParCrsEntityContrService.findList(accountParCrsEntityContr);
        List<AccountParCrsEntityContrModel> accountParCrsEntityContrModels = new ArrayList<>();
        for(AccountParCrsEntityContr crsEntityContr : accountParCrsEntityContrs){
            AccountParCrsControlNum accountParCrsControlNum =new AccountParCrsControlNum();
            accountParCrsControlNum.setAccountId(accountId);
            accountParCrsControlNum.setParCrsEntityContrId(crsEntityContr.getId());
            AccountParCrsEntityContrModel model = new AccountParCrsEntityContrModel()
                    .setAccountParCrsControls(crsControl(accountId,crsEntityContr.getId(),lang))
                    .setAccountParCrsControlNums(getAccountParCrsControlNum(accountParCrsControlNumService.findList(accountParCrsControlNum),lang));
            accountParCrsEntityContrModels.add(model);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountParCrsEntityContrModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findParCrsEntity")
    @ApiOperation(value = "根据AccountId取合伙企业账户CRS实体自我证明表格", httpMethod = "GET")
    public JsonMessage findParCrsEntity(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParCrsEntity accountParCrsEntity = new AccountParCrsEntity();
        accountParCrsEntity.setAccountId(accountId);
        List<AccountParCrsEntity> accountParCrsEntities = accountParCrsEntityService.findList(accountParCrsEntity);
        List<AccountParCrsEntityModel> accountParCrsEntityModels =new ArrayList<>();
        AccountParCrsEntityContr accountParCrsEntityContr = new AccountParCrsEntityContr();
        accountParCrsEntityContr.setAccountId(accountId);
        for(AccountParCrsEntity crsEntity : accountParCrsEntities){
            AccountParCrsEntityModel model = new AccountParCrsEntityModel()
                    .convertToModel(crsEntity)
                    .setAccountParCrsEntityContrs(accountParCrsEntityContrService.findList(accountParCrsEntityContr));
            accountParCrsEntityModels.add(model);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountParCrsEntityModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findFile")
    @ApiOperation(value = "根据accountId取合伙企业账户文件信息", httpMethod = "GET")
    public JsonMessage findParFile(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParFile accountParFile = new AccountParFile();
        accountParFile.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParFile(accountParFileService.findList(accountParFile),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findTaxClassification")
    @ApiOperation(value = "根据accountId取合伙企业账户税收分类", httpMethod = "GET")
    public JsonMessage findParTaxClassification(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParTaxClassification accountParTaxClassification = new AccountParTaxClassification();
        accountParTaxClassification.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParTaxClassification(accountParTaxClassificationService.findList(accountParTaxClassification),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findInvestmentAccount")
    @ApiOperation(value = "根据accountId取合伙企业账户一体化投资管理账户", httpMethod = "GET")
    public JsonMessage findParInvestmentAccount(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParInvestmentAccount accountParInvestmentAccount = new AccountParInvestmentAccount();
        accountParInvestmentAccount.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParInvestmentAccountService.findList(accountParInvestmentAccount));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findInvestmentTarget")
    @ApiOperation(value = "根据accountId取合伙企业账户投资目标信息", httpMethod = "GET")
    public JsonMessage findParInvestmentTarget(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParInvestmentTarget accountParInvestmentTarget = new AccountParInvestmentTarget();
        accountParInvestmentTarget.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParInvestmentTargetService.findList(accountParInvestmentTarget,lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findRegulatory")
    @ApiOperation(value = "根据accountId取合伙企账户业规管信息", httpMethod = "GET")
    public JsonMessage findParRegulatory(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParRegulatory accountParRegulatory = new AccountParRegulatory();
        accountParRegulatory.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParRegulatory(accountParRegulatoryService.findList(accountParRegulatory),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findSafeQuestion")
    @ApiOperation(value = "根据accountId取合伙企业账户安全问题", httpMethod = "GET")
    public JsonMessage findParSafeQuestion(Long accountId,String lang) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParSafeQuestion accountParSafeQuestion = new AccountParSafeQuestion();
        accountParSafeQuestion.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParSafeQuestion(accountParSafeQuestionService.findList(accountParSafeQuestion),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findSecurityOfficer")
    @ApiOperation(value = "根据accountId获取合伙企业账户安全官", httpMethod = "GET")
    public JsonMessage findParSecurityOfficer(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParSecurityOfficer accountParSecurityOfficer =new AccountParSecurityOfficer();
        accountParSecurityOfficer.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParSecurityOfficer(accountParSecurityOfficerService.findList(accountParSecurityOfficer),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findTradeExperience")
    @ApiOperation(value = "根据AccountId取合伙企业账户交易经验信息", httpMethod = "GET")
    public JsonMessage findParTradeExperience(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParTradeExperience accountParTradeExperience = new AccountParTradeExperience();
        accountParTradeExperience.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParTradeExperienceService.findList(accountParTradeExperience,lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findWbenContract")
    @ApiOperation(value = "根据AccountId和类型取合伙企业账户w8ben表", httpMethod = "GET")
    public JsonMessage findParWbenContract(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //税收分类
        AccountParTaxClassification accountParTaxClassification = new AccountParTaxClassification();
        accountParTaxClassification.setAccountId(accountId);
        List<AccountParTaxClassification> accountParTaxClassifications = accountParTaxClassificationService.findList(accountParTaxClassification);
        //W8
        AccountParWbenContract accountParWbenContract = new AccountParWbenContract();
        accountParWbenContract.setAccountId(accountId);
        if(accountParTaxClassifications.isEmpty() == false){
            accountParWbenContract.setType(accountParTaxClassifications.get(0).getTaxType());
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParWbenContractService.findList(accountParWbenContract));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findCrsControlNum")
    @ApiOperation(value = "根据 accountId获取企业CRS控权人自我证明控权人编号", httpMethod = "GET")
    public JsonMessage findParCrsControlNum(Long accountId,String lang) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParCrsControlNum accountParCrsControlNum =new AccountParCrsControlNum();
        accountParCrsControlNum.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountParCrsControlNum(accountParCrsControlNumService.findList(accountParCrsControlNum),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findPerInfo")
    @ApiOperation(value = "根据accountId和类型获取企业账户个人及相关信息", httpMethod = "GET")
    public JsonMessage findParPerInfo(Long accountId,String personalType,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(accountId);
        accountParPersonal.setPersonalType(personalType);
        return this.getJsonMessage(CommonEnums.SUCCESS, getParPerInfo(accountId,accountParPersonalService.findList(accountParPersonal),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/partnerShip/findBeneficiaryOwner")
    @ApiOperation(value = "根据accountId获取企业账户受益所有人", httpMethod = "GET")
    public JsonMessage findBeneficiaryOwner(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //经营负责人
        String operationPrincipal = "operationPrincipal";
        //个人股东
        String individualShareholder = "individualShareholder";
        //25%或以上的受益人
        String TwentyFivePercentAboveBeneficiary = "25%AboveBeneficiary";
        //10%或以上的受益人
        String tenPercentAboveBeneficiary = "10%AboveBeneficiary";
        lang =dictLangParameterVerification(lang);
        AccountParBeneficiaryOwnerModel accountParBeneficiaryOwnerModel =new AccountParBeneficiaryOwnerModel();
        List<AccountParPersonal> accountParPersonalList = new ArrayList<>();
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(accountId);
        accountParPersonal.setPersonalType(individualShareholder);
        accountParPersonalList.addAll(accountParPersonalService.findList(accountParPersonal));
        accountParPersonal.setPersonalType(TwentyFivePercentAboveBeneficiary);
        accountParPersonalList.addAll(accountParPersonalService.findList(accountParPersonal));
        accountParPersonal.setPersonalType(tenPercentAboveBeneficiary);
        accountParPersonalList.addAll(accountParPersonalService.findList(accountParPersonal));
        accountParPersonalList=prcentageOfOwnershipDesc(accountParPersonalList);
        //使用迭代器删除列表中的经营负责人
        Iterator<AccountParPersonal> iterator=accountParPersonalList.iterator();
        while (iterator.hasNext()){
            AccountParPersonal parPersonal=iterator.next();
                if(parPersonal.getPersonalTypeTwo()!=null && parPersonal.getPersonalTypeTwo().equals(operationPrincipal)){
                    iterator.remove();
                }
        }
        accountParPersonal.setPersonalType(operationPrincipal);
        List<AccountParPersonal> operationPrincipals = accountParPersonalService.findList(accountParPersonal);
        if(operationPrincipals.isEmpty()){
            accountParPersonal.setPersonalType(null);
            accountParPersonal.setPersonalTypeTwo(operationPrincipal);
            operationPrincipals = (accountParPersonalService.findList(accountParPersonal));
        }
        accountParBeneficiaryOwnerModel.setOperationPrincipalList(getParPerInfo(accountId,operationPrincipals,lang));
        accountParBeneficiaryOwnerModel.setBeneficiaryOwnerList(getParPerInfo(accountId,accountParPersonalList,lang));
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParBeneficiaryOwnerModel);
    }


    /**
     * 获取企业个人信息
     * @param accountId
     * @param accountParPersonalList
     * @param lang
     * @return
     */
    private List<ParPersonalInfoModel> getParPerInfo(Long accountId,List<AccountParPersonal> accountParPersonalList,String lang){
        List<ParPersonalInfoModel> companyPersonalInfoModels =new ArrayList<>();
        //取企业个人地址信息
        AccountParAddress accountParAddress = new AccountParAddress();
        accountParAddress.setAccountId(accountId);
        //取企业个人联系信息
        AccountParContact accountParContact = new AccountParContact();
        accountParContact.setAccountId(accountId);
        //取企业个人身份信息
        AccountParIdentity accountParIdentity = new AccountParIdentity();
        accountParIdentity.setAccountId(accountId);
        //取企业税务居住地信息
        AccountParTaxes accountParTaxes = new AccountParTaxes();
        accountParTaxes.setAccountId(accountId);
        for(AccountParPersonal parPersonal:accountParPersonalList){
            if(null != parPersonal.getId()){
                accountParAddress.setParPersonalId(parPersonal.getId());
                accountParContact.setParPersonalId(parPersonal.getId());
                accountParIdentity.setParPersonalId(parPersonal.getId());
                accountParTaxes.setParPersonalId(parPersonal.getId());
                ParPersonalInfoModel parPersonalInfoModel = new ParPersonalInfoModel()
                        .setAccountParPersonal(getParPerInfo(parPersonal,lang))
                        .setAccountParAddressList(accountParAddressService.findList(accountParAddress,lang))
                        .setAccountParContactList(accountParContactService.findList(accountParContact,lang))
                        .setAccountParIdentityList(getAccountParIdentity(accountParIdentityService.findList(accountParIdentity),lang))
                        .setAccountParTaxesList(accountParTaxesService.findList(accountParTaxes,lang));
                companyPersonalInfoModels.add(parPersonalInfoModel);
            }
        }
        return companyPersonalInfoModels;
    }

    /**
     * 获取 账户组合信息 中关联的字典表信息
     * @param accountParAccountPortfolios
     * @return
     */
    private List<AccountParAccountPortfolioModel> getAccountParAccountPortfolio(List<AccountParAccountPortfolio> accountParAccountPortfolios,String lang){

        List<AccountParAccountPortfolioModel> accountParAccountPortfolioModels = new ArrayList<>();
        for(AccountParAccountPortfolio portfolio:accountParAccountPortfolios){
            AccountParAccountPortfolioModel accountParAccountPortfolioModel = new AccountParAccountPortfolioModel()
                    .convertToModel(portfolio)
                    .setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",portfolio.getAccountPortfolioType(),lang))
                    .setBaseCurrency(dictionaryService.findDictionary("baseCurrency",portfolio.getBaseCurrency(),lang));
            accountParAccountPortfolioModels.add(accountParAccountPortfolioModel);
        }
        return accountParAccountPortfolioModels;
    }

    /**
     * 获取身份信息相关联的字典表详情
     * @param accountParIdentities
     * @return
     */
    private List<AccountParIdentityModel> getAccountParIdentity(List<AccountParIdentity> accountParIdentities,String lang){
        List<AccountParIdentityModel> accountParIdentityModels =new LinkedList<>();
        for(AccountParIdentity identity:accountParIdentities){
            AccountParIdentityModel accountParIdentityModel = new AccountParIdentityModel()
                    .convertToModel(identity)
                    .setIdCardType(dictionaryService.findDictionary("cardType",identity.getIdCardType(),lang))
                    .setIssuingCountry(regionService.findBysCode(identity.getIssuingCountry()));
            accountParIdentityModels.add(accountParIdentityModel);
        }
        return accountParIdentityModels;
    }

    /**
     * 查找 安全问题 中关联的字典表信息
     * @param accountParSafeQuestions
     * @return
     */
    private List<AccountParSafeQuestionModel> getAccountParSafeQuestion(List<AccountParSafeQuestion> accountParSafeQuestions,String lang){
        List<AccountParSafeQuestionModel> accountParSafeQuestionModels = new ArrayList<>();
        for(AccountParSafeQuestion safeQuestion:accountParSafeQuestions){
            AccountParSafeQuestionModel accountParSafeQuestionModel = new AccountParSafeQuestionModel()
                    .convertToModel(safeQuestion)
                    .setSafeQuestion(dictionaryService.findDictionary("safeQuestionType",safeQuestion.getSafeQuestion(),lang));
            accountParSafeQuestionModels.add(accountParSafeQuestionModel);
        }
        return accountParSafeQuestionModels;
    }

    /**
     * 获取 安全官 相关字典数据
     * @param accountParSecurityOfficers
     * @return
     */
    private List<AccountParSecurityOfficerModel> getAccountParSecurityOfficer(List<AccountParSecurityOfficer> accountParSecurityOfficers,String lang){
        List<AccountParSecurityOfficerModel> accountParSecurityOfficerModels = new ArrayList<>();
        for(AccountParSecurityOfficer securityOfficer : accountParSecurityOfficers){
            AccountParSecurityOfficerModel accountParSecurityOfficerModel =new AccountParSecurityOfficerModel()
                    .convertToModel(securityOfficer)
                    .setAppellation(dictionaryService.findDictionary("appellationType",securityOfficer.getAppellation(),lang));
            accountParSecurityOfficerModels.add(accountParSecurityOfficerModel);
        }
        return accountParSecurityOfficerModels;
    }

    /**
     * 获取 税收分类相关字典表信息
     * @param accountParTaxClassifications
     * @return
     */
    private List<AccountParTaxClassificationModel> getAccountParTaxClassification(List<AccountParTaxClassification> accountParTaxClassifications,String lang){
        List<AccountParTaxClassificationModel> accountParTaxClassificationModels = new ArrayList<>();
        for(AccountParTaxClassification taxClassification : accountParTaxClassifications){
            AccountParTaxClassificationModel accountParTaxClassificationModel = new AccountParTaxClassificationModel()
                    .convertToModel(taxClassification)
                    .setTaxType(dictionaryService.findDictionary("taxType",taxClassification.getTaxType(),lang));
            accountParTaxClassificationModels.add(accountParTaxClassificationModel);
        }
        return accountParTaxClassificationModels;
    }

    /**
     * 获取 文件相关字典表信息
     * @param accountParFiles
     * @return
     */
    private List<AccountParFileModel> getAccountParFile(List<AccountParFile> accountParFiles,String lang){
        List<AccountParFileModel> accountParFileModels =new ArrayList<>();
        for(AccountParFile file : accountParFiles){
            AccountParFileModel accountParFileModel =new AccountParFileModel()
                    .convertToModel(file);
            UploadFileType uploadFileType = new UploadFileType();
            uploadFileType.setTypeKey(file.getFileIdentification());
            List<UploadFileType> uploadFileTypes=uploadFileTypeService.findList(uploadFileType);
            if(uploadFileTypes.isEmpty()==false){
                accountParFileModel.setFileIdentification(uploadFileTypes.get(0));
            }
            //文件股东
            if(accountParFileModel.getParPersonalId() != null){
                accountParPersonalService.selectByPrimaryKey(accountParFileModel.getParPersonalId());
                accountParFileModel.setPersonal(getParPerInfo(accountParPersonalService.selectByPrimaryKey(accountParFileModel.getParPersonalId()),lang));
            }
            accountParFileModels.add(accountParFileModel);
        }
        return accountParFileModels;
    }

    /**
     * 获取规管信息相关字典表信息
     * @param accountParRegulatories
     * @return
     */
    private  List<AccountParRegulatoryModel> getAccountParRegulatory(List<AccountParRegulatory> accountParRegulatories,String lang){
        List<AccountParRegulatoryModel> accountParRegulatoryModels = new ArrayList<>();
        for( AccountParRegulatory regulatory: accountParRegulatories){
            AccountParRegulatoryModel accountParRegulatoryModel =new AccountParRegulatoryModel()
                    .convertToModel(regulatory)
                    .setInfoType(dictionaryService.findDictionary("regulatoryInfo",regulatory.getInfoType(),lang));
            accountParRegulatoryModels.add(accountParRegulatoryModel);
        }
        return accountParRegulatoryModels;
    }

    /**
     * 获取企业CRS控权人自我证明控权人编号及相关字典信息
     * @param accountParCrsControlNums
     * @return
     */
    private List<AccountParCrsControlNumModel> getAccountParCrsControlNum(List<AccountParCrsControlNum> accountParCrsControlNums,String lang){
        List<AccountParCrsControlNumModel> accountParCrsControlNumModels = new ArrayList<>();
        for(AccountParCrsControlNum crsControlNum : accountParCrsControlNums)
        {
            AccountParCrsControlNumModel coCrsControlNumModel = new AccountParCrsControlNumModel()
                    .convertToModel(crsControlNum);
            List<String> controlCategoryStr = Arrays.asList(crsControlNum.getControlCategory().split(","));
            List<Dictionary> dictionaries =new ArrayList<>();
            for(String str: controlCategoryStr){
                if(crsControlNum.getEntityCategory().endsWith("legalPerson")){
                    dictionaries.add(dictionaryService.findDictionary("controllingPersonType_1",str,lang));
                }
                if(crsControlNum.getEntityCategory().endsWith("trust")){
                    dictionaries.add(dictionaryService.findDictionary("controllingPersonType_2",str,lang));
                }
                if(crsControlNum.getEntityCategory().endsWith("Legal Arrangementother than Trust")){
                    dictionaries.add(dictionaryService.findDictionary("controllingPersonType_3",str,lang));
                }
            }
            coCrsControlNumModel.setControlCategory(dictionaries);
            coCrsControlNumModel.setEntityCategory(dictionaryService.findDictionary("controllingPersonType",crsControlNum.getEntityCategory(),lang));
            accountParCrsControlNumModels.add(coCrsControlNumModel);
        }
        return accountParCrsControlNumModels;
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
                .setPersonalTypeTwo(dictionaryService.findDictionary("personalType",personal.getPersonalTypeTwo(),lang));
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

    /**
     * 获取企业机构及其字典信息
     * @param accountParInstitutionals
     * @param lang
     * @return
     */
    private List<AccountParInstitutionalModel> getAccountParInstitutional(List<AccountParInstitutional> accountParInstitutionals,String lang){
        List<AccountParInstitutionalModel> accountParInstitutionalModels = new ArrayList<>();
        for(AccountParInstitutional institutional: accountParInstitutionals){
            AccountParInstitutionalModel accountParInstitutionalModel = new AccountParInstitutionalModel()
                    .convertToModel(institutional);
            List<Dictionary> dictionaries =new ArrayList<>();
            if(institutional.getIstitutionalNature().contains(";")){
                List<String> istitutionalNatureList =  Arrays.asList(institutional.getIstitutionalNature().split(";"));
                istitutionalNatureList = new ArrayList<>(istitutionalNatureList);
               if(istitutionalNatureList.isEmpty()==false){
                   dictionaries.add(dictionaryService.findDictionary("institutionalNature",istitutionalNatureList.get(0),lang));
                   istitutionalNatureList.remove(0);
               }
                for(String str:istitutionalNatureList){
                    List<String> list = Arrays.asList(str.split(","));
                    for(String istitutionalNature:list){
                        dictionaries.add(dictionaryService.findDictionary("institutionalNature_1",istitutionalNature,lang));
                        dictionaries.add(dictionaryService.findDictionary("institutionalNature_2",istitutionalNature,lang));
                    }
                }
            }
            else {
                dictionaries.add(dictionaryService.findDictionary("institutionalNature",institutional.getIstitutionalNature(),lang));
            }
            accountParInstitutionalModel.setIstitutionalNature(dictionaries);
            accountParInstitutionalModels.add(accountParInstitutionalModel);
        }
        return accountParInstitutionalModels;
    }



    private  Map<String, Object> parPdf(Long accountId,String lang){
        Map<String, Object> data = new HashMap<>();
        data.put("account",getAccount(accountId));
        //投资组合
        AccountParAccountPortfolio accountParAccountPortfolio = new AccountParAccountPortfolio();
        accountParAccountPortfolio.setAccountId(accountId);
        data.put("portfolios",getAccountParAccountPortfolio(accountParAccountPortfolioService.findList(accountParAccountPortfolio),lang));
        //机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        data.put("institutional",getAccountParInstitutional(accountParInstitutionalService.findList((accountParInstitutional)),lang));
        //机构地址
        data.putAll(getParPdfAddress(accountId,lang));
        //机构联系信息
        data.put("institutionalContacts",getInstitutionalContact(accountId,lang));
        //机构税务居住地
        data.put("institutionalTaxes",getInstitutionalTaxes(accountId,lang));
        //财富来源
        AccountParWealthSource accountParWealthSource = new AccountParWealthSource();
        accountParWealthSource.setAccountId(accountId);
        data.put("wealthSources", accountParWealthSourceService.findList(accountParWealthSource,lang));
        //被授权人
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(accountId);
        accountParPersonal.setPersonalType("authorizedPerson");
        List<ParPersonalInfoModel> parPersonalInfoModels = getParPerInfo(accountId,accountParPersonalService.findList(accountParPersonal),lang);
        parPersonalInfoModels.get(0).setAccountParAddressList(address(parPersonalInfoModels.get(0).getAccountParAddressList()));
        data.put("authorizedPersons",parPersonalInfoModels);
        //税收分类
        AccountParTaxClassification accountParTaxClassification = new AccountParTaxClassification();
        accountParTaxClassification.setAccountId(accountId);
        data.put("taxClassifications",getAccountParTaxClassification(accountParTaxClassificationService.findList(accountParTaxClassification),lang));
        //规管信息
        AccountParRegulatory accountParRegulatory = new AccountParRegulatory();
        accountParRegulatory.setAccountId(accountId);
        data.put("regulatorys", getAccountParRegulatory(accountParRegulatoryService.findList(accountParRegulatory),lang));
        //资产信息
        AccountParAsset accountParAsset = new AccountParAsset();
        accountParAsset.setAccountId(accountId);
        data.put("assets",accountParAssetService.findList(accountParAsset));
        //交易经验
        AccountParTradeExperience accountParTradeExperience = new AccountParTradeExperience();
        accountParTradeExperience.setAccountId(accountId);
        data.put("tradeExps",accountParTradeExperienceService.findList(accountParTradeExperience,lang));
        //投资目标
        AccountParInvestmentTarget accountParInvestmentTarget = new AccountParInvestmentTarget();
        accountParInvestmentTarget.setAccountId(accountId);
        data.put("investmentTargets",accountParInvestmentTargetService.findList(accountParInvestmentTarget,lang));
        //受益所有人，经营负责人
        AccountParBeneficiaryOwnerModel beneficiaryOwner=getBeneficiaryOwner(accountId,lang);
        beneficiaryOwner.getOperationPrincipalList().get(0).setAccountParAddressList(address(beneficiaryOwner.getOperationPrincipalList().get(0).getAccountParAddressList()));
        if(beneficiaryOwner.getBeneficiaryOwnerList().isEmpty()==false){
            beneficiaryOwner.getBeneficiaryOwnerList().get(0).setAccountParAddressList(address(beneficiaryOwner.getBeneficiaryOwnerList().get(0).getAccountParAddressList()));
        }
        for (ParPersonalInfoModel model:beneficiaryOwner.getBeneficiaryOwnerList()) {
            model.setAccountParAddressList(address(model.getAccountParAddressList()));
        }
        data.put("beneficiaryOwner", beneficiaryOwner);
        //        注册拥有人（个人股东，法人股东）
        List<ParPersonalInfoModel> shareholder = new ArrayList<>();
        shareholder.addAll(findParPer(accountId,"individualShareholder",lang));
        shareholder.addAll(findParPer(accountId,"legalPersonShareholder",lang));
        for (ParPersonalInfoModel model:shareholder) {
            model.setAccountParAddressList(address(model.getAccountParAddressList()));
        }
        data.put("shareholder", shareholder);
        return data;
    }
    private  Map<String,Object> W8Pdf(Long accountId,String lang) {
        Map<String, Object> data = new HashMap<>();
        //String转JSONObject
        JSONObject result = JSON.parseObject(getW8(accountId).get(0).getChecked());
        data.put("w8", getW8(accountId));
        data.put("w8Part",result.get("Part5isList"));
        data.putAll(getParPdfAddress(accountId,lang));
        return data;
    }
    /**
     * 企业 Crs Entity pdf
     * @param accountId
     * @param lang
     * @return
     */
    private  Map<String,Object> CrsEntityPdf(Long accountId,String lang) {
        Map<String, Object> data = new HashMap<>();
        AccountParCrsEntity accountParCrsEntity = new AccountParCrsEntity();
        accountParCrsEntity.setAccountId(accountId);
        List<AccountParCrsEntity> accountParCrsEntities=accountParCrsEntityService.findList(accountParCrsEntity);
        for (AccountParCrsEntity entity:accountParCrsEntities) {
            entity.setOtherCountry(regionService.findBysCode(entity.getOtherCountry()).getEnName());
            if(entity.getEntityInput() != null){
                entity.setEntityInput( entity.getEntityInput().replace("-#-",","));
            }
            if(entity.getOtherAddresses() != null){
                entity.setOtherAddresses( entity.getOtherAddresses().replace("-#-",","));
            }
        }
        if(accountParCrsEntities.isEmpty() == false){
            data.put("crsEntity",accountParCrsEntities.get(0));
            AccountParCrsEntityContr accountParCrsEntityContr = new AccountParCrsEntityContr();
            accountParCrsEntityContr.setAccountId(accountId);
            accountParCrsEntityContr.setParCrsEntityId(accountParCrsEntities.get(0).getId());
            data.put("crsEntityContrs",accountParCrsEntityContrService.findList(accountParCrsEntityContr));
        }
        data.putAll(getParPdfAddress(accountId,lang));
        JSON.toJSONString(data);
        return data;
    }

    /**
     *  CrsCp  pdf
     * @param accountId
     * @param lang
     * @return
     */
    private  Map<String,Object> crsCpPdf(Long accountId,Long crsEntityContrId,String lang) {
        Map<String, Object> data = new HashMap<>();
        List<AccountParCrsControlModel> crsControlModels = crsControl(accountId,crsEntityContrId,lang);
        if(crsControlModels.isEmpty()){
            return data;
        }
        if(crsControlModels.get(0).getOtherCountry()!=null){
            crsControlModels.get(0).setOtherCountry(regionService.findBysCode(crsControlModels.get(0).getOtherCountry()).getEnName());
        }
        if(crsControlModels.get(0).getOtherAddresses()!=null){
            crsControlModels.get(0).setOtherAddresses(crsControlModels.get(0).getOtherAddresses().replace("-#-",","));
        }
        String entityAccountName = crsControlModels.get(0).getEntityAccountName();
        if(entityAccountName != null){
            String[] entityAccountNameArr= entityAccountName.split(",");
            List<String> entityAccountNames = Arrays.asList(entityAccountNameArr);
            data.put("entityAccountNames",entityAccountNames);
        }
        data.put("crsControl",crsControlModels.get(0));
        AccountParCrsControlNum accountParCrsControlNum =new AccountParCrsControlNum();
        accountParCrsControlNum.setAccountId(accountId);
        accountParCrsControlNum.setParCrsEntityContrId(crsEntityContrId);
        List<AccountParCrsControlNumModel> crsControlNums = getAccountParCrsControlNum(accountParCrsControlNumService.findList(accountParCrsControlNum),lang);
        for(AccountParCrsControlNumModel crsControlNumModel:crsControlNums){
            switch (crsControlNumModel.getEntityNumber()){
                case "entity1":
                    data.put("entity1",crsControlNumModel);
                    break;
                case "entity2":
                    data.put("entity2",crsControlNumModel);
                    break;
                case "entity3":
                    data.put("entity3",crsControlNumModel);
                    break;
            }
        }
        AccountParAddress accountParAddress = new AccountParAddress();
        accountParAddress.setAccountId(accountId);
        accountParAddress.setParPersonalId(crsEntityContrId);
        data.put("address",accountParAddressService.findList(accountParAddress,lang));
        return data;
    }

    /**
     * 账户信息
     * @param accountId
     * @param
     * @return
     */
    private Account getAccount(Long accountId){
        Account account= accountService.selectByPrimaryKey(accountId);
        account.setCountry(regionService.findBysCode(account.getCountry()).getEnName());
        account.setNationality(regionService.findBysCode(account.getNationality()).getEnName());
        return account;
    }
    /**
     * 企业 pdf 地址
     * @param accountId
     * @param lang
     * @return
     */
    private Map<String, Object> getParPdfAddress(Long accountId,String lang){
        Map<String, Object> data = new HashMap<>();
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> accountParInstitutionals = accountParInstitutionalService.findList((accountParInstitutional));
        if(accountParInstitutionals.isEmpty()==false && accountParInstitutionals.get(0).getId()!=null){
            AccountParAddress accountParAddress = new AccountParAddress();
            accountParAddress.setAccountId(accountId);
            accountParAddress.setParPersonalId(accountParInstitutionals.get(0).getId());
            accountParAddress.setAddressType("personalAddress");
            data.put("personalAddress", address(accountParAddressService.findList(accountParAddress,lang)).get(0));
            accountParAddress.setAddressType("mail");
            if(accountParAddressService.findList(accountParAddress,lang).isEmpty()==false){
                data.put("mailAddress", address(accountParAddressService.findList(accountParAddress,lang)).get(0));
            }
        }
        return data;
    }
    /**
     * 获取机构联系信息
     * @param accountId
     * @param lang
     * @return
     */
    private List<AccountParContactModel> getInstitutionalContact(Long accountId,String lang){
        // 查出机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> parInstitutionals = accountParInstitutionalService.findList(accountParInstitutional);
        //取合伙企业机构联系信息
        List<AccountParContactModel> accountParContactModels =new ArrayList<>();
        for(AccountParInstitutional institutional : parInstitutionals){
            if(null != institutional.getId()){
                AccountParContact accountParContact = new AccountParContact();
                accountParContact.setAccountId(accountId);
                accountParContact.setParPersonalId(institutional.getId());
                accountParContactModels.addAll(accountParContactService.findList(accountParContact,lang));
            }
        }
        return accountParContactModels;
    }

    /**
     * 获取机构税务居住地
     * @param accountId
     * @param lang
     * @return
     */
    private  List<AccountParTaxesModel> getInstitutionalTaxes(Long accountId,String lang){
        // 查出机构信息
        AccountParInstitutional accountParInstitutional = new AccountParInstitutional();
        accountParInstitutional.setAccountId(accountId);
        List<AccountParInstitutional> parInstitutionals = accountParInstitutionalService.findList(accountParInstitutional);
        //取合伙企业机构税务居住地
        List<AccountParTaxesModel> accountParTaxesModels = new ArrayList<>();
        for (AccountParInstitutional institutional : parInstitutionals) {
            if(null != institutional.getId()){
                AccountParTaxes accountParTaxes = new AccountParTaxes();
                accountParTaxes.setAccountId(accountId);
                accountParTaxes.setParPersonalId(institutional.getId());
                accountParTaxesModels.addAll(accountParTaxesService.findList(accountParTaxes,lang));
            }
        }
        return accountParTaxesModels;
    }

    private AccountParBeneficiaryOwnerModel getBeneficiaryOwner(Long accountId,String lang){
        //经营负责人
        String operationPrincipal = "operationPrincipal";
        //个人股东
        String individualShareholder = "individualShareholder";
        //25%或以上的受益人
        String TwentyFivePercentAboveBeneficiary = "25%AboveBeneficiary";
        //10%或以上的受益人
        String tenPercentAboveBeneficiary = "10%AboveBeneficiary";
        lang =dictLangParameterVerification(lang);
        AccountParBeneficiaryOwnerModel accountParBeneficiaryOwnerModel =new AccountParBeneficiaryOwnerModel();
        List<AccountParPersonal> accountParPersonalList = new ArrayList<>();
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(accountId);
        accountParPersonal.setPersonalType(individualShareholder);
        accountParPersonalList.addAll(accountParPersonalService.findList(accountParPersonal));
        accountParPersonal.setPersonalType(TwentyFivePercentAboveBeneficiary);
        accountParPersonalList.addAll(accountParPersonalService.findList(accountParPersonal));
        accountParPersonal.setPersonalType(tenPercentAboveBeneficiary);
        accountParPersonalList.addAll(accountParPersonalService.findList(accountParPersonal));
        accountParPersonalList=prcentageOfOwnershipDesc(accountParPersonalList);
        //使用迭代器删除列表中的经营负责人
        Iterator<AccountParPersonal> iterator=accountParPersonalList.iterator();
        while (iterator.hasNext()){
            AccountParPersonal parPersonal=iterator.next();
            if(parPersonal.getPersonalTypeTwo()!=null && parPersonal.getPersonalTypeTwo().equals(operationPrincipal)){
                iterator.remove();
            }
        }
        accountParPersonal.setPersonalType(operationPrincipal);
        List<AccountParPersonal> operationPrincipals = accountParPersonalService.findList(accountParPersonal);
        if(operationPrincipals.isEmpty()){
            accountParPersonal.setPersonalType(null);
            accountParPersonal.setPersonalTypeTwo(operationPrincipal);
            operationPrincipals = (accountParPersonalService.findList(accountParPersonal));
        }
        accountParBeneficiaryOwnerModel.setOperationPrincipalList(getParPerInfo(accountId,operationPrincipals,lang));
        accountParBeneficiaryOwnerModel.setBeneficiaryOwnerList(getParPerInfo(accountId,accountParPersonalList,lang));
        return accountParBeneficiaryOwnerModel;
    }
    public List<ParPersonalInfoModel> findParPer(Long accountId,String personalType,String lang) {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountParPersonal accountParPersonal = new AccountParPersonal();
        accountParPersonal.setAccountId(accountId);
        accountParPersonal.setPersonalType(personalType);
        return getParPerInfo(accountId,accountParPersonalService.findList(accountParPersonal),lang);
    }
    private List<AccountParWbenContract> getW8(Long accountId){
        //税收分类
        AccountParTaxClassification accountParTaxClassification = new AccountParTaxClassification();
        accountParTaxClassification.setAccountId(accountId);
        List<AccountParTaxClassification> accountParTaxClassifications = accountParTaxClassificationService.findList(accountParTaxClassification);
        //W8
        AccountParWbenContract accountParWbenContract = new AccountParWbenContract();
        accountParWbenContract.setAccountId(accountId);
        if(accountParTaxClassifications.isEmpty() == false){
            accountParWbenContract.setType(accountParTaxClassifications.get(0).getTaxType());
        }
        List<AccountParWbenContract> wbenContracts=accountParWbenContractService.findList(accountParWbenContract);
        for (AccountParWbenContract wbenContract:wbenContracts) {
            wbenContract.setOrganizationCountry(regionService.findBysCode(wbenContract.getOrganizationCountry()).getEnName());
            wbenContract.setBranchCountry(regionService.findBysCode(wbenContract.getBranchCountry()).getEnName());
        }
        return wbenContracts;
    }

    /**
     * crs控权人
     * @param accountId
     * @param lang
     * @return
     */
    private List<AccountParCrsControlModel> crsControl(Long accountId,Long CrsEntityContrId,String lang){
        AccountParCrsControl accountParCrsControl = new AccountParCrsControl();
        accountParCrsControl.setAccountId(accountId);
        accountParCrsControl.setParCrsEntityContrId(CrsEntityContrId);
        AccountParAddress accountParAddress = new AccountParAddress();
        accountParAddress.setAccountId(accountId);
        List<AccountParCrsControl> accountParCrsControls=accountParCrsControlService.findList(accountParCrsControl);
        List<AccountParCrsControlModel> accountParCrsControlModels = Lists.newArrayList();
        for (AccountParCrsControl crsControl:accountParCrsControls) {
            accountParAddress.setParPersonalId(crsControl.getId());
            AccountParCrsControlModel model = new AccountParCrsControlModel()
                    .convertToModel(crsControl)
                    .setIdCardType(dictionaryService.findDictionary("cardType",crsControl.getIdCardType(), lang))
                    .setAppellation(dictionaryService.findDictionary("appellationType",crsControl.getAppellation(),lang))
                    .setAccountParAddressModelList(accountParAddressService.findList(accountParAddress,lang));
            accountParCrsControlModels.add(model);
        }
        return accountParCrsControlModels;
    }
    /**
     * 地址 分隔符处理
     * @param addressModels
     * @return
     */
    private  List<AccountParAddressModel> address(List<AccountParAddressModel> addressModels){
        for(AccountParAddressModel address:addressModels){
            if(address.getAddress() !=null){
                address.setAddress(address.getAddress().replace("-#-",","));
            }
        }
        return addressModels;
    }
    /**
     * 验证字典的语言类型
     * @param lang
     * @return
     */
    private String dictLangParameterVerification(String lang){
        return StringUtils.isBlank(lang) ? "zh_CN" : lang;
    }

    /**
     * 根据企业个人所有权百分比(prcentageOfOwnership)降序排序
     * @param parPersonalInfoModels
     * @return
     */
    private List<AccountParPersonal> prcentageOfOwnershipDesc(List<AccountParPersonal> parPersonalInfoModels){
        /*
         * int compare(CompanyPersonalInfoModel o1, CompanyPersonalInfoModel o2) 返回一个基本类型的整型，
         * 返回负数表示：o1 大于o2，
         * 返回0 表示：o1和o2相等，
         * 返回正数表示：o1小于o2。
         */
        Collections.sort(parPersonalInfoModels, (o1, o2) -> {
            int i = 1;
            if( null == o1.getPrcentageOfOwnership()){
                return  i;
            }
            if( null == o2.getPrcentageOfOwnership()){
                return  -1;
            }
            if(o1.getPrcentageOfOwnership().compareTo(o2.getPrcentageOfOwnership())==1){
                i = -1;
            }
            if(o1.getPrcentageOfOwnership().compareTo(o2.getPrcentageOfOwnership())==0){
                i = 0;
            }
            return  i;
        });
        return parPersonalInfoModels;
    }
}
