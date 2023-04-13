package com.fintech.gams.ib.service;

import com.fintech.gams.exception.BusinessException;
import com.ib.client.ExecutionFilter;

public interface IbCoreRequestService
{
    /**
     * reqManagedAccounts->IB(managedAccounts())
     */
    void reqManagedAccounts() throws BusinessException;

    /**
     * reqFamilyCodes->IB(familyCodes())
     */
    void reqFamilyCodes() throws BusinessException;

    /**
     * reqAccountUpdates->IB(updateAccountValue()、updatePortfolio())
     */
    void reqAccountUpdates() throws InterruptedException, BusinessException;

    /**
     * reqAccountUpdatesMulti->IB(accountUpdateMulti())
     */
    void reqAccountUpdatesMulti() throws InterruptedException, BusinessException;

    /**
     * reqPositions->IB(position())
     */
    void reqPositions() throws InterruptedException, BusinessException;

    /**
     * reqPositionsMulti->IB(positionMulti())
     */
    void reqPositionsMulti() throws BusinessException;

    /**
     * reqPnLSingle->IB(pnlSingle())
     */
    void reqPnLSingle() throws InterruptedException, BusinessException;

    /**
     * reqPnL->IB(pnl())
     */
    void reqPnL() throws InterruptedException, BusinessException;

    /**
     * reqAccountSummary->IB(accountSummary())
     */
    void reqAccountSummary() throws InterruptedException, BusinessException;

    /**
     * reqExecutions->IB(execDetails())
     */
    void reqExecutions(ExecutionFilter executionFilter) throws BusinessException;

    /**
     * 股票行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickStock() throws InterruptedException, BusinessException;

    /**
     * 债券行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickBond() throws InterruptedException, BusinessException;

    /**
     * 基金行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickFund() throws InterruptedException, BusinessException;

    /**
     * 外汇行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickForeignEx() throws InterruptedException, BusinessException;

    /**
     * 期货行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickFutures() throws InterruptedException, BusinessException;

    /**
     * 期权行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickOption() throws InterruptedException, BusinessException;

    /**
     * 权证行情
     * @throws InterruptedException
     * @throws BusinessException
     */
    void reqTickWarrant() throws InterruptedException, BusinessException;
}
