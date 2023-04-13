package com.fintech.gams.stock.service;

import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.stock.model.*;

/**
 * 热门证券行情服务接口
 * <p>File: StockHotQuoteService.java </p>
 * <p>Title: StockHotQuoteService </p>
 * <p>Description: StockHotQuoteService </p>
 * <p>Copyright: Copyright (c) 2019-06-14</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public interface StockHotQuoteService
{
    /**
     * 查询热门股票行情
     * @param pagin
     * @param model
     * @return
     * @throws BusinessException
     */
    PaginateResult<HotStockQuote> searchStockQuote(Pagination pagin, HotStockQuote model) throws BusinessException;
    
    /**
    * 查询热门基金行情
    * @param pagin
    * @param model
    * @returnF
    * @throws BusinessException
    */
    PaginateResult<HotFundQuote> searchFundQuote(Pagination pagin, HotFundQuote model) throws BusinessException;
    
    /**
    * 查询热门基金行情
    * @param pagin
    * @param model
    * @returnF
    * @throws BusinessException
    */
    PaginateResult<HotForeignExQuote> searchForeignExQuote(Pagination pagin, HotForeignExQuote model) throws BusinessException;
    
    /**
    * 查询期权行情
    * @param pagin
    * @param model
    * @returnF
    * @throws BusinessException
    */
    PaginateResult<HotOptionQuote> searchOptionQuote(Pagination pagin, HotOptionQuote model) throws BusinessException;
    
    /**
    * 查询期货行情
    * @param pagin
    * @param model
    * @returnF
    * @throws BusinessException
    */
    PaginateResult<HotFuturesQuote> searchFuturesQuote(Pagination pagin, HotFuturesQuote model) throws BusinessException;
    
    /**
    * 查询债券行情
    * @param pagin
    * @param model
    * @returnF
    * @throws BusinessException
    */
    PaginateResult<HotBondQuote> searchBondQuote(Pagination pagin, HotBondQuote model) throws BusinessException;
}
