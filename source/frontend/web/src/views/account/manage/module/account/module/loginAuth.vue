<template>
  <el-dialog :modal-append-to-body="false" :visible.sync="isShow" :close-on-click-modal="false" :close-on-press-escape="false" width="600px">
    <el-form ref="loginAuthForm" :model="loginAuthForm" :rules="authRules" status-icon class="account-form">
      <div class="account-title"><!--登录验证-->{{ $t('account.manage.account.mobile_verify') }}</div>
      <el-divider></el-divider>
      <el-form-item>
        <div class="account-readonly-item"><!--手机号码-->{{ $t('account.manage.account.phone_number') }}：{{ mobile }}</div>
      </el-form-item>
      <el-form-item>
        <div id="phoneCaptcha"></div>
      </el-form-item>
      <el-form-item prop="valiCode">
        <el-input v-model="loginAuthForm.valiCode" class="code" :placeholder="$t('account.manage.account.please_enter_code')" type="text" autocomplete="off" />
        <el-button class="sendCode" :disabled="sendPhoneCodeBtn.disabled" @click="sendPhoneCode">
          <span v-if="sendPhoneCodeBtn.txt === 'send'"><!--发送-->{{ $t('account.manage.account.' + sendPhoneCodeBtn.txt) }}</span>
          <span v-else>{{ sendPhoneCodeBtn.txt }}</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" @click="doLoginAuth('loginAuthForm')"><!--提交-->{{ $t('account.manage.account.submit') }}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: 'LoginAuth',
  props: {
    mobile: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      isShow: false,
      loginAuthForm: {
        csessionid: '',
        scene: '',
        sig: '',
        token: '',
        valiCode: ''
      },
      sendPhoneCodeBtn: {
        disabled: true,
        txt: 'send'
      },
      authRules: {
        phone: [{ required: true, trigger: 'blur' }],
        valiCode: [{ required: true, trigger: 'blur' }]
      },
      regionList: ''
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
  watch: {
    isShow: function(val) {
      if (val === true) {
        this.$nextTick(function() {
          this.phoneCaptchaInit();
        });
      }
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
    phoneCaptchaInit() {
      const _this = this;
      const ncPhone_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncPhone_appkey = 'FFFF0N00000000007E55';
      const ncPhone_scene = 'nc_register';
      const NC_PHONE_OPT =
        {
          renderTo: '#phoneCaptcha',
          appkey: ncPhone_appkey,
          scene: ncPhone_scene,
          token: ncPhone_token,
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
            _this.loginAuthForm = {
              csessionid: data.csessionid,
              scene: ncPhone_scene,
              sig: data.sig,
              token: ncPhone_token
            };
            _this.sendPhoneCodeBtn.disabled = false;
          }
        };
      // eslint-disable-next-line new-cap,no-undef
      const nc = new noCaptcha(NC_PHONE_OPT);
      nc.upLang('cn', {
        _startTEXT: '请按住滑块，拖动到最右边',
        _yesTEXT: '验证通过',
        _error300: '哎呀，出错了，单击<a href="javascript:__nc.reset()">刷新</a>再来一次',
        _errorNetwork: '网络不给力，请<a href="javascript:__nc.reset()">单击刷新</a>'
      });
    },
    sendPhoneCode() {
      this.$http({
        url: '/account/sendPhoneSMS',
        method: 'post',
        params: this.loginAuthForm
      }).then(res => {
        if (res.code === 200) {
          this.$message.success(res.message);
          this.countTime('sendPhoneCodeBtn');
        } else {
          this.$message.error(res.message);
          this.phoneCaptchaInit();
          this.sendPhoneCodeBtn.disabled = true;
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    },
    doLoginAuth(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/account/enablePhone',
            method: 'post',
            params: this.loginAuthForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.$emit('updateData');
              this.isShow = false;
            } else {
              this.$message.error(res.message);
            }
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
  .account-form .nc_scale{
    width: 400px;
  }
</style>

<style lang="scss" scoped>
  .account-form {
    width: 400px;
    margin: 0 auto;
    .submit {
      width: 100%;
      background-color: #FF3E4D;
      border-color: #FF3E4D;
      color: #FFFFFF;
    }
    .submit.is-disabled {
      opacity: 0.6;
    }
    .region {
      width: 100px;
      display: inline-block;
    }
    .phone {
      width: 305px;
      margin-left: -10px;
      display: inline-block;
    }
    .code {
      width: 260px;
      display: inline-block;
    }
    .sendCode {
      width: 130px;
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
    .account-title {
      font-size: 22px;
      color: #434343;
      text-align: center;
      margin-bottom: 30px;
    }
    .account-readonly-item {
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
  }
</style>
