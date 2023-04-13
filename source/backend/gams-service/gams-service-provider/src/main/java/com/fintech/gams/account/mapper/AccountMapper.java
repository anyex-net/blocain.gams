/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.mapper;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.AccountInfoModel;
import com.fintech.gams.account.model.AccountManagementListModel;
import com.fintech.gams.account.model.AccountManagementFindAccountListModel;
import com.fintech.gams.bean.GenericMapper;
import com.fintech.gams.exception.BusinessException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 账户表 持久层接口
 * <p>File：AccountDao.java </p>
 * <p>Title: AccountDao </p>
 * <p>Description:AccountDao </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface AccountMapper extends GenericMapper<Account>
{
    /**
     * 取最大的UNID
     *
     * @return
     */
    Long getMaxUNID();
    
    /**
     * 根据帐户名取数据
     *
     * @param accountName
     * @return
     */
    Account findByName(String accountName);
    
    /**
     * 根据用户UNID查
     *
     * @param unid
     * @return
     */
    Account findByUnid(Long unid);
    
    /**
     * 根据手机号查询
     *
     * @param region
     * @param mobile
     * @return
     */
    Account findByPhone(@Param("region") String region, @Param("mobile") String mobile);

    /**
     * 根据邮件查找
     * @param email
     * @return
     * @throws BusinessException
     */
    Account findByEmail(String email) throws BusinessException;

    /**
     * 根据 accountName(模糊查询) 和 注册开户状态 查询相关账户信息
     * @param accountName
     * @param registerStatus
     * @return
     */
    List<Account> findAccountList(@Param("accountName") String accountName, @Param("registerStatus") Integer registerStatus);

    /**
     * 根据 id 查询个人账户信息
     * @param id
     * @return
     */
    AccountInfoModel findPersonalInfo(Long id);

    /**
     * 根据 id 查询有限公司信息
     * @param id
     * @return
     */
    AccountInfoModel findLimitedLiablityInfo(Long id);

    /**
     * 根据 id 查询合伙企业信息
     * @param id
     * @return
     */
    AccountInfoModel findPartnerShipInfo(Long id);

    /**
     * 根据 账户信息  查询合伙企业账户列表
     * @param accountManagementFindAccountListModel
     * @return
     */
    List<AccountManagementListModel> findAccountPartnerShipList(@Param("accountModel") AccountManagementFindAccountListModel accountManagementFindAccountListModel);

    /**
     * 根据 账户信息  查询合公司账户列表
     * @param accountManagementFindAccountListModel
     * @return
     */
    List<AccountManagementListModel> findAccountLimitedLiablityList(@Param("accountModel") AccountManagementFindAccountListModel accountManagementFindAccountListModel);
    /**
     * 根据 账户信息  查询个人账户列表
     * @param accountManagementFindAccountListModel
     * @return
     */
    List<AccountManagementListModel> findAccountPersonalList(@Param("accountModel") AccountManagementFindAccountListModel accountManagementFindAccountListModel);

    /**
     * 根据 账户信息 查询账户列表（不可用客户名）
     * @param accountManagementFindAccountListModel
     * @return
     */
    List<AccountManagementListModel> findAccountManagementList(@Param("accountModel") AccountManagementFindAccountListModel accountManagementFindAccountListModel);
}
