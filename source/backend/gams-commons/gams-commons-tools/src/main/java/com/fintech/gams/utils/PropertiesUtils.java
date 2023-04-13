/*
 * @(#)Dialect.java 2015-4-17 下午3:29:26
 * Copyright 2015 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>File：PropertiesUtils.java</p>
 * <p>Title: PropertiesUtils</p>
 * <p>Description:
 *  Properties文件载入工具类. 可载入多个properties文件,
 *  相同的属性在最后载入的文件中的值将会覆盖之前的值，但以System的Property优先.</p>
 * <p>Copyright: Copyright (c) 2015/04/18 11:29</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
public class PropertiesUtils
{
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    
    private final Properties      properties;
    
    public PropertiesUtils(String ... resourcesPaths)
    {
        properties = loadProperties(resourcesPaths);
    }
    
    public Properties getProperties()
    {
        return properties;
    }
    
    /**
     * 取出Property，但以System的Property优先,取不到返回空字符串.
     */
    private String getValue(String key)
    {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null)
        { return systemProperty; }
        if (properties.containsKey(key))
        { return properties.getProperty(key); }
        return "";
    }
    
    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public String getProperty(String key)
    {
        String value = getValue(key);
        if (value == null)
        { throw new NoSuchElementException(); }
        return value;
    }
    
    /**
     * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
     */
    public String getProperty(String key, String defaultValue)
    {
        String value = getValue(key);
        return value != null ? value : defaultValue;
    }
    
    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Integer getInteger(String key)
    {
        String value = getValue(key);
        if (value == null)
        { throw new NoSuchElementException(); }
        return Integer.valueOf(value);
    }
    
    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public Integer getInteger(String key, Integer defaultValue)
    {
        String value = getValue(key);
        return StringUtils.isNotBlank(value) ? Integer.valueOf(value) : defaultValue;
    }
    
    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Double getDouble(String key)
    {
        String value = getValue(key);
        if (value == null)
        { throw new NoSuchElementException(); }
        return Double.valueOf(value);
    }
    
    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Long getLong(String key)
    {
        String value = getValue(key);
        if (value == null)
        { throw new NoSuchElementException(); }
        return Long.valueOf(value);
    }
    
    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public Long getLong(String key, long defaultValue)
    {
        String value = getValue(key);
        return value != null ? Long.valueOf(value) : defaultValue;
    }
    
    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public Double getDouble(String key, Integer defaultValue)
    {
        String value = getValue(key);
        return value != null ? Double.valueOf(value) : defaultValue;
    }
    
    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.
     */
    public Boolean getBoolean(String key)
    {
        String value = getValue(key);
        if (value == null)
        { throw new NoSuchElementException(); }
        return Boolean.valueOf(value);
    }
    
    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/false则返回false.
     */
    public Boolean getBoolean(String key, boolean defaultValue)
    {
        String value = getValue(key);
        return value != null ? Boolean.valueOf(value) : defaultValue;
    }
    
    /**
     * 载入多个文件, 文件路径使用Spring Resource格式.
     */
    private Properties loadProperties(String ... resourcesPaths)
    {
        Properties props = new Properties();
        for (String location : resourcesPaths)
        {
            InputStream is = null;
            try
            {
                Resource resource = resourceLoader.getResource(location);
                is = resource.getInputStream();
                props.load(is);
                is.close();
            }
            catch (IOException ex)
            {
                log.warn("Could not load properties from path:" + location + ", " + ex.getMessage());
            }
        }
        return props;
    }
}