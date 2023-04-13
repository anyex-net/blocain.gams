/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.AccountInfoModel;
import com.fintech.gams.account.model.AccountManagementFindAccountListModel;
import com.fintech.gams.account.model.AccountManagementListModel;
import com.fintech.gams.bean.GenericService;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.exception.BusinessException;

/**
 * 账户表 服务接口
 * <p>File：AccountService.java </p>
 * <p>Title: AccountService </p>
 * <p>Description:AccountService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface AccountService extends GenericService<Account> {
    /**
     * 根据帐户名取数据
     *
     * @param accountName
     * @return {@link Account}
     */
    Account findByName(String accountName) throws BusinessException;

    /**
     * 根据手机号查找
     *
     * @param region
     * @param phone
     * @return
     * @throws BusinessException
     */
    Account findByPhone(String region, String phone) throws BusinessException;

    /**
     * 根据邮件查找
     *
     * @param email
     * @return
     * @throws BusinessException
     */
    Account findByEmail(String email) throws BusinessException;

    /**
     * 修改密码
     *
     * @param id
     * @param oldPwd
     * @param newPwd
     * @throws BusinessException
     */
    void changePassword(Long id, String oldPwd, String newPwd) throws BusinessException;

    /**
     * 创建账户
     *
     * @param account
     * @return
     * @throws BusinessException
     */
    int createAccount(Account account) throws BusinessException;

    /**
     * 验证账户名是否重复
     *
     * @param accountName
     * @return true 不重复 false 重复
     * @throws BusinessException
     */
    boolean checkDuplicateName(String accountName) throws BusinessException;

    /**
     * 验证手机号是否重复
     *
     * @param mobile
     * @param region 国家代码
     * @return
     * @throws BusinessException
     */
    boolean checkDuplicateMobile(String mobile, String region) throws BusinessException;

    /**
     * 验证邮箱是否重复
     *
     * @param email
     * @return
     * @throws BusinessException
     */
    boolean checkDuplicateEmail(String email) throws BusinessException;

    /**
     * 注册用户激活码激活  -1：未激活 0：已激活
     *
     * @param activationCode
     * @return
     * @throws BusinessException
     */
    Account accountActivation(String activationCode) throws BusinessException;

    /**
     * 通过审核 1
     * 注册开户状态(0 表示开户ok 未审核, 1 表示开户OK 并且审核通过,注册过程 写负数(页数))
     *
     * @param id
     * @return
     * @throws BusinessException
     */
    Account approvedRegister(Long id) throws BusinessException;


    /**
     * 根据 accountName(模糊查询) 和 注册开户状态 查询相关账户信息
     *
     * @param pagin
     * @param accountName
     * @param registerStatus
     * @return
     * @throws BusinessException
     */
    PaginateResult<Account> findAccountList(Pagination pagin, String accountName, Integer registerStatus) throws BusinessException;

    /**
     * 根据 id 和 accountType 查询相关的账户信息
     *
     * @param id
     * @param accountType
     * @return
     */
    AccountInfoModel findAccountInfo(Long id, String accountType);

    /**
     * 根据 账户信息  查询相关账户列表
     *
     * @param pagin
     * @param accountListModel
     * @return
     * @throws BusinessException
     */
    PaginateResult<AccountManagementListModel> findAccountList(Pagination pagin, AccountManagementFindAccountListModel accountListModel) throws BusinessException;

}
