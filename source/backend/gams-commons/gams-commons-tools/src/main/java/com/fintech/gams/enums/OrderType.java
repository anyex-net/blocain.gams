package com.fintech.gams.enums;

import lombok.Getter;

/**
 * 订单类型
 */
@Getter
public enum OrderType
{
    LIMIT(0), // 限价
    MARKET(1); // 市价
    private byte code;
    
    OrderType(int code)
    {
        this.code = (byte) code;
    }
}
