package com.fintech.gams.account.service;

import com.fintech.gams.account.entity.Account;
import com.fintech.gams.account.model.AccountInfoModel;
import com.fintech.gams.utils.EncryptUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.BaseServiceImplTest;
import com.fintech.gams.exception.BusinessException;

/**
 * AccountServiceTest
 * <p>File: AccountServiceTest.java </p>
 * <p>Title: AccountServiceTest </p>
 * <p>Description: AccountServiceTest </p>
 * <p>Copyright: Copyright (c) 2019-05-30</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class AccountServiceTest extends BaseServiceImplTest
{
    @Autowired
    private AccountService accountService;

    @Test
    public void insert() throws BusinessException
    {
        Account account = new Account();
        account.setId(200000000000L);
        account.setUnid(1L);
        account.setAccountType("personal");
        account.setAccountName("zhangsan");
        account.setNickName("张三");
        account.setEmail("793491348@qq.com");
        account.setMobile("13857899227");
        account.setLocation("127.0.0.1");
        account.setCountry("china");
        account.setNationality("USA");
        account.setLoginPwd(EncryptUtils.entryptPassword("123456"));
        account.setFundPwd(EncryptUtils.entryptPassword("123456"));
        account.setStatus(0);
        account.setTradePolicy(0);
        account.setSecurityPolicy(0);
        account.setCreateDate(System.currentTimeMillis());
        account.setUpdateDate(System.currentTimeMillis());
        accountService.insert(account);

        account = new Account();
        account.setId(200000000001L);
        account.setUnid(2L);
        account.setAccountType("personal");
        account.setAccountName("lisi");
        account.setNickName("李四");
        account.setEmail("275625385@qq.com");
        account.setMobile("13857899228");
        account.setCountry("china");
        account.setNationality("USA");
        account.setLoginPwd(EncryptUtils.entryptPassword("123456"));
        account.setFundPwd(EncryptUtils.entryptPassword("123456"));
        account.setLocation("127.0.0.1");
        account.setStatus(0);
        account.setTradePolicy(0);
        account.setSecurityPolicy(0);
        account.setCreateDate(System.currentTimeMillis());
        account.setUpdateDate(System.currentTimeMillis());
        accountService.insert(account);
    }

    @Test
    public void findAccountInfo() throws BusinessException{

        AccountInfoModel accountInfoModel = accountService.findAccountInfo(243921532659699712l,"personal");
        AccountInfoModel accountInfoModel1 = accountService.findAccountInfo(269273975799025664l,"limitedLiablity");

        AccountInfoModel accountInfoModel2 = accountService.findAccountInfo(269264022359117824l,"partnerShip");

        if(accountInfoModel1 != null){
            System.out.println(accountInfoModel.toString());
            System.out.println(accountInfoModel1.toString());
            System.out.println(accountInfoModel2.toString());
        }
}
}
