package com.fintech.gams.controller.account;

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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author yann
 * @date 2019-08-07 14:46
 * @Description
 */
@RestController
@RequestMapping(GlobalConst.ACCOUNT)
@Api(description = "帐户个人开户管理")
public class AccountPerRegisterOpenController  extends GenericController {
    @Autowired(required = false)
    private AccountPerAddressService accountPerAddressService;
    @Autowired(required = false)
    private AccountPerAssetService accountPerAssetService;
    @Autowired(required = false)
    private RegionService regionService;
    @Autowired(required = false)
    private AccountPerContactService accountPerContactService;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountPerCrsService accountPerCrsService;
    @Autowired(required = false)
    private AccountPerEmploymentService accountPerEmploymentService;
    @Autowired(required = false)
    private AccountPerFileService accountPerFileService;
    @Autowired(required = false)
    private AccountPerIdentityService accountPerIdentityService;
    @Autowired(required = false)
    private AccountPerInfoService accountPerInfoService;
    @Autowired(required = false)
    private AccountPerInvestAccountService accountPerInvestAccountService;
    @Autowired(required = false)
    private AccountPerInvestTargetService accountPerInvestTargetService;
    @Autowired(required = false)
    private AccountPerPortfolioService accountPerPortfolioService;
    @Autowired(required = false)
    private AccountPerRegulatoryService accountPerRegulatoryService;
    @Autowired(required = false)
    private AccountPerSafeQuestionService accountPerSafeQuestionService;
    @Autowired(required = false)
    private AccountPerTaxesService accountPerTaxesService;
    @Autowired(required = false)
    private AccountPerTradeExpService accountPerTradeExpService;
    @Autowired(required = false)
    private AccountPerWbenContractService accountPerWbenContractService;
    @Autowired(required = false)
    private AccountPerWealthSourceService accountPerWealthSourceService;
    @Autowired(required = false)
    private AccountService accountService;
    @Autowired(required = false)
    PdfUtils pdfUtils;


