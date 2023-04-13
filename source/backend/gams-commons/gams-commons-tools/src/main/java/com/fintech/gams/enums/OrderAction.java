package com.fintech.gams.enums;

import lombok.Getter;

/**
 * 买卖方向
 * <p>File: OrderAction.java </p>
 * <p>Title: OrderAction </p>
 * <p>Description: OrderAction </p>
 * <p>Copyright: Copyright (c) 2018-12-04</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Getter
public enum OrderAction
{
    BUY(0), // 买入
    SELL(1), // 卖出
    CANCEL(2); // 撤单
    private int code;
    
    OrderAction(int code)
    {
        this.code = code;
    }
    
    /**
     * 判断是否是买单
     * @return
     */
    public boolean isBuy()
    {
        return this == BUY ? true : false;
    }
    
    /**
    * 判断是否是卖单
    * @return
    */
    public boolean isSell()
    {
        return this == SELL ? true : false;
    }
    
    /**
     * 判断是否是撤单
     * @return
     */
    public boolean isCancel()
    {
        return this == CANCEL ? true : false;
    }
}
