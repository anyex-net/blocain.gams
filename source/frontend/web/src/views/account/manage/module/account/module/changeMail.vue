<template>
  <el-dialog :modal-append-to-body="false" :visible.sync="isShow" :close-on-click-modal="false" :close-on-press-escape="false" width="600px" @closed="step = 'first'">
    <el-form v-if="step === 'first'" ref="oldMailForm" :model="oldMailForm" :rules="mailRules" status-icon class="account-form">
      <div class="account-title"><!--验证旧邮箱-->{{ $t('account.manage.account.verify_old_mail_address') }}</div>
      <el-divider></el-divider>
      <el-form-item>
        <div class="account-readonly-item"><!--旧邮箱-->{{ $t('account.manage.account.old_mail_address') }}：{{ mail }}</div>
      </el-form-item>
      <el-form-item>
        <div id="oldMailCaptcha"></div>
      </el-form-item>
      <el-form-item prop="valiCode">
        <el-input v-model="oldMailForm.valiCode" class="code" :placeholder="$t('account.manage.account.please_enter_code')" type="text" autocomplete="off" />
        <el-button class="sendCode" :disabled="sendOldMailCodeBtn.disabled" @click="sendOldMailCode">
          <span v-if="sendOldMailCodeBtn.txt === 'send'"><!--发送-->{{ $t('account.manage.account.' + sendOldMailCodeBtn.txt) }}</span>
          <span v-else>{{ sendOldMailCodeBtn.txt }}</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" @click="doChangeMailFirst('oldMailForm')"><!--下一步-->{{ $t('account.manage.account.next') }}</el-button>
      </el-form-item>
    </el-form>
    <el-form v-if="step === 'second'" ref="newMailForm" :model="newMailForm" :rules="mailRules" status-icon class="account-form">
      <div class="account-title"><!--绑定新邮箱-->{{ $t('account.manage.account.bind_new_mail_address') }}</div>
      <el-divider></el-divider>
      <el-form-item prop="email">
        <el-input v-model="newMailForm.email" :placeholder="$t('account.manage.account.please_enter_new_mail')" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <div id="newMailCaptcha"></div>
      </el-form-item>
      <el-form-item prop="valiCode">
        <el-input v-model="newMailForm.valiCode" class="code" :placeholder="$t('account.manage.account.please_enter_code')" type="text" autocomplete="off" />
        <el-button class="sendCode" :disabled="sendNewMailCodeBtn.disabled" @click="sendNewMailCode">
          <span v-if="sendNewMailCodeBtn.txt === 'send'"><!--发送-->{{ $t('account.manage.account.' + sendNewMailCodeBtn.txt) }}</span>
          <span v-else>{{ sendNewMailCodeBtn.txt }}</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" @click="doChangeMailSecond('newMailForm')"><!--提交-->{{ $t('account.manage.account.submit') }}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: 'ChangeMail',
  props: {
    mail: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      isShow: false,
      step: 'first',
      oldMailForm: {
        csessionid: '',
        scene: '',
        sig: '',
        token: '',
        valiCode: ''
      },
      sendOldMailCodeBtn: {
        disabled: true,
        txt: 'send'
      },
      newMailForm: {
        csessionid: '',
        email: '',
        scene: '',
        sig: '',
        token: '',
        code: '',
        valiCode: ''
      },
      sendNewMailCodeBtn: {
        disabled: true,
        txt: 'send'
      },
      mailRules: {
        email: [{ required: true, trigger: 'blur' }],
        valiCode: [{ required: true, trigger: 'blur' }]
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
  watch: {
    isShow: function(val) {
      if (val === true) {
        this.$nextTick(function() {
          this.$refs.oldMailForm.resetFields();
          this.oldMailCaptchaInit();
        });
      }
    },
    step: function(val) {
      if (val === 'second') {
        this.$nextTick(function() {
          this.newMailCaptchaInit();
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
    oldMailCaptchaInit() {
      const _this = this;
      const ncMail_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncMail_appkey = 'FFFF0N00000000007E55';
      const ncMail_scene = 'nc_register';
      const NC_MAIL_OPT =
        {
          renderTo: '#oldMailCaptcha',
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
            _this.oldMailForm = {
              csessionid: data.csessionid,
              scene: ncMail_scene,
              sig: data.sig,
              token: ncMail_token
            };
            _this.sendOldMailCodeBtn.disabled = false;
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
    newMailCaptchaInit() {
      const _this = this;
      const ncMail_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncMail_appkey = 'FFFF0N00000000007E55';
      const ncMail_scene = 'nc_register';
      const NC_MAIL_OPT =
        {
          renderTo: '#newMailCaptcha',
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
            _this.newMailForm = {
              csessionid: data.csessionid,
              email: _this.newMailForm.email,
              scene: ncMail_scene,
              sig: data.sig,
              token: ncMail_token
            };
            _this.sendNewMailCodeBtn.disabled = false;
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
    sendOldMailCode() {
      this.$http({
        url: '/account/sendMail',
        method: 'post',
        params: this.oldMailForm
      }).then(res => {
        if (res.code === 200) {
          this.$message.success(res.message);
          this.countTime('sendOldMailCodeBtn');
        } else {
          this.$message.error(res.message);
          this.oldMailCaptchaInit();
          this.sendOldMailCodeBtn.disabled = true;
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    },
    sendNewMailCode() {
      if (this.newMailForm.email === '' || this.newMailForm.email === null) {
        this.$message.error('E-mail can not be empty');
      } else {
        this.$http({
          url: '/account/sendChangeMail',
          method: 'post',
          params: this.newMailForm
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.countTime('sendNewMailCodeBtn');
          } else {
            this.$message.error(res.message);
            this.newMailCaptchaInit();
            this.sendNewMailCodeBtn.disabled = true;
          }
        }).catch(error => {
          this.$message.error(error || 'Has Error');
        });
      }
    },
    doChangeMailFirst(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/account/checkMail',
            method: 'post',
            params: this.oldMailForm
          }).then(res => {
            if (res.code === 200) {
              this.step = 'second';
            } else {
              this.$message.error(res.message);
            }
          }).catch(error => {
            this.$message.error(error || 'Has Error');
          });
        }
      });
    },
    doChangeMailSecond(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/account/changeMail',
            method: 'post',
            params: this.newMailForm
          }).then(res => {
            if (res.code === 200) {
              this.$emit('updateData');
              this.step = 'first';
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
