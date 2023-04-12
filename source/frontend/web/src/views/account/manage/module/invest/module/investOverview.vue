<template>
  <div class="app-container">
    <div class="invest-overview-wrapper">
      <span class="invest-title">{{ $t('account.manage.invest.overview.NAV') }}<!--资产净值(NAV)--></span>
      <el-row :gutter="35" style="margin-top: 30px;">
        <el-col :span="6">
          <div class="invest-content">
            <p v-if="assetType === 'account' && marketData[0]" class="content-title">{{ marketData[0].totalCashBalanceStart | keepNumTwo }}</p>
            <p v-else-if="assetType === 'invest' && marketData[7]" class="content-title">{{ marketData[7].totalCashBalanceStart | keepNumTwo }}</p>
            <p v-else class="content-title">0</p>
            <p class="content-txt">{{ $t('account.manage.invest.overview.beginning_value') }}<!--初始价值--></p>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="invest-content">
            <p v-if="assetType === 'account' && marketData[0]" class="content-title">{{ marketData[0].totalCashBalanceEnd | keepNumTwo }}</p>
            <p v-else-if="assetType === 'invest'&& marketData[7]" class="content-title">{{ marketData[7].totalCashBalanceEnd | keepNumTwo }}</p>
            <p v-else class="content-title">0</p>
            <p class="content-txt">{{ $t('account.manage.invest.overview.ending_value') }}<!--结束价值--></p>
          </div>
        </el-col>
        <el-col :span="6">
          <div class="invest-content">
            <p v-if="assetType === 'account' && marketData[0]" class="content-title" style="color: #FF3E4D;">{{ marketData[0].returnRate | keepNumTwo }}%</p>
            <p v-else-if="assetType === 'invest'&& marketData[7]" class="content-title" style="color: #FF3E4D;">{{ marketData[7].returnRate | keepNumTwo }}%</p>
            <p v-else class="content-title">0</p>
            <p class="content-txt">{{ $t('account.manage.invest.overview.return') }}<!--回报率--></p>
          </div>
        </el-col>
        <div style="float: right;">
          <span style="color: #999999;margin-right: 10px;">{{ $t('account.manage.invest.overview.view_by') }}<!--查看分类方式--></span>
          <el-select v-model="assetType" size="mini" style="width: 120px;" value="" @change="doTypeChange()">
            <el-option v-for="data in investDataForm" :key="data.value" :label="$t('account.manage.invest.overview.' + data.label)" :value="data.value" />
          </el-select>
        </div>
      </el-row>
      <div class="block">
        <el-row>
          <el-col :span="2" style="margin: 10px 20px 10px 0;text-align: right;width: 60px;">{{ $t('account.manage.invest.overview.date') }}<!--时间段--></el-col>
          <el-col :span="4">
            <el-date-picker
              v-model="createDateStart"
              type="date"
              value-format="timestamp"
              :placeholder="$t('account.manage.invest.overview.from_date')"
              style="width:100%;"
              :picker-options="startDatePicker"
              size="small"
              @change="dateChoose()"
            />
          </el-col>
          <el-col style="text-align: center; width: 53px; padding: 8px 0;" class="line" :span="1">-</el-col>
          <el-col :span="4">
            <el-date-picker
              v-model="createDateEnd"
              type="date"
              value-format="timestamp"
              :placeholder="$t('account.manage.invest.overview.to_date')"
              style="width:100%;"
              :picker-options="endDatePicker"
              size="small"
              @change="dateChoose()"
            />
          </el-col>
          <el-col :span="10">
            <el-radio-group v-model="daysChange" style="margin-left: 20px;" @change="doDaysChange()">
              <el-radio :label="1">{{ $t('account.manage.invest.overview.three_day') }}<!--近3天--></el-radio>
              <el-radio :label="2">{{ $t('account.manage.invest.overview.seven_day') }}<!--近7天--></el-radio>
              <el-radio :label="3">{{ $t('account.manage.invest.overview.one_month') }}<!--月初至今--></el-radio>
              <el-radio :label="4">{{ $t('account.manage.invest.overview.one_year') }}<!--年初至今--></el-radio>
            </el-radio-group>
            <el-button class="invest-button" type="danger" size="mini" @click="doTypeChange()">{{ $t('account.manage.invest.overview.search') }}<!--查询--></el-button>
          </el-col>
        </el-row>
      </div>
      <ve-histogram
        v-if="!dataEmpty"
        :legend-visible="false"
        :data="chartData"
        :extend="chartExtend"
        :settings="chartSettings"
        style="height: 280px;"
      />
      <div v-if="dataEmpty" class="data-empty" style="margin: 0 auto;text-align: center;color: #909399;padding: 140px 0;">{{ $t('account.manage.no_data') }}</div><!--无数据-->
      <div class="invest-overview-wrapper">
        <el-table
          v-show="assetType === 'account'"
          v-loading="dataLoading"
          :empty-text="$t('account.manage.no_data')"
          class="invest-table"
          :data="marketData"
          style="width:100%;margin-bottom: 50px;"
          row-key="id"
          highlight-current-row
        >
          <el-table-column prop="accountCode" :label="$t('account.manage.invest.overview.account_id')"><!--账户代码-->
            <template slot-scope="scope">
              <div class="type" style="background: rgb(92,207,175);" />
              <span>{{ scope.row.accountCode }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceStart" :label="$t('account.manage.invest.overview.beginning_value')" align="right"><!--初始价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceStart | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceEnd" :label="$t('account.manage.invest.overview.ending_value')" align="right"><!--结束价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceEnd | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="change" :label="$t('account.manage.invest.overview.change')" align="right"><!--变动价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.change | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="returnRate" :label="$t('account.manage.invest.overview.return')" align="right"><!--回报率-->
            <template slot-scope="scope">
              <span>{{ scope.row.returnRate | keepNumTwo }}%</span>
            </template>
          </el-table-column>
        </el-table>
        <el-table
          v-show="assetType === 'invest'"
          v-loading="dataLoading"
          :empty-text="$t('account.manage.no_data')"
          class="invest-table"
          :data="marketData"
          style="width:100%;margin-bottom: 50px;"
          row-key="id"
          highlight-current-row
        >
          <el-table-column prop="assetinvest">
            <template slot-scope="scope">
              <div class="type" :style="typeStyle(scope.row.assetinvest)" />
              <span>{{ scope.row.assetinvest }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceStart" :label="$t('account.manage.invest.overview.beginning_value')" align="right"><!--初始价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceStart | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceEnd" :label="$t('account.manage.invest.overview.ending_value')" align="right"><!--结束价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceEnd | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="change" :label="$t('account.manage.invest.overview.change')" align="right"><!--变动价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.change | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="returnRate" :label="$t('account.manage.invest.overview.return')" align="right"><!--回报率-->
            <template slot-scope="scope">
              <span>{{ scope.row.returnRate | keepNumTwo }}%</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import 'v-charts/lib/style.css';
export default {
  name: 'InvestOverview',
  data() {
    this.chartSettings = {
      metrics: ['资产'],
      dimension: ['日期']
    };
    return {
      order: {
        order: 'ascending'
      },
      assetType: 'account',
      dataEmpty: '',
      createDateStart: '',
      createDateEnd: '',
      investForm: {
        createDateStart: '',
        createDateEnd: ''
      },
      marketValueData: [],
      marketData: [],
      daysChange: '',
      value2: '',
      startDatePicker: {
        disabledDate: time => {
          if (this.createDateEnd) {
            return (
              time.getTime() > new Date(this.createDateEnd).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      },
      endDatePicker: {
        disabledDate: time => {
          if (this.createDateStart) {
            return (
              time.getTime() > Date.now() ||
              time.getTime() < new Date(this.createDateStart).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      },
      chartExtend: {
        grid: {
          x: 0,
          y: 30,
          x2: 0,
          y2: 30,
          height: '60%'
        },
        xAxis: {
          axisLine: {
            show: true,
            lineStyle: {
              color: '#999999'
            }
          }
        },
        yAxis: {
          type: 'value',
          splitNumber: 4,
          position: 'left',
          splitLine: {
            show: false
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#999999'
            }
          }
        },
        series: {
          barWidth: 50
        }
      },
      dataLoading: '',
      chartData: {
        columns: [],
        rows: []
      },
      lineMap: {},
      investDataForm: [{
        value: 'account',
        label: 'account'
      }, {
        value: 'invest',
        label: 'assets'
      }]
    };
  },
  mounted() {
    this.doInitMarketValueHisAccountData();
    // this.doInitMarketValueHisInvestData();
  },
  methods: {
    doInitMarketValueHisAccountData() {
      this.investForm.createDateStart = this.$moment(this.createDateStart).format('YYYYMMDD');
      this.investForm.createDateEnd = this.$moment(this.createDateEnd).format('YYYYMMDD');
      this.$http({
        url: 'ib/accountMarketValueHis/investSummaryList',
        method: 'post',
        params: Object.assign(this.investForm, this.order)
      }).then(res => {
        if (res.code === 200) {
          const l = Object.keys(res.object).length;
          if (res.object === undefined) {
            this.chartData.rows = [];
          }
          if (this.assetType === 'account') {
            this.marketData = [];
            if (l === 0) {
              this.dataEmpty = true;
            } else {
              this.dataEmpty = false;
              this.marketValueData[0] = {
                totalCashBalanceStart: res.object[0].totalCashBalance,
                accountCode: res.object[0].accountCode,
                totalCashBalanceEnd: res.object[l - 1].totalCashBalance,
                change: res.object[l - 1].totalCashBalance - res.object[0].totalCashBalance
              };
              if (res.object[0].totalCashBalance === 0) {
                this.marketValueData[0].returnRate = 0;
              } else {
                this.marketValueData[0].returnRate = ((res.object[l - 1].totalCashBalance - res.object[0].totalCashBalance) / res.object[0].totalCashBalance) * 100;
              }
              this.marketData.push(this.marketValueData[0]);
              this.chartData.rows = [];
              if (this.$i18n.locale === 'zh_CN') {
                this.chartSettings = {
                  metrics: ['资产'],
                  dimension: ['日期']
                };
                for (let i = 0; i < l; i++) {
                  let date = res.object[i].createDate.toString();
                  let newDate = date.slice(0, 4) + '/' + date.slice(4, 6) + '/' + date.slice(6);
                  this.lineMap[i] = { '日期': newDate, '资产': res.object[i].totalCashBalance };
                  this.chartData.rows.push(this.lineMap[i]);
                }
                this.chartData.columns = ['日期', '资产'];
              } else if (this.$i18n.locale === 'en_US') {
                this.chartSettings = {
                  metrics: ['Asset'],
                  dimension: ['Date']
                };
                for (let i = 0; i < l; i++) {
                  let date = res.object[i].createDate.toString();
                  let newDate = date.slice(0, 4) + '/' + date.slice(4, 6) + '/' + date.slice(6);
                  this.lineMap[i] = { 'Date': newDate, 'Asset': res.object[i].totalCashBalance };
                  this.chartData.rows.push(this.lineMap[i]);
                }
                this.chartData.columns = ['Date', 'Asset'];
              }
            }
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doInitMarketValueHisInvestData() {
      this.investForm.createDateStart = this.$moment(this.createDateStart).format('YYYYMMDD');
      this.investForm.createDateEnd = this.$moment(this.createDateEnd).format('YYYYMMDD');
      this.$http({
        url: 'ib/accountMarketValueHis/investSummaryList',
        method: 'post',
        params: this.investForm
      }).then(res => {
        if (res.code === 200) {
          const l = Object.keys(res.object).length;
          if (res.object === undefined) {
            this.chartData.rows = [];
          }
          if (this.assetType === 'invest') {
            this.marketData = [];
            if (l === 0) {
              this.dataEmpty = true;
            } else {
              var stockNum = 0;
              var marketNum = 1;
              var fundNum = 2;
              var futuresNum = 3;
              var optionNum = 4;
              var fxCashNum = 5;
              var bundNum = 6;
              this.dataEmpty = false;
              // 股票
              this.marketValueData[stockNum] = {
                totalCashBalanceStart: res.object[0].stockMarketValue,
                assetinvest: '',
                totalCashBalanceEnd: res.object[l - 1].stockMarketValue,
                change: res.object[l - 1].stockMarketValue - res.object[0].stockMarketValue
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[stockNum].assetinvest = '股票';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[stockNum].assetinvest = 'Stocks';
              }
              if (res.object[0].stockMarketValue === 0) {
                this.marketValueData[stockNum].returnRate = 0;
              } else {
                this.marketValueData[stockNum].returnRate = ((res.object[l - 1].stockMarketValue - res.object[0].stockMarketValue) / res.object[0].stockMarketValue) * 100;
              }
              this.marketData[stockNum] = this.marketValueData[stockNum];
              // 期货期权
              this.marketValueData[marketNum] = {
                totalCashBalanceStart: res.object[0].futureOptionValue,
                assetinvest: '',
                totalCashBalanceEnd: res.object[l - 1].futureOptionValue,
                change: res.object[l - 1].futureOptionValue - res.object[0].futureOptionValue
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[marketNum].assetinvest = '期货期权';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[marketNum].assetinvest = 'FOPs';
              }
              if (res.object[0].futureOptionValue === 0) {
                this.marketValueData[marketNum].returnRate = 0;
              } else {
                this.marketValueData[marketNum].returnRate = ((res.object[l - 1].futureOptionValue - res.object[0].futureOptionValue) / res.object[0].futureOptionValue) * 100;
              }
              this.marketData[marketNum] = this.marketValueData[marketNum];
              // 基金
              var fundValueSumStart = res.object[0].fundValue + res.object[0].mutualFundValue + res.object[0].moneyMarketFundValue;
              var fundValueSumEnd = res.object[l - 1].fundValue + res.object[l - 1].mutualFundValue + res.object[l - 1].moneyMarketFundValue;
              this.marketValueData[fundNum] = {
                totalCashBalanceStart: fundValueSumStart,
                assetinvest: '',
                totalCashBalanceEnd: fundValueSumEnd,
                change: (fundValueSumEnd - fundValueSumStart).toFixed(2)
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[fundNum].assetinvest = '基金';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[fundNum].assetinvest = 'Funds';
              }
              if (fundValueSumStart === 0) {
                this.marketValueData[fundNum].returnRate = 0;
              } else {
                this.marketValueData[fundNum].returnRate = ((fundValueSumEnd - fundValueSumStart) / fundValueSumStart) * 100;
              }
              this.marketData[fundNum] = this.marketValueData[fundNum];
              // 期货
              this.marketValueData[futuresNum] = {
                totalCashBalanceStart: res.object[0].futuresPnl,
                assetinvest: '',
                totalCashBalanceEnd: res.object[l - 1].futuresPnl,
                change: res.object[l - 1].futuresPnl - res.object[0].futuresPnl
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[futuresNum].assetinvest = '期货';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[futuresNum].assetinvest = 'Futures';
              }
              if (res.object[0].futuresPnl === 0) {
                this.marketValueData[futuresNum].returnRate = 0;
              } else {
                this.marketValueData[futuresNum].returnRate = ((res.object[l - 1].futuresPnl - res.object[0].futuresPnl) / res.object[0].futuresPnl) * 100;
              }
              this.marketData[futuresNum] = this.marketValueData[futuresNum];
              // 期权
              this.marketValueData[optionNum] = {
                totalCashBalanceStart: res.object[0].optionMarketValue,
                assetinvest: '',
                totalCashBalanceEnd: res.object[l - 1].optionMarketValue,
                change: res.object[l - 1].optionMarketValue - res.object[0].optionMarketValue
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[optionNum].assetinvest = '期权';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[optionNum].assetinvest = 'Options';
              }
              if (res.object[0].optionMarketValue === 0) {
                this.marketValueData[optionNum].returnRate = 0;
              } else {
                this.marketValueData[optionNum].returnRate = ((res.object[l - 1].optionMarketValue - res.object[0].optionMarketValue) / res.object[0].optionMarketValue) * 100;
              }
              this.marketData[optionNum] = this.marketValueData[optionNum];
              // 外汇
              this.marketValueData[fxCashNum] = {
                totalCashBalanceStart: res.object[0].fxCashBalance,
                assetinvest: '',
                totalCashBalanceEnd: res.object[l - 1].fxCashBalance,
                change: res.object[l - 1].fxCashBalance - res.object[0].fxCashBalance
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[fxCashNum].assetinvest = '外汇';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[fxCashNum].assetinvest = 'Forex';
              }
              if (res.object[0].fxCashBalance === 0) {
                this.marketValueData[fxCashNum].returnRate = 0;
              } else {
                this.marketValueData[fxCashNum].returnRate = ((res.object[l - 1].fxCashBalance - res.object[0].fxCashBalance) / res.object[0].fxCashBalance) * 100;
              }
              this.marketData[fxCashNum] = this.marketValueData[fxCashNum];
              // 债券
              var bundValueSumStart = res.object[0].corporateBondValue + res.object[0].tBondValue + res.object[0].tBillValue;
              var bundValueSumEnd = res.object[l - 1].corporateBondValue + res.object[l - 1].tBondValue + res.object[l - 1].tBillValue;
              this.marketValueData[bundNum] = {
                totalCashBalanceStart: bundValueSumStart,
                assetinvest: '',
                totalCashBalanceEnd: bundValueSumEnd,
                change: bundValueSumEnd - bundValueSumStart
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[bundNum].assetinvest = '债券';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[bundNum].assetinvest = 'Bonds';
              }
              if (bundValueSumStart === 0) {
                this.marketValueData[bundNum].returnRate = 0;
              } else {
                this.marketValueData[bundNum].returnRate = ((bundValueSumEnd - bundValueSumStart) / bundValueSumStart) * 100;
              }
              this.marketData[bundNum] = this.marketValueData[bundNum];
              // 总计
              var totalCashBalanceStartSum = 0;
              var totalCashBalanceEndSum = 0;
              var changeSum = 0;
              var returnRateSum = 0;
              for (let i = 0; i < this.marketData.length; i++) {
                totalCashBalanceStartSum += this.marketData[i].totalCashBalanceStart;
                totalCashBalanceEndSum += this.marketData[i].totalCashBalanceEnd;
              }
              changeSum = totalCashBalanceEndSum - totalCashBalanceStartSum;
              returnRateSum = changeSum / totalCashBalanceStartSum;
              if (totalCashBalanceStartSum === 0) {
                returnRateSum = 0;
              }
              this.marketValueData[this.marketData.length] = {
                assetinvest: '',
                totalCashBalanceStart: totalCashBalanceStartSum,
                totalCashBalanceEnd: totalCashBalanceEndSum,
                change: changeSum,
                returnRate: returnRateSum * 100
              };
              if (this.$i18n.locale === 'zh_CN') {
                this.marketValueData[this.marketData.length].assetinvest = '总计';
              } else if (this.$i18n.locale === 'en_US') {
                this.marketValueData[this.marketData.length].assetinvest = 'Total';
              }
              this.marketData.push(this.marketValueData[7]);
              // 图表数据
              this.chartData.rows = [];
              if (this.$i18n.locale === 'zh_CN') {
                this.chartSettings = {
                  metrics: ['股票', '期货期权', '基金', '期货', '期权', '外汇', '债券'],
                  dimension: ['日期'],
                  stack: { '资产': ['股票', '期货期权', '基金', '期货', '期权', '外汇', '债券'] }
                };
                for (let i = 0; i < l; i++) {
                  let date = res.object[i].createDate.toString();
                  let newDate = date.slice(0, 4) + '/' + date.slice(4, 6) + '/' + date.slice(6);
                  this.lineMap[i] = {
                    '日期': newDate,
                    '股票': res.object[i].stockMarketValue,
                    '期货期权': res.object[i].futureOptionValue,
                    '基金': res.object[i].fundValue + res.object[i].mutualFundValue + res.object[i].moneyMarketFundValue,
                    '期货': res.object[i].futuresPnl,
                    '期权': res.object[i].optionMarketValue,
                    '外汇': res.object[i].fxCashBalance,
                    '债券': res.object[i].corporateBondValue + res.object[i].tBondValue + res.object[i].tBillValue
                  };
                  this.chartData.rows.push(this.lineMap[i]);
                  this.chartData.columns = ['日期', '股票'];
                }
              } else if (this.$i18n.locale === 'en_US') {
                this.chartSettings = {
                  metrics: ['Stocks', 'FOPs', 'Funds', 'Options', 'Futures', 'Forex', 'Bonds'],
                  dimension: ['Date'],
                  stack: { 'Asset': ['Stocks', 'FOPs', 'Funds', 'Options', 'Futures', 'Forex', 'Bonds'] }
                };
                for (let i = 0; i < l; i++) {
                  let date = res.object[i].createDate.toString();
                  let newDate = date.slice(0, 4) + '/' + date.slice(4, 6) + '/' + date.slice(6);
                  this.lineMap[i] = {
                    'Date': newDate,
                    'Stocks': res.object[i].stockMarketValue,
                    'FOPs': res.object[i].futureOptionValue,
                    'Funds': res.object[i].fundValue + res.object[i].mutualFundValue + res.object[i].moneyMarketFundValue,
                    'Options': res.object[i].futuresPnl,
                    'Futures': res.object[i].optionMarketValue,
                    'Forex': res.object[i].fxCashBalance,
                    'Bonds': res.object[i].corporateBondValue + res.object[i].tBondValue + res.object[i].tBillValue
                  };
                  this.chartData.rows.push(this.lineMap[i]);
                  this.chartData.columns = ['Date', 'Stocks'];
                }
              }
            }
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doDaysChange() {
      this.createDateStart = '';
      this.createDateEnd = '';
      const start = new Date();
      const date = new Date();
      const y = start.getFullYear();
      const d = start.getDate() - 1;
      if (this.daysChange === 1) {
        this.createDateStart = start.setTime(start.getTime() - 3600 * 1000 * 24 * 3);
        this.createDateEnd = date.getTime();
      } else if (this.daysChange === 2) {
        this.createDateStart = start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
        this.createDateEnd = date.getTime();
      } else if (this.daysChange === 3) {
        this.createDateStart = start.setTime(start.getTime() - 3600 * 1000 * 24 * d);
        this.createDateEnd = date.getTime();
      } else if (this.daysChange === 4) {
        this.createDateStart = new Date(y + '-01-01').valueOf();
        this.createDateEnd = date.getTime();
      }
    },
    doTypeChange() {
      if (this.assetType === 'account') {
        this.doInitMarketValueHisAccountData();
      } else if (this.assetType === 'invest') {
        this.doInitMarketValueHisInvestData();
      }
    },
    dateChoose() {
      this.daysChange = '';
    },
    typeStyle(type) {
      if (type === '股票' || type === 'Stocks') {
        return 'background: #27CEAC;';
      } else if (type === '期货期权' || type === 'FOPs') {
        return 'background: #72B9E3;';
      } else if (type === '基金' || type === 'Funds') {
        return 'background: #E64C79;';
      } else if (type === '期货' || type === 'Futures') {
        return 'background: #EF9D5B;';
      } else if (type === '期权' || type === 'Options') {
        return 'background: #4370E6;';
      } else if (type === '外汇' || type === 'Forex') {
        return 'background: #CFAFD8;';
      } else if (type === '债券' || type === 'Bonds') {
        return 'background: #CA8585;';
      }
    }
  }
};
</script>
<style lang="scss">
  .invest-overview-wrapper {
    .v-charts-data-empty {
      background-color: white;
      margin-left: -10px;
    }
    th {
      color: #666666;
      font-weight: 400;
      font-size:16px;
      background-color: #F6F6F6;
    }
    .el-table .cell {
      white-space: pre-line;
    }
    .el-radio__label {
      font-size: 13px;
      padding-left: 10px;
      margin-right: -10px;
    }
    .el-col-10 {
      width: auto;
    }
  }
</style>
<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .invest-overview-wrapper {
      padding: 30px 40px 10px;
      background-color: #FFFFFF;
      .invest-title {
        font-size: 18px;
        margin-left: -40px;
        padding-left: 38px;
        border-left: 2px solid;
        color: #434343;
      }
      .invest-content {
        text-align: center;
        box-shadow: 10px 10px 20px rgba(110, 163, 225, 0.1);
        padding: 10px 20px;
        .content-title {
          color: #4B98FF;
          font-size:20px;
          font-weight:400;
        }
        .content-txt {
          color: #434343;
        }
      }
      .block {
        margin-top: 50px;
        .invest-button {
          margin-left: 20px;
          width: 80px;
          border-radius:5px;
        }
      }
    }
    .type {
      width:16px;
      height:16px;
      display: inline-block;
      position: relative;
      top: 3px;
    }
  }
</style>
