<template>
  <div class="app-container">
    <div class="account-wrapper">
      <span class="account-title"><!--账户信息-->{{ $t('account.manage.account.account_info') }}</span>
      <div class="account-content">
        <span class="account-label"><!--账户名-->{{ $t('account.manage.account.username') }}</span>
        <span class="account-value">{{ currentUserInfo.accountName }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--客户名称-->{{ $t('account.manage.account.customer_name') }}</span>
        <span class="account-value">{{ currentUserInfo.name }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--盈透账号-->{{ $t('account.manage.account.IB_account') }}</span>
        <span class="account-value">{{ currentUserInfo.ibAccountCode }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--客户类型-->{{ $t('account.manage.account.customer_type') }}</span>
        <span class="account-value">{{ dictFormat('accountType', currentUserInfo.accountType) }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--账户类型-->{{ $t('account.manage.account.account_type') }}</span>
        <span class="account-value">{{ dictFormat('accountPortfolioType', currentUserInfo.accountPortfolioType) }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--基础货币-->{{ $t('account.manage.account.base_currency') }}</span>
        <span class="account-value">{{ dictFormat('baseCurrency', currentUserInfo.baseCurrency) }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--联系方式-->{{ $t('account.manage.account.phone_number') }}</span>
        <span v-if="currentUserInfo.region" style="color: #000;">{{ '+' + currentUserInfo.region }}</span>
        <span class="account-value"> {{ currentUserInfo.mobile }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--联系地址-->{{ $t('account.manage.account.contact_address') }}</span>
        <span class="account-value">{{ currentUserInfo.country }} {{ currentUserInfo.rovince }} {{ currentUserInfo.city }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--邮箱地址-->{{ $t('account.manage.account.email_address') }}</span>
        <span class="account-value">{{ currentUserInfo.email }}</span>
      </div>
    </div>
    <div class="account-wrapper">
      <span class="account-title"><!--账户安全-->{{ $t('account.manage.account.account_security') }}</span>
      <div class="account-content">
        <span class="account-label"><!--登录手机验证-->{{ $t('account.manage.account.mobile_verify') }}</span>
        <span class="account-value1"><!--登录账号后将会发送验证码给您进行二次验证-->{{ $t('account.manage.account.mobile_verify_txt') }}</span>
        <span v-if="currentUserInfo.securityPolicy === 0" class="account-button" @click="dialogShow('loginAuth')"><!--开启-->{{ $t('account.manage.account.open') }}</span>
        <span v-if="currentUserInfo.securityPolicy === 1" class="disabled-button"><!--已开启-->{{ $t('account.manage.account.enabled') }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--邮箱账号-->{{ $t('account.manage.account.email') }}</span>
        <span class="account-value1">{{ currentUserInfo.email }}</span>
        <span class="account-button" @click="dialogShow('changeMail')"><!--更换-->{{ $t('account.manage.account.change') }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--手机号-->{{ $t('account.manage.account.mobile_number') }}</span>
        <span class="account-value1">{{ currentUserInfo.mobile }}</span>
        <span class="account-button" @click="dialogShow('changePhone')"><!--更换-->{{ $t('account.manage.account.change') }}</span>
      </div>
      <div class="account-content">
        <span class="account-label"><!--登录密码-->{{ $t('account.manage.account.password') }}</span>
        <span class="account-value1">**********</span>
        <span class="account-button" @click="dialogShow('changePwd')"><!--更换-->{{ $t('account.manage.account.change') }}</span>
      </div>
    </div>
    <login-auth ref="loginAuth" :mobile="currentUserInfo.mobile" @updateData="doInitData"></login-auth>
    <change-mail ref="changeMail" :mail="currentUserInfo.email" @updateData="doInitData"></change-mail>
    <change-phone ref="changePhone" :mobile="currentUserInfo.mobile" @updateData="doInitData"></change-phone>
    <change-pwd ref="changePwd" @updateData="doInitData"></change-pwd>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import LoginAuth from './module/loginAuth';
import ChangeMail from './module/changeMail';
import ChangePhone from './module/changePhone';
import ChangePwd from './module/changePwd';

export default {
  name: 'Account',
  components: {
    LoginAuth,
    ChangeMail,
    ChangePhone,
    ChangePwd
  },
  data() {
    return {
      type: '',
      userInfo: '',
      currentUserInfo: [],
      baseCurrencyData: [],
      accountTypeData: [],
      accountPortfolioTypeData: []
    };
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  mounted() {
    this.doInitData();
  },
  methods: {
    dictFormat: function(dictCode, value) {
      const dictArray = this[dictCode + 'Data'];
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === value) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    dialogShow(type) {
      if (type === 'loginAuth') {
        this.$refs.loginAuth.isShow = true;
      }
      if (type === 'changeMail') {
        this.$refs.changeMail.isShow = true;
      }
      if (type === 'changePhone') {
        this.$refs.changePhone.isShow = true;
      }
      if (type === 'changePwd') {
        this.$refs.changePwd.isShow = true;
      }
    },
    doInitData() {
      this.$http({
        url: '/account/currentUserInfo',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          this.currentUserInfo = res.object;
        } else {
          this.$message.error(res.message);
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'baseCurrency',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.baseCurrencyData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'accountType',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.accountTypeData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'accountPortfolioType',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.accountPortfolioTypeData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .account-wrapper {
      padding: 25px 40px;
      background-color: #FFFFFF;
      margin-bottom: 20px;
      .account-title {
        display: inline-block;
        font-size: 18px;
        margin-left: -40px;
        margin-bottom: 10px;
        padding-left: 38px;
        border-left: 2px solid;
      }
      .account-content {
        height: 25px;
        margin-top: 15px;
        color: #999999;
        .account-label {
          width: 150px;
          display: inline-block;
        }
        .account-value {
          width: 80%;
          display: inline-block;
          color: #434343;
        }
        .account-value1 {
          width: 400px;
          display: inline-block;
          color: #434343;
        }
        .account-button {
          width: 80px;
          height: 25px;
          line-height: 25px;
          display: inline-block;
          text-align: center;
          background: rgba(255,62,77,1);
          color: #FFF;
          border-radius: 5px;
          cursor: pointer;
        }
        .disabled-button {
          width: 80px;
          height: 25px;
          line-height: 25px;
          display: inline-block;
          text-align: center;
          background: #999999;
          color: #FFF;
          border-radius: 5px;
          cursor: default;
        }
      }
    }
  }
</style>
