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
 * @Date: 2019/8/31 19:49
 * @Description
 */
@Service
public class AccountPartnerShipServiceImpl implements AccountPartnerShipService {

    @Autowired(required = false)
    protected AccountParBrokerPermissionService accountParBrokerPermissionService;
    @Autowired(required = false)
    protected AccountService accountService;
    @Autowired(required = false)
    protected AccountParBrokerInfoService accountParBrokerInfoService;
    @Autowired(required = false)
    protected AccountParInstitutionalService accountParInstitutionalService;
    @Autowired(required = false)
    protected AccountParAddressService accountParAddressService;
    @Autowired(required = false)
    protected AccountParContactService accountParContactService;
    @Autowired(required = false)
    protected AccountParTaxesService accountParTaxesService;
    @Autowired(required = false)
    protected AccountParWealthSourceService accountParWealthSourceService;
    @Autowired(required = false)
    protected AccountParAccountPortfolioService accountParAccountPortfolioService;
    @Autowired(required = false)
    protected AccountParPersonalService accountParPersonalService;
    @Autowired(required = false)
    protected AccountParIdentityService accountParIdentityService;
    @Autowired(required = false)
    protected AccountParSafeQuestionService accountParSafeQuestionService;
    @Autowired(required = false)
    protected AccountParTaxClassificationService accountParTaxClassificationService;
    @Autowired(required = false)
    protected AccountParRegulatoryService accountParRegulatoryService;
    @Autowired(required = false)
    protected AccountParAssetService accountParAssetService;
    @Autowired(required = false)
    protected AccountParInvestmentTargetService accountParInvestmentTargetService;
    @Autowired(required = false)
    protected AccountParTradeExperienceService accountParTradeExperienceService;
    @Autowired(required = false)
    protected AccountParSecurityOfficerService accountParSecurityOfficerService;
    @Autowired(required = false)
    protected AccountParWbenContractService accountParWbenContractService;
    @Autowired(required = false)
    protected AccountParCrsEntityService accountParCrsEntityService;
    @Autowired(required = false)
    protected AccountParCrsControlService accountParCrsControlService;
    @Autowired(required = false)
    protected AccountParAgreementService accountParAgreementService;
    @Autowired(required = false)
    protected AccountParInvestmentAccountService accountParInvestmentAccountService;
    @Autowired(required = false)
    protected AccountParFileService accountParFileService;
    @Autowired(required = false)
    protected AccountCommonAgreementTypeService accountCommonAgreementTypeService;
    @Autowired(required = false)
    protected AccountCommonAgreementService accountCommonAgreementService;
    @Autowired(required = false)
    protected AccountParCrsControlNumService accountParCrsControlNumService;
    @Autowired(required = false)
    protected AccountParAmlCtfService accountParAmlCtfService;
    @Autowired(required = false)
    protected UploadFileTypeService uploadFileTypeService;
    @Autowired(required = false)
    protected AccountCommonFileService accountCommonFileService;
    @Autowired(required = false)
    private DictionaryService dictionaryService;
    @Autowired(required = false)
    private AccountParCrsEntityContrService accountParCrsEntityContrService;

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
    public void saveOne(PartnerShipInfoModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountParInstitutional().getAccountId());
        // 合伙企业机构信息
        saveParInstitutional(model.getAccountParInstitutional());
        // 第一页是把合伙企业机构信息的id 存到 PAR_PERSONAL_ID
        // 合伙企业地址信息
        saveParAddress(model.getAccountParInstitutional().getId(), model.getAccountParAddressList());
        // 合伙企业联系信息
        saveParContact(model.getAccountParInstitutional().getId(), model.getAccountParContactList());
        // 合伙企业税务居住地
        saveParTaxes(model.getAccountParInstitutional().getId(), model.getAccountParTaxesList());
        // 合伙企业财富来源
        saveParWealthSource(model.getAccountParWealthSourceList());
        // 合伙企业账户组合信息
        saveParAccountPortfolio(model.getAccountParAccountPortfolio());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTwo(PartnerShipAuthorizedModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountParPersonal().getAccountId());
        // 合伙企业个人信息
        saveParPersonal(model.getAccountParPersonal());
        // 第二页把合伙企业个人信息的id 存到 PAR_PERSONAL_ID
        // 合伙企业地址信息
        saveParAddress(model.getAccountParPersonal().getId(), model.getAccountParAddressList());
        // 合伙企业联系信息
        saveParContact(model.getAccountParPersonal().getId(), model.getAccountParContactList());
        // 合伙企业身份信息
        saveParIdentity(model.getAccountParPersonal().getId(), model.getAccountParIdentity());
        // 合伙企业税务居住地
        saveParTaxes(model.getAccountParPersonal().getId(), model.getAccountParTaxesList());
        // 合伙企业安全问题
        saveParSafeQuestion(model.getAccountParSafeQuestionList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveThree(AccountParTaxClassification accountParTaxClassification) throws BusinessException {
        validateOpenAccountFinish(accountParTaxClassification.getAccountId());
        // 税收分类
        saveParTaxClassification(accountParTaxClassification);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFour(List<AccountParRegulatory> accountParRegulatoryList) throws BusinessException {
        validateOpenAccountFinish(accountParRegulatoryList.get(0).getAccountId());
        // 合伙企业 规管信息
        saveParRegulatory(accountParRegulatoryList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFive(AccountParBrokerPermission accountParBrokerPermission) throws BusinessException {
        validateOpenAccountFinish(accountParBrokerPermission.getAccountId());
        // 合伙企业经济商监管许可
        saveParBrokerPermission(accountParBrokerPermission);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSix(List<AccountParBrokerInfo> accountParBrokerInfoList) throws BusinessException {
        validateOpenAccountFinish(accountParBrokerInfoList.get(0).getAccountId());
        // 合伙企业经济商信息
        saveParBrokerInfo(accountParBrokerInfoList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSeven(PartnerShipAssetModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountParAsset().getAccountId());
        // 合伙企业资产信息
        saveParAsset(model.getAccountParAsset());
        // 合伙企业投资目标信息
        saveParInvestmentTarget(model.getAccountParInvestmentTarget());
        // 合伙企业交易经验信息
        saveParTradeExperience(model.getAccountParTradeExperienceList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEight(PartnerShipRegisteredOwnerModel model) throws BusinessException {
        for (PartnerShipRegisteredOwnerInfoModel partnerShipRegisteredOwnerInfoModel : model.getPartnerShipRegisteredOwnerInfoModelList()) {
            validateOpenAccountFinish(partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getAccountId());
            // 合伙企业个人信息
            saveParPersonal(partnerShipRegisteredOwnerInfoModel.getAccountParPersonal());
            // 第八页把合伙企业个人信息的id 存到 PAR_PERSONAL_ID
            // 合伙企业地址信息
            saveParAddress(partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getId(), partnerShipRegisteredOwnerInfoModel.getAccountParAddressList());
            // 合伙企业联系信息
            saveParContact(partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getId(), partnerShipRegisteredOwnerInfoModel.getAccountParContactList());
            // 合伙企业身份信息 个人股东才有身份信息 ：individualShareholder
            if (partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getPersonalType().endsWith(AccountConsts.ACCOUNT_PERSONAL_TYPE)) {
                if (null != partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().getIssuingCountry()
                        && null != partnerShipRegisteredOwnerInfoModel.getAccountParIdentity().getIdCardType()) {
                    saveParIdentity(partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getId(), partnerShipRegisteredOwnerInfoModel.getAccountParIdentity());
                }
            }
            // 合伙企业税务居住地
            saveParTaxes(partnerShipRegisteredOwnerInfoModel.getAccountParPersonal().getId(), partnerShipRegisteredOwnerInfoModel.getAccountParTaxesList());
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeEight(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        // 查出合伙企业的个人信息
        AccountParPersonal accountParPersonal = accountParPersonalService.selectByPrimaryKey(id);
        // 删除 合伙企业个人信息
        accountParPersonalService.remove(accountParPersonal.getId());
        // 删除 合伙企业地址信息
        removeAddressByParPersonalId(accountParPersonal.getId());
        // 删除 合伙企业联系信息
        removeContactByParPersonalId(accountParPersonal.getId());
        //  合伙企业身份信息 个人股东才有身份信息 ：individualShareholder
        if (accountParPersonal.getPersonalType().endsWith(AccountConsts.ACCOUNT_PERSONAL_TYPE)) {
            // 删除 合伙企业身份信息
            removeIdentityByParPersonalId(accountParPersonal.getId());
        }
        // 删除 合伙企业税务居住地
        removeTaxesByParPersonalId(accountParPersonal.getId());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveNine(PartnerShipBeneficiaryOwnersModel model) throws BusinessException {
        if (null != model.getPartnerShipBeneficiaryOwnersInfoModelList() && model.getPartnerShipBeneficiaryOwnersInfoModelList().size() > 0) {
            for (PartnerShipBeneficiaryOwnersInfoModel partnerShipBeneficiaryOwnersInfoModel : model.getPartnerShipBeneficiaryOwnersInfoModelList()) {
                validateOpenAccountFinish(partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().getAccountId());
                // 合伙企业个人信息
                saveParPersonal(partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal());
                // 第九页把合伙企业个人信息的id 存到 PAR_PERSONAL_ID
                // 合伙企业地址信息
                saveParAddress(partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().getId(), partnerShipBeneficiaryOwnersInfoModel.getAccountParAddressList());
                // 合伙企业身份信息
                if (null != partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().getIssuingCountry()
                        && null != partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity().getIdCardType()) {
                    saveParIdentity(partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().getId(), partnerShipBeneficiaryOwnersInfoModel.getAccountParIdentity());
                }
                // 合伙企业税务居住地
                saveParTaxes(partnerShipBeneficiaryOwnersInfoModel.getAccountParPersonal().getId(), partnerShipBeneficiaryOwnersInfoModel.getAccountParTaxesList());
            }
            updateAccountStatus(model.getPartnerShipBeneficiaryOwnersInfoModelList().get(0).getAccountParPersonal().getAccountId(), AccountConsts.ACCOUNT_STATUS_PAGE_NINE);
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void removeNine(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        // 查出合伙企业的个人信息
        AccountParPersonal accountParPersonal = accountParPersonalService.selectByPrimaryKey(id);
        // 判断是已列出的还是未列出
        if (null != accountParPersonal.getPosition() && null != accountParPersonal.getPersonalTypeTwo()) {
            // 已列出. 引用被授权人/个人股东作为经营负责人,删除的的时候只需要把这两个字段set成null 即可
            accountParPersonal.setPosition(null);
            accountParPersonal.setPersonalTypeTwo(null);
            accountParPersonalService.updateByPrimaryKey(accountParPersonal);
        } else {
            // 未列出
            // 删除 合伙企业个人信息
            accountParPersonalService.remove(accountParPersonal.getId());
            // 删除 合伙企业地址信息
            removeAddressByParPersonalId(accountParPersonal.getId());
            // 删除 合伙企业身份信息
            removeIdentityByParPersonalId(accountParPersonal.getId());
            // 删除 合伙企业税务居住地
            removeTaxesByParPersonalId(accountParPersonal.getId());
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTen(PartnerShipSecurityOfficerModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountParSecurityOfficerList().get(0).getAccountId());
        // 安全官
        saveParSecurityOfficer(model.getAccountParSecurityOfficerList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEleven(AccountParWbenContract accountParWbenContract) throws BusinessException {
        validateOpenAccountFinish(accountParWbenContract.getAccountId());
        // w8Ben 表格
        saveParWbenContract(accountParWbenContract);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTwelve(PartnerShipCrsEntityContrModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountParCrsEntity().getAccountId());
        // 共同汇报标准（CRS）实体自我证明表格（CRS-E）
        saveParCrsEntity(model.getAccountParCrsEntity());
        if (model.getAccountParCrsEntity().getEntityNumber().startsWith("Passive NFE")){
            // 被动非财务实体才能保存
            saveParCrsEntityContr(model.getAccountParCrsEntity().getId(), model.getAccountParCrsEntityContrList());
        }
        if (model.getAccountParCrsEntity().getEntityNumber().startsWith("Financial Institution")) {
            // 财务机构 当修改成财务机构的时候判断是否保存过被动非财务实体(控权人信息)，保存过删除掉
            disposeTwelve(model.getAccountParCrsEntity().getAccountId(), model.getAccountParCrsEntity().getId());
        }
        if (model.getAccountParCrsEntity().getEntityNumber().startsWith("Active NFE")) {
            // 主动非财务实体 当修改成主动非财务实体的时候判断是否保存过被动非财务实体(控权人信息)，保存过删除掉
            disposeTwelve(model.getAccountParCrsEntity().getAccountId(), model.getAccountParCrsEntity().getId());
        }
    }

    /**
     * 处理保存过12页的控权人姓名信息，
     *
     * @param accountId
     * @param parCrsEntityId
     */
    private void disposeTwelve(Long accountId, Long parCrsEntityId) {
        AccountParCrsEntityContr accountParCrsEntityContr = new AccountParCrsEntityContr();
        accountParCrsEntityContr.setAccountId(accountId);
        accountParCrsEntityContr.setParCrsEntityId(parCrsEntityId);
        List<AccountParCrsEntityContr> crsEntityContrServiceList = accountParCrsEntityContrService.findList(accountParCrsEntityContr);
        if (null != crsEntityContrServiceList && crsEntityContrServiceList.size() > 0) {
            for (AccountParCrsEntityContr parCrsEntityContr : crsEntityContrServiceList) {
                accountParCrsEntityContrService.remove(parCrsEntityContr.getId());
                // 删除对应的CRS-CP表格信息
                // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
                AccountParCrsControl accountParCrsControl = new AccountParCrsControl();
                accountParCrsControl.setAccountId(accountParCrsEntityContr.getAccountId());
                accountParCrsControl.setParCrsEntityContrId(parCrsEntityContr.getId());
                List<AccountParCrsControl> parCrsControlServiceList = accountParCrsControlService.findList(accountParCrsControl);
                accountParCrsControlService.removeByParCrsEntityContrId(parCrsEntityContr.getId());
                // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）地址信息
                if (null != parCrsControlServiceList && parCrsControlServiceList.size() > 0) {
                    removeAddressByParPersonalId(parCrsControlServiceList.get(0).getId());
                }
                // 公司CRS控权人自我证明控权人编号
                accountParCrsControlNumService.removeByParCrsEntityContrId(parCrsEntityContr.getId());
            }
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveThirteen(PartnerShipCrsControlModel model) throws BusinessException {
        validateOpenAccountFinish(model.getAccountParCrsControl().getAccountId());
        // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
        saveParCrsControl(model.getAccountParCrsControl());
        // 合伙企业共同汇报标准（CRS）控权人自我证明表格（CRS-CP）地址信息
        saveParAddress(model.getAccountParCrsControl().getId(), model.getAccountParAddressList());
        // 合伙企业CRS控权人自我证明控权人编号
        saveParCrsControlNum(model.getAccountParCrsControl().getParCrsEntityContrId(), model.getAccountParCrsControlNumList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFourteen(List<AccountParAgreement> accountParAgreementList) throws BusinessException {
        validateOpenAccountFinish(accountParAgreementList.get(0).getAccountId());
        // 协议纰漏
        saveParAgreement(accountParAgreementList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFifteen(AccountParInvestmentAccount accountParInvestmentAccount) throws BusinessException {
        validateOpenAccountFinish(accountParInvestmentAccount.getAccountId());
        // 合伙企业一体化投资管理账户
        saveParInvestmentAccount(accountParInvestmentAccount);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveEighteen(List<AccountParAmlCtf> accountParAmlCtfList) throws BusinessException {
        validateOpenAccountFinish(accountParAmlCtfList.get(0).getAccountId());
        // 合伙企业 aml/ctf 风险问卷
        saveParAmlCtf(accountParAmlCtfList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveNineteen(AccountParFile accountParFile) throws BusinessException {
        validateOpenAccountFinish(accountParFile.getAccountId());
        // 保存上传文件
        saveParFile(accountParFile);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public UploadFileTypeModel getFileUploadType(Long accountId, String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        AccountParPersonal personal = new AccountParPersonal();
        personal.setAccountId(accountId);
        //  个人股东
        personal.setPersonalType(AccountConsts.ACCOUNT_PERSONAL_TYPE);
        List<AccountParPersonal> parPersonalList = accountParPersonalService.findList(personal);
        UploadFileTypeModel model = new UploadFileTypeModel();
        List<UploadFileType> personalList = new ArrayList<>();
        List<UploadFileType> count = new ArrayList<>();
        List<UploadFileType> legalPersonal = new ArrayList<>();
        // 法人股东
        personal.setPersonalType(AccountConsts.LEGAL_PERSON_SHAREHOLDER);
        List<AccountParPersonal> legalPersonalList = accountParPersonalService.findList(personal);
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        if (0 == parPersonalList.size() && 0 != legalPersonalList.size()) {
            // 没有个人股东，有法人股东
            Long[] uploadFileTypeKindLegalPersonal = {3L};
            // 法人股东的地址和法人股东的身份生日证明
            List<UploadFileType> uploadFileTypeLegalPersonalList = uploadFileTypeService.findByKindArr(uploadFileTypeKindLegalPersonal, lang, AccountConsts.ACCOUNT_PARTNERSHIP);
            Long[] uploadFileTypeKindArr = {1L};
            count.addAll(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_PARTNERSHIP));
            // 法人股东
            returnUploadFileType(uploadFileTypeLegalPersonalList,legalPersonalList,legalPersonal);
            count.addAll(legalPersonal);
            model.setUploadFileTypeList(count);
            return model;
        }
        if (0 == legalPersonalList.size() && 0 == parPersonalList.size()) {
            // 没有个人股东，没有法人股东
            Long[] uploadFileTypeKindArr = {1L};
            model.setUploadFileTypeList(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_PARTNERSHIP));
            return model;
        }
        if (0 == legalPersonalList.size() && 0 != parPersonalList.size()) {
            // 没有法人股东，有个人股东
            Long[] uploadFileTypeCoPersonal = {2L};
            // 个人股东的地址和个人股东的身份生日证明
            List<UploadFileType> uploadFileTypeIndividualShareholder = uploadFileTypeService.findByKindArr(uploadFileTypeCoPersonal, lang, AccountConsts.ACCOUNT_PARTNERSHIP);
            Long[] uploadFileTypeKindArr = {1L};
            count.addAll(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_PARTNERSHIP));
            returnUploadFileType(uploadFileTypeIndividualShareholder,parPersonalList,personalList);
            count.addAll(personalList);
            model.setUploadFileTypeList(count);
            return model;
        }
        // 有个人股东有法人股东
        Long[] uploadFileTypeKindArr = {1L};
        count.addAll(uploadFileTypeService.findByKindArr(uploadFileTypeKindArr, lang, AccountConsts.ACCOUNT_PARTNERSHIP));
        // 个人股东的地址和个人股东的身份生日证明
        Long[] uploadFileTypeKindCoPersonal = {2L};
        List<UploadFileType> uploadFileTypeIndividualShareholder = uploadFileTypeService.findByKindArr(uploadFileTypeKindCoPersonal, lang, AccountConsts.ACCOUNT_PARTNERSHIP);
        // 法人股东的地址和法人股东的身份生日证明
        Long[] uploadFileTypeKindLegalPersonal = {3L};
        List<UploadFileType> uploadFileTypeLegalPersonalList = uploadFileTypeService.findByKindArr(uploadFileTypeKindLegalPersonal, lang, AccountConsts.ACCOUNT_PARTNERSHIP);
        // 个人股东
        returnUploadFileType(uploadFileTypeIndividualShareholder,parPersonalList,personalList);
        count.addAll(personalList);
        // 法人股东
        returnUploadFileType(uploadFileTypeLegalPersonalList,legalPersonalList,legalPersonal);
        count.addAll(legalPersonal);
        model.setUploadFileTypeList(count);
        return model;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public List<AccountCommonAgreement> findByAgreement(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Account account = accountService.selectByPrimaryKey(accountId);
        List<AccountCommonAgreementType> agreementTypes = new LinkedList<>();
        // 获取 合伙企业账户投资组合 信息
        AccountParAccountPortfolio accountParAccountPortfolio = new AccountParAccountPortfolio();
        accountParAccountPortfolio.setAccountId(accountId);
        List<AccountParAccountPortfolio> parAccountPortfolioList = accountParAccountPortfolioService.findList(accountParAccountPortfolio);
        //根据账户投资组合类型去获取披露协议
        AccountCommonAgreementType accountCommonAgreementType = new AccountCommonAgreementType();
        // 账户投资组合类型
        accountCommonAgreementType.setAccountPortfolioType(parAccountPortfolioList.get(0).getAccountPortfolioType());
        // 账户类型
        accountCommonAgreementType.setAccountType(account.getAccountType());
        // 根据交易经验的国家和类型去获取披露协议
        AccountParTradeExperience accountParTradeExperience = new AccountParTradeExperience();
        accountParTradeExperience.setAccountId(accountId);
        List<AccountParTradeExperience> parTradeExperienceServiceList = accountParTradeExperienceService.findList(accountParTradeExperience);
        for (AccountParTradeExperience parTradeExperience : parTradeExperienceServiceList) {
            accountCommonAgreementType.setAgreementType(parTradeExperience.getTansactionType());
            List<String> locationStrsList = Arrays.asList(parTradeExperience.getTradingLocation().split(","));
            agreementTypes = findByTradingLocation(agreementTypes, locationStrsList, accountCommonAgreementType, parTradeExperience.getTansactionType());
        }
        // 去重
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
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updatePersonalByPrimaryKeySelective(Long id, String position, String personalTypeTwo) throws BusinessException {
        AccountParPersonal accountParPersonal = accountParPersonalService.selectByPrimaryKey(id);
        accountParPersonal.setPosition(position);
        accountParPersonal.setPersonalTypeTwo(personalTypeTwo);
        return accountParPersonalService.updateByPrimaryKeySelective(accountParPersonal);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int updateCrsEntityStatus(Long parCrsEntityContrId) throws BusinessException {
        if (null == parCrsEntityContrId){
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        AccountParCrsEntityContr accountParCrsEntityContr = accountParCrsEntityContrService.selectByPrimaryKey(parCrsEntityContrId);
        AccountParCrsEntity accountParCrsEntity = accountParCrsEntityService.selectByPrimaryKey(accountParCrsEntityContr.getParCrsEntityId());
        accountParCrsEntity.setCrsCpStatusId(parCrsEntityContrId);
        return accountParCrsEntityService.updateByPrimaryKeySelective(accountParCrsEntity);
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
     * 插入/更新 合伙企业机构信息
     *
     * @param accountParInstitutional
     * @return
     */
    int saveParInstitutional(AccountParInstitutional accountParInstitutional) {
        accountParInstitutional.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParInstitutional.getId()) {
            accountParInstitutional.setId(SerialnoUtils.buildPrimaryKey());
            accountParInstitutional.setCreatedDate(System.currentTimeMillis());
            validateInstitutional(accountParInstitutional.getAccountId());
            return accountParInstitutionalService.insert(accountParInstitutional);
        } else {
            return accountParInstitutionalService.updateByPrimaryKey(accountParInstitutional);
        }
    }

    /**
     * 插入/更新 合伙企业地址信息
     *
     * @param parPersonalId
     * @param accountParAddressList
     */
    void saveParAddress(Long parPersonalId, List<AccountParAddress> accountParAddressList) {
        AccountParAddress parAddress = new AccountParAddress();
        parAddress.setAccountId(accountParAddressList.get(0).getAccountId());
        parAddress.setParPersonalId(parPersonalId);
        List<AccountParAddress> oldParAddresses = accountParAddressService.findList(parAddress);
        for (AccountParAddress newAccountParAddress : accountParAddressList) {
            int i = 0;
            for (int j = 0; j < oldParAddresses.size(); j++) {
                if (newAccountParAddress.getId() != null && newAccountParAddress.getId().equals(oldParAddresses.get(j).getId())) {
                    i = 1;
                    oldParAddresses.remove(j);
                    newAccountParAddress.setUpdatedDate(System.currentTimeMillis());
                    newAccountParAddress.setParPersonalId(parPersonalId);
                    // 更新数据
                    accountParAddressService.updateByPrimaryKey(newAccountParAddress);
                }
            }
            if (i == 0) {
                // 插入数据
                newAccountParAddress.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParAddress.setParPersonalId(parPersonalId);
                newAccountParAddress.setCreatedDate(System.currentTimeMillis());
                newAccountParAddress.setUpdatedDate(System.currentTimeMillis());
                accountParAddressService.insert(newAccountParAddress);
            }
        }
        for (AccountParAddress oldParAddress : oldParAddresses) {
            accountParAddressService.remove(oldParAddress.getId());
        }
    }


    /**
     * 插入/更新 合伙企业 联系信息
     *
     * @param parPersonalId
     * @param accountParContactList
     */
    void saveParContact(Long parPersonalId, List<AccountParContact> accountParContactList) {
        AccountParContact accountParContact = new AccountParContact();
        accountParContact.setAccountId(accountParContactList.get(0).getAccountId());
        accountParContact.setParPersonalId(parPersonalId);
        List<AccountParContact> oldParContactService = accountParContactService.findList(accountParContact);
        for (AccountParContact newAccountParContact : accountParContactList) {
            int i = 0;
            for (int j = 0; j < oldParContactService.size(); j++) {
                if (newAccountParContact.getId() != null && newAccountParContact.getId().equals(oldParContactService.get(j).getId())) {
                    i = 1;
                    oldParContactService.remove(j);
                    newAccountParContact.setUpdatedDate(System.currentTimeMillis());
                    newAccountParContact.setParPersonalId(parPersonalId);
                    // 更新数据
                    accountParContactService.updateByPrimaryKey(newAccountParContact);
                }
            }
            if (i == 0) {
                // 插入数据
                newAccountParContact.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParContact.setParPersonalId(parPersonalId);
                newAccountParContact.setCreatedDate(System.currentTimeMillis());
                newAccountParContact.setUpdatedDate(System.currentTimeMillis());
                accountParContactService.insert(newAccountParContact);
            }
        }
        for (AccountParContact oldParContact : oldParContactService) {
            accountParContactService.remove(oldParContact.getId());
        }
    }

    /**
     * 插入/更新  合伙企业税务居住地
     *
     * @param parPersonalId
     * @param accountParTaxesList
     */
    void saveParTaxes(Long parPersonalId, List<AccountParTaxes> accountParTaxesList) {
        AccountParTaxes accountParTaxes = new AccountParTaxes();
        accountParTaxes.setAccountId(accountParTaxesList.get(0).getAccountId());
        accountParTaxes.setParPersonalId(parPersonalId);
        List<AccountParTaxes> oldTaxesService = accountParTaxesService.findList(accountParTaxes);
        for (AccountParTaxes newAccountParTaxes : accountParTaxesList) {
            int i = 0;
            for (int j = 0; j < oldTaxesService.size(); j++) {
                if (newAccountParTaxes.getId() != null && newAccountParTaxes.getId().equals(oldTaxesService.get(j).getId())) {
                    i = 1;
                    oldTaxesService.remove(j);
                    newAccountParTaxes.setUpdatedDate(System.currentTimeMillis());
                    newAccountParTaxes.setParPersonalId(parPersonalId);
                    // 更新数据
                    accountParTaxesService.updateByPrimaryKey(newAccountParTaxes);
                }
            }
            if (i == 0) {
                // 插入数据
                newAccountParTaxes.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParTaxes.setParPersonalId(parPersonalId);
                newAccountParTaxes.setCreatedDate(System.currentTimeMillis());
                newAccountParTaxes.setUpdatedDate(System.currentTimeMillis());
                accountParTaxesService.insert(newAccountParTaxes);
            }
        }
        for (AccountParTaxes parTaxes : oldTaxesService) {
            accountParTaxesService.remove(parTaxes.getId());
        }
    }

    /**
     * 插入/更新  合伙企业财富来源
     *
     * @param accountParWealthSourceList
     */
    void saveParWealthSource(List<AccountParWealthSource> accountParWealthSourceList) {
        AccountParWealthSource accountParWealthSource = new AccountParWealthSource();
        accountParWealthSource.setAccountId(accountParWealthSourceList.get(0).getAccountId());
        List<AccountParWealthSource> oldAccountParWealthSource = accountParWealthSourceService.findList(accountParWealthSource);
        for (AccountParWealthSource newAccountParWealthSource : accountParWealthSourceList) {
            int i = 0;
            for (int j = 0; j < oldAccountParWealthSource.size(); j++) {
                if (newAccountParWealthSource.getId() != null && newAccountParWealthSource.getId().equals(oldAccountParWealthSource.get(j).getId())) {
                    i = 1;
                    oldAccountParWealthSource.remove(j);
                    newAccountParWealthSource.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountParWealthSourceService.updateByPrimaryKey(newAccountParWealthSource);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParWealthSource.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParWealthSource.setCreatedDate(System.currentTimeMillis());
                newAccountParWealthSource.setUpdatedDate(System.currentTimeMillis());
                accountParWealthSourceService.insert(newAccountParWealthSource);
            }
        }
        for (AccountParWealthSource parWealthSource : oldAccountParWealthSource) {
            accountParWealthSourceService.remove(parWealthSource.getId());
        }
    }

    /**
     * 插入/更新  合伙企业账户组合信息
     *
     * @param accountParAccountPortfolio
     * @return
     */
    int saveParAccountPortfolio(AccountParAccountPortfolio accountParAccountPortfolio) {
        accountParAccountPortfolio.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParAccountPortfolio.getId()) {
            accountParAccountPortfolio.setId(SerialnoUtils.buildPrimaryKey());
            accountParAccountPortfolio.setCreatedDate(System.currentTimeMillis());
            validatePortfolio(accountParAccountPortfolio.getAccountId());
            return accountParAccountPortfolioService.insert(accountParAccountPortfolio);
        } else {
            return accountParAccountPortfolioService.updateByPrimaryKey(accountParAccountPortfolio);
        }
    }


    /**
     * 插入/更新  合伙企业个人信息
     *
     * @param accountParPersonal
     * @return
     */
    int saveParPersonal(AccountParPersonal accountParPersonal) {
        accountParPersonal.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParPersonal.getId()) {
            accountParPersonal.setId(SerialnoUtils.buildPrimaryKey());
            accountParPersonal.setCreatedDate(System.currentTimeMillis());
            if (accountParPersonal.getPersonalType().equals(AccountConsts.ACCOUNT_AUTHORIZEDPERSON)) {
                validatePersonalAuthorizedPerson(accountParPersonal.getAccountId(), accountParPersonal.getPersonalType());
            }
            if (accountParPersonal.getPersonalType().equals(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL)) {
                validatePersonalOperationPrincipal(accountParPersonal.getAccountId(), accountParPersonal.getPersonalType());
            }
            if (null != accountParPersonal.getPersonalTypeTwo() && accountParPersonal.getPersonalTypeTwo().equals(AccountConsts.ACCOUNT_OPERATIONPRINCIPAL)) {
                validatePersonalOperationPrincipalTwo(accountParPersonal.getAccountId(), AccountConsts.ACCOUNT_OPERATIONPRINCIPAL);
            }
            return accountParPersonalService.insert(accountParPersonal);
        } else {
            return accountParPersonalService.updateByPrimaryKey(accountParPersonal);
        }
    }

    /**
     * 插入/更新 合伙企业身份信息
     *
     * @param parPersonalId
     * @param accountParIdentity
     * @return
     */
    int saveParIdentity(Long parPersonalId, AccountParIdentity accountParIdentity) {
        accountParIdentity.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParIdentity.getId()) {
            accountParIdentity.setId(SerialnoUtils.buildPrimaryKey());
            accountParIdentity.setParPersonalId(parPersonalId);
            accountParIdentity.setCreatedDate(System.currentTimeMillis());
            validateIdentity(accountParIdentity.getAccountId(),parPersonalId);
            return accountParIdentityService.insert(accountParIdentity);
        } else {
            accountParIdentity.setParPersonalId(parPersonalId);
            return accountParIdentityService.updateByPrimaryKey(accountParIdentity);
        }
    }

    /**
     * 插入/更新  合伙企业安全问题
     *
     * @param accountParSafeQuestionList
     */
    void saveParSafeQuestion(List<AccountParSafeQuestion> accountParSafeQuestionList) {
        AccountParSafeQuestion accountParSafeQuestion = new AccountParSafeQuestion();
        accountParSafeQuestion.setAccountId(accountParSafeQuestionList.get(0).getAccountId());
        List<AccountParSafeQuestion> oldAccountParSafeQuestionService = accountParSafeQuestionService.findList(accountParSafeQuestion);
        for (AccountParSafeQuestion newAccountParSafeQuestion : accountParSafeQuestionList) {
            int i = 0;
            for (int j = 0; j < oldAccountParSafeQuestionService.size(); j++) {
                if (newAccountParSafeQuestion.getId() != null && newAccountParSafeQuestion.getId().equals(oldAccountParSafeQuestionService.get(j).getId())) {
                    i = 1;
                    oldAccountParSafeQuestionService.remove(j);
                    newAccountParSafeQuestion.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    validateAccountParSafeQuestion(newAccountParSafeQuestion);
                    accountParSafeQuestionService.updateByPrimaryKey(newAccountParSafeQuestion);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParSafeQuestion.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParSafeQuestion.setCreatedDate(System.currentTimeMillis());
                newAccountParSafeQuestion.setUpdatedDate(System.currentTimeMillis());
                validateAccountParSafeQuestion(newAccountParSafeQuestion);
                accountParSafeQuestionService.insert(newAccountParSafeQuestion);
            }
        }
        for (AccountParSafeQuestion parSafeQuestion : oldAccountParSafeQuestionService) {
            accountParSafeQuestionService.remove(parSafeQuestion.getId());
        }
    }

    /**
     * 插入/更新 合伙企业税收分类
     *
     * @param accountParTaxClassification
     * @return
     */
    int saveParTaxClassification(AccountParTaxClassification accountParTaxClassification) {
        accountParTaxClassification.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParTaxClassification.getId()) {
            accountParTaxClassification.setId(SerialnoUtils.buildPrimaryKey());
            accountParTaxClassification.setCreatedDate(System.currentTimeMillis());
            validateTaxClassification(accountParTaxClassification.getAccountId());
            return accountParTaxClassificationService.insert(accountParTaxClassification);
        } else {
            return accountParTaxClassificationService.updateByPrimaryKey(accountParTaxClassification);
        }
    }

    /**
     * 插入/更新  合伙企业规管信息
     *
     * @param accountParRegulatoryList
     */
    void saveParRegulatory(List<AccountParRegulatory> accountParRegulatoryList) {
        AccountParRegulatory accountParRegulatory = new AccountParRegulatory();
        accountParRegulatory.setAccountId(accountParRegulatoryList.get(0).getAccountId());
        List<AccountParRegulatory> oldRegulatoryService = accountParRegulatoryService.findList(accountParRegulatory);
        for (AccountParRegulatory newAccountParRegulatoryInfo : accountParRegulatoryList) {
            int i = 0;
            for (int j = 0; j < oldRegulatoryService.size(); j++) {
                if (newAccountParRegulatoryInfo.getId() != null && newAccountParRegulatoryInfo.getId().equals(oldRegulatoryService.get(j).getId())) {
                    i = 1;
                    oldRegulatoryService.remove(j);
                    //更新数据
                    accountParRegulatoryService.updateByPrimaryKey(newAccountParRegulatoryInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParRegulatoryInfo.setId(SerialnoUtils.buildPrimaryKey());
                accountParRegulatoryService.insert(newAccountParRegulatoryInfo);
            }
        }
        for (AccountParRegulatory parRegulatory : oldRegulatoryService) {
            accountParRegulatoryService.remove(parRegulatory.getId());
        }
    }

    /**
     * 插入/更新 合伙企业经济商监管许可
     *
     * @param accountParBrokerPermission
     * @return
     */
    int saveParBrokerPermission(AccountParBrokerPermission accountParBrokerPermission) {
        accountParBrokerPermission.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParBrokerPermission.getId()) {
            accountParBrokerPermission.setId(SerialnoUtils.buildPrimaryKey());
            accountParBrokerPermission.setCreatedDate(System.currentTimeMillis());
            return accountParBrokerPermissionService.insert(accountParBrokerPermission);
        } else {
            return accountParBrokerPermissionService.updateByPrimaryKey(accountParBrokerPermission);
        }
    }

    /**
     * 插入/更新  合伙企业经济商信息
     *
     * @param accountParBrokerInfoList
     */
    void saveParBrokerInfo(List<AccountParBrokerInfo> accountParBrokerInfoList) {
        AccountParBrokerInfo parBrokerInfo = new AccountParBrokerInfo();
        parBrokerInfo.setAccountId(accountParBrokerInfoList.get(0).getAccountId());
        List<AccountParBrokerInfo> oldParBrokerInfoService = accountParBrokerInfoService.findList(parBrokerInfo);
        for (AccountParBrokerInfo newAccountParBrokerInfo : accountParBrokerInfoList) {
            int i = 0;
            for (int j = 0; j < oldParBrokerInfoService.size(); j++) {
                if (newAccountParBrokerInfo.getId() != null && newAccountParBrokerInfo.getId().equals(oldParBrokerInfoService.get(j).getId())) {
                    i = 1;
                    oldParBrokerInfoService.remove(j);
                    newAccountParBrokerInfo.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountParBrokerInfoService.updateByPrimaryKey(newAccountParBrokerInfo);
                }
            }
            if (i == 0) {
                // 插入数据
                newAccountParBrokerInfo.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParBrokerInfo.setCreatedDate(System.currentTimeMillis());
                newAccountParBrokerInfo.setUpdatedDate(System.currentTimeMillis());
                accountParBrokerInfoService.insert(newAccountParBrokerInfo);
            }
        }
        for (AccountParBrokerInfo accountParBrokerInfo : oldParBrokerInfoService) {
            accountParBrokerInfoService.remove(accountParBrokerInfo.getId());
        }
    }

    /**
     * 插入/更新  合伙企业资产信息
     *
     * @param accountParAsset
     * @return
     */
    int saveParAsset(AccountParAsset accountParAsset) {
        accountParAsset.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParAsset.getId()) {
            accountParAsset.setId(SerialnoUtils.buildPrimaryKey());
            accountParAsset.setCreatedDate(System.currentTimeMillis());
            validateAsset(accountParAsset.getAccountId());
            return accountParAssetService.insert(accountParAsset);
        } else {
            return accountParAssetService.updateByPrimaryKey(accountParAsset);
        }
    }

    /**
     * 插入/更新 合伙企业投资目标信息
     *
     * @param accountParInvestmentTarget
     * @return
     */
    int saveParInvestmentTarget(AccountParInvestmentTarget accountParInvestmentTarget) {
        accountParInvestmentTarget.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParInvestmentTarget.getId()) {
            accountParInvestmentTarget.setId(SerialnoUtils.buildPrimaryKey());
            accountParInvestmentTarget.setCreatedDate(System.currentTimeMillis());
            validateInvestTarget(accountParInvestmentTarget);
            validateInvestmentTarget(accountParInvestmentTarget.getAccountId());
            return accountParInvestmentTargetService.insert(accountParInvestmentTarget);
        } else {
             validateInvestTarget(accountParInvestmentTarget);
            return accountParInvestmentTargetService.updateByPrimaryKey(accountParInvestmentTarget);
        }
    }

    /**
     * 插入/更新  合伙企业交易经验信息
     *
     * @param accountParTradeExperienceList
     */
    void saveParTradeExperience(List<AccountParTradeExperience> accountParTradeExperienceList) {
        AccountParTradeExperience accountParTradeExperience = new AccountParTradeExperience();
        accountParTradeExperience.setAccountId(accountParTradeExperienceList.get(0).getAccountId());
        List<AccountParTradeExperience> oldAccountParTradeExperience = accountParTradeExperienceService.findList(accountParTradeExperience);
        for (AccountParTradeExperience newAccountParTradeExperience : accountParTradeExperienceList) {
            int i = 0;
            for (int j = 0; j < oldAccountParTradeExperience.size(); j++) {
                if (newAccountParTradeExperience.getId() != null && newAccountParTradeExperience.getId().equals(oldAccountParTradeExperience.get(j).getId())) {
                    i = 1;
                    oldAccountParTradeExperience.remove(j);
                    newAccountParTradeExperience.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    validateKnowledge(newAccountParTradeExperience);
                    accountParTradeExperienceService.updateByPrimaryKey(newAccountParTradeExperience);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParTradeExperience.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParTradeExperience.setCreatedDate(System.currentTimeMillis());
                newAccountParTradeExperience.setUpdatedDate(System.currentTimeMillis());
                validateKnowledge(newAccountParTradeExperience);
                accountParTradeExperienceService.insert(newAccountParTradeExperience);
            }
        }
        for (AccountParTradeExperience parTradeExperience : oldAccountParTradeExperience) {
            accountParTradeExperienceService.remove(parTradeExperience.getId());
        }
    }

    /**
     * 插入/更新 合伙企业安全官
     *
     * @param accountParSecurityOfficerList
     */
    void saveParSecurityOfficer(List<AccountParSecurityOfficer> accountParSecurityOfficerList) {
        AccountParSecurityOfficer accountParSecurityOfficer = new AccountParSecurityOfficer();
        accountParSecurityOfficer.setAccountId(accountParSecurityOfficerList.get(0).getAccountId());
        List<AccountParSecurityOfficer> oldParSecurityOfficerService = accountParSecurityOfficerService.findList(accountParSecurityOfficer);
        for (AccountParSecurityOfficer newAccountParSecurityOfficer : accountParSecurityOfficerList) {
            int i = 0;
            for (int j = 0; j < oldParSecurityOfficerService.size(); j++) {
                if (newAccountParSecurityOfficer.getId() != null && newAccountParSecurityOfficer.getId().equals(oldParSecurityOfficerService.get(j).getId())) {
                    i = 1;
                    oldParSecurityOfficerService.remove(j);
                    newAccountParSecurityOfficer.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountParSecurityOfficerService.updateByPrimaryKey(newAccountParSecurityOfficer);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParSecurityOfficer.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParSecurityOfficer.setCreatedDate(System.currentTimeMillis());
                newAccountParSecurityOfficer.setUpdatedDate(System.currentTimeMillis());
                accountParSecurityOfficerService.insert(newAccountParSecurityOfficer);
            }
        }
    }

    /**
     * 删除合伙企业地址信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     */
    int removeAddressByParPersonalId(Long parPersonalId) {
        if (null == parPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountParAddressService.removeByParPersonalId(parPersonalId);
    }

    /**
     * 删除合伙企业联系信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     */
    int removeContactByParPersonalId(Long parPersonalId) {
        if (null == parPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountParContactService.removeByParPersonalId(parPersonalId);
    }

    /**
     * 删除合伙企业身份信息  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     */
    int removeIdentityByParPersonalId(Long parPersonalId) {
        if (null == parPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountParIdentityService.removeByParPersonalId(parPersonalId);
    }

    /**
     * 删除合伙企业税务居住地  通过 parPersonalId
     *
     * @param parPersonalId
     * @return
     */
    int removeTaxesByParPersonalId(Long parPersonalId) {
        if (null == parPersonalId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return accountParTaxesService.removeByParPersonalId(parPersonalId);
    }

    /**
     * 插入/更新  w8ben表格
     *
     * @param accountParWbenContract
     * @return
     */
    int saveParWbenContract(AccountParWbenContract accountParWbenContract) {
        accountParWbenContract.setUpdatedDate(System.currentTimeMillis());
        accountParWbenContract.setSignatureTime(System.currentTimeMillis());
        if (null == accountParWbenContract.getId()) {
            accountParWbenContract.setId(SerialnoUtils.buildPrimaryKey());
            accountParWbenContract.setCreatedDate(System.currentTimeMillis());
            validateWbenContract(accountParWbenContract.getAccountId(),accountParWbenContract.getType());
            return accountParWbenContractService.insert(accountParWbenContract);
        } else {
            return accountParWbenContractService.updateByPrimaryKey(accountParWbenContract);
        }
    }

    /**
     * 插入/更新  共同汇报标准（CRS）实体自我证明表格（CRS-E）
     *
     * @param accountParCrsEntity
     * @return
     */
    int saveParCrsEntity(AccountParCrsEntity accountParCrsEntity) {
        accountParCrsEntity.setUpdatedDate(System.currentTimeMillis());
        accountParCrsEntity.setSignatureDate(System.currentTimeMillis());
        if (null == accountParCrsEntity.getId()) {
            accountParCrsEntity.setId(SerialnoUtils.buildPrimaryKey());
            accountParCrsEntity.setCreatedDate(System.currentTimeMillis());
            validateCrsEntity(accountParCrsEntity.getAccountId());
            return accountParCrsEntityService.insert(accountParCrsEntity);
        } else {
            return accountParCrsEntityService.updateByPrimaryKey(accountParCrsEntity);
        }
    }

    /**
     * 合伙企业crs实体（CRS-E）控权人信息
     * @param accountParCrsEntityContrList
     */
    void saveParCrsEntityContr(Long parCrsEntityId, List<AccountParCrsEntityContr> accountParCrsEntityContrList) {
        AccountParCrsEntityContr accountParCrsEntityContr = new AccountParCrsEntityContr();
        accountParCrsEntityContr.setAccountId(accountParCrsEntityContrList.get(0).getAccountId());
        accountParCrsEntityContr.setParCrsEntityId(parCrsEntityId);
        List<AccountParCrsEntityContr> oldAccountParCrsEntityContr = accountParCrsEntityContrService.findList(accountParCrsEntityContr);
        for (AccountParCrsEntityContr newAccountParCrsEntityContr : accountParCrsEntityContrList) {
            int i = 0;
            for (int j = 0; j < oldAccountParCrsEntityContr.size(); j++) {
                if (newAccountParCrsEntityContr.getId() != null && newAccountParCrsEntityContr.getId().equals(oldAccountParCrsEntityContr.get(j).getId())) {
                    i = 1;
                    oldAccountParCrsEntityContr.remove(j);
                    // 更新数据
                    newAccountParCrsEntityContr.setUpdatedDate(System.currentTimeMillis());
                    newAccountParCrsEntityContr.setParCrsEntityId(parCrsEntityId);
                    accountParCrsEntityContrService.updateByPrimaryKey(newAccountParCrsEntityContr);
                }
            }
            if (i == 0) {
                // 插入数据
                newAccountParCrsEntityContr.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParCrsEntityContr.setCreatedDate(System.currentTimeMillis());
                newAccountParCrsEntityContr.setParCrsEntityId(parCrsEntityId);
                newAccountParCrsEntityContr.setUpdatedDate(System.currentTimeMillis());
                accountParCrsEntityContrService.insert(newAccountParCrsEntityContr);
            }
        }
        for (AccountParCrsEntityContr crsEntityContr : oldAccountParCrsEntityContr) {
            accountParCrsEntityContrService.remove(crsEntityContr.getId());
            // 删除对应的CRS-CP表格信息
            // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
            AccountParCrsControl accountParCrsControl = new AccountParCrsControl();
            accountParCrsControl.setAccountId(crsEntityContr.getAccountId());
            accountParCrsControl.setParCrsEntityContrId(crsEntityContr.getId());
            List<AccountParCrsControl> parCrsControlList = accountParCrsControlService.findList(accountParCrsControl);
            accountParCrsControlService.removeByParCrsEntityContrId(crsEntityContr.getId());
            // 共同汇报标准（CRS）控权人自我证明表格（CRS-CP）地址信息
            if (null != parCrsControlList && parCrsControlList.size() > 0) {
                removeAddressByParPersonalId(parCrsControlList.get(0).getId());
            }
            // 合伙企业CRS控权人自我证明控权人编号
            accountParCrsControlNumService.removeByParCrsEntityContrId(crsEntityContr.getId());
        }
    }

    /**
     * 插入/更新  共同汇报标准（CRS）控权人自我证明表格（CRS-CP）
     *
     * @param accountParCrsControl
     * @return
     */
    int saveParCrsControl(AccountParCrsControl accountParCrsControl) {
        accountParCrsControl.setUpdatedDate(System.currentTimeMillis());
        accountParCrsControl.setSignatureDate(System.currentTimeMillis());
        if (null == accountParCrsControl.getId()) {
            accountParCrsControl.setId(SerialnoUtils.buildPrimaryKey());
            accountParCrsControl.setCreatedDate(System.currentTimeMillis());
            validateCrsControl(accountParCrsControl.getParCrsEntityContrId(), accountParCrsControl.getAccountId());
            return accountParCrsControlService.insert(accountParCrsControl);
        } else {
            return accountParCrsControlService.updateByPrimaryKeySelective(accountParCrsControl);
        }
    }

    /**
     * 插入/更新 合伙企业CRS控权人自我证明控权人编号
     * @param parCrsEntityContrId crs实体（CRS-E）控权人信息ID
     * @param accountParCrsControlNumList
     */
    void saveParCrsControlNum(Long parCrsEntityContrId, List<AccountParCrsControlNum> accountParCrsControlNumList) {
        AccountParCrsControlNum accountParCrsControlNum = new AccountParCrsControlNum();
        accountParCrsControlNum.setAccountId(accountParCrsControlNumList.get(0).getAccountId());
        accountParCrsControlNum.setParCrsEntityContrId(parCrsEntityContrId);
        List<AccountParCrsControlNum> oldAccountParCrsControlNum = accountParCrsControlNumService.findList(accountParCrsControlNum);
        for (AccountParCrsControlNum newAccountParCrsControlNum : accountParCrsControlNumList) {
            int i = 0;
            for (int j = 0; j < oldAccountParCrsControlNum.size(); j++) {
                if (newAccountParCrsControlNum.getId() != null && newAccountParCrsControlNum.getId().equals(oldAccountParCrsControlNum.get(j).getId())) {
                    i = 1;
                    oldAccountParCrsControlNum.remove(j);
                    //更新数据
                    newAccountParCrsControlNum.setParCrsEntityContrId(parCrsEntityContrId);
                    accountParCrsControlNumService.updateByPrimaryKeySelective(newAccountParCrsControlNum);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParCrsControlNum.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParCrsControlNum.setParCrsEntityContrId(parCrsEntityContrId);
                accountParCrsControlNumService.insert(newAccountParCrsControlNum);
            }
        }
        for (AccountParCrsControlNum parCrsControlNum : oldAccountParCrsControlNum) {
            accountParCrsControlNumService.remove(parCrsControlNum.getId());
        }
    }

    /**
     * 插入/更新  合伙企业协议纰漏信息
     *
     * @param accountParAgreementList
     */
    void saveParAgreement(List<AccountParAgreement> accountParAgreementList) {
        AccountParAgreement accountParAgreement = new AccountParAgreement();
        accountParAgreement.setAccountId(accountParAgreementList.get(0).getAccountId());
        List<AccountParAgreement> oldAccountParAgreement = accountParAgreementService.findList(accountParAgreement);
        for (AccountParAgreement newAccountParAgreement : accountParAgreementList) {
            int i = 0;
            for (int j = 0; j < oldAccountParAgreement.size(); j++) {
                if (newAccountParAgreement.getId() != null && newAccountParAgreement.getId().equals(oldAccountParAgreement.get(j).getId())) {
                    i = 1;
                    oldAccountParAgreement.remove(j);
                    newAccountParAgreement.setUpdatedDate(System.currentTimeMillis());
                    newAccountParAgreement.setSignatureTime(System.currentTimeMillis());
                    //更新数据
                    accountParAgreementService.updateByPrimaryKey(newAccountParAgreement);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParAgreement.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParAgreement.setCreatedDate(System.currentTimeMillis());
                newAccountParAgreement.setSignatureTime(System.currentTimeMillis());
                newAccountParAgreement.setUpdatedDate(System.currentTimeMillis());
                accountParAgreementService.insert(newAccountParAgreement);
            }
        }
        for (AccountParAgreement parAgreement : oldAccountParAgreement) {
            accountParAgreementService.remove(parAgreement.getId());
        }
    }

    /**
     * 插入/更新  合伙企业一体化投资管理账户
     *
     * @param accountParInvestmentAccount
     * @return
     */
    int saveParInvestmentAccount(AccountParInvestmentAccount accountParInvestmentAccount) {
        accountParInvestmentAccount.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParInvestmentAccount.getId()) {
            accountParInvestmentAccount.setId(SerialnoUtils.buildPrimaryKey());
            accountParInvestmentAccount.setCreatedDate(System.currentTimeMillis());
            validateInvestAccount(accountParInvestmentAccount.getAccountId());
            return accountParInvestmentAccountService.insert(accountParInvestmentAccount);
        } else {
            return accountParInvestmentAccountService.updateByPrimaryKey(accountParInvestmentAccount);
        }
    }

    /**
     * 插入/更新  合伙企业AML/CTF 风险问卷
     *
     * @param accountParAmlCtfList
     */
    void saveParAmlCtf(List<AccountParAmlCtf> accountParAmlCtfList) {
        AccountParAmlCtf parAmlCtf = new AccountParAmlCtf();
        parAmlCtf.setAccountId(accountParAmlCtfList.get(0).getAccountId());
        List<AccountParAmlCtf> oldAccountParAmlCtf = accountParAmlCtfService.findList(parAmlCtf);
        for (AccountParAmlCtf newAccountParAmlCtf : accountParAmlCtfList) {
            int i = 0;
            for (int j = 0; j < oldAccountParAmlCtf.size(); j++) {
                if (newAccountParAmlCtf.getId() != null && newAccountParAmlCtf.getId().equals(oldAccountParAmlCtf.get(j).getId())) {
                    i = 1;
                    oldAccountParAmlCtf.remove(j);
                    newAccountParAmlCtf.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountParAmlCtfService.updateByPrimaryKey(newAccountParAmlCtf);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountParAmlCtf.setId(SerialnoUtils.buildPrimaryKey());
                newAccountParAmlCtf.setCreatedDate(System.currentTimeMillis());
                newAccountParAmlCtf.setUpdatedDate(System.currentTimeMillis());
                accountParAmlCtfService.insert(newAccountParAmlCtf);
            }
        }
        for (AccountParAmlCtf accountParAmlCtf : oldAccountParAmlCtf) {
            accountParAmlCtfService.remove(accountParAmlCtf.getId());
        }
    }

    int saveParFile(AccountParFile accountParFile) {
        accountParFile.setUpdatedDate(System.currentTimeMillis());
        if (null == accountParFile.getId()) {
            accountParFile.setId(SerialnoUtils.buildPrimaryKey());
            accountParFile.setCreatedDate(System.currentTimeMillis());
            return accountParFileService.insert(accountParFile);
        } else {
            return accountParFileService.updateByPrimaryKey(accountParFile);
        }
    }

    /**
     * 验证合伙企业账户组合信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePortfolio(Long accountId) throws BusinessException {
        AccountParAccountPortfolio portfolio = new AccountParAccountPortfolio();
        portfolio.setAccountId(accountId);
        if (accountParAccountPortfolioService.findList(portfolio).size() > 0) {
            throw new BusinessException("Only one item of portfolio information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证合伙企业个人信息只能保存一条 （被授权人）
     *
     * @param accountId
     * @param personalType
     * @throws BusinessException
     */
    private void validatePersonalAuthorizedPerson(Long accountId, String personalType) throws BusinessException {
        AccountParPersonal perInfo = new AccountParPersonal();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalType);
        if (accountParPersonalService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one authorized person information can be saved, do not repeat the operation");
        }
    }

    /**
     * 验证合伙企业个人信息只能保存一条 （经营负责人）
     *
     * @param accountId
     * @param personalType
     * @throws BusinessException
     */
    private void validatePersonalOperationPrincipal(Long accountId, String personalType) throws BusinessException {
        AccountParPersonal perInfo = new AccountParPersonal();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalType);
        if (accountParPersonalService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one operating principal information can be saved, do not repeat the operation");
        }
    }

    /**
     * 验证合伙企业个人信息只能保存一条 （经营负责人（引用被授权人/个人股东））
     *
     * @param accountId
     * @param personalTypeTwo 第二种身份
     * @throws BusinessException
     */
    private void validatePersonalOperationPrincipalTwo(Long accountId, String personalTypeTwo) throws BusinessException {
        AccountParPersonal perInfo = new AccountParPersonal();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalTypeTwo);
        if (accountParPersonalService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one operating principal information can be saved, do not repeat the operation");
        }
    }

    /**
     * 验证合伙企业身份信息只能保存一条
     *
     * @param accountId
     * @param coPersonalId
     * @throws BusinessException
     */
    private void validateIdentity(Long accountId, Long coPersonalId) throws BusinessException {
        AccountParIdentity perIdentity = new AccountParIdentity();
        perIdentity.setAccountId(accountId);
        perIdentity.setParPersonalId(coPersonalId);
        List<AccountParIdentity> identityList = accountParIdentityService.findList(perIdentity);
        if (identityList.size() > 0) {
            throw new BusinessException("identity information can only be saved one, do not repeat operation");
        }
    }

    /**
     * 验证合伙企业机构信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateInstitutional(Long accountId) throws BusinessException {
        AccountParInstitutional institutional = new AccountParInstitutional();
        institutional.setAccountId(accountId);
        if (accountParInstitutionalService.findList(institutional).size() > 0) {
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
     * @param accountParSafeQuestion
     * @throws BusinessException
     */
    private void validateAccountParSafeQuestion(AccountParSafeQuestion accountParSafeQuestion) throws BusinessException {
        // 安全问题类型
        Dictionary byValue = dictionaryService.findByValue(accountParSafeQuestion.getSafeQuestion());
        if (null == byValue) {
            throw new BusinessException("Illegal security issues");
        }
    }

    /**
     * 验证合伙企业税收分类只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateTaxClassification(Long accountId) throws BusinessException {
        AccountParTaxClassification parTaxClassification = new AccountParTaxClassification();
        parTaxClassification.setAccountId(accountId);
        if (accountParTaxClassificationService.findList(parTaxClassification).size() > 0) {
            throw new BusinessException("Tax classification can only save one item, do not repeat operation");
        }
    }

    /**
     * 验证合伙企业资产信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateAsset(Long accountId) throws BusinessException {
        AccountParAsset accountParAsset = new AccountParAsset();
        accountParAsset.setAccountId(accountId);
        if (accountParAssetService.findList(accountParAsset).size() > 0) {
            throw new BusinessException("Only one item of asset information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证合伙企业投资目标只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateInvestmentTarget(Long accountId) throws BusinessException {
        AccountParInvestmentTarget investmentTarget = new AccountParInvestmentTarget();
        investmentTarget.setAccountId(accountId);
        if (accountParInvestmentTargetService.findList(investmentTarget).size() > 0) {
            throw new BusinessException("Only one investment goal can be saved. Do not repeat the operation.");
        }
    }

    /**
     * 逻辑判断 验证是否包含某个字符    (投资目标)
     *
     * @param accountParInvestmentTarget
     */
    private void validateInvestTarget(AccountParInvestmentTarget accountParInvestmentTarget) throws BusinessException {
        String investmentTarget = accountParInvestmentTarget.getInvestmentTarget();
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
     * @param accountParTradeExperience
     */
    private void validateKnowledge(AccountParTradeExperience accountParTradeExperience) throws BusinessException {
        String ids = accountParTradeExperience.getKnowledgeLevel();
        if (ids.equalsIgnoreCase(AccountConsts.KNOWLEDGELEVELISZERO) || ids.equalsIgnoreCase(AccountConsts.LIMITEDKNOWLEDGELEVEL)) {
            // 知识水平水平不够
            throw new BusinessException("The level of knowledge is not enough, it needs a good level of knowledge above");
        }
    }

    /**
     * 验证合伙企业WBen合约只能保存一条
     *
     * @param accountId
     * @param type
     * @throws BusinessException
     */
    private void validateWbenContract(Long accountId, String type) throws BusinessException {
        AccountParWbenContract accountParWbenContract = new AccountParWbenContract();
        accountParWbenContract.setAccountId(accountId);
        accountParWbenContract.setType(type);
        if (accountParWbenContractService.findList(accountParWbenContract).size() > 0) {
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
        AccountParCrsEntity crsEntity = new AccountParCrsEntity();
        crsEntity.setAccountId(accountId);
        if (accountParCrsEntityService.findList(crsEntity).size() > 0) {
            throw new BusinessException("CRS Common Reporting Standard (CRS) Entity Self-Certification Form (CRS-E) can only save one item. Do not repeat operation");
        }
    }

    /**
     * 验证合伙企业CRS控权人自我证明表格只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateCrsControl(Long accountId, Long parCrsEntityContrId) throws BusinessException {
        AccountParCrsControl crsControl = new AccountParCrsControl();
        crsControl.setAccountId(accountId);
        crsControl.setParCrsEntityContrId(parCrsEntityContrId);
        if (accountParCrsControlService.findList(crsControl).size() > 0) {
            throw new BusinessException("Only one CRS controller self-certification form can be saved. Please do not repeat the operation");
        }
    }

    /**
     * 验证合伙企业一体化投资管理账户功能只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateInvestAccount(Long accountId) throws BusinessException {
        AccountParInvestmentAccount investmentAccount = new AccountParInvestmentAccount();
        investmentAccount.setAccountId(accountId);
        if (accountParInvestmentAccountService.findList(investmentAccount).size() > 0) {
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
    private void returnUploadFileType(List<UploadFileType> uploadFileTypeList, List<AccountParPersonal> accountCoPersonalList, List<UploadFileType> list) {
        for (UploadFileType uploadFileType : uploadFileTypeList) {
            for (AccountParPersonal accountParPersonal : accountCoPersonalList) {
                UploadFileType uploadFile = new UploadFileType();
                // 个人股东的名字/法人股东名字
                String middleName = null == accountParPersonal.getMiddleName() ? "" : accountParPersonal.getMiddleName();
                String suffix = null == accountParPersonal.getSuffix() ? "" : accountParPersonal.getSuffix();
                String name = accountParPersonal.getLastName() + " " + middleName + " " + accountParPersonal.getFirstName() + " " + suffix;
                String format = String.format(uploadFileType.getContent(), name);
                uploadFile.setId(uploadFileType.getId());
                // 个人股东id/法人股东id
                uploadFile.setCoPersonalId(accountParPersonal.getId());
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
