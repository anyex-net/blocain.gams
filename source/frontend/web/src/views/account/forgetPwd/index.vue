<template>
  <el-container>
    <el-header>
      <div class="app-header">
        <router-link to="/">
          <img src="@/assets/images/common/logo.png" class="logo">
        </router-link>
        <div class="tab-wrapper">
          <span>
            <span><!--已有账户，-->{{ $t('account.forget_pwd.exist') }}</span>
            <router-link to="/login" class="link"><!--马上登录-->{{ $t('account.forget_pwd.login') }}</router-link>
          </span>
          <span class="divide">|</span>
          <router-link to="/" class="link"><!--返回首页-->{{ $t('account.forget_pwd.home') }}</router-link>
        </div>
      </div>
    </el-header>
    <el-main>
      <div class="app-content">
        <div class="title"><!--找回密码-->{{ $t('account.forget_pwd.retrieve_pwd') }}</div>
        <router-view></router-view>
      </div>
    </el-main>
    <el-footer class="app-footer">
      <span><!--@版权所有 • MOF Securities Co Ltd 国瑞证券有限公司 2017-2019 • 不得转载 •-->{{ $t('account.forget_pwd.copyright') }}</span>
    </el-footer>
  </el-container>
</template>

<script>
export default {
  name: 'ForgetPwd'
};
</script>

<style>
  .el-step.is-simple .el-step__title {
    font-size: 14px;
  }
  .el-step.is-simple:not(:last-of-type) .el-step__title {
    max-width: 100%;
  }
  .el-step.is-simple .el-step__icon-inner[class*=el-icon]:not(.is-status) {
    font-size: 16px;
  }
</style>

<style lang="scss" scoped>
  .el-header, .el-main, .el-footer{
    padding: 0;
  }
  .app-header {
    width: 1200px;
    height: 100%;
    margin: 0 auto;
    line-height: 58px;
  }
  .app-header .logo {
    width: 138px;
    margin-top: 10px;
  }
  .app-header .tab-wrapper {
    float: right;
    color: #434343;
    font-size: 14px;
  }
  .app-header .tab-wrapper .link {
    color: #7FB0F3;
    cursor: pointer;
    text-decoration: none;
  }
  .app-header .tab-wrapper .divide {
    padding: 0 10px;
  }
  .app-content {
    width: 700px;
    margin: 80px auto 40px;
    background-color: #FFFFFF;
    overflow: hidden;
    height: 415px;
    border-radius: 5px;
  }
  .app-content .title {
    padding: 15px;
    background-color: #FF3E4D;
    color: #FFFFFF;
    text-align: center;
    font-size: 18px;
  }
  .app-footer {
    text-align: center;
    color: #999999;
    font-size: 14px;
  }
</style>
