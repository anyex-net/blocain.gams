package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.*;
import com.fintech.gams.exception.BusinessException;

import java.util.List;


/**
 * @Author: hcj
 * @Date: 2019/8/27 20:28
 * @Description
 */
public interface AccountCompanyService {


    /**
     * 根据id更新注册开户状态
     *
     * @param accountId
     * @param registerStatus
     * @return
     * @throws BusinessException
     */
    int updateAccountStatus(Long accountId, int registerStatus) throws BusinessException;

    /**
     * 公司开户第一页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveOne(CompanyInfoModel model) throws BusinessException;

    /**
     * 公司开户第二页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveTwo(CompanyAuthorizedModel model) throws BusinessException;

    /**
     * 公司开户第三页插入/更新
     *
     * @param accountCoTaxClassification
     * @throws BusinessException
     */
    void saveThree(AccountCoTaxClassification accountCoTaxClassification) throws BusinessException;

    /**
     * 公司开户第四页插入/更新
     *
     * @param accountCoRegulatoryList
     * @throws BusinessException
     */
    void saveFour(List<AccountCoRegulatory> accountCoRegulatoryList) throws BusinessException;

    /**
     * 公司开户第七页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveSeven(CompanyAssetModel model) throws BusinessException;

    /**
     * 公司开户第八页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveEight(CompanyRegisteredOwnerModel model) throws BusinessException;

    /**
     * 删除公司开户第八页通过公司个人信息的id
     *
     * @param id
     * @throws BusinessException
     */
    void removeEight(Long id) throws BusinessException;

    /**
     * 公司开户第九页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveNine(CompanyBeneficiaryOwnersModel model) throws BusinessException;

    /**
     * 删除公司开户第九页通过公司个人信息的id
     *
     * @param id
     * @throws BusinessException
     */
    void removeNine(Long id) throws BusinessException;

    /**
     * 公司开户第十页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveTen(CompanySecurityOfficerModel model) throws BusinessException;

    /**
     * 公司开户第十一页插入/更新
     *
     * @param accountCoWbenContract
     * @throws BusinessException
     */
    void saveEleven(AccountCoWbenContract accountCoWbenContract) throws BusinessException;

    /**
     * 公司开户第十二页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveTwelve(CompanyCrsEntityContrModel model) throws BusinessException;

    /**
     * 公司开户第十三页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveThirteen(CompanyCrsControlModel model) throws BusinessException;

    /**
     * 公司开户第十四页插入/更新
     *
     * @param accountCoAgreementList
     * @throws BusinessException
     */
    void saveFourteen(List<AccountCoAgreement> accountCoAgreementList) throws BusinessException;

    /**
     * 公司开户第十五页插入/更新
     *
     * @param accountCoInvestmentAccount
     * @throws BusinessException
     */
    void saveFifteen(AccountCoInvestmentAccount accountCoInvestmentAccount) throws BusinessException;

    /**
     * 公司开户第十八页插入/更新
     *
     * @param accountCoAmlCtfList
     * @throws BusinessException
     */
    void saveEighteen(List<AccountCoAmlCtf> accountCoAmlCtfList) throws BusinessException;

    /**
     * 公司开户第十九页插入/更新
     *
     * @param accountCoFile
     * @throws BusinessException
     */
    void saveNineteen(AccountCoFile accountCoFile) throws BusinessException;


    /**
     * 根据accountId获取公司协议纰漏
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    List<AccountCommonAgreement> findByAgreement(Long accountId) throws BusinessException;

    /**
     * 根据accountId和语言获取公司文件上传类型
     *
     * @param accountId
     * @param lang
     * @return
     * @throws BusinessException
     */
    UploadFileTypeModel getFileUploadType(Long accountId, String lang) throws BusinessException;

    /**
     * 公司开户第九页/修改引用经营负责人信息
     *
     * @param id              公司个人信息id
     * @param position        经营负责人职位（第九页引用需要）
     * @param personalTypeTwo 个人类型第二种身份(见数据字典个人类型，第九页引用需要)
     * @return
     * @throws BusinessException
     */
    int updatePersonalByPrimaryKeySelective(Long id, String position, String personalTypeTwo) throws BusinessException;

    /**
     * 根据 公司crs实体（CRS-E）控权人信息ID 记录CRS-CP的状态
     * @param coCrsEntityContrId
     * @return
     * @throws BusinessException
     */
    int updateCrsEntityStatus(Long coCrsEntityContrId) throws BusinessException;
}
