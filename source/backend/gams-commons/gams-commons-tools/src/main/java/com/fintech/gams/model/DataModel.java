package com.fintech.gams.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fintech.gams.enums.DataType;

import lombok.Data;

/**
 * 数据类型
 * <p>File: DataInfo.java </p>
 * <p>Title: DataInfo </p>
 * <p>Description: DataInfo </p>
 * <p>Copyright: Copyright (c) 2018-12-06</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataModel implements Serializable
{
    private static final long serialVersionUID = 1821469751858191941L;
    
    /**
     * 数据类型
     * 委托订单、成交流水（撮合）
     */
    protected DataType        dataType;
}
