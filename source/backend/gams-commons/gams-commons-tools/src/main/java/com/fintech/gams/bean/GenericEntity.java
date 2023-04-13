package com.fintech.gams.bean;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>File：BaseEntity.java</p>
 * <p>Title: BaseEntity</p>
 * <p>Description:基础实体对象</p>
 * <p>Copyright: Copyright (c) 2015/04/21 11:52</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Getter
@Setter
public abstract class GenericEntity implements Serializable
{
    /**
     * 主键编号
     */
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    protected Long    id;
    
    /**
     * 数据版本号
     */
    @JsonIgnore
    protected Long    version = Long.valueOf(1);
    
    /**
     * 时间字段
     */
    @JsonIgnore
    private String    dateField;
    
    /**
     * 开始时间
     */
    @JsonIgnore
    private Long      startDate;
    
    /**
     * 结束时间
     */
    @JsonIgnore
    private Long      endDate;
    
    /**
     * 删除标识
     */
    @JsonIgnore
    protected Boolean delFlag = false;
}
