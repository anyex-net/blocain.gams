<template>
  <div class="app-header">
    <router-link to="/index">
      <img src="@/assets/images/common/logo.png" class="logo">
    </router-link>
    <el-menu
      :default-active="$route.matched[1].path"
      :class="[$i18n.locale === 'en_US' ? 'menu1' : 'menu']"
      mode="horizontal"
      background-color="#FFFFFF"
      text-color="#434343"
      active-text-color="#FF4057"
      :router="true"
    >
      <el-menu-item index="/index"><!--首页-->{{ $t('common.header.index') }}</el-menu-item>
      <el-menu-item index="/about"><!--关于国瑞-->{{ $t('common.header.about') }}</el-menu-item>
      <!-- <el-menu-item index="/express">市场快讯{{ $t('common.header.new') }}</el-menu-item> -->
      <el-menu-item index="/fee"><!--费用-->{{ $t('common.header.fee') }}</el-menu-item>
      <el-menu-item index="/download"><!--下载-->{{ $t('common.header.download') }}</el-menu-item>
      <el-menu-item index="/product"><!--产品-->{{ $t('common.header.product') }}</el-menu-item>
      <!-- <el-menu-item index="/help">帮助{{ $t('common.header.help') }}</el-menu-item> -->
      <router-link v-if="!name" to="/register">
        <el-button class="register-button"><!--开立账户-->{{ $t('common.header.open_account') }}</el-button>
      </router-link>
      <a v-else @click="openAccount()">
        <el-button class="register-button"><!--开立账户-->{{ $t('common.header.open_account') }}</el-button>
      </a>

      <el-dropdown class="drop" trigger="click" style="outline:none;" @command="changeLocale">
        <span class="el-dropdown-link">
          <span v-if="$i18n.locale === 'zh_CN'">简体中文</span>
          <span v-if="$i18n.locale === 'zh_HK'">繁体中文</span>
          <span v-if="$i18n.locale === 'en_US'">English</span>
          <i class="el-icon-arrow-down el-icon--right" />
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item command="zh_CN">简体中文</el-dropdown-item>
          <!--<el-dropdown-item command="zh_HK">繁体中文</el-dropdown-item>-->
          <el-dropdown-item command="en_US">English</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <!--登录-->
      <!-- <router-link v-if="!name" to="/login" style="outline:none;">
        <el-button class="login-button">{{ $t('common.header.login') }}</el-button>
      </router-link> -->
      <el-dropdown v-if="!name" class="drop" trigger="click">
        <span class="el-dropdown-link">
          <el-button class="login-button">{{ $t('common.header.login') }}</el-button>
        </span>
        <el-dropdown-menu slot="dropdown">
          <a href="https://www.clientam.com/sso/Login?partnerID=MOF2020" target="_blank">
            <el-dropdown-item>
              <!--账户管理-->{{ $t('common.header.my_account') }}
            </el-dropdown-item>
          </a>
          <el-dropdown-item divided>
            <router-link v-if="!name" to="/login" style="outline:none;">
              <span style="display:block;" @click="logout"><!--继续完成申请-->{{ $t('common.header.finish_an_application') }}</span>
            </router-link>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-dropdown v-else class="drop" trigger="click">
        <span class="el-dropdown-link">
          <span class="el-icon-user-solid" />
          <span class="username">{{ name }}</span>
        </span>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>
              <!--网站首页-->{{ $t('common.header.home') }}
            </el-dropdown-item>
          </router-link>
          <router-link to="/manage">
            <el-dropdown-item>
              <!--账户管理-->{{ $t('common.header.my_account') }}
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span style="display:block;" @click="logout"><!--退出-->{{ $t('common.header.logout') }}</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </el-menu>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import cookies from 'js-cookie';

