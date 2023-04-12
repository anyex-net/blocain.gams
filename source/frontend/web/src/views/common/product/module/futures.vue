<template>
  <div class="app-container">
    <div class="product-wrapper">
      <el-row :gutter="20">
        <el-col :span="4">
          <router-link to="/product/stock">
            <el-card class="product-card">
              <span class="product-txt">{{ $t('common.product.stocks.name') }}<!--股票--></span>
              <div class="line"></div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="4">
          <router-link to="/product/fund">
            <el-card class="product-card">
              <span class="product-txt">{{ $t('common.product.funds.name') }}<!--基金--></span>
              <div class="line"></div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="4">
          <router-link to="/product/foreignEx">
            <el-card class="product-card">
              <span class="product-txt">{{ $t('common.product.forEx.name') }}<!--外汇--></span>
              <div class="line"></div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="4">
          <router-link to="/product/option">
            <el-card class="product-card">
              <span class="product-txt">{{ $t('common.product.options.name') }}<!--期权--></span>
              <div class="line"></div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="4">
          <router-link to="/product/futures">
            <el-card class="product-card active">
              <span class="product-txt">{{ $t('common.product.futures.name') }}<!--期货--></span>
              <div class="line"></div>
            </el-card>
          </router-link>
        </el-col>
        <el-col :span="4">
          <router-link to="/product/bond">
            <el-card class="product-card">
              <span class="product-txt">{{ $t('common.product.bonds.name') }}<!--债券--></span>
              <div class="line"></div>
            </el-card>
          </router-link>
        </el-col>
      </el-row>
      <el-table :data="productData" :empty-text="$t('common.product.no_data')" class="product-table"> <!--无数据-->
        <el-table-column prop="stockCode" :label="$t('common.product.futures.code')" /><!--期货代码-->
        <el-table-column prop="baseCurrency" :label="$t('common.product.currency')" :formatter="dictFormat" width="100px" /><!--币种-->
        <el-table-column prop="quoteDate" :label="$t('common.product.date')" :formatter="dateFormat" width="120px" /><!--行情日期-->
        <el-table-column prop="prevClose" :label="$t('common.product.close_price')" align="right"><!--昨收盘价-->
          <template slot-scope="scope">
            <span>{{ scope.row.prevClose | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="last" :label="$t('common.product.last_price')" align="right"><!--最新价-->
          <template slot-scope="scope">
            <span>{{ scope.row.last | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="bid" :label="$t('common.product.bid_price')" align="right"><!--买入价-->
          <template slot-scope="scope">
            <span>{{ scope.row.bid | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="bidSize" :label="$t('common.product.bid_size')" align="right" /><!--买入量-->
        <el-table-column prop="ask" :label="$t('common.product.ask_price')" align="right"><!--卖出价-->
          <template slot-scope="scope">
            <span>{{ scope.row.ask | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="askSize" :label="$t('common.product.ask_size')" align="right" /><!--卖出量-->
      </el-table>
      <el-pagination
        style="text-align:center;"
        background
        layout="prev, pager, next"
        :hide-on-single-page="true"
        :page-size="pageParams.rows"
        :page-count="pageParams.totalPage"
        :current-page="pageParams.page"
        @current-change="doInitData"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: 'Futures',
  data() {
    return {
      productData: [],
      baseCurrencyData: [],
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      }
    };
  },
  mounted: function() {
    this.doInitData();
  },
  methods: {
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD');
    },
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
    doInitData(page) {
      this.pageParams.page = page;
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
      this.$http({
        url: '/stock/quote/futures',
        method: 'get',
        params: this.pageParams
      }).then(res => {
        if (res.code === 200) {
          this.productData = res.rows;
          this.pageParams.totalPage = res.totalPage;
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    }
  }
};
</script>

<style lang="scss">
  .product-table {
    background-color: #F6F6F6;
    &::before {
      height: 0;
    }
    .el-table__body {
      // border-spacing: 0 10px;
    }
    th {
      background-color: #898E97;
      color: #FFFFFF;
      font-weight: 400;
    }
    td {
      background-color: #FFFFFF;
    }
    td, th {
      // padding: 9px;
      border-bottom: none!important;
    }
  }
</style>

<style lang="scss" scoped>
  .app-container {
    text-align: center;
    .product-wrapper {
      width: 1200px;
      margin: 30px auto;
      .product-card {
        height: 80px;
        line-height: 40px;
        text-decoration: solid;
        cursor: pointer;
        .product-txt {
          letter-spacing: 3px;
          border-bottom: 1px solid;
          padding-bottom: 3px;
        }
      }
      .product-card.active {
        color: #FFFFFF;
        margin-top: -10px;
        background: linear-gradient(to right, #E90031, #FB5E06);
      }
      .product-table {
        margin: 40px auto;
        min-height: 300px;
      }
    }
  }
</style>
