<template>
  <!--<div class="investmentOverview">
    <ve-pie v-if="ready" :data="chartLineData" :settings="chartSettings"></ve-pie>
    <h4>投资组合</h4>
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="全部" name="all">
        <el-table
          :data="investmentData"
          border
          style="width: 100%"
        >
          <el-table-column
            prop="productType"
            label="产品类型"
            width="150px"
          >
          </el-table-column>
          <el-table-column
            align="right"
            prop="baseMonetaryValue"
            label="以基础货币计的价值"
          >
          </el-table-column>
          <el-table-column
            align="right"
            prop="monetaryBaseBenefit"
            label="以基础货币表示的未实现盈亏"
          >
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="股票" name="stocks">gupiao</el-tab-pane>
      <el-tab-pane label="基金" name="funds">基金</el-tab-pane>
      <el-tab-pane label="外汇" name="forex">外汇</el-tab-pane>
      <el-tab-pane label="期权" name="shareOption">期权</el-tab-pane>
      <el-tab-pane label="期货" name="futures">期货</el-tab-pane>
      <el-tab-pane label="债券" name="bond">债券</el-tab-pane>
    </el-tabs>
  </div>-->
  <div class="app-container">
    <div class="invest-overview-wrapper">
      <div class="block">
        <el-form>
          <el-row>
            <el-col :span="5">
              <el-form-item prop="createDateStart" label="开始时间">
                <el-date-picker
                  v-model="createDateStart"
                  placeholder="开始时间"
                  type="date"
                  value-format="timestamp"
                  style="width:60%;"
                  :picker-options="startDatePicker"
                  size="small"
                  @change="dateChoose()"
                />
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item prop="createDateEnd" label="结束时间">
                <el-date-picker
                  v-model="createDateEnd"
                  placeholder="结束时间"
                  type="date"
                  value-format="timestamp"
                  style="width:60%;"
                  :picker-options="endDatePicker"
                  size="small"
                  @change="dateChoose()"
                />
              </el-form-item>
            </el-col>
            <el-col :span="10">
              <el-radio-group v-model="daysChange" style="margin-left: 20px;" @change="doDaysChange()">
                <el-radio :label="1">近3天<!--近3天--></el-radio>
                <el-radio :label="2">近7天<!--近7天--></el-radio>
                <el-radio :label="3">月初至今<!--月初至今--></el-radio>
                <el-radio :label="4">年初至今<!--年初至今--></el-radio>
              </el-radio-group>
              <el-button style="margin: 5px 0 0 10px;" type="danger" size="mini" @click="doTypeChange()">查询<!--查询--></el-button>
            </el-col>
            <el-col :span="4">
              <div style="float: right;z-index: 1000;margin-top: 5px">
                <span style="color: #999999;margin-right: 10px;font-size: 14px">分类方式<!--查看分类方式--></span>
                <el-select v-model="assetType" size="mini" style="width: 80px;" value="" @change="doTypeChange()">
                  <el-option label="账户" value="account"></el-option>
                  <el-option label="资产" value="invest"></el-option>
                </el-select>
              </div>
            </el-col>
          </el-row>
        </el-form>
      </div>
      <ve-histogram
        v-if="!dataEmpty"
        :legend-visible="false"
        :data="chartData"
        :extend="chartExtend"
        :settings="chartSettings"
        style="height: 280px;margin-top: 30px;"
      >
      </ve-histogram>
      <div v-if="dataEmpty" class="data-empty" style="margin: 0 auto;text-align: center;color: #909399;padding: 140px 0;">无数据</div><!--无数据-->
      <div class="invest-overview-wrapper">
        <el-table
          v-show="assetType === 'account'"
          class="invest-table"
          :data="marketData"
          style="width:100%;margin-bottom: 50px;"
          row-key="id"
          highlight-current-row
          empty-text="无数据"
        >
          <el-table-column prop="accountCode" label="账户代码">
          </el-table-column>
          <el-table-column prop="totalCashBalanceStart" label="初始价值" align="right"><!--初始价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceStart | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceEnd" label="结束价值" align="right"><!--结束价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceEnd | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="change" label="变动" align="right"><!--变动价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.change | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="returnRate" label="回报率" align="right"><!--回报率-->
            <template slot-scope="scope">
              <span>{{ scope.row.returnRate | keepNumTwo }}%</span>
            </template>
          </el-table-column>
        </el-table>
        <el-table
          v-show="assetType === 'invest'"
          class="invest-table"
          :data="marketData"
          style="width:100%;margin-bottom: 50px;"
          row-key="id"
          highlight-current-row
          empty-text="无数据"
        >
          <el-table-column prop="assetinvest">
            <template slot-scope="scope">
              <div class="type" :style="typeStyle(scope.row.assetinvest)"></div>
              <span>{{ scope.row.assetinvest }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceStart" label="初始价值" align="right"><!--初始价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceStart | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="totalCashBalanceEnd" label="结束价值" align="right"><!--结束价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.totalCashBalanceEnd | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="change" label="变动" align="right"><!--变动价值-->
            <template slot-scope="scope">
              <span>{{ scope.row.change | keepNumTwo }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="returnRate" label="回报率" align="right"><!--回报率-->
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
  props: {
    accountInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    this.chartSettings = {
      metrics: ['资产'],
      dimension: ['日期']
    };
    return {
      assetType: 'account',
      createDateStart: '',
      createDateEnd: '',
      daysChange: '',
      dataEmpty: '',
      investForm: {
        createDateStart: '',
        createDateEnd: '',
        accountId: this.accountInfo.accountId
      },
      order: {
        order: 'ascending'
      },
      marketValueData: [],
      marketData: [],
      ready: false,
      chartData: {
        columns: [],
        rows: []
      },
      lineMap: {},
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
      }
    };
  },
  mounted() {
    this.doInitMarketValueHisAccountData();
  },
  methods: {
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
      if (type === '股票') {
        return 'background: #27CEAC;';
      } else if (type === '期货期权') {
        return 'background: #72B9E3;';
      } else if (type === '基金') {
        return 'background: #E64C79;';
      } else if (type === '期货') {
        return 'background: #EF9D5B;';
      } else if (type === '期权') {
        return 'background: #4370E6;';
      } else if (type === '外汇') {
        return 'background: #CFAFD8;';
      } else if (type === '债券') {
        return 'background: #CA8585;';
      }
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
    doInitMarketValueHisAccountData() {
      this.investForm.createDateStart = this.$moment(this.createDateStart).format('YYYYMMDD');
      this.investForm.createDateEnd = this.$moment(this.createDateEnd).format('YYYYMMDD');
      this.$http({
        url: 'account/manage/ib/accountMarketValueHis/investSummaryList',
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
              this.chartSettings = {
                metrics: ['资产'],
                dimension: ['日期']
              };
              for (let i = 0; i < l; i++) {
                const date = res.object[i].createDate.toString();
                const newDate = date.slice(0, 4) + '/' + date.slice(4, 6) + '/' + date.slice(6);
                this.lineMap[i] = { '日期': newDate, '资产': res.object[i].totalCashBalance };
                this.chartData.rows.push(this.lineMap[i]);
              }
              this.chartData.columns = ['日期', '资产'];
            }
          }
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doInitMarketValueHisInvestData() {
      this.investForm.createDateStart = this.$moment(this.createDateStart).format('YYYYMMDD');
      this.investForm.createDateEnd = this.$moment(this.createDateEnd).format('YYYYMMDD');
      this.$http({
        url: 'account/manage/ib/accountMarketValueHis/investSummaryList',
        method: 'post',
        params: Object.assign(this.investForm, this.order)
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
                assetinvest: '股票',
                totalCashBalanceEnd: res.object[l - 1].stockMarketValue,
                change: res.object[l - 1].stockMarketValue - res.object[0].stockMarketValue
              };
              if (res.object[0].stockMarketValue === 0) {
                this.marketValueData[stockNum].returnRate = 0;
              } else {
                this.marketValueData[stockNum].returnRate = ((res.object[l - 1].stockMarketValue - res.object[0].stockMarketValue) / res.object[0].stockMarketValue) * 100;
              }
              this.marketData[stockNum] = this.marketValueData[stockNum];
              // 期货期权
              this.marketValueData[marketNum] = {
                totalCashBalanceStart: res.object[0].futureOptionValue,
                assetinvest: '期货期权',
                totalCashBalanceEnd: res.object[l - 1].futureOptionValue,
                change: res.object[l - 1].futureOptionValue - res.object[0].futureOptionValue
              };
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
                assetinvest: '基金',
                totalCashBalanceEnd: fundValueSumEnd,
                change: (fundValueSumEnd - fundValueSumStart).toFixed(2)
              };
              if (fundValueSumStart === 0) {
                this.marketValueData[fundNum].returnRate = 0;
              } else {
                this.marketValueData[fundNum].returnRate = ((fundValueSumEnd - fundValueSumStart) / fundValueSumStart) * 100;
              }
              this.marketData[fundNum] = this.marketValueData[fundNum];
              // 期货
              this.marketValueData[futuresNum] = {
                totalCashBalanceStart: res.object[0].futuresPnl,
                assetinvest: '期货',
                totalCashBalanceEnd: res.object[l - 1].futuresPnl,
                change: res.object[l - 1].futuresPnl - res.object[0].futuresPnl
              };
              if (res.object[0].futuresPnl === 0) {
                this.marketValueData[futuresNum].returnRate = 0;
              } else {
                this.marketValueData[futuresNum].returnRate = ((res.object[l - 1].futuresPnl - res.object[0].futuresPnl) / res.object[0].futuresPnl) * 100;
              }
              this.marketData[futuresNum] = this.marketValueData[futuresNum];
              // 期权
              this.marketValueData[optionNum] = {
                totalCashBalanceStart: res.object[0].optionMarketValue,
                assetinvest: '期权',
                totalCashBalanceEnd: res.object[l - 1].optionMarketValue,
                change: res.object[l - 1].optionMarketValue - res.object[0].optionMarketValue
              };
              if (res.object[0].optionMarketValue === 0) {
                this.marketValueData[optionNum].returnRate = 0;
              } else {
                this.marketValueData[optionNum].returnRate = ((res.object[l - 1].optionMarketValue - res.object[0].optionMarketValue) / res.object[0].optionMarketValue) * 100;
              }
              this.marketData[optionNum] = this.marketValueData[optionNum];
              // 外汇
              this.marketValueData[fxCashNum] = {
                totalCashBalanceStart: res.object[0].fxCashBalance,
                assetinvest: '外汇',
                totalCashBalanceEnd: res.object[l - 1].fxCashBalance,
                change: res.object[l - 1].fxCashBalance - res.object[0].fxCashBalance
              };
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
                assetinvest: '债券',
                totalCashBalanceEnd: bundValueSumEnd,
                change: bundValueSumEnd - bundValueSumStart
              };
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
                assetinvest: '总计',
                totalCashBalanceStart: totalCashBalanceStartSum,
                totalCashBalanceEnd: totalCashBalanceEndSum,
                change: changeSum,
                returnRate: returnRateSum * 100
              };
              this.marketData.push(this.marketValueData[7]);
              // 图表数据
              this.chartData.rows = [];
              this.chartSettings = {
                metrics: ['股票', '期货期权', '基金', '期货', '期权', '外汇', '债券'],
                dimension: ['日期'],
                stack: { '资产': ['股票', '期货期权', '基金', '期货', '期权', '外汇', '债券'] }
              };
              for (let i = 0; i < l; i++) {
                const date = res.object[i].createDate.toString();
                const newDate = date.slice(0, 4) + '/' + date.slice(4, 6) + '/' + date.slice(6);
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
            }
          }
        }
      }).catch(error => {
        this.$message.error(error);
      });
    }
  }
};
</script>
<style scoped lang="scss">
    .app-container{
        padding: 10px;
        background-color: #fff;
        h4{
            background: #fff;
            border-left: 5px solid #ae1207;
            padding: 8px 10px;
        }
      tr{
        th{
          background-color: rgb(242, 242, 242);
        }
      }
      .el-radio {
        margin-right: 10px;
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
