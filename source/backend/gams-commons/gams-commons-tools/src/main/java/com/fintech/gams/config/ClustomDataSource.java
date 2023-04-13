package com.fintech.gams.config;

import com.fintech.gams.utils.EncryptUtils;
import com.zaxxer.hikari.HikariDataSource;

/**
 * 推展数据库连接
 * <p>File: ClustomDataSource.java </p>
 * <p>Title: ClustomDataSource </p>
 * <p>Description: ClustomDataSource </p>
 * <p>Copyright: Copyright (c) 2019-02-20</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class ClustomDataSource extends HikariDataSource
{
    @Override
    public void setPassword(String password)
    {
        super.setPassword(EncryptUtils.desDecrypt(password));
    }
}
