package com.fintech.gams.ib.service;

import com.fintech.gams.consts.BizConst;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.ib.entity.IbAccountFamily;
import com.fintech.gams.stock.entity.StockInfo;
import com.fintech.gams.stock.service.StockInfoService;
import com.fintech.gams.utils.RedisUtils;
import com.fintech.gams.utils.StringUtils;
import com.ib.client.Contract;
import com.ib.client.ExecutionFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static java.lang.Math.abs;

@Slf4j
@Service
public class IbCoreRequestServiceImpl implements IbCoreRequestService
{
    @Autowired
    private IbCoreService           ibCoreService;

    @Autowired
    private IbAccountFamilyService  ibAccountFamilyService;

    @Autowired
    private StockInfoService        stockInfoService;

    /**
     * reqManagedAccounts->IB(managedAccounts())
     *
     * @throws BusinessException
     */
    @Override
    public void reqManagedAccounts() throws BusinessException
    {
        ibCoreService.getClient().reqManagedAccts();
    }

    /**
     * reqFamilyCodes->IB(familyCodes())
     */
    @Override
    public void reqFamilyCodes() throws BusinessException
    {
        ibCoreService.getClient().reqFamilyCodes();
    }

    /**
     * reqAccountUpdates->IB(updateAccountValue()、updatePortfolio())
     */
    @Override
    public void reqAccountUpdates() throws InterruptedException, BusinessException
    {
        IbAccountFamily ibAccountFamilySelect = new IbAccountFamily();
        List<IbAccountFamily> list = ibAccountFamilyService.findList(ibAccountFamilySelect);
        if(null != list && list.size() > 0){
            for (IbAccountFamily ibAccountFamily : list) {
                if(null != ibAccountFamily.getAccountId() && StringUtils.isNotEmpty(ibAccountFamily.getIbAccountCode())){
                    ibCoreService.getClient().reqAccountUpdates(true, ibAccountFamily.getIbAccountCode());

                    Thread.sleep(2000);
                    ibCoreService.getClient().reqAccountUpdates(false, ibAccountFamily.getIbAccountCode());
                }
            }
        }
    }

    /**
     * reqAccountUpdatesMulti->IB(accountUpdateMulti())
     */
    @Override
    public void reqAccountUpdatesMulti() throws InterruptedException, BusinessException
    {
        IbAccountFamily ibAccountFamilySelect = new IbAccountFamily();
        List<IbAccountFamily> list = ibAccountFamilyService.findList(ibAccountFamilySelect);
        if(null != list && list.size() > 0){
            for (IbAccountFamily ibAccountFamily : list) {
                if(null != ibAccountFamily.getAccountId() && StringUtils.isNotEmpty(ibAccountFamily.getIbAccountCode())){
                    Random random = new Random();
                    int reqId = abs(random.nextInt());

                    ibCoreService.getClient().reqAccountUpdatesMulti(reqId, ibAccountFamily.getIbAccountCode(), "Core", true);

                    Thread.sleep(2000);
                    ibCoreService.getClient().reqAccountUpdatesMulti(reqId, ibAccountFamily.getIbAccountCode(), "Core", false);
                }
            }
        }
    }

    /**
     * reqPositions->IB(position())
     */
    @Override
    public void reqPositions() throws InterruptedException, BusinessException
    {
        ibCoreService.getClient().reqPositions();

        Thread.sleep(2000);
        ibCoreService.getClient().cancelPositions();
    }

    /**
     * reqPositionsMulti->IB(positionMulti())
     */
    @Override
    public void reqPositionsMulti() throws BusinessException
    {
        IbAccountFamily ibAccountFamilySelect = new IbAccountFamily();
        List<IbAccountFamily> list = ibAccountFamilyService.findList(ibAccountFamilySelect);
        if(null != list && list.size() > 0){
            for (IbAccountFamily ibAccountFamily : list) {
                if(null != ibAccountFamily.getAccountId() && StringUtils.isNotEmpty(ibAccountFamily.getIbAccountCode())){
                    Random random = new Random();
                    int reqId = abs(random.nextInt());

                    ibCoreService.getClient().reqPositionsMulti(reqId, ibAccountFamily.getIbAccountCode(), "Core");
                }
            }
        }
    }

