<template>
  <div class="userDetails">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
      <el-breadcrumb-item :to="{ name: 'Account',params:{key: 'key'} }">账户管理</el-breadcrumb-item>
      <el-breadcrumb-item>{{ tabViewCn }}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-tabs v-model="tabView" type="card" class="navTableCheck" @tab-click="tabClick">
      <el-tab-pane label="账户概述" name="accountOverview"></el-tab-pane>
      <el-tab-pane label="资金概览" name="assetOverview"></el-tab-pane>
      <el-tab-pane label="投资概览" name="investmentOverview"></el-tab-pane>
      <el-tab-pane label="交易记录" name="tradingRecord"></el-tab-pane>
      <el-tab-pane label="出入金记录" name="assetRecord"></el-tab-pane>
      <el-tab-pane label="更多信息" name="moreInformation"></el-tab-pane>
    </el-tabs>
    <keep-alive>
      <component :is="tabView" :account-info="accountInfo"></component>
    </keep-alive>
  </div>
</template>
<script>
import accountOverview from './tabViews/accountOverview';
import assetRecord from './tabViews/assetRecord';
import investmentOverview from './tabViews/investmentOverview';
import assetOverview from './tabViews/assetOverview';
import moreInformation from './tabViews/moreInformation';
import tradingRecord from './tabViews/tradingRecord';
export default {
  components: {
    accountOverview,
    assetRecord,
    investmentOverview,
    assetOverview,
    moreInformation,
    tradingRecord
  },
  props: {
    accountInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      tabView: 'accountOverview',
      tabViewCn: '账户概述'
    };
  },
  methods: {
    tabClick(tab) {
      this.tabViewCn = tab.label;
    }
  }
};
</script>
<style lang="scss">
.userDetails {
  .navTableCheck {
    .el-tabs__nav {
      background: #f3f6f9;
      border-radius: 0px;
      .el-tabs__item {
        padding: 0 30px;
        &:hover {
          color: #ae1207;
        }
      }
    }
    .is-top .is-active {
      background: #fff !important;
      border-top: 3px solid rgb(174, 18, 7);
      box-sizing: content-box;
      color: #ae1207;
    }
    .el-tabs__content {
      display: none;
    }
  }
  .breadcrumb {
    margin-bottom: 40px;
    font-size: 14px;
    span {
      &:nth-child(1) {
        span {
          font-weight: 700 !important;
        }
      }
    }
  }
}
</style>

