<#include "/macro.include"/>
<#include "/copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${subpackage}.service;

import com.fintech.gams.bean.GenericServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.${subpackage}.entity.${className};
import ${basepackage}.${subpackage}.mapper.${className}Mapper;

/**
 * ${table.sqlRemark} 服务实现类
 * <p>File：${className}ServiceImpl.java </p>
 * <p>Title: ${className}ServiceImpl </p>
 * <p>Description:${className}ServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Service
//@SofaService(interfaceType = ${className}Service.class, bindings = {@SofaServiceBinding(bindingType = "bolt")})
public class ${className}ServiceImpl extends GenericServiceImpl<${className}> implements ${className}Service
{

    protected ${className}Mapper ${classNameLower}Mapper;

    @Autowired(required = false)
    public ${className}ServiceImpl(${className}Mapper ${classNameLower}Mapper)
    {
        super(${classNameLower}Mapper);
        this.${classNameLower}Mapper = ${classNameLower}Mapper;
    }
}
