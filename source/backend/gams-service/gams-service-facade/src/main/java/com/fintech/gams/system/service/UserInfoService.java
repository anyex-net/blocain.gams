/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.system.entity.UserInfo;

/**
 * 用户基础信息表 服务接口
 * <p>File：UserInfoService.java </p>
 * <p>Title: UserInfoService </p>
 * <p>Description:UserInfoService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface UserInfoService extends GenericService<UserInfo>
{
    /**
     * 根据用户名找用户
     *
     * @param userName
     * @return
     */
    UserInfo findByUserName(String userName);

    /**
     * 修复密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @throws BusinessException
     */
    void changePassword(Long userId,String oldPwd,String newPwd)throws BusinessException;
}
