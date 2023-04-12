<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.financial.wpm.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.financial.wpm.title') }}<!--理财易--></span>
    </div>
    <div class="wrapper">
      <el-row v-if="recommendData" :gutter="25">
        <el-col v-for="data in recommendData" :key="data.id" :span="8">
          <div class="recommend">
            <p class="recommend-title">{{ data.stockInfo.stockNameEn }}</p>
            <div style="height: 56px;display: inline-block">
              <span class="recommend-num">{{ data.expectedAnnualReturn | keepNum }}</span>
              <span v-if="data.expectedAnnualReturn" class="recommend-num" style="font-size: 14px;"> %</span>
            </div>
            <el-button class="recommend-button" type="danger">{{ $t('common.financial.wpm.buy_now') }}<!--立即抢购--></el-button>
            <p class="recommend-remark">{{ $t('common.financial.wpm.expected_rate') }}<!--预计年化收益率--></p>
          </div>
        </el-col>
      </el-row>
      <div style="position:relative;">
        <div style="width:6px;height:30px;background:rgba(255,62,77,1);position: absolute;top: -5px;"></div>
        <p class="wrapper-title">{{ $t('common.financial.wpm.financial_products') }}<!--理财产品--></p>
      </div>
      <div class="wrapper-search">
        <el-menu>
          <div style="margin-bottom: 10px;">
            <el-menu-item>
              <span class="search-title">{{ $t('common.financial.wpm.product_name') }}<!--产品名称-->：</span>
              <el-input v-model="wmpSearchForm.stockNameCn" size="mini" style="width: 200px;" clearable></el-input>
              <el-button type="danger" size="mini" class="search-button" @click="doSearch()">{{ $t('common.financial.wpm.search') }}<!--搜索--></el-button>
            </el-menu-item>
          </div>
          <el-menu-item>
            <span class="search-title">{{ $t('common.financial.wpm.product_type') }}<!--产品种类-->：</span>
            <el-checkbox-group v-model="fundTypeList" class="search-checkbox">
              <el-checkbox label="" class="checkBox" @change="flagChange('fundType')">{{ $t('common.financial.wpm.all') }}<!--全部--></el-checkbox>
              <el-checkbox v-for="data in fundType" :label="data.id" :key="data.name" class="checkBox-item">{{ $t('common.financial.wpm.' + data.name) }}</el-checkbox>
            </el-checkbox-group>
          </el-menu-item>
          <el-menu-item>
            <span class="search-title">{{ $t('common.financial.wpm.risk_rating') }}<!--风险评级-->：</span>
            <el-checkbox-group v-model="riskLevelList" class="search-checkbox">
              <el-checkbox label="" class="checkBox" @change="flagChange('riskLevel')">{{ $t('common.financial.wpm.all') }}<!--全部--></el-checkbox>
              <el-checkbox v-for="data in riskLevel" :label="data.id" :key="data.name" class="checkBox-item">{{ $t('common.financial.wpm.' + data.name) }}</el-checkbox>
            </el-checkbox-group>
          </el-menu-item>
          <el-menu-item>
            <span class="search-title">{{ $t('common.financial.wpm.currency_type') }}<!--投资币种-->：</span>
            <el-checkbox-group v-model="baseCurrencyList" class="search-checkbox">
              <el-checkbox label="" class="checkBox" @change="flagChange('baseCurrency')">{{ $t('common.financial.wpm.all') }}<!--全部--></el-checkbox>
              <el-checkbox v-for="data in baseCurrency" :label="data.name" :key="data.name" class="checkBox-item">{{ $t('common.financial.wpm.' + data.name) }}</el-checkbox>
            </el-checkbox-group>
          </el-menu-item>
        </el-menu>
      </div>
      <el-table :data="stockWmpData" class="wmp-table">
        <el-table-column prop="stockInfo.stockNameEn" width="200" :label="$t('common.financial.wpm.product_name')" /><!--产品名称-->
        <el-table-column prop="fundType" :formatter="dictFormat1" width="110" :label="$t('common.financial.wpm.product_type')" /><!--产品种类-->
        <el-table-column width="145" align="right" :label="$t('common.financial.wpm.expected_rate')"><!--预计年化收益率-->
          <template slot-scope="scope">
            <span v-if="scope.row.expectedAnnualReturn || scope.row.expectedAnnualReturn === 0">{{ scope.row.expectedAnnualReturn | keepNum }}%</span>
          </template>
        </el-table-column>
        <el-table-column width="120" align="right" :label="$t('common.financial.wpm.product_period') + $t('common.financial.wpm.day')"><!--产品期限-->
          <template slot-scope="scope">
            <span v-if="scope.row.productLife || scope.row.productLife === 0">{{ scope.row.productLife }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="riskLevel" :formatter="dictFormat" width="150" align="center" :label="$t('common.financial.wpm.risk_rating')" /><!--风险评级-->
        <el-table-column prop="startSaleAmt" width="80" align="right" :label="$t('common.financial.wpm.starting_amount')" /><!--起售金额-->
        <el-table-column prop="stockInfo.baseCurrency" align="center" :label="$t('common.financial.wpm.currency')" /><!--币种-->
        <el-table-column prop="listingDate" :formatter="dateFormat" :label="$t('common.financial.wpm.issue_date')" /><!--投资起始日-->
        <el-table-column prop="expireDate" :formatter="dateFormat" :label="$t('common.financial.wpm.maturity_date')" /><!--投资结束日-->
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
  name: 'Wmp',
  filters: {
    keepNum(value) {
      value = Number(value);
      return value.toFixed(2);
    }
  },
  data() {
    return {
      locale: this.$i18n.locale,
      ifShow: true,
      stockWmpData: [],
      stockWmpLoading: false,
      fundTypeFlag: 0,
      riskLevelFlag: 0,
      baseCurrencyFlag: 0,
      assetTypeData: [],
      fundTypeData: [],
      baseCurrencyData: [],
      riskLevelData: [],
      fundTypeList: [],
      riskLevelList: [],
      baseCurrencyList: [],
      riskLevel: [
        { id: 'R1', name: 'R1' },
        { id: 'R2', name: 'R2' },
        { id: 'R3', name: 'R3' },
        { id: 'R4', name: 'R4' },
        { id: 'R5', name: 'R5' }
      ],
      fundType: [
        { id: 'moneyFund', name: 'money_funds' },
        { id: 'stockFund', name: 'equity_funds' },
        { id: 'bondFund', name: 'bond_funds' },
        { id: 'foreignExFund', name: 'fx_funds' }
      ],
      baseCurrency: [
        { name: 'HKD' },
        { name: 'USD' },
        { name: 'CHN' },
        { name: 'GBP' },
        { name: 'CAD' },
        { name: 'AUD' }
      ],
      wmpSearchForm: {
        stockNameCn: '',
        fundType: '',
        riskLevel: '',
        baseCurrency: ''
      },
      recommendData: [],
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      }
    };
  },
  watch: {
    fundTypeFlag: function() {
      if (this.fundTypeFlag === true) {
        this.fundTypeList = [''];
      }
    },
    fundTypeList: function() {
      this.watchList(this.fundTypeList, this.fundTypeFlag);
    },
    riskLevelFlag: function() {
      if (this.riskLevelFlag === true) {
        this.riskLevelList = [''];
      }
    },
    riskLevelList: function() {
      this.watchList(this.riskLevelList, this.riskLevelFlag);
    },
    baseCurrencyFlag: function() {
      if (this.baseCurrencyFlag === true) {
        this.baseCurrencyList = [''];
      }
    },
    baseCurrencyList: function() {
      this.watchList(this.baseCurrencyList, this.baseCurrencyFlag);
    }
  },
  mounted() {
    this.doInitData();
    this.doSearch();
  },
  methods: {
    watchList(list, flag) {
      if (list.length > 1 && list[0] === '') {
        flag = false;
        list.splice(0, 1);
      }
    },
    flagChange(type) {
      if (type === 'fundType') {
        this.fundTypeFlag = !this.fundTypeFlag;
      } else if (type === 'riskLevel') {
        this.riskLevelFlag = !this.riskLevelFlag;
      } else if (type === 'baseCurrency') {
        this.baseCurrencyFlag = !this.baseCurrencyFlag;
      }
    },
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
          dictValue = data.dictValue;
        }
      });
      return dictValue;
    },
    dictFormat1: function(row, column) {
      const dictArray = this[column.property + 'Data'];
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row[column.property]) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    doInitData() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'fundType',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.fundTypeData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
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
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'riskLevel',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.riskLevelData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
      this.$http({
        url: '/stock/stockWmp/findRecommend',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          this.recommendData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doSearch: function(page) {
      var fundTypeInfo = '';
      var riskLevelInfo = '';
      var baseCurrencyInfo = '';
      for (let i = 0; i < this.fundTypeList.length; i++) {
        fundTypeInfo = (fundTypeInfo + this.fundTypeList[i]) + (((i + 1) === this.fundTypeList.length) ? '' : ',');
      }
      for (let i = 0; i < this.riskLevelList.length; i++) {
        riskLevelInfo = (riskLevelInfo + this.riskLevelList[i]) + (((i + 1) === this.riskLevelList.length) ? '' : ',');
      }
      for (let i = 0; i < this.baseCurrencyList.length; i++) {
        baseCurrencyInfo = (baseCurrencyInfo + this.baseCurrencyList[i]) + (((i + 1) === this.baseCurrencyList.length) ? '' : ',');
      }
      this.pageParams.page = page;
      this.stockWmpLoading = true;
      this.$http({
        url: '/stock/stockWmp/findWmp',
        method: 'post',
        params: Object.assign(this.pageParams, this.wmpSearchForm, { fundTypeArray: fundTypeInfo }, { riskLevelArray: riskLevelInfo }, { baseCurrencyArray: baseCurrencyInfo })
      }).then(res => {
        if (res.code === 200) {
          this.stockWmpData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.stockWmpLoading = false;
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
    .el-menu {
      border: none!important;
      .el-menu-item {
        cursor: default;
        background-color: white!important;
      }
      .el-menu-item:hover {
        background-color: transparent!important;
      }
    }
    .el-menu-item, .el-submenu__title {
      line-height: 20px;
    }
    .wrapper-search .search-checkbox {
      width: auto!important;
    }
    th {
      background-color: #898E97;
      color: #FFFFFF;
      font-weight: 400;
    }
    td {
      background-color: #FFFFFF;
    }
    td .el-table-column {
      // padding: 0;
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
      margin: 40px auto;
      .recommend {
        background: white;
        border-radius:4px;
        padding: 20px 30px;
        height: 111px;
        margin-bottom: 20px;
        .recommend-title {
          font-size:18px;
          font-weight:bold;
          color:rgba(67,67,67,1);
          line-height: 22px;
        }
        .recommend-num {
          font-size:40px;
          font-weight:bold;
          color:rgba(255,86,47,1);
          line-height: 50px;
          height: 56px;
        }
        .recommend-button {
          float: right;
          height: 30px;
          margin-top: 10px;
          padding: 5px 20px;
          background-color: #FF3E4D;
        }
        .recommend-remark {
          color: #999999;
        }
      }
      .wrapper-search {
        height: 130px;
        background-color: white;
        margin-bottom: 20px;
        padding: 20px 10px;
        .el-menu-item {
          height: 30px;
        }
        .search-title {
          color: #999999;
          width: 110px;
          display: inline-block;
        }
        .search-button {
          margin-left: 10px;
          width: 80px;
          height: 26px;
          padding: 6px 15px;
          background: #FF0000;
        }
        .search-checkbox {
          width: 300px;
          display: inline-block;
          .checkBox {
            width: 80px;
          }
          .checkBox-item {
            width: 130px;
          }
        }
      }
      .wrapper-title {
        font-size: 20px;
        margin: 10px 20px 20px;
      }
      .wmp-table {
        margin: 0 auto 20px;
        border-radius: 3px;
      }
    }
  }
</style>
