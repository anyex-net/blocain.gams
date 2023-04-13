package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.model.AccountPersonalSaveOneModel;
import com.fintech.gams.account.model.AccountPersonalSaveTwoModel;
import com.fintech.gams.exception.BusinessException;

import java.util.List;
import java.util.Map;

/**
 * @Author: hcj
 * @Date: 2019/8/31 14:31
 * @Description  个人开户 服务接口
 */
public interface AccountPersonalService {

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
     * 个人开户第一页插入/更新
     *
     * @param accountPersonalSaveOneModel
     * @throws BusinessException
     */
    void saveOne(AccountPersonalSaveOneModel accountPersonalSaveOneModel) throws BusinessException;

    /**
     * 个人开户第二页插入/更新
     *
     * @param accountPersonalSaveTwoModel
     * @throws BusinessException
     */
    void saveTwo(AccountPersonalSaveTwoModel accountPersonalSaveTwoModel) throws BusinessException;

    /**
     * 个人开户第三页插入/更新
     *
     * @param accountPerWbenContract
     * @throws BusinessException
     */
    void saveThree(AccountPerWbenContract accountPerWbenContract) throws BusinessException;

    /**
     * 个人开户第四页插入/更新
     *
     * @param accountPerAgreementList
     * @throws BusinessException
     */
    void saveFour(List<AccountPerAgreement> accountPerAgreementList) throws BusinessException;

    /**
     * 个人开户第五页插入/更新
     *
     * @param accountPerInvestAccount
     * @throws BusinessException
     */
    void saveFive(AccountPerInvestAccount accountPerInvestAccount) throws BusinessException;

    /**
     * 个人开户第七页插入/更新
     *
     * @param accountPerCrs
     * @throws BusinessException
     */
    void saveSeven(AccountPerCrs accountPerCrs) throws BusinessException;

    /**
     * 个人开户第九页插入/更新
     *
     * @param accountPerAmlCtfList
     * @throws BusinessException
     */
    void saveNine(List<AccountPerAmlCtf> accountPerAmlCtfList) throws BusinessException;

    /**
     * 个人开户第十页插入/更新
     *
     * @param accountPerFile
     * @throws BusinessException
     */
    void saveTen(AccountPerFile accountPerFile) throws BusinessException;

    /**
     * 根据accountId获取个人协议纰漏
     * @param accountId
     * @return
     * @throws BusinessException
     */
    List<AccountCommonAgreement> findByAgreement(Long accountId) throws BusinessException;

    /**
     * 根据accountId和语言获取个人文件上传类型
     * @param accountId
     * @param lang
     * @return
     * @throws BusinessException
     */
    Map<String,Object> getFileUploadType(Long accountId, String lang) throws BusinessException;
}
