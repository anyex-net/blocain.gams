package com.fintech.gams.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fintech.gams.bean.GenericController;
import com.fintech.gams.bean.JsonMessage;
import com.fintech.gams.bean.PaginateResult;
import com.fintech.gams.bean.Pagination;
import com.fintech.gams.consts.GlobalConst;
import com.fintech.gams.enums.CommonEnums;
import com.fintech.gams.exception.BusinessException;
import com.fintech.gams.stock.model.*;
import com.fintech.gams.stock.service.StockHotQuoteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 热门证券行情控制器
 * <p>File: StockHotQuoteController.java </p>
 * <p>Title: StockHotQuoteController </p>
 * <p>Description: StockHotQuoteController </p>
 * <p>Copyright: Copyright (c) 2019-06-14</p>
 * <p>Company: BloCain</p>
 *
 * @author Playguy
 * @version 1.0
 */
@RestController
@RequestMapping(GlobalConst.STOCK)
@Api(description = "热门证券行情控制器")
public class StockHotQuoteController extends GenericController
{
    @Autowired(required = false)
    private StockHotQuoteService hotStockQuoteService;
    
    @GetMapping("/quote/stock")
    @ApiOperation(value = "股票行情", httpMethod = "GET")
    public JsonMessage stockQuote(Pagination pagin) throws BusinessException
    {
        PaginateResult<HotStockQuote> result = hotStockQuoteService.searchStockQuote(pagin, new HotStockQuote());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @GetMapping("/quote/fund")
    @ApiOperation(value = "基金行情", httpMethod = "GET")
    public JsonMessage fundQuote(Pagination pagin) throws BusinessException
    {
        PaginateResult<HotFundQuote> result = hotStockQuoteService.searchFundQuote(pagin, new HotFundQuote());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @GetMapping("/quote/foreignex")
    @ApiOperation(value = "外汇行情", httpMethod = "GET")
    public JsonMessage foreignexQuote(Pagination pagin) throws BusinessException
    {
        PaginateResult<HotForeignExQuote> result = hotStockQuoteService.searchForeignExQuote(pagin, new HotForeignExQuote());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @GetMapping("/quote/option")
    @ApiOperation(value = "期权行情", httpMethod = "GET")
    public JsonMessage optionQuote(Pagination pagin) throws BusinessException
    {
        PaginateResult<HotOptionQuote> result = hotStockQuoteService.searchOptionQuote(pagin, new HotOptionQuote());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @GetMapping("/quote/futures")
    @ApiOperation(value = "期货行情", httpMethod = "GET")
    public JsonMessage futuresQuote(Pagination pagin) throws BusinessException
    {
        PaginateResult<HotFuturesQuote> result = hotStockQuoteService.searchFuturesQuote(pagin, new HotFuturesQuote());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
    
    @GetMapping("/quote/bond")
    @ApiOperation(value = "债券行情", httpMethod = "GET")
    public JsonMessage bondQuote(Pagination pagin) throws BusinessException
    {
        PaginateResult<HotBondQuote> result = hotStockQuoteService.searchBondQuote(pagin, new HotBondQuote());
        return getJsonMessage(CommonEnums.SUCCESS, result);
    }
}