    /**
     * reqPnLSingle->IB(pnlSingle())
     */
    @Override
    public void reqPnLSingle() throws InterruptedException, BusinessException
    {
        IbAccountFamily ibAccountFamilySelect = new IbAccountFamily();
        List<IbAccountFamily> list = ibAccountFamilyService.findList(ibAccountFamilySelect);
        if(null != list && list.size() > 0){
            for (IbAccountFamily ibAccountFamily : list) {
                if(null != ibAccountFamily.getAccountId() && StringUtils.isNotEmpty(ibAccountFamily.getIbAccountCode())){
                    Random random = new Random();
                    int reqId = abs(random.nextInt());

                    ibCoreService.getClient().reqPnLSingle(reqId, ibAccountFamily.getIbAccountCode(), "", 268084);

                    Thread.sleep(2000);
                    ibCoreService.getClient().cancelPnLSingle(reqId);
                }
            }
        }
    }

    /**
     * reqPnL->IB(pnl())
     */
    @Override
    public void reqPnL() throws InterruptedException, BusinessException
    {
        IbAccountFamily ibAccountFamilySelect = new IbAccountFamily();
        List<IbAccountFamily> list = ibAccountFamilyService.findList(ibAccountFamilySelect);
        if(null != list && list.size() > 0){
            for (IbAccountFamily ibAccountFamily : list) {
                if(null != ibAccountFamily.getAccountId() && StringUtils.isNotEmpty(ibAccountFamily.getIbAccountCode())){
                    Random random = new Random();
                    int reqId = abs(random.nextInt());

                    // 记录reqId对应的accountCode
                    String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append(reqId).toString();
                    RedisUtils.putObject(cacheKey, ibAccountFamily.getIbAccountCode(), CacheConst.ONE_MINUTE_CACHE_TIME);

                    ibCoreService.getClient().reqPnL(reqId, ibAccountFamily.getIbAccountCode(), "");

                    Thread.sleep(2000);
                    ibCoreService.getClient().cancelPnL(reqId);
                }
            }
        }
    }

    /**
     * reqAccountSummary->IB(accountSummary())
     */
    @Override
    public void reqAccountSummary() throws InterruptedException, BusinessException
    {
        ibCoreService.getClient().reqAccountSummary(9001, "All",
                "AccountType,NetLiquidation,TotalCashValue,SettledCash,AccruedCash,BuyingPower,EquityWithLoanValue," +
                        "PreviousEquityWithLoanValue,GrossPositionValue,ReqTEquity,ReqTMargin,SMA,InitMarginReq,MaintMarginReq," +
                        "AvailableFunds,ExcessLiquidity,Cushion,FullInitMarginReq,FullMaintMarginReq,FullAvailableFunds," +
                        "FullExcessLiquidity,LookAheadNextChange,LookAheadInitMarginReq ,LookAheadMaintMarginReq,LookAheadAvailableFunds," +
                        "LookAheadExcessLiquidity,HighestSeverity,DayTradesRemaining,Leverage");

        Thread.sleep(2000);
        ibCoreService.getClient().cancelAccountSummary(9001);
    }

    /**
     * reqExecutions->IB(execDetails())
     */
    @Override
    public void reqExecutions(ExecutionFilter executionFilter) throws BusinessException
    {
        IbAccountFamily ibAccountFamilySelect = new IbAccountFamily();
        List<IbAccountFamily> list = ibAccountFamilyService.findList(ibAccountFamilySelect);
        if(null != list && list.size() > 0){
            for (IbAccountFamily ibAccountFamily : list) {
                if(null != ibAccountFamily.getAccountId() && StringUtils.isNotEmpty(ibAccountFamily.getIbAccountCode())){
                    Random random = new Random();
                    int reqId = abs(random.nextInt());

                    // 记录reqId对应的accountCode
                    executionFilter.acctCode(ibAccountFamily.getIbAccountCode());
                    //
                    String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append(reqId).toString();
                    RedisUtils.putObject(cacheKey, executionFilter.acctCode(), CacheConst.ONE_MINUTE_CACHE_TIME);

                    ibCoreService.getClient().reqExecutions(reqId, executionFilter);
                }
            }
        }
    }

