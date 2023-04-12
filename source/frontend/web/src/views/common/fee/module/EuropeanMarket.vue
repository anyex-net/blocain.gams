<template>
  <div class="app-container">
    <div class="fee-wrapper">
      <div class="item-title">
        <div class="title-img">
          <p class="title-txt">{{ $t('common.fee.commission') }}<!--佣金--></p>
        </div>
      </div>
      <el-table :data="firstTableData" style="width: 100%">
        <!--套餐-->
        <el-table-column :label="$t('common.fee.package')" width="400">
          <template slot-scope="scope">{{ $t('common.fee.' + scope.row.package) }}</template>
        </el-table-column>
        <!--类型-->
        <el-table-column :label="$t('common.fee.type')" width="400">
          <template slot-scope="scope">{{ $t('common.fee.' + scope.row.type) }}</template>
        </el-table-column>
        <!--费用-->
        <el-table-column :label="$t('common.fee.fees')" width="400">
          <template slot-scope="scope">{{ $t('common.fee.' + scope.row.fee) }}</template>
        </el-table-column>
      </el-table>
      <div class="item-title">
        <div class="title-img">
          <p class="title-txt">{{ $t('common.fee.usage_fee') }}<!--平台使用费--></p>
        </div>
        <el-table :data="secondTableData" style="width: 100%">
          <el-table-column prop="package" :label="$t('common.fee.usage_fee')" width="400"><!--平台使用费-->
            <template slot-scope="scope">{{ $t('common.fee.' + scope.row.package) }}</template>
          </el-table-column>
          <el-table-column label="" width="400" />
          <el-table-column prop="fee" :label="$t('common.fee.fees')" width="400"><!--费用-->
            <template slot-scope="scope">{{ $t('common.fee.' + scope.row.fee) }}</template>
          </el-table-column>
        </el-table>
      </div>
      <div class="item-title">
        <div class="title-img">
          <p class="title-txt">{{ $t('common.fee.margi_trading') }}<!--融资融券--></p>
        </div>
        <el-table :data="thirdTableData" style="width: 100%">
          <el-table-column prop="package" :label="$t('common.fee.borrowing_amount')" width="400" /><!--借贷金额-->
          <el-table-column prop="type" :label="$t('common.fee.package_1')" width="400" /><!--低佣金套餐-->
          <el-table-column prop="fee" :label="$t('common.fee.package_2')" width="400" /><!--低利率套餐-->
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      firstTableData: [{
        package: 'package_1',
        type: 'commission',
        fee: 'fees_1'
      }, {
        package: 'package_2',
        type: 'commission',
        fee: 'fees_2'
      }],
      secondTableData: [{
        package: 'usage_fee',
        type: ' ',
        fee: 'fees_1'
      }],
      thirdTableData: [{
        package: '0-100,000',
        type: '5.88%',
        fee: '3.88%'
      }, {
        package: '100,000.01 - 1,000,000',
        type: '5.38%',
        fee: '3.88% '
      }
      ]
    };
  }
};
</script>

<style lang="scss" scoped>
  .app-container {
    .fee-wrapper {
      width: 1200px;
      margin: auto;
    }
    .menu-title {
      height: 50px;
      background-color: #FFFFFF;
      text-align: center;
    }
    .item-title {
      margin-top: 40px;
    }
    .el-menu-demo {
      display: inline-block;
    }
    .el-menu-item {
      width: 400px;
      text-align: center;
      height: 50px;
    }
    .el-table-column {
      text-align: center;
      margin-bottom: 20px;
    }
    .title-img {
      border-left: 6px solid #FF6571;
      margin-bottom: 24px;
    }
    .title-txt {
      margin-left: 10px;
      font-size: 24px;
      font-weight: 400;
      color: #434343;
      padding: 3px;
    }
  }
</style>
