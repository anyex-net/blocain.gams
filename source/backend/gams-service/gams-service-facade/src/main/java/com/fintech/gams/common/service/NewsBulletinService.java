/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.bean.GenericService;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.common.entity.NewsBulletin;
import com.fintech.gams.common.model.NewsBulletinModel;
import com.fintech.gams.exception.BusinessException;

/**
 * 快讯信息 服务接口
 * <p>File：NewsBulletinService.java </p>
 * <p>Title: NewsBulletinService </p>
 * <p>Description:NewsBulletinService </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
public interface NewsBulletinService extends GenericService<NewsBulletin>{

    /**
     *定时发布
     * @throws BusinessException
     */
    void scheduleRelease() throws BusinessException;

    /**
     * 根据更新时间排序
     * @param pagin
     * @param entity
     * @return
     * @throws BusinessException
     */
    PaginateResult<NewsBulletin> findOrderyUpdateDate(Pagination pagin, NewsBulletinModel entity) throws BusinessException;

    /**
     * 根据发布时间排序
     * @param
     * @return
     */
    PaginateResult<NewsBulletin> findOrderyReleaseDate(Pagination pagin, NewsBulletinModel entity);

    /**
     * 更新消息公告
     * @param info
     * @param status
     * @return
     * @throws BusinessException
     */
    NewsBulletin updateInfo(NewsBulletin info, Integer status) throws BusinessException;

}
