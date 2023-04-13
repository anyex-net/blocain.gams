package com.fintech.gams.config;

import lombok.Data;

/**
 * 文件存储参数配置
 * <p>File: Bucket.java </p>
 * <p>Title: Bucket </p>
 * <p>Description: Bucket </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class Bucket
{
    /**
     * 正式空间名称
     */
    private String releaseName;
    
    /**
     * 正式空间地址
     */
    private String releaseUrl;
    
    /**
     * 临时空间名称
     */
    private String tempName;
    
    /**
     * 临时空间地址
     */
    private String tempUrl;
}