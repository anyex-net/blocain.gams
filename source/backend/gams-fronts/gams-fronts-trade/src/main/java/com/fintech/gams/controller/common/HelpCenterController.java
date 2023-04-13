/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.controller.common;

import com.fintech.gams.common.entity.Dictionary;
import com.fintech.gams.common.model.HelpCenterTreeModel;
import com.fintech.gams.common.service.DictionaryService;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.utils.StringUtils;
import com.sun.org.apache.xpath.internal.SourceTree;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.common.entity.HelpCenter;
import com.fintech.gams.common.service.HelpCenterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;

/**
 * 帮助中心 控制器
 * <p>File：HelpCenterController.java </p>
 * <p>Title: HelpCenterController </p>
 * <p>Description:HelpCenterController </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.COMMON)
@Api(description = "帮助中心")
public class HelpCenterController extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = HelpCenterService.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private HelpCenterService helpCenterService;

    @Autowired(required = false)
    private DictionaryService dictionaryService;

    @GetMapping(value = "/helpCenter/findBy")
    @ApiOperation(value = "根据ID取帮助中心", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, helpCenterService.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/helpCenter/data")
    @ApiOperation(value = "查询帮助中心", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute HelpCenter entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        entity.setStatus(1);
        String lang = StringUtils.isBlank(entity.getLang()) ? "en-US" : entity.getLang();
        entity.setLang(lang);
        PaginateResult<HelpCenter> result = helpCenterService.search(pagin,entity);
        for (HelpCenter helpCenter : result.getList()) {
            // 查询父节点字典表
            HelpCenterTreeModel problemType = dictionaryService.findByCodeAndValue("helpCenterType", helpCenter.getProblemType(), lang);
            // 查询子节点字典表
            HelpCenterTreeModel problemLevelOne = dictionaryService.findByCodeAndValue(helpCenter.getProblemType(), helpCenter.getProblemLevelOne(), lang);
            helpCenter.setParent(problemType);
            problemType.setChildrenInfo(problemLevelOne);
        }
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/helpCenter/classifyTree")
    @ApiOperation(value = "查询帮助中心分类菜单栏", httpMethod = "POST")
    public JsonMessage classifyTree(String lang) throws BusinessException {
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        // 查询父节点字典表
        List<HelpCenterTreeModel> helpCenterType = dictionaryService.findByCodeAndTree("helpCenterType", lang);
        List<HelpCenterTreeModel> dictionaryList = new ArrayList<>();
        HelpCenterTreeModel dict;
        for (HelpCenterTreeModel dictionary : helpCenterType) {
            dict = new HelpCenterTreeModel();
            dict.setId(dictionary.getId());
            dict.setDictCode(dictionary.getDictCode());
            dict.setDictName(dictionary.getDictName());
            dict.setDictValue(dictionary.getDictValue());
            dict.setDictDest(dictionary.getDictDest());
            dict.setLang(dictionary.getLang());
            dict.setSortNum(dictionary.getSortNum());
            dict.setCreateBy(dictionary.getCreateBy());
            dict.setCreateDate(dictionary.getCreateDate());
            dict.setUpdateBy(dictionary.getUpdateBy());
            dict.setUpdateDate(dictionary.getUpdateDate());
            // el-icon-menu
            dict.setIconCls("el-icon-menu");
            // 查询子节点字典表
            List<HelpCenterTreeModel> byCode = dictionaryService.findByCodeAndTree(dictionary.getDictValue(), lang);
            dict.setChildren(byCode);
            dictionaryList.add(dict);
        }
        return getJsonMessage(CommonEnums.SUCCESS, dictionaryList);
    }

    @PostMapping(value = "/helpCenter/classify/info")
    @ApiOperation(value = "查询帮助中心分类内容信息列表", httpMethod = "POST")
    public JsonMessage classifyInfo(@ApiParam(required = true, name = "problemType", value = "问题类型(开户类，交易类..)") String problemType,
                                    @ApiParam(required = true, name = "problemLevelOne", value = "问题(一级)") String problemLevelOne,
                                    String lang, Pagination pagin) throws BusinessException {
        if (null == problemType || null == problemLevelOne) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        PaginateResult<HelpCenter> result = helpCenterService.classifyInfo(pagin, problemType, problemLevelOne, lang);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/helpCenter/search")
    @ApiOperation(value = "搜索帮助中心", httpMethod = "POST")
    public JsonMessage search(@ApiParam(required = true, name = "searchValue", value = "搜索框的值") String searchValue,
                              Pagination pagin, String lang) throws BusinessException {
        if (null == searchValue) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        lang = StringUtils.isBlank(lang) ? "en-US" : lang;
        PaginateResult<HelpCenter> result = helpCenterService.searchHelpCenter(searchValue, pagin, lang);
        for (HelpCenter helpCenter : result.getList()) {
            // 查询父节点字典表
            HelpCenterTreeModel problemType = dictionaryService.findByCodeAndValue("helpCenterType", helpCenter.getProblemType(), lang);
            // 查询子节点字典表
            HelpCenterTreeModel problemLevelOne = dictionaryService.findByCodeAndValue(helpCenter.getProblemType(), helpCenter.getProblemLevelOne(), lang);
            helpCenter.setParent(problemType);
            problemType.setChildrenInfo(problemLevelOne);
            // 高亮显示
            String highlight = "<em class=\"red-em\">" + searchValue + "</em>";
            // 搜索问题（二级）
            String problemLevelTwo = helpCenter.getProblemLevelTwo();
            // 搜索问题解答内容
            String searchContent = helpCenter.getSearchContent();
            helpCenter.setProblemLevelTwo(problemLevelTwo.replaceAll(searchValue, highlight));
            helpCenter.setSearchContent(searchContent.replaceAll(searchValue, highlight));
        }
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
