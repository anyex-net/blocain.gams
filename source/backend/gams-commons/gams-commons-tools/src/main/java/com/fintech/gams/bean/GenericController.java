package com.fintech.gams.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import com.fintech.gams.editor.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.jackson.DecimalSerialize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * <p>File：GenericController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-21 下午1:46:45</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public abstract class GenericController
{
    /**
     * 验证Bean实例对象
     */
    @Autowired(required = false)
    protected Validator  validator;
    
    @Autowired(required = false)
    private ObjectMapper objectMapper;

    /**
     * 初始化数据绑定
     * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
     * 2. 将字段中Date类型转换为String类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        binder.setAutoGrowCollectionLimit(Integer.MAX_VALUE);
        // Byte
        binder.registerCustomEditor(Byte.class, new ByteEditorSupport());
        // Float
        binder.registerCustomEditor(Float.class, new FloatEditorSupport());
        // Double
        binder.registerCustomEditor(Double.class, new DoubleEditorSupport());
        // Long
        binder.registerCustomEditor(Long.class, new LongEditorSupport());
        // Integer
        binder.registerCustomEditor(Integer.class, new IntegerEditorSupport());
        // Boolean
        binder.registerCustomEditor(Boolean.class, new BooleanEditorSupport());
        // Short
        binder.registerCustomEditor(Short.class, new ShortEditorSupport());
        // String
        binder.registerCustomEditor(String.class, new StringEditorSupport());
    }

    /**
     * 服务端参数有效性验证
     *
     * @param jsonMessage 错误信息（不能为null）
     * @param object      验证的实体对象
     * @param groups      验证组
     * @return 验证成功：返回true；严重失败：将错误信息添加到 jsonMessage rows 中
     */
    protected boolean beanValidator(JsonMessage jsonMessage, Object object, Class<?> ... groups)
    {
        try
        {
            BeanValidators.validateWithException(validator, object, groups);
        }
        catch (ConstraintViolationException ex)
        {
            List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
            jsonMessage.setCode(CommonEnums.FAIL.getCode());
            // jsonMessage.setMessage(CommonEnums.FAIL.getMessage());
            jsonMessage.setMessage(ex.getLocalizedMessage());
            jsonMessage.setObject(list);
            return false;
        }
        return true;
    }
    
    /**
     * 分页查询记录集：API接口返回分页及记录集的JSON处理，不返回状态码描述
     * @param describable 异常码
     * @param result 结果集及Pagination对象
     * @return JsonMessage JsonMessage
     */
    protected <T extends Object> JsonMessage getJsonMessage(EnumDescribable describable, PaginateResult<T> result)
    {
        JsonMessage json = getJsonMessage(describable);
        if (null != result)
        {
            Pagination page = result.getPage();
            json.setRows(result.getList());
            json.setTotal(result.getPage().getTotalRows());
            json.setHasNext(page.getHasNextPage());
            json.setHasPrevious(page.getHasPreviousPage());
            json.setPages(page.getPage());
            json.setTotalPage(page.getTotalPage());
            json.setMessage(json.getMessage());
        }
        return json;
    }
    
    /**
     * 接口处理结果反馈
     *
     * @param describable 异常代码描述
     * @param object      单结果返回对象
     * @return
     * @author Playguy
     */
    protected JsonMessage getJsonMessage(EnumDescribable describable, Object object)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        jsonMessage.setObject(object);
        return jsonMessage;
    }
    
    /**
     * 接口处理结果反馈 : ：API接口返回数据或交易处理结果的JSON处理
     *
     * @param describable 异常代码描述
     * @return JsonMessage JsonMessage
     * @author Playguy
     */
    public JsonMessage getJsonMessage(EnumDescribable describable)
    {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setCode(describable.getCode());
        jsonMessage.setMessage(describable.getMessage());
        return jsonMessage;
    }
    
    /**
     * 分页查询记录集：API接口返回分页及记录集的JSON处理，不返回状态码描述(过滤BigDecimal为小数长度为n的字符串)
     * @param describable 异常码
     * @param result 结果集及Pagination对象
     * @return JsonMessage JsonMessage
     */
    protected <T extends Object> String getJsonFilterMessage(EnumDescribable describable, PaginateResult<T> result, int scacle) throws BusinessException
    {
        objectMapper.registerModules(new SimpleModule().addSerializer(BigDecimal.class, new DecimalSerialize(scacle)));
        JsonMessage json = getJsonMessage(describable);
        try
        {
            if (null != result)
            {
                Pagination page = result.getPage();
                json.setRows(result.getList());
                json.setTotal(result.getPage().getTotalRows());
                json.setHasNext(page.getHasNextPage());
                json.setHasPrevious(page.getHasPreviousPage());
                json.setPages(page.getPage());
                json.setTotalPage(page.getTotalPage());
                json.setMessage(json.getMessage());
            }
            return objectMapper.writeValueAsString(json);
        }
        catch (JsonProcessingException e)
        {
            throw new BusinessException(e.getMessage());
        }
    }
}
