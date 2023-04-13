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
 * @date 2019-08-07 10:15
 * @Description
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "账户公司开户管理")
public class AccountCoRegisterOpenController extends GenericController {

    @Autowired(required = false)
    private AccountCoAccountPortfolioService accountCoAccountPortfolioService;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountCoAddressService accountCoAddressService;
    @Autowired(required = false)
    private AccountCoInstitutionalService accountCoInstitutionalService;
    @Autowired(required = false)
    private RegionService regionService;
    @Autowired(required = false)
    private AccountCoAssetService accountCoAssetService;
    @Autowired(required = false)
    private AccountCoContactService accountCoContactService;
    @Autowired(required = false)
    private AccountCoCrsControlService accountCoCrsControlService;
    @Autowired(required = false)
    private AccountCoCrsEntityService accountCoCrsEntityService;
    @Autowired(required = false)
    private AccountCoFileService accountCoFileService;
    @Autowired(required = false)
    private AccountCoInvestmentAccountService accountCoInvestmentAccountService;
    @Autowired(required = false)
    private AccountCoInvestmentTargetService accountCoInvestmentTargetService;
    @Autowired(required = false)
    private AccountCoTaxesService accountCoTaxesService;
    @Autowired(required = false)
    private AccountCoPersonalService accountCoPersonalService;
    @Autowired(required = false)
    private AccountCoIdentityService accountCoIdentityService;
    @Autowired(required = false)
    private AccountCoRegulatoryService accountCoRegulatoryService;
    @Autowired(required = false)
    private AccountCoSafeQuestionService accountCoSafeQuestionService;
    @Autowired(required = false)
    private AccountCoSecurityOfficerService accountCoSecurityOfficerService;
    @Autowired(required = false)
    private AccountCoTaxClassificationService accountCoTaxClassificationService;
    @Autowired(required = false)
    private AccountCoTradeExperienceService accountCoTradeExperienceService;
    @Autowired(required = false)
    private AccountCoWbenContractService accountCoWbenContractService;
    @Autowired(required = false)
    private AccountCoWealthSourceService accountCoWealthSourceService;
    @Autowired(required = false)
    private AccountCoCrsControlNumService accountCoCrsControlNumService;
    @Autowired(required = false)
    private AccountCoCrsEntityContrService accountCoCrsEntityContrService;
    @Autowired(required = false)
    private UploadFileTypeService uploadFileTypeService;
    @Autowired(required = false)
    AccountService accountService;
    @Autowired(required = false)
    PdfUtils pdfUtils;

