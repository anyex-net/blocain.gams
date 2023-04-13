/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.NewsBulletin;
import com.fintech.gams.common.mapper.NewsBulletinMapper;
import com.fintech.gams.common.model.NewsBulletinModel;
import com.fintech.gams.exception.BusinessException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 快讯信息 服务实现类
 * <p>File：NewsBulletinServiceImpl.java </p>
 * <p>Title: NewsBulletinServiceImpl </p>
 * <p>Description:NewsBulletinServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
public class NewsBulletinServiceImpl extends GenericServiceImpl<NewsBulletin> implements NewsBulletinService
{

    protected NewsBulletinMapper newsBulletinMapper;

    @Autowired(required = false)
    public NewsBulletinServiceImpl(NewsBulletinMapper newsBulletinMapper)
    {
        super(newsBulletinMapper);
        this.newsBulletinMapper = newsBulletinMapper;
    }


    @Override
    public void scheduleRelease() throws BusinessException {
        Long cron = (System.currentTimeMillis() / 1000 / 60 );
        List<NewsBulletin> newsBulletins = newsBulletinMapper.scheduleReleaseInfo();
        for(NewsBulletin newsBulletin : newsBulletins){
            if(newsBulletin.getCron().equals(cron)){
                newsBulletin.setStatus(1);
                newsBulletin.setUpdateDate(System.currentTimeMillis());
                newsBulletin.setReleaseDate(System.currentTimeMillis());
                newsBulletinMapper.updateByPrimaryKeySelective(newsBulletin);
            }
        }
    }


    @Override
    public PaginateResult<NewsBulletin> findOrderyUpdateDate(Pagination pagin, NewsBulletinModel entity) throws BusinessException {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<NewsBulletin> pageInfo = new PageInfo<>();
        //根据更新时间排序
        pageInfo  = PageInfo.of(newsBulletinMapper.findOrderyUpdateDate(entity));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public PaginateResult<NewsBulletin> findOrderyReleaseDate(Pagination pagin, NewsBulletinModel entity) {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<NewsBulletin> pageInfo = new PageInfo<>();
        //根据发布时间排序
        pageInfo  = PageInfo.of(newsBulletinMapper.findOrderyReleaseDate(entity));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }

    @Override
    public NewsBulletin updateInfo(NewsBulletin info, Integer status) throws BusinessException {
        // 待发布(0)状态下可以任意修改
        if(info.getStatus() != 2){
            //更新状态为1(已发布) 设置发布时间
            if(status ==1){
                info.setReleaseDate(System.currentTimeMillis());
            }
            newsBulletinMapper.updateByPrimaryKey(info);
            info.setStatus(status);
            info.setIsTimedJob(false);
            newsBulletinMapper.updateByPrimaryKey(info);
        }
        return info;
    }
}
