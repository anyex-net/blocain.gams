package com.fintech.gams.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.stock.mapper.StockHotQuoteMapper;
import com.fintech.gams.stock.model.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * StockHotQuoteServiceImpl
 * <p>File: StockHotQuoteServiceImpl.java </p>
 * <p>Title: StockHotQuoteServiceImpl </p>
 * <p>Description: StockHotQuoteServiceImpl </p>
 * <p>Copyright: Copyright (c) 2019-06-14</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@Service
@Transactional(value = "transactionManager", readOnly = true)
public class StockHotQuoteServiceImpl implements StockHotQuoteService
{
    @Autowired(required = false)
    private StockHotQuoteMapper hotStockQuoteMapper;
    
    @Override
    public PaginateResult<HotStockQuote> searchStockQuote(Pagination pagin, HotStockQuote model) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HotStockQuote> pageInfo = PageInfo.of(hotStockQuoteMapper.searchStockQuote(model));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
    
    @Override
    public PaginateResult<HotFundQuote> searchFundQuote(Pagination pagin, HotFundQuote model) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HotFundQuote> pageInfo = PageInfo.of(hotStockQuoteMapper.searchFundQuote(model));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
    
    @Override
    public PaginateResult<HotForeignExQuote> searchForeignExQuote(Pagination pagin, HotForeignExQuote model) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HotForeignExQuote> pageInfo = PageInfo.of(hotStockQuoteMapper.searchForeignExQuote(model));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
    
    @Override
    public PaginateResult<HotOptionQuote> searchOptionQuote(Pagination pagin, HotOptionQuote model) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HotOptionQuote> pageInfo = PageInfo.of(hotStockQuoteMapper.searchOptionQuote(model));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
    
    @Override
    public PaginateResult<HotFuturesQuote> searchFuturesQuote(Pagination pagin, HotFuturesQuote model) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HotFuturesQuote> pageInfo = PageInfo.of(hotStockQuoteMapper.searchFuturesQuote(model));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
    
    @Override
    public PaginateResult<HotBondQuote> searchBondQuote(Pagination pagin, HotBondQuote model) throws BusinessException
    {
        PageHelper.startPage(pagin.getPage(), pagin.getRows());
        PageInfo<HotBondQuote> pageInfo = PageInfo.of(hotStockQuoteMapper.searchBondQuote(model));
        pagin.setTotalRows(pageInfo.getTotal());
        pagin.setPage(pageInfo.getPageNum());
        return new PaginateResult<>(pagin, pageInfo.getList());
    }
}
