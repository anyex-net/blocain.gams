package com.fintech.gams.common;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fintech.gams.BaseServiceImplTest;
import com.fintech.gams.bean.AmazonS3;
import com.fintech.gams.exception.BusinessException;

/**
 * AmazonS3Test
 * <p>File: AmazonS3Test.java </p>
 * <p>Title: AmazonS3Test </p>
 * <p>Description: AmazonS3Test </p>
 * <p>Copyright: Copyright (c) 2019-06-05</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class AmazonS3Test extends BaseServiceImplTest
{
    @Autowired
    private AmazonS3 amazonS3;
    
    @Test
    public void upload() throws BusinessException
    {
        amazonS3.upload("/Users/playguy/Documents/test.png","test.png");
    }
}
