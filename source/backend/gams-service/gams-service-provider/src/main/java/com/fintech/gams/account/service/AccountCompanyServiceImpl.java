package com.fintech.gams.account.service;


import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.*;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author: hcj
 * @Date: 2019/8/27 20:29
 * @Description
 */
@Service
public class AccountCompanyServiceImpl implements AccountCompanyService {
    @Autowired(required = false)
    protected AccountService accountService;
    @Autowired(required = false)
    protected AccountCoAddressService accountCoAddressService;
    @Autowired(required = false)
    protected AccountCoContactService accountCoContactService;
    @Autowired(required = false)
    protected AccountCoTaxesService accountCoTaxesService;
    @Autowired(required = false)
    protected AccountCoPersonalService accountCoPersonalService;
    @Autowired(required = false)
    protected AccountCoIdentityService accountCoIdentityService;
    @Autowired(required = false)
    protected AccountCoSafeQuestionService accountCoSafeQuestionService;
    @Autowired(required = false)
    protected AccountCoInstitutionalService accountCoInstitutionalService;
    @Autowired(required = false)
    protected AccountCoWealthSourceService accountCoWealthSourceService;
    @Autowired(required = false)
    protected AccountCoAccountPortfolioService accountCoAccountPortfolioService;
    @Autowired(required = false)
    protected AccountCoTaxClassificationService accountCoTaxClassificationService;
    @Autowired(required = false)
    protected AccountCoRegulatoryService accountCoRegulatoryService;
    @Autowired(required = false)
    protected AccountCoAssetService accountCoAssetService;
    @Autowired(required = false)
    protected AccountCoInvestmentTargetService accountCoInvestmentTargetService;
    @Autowired(required = false)
    protected AccountCoTradeExperienceService accountCoTradeExperienceService;
    @Autowired(required = false)
    protected AccountCoSecurityOfficerService accountCoSecurityOfficerService;
    @Autowired(required = false)
    protected AccountCoWbenContractService accountCoWbenContractService;
    @Autowired(required = false)
    protected AccountCoCrsEntityService accountCoCrsEntityService;
    @Autowired(required = false)
    protected AccountCoCrsControlService accountCoCrsControlService;
    @Autowired(required = false)
    protected AccountCoCrsControlNumService accountCoCrsControlNumService;
    @Autowired(required = false)
    protected AccountCoAgreementService accountCoAgreementService;
    @Autowired(required = false)
    protected AccountCoInvestmentAccountService accountCoInvestmentAccountService;
    @Autowired(required = false)
    protected AccountCoAmlCtfService accountCoAmlCtfService;
    @Autowired(required = false)
    protected AccountCoFileService accountCoFileService;
    @Autowired(required = false)
    private AccountCommonAgreementService accountCommonAgreementService;
    @Autowired(required = false)
    private AccountCommonAgreementTypeService accountCommonAgreementTypeService;
    @Autowired(required = false)
    private UploadFileTypeService uploadFileTypeService;
    @Autowired(required = false)
    private AccountCommonFileService accountCommonFileService;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountCoCrsEntityContrService accountCoCrsEntityContrService;

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateAccountStatus(Long accountId, int registerStatus) throws BusinessException {
        if (null == accountId) { // 参数判断
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        Account account = accountService.selectByPrimaryKey(accountId);
        account.setId(accountId);
        account.setRegisterStatus(registerStatus);
        return accountService.updateByPrimaryKeySelective(account);
    }


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveOne(CompanyInfoModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountCoInstitutional().getAccountId());
        // 公司机构信息
        saveCoInstitutional(model.getAccountCoInstitutional());
        // 第一页是把公司机构信息的id 存到 CO_PERSONAL_ID
        // 公司地址信息
        saveCoAddress(model.getAccountCoInstitutional().getId(), model.getAccountCoAddressList());
        // 公司联系信息
        saveCoContact(model.getAccountCoInstitutional().getId(), model.getAccountCoContactList());
        // 公司税务居住地
        saveCoTaxes(model.getAccountCoInstitutional().getId(), model.getAccountCoTaxesList());
        // 公司财富来源
        saveCoWealthSource(model.getAccountCoWealthSourceList());
        // 公司账户组合信息
        saveCoAccountPortfolio(model.getAccountCoAccountPortfolio());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTwo(CompanyAuthorizedModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountCoPersonal().getAccountId());
        // 公司个人信息
        saveCoPersonal(model.getAccountCoPersonal());
        // 第二页把公司个人信息的id 存到 CO_PERSONAL_ID
        // 公司地址信息
        saveCoAddress(model.getAccountCoPersonal().getId(), model.getAccountCoAddressList());
        // 公司联系信息
        saveCoContact(model.getAccountCoPersonal().getId(), model.getAccountCoContactList());
        // 公司身份信息
        saveCoIdentity(model.getAccountCoPersonal().getId(), model.getAccountCoIdentity());
        // 公司税务居住地
        saveCoTaxes(model.getAccountCoPersonal().getId(), model.getAccountCoTaxesList());
        //  公司安全问题
        saveCoSafeQuestion(model.getAccountCoSafeQuestionList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveThree(AccountCoTaxClassification accountCoTaxClassification) throws BusinessException {
        validateOpenAccountFinish(accountCoTaxClassification.getAccountId());
        // 税收分类
        saveCoTaxClassification(accountCoTaxClassification);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFour(List<AccountCoRegulatory> accountCoRegulatoryList) throws BusinessException {
        validateOpenAccountFinish(accountCoRegulatoryList.get(0).getAccountId());
        // 公司规管信息
        saveCoRegulatory(accountCoRegulatoryList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSeven(CompanyAssetModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountCoAsset().getAccountId());
        // 公司资产信息
        saveCoAsset(model.getAccountCoAsset());
        // 公司投资目标信息
        saveCoInvestmentTarget(model.getAccountCoInvestmentTarget());
        // 公司交易经验信息
        saveCoTradeExperience(model.getAccountCoTradeExperiencesList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEight(CompanyRegisteredOwnerModel model) throws BusinessException {
        for (CompanyRegisteredOwnerInfoModel companyRegisteredOwnerInfoModel : model.getCompanyRegisteredOwnerInfoModelList()) {
            validateOpenAccountFinish(companyRegisteredOwnerInfoModel.getAccountCoPersonal().getAccountId());
            // 公司个人信息
            saveCoPersonal(companyRegisteredOwnerInfoModel.getAccountCoPersonal());
            // 第八页把公司个人信息的id 存到 CO_PERSONAL_ID
            // 公司地址信息
            saveCoAddress(companyRegisteredOwnerInfoModel.getAccountCoPersonal().getId(), companyRegisteredOwnerInfoModel.getAccountCoAddressList());
            // 公司联系信息
            saveCoContact(companyRegisteredOwnerInfoModel.getAccountCoPersonal().getId(), companyRegisteredOwnerInfoModel.getAccountCoContactList());
            // 公司身份信息 个人股东才有身份信息 ：individualShareholder
            if (companyRegisteredOwnerInfoModel.getAccountCoPersonal().getPersonalType().endsWith(AccountConsts.ACCOUNT_PERSONAL_TYPE)) {
                if (null != companyRegisteredOwnerInfoModel.getAccountCoIdentity().getIssuingCountry()
                        && null != companyRegisteredOwnerInfoModel.getAccountCoIdentity().getIdCardType()) {
                    saveCoIdentity(companyRegisteredOwnerInfoModel.getAccountCoPersonal().getId(), companyRegisteredOwnerInfoModel.getAccountCoIdentity());
                }
            }
            // 公司税务居住地
            saveCoTaxes(companyRegisteredOwnerInfoModel.getAccountCoPersonal().getId(), companyRegisteredOwnerInfoModel.getAccountCoTaxesList());
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeEight(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //查出公司个人信息
        AccountCoPersonal accountCoPersonal = accountCoPersonalService.selectByPrimaryKey(id);
        //  删除 公司个人信息
        accountCoPersonalService.remove(accountCoPersonal.getId());
        //  删除 公司地址信息
        removeAddressByCoPersonalId(accountCoPersonal.getId());
        //  删除 公司联系信息
        removeContactByCoPersonalId(accountCoPersonal.getId());
        //  公司身份信息 个人股东才有身份信息 ：individualShareholder
        if (accountCoPersonal.getPersonalType().endsWith(AccountConsts.ACCOUNT_PERSONAL_TYPE)) {
            // 删除公司身份信息
            removeIdentityByCoPersonalId(accountCoPersonal.getId());
        }
        //  删除 公司税务居住地
        removeTaxesByCoPersonalId(accountCoPersonal.getId());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveNine(CompanyBeneficiaryOwnersModel model) throws BusinessException {
        if (null != model.getCompanyBeneficiaryOwnersInfoModelList() && model.getCompanyBeneficiaryOwnersInfoModelList().size() > 0) {
            for (CompanyBeneficiaryOwnersInfoModel companyBeneficiaryOwnersInfoModel : model.getCompanyBeneficiaryOwnersInfoModelList()) {
                validateOpenAccountFinish(companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().getAccountId());
                // 公司个人信息
                saveCoPersonal(companyBeneficiaryOwnersInfoModel.getAccountCoPersonal());
                // 第九页把公司个人信息的id 存到 CO_PERSONAL_ID
                // 公司地址信息
                saveCoAddress(companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().getId(), companyBeneficiaryOwnersInfoModel.getAccountCoAddressList());
                // 公司身份信息
                if (null != companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().getIssuingCountry()
                        && null != companyBeneficiaryOwnersInfoModel.getAccountCoIdentity().getIdCardType()) {
                    saveCoIdentity(companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().getId(), companyBeneficiaryOwnersInfoModel.getAccountCoIdentity());
                }
                // 公司税务居住地
                saveCoTaxes(companyBeneficiaryOwnersInfoModel.getAccountCoPersonal().getId(), companyBeneficiaryOwnersInfoModel.getAccountCoTaxesList());
            }
            updateAccountStatus(model.getCompanyBeneficiaryOwnersInfoModelList().get(0).getAccountCoPersonal().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINE);
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeNine(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        //查出公司个人信息
        AccountCoPersonal accountCoPersonal = accountCoPersonalService.selectByPrimaryKey(id);
        // 判断是已列出的还是未列出
        if (null != accountCoPersonal.getPosition() && null != accountCoPersonal.getPersonalTypeTwo()) {
            // 已列出. 引用被授权人/个人股东作为经营负责人,删除的的时候只需要把这两个字段set成null 即可
            accountCoPersonal.setPosition(null);
            accountCoPersonal.setPersonalTypeTwo(null);
            accountCoPersonalService.updateByPrimaryKey(accountCoPersonal);
        } else {
            // 未列出
            //  删除 公司个人信息
            accountCoPersonalService.remove(accountCoPersonal.getId());
            //  删除 公司地址信息
            removeAddressByCoPersonalId(accountCoPersonal.getId());
            //  公司身份信息
            removeIdentityByCoPersonalId(accountCoPersonal.getId());
            //  删除 公司税务居住地
            removeTaxesByCoPersonalId(accountCoPersonal.getId());
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTen(CompanySecurityOfficerModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountCoSecurityOfficerList().get(0).getAccountId());
        // 公司安全官
        saveSecurityOfficer(model.getAccountCoSecurityOfficerList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEleven(AccountCoWbenContract accountCoWbenContract) throws BusinessException {
        validateOpenAccountFinish(accountCoWbenContract.getAccountId());
        // W8
        saveCoWbenContract(accountCoWbenContract);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTwelve(CompanyCrsEntityContrModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountCoCrsEntity().getAccountId());
        //CRS 共同汇报标准（CRS）实体自我证明表格（CRS-E）
        saveCoCrsEntity(model.getAccountCoCrsEntity());
        // 公司crs实体（CRS-E）控权人信息
        if (model.getAccountCoCrsEntity().getEntityNumber().startsWith("Passive NFE")){
            // 被动非财务实体才能保存
            saveCoCrsEntityContr(model.getAccountCoCrsEntity().getId(), model.getAccountCoCrsEntityContrList());
        }
        if (model.getAccountCoCrsEntity().getEntityNumber().startsWith("Financial Institution")) {
            // 财务机构 当修改成财务机构的时候判断是否保存过被动非财务实体(控权人信息)，保存过删除掉
            disposeTwelve(model.getAccountCoCrsEntity().getAccountId(), model.getAccountCoCrsEntity().getId());
        }
        if (model.getAccountCoCrsEntity().getEntityNumber().startsWith("Active NFE")) {
            // 主动非财务实体 当修改成主动非财务实体的时候判断是否保存过被动非财务实体(控权人信息)，保存过删除掉
            disposeTwelve(model.getAccountCoCrsEntity().getAccountId(), model.getAccountCoCrsEntity().getId());
        }
    }

    /**
     * 处理保存过12页的控权人姓名信息，
     *
     * @param accountId
     * @param coCrsEntityId
     */
    private void disposeTwelve(Long accountId, Long coCrsEntityId) {
        AccountCoCrsEntityContr accountCoCrsEntityContr = new AccountCoCrsEntityContr();
        accountCoCrsEntityContr.setAccountId(accountId);
        accountCoCrsEntityContr.setCoCrsEntityId(coCrsEntityId);
        List<AccountCoCrsEntityContr> crsEntityContrServiceList = accountCoCrsEntityContrService.findList(accountCoCrsEntityContr);
        if (null != crsEntityContrServiceList && crsEntityContrServiceList.size() > 0) {
            for (AccountCoCrsEntityContr coCrsEntityContr : crsEntityContrServiceList) {
                accountCoCrsEntityContrService.remove(coCrsEntityContr.getId());
                // 删除对应的CRS-CP表格信息
                // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
                AccountCoCrsControl accountCoCrsControl = new AccountCoCrsControl();
                accountCoCrsControl.setAccountId(accountCoCrsEntityContr.getAccountId());
                accountCoCrsControl.setCoCrsEntityContrId(coCrsEntityContr.getId());
                List<AccountCoCrsControl> coCrsControlServiceList = accountCoCrsControlService.findList(accountCoCrsControl);
                accountCoCrsControlService.removeByCoCrsEntityContrId(coCrsEntityContr.getId());
                // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）地址信息
                if (null != coCrsControlServiceList && coCrsControlServiceList.size() > 0) {
                    removeAddressByCoPersonalId(coCrsControlServiceList.get(0).getId());
                }
                // 公司CRS控权人自我证明控权人编号
                accountCoCrsControlNumService.removeByCoCrsEntityContrId(coCrsEntityContr.getId());
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveThirteen(CompanyCrsControlModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountCoCrsControl().getAccountId());
        // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
        saveCoCrsControl(model.getAccountCoCrsControl());
        // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）地址信息
        saveCoAddress(model.getAccountCoCrsControl().getId(),model.getAccountCoAddressList());
        // 公司CRS控权人自我证明控权人编号
        saveCoCrsControlNum(model.getAccountCoCrsControl().getCoCrsEntityContrId(),model.getAccountCoCrsControlNumList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFourteen(List<AccountCoAgreement> accountCoAgreementList) throws BusinessException {
        validateOpenAccountFinish(accountCoAgreementList.get(0).getAccountId());
        // 协议纰漏
        saveCoAgreement(accountCoAgreementList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFifteen(AccountCoInvestmentAccount accountCoInvestmentAccount) throws BusinessException {
        validateOpenAccountFinish(accountCoInvestmentAccount.getAccountId());
        // 一体化投资帐户
        saveCoInvestmentAccount(accountCoInvestmentAccount);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEighteen(List<AccountCoAmlCtf> accountCoAmlCtfList) throws BusinessException {
        validateOpenAccountFinish(accountCoAmlCtfList.get(0).getAccountId());
        // 公司 aml/ctf 风险问卷
        saveCoAmlCtf(accountCoAmlCtfList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveNineteen(AccountCoFile accountCoFile) throws BusinessException {
        validateOpenAccountFinish(accountCoFile.getAccountId());
        // 保存上传文件
        saveCoFile(accountCoFile);
    }


    @Override
    public List<AccountCommonAgreement> findByAgreement(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Account account = accountService.selectByPrimaryKey(accountId);
        List<AccountCommonAgreementType> agreementTypes = new LinkedList<>();
        //获取 公司账户投资组合 信息
        AccountCoAccountPortfolio accountCoAccountPortfolio = new AccountCoAccountPortfolio();
        accountCoAccountPortfolio.setAccountId(accountId);
        List<AccountCoAccountPortfolio> accountPortfolioList = accountCoAccountPortfolioService.findList(accountCoAccountPortfolio);
        //根据账户投资组合类型去获取披露协议
        AccountCommonAgreementType accountCommonAgreementType = new AccountCommonAgreementType();
        //账户投资组合类型
        accountCommonAgreementType.setAccountPortfolioType(accountPortfolioList.get(0).getAccountPortfolioType());
        //账户类型
        accountCommonAgreementType.setAccountType(account.getAccountType());
        //根据交易经验的国家和类型去获取披露协议
        AccountCoTradeExperience accountCoTradeExperience = new AccountCoTradeExperience();
        accountCoTradeExperience.setAccountId(accountId);
        List<AccountCoTradeExperience> accountCoTradeExperienceList = accountCoTradeExperienceService.findList(accountCoTradeExperience);
        for (AccountCoTradeExperience coTradeExperience : accountCoTradeExperienceList) {
            accountCommonAgreementType.setAgreementType(coTradeExperience.getTansactionType());
            List<String> locationStrsList = Arrays.asList(coTradeExperience.getTradingLocation().split(","));
            agreementTypes = findByTradingLocation(agreementTypes, locationStrsList, accountCommonAgreementType, coTradeExperience.getTansactionType());
        }
        //去重
        agreementTypes = distinct(agreementTypes);
        //根据协议披露的名字获取链接
        List<AccountCommonAgreement> agreements = new LinkedList<>();
        AccountCommonAgreement accountCommonAgreement = new AccountCommonAgreement();
        for (AccountCommonAgreementType agreementType : agreementTypes) {
            accountCommonAgreement.setAgreementNumber(agreementType.getAgreementNumber());
            agreements.addAll(accountCommonAgreementService.findList(accountCommonAgreement));
        }

        return agreements;
    }

    @Override
    public UploadFileTypeModel getFileUploadType(Long accountId, String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountCoPersonal personal = new AccountCoPersonal();
        personal.setAccountId(accountId);
        // 个人股东
        personal.setPersonalType(AccountConsts.ACCOUNT_PERSONAL_TYPE);
        List<AccountCoPersonal> coPersonalList = accountCoPersonalService.findList(personal);
        UploadFileTypeModel model = new UploadFileTypeModel();
        List<UploadFileType> personalList = new ArrayList<>();
        List<UploadFileType> count = new ArrayList<>();
        List<UploadFileType> legalPersonal = new ArrayList<>();
        // 法人股东
        personal.setPersonalType(AccountConsts.LEGAL_PERSON_SHAREHOLDER);
        List<AccountCoPersonal> legalPersonalList = accountCoPersonalService.findList(personal);
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        if (0 == coPersonalList.size() && 0 != legalPersonalList.size()) {
            // 没有个人股东，有法人股东
            Long[] uploadFileTypeKindLegalPersonal = {3L};
            // 法人股东的地址和法人股东的身份生日证明
            List<UploadFileType> uploadFileTypeLegalPersonalList = uploadFileTypeService.findByKindArr(uploadFileTypeKindLegalPersonal, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY);
            Long[] uploadFileTypeKindArr = {1L};
            count.addAll(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY));
            // 法人股东
            returnUploadFileType(uploadFileTypeLegalPersonalList,legalPersonalList,legalPersonal);
            count.addAll(legalPersonal);
            model.setUploadFileTypeList(count);
            return model;
        }
        if (0 == legalPersonalList.size() && 0 == coPersonalList.size()) {
            // 没有个人股东，没有法人股东
            Long[] uploadFileTypeKindArr = {1L};
            model.setUploadFileTypeList(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY));
            return model;
        }
        if (0 == legalPersonalList.size() && 0 != coPersonalList.size()) {
            // 没有法人股东，有个人股东
            Long[] uploadFileTypeCoPersonal = {2L};
            // 个人股东的地址和个人股东的身份生日证明
            List<UploadFileType> uploadFileTypeIndividualShareholder = uploadFileTypeService.findByKindArr(uploadFileTypeCoPersonal, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY);
            Long[] uploadFileTypeKindArr = {1L};
            count.addAll(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY));
            returnUploadFileType(uploadFileTypeIndividualShareholder,coPersonalList,personalList);
            count.addAll(personalList);
            model.setUploadFileTypeList(count);
            return model;
        }
        // 有个人股东有法人股东
        Long[] uploadFileTypeKindArr = {1L};
        count.addAll(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY));
        // 个人股东的地址和个人股东的身份生日证明
        Long[] uploadFileTypeKindCoPersonal = {2L};
        List<UploadFileType> uploadFileTypeIndividualShareholder = uploadFileTypeService.findByKindArr(uploadFileTypeKindCoPersonal, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY);
        // 法人股东的地址和法人股东的身份生日证明
        Long[] uploadFileTypeKindLegalPersonal = {3L};
        List<UploadFileType> uploadFileTypeLegalPersonalList = uploadFileTypeService.findByKindArr(uploadFileTypeKindLegalPersonal, lang, AccountConsts.ACCOUNT_LIMITEDLIABLITY);
        // 个人股东
        returnUploadFileType(uploadFileTypeIndividualShareholder,coPersonalList,personalList);
        count.addAll(personalList);
        // 法人股东
        returnUploadFileType(uploadFileTypeLegalPersonalList,legalPersonalList,legalPersonal);
        count.addAll(legalPersonal);
        model.setUploadFileTypeList(count);
        return model;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updatePersonalByPrimaryKeySelective(Long id, String position, String personalTypeTwo) throws BusinessException {
        AccountCoPersonal accountCoPersonal = accountCoPersonalService.selectByPrimaryKey(id);
        accountCoPersonal.setPosition(position);
        accountCoPersonal.setPersonalTypeTwo(personalTypeTwo);
        return accountCoPersonalService.updateByPrimaryKeySelective(accountCoPersonal);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateCrsEntityStatus(Long coCrsEntityContrId) {
        if (null == coCrsEntityContrId){
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        AccountCoCrsEntityContr accountCoCrsEntityContr = accountCoCrsEntityContrService.selectByPrimaryKey(coCrsEntityContrId);
        AccountCoCrsEntity accountCoCrsEntity = accountCoCrsEntityService.selectByPrimaryKey(accountCoCrsEntityContr.getCoCrsEntityId());
        accountCoCrsEntity.setCrsCpStatusId(coCrsEntityContrId);
        return accountCoCrsEntityService.updateByPrimaryKeySelective(accountCoCrsEntity);
    }


    /**
     * 根据 tradingLocation 获取 披露协议列表
     *
     * @param agreements
     * @param locationStrs
     * @param accountCommonAgreementType
     * @return
     */
    public List findByTradingLocation(List<AccountCommonAgreementType> agreements, List<String> locationStrs, AccountCommonAgreementType accountCommonAgreementType, String tansactionType) {
        for (String str : locationStrs) {
            accountCommonAgreementType.setAgreementLocation(str);
            agreements.addAll(accountCommonAgreementTypeService.findList(accountCommonAgreementType));
        }
        return agreements;
    }

    /**
     * AccountCommonAgreementType List根据 agreementNumber 去重
     *
     * @param agreements
     * @return
     */
    public List distinct(List<AccountCommonAgreementType> agreements) {
        List<AccountCommonAgreementType> list = new ArrayList<>(agreements);
        //添加一个比较器,根据 agreementNumber 排序
        TreeSet<AccountCommonAgreementType> tree = new TreeSet<>(
                Comparator.comparing(AccountCommonAgreementType::getAgreementNumber)
        );
        tree.addAll(list);
        list.clear();
        list.addAll(tree);
        return list;
    }


    /**
     * 插入/更新 公司地址信息
     *
     * @param coPersonalId
     * @param accountCoAddressList
     */
    void saveCoAddress(Long coPersonalId, List<AccountCoAddress> accountCoAddressList) throws BusinessException {
        AccountCoAddress coAddress = new AccountCoAddress();
        coAddress.setAccountId(accountCoAddressList.get(0).getAccountId());
        coAddress.setCoPersonalId(coPersonalId);
        List<AccountCoAddress> oldCoAddresses = accountCoAddressService.findList(coAddress);
        for (AccountCoAddress newAddressesInfo : accountCoAddressList) {
            int i = 0;
            for (int j = 0; j < oldCoAddresses.size(); j++) {
                if (newAddressesInfo.getId() != null && newAddressesInfo.getId().equals(oldCoAddresses.get(j).getId())) {
                    i = 1;
                    oldCoAddresses.remove(j);
                    newAddressesInfo.setUpdatedDate(System.currentTimeMillis());
                    newAddressesInfo.setCoPersonalId(coPersonalId);
                    //更新数据
                    accountCoAddressService.updateByPrimaryKey(newAddressesInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAddressesInfo.setId(SerialnoUtils.buildPrimaryKey());
                newAddressesInfo.setCoPersonalId(coPersonalId);
                newAddressesInfo.setCreatedDate(System.currentTimeMillis());
                newAddressesInfo.setUpdatedDate(System.currentTimeMillis());
                accountCoAddressService.insert(newAddressesInfo);
            }
        }
        for (AccountCoAddress accountCoAddress : oldCoAddresses) {
            accountCoAddressService.remove(accountCoAddress.getId());
        }
    }

    /**
     * 插入/更新 公司联系信息
     *
     * @param coPersonalId
     * @param accountCoContactList
     */
    void saveCoContact(Long coPersonalId, List<AccountCoContact> accountCoContactList) throws BusinessException {
        AccountCoContact coContact = new AccountCoContact();
        coContact.setAccountId(accountCoContactList.get(0).getAccountId());
        coContact.setCoPersonalId(coPersonalId);
        List<AccountCoContact> oldAccountCoContact = accountCoContactService.findList(coContact);
        for (AccountCoContact newAccountCoContactInfo : accountCoContactList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoContact.size(); j++) {
                if (newAccountCoContactInfo.getId() != null && newAccountCoContactInfo.getId().equals(oldAccountCoContact.get(j).getId())) {
                    i = 1;
                    oldAccountCoContact.remove(j);
                    newAccountCoContactInfo.setUpdatedDate(System.currentTimeMillis());
                    newAccountCoContactInfo.setCoPersonalId(coPersonalId);
                    //更新数据
                    accountCoContactService.updateByPrimaryKey(newAccountCoContactInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoContactInfo.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoContactInfo.setCoPersonalId(coPersonalId);
                newAccountCoContactInfo.setCreatedDate(System.currentTimeMillis());
                newAccountCoContactInfo.setUpdatedDate(System.currentTimeMillis());
                accountCoContactService.insert(newAccountCoContactInfo);
            }
        }
        for (AccountCoContact accountCoContact : oldAccountCoContact) {
            accountCoContactService.remove(accountCoContact.getId());
        }
    }

    /**
     * 插入/更新 公司税务居住地
     *
     * @param coPersonalId
     * @param accountCoTaxesList
     */
    void saveCoTaxes(Long coPersonalId, List<AccountCoTaxes> accountCoTaxesList) throws BusinessException {
        AccountCoTaxes coTaxes = new AccountCoTaxes();
        coTaxes.setAccountId(accountCoTaxesList.get(0).getAccountId());
        coTaxes.setCoPersonalId(coPersonalId);
        List<AccountCoTaxes> oldAccountCoTaxes = accountCoTaxesService.findList(coTaxes);
        for (AccountCoTaxes newAccountCoTaxesInfo : accountCoTaxesList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoTaxes.size(); j++) {
                if (newAccountCoTaxesInfo.getId() != null && newAccountCoTaxesInfo.getId().equals(oldAccountCoTaxes.get(j).getId())) {
                    i = 1;
                    oldAccountCoTaxes.remove(j);
                    newAccountCoTaxesInfo.setUpdatedDate(System.currentTimeMillis());
                    newAccountCoTaxesInfo.setCoPersonalId(coPersonalId);
                    //更新数据
                    accountCoTaxesService.updateByPrimaryKey(newAccountCoTaxesInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoTaxesInfo.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoTaxesInfo.setCoPersonalId(coPersonalId);
                newAccountCoTaxesInfo.setCreatedDate(System.currentTimeMillis());
                newAccountCoTaxesInfo.setUpdatedDate(System.currentTimeMillis());
                accountCoTaxesService.insert(newAccountCoTaxesInfo);
            }
        }
        for (AccountCoTaxes accountCoContact : oldAccountCoTaxes) {
            accountCoTaxesService.remove(accountCoContact.getId());
        }
    }


    /**
     * 插入/更新  公司个人信息
     *
     * @param accountCoPersonal
     */
    int saveCoPersonal(AccountCoPersonal accountCoPersonal) throws BusinessException {
        accountCoPersonal.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoPersonal.getId()) {
            accountCoPersonal.setId(SerialnoUtils.buildPrimaryKey());
            accountCoPersonal.setCreatedDate(System.currentTimeMillis());
            if (accountCoPersonal.getPersonalType().equals(AccountConsts.ACCOUNT_AUTHORIZEDPERSON)) {
                validatePersonalAuthorizedPerson(accountCoPersonal.getAccountId(), accountCoPersonal.getPersonalType());
            }
            if (accountCoPersonal.getPersonalType().equals(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL)) {
                validatePersonalOperationPrincipal(accountCoPersonal.getAccountId(), accountCoPersonal.getPersonalType());
            }
            if (null != accountCoPersonal.getPersonalTypeTwo() && accountCoPersonal.getPersonalTypeTwo().equals(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL)) {
                validatePersonalOperationPrincipalTwo(accountCoPersonal.getAccountId(), AccountConsts.ACCOUNT_OPERATIONPRINCIPAL);
            }
            return accountCoPersonalService.insert(accountCoPersonal);
        } else {
            return accountCoPersonalService.updateByPrimaryKey(accountCoPersonal);
        }
    }

    /**
     * 插入/更新 公司身份信息
     *
     * @param coPersonalId
     * @param accountCoIdentity
     * @return
     */
    int saveCoIdentity(Long coPersonalId, AccountCoIdentity accountCoIdentity) {
        accountCoIdentity.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoIdentity.getId()) {
            accountCoIdentity.setId(SerialnoUtils.buildPrimaryKey());
            accountCoIdentity.setCoPersonalId(coPersonalId);
            accountCoIdentity.setCreatedDate(System.currentTimeMillis());
            validateIdentity(accountCoIdentity.getAccountId(),coPersonalId);
            return accountCoIdentityService.insert(accountCoIdentity);
        } else {
            accountCoIdentity.setCoPersonalId(coPersonalId);
            return accountCoIdentityService.updateByPrimaryKey(accountCoIdentity);
        }
    }

    /**
     * 插入/更新  公司安全问题
     *
     * @param accountCoSafeQuestionList
     */
    void saveCoSafeQuestion(List<AccountCoSafeQuestion> accountCoSafeQuestionList) {
        AccountCoSafeQuestion coSafeQuestion = new AccountCoSafeQuestion();
        coSafeQuestion.setAccountId(accountCoSafeQuestionList.get(0).getAccountId());
        List<AccountCoSafeQuestion> oldAccountCoSafeQuestion = accountCoSafeQuestionService.findList(coSafeQuestion);
        for (AccountCoSafeQuestion newAccountCoSafeQuestion : accountCoSafeQuestionList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoSafeQuestion.size(); j++) {
                if (newAccountCoSafeQuestion.getId() != null && newAccountCoSafeQuestion.getId().equals(oldAccountCoSafeQuestion.get(j).getId())) {
                    i = 1;
                    oldAccountCoSafeQuestion.remove(j);
                    newAccountCoSafeQuestion.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    validateAccountCoSafeQuestion(newAccountCoSafeQuestion);
                    accountCoSafeQuestionService.updateByPrimaryKey(newAccountCoSafeQuestion);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoSafeQuestion.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoSafeQuestion.setCreatedDate(System.currentTimeMillis());
                newAccountCoSafeQuestion.setUpdatedDate(System.currentTimeMillis());
                validateAccountCoSafeQuestion(newAccountCoSafeQuestion);
                accountCoSafeQuestionService.insert(newAccountCoSafeQuestion);
            }
        }
        for (AccountCoSafeQuestion accountCoSafeQuestion : oldAccountCoSafeQuestion) {
            accountCoSafeQuestionService.remove(accountCoSafeQuestion.getId());
        }
    }

    /**
     * 插入/更新 公司机构信息
     *
     * @param accountCoInstitutional
     */
    int saveCoInstitutional(AccountCoInstitutional accountCoInstitutional) {
        accountCoInstitutional.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoInstitutional.getId()) {
            accountCoInstitutional.setId(SerialnoUtils.buildPrimaryKey());
            accountCoInstitutional.setCreatedDate(System.currentTimeMillis());
            validateInstitutional(accountCoInstitutional.getAccountId());
            return accountCoInstitutionalService.insert(accountCoInstitutional);
        } else {
            return accountCoInstitutionalService.updateByPrimaryKey(accountCoInstitutional);
        }
    }

    /**
     * 插入/更新  公司财富来源
     *
     * @param accountCoWealthSourceList
     */
    void saveCoWealthSource(List<AccountCoWealthSource> accountCoWealthSourceList) {
        AccountCoWealthSource coWealthSource = new AccountCoWealthSource();
        coWealthSource.setAccountId(accountCoWealthSourceList.get(0).getAccountId());
        List<AccountCoWealthSource> oldAccountCoWealthSource = accountCoWealthSourceService.findList(coWealthSource);
        for (AccountCoWealthSource newAccountCoWealthSourceInfo : accountCoWealthSourceList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoWealthSource.size(); j++) {
                if (newAccountCoWealthSourceInfo.getId() != null && newAccountCoWealthSourceInfo.getId().equals(oldAccountCoWealthSource.get(j).getId())) {
                    i = 1;
                    oldAccountCoWealthSource.remove(j);
                    newAccountCoWealthSourceInfo.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountCoWealthSourceService.updateByPrimaryKey(newAccountCoWealthSourceInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoWealthSourceInfo.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoWealthSourceInfo.setCreatedDate(System.currentTimeMillis());
                newAccountCoWealthSourceInfo.setUpdatedDate(System.currentTimeMillis());
                accountCoWealthSourceService.insert(newAccountCoWealthSourceInfo);
            }
        }
        for (AccountCoWealthSource accountCoWealthSource : oldAccountCoWealthSource) {
            accountCoWealthSourceService.remove(accountCoWealthSource.getId());
        }
    }

    /**
     * 插入/更新  公司账户组合信息
     *
     * @param accountCoAccountPortfolio
     */
    int saveCoAccountPortfolio(AccountCoAccountPortfolio accountCoAccountPortfolio) {
        accountCoAccountPortfolio.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoAccountPortfolio.getId()) {
            accountCoAccountPortfolio.setId(SerialnoUtils.buildPrimaryKey());
            accountCoAccountPortfolio.setCreatedDate(System.currentTimeMillis());
            validatePortfolio(accountCoAccountPortfolio.getAccountId());
            return accountCoAccountPortfolioService.insert(accountCoAccountPortfolio);
        } else {
            return accountCoAccountPortfolioService.updateByPrimaryKey(accountCoAccountPortfolio);
        }
    }

    /**
     * 插入/更新  公司税收分类
     *
     * @param accountCoTaxClassification
     * @return
     */
    int saveCoTaxClassification(AccountCoTaxClassification accountCoTaxClassification) throws BusinessException {
        accountCoTaxClassification.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoTaxClassification.getId()) {
            accountCoTaxClassification.setId(SerialnoUtils.buildPrimaryKey());
            accountCoTaxClassification.setCreatedDate(System.currentTimeMillis());
            validateTaxClassification(accountCoTaxClassification.getAccountId());
            return accountCoTaxClassificationService.insert(accountCoTaxClassification);
        } else {
            return accountCoTaxClassificationService.updateByPrimaryKey(accountCoTaxClassification);
        }
    }

    /**
     * 插入/更新  公司规管信息
     *
     * @param accountCoRegulatoryList
     */
    void saveCoRegulatory(List<AccountCoRegulatory> accountCoRegulatoryList) {
        AccountCoRegulatory coRegulatory = new AccountCoRegulatory();
        coRegulatory.setAccountId(accountCoRegulatoryList.get(0).getAccountId());
        List<AccountCoRegulatory> oldAccountCoRegulatory = accountCoRegulatoryService.findList(coRegulatory);
        for (AccountCoRegulatory newAccountCoRegulatoryInfo : accountCoRegulatoryList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoRegulatory.size(); j++) {
                if (newAccountCoRegulatoryInfo.getId() != null && newAccountCoRegulatoryInfo.getId().equals(oldAccountCoRegulatory.get(j).getId())) {
                    i = 1;
                    oldAccountCoRegulatory.remove(j);
                    //更新数据
                    accountCoRegulatoryService.updateByPrimaryKey(newAccountCoRegulatoryInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoRegulatoryInfo.setId(SerialnoUtils.buildPrimaryKey());
                accountCoRegulatoryService.insert(newAccountCoRegulatoryInfo);
            }
        }
        for (AccountCoRegulatory accountCoRegulatory : oldAccountCoRegulatory) {
            accountCoRegulatoryService.remove(accountCoRegulatory.getId());
        }
    }

    /**
     * 插入/更新  公司资产信息
     *
     * @param accountCoAsset
     * @return
     */
    int saveCoAsset(AccountCoAsset accountCoAsset) {
        accountCoAsset.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoAsset.getId()) {
            accountCoAsset.setId(SerialnoUtils.buildPrimaryKey());
            accountCoAsset.setCreatedDate(System.currentTimeMillis());
            validateAsset(accountCoAsset.getAccountId());
            return accountCoAssetService.insert(accountCoAsset);
        } else {
            return accountCoAssetService.updateByPrimaryKey(accountCoAsset);
        }
    }

    /**
     * 插入/更新  公司投资目标信息
     *
     * @param accountCoInvestmentTarget
     * @return
     */
    int saveCoInvestmentTarget(AccountCoInvestmentTarget accountCoInvestmentTarget) {
        accountCoInvestmentTarget.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoInvestmentTarget.getId()) {
            accountCoInvestmentTarget.setId(SerialnoUtils.buildPrimaryKey());
            accountCoInvestmentTarget.setCreatedDate(System.currentTimeMillis());
            validateInvestTarget(accountCoInvestmentTarget);
            validateInvestmentTarget(accountCoInvestmentTarget.getAccountId());
            return accountCoInvestmentTargetService.insert(accountCoInvestmentTarget);
        } else {
            validateInvestTarget(accountCoInvestmentTarget);
            return accountCoInvestmentTargetService.updateByPrimaryKey(accountCoInvestmentTarget);
        }
    }

    /**
     * 插入/更新  公司交易经验信息
     *
     * @param accountCoTradeExperiencesList
     */
    void saveCoTradeExperience(List<AccountCoTradeExperience> accountCoTradeExperiencesList) {
        AccountCoTradeExperience coTradeExperience = new AccountCoTradeExperience();
        coTradeExperience.setAccountId(accountCoTradeExperiencesList.get(0).getAccountId());
        List<AccountCoTradeExperience> oldAccountCoTradeExperiences = accountCoTradeExperienceService.findList(coTradeExperience);
        for (AccountCoTradeExperience newAccountCoTradeExperience : accountCoTradeExperiencesList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoTradeExperiences.size(); j++) {
                if (newAccountCoTradeExperience.getId() != null && newAccountCoTradeExperience.getId().equals(oldAccountCoTradeExperiences.get(j).getId())) {
                    i = 1;
                    oldAccountCoTradeExperiences.remove(j);
                    newAccountCoTradeExperience.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    validateKnowledge(newAccountCoTradeExperience);
                    accountCoTradeExperienceService.updateByPrimaryKey(newAccountCoTradeExperience);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoTradeExperience.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoTradeExperience.setCreatedDate(System.currentTimeMillis());
                newAccountCoTradeExperience.setUpdatedDate(System.currentTimeMillis());
                validateKnowledge(newAccountCoTradeExperience);
                accountCoTradeExperienceService.insert(newAccountCoTradeExperience);
            }
        }
        for (AccountCoTradeExperience accountCoTradeExperience : oldAccountCoTradeExperiences) {
            accountCoTradeExperienceService.remove(accountCoTradeExperience.getId());
        }
    }

    /**
     * 插入/更新 安全官
     *
     * @param accountCoSecurityOfficerList
     */
    void saveSecurityOfficer(List<AccountCoSecurityOfficer> accountCoSecurityOfficerList) {
        AccountCoSecurityOfficer coSecurityOfficer = new AccountCoSecurityOfficer();
        coSecurityOfficer.setAccountId(accountCoSecurityOfficerList.get(0).getAccountId());
        List<AccountCoSecurityOfficer> oldAccountCoSecurityOfficers = accountCoSecurityOfficerService.findList(coSecurityOfficer);
        for (AccountCoSecurityOfficer newAccountCoSecurityOfficer : accountCoSecurityOfficerList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoSecurityOfficers.size(); j++) {
                if (newAccountCoSecurityOfficer.getId() != null && newAccountCoSecurityOfficer.getId().equals(oldAccountCoSecurityOfficers.get(j).getId())) {
                    i = 1;
                    oldAccountCoSecurityOfficers.remove(j);
                    newAccountCoSecurityOfficer.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountCoSecurityOfficerService.updateByPrimaryKey(newAccountCoSecurityOfficer);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoSecurityOfficer.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoSecurityOfficer.setCreatedDate(System.currentTimeMillis());
                newAccountCoSecurityOfficer.setUpdatedDate(System.currentTimeMillis());
                accountCoSecurityOfficerService.insert(newAccountCoSecurityOfficer);
            }
        }
    }

    /**
     * 插入/更新  w8ben表格
     *
     * @param accountCoWbenContract
     * @return
     */
    int saveCoWbenContract(AccountCoWbenContract accountCoWbenContract) {
        accountCoWbenContract.setUpdatedDate(System.currentTimeMillis());
        accountCoWbenContract.setSignatureTime(System.currentTimeMillis());
        if (null == accountCoWbenContract.getId()) {
            accountCoWbenContract.setId(SerialnoUtils.buildPrimaryKey());
            accountCoWbenContract.setCreatedDate(System.currentTimeMillis());
            validateWbenContract(accountCoWbenContract.getAccountId(),accountCoWbenContract.getType());
            return accountCoWbenContractService.insert(accountCoWbenContract);
        } else {
            return accountCoWbenContractService.updateByPrimaryKey(accountCoWbenContract);
        }
    }

    /**
     * 插入/更新  共同汇报标准（CRS）实体自我证明表格（CRS-E）
     *
     * @param accountCoCrsEntity
     * @return
     */
    int saveCoCrsEntity(AccountCoCrsEntity accountCoCrsEntity) {
        accountCoCrsEntity.setUpdatedDate(System.currentTimeMillis());
        accountCoCrsEntity.setSignatureDate(System.currentTimeMillis());
        if (null == accountCoCrsEntity.getId()) {
            accountCoCrsEntity.setId(SerialnoUtils.buildPrimaryKey());
            accountCoCrsEntity.setCreatedDate(System.currentTimeMillis());
            validateCrsEntity(accountCoCrsEntity.getAccountId());
            return accountCoCrsEntityService.insert(accountCoCrsEntity);
        } else {
            return accountCoCrsEntityService.updateByPrimaryKey(accountCoCrsEntity);
        }
    }

    /**
     * 插入/更新  共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
     *
     * @param accountCoCrsControl
     * @return
     */
    int saveCoCrsControl(AccountCoCrsControl accountCoCrsControl) {
        accountCoCrsControl.setUpdatedDate(System.currentTimeMillis());
        accountCoCrsControl.setSignatureDate(System.currentTimeMillis());
        if (null == accountCoCrsControl.getId()) {
            accountCoCrsControl.setId(SerialnoUtils.buildPrimaryKey());
            accountCoCrsControl.setCreatedDate(System.currentTimeMillis());
            validateCrsControl(accountCoCrsControl.getAccountId(),accountCoCrsControl.getCoCrsEntityContrId());
            return accountCoCrsControlService.insert(accountCoCrsControl);
        } else {
            return accountCoCrsControlService.updateByPrimaryKeySelective(accountCoCrsControl);
        }
    }

    /**
     * 插入/更新  公司CRS控权人自我证明控权人编号
     *
     * @param accountCoCrsControlNumList
     * @param coCrsEntityContrId  crs实体（CRS-E）控权人信息ID
     */
    void saveCoCrsControlNum(Long coCrsEntityContrId, List<AccountCoCrsControlNum> accountCoCrsControlNumList) {
        AccountCoCrsControlNum accountCoCrsControlNum = new AccountCoCrsControlNum();
        accountCoCrsControlNum.setAccountId(accountCoCrsControlNumList.get(0).getAccountId());
        accountCoCrsControlNum.setCoCrsEntityContrId(coCrsEntityContrId);
        List<AccountCoCrsControlNum> oldAccountCoCrsControlNum = accountCoCrsControlNumService.findList(accountCoCrsControlNum);
        for (AccountCoCrsControlNum newAccountCoCrsControlNum : accountCoCrsControlNumList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoCrsControlNum.size(); j++) {
                if (newAccountCoCrsControlNum.getId() != null && newAccountCoCrsControlNum.getId().equals(oldAccountCoCrsControlNum.get(j).getId())) {
                    i = 1;
                    oldAccountCoCrsControlNum.remove(j);
                    //更新数据
                    newAccountCoCrsControlNum.setCoCrsEntityContrId(coCrsEntityContrId);
                    accountCoCrsControlNumService.updateByPrimaryKeySelective(newAccountCoCrsControlNum);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoCrsControlNum.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoCrsControlNum.setCoCrsEntityContrId(coCrsEntityContrId);
                accountCoCrsControlNumService.insert(newAccountCoCrsControlNum);
            }
        }
        for (AccountCoCrsControlNum coCrsControlNum : oldAccountCoCrsControlNum) {
            accountCoCrsControlNumService.remove(coCrsControlNum.getId());
        }
    }

    /**
     *  公司crs实体（CRS-E）控权人信息
     * @param accountCoCrsEntityContrList
     */
    void saveCoCrsEntityContr(Long coCrsEntityId, List<AccountCoCrsEntityContr> accountCoCrsEntityContrList) {
        AccountCoCrsEntityContr coCrsEntityContr = new AccountCoCrsEntityContr();
        coCrsEntityContr.setAccountId(accountCoCrsEntityContrList.get(0).getAccountId());
        coCrsEntityContr.setCoCrsEntityId(coCrsEntityId);
        List<AccountCoCrsEntityContr> oldAccountCoCrsEntityContr = accountCoCrsEntityContrService.findList(coCrsEntityContr);
        for (AccountCoCrsEntityContr newAccountCoCrsEntityContr : accountCoCrsEntityContrList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoCrsEntityContr.size(); j++) {
                if (newAccountCoCrsEntityContr.getId() != null && newAccountCoCrsEntityContr.getId().equals(oldAccountCoCrsEntityContr.get(j).getId())) {
                    i = 1;
                    oldAccountCoCrsEntityContr.remove(j);
                    newAccountCoCrsEntityContr.setUpdatedDate(System.currentTimeMillis());
                    newAccountCoCrsEntityContr.setCoCrsEntityId(coCrsEntityId);
                    //更新数据
                    accountCoCrsEntityContrService.updateByPrimaryKey(newAccountCoCrsEntityContr);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoCrsEntityContr.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoCrsEntityContr.setCreatedDate(System.currentTimeMillis());
                newAccountCoCrsEntityContr.setCoCrsEntityId(coCrsEntityId);
                newAccountCoCrsEntityContr.setUpdatedDate(System.currentTimeMillis());
                accountCoCrsEntityContrService.insert(newAccountCoCrsEntityContr);
            }
        }
        for (AccountCoCrsEntityContr accountCoCrsEntityContr : oldAccountCoCrsEntityContr) {
            accountCoCrsEntityContrService.remove(accountCoCrsEntityContr.getId());
            // 删除对应的CRS-CP表格信息
            // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
            AccountCoCrsControl accountCoCrsControl = new AccountCoCrsControl();
            accountCoCrsControl.setAccountId(accountCoCrsEntityContr.getAccountId());
            accountCoCrsControl.setCoCrsEntityContrId(accountCoCrsEntityContr.getId());
            List<AccountCoCrsControl> coCrsControlList = accountCoCrsControlService.findList(accountCoCrsControl);
            accountCoCrsControlService.removeByCoCrsEntityContrId(accountCoCrsEntityContr.getId());
            // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）地址信息
            if (null != coCrsControlList && coCrsControlList.size() > 0) {
                removeAddressByCoPersonalId(coCrsControlList.get(0).getId());
            }
            // 公司CRS控权人自我证明控权人编号
            accountCoCrsControlNumService.removeByCoCrsEntityContrId(accountCoCrsEntityContr.getId());
        }
    }

    /**
     * 插入/更新  公司协议纰漏信息
     *
     * @param accountCoAgreementList
     */
    void saveCoAgreement(List<AccountCoAgreement> accountCoAgreementList) {
        AccountCoAgreement coAgreement = new AccountCoAgreement();
        coAgreement.setAccountId(accountCoAgreementList.get(0).getAccountId());
        List<AccountCoAgreement> oldCoAgreementService = accountCoAgreementService.findList(coAgreement);
        for (AccountCoAgreement newAccountCoAgreement : accountCoAgreementList) {
            int i = 0;
            for (int j = 0; j < oldCoAgreementService.size(); j++) {
                if (newAccountCoAgreement.getId() != null && newAccountCoAgreement.getId().equals(oldCoAgreementService.get(j).getId())) {
                    i = 1;
                    oldCoAgreementService.remove(j);
                    newAccountCoAgreement.setUpdatedDate(System.currentTimeMillis());
                    newAccountCoAgreement.setSignatureTime(System.currentTimeMillis());
                    //更新数据
                    accountCoAgreementService.updateByPrimaryKey(newAccountCoAgreement);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoAgreement.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoAgreement.setCreatedDate(System.currentTimeMillis());
                newAccountCoAgreement.setSignatureTime(System.currentTimeMillis());
                newAccountCoAgreement.setUpdatedDate(System.currentTimeMillis());
                accountCoAgreementService.insert(newAccountCoAgreement);
            }
        }
        for (AccountCoAgreement accountCoAgreement : oldCoAgreementService) {
            accountCoAgreementService.remove(accountCoAgreement.getId());
        }
    }


    /**
     * 插入/更新  公司一体化投资管理账户
     *
     * @param accountCoInvestmentAccount
     * @return
     */
    int saveCoInvestmentAccount(AccountCoInvestmentAccount accountCoInvestmentAccount) {
        accountCoInvestmentAccount.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoInvestmentAccount.getId()) {
            accountCoInvestmentAccount.setId(SerialnoUtils.buildPrimaryKey());
            accountCoInvestmentAccount.setCreatedDate(System.currentTimeMillis());
            validateInvestAccount(accountCoInvestmentAccount.getAccountId());
            return accountCoInvestmentAccountService.insert(accountCoInvestmentAccount);
        } else {
            return accountCoInvestmentAccountService.updateByPrimaryKey(accountCoInvestmentAccount);
        }
    }

    /**
     * 插入/更新  公司AML/CTF 风险问卷
     *
     * @param accountCoAmlCtfList
     */
    void saveCoAmlCtf(List<AccountCoAmlCtf> accountCoAmlCtfList) {
        AccountCoAmlCtf coAmlCtf = new AccountCoAmlCtf();
        coAmlCtf.setAccountId(accountCoAmlCtfList.get(0).getAccountId());
        List<AccountCoAmlCtf> oldAccountCoAmlCtf = accountCoAmlCtfService.findList(coAmlCtf);
        for (AccountCoAmlCtf newAccountCoAmlCtf : accountCoAmlCtfList) {
            int i = 0;
            for (int j = 0; j < oldAccountCoAmlCtf.size(); j++) {
                if (newAccountCoAmlCtf.getId() != null && newAccountCoAmlCtf.getId().equals(oldAccountCoAmlCtf.get(j).getId())) {
                    i = 1;
                    oldAccountCoAmlCtf.remove(j);
                    newAccountCoAmlCtf.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountCoAmlCtfService.updateByPrimaryKey(newAccountCoAmlCtf);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountCoAmlCtf.setId(SerialnoUtils.buildPrimaryKey());
                newAccountCoAmlCtf.setCreatedDate(System.currentTimeMillis());
                newAccountCoAmlCtf.setUpdatedDate(System.currentTimeMillis());
                accountCoAmlCtfService.insert(newAccountCoAmlCtf);
            }
        }
        for (AccountCoAmlCtf accountCoAmlCtf : oldAccountCoAmlCtf) {
            accountCoAmlCtfService.remove(accountCoAmlCtf.getId());
        }
    }

    /**
     * 插入/更新 保存上传文件
     *
     * @param accountCoFile
     * @return
     */
    int saveCoFile(AccountCoFile accountCoFile) {
        accountCoFile.setUpdatedDate(System.currentTimeMillis());
        if (null == accountCoFile.getId()) {
            accountCoFile.setId(SerialnoUtils.buildPrimaryKey());
            accountCoFile.setCreatedDate(System.currentTimeMillis());
            return accountCoFileService.insert(accountCoFile);
        } else {
            return accountCoFileService.updateByPrimaryKey(accountCoFile);
        }
    }


    /**
     * 删除公司地址信息  通过 coPersonalId
     *
     * @param coPersonalId
     * @return
     */
    int removeAddressByCoPersonalId(Long coPersonalId) {
        if (null == coPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountCoAddressService.removeByCoPersonalId(coPersonalId);
    }

    /**
     * 删除公司联系信息  通过 coPersonalId
     *
     * @param coPersonalId
     * @return
     */
    int removeContactByCoPersonalId(Long coPersonalId) {
        if (null == coPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountCoContactService.removeByCoPersonalId(coPersonalId);
    }

    /**
     * 删除公司身份信息  通过 coPersonalId
     *
     * @param coPersonalId
     * @return
     */
    int removeIdentityByCoPersonalId(Long coPersonalId) {
        if (null == coPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountCoIdentityService.removeByCoPersonalId(coPersonalId);
    }


    /**
     * 删除公司税务居住地  通过 coPersonalId
     *
     * @param coPersonalId
     * @return
     */
    int removeTaxesByCoPersonalId(Long coPersonalId) {
        if (null == coPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountCoTaxesService.removeByCoPersonalId(coPersonalId);
    }

    /**
     * 验证公司账户组合信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePortfolio(Long accountId) throws BusinessException {
        AccountCoAccountPortfolio portfolio = new AccountCoAccountPortfolio();
        portfolio.setAccountId(accountId);
        if (accountCoAccountPortfolioService.findList(portfolio).size() > 0) {
            throw new BusinessException("Only one item of portfolio information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证公司个人信息只能保存一条 （被授权人）
     *
     * @param accountId
     * @param personalType
     * @throws BusinessException
     */
    private void validatePersonalAuthorizedPerson(Long accountId, String personalType) throws BusinessException {
        AccountCoPersonal perInfo = new AccountCoPersonal();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalType);
        if (accountCoPersonalService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one authorized person information can be saved, do not repeat the operation");
        }
    }

    /**
     * 验证公司个人信息只能保存一条 （经营负责人）
     *
     * @param accountId
     * @param personalType
     * @throws BusinessException
     */
    private void validatePersonalOperationPrincipal(Long accountId, String personalType) throws BusinessException {
        AccountCoPersonal perInfo = new AccountCoPersonal();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalType);
        if (accountCoPersonalService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one operating principal information can be saved, do not repeat the operation");
        }
    }

    /**
     * 验证公司个人信息只能保存一条 （经营负责人（引用被授权人/个人股东））
     *
     * @param accountId
     * @param personalTypeTwo 第二种身份
     * @throws BusinessException
     */
    private void validatePersonalOperationPrincipalTwo(Long accountId, String personalTypeTwo) throws BusinessException {
        AccountCoPersonal perInfo = new AccountCoPersonal();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalTypeTwo);
        if (accountCoPersonalService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one operating principal information can be saved, do not repeat the operation");
        }
    }

    /**
     * 验证公司身份信息只能保存一条
     *
     * @param accountId
     * @param coPersonalId
     * @throws BusinessException
     */
    private void validateIdentity(Long accountId, Long coPersonalId) throws BusinessException {
        AccountCoIdentity perIdentity = new AccountCoIdentity();
        perIdentity.setAccountId(accountId);
        perIdentity.setCoPersonalId(coPersonalId);
        List<AccountCoIdentity> identityList = accountCoIdentityService.findList(perIdentity);
        if (identityList.size() > 0) {
            throw new BusinessException("identity information can only be saved one, do not repeat operation");
        }
    }

    /**
     * 验证公司机构信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateInstitutional(Long accountId) throws BusinessException {
        AccountCoInstitutional institutional = new AccountCoInstitutional();
        institutional.setAccountId(accountId);
        if (accountCoInstitutionalService.findList(institutional).size() > 0) {
            throw new BusinessException("Only one mechanism information can be saved. Do not repeat this operation");
        }
    }


    /**
     * 验证 开户完成 为待审核 状态 不允许再更新数据
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateOpenAccountFinish(Long accountId) throws BusinessException {
        Account account = accountService.selectByPrimaryKey(accountId);
        if (null != account.getRegisterStatus()) {
            if (account.getRegisterStatus() == 0) {
                throw new BusinessException("Open an account to be reviewed, not allowed to update the data");
            }
        }
    }

    /**
     * 验证安全问题避免自定义（需要获取字典表）
     *
     * @param accountCoSafeQuestion
     * @throws BusinessException
     */
    private void validateAccountCoSafeQuestion(AccountCoSafeQuestion accountCoSafeQuestion) throws BusinessException {
        // 安全问题类型
        Dictionary byValue = dictionaryService.findByValue(accountCoSafeQuestion.getSafeQuestion());
        if (null == byValue) {
            throw new BusinessException("Illegal security issues");
        }
    }

    /**
     * 验证公司税收分类只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateTaxClassification(Long accountId) throws BusinessException {
        AccountCoTaxClassification coTaxClassification = new AccountCoTaxClassification();
        coTaxClassification.setAccountId(accountId);
        if (accountCoTaxClassificationService.findList(coTaxClassification).size() > 0) {
            throw new BusinessException("Tax classification can only save one item, do not repeat operation");
        }
    }

    /**
     * 验证公司资产信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateAsset(Long accountId) throws BusinessException {
        AccountCoAsset accountCoAsset = new AccountCoAsset();
        accountCoAsset.setAccountId(accountId);
        if (accountCoAssetService.findList(accountCoAsset).size() > 0) {
            throw new BusinessException("Only one item of asset information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证公司投资目标只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateInvestmentTarget(Long accountId) throws BusinessException {
        AccountCoInvestmentTarget investmentTarget = new AccountCoInvestmentTarget();
        investmentTarget.setAccountId(accountId);
        if (accountCoInvestmentTargetService.findList(investmentTarget).size() > 0) {
            throw new BusinessException("Only one investment goal can be saved. Do not repeat the operation.");
        }
    }

    /**
     * 逻辑判断 验证是否包含某个字符    (投资目标)
     *
     * @param accountCoInvestmentTarget
     */
    private void validateInvestTarget(AccountCoInvestmentTarget accountCoInvestmentTarget) throws BusinessException {
        String investmentTarget = accountCoInvestmentTarget.getInvestmentTarget();
        String[] investmentTargetArr = investmentTarget.split(",");
        List<String> str = Arrays.asList(investmentTargetArr);
        if (str.contains(AccountConsts.INCOME) && str.contains(AccountConsts.TRADINGPROFITS)) {
            // 收入和交易利润只能保存其中一个
            throw new BusinessException("Income and transaction profits can only be kept in one of them.");
        }
        if (str.contains(AccountConsts.INCOME) && str.contains(AccountConsts.SPECULATE)) {
            // 收入和投机只能保存其中一个
            throw new BusinessException("Revenue and speculation can only save one of them.");
        }
        if (str.contains(AccountConsts.CAPITALPRESERVATION) && str.contains(AccountConsts.TRADINGPROFITS)) {
            // 资本保值和交易利润只能保存其中一个
            throw new BusinessException("Capital hedging and trading profits can only be preserved in one of them");
        }
        if (str.contains(AccountConsts.CAPITALPRESERVATION) && str.contains(AccountConsts.SPECULATE)) {
            // 资本保值和投机只能保存其中一个
            throw new BusinessException("Capital hedging and speculation can only preserve one of them");
        }
    }

    /**
     * 逻辑判断 知识水平 ( 交易经验 )
     *
     * @param accountCoTradeExperience
     */
    private void validateKnowledge(AccountCoTradeExperience accountCoTradeExperience) throws BusinessException {
        String ids = accountCoTradeExperience.getKnowledgeLevel();
        if (ids.equalsIgnoreCase(AccountConsts.KNOWLEDGELEVELISZERO) || ids.equalsIgnoreCase(AccountConsts.LIMITEDKNOWLEDGELEVEL)) {
            // 知识水平水平不够
            throw new BusinessException("The level of knowledge is not enough, it needs a good level of knowledge above");
        }
    }

    /**
     * 验证公司WBen合约只能保存一条
     *
     * @param accountId
     * @param type
     * @throws BusinessException
     */
    private void validateWbenContract(Long accountId, String type) throws BusinessException {
        AccountCoWbenContract accountCoWbenContract = new AccountCoWbenContract();
        accountCoWbenContract.setAccountId(accountId);
        accountCoWbenContract.setType(type);
        if (accountCoWbenContractService.findList(accountCoWbenContract).size() > 0) {
            throw new BusinessException("W8 contract can only be saved one, do not repeat operation");
        }
    }

    /**
     * 验证CRS 共同汇报标准（CRS）实体自我证明表格（CRS-E）只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateCrsEntity(Long accountId) throws BusinessException {
        AccountCoCrsEntity coCrsEntity = new AccountCoCrsEntity();
        coCrsEntity.setAccountId(accountId);
        if (accountCoCrsEntityService.findList(coCrsEntity).size() > 0) {
            throw new BusinessException("CRS Common Reporting Standard (CRS) Entity Self-Certification Form (CRS-E) can only save one item. Do not repeat operation");
        }
    }

    /**
     * 验证公司CRS控权人自我证明表格只能保存一条
     *
     * @param accountId
     * @param coCrsEntityContrId  crs实体（CRS-E）控权人信息ID
     * @throws BusinessException
     */
    private void validateCrsControl(Long accountId, Long coCrsEntityContrId) throws BusinessException {
        AccountCoCrsControl coCrsControl = new AccountCoCrsControl();
        coCrsControl.setAccountId(accountId);
        coCrsControl.setCoCrsEntityContrId(coCrsEntityContrId);
        if (accountCoCrsControlService.findList(coCrsControl).size() > 0) {
            throw new BusinessException("Only one CRS controller self-certification form can be saved. Please do not repeat the operation");
        }
    }

    /**
     * 验证公司一体化投资管理账户功能只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateInvestAccount(Long accountId) throws BusinessException {
        AccountCoInvestmentAccount investmentAccount = new AccountCoInvestmentAccount();
        investmentAccount.setAccountId(accountId);
        if (accountCoInvestmentAccountService.findList(investmentAccount).size() > 0) {
            throw new BusinessException("Integrative Investment Management Account can only be saved one, do not repeat operation");
        }
    }

    /**
     * 返回文件上传类型列表
     *
     * @param uploadFileTypeList
     * @param accountCoPersonalList
     * @param list
     */
    private void returnUploadFileType(List<UploadFileType> uploadFileTypeList, List<AccountCoPersonal> accountCoPersonalList, List<UploadFileType> list) {
        for (UploadFileType uploadFileType : uploadFileTypeList) {
            for (AccountCoPersonal accountCoPersonal : accountCoPersonalList) {
                UploadFileType uploadFile = new UploadFileType();
                // 个人股东的名字/法人股东名字
                String middleName = null == accountCoPersonal.getMiddleName() ? "" : accountCoPersonal.getMiddleName();
                String suffix = null == accountCoPersonal.getSuffix() ? "" : accountCoPersonal.getSuffix();
                String name = accountCoPersonal.getLastName() + " " + middleName + " " + accountCoPersonal.getFirstName() + " " + suffix;
                String format = String.format(uploadFileType.getContent(), name);
                uploadFile.setId(uploadFileType.getId());
                // 个人股东id/法人股东id
                uploadFile.setCoPersonalId(accountCoPersonal.getId());
                uploadFile.setTypeKey(uploadFileType.getTypeKey());
                uploadFile.setType(uploadFileType.getType());
                uploadFile.setLang(uploadFileType.getLang());
                uploadFile.setContent(format);
                uploadFile.setDest(uploadFileType.getDest());
                uploadFile.setMoreInfoPdf(uploadFileType.getMoreInfoPdf());
                uploadFile.setAccountType(uploadFileType.getAccountType());
                list.add(uploadFile);
            }
        }
    }
}
