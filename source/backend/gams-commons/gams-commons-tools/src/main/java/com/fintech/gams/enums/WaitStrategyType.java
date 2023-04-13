package com.fintech.gams.enums;

import com.lmax.disruptor.*;

/**
 * WaitStrategyType
 * <p>File: WaitStrategyType.java </p>
 * <p>Title: WaitStrategyType </p>
 * <p>Description: WaitStrategyType </p>
 * <p>Copyright: Copyright (c) 2018-12-07</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
public enum WaitStrategyType
{
    BUSY_SPIN, YIELDING, SLEEPING, BLOCKING;
    public WaitStrategy create()
    {
        switch (this)
        {
            case SLEEPING: // 适合用于异步日志类似的场景
                return new SleepingWaitStrategy();
            case BUSY_SPIN: // 用在事件处理线程比物理内核数目还要小的时候。例如：在禁用超线程技术的时候。
                return new BusySpinWaitStrategy();
            case YIELDING: // 适合用于低延迟的系统
                return new YieldingWaitStrategy();
            default: // 最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
                return new BlockingWaitStrategy();
        }
    }
}
