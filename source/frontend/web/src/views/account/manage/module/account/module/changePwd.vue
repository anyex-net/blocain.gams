<template>
  <el-dialog :modal-append-to-body="false" :visible.sync="isShow" :close-on-click-modal="false" :close-on-press-escape="false" width="600px">
    <el-form ref="passwordForm" :model="passwordForm" :rules="passwordRules" status-icon class="account-form">
      <div class="account-title"><!--修改密码-->{{ $t('account.manage.account.change_password') }}</div>
      <el-divider></el-divider>
      <el-form-item prop="oldPwd">
        <el-input v-model="passwordForm.oldPwd" :placeholder="$t('account.manage.account.please_enter_old_pwd')" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="newPwd">
        <el-input v-model="passwordForm.newPwd" :placeholder="$t('account.manage.account.please_enter_new_pwd')" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item prop="confirmPwd">
        <el-input v-model="passwordForm.confirmPwd" :placeholder="$t('account.manage.account.please_confirm_new_pwd')" type="password" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <div id="pwdCaptcha"></div>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" :disabled="isDisabled" @click="doChangePwd('passwordForm')"><!--提交-->{{ $t('account.manage.account.submit') }}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: 'ChangePwd',
  data() {
    const validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please enter password'));
      } else if (value.length > 16 || value.length < 8) {
        callback(new Error('Password must be 8-16 characters long'));
      } else if (value.indexOf(' ') > -1) {
        callback(new Error('Password cannot contain spaces'));
      } else {
        this.$refs.passwordForm.validateField('confirmPwd');
        callback();
      }
    };
    const validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please confirm password'));
      } else if (value !== this.passwordForm.newPwd) {
        callback(new Error('Entered passwords differ'));
      } else {
        callback();
      }
    };
    return {
      isShow: false,
      isDisabled: true,
      passwordForm: {
        oldPwd: '',
        newPwd: '',
        confirmPwd: '',
        csessionid: '',
        scene: '',
        sig: '',
        token: ''
      },
      passwordRules: {
        oldPwd: [{ required: true, trigger: 'blur' }],
        newPwd: [{ validator: validatePass, trigger: 'blur' }],
        confirmPwd: [{ validator: validatePass2, trigger: 'blur' }]
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
          this.$refs.passwordForm.resetFields();
          this.pwdCaptchaInit();
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
    pwdCaptchaInit() {
      const _this = this;
      const ncPwd_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncPwd_appkey = 'FFFF0N00000000007E55';
      const ncPwd_scene = 'nc_register';
      const NC_PWD_OPT =
        {
          renderTo: '#pwdCaptcha',
          appkey: ncPwd_appkey,
          scene: ncPwd_scene,
          token: ncPwd_token,
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
            _this.passwordForm = {
              csessionid: data.csessionid,
              scene: ncPwd_scene,
              sig: data.sig,
              token: ncPwd_token,
              oldPwd: _this.passwordForm.oldPwd,
              newPwd: _this.passwordForm.newPwd,
              confirmPwd: _this.passwordForm.confirmPwd
            };
            _this.isDisabled = false;
          }
        };
      // eslint-disable-next-line new-cap,no-undef
      const nc = new noCaptcha(NC_PWD_OPT);
      nc.upLang('cn', {
        _startTEXT: '请按住滑块，拖动到最右边',
        _yesTEXT: '验证通过',
        _error300: '哎呀，出错了，单击<a href="javascript:__nc.reset()">刷新</a>再来一次',
        _errorNetwork: '网络不给力，请<a href="javascript:__nc.reset()">单击刷新</a>'
      });
    },
    doChangePwd(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/account/changePwd',
            method: 'post',
            params: this.passwordForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.$emit('updateData');
              this.isShow = false;
            } else {
              this.$message.error(res.message);
              this.pwdCaptchaInit();
              this.isDisabled = true;
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
    .account-title {
      font-size: 22px;
      color: #434343;
      text-align: center;
      margin-bottom: 30px;
    }
  }
</style>
