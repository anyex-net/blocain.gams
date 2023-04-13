/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.gams.annotation.SlaveDataSource;
import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.config.GlobalProperies;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.system.entity.UserData;
import com.fintech.gams.system.entity.UserInfo;
import com.fintech.gams.system.entity.UserRole;
import com.fintech.gams.system.mapper.*;
import com.fintech.gams.utils.EncryptUtils;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;

/**
 * 用户基础信息表 服务实现类
 * <p>File：UserInfo.java </p>
 * <p>Title: UserInfo </p>
 * <p>Description:UserInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class UserInfoServiceImpl extends GenericServiceImpl<UserInfo> implements UserInfoService
{
    private UserInfoMapper  userInfoMapper;
    
    @Autowired(required = false)
    private UserRoleMapper  userRoleMapper;
    
    @Autowired(required = false)
    private OrganizeMapper  organizeMapper;
    
    @Autowired(required = false)
    private RoleInfoMapper  roleInfoMapper;
    
    @Autowired(required = false)
    private UserDataMapper  userDataMapper;
    
    @Autowired(required = false)
    private GlobalProperies properies;
    
    @Autowired(required = false)
    public UserInfoServiceImpl(UserInfoMapper userInfoMapper)
    {
        super(userInfoMapper);
        this.userInfoMapper = userInfoMapper;
    }
    
    @Override
    public UserInfo selectByPrimaryKey(Long id) throws BusinessException
    {
        UserInfo info = userInfoMapper.selectByPrimaryKey(id);
        info.setRoleList(roleInfoMapper.findByUserId(info.getId()));
        info.setOrganizeList(organizeMapper.findByUserId(info.getId()));
        return info;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int save(UserInfo entity) throws BusinessException
    {
        int flag;
        this.beanValidator(entity);
        if (null == entity.getId())
        {
            // 如果用户没有设置密码，初始化一个
            if (StringUtils.isBlank(entity.getPassword()))
            {
                entity.setPassword(properies.getPassWord());
            }
            entity.setId(SerialnoUtils.buildPrimaryKey());
            entity.setPassword(EncryptUtils.entryptPassword(entity.getPassword()));
            if (null != userInfoMapper.findByUserName(entity.getUserName()))
            {// 去队重复
                throw new BusinessException(CommonEnums.ERROR_ACCOUNT_EXIST);
            }
            flag = userInfoMapper.insert(entity);
            saveUserRole(entity);
            saveUserData(entity);
        }
        else
        {
            UserInfo dbInfo = userInfoMapper.selectByPrimaryKey(entity.getId());
            if (StringUtils.isNotBlank(dbInfo.getAuthKey()))
            { // 防止空指针
                entity.setAuthKey(dbInfo.getAuthKey());
            }
            if (StringUtils.isNotBlank(entity.getPassword()))
            {// 修改密码
                entity.setPassword(EncryptUtils.entryptPassword(entity.getPassword()));
            }
            else
            {// 保留原始密码
                entity.setPassword(dbInfo.getPassword());
            }
            flag = userInfoMapper.updateByPrimaryKey(entity);
            saveUserRole(entity);
            saveUserData(entity);
        }
        return flag;
    }
    
    /**
     * 保存用户角色
     *
     * @param info
     */
    private void saveUserRole(UserInfo info)
    {
        userRoleMapper.removeByUser(info.getId());
        if (StringUtils.isNotBlank(info.getRoleIds()))
        {
            String[] roleIds = info.getRoleIds().split(",");
            List<UserRole> userRoleList = Lists.newArrayList();
            UserRole userRole;
            for (String roleId : roleIds)
            {
                userRole = new UserRole(Long.parseLong(roleId), info.getId());
                userRole.setId(SerialnoUtils.buildPrimaryKey());
                userRoleList.add(userRole);
            }
            userRoleMapper.insertBatch(userRoleList);
        }
    }
    
    /**
     * 保存用户数据权限
     * @param info
     */
    private void saveUserData(UserInfo info)
    {
        userDataMapper.removeByUser(info.getId());
        if (StringUtils.isNotBlank(info.getOrgIds()))
        {
            String[] orgIds = info.getOrgIds().split(",");
            List<UserData> userDataList = Lists.newArrayList();
            UserData entity;
            for (String orgId : orgIds)
            {
                entity = new UserData(info.getId(), Long.parseLong(orgId));
                entity.setId(SerialnoUtils.buildPrimaryKey());
                userDataList.add(entity);
            }
            userDataMapper.insertBatch(userDataList);
        }
    }
    
    @Override
    @SlaveDataSource()
    public PaginateResult<UserInfo> search(Pagination pagin, UserInfo entity) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<UserInfo> pageInfo = PageInfo.of(findList(entity));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        List<UserInfo> result = pageInfo.getList();
        for (UserInfo info : result)
        {
            info.setRoleList(roleInfoMapper.findByUserId(info.getId()));
            info.setOrganizeList(organizeMapper.findByUserId(info.getId()));
        }
        return new PaginateResult<>(pagin, result);
    }
    
    @Override
    public UserInfo findByUserName(String userName)
    {
        return userInfoMapper.findByUserName(userName);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changePassword(Long userId, String oldPwd, String newPwd) throws BusinessException
    {
        UserInfo entity = userInfoMapper.selectByPrimaryKey(userId);
        if (null == entity)
        { throw new BusinessException(CommonEnums.ERROR_LOGIN_ACCOUNT); }
        if (!EncryptUtils.validatePassword(String.valueOf(oldPwd), entity.getPassword()))
        {// 验证帐户密码
            throw new BusinessException(CommonEnums.ERROR_LOGIN_PASSWORD);
        }
        entity.setPassword(EncryptUtils.entryptPassword(newPwd));
        userInfoMapper.updateByPrimaryKey(entity);
    }
}
