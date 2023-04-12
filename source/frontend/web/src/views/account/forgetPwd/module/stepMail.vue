<template>
  <div>
    <el-steps class="step-wrapper" :active="1" finish-status="success" simple>
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
      <p class="success-txt1"><!--我们已向您的邮箱-->{{ $t('account.forget_pwd.send_mail_txt_1') }} {{ object }} {{ $t('account.forget_pwd.send_mail_txt_2') }}</p>
      <el-button class="mail-box-button" @click="jumpUrl()"><!--去邮箱接收邮件-->{{ $t('account.forget_pwd.check_email') }}</el-button>
      <p class="success-txt2" style="margin-top:20px;"><!--请注意查收邮件，并按照邮件中的提示操作，完成安全认证。-->{{ $t('account.forget_pwd.send_mail_txt_3') }}</p>
      <p class="success-txt2"><!--没有收到？-->{{ $t('account.forget_pwd.not_received') }}<router-link to="/forgetPwd/stepTwo/mail"><!--重新发送-->{{ $t('account.forget_pwd.send_again') }}</router-link></p>
    </div>
  </div>
</template>
<script>
export default {
  name: 'StepFour',
  props: {
    object: {
      type: String,
      default: ''
    }
  },
  mounted: function() {
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function() {
      history.pushState(null, null, document.URL);
    });
  },
  methods: {
    jumpUrl() {
      const hash = {
        'qq.com': 'http://mail.qq.com',
        'gmail.com': 'http://mail.google.com',
        'sina.com': 'http://mail.sina.com.cn',
        '163.com': 'http://mail.163.com',
        '126.com': 'http://mail.126.com',
        'yeah.net': 'http://www.yeah.net/',
        'sohu.com': 'http://mail.sohu.com/',
        'tom.com': 'http://mail.tom.com/',
        'sogou.com': 'http://mail.sogou.com/',
        '139.com': 'http://mail.10086.cn/',
        'hotmail.com': 'http://www.hotmail.com',
        'live.com': 'http://login.live.com/',
        'live.cn': 'http://login.live.cn/',
        'live.com.cn': 'http://login.live.com.cn',
        '189.com': 'http://webmail16.189.cn/webmail/',
        'yahoo.com.cn': 'http://mail.cn.yahoo.com/',
        'yahoo.cn': 'http://mail.cn.yahoo.com/',
        'eyou.com': 'http://www.eyou.com/',
        '21cn.com': 'http://mail.21cn.com/',
        '188.com': 'http://www.188.com/',
        'foxmail.com': 'http://www.foxmail.com',
        'outlook.com': 'http://www.outlook.com'
      };

      const email = this.object.split('@')[1];

      if (hash.hasOwnProperty(email)) {
        location.href = hash[email];
      } else {
        this.$message.error('Sorry! The corresponding email address is not found. Please log in to check your email!');
      }
    }
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
  .success-wrapper .success-txt1 {
    color: #434343;
    text-align: center;
    margin: 30px auto;
    font-size: 15px;
    line-height: 30px;
  }
  .success-wrapper .success-txt2 {
    color: #999999;
    text-align: left;
    margin: 8px auto;
    font-size: 14px;
  }
  .success-wrapper .mail-box-button {
    width: 100%;
    background-color: #FF3E4D;
    border-color: #FF3E4D;
    color: #FFFFFF;
  }
</style>
