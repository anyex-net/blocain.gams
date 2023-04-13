package com.fintech.gams.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * TestTask Introduce
 * <p>File：TestTask.java</p>
 * <p>Title: TestTask</p>
 * <p>Description: TestTask</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Component
@TestComponent
public class TestTask
{
    //@Scheduled(cron="0 0/1 * * * ?")
    public void task1() throws RuntimeException
    {
        log.info( "task1开始测试任务");
        try
        {
            log.info( "task1开始执行任务");
            Thread.sleep(5000);// 每次休眠5秒
        }
        catch (Exception e)
        {
            log.error("task1测试任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "task1结束测试任务");
    }

    //@Scheduled(cron="0 0/1 * * * ?")
    public void task2() throws RuntimeException
    {
        log.info( "task2开始测试任务");
        try
        {
            log.info( "task2开始执行任务");
            Thread.sleep(5000);// 每次休眠5秒
        }
        catch (Exception e)
        {
            log.error("task2测试任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "task2结束测试任务");
    }
}
