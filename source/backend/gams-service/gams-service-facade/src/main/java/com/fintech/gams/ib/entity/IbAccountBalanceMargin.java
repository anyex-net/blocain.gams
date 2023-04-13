/*
 * Copyright 2017 GAMS, Inc. All rights reserved. com.gams
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fintech.gams.ib.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fintech.gams.bean.GenericEntity;
import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IB账户资金保证金表 实体对象
 * <p>File：IbAccountBalanceMargin.java</p>
 * <p>Title: IbAccountBalanceMargin</p>
 * <p>Description:IbAccountBalanceMargin</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: GAMS</p>
 * @author Playguy
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "IB账户资金保证金表")
public class IbAccountBalanceMargin extends GenericEntity {
	
	private static final long serialVersionUID = 1L;
	
	/**账户ID*/
	@NotNull(message = "账户ID不可为空")
	@ApiModelProperty(value = "账户ID", required = true)
	@JsonSerialize(using = ToStringSerializer.class)
	private java.lang.Long accountId;

	/**IB对应AccountCode*/
	@NotNull(message = "IB对应AccountCode不可为空")
	@ApiModelProperty(value = "IB对应AccountCode", required = true)
	private java.lang.String accountCode;

	/**账户类型*/
	@ApiModelProperty(value = "账户类型", required = true)
	private java.lang.String accountType;

	/**账户就绪*/
	@ApiModelProperty(value = "账户就绪", required = true)
	private java.lang.String accountReady;

	/**Cushion*/
	@ApiModelProperty(value = "Cushion")
	private java.math.BigDecimal cushion;

	/**杠杆*/
	@ApiModelProperty(value = "杠杆")
	private java.math.BigDecimal leverageS;

	/**杠杆*/
	@ApiModelProperty(value = "杠杆")
	private java.math.BigDecimal leverageC;

	/**前瞻下一变化*/
	@ApiModelProperty(value = "前瞻下一变化")
	private java.math.BigDecimal lookAheadNextChange;

	/**净清算值和保证金审查*/
	@ApiModelProperty(value = "净清算值和保证金审查")
	private java.lang.String nlvAndMarginInReview;

	/**分部标题*/
	@ApiModelProperty(value = "分部标题")
	private java.lang.String segmentTitleS;

	/**交易类型*/
	@ApiModelProperty(value = "交易类型")
	private java.lang.String tradingTypeS;

	/**分部标题*/
	@ApiModelProperty(value = "分部标题")
	private java.lang.String segmentTitleC;

	/**交易类型*/
	@ApiModelProperty(value = "交易类型")
	private java.lang.String tradingTypeC;

	/**剩余日内交易数T**/
	@ApiModelProperty(value = "剩余日内交易数T")
	private java.math.BigDecimal dayTradesRemaining;

	/**剩余日内交易数T+1**/
	@ApiModelProperty(value = "剩余日内交易数T+1")
	private java.math.BigDecimal dayTradesRemainingT1;

	/**剩余日内交易数T+2**/
	@ApiModelProperty(value = "剩余日内交易数T+2")
	private java.math.BigDecimal dayTradesRemainingT2;

	/**剩余日内交易数T+3**/
	@ApiModelProperty(value = "剩余日内交易数T+3")
	private java.math.BigDecimal dayTradesRemainingT3;

	/**剩余日内交易数T+4**/
	@ApiModelProperty(value = "剩余日内交易数T+4")
	private java.math.BigDecimal dayTradesRemainingT4;

	/**币种*/
	@ApiModelProperty(value = "币种", required = true)
	private java.lang.String currency;

	/**应计现金*/
	@ApiModelProperty(value = "应计现金")
	private java.math.BigDecimal accruedCash;

	/**应计现金*/
	@ApiModelProperty(value = "应计现金")
	private java.math.BigDecimal accruedCashS;

	/**应计现金*/
	@ApiModelProperty(value = "应计现金")
	private java.math.BigDecimal accruedCashC;

	/**应计股息*/
	@ApiModelProperty(value = "应计股息")
	private java.math.BigDecimal accruedDividend;

	/**应计股息*/
	@ApiModelProperty(value = "应计股息")
	private java.math.BigDecimal accruedDividendS;

	/**应计股息*/
	@ApiModelProperty(value = "应计股息")
	private java.math.BigDecimal accruedDividendC;

	/**可用资金*/
	@ApiModelProperty(value = "可用资金")
	private java.math.BigDecimal availableFunds;

	/**可用资金*/
	@ApiModelProperty(value = "可用资金")
	private java.math.BigDecimal availableFundsS;

	/**可用资金*/
	@ApiModelProperty(value = "可用资金")
	private java.math.BigDecimal availableFundsC;

	/**Billable*/
	@ApiModelProperty(value = "Billable")
	private java.math.BigDecimal billable;

	/**Billable_S*/
	@ApiModelProperty(value = "Billable_S")
	private java.math.BigDecimal billableS;

	/**Billable_C*/
	@ApiModelProperty(value = "Billable_C")
	private java.math.BigDecimal billableC;

	/**购买力*/
	@ApiModelProperty(value = "购买力")
	private java.math.BigDecimal buyingPower;

	/**含贷款价值的资产*/
	@ApiModelProperty(value = "含贷款价值的资产")
	private java.math.BigDecimal equityWithLoanValue;

	/**含贷款价值的资产*/
	@ApiModelProperty(value = "含贷款价值的资产")
	private java.math.BigDecimal equityWithLoanValueS;

	/**含贷款价值的资产*/
	@ApiModelProperty(value = "含贷款价值的资产")
	private java.math.BigDecimal equityWithLoanValueC;

	/**剩余流动性*/
	@ApiModelProperty(value = "剩余流动性")
	private java.math.BigDecimal excessLiquidity;

	/**剩余流动性*/
	@ApiModelProperty(value = "剩余流动性")
	private java.math.BigDecimal excessLiquidityS;

	/**剩余流动性*/
	@ApiModelProperty(value = "剩余流动性")
	private java.math.BigDecimal excessLiquidityC;

	/**全部可用资金*/
	@ApiModelProperty(value = "全部可用资金")
	private java.math.BigDecimal fullAvailableFunds;

	/**全部可用资金*/
	@ApiModelProperty(value = "全部可用资金")
	private java.math.BigDecimal fullAvailableFundsS;

	/**全部可用资金*/
	@ApiModelProperty(value = "全部可用资金")
	private java.math.BigDecimal fullAvailableFundsC;

	/**全部过剩流动性*/
	@ApiModelProperty(value = "全部过剩流动性")
	private java.math.BigDecimal fullExcessLiquidity;

	/**全部过剩流动性*/
	@ApiModelProperty(value = "全部过剩流动性")
	private java.math.BigDecimal fullExcessLiquidityS;

	/**全部过剩流动性*/
	@ApiModelProperty(value = "全部过剩流动性")
	private java.math.BigDecimal fullExcessLiquidityC;

	/**全部初始保证金要求*/
	@ApiModelProperty(value = "全部初始保证金要求")
	private java.math.BigDecimal fullInitMarginReq;

	/**全部初始保证金要求*/
	@ApiModelProperty(value = "全部初始保证金要求")
	private java.math.BigDecimal fullInitMarginReqS;

	/**全部初始保证金要求*/
	@ApiModelProperty(value = "全部初始保证金要求")
	private java.math.BigDecimal fullInitMarginReqC;

	/**全部维持保证金要求*/
	@ApiModelProperty(value = "全部维持保证金要求")
	private java.math.BigDecimal fullMaintMarginReq;

	/**全部维持保证金要求*/
	@ApiModelProperty(value = "全部维持保证金要求")
	private java.math.BigDecimal fullMaintMarginReqS;

	/**全部维持保证金要求*/
	@ApiModelProperty(value = "全部维持保证金要求")
	private java.math.BigDecimal fullMaintMarginReqC;

	/**总头寸价值*/
	@ApiModelProperty(value = "总头寸价值")
	private java.math.BigDecimal grossPositionValue;

	/**总头寸价值*/
	@ApiModelProperty(value = "总头寸价值")
	private java.math.BigDecimal grossPositionValueS;

	/**总头寸价值*/
	@ApiModelProperty(value = "总头寸价值")
	private java.math.BigDecimal grossPositionValueC;

	/**担保*/
	@ApiModelProperty(value = "担保")
	private java.math.BigDecimal guarantee;

	/**担保*/
	@ApiModelProperty(value = "担保")
	private java.math.BigDecimal guaranteeS;

	/**担保*/
	@ApiModelProperty(value = "担保")
	private java.math.BigDecimal guaranteeC;

	/**印度股票*/
	@ApiModelProperty(value = "印度股票")
	private java.math.BigDecimal indianStockHaircut;

	/**印度股票*/
	@ApiModelProperty(value = "印度股票")
	private java.math.BigDecimal indianStockHaircutS;

	/**印度股票*/
	@ApiModelProperty(value = "印度股票")
	private java.math.BigDecimal indianStockHaircutC;

	/**初始保证金要求*/
	@ApiModelProperty(value = "初始保证金要求")
	private java.math.BigDecimal initMarginReq;

	/**初始保证金要求*/
	@ApiModelProperty(value = "初始保证金要求")
	private java.math.BigDecimal initMarginReqS;

	/**初始保证金要求*/
	@ApiModelProperty(value = "初始保证金要求")
	private java.math.BigDecimal initMarginReqC;

	/**前瞻可用资金*/
	@ApiModelProperty(value = "前瞻可用资金")
	private java.math.BigDecimal lookAheadAvailableFunds;

	/**前瞻可用资金*/
	@ApiModelProperty(value = "前瞻可用资金")
	private java.math.BigDecimal lookAheadAvailableFundsS;

	/**前瞻可用资金*/
	@ApiModelProperty(value = "前瞻可用资金")
	private java.math.BigDecimal lookAheadAvailableFundsC;

	/**前瞻剩余流动性*/
	@ApiModelProperty(value = "前瞻剩余流动性")
	private java.math.BigDecimal lookAheadExcessLiquidity;

	/**前瞻剩余流动性*/
	@ApiModelProperty(value = "前瞻剩余流动性")
	private java.math.BigDecimal lookAheadExcessLiquidityS;

	/**前瞻剩余流动性*/
	@ApiModelProperty(value = "前瞻剩余流动性")
	private java.math.BigDecimal lookAheadExcessLiquidityC;

	/**前瞻初始保证金要求*/
	@ApiModelProperty(value = "前瞻初始保证金要求")
	private java.math.BigDecimal lookAheadInitMarginReq;

	/**前瞻初始保证金要求*/
	@ApiModelProperty(value = "前瞻初始保证金要求")
	private java.math.BigDecimal lookAheadInitMarginReqS;

	/**前瞻初始保证金要求*/
	@ApiModelProperty(value = "前瞻初始保证金要求")
	private java.math.BigDecimal lookAheadInitMarginReqC;

	/**前瞻维持保证金要求*/
	@ApiModelProperty(value = "前瞻维持保证金要求")
	private java.math.BigDecimal lookAheadMaintMarginReq;

	/**前瞻维持保证金要求*/
	@ApiModelProperty(value = "前瞻维持保证金要求")
	private java.math.BigDecimal lookAheadMaintMarginReqS;

	/**前瞻维持保证金要求*/
	@ApiModelProperty(value = "前瞻维持保证金要求")
	private java.math.BigDecimal lookAheadMaintMarginReqC;

	/**维持保证金要求*/
	@ApiModelProperty(value = "维持保证金要求")
	private java.math.BigDecimal maintMarginReq;

	/**维持保证金要求*/
	@ApiModelProperty(value = "维持保证金要求")
	private java.math.BigDecimal maintMarginReqS;

	/**维持保证金要求*/
	@ApiModelProperty(value = "维持保证金要求")
	private java.math.BigDecimal maintMarginReqC;

	/**净清算*/
	@ApiModelProperty(value = "净清算")
	private java.math.BigDecimal netLiquidation;

	/**净清算*/
	@ApiModelProperty(value = "净清算")
	private java.math.BigDecimal netLiquidationS;

	/**净清算*/
	@ApiModelProperty(value = "净清算")
	private java.math.BigDecimal netLiquidationC;

	/**净清算不确定*/
	@ApiModelProperty(value = "净清算不确定")
	private java.math.BigDecimal netLiquidationUncertainty;

	/**PAShares_Value*/
	@ApiModelProperty(value = "PAShares_Value")
	private java.math.BigDecimal pasharesValue;

	/**PAShares_Value_S*/
	@ApiModelProperty(value = "PAShares_Value_S")
	private java.math.BigDecimal pasharesValueS;

	/**PAShares_Value_C*/
	@ApiModelProperty(value = "PAShares_Value_C")
	private java.math.BigDecimal pasharesValueC;

	/**到期后剩余*/
	@ApiModelProperty(value = "到期后剩余")
	private java.math.BigDecimal postExpirationExcess;

	/**到期后剩余*/
	@ApiModelProperty(value = "到期后剩余")
	private java.math.BigDecimal postExpirationExcessS;

	/**到期后剩余*/
	@ApiModelProperty(value = "到期后剩余")
	private java.math.BigDecimal postExpirationExcessC;

	/**到期后保证金*/
	@ApiModelProperty(value = "到期后保证金")
	private java.math.BigDecimal postExpirationMargin;

	/**到期后保证金*/
	@ApiModelProperty(value = "到期后保证金")
	private java.math.BigDecimal postExpirationMarginS;

	/**到期后保证金*/
	@ApiModelProperty(value = "到期后保证金")
	private java.math.BigDecimal postExpirationMarginC;

	/**RegT权益**/
	@ApiModelProperty(value = "RegT权益")
	private java.math.BigDecimal regTEquity;

	/**RegT权益S**/
	@ApiModelProperty(value = "RegT权益S")
	private java.math.BigDecimal regTEquityS;

	/**RegT权益C**/
	@ApiModelProperty(value = "RegT权益C")
	private java.math.BigDecimal regTEquityC;

	/**RegT保证金**/
	@ApiModelProperty(value = "RegT保证金")
	private java.math.BigDecimal regTMargin;

	/**RegT保证金S**/
	@ApiModelProperty(value = "RegT保证金S")
	private java.math.BigDecimal regTMarginS;

	/**RegT保证金C**/
	@ApiModelProperty(value = "RegT保证金C")
	private java.math.BigDecimal regTMarginC;

	/**特别备忘录账户**/
	@ApiModelProperty(value = "特别备忘录账户")
	private java.math.BigDecimal SMA;

	/**特别备忘录账户S**/
	@ApiModelProperty(value = "特别备忘录账户S")
	private java.math.BigDecimal SMAS;

	/**特别备忘录账户C**/
	@ApiModelProperty(value = "特别备忘录账户C")
	private java.math.BigDecimal SMAC;

	/**总现金价值*/
	@ApiModelProperty(value = "总现金价值")
	private java.math.BigDecimal totalCashvalue;

	/**总现金价值*/
	@ApiModelProperty(value = "总现金价值")
	private java.math.BigDecimal totalCashvalueS;

	/**总现金价值*/
	@ApiModelProperty(value = "总现金价值")
	private java.math.BigDecimal totalCashvalueC;

	/**待支付的借记卡费用*/
	@ApiModelProperty(value = "待支付的借记卡费用")
	private java.math.BigDecimal debitCardPendingCharges;

	/**待支付的借记卡费用*/
	@ApiModelProperty(value = "待支付的借记卡费用")
	private java.math.BigDecimal debitCardPendingChargesS;

	/**待支付的借记卡费用*/
	@ApiModelProperty(value = "待支付的借记卡费用")
	private java.math.BigDecimal debitCardPendingChargesC;

	/**备注*/
	@ApiModelProperty(value = "备注")
	private java.lang.String remark;

	/**创建时间*/
	@ApiModelProperty(value = "创建时间")
	private java.lang.Long createDate;

	/**修改时间*/
	@ApiModelProperty(value = "修改时间")
	private java.lang.Long updateDate;

	/***************关联查询数据***************/
	private java.lang.String accountName;

}

