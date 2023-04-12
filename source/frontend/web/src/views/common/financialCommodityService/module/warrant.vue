<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.financial.warrant.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.financial.warrant.title') }}<!--权证--></span>
    </div>
    <div class="wrapper">
      <div style="width:6px;height:30px;background:rgba(255,62,77,1);position: absolute;top: 130px;"></div>
      <div class="wrapper-title">{{ $t('common.financial.warrant.title') }}<!--权证--></div>
      <el-table :data="stockWarrantData" class="warrant-table">
        <el-table-column prop="stockSymbol" :label="$t('common.financial.warrant.code')" /><!--产品代码-->
        <el-table-column :label="$t('common.financial.warrant.call_put')"><!--购/沽-->
          <template slot-scope="scope">
            <span v-if="scope.row.warrantType === 'call'">{{ $t('common.financial.warrant.call') }}</span>
            <span v-if="scope.row.warrantType === 'put'">{{ $t('common.financial.warrant.put') }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="baseCurrency" :formatter="dictFormat" :label="$t('common.financial.warrant.currency')" /><!--币种-->
        <el-table-column prop="exercisePrice" :label="$t('common.financial.warrant.strike_price')" align="right"><!--行权价-->
          <template slot-scope="scope">
            <span>{{ scope.row.exercisePrice | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="prevClose" :label="$t('common.financial.warrant.close_price')" align="right"><!--昨收盘价-->
          <template slot-scope="scope">
            <span>{{ scope.row.prevClose | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="bid" :label="$t('common.financial.warrant.bid_price')" align="right"><!--买入价-->
          <template slot-scope="scope">
            <span>{{ scope.row.bid | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="bidSize" :label="$t('common.financial.warrant.bid_size')" align="right" /><!--买入量-->
        <el-table-column prop="ask" :label="$t('common.financial.warrant.ask_price')" align="right"><!--卖出价-->
          <template slot-scope="scope">
            <span>{{ scope.row.ask | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="askSize" :label="$t('common.financial.warrant.qsk_size')" align="right" /><!--卖出量-->
        <el-table-column prop="quoteDate" :label="$t('common.financial.warrant.quote_date')" align="right" :formatter="dateFormat" /><!--行情日期-->
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
export default {
  name: 'Warrant',
  data() {
    return {
      stockWarrantData: [],
      baseCurrencyData: [],
      stockWarrantLoading: false,
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      }
    };
  },
  mounted() {
    this.doSearch();
    this.doInitData();
  },
  methods: {
    dictFormat: function(row, column) {
      const dictArray = this[column.property + 'Data'];
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row[column.property]) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD');
    },
    doInitData: function() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'baseCurrency',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.baseCurrencyData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.stockWarrantLoading = true;
      this.$http({
        url: '/stock/stockWarrant/findWarrant',
        method: 'post',
        params: Object.assign(this.pageParams, { warrantClass: 'warrant' })
      }).then(res => {
        if (res.code === 200) {
          this.stockWarrantData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.stockWarrantLoading = false;
        }
      }).catch(error => {
        console.log(error);
      });
    }
  }
};
</script>
<style lang="scss">
  .wrapper {
    th {
      background-color: #898E97;
      color: #FFFFFF;
      font-weight: 400;
    }
    td {
      background-color: #FFFFFF;
    }
    td .el-table-column {
      padding: 0;
    }
    td, th {
      // padding: 12px 10px;
      text-align: center;
    }
    .el-table .cell {
      padding: 5px 10px;
    }
  }
</style>
<style lang="scss" scoped>
  .app-container {
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .wrapper {
      width: 1200px;
      min-height: 400px;
      margin: 40px auto;
      .wrapper-title {
        font-size: 20px;
        margin: 30px 20px 20px;
      }
      .warrant-table {
        margin: 0 auto 80px;
        // min-height: 300px;
        border-radius: 3px
      }
    }
  }
</style>
