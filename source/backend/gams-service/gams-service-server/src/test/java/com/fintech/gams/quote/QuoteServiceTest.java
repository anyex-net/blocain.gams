package com.fintech.gams.quote;

import com.fintech.gams.BaseServiceImplTest;
import com.fintech.gams.quote.entity.*;
import com.fintech.gams.quote.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;

public class QuoteServiceTest extends BaseServiceImplTest
{

    @Autowired
    private QuoteStockService quoteStockService;

    @Autowired
    private QuoteBondService quoteBondService;

    @Autowired
    private QuoteForeignExService quoteForeignExService;

    @Autowired
    private QuoteFundService quoteFundService;

    @Autowired
    private QuoteFuturesService quoteFuturesService;

    @Autowired
    private QuoteOptionService quoteOptionService;

    @Autowired
    private QuoteWarrantService quoteWarrantService;

    @Test
    public void insertQuoteStock(){
        //将时间转换成YYYYMMDD
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Long currentTime = Long.parseLong(simpleDateFormat.format(System.currentTimeMillis()));
        System.out.println(currentTime);

        String stockCode = "c";
        Long quoteDate = 20190911l;
        QuoteStock entity = quoteStockService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);

        if(entity != null){
            System.out.println(entity.toString());
        }
    }

    @Test
    public void insertQuoteBond(){
        String stockCode = "b";
        Long quoteDate = 20190911l;
        QuoteBond entity = quoteBondService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);

        if(entity != null){
            System.out.println(entity.toString());
        }
    }

    @Test
    public void insertQuoteForeignEx(){
        String stockCode = "b";
        Long quoteDate = 20190911l;
        QuoteForeignEx entity = quoteForeignExService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);

        if(entity != null){
            System.out.println(entity.toString());
        }

    }

    @Test
    public void insertQuoteFund(){
        String stockCode = "b";
        Long quoteDate = 20190911l;
        QuoteFund entity = quoteFundService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);

        if(entity != null){
            System.out.println(entity.toString());
        }
    }

    @Test
    public void insertQuoteFutures(){
        String stockCode = "b";
        Long quoteDate = 20190911l;
        QuoteFutures entity = quoteFuturesService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);
        if(entity != null){
            System.out.println(entity.toString());
        }
    }

    @Test
    public void insertQuoteOption(){
        String stockCode = "b";
        Long quoteDate = 20190911l;
        QuoteOption entity = quoteOptionService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);
        if(entity != null){
            System.out.println(entity.toString());
        }
    }

    @Test
    public void insertQuoteWarrant(){
        String stockCode = "b";
        Long quoteDate = 20190911l;
        QuoteWarrant entity = quoteWarrantService.selectQuoteByStockCodeAndQuoteDate(stockCode,quoteDate);
        if(entity != null){
            System.out.println(entity.toString());
        }
    }
}
