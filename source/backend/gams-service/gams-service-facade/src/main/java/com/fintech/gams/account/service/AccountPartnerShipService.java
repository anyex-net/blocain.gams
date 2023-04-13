package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.*;
import com.fintech.gams.exception.BusinessException;

import java.util.List;

/**
 * @Author: hcj
 * @Date: 2019/8/31 19:47
 * @Description 合伙企业开户 服务接口
 */
public interface AccountPartnerShipService {

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
     * 合伙企业开户第一页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveOne(PartnerShipInfoModel model) throws BusinessException;

    /**
     * 合伙企业开户第二页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveTwo(PartnerShipAuthorizedModel model) throws BusinessException;

    /**
     * 合伙企业开户第三页插入/更新
     *
     * @param accountParTaxClassification
     * @throws BusinessException
     */
    void saveThree(AccountParTaxClassification accountParTaxClassification) throws BusinessException;

    /**
     * 合伙企业开户第四页插入/更新
     *
     * @param accountParRegulatoryList
     * @throws BusinessException
     */
    void saveFour(List<AccountParRegulatory> accountParRegulatoryList) throws BusinessException;

    /**
     * 合伙企业开户第五页插入/更新
     *
     * @param accountParBrokerPermission
     * @throws BusinessException
     */
    void saveFive(AccountParBrokerPermission accountParBrokerPermission) throws BusinessException;

    /**
     * 合伙企业开户第六页插入/更新
     *
     * @param accountParBrokerInfoList
     * @throws BusinessException
     */
    void saveSix(List<AccountParBrokerInfo> accountParBrokerInfoList) throws BusinessException;

    /**
     * 合伙企业第七页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveSeven(PartnerShipAssetModel model) throws BusinessException;

    /**
     * 合伙企业开户第八页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveEight(PartnerShipRegisteredOwnerModel model) throws BusinessException;

    /**
     * 删除合伙企业开户第八页通过合伙企业个人信息的id
     *
     * @param id
     * @throws BusinessException
     */
    void removeEight(Long id) throws BusinessException;

    /**
     * 合伙企业开户第九页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveNine(PartnerShipBeneficiaryOwnersModel model) throws BusinessException;

    /**
     * 删除合伙企业开户第九页通过合伙企业个人信息的id
     *
     * @param id
     * @throws BusinessException
     */
    void removeNine(Long id) throws BusinessException;

    /**
     * 合伙企业开户第十页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveTen(PartnerShipSecurityOfficerModel model) throws BusinessException;

    /**
     * 合伙企业开户第十一页插入/更新
     *
     * @param accountParWbenContract
     * @throws BusinessException
     */
    void saveEleven(AccountParWbenContract accountParWbenContract) throws BusinessException;

    /**
     * 合伙企业开户第十二页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveTwelve(PartnerShipCrsEntityContrModel model) throws BusinessException;

    /**
     * 合伙企业开户第十三页插入/更新
     *
     * @param model
     * @throws BusinessException
     */
    void saveThirteen(PartnerShipCrsControlModel model) throws BusinessException;

    /**
     * 合伙企业开户第十四页插入/更新
     *
     * @param accountParAgreementList
     * @throws BusinessException
     */
    void saveFourteen(List<AccountParAgreement> accountParAgreementList) throws BusinessException;

    /**
     * 合伙企业开户第十五页插入/更新
     *
     * @param accountParInvestmentAccount
     * @throws BusinessException
     */
    void saveFifteen(AccountParInvestmentAccount accountParInvestmentAccount) throws BusinessException;

    /**
     * 合伙企业开户第十八页插入/更新
     *
     * @param accountParAmlCtfList
     * @throws BusinessException
     */
    void saveEighteen(List<AccountParAmlCtf> accountParAmlCtfList) throws BusinessException;

    /**
     * 合伙企业开户第十九页插入/更新
     *
     * @param accountParFile
     * @throws BusinessException
     */
    void saveNineteen(AccountParFile accountParFile) throws BusinessException;

    /**
     * 根据accountId和语言获取合伙企业文件上传类型
     *
     * @param accountId
     * @param lang
     * @return
     * @throws BusinessException
     */
    UploadFileTypeModel getFileUploadType(Long accountId, String lang) throws BusinessException;

    /**
     * 根据accountId获取合伙企业协议纰漏
     *
     * @param accountId
     * @return
     * @throws BusinessException
     */
    List<AccountCommonAgreement> findByAgreement(Long accountId) throws BusinessException;

    /**
     * 合伙企业开户第九页/修改引用经营负责人信息
     *
     * @param id              合伙企业个人信息id
     * @param position        经营负责人职位（第九页引用需要）
     * @param personalTypeTwo 个人类型第二种身份(见数据字典个人类型，第九页引用需要)
     * @return
     * @throws BusinessException
     */
    int updatePersonalByPrimaryKeySelective(Long id, String position, String personalTypeTwo) throws BusinessException;

    /**
     * 根据 合伙企业crs实体（CRS-E）控权人信息ID 记录CRS-CP的状态
     * @param parCrsEntityContrId
     * @return
     * @throws BusinessException
     */
    int updateCrsEntityStatus(Long parCrsEntityContrId) throws BusinessException;
}
