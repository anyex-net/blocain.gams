package com.fintech.gams.account.entity;

import org.junit.Test;

/**
 * AccountTest
 * <p>File: AccountTest.java </p>
 * <p>Title: AccountTest </p>
 * <p>Description: AccountTest </p>
 * <p>Copyright: Copyright (c) 2019-05-24</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class AccountTest
{
    /**
     * do sign
     * @throws Exception
     */
    @Test
    public void doSign() throws Exception
    {
        Account account = new Account();
        account.setId(200000000000L);
        account.setUnid(1L);
        account.setAccountName("admin");
        account.setEmail("793491348@qq.com");
        account.setLocation("127.0.0.1");
        account.doSign();
        System.out.println(account);
    }
}
