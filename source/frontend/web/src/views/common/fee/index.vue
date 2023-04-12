<template>
  <div class="app-container">
    <img class="fee-banner" src="@/assets/images/common/banner_image_fee.png" />
    <div class="fee-menu-title">
      <el-menu :default-active="$route.fullPath" class="el-menu-demo" mode="horizontal" :router="true">
        <el-menu-item index="/fee/northAmericanMarket">{{ $t('common.fee.north_american') }}<!--北美市场--></el-menu-item>
        <el-menu-item index="/fee/asianMarket">{{ $t('common.fee.asia_pacific') }}<!--亚洲市场--></el-menu-item>
        <el-menu-item index="/fee/europeanMarket">{{ $t('common.fee.europe') }}<!--欧洲市场--></el-menu-item>
      </el-menu>
    </div>
    <router-view></router-view>
    <ul class="fee-other">
      <li class="fee-other-txt">{{ $t('common.fee.txt_1') }}</li>
      <li class="fee-other-txt">{{ $t('common.fee.txt_2') }}</li>
      <li class="fee-other-txt">{{ $t('common.fee.txt_3') }}</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Fee',
  methods: {
    getImgUrl(image) {
      return require('@/assets/images' + image);
    }
  }
};
</script>
<style lang="scss" scoped>
  .app-container {
    min-height: 600px;
    .fee-banner {
      width: 100%;
    }
    .fee-menu-title {
      background-color: #FFFFFF;
      text-align: center;
      height: 50px;
      margin-top: -2px;
    }
      .el-menu--horizontal>.el-menu-item.is-active {
        background-color: #FF6571!important;
        color: #FFFFFF!important;
        border:none;
      }
      .el-menu.el-menu--horizontal {
        border: none;
      }
    .el-menu-demo {
      display: inline-block;
    }
    .el-menu-item {
      width: 400px;
      text-align: center;
      height: 50px;
      line-height: 50px;
    }
    .fee-other {
      width: 1200px;
      margin: 30px auto 50px;
      .fee-other-txt {
        font-size: 14px;
        font-weight: 400;
        color: #999999;
        line-height: 30px;
      }
    }
  }
</style>
