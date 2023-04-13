/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.mapper;

import com.fintech.gams.common.entity.NewsBulletin;
import com.fintech.gams.common.model.NewsBulletinModel;
import org.apache.ibatis.annotations.Mapper;
import com.fintech.gams.bean.GenericMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 消息公告 持久层接口
 * <p>File：NewsBulletinMapper.java </p>
 * <p>Title: NewsBulletinMapper </p>
 * <p>Description:NewsBulletinMapper </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface NewsBulletinMapper extends GenericMapper<NewsBulletin>
{
    /**
     * 查询数据
     * @param newsBulletinModel
     * @return
     */
    List<NewsBulletin> findOrderyUpdateDate(@Param("model") NewsBulletinModel newsBulletinModel);

    /**
     * 根据发布时间排序
     * @param newsBulletinModel
     * @return  List<NewsAnnounce>
     */
    List<NewsBulletin> findOrderyReleaseDate(@Param("model") NewsBulletinModel newsBulletinModel);

    /**
     * 查询定时任务列表信息
     * @param
     * @return
     */
    List<NewsBulletin> scheduleReleaseInfo();
}
