package com.fintech.gams.consts;

/**
 * 队列常量
 * <p>File: QueueConst.java </p>
 * <p>Title: QueueConst </p>
 * <p>Description: QueueConst </p>
 * <p>Copyright: Copyright (c) 2018-12-04</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public class QueueConst
{
    /**
     * RPC队列
     */
    public static final String QUEUE_RPC_KEY        = "exchange_queue_rpc";
    
    /**
     * 撮合队列
     */
    public static final String QUEUE_MATCH_KEY      = "exchange_queue_match";
    
    /**
     * 行情队列
     */
    public static final String QUEUE_QUOTATION_KEY  = "exchange_queue_quotation";
    
    /**
     * SOCKET队列
     */
    public static final String QUEUE_SOCKET_KEY     = "exchange_queue_socket";
    
    /**
     * 订单备份队列
     * <p>
     *     存储在REDIS中
     * </p>
     */
    public static final String QUEUE_ORDER_BAK_KEY  = "exchange_backup_order";
}
