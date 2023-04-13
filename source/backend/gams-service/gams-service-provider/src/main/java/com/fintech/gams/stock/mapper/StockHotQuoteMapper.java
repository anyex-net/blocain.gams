package com.fintech.gams.stock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fintech.gams.stock.model.*;

/**
 * StockHotQuoteMapper
 * <p>File: StockHotQuoteMapper.java </p>
 * <p>Title: StockHotQuoteMapper </p>
 * <p>Description: StockHotQuoteMapper </p>
 * <p>Copyright: Copyright (c) 2019-06-14</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Mapper
public interface StockHotQuoteMapper
{
    /**
     * 查询热门股票行情
     * @param model
     * @return 
     */
    List<HotStockQuote> searchStockQuote(HotStockQuote model);
    
    /**
     * 查询热门基金行情
     * @param model
     * @returnF 
     */
    List<HotFundQuote> searchFundQuote(HotFundQuote model);
    
    /**
     * 查询外汇行情
     * @param model
     * @returnF 
     */
    List<HotForeignExQuote> searchForeignExQuote(HotForeignExQuote model);
    
    /**
     * 查询期权行情
     * @param model
     * @returnF 
     */
    List<HotOptionQuote> searchOptionQuote(HotOptionQuote model);
    
    /**
     * 查询期货行情
     * @param model
     * @returnF 
     */
    List<HotFuturesQuote> searchFuturesQuote(HotFuturesQuote model);
    
    /**
     * 查询债券行情
     * @param model
     * @returnF 
     */
    List<HotBondQuote> searchBondQuote(HotBondQuote model);
}
