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
          <div class="login-title">登录</div>
          <el-form v-if="step === 'first'" ref="loginForm" :model="loginForm" status-icon :rules="loginRules" label-width="100px" class="login-form">
            <el-form-item label-width="auto" prop="username">
              <el-input v-model="loginForm.username" placeholder="用户名" type="text" autocomplete="off" />
            </el-form-item>
            <el-form-item label-width="auto" prop="password">
              <el-input v-model="loginForm.password" placeholder="密码" type="password" autocomplete="off" />
            </el-form-item>
            <el-form-item label-width="auto">
              <el-button class="login-submit" :loading="loading" @click="doLogin('loginForm')">提交</el-button>
              <router-link class="forgetPwd-txt" to="/forgetPwd">忘记密码？</router-link>
            </el-form-item>
          </el-form>
          <el-form v-if="step === 'second'" ref="checkForm" :model="checkForm" status-icon :rules="checkRules" label-width="100px" class="login-form">
            <el-form-item label-width="auto" prop="username">
              <div class="login-readonly-item">旧手机：{{ mobNo }}</div>
            </el-form-item>
            <el-form-item label-width="auto" prop="sms">
              <el-input v-model="checkForm.sms" class="code" placeholder="请输入验证码" type="text" autocomplete="off" />
              <el-button class="sendCode" :disabled="sendPhoneCodeBtn.disabled" @click="sendPhoneCode">{{ sendPhoneCodeBtn.txt }}</el-button>
            </el-form-item>
            <el-form-item label-width="auto">
              <el-button class="login-submit" :loading="loading" @click="doCheck('checkForm')">提交</el-button>
              <router-link class="forgetPwd-txt" to="/forgetPwd">忘记密码？</router-link>
            </el-form-item>
          </el-form>
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
  name: 'Login',
  data() {
    return {
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      step: 'first',
      mobNo: '',
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur' }],
        password: [{ required: true, trigger: 'blur' }]
      },
      checkForm: {
        sms: ''
      },
      checkRules: {
        sms: [{ required: true, trigger: 'blur' }]
      },
      sendPhoneCodeBtn: {
        disabled: false,
        txt: 'send'
      }
    };
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect;
      },
      immediate: true
    }
  },
  methods: {
    countTime(object) {
      let count = 60;
      const timer = window.setInterval(() => {
        if (count > 0) {
          this[object].txt = count + 's';
          this[object].disabled = true;
          count--;
        } else {
          this[object].txt = 'send';
          this[object].disabled = false;
          window.clearInterval(timer);
        }
      }, 1000);
    },
    sendPhoneCode() {
      this.$http({
        url: '/common/login/sendSms',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          this.$message.success(res.message);
          this.countTime('sendPhoneCodeBtn');
        } else {
          this.$message.error(res.message);
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    },
    doLogin(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.$store.dispatch('user/login', this.loginForm).then(res => {
            if (res.code === 200) {
              this.$router.push({ path: this.redirect || '/manage' });
            } else if (res.code === 2001) {
              this.step = 'second';
              this.mobNo = res.object;
            } else {
              this.$message.error(res.message || 'Has Error');
            }
            this.loading = false;
          }).catch(() => {
            this.loading = false;
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    doCheck(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.loading = true;
          this.$store.dispatch('user/check', this.checkForm).then(res => {
            if (res.code === 200) {
              this.$router.push({ path: this.redirect || '/manage' });
            } else {
              this.$message.error(res.message || 'Has Error');
            }
            this.loading = false;
          }).catch(() => {
            this.loading = false;
          });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
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
      .login-title {
        text-align: center;
        color: #FF3E4D;
        margin-top: 50px;
        font-size: 24px;
      }
      .login-form {
        padding: 50px;
        .code {
          width: 195px;
          display: inline-block;
        }
        .sendCode {
          width: 95px;
          margin-left: 5px;
          display: inline-block;
          background-color: #FF3E4D;
          border-color: #FF3E4D;
          color: #FFFFFF;
          text-align: center;
        }
        .sendCode.is-disabled {
          opacity: 0.6;
        }
      }
      .login-submit {
        width: 100%;
        background-color: #FF3E4D;
        border-color: #FF3E4D;
        color: #FFFFFF;
      }
      .forgetPwd-txt {
        color: #999999;
        float: right;
        text-decoration: none;
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
