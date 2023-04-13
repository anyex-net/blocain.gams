/*
 * @(#)LongEditorSupport.java 2014-6-14 下午4:06:36
 * Copyright 2014 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.editor;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * <p>File：LongEditorSupport.java</p>
 * <p>Title: 长整数型参数处理</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2014 2014-6-14 下午4:06:36</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class LongEditorSupport extends PropertyEditorSupport
{
    @Override
    public void setAsText(String text) throws IllegalArgumentException
    {
        if (StringUtils.isBlank(text))
        {
            setValue(null);
        }
        else
        {
            text = Jsoup.clean(text, Whitelist.relaxed());
            try
            {
                setValue(Long.parseLong(text));
            }
            catch (NumberFormatException e)
            {
                setValue(null);
            }
        }
    }
    
    @Override
    public String getAsText()
    {
        Long value = (Long) getValue();
        if (null == value)
        {
            return null;
        }
        else
        {
            return value.toString();
        }
    }
}
