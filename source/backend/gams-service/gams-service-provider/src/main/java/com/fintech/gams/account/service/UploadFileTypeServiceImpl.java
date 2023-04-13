/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.account.service;

import com.fintech.gams.bean.GenericServiceImpl;

import com.fintech.gams.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.account.entity.UploadFileType;
import com.fintech.gams.account.mapper.UploadFileTypeMapper;

import java.util.List;

/**
 * 上传文件类型 服务实现类
 * <p>File：UploadFileTypeServiceImpl.java </p>
 * <p>Title: UploadFileTypeServiceImpl </p>
 * <p>Description:UploadFileTypeServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = UploadFileTypeService.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class UploadFileTypeServiceImpl extends GenericServiceImpl<UploadFileType> implements UploadFileTypeService
{

    protected UploadFileTypeMapper uploadFileTypeMapper;

    @Autowired(required = false)
    public UploadFileTypeServiceImpl(UploadFileTypeMapper uploadFileTypeMapper)
    {
        super(uploadFileTypeMapper);
        this.uploadFileTypeMapper = uploadFileTypeMapper;
    }

    @Override
    public List<UploadFileType> findByKindArr(Long[] kindArr, String lang, String accountType) throws BusinessException {
        return uploadFileTypeMapper.findByKindArr(kindArr, lang, accountType);
    }
}
