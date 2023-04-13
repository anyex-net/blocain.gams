/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.account;

import com.fintech.gams.account.entity.AccountParCrsEntityContr;
import com.fintech.gams.account.model.PartnerShipCrsEntityContrModel;
import com.fintech.gams.account.service.AccountParCrsEntityContrService;
import com.fintech.gams.shiro.model.UserPrincipal;
import com.fintech.gams.utils.OnLineUserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.account.entity.AccountParCrsEntity;
import com.fintech.gams.account.service.AccountParCrsEntityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.util.List;

/**
 * 合伙企业CRS实体自我证明表格(CRS-CP) 控制器
 * <p>File：AccountParCrsEntityController.java </p>
 * <p>Title: AccountParCrsEntityController </p>
 * <p>Description:AccountParCrsEntityController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/account/accountParCrsEntity")
@Api(description = "合伙企业CRS实体自我证明表格(CRS-CP)")
public class AccountParCrsEntityController extends GenericController {
    @Autowired(required = false)
    //@SofaReference(interfaceType = AccountParCrsEntityService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private AccountParCrsEntityService accountParCrsEntityService;

    @Autowired(required = false)
    private AccountParCrsEntityContrService accountParCrsEntityContrService;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("account:accountParCrsEntity:data")
    @ApiOperation(value = "根据ID取合伙企业CRS实体自我证明表格(CRS-CP)", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParCrsEntityService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("account:accountParCrsEntity:operator")
    @ApiOperation(value = "保存合伙企业CRS实体自我证明表格(CRS-CP)", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute AccountParCrsEntity info) throws BusinessException {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info)) {
            accountParCrsEntityService.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("account:accountParCrsEntity:data")
    @ApiOperation(value = "查询合伙企业CRS实体自我证明表格(CRS-CP)", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute AccountParCrsEntity entity, @ModelAttribute Pagination pagin) throws BusinessException {
        PaginateResult<AccountParCrsEntity> result = accountParCrsEntityService.search(pagin, entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("account:accountParCrsEntity:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException {
        accountParCrsEntityService.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }

    @GetMapping(value = "/findByAccountId")
    @ApiOperation(value = "根据AccountId取合伙企业CRS实体自我证明表格结果集", httpMethod = "GET")
    public JsonMessage findByAccountId() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParCrsEntity accountParCrsEntity = new AccountParCrsEntity();
        accountParCrsEntity.setAccountId(principal.getId());
        List<AccountParCrsEntity> parCrsEntityServiceList = accountParCrsEntityService.findList(accountParCrsEntity);
        //合伙企业crs实体（CRS-E）控权人信息
        AccountParCrsEntityContr accountParCrsEntityContr = new AccountParCrsEntityContr();
        accountParCrsEntityContr.setAccountId(principal.getId());
        List<AccountParCrsEntityContr> accountParCrsEntityContrList = accountParCrsEntityContrService.findList(accountParCrsEntityContr);
        PartnerShipCrsEntityContrModel model = new PartnerShipCrsEntityContrModel();
        for (AccountParCrsEntity crsEntity : parCrsEntityServiceList) {
            model.setAccountParCrsEntity(crsEntity);
        }
        if (parCrsEntityServiceList.size() == 0) {
            return this.getJsonMessage(CommonEnums.SUCCESS, "");
        }
        model.setAccountParCrsEntityContrList(accountParCrsEntityContrList);
        return this.getJsonMessage(CommonEnums.SUCCESS, model);
    }

    @GetMapping(value = "/findCrsEntity")
    @ApiOperation(value = "根据AccountId取CRS实体自我证明表格", httpMethod = "GET")
    public JsonMessage findCrsEntity() throws BusinessException {
        UserPrincipal principal = OnLineUserUtils.getPrincipal();
        AccountParCrsEntity accountParCrsEntity = new AccountParCrsEntity();
        accountParCrsEntity.setAccountId(principal.getId());
        List<AccountParCrsEntity> accountParCrsEntitieList = accountParCrsEntityService.findList(accountParCrsEntity);
        return this.getJsonMessage(CommonEnums.SUCCESS, accountParCrsEntitieList);
    }
}
