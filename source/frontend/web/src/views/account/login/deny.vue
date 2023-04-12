<template>
  <el-container>
    <el-header>
      <div class="app-header">
        <router-link to="/">
          <img src="@/assets/images/common/logo.png" class="logo">
        </router-link>
        <div class="tab-wrapper">
          <router-link to="/" class="register">开立账户</router-link>
          <span class="divide">|</span>
          <router-link to="/" class="return">返回首页</router-link>
        </div>
      </div>
    </el-header>
    <el-main>
      <div class="app-content">
        <div class="left-wrapper"></div>
        <div class="right-wrapper">
          <div class="deny-title">授权</div>
          <div class="deny-txt">
            <p>登录失败，用户拒绝授权</p>
            <p>错误信息为：{{ message }}</p>
          </div>
          <router-link to="/">
            <el-button class="deny-btn" size="mini">返回首页</el-button>
          </router-link>
        </div>
      </div>
    </el-main>
    <el-footer class="app-footer">
      @版权所有 • MOF Securities Co Ltd 国瑞证券有限公司 2017-2019 • 不得转载 •
    </el-footer>
  </el-container>
</template>

<script>
export default {
  name: 'Deny',
  props: {
    message: {
      type: String,
      default: '登录失败，用户拒绝授权'
    }
  }
};
</script>

<style lang="scss" scoped>
  .el-header, .el-main, .el-footer{
    padding: 0;
  }
  .app-header {
    width: 1200px;
    height: 100%;
    margin: 0 auto;
    line-height: 58px;
    .logo {
      width: 138px;
      margin-top: 10px;
    }
    .tab-wrapper {
      float: right;
      color: #999999;
      font-size: 14px;
      .register{
        color: #FF3E4D;
        cursor: pointer;
      }
      .divide {
        padding: 0 10px;
      }
      .return {
        cursor: pointer;
        color: #999999;
      }
    }
  }
  .app-content {
    width: 720px;
    margin: 100px auto 40px;
    background-color: #FFFFFF;
    overflow: hidden;
    border-radius: 5px;
    .left-wrapper {
      float: left;
      width: 320px;
      height: 400px;
      position: relative;
      background: url('../../../assets/images/account/login.png') no-repeat;
      background-size: cover;
    }
    .right-wrapper {
      float: left;
      width: 400px;
      height: 400px;
      position: relative;
      text-align: center;
      .deny-title {
        color: #FF3E4D;
        margin-top: 50px;
        font-size: 24px;
      }
      .deny-txt {
        text-align: center;
        margin-top: 80px;
      }
      .deny-btn {
        margin-top: 20px;
        width: 150px;
        background-color: #FF3E4D;
        border-color: #FF3E4D;
        color: #FFFFFF;
      }
    }
  }
  .app-footer {
    text-align: center;
    color: #999999;
    font-size: 14px;
  }
  .login-readonly-item {
    -webkit-appearance: none;
    background-color: #EDEDED;
    background-image: none;
    border-radius: 4px;
    border: 1px solid #999999;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    color: #999999;
    display: inline-block;
    font-size: inherit;
    height: 40px;
    line-height: 40px;
    outline: 0;
    padding: 0 15px;
    -webkit-transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    transition: border-color .2s cubic-bezier(.645,.045,.355,1);
    width: 100%;
    cursor: default;
  }
</style>
