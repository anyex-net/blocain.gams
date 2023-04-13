/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.system.service;

import java.util.List;

import com.fintech.gams.annotation.SlaveDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.model.TreeModel;
import com.fintech.gams.system.entity.Resources;
import com.fintech.gams.system.mapper.ResourcesMapper;
import com.fintech.gams.utils.ListUtils;
import com.google.common.collect.Lists;

/**
 * 资源菜单信息表 服务实现类
 * <p>File：Resources.java </p>
 * <p>Title: Resources </p>
 * <p>Description:Resources </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ResourcesServiceImpl extends GenericServiceImpl<Resources> implements ResourcesService
{
    private ResourcesMapper resourcesMapper;

    @Autowired(required = false)
    public ResourcesServiceImpl(ResourcesMapper resourcesMapper)
    {
        super(resourcesMapper);
        this.resourcesMapper = resourcesMapper;
    }
    
    @Override
    @SlaveDataSource()
    public List<Resources> findByRoleId(Long roleId)
    {
        return resourcesMapper.findByRoleId(roleId);
    }
    
    @Override
    @SlaveDataSource()
    public List<TreeModel> findByResources(Long id) throws BusinessException
    {
        List<Resources> data = resourcesMapper.findByParentId(id);
        if (ListUtils.isNull(data))
        { return null; }
        List<TreeModel> models = Lists.newArrayList();
        for (Resources res : data)
        {
            models.add(conventResources(res));
        }
        return models;
    }

    @Override
    @SlaveDataSource()
    public List<Resources> findList(Resources resources) throws BusinessException {
        List<Resources> data = Lists.newArrayList();
        List<Resources> entitys = resourcesMapper.findList(resources);
        for (Resources parent : entitys)
        {
            if (null == parent.getParentId() || 0L == parent.getParentId())
            {
                data.add(parent);
            }
            for (Resources child : entitys)
            {
                if (parent.getId().equals(child.getParentId()))
                {
                    if (parent.getChildren() == null)
                    {
                        parent.setChildren(Lists.newArrayList(child));
                    }
                    else
                    {
                        parent.getChildren().add(child);
                    }
                }
            }
        }
        return data;
    }

    /**
     * 转换对象
     *
     * @param resource
     * @return
     */
    TreeModel conventResources(Resources resource)
    {
        TreeModel model = new TreeModel();
        model.setId(resource.getId());
        model.setPid(resource.getParentId());
        if (null != resource.getParentId())
        {
            model.setState("closed");
        }
        model.setIconCls(resource.getResIcon());
        model.setText(resource.getResName());
        return model;
    }
}
