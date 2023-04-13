/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.common.service;

import com.fintech.gams.common.consts.OperationManageConsts;
import com.fintech.gams.common.model.ThumbsUpModel;
import com.fintech.gams.consts.DateConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.bean.GenericServiceImpl;
import com.fintech.gams.common.entity.Express;
import com.fintech.gams.common.mapper.ExpressMapper;
import com.fintech.gams.exception.BusinessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * 快讯信息 服务实现类
 * <p>File：ExpressServiceImpl.java </p>
 * <p>Title: ExpressServiceImpl </p>
 * <p>Description:ExpressServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
public class ExpressServiceImpl extends GenericServiceImpl<Express> implements ExpressService
{
    protected ExpressMapper expressMapper;
    
    @Autowired(required = false)
    public ExpressServiceImpl(ExpressMapper expressMapper)
    {
        super(expressMapper);
        this.expressMapper = expressMapper;
    }
    
    @Override
    public Express detail(Long id) throws BusinessException
    {
        return null == id ? null : expressMapper.detail(id);
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void thumbsUpOrCancel(String ip, ThumbsUpModel model) throws BusinessException {
        if (model.getThumbsUpType()) {
            // 点赞
            Express express = expressMapper.selectByPrimaryKey(model.getId());
            express.setThumbsUp(express.getThumbsUp() + 1);
            // 不可重复点
            if (RedisUtils.get(OperationManageConsts.EXPRESS_ + ip + OperationManageConsts.UNDERLINE + model.getId()) != null) {
                //throw new BusinessException(2011, "请勿重复点赞");
                throw new BusinessException(CommonEnums.SUCCESS);
            }
            expressMapper.updateByPrimaryKeySelective(express);
            // 避免刷赞 存redis 中 (express_电脑ip_快讯id) ,设置缓存  24小时过期
            RedisUtils.putObject(OperationManageConsts.EXPRESS_ + ip + OperationManageConsts.UNDERLINE + model.getId(), "1", 60 * 60 * 24);
        } else {
            Express express = expressMapper.selectByPrimaryKey(model.getId());
            if (0 == express.getThumbsUp()) {
                throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
            }
            // 点过赞的才能取消点赞
            if (null == RedisUtils.get(OperationManageConsts.EXPRESS_ + ip + OperationManageConsts.UNDERLINE + model.getId())) {
                // throw new BusinessException(CommonEnums.ERROR_ILLEGAL_REQUEST);
                throw new BusinessException(CommonEnums.SUCCESS);
            }
            // 取消点赞
            express.setThumbsUp(express.getThumbsUp() - 1);
            expressMapper.updateByPrimaryKeySelective(express);
            RedisUtils.del(OperationManageConsts.EXPRESS_ + ip + OperationManageConsts.UNDERLINE + model.getId());
        }
    }

    @Override
    public List<Express> hotReading(String lang) throws BusinessException {
        // 热门阅读 月榜(过去30天) 以30天算
        Timestamp proHour = getProHour(new Timestamp(System.currentTimeMillis()), 30 * 24);
        String dateTime = dateTimeStamp(proHour.toString(), DateConst.DATE_FORMAT_YMDHMS);
        long time = Long.parseLong(dateTime);
        return expressMapper.hotReading(lang, time);
    }

    /**
     * 获取过去小时的时间
     *
     * @param currentdate
     * @param proTime
     * @return
     */
    private static Timestamp getProHour(Timestamp currentdate, Integer proTime) {
        Calendar cc = Calendar.getInstance();
        cc.setTime(currentdate);
        cc.set(Calendar.HOUR_OF_DAY, cc.get(Calendar.HOUR_OF_DAY) - proTime);
        return new Timestamp(cc.getTimeInMillis());
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    private static String dateTimeStamp(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
