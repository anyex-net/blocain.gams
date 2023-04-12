<template>
  <div>
    <el-steps class="step-wrapper" :active="3" finish-status="success" simple>
      <el-step icon="el-icon-s-open">
        <template slot="title">
          <span><!--选择途径-->{{ $t('account.forget_pwd.choose_type') }}</span>
        </template>
      </el-step>
      <el-step icon="el-icon-upload">
        <template slot="title">
          <span><!--验证身份-->{{ $t('account.forget_pwd.verify_identity') }}</span>
        </template>
      </el-step>
      <el-step icon="el-icon-edit">
        <template slot="title">
          <span><!--设置新密码-->{{ $t('account.forget_pwd.set_pwd') }}</span>
        </template>
      </el-step>
      <el-step icon="el-icon-success">
        <template slot="title">
          <span><!--完成-->{{ $t('account.forget_pwd.finish') }}</span>
        </template>
      </el-step>
    </el-steps>
    <div class="success-wrapper">
      <img src="@/assets/images/account/success.png" />
      <p class="success-txt"><!--您的新密码已经设置完毕，请牢记哦-->{{ $t('account.forget_pwd.set_pwd_txt') }}</p>
      <router-link to="/login">
        <el-button class="login"><!--立即登录-->{{ $t('account.forget_pwd.login') }}</el-button>
      </router-link>
    </div>
  </div>
</template>
<script>
export default {
  name: 'StepFour',
  mounted: function() {
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function() {
      history.pushState(null, null, document.URL);
    });
  }
};
</script>

<style scoped>
  .step-wrapper {
    padding: 13px 4%;
    background-color: #FFFFFF;
    margin-top: 10px;
    border-bottom: 1px solid #E4E4E4;
  }
  .success-wrapper {
    width: 400px;
    margin: 50px auto;
    text-align: center;
  }
  .success-wrapper .success-txt {
    color: #434343;
    text-align: center;
    margin: 30px auto;
    font-size: 14px;
  }
  .success-wrapper .login {
    width: 100%;
    background-color: #FF3E4D;
    border-color: #FF3E4D;
    color: #FFFFFF;
  }
</style>
