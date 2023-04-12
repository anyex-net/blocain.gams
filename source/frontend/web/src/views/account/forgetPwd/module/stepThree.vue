<template>
  <div>
    <el-steps class="step-wrapper" :active="2" finish-status="success" simple>
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
    <el-form ref="setPwdForm" :model="setPwdForm" :rules="setPwdRules" status-icon class="forgetPwd-form">
      <el-form-item prop="newPwd">
        <el-input v-model="setPwdForm.newPwd" :placeholder="$t('account.forget_pwd.new_pwd')" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="confirmPwd">
        <el-input v-model="setPwdForm.confirmPwd" :placeholder="$t('account.forget_pwd.confirm_pwd')" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <div id="setPwdCaptcha"></div>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" :disabled="setPwdBtnDisabled" @click="doSubmit('setPwdForm')"><!--提交-->{{ $t('account.forget_pwd.submit') }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: 'StepThree',
  props: {
    authCode: {
      type: String,
      default: ''
    }
  },
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please enter password'));
      } else if (value.length > 16 || value.length < 8) {
        callback(new Error('Password must be 8-16 characters long'));
      } else if (value.indexOf(' ') > -1) {
        callback(new Error('Password cannot contain spaces'));
      } else {
        this.$refs.setPwdForm.validateField('confirmPwd');
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please confirm password'));
      } else if (value !== this.setPwdForm.newPwd) {
        callback(new Error('Entered passwords differ'));
      } else {
        callback();
      }
    };
    return {
      setPwdBtnDisabled: true,
      setPwdForm: {
        csessionid: '',
        scene: '',
        sig: '',
        token: '',
        authCode: this.authCode,
        newPwd: '',
        confirmPwd: ''
      },
      setPwdRules: {
        newPwd: [
          { validator: validatePass, trigger: 'blur' }
        ],
        confirmPwd: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    };
  },
  computed: {
    ncLocale: function() {
      let locale = this.$i18n.locale;
      if (locale === 'zh_CN') {
        locale = 'cn';
      }
      if (locale === 'zh_HK') {
        locale = 'tw';
      }
      if (locale === 'en_US') {
        locale = 'en';
      }
      return locale;
    }
  },
  mounted: function() {
    this.setCaptchaInit();
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function() {
      history.pushState(null, null, document.URL);
    });
  },
  methods: {
    setCaptchaInit() {
      const _this = this;
      const nc_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const nc_appkey = 'FFFF0N00000000007E55';
      const nc_scene = 'nc_register';
      const NC_OPT =
        {
          renderTo: '#setPwdCaptcha',
          appkey: nc_appkey,
          scene: nc_scene,
          token: nc_token,
          customWidth: 400,
          is_Opt: 0,
          language: _this.ncLocale,
          isEnabled: true,
          timeout: 3000,
          times: 5,
          apimap: {
            // 'analyze': '//a.com/nocaptcha/analyze.jsonp',
            // 'get_captcha': '//b.com/get_captcha/ver3',
            // 'get_captcha': '//pin3.aliyun.com/get_captcha/ver3'
            // 'get_img': '//c.com/get_img',
            // 'checkcode': '//d.com/captcha/checkcode.jsonp',
            // 'umid_Url': '//e.com/security/umscript/3.2.1/um.js',
            // 'uab_Url': '//aeu.alicdn.com/js/uac/909.js',
            // 'umid_serUrl': 'https://g.com/service/um.json'
          },
          callback: function(data) {
            _this.setPwdForm = {
              csessionid: data.csessionid,
              scene: nc_scene,
              sig: data.sig,
              token: nc_token,
              authCode: _this.setPwdForm.authCode,
              newPwd: _this.setPwdForm.newPwd,
              confirmPwd: _this.setPwdForm.confirmPwd
            };
            _this.setPwdBtnDisabled = false;
          }
        };
      // eslint-disable-next-line new-cap,no-undef
      const nc = new noCaptcha(NC_OPT);
      nc.upLang('cn', {
        _startTEXT: '请按住滑块，拖动到最右边',
        _yesTEXT: '验证通过',
        _error300: '哎呀，出错了，单击<a href="javascript:__nc.reset()">刷新</a>再来一次',
        _errorNetwork: '网络不给力，请<a href="javascript:__nc.reset()">单击刷新</a>'
      });
    },
    doSubmit(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.setPwdBtnDisabled = true;
          this.$http({
            url: '/forgetPass/resetPwd',
            method: 'post',
            params: this.setPwdForm
          }).then(res => {
            if (res.code === 200) {
              this.$router.push('/forgetPwd/stepFour');
            } else {
              this.$message.error(res.message);
            }
            this.setPwdBtnDisabled = false;
          }).catch(error => {
            this.$message.error(error || 'Has Error');
          });
        }
      });
    }
  }
};
</script>

<style>
  .forgetPwd-form .nc_scale{
    width: 400px;
  }
</style>

<style lang="scss" scoped>
  .step-wrapper {
    padding: 13px 4%;
    background-color: #FFFFFF;
    margin-top: 10px;
    border-bottom: 1px solid #E4E4E4;
  }
  .forgetPwd-form {
    width: 400px;
    margin: 30px auto;
    .submit {
      width: 100%;
      background-color: #FF3E4D;
      border-color: #FF3E4D;
      color: #FFFFFF;
    }
    .submit.is-disabled {
      opacity: 0.6;
    }
  }
</style>