    @Override
    public void reqTickStock() throws InterruptedException, BusinessException
    {
        log.info("reqTickStock start...");
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_STOCK);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.symbol(stockInfo.getIbSymbol());
                contract.secType("STK");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange(stockInfo.getStockExchange().getExchangeCode());
                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(5000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }
        log.info("reqTickStock end...");

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.symbol("IBM");
        contract.secType("STK");
        contract.currency("USD");
        contract.exchange("SMART");
        ibCoreService.getClient().reqMktData(1001, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1001);
        */
    }

    @Override
    public void reqTickBond() throws InterruptedException, BusinessException
    {
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_BOND);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.symbol(stockInfo.getIbSymbol());
                contract.secType("BOND");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange(stockInfo.getStockExchange().getExchangeCode());

                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(50000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.symbol("IBCID115702622");
        contract.secType("BOND");
        contract.currency("USD");
        contract.exchange("SMART");

        ibCoreService.getClient().reqMktData(1002, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1002);
        */
    }

    @Override
    public void reqTickFund() throws InterruptedException, BusinessException
    {
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_FUND);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.symbol(stockInfo.getIbSymbol());
                contract.secType("FUND");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange(stockInfo.getStockExchange().getExchangeCode());
                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(5000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.symbol("GWMIX");
        contract.secType("FUND");
        contract.currency("USD");
        contract.exchange("FUNDSERV");
        ibCoreService.getClient().reqMktData(1003, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1003);
        */
    }

    @Override
    public void reqTickForeignEx() throws InterruptedException, BusinessException
    {
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_FOREIGNEX);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.symbol(stockInfo.getIbSymbol());
                contract.secType("CASH");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange("IDEALPRO");

                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(5000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.symbol("EUR");
        contract.secType("CASH");
        contract.currency("GBP");
        contract.exchange("IDEALPRO");

        ibCoreService.getClient().reqMktData(1004, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1004);
        */
    }

    @Override
    public void reqTickFutures() throws InterruptedException, BusinessException
    {
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_FUTURES);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.localSymbol(stockInfo.getIbSymbol());
                contract.secType("FUT");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange(stockInfo.getStockExchange().getExchangeCode());
                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(5000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.localSymbol("GDUU9");
        contract.secType("FUT");
        contract.currency("USD");
        contract.exchange("HKFE");
        ibCoreService.getClient().reqMktData(1005, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1005);
        */
    }

    @Override
    public void reqTickOption() throws InterruptedException, BusinessException
    {
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_OPTION);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.localSymbol(stockInfo.getIbSymbol());
                contract.secType("OPT");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange(stockInfo.getStockExchange().getExchangeCode());
                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(5000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.localSymbol("ADTN  190920P00020000");
        contract.secType("OPT");
        contract.currency("USD");
        contract.exchange("SMART");
        ibCoreService.getClient().reqMktData(1006, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1006);
        */
    }

    @Override
    public void reqTickWarrant() throws InterruptedException, BusinessException
    {
        StockInfo stockInfoSelect = new StockInfo();
        stockInfoSelect.setStockType(BizConst.STOCK_TYPE_WARRANT);
        List<StockInfo> list = stockInfoService.findList(stockInfoSelect);
        if(null != list && list.size() > 0){
            for (StockInfo stockInfo : list) {
                Random random = new Random();
                int tickerId = abs(random.nextInt());

                // 记录reqId对应的StockInfoId
                String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
                RedisUtils.putObject(cacheKey, stockInfo, CacheConst.ONE_MINUTE_CACHE_TIME);

                Contract contract = new Contract();
                contract.localSymbol(stockInfo.getIbSymbol());
                contract.secType("WAR");
                contract.currency(stockInfo.getBaseCurrency());
                contract.exchange(stockInfo.getStockExchange().getExchangeCode());
                ibCoreService.getClient().reqMktData(tickerId, contract, "", false, false, null);

                Thread.sleep(5000);
                ibCoreService.getClient().cancelMktData(tickerId);
            }
        }

        /*** Requesting real time market data ***/
        /*
        Contract contract = new Contract();
        contract.localSymbol("19869");
        contract.secType("WAR");
        contract.currency("HKD");
        contract.exchange("SEHK");
        ibCoreService.getClient().reqMktData(1007, contract, "", false, false, null);

        Thread.sleep(10000);
        ibCoreService.getClient().cancelMktData(1007);
        */
    }
}
