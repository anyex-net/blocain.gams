/*
 * @(#)ZttxConst.java 2015-4-14 下午2:02:23
 * Copyright 2015 Playguy, Inc. All rights reserved. com.fintech
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.consts;

/**
 * <p>File：BizConst.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: BizConst (c) 2019 2019-9-10 下午2:02:23</p>
 * <p>Company: GAMS</p>
 *
 * @author SUN
 * @version 1.0
 */
public class BizConst
{
    private BizConst()
    {// 防止实例化
    }

    /**
     * 证券类型
     */
    public static final String  STOCK_TYPE_STOCK                            = "stock";
    public static final String  STOCK_TYPE_BOND                             = "bond";
    public static final String  STOCK_TYPE_FUND                             = "fund";
    public static final String  STOCK_TYPE_FOREIGNEX                        = "foreignEx";
    public static final String  STOCK_TYPE_FUTURES                          = "futures";
    public static final String  STOCK_TYPE_OPTION                           = "option";
    public static final String  STOCK_TYPE_WARRANT                          = "warrant";
}
