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
    <el-form v-if="type === 'mail'" ref="mailForm" :model="mailForm" :rules="mailRules" status-icon class="forgetPwd-form">
      <el-form-item prop="mail">
        <el-input v-model="mailForm.mail" :placeholder="$t('account.forget_pwd.please_enter_mail')" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <div id="mailCaptcha"></div>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" :disabled="submitMailBtnDisabled" @click="sendMailCode('mailForm')"><!--提交-->{{ $t('account.forget_pwd.submit') }}</el-button>
      </el-form-item>
    </el-form>
    <el-form v-if="type === 'phone'" ref="phoneForm" :model="phoneForm" :rules="phoneRules" status-icon class="forgetPwd-form">
      <el-form-item>
        <el-select v-model="phoneForm.region" class="region" value="" :filterable="true" :no-match-text="$t('account.forget_pwd.no_data')">
          <el-option v-for="data in regionList" :key="data.id" :value="data.lCode" :label="'+'+data.lCode">
            <span style="float: left">+{{ data.lCode }}</span>
            <span v-if="$i18n.locale === 'en_US'" style="float: right;">{{ data.enName }}</span>
            <span v-else style="float: right;">{{ data.cnName }}</span>
          </el-option>
        </el-select>
        <el-form-item prop="phone" style="display:inline-block;">
          <el-input v-model="phoneForm.phone" class="phone" :placeholder="$t('account.forget_pwd.please_enter_phone')" type="text" autocomplete="off" />
        </el-form-item>
      </el-form-item>
      <el-form-item>
        <div id="phoneCaptcha"></div>
      </el-form-item>
      <el-form-item prop="authCode">
        <el-input v-model="phoneForm.authCode" class="code" :placeholder="$t('account.forget_pwd.please_enter_code')" type="text" autocomplete="off" />
        <el-button class="sendCode" :disabled="sendPhoneCodeBtn.disabled" @click="sendPhoneCode('phoneForm')">
          <span v-if="sendPhoneCodeBtn.txt === 'send'"><!--发送-->{{ $t('account.manage.account.' + sendPhoneCodeBtn.txt) }}</span>
          <span v-else>{{ sendPhoneCodeBtn.txt }}</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" :disabled="submitMailBtnDisabled" @click="doPhoneVerify('phoneForm')"><!--提交-->{{ $t('account.forget_pwd.submit') }}</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
export default {
  name: 'StepTwo',
  props: {
    type: {
      type: String,
      default: 'mail'
    }
  },
  data() {
    return {
      submitMailBtnDisabled: true,
      mailForm: {
        csessionid: '',
        mail: '',
        scene: '',
        sig: '',
        token: ''
      },
      mailRules: {
        mail: [{ required: true, trigger: 'blur' }]
      },
      sendPhoneCodeBtn: {
        disabled: true,
        txt: 'send'
      },
      phoneForm: {
        csessionid: '',
        region: '86',
        phone: '',
        scene: '',
        sig: '',
        token: '',
        authCode: '',
        authType: '',
        object: ''
      },
      phoneRules: {
        phone: [{ required: true, trigger: 'blur' }]
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
  mounted: function() {
    if (this.type === 'mail') {
      this.mailCaptchaInit();
    }
    if (this.type === 'phone') {
      this.phoneCaptchaInit();
      this.regionListInit();
    }
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function() {
      history.pushState(null, null, document.URL);
    });
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
    regionListInit() {
      this.$http({
        url: '/common/region/findAll',
        method: 'get'
      }).then(res => {
        if (res.code === 200) {
          this.regionList = res.object;
        } else {
          this.$message.error(res.message);
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    },
    mailCaptchaInit() {
      const _this = this;
      const ncMail_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncMail_appkey = 'FFFF0N00000000007E55';
      const ncMail_scene = 'nc_register';
      const NC_MAIL_OPT =
        {
          renderTo: '#mailCaptcha',
          appkey: ncMail_appkey,
          scene: ncMail_scene,
          token: ncMail_token,
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
            _this.mailForm = {
              csessionid: data.csessionid,
              mail: _this.mailForm.mail,
              scene: ncMail_scene,
              sig: data.sig,
              token: ncMail_token
            };
            _this.submitMailBtnDisabled = false;
          }
        };
      // eslint-disable-next-line new-cap,no-undef
      const nc = new noCaptcha(NC_MAIL_OPT);
      nc.upLang('cn', {
        _startTEXT: '请按住滑块，拖动到最右边',
        _yesTEXT: '验证通过',
        _error300: '哎呀，出错了，单击<a href="javascript:__nc.reset()">刷新</a>再来一次',
        _errorNetwork: '网络不给力，请<a href="javascript:__nc.reset()">单击刷新</a>'
      });
    },
    sendMailCode(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitMailBtnDisabled = true;
          this.$http({
            url: '/common/forgetPass/sendMail',
            method: 'post',
            params: this.mailForm
          }).then(res => {
            if (res.code === 200) {
              this.$router.push('/forgetPwd/stepMail/' + this.mailForm.mail);
            } else {
              this.$message.error(res.message);
              this.mailCaptchaInit();
            }
            this.submitMailBtnDisabled = false;
          }).catch(error => {
            this.$message.error(error || 'Has Error');
          });
        }
      });
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
            _this.phoneForm = {
              csessionid: data.csessionid,
              region: _this.phoneForm.region,
              phone: _this.phoneForm.phone,
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
    sendPhoneCode(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/common/forgetPass/sendSMS',
            method: 'post',
            params: this.phoneForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.countTime('sendPhoneCodeBtn');
              this.submitMailBtnDisabled = false;
            } else {
              this.$message.error(res.message);
              this.sendPhoneCodeBtn.disabled = true;
              this.phoneCaptchaInit();
            }
          }).catch(error => {
            this.$message.error(error || 'Has Error');
          });
        }
      });
    },
    doPhoneVerify(formName) {
      this.phoneForm = {
        authCode: this.phoneForm.authCode,
        authType: false,
        phone: this.phoneForm.phone,
        region: this.phoneForm.region,
        object: this.phoneForm.phone
      };
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitMailBtnDisabled = true;
          this.$http({
            url: '/forgetPass/verify',
            method: 'post',
            params: this.phoneForm
          }).then(res => {
            if (res.code === 200) {
              this.$router.push('/forgetPwd/stepThree/' + res.object);
            } else {
              this.$message.error(res.message);
            }
            this.submitMailBtnDisabled = false;
          }).catch(error => {
            this.$message.error(error || 'Has Error');
          });
        }
      });
    }
  }
};
</script>

<style lang="scss">
  .forgetPwd-form {
    .nc_scale {
      width: 400px;
    }
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
    margin: 35px auto;
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
  }
</style>
