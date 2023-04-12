<template>
  <div class="app-container">
    页面跳转中 。 。 。
  </div>
</template>

<script>
import dictionary from '@/components/dictionary/dictionary';
import cookies from 'js-cookie';
import 'v-charts/lib/style.css';
export default {
  name: 'Overview',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    dictionary
  },
  data() {
    this.lineSettings = {
      dimension: ['日期'],
      metrics: ['资产']
    };
    this.ringSettings = {
      dimension: '资产',
      metrics: '数量',
      label: {
        normal: {
          show: true
        }
      },
      radius: [
        '30%', '55%'
      ],
      offsetY: 160
    };
    return {
      // dataEmpty1: '',
      order: {
        order: 'ascending'
      },
      dataEmpty2: '',
      secTyper: '',
      CurrencyData: [],
      marketValueData: [],
      marketValueHisData: [],
      dailyPnlData: [],
      balanceMarginData: [],
      exchangeData: [],
      currencyData: [],
      date: new Date(),
      assetData: {
        columns: [],
        rows: []
      },
      countListMap: {},
      assetTitle: ['资产'],
      lineData: {
        columns: [],
        rows: []
      },
      lineMap: {},
      ringExtend: {
        grid: {
          containLabel: true
        }
      },
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
      }
    };
  },
  created() {
    // this.doInitExchangeData();
    // this.doInitBalanceMarginData();
    // this.doInitMarketValueData();
    // this.doInitDailyPnlData();
    // this.doInitMarketValueHisData();
    this.openAccount();
  },
  mounted() {
    // const _this = this;
    // this.timer = setInterval(function() {
    //   _this.date = new Date().toLocaleString();
    // });
  },
  beforeDestroy: function() {
    // if (this.timer) {
    //   clearInterval(this.timer);
    // }
  },
  methods: {
    openAccount() {
      this.$store.dispatch('user/getInfo').then(res => {
        if (res.code === 200) {
          cookies.set('locale', this.$i18n.locale);
          if (this.$store.state.user.applyData.xinxi.accountType === 'personal') {
            this.applySkip('person');
          } else if (this.$store.state.user.applyData.xinxi.accountType === 'limitedLiablity') {
            this.applySkip('company');
          } else if (this.$store.state.user.applyData.xinxi.accountType === 'partnerShip') {
            this.applySkip('partnership');
          }
        } else if (res.code === 2003) {
          this.$verify.staleDated(this);
        } else {
          this.$message.error(res.message || 'Has Error');
        }
      }).catch((error) => {
        this.$message.error('' + error + '');
      });
    },
    applySkip(location) {
      let num = '';
      const registerStatus = String(this.$store.state.user.applyData.xinxi.registerStatus);
      if (this.$store.state.user.applyData.xinxi.registerStatus === '') {
        num = 1;
      } else if (this.$store.state.user.applyData.xinxi.registerStatus === 0) {
        if (location === 'person') {
          num = 10;
        } else {
          num = 19;
        }
        // this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.AccountOpeningStatus1')); // $i18n - 完成开户,未审核
      } else if (this.$store.state.user.applyData.xinxi.registerStatus === 1) {
        if (location === 'person') {
          num = 10;
        } else {
          num = 19;
        }
        // this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.AccountOpeningStatus2')); // $i18n - 完成开户,已审核通过
      } else {
        if (registerStatus.indexOf('-') !== -1) {
          num = registerStatus.split('-')[1];
          if (num === '51') {
            num = '11a';
          } else if (num === '52') {
            num = '11b';
          }
        } else {
          num = 1;
        }
      }
      const path = '/openAccount/' + location + num;
      if (num === '') {
        return;
      } else {
        this.$verify.routerPermissions({ path });
      }
    },
    doInitBalanceMarginData() {
      this.$http({
        url: 'ib/accountBalanceMargin/cashSummary',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          if (res.object) {
            this.balanceMarginData.push(res.object);
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doInitMarketValueData() {
      this.$http({
        url: 'ib/accountMarketValue/accountMarketValue',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          if (res.object === '' || res.object === undefined) {
            // this.dataEmpty1 = true;
            if (this.$i18n.locale === 'zh_CN') {
              this.countListMap[0] = { '资产': '股票', '数量': 0 };
              this.countListMap[1] = { '资产': '基金', '数量': 0 };
              this.countListMap[2] = { '资产': '外汇', '数量': 0 };
              this.countListMap[3] = { '资产': '期权', '数量': 0 };
              this.countListMap[4] = { '资产': '期货', '数量': 0 };
              this.countListMap[5] = { '资产': '债券', '数量': 0 };
            } else if (this.$i18n.locale === 'en_US') {
              this.countListMap[0] = { '资产': 'Stocks', '数量': 0 };
              this.countListMap[1] = { '资产': 'Funds', '数量': 0 };
              this.countListMap[2] = { '资产': 'Forex', '数量': 0 };
              this.countListMap[3] = { '资产': 'Options', '数量': 0 };
              this.countListMap[4] = { '资产': 'Futures', '数量': 0 };
              this.countListMap[5] = { '资产': 'Bonds', '数量': 0 };
            }
          } else {
            // this.dataEmpty1 = false;
            this.marketValueData.push(res.object);
            var market = this.marketValueData[0];
            this.marketValueData[0].total = market.stockMarketValue + market.fundValue + market.mutualFundValue +
              market.moneyMarketFundValue + market.fxCashBalance + market.optionMarketValue + market.futuresPnl +
              market.corporateBondValue + market.tBondValue + market.tBillValue;
            if (this.$i18n.locale === 'zh_CN') {
              this.countListMap[0] = { '资产': '股票', '数量': res.object.stockMarketValue };
              this.countListMap[1] = { '资产': '基金', '数量': res.object.fundValue + res.object.mutualFundValue + res.object.moneyMarketFundValue };
              this.countListMap[2] = { '资产': '外汇', '数量': res.object.fxCashBalance };
              this.countListMap[3] = { '资产': '期权', '数量': res.object.optionMarketValue };
              this.countListMap[4] = { '资产': '期货', '数量': res.object.futuresPnl };
              this.countListMap[5] = { '资产': '债券', '数量': res.object.corporateBondValue + res.object.tBondValue + res.object.tBillValue };
            } else if (this.$i18n.locale === 'en_US') {
              this.countListMap[0] = { '资产': 'Stocks', '数量': res.object.stockMarketValue };
              this.countListMap[1] = { '资产': 'Funds', '数量': res.object.fundValue + res.object.mutualFundValue + res.object.moneyMarketFundValue };
              this.countListMap[2] = { '资产': 'Forex', '数量': res.object.fxCashBalance };
              this.countListMap[3] = { '资产': 'Options', '数量': res.object.optionMarketValue };
              this.countListMap[4] = { '资产': 'Futures', '数量': res.object.futuresPnl };
              this.countListMap[5] = { '资产': 'Bonds', '数量': res.object.corporateBondValue + res.object.tBondValue + res.object.tBillValue };
            }
          }
          for (let i = 0; i < 6; i++) {
            this.assetData.rows.push(this.countListMap[i]);
          }
          this.assetData.columns = this.assetTitle;
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doInitDailyPnlData() {
      this.$http({
        url: 'ib/accountDailyPnl/accountDailyPnl',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          if (res.object) {
            this.dailyPnlData.push(res.object);
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doInitMarketValueHisData() {
      this.$http({
        url: 'ib/accountMarketValueHis/accountSummaryList',
        method: 'post',
        params: this.order
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
            if (this.$i18n.locale === 'zh_CN') {
              this.lineData.columns = ['日期', '资产'];
              for (let i = 0; i < Object.keys(res.object).length; i++) {
                this.lineMap[i] = { '日期': (res.object[i].createDate.toString()), '资产': res.object[i].totalCashBalance };
                this.lineData.rows.push(this.lineMap[i]);
              }
            } else if (this.$i18n.locale === 'en_US') {
              this.lineData.columns = ['Date', 'Assets'];
              for (let i = 0; i < Object.keys(res.object).length; i++) {
                this.lineMap[i] = { 'Date': (res.object[i].createDate.toString()), 'Assets': res.object[i].totalCashBalance };
                this.lineData.rows.push(this.lineMap[i]);
              }
            }
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doInitExchangeData() {
      this.$http({
        url: 'ib/accountMarketValue/findExchangeRateList',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          if (Object.keys(res.object).length === 0 || res.object === undefined) {
            this.currencyData = [];
          } else {
            for (let i = 0; i < res.object.length; i++) {
              this.currencyData[i] = { currencyCode: res.object[i].currency + '.' + res.object[i].baseCurrency, exchangeRate: res.object[i].exchangeRate };
              this.CurrencyData.push(this.currencyData[i]);
            }
          }
        }
      }).catch(error => {
        console.log(error);
      });
    }
  }
};
</script>
<style lang="scss">
  .overview-wrapper {
    ::-webkit-scrollbar
    {
      width: 10px;
      height: 10px;
      background-color: #F5F5F5;
    }

    /*定义滚动条轨道 内阴影+圆角*/
    ::-webkit-scrollbar-track
    {
      // -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,0.3);
      border-radius: 10px;
      background-color: #F5F5F5;
    }

    /*定义滑块 内阴影+圆角*/
    ::-webkit-scrollbar-thumb
    {
      border-radius: 10px;
      // -webkit-box-shadow: inset 0 0 6px rgba(0,0,0,.3);
      background-color: #cccccc;
    }
    .el-table {
      th, td {
        padding: 5px 0;
        border: none!important;
      }
    }
    .el-table--border::after, .el-table--group::after{
      width: 0;
    }
    .el-table--border::after, .el-table--group::after, .el-table::before {
      z-index: 0;
    }
    .el-table--border::after, .el-table--group::after, .el-table::before {
      background-color: transparent;
    }
    .first-txt {
      li {
        z-index: 1001;
      }
    }
    .first-title {
      li {
        z-index: 1001;
      }
    }
    .v-charts-data-empty {
      height: 200px;
      background-color: #FFFFFF;
    }
    .el-menu-item, .el-submenu__title {
      line-height: 25px;
    }
    .el-form-item {
      margin: 0;
    }
    .el-divider--horizontal {
      margin: 0;
    }
    .el-divider--horizontal {
      background-color: #dcdfe6ab;
    }
    .el-card__body {
      min-height: 20px;
      padding: 10px 20px 0;
    }
    .box-card-black {
      .el-card__header {
        background-color: #434343;
        padding: 5px 20px;
      }
    }
    .box-card-gray {
      .el-card__header {
        background-color: #666666;
        padding: 5px 20px;
      }
    }
    .second-module-box {
      .el-menu {
        border: none!important;
      }
      .el-menu-item {
        padding: 0!important;
        height: 25px;
      }
      .el-menu-item:focus, .el-menu-item:hover {
        background-color: transparent;
      }
    }
  }
</style>
<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    background-color: transparent;
    .currency-table {
      line-height: 20px;
      color: #999999;
    }
    .first-title {
      color: #999999;
      line-height: 30px;
    }
    .box-title {
      font-size: 16px;
      color: #434343;
    }
    .overview-wrapper {
      .el-row {
        margin-bottom: 15px;
        &:last-child {
          margin-bottom: 0;
        }
      .el-col {
        border-radius: 4px;
      }
      .bg {
        background: #d3dce6;
      }
      .grid-content {
        border-radius: 4px;
        min-height: 36px;
      }
      .row-bg {
        padding: 10px 0;
        background-color: #f9fafc;
      }
    }
      .card-title {
        font-size: 14px;
        color: #FFF;
     }
      .card-txt {
        font-size:16px;
        font-weight:400;
        color: #434343;
      }
  }
    .second-module {
      .second-module-box {
        background-color: white;
        height: 470px;
        padding: 20px 30px 0;
      }
      .first-box {
        height: 230px;
        margin-bottom: 15px;
        background-color: white;
        padding: 20px;
       }
      .second-box {
        height: 150px;
        background-color: white;
        padding: 20px;
      }
      .first-txt {
        line-height: 30px;
        text-align: right;
      }
    }
    .third-module {
      margin: -200px 0 50px;
      padding: 30px 30px 0;
      height: 280px;
      background-color: #fff;
    }
  }
</style>
