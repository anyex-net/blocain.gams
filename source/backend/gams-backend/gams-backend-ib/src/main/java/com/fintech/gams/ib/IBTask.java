package com.fintech.gams.ib;

import com.fintech.gams.ib.service.IbCoreRequestService;
import com.ib.client.ExecutionFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * IBTask Introduce
 * <p>File：IBTask.java</p>
 * <p>Title: IBTask</p>
 * <p>Description: IBTask</p>
 * <p>Copyright: Copyright (c) 2017/8/3</p>
 * <p>Company: GAMS</p>
 *
 * @author SUN
 * @version 1.0
 */
@Slf4j
@Component
public class IBTask
{
    @Autowired
    private IbCoreRequestService ibCoreRequestService;

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqManagedAccounts() throws RuntimeException
    {
        log.info( "reqManagedAccounts开始任务");
        try
        {
            ibCoreRequestService.reqManagedAccounts();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqManagedAccounts任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqManagedAccounts结束任务");
    }

    @Scheduled(cron="0 0/10 * * * ?")
    public void reqFamilyCodes() throws RuntimeException
    {
        log.info( "reqFamilyCodes开始任务");
        try
        {
            ibCoreRequestService.reqFamilyCodes();
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqFamilyCodes任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqFamilyCodes结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqAccountUpdates() throws RuntimeException
    {
        log.info( "reqAccountUpdates开始任务");
        try
        {
            ibCoreRequestService.reqAccountUpdates();
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqAccountUpdates任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqAccountUpdates结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqAccountUpdatesMulti() throws RuntimeException
    {
        log.info( "reqAccountUpdatesMulti开始任务");
        try
        {
            ibCoreRequestService.reqAccountUpdatesMulti();
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqAccountUpdatesMulti任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqAccountUpdatesMulti结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqPositions() throws RuntimeException
    {
        log.info( "reqPositions开始任务");
        try
        {
            ibCoreRequestService.reqPositions();
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqPositions任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqPositions结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqPositionsMulti() throws RuntimeException
    {
        log.info( "reqPositionsMulti开始任务");
        try
        {
            ibCoreRequestService.reqPositionsMulti();
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqPositionsMulti任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqPositionsMulti结束任务");
    }

    //@Scheduled(cron="0 0/1 * * * ?")
//    public void reqPnLSingle() throws RuntimeException
//    {
//        log.info( "reqPnLSingle开始任务");
//        try
//        {
//            ibCoreRequestService.reqPnLSingle();
//            Thread.sleep(11000);
//        }
//        catch (Exception e)
//        {
//            log.error("reqPnLSingle任务失败：{}", e.getLocalizedMessage());
//        }
//        log.info( "reqPnLSingle结束任务");
//    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqPnL() throws RuntimeException
    {
        log.info( "reqPnL开始任务");
        try
        {
            ibCoreRequestService.reqPnL();
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqPnL任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqPnL结束任务");
    }

    //@Scheduled(cron="0 0/1 * * * ?")
//    public void reqAccountSummary() throws RuntimeException
//    {
//        log.info( "reqAccountSummary开始任务");
//        try
//        {
//            ibCoreRequestService.reqAccountSummary();
//            Thread.sleep(11000);
//        }
//        catch (Exception e)
//        {
//            log.error("reqAccountSummary任务失败：{}", e.getLocalizedMessage());
//        }
//        log.info( "reqAccountSummary结束任务");
//    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqExecutions() throws RuntimeException
    {
        log.info( "reqExecutions开始任务");
        try
        {
            ExecutionFilter executionFilter = new ExecutionFilter();
            //
            ibCoreRequestService.reqExecutions(executionFilter);
            Thread.sleep(11000);
        }
        catch (Exception e)
        {
            log.error("reqExecutions任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqExecutions结束任务");
    }

    @Scheduled(cron="0 0/2 * * * ?")
    public void reqTickStock() throws RuntimeException
    {
        log.info( "reqTickStock开始任务");
        try
        {
            // 股票
            ibCoreRequestService.reqTickStock();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickStock任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickStock结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqTickBond() throws RuntimeException
    {
        log.info( "reqTickBond开始任务");
        try
        {
            // 债券
            ibCoreRequestService.reqTickBond();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickBond任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickBond结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqTickFund() throws RuntimeException
    {
        log.info( "reqTickFund开始任务");
        try
        {
            // 基金
            ibCoreRequestService.reqTickFund();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickFund任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickFund结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqTickForeignEx() throws RuntimeException
    {
        log.info( "reqTickForeignEx开始任务");
        try
        {
            // 外汇
            ibCoreRequestService.reqTickForeignEx();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickForeignEx任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickForeignEx结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqTickFutures() throws RuntimeException
    {
        log.info( "reqTickFutures开始任务");
        try
        {
            // 期货
            ibCoreRequestService.reqTickFutures();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickFutures任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickFutures结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqTickOption() throws RuntimeException
    {
        log.info( "reqTickOption开始任务");
        try
        {
            // 期权
            ibCoreRequestService.reqTickOption();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickOption任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickOption结束任务");
    }

    @Scheduled(cron="0 0/5 * * * ?")
    public void reqTickWarrant() throws RuntimeException
    {
        log.info( "reqTickWarrant开始任务");
        try
        {
            // 权证
            ibCoreRequestService.reqTickWarrant();
            Thread.sleep(10000);
        }
        catch (Exception e)
        {
            log.error("reqTickWarrant任务失败：{}", e.getLocalizedMessage());
        }
        log.info( "reqTickWarrant结束任务");
    }
}
