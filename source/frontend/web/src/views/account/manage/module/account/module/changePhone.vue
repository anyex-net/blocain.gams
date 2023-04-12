<template>
  <el-dialog :modal-append-to-body="false" :visible.sync="isShow" :close-on-click-modal="false" :close-on-press-escape="false" width="600px" @closed="step = 'first'">
    <el-form v-if="step === 'first'" ref="oldPhoneForm" :model="oldPhoneForm" :rules="phoneRules" status-icon class="account-form">
      <div class="account-title"><!--验证旧手机-->{{ $t('account.manage.account.verify_old_phone_number') }}</div>
      <el-divider></el-divider>
      <el-form-item>
        <div class="account-readonly-item"><!--旧手机-->{{ $t('account.manage.account.old_phone_number') }}：{{ mobile }}</div>
      </el-form-item>
      <el-form-item>
        <div id="oldPhoneCaptcha"></div>
      </el-form-item>
      <el-form-item prop="valiCode">
        <el-input v-model="oldPhoneForm.valiCode" class="code" :placeholder="$t('account.manage.account.please_enter_code')" type="text" autocomplete="off" />
        <el-button class="sendCode" :disabled="sendOldPhoneCodeBtn.disabled" @click="sendOldPhoneCode">
          <span v-if="sendOldPhoneCodeBtn.txt === 'send'"><!--发送-->{{ $t('account.manage.account.' + sendOldPhoneCodeBtn.txt) }}</span>
          <span v-else>{{ sendOldPhoneCodeBtn.txt }}</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" @click="doChangePhoneFirst('oldPhoneForm')"><!--下一步-->{{ $t('account.manage.account.next') }}</el-button>
      </el-form-item>
    </el-form>
    <el-form v-if="step === 'second'" ref="newPhoneForm" :model="newPhoneForm" :rules="phoneRules" status-icon class="account-form">
      <div class="account-title"><!--绑定新手机-->{{ $t('account.manage.account.bind_new_phone_number') }}</div>
      <el-divider></el-divider>
      <el-select v-model="newPhoneForm.region" class="region" value="" :filterable="true" :no-match-text="$t('account.manage.no_data')">
        <el-option v-for="data in regionList" :key="data.id" :value="data.lCode" :label="'+'+data.lCode">
          <span style="float: left">+{{ data.lCode }}</span>
          <span v-if="$i18n.locale === 'en_US'" style="float: right;">{{ data.enName }}</span>
          <span v-else style="float: right;">{{ data.cnName }}</span>
        </el-option>
      </el-select>
      <el-form-item prop="phone" style="display:inline-block;">
        <el-input v-model="newPhoneForm.phone" class="phone" :placeholder="$t('account.manage.account.please_enter_phone')" type="text" autocomplete="off" />
      </el-form-item>
      <el-form-item>
        <div id="newPhoneCaptcha"></div>
      </el-form-item>
      <el-form-item prop="valiCode">
        <el-input v-model="newPhoneForm.valiCode" class="code" :placeholder="$t('account.manage.account.please_enter_code')" type="text" autocomplete="off" />
        <el-button class="sendCode" :disabled="sendNewPhoneCodeBtn.disabled" @click="sendNewPhoneCode">
          <span v-if="sendNewPhoneCodeBtn.txt === 'send'"><!--发送-->{{ $t('account.manage.account.' + sendNewPhoneCodeBtn.txt) }}</span>
          <span v-else>{{ sendNewPhoneCodeBtn.txt }}</span>
        </el-button>
      </el-form-item>
      <el-form-item>
        <el-button class="submit" @click="doChangePhoneSecond('newPhoneForm')"><!--提交-->{{ $t('account.manage.account.submit') }}</el-button>
      </el-form-item>
    </el-form>
  </el-dialog>
</template>

<script>
export default {
  name: 'ChangePhone',
  props: {
    mobile: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      isShow: false,
      step: 'first',
      oldPhoneForm: {
        csessionid: '',
        scene: '',
        sig: '',
        token: '',
        valiCode: ''
      },
      sendOldPhoneCodeBtn: {
        disabled: true,
        txt: 'send'
      },
      newPhoneForm: {
        csessionid: '',
        scene: '',
        sig: '',
        token: '',
        phone: '',
        region: '86',
        valiCode: ''
      },
      sendNewPhoneCodeBtn: {
        disabled: true,
        txt: 'send'
      },
      phoneRules: {
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
          this.$refs.oldPhoneForm.resetFields();
          this.oldPhoneCaptchaInit();
          this.regionListInit();
        });
      }
    },
    step: function(val) {
      if (val === 'second') {
        this.$nextTick(function() {
          this.newPhoneCaptchaInit();
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
    oldPhoneCaptchaInit() {
      const _this = this;
      const ncPhone_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncPhone_appkey = 'FFFF0N00000000007E55';
      const ncPhone_scene = 'nc_register';
      const NC_PHONE_OPT =
        {
          renderTo: '#oldPhoneCaptcha',
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
            _this.oldPhoneForm = {
              csessionid: data.csessionid,
              scene: ncPhone_scene,
              sig: data.sig,
              token: ncPhone_token
            };
            _this.sendOldPhoneCodeBtn.disabled = false;
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
    newPhoneCaptchaInit() {
      const _this = this;
      const ncPhone_token = ['FFFF0N00000000007E55', (new Date()).getTime(), Math.random()].join(':');
      const ncPhone_appkey = 'FFFF0N00000000007E55';
      const ncPhone_scene = 'nc_register';
      const NC_PHONE_OPT =
        {
          renderTo: '#newPhoneCaptcha',
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
            _this.newPhoneForm = {
              csessionid: data.csessionid,
              scene: ncPhone_scene,
              sig: data.sig,
              token: ncPhone_token,
              phone: _this.newPhoneForm.phone,
              region: _this.newPhoneForm.region
            };
            _this.sendNewPhoneCodeBtn.disabled = false;
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
    sendOldPhoneCode() {
      this.$http({
        url: '/account/sendBindPhoneSMS',
        method: 'post',
        params: this.oldPhoneForm
      }).then(res => {
        if (res.code === 200) {
          this.$message.success(res.message);
          this.countTime('sendOldPhoneCodeBtn');
        } else {
          this.$message.error(res.message);
          this.oldPhoneCaptchaInit();
          this.sendOldPhoneCodeBtn.disabled = true;
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    },
    sendNewPhoneCode() {
      if (this.newPhoneForm.phone === '' || this.newPhoneForm.phone === null) {
        this.$message.error('Phone can not be empty');
      } else {
        this.$http({
          url: '/account/sendChangePhoneSMS',
          method: 'post',
          params: this.newPhoneForm
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.countTime('sendNewPhoneCodeBtn');
          } else {
            this.$message.error(res.message);
            this.newPhoneCaptchaInit();
            this.sendNewPhoneCodeBtn.disabled = true;
          }
        }).catch(error => {
          this.$message.error(error || 'Has Error');
        });
      }
    },
    doChangePhoneFirst(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/account/checkBindPhoneSMS',
            method: 'post',
            params: this.oldPhoneForm
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
    doChangePhoneSecond(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$http({
            url: '/account/changePhone',
            method: 'post',
            params: this.newPhoneForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
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

<style lang="scss">
  .account-form {
    .nc_scale{
      width: 400px;
    }
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
