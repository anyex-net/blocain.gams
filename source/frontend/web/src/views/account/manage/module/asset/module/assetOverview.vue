<template>
  <div class="app-container">
    <div class="asset-overview-wrapper">
      <span class="account-title">{{ $t('account.manage.asset.overview.margin') }}<!--账户保证金--></span>
      <el-table :data="assetOverviewData" class="assetOverview-table" :empty-text="$t('account.manage.no_data')">
        <el-table-column prop="accountCode" :label="$t('account.manage.asset.overview.account')" /><!--账户代码-->
        <el-table-column prop="currency" :label="$t('account.manage.asset.overview.currency')" /><!--币种-->
        <el-table-column prop="fullInitMarginReq" :label="$t('account.manage.asset.overview.all_initial')"><!--全部初始保证金要求-->
          <template slot-scope="scope">
            <span v-if="scope.row.fullInitMarginReq || scope.row.fullInitMarginReq === 0">{{ scope.row.fullInitMarginReq | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="fullMaintMarginReq" :label="$t('account.manage.asset.overview.all_maintenance')"><!--全部维持保证金要求-->
          <template slot-scope="scope">
            <span v-if="scope.row.fullMaintMarginReq || scope.row.fullMaintMarginReq === 0">{{ scope.row.fullMaintMarginReq | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateDate" :label="$t('account.manage.asset.overview.updated')" :formatter="dateFormat" /><!--更新日期-->
      </el-table>
    </div>
    <div class="asset-overview-wrapper">
      <span class="account-title">{{ $t('account.manage.asset.overview.balance') }}<!--账户余额--></span>
      <el-table :data="assetOverviewData" class="assetOverview-table" :empty-text="$t('account.manage.no_data')">
        <el-table-column prop="accountCode" :label="$t('account.manage.asset.overview.account')" /><!--账户代码-->
        <el-table-column prop="currency" :label="$t('account.manage.asset.overview.currency')" /><!--币种-->
        <el-table-column prop="netLiquidation" :label="$t('account.manage.asset.overview.liquidation_value')"><!--净清算价值-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.liquidation_value') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.netLiquidation || scope.row.netLiquidation === 0">{{ scope.row.netLiquidation | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="equityWithLoanValue" :label="$t('account.manage.asset.overview.loan_Value')" min-width="100"><!--含贷款价值的资产-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.loan_Value') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.equityWithLoanValue || scope.row.equityWithLoanValue === 0">{{ scope.row.equityWithLoanValue | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="netLiquidationUncertainty" :label="$t('account.manage.asset.overview.liquidation_uncertainty')"><!--净清算不确定-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.liquidation_uncertainty') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.netLiquidationUncertainty || scope.row.netLiquidationUncertainty === 0">{{ scope.row.netLiquidationUncertainty | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="grossPositionValue" :label="$t('account.manage.asset.overview.position_value')" min-width="100"><!--证券头寸总价值-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.position_value') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.grossPositionValue || scope.row.grossPositionValue === 0">{{ scope.row.grossPositionValue | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="totalCashvalue" :label="$t('account.manage.asset.overview.cash_value')"><!--总现金价值-->
          <template slot-scope="scope">
            <span v-if="scope.row.totalCashvalue || scope.row.totalCashvalue === 0">{{ scope.row.totalCashvalue | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateDate" :label="$t('account.manage.asset.overview.updated')" :formatter="dateFormat" /><!--更新日期-->
      </el-table>
    </div>
    <div class="asset-overview-wrapper">
      <span class="account-title">{{ $t('account.manage.asset.overview.available_funds') }}<!--交易可用资金--></span>
      <el-table :data="assetOverviewData" class="assetOverview-table" :empty-text="$t('account.manage.no_data')">
        <el-table-column prop="accountCode" :label="$t('account.manage.asset.overview.account')" /><!--账户代码-->
        <el-table-column prop="currency" :label="$t('account.manage.asset.overview.currency')" /><!--币种-->
        <el-table-column prop="fullAvailableFunds" :label="$t('account.manage.asset.overview.all_available_funds')" min-width="110"><!--全部可用资金-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.all_available_funds') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.fullAvailableFunds || scope.row.fullAvailableFunds === 0">{{ scope.row.fullAvailableFunds | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="excessLiquidity" :label="$t('account.manage.asset.overview.qll_excess_liquidity')" min-width="120"><!--全部剩余流动性-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.qll_excess_liquidity') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.excessLiquidity || scope.row.excessLiquidity === 0">{{ scope.row.excessLiquidity | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="postExpirationExcess" :label="$t('account.manage.asset.overview.post_expiry')" min-width="100"><!--到期后剩余-->
          <template slot-scope="scope">
            <span v-if="scope.row.postExpirationExcess || scope.row.postExpirationExcess === 0">{{ scope.row.postExpirationExcess | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="lookAheadAvailableFunds" min-width="120"><!--前瞻可用资金-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.Look_available') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.lookAheadAvailableFunds || scope.row.lookAheadAvailableFunds === 0">{{ scope.row.lookAheadAvailableFunds | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="lookAheadExcessLiquidity" :label="$t('account.manage.asset.overview.Look_excess')" min-width="120"><!--前瞻剩余流动性-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.Look_excess') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.lookAheadExcessLiquidity || scope.row.lookAheadExcessLiquidity === 0">{{ scope.row.lookAheadExcessLiquidity | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="buyingPower" :label="$t('account.manage.asset.overview.buying_power')"><!--购买力-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.buying_power') }}</span>
          </template>
          <template slot-scope="scope">
            <span v-if="scope.row.buyingPower || scope.row.buyingPower === 0">{{ scope.row.buyingPower | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="leverageS" :label="$t('account.manage.asset.overview.leverage')"><!--杠杆-->
          <template slot-scope="scope">
            <span v-if="scope.row.leverageS || scope.row.leverageS === 0">{{ scope.row.leverageS | keepNumTwo }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="lookAheadNextChange" :label="$t('account.manage.asset.overview.Look_next')" :formatter="date1Format" min-width="110"><!--前瞻下一变化-->
          <template slot="header">
            <span style="word-break: keep-all;">{{ $t('account.manage.asset.overview.Look_next') }}</span>
          </template>
        </el-table-column>
        <el-table-column prop="updateDate" :label="$t('account.manage.asset.overview.updated')" :formatter="dateFormat" /><!--更新日期-->
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AssetOverview',
  data() {
    return {
      assetOverviewData: []
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
      return this.$moment(date).format('YYYY-MM-DD');
    },
    date1Format: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date * 1000).format('HH:mm:ss');
    },
    doSearch() {
      this.$http({
        url: 'ib/accountBalanceMargin/cashSummary',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          if (res.object) {
            this.assetOverviewData.push(res.object);
          }
        }
      }).catch(error => {
        console.log(error);
      });
    }
  }
};
</script>
<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .asset-overview-wrapper {
      padding: 30px 40px 20px;
      background-color: #FFFFFF;
      margin-bottom: 20px;
      .assetOverview-table {
        width: 100%;
        margin-top: 20px;
      }
      .account-title {
        font-size: 18px;
        margin-left: -40px;
        padding-left: 38px;
        border-left: 2px solid;
      }
      .account-content {
        margin-top: 30px;
        color: #999999;
        .account-label {
          width: 250px;
          display: inline-block;
        }
        .account-value {
          width: 400px;
          display: inline-block;
          color: #434343;
        }
      }
    }
  }
</style>
