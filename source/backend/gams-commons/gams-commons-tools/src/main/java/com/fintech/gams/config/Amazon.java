package com.fintech.gams.config;

import lombok.Data;

/**
 * 亚马逊参数配置
 * <p>File: Amazon.java </p>
 * <p>Title: Amazon </p>
 * <p>Description: Amazon </p>
 * <p>Copyright: Copyright (c) 2019-05-29</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Data
public class Amazon
{
    /**
     * 密钥ID
     */
    private String accessKey;

    /**
     * 密钥
     */
    private String secretKey;

    private Bucket bucket = new Bucket();
}
