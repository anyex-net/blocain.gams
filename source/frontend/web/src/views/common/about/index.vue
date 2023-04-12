<template>
  <div class="app-container">
    <img class="about-banner" src="@/assets/images/common/banner_image_about.png">
    <div class="about-wrapper">
      <el-menu
        :default-active="$route.fullPath"
        class="about-el-menu"
        :router="true"
      >
        <div class="about-title">{{ $t('common.about.about_us') }}<!--关于国瑞--></div>
        <el-menu-item index="/about/introduction">
          <span slot="title">{{ $t('common.about.introduction.name') }}<!--公司简介--></span>
        </el-menu-item>
<!--        <el-menu-item index="/about/reportList">
          <span slot="title">媒体报道</span>
        </el-menu-item>-->
        <el-menu-item index="/about/development">
          <span slot="title">{{ $t('common.about.development.name') }}<!--发展历程--></span>
        </el-menu-item>
        <el-menu-item index="/about/contactUs">
          <span slot="title">{{ $t('common.about.contact_us.name') }}<!--联系我们--></span>
        </el-menu-item>
      </el-menu>
      <div class="about-content">
        <router-view></router-view>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'About'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .about-banner {
      width: 100%;
    }
    .about-wrapper {
      width: 1200px;
      margin: -46px auto 30px;
      overflow: hidden;
      .about-el-menu {
        float: left;
        width: 180px;
        height: 350px;
        text-align: center;
        border-right: none;
        border-radius: 10px 0 0 10px;
        .el-menu-item {
          background-color: #FFFFFF;
          height: 45px;
          line-height: 45px;
        }
        .el-menu-item.is-active {
          color: #E90031;
          background: rgba(233, 0, 49, 0.1);
          border-left: 2px solid #E90031;
        }
        .about-title {
          background-color: #FF3E4D;
          color: #FFFFFF;
          padding: 15px;
          border-top-left-radius: 10px;
        }
      }
      .about-content {
        float: left;
        width: 1000px;
        margin-left: 20px;
        background-color: #FFFFFF;
        min-height: 600px;
        position: relative;
        border-radius: 0 10px 10px 0;
      }
    }
  }
</style>

