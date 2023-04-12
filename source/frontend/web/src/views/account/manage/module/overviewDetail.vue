<template>
  <div class="app-container">
    <div class="detailed-top">
      <router-link to="/manage/overview">
        <span style="font-size: 12px;color: #999999;">{{ $t('account.manage.overview.portfolio') }}<!--资产分布--> ></span>
      </router-link>
      <el-select v-model="sec" class="detailed-select" size="mini" value="" @change="doSearch()">
        <el-option v-for="item in options" :key="item.value" :label="$t('account.manage.overview.' + item.label)" :value="item.value" />
      </el-select>
    </div>

    <div class="overview-detailed-wrapper" style="padding: 0 40px 10px;">
      <span class="detailed-title" style="margin-top: 30px;position: absolute;height: 25px;"><span style="text-align: center;font-size: 18px;">{{ $t('account.manage.overview.top_positions') }}<!--主要头寸--></span></span>
      <el-row>
        <el-col :span="24">
          <ve-ring
            :data="assetData"
            :settings="chartSettings"
            :title="title1"
            :legend-visible="false"
            style="height: 280px;"
          >
            <div v-if="dataEmpty" class="data-empty" style="margin: -270px auto 0;text-align: center;color: #909399;">{{ $t('account.manage.no_data') }}</div><!--无数据-->
          </ve-ring>
        </el-col>
      </el-row>
    </div>
    <div class="overview-detailed-wrapper" style="height: auto;">
      <span class="detailed-title">{{ $t('account.manage.overview.positions') }}<!--头寸--></span>
      <el-table :data="accountPositionData" highlight-current-row class="accountPosition-table" :empty-text="$t('account.manage.no_data')">
        <el-table-column prop="accountCode" :label="$t('account.manage.overview.account')" width="100" /><!--账户代码-->
        <el-table-column prop="symbol" :label="$t('account.manage.overview.symbol')" /><!--金融产品代码-->
        <el-table-column prop="secType" :label="$t('account.manage.overview.type')" /><!--金融产品类型-->
        <el-table-column prop="exchange" :label="$t('account.manage.overview.exchange')" /><!--交易所-->
        <el-table-column prop="position" :label="$t('account.manage.overview.quantity')" /><!--持仓数量-->
        <el-table-column prop="marketPrice" :label="$t('account.manage.overview.price')" /><!--市价-->
        <el-table-column prop="marketValue" :label="$t('account.manage.overview.value')" /><!--市场价值-->
        <el-table-column prop="averageCost" :label="$t('account.manage.overview.average_cost')"><!--平均价格-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.overview.average_cost') }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="unrealizedPnl" :label="$t('account.manage.overview.rizd')" /><!--未实现盈亏-->
        <el-table-column prop="realizedPnl" :label="$t('account.manage.overview.unrizd')"><!--已实现盈亏-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.overview.unrizd') }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateDate" :label="$t('account.manage.overview.updated')" :formatter="dateFormat" /><!--更新日期-->
      </el-table>
      <el-pagination
        style="text-align:center;"
        background
        layout="prev, pager, next"
        :hide-on-single-page="true"
        :page-size="pageParams.rows"
        :page-count="pageParams.totalPage"
        :current-page="pageParams.page"
        @current-change="doSearch"
      />
    </div>
  </div>
</template>

<script>
import 'v-charts/lib/style.css';
export default {
  name: 'OverviewDetail',
  props: {
    secType: {
      type: String,
      default: ''
    }
  },
  data() {
    this.chartSettings = {
      dimension: '市值',
      metrics: '数量',
      label: {
        normal: {
          show: true
        }
      },
      limitShowNum: 3,
      radius: [
        '60', '95'
      ],
      offsetY: 150
    };
    return {
      dataEmpty: '',
      sec: this.secType,
      options: [{
        value: 'STK',
        label: 'stocks'
      }, {
        value: 'FUND',
        label: 'funds'
      }, {
        value: 'FIXED',
        label: 'forex'
      }, {
        value: 'FUT',
        label: 'futures'
      }, {
        value: 'OPT',
        label: 'options'
      }, {
        value: 'BOND',
        label: 'bonds'
      }, {
        value: 'CASH',
        label: 'cash'
      }],
      title1: {
        text: '',
        subtext: '',
        x: 'center',
        y: '25%',
        itemGap: 20,
        textStyle: {
          color: '#999999',
          fontFamily: '微软雅黑',
          fontSize: 16,
          fontWeight: 'bolder'
        },
        subtextStyle: {
          fontSize: 16
        }
      },
      value2: '',
      subtext: '',
      assetData: {
        columns: [],
        rows: []
      },
      countListMap: {},
      assetTitle: ['资产'],
      accountPositionData: [],
      pageParams: {
        'rows': 50,
        'page': 1,
        'totalPage': 0
      }
    };
  },
  mounted: function() {
    this.doSearch();
  },
  methods: {
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYYMMDD');
    },
    doSearch(page) {
      this.pageParams.page = page;
      this.$http({
        url: 'ib/accountPosition/list',
        method: 'post',
        params: Object.assign(this.pageParams, { secType: this.sec })
      }).then(res => {
        if (res.code === 200) {
          this.assetData.rows = [];
          this.accountPositionData = res.rows;
          if (res.total === 0) {
            this.dataEmpty = true;
          } else {
            this.dataEmpty = false;
            this.pageParams.totalPage = res.totalPage;
            for (let i = 0; i < res.total; i++) {
              if (!res.rows[i].marketValue) {
                res.rows[i].marketValue = 0;
              }
              this.countListMap[i] = { '市值': res.rows[i].symbol, '数量': res.rows[i].marketValue };
              this.assetData.rows.push(this.countListMap[i]);
              this.subtext += res.rows.marketValue;
            }
            this.assetData.columns = this.assetTitle;
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
  .v-charts-data-empty {
    height: 250px;
    background-color: #FFFFFF;
  }
</style>
<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .detailed-top {
      margin-bottom: 20px;
    }
    .detailed-select {
      width: 100px;
    }
    .overview-detailed-wrapper {
      padding: 30px 40px 10px;
      background-color: #FFFFFF;
      margin-bottom: 30px;
      height: 280px;
      .accountPosition-table {
        margin: 20px 0;
      }
      .detailed-title {
        font-size: 18px;
        margin-left: -40px;
        padding-left: 38px;
        border-left: 2px solid;
      }
      .detailed-content {
        margin-top: 30px;
        color: #999999;
        .detailed-label {
          width: 250px;
          display: inline-block;
        }
        .detailed-value {
          width: 400px;
          display: inline-block;
          color: #434343;
        }
      }
    }
  }
</style>
