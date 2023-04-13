/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.service;

import com.fintech.gams.consts.BizConst;
import com.fintech.gams.consts.CacheConst;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.quote.entity.*;
import com.fintech.gams.quote.service.*;
import com.fintech.gams.stock.entity.StockInfo;
import com.fintech.gams.stock.service.StockInfoService;
import com.fintech.gams.utils.RedisUtils;
import com.ib.client.TickAttrib;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * IB Tick 服务实现类
 * <p>File：IbTickServiceImpl.java </p>
 * <p>Title: IbTickServiceImpl </p>
 * <p>Description:IbTickServiceImpl </p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Slf4j
@Service
public class IbTickServiceImpl implements IbTickService
{
    @Autowired(required = false)
    private StockInfoService stockInfoService;

    @Autowired(required = false)
    private QuoteStockService quoteStockService;

    @Autowired(required = false)
    private QuoteBondService quoteBondService;

    @Autowired(required = false)
    private QuoteFundService quoteFundService;

    @Autowired(required = false)
    private QuoteForeignExService quoteForeignExService;

    @Autowired(required = false)
    private QuoteFuturesService quoteFuturesService;

    @Autowired(required = false)
    private QuoteOptionService quoteOptionService;

    @Autowired(required = false)
    private QuoteWarrantService quoteWarrantService;

