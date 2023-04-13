/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.account.consts.AccountConsts;
import com.fintech.gams.account.entity.*;
import com.fintech.gams.account.mapper.*;
import com.fintech.gams.account.model.AccountInfoModel;
import com.fintech.gams.account.model.AccountManagementListModel;
import com.fintech.gams.account.model.AccountManagementFindAccountListModel;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.entity.Region;
import com.fintech.gams.common.mapper.RegionMapper;
import com.fintech.gams.common.service.MsgLogService;
import com.fintech.gams.common.service.RegionService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.common.mapper.DictionaryMapper;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.utils.EncryptUtils;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 账户表 服务实现类
 * <p>File：AccountServiceImpl.java </p>
 * <p>Title: AccountServiceImpl </p>
 * <p>Description:AccountServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl extends GenericServiceImpl<Account> implements AccountService
{
    private AccountMapper accountMapper;
    @Autowired(required = false)
    private AccountCoInstitutionalMapper accountCoInstitutionalMapper;
    @Autowired(required = false)
    private AccountCoAccountPortfolioMapper accountCoAccountPortfolioMapper;
    @Autowired(required = false)
    private AccountParInstitutionalMapper accountParInstitutionalMapper;
    @Autowired(required = false)
    private AccountParAccountPortfolioMapper accountParAccountPortfolioMapper;
    @Autowired(required = false)
    private AccountPerInfoMapper accountPerInfoMapper;
    @Autowired(required = false)
    private AccountPerPortfolioMapper accountPerPortfolioMapper;
    @Autowired(required = false)
    private DictionaryMapper dictionaryMapper;
    @Autowired(required = false)
    private RegionMapper regionMapper;
    @Autowired(required = false)
    private MsgLogService msgLogService;


    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper)
    {
        super(accountMapper);
        this.accountMapper = accountMapper;
    }

    @Override
    public Account findByName(String accountName) throws BusinessException
    {
        if (StringUtils.isBlank(accountName))
        { // 参数判断
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return accountMapper.findByName(accountName);
    }

    @Override
    public Account findByPhone(String region, String phone) throws BusinessException
    {
        if (StringUtils.isBlank(phone))
        { // 参数判断
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return accountMapper.findByPhone(region, phone);
    }

    @Override
    public Account findByEmail(String email) throws BusinessException
    {
        if (StringUtils.isBlank(email))
        { // 参数判断
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        return accountMapper.findByEmail(email);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changePassword(Long userId, String oldPwd, String newPwd) throws BusinessException
    {
        Account entity = accountMapper.selectByPrimaryKey(userId);
        if (null == entity)
        { throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT); }
        if (!EncryptUtils.validatePassword(String.valueOf(oldPwd), entity.getLoginPwd()))
        {// 验证帐户密码
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        entity.setLoginPwd(EncryptUtils.entryptPassword(newPwd));
        accountMapper.updateByPrimaryKey(entity);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public  int createAccount(Account account) throws BusinessException
    {
        this.beanValidator(account);
        log.info("进入创建账户");
        // 判断账户名是否重复
        if (!this.checkDuplicateName(account.getAccountName())) {
            throw new BusinessException(CommonEnums.ERROR_ACCOUNT_EXIST);
        }
        // 判断邮箱是否重复
        if (!this.checkDuplicateEmail(account.getEmail())) {
            throw new BusinessException(CommonEnums.ERROR_EMAIL_EXIST);
        }
        // 判断手机号是否重复
        if (!this.checkDuplicateMobile(account.getMobile(),account.getRegion())) {
            throw new BusinessException(CommonEnums.ERROR_MOBILE_EXIST);
        }
        if (StringUtils.isBlank(account.getNationality()) || null == account.getNationality()) {
            account.setNationality(account.getCountry());
        }
        // 避免传入非法的国际简码
        Region regionSCode = regionMapper.findRegionBySCode(account.getCountry());
        if (null == regionSCode) throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        // 避免传入非法的国际代码
        List<Region> regionLCodeList = regionMapper.findRegionByLCode(account.getRegion());
        if (null == regionLCodeList) throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        // 避免传入非法的账户类型
        Dictionary accountType = dictionaryMapper.findDictionary(AccountConsts.ACCOUNT_TYPE, account.getAccountType(), GlobalConst.DEFAULT_LANG);
        if (null == accountType) throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        account.setId(SerialnoUtils.buildPrimaryKey());
        if (null != accountMapper.getMaxUNID()) {
            account.setUnid((accountMapper.getMaxUNID() + 1L));
        } else {
            account.setUnid(1L);
        }
        account.setLang(GlobalConst.DEFAULT_LANG);
        account.setCreateDate(System.currentTimeMillis());
        account.setUpdateDate(System.currentTimeMillis());
        account.setLoginPwd(EncryptUtils.entryptPassword(account.getLoginPwd()));
        // 资金密码
        // account.setFundPwd(EncryptUtils.entryptPassword(account.getFundPwd()));
        account.setStatus(-1);
        account.setSecurityPolicy(0);
        account.setTradePolicy(0);
        // 保存
        int count = accountMapper.insert(account);
        if (count > 0) {
            // 创建帐户成功发邮件
            msgLogService.sendRegisterMail(account.getEmail(), account.getActivationCode(), GlobalConst.DEFAULT_LANG);
        }
        return count;
    }

    @Override
    public boolean checkDuplicateName(String accountName) throws BusinessException {
        Account account = new Account();
        account.setAccountName(accountName);
        if (accountMapper.findList(account).size()==0)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateMobile(String mobile, String region) throws BusinessException {
        if (StringUtils.isBlank(mobile)) {
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        Account account = new Account();
        if (StringUtils.isNotBlank(region)) {
            account.setMobile(mobile);
            account.setRegion(region);
        } else {
            return true;
        }
        if (accountMapper.findList(account).size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateEmail(String email) throws BusinessException {
        if (StringUtils.isBlank(email)) {
            throw new BusinessException(CommonEnums.ERROR_DATA_VALID_ERR);
        }
        Account account = new Account();
        account.setEmail(email);
        if (accountMapper.findList(account).size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Account accountActivation(String activationCode) throws BusinessException
    {
        Account account = new Account();
        account.setActivationCode(activationCode);
        List<Account> accountList = accountMapper.findList(account);
        if (accountList.size()!=0)
        {
            if (accountList.get(0).getStatus().equals(-1))
            {
                account = accountList.get(0);
                account.setStatus(0);
                accountMapper.updateByPrimaryKeySelective(account);
            }else
            {// 已激活
                throw new BusinessException(CommonEnums.SUCCESS);
            }
        }
        return account;
    }

    @Override
    public Account approvedRegister(Long id) throws BusinessException {
        if (null == id)
        { throw new BusinessException(CommonEnums.PARAMS_VALID_ERR); }
        Account account=accountMapper.selectByPrimaryKey(id);
        if(null != account && account.getRegisterStatus()==0){
            account.setRegisterStatus(1);
            accountMapper.updateByPrimaryKey(account);
        }
        return account;
    }

    @Override
    public PaginateResult<Account> findAccountList(Pagination pagin, String accountName, Integer registerStatus) throws BusinessException {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<Account> pageInfo = PageInfo.of(accountMapper.findAccountList(accountName,registerStatus));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public AccountInfoModel findAccountInfo(Long id, String accountType) {
        AccountInfoModel accountInfoModel;
        String mobile, email;
        if (accountType.equals("personal")) {
            accountInfoModel = accountMapper.findPersonalInfo(id);
            // 将电话号码与邮箱用*加密
            mobile = accountInfoModel.getMobile();
            email = accountInfoModel.getEmail();
            if (mobile.length() <= 6) {
                accountInfoModel.setMobile(mobile.substring(0, 1) + "****" + mobile.substring(mobile.length() - 1));
            } else {
                accountInfoModel.setMobile(mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4));
            }
            accountInfoModel.setEmail(email.substring(0, 1) + "****" + email.substring(email.indexOf('@')-1));

            return accountInfoModel;
        } else if (accountType.equals("limitedLiablity")) {
            accountInfoModel = accountMapper.findLimitedLiablityInfo(id);
            // 将电话号码与邮箱用*加密
            mobile = accountInfoModel.getMobile();
            email = accountInfoModel.getEmail();
            if (mobile.length() <= 6) {
                accountInfoModel.setMobile(mobile.substring(0, 1) + "****" + mobile.substring(mobile.length() - 1));
            } else {
                accountInfoModel.setMobile(mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4));
            }
            accountInfoModel.setEmail(email.substring(0, 1) + "****" + email.substring(email.indexOf('@')-1));

            return accountInfoModel;
        } else {
            accountInfoModel = accountMapper.findPartnerShipInfo(id);
            // 将电话号码与邮箱用*加密
            mobile = accountInfoModel.getMobile();
            email = accountInfoModel.getEmail();
            if (mobile.length() <= 6) {
                accountInfoModel.setMobile(mobile.substring(0, 1) + "****" + mobile.substring(mobile.length() - 1));
            } else {
                accountInfoModel.setMobile(mobile.substring(0, 3) + "****" + mobile.substring(mobile.length() - 4));
            }
            accountInfoModel.setEmail(email.substring(0, 1) + "****" + email.substring(email.indexOf('@')-1));

            return accountInfoModel;
        }
    }

    @Override
    public PaginateResult<AccountManagementListModel> findAccountList(Pagination pagin, AccountManagementFindAccountListModel model) throws BusinessException {
        if(model.getAccountType()==null && model.getCustomerName() !=null){
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        model.setLang(StringUtils.isBlank(model.getLang()) ? "zh_CN" : model.getLang());
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<AccountManagementListModel> pageInfo = PageInfo.of(new ArrayList());
        if(model.getAccountId() ==null && model.getAccountType()==null && model.getCustomerName()==null){
            pageInfo = PageInfo.of(accountMapper.findAccountManagementList(model));
            for(AccountManagementListModel account:pageInfo.getList()){
                if(account.getAccounType().endsWith("partnerShip")){
                    //机构名称
                    AccountParInstitutional institutional =new AccountParInstitutional();
                    institutional.setAccountId(account.getAccountId());
                    List<AccountParInstitutional> accountParInstitutionals=accountParInstitutionalMapper.findList(institutional);
                    if(accountParInstitutionals.isEmpty()==false && accountParInstitutionals.get(0).getInstitutionalName() != null){
                        account.setCustomerName(accountParInstitutionals.get(0).getInstitutionalName());
                    }
                    //投资目标
                    AccountParAccountPortfolio portfolio = new AccountParAccountPortfolio();
                    portfolio.setAccountId(account.getAccountId());
                    List<AccountParAccountPortfolio> portfolios=accountParAccountPortfolioMapper.findList(portfolio);
                    if(portfolios.isEmpty()==false){
                        if(portfolios.get(0).getAccountPortfolioType()!= null){
                            account.setAccountPortfolioTypeDest(dictionaryMapper.findDictionary("accountPortfolioType",portfolios.get(0).getAccountPortfolioType(),model.getLang()).getDictDest());
                        }
                        account.setBaseCurrency(portfolios.get(0).getBaseCurrency());
                    }
                }
                if(account.getAccounType().endsWith("limitedLiablity")){
                    //机构名
                    AccountCoInstitutional accountCoInstitutional=accountCoInstitutionalMapper.findByAccountId(account.getAccountId());
                    if(accountCoInstitutional !=null){
                        account.setCustomerName( accountCoInstitutional.getInstitutionalName());
                    }
                    //投资目标
                    AccountCoAccountPortfolio portfolio=accountCoAccountPortfolioMapper.findByAccountId(account.getAccountId());
                    if(portfolio!=null){
                        account.setAccountPortfolioTypeDest(dictionaryMapper.findDictionary("accountPortfolioType",portfolio.getAccountPortfolioType(),model.getLang()).getDictDest());
                        account.setBaseCurrency(portfolio.getBaseCurrency());
                    }

                }
                if(account.getAccounType().endsWith("personal")){
                    //机构名
                    AccountPerInfo accountPerInfo =new AccountPerInfo();
                    accountPerInfo.setAccountId(account.getAccountId());
                    List<AccountPerInfo> accountPerInfos=accountPerInfoMapper.findList(accountPerInfo);
                    if(accountPerInfos.isEmpty() == false){
                        account.setCustomerName(accountPerInfos.get(0).getLastName()+accountPerInfos.get(0).getFirstName());
                    }
                    //投资目标
                    AccountPerPortfolio portfolio = new AccountPerPortfolio();
                    portfolio.setAccountId(account.getAccountId());
                    List<AccountPerPortfolio> portfolios=accountPerPortfolioMapper.findList(portfolio);
                    if(portfolios.isEmpty() == false ){
                        if(portfolios.get(0).getAccountPortfolioType()!=null){
                            account.setAccountPortfolioTypeDest(dictionaryMapper.findDictionary("accountPortfolioType",portfolios.get(0).getAccountPortfolioType(),model.getLang()).getDictDest());
                        }
                        account.setBaseCurrency(portfolios.get(0).getBaseCurrency());
                    }
                }
            }
        }else {
            if(model.getAccountType().endsWith("partnerShip")){
                pageInfo = PageInfo.of(accountMapper.findAccountPartnerShipList(model));
            }
            if(model.getAccountType().endsWith("limitedLiablity")){
                pageInfo = PageInfo.of(accountMapper.findAccountLimitedLiablityList(model));
            }
            if(model.getAccountType().endsWith("personal")){
                pageInfo = PageInfo.of(accountMapper.findAccountPersonalList(model));
            }
        }
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

}
