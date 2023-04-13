package com.fintech.gams.common;

import com.fintech.gams.common.service.NewsBulletinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author yann
 * @date 2019-12-06 14:51
 * @Description
 */
@Slf4j
@Component
@TestComponent
public class NewsBulletinTask {

    @Autowired(required = false)
    private NewsBulletinService newsBulletinService;

    /**
     * 定时发布消息公告
     * @throws RuntimeException
     */
    @Scheduled(cron="0 0/1 * * * ?")
    public void newsBulletin() throws RuntimeException
    {
        log.info( "newsBulletin开始任务");
        try
        {
            newsBulletinService.scheduleRelease();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
            log.error("newsBulletin任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "newsBulletin结束任务");
    }
}