export default {
  name: 'AppHeader',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  methods: {
    openAccount() {
      this.$store.dispatch('user/getInfo').then(res => {
        if (res.code === 200) {
          cookies.set('locale', this.$i18n.locale);
          if (this.$store.state.user.applyData.xinxi.accountType === 'personal') {
            this.applySkip('person');
          } else if (this.$store.state.user.applyData.xinxi.accountType === 'limitedLiablity') {
            this.applySkip('company');
          } else if (this.$store.state.user.applyData.xinxi.accountType === 'partnerShip') {
            this.applySkip('partnership');
          }
        } else {
          this.$message.error(res.message || 'Has Error');
        }
      }).catch((error) => {
        this.$message.error('' + error + '');
      });
    },
    applySkip(location) {
      let num = '';
      const registerStatus = String(this.$store.state.user.applyData.xinxi.registerStatus);
      if (this.$store.state.user.applyData.xinxi.registerStatus === '') {
        num = 1;
      } else if (this.$store.state.user.applyData.xinxi.registerStatus === 0) {
        this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.AccountOpeningStatus1')); // $i18n - 完成开户,未审核
      } else if (this.$store.state.user.applyData.xinxi.registerStatus === 1) {
        // this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.AccountOpeningStatus2')); // $i18n - 完成开户,已审核通过
        this.$confirm(this.$i18n.t('registerOpenAccount.register.index.hint'), { // $i18n - 开立新账户，需要将当前账户退出 ...
          confirmButtonText: this.$i18n.t('registerOpenAccount.register.index.confirm'), // $i18n - 确定
          cancelButtonText: this.$i18n.t('registerOpenAccount.register.index.cancel'), // $i18n - 取消
          type: 'warning'
        }).then(() => {
          this.$store.dispatch('user/logout');
          this.$router.push({ path: '/register' });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: this.$i18n.t('registerOpenAccount.register.index.endCancel') // $i18n - 已取消
          });
        });
      } else {
        if (registerStatus.indexOf('-') !== -1) {
          num = registerStatus.split('-')[1];
          if (num === '51') {
            num = '11a';
          } else if (num === '52') {
            num = '11b';
          }
        } else {
          num = 1;
        }
      }
      const path = '/openAccount/' + location + num;
      if (num === '') {
        return false;
      } else {
        this.$verify.routerPermissions({ path });
      }
    },
    changeLocale(value) {
      const locale = value || this.$i18n.locale;

      // this.$i18n.locale = locale;
      cookies.set('locale', locale);

      window.location.reload();
    },
    async logout() {
      await this.$store.dispatch('user/logout');
      // this.$router.push(`/login?redirect=${this.$route.fullPath}`);
      this.$router.push('/');
    }
  }
};
</script>
<style lang="scss">
  .el-header {
    background-color: white;
  }
</style>
<style lang="scss" scoped>
  .app-header {
    width: 1200px;
    height: 100%;
    margin: 0 auto;
    line-height: 58px;
    .logo {
      width: 138px;
      margin-top: 13px;
    }
    .menu {
      display: inline-block;
      float: right;
      // min-width: 900px;
    }
    .menu1 {
      display: inline-block;
      float: right;
      // min-width: 1050px;
    }
    .el-menu {
      border: none;
    }
    .el-menu-item {
      background-color: white!important;
    }
    .el-menu-item:hover {
      background-color: transparent!important;
      border-bottom: 4px solid #FF4057!important;
      color: #FF4057!important;
    }
    .el-menu--horizontal>.el-menu-item.is-active {
      border-bottom: 4px solid #FF4057;
    }
    .drop {
      margin-left: 20px;
      cursor: pointer;
    }
    .login-button {
      margin-left: 30px;
      outline: none;
    }
    .login-button:hover, .login-button:focus {
      background-color: #FFFFFF;
      border: 1px solid #FF4057;
      color: #FF4057;
    }
    .register-button {
      background-color: #FF4057;
      border: 1px solid #FF4057;
      color: #FFFFFF;
      margin-left: 10px;
    }
    .register-button:hover, .register-button:focus {
      background-color: #FFFFFF;
      border: 1px solid #FF4057;
      color: #FF4057;
    }
  }
</style>