    @RequestMapping(value = "/registerOpen/personal/pdf/download", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "个人PDF下载", httpMethod = "GET")
    public void perPdfDownload(Long accountId, String lang, HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("PerInfo.ftl",perInfo(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @RequestMapping(value = "/registerOpen/personal/pdf/preview", method = RequestMethod.GET)
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "个人PDF预览", httpMethod = "GET")
    public void perPdfPreview(Long accountId, String lang,HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("PerInfo.ftl",perInfo(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @GetMapping(value = "/registerOpen/personal/crsPdf/download")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "个人CRS PDF", httpMethod = "GET")
    public void perCrsDown(Long accountId, String lang,HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //根据accountId查询个人信息
        pdfUtils.download("PerCrs.ftl",PerCrs(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @GetMapping(value = "/registerOpen/personal/crsPdf/preview")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "个人CRS PDF", httpMethod = "GET")
    public void perCrsPreview(Long accountId, String lang,HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("PerCrs.ftl",PerCrs(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }

    @GetMapping(value = "/registerOpen/personal/w8Pdf/download")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "个人W8 PDF", httpMethod = "GET")
    public void perWbenDown(Long accountId, String lang,HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.download("PerW8.ftl",perWben(accountId,lang),response,accountId+".pdf","logo.png","SourceHanSans",".ttf");
    }

    @GetMapping(value = "/registerOpen/personal/w8Pdf/preview")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "个人W8 PDF", httpMethod = "GET")
    public void perWbenPreview(Long accountId, String lang,HttpServletRequest request, HttpServletResponse response) throws BusinessException
    {
        if(accountId ==null || StringUtils.isBlank(lang)){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        pdfUtils.preview("PerW8.ftl",perWben(accountId,lang),response,"logo.png","SourceHanSans",".ttf");
    }
    private Map<String,Object> PerCrs(Long accountId, String lang){
        Map<String,Object> data = new HashMap<>();
        data.put("account",accountService.selectByPrimaryKey(accountId));
        data.put("perInfo", getPerInfo(accountId));
        List<AccountPerAddressModel> addresse = getPerAddress(accountId,lang);
        AccountPerAddressModel personalAddress = new AccountPerAddressModel();
        AccountPerAddressModel mailAddress = new AccountPerAddressModel();
        for(AccountPerAddressModel address:addresse){
            if(address.getAddress() !=null){
                address.setAddress(address.getAddress().replace("-#-",","));
            }
            if(address.getAddressType().endsWith("personalAddress")){
                personalAddress=address;
            }
            if(address.getAddressType().endsWith("mail")){
                mailAddress=address;
            }
        }
        data.put("personalAddress", personalAddress);
        data.put("mailAddress", mailAddress);
        data.put("crs", getPerCrs(accountId,lang));
        data.put("taxes", getPerTaxes(accountId,lang));
        return data;
    }

    @GetMapping(value = "/registerOpen/personal/findAddress")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人地址", httpMethod = "GET")
    public JsonMessage findPerAddress(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerAddress accountPerAddress = new AccountPerAddress();
        accountPerAddress.setAccountId(accountId);
        return getJsonMessage(CommonEnums.SUCCESS,accountPerAddressService.findList(accountPerAddress,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findAsset")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人资产", httpMethod = "GET")
    public JsonMessage findPerAsset(Long accountId) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountPerAsset accountPerAsset = new AccountPerAsset();
        accountPerAsset.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerAssetService.findList(accountPerAsset));
    }

    @GetMapping(value = "/registerOpen/personal/findContact")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人联系方式", httpMethod = "GET")
    public JsonMessage findPerContact(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        //根据accountId查询 个人联系方式
        AccountPerContact accountPerContact = new AccountPerContact();
        accountPerContact.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS,accountPerContactService.findList(accountPerContact,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findCrs")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人CRS", httpMethod = "GET")
    public JsonMessage findPerCrs(Long accountId) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountPerCrs accountPerCrs = new AccountPerCrs();
        accountPerCrs.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerCrsService.findList(accountPerCrs));
    }

    @GetMapping(value = "/registerOpen/personal/findEmployment")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人雇佣(就业)信息", httpMethod = "GET")
    public JsonMessage findPerEmployment(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        //根据accountId查询个人雇佣信息
        AccountPerEmployment accountPerEmployment = new AccountPerEmployment();
        accountPerEmployment.setAccountId(accountId);
        List<AccountPerEmployment> accountPerEmployments=accountPerEmploymentService.findList(accountPerEmployment);
        //查找个人雇佣信息中关联的字典表信息
        List<AccountPerEmploymentModel> accountPerEmploymentModels = new ArrayList<>();
        for(AccountPerEmployment perEmployment:accountPerEmployments){
            AccountPerEmploymentModel accountPerEmploymentModel = new AccountPerEmploymentModel()
                    .convertToModel(perEmployment)
                    .setEmploymentStatus(dictionaryService.findDictionary("employmentType",perEmployment.getEmploymentStatus(),lang));
            if(perEmployment.getCommercialNature() != null){
                accountPerEmploymentModel.setCommercialNature(dictionaryService.findDictionary("businessNature",perEmployment.getCommercialNature(),lang));
            }
            if(perEmployment.getPosition() != null){
                accountPerEmploymentModel.setPosition(dictionaryService.findDictionary("position",perEmployment.getPosition(),lang));
            }
            accountPerEmploymentModels.add(accountPerEmploymentModel);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerEmploymentModels);
    }

    @GetMapping(value = "/registerOpen/personal/findFile")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据ID取个人文件", httpMethod = "GET")
    public JsonMessage findPerFile(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerFile accountPerFile = new AccountPerFile();
        accountPerFile.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, getAccountPerFile(accountPerFileService.findList(accountPerFile),lang));
    }

    @GetMapping(value = "/registerOpen/personal/findIdentity")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人身份信息", httpMethod = "GET")
    public JsonMessage findPerIdentity(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        //根据accountId查询 个人身份信息
        AccountPerIdentity accountPerIdentity = new AccountPerIdentity();
        accountPerIdentity.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS,accountPerIdentityService.findList(accountPerIdentity,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findPerInfo")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据AccountId取个人信息", httpMethod = "GET")
    public JsonMessage findPerInfo(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        return this.getJsonMessage(CommonEnums.SUCCESS, getPerInfo(accountId,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findInvestAccount")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据 accountId 取个人一体化投资管理账户功能", httpMethod = "GET")
    public JsonMessage findPerInvestAccount(Long accountId) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountPerInvestAccount accountPerInvestAccount = new AccountPerInvestAccount();
        accountPerInvestAccount.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerInvestAccountService.findList(accountPerInvestAccount));
    }

    @GetMapping(value = "/registerOpen/personal/findInvestTarget")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人投资目标", httpMethod = "GET")
    public JsonMessage findPerInvestTarget(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerInvestTarget accountPerInvestTarget =new AccountPerInvestTarget();
        accountPerInvestTarget.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerInvestTargetService.findList(accountPerInvestTarget,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findPortfolio")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人账户投资组合", httpMethod = "GET")
    public JsonMessage findPerPortfolio(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        return this.getJsonMessage(CommonEnums.SUCCESS,getAccountPortfolios(accountId,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findRegulatory")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人账户规管信息", httpMethod = "GET")
    public JsonMessage findPerRegulatory(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerRegulatory accountPerRegulatory = new AccountPerRegulatory();
        accountPerRegulatory.setAccountId(accountId);
        List<AccountPerRegulatory> perRegulatories = accountPerRegulatoryService.findList(accountPerRegulatory);
        List<AccountPerRegulatoryModel> models =new ArrayList<>();
        for(AccountPerRegulatory regulatory: perRegulatories){
            AccountPerRegulatoryModel model =new AccountPerRegulatoryModel()
                    .convertToModel(regulatory)
                    .setRelationshipAccountHolders(findDictionary("accountHolderRelationship",regulatory.getRelationshipAccountHolders(),lang));
            models.add(model);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, models);
    }

    @GetMapping(value = "/registerOpen/personal/findSafeQuestion")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人安全问题", httpMethod = "GET")
    public JsonMessage findPerSafeQuestion(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        //根据accountId查询 个人安全问题
        AccountPerSafeQuestion accountPerSafeQuestion = new AccountPerSafeQuestion();
        accountPerSafeQuestion.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS,getAccountPerSafeQuestion(accountPerSafeQuestionService.findList(accountPerSafeQuestion),lang));
    }

    @GetMapping(value = "/registerOpen/personal/findTaxes")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人税务居住地", httpMethod = "GET")
    public JsonMessage findPerTaxes(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerTaxes accountPerTaxes = new AccountPerTaxes();
        accountPerTaxes.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerTaxesService.findList(accountPerTaxes,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findTradeExp")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人交易经验信息", httpMethod = "GET")
    public JsonMessage findPerTradeExp(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerTradeExp accountPerTradeExp = new AccountPerTradeExp();
        accountPerTradeExp.setAccountId(accountId);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerTradeExpService.findList(accountPerTradeExp,lang));
    }

    @GetMapping(value = "/registerOpen/personal/findWbenContract")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取个人WBen合约", httpMethod = "GET")
    public JsonMessage findPerWbenContract(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        AccountPerWbenContract accountPerWbenContract=new AccountPerWbenContract();
        accountPerWbenContract.setAccountId(accountId);
        List<AccountPerWbenContract> accountPerWbenContracts = accountPerWbenContractService.findList(accountPerWbenContract);
        List<AccountPerWbenContractModel> accountPerWbenContractModels =new ArrayList<>();
        for(AccountPerWbenContract wbenContract:accountPerWbenContracts){
            AccountPerWbenContractModel accountPerWbenContractModel = new AccountPerWbenContractModel()
                    .convertToModel(wbenContract)
                    .setOtherNationality(regionService.findBysCode(wbenContract.getOtherNationality()))
                    .setUsaCountryAgreement(regionService.findBysCode(wbenContract.getUsaCountryAgreement()))
                    .setExplainAddress(dictionaryService.findDictionary("taxIdentificationNumber",wbenContract.getExplainAddress(),lang));
            if(accountPerWbenContractModel.getExplainAddress() != null && StringUtils.isBlank(accountPerWbenContractModel.getExplainAddress().getDictDest())==false){
                //替换 占位字段 country
                if(accountPerWbenContractModel.getExplainAddress().getDictDest().contains("country") && accountPerWbenContractModel.getUsaCountryAgreement()!=null){
                    if(lang.endsWith("zh_CN")){
                        accountPerWbenContractModel.getExplainAddress().setDictDest(accountPerWbenContractModel.getExplainAddress().getDictDest().replace("country",accountPerWbenContractModel.getUsaCountryAgreement().getCnName()));
                    }
                    if(lang.endsWith("en_US")){
                        accountPerWbenContractModel.getExplainAddress().setDictDest(accountPerWbenContractModel.getExplainAddress().getDictDest().replace("country",accountPerWbenContractModel.getUsaCountryAgreement().getEnName()));
                    }
                }
            }
            //字典为空则判定为用户填写
            if(accountPerWbenContractModel.getExplainAddress() == null || StringUtils.isBlank(accountPerWbenContractModel.getExplainAddress().getDictValue())){
                Dictionary dictionary =new Dictionary();
                dictionary.setDictDest(wbenContract.getExplainAddress());
                accountPerWbenContractModel.setExplainAddress(dictionary);
            }

            accountPerWbenContractModels.add(accountPerWbenContractModel);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS,accountPerWbenContractModels);
    }

    @GetMapping(value = "/registerOpen/personal/findWealthSource")
    @RequiresPermissions("account:registerOpen:data")
    @ApiOperation(value = "根据accountId取财富来源", httpMethod = "GET")
    public JsonMessage findPerWealthSource(Long accountId,String lang) throws BusinessException
    {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang =dictLangParameterVerification(lang);
        //根据accountId查询 个人财富来源
        AccountPerWealthSource accountPerWealthSource =new AccountPerWealthSource();
        accountPerWealthSource.setAccountId(accountId);
        List<AccountPerWealthSource> accountPerWealthSources = accountPerWealthSourceService.findList(accountPerWealthSource);
        //查找 个人财富来源 中关联的字典表信息
        List<AccountPerWealthSourceModel> accountPerWealthSourceModels =new LinkedList<>();
        for(AccountPerWealthSource perIdentity:accountPerWealthSources){
            AccountPerWealthSourceModel accountPerWealthSourceModel = new AccountPerWealthSourceModel()
                    .convertToModel(perIdentity)
                    .setIncomeType(dictionaryService.findDictionary("personalWealthSource",perIdentity.getIncomeType(),lang));
            accountPerWealthSourceModels.add(accountPerWealthSourceModel);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountPerWealthSourceModels);
    }

    /**
     * 查找 个人安全问题 中关联的字典表信息
     * @param accountPerSafeQuestions
     * @return
     */
    private List<AccountPerSafeQuestionModel> getAccountPerSafeQuestion(List<AccountPerSafeQuestion> accountPerSafeQuestions,String lang){
        List<AccountPerSafeQuestionModel> accountPerSafeQuestionModels = new ArrayList<>();
        for(AccountPerSafeQuestion perSafeQuestion:accountPerSafeQuestions){
            AccountPerSafeQuestionModel accountPerSafeQuestionModel = new AccountPerSafeQuestionModel()
                    .convertToModel(perSafeQuestion)
                    .setSafeQuestion(dictionaryService.findDictionary("safeQuestionType",perSafeQuestion.getSafeQuestion(),lang));
            accountPerSafeQuestionModels.add(accountPerSafeQuestionModel);
        }
        return accountPerSafeQuestionModels;
    }

    /**
     * 获取 文件相关字典表信息
     * @param accountPerFiles
     * @return
     */
    private List<AccountPerFileModel> getAccountPerFile(List<AccountPerFile> accountPerFiles,String lang){
        List<AccountPerFileModel> accountPerFileModels =new ArrayList<>();
        for(AccountPerFile file : accountPerFiles){
            AccountPerFileModel accountPerFileModel =new AccountPerFileModel()
                    .convertToModel(file)
                    .setFileIdentification(dictionaryService.findDictionary("fileIdCardType",file.getFileIdentification(),lang));
            accountPerFileModels.add(accountPerFileModel);
        }
        return accountPerFileModels;
    }

    /**
     * per ITextPdf info
     * @param accountId
     * @param lang
     * @return
     */
    private Map<String,Object> perInfo(Long accountId, String lang){
        Map<String,Object> data = new HashMap<>();
        data.put("account",getAccount(accountId));
        data.put("portfolios",getAccountPortfolios(accountId,lang));
        data.put("perInfos", getPerInfo(accountId,lang));
        data.put("contacts", getPerContact(accountId,lang));
        data.put("identity", getPerIdentity(accountId,lang));
        data.put("taxes", getPerTaxes(accountId,lang));
        List<AccountPerEmploymentModel> accountPerEmploymentModels= getPerEmployment(accountId,lang);
        data.put("employments", getPerEmployment(accountId,lang));
        data.put("wealthSources", getPerWealthSource(accountId,lang));
        data.put("regulatorys", getPerRegulatory(accountId,lang));
        data.put("assets", getPerAsset(accountId,lang));
        data.put("investTargets", getPerInvestTarget(accountId,lang));
        data.put("tradeExps", getPerTradeExp(accountId,lang));
//        data.put("agreements", getPerAgreement(accountId,lang));
        List<AccountPerAddressModel> addresse = getPerAddress(accountId,lang);
        AccountPerAddressModel personalAddress = new AccountPerAddressModel();
        AccountPerAddressModel mailAddress = new AccountPerAddressModel();
        AccountPerAddressModel employmentAddress = new AccountPerAddressModel();
        for(AccountPerAddressModel address:addresse){
            if(address.getAddress() !=null){
                address.setAddress(address.getAddress().replace("-#-",","));
            }
            if(address.getAddressType().endsWith("personalAddress")){
                personalAddress=address;
            }
            if(address.getAddressType().endsWith("mail")){
                mailAddress=address;
            }
            if(address.getAddressType().endsWith("employmentAddress")){
                employmentAddress=address;
            }
        }
        data.put("personalAddress", personalAddress);
        data.put("mailAddress", mailAddress);
        data.put("employmentAddress", employmentAddress);
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
     * per info
     * @param accountId
     * @param lang
     * @return
     */
    private List<AccountPerInfoModel>  getPerInfo(Long accountId,String lang){
        //根据accountId查询个人信息
        AccountPerInfo accountPerInfo = new AccountPerInfo();
        accountPerInfo.setAccountId(accountId);
        List<AccountPerInfo> accountPerInfos= accountPerInfoService.findList(accountPerInfo);
        //查找 个人雇佣 中关联的字典表信息
        List<AccountPerInfoModel> accountPerInfoModels= new ArrayList<>();
        for(AccountPerInfo perInfo :accountPerInfos){
            AccountPerInfoModel accountPerInfoModel= new AccountPerInfoModel()
                    .convertToModel(perInfo)
                    .setAppellation(dictionaryService.findDictionary("appellationType",perInfo.getAppellation(),lang))
                    .setGender(dictionaryService.findDictionary("gender",perInfo.getGender(),lang))
                    .setMaritalStatus(dictionaryService.findDictionary("maritalStatus",perInfo.getMaritalStatus(),lang))
                    .setNumberOfFamilyMembers(dictionaryService.findDictionary("familyNumbers",perInfo.getNumberOfFamilyMembers(),lang));
            accountPerInfoModels.add(accountPerInfoModel);
        }
        return accountPerInfoModels;
    }
    /**
     * 投资组合
     * @param accountId
     * @param lang
     * @return
     */
    private  List<AccountPerPortfolioModel> getAccountPortfolios(Long accountId,String lang){
        //根据accountId查询 个人账户投资组合
        AccountPerPortfolio accountPerPortfolio = new AccountPerPortfolio();
        accountPerPortfolio.setAccountId(accountId);
        List<AccountPerPortfolio> accountPerPortfolios = accountPerPortfolioService.findList(accountPerPortfolio);
        //查找 个人账户投资组合 中关联的字典表信息
        List<AccountPerPortfolioModel> accountPerPortfolioModels = new LinkedList<>();
        for(AccountPerPortfolio perPortfolio:accountPerPortfolios){
            AccountPerPortfolioModel accountPerPortfolioModel = new AccountPerPortfolioModel()
                    .convertToModel(perPortfolio)
                    .setAccountPortfolioType(dictionaryService.findDictionary("accountPortfolioType",perPortfolio.getAccountPortfolioType(),lang))
                    .setBaseCurrency(dictionaryService.findDictionary("baseCurrency",perPortfolio.getBaseCurrency(),lang));
            accountPerPortfolioModels.add(accountPerPortfolioModel);
        }
        return accountPerPortfolioModels;
    }
    /**
     * 个人联系
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerContact(Long accountId,String lang){
        AccountPerContact accountPerContact = new AccountPerContact();
        accountPerContact.setAccountId(accountId);
        return accountPerContactService.findList(accountPerContact,lang);
    }
    /**
     * 个人身份
     * @param accountId
     * @param lang
     * @return
     */
    private AccountPerIdentityModel getPerIdentity(Long accountId, String lang){
        AccountPerIdentity accountPerIdentity = new AccountPerIdentity();
        accountPerIdentity.setAccountId(accountId);
        List<AccountPerIdentityModel> identityModels=accountPerIdentityService.findList(accountPerIdentity,lang);
        if(identityModels.isEmpty()){
            return new AccountPerIdentityModel();
        }
        return identityModels.get(0);
    }
    /**
     *税务信息
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerTaxes(Long accountId,String lang){
        AccountPerTaxes accountPerTaxes = new AccountPerTaxes();
        accountPerTaxes.setAccountId(accountId);
        return accountPerTaxesService.findList(accountPerTaxes,lang);
    }
    /**
     * 个人雇佣
     * @param accountId
     * @param lang
     * @return
     */
    private List<AccountPerEmploymentModel> getPerEmployment(Long accountId,String lang){
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //根据accountId查询个人雇佣信息
        AccountPerEmployment accountPerEmployment = new AccountPerEmployment();
        accountPerEmployment.setAccountId(accountId);
        List<AccountPerEmployment> accountPerEmployments=accountPerEmploymentService.findList(accountPerEmployment);
        //查找个人雇佣信息中关联的字典表信息
        List<AccountPerEmploymentModel> accountPerEmploymentModels = new ArrayList<>();
        for(AccountPerEmployment perEmployment:accountPerEmployments){
            AccountPerEmploymentModel accountPerEmploymentModel = new AccountPerEmploymentModel()
                    .convertToModel(perEmployment)
                    .setEmploymentStatus(dictionaryService.findDictionary("employmentType",perEmployment.getEmploymentStatus(),lang));
            if(perEmployment.getCommercialNature() != null){
                accountPerEmploymentModel.setCommercialNature(dictionaryService.findDictionary("businessNature",perEmployment.getCommercialNature(),lang));
            }
            if(perEmployment.getPosition() != null){
                accountPerEmploymentModel.setPosition(dictionaryService.findDictionary("position",perEmployment.getPosition(),lang));
            }
            //雇佣公司地址
            AccountPerAddress accountPerAddress = new AccountPerAddress();
            accountPerAddress.setAccountId(accountId);
            accountPerAddress.setAddressType("employmentAddress");
            List<AccountPerAddressModel> accountPerAddresses=accountPerAddressService.findList(accountPerAddress,lang);
            if(accountPerAddresses.isEmpty()==false){
                accountPerEmploymentModel.setAccountPerAddress(accountPerAddresses.get(0));
            }
            accountPerEmploymentModels.add(accountPerEmploymentModel);
        }
        return accountPerEmploymentModels;
    }
    /**
     * 个人财富来源
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerWealthSource(Long accountId,String lang){
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
        return accountPerWealthSourceModels;
    }
    /**
     * 个人规管信息
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerRegulatory(Long accountId,String lang){
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountPerRegulatory accountPerRegulatory = new AccountPerRegulatory();
        accountPerRegulatory.setAccountId(accountId);
        List<AccountPerRegulatory> perRegulatories = accountPerRegulatoryService.findList(accountPerRegulatory);
        List<AccountPerRegulatoryModel> models =new ArrayList<>();
        for(AccountPerRegulatory regulatory: perRegulatories){
            AccountPerRegulatoryModel model =new AccountPerRegulatoryModel()
                    .convertToModel(regulatory)
                    .setRelationshipAccountHolders(dictionaryService.findDictionary("accountHolderRelationship",regulatory.getRelationshipAccountHolders(),lang));
            models.add(model);
        }
        return models;
    }
    /**
     * 资产
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerAsset(Long accountId,String lang){
        AccountPerAsset accountPerAsset = new AccountPerAsset();
        accountPerAsset.setAccountId(accountId);
        return accountPerAssetService.findList(accountPerAsset);
    }

    /**
     * 个人投资目标
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerInvestTarget(Long accountId,String lang){
        AccountPerInvestTarget accountPerInvestTarget =new AccountPerInvestTarget();
        accountPerInvestTarget.setAccountId(accountId);
        return accountPerInvestTargetService.findList(accountPerInvestTarget,lang);
    }

    /**
     * 交易经验
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerTradeExp(Long accountId,String lang){
        AccountPerTradeExp accountPerTradeExp = new AccountPerTradeExp();
        accountPerTradeExp.setAccountId(accountId);
        return accountPerTradeExpService.findList(accountPerTradeExp,lang);
    }

    /**
     * 个人W8
     * @param accountId
     * @param lang
     * @return
     */
    private AccountPerWbenContractModel getPerWbenContract(Long accountId, String lang){
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountPerWbenContract accountPerWbenContract=new AccountPerWbenContract();
        accountPerWbenContract.setAccountId(accountId);
        List<AccountPerWbenContract> accountPerWbenContracts = accountPerWbenContractService.findList(accountPerWbenContract);
        List<AccountPerWbenContractModel> accountPerWbenContractModels =new ArrayList<>();
        for(AccountPerWbenContract wbenContract:accountPerWbenContracts){
            AccountPerWbenContractModel accountPerWbenContractModel = new AccountPerWbenContractModel()
                    .convertToModel(wbenContract)
                    .setOtherNationality(regionService.findBysCode(wbenContract.getOtherNationality()))
                    .setUsaCountryAgreement(regionService.findBysCode(wbenContract.getUsaCountryAgreement()))
                    .setExplainAddress(dictionaryService.findDictionary("taxIdentificationNumber",wbenContract.getExplainAddress(),lang));
            if(accountPerWbenContractModel.getExplainAddress() != null && StringUtils.isBlank(accountPerWbenContractModel.getExplainAddress().getDictDest())==false){
                //替换 占位字段 country
                if(accountPerWbenContractModel.getExplainAddress().getDictDest().contains("country") && accountPerWbenContractModel.getUsaCountryAgreement()!=null){
                    if(lang.endsWith("zh_CN")){
                        accountPerWbenContractModel.getExplainAddress().setDictDest(accountPerWbenContractModel.getExplainAddress().getDictDest().replace("country",accountPerWbenContractModel.getUsaCountryAgreement().getCnName()));
                    }
                    if(lang.endsWith("en_US")){
                        accountPerWbenContractModel.getExplainAddress().setDictDest(accountPerWbenContractModel.getExplainAddress().getDictDest().replace("country",accountPerWbenContractModel.getUsaCountryAgreement().getEnName()));
                    }
                }
            }
            //字典为空则判定为用户填写
            if(accountPerWbenContractModel.getExplainAddress() == null || StringUtils.isBlank(accountPerWbenContractModel.getExplainAddress().getDictValue())){
                Dictionary dictionary =new Dictionary();
                dictionary.setDictDest(wbenContract.getExplainAddress());
                accountPerWbenContractModel.setExplainAddress(dictionary);
            }

            accountPerWbenContractModels.add(accountPerWbenContractModel);
        }
        if(accountPerWbenContractModels.isEmpty()){
            return new AccountPerWbenContractModel();
        }
        return accountPerWbenContractModels.get(0);
    }
    private  Map<String,Object> perWben(Long accountId,String lang){
        Map<String,Object> data = new HashMap<>();
        data.put("wbenContract",getPerWbenContract(accountId,lang));
        data.put("perInfo", getPerInfo(accountId));
        List<AccountPerAddressModel> addresse = getPerAddress(accountId,lang);
        AccountPerAddressModel personalAddress = new AccountPerAddressModel();
        AccountPerAddressModel mailAddress = new AccountPerAddressModel();
        for(AccountPerAddressModel address:addresse){
            if(address.getAddress() !=null){
                address.setAddress(address.getAddress().replace("-#-",","));
            }
            if(address.getAddressType().endsWith("personalAddress")){
                personalAddress=address;
            }
            if(address.getAddressType().endsWith("mail")){
                mailAddress=address;
            }
        }
        data.put("personalAddress", personalAddress);
        data.put("mailAddress", mailAddress);
        data.put("identity", getPerIdentity(accountId,lang));
        data.put("taxes", getPerTaxes(accountId,lang));
        return data;
    }
    /**
     * 个人CRS
     * @param accountId
     * @param lang
     * @return
     */
    private AccountPerCrs getPerCrs(Long accountId,String lang){
        AccountPerCrs accountPerCrs = new AccountPerCrs();
        accountPerCrs.setAccountId(accountId);
        List<AccountPerCrs> accountPerCrsList= accountPerCrsService.findList(accountPerCrs);
        if(accountPerCrsList.isEmpty()){
            return new AccountPerCrs();
        }
        AccountPerCrs crs = accountPerCrsList.get(0);
        if(crs.getOtherAddresses()!=null){
            crs.setOtherAddresses(crs.getOtherAddresses().replace("-#-",","));
        }
        crs.setNoTaxNumber( dictionaryService.findDictionary("taxNumberType",accountPerCrsList.get(0).getNoTaxNumber(),lang).getDictDest());
        crs.setOtherCountry( regionService.findBysCode(crs.getOtherCountry()).getEnName());
        return crs;
    }

    /**
     * 个人地址
     * @param accountId
     * @param lang
     * @return
     */
    private List getPerAddress(Long accountId,String lang){
        AccountPerAddress accountPerAddress = new AccountPerAddress();
        accountPerAddress.setAccountId(accountId);
        return accountPerAddressService.findList(accountPerAddress,lang);
    }
    /**
     * 个人信息
     * @param accountId
     * @return
     */
    private AccountPerInfo getPerInfo(Long accountId){
        //根据accountId查询个人信息
        AccountPerInfo accountPerInfo = new AccountPerInfo();
        accountPerInfo.setAccountId(accountId);
        List<AccountPerInfo>accountPerInfos = accountPerInfoService.findList(accountPerInfo);
        if(accountPerInfos.isEmpty()){
            return new AccountPerInfo();
        }
        return accountPerInfos.get(0);
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
     * 根据 类型编码 字典键值 字段语言 查询字典表
     * @param dictCode
     * @param dictValue
     * @param lang
     * @return
     */
    private Dictionary findDictionary(String dictCode,String dictValue,String lang){
        if (StringUtils.isBlank(dictCode) || StringUtils.isBlank(dictValue)) {
           return new Dictionary();
        }
        return  dictionaryService.findDictionary(dictCode,dictValue,lang);
    }
}
