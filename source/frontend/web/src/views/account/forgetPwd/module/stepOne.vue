<template>
  <div>
    <el-steps class="step-wrapper" :active="0" finish-status="success" simple>
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
    <div class="select-wrapper">
      <router-link to="/forgetPwd/stepTwo/mail">
        <el-button class="mail-button"><!--邮箱验证-->{{ $t('account.forget_pwd.mailbox_verify') }}</el-button>
      </router-link>
      <br>
      <router-link to="/forgetPwd/stepTwo/phone">
        <el-button class="phone-button"><!--手机验证-->{{ $t('account.forget_pwd.mobile_verify') }}</el-button>
      </router-link>
    </div>
  </div>
</template>
<script>
export default {
  name: 'StepOne'
};
</script>

<style scoped>
  .step-wrapper {
    padding: 13px 4%;
    background-color: #FFFFFF;
    margin-top: 10px;
    border-bottom: 1px solid #E4E4E4;
  }
  .select-wrapper {
    width: 400px;
    margin: 80px auto;
  }
  .select-wrapper .mail-button {
    display: block;
    width: 100%;
    background-color: #FF3E4D;
    border-color: #FF3E4D;
    color: #FFFFFF;
  }
  .select-wrapper .phone-button {
    display: block;
    width: 100%;
    background-color: #999999;
    border-color: #999999;
    color: #FFFFFF;
  }
</style>
