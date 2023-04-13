package com.fintech.gams.account.service;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.AccountPersonalSaveOneModel;
import com.fintech.gams.account.model.AccountPersonalSaveTwoModel;
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

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @Author: hcj
 * @Date: 2019/8/31 14:32
 * @Description  个人开户 服务实现类
 */
@Service
public class AccountPersonalServiceImpl implements AccountPersonalService {

    @Autowired(required = false)
    protected AccountPerInfoService accountPerInfoService;
    @Autowired(required = false)
    protected AccountPerContactService accountPerContactService;
    @Autowired(required = false)
    protected AccountPerIdentityService accountPerIdentityService;
    @Autowired(required = false)
    protected AccountPerTaxesService accountPerTaxesService;
    @Autowired(required = false)
    protected AccountPerAddressService accountPerAddressService;
    @Autowired(required = false)
    protected AccountPerWealthSourceService accountPerWealthSourceService;
    @Autowired(required = false)
    protected AccountPerSafeQuestionService accountPerSafeQuestionService;
    @Autowired(required = false)
    protected AccountPerEmploymentService accountPerEmploymentService;
    @Autowired(required = false)
    protected AccountPerPortfolioService accountPerPortfolioService;
    @Autowired(required = false)
    protected AccountPerAssetService accountPerAssetService;
    @Autowired(required = false)
    protected AccountPerRegulatoryService accountPerRegulatoryService;
    @Autowired(required = false)
    protected AccountPerInvestTargetService accountPerInvestTargetService;
    @Autowired(required = false)
    protected AccountPerTradeExpService accountPerTradeExpService;
    @Autowired(required = false)
    protected AccountPerWbenContractService accountPerWbenContractService;
    @Autowired(required = false)
    protected AccountPerAgreementService accountPerAgreementService;
    @Autowired(required = false)
    protected AccountPerInvestAccountService accountPerInvestAccountService;
    @Autowired(required = false)
    protected AccountService accountService;
    @Autowired(required = false)
    protected AccountPerCrsService accountPerCrsService;
    @Autowired(required = false)
    protected AccountPerFileService accountPerFileService;
    @Autowired(required = false)
    protected AccountCommonAgreementService accountCommonAgreementService;
    @Autowired(required = false)
    protected AccountCommonAgreementTypeService accountCommonAgreementTypeService;
    @Autowired(required = false)
    protected DictionaryService dictionaryService;
    @Autowired(required = false)
    protected AccountPerAmlCtfService accountPerAmlCtfService;
    @Autowired(required = false)
    protected AccountCommonFileService accountCommonFileService;

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
    public void saveOne(AccountPersonalSaveOneModel accountPersonalSaveOneModel) throws BusinessException {
        validateOpenAccountFinish(accountPersonalSaveOneModel.getAccountPerInfo().getAccountId());
        savePerInfo(accountPersonalSaveOneModel.getAccountPerInfo());
        savePerAddress(accountPersonalSaveOneModel.getAccountPerAddressList());
        savePerContact(accountPersonalSaveOneModel.getAccountPerContactList());
        savePerIdentity(accountPersonalSaveOneModel.getAccountPerIdentity());
        saveAccountPerTaxes(accountPersonalSaveOneModel.getAccountPerTaxesList());
        saveAccountPerWealthSource(accountPersonalSaveOneModel.getAccountPerWealthSourceList());
        saveAccountPerSafeQuestion(accountPersonalSaveOneModel.getAccountPerSafeQuestionList());
        savePerEmployment(accountPersonalSaveOneModel.getAccountPerEmployment());
        savePerPortfolio(accountPersonalSaveOneModel.getAccountPerPortfolio());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTwo(AccountPersonalSaveTwoModel accountPersonalSaveTwoModel) throws BusinessException {
        validateOpenAccountFinish(accountPersonalSaveTwoModel.getAccountPerAsset().getAccountId());
        savePerAsset(accountPersonalSaveTwoModel.getAccountPerAsset());
        savePerRegulatory(accountPersonalSaveTwoModel.getAccountPerRegulatories());
        savePerInvestTarget(accountPersonalSaveTwoModel.getAccountPerInvestTargets());
        saveAccountPerTradeExp(accountPersonalSaveTwoModel.getAccountPerTradeExpList());
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveThree(AccountPerWbenContract accountPerWbenContract) throws BusinessException {
        validateOpenAccountFinish(accountPerWbenContract.getAccountId());
        savePerWbenContract(accountPerWbenContract);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFour(List<AccountPerAgreement> accountPerAgreementList) throws BusinessException {
        validateOpenAccountFinish(accountPerAgreementList.get(0).getAccountId());
        saveAccountPerAgreement(accountPerAgreementList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveFive(AccountPerInvestAccount accountPerInvestAccount) throws BusinessException {
        validateOpenAccountFinish(accountPerInvestAccount.getAccountId());
        savePerInvestAccount(accountPerInvestAccount);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveSeven(AccountPerCrs accountPerCrs) throws BusinessException {
        validateOpenAccountFinish(accountPerCrs.getAccountId());
        savePerCrs(accountPerCrs);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveNine(List<AccountPerAmlCtf> accountPerAmlCtfList) throws BusinessException {
        validateOpenAccountFinish(accountPerAmlCtfList.get(0).getAccountId());
        savePerAmlCtf(accountPerAmlCtfList);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveTen(AccountPerFile accountPerFile) throws BusinessException {
        validateOpenAccountFinish(accountPerFile.getAccountId());
        savePerFile(accountPerFile);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public List<AccountCommonAgreement> findByAgreement(Long accountId) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        Account account = accountService.selectByPrimaryKey(accountId);
        List<AccountCommonAgreementType> agreementTypes = new LinkedList<>();
        //获取 个人账户投资组合 信息
        AccountPerPortfolio accountPerPortfolio = new AccountPerPortfolio();
        accountPerPortfolio.setAccountId(accountId);
        List<AccountPerPortfolio> accountPerPortfolios = accountPerPortfolioService.findList(accountPerPortfolio);
        //根据账户投资组合类型去获取披露协议
        AccountCommonAgreementType accountCommonAgreementType = new AccountCommonAgreementType();
        //账户投资组合类型
        accountCommonAgreementType.setAccountPortfolioType(accountPerPortfolios.get(0).getAccountPortfolioType());
        //账户类型
        accountCommonAgreementType.setAccountType(account.getAccountType());
        //根据交易经验的国家和类型去获取披露协议
        AccountPerTradeExp accountPerTradeExp = new AccountPerTradeExp();
        accountPerTradeExp.setAccountId(accountId);
        List<AccountPerTradeExp> accountPerTradeExps = accountPerTradeExpService.findList(accountPerTradeExp);
        for (AccountPerTradeExp perTradeExp : accountPerTradeExps) {
            accountCommonAgreementType.setAgreementType(perTradeExp.getTansactionType());
            List<String> locationStrsList = Arrays.asList(perTradeExp.getTradingLocation().split(","));
            agreementTypes = findByTradingLocation(agreementTypes, locationStrsList, accountCommonAgreementType, perTradeExp.getTansactionType());
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
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = true)
    public Map<String, Object> getFileUploadType(Long accountId, String lang) throws BusinessException {
        if (null == accountId) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        // 根据个人的地址国家和美国所属国税收地址查询
        AccountPerAddress accountPerAddress = new AccountPerAddress();
        accountPerAddress.setAccountId(accountId);
        List<AccountPerAddress> perAddressList = accountPerAddressService.findList(accountPerAddress);
        // 个人地址的国家
        String country = perAddressList.get(0).getCountry();
        // 美国所属国税收地址（个人Wben表格）
        AccountPerWbenContract accountPerWbenContract = new AccountPerWbenContract();
        accountPerWbenContract.setAccountId(accountId);
        List<AccountPerWbenContract> perWbenContractList = accountPerWbenContractService.findList(accountPerWbenContract);
        String usaCountryAddress = perWbenContractList.get(0).getUsaCountryAgreement();
        Map<String, Object> map = new HashMap<>(16);
        // 字典数据 文件身份证类型
        final String dictCode = "fileIdCardType";
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        // 共同的文件
        AccountCommonFile commonFile = new AccountCommonFile();
        if (country.equals(usaCountryAddress)) {
            // 两个国家相等 就显示身份证正面，住址证明，身份证反面 根据字典键值
            final String[] dictCodeArr = {"identityAndBirthdayCertificate", "proofOfAddress"};
            commonFile.setKind(1L);
            commonFile.setLang(lang);
            map.put("accountCommonFileList", accountCommonFileService.findList(commonFile));
            map.put("fileIdCardType", dictionaryService.findByCodeArr(dictCodeArr, lang));
            return map;
        }
        // 不相等
        commonFile.setAccountType("personal");
        commonFile.setLang(lang);
        map.put("accountCommonFileList", accountCommonFileService.findList(commonFile));
        map.put("fileIdCardType", dictionaryService.findByCode(dictCode, lang));
        return map;
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
     * 插入/更新个人信息
     *
     * @param accountPerInfo
     * @return
     */
    int savePerInfo(AccountPerInfo accountPerInfo) {
        accountPerInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerInfo.getId()) {
            accountPerInfo.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerInfo.setId(SerialnoUtils.buildPrimaryKey());
            validatePersonalInfo(accountPerInfo.getAccountId(),accountPerInfo.getPersonalType());
            return accountPerInfoService.insert(accountPerInfo);
        } else {
            return accountPerInfoService.updateByPrimaryKey(accountPerInfo);
        }
    }

    /**
     * 插入/更新个人身份信息
     *
     * @param accountPerIdentity
     * @return
     */
    int savePerIdentity(AccountPerIdentity accountPerIdentity) {
        accountPerIdentity.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerIdentity.getId()) {
            accountPerIdentity.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerIdentity.setId(SerialnoUtils.buildPrimaryKey());
            validateIdentity(accountPerIdentity.getAccountId());
            return accountPerIdentityService.insert(accountPerIdentity);
        } else {
            return accountPerIdentityService.updateByPrimaryKey(accountPerIdentity);
        }
    }

    /**
     * 插入/更新 个人雇佣 信息
     *
     * @param accountPerEmployment
     * @return
     */
    int savePerEmployment(AccountPerEmployment accountPerEmployment) {
        accountPerEmployment.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerEmployment.getId()) {
            accountPerEmployment.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerEmployment.setId(SerialnoUtils.buildPrimaryKey());
            validateEmployment(accountPerEmployment.getAccountId());
            return accountPerEmploymentService.insert(accountPerEmployment);
        } else {
            return accountPerEmploymentService.updateByPrimaryKey(accountPerEmployment);
        }
    }

    /**
     * 插入/更新 个人账户投资组合
     *
     * @param accountPerPortfolio
     * @return
     */
    int savePerPortfolio(AccountPerPortfolio accountPerPortfolio) {
        accountPerPortfolio.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerPortfolio.getId()) {
            accountPerPortfolio.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerPortfolio.setId(SerialnoUtils.buildPrimaryKey());
            validatePortfolio(accountPerPortfolio.getAccountId());
            return accountPerPortfolioService.insert(accountPerPortfolio);
        } else {
            return accountPerPortfolioService.updateByPrimaryKey(accountPerPortfolio);
        }
    }

    /**
     * 插入/更新 个人资产信息
     *
     * @param accountPerAsset
     * @return
     */
    int savePerAsset(AccountPerAsset accountPerAsset) {
        accountPerAsset.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerAsset.getId()) {
            accountPerAsset.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerAsset.setId(SerialnoUtils.buildPrimaryKey());
            validatePerAsset(accountPerAsset.getAccountId());
            return accountPerAssetService.insert(accountPerAsset);
        } else {
            return accountPerAssetService.updateByPrimaryKey(accountPerAsset);
        }
    }

    /**
     * 插入/更新 个人账户规管信息
     *
     * @param accountPerRegulatory
     * @return
     */
    int savePerRegulatory(AccountPerRegulatory accountPerRegulatory) {
        accountPerRegulatory.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerRegulatory.getId()) {
            accountPerRegulatory.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerRegulatory.setId(SerialnoUtils.buildPrimaryKey());
            validatePerRegulatory(accountPerRegulatory.getAccountId());
            return accountPerRegulatoryService.insert(accountPerRegulatory);
        } else {
            return accountPerRegulatoryService.updateByPrimaryKey(accountPerRegulatory);
        }
    }

    /**
     * 插入/更新 个人投资目标
     *
     * @param accountPerInvestTarget
     * @return
     */
    int savePerInvestTarget(AccountPerInvestTarget accountPerInvestTarget) {
        accountPerInvestTarget.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerInvestTarget.getId()) {
            accountPerInvestTarget.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerInvestTarget.setId(SerialnoUtils.buildPrimaryKey());
            validateInvestTarget(accountPerInvestTarget);
            validatePerInvestTarget(accountPerInvestTarget.getAccountId());
            return accountPerInvestTargetService.insert(accountPerInvestTarget);
        } else {
            validateInvestTarget(accountPerInvestTarget);
            return accountPerInvestTargetService.updateByPrimaryKey(accountPerInvestTarget);
        }
    }

    /**
     * 插入/更新 个人WBen合约
     *
     * @param accountPerWbenContract
     * @return
     */
    int savePerWbenContract(AccountPerWbenContract accountPerWbenContract) {
        accountPerWbenContract.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        accountPerWbenContract.setSignatureTime(System.currentTimeMillis());
        if (null == accountPerWbenContract.getId()) {
            accountPerWbenContract.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
            accountPerWbenContract.setId(SerialnoUtils.buildPrimaryKey());
            validatePerWbenContract(accountPerWbenContract.getAccountId());
            return accountPerWbenContractService.insert(accountPerWbenContract);
        } else {
            return accountPerWbenContractService.updateByPrimaryKey(accountPerWbenContract);
        }
    }

    /**
     * 插入/更新 个人协议纰漏
     *
     * @param accountPerAgreementList
     */
    void saveAccountPerAgreement(List<AccountPerAgreement> accountPerAgreementList) {
        List<AccountPerAgreement> insertList = new LinkedList<>();
        List<AccountPerAgreement> updateList = new LinkedList<>();
        AccountPerAgreement perAgreement = new AccountPerAgreement();
        perAgreement.setAccountId(accountPerAgreementList.get(0).getAccountId());
        List<AccountPerAgreement> oldAccountPerAgreementList = accountPerAgreementService.findList(perAgreement);
        for (AccountPerAgreement accountPerAgreement : accountPerAgreementList) {
            int i = 0;
            for (int j = 0; j < oldAccountPerAgreementList.size(); j++) {
                if (accountPerAgreement.getId() != null && accountPerAgreement.getId().equals(oldAccountPerAgreementList.get(j).getId())) {
                    i = 1;
                    oldAccountPerAgreementList.remove(j);
                    updateList.add(accountPerAgreement);
                }
            }
            if (i == 0) {
                //插入数据
                insertList.add(accountPerAgreement);
            }
        }
        //插入数据
        for (AccountPerAgreement accountPerAgreement : insertList) {
            accountPerAgreement.setId(SerialnoUtils.buildPrimaryKey());
            accountPerAgreement.setSignatureTime(System.currentTimeMillis());
            accountPerAgreement.setCreatedDate(System.currentTimeMillis());
            accountPerAgreement.setUpdatedDate(System.currentTimeMillis());
            accountPerAgreementService.insert(accountPerAgreement);
        }
        if (updateList.size() > 0) {
            for (AccountPerAgreement accountPerAgreement : updateList) {
                accountPerAgreement.setSignatureTime(System.currentTimeMillis());
                accountPerAgreement.setUpdatedDate(System.currentTimeMillis());
            }
            accountPerAgreementService.updateBatch(updateList);
        }
        for (AccountPerAgreement accountPerAgreement : oldAccountPerAgreementList) {
            accountPerAgreementService.remove(accountPerAgreement.getId());
        }
    }

    /**
     * 插入/更新 个人一体化投资管理账户功能
     *
     * @param accountPerInvestAccount
     * @return
     */
    int savePerInvestAccount(AccountPerInvestAccount accountPerInvestAccount) {
        accountPerInvestAccount.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerInvestAccount.getId()) {
            accountPerInvestAccount.setCreatedDate(System.currentTimeMillis());
            accountPerInvestAccount.setId(SerialnoUtils.buildPrimaryKey());
            validatePerInvestAccount(accountPerInvestAccount.getAccountId());
            return accountPerInvestAccountService.insert(accountPerInvestAccount);
        } else {
            return accountPerInvestAccountService.updateByPrimaryKey(accountPerInvestAccount);
        }
    }

    /**
     * 插入/更新 个人CRS
     *
     * @param accountPerCrs
     * @return
     */
    int savePerCrs(AccountPerCrs accountPerCrs) {
        accountPerCrs.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        accountPerCrs.setSignatureDate(System.currentTimeMillis());
        if (null == accountPerCrs.getId()) {
            accountPerCrs.setCreatedDate(System.currentTimeMillis());
            accountPerCrs.setId(SerialnoUtils.buildPrimaryKey());
            return accountPerCrsService.insert(accountPerCrs);
        } else {
            return accountPerCrsService.updateByPrimaryKey(accountPerCrs);
        }
    }

    /**
     * 插入/更新  个人AML/CTF 风险问卷
     *
     * @param accountPerAmlCtfList
     */
    void savePerAmlCtf(List<AccountPerAmlCtf> accountPerAmlCtfList) {
        AccountPerAmlCtf perAmlCtf = new AccountPerAmlCtf();
        perAmlCtf.setAccountId(accountPerAmlCtfList.get(0).getAccountId());
        List<AccountPerAmlCtf> oldAccountPerAmlCtf = accountPerAmlCtfService.findList(perAmlCtf);
        for (AccountPerAmlCtf newAccountPerAmlCtf : accountPerAmlCtfList) {
            int i = 0;
            for (int j = 0; j < oldAccountPerAmlCtf.size(); j++) {
                if (newAccountPerAmlCtf.getId() != null && newAccountPerAmlCtf.getId().equals(oldAccountPerAmlCtf.get(j).getId())) {
                    i = 1;
                    oldAccountPerAmlCtf.remove(j);
                    newAccountPerAmlCtf.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    accountPerAmlCtfService.updateByPrimaryKey(newAccountPerAmlCtf);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountPerAmlCtf.setId(SerialnoUtils.buildPrimaryKey());
                newAccountPerAmlCtf.setCreatedDate(System.currentTimeMillis());
                newAccountPerAmlCtf.setUpdatedDate(System.currentTimeMillis());
                accountPerAmlCtfService.insert(newAccountPerAmlCtf);
            }
        }
        for (AccountPerAmlCtf accountPerAmlCtf : oldAccountPerAmlCtf) {
            accountPerAmlCtfService.remove(accountPerAmlCtf.getId());
        }
    }

    /**
     * 插入/更新 个人文件
     *
     * @param accountPerFile
     * @return
     */
    int savePerFile(AccountPerFile accountPerFile) {
        accountPerFile.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        if (null == accountPerFile.getId()) {
            accountPerFile.setCreatedDate(System.currentTimeMillis());
            accountPerFile.setId(SerialnoUtils.buildPrimaryKey());
            return accountPerFileService.insert(accountPerFile);
        } else {
            return accountPerFileService.updateByPrimaryKey(accountPerFile);
        }
    }

    /**
     * 插入/更新 个人地址
     *
     * @param accountPerAddressList
     */
    void savePerAddress(List<AccountPerAddress> accountPerAddressList) {
        AccountPerAddress perAddress = new AccountPerAddress();
        perAddress.setAccountId(accountPerAddressList.get(0).getAccountId());
        List<AccountPerAddress> addressesInfoList = accountPerAddressService.findList(perAddress);
        for (AccountPerAddress newAddressesInfo : accountPerAddressList) {
            int i = 0;
            for (int j = 0; j < addressesInfoList.size(); j++) {
                if (newAddressesInfo.getId() != null && newAddressesInfo.getId().equals(addressesInfoList.get(j).getId())) {
                    i = 1;
                    addressesInfoList.remove(j);
                    newAddressesInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    //更新数据
                    accountPerAddressService.updateByPrimaryKey(newAddressesInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newAddressesInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newAddressesInfo.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newAddressesInfo.setId(SerialnoUtils.buildPrimaryKey());
                accountPerAddressService.insert(newAddressesInfo);
            }
        }
        for (AccountPerAddress addressesInfo : addressesInfoList) {
            //删除数据
            accountPerAddressService.remove(addressesInfo.getId());
        }
    }

    /**
     * 插入/更新 个人联系方式
     *
     * @param accountPerContactList
     */
    void savePerContact(List<AccountPerContact> accountPerContactList) {
        AccountPerContact perContact = new AccountPerContact();
        perContact.setAccountId(accountPerContactList.get(0).getAccountId());
        List<AccountPerContact> contactsInfoList = accountPerContactService.findList(perContact);
        for (AccountPerContact newContactsInfo : accountPerContactList) {
            int i = 0;
            for (int j = 0; j < contactsInfoList.size(); j++) {
                if (newContactsInfo.getId() != null && newContactsInfo.getId().equals(contactsInfoList.get(j).getId())) {
                    i = 1;
                    contactsInfoList.remove(j);
                    newContactsInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    //更新数据
                    accountPerContactService.updateByPrimaryKey(newContactsInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newContactsInfo.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newContactsInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newContactsInfo.setId(SerialnoUtils.buildPrimaryKey());
                accountPerContactService.insert(newContactsInfo);
            }
        }
        //删除数据
        for (AccountPerContact accountPerContact : contactsInfoList) {
            accountPerContactService.remove(accountPerContact.getId());
        }
    }


    /**
     * 插入/更新 个人税务居住地
     *
     * @param accountPerTaxesList
     */
    void saveAccountPerTaxes(List<AccountPerTaxes> accountPerTaxesList) {
        AccountPerTaxes perTaxes = new AccountPerTaxes();
        perTaxes.setAccountId(accountPerTaxesList.get(0).getAccountId());
        List<AccountPerTaxes> taxesInfoList = accountPerTaxesService.findList(perTaxes);
        for (AccountPerTaxes newTaxesInfo : accountPerTaxesList) {
            int i = 0;
            for (int j = 0; j < taxesInfoList.size(); j++) {
                if (newTaxesInfo.getId() != null && newTaxesInfo.getId().equals(taxesInfoList.get(j).getId())) {
                    i = 1;
                    taxesInfoList.remove(j);
                    newTaxesInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    //更新数据
                    accountPerTaxesService.updateByPrimaryKey(newTaxesInfo);
                }
            }
            if (i == 0) {
                //插入数据
                newTaxesInfo.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newTaxesInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newTaxesInfo.setId(SerialnoUtils.buildPrimaryKey());
                accountPerTaxesService.insert(newTaxesInfo);
            }
        }
        //删除数据
        for (AccountPerTaxes accountPerTaxes : taxesInfoList) {
            accountPerTaxesService.remove(accountPerTaxes.getId());
        }
    }

    /**
     * 插入/更新  个人财富来源
     *
     * @param accountPerWealthSourceList
     */
    void saveAccountPerWealthSource(List<AccountPerWealthSource> accountPerWealthSourceList) {
        AccountPerWealthSource perWealthSource = new AccountPerWealthSource();
        perWealthSource.setAccountId(accountPerWealthSourceList.get(0).getAccountId());
        List<AccountPerWealthSource> wealthSourceByAid = accountPerWealthSourceService.findList(perWealthSource);
        for (AccountPerWealthSource newWealthSourceInfo : accountPerWealthSourceList) {
            int i = 0;
            for (int j = 0; j < wealthSourceByAid.size(); j++) {
                if (newWealthSourceInfo.getId() != null && newWealthSourceInfo.getId().equals(wealthSourceByAid.get(j).getId())) {
                    i = 1;
                    wealthSourceByAid.remove(j);
                    newWealthSourceInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    //更新
                    accountPerWealthSourceService.updateByPrimaryKey(newWealthSourceInfo);
                }
            }
            if (i == 0) {
                //插入
                newWealthSourceInfo.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newWealthSourceInfo.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                newWealthSourceInfo.setId(SerialnoUtils.buildPrimaryKey());
                accountPerWealthSourceService.insert(newWealthSourceInfo);
            }
        }
        //删除
        for (AccountPerWealthSource wealthSourceInfo : wealthSourceByAid) {
            accountPerWealthSourceService.remove(wealthSourceInfo.getId());
        }
    }

    /**
     * 插入/更新 个人交易经验信息
     *
     * @param accountPerTradeExpList
     */
    void saveAccountPerTradeExp(List<AccountPerTradeExp> accountPerTradeExpList) {
        AccountPerTradeExp perTradeExp = new AccountPerTradeExp();
        perTradeExp.setAccountId(accountPerTradeExpList.get(0).getAccountId());
        List<AccountPerTradeExp> oldAccountPerTradeExpsList = accountPerTradeExpService.findList(perTradeExp);
        for (AccountPerTradeExp accountPerTradeExp : accountPerTradeExpList) {
            int i = 0;
            for (int j = 0; j < oldAccountPerTradeExpsList.size(); j++) {
                if (accountPerTradeExp.getId() != null && accountPerTradeExp.getId().equals(oldAccountPerTradeExpsList.get(j).getId())) {
                    i = 1;
                    oldAccountPerTradeExpsList.remove(j);
                    accountPerTradeExp.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                    validateKnowledge(accountPerTradeExp);
                    accountPerTradeExpService.updateByPrimaryKey(accountPerTradeExp);
                }
            }
            if (i == 0) {
                //插入数据
                accountPerTradeExp.setUpdatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                accountPerTradeExp.setCreatedDate(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
                accountPerTradeExp.setId(SerialnoUtils.buildPrimaryKey());
                validateKnowledge(accountPerTradeExp);
                accountPerTradeExpService.insert(accountPerTradeExp);
            }
        }
        for (AccountPerTradeExp accountPerTradeExp : oldAccountPerTradeExpsList) {
            accountPerTradeExpService.remove(accountPerTradeExp.getId());
        }
    }

    /**
     * 插入/更新 个人安全问题
     *
     * @param accountPerSafeQuestionList
     */
    void saveAccountPerSafeQuestion(List<AccountPerSafeQuestion> accountPerSafeQuestionList) {
        AccountPerSafeQuestion accountPerSafeQuestion = new AccountPerSafeQuestion();
        accountPerSafeQuestion.setAccountId(accountPerSafeQuestionList.get(0).getAccountId());
        List<AccountPerSafeQuestion> oldAccountPerSafeQuestion = accountPerSafeQuestionService.findList(accountPerSafeQuestion);
        for (AccountPerSafeQuestion newAccountPerSafeQuestion : accountPerSafeQuestionList) {
            int i = 0;
            for (int j = 0; j < oldAccountPerSafeQuestion.size(); j++) {
                if (newAccountPerSafeQuestion.getId() != null && newAccountPerSafeQuestion.getId().equals(oldAccountPerSafeQuestion.get(j).getId())) {
                    i = 1;
                    oldAccountPerSafeQuestion.remove(j);
                    newAccountPerSafeQuestion.setUpdatedDate(System.currentTimeMillis());
                    //更新数据
                    validateAccountPerSafeQuestion(newAccountPerSafeQuestion);
                    accountPerSafeQuestionService.updateByPrimaryKey(newAccountPerSafeQuestion);
                }
            }
            if (i == 0) {
                //插入数据
                newAccountPerSafeQuestion.setId(SerialnoUtils.buildPrimaryKey());
                newAccountPerSafeQuestion.setCreatedDate(System.currentTimeMillis());
                newAccountPerSafeQuestion.setUpdatedDate(System.currentTimeMillis());
                validateAccountPerSafeQuestion(newAccountPerSafeQuestion);
                accountPerSafeQuestionService.insert(newAccountPerSafeQuestion);
            }
        }
        for (AccountPerSafeQuestion perSafeQuestion : oldAccountPerSafeQuestion) {
            accountPerSafeQuestionService.remove(perSafeQuestion.getId());
        }
    }

    /**
     * 验证个人身份信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateIdentity(Long accountId) throws BusinessException {
        AccountPerIdentity perIdentity = new AccountPerIdentity();
        perIdentity.setAccountId(accountId);
        List<AccountPerIdentity> identityList = accountPerIdentityService.findList(perIdentity);
        if (identityList.size() > 0) {
            throw new BusinessException("Personal identity information can only be saved one, do not repeat operation");
        }
    }

    /**
     * 验证雇佣信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validateEmployment(Long accountId) throws BusinessException {
        AccountPerEmployment employment = new AccountPerEmployment();
        employment.setAccountId(accountId);
        if (accountPerEmploymentService.findList(employment).size() > 0) {
            throw new BusinessException("Only one piece of employment information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证投资组合信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePortfolio(Long accountId) throws BusinessException {
        AccountPerPortfolio portfolio = new AccountPerPortfolio();
        portfolio.setAccountId(accountId);
        if (accountPerPortfolioService.findList(portfolio).size() > 0) {
            throw new BusinessException("Only one item of portfolio information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证个人信息只能保存一条
     *
     * @param accountId
     * @param personalType
     * @throws BusinessException
     */
    private void validatePersonalInfo(Long accountId, String personalType) throws BusinessException {
        AccountPerInfo perInfo = new AccountPerInfo();
        perInfo.setAccountId(accountId);
        perInfo.setPersonalType(personalType);
        if (accountPerInfoService.findList(perInfo).size() > 0) {
            throw new BusinessException("Only one item of personal information can be saved, please do not repeat the operation");
        }
    }

    /**
     * 验证安全问题避免自定义（需要获取字典表）
     *
     * @param accountPerSafeQuestion
     * @throws BusinessException
     */
    private void validateAccountPerSafeQuestion(AccountPerSafeQuestion accountPerSafeQuestion) throws BusinessException {
        Dictionary byValue = dictionaryService.findByValue(accountPerSafeQuestion.getSafeQuestion());
        if (null == byValue) {
            throw new BusinessException("Illegal security issues");
        }
    }

    /**
     * 验证个人资产信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePerAsset(Long accountId) throws BusinessException {
        AccountPerAsset perAsset = new AccountPerAsset();
        perAsset.setAccountId(accountId);
        if (accountPerAssetService.findList(perAsset).size() > 0) {
            throw new BusinessException("Only one item of asset information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证个人账户规管信息只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePerRegulatory(Long accountId) throws BusinessException {
        AccountPerRegulatory regulatory = new AccountPerRegulatory();
        regulatory.setAccountId(accountId);
        if (accountPerRegulatoryService.findList(regulatory).size() > 0) {
            throw new BusinessException("Only one piece of regulatory information can be saved. Do not repeat the operation");
        }
    }

    /**
     * 验证个人投资目标只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePerInvestTarget(Long accountId) throws BusinessException {
        AccountPerInvestTarget investTarget = new AccountPerInvestTarget();
        investTarget.setAccountId(accountId);
        if (accountPerInvestTargetService.findList(investTarget).size() > 0) {
            throw new BusinessException("Only one investment goal can be saved. Do not repeat the operation.");
        }
    }

    /**
     * 验证个人WBen合约只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePerWbenContract(Long accountId) throws BusinessException {
        AccountPerWbenContract accountPerWbenContract = new AccountPerWbenContract();
        accountPerWbenContract.setAccountId(accountId);
        if (accountPerWbenContractService.findList(accountPerWbenContract).size() > 0) {
            throw new BusinessException("W8 contract can only be saved one, do not repeat operation");
        }
    }

    /**
     * 验证个人一体化投资管理账户功能只能保存一条
     *
     * @param accountId
     * @throws BusinessException
     */
    private void validatePerInvestAccount(Long accountId) throws BusinessException {
        AccountPerInvestAccount investAccount = new AccountPerInvestAccount();
        investAccount.setAccountId(accountId);
        if (accountPerInvestAccountService.findList(investAccount).size() > 0) {
            throw new BusinessException("Integrative Investment Management Account can only be saved one, do not repeat operation");
        }
    }


    /**
     * 逻辑判断 验证是否包含某个字符    (投资目标)
     *
     * @param accountPerInvestTarget
     */
    private void validateInvestTarget(AccountPerInvestTarget accountPerInvestTarget) throws BusinessException {
        String investmentTarget = accountPerInvestTarget.getInvestmentTarget();
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
     * @param accountPerTradeExp
     */
    private void validateKnowledge(AccountPerTradeExp accountPerTradeExp) throws BusinessException {
        String ids = accountPerTradeExp.getKnowledgeLevel();
        if (ids.equalsIgnoreCase(AccountConsts.KNOWLEDGELEVELISZERO) || ids.equalsIgnoreCase(AccountConsts.LIMITEDKNOWLEDGELEVEL)) {
            // 知识水平水平不够
            throw new BusinessException("The level of knowledge is not enough, it needs a good level of knowledge above");
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
}
