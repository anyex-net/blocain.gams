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
import com.fintech.gams.system.entity.Organize;
import com.fintech.gams.system.mapper.OrganizeMapper;
import com.fintech.gams.utils.ListUtils;
import com.google.common.collect.Lists;

/**
 * 组织机构信息 服务实现类
 * <p>File：OrganizeServiceImpl.java </p>
 * <p>Title: OrganizeServiceImpl </p>
 * <p>Description:OrganizeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class OrganizeServiceImpl extends GenericServiceImpl<Organize> implements OrganizeService
{
    private OrganizeMapper organizeMapper;

    @Autowired(required = false)
    public OrganizeServiceImpl(OrganizeMapper organizeMapper)
    {
        super(organizeMapper);
        this.organizeMapper = organizeMapper;
    }
    
    @Override
    @SlaveDataSource()
    public List<TreeModel> findByOrganization(Organize organiz) throws BusinessException
    {
        List<Organize> data = organizeMapper.findList(organiz);
        if (ListUtils.isNull(data))
        { return null; }
        List<TreeModel> models = Lists.newArrayList();
        for (Organize org : data)
        {
            models.add(conventOrganization(org));
        }
        return models;
    }
    
    @Override
    @SlaveDataSource()
    public List<Organize> findList(Organize organiz) throws BusinessException
    {
        List<Organize> data = Lists.newArrayList();
        List<Organize> entitys = organizeMapper.findList(organiz);
        for (Organize parent : entitys)
        {
            if (null == parent.getParentId() || 0L == parent.getParentId())
            {
                data.add(parent);
            }
            for (Organize child : entitys)
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
     * 转换机构对象
     * @param orgiz
     * @return
     */
    private TreeModel conventOrganization(Organize orgiz)
    {
        TreeModel model = new TreeModel();
        model.setId(orgiz.getId());
        model.setPid(orgiz.getParentId());
        model.setText(orgiz.getOrgName());
        return model;
    }
}