    /**
     * @param tickerId
     * @param field
     * @param price
     * @param attribs
     */
    public void ibMessageProcessTickPrice(int tickerId, int field, double price, TickAttrib attribs) {
        // 根据记录tickerId对应的stockInfo
        String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
        StockInfo stockInfo = (StockInfo) RedisUtils.getObject(cacheKey);
        //将时间转换成YYYYMMDD
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Long currentTime = Long.parseLong(simpleDateFormat.format(System.currentTimeMillis()));
        log.debug("stockInfo:{}, currentTime:{}", stockInfo.toString(), currentTime);
        try {
            switch (stockInfo.getStockType()) {
                case BizConst.STOCK_TYPE_STOCK:
                    QuoteStock quoteStock = quoteStockService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (null != quoteStock) {
                        log.info("quoteStock:{}, currentTime:{}", quoteStock.toString(), currentTime);
                    }
                    if (field == 1) {
                        if (null != quoteStock) {
                            quoteStock.setBid(BigDecimal.valueOf(price));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setBid(BigDecimal.valueOf(price));
                            quoteStockService.insert(quoteStock);
                        }
                    } else if (field == 2) {
                        if (null != quoteStock) {
                            quoteStock.setAsk(BigDecimal.valueOf(price));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setAsk(BigDecimal.valueOf(price));
                            quoteStockService.insert(quoteStock);
                        }
                    } else if (field == 4) {
                        if (null != quoteStock) {
                            quoteStock.setLast(BigDecimal.valueOf(price));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setLast(BigDecimal.valueOf(price));
                            quoteStockService.insert(quoteStock);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if (field == 6) {
                        if (null != quoteStock) {
                            quoteStock.setHigh(BigDecimal.valueOf(price));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setHigh(BigDecimal.valueOf(price));
                            quoteStockService.insert(quoteStock);
                        }
                    } else if (field == 7) {
                        if (null != quoteStock) {
                            quoteStock.setLow(BigDecimal.valueOf(price));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setLow(BigDecimal.valueOf(price));
                            quoteStockService.insert(quoteStock);
                        }
                    } else if (field == 9) {
                        if (null != quoteStock) {
                            quoteStock.setPrevClose(BigDecimal.valueOf(price));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setPrevClose(BigDecimal.valueOf(price));
                            quoteStockService.insert(quoteStock);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_BOND:
                    QuoteBond quoteBond = quoteBondService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 1) {
                        if (null != quoteBond) {
                            quoteBond.setBid(BigDecimal.valueOf(price));
                            quoteBondService.updateByPrimaryKeySelective(quoteBond);
                        } else {
                            quoteBond = insertQuoteBond(stockInfo);
                            quoteBond.setBid(BigDecimal.valueOf(price));
                            quoteBondService.insert(quoteBond);
                        }
                    } else if (field == 2) {
                        if (null != quoteBond) {
                            quoteBond.setAsk(BigDecimal.valueOf(price));
                            quoteBondService.updateByPrimaryKeySelective(quoteBond);
                        } else {
                            quoteBond = insertQuoteBond(stockInfo);
                            quoteBond.setAsk(BigDecimal.valueOf(price));
                            quoteBondService.insert(quoteBond);
                        }
                    } else if (field == 4) {
                        if (null != quoteBond) {
                            quoteBond.setLast(BigDecimal.valueOf(price));
                            quoteBondService.updateByPrimaryKeySelective(quoteBond);
                        } else {
                            quoteBond = insertQuoteBond(stockInfo);
                            quoteBond.setLast(BigDecimal.valueOf(price));
                            quoteBondService.insert(quoteBond);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if(field == 6){

                    } else if(field == 7){

                    } else if (field == 9) {
                        if (null != quoteBond) {
                            quoteBond.setPrevClose(BigDecimal.valueOf(price));
                            quoteBondService.updateByPrimaryKeySelective(quoteBond);
                        } else {
                            quoteBond = insertQuoteBond(stockInfo);
                            quoteBond.setPrevClose(BigDecimal.valueOf(price));
                            quoteBondService.insert(quoteBond);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_FOREIGNEX:
                    QuoteForeignEx quoteForeignEx = quoteForeignExService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 1) {
                        if (null != quoteForeignEx) {
                            quoteForeignEx.setBid(BigDecimal.valueOf(price));
                            quoteForeignExService.updateByPrimaryKeySelective(quoteForeignEx);
                        } else {
                            quoteForeignEx = insertQuoteForeignEx(stockInfo);
                            quoteForeignEx.setBid(BigDecimal.valueOf(price));
                            quoteForeignExService.insert(quoteForeignEx);
                        }
                    } else if (field == 2) {
                        if (null != quoteForeignEx) {
                            quoteForeignEx.setAsk(BigDecimal.valueOf(price));
                            quoteForeignExService.updateByPrimaryKeySelective(quoteForeignEx);
                        } else {
                            quoteForeignEx = insertQuoteForeignEx(stockInfo);
                            quoteForeignEx.setAsk(BigDecimal.valueOf(price));
                            quoteForeignExService.insert(quoteForeignEx);
                        }
                    } else if (field == 4) {
                        if (null != quoteForeignEx) {
                            quoteForeignEx.setLast(BigDecimal.valueOf(price));
                            quoteForeignExService.updateByPrimaryKeySelective(quoteForeignEx);
                        } else {
                            quoteForeignEx = insertQuoteForeignEx(stockInfo);
                            quoteForeignEx.setLast(BigDecimal.valueOf(price));
                            quoteForeignExService.insert(quoteForeignEx);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if(field == 6){

                    } else if(field == 7){

                    } else if (field == 9) {
                        if (null != quoteForeignEx) {
                            quoteForeignEx.setPrevClose(BigDecimal.valueOf(price));
                            quoteForeignExService.updateByPrimaryKeySelective(quoteForeignEx);
                        } else {
                            quoteForeignEx = insertQuoteForeignEx(stockInfo);
                            quoteForeignEx.setPrevClose(BigDecimal.valueOf(price));
                            quoteForeignExService.insert(quoteForeignEx);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_FUND:
                    QuoteFund quoteFund = quoteFundService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 1) {
                        if (null != quoteFund) {
                            quoteFund.setBid(BigDecimal.valueOf(price));
                            quoteFundService.updateByPrimaryKeySelective(quoteFund);
                        } else {
                            quoteFund = insertQuoteFund(stockInfo);
                            quoteFund.setBid(BigDecimal.valueOf(price));
                            quoteFundService.insert(quoteFund);
                        }
                    } else if (field == 2) {
                        if (null != quoteFund) {
                            quoteFund.setAsk(BigDecimal.valueOf(price));
                            quoteFundService.updateByPrimaryKeySelective(quoteFund);
                        } else {
                            quoteFund = insertQuoteFund(stockInfo);
                            quoteFund.setAsk(BigDecimal.valueOf(price));
                            quoteFundService.insert(quoteFund);
                        }
                    } else if (field == 4) {
                        if (null != quoteFund) {
                            quoteFund.setLast(BigDecimal.valueOf(price));
                            quoteFundService.updateByPrimaryKeySelective(quoteFund);
                        } else {
                            quoteFund = insertQuoteFund(stockInfo);
                            quoteFund.setLast(BigDecimal.valueOf(price));
                            quoteFundService.insert(quoteFund);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if(field == 6){

                    } else if(field == 7){

                    } else if (field == 9) {
                        if (null != quoteFund) {
                            quoteFund.setPrevClose(BigDecimal.valueOf(price));
                            quoteFundService.updateByPrimaryKeySelective(quoteFund);
                        } else {
                            quoteFund = insertQuoteFund(stockInfo);
                            quoteFund.setPrevClose(BigDecimal.valueOf(price));
                            quoteFundService.insert(quoteFund);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_FUTURES:
                    QuoteFutures quoteFutures = quoteFuturesService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 1) {
                        if (null != quoteFutures) {
                            quoteFutures.setBid(BigDecimal.valueOf(price));
                            quoteFuturesService.updateByPrimaryKeySelective(quoteFutures);
                        } else {
                            quoteFutures = insertQuoteFutures(stockInfo);
                            quoteFutures.setBid(BigDecimal.valueOf(price));
                            quoteFuturesService.insert(quoteFutures);
                        }
                    } else if (field == 2) {
                        if (null != quoteFutures) {
                            quoteFutures.setAsk(BigDecimal.valueOf(price));
                            quoteFuturesService.updateByPrimaryKeySelective(quoteFutures);
                        } else {
                            quoteFutures = insertQuoteFutures(stockInfo);
                            quoteFutures.setAsk(BigDecimal.valueOf(price));
                            quoteFuturesService.insert(quoteFutures);
                        }
                    } else if (field == 4) {
                        if (null != quoteFutures) {
                            quoteFutures.setLast(BigDecimal.valueOf(price));
                            quoteFuturesService.updateByPrimaryKeySelective(quoteFutures);
                        } else {
                            quoteFutures = insertQuoteFutures(stockInfo);
                            quoteFutures.setLast(BigDecimal.valueOf(price));
                            quoteFuturesService.insert(quoteFutures);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if(field == 6){

                    } else if(field == 7){

                    } else if (field == 9) {
                        if (null != quoteFutures) {
                            quoteFutures.setPrevClose(BigDecimal.valueOf(price));
                            quoteFuturesService.updateByPrimaryKeySelective(quoteFutures);
                        } else {
                            quoteFutures = insertQuoteFutures(stockInfo);
                            quoteFutures.setPrevClose(BigDecimal.valueOf(price));
                            quoteFuturesService.insert(quoteFutures);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_OPTION:
                    QuoteOption quoteOption = quoteOptionService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 1) {
                        if (null != quoteOption) {
                            quoteOption.setBid(BigDecimal.valueOf(price));
                            quoteOptionService.updateByPrimaryKeySelective(quoteOption);
                        } else {
                            quoteOption = insertQuoteOption(stockInfo);
                            quoteOption.setBid(BigDecimal.valueOf(price));
                            quoteOptionService.insert(quoteOption);
                        }
                    } else if (field == 2) {
                        if (null != quoteOption) {
                            quoteOption.setAsk(BigDecimal.valueOf(price));
                            quoteOptionService.updateByPrimaryKeySelective(quoteOption);
                        } else {
                            quoteOption = insertQuoteOption(stockInfo);
                            quoteOption.setAsk(BigDecimal.valueOf(price));
                            quoteOptionService.insert(quoteOption);
                        }
                    } else if (field == 4) {
                        if (null != quoteOption) {
                            quoteOption.setLast(BigDecimal.valueOf(price));
                            quoteOptionService.updateByPrimaryKeySelective(quoteOption);
                        } else {
                            quoteOption = insertQuoteOption(stockInfo);
                            quoteOption.setLast(BigDecimal.valueOf(price));
                            quoteOptionService.insert(quoteOption);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if(field == 6){

                    } else if(field == 7){

                    } else if (field == 9) {
                        if (null != quoteOption) {
                            quoteOption.setPrevClose(BigDecimal.valueOf(price));
                            quoteOptionService.updateByPrimaryKeySelective(quoteOption);
                        } else {
                            quoteOption = insertQuoteOption(stockInfo);
                            quoteOption.setPrevClose(BigDecimal.valueOf(price));
                            quoteOptionService.insert(quoteOption);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_WARRANT:
                    QuoteWarrant quoteWarrant = quoteWarrantService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 1) {
                        if (null != quoteWarrant) {
                            quoteWarrant.setBid(BigDecimal.valueOf(price));
                            quoteWarrantService.updateByPrimaryKeySelective(quoteWarrant);
                        } else {
                            quoteWarrant = insertQuoteWarrant(stockInfo);
                            quoteWarrant.setBid(BigDecimal.valueOf(price));
                            quoteWarrantService.insert(quoteWarrant);
                        }
                    } else if (field == 2) {
                        if (null != quoteWarrant) {
                            quoteWarrant.setAsk(BigDecimal.valueOf(price));
                            quoteWarrantService.updateByPrimaryKeySelective(quoteWarrant);
                        } else {
                            quoteWarrant = insertQuoteWarrant(stockInfo);
                            quoteWarrant.setAsk(BigDecimal.valueOf(price));
                            quoteWarrantService.insert(quoteWarrant);
                        }
                    } else if (field == 4) {
                        if (null != quoteWarrant) {
                            quoteWarrant.setLast(BigDecimal.valueOf(price));
                            quoteWarrantService.updateByPrimaryKeySelective(quoteWarrant);
                        } else {
                            quoteWarrant = insertQuoteWarrant(stockInfo);
                            quoteWarrant.setLast(BigDecimal.valueOf(price));
                            quoteWarrantService.insert(quoteWarrant);
                        }

                        stockInfo.setLastPrice(BigDecimal.valueOf(price));
                        stockInfoService.updateByPrimaryKeySelective(stockInfo);
                    } else if(field == 6){

                    } else if(field == 7){

                    } else if (field == 9) {
                        if (null != quoteWarrant) {
                            quoteWarrant.setPrevClose(BigDecimal.valueOf(price));
                            quoteWarrantService.updateByPrimaryKeySelective(quoteWarrant);
                        } else {
                            quoteWarrant = insertQuoteWarrant(stockInfo);
                            quoteWarrant.setPrevClose(BigDecimal.valueOf(price));
                            quoteWarrantService.insert(quoteWarrant);
                        }
                    }
                    break;
                default:
                    log.error("ibMessageProcessTickPrice 证券类型{}没匹配上", stockInfo.getStockType());
                    break;
            }
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    private QuoteWarrant insertQuoteWarrant(StockInfo stockInfo) {
        QuoteWarrant quoteWarrant = new QuoteWarrant();
        quoteWarrant.setStockinfoId(stockInfo.getId());
        quoteWarrant.setStockCode(stockInfo.getStockSymbol());
        quoteWarrant.setExchangeId(stockInfo.getStockExchange().getId());
        quoteWarrant.setQuoteDate(System.currentTimeMillis());
        quoteWarrant.setPrevClose(BigDecimal.ZERO);
        quoteWarrant.setLast(BigDecimal.ZERO);
        quoteWarrant.setCreateDate(System.currentTimeMillis());
        quoteWarrant.setUpdateDate(System.currentTimeMillis());

        return quoteWarrant;
    }

    private QuoteOption insertQuoteOption(StockInfo stockInfo) {
        QuoteOption quoteOption = new QuoteOption();
        quoteOption.setStockinfoId(stockInfo.getId());
        quoteOption.setStockCode(stockInfo.getStockSymbol());
        quoteOption.setExchangeId(stockInfo.getStockExchange().getId());
        quoteOption.setQuoteDate(System.currentTimeMillis());
        quoteOption.setPrevClose(BigDecimal.ZERO);
        quoteOption.setLast(BigDecimal.ZERO);
        quoteOption.setCreateDate(System.currentTimeMillis());
        quoteOption.setUpdateDate(System.currentTimeMillis());

        return quoteOption;
    }

    private QuoteFutures insertQuoteFutures(StockInfo stockInfo) {
        QuoteFutures quoteFutures = new QuoteFutures();
        quoteFutures.setStockinfoId(stockInfo.getId());
        quoteFutures.setStockCode(stockInfo.getStockSymbol());
        quoteFutures.setExchangeId(stockInfo.getStockExchange().getId());
        quoteFutures.setQuoteDate(System.currentTimeMillis());
        quoteFutures.setPrevClose(BigDecimal.ZERO);
        quoteFutures.setLast(BigDecimal.ZERO);
        quoteFutures.setCreateDate(System.currentTimeMillis());
        quoteFutures.setUpdateDate(System.currentTimeMillis());

        return quoteFutures;
    }

    private QuoteFund insertQuoteFund(StockInfo stockInfo) {
        QuoteFund quoteFund = new QuoteFund();
        quoteFund.setStockinfoId(stockInfo.getId());
        quoteFund.setStockCode(stockInfo.getStockSymbol());
        quoteFund.setExchangeId(stockInfo.getStockExchange().getId());
        quoteFund.setQuoteDate(System.currentTimeMillis());
        quoteFund.setPrevClose(BigDecimal.ZERO);
        quoteFund.setLast(BigDecimal.ZERO);
        quoteFund.setCreateDate(System.currentTimeMillis());
        quoteFund.setUpdateDate(System.currentTimeMillis());

        return quoteFund;
    }

    private QuoteForeignEx insertQuoteForeignEx(StockInfo stockInfo) {
        QuoteForeignEx quoteForeignEx = new QuoteForeignEx();
        quoteForeignEx.setStockinfoId(stockInfo.getId());
        quoteForeignEx.setStockCode(stockInfo.getStockSymbol());
        quoteForeignEx.setExchangeId(stockInfo.getStockExchange().getId());
        quoteForeignEx.setQuoteDate(System.currentTimeMillis());
        quoteForeignEx.setPrevClose(BigDecimal.ZERO);
        quoteForeignEx.setLast(BigDecimal.ZERO);
        quoteForeignEx.setCreateDate(System.currentTimeMillis());
        quoteForeignEx.setUpdateDate(System.currentTimeMillis());

        return quoteForeignEx;
    }

    private QuoteBond insertQuoteBond(StockInfo stockInfo) {
        QuoteBond quoteBond = new QuoteBond();
        quoteBond.setStockinfoId(stockInfo.getId());
        quoteBond.setStockCode(stockInfo.getStockSymbol());
        quoteBond.setExchangeId(stockInfo.getStockExchange().getId());
        quoteBond.setQuoteDate(System.currentTimeMillis());
        quoteBond.setPrevClose(BigDecimal.ZERO);
        quoteBond.setLast(BigDecimal.ZERO);
        quoteBond.setCreateDate(System.currentTimeMillis());
        quoteBond.setUpdateDate(System.currentTimeMillis());

        return quoteBond;
    }

    private QuoteStock insertQuoteStock(StockInfo stockInfo) {
        QuoteStock quoteStock = new QuoteStock();
        quoteStock.setStockinfoId(stockInfo.getId());
        quoteStock.setStockCode(stockInfo.getStockSymbol());
        quoteStock.setExchangeId(stockInfo.getStockExchange().getId());
        quoteStock.setQuoteDate(System.currentTimeMillis());
        quoteStock.setPrevClose(BigDecimal.ZERO);
        quoteStock.setLast(BigDecimal.ZERO);
        quoteStock.setCreateDate(System.currentTimeMillis());
        quoteStock.setUpdateDate(System.currentTimeMillis());

        return quoteStock;
    }

    /**
     * @param tickerId
     * @param field
     * @param size
     */
    public void ibMessageProcessTickSize(int tickerId, int field, int size) {
        // 根据记录tickerId对应的stockInfo
        String cacheKey = new StringBuffer(CacheConst.REDIS_GATEWAY_IB_PREFIX).append(GlobalConst.SEPARATOR).append("tickerId").append(tickerId).toString();
        StockInfo stockInfo = (StockInfo) RedisUtils.getObject(cacheKey);
        //将时间转换成YYYYMMDD
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Long currentTime = Long.parseLong(simpleDateFormat.format(System.currentTimeMillis()));
        // log.info("stockInfo:{}", stockInfo.toString());
        try {
            switch (stockInfo.getStockType()) {
                case BizConst.STOCK_TYPE_STOCK:
                    QuoteStock quoteStock = quoteStockService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (null != quoteStock) {
                        log.info("quoteStock:{}, currentTime:{}", quoteStock.toString(), currentTime);
                    }
                    if (field == 0) {
                        if (null != quoteStock) {
                            quoteStock.setBidSize(Long.valueOf(size));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setBidSize(Long.valueOf(size));
                            quoteStockService.insert(quoteStock);
                        }
                    } else if (field == 3) {
                        if (null != quoteStock) {
                            quoteStock.setAskSize(Long.valueOf(size));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setAskSize(Long.valueOf(size));
                            quoteStockService.insert(quoteStock);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                        if (null != quoteStock) {
                            quoteStock.setVolume(BigDecimal.valueOf(size));
                            quoteStockService.updateByPrimaryKeySelective(quoteStock);
                        } else {
                            quoteStock = insertQuoteStock(stockInfo);
                            quoteStock.setVolume(BigDecimal.valueOf(size));
                            quoteStockService.insert(quoteStock);
                        }
                    }
                    break;
                case BizConst.STOCK_TYPE_BOND:
                    QuoteBond quoteBond = quoteBondService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 0) {
                        if (null != quoteBond) {
                            quoteBond.setBidSize(Long.valueOf(size));
                            quoteBondService.updateByPrimaryKeySelective(quoteBond);
                        } else {
                            quoteBond = insertQuoteBond(stockInfo);
                            quoteBond.setBidSize(Long.valueOf(size));
                            quoteBondService.insert(quoteBond);
                        }
                    } else if (field == 3) {
                        if (null != quoteBond) {
                            quoteBond.setAskSize(Long.valueOf(size));
                            quoteBondService.updateByPrimaryKeySelective(quoteBond);
                        } else {
                            quoteBond = insertQuoteBond(stockInfo);
                            quoteBond.setAskSize(Long.valueOf(size));
                            quoteBondService.insert(quoteBond);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                    }
                    break;
                case BizConst.STOCK_TYPE_FOREIGNEX:
                    QuoteForeignEx quoteForeignEx = quoteForeignExService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 0) {
                        if (null != quoteForeignEx) {
                            quoteForeignEx.setBidSize(Long.valueOf(size));
                            quoteForeignExService.updateByPrimaryKeySelective(quoteForeignEx);
                        } else {
                            quoteForeignEx = insertQuoteForeignEx(stockInfo);
                            quoteForeignEx.setBidSize(Long.valueOf(size));
                            quoteForeignExService.insert(quoteForeignEx);
                        }
                    } else if (field == 3) {
                        if (null != quoteForeignEx) {
                            quoteForeignEx.setAskSize(Long.valueOf(size));
                            quoteForeignExService.updateByPrimaryKeySelective(quoteForeignEx);
                        } else {
                            quoteForeignEx = insertQuoteForeignEx(stockInfo);
                            quoteForeignEx.setAskSize(Long.valueOf(size));
                            quoteForeignExService.insert(quoteForeignEx);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                    }
                    break;
                case BizConst.STOCK_TYPE_FUND:
                    QuoteFund quoteFund = quoteFundService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 0) {
                        if (null != quoteFund) {
                            quoteFund.setBidSize(Long.valueOf(size));
                            quoteFundService.updateByPrimaryKeySelective(quoteFund);
                        } else {
                            quoteFund = insertQuoteFund(stockInfo);
                            quoteFund.setBidSize(Long.valueOf(size));
                            quoteFundService.insert(quoteFund);
                        }
                    } else if (field == 3) {
                        if (null != quoteFund) {
                            quoteFund.setAskSize(Long.valueOf(size));
                            quoteFundService.updateByPrimaryKeySelective(quoteFund);
                        } else {
                            quoteFund = insertQuoteFund(stockInfo);
                            quoteFund.setAskSize(Long.valueOf(size));
                            quoteFundService.insert(quoteFund);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                    }
                    break;
                case BizConst.STOCK_TYPE_FUTURES:
                    QuoteFutures quoteFutures = quoteFuturesService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 0) {
                        if (null != quoteFutures) {
                            quoteFutures.setBidSize(Long.valueOf(size));
                            quoteFuturesService.updateByPrimaryKeySelective(quoteFutures);
                        } else {
                            quoteFutures = insertQuoteFutures(stockInfo);
                            quoteFutures.setBidSize(Long.valueOf(size));
                            quoteFuturesService.insert(quoteFutures);
                        }
                    } else if (field == 3) {
                        if (null != quoteFutures) {
                            quoteFutures.setAskSize(Long.valueOf(size));
                            quoteFuturesService.updateByPrimaryKeySelective(quoteFutures);
                        } else {
                            quoteFutures = insertQuoteFutures(stockInfo);
                            quoteFutures.setAskSize(Long.valueOf(size));
                            quoteFuturesService.insert(quoteFutures);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                    }
                    break;
                case BizConst.STOCK_TYPE_OPTION:
                    QuoteOption quoteOption = quoteOptionService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 0) {
                        if (null != quoteOption) {
                            quoteOption.setBidSize(Long.valueOf(size));
                            quoteOptionService.updateByPrimaryKeySelective(quoteOption);
                        } else {
                            quoteOption = insertQuoteOption(stockInfo);
                            quoteOption.setBidSize(Long.valueOf(size));
                            quoteOptionService.insert(quoteOption);
                        }
                    } else if (field == 3) {
                        if (null != quoteOption) {
                            quoteOption.setAskSize(Long.valueOf(size));
                            quoteOptionService.updateByPrimaryKeySelective(quoteOption);
                        } else {
                            quoteOption = insertQuoteOption(stockInfo);
                            quoteOption.setAskSize(Long.valueOf(size));
                            quoteOptionService.insert(quoteOption);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                    }
                    break;
                case BizConst.STOCK_TYPE_WARRANT:
                    QuoteWarrant quoteWarrant = quoteWarrantService.selectQuoteByStockCodeAndQuoteDate(stockInfo.getStockSymbol(), currentTime);
                    if (field == 0) {
                        if (null != quoteWarrant) {
                            quoteWarrant.setBidSize(Long.valueOf(size));
                            quoteWarrantService.updateByPrimaryKeySelective(quoteWarrant);
                        } else {
                            quoteWarrant = insertQuoteWarrant(stockInfo);
                            quoteWarrant.setBidSize(Long.valueOf(size));
                            quoteWarrantService.insert(quoteWarrant);
                        }
                    } else if (field == 3) {
                        if (null != quoteWarrant) {
                            quoteWarrant.setAskSize(Long.valueOf(size));
                            quoteWarrantService.updateByPrimaryKeySelective(quoteWarrant);
                        } else {
                            quoteWarrant = insertQuoteWarrant(stockInfo);
                            quoteWarrant.setAskSize(Long.valueOf(size));
                            quoteWarrantService.insert(quoteWarrant);
                        }
                    } else if (field == 5) {

                    } else if (field == 8) {
                    }
                    break;
                default:
                    log.error("ibMessageProcessTickSize 证券类型{}没匹配上", stockInfo.getStockType());
                    break;
            }
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            ex.printStackTrace();
        }
    }

    /**
     * @param tickerId
     * @param tickType
     * @param value
     */
    public void ibMessageProcessTickString(int tickerId, int tickType, String value) {
        // 暂时用不到
    }

    /**
     * @param tickerId
     * @param field
     * @param impliedVol
     * @param delta
     * @param optPrice
     * @param pvDividend
     * @param gamma
     * @param vega
     * @param theta
     * @param undPrice
     */
    public void ibMessageProcessTickOptionComputation(int tickerId, int field, double impliedVol, double delta, double optPrice,
                                                      double pvDividend, double gamma, double vega, double theta, double undPrice) {
        // 暂时用不到
    }
}
