<#include "/macro.include"/>
<#include "/copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${subpackage}.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import ${basepackage}.${subpackage}.entity.${className};
import ${basepackage}.${subpackage}.service.${className}Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * ${table.sqlRemark} 控制器
 * <p>File：${className}Controller.java </p>
 * <p>Title: ${className}Controller </p>
 * <p>Description:${className}Controller </p>
 * <p>Copyright: Copyright (c) May 26, 2015 </p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping("/${subpackage}/${classNameLower}")
@Api(description = "${table.sqlRemark}")
public class ${className}Controller extends GenericController
{
    @Autowired(required = false)
    //@SofaReference(interfaceType = ${className}Service.class, binding = @SofaReferenceBinding(bindingType = "bolt"),jvmFirst = false)
    private ${className}Service ${classNameLower}Service;

    @GetMapping(value = "/findBy")
    @RequiresPermissions("${subpackage}:${classNameLower}:data")
    @ApiOperation(value = "根据ID取${table.sqlRemark}", httpMethod = "GET")
    public JsonMessage findBy(Long id) throws BusinessException
    {
        if (null == id) {
            throw new BusinessException(CommonEnums.PARAMS_VALID_ERR);
        }
        return this.getJsonMessage(CommonEnums.SUCCESS, ${classNameLower}Service.selectByPrimaryKey(id));
    }

    @PostMapping(value = "/save")
    @RequiresPermissions("${subpackage}:${classNameLower}:operator")
    @ApiOperation(value = "保存${table.sqlRemark}", httpMethod = "POST")
    public JsonMessage save(@ModelAttribute ${className} info) throws BusinessException
    {
        JsonMessage json = getJsonMessage(CommonEnums.SUCCESS);
        if (beanValidator(json, info))
        {
            ${classNameLower}Service.save(info);
        }
        return json;
    }

    @PostMapping(value = "/data")
    @RequiresPermissions("${subpackage}:${classNameLower}:data")
    @ApiOperation(value = "查询${table.sqlRemark}", httpMethod = "POST")
    public JsonMessage data(@ModelAttribute ${className} entity,@ModelAttribute Pagination pagin) throws BusinessException
    {
        PaginateResult<${className}> result = ${classNameLower}Service.search(pagin,entity);
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }

    @PostMapping(value = "/del")
    @RequiresPermissions("${subpackage}:${classNameLower}:operator")
    @ApiOperation(value = "根据指定ID删除", httpMethod = "POST")
    @ApiImplicitParam(name = "ids", value = "以','分割的编号组", paramType = "form")
    public JsonMessage del(String ids) throws BusinessException
    {
        ${classNameLower}Service.removeBatch(ids.split(","));
        return getJsonMessage(CommonEnums.SUCCESS);
    }
}
