package com.fintech.gams.ib.service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fintech.gams.ib.config.IBGateWay;
import com.ib.client.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IbCoreServiceImpl implements IbCoreService, EWrapper, InitializingBean, DisposableBean
{
    @Autowired
    private IBGateWay                      ibGateWay;
    
    @Autowired
    private IbAccountFamilyService         ibAccountFamilyService;
    
    @Autowired
    private IbAccountBalanceMarginService  ibAccountBalanceMarginService;
    
    @Autowired
    private IbAccountMarketValueService    ibAccountMarketValueService;
    
    @Autowired
    private IbAccountMarketValueHisService ibAccountMarketValueHisService;
    
    @Autowired
    private IbAccountDailyPnlService       ibAccountDailyPnlService;
    
    @Autowired
    private IbAccountPositionService       ibAccountPositionService;
    
    @Autowired
    private IbAccountTradeService          ibAccountTradeService;
    
    @Autowired
    private IbTickService                  ibTickService;
    
    private static EReaderSignal           readerSignal;
    
    private static EClientSocket           clientSocket;
    
    private static EReader                 reader;
    
    protected int                          currentOrderId = -1;
    
    // ! [socket_init]
    @Override
    public void afterPropertiesSet() throws Exception
    {
        readerSignal = new EJavaSignal();
        clientSocket = new EClientSocket(this, readerSignal);
        // ibGateWay socket init
        clientSocket.eConnect(ibGateWay.getHost(), ibGateWay.getPort(), ibGateWay.getClientId());
        reader = new EReader(clientSocket, readerSignal);
        reader.start();
        new Thread(() -> {
            while (clientSocket.isConnected())
            {
                readerSignal.waitForSignal();
                try
                {
                    reader.processMsgs();
                }
                catch (Exception e)
                {
                    log.error(e.getLocalizedMessage());
                }
            }
        }).start();
    }
    
    // ! [socket_destroy]
    @Override
    public void destroy() throws Exception
    {
        clientSocket.eDisconnect();
    }
    
    // ! [socket_init]
    @Override
    public synchronized EClientSocket getClient()
    {
        if (!clientSocket.isConnected())
        {
            log.info("IB网关重启后 ibGateWay socket init 开始...");
            // 假如IB网关重启后 ibGateWay socket init
            readerSignal = new EJavaSignal();
            clientSocket = new EClientSocket(this, readerSignal);
            // ibGateWay socket init
            clientSocket.eConnect(ibGateWay.getHost(), ibGateWay.getPort(), ibGateWay.getClientId());
            reader = new EReader(clientSocket, readerSignal);
            reader.start();
            new Thread(() -> {
                while (clientSocket.isConnected())
                {
                    readerSignal.waitForSignal();
                    try
                    {
                        reader.processMsgs();
                    }
                    catch (Exception e)
                    {
                        log.error(e.getLocalizedMessage());
                    }
                }
            }).start();
            log.info("IB网关重启后 ibGateWay socket init 结束...");
        }
        return clientSocket;
    }
    
    // ! [tickprice]
    @Override
    public void tickPrice(int tickerId, int field, double price, TickAttrib attribs)
    {
        log.debug("Tick Price. Ticker Id:" + tickerId + ", Field: " + field + ", Price: " + price + ", CanAutoExecute: " + attribs.canAutoExecute() + ", pastLimit: "
                + attribs.pastLimit() + ", pre-open: " + attribs.preOpen());
        ibTickService.ibMessageProcessTickPrice(tickerId, field, price, attribs);
    }
    // ! [tickprice]
    
    // ! [ticksize]
    @Override
    public void tickSize(int tickerId, int field, int size)
    {
        log.debug("Tick Size. Ticker Id:" + tickerId + ", Field: " + field + ", Size: " + size);
        ibTickService.ibMessageProcessTickSize(tickerId, field, size);
    }
    // ! [ticksize]
    
    // ! [tickoptioncomputation]
    @Override
    public void tickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice, double pvDividend, double gamma, double vega, double theta,
            double undPrice)
    {
        log.debug("TickOptionComputation. TickerId: " + tickerId + ", field: " + field + ", ImpliedVolatility: " + impliedVol + ", Delta: " + delta + ", OptionPrice: "
                + optPrice + ", pvDividend: " + pvDividend + ", Gamma: " + gamma + ", Vega: " + vega + ", Theta: " + theta + ", UnderlyingPrice: " + undPrice);
        ibTickService.ibMessageProcessTickOptionComputation(tickerId, field, impliedVol, delta, optPrice, pvDividend, gamma, vega, theta, undPrice);
    }
    // ! [tickoptioncomputation]
    
    // ! [tickgeneric]
    @Override
    public void tickGeneric(int tickerId, int tickType, double value)
    {
        log.info("Tick Generic. Ticker Id:" + tickerId + ", Field: " + TickType.getField(tickType) + ", Value: " + value);
    }
    // ! [tickgeneric]
    
    // ! [tickstring]
    @Override
    public void tickString(int tickerId, int tickType, String value)
    {
        log.debug("Tick string. Ticker Id:" + tickerId + ", Type: " + tickType + ", Value: " + value);
        ibTickService.ibMessageProcessTickString(tickerId, tickType, value);
    }
    
    // ! [tickstring]
    @Override
    public void tickEFP(int tickerId, int tickType, double basisPoints, String formattedBasisPoints, double impliedFuture, int holdDays, String futureLastTradeDate,
            double dividendImpact, double dividendsToLastTradeDate)
    {
        log.info("TickEFP. " + tickerId + ", Type: " + tickType + ", BasisPoints: " + basisPoints + ", FormattedBasisPoints: " + formattedBasisPoints + ", ImpliedFuture: "
                + impliedFuture + ", HoldDays: " + holdDays + ", FutureLastTradeDate: " + futureLastTradeDate + ", DividendImpact: " + dividendImpact
                + ", DividendsToLastTradeDate: " + dividendsToLastTradeDate);
    }
    
    // ! [orderstatus]
    @Override
    public void orderStatus(int orderId, String status, double filled, double remaining, double avgFillPrice, int permId, int parentId, double lastFillPrice, int clientId,
            String whyHeld, double mktCapPrice)
    {
        log.info("OrderStatus. Id: " + orderId + ", Status: " + status + ", Filled" + filled + ", Remaining: " + remaining + ", AvgFillPrice: " + avgFillPrice
                + ", PermId: " + permId + ", ParentId: " + parentId + ", LastFillPrice: " + lastFillPrice + ", ClientId: " + clientId + ", WhyHeld: " + whyHeld
                + ", MktCapPrice: " + mktCapPrice);
    }
    // ! [orderstatus]
    
    // ! [openorder]
    @Override
    public void openOrder(int orderId, Contract contract, Order order, OrderState orderState)
    {
        log.info(EWrapperMsgGenerator.openOrder(orderId, contract, order, orderState));
    }
    // ! [openorder]
    
    // ! [openorderend]
    @Override
    public void openOrderEnd()
    {
        log.info("OpenOrderEnd");
    }
    // ! [openorderend]
    
    // ! [updateaccountvalue]
    @Override
    public void updateAccountValue(String key, String value, String currency, String accountName)
    {
        log.debug("updateAccountValue UpdateAccountValue. Key: " + key + ", Value: " + value + ", Currency: " + currency + ", AccountName: " + accountName);
        // RPC处理
        ibAccountBalanceMarginService.ibMessageProcess(key, value, currency, accountName);
        // RPC处理
        ibAccountMarketValueService.ibMessageProcessUpdateAccountValue(key, value, currency, accountName);
        ibAccountMarketValueHisService.ibMessageProcessUpdateAccountValueHis(key, value, currency, accountName);
    }
    // ! [updateaccountvalue]
    
    // ! [updateportfolio]
    @Override
    public void updatePortfolio(Contract contract, double position, double marketPrice, double marketValue, double averageCost, double unrealizedPNL, double realizedPNL,
            String accountName)
    {
        log.debug("updatePortfolio UpdatePortfolio. " + contract.symbol() + ", " + contract.secType() + " @ " + contract.exchange() + ": Position: " + position
                + ", MarketPrice: " + marketPrice + ", MarketValue: " + marketValue + ", AverageCost: " + averageCost + ", UnrealizedPNL: " + unrealizedPNL
                + ", RealizedPNL: " + realizedPNL + ", AccountName: " + accountName);
        // RPC处理
        ibAccountPositionService.ibMessageProcessPortfolio(contract, position, marketPrice, marketValue, averageCost, unrealizedPNL, realizedPNL, accountName);
    }
    // ! [updateportfolio]
    
    // ! [updateaccounttime]
    @Override
    public void updateAccountTime(String timeStamp)
    {
        log.info("updateAccountTime UpdateAccountTime. Time: " + timeStamp + "\n");
    }
    // ! [updateaccounttime]
    
    // ! [accountdownloadend]
    @Override
    public void accountDownloadEnd(String accountName)
    {
        log.info("accountDownloadEnd Account download finished: " + accountName + "\n");
    }
    // ! [accountdownloadend]
    
    // ! [nextvalidid]
    @Override
    public void nextValidId(int orderId)
    {
        log.info("nextValidId Next Valid Id: [" + orderId + "]");
        currentOrderId = orderId;
    }
    // ! [nextvalidid]
    
    // ! [contractdetails]
    @Override
    public void contractDetails(int reqId, ContractDetails contractDetails)
    {
        log.info(EWrapperMsgGenerator.contractDetails(reqId, contractDetails));
    }
    
    // ! [contractdetails]
    @Override
    public void bondContractDetails(int reqId, ContractDetails contractDetails)
    {
        log.info(EWrapperMsgGenerator.bondContractDetails(reqId, contractDetails));
    }
    
    // ! [contractdetailsend]
    @Override
    public void contractDetailsEnd(int reqId)
    {
        log.info("ContractDetailsEnd. " + reqId + "\n");
    }
    // ! [contractdetailsend]
    
    // ! [execdetails]
    @Override
    public void execDetails(int reqId, Contract contract, Execution execution)
    {
        log.info("execDetails ExecDetails. " + reqId + " - [" + contract.symbol() + "], [" + contract.secType() + "], [" + contract.currency() + "], [" + execution.execId()
                + "], [" + execution.orderId() + "], [" + execution.shares() + "]" + ", [" + execution.lastLiquidity() + "]" + ", [" + execution.time() + "]");
        // RPC处理
        ibAccountTradeService.ibMessageProcessExecDetails(reqId, contract, execution);
    }
    // ! [execdetails]
    
    // ! [execdetailsend]
    @Override
    public void execDetailsEnd(int reqId)
    {
        log.info("execDetailsEnd ExecDetailsEnd. " + reqId + "\n");
    }
    // ! [execdetailsend]
    
    // ! [updatemktdepth]
    @Override
    public void updateMktDepth(int tickerId, int position, int operation, int side, double price, int size)
    {
        log.info("UpdateMarketDepth. " + tickerId + " - Position: " + position + ", Operation: " + operation + ", Side: " + side + ", Price: " + price + ", Size: " + size
                + "");
    }
    // ! [updatemktdepth]
    
    // ! [updatemktdepthl2]
    @Override
    public void updateMktDepthL2(int tickerId, int position, String marketMaker, int operation, int side, double price, int size, boolean isSmartDepth)
    {
        log.info("UpdateMarketDepthL2. " + tickerId + " - Position: " + position + ", Operation: " + operation + ", Side: " + side + ", Price: " + price + ", Size: " + size
                + ", isSmartDepth: " + isSmartDepth);
    }
    // ! [updatemktdepthl2]
    
    // ! [updatenewsbulletin]
    @Override
    public void updateNewsBulletin(int msgId, int msgType, String message, String origExchange)
    {
        log.info("News Bulletins. " + msgId + " - Type: " + msgType + ", Message: " + message + ", Exchange of Origin: " + origExchange + "\n");
    }
    // ! [updatenewsbulletin]
    
    // ! [managedaccounts]
    @Override
    public void managedAccounts(String accountsList)
    {
        log.info("managedAccounts account list: " + accountsList);
    }
    // ! [managedaccounts]
    
    // ! [receivefa]
    @Override
    public void receiveFA(int faDataType, String xml)
    {
        log.info("Receiving FA: " + faDataType + " - " + xml);
    }
    // ! [receivefa]
    
    // ! [historicaldata]
    @Override
    public void historicalData(int reqId, Bar bar)
    {
        log.info("HistoricalData. " + reqId + " - Date: " + bar.time() + ", Open: " + bar.open() + ", High: " + bar.high() + ", Low: " + bar.low() + ", Close: "
                + bar.close() + ", Volume: " + bar.volume() + ", Count: " + bar.count() + ", WAP: " + bar.wap());
    }
    // ! [historicaldata]
    
    // ! [historicaldataend]
    @Override
    public void historicalDataEnd(int reqId, String startDateStr, String endDateStr)
    {
        log.info("HistoricalDataEnd. " + reqId + " - Start Date: " + startDateStr + ", End Date: " + endDateStr);
    }
    // ! [historicaldataend]
    
    // ! [scannerparameters]
    @Override
    public void scannerParameters(String xml)
    {
        log.info("ScannerParameters. " + xml + "\n");
    }
    // ! [scannerparameters]
    
    // ! [scannerdata]
    @Override
    public void scannerData(int reqId, int rank, ContractDetails contractDetails, String distance, String benchmark, String projection, String legsStr)
    {
        log.info("ScannerData. " + reqId + " - Rank: " + rank + ", Symbol: " + contractDetails.contract().symbol() + ", SecType: " + contractDetails.contract().secType()
                + ", Currency: " + contractDetails.contract().currency() + ", Distance: " + distance + ", Benchmark: " + benchmark + ", Projection: " + projection
                + ", Legs String: " + legsStr);
    }
    // ! [scannerdata]
    
    // ! [scannerdataend]
    @Override
    public void scannerDataEnd(int reqId)
    {
        log.info("ScannerDataEnd. " + reqId);
    }
    // ! [scannerdataend]
    
    // ! [realtimebar]
    @Override
    public void realtimeBar(int reqId, long time, double open, double high, double low, double close, long volume, double wap, int count)
    {
        log.info("RealTimeBars. " + reqId + " - Time: " + time + ", Open: " + open + ", High: " + high + ", Low: " + low + ", Close: " + close + ", Volume: " + volume
                + ", Count: " + count + ", WAP: " + wap);
    }
    
    // ! [realtimebar]
    @Override
    public void currentTime(long time)
    {
        log.info("currentTime: " + time);
    }
    
    // ! [fundamentaldata]
    @Override
    public void fundamentalData(int reqId, String data)
    {
        log.info("FundamentalData. ReqId: [" + reqId + "] - Data: [" + data + "]");
    }
    
    // ! [fundamentaldata]
    @Override
    public void deltaNeutralValidation(int reqId, DeltaNeutralContract deltaNeutralContract)
    {
        log.info("deltaNeutralValidation");
    }
    
    // ! [ticksnapshotend]
    @Override
    public void tickSnapshotEnd(int reqId)
    {
        log.info("TickSnapshotEnd: " + reqId);
    }
    // ! [ticksnapshotend]
    
    // ! [marketdatatype]
    @Override
    public void marketDataType(int reqId, int marketDataType)
    {
        log.info("MarketDataType. [" + reqId + "], Type: [" + marketDataType + "]\n");
    }
    // ! [marketdatatype]
    
    // ! [commissionreport]
    @Override
    public void commissionReport(CommissionReport commissionReport)
    {
        log.info("commissionReport CommissionReport. [" + commissionReport.execId() + "] - [" + commissionReport.commission() + "] [" + commissionReport.currency()
                + "] RPNL [" + commissionReport.realizedPNL() + "]");
    }
    // ! [commissionreport]
    
    // ! [position]
    @Override
    public void position(String account, Contract contract, double pos, double avgCost)
    {
        log.debug("position Position. accountCode: " + account + " - Symbol: " + contract.symbol() + ", SecType: " + contract.secType() + ", Currency: "
                + contract.currency() + ", Position: " + pos + ", Avg cost: " + avgCost);
        // RPC处理
        ibAccountPositionService.ibMessageProcessPosition(account, contract, pos, avgCost);
    }
    // ! [position]
    
    // ! [positionend]
    @Override
    public void positionEnd()
    {
        log.info("positionEnd PositionEnd \n");
    }
    // ! [positionend]
    
    // ! [accountsummary]
    @Override
    public void accountSummary(int reqId, String account, String tag, String value, String currency)
    {
        log.info("accountSummary Acct Summary. ReqId: " + reqId + ", Acct: " + account + ", Tag: " + tag + ", Value: " + value + ", Currency: " + currency);
    }
    // ! [accountsummary]
    
    // ! [accountsummaryend]
    @Override
    public void accountSummaryEnd(int reqId)
    {
        log.info("accountSummaryEnd AccountSummaryEnd. Req Id: " + reqId + "\n");
    }
    
    // ! [accountsummaryend]
    @Override
    public void verifyMessageAPI(String apiData)
    {
        log.info("verifyMessageAPI");
    }
    
    @Override
    public void verifyCompleted(boolean isSuccessful, String errorText)
    {
        log.info("verifyCompleted");
    }
    
    @Override
    public void verifyAndAuthMessageAPI(String apiData, String xyzChallenge)
    {
        log.info("verifyAndAuthMessageAPI");
    }
    
    @Override
    public void verifyAndAuthCompleted(boolean isSuccessful, String errorText)
    {
        log.info("verifyAndAuthCompleted");
    }
    
    // ! [displaygrouplist]
    @Override
    public void displayGroupList(int reqId, String groups)
    {
        log.info("Display Group List. ReqId: " + reqId + ", Groups: " + groups + "\n");
    }
    // ! [displaygrouplist]
    
    // ! [displaygroupupdated]
    @Override
    public void displayGroupUpdated(int reqId, String contractInfo)
    {
        log.info("Display Group Updated. ReqId: " + reqId + ", Contract info: " + contractInfo + "\n");
    }
    
    // ! [displaygroupupdated]
    @Override
    public void error(Exception e)
    {
        log.error("error Exception: " + e.getMessage());
    }
    
    @Override
    public void error(String str)
    {
        log.error("error Error STR");
    }
    
    // ! [error]
    @Override
    public void error(int id, int errorCode, String errorMsg)
    {
        log.error("error Error. Id: " + id + ", Code: " + errorCode + ", Msg: " + errorMsg + "\n");
    }
    
    // ! [error]
    @Override
    public void connectionClosed()
    {
        log.info("connectionClosed Connection closed");
    }
    
    // ! [connectack]
    @Override
    public void connectAck()
    {
        if (clientSocket.isAsyncEConnect())
        {
            log.info("connectAck Acknowledging connection");
            clientSocket.startAPI();
        }
    }
    // ! [connectack]
    
    // ! [positionmulti]
    @Override
    public void positionMulti(int reqId, String account, String modelCode, Contract contract, double pos, double avgCost)
    {
        log.debug("positionMulti Position Multi. Request: " + reqId + ", Account: " + account + ", ModelCode: " + modelCode + ", Symbol: " + contract.symbol()
                + ", SecType: " + contract.secType() + ", Currency: " + contract.currency() + ", Position: " + pos + ", Avg cost: " + avgCost + "\n");
        // RPC处理
        ibAccountPositionService.ibMessageProcessPositionMulti(reqId, account, modelCode, contract, pos, avgCost);
    }
    // ! [positionmulti]
    
    // ! [positionmultiend]
    @Override
    public void positionMultiEnd(int reqId)
    {
        //log.info("positionMultiEnd Position Multi End. Request: " + reqId + "\n");
    }
    // ! [positionmultiend]
    
    // ! [accountupdatemulti]
    @Override
    public void accountUpdateMulti(int reqId, String account, String modelCode, String key, String value, String currency)
    {
        /*
        System.out.println("accountUpdateMulti reqId:" + reqId);
        System.out.println("accountUpdateMulti account:" + account);
        System.out.println("accountUpdateMulti modelCode:" + modelCode);
        System.out.println("accountUpdateMulti key:" + key);
        System.out.println("accountUpdateMulti value:" + value);
        System.out.println("accountUpdateMulti currency:" + currency);
        log.info("accountUpdateMulti Account Update Multi. Request: " + reqId + ", Account: " + account + ", ModelCode: " + modelCode + ", Key: " + key + ", Value: "
                + value + ", Currency: " + currency);
        */
        // RPC处理
        ibAccountMarketValueService.ibMessageProcessAccountUpdateMulti(reqId, account, modelCode, key, value, currency);
        ibAccountMarketValueHisService.ibMessageProcessAccountUpdateMultiHis(reqId, account, modelCode, key, value, currency);
    }
    // ! [accountupdatemulti]
    
    // ! [accountupdatemultiend]
    @Override
    public void accountUpdateMultiEnd(int reqId)
    {
        //log.info("accountUpdateMultiEnd Account Update Multi End. Request: " + reqId + "\n");
    }
    // ! [accountupdatemultiend]
    
    // ! [securityDefinitionOptionParameter]
    @Override
    public void securityDefinitionOptionalParameter(int reqId, String exchange, int underlyingConId, String tradingClass, String multiplier, Set<String> expirations,
            Set<Double> strikes)
    {
        log.info("Security Definition Optional Parameter. Request: " + reqId + ", Trading Class: " + tradingClass + ", Multiplier: " + multiplier + " \n");
    }
    // ! [securityDefinitionOptionParameter]
    
    // ! [securityDefinitionOptionParameterEnd]
    @Override
    public void securityDefinitionOptionalParameterEnd(int reqId)
    {
        log.info("Security Definition Optional Parameter End. Request: " + reqId);
    }
    // ! [securityDefinitionOptionParameterEnd]
    
    // ! [softDollarTiers]
    @Override
    public void softDollarTiers(int reqId, SoftDollarTier[] tiers)
    {
        for (SoftDollarTier tier : tiers)
        {
            log.info("tier: " + tier.toString() + ", ");
        }
    }
    // ! [softDollarTiers]
    
    // ! [familyCodes]
    @Override
    public void familyCodes(FamilyCode[] familyCodes)
    {
        for (FamilyCode fc : familyCodes)
        {
            log.debug("familyCodes Family Code. AccountCode: " + fc.accountID() + ", FamilyCode: " + fc.familyCodeStr());
        }
        // RPC处理
        ibAccountFamilyService.ibMessageProcess(familyCodes);
    }
    // ! [familyCodes]
    
    // ! [symbolSamples]
    @Override
    public void symbolSamples(int reqId, ContractDescription[] contractDescriptions)
    {
        log.info("Contract Descriptions. Request: " + reqId + "\n");
        for (ContractDescription cd : contractDescriptions)
        {
            Contract c = cd.contract();
            StringBuilder derivativeSecTypesSB = new StringBuilder();
            for (String str : cd.derivativeSecTypes())
            {
                derivativeSecTypesSB.append(str);
                derivativeSecTypesSB.append(",");
            }
            log.info("Contract. ConId: " + c.conid() + ", Symbol: " + c.symbol() + ", SecType: " + c.secType() + ", PrimaryExch: " + c.primaryExch() + ", Currency: "
                    + c.currency() + ", DerivativeSecTypes:[" + derivativeSecTypesSB.toString() + "]");
        }
    }
    // ! [symbolSamples]
    
    // ! [mktDepthExchanges]
    @Override
    public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions)
    {
        for (DepthMktDataDescription depthMktDataDescription : depthMktDataDescriptions)
        {
            log.info("Depth Mkt Data Description. Exchange: " + depthMktDataDescription.exchange() + ", ListingExch: " + depthMktDataDescription.listingExch()
                    + ", SecType: " + depthMktDataDescription.secType() + ", ServiceDataType: " + depthMktDataDescription.serviceDataType() + ", AggGroup: "
                    + depthMktDataDescription.aggGroup());
        }
    }
    // ! [mktDepthExchanges]
    
    // ! [tickNews]
    @Override
    public void tickNews(int tickerId, long timeStamp, String providerCode, String articleId, String headline, String extraData)
    {
        log.info("Tick News. TickerId: " + tickerId + ", TimeStamp: " + timeStamp + ", ProviderCode: " + providerCode + ", ArticleId: " + articleId + ", Headline: "
                + headline + ", ExtraData: " + extraData + "\n");
    }
    // ! [tickNews]
    
    // ! [smartcomponents]
    @Override
    public void smartComponents(int reqId, Map<Integer, Map.Entry<String, Character>> theMap)
    {
        log.info("smart components req id:" + reqId);
        for (Map.Entry<Integer, Map.Entry<String, Character>> item : theMap.entrySet())
        {
            log.info("bit number: " + item.getKey() + ", exchange: " + item.getValue().getKey() + ", exchange letter: " + item.getValue().getValue());
        }
    }
    // ! [smartcomponents]
    
    // ! [tickReqParams]
    @Override
    public void tickReqParams(int tickerId, double minTick, String bboExchange, int snapshotPermissions)
    {
        log.info("Tick req params. Ticker Id:" + tickerId + ", Min tick: " + minTick + ", bbo exchange: " + bboExchange + ", Snapshot permissions: " + snapshotPermissions);
    }
    // ! [tickReqParams]
    
    // ! [newsProviders]
    @Override
    public void newsProviders(NewsProvider[] newsProviders)
    {
        for (NewsProvider np : newsProviders)
        {
            log.info("News Provider. ProviderCode: " + np.providerCode() + ", ProviderName: " + np.providerName() + "\n");
        }
    }
    // ! [newsProviders]
    
    // ! [newsArticle]
    @Override
    public void newsArticle(int requestId, int articleType, String articleText)
    {
        log.info("News Article. Request Id: " + requestId + ", ArticleType: " + articleType + ", ArticleText: " + articleText);
    }
    // ! [newsArticle]
    
    // ! [historicalNews]
    @Override
    public void historicalNews(int requestId, String time, String providerCode, String articleId, String headline)
    {
        log.info("Historical News. RequestId: " + requestId + ", Time: " + time + ", ProviderCode: " + providerCode + ", ArticleId: " + articleId + ", Headline: "
                + headline + "\n");
    }
    // ! [historicalNews]
    
    // ! [historicalNewsEnd]
    @Override
    public void historicalNewsEnd(int requestId, boolean hasMore)
    {
        log.info("Historical News End. RequestId: " + requestId + ", HasMore: " + hasMore + "\n");
    }
    // ! [historicalNewsEnd]
    
    // ! [headTimestamp]
    @Override
    public void headTimestamp(int reqId, String headTimestamp)
    {
        log.info("Head timestamp. Req Id: " + reqId + ", headTimestamp: " + headTimestamp);
    }
    // ! [headTimestamp]
    
    // ! [histogramData]
    @Override
    public void histogramData(int reqId, List<HistogramEntry> items)
    {
        log.info(EWrapperMsgGenerator.histogramData(reqId, items));
    }
    // ! [histogramData]
    
    // ! [historicalDataUpdate]
    @Override
    public void historicalDataUpdate(int reqId, Bar bar)
    {
        log.info("HistoricalDataUpdate. " + reqId + " - Date: " + bar.time() + ", Open: " + bar.open() + ", High: " + bar.high() + ", Low: " + bar.low() + ", Close: "
                + bar.close() + ", Volume: " + bar.volume() + ", Count: " + bar.count() + ", WAP: " + bar.wap());
    }
    // ! [historicalDataUpdate]
    
    // ! [rerouteMktDataReq]
    @Override
    public void rerouteMktDataReq(int reqId, int conId, String exchange)
    {
        log.info(EWrapperMsgGenerator.rerouteMktDataReq(reqId, conId, exchange));
    }
    // ! [rerouteMktDataReq]
    
    // ! [rerouteMktDepthReq]
    @Override
    public void rerouteMktDepthReq(int reqId, int conId, String exchange)
    {
        log.info(EWrapperMsgGenerator.rerouteMktDepthReq(reqId, conId, exchange));
    }
    // ! [rerouteMktDepthReq]
    
    // ! [marketRule]
    @Override
    public void marketRule(int marketRuleId, PriceIncrement[] priceIncrements)
    {
        DecimalFormat df = new DecimalFormat("#.#");
        df.setMaximumFractionDigits(340);
        log.info("Market Rule Id: " + marketRuleId);
        for (PriceIncrement pi : priceIncrements)
        {
            log.info("Price Increment. Low Edge: " + df.format(pi.lowEdge()) + ", Increment: " + df.format(pi.increment()));
        }
    }
    // ! [marketRule]
    
    // ! [pnl]
    @Override
    public void pnl(int reqId, double dailyPnL, double unrealizedPnL, double realizedPnL)
    {
        log.debug("pnl: " + EWrapperMsgGenerator.pnl(reqId, dailyPnL, unrealizedPnL, realizedPnL));
        ibAccountDailyPnlService.ibMessageProcessPnL(reqId, dailyPnL, unrealizedPnL, realizedPnL);
    }
    // ! [pnl]
    
    // ! [pnlsingle]
    @Override
    public void pnlSingle(int reqId, int pos, double dailyPnL, double unrealizedPnL, double realizedPnL, double value)
    {
        log.info("pnlSingle: " + EWrapperMsgGenerator.pnlSingle(reqId, pos, dailyPnL, unrealizedPnL, realizedPnL, value));
    }
    // ! [pnlsingle]
    
    // ! [historicalticks]
    @Override
    public void historicalTicks(int reqId, List<HistoricalTick> ticks, boolean done)
    {
        for (HistoricalTick tick : ticks)
        {
            log.info(EWrapperMsgGenerator.historicalTick(reqId, tick.time(), tick.price(), tick.size()));
        }
    }
    // ! [historicalticks]
    
    // ! [historicalticksbidask]
    @Override
    public void historicalTicksBidAsk(int reqId, List<HistoricalTickBidAsk> ticks, boolean done)
    {
        for (HistoricalTickBidAsk tick : ticks)
        {
            log.info(EWrapperMsgGenerator.historicalTickBidAsk(reqId, tick.time(), tick.tickAttribBidAsk(), tick.priceBid(), tick.priceAsk(), tick.sizeBid(),
                    tick.sizeAsk()));
        }
    }
    // ! [historicalticksbidask]
    
    @Override
    // ! [historicaltickslast]
    public void historicalTicksLast(int reqId, List<HistoricalTickLast> ticks, boolean done)
    {
        for (HistoricalTickLast tick : ticks)
        {
            log.info(EWrapperMsgGenerator.historicalTickLast(reqId, tick.time(), tick.tickAttribLast(), tick.price(), tick.size(), tick.exchange(),
                    tick.specialConditions()));
        }
    }
    // ! [historicaltickslast]
    
    // ! [tickbytickalllast]
    @Override
    public void tickByTickAllLast(int reqId, int tickType, long time, double price, int size, TickAttribLast tickAttribLast, String exchange, String specialConditions)
    {
        log.info(EWrapperMsgGenerator.tickByTickAllLast(reqId, tickType, time, price, size, tickAttribLast, exchange, specialConditions));
    }
    // ! [tickbytickalllast]
    
    // ! [tickbytickbidask]
    @Override
    public void tickByTickBidAsk(int reqId, long time, double bidPrice, double askPrice, int bidSize, int askSize, TickAttribBidAsk tickAttribBidAsk)
    {
        log.info(EWrapperMsgGenerator.tickByTickBidAsk(reqId, time, bidPrice, askPrice, bidSize, askSize, tickAttribBidAsk));
    }
    // ! [tickbytickbidask]
    
    // ! [tickbytickmidpoint]
    @Override
    public void tickByTickMidPoint(int reqId, long time, double midPoint)
    {
        log.info(EWrapperMsgGenerator.tickByTickMidPoint(reqId, time, midPoint));
    }
    // ! [tickbytickmidpoint]
    
    // ! [orderbound]
    @Override
    public void orderBound(long orderId, int apiClientId, int apiOrderId)
    {
        log.info(EWrapperMsgGenerator.orderBound(orderId, apiClientId, apiOrderId));
    }
    // ! [orderbound]
    
    // ! [completedorder]
    @Override
    public void completedOrder(Contract contract, Order order, OrderState orderState)
    {
        log.info(EWrapperMsgGenerator.completedOrder(contract, order, orderState));
    }
    // ! [completedorder]
    
    // ! [completedordersend]
    @Override
    public void completedOrdersEnd()
    {
        log.info(EWrapperMsgGenerator.completedOrdersEnd());
    }
    // ! [completedordersend]
}