    @RequestMapping(value = "/registerOpen/limitedLiablity/pdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void pdfDownload(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("LimitedLiablity.ftl",coPdf(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/pdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void pdfPreview(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("LimitedLiablity.ftl",coPdf(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/w8Pdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void w8PdfDownload(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Map<String,Object> data = W8Pdf(accountId,lang);
        List<AccountCoWbenContract> wbenContracts = (List<AccountCoWbenContract>) data.get("w8");
        switch (wbenContracts.get(0).getType()){
            case "enterprise":
                pdfUtils.download("LimitedLiablityW8E.ftl",data,response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
                break;
            case "partnership (must have at least 2 owners)":
                pdfUtils.download("LimitedLiablityW8IMY.ftl",data,response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
                break;
        }
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/w8Pdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void w8PdfPreview(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Map<String,Object> data = W8Pdf(accountId,lang);
        List<AccountCoWbenContract> wbenContracts = (List<AccountCoWbenContract>) data.get("w8");
        switch (wbenContracts.get(0).getType()){
            case "enterprise":
                pdfUtils.preview("LimitedLiablityW8E.ftl",data,response,"logo.png","SourceHanSans",".ttf");
                break;
            case "partnership (must have at least 2 owners)":
                pdfUtils.preview("LimitedLiablityW8IMY.ftl",data,response,"logo.png","SourceHanSans",".ttf");
                break;
        }
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/crsEntityPdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsEntityPdfDownload(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("LimitedLiablityCrsE.ftl",CrsEntityPdf(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/crsEntityPdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsEntityPdfPreview(Long accountId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("LimitedLiablityCrsE.ftl",CrsEntityPdf(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/crsCpPdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsCpPdfDownload(Long accountId,Long crsEntityContrId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || crsEntityContrId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("LimitedLiablityCrsCp.ftl",crsCpPdf(accountId,crsEntityContrId,lang),response,crsEntityContrId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/limitedLiablity/crsCpPdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "pdf", httpMethod = "GET")
    public void crsCpPdfPreview(Long accountId,Long crsEntityContrId, String lang, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || crsEntityContrId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("LimitedLiablityCrsCp.ftl",crsCpPdf(accountId,crsEntityContrId,lang),response,"logo.png","SourceHanSans",".ttf");
    }


    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findInstitutional")
    @ApiOperation(value = "根据AccountId取公司机构信息", httpMethod = "GET")
    public JsonMessage findCoInstitutional(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountCoInstitutional(accountCoInstitutionalService.findList(accountCoInstitutional),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findInstitutionalAddress")
    @ApiOperation(value = "根据accountId取公司机构地址信息", httpMethod = "GET")
    public JsonMessage findCoInstitutionalAddress(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        // 查出机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional>accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
        //取公司地址信息
        List<AccountCoAddressModel> accountCoAddressModels = new ArrayList<>();
        for(AccountCoInstitutional institutional : accountCoInstitutionals){
            if(institutional.getId() != null){
                AccountCoAddress accountCoAddress = new AccountCoAddress();
                accountCoAddress.setAccountId(accountId);
                accountCoAddress.setCoPersonalId(institutional.getId());
                accountCoAddressModels.addAll(accountCoAddressService.findList(accountCoAddress,lang));
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountCoAddressModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findInstitutionalContact")
    @ApiOperation(value = "根据AccountId取公司机构联系信息", httpMethod = "GET")
    public JsonMessage findCoInstitutionalContact(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        // 查出机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional>accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
        // 查出机构联系信息
        List<AccountCoContactModel> accountCoContactModels = new ArrayList<>();
        for(AccountCoInstitutional institutional : accountCoInstitutionals){
            if(institutional.getId() != null){
                AccountCoContact accountCoContact = new AccountCoContact();
                accountCoContact.setAccountId(accountId);
                accountCoContact.setCoPersonalId(institutional.getId());
                accountCoContactModels.addAll(accountCoContactService.findList(accountCoContact,lang));
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountCoContactModels );
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findTaxes")
    @ApiOperation(value = "根据AccountId和机构信息的id取公司税务居住地信息", httpMethod = "GET")
    public JsonMessage findCoTaxes(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        // 查出机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional>accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
        // 查出机构税务居住地信息
        List<AccountCoTaxesModel> accountCoTaxesModels =new ArrayList<>();
        for(AccountCoInstitutional institutional : accountCoInstitutionals){
            if( institutional.getId() != null){
                AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
                accountCoTaxes.setAccountId(accountId);
                accountCoTaxes.setCoPersonalId(institutional.getId());
                accountCoTaxesModels.addAll(accountCoTaxesService.findList(accountCoTaxes,lang));
            }
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTaxesModels);
    }

    @GetMapping(value = "/registerOpen/limitedLiablity/findPortfolio")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据AccountId取公司账户组合信息", httpMethod = "GET")
    public JsonMessage findCoPortfolio(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoAccountPortfolio accountCoAccountPortfolio = new AccountCoAccountPortfolio();
        accountCoAccountPortfolio.setAccountId(accountId);
        List<AccountCoAccountPortfolio> accountCoAccountPortfolios = accountCoAccountPortfolioService.findList(accountCoAccountPortfolio);
        List<AccountCoAccountPortfolioModel> accountCoAccountPortfolioModels =new ArrayList<>();
        for(AccountCoAccountPortfolio accountPortfolio : accountCoAccountPortfolios ){
            AccountCoAccountPortfolioModel accountCoAccountPortfolioModel =new AccountCoAccountPortfolioModel()
                    .convertToModel(accountPortfolio)
                    .setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",accountPortfolio.getAccountPortfolioType(),lang))
                    .setBaseCurrency(dictionaryService.findDictionary("baseCurrency",accountPortfolio.getBaseCurrency(),lang));
            accountCoAccountPortfolioModels.add(accountCoAccountPortfolioModel);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountCoAccountPortfolioModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findAsset")
    @ApiOperation(value = "根据AccountId取公司资产信息", httpMethod = "GET")
    public JsonMessage findCoAsset(Long accountId) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountCoAsset accountCoAsset= new AccountCoAsset();
        accountCoAsset.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS,  accountCoAssetService.findList(accountCoAsset));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findCrsEntityContrs")
    @ApiOperation(value = "根据accountId取CRS控权人自我证明表数量", httpMethod = "GET")
    public JsonMessage findCrsEntityContrs(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountCoCrsEntity accountCoCrsEntity = new AccountCoCrsEntity();
        accountCoCrsEntity.setAccountId(accountId);
        List<AccountCoCrsEntity> accountCoCrsEntities = accountCoCrsEntityService.findList(accountCoCrsEntity);
        AccountCoCrsEntityContr crsEntityContr = new AccountCoCrsEntityContr();
        crsEntityContr.setAccountId(accountId);
        List<AccountCoCrsEntityContr> accountCoCrsEntityContrs = new ArrayList<>();
        for(AccountCoCrsEntity parCrsEntity : accountCoCrsEntities){
            crsEntityContr.setCoCrsEntityId(parCrsEntity.getId());
            accountCoCrsEntityContrs.addAll(accountCoCrsEntityContrService.findList(crsEntityContr));
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountCoCrsEntityContrs);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findCrsControl")
    @ApiOperation(value = "根据accountId取CRS控权人自我证明表格", httpMethod = "GET")
    public JsonMessage findCoCrsControl(Long accountId ,Long crsEntityId, String lang) throws BusinessException {
        if (null == accountId || crsEntityId == null) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoCrsEntityContr accountCoCrsEntityContr = new AccountCoCrsEntityContr();
        accountCoCrsEntityContr.setAccountId(accountId);
        accountCoCrsEntityContr.setCoCrsEntityId(crsEntityId);
        List<AccountCoCrsEntityContr> accountCoCrsEntityContrs = accountCoCrsEntityContrService.findList(accountCoCrsEntityContr);
        List<AccountCoCrsEntityContrModel> accountCoCrsEntityContrModels = new ArrayList<>();
        for(AccountCoCrsEntityContr crsEntityContr : accountCoCrsEntityContrs){
            AccountCoCrsControlNum accountCoCrsControlNum =new AccountCoCrsControlNum();
            accountCoCrsControlNum.setAccountId(accountId);
            accountCoCrsControlNum.setCoCrsEntityContrId(crsEntityContr.getId());
            AccountCoCrsEntityContrModel model = new AccountCoCrsEntityContrModel()
                    .setAccountCoCrsControls(crsControl(accountId,crsEntityContr.getId(),lang))
                    .setAccountCoCrsControlNums(getCoCrsControlNum(accountCoCrsControlNumService.findList(accountCoCrsControlNum),lang));
            accountCoCrsEntityContrModels.add(model);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsEntityContrModels);
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findCrsEntity")
    @ApiOperation(value = "根据AccountId取CRS实体自我证明表格", httpMethod = "GET")
    public JsonMessage findCoCrsEntity(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountCoCrsEntity accountCoCrsEntity = new AccountCoCrsEntity();
        accountCoCrsEntity.setAccountId(accountId);
        List<AccountCoCrsEntity> accountCoCrsEntities = accountCoCrsEntityService.findList(accountCoCrsEntity);
        List<AccountCoCrsEntityModel> accountCoCrsEntityModels = new ArrayList<>();
        AccountCoCrsEntityContr accountCoCrsEntityContr = new AccountCoCrsEntityContr();
        accountCoCrsEntityContr.setAccountId(accountId);
        for(AccountCoCrsEntity crsEntity:accountCoCrsEntities){
            accountCoCrsEntityContr.setCoCrsEntityId(crsEntity.getId());
            AccountCoCrsEntityModel model = new AccountCoCrsEntityModel()
                    .convertToModel(crsEntity)
                    .setAccountCoCrsEntityContrs(accountCoCrsEntityContrService.findList(accountCoCrsEntityContr));
            accountCoCrsEntityModels.add(model);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoCrsEntityModels);
    }

    @GetMapping(value = "/registerOpen/limitedLiablity/findFile")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "查询公司文件信息", httpMethod = "GET")
    public JsonMessage findCofindFile(Long accountId , String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoFile accountCoFile = new AccountCoFile();
        accountCoFile.setAccountId(accountId);
        return getJsonMessage(CommonEnums.SUCCESS, getAccountCoFile(accountCoFileService.findList(accountCoFile),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findInvestmentAccount")
    @ApiOperation(value = "根据accountId取公司一体化投资管理账户", httpMethod = "GET")
    public JsonMessage findCoInvestmentAccount(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountCoInvestmentAccount accountCoInvestmentAccount = new AccountCoInvestmentAccount();
        accountCoInvestmentAccount.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoInvestmentAccountService.findList(accountCoInvestmentAccount));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findInvestmentTarget")
    @ApiOperation(value = "根据AccountId取公司投资目标信息", httpMethod = "GET")
    public JsonMessage findCoInvestmentTarget(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoInvestmentTarget accountCoInvestmentTarget =new AccountCoInvestmentTarget();
        accountCoInvestmentTarget.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoInvestmentTargetService.findList(accountCoInvestmentTarget,lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findRegulatory")
    @ApiOperation(value = "根据accountId取公司规管信息", httpMethod = "GET")
    public JsonMessage findCoRegulatory(Long accountId,String lang) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoRegulatory accountCoRegulatory = new AccountCoRegulatory();
        accountCoRegulatory.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountCoRegulatory(accountCoRegulatoryService.findList(accountCoRegulatory),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findSafeQuestion")
    @ApiOperation(value = "根据accountId取公司安全问题", httpMethod = "GET")
    public JsonMessage findCoSafeQuestion(Long accountId,String lang) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoSafeQuestion accountCoSafeQuestion = new AccountCoSafeQuestion();
        accountCoSafeQuestion.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountCoSafeQuestion(accountCoSafeQuestionService.findList(accountCoSafeQuestion),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findSecurityOfficer")
    @ApiOperation(value = "根据accountId取公司安全官", httpMethod = "GET")
    public JsonMessage findCoSecurityOfficer(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoSecurityOfficer accountCoSecurityOfficer = new AccountCoSecurityOfficer();
        accountCoSecurityOfficer.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountCoSecurityOfficer(accountCoSecurityOfficerService.findList(accountCoSecurityOfficer),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findTaxClassification")
    @ApiOperation(value = "根据accountId取公司税收分类", httpMethod = "GET")
    public JsonMessage findCoTaxClassification(Long accountId,String lang) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoTaxClassification accountCoTaxClassification = new AccountCoTaxClassification();
        accountCoTaxClassification.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountCoTaxClassification(accountCoTaxClassificationService.findList(accountCoTaxClassification),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findTradeExperience")
    @ApiOperation(value = "根据AccountId取公司交易经验信息", httpMethod = "GET")
    public JsonMessage findCoTradeExperience(Long accountId,String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoTradeExperience accountCoTradeExperience = new AccountCoTradeExperience();
        accountCoTradeExperience.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoTradeExperienceService.findList(accountCoTradeExperience,lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findWbenContract")
    @ApiOperation(value = "根据AccountId和类型取公司w8ben表", httpMethod = "GET")
    public JsonMessage findCoWbenContract(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //税收分类
        AccountCoTaxClassification accountCoTaxClassification = new AccountCoTaxClassification();
        accountCoTaxClassification.setAccountId(accountId);
        List<AccountCoTaxClassification> accountCoTaxClassifications = accountCoTaxClassificationService.findList(accountCoTaxClassification);
        //W8
        AccountCoWbenContract accountCoWbenContract = new AccountCoWbenContract();
        accountCoWbenContract.setAccountId(accountId);
        if(accountCoTaxClassifications.isEmpty() == false){
            accountCoWbenContract.setType(accountCoTaxClassifications.get(0).getTaxType());
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoWbenContractService.findList(accountCoWbenContract));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findWealthSource")
    @ApiOperation(value = "根据AccountId取公司财富来源", httpMethod = "GET")
    public JsonMessage findCoWealthSource(Long accountId,String lang) throws BusinessException{
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoWealthSource accountCoWealthSource = new AccountCoWealthSource();
        accountCoWealthSource.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoWealthSourceService.findList(accountCoWealthSource,lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findCrsControlNum")
    @ApiOperation(value = "根据 accountId获取公司CRS控权人自我证明控权人编号", httpMethod = "GET")
    public JsonMessage findCoCrsControlNum(Long accountId,String lang) throws BusinessException {
        if (null == accountId ) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoCrsControlNum accountCoCrsControlNum =new AccountCoCrsControlNum();
        accountCoCrsControlNum.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getCoCrsControlNum(accountCoCrsControlNumService.findList(accountCoCrsControlNum),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findPerInfo")
    @ApiOperation(value = "根据 accountId 和 公司个人类型 获取 公司个人及其相关信息", httpMethod = "GET")
    public JsonMessage findCoPerInfo(Long accountId,String personalType,String lang) throws BusinessException {

        if (null == accountId ) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(accountId);
        accountCoPersonal.setPersonalType(personalType);
        return this.getJsonMessage(CommonEnums.SUCCESS,getCoPerInfo(accountId,accountCoPersonalService.findList(accountCoPersonal),lang));
    }

    @RequiresPermissions("account:registerOpen:data")
    @GetMapping(value = "/registerOpen/limitedLiablity/findBeneficiaryOwner")
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
        AccountCoBeneficiaryOwnerModel accountCoBeneficiaryOwnerModel = new AccountCoBeneficiaryOwnerModel();
        List<AccountCoPersonal> accountCoPersonalList = new ArrayList<>();
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(accountId);
        accountCoPersonal.setPersonalType(individualShareholder);
        accountCoPersonalList.addAll(accountCoPersonalService.findList(accountCoPersonal));
        accountCoPersonal.setPersonalType(TwentyFivePercentAboveBeneficiary);
        accountCoPersonalList.addAll(accountCoPersonalService.findList(accountCoPersonal));
        accountCoPersonal.setPersonalType(tenPercentAboveBeneficiary);
        accountCoPersonalList.addAll(accountCoPersonalService.findList(accountCoPersonal));
        accountCoPersonalList=prcentageOfOwnershipDesc(accountCoPersonalList);
        //使用迭代器删除列表中的经营负责人
        Iterator<AccountCoPersonal> iterator=accountCoPersonalList.iterator();
        while (iterator.hasNext()){
            AccountCoPersonal personal=iterator.next();
            if(personal.getPersonalTypeTwo()!=null && personal.getPersonalTypeTwo().equals(operationPrincipal)){
                iterator.remove();
            }
        }
        accountCoPersonal.setPersonalType(operationPrincipal);
        List<AccountCoPersonal> operationPrincipals =accountCoPersonalService.findList(accountCoPersonal);
        if(operationPrincipals.isEmpty()){
            accountCoPersonal.setPersonalType(null);
            accountCoPersonal.setPersonalTypeTwo(operationPrincipal);
            operationPrincipals = (accountCoPersonalService.findList(accountCoPersonal));
        }
        accountCoBeneficiaryOwnerModel.setOperationPrincipalList(getCoPerInfo(accountId,operationPrincipals,lang));
        accountCoBeneficiaryOwnerModel.setBeneficiaryOwnerList(getCoPerInfo(accountId,accountCoPersonalList,lang));
        return this.getJsonMessage(CommonEnums.SUCCESS, accountCoBeneficiaryOwnerModel);
    }

    /**
     * 获取公司个人信息
     * @param accountId
     * @param accountCoPersonalList
     * @param lang
     * @return
     */
    private  List<CompanyPersonalInfoModel> getCoPerInfo(Long accountId,List<AccountCoPersonal> accountCoPersonalList,String lang){

        List<CompanyPersonalInfoModel> companyPersonalInfoModels =new ArrayList<>();
        //取公司个人地址信息
        AccountCoAddress accountCoAddress = new AccountCoAddress();
        accountCoAddress.setAccountId(accountId);
        //取公司个人联系信息
        AccountCoContact accountCoContact = new AccountCoContact();
        accountCoContact.setAccountId(accountId);
        //取公司个人身份信息
        AccountCoIdentity accountCoIdentity = new AccountCoIdentity();
        accountCoIdentity.setAccountId(accountId);
        //取公司税务居住地信息
        AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
        accountCoTaxes.setAccountId(accountId);
        for(AccountCoPersonal coPersonal:accountCoPersonalList){
            if(null != coPersonal.getId()){
                CompanyPersonalInfoModel companyPersonalInfoModel = new CompanyPersonalInfoModel();
                accountCoAddress.setCoPersonalId(coPersonal.getId());
                accountCoContact.setCoPersonalId(coPersonal.getId());
                accountCoIdentity.setCoPersonalId(coPersonal.getId());
                accountCoTaxes.setCoPersonalId(coPersonal.getId());
                companyPersonalInfoModel.setAccountCoPersonal(getCoPerInfo(coPersonal,lang));
                companyPersonalInfoModel.setAccountCoAddressList(accountCoAddressService.findList(accountCoAddress,lang));
                companyPersonalInfoModel.setAccountCoContactList(accountCoContactService.findList(accountCoContact,lang));
                companyPersonalInfoModel.setAccountCoIdentityList(getAccountCoIdentity(accountCoIdentityService.findList(accountCoIdentity),lang));
                companyPersonalInfoModel.setAccountCoTaxesList(accountCoTaxesService.findList(accountCoTaxes,lang));
                companyPersonalInfoModels.add(companyPersonalInfoModel);
            }
        }
        return  companyPersonalInfoModels;
    }

    /**
     * 获取身份信息相关联的字典表详情
     * @param accountCoIdentities
     * @return
     */
    private List<AccountCoIdentityModel> getAccountCoIdentity(List<AccountCoIdentity> accountCoIdentities,String lang){
        List<AccountCoIdentityModel> accountCoIdentityModels =new LinkedList<>();
        for(AccountCoIdentity identity:accountCoIdentities){
            AccountCoIdentityModel accountCoIdentityModel = new AccountCoIdentityModel()
                    .convertToModel(identity)
                    .setIdCardType(dictionaryService.findDictionary("cardType",identity.getIdCardType(), lang))
                    .setIssuingCountry(regionService.findBysCode(identity.getIssuingCountry()));
            accountCoIdentityModels.add(accountCoIdentityModel);
        }
        return accountCoIdentityModels;
    }

    /**
     * 查找 安全问题 中关联的字典表信息
     * @param accountCoSafeQuestions
     * @return
     */
    private List<AccountCoSafeQuestionModel> getAccountCoSafeQuestion(List<AccountCoSafeQuestion> accountCoSafeQuestions,String lang){

        List<AccountCoSafeQuestionModel> accountCoSafeQuestionModels = new ArrayList<>();
        for(AccountCoSafeQuestion safeQuestion:accountCoSafeQuestions){
            AccountCoSafeQuestionModel accountCoSafeQuestionModel = new AccountCoSafeQuestionModel()
                    .convertToModel(safeQuestion)
                    .setSafeQuestion(dictionaryService.findDictionary("safeQuestionType",safeQuestion.getSafeQuestion(),lang));
            accountCoSafeQuestionModels.add(accountCoSafeQuestionModel);
        }
        return accountCoSafeQuestionModels;
    }

    /**
     * 获取 安全官 相关字典数据
     * @param accountCoSecurityOfficers
     * @return
     */
    private List<AccountCoSecurityOfficerModel> getAccountCoSecurityOfficer(List<AccountCoSecurityOfficer> accountCoSecurityOfficers,String lang){
        List<AccountCoSecurityOfficerModel> accountCoSecurityOfficerModels = new ArrayList<>();
        for(AccountCoSecurityOfficer securityOfficer : accountCoSecurityOfficers){
            AccountCoSecurityOfficerModel accountCoSecurityOfficerModel =new AccountCoSecurityOfficerModel()
                    .convertToModel(securityOfficer)
                    .setAppellation(dictionaryService.findDictionary("appellationType",securityOfficer.getAppellation(),lang));
            accountCoSecurityOfficerModels.add(accountCoSecurityOfficerModel);
        }
        return accountCoSecurityOfficerModels;
    }

    /**
     * 获取 税收分类相关字典表信息
     * @param accountCoTaxClassifications
     * @return
     */
    private List<AccountCoTaxClassificationModel> getAccountCoTaxClassification(List<AccountCoTaxClassification> accountCoTaxClassifications,String lang){
        List<AccountCoTaxClassificationModel> accountCoTaxClassificationModels = new ArrayList<>();
        for(AccountCoTaxClassification taxClassification : accountCoTaxClassifications){
            AccountCoTaxClassificationModel accountCoTaxClassificationModel = new AccountCoTaxClassificationModel()
                    .convertToModel(taxClassification)
                    .setTaxType(dictionaryService.findDictionary("taxType",taxClassification.getTaxType(),lang));
            accountCoTaxClassificationModels.add(accountCoTaxClassificationModel);
        }
        return accountCoTaxClassificationModels;
    }

    /**
     * 获取 文件相关字典表信息
     * @param accountCoFiles
     * @return
     */
    private List<AccountCoFileModel> getAccountCoFile(List<AccountCoFile> accountCoFiles,String lang){
        List<AccountCoFileModel> accountCoFileModels =new ArrayList<>();
        for(AccountCoFile file : accountCoFiles){
            AccountCoFileModel accountCoFileModel =new AccountCoFileModel()
                    .convertToModel(file);
            UploadFileType uploadFileType = new UploadFileType();
            uploadFileType.setTypeKey(file.getFileIdentification());
            List<UploadFileType> uploadFileTypes=uploadFileTypeService.findList(uploadFileType);
            if(uploadFileTypes.isEmpty()==false){
                accountCoFileModel.setFileIdentification(uploadFileTypes.get(0));
            }
            //文件股东
            if(accountCoFileModel.getCoPersonalId() != null){
                accountCoPersonalService.selectByPrimaryKey(accountCoFileModel.getCoPersonalId());
                accountCoFileModel.setPersonal(getCoPerInfo(accountCoPersonalService.selectByPrimaryKey(accountCoFileModel.getCoPersonalId()),lang));
            }
            accountCoFileModels.add(accountCoFileModel);
        }
        return accountCoFileModels;
    }

    /**
     * 获取规管信息相关字典表信息
     * @param accountCoRegulatories
     * @return
     */
    private  List<AccountCoRegulatoryModel> getAccountCoRegulatory(List<AccountCoRegulatory> accountCoRegulatories,String lang){
        List<AccountCoRegulatoryModel> accountCoRegulatoryModels = new ArrayList<>();
        for( AccountCoRegulatory regulatory: accountCoRegulatories){
            AccountCoRegulatoryModel accountCoRegulatoryModel =new AccountCoRegulatoryModel()
                    .convertToModel(regulatory)
                    .setInfoType(dictionaryService.findDictionary("regulatoryInfo",regulatory.getInfoType(),lang));
            accountCoRegulatoryModels.add(accountCoRegulatoryModel);
        }
        return accountCoRegulatoryModels;
    }

    /**
     * 获取公司CRS控权人自我证明控权人编号及相关字典信息
     * @param accountCoCrsControlNums
     * @return
     */
    private List<AccountCoCrsControlNumModel> getCoCrsControlNum(List<AccountCoCrsControlNum> accountCoCrsControlNums,String lang){
        List<AccountCoCrsControlNumModel> accountCoCrsControlNumModels = new ArrayList<>();
        for(AccountCoCrsControlNum crsControlNum : accountCoCrsControlNums)
        {
            AccountCoCrsControlNumModel coCrsControlNumModel = new AccountCoCrsControlNumModel()
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
            accountCoCrsControlNumModels.add(coCrsControlNumModel);
        }
        return accountCoCrsControlNumModels;
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
     * 获取公司机构及其字典信息
     * @param accountCoInstitutionals
     * @param lang
     * @return
     */
    private List<AccountCoInstitutionalModel> getAccountCoInstitutional(List<AccountCoInstitutional> accountCoInstitutionals,String lang){
        List<AccountCoInstitutionalModel> accountCoInstitutionalModels = new ArrayList<>();
        for(AccountCoInstitutional institutional: accountCoInstitutionals){
            AccountCoInstitutionalModel accountCoInstitutionalModel = new AccountCoInstitutionalModel()
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
            accountCoInstitutionalModel.setIstitutionalNature(dictionaries);
            accountCoInstitutionalModels.add(accountCoInstitutionalModel);
        }
        return accountCoInstitutionalModels;
    }

    /**
     * 获取公司pdf信息
     * @param accountId
     * @param lang
     * @return
     */
    private Map<String, Object> coPdf(Long accountId,String lang) {
        Map<String, Object> data = new HashMap<>();
        data.put("account", getAccount(accountId));
        //投资组合
        data.put("portfolios", getAccountPortfolios(accountId, lang));
        //机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        data.put("institutional", getAccountCoInstitutional(accountCoInstitutionalService.findList(accountCoInstitutional), lang));
        //机构地址
        data.putAll(getCoPdfAddress(accountId, lang));
        //机构联系信息
        data.put("institutionalContacts", getInstitutionalContact(accountId, lang));
        //机构税务居住地
        data.put("institutionalTaxes", getInstitutionalTaxes(accountId, lang));
        //财富来源
        AccountCoWealthSource accountCoWealthSource = new AccountCoWealthSource();
        accountCoWealthSource.setAccountId(accountId);
        data.put("wealthSources", accountCoWealthSourceService.findList(accountCoWealthSource, lang));
        //被授权人
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(accountId);
        accountCoPersonal.setPersonalType("authorizedPerson");
        List<CompanyPersonalInfoModel> companyPersonalInfoModels = getCoPerInfo(accountId, accountCoPersonalService.findList(accountCoPersonal), lang);
        companyPersonalInfoModels.get(0).setAccountCoAddressList(address(companyPersonalInfoModels.get(0).getAccountCoAddressList()));
        data.put("authorizedPersons", companyPersonalInfoModels);
        //税收分类
        lang = dictLangParameterVerification(lang);
        AccountCoTaxClassification accountCoTaxClassification = new AccountCoTaxClassification();
        accountCoTaxClassification.setAccountId(accountId);
        data.put("taxClassifications", getAccountCoTaxClassification(accountCoTaxClassificationService.findList(accountCoTaxClassification), lang));
        //规管信息
        AccountCoRegulatory accountCoRegulatory = new AccountCoRegulatory();
        accountCoRegulatory.setAccountId(accountId);
        data.put("regulatorys", getAccountCoRegulatory(accountCoRegulatoryService.findList(accountCoRegulatory), lang));
        //资产信息
        AccountCoAsset accountCoAsset = new AccountCoAsset();
        accountCoAsset.setAccountId(accountId);
        data.put("assets", accountCoAssetService.findList(accountCoAsset));
        //交易经验
        AccountCoTradeExperience accountCoTradeExperience = new AccountCoTradeExperience();
        accountCoTradeExperience.setAccountId(accountId);
        data.put("tradeExps", accountCoTradeExperienceService.findList(accountCoTradeExperience, lang));
        //投资目标
        AccountCoInvestmentTarget accountCoInvestmentTarget = new AccountCoInvestmentTarget();
        accountCoInvestmentTarget.setAccountId(accountId);
        data.put("investmentTargets", accountCoInvestmentTargetService.findList(accountCoInvestmentTarget, lang));
        //受益所有人，经营负责人
        AccountCoBeneficiaryOwnerModel accountCoBeneficiaryOwnerModel = getBeneficiaryOwnerModel(accountId, lang);
        accountCoBeneficiaryOwnerModel.getOperationPrincipalList().get(0).setAccountCoAddressList(address(accountCoBeneficiaryOwnerModel.getOperationPrincipalList().get(0).getAccountCoAddressList()));
        for(CompanyPersonalInfoModel model:accountCoBeneficiaryOwnerModel.getBeneficiaryOwnerList()){
            model.setAccountCoAddressList(address(model.getAccountCoAddressList()));
        }
        data.put("beneficiaryOwner", accountCoBeneficiaryOwnerModel);
//        注册拥有人（个人股东，法人股东）
        List<CompanyPersonalInfoModel> shareholder = new ArrayList<>();
        shareholder.addAll(findCoPer(accountId,"individualShareholder",lang));
        shareholder.addAll(findCoPer(accountId,"legalPersonShareholder",lang));
        for(CompanyPersonalInfoModel model:shareholder){
            model.setAccountCoAddressList(address(model.getAccountCoAddressList()));
        }
        data.put("shareholder", shareholder);
        return data;
    }
    /**
     * 公司 W8 pdf
     * @param accountId
     * @param lang
     * @return
     */
    private  Map<String,Object> W8Pdf(Long accountId,String lang) {
        Map<String, Object> data = new HashMap<>();
        //String转JSONObject
        JSONObject result = JSON.parseObject(getW8(accountId).get(0).getChecked());
        data.put("w8", getW8(accountId));
        data.put("w8Part",result.get("Part5isList"));
        data.putAll(getCoPdfAddress(accountId,lang));
        return data;
    }

    /**
     * 公司 Crs Entity pdf
     * @param accountId
     * @param lang
     * @return
     */
    private  Map<String,Object> CrsEntityPdf(Long accountId,String lang) {
        Map<String, Object> data = new HashMap<>();
        AccountCoCrsEntity accountCoCrsEntity = new AccountCoCrsEntity();
        accountCoCrsEntity.setAccountId(accountId);
        List<AccountCoCrsEntity> accountCoCrsEntities=accountCoCrsEntityService.findList(accountCoCrsEntity);
        for (AccountCoCrsEntity entity:accountCoCrsEntities) {
            entity.setOtherCountry(regionService.findBysCode(entity.getOtherCountry()).getEnName());
            if(entity.getEntityInput() != null){
                entity.setEntityInput( entity.getEntityInput().replace("-#-",","));
            }
            if(entity.getOtherAddresses() != null){
                entity.setOtherAddresses( entity.getOtherAddresses().replace("-#-",","));
            }
        }
        if(accountCoCrsEntities.isEmpty() == false){
            data.put("crsEntity",accountCoCrsEntities.get(0));
            AccountCoCrsEntityContr accountCoCrsEntityContr = new AccountCoCrsEntityContr();
            accountCoCrsEntityContr.setAccountId(accountId);
            accountCoCrsEntityContr.setCoCrsEntityId(accountCoCrsEntities.get(0).getId());
            data.put("crsEntityContrs",accountCoCrsEntityContrService.findList(accountCoCrsEntityContr));
        }
        data.putAll(getCoPdfAddress(accountId,lang));
        return data;
    }

    /**
     * 公司 CrsCp  pdf
     * @param
     * @param lang
     * @return
     */
    private  Map<String,Object> crsCpPdf(Long accountId,Long crsEntityContrId,String lang) {
        Map<String, Object> data = new HashMap<>();
        List<AccountCoCrsControlModel> crsControlModels = crsControl(accountId,crsEntityContrId,lang);
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
        AccountCoCrsControlNum accountCoCrsControlNum =new AccountCoCrsControlNum();
        accountCoCrsControlNum.setAccountId(accountId);
        accountCoCrsControlNum.setCoCrsEntityContrId(crsEntityContrId);
        List<AccountCoCrsControlNumModel> coCrsControlNumModels = getCoCrsControlNum(accountCoCrsControlNumService.findList(accountCoCrsControlNum),lang);
        for(AccountCoCrsControlNumModel crsControlNumModel:coCrsControlNumModels){
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
     * 投资组合
     * @param accountId
     * @param lang
     * @return
     */
    private  List<AccountCoAccountPortfolioModel> getAccountPortfolios(Long accountId,String lang){
        AccountCoAccountPortfolio accountCoAccountPortfolio = new AccountCoAccountPortfolio();
        accountCoAccountPortfolio.setAccountId(accountId);
        List<AccountCoAccountPortfolio> accountCoAccountPortfolios = accountCoAccountPortfolioService.findList(accountCoAccountPortfolio);
        List<AccountCoAccountPortfolioModel> accountCoAccountPortfolioModels =new ArrayList<>();
        for(AccountCoAccountPortfolio accountPortfolio : accountCoAccountPortfolios ){
            AccountCoAccountPortfolioModel accountCoAccountPortfolioModel =new AccountCoAccountPortfolioModel()
                    .convertToModel(accountPortfolio)
                    .setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",accountPortfolio.getAccountPortfolioType(),lang))
                    .setBaseCurrency(dictionaryService.findDictionary("baseCurrency",accountPortfolio.getBaseCurrency(),lang));
            accountCoAccountPortfolioModels.add(accountCoAccountPortfolioModel);
        }
        return accountCoAccountPortfolioModels;
    }

    /**
     * 公司联系信息
     * @param accountId
     * @param lang
     */
    private List<AccountCoContactModel>  getInstitutionalContact(Long accountId,String lang){
        // 查出机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional>accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
        // 查出机构联系信息
        List<AccountCoContactModel> accountCoContactModels = new ArrayList<>();
        for(AccountCoInstitutional institutional : accountCoInstitutionals){
            if(institutional.getId() != null){
                AccountCoContact accountCoContact = new AccountCoContact();
                accountCoContact.setAccountId(accountId);
                accountCoContact.setCoPersonalId(institutional.getId());
                accountCoContactModels.addAll(accountCoContactService.findList(accountCoContact,lang));
            }
        }
        return accountCoContactModels;
    }

    /**
     * 机构税务居住地
     * @return
     */
    private List<AccountCoTaxesModel> getInstitutionalTaxes(Long accountId,String lang){
        // 查出机构信息
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional>accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
        // 查出机构税务居住地信息
        List<AccountCoTaxesModel> accountCoTaxesModels =new ArrayList<>();
        for(AccountCoInstitutional institutional : accountCoInstitutionals){
            if( institutional.getId() != null){
                AccountCoTaxes accountCoTaxes = new AccountCoTaxes();
                accountCoTaxes.setAccountId(accountId);
                accountCoTaxes.setCoPersonalId(institutional.getId());
                accountCoTaxesModels.addAll(accountCoTaxesService.findList(accountCoTaxes,lang));
            }
        }
        return accountCoTaxesModels;
    }
    /**
     * 公司 pdf 地址
     * @param accountId
     * @param lang
     * @return
     */
    private Map<String, Object> getCoPdfAddress(Long accountId,String lang){
        Map<String, Object> data = new HashMap<>();
        AccountCoInstitutional accountCoInstitutional = new AccountCoInstitutional();
        accountCoInstitutional.setAccountId(accountId);
        List<AccountCoInstitutional>accountCoInstitutionals=accountCoInstitutionalService.findList((accountCoInstitutional));
        if(accountCoInstitutionals.isEmpty()==false && accountCoInstitutionals.get(0).getId()!=null){
            AccountCoAddress accountCoAddress = new AccountCoAddress();
            accountCoAddress.setAccountId(accountId);
            accountCoAddress.setCoPersonalId(accountCoInstitutionals.get(0).getId());
            accountCoAddress.setAddressType("personalAddress");
            data.put("personalAddress", address(accountCoAddressService.findList(accountCoAddress,lang)).get(0));
            accountCoAddress.setAddressType("mail");
            if(accountCoAddressService.findList(accountCoAddress,lang).isEmpty()==false){
                data.put("mailAddress", address(accountCoAddressService.findList(accountCoAddress,lang)).get(0));
            }
        }
        return data;
    }
    /**
     * 受益所有人
     * 经营负责人
     * @param accountId
     * @param lang
     * @return
     */
    private AccountCoBeneficiaryOwnerModel getBeneficiaryOwnerModel(Long accountId,String lang){
        //经营负责人
        String operationPrincipal = "operationPrincipal";
        //个人股东
        String individualShareholder = "individualShareholder";
        //25%或以上的受益人
        String TwentyFivePercentAboveBeneficiary = "25%AboveBeneficiary";
        //10%或以上的受益人
        String tenPercentAboveBeneficiary = "10%AboveBeneficiary";
        lang =dictLangParameterVerification(lang);
        AccountCoBeneficiaryOwnerModel accountCoBeneficiaryOwnerModel = new AccountCoBeneficiaryOwnerModel();
        List<AccountCoPersonal> accountCoPersonalList = new ArrayList<>();
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(accountId);
        accountCoPersonal.setPersonalType(individualShareholder);
        accountCoPersonalList.addAll(accountCoPersonalService.findList(accountCoPersonal));
        accountCoPersonal.setPersonalType(TwentyFivePercentAboveBeneficiary);
        accountCoPersonalList.addAll(accountCoPersonalService.findList(accountCoPersonal));
        accountCoPersonal.setPersonalType(tenPercentAboveBeneficiary);
        accountCoPersonalList.addAll(accountCoPersonalService.findList(accountCoPersonal));
        accountCoPersonalList=prcentageOfOwnershipDesc(accountCoPersonalList);
        //使用迭代器删除列表中的经营负责人
        Iterator<AccountCoPersonal> iterator=accountCoPersonalList.iterator();
        while (iterator.hasNext()){
            AccountCoPersonal personal=iterator.next();
            if(personal.getPersonalTypeTwo()!=null && personal.getPersonalTypeTwo().equals(operationPrincipal)){
                iterator.remove();
            }
        }
        accountCoPersonal.setPersonalType(operationPrincipal);
        List<AccountCoPersonal> operationPrincipals =accountCoPersonalService.findList(accountCoPersonal);
        if(operationPrincipals.isEmpty()){
            accountCoPersonal.setPersonalType(null);
            accountCoPersonal.setPersonalTypeTwo(operationPrincipal);
            operationPrincipals = (accountCoPersonalService.findList(accountCoPersonal));
        }
        accountCoBeneficiaryOwnerModel.setOperationPrincipalList(getCoPerInfo(accountId,operationPrincipals,lang));
        accountCoBeneficiaryOwnerModel.setBeneficiaryOwnerList(getCoPerInfo(accountId,accountCoPersonalList,lang));
        return accountCoBeneficiaryOwnerModel;
    }
    public List<CompanyPersonalInfoModel> findCoPer(Long accountId,String personalType,String lang) throws BusinessException {
        if (null == accountId ) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountCoPersonal accountCoPersonal = new AccountCoPersonal();
        accountCoPersonal.setAccountId(accountId);
        accountCoPersonal.setPersonalType(personalType);
        return getCoPerInfo(accountId,accountCoPersonalService.findList(accountCoPersonal),lang);
    }
    /**
     * w8
     * @param accountId
     * @return
     */
    private List<AccountCoWbenContract> getW8(Long accountId){
        //税收分类
        AccountCoTaxClassification accountCoTaxClassification = new AccountCoTaxClassification();
        accountCoTaxClassification.setAccountId(accountId);
        List<AccountCoTaxClassification> accountCoTaxClassifications = accountCoTaxClassificationService.findList(accountCoTaxClassification);
        //W8
        AccountCoWbenContract accountCoWbenContract = new AccountCoWbenContract();
        accountCoWbenContract.setAccountId(accountId);
        if(accountCoTaxClassifications.isEmpty() == false){
            accountCoWbenContract.setType(accountCoTaxClassifications.get(0).getTaxType());
        }
        List<AccountCoWbenContract> wbenContracts=accountCoWbenContractService.findList(accountCoWbenContract);
        for (AccountCoWbenContract wbenContract:wbenContracts) {
            wbenContract.setOrganizationCountry(regionService.findBysCode(wbenContract.getOrganizationCountry()).getEnName());
        }
        return accountCoWbenContractService.findList(accountCoWbenContract);
    }
    /**
     * crs控权人
     * @param accountId
     * @param lang
     * @return
     */
    private List<AccountCoCrsControlModel> crsControl(Long accountId,Long CrsEntityContrId,String lang){
        AccountCoCrsControl accountCoCrsControl = new AccountCoCrsControl();
        accountCoCrsControl.setAccountId(accountId);
        accountCoCrsControl.setCoCrsEntityContrId(CrsEntityContrId);
        List<AccountCoCrsControl> accountCoCrsControls=accountCoCrsControlService.findList(accountCoCrsControl);
        List<AccountCoCrsControlModel> accountCoCrsControlModels = Lists.newArrayList();
        AccountCoAddress accountCoAddress = new AccountCoAddress();
        accountCoAddress.setAccountId(accountId);
        AccountCoCrsControlNum accountCoCrsControlNum =new AccountCoCrsControlNum();
        accountCoCrsControlNum.setAccountId(accountId);
        for (AccountCoCrsControl crsControl:accountCoCrsControls) {
            accountCoAddress.setCoPersonalId(crsControl.getId());
            accountCoCrsControlNum.setCoCrsEntityContrId(crsControl.getId());
            AccountCoCrsControlModel model = new AccountCoCrsControlModel()
                    .convertToModel(crsControl)
                    .setIdCardType(dictionaryService.findDictionary("cardType",crsControl.getIdCardType(), lang))
                    .setAppellation(dictionaryService.findDictionary("appellationType",crsControl.getAppellation(),lang))
                    .setAccountCoAddressList(accountCoAddressService.findList(accountCoAddress,lang))
                    ;
            accountCoCrsControlModels.add(model);
        }
        return accountCoCrsControlModels;
    }
    /**
     * 地址 分隔符处理
     * @param addressModels
     * @return
     */
    private  List<AccountCoAddressModel> address(List<AccountCoAddressModel> addressModels){
        for(AccountCoAddressModel address:addressModels){
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
     * 根据公司个人所有权百分比(prcentageOfOwnership)降序排序
     * @param accountCoPersonals
     * @return
     */
    private List<AccountCoPersonal>  prcentageOfOwnershipDesc (List<AccountCoPersonal>  accountCoPersonals){
        /*
         * int compare(CompanyPersonalInfoModel o1, CompanyPersonalInfoModel o2) 返回一个基本类型的整型，
         * 返回负数表示：o1 大于o2，
         * 返回0 表示：o1和o2相等，
         * 返回正数表示：o1小于o2。
         */
        Collections.sort(accountCoPersonals, (o1, o2) -> {
            int i = 1;
            if( null == o1.getPrcentageOfOwnership()){
                return  i;
            }
            if(null == o2.getPrcentageOfOwnership()){
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
        return accountCoPersonals;
    }
}
