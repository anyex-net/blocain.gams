<template>
  <div class="moneySummary">
    <el-container class="container">
      <el-aside width="45%">
        <h5>交易可用资金</h5>
        <div class="left">
          <span>全部可用资金</span>
          <span>{{ assetOverviewData.fullAvailableFunds }}</span>
        </div>
        <div class="left">
          <span>全部剩余流动性</span>
          <span>{{ assetOverviewData.excessLiquidity }}</span>
        </div>
        <div class="left">
          <span>到期后剩余</span>
          <span>{{ assetOverviewData.postExpirationExcess }}</span>
        </div>
        <div class="left">
          <span>前瞻可用资金</span>
          <span>{{ assetOverviewData.lookAheadAvailableFunds }}</span>
        </div>
        <div class="left">
          <span>前瞻剩余流动性</span>
          <span>{{ assetOverviewData.lookAheadExcessLiquidity }}</span>
        </div>
        <div class="left">
          <span>购买力</span>
          <span>{{ assetOverviewData.buyingPower }}</span>
        </div>
        <div class="left">
          <span>杠杆</span>
          <span>{{ assetOverviewData.leverageS }}</span>
        </div>
        <div class="left">
          <span>前瞻下一变化</span>
          <span>{{ assetOverviewData.lookAheadNextChange }}</span>
        </div>
        <div class="left">
          <span>币种</span>
          <span>{{ assetOverviewData.currency }}</span>
        </div>
        <div class="left">
          <span>更新时间</span>
          <span>{{ assetOverviewData.updateDate }}</span>
        </div>

      </el-aside>
      <el-container>
        <el-header style="height:140px">
          <h5>账户保证金</h5>
          <div class="left">
            <span>全部初始保证金要求</span>
            <span>{{ assetOverviewData.fullInitMarginReq }}</span>
          </div>
          <div class="left">
            <span>全部维持保证金要求</span>
            <span>{{ assetOverviewData.fullMaintMarginReq }}</span>
          </div>
        </el-header>
        <el-main>
          <h5>账户余额</h5>
          <div class="left">
            <span>净清算价值</span>
            <span>{{ assetOverviewData.netLiquidation }}</span>
          </div>
          <div class="left">
            <span>含贷款价值的资产</span>
            <span>{{ assetOverviewData.equityWithLoanValue }}</span>
          </div>
          <div class="left">
            <span>净清算不确定</span>
            <span>{{ assetOverviewData.netLiquidationUncertainty }}</span>
          </div>
          <div class="left">
            <span>证券头寸总价值</span>
            <span>{{ assetOverviewData.grossPositionValue }}</span>
          </div>
          <div class="left">
            <span>总现金价值</span>
            <span>{{ assetOverviewData.totalCashvalue }}</span>
          </div>
        </el-main>
      </el-container>
    </el-container>
    <div class="chartLine">
      <h4>资产净值/时间</h4>
      <ve-line
        :setting="lineSettings"
        :data="lineData"
        style="width: 90%;margin-left: 50px;"
        :tooltip-visible="true"
        :legend-visible="false"
        :extend="lineExtend"
      >
        <div v-if="dataEmpty2" class="data-empty" style="margin: -300px auto 0;text-align: center;color: #909399;">无数据</div><!--无数据-->
      </ve-line>
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
  // components:['veLine'],
  data() {
    this.lineSettings = {
      dimension: ['日期'],
      metrics: ['资产']
    };
    return {
      dataEmpty2: '',
      accountId: this.accountInfo.accountId,
      assetOverviewData: [],
      order: {
        order: 'ascending'
      },
      lineData: {
        columns: [],
        rows: []
      },
      lineMap: {},
      lineExtend: {
        grid: {
          show: false,
          x: 0,
          y: 30,
          x2: 0,
          y2: 30,
          height: '45%'
        },
        series: {
          type: 'line',
          label: {
            normal: {
              show: false,
              color: '#A6CCFF',
              fontSize: 18
            }
          },
          symbol: 'circle',
          symbolSize: 10,
          color: ['#A6CCFF'],
          areaStyle: {
            normal: {
              color: {
                type: 'linear',
                x: 0,
                y: 0,
                x2: 0,
                y2: 1,
                colorStops: [{
                  offset: 0, color: '#dceaf8' // 0% 处的颜色
                }, {
                  offset: 1, color: 'rgba(220,234,248,0.13)' // 100% 处的颜色
                }],
                global: false // 缺省为 false
              }
            }
          }
        },
        yAxis: {
          type: 'value',
          scale: true,
          splitNumber: 4,
          splitLine: {
            show: true,
            lineStyle: {
              color: ['#EBEBEB'],
              type: 'dashed'
            }
          }
        }
      },
      chartSettings: {}
    };
  },
  mounted() {
    this.doInitData();
    this.doInitMarketValueHisData();
  },
  methods: {
    doInitData() {
      this.$http({
        url: '/account/manage/ib/accountBalanceMargin/cashSummary',
        method: 'post',
        params: { accountId: this.accountId }
      }).then(res => {
        if (res.code === 200) {
          if (res.object) {
            this.assetOverviewData = res.object;
            this.assetOverviewData.lookAheadNextChange = this.$moment(this.assetOverviewData.lookAheadNextChange * 1000).format('HH:mm:ss');
            this.assetOverviewData.updateDate = this.$moment(this.assetOverviewData.updateDate).format('YYYY-MM-DD');
          }
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doInitMarketValueHisData() {
      this.$http({
        url: '/account/manage/ib/accountMarketValueHis/accountSummaryList',
        method: 'post',
        params: Object.assign(this.order, { accountId: this.accountId })
      }).then(res => {
        if (res.code === 200) {
          if (Object.keys(res.object).length === 0 || res.object === undefined) {
            this.dataEmpty2 = true;
          } else {
            this.dataEmpty2 = false;
            this.marketValueHisData = res.object;
            // this.marketValueHisData.sort(function(a, b) {
            //   return a.createDate > b.createDate ? 1 : -1;
            // });
            this.lineData.columns = ['日期', '资产'];
            for (let i = 0; i < Object.keys(res.object).length; i++) {
              this.lineMap[i] = { '日期': (res.object[i].createDate.toString()), '资产': res.object[i].totalCashBalance };
              this.lineData.rows.push(this.lineMap[i]);
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

<style lang="scss" scoped>
    .moneySummary{
        background-color: #fff;
        padding: 10px;
        .chartLine{
            h4{
                border-left: 5px solid orange;
                padding: 8px 10px;
            }
        }
        .left{
            height: 30px;
            line-height: 30px;
            span{
                font-size: 14px;
                .circle{
                    margin-right: 8px;
                    width: 10px;
                    height: 10px;
                    border-radius: 10px;
                    background-color: rgb(0, 204, 0);
                    display: inline-block;
                }
                .circleGray{
                     margin-right: 8px;
                    width: 10px;
                    height: 10px;
                    border-radius: 10px;
                    background-color: gray;
                    display: inline-block;
                }
                &:nth-child(1){
                    float: left;
                }
                &:nth-child(2){
                    width: 120px;
                    float: right;
                    text-align: left;
                }
            }

        }
        .head{
            padding: 10px 0;
            .number{
                font-size: 28px;
            }
            .usd{
                line-height: 38px;
                font-size: 14px;
                margin-right: 8px;
            }
            &:nth-child(1){
                font-weight: 700;
            }
            >span{
                &:nth-child(1){
                    float: left;
                }
            }
        }
        .mainLeft{
            width: 600px;
            float: left;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
        }
        .mainRight{
            width: 600px;
            float: right;
            height: 30px;
            line-height: 30px;
            font-size: 14px;
            text-align: center;
        }
        .el-header {
            margin-bottom: 20px;
            background-color: #fff;
            color: #333;
            text-align: center;
            border: 1px solid #e6e6e6;
            border-radius: 5px;
            h5{
                font-size: 18px;
                height: 30px;
                line-height: 30px;
                text-align: left;
                margin: 15px 0px;
            }
        }

        .el-aside {
            height: 400px;
            padding: 0 30px;
            padding-bottom: 20px;
            background-color: #fff;
            color: #333;
            text-align: center;
            line-height: 200px;
            border: 1px solid #e6e6e6;
            border-radius: 5px;
            margin-right: 20px;
            h5{
                font-size: 18px;
                height: 30px;
                line-height: 30px;
                text-align: left;
                margin: 15px 0px;
            }
        }

        .el-main {
            background-color: #fff;
            color: #333;
            text-align: center;
            line-height: 160px;
            border: 1px solid #e6e6e6;
            border-radius: 5px;
            padding: 0 20px;
            h5{
                font-size: 18px;
                height: 30px;
                line-height: 30px;
                text-align: left;
                margin: 15px 0px;
            }
        }
    }
</style>
