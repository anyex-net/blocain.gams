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

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.model.TreeModel;
import com.fintech.gams.system.entity.RoleInfo;
import com.fintech.gams.system.entity.RoleRes;
import com.fintech.gams.system.mapper.RoleInfoMapper;
import com.fintech.gams.system.mapper.RoleResMapper;
import com.fintech.gams.utils.ListUtils;
import com.fintech.gams.utils.SerialnoUtils;
import com.fintech.gams.utils.StringUtils;
import com.google.common.collect.Lists;

/**
 * 角色信息表 服务实现类
 * <p>File：RoleInfo.java </p>
 * <p>Title: RoleInfo </p>
 * <p>Description:RoleInfo </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class RoleInfoServiceImpl extends GenericServiceImpl<RoleInfo> implements RoleInfoService
{
    private RoleInfoMapper roleInfoMapper;

    @Autowired(required = false)
    private RoleResMapper  roleResMapper;

    @Autowired(required = false)
    public RoleInfoServiceImpl(RoleInfoMapper roleInfoMapper)
    {
        super(roleInfoMapper);
        this.roleInfoMapper = roleInfoMapper;
    }
    
    @Override
    public List<RoleInfo> findByUserId(Long userId)
    {
        return roleInfoMapper.findByUserId(userId);
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveGrant(Long id, String resourceIds) throws BusinessException
    {
        if (null == id)
        { throw new BusinessException("角色编码不可为空"); }
        if (StringUtils.isBlank(resourceIds))
        { throw new BusinessException("角色授权资源不可为空"); }
        // 先删除原有的授权数据
        roleResMapper.removeByRoleId(id);
        String[] resIds = resourceIds.split(",");
        List<RoleRes> resList = Lists.newArrayList();
        RoleRes res;
        for (String resId : resIds)
        {
            res = new RoleRes(id, Long.parseLong(resId));
            res.setId(SerialnoUtils.buildPrimaryKey());
            resList.add(res);
        }
        roleResMapper.insertBatch(resList);
    }
    
    @Override
    public List<TreeModel> findByRole(RoleInfo role) throws BusinessException
    {
        List<RoleInfo> data = roleInfoMapper.findList(role);
        if (ListUtils.isNull(data)) return null;
        List<TreeModel> models = Lists.newArrayList();
        for (RoleInfo roleInfo : data)
        {
            models.add(conventObject(roleInfo));
        }
        return models;
    }
    
    /**
     * 转换对象
     *
     * @param roleInfo
     * @return
     */
    private TreeModel conventObject(RoleInfo roleInfo)
    {
        TreeModel model = new TreeModel();
        model.setId(roleInfo.getId());
        model.setText(roleInfo.getRoleName());
        return model;
    }
    
    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int insertBatch(List<RoleInfo> list, boolean flag) throws BusinessException
    {
        int count = roleInfoMapper.insertBatch(list);
        if (flag)
        { throw new BusinessException("数据插入失败！"); }
        return count;
    }
}
