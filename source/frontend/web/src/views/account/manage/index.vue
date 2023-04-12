<template>
  <el-container>
    <el-main>
      <div class="app-content">
        <div class="main-container">
          <router-view />
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import { mapGetters } from 'vuex';
import cookies from 'js-cookie';

export default {
  name: 'Manage',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  mounted: function() {
    // this.changeLocale('');
  },
  methods: {
    changeLocale(value) {
      const locale = value || this.$i18n.locale;

      if (locale === 'zh_CN') {
        this.$refs.lang.innerText = '简';
      }
      if (locale === 'zh_HK') {
        this.$refs.lang.innerText = '繁';
      }
      if (locale === 'en_US') {
        this.$refs.lang.innerText = 'En';
      }

      // this.$i18n.locale = locale;
      cookies.set('locale', locale);

      if (value !== '') {
        window.location.reload();
      }
    },
    async logout() {
      await this.$store.dispatch('user/logout');
      // this.$router.push(`/login?redirect=${this.$route.fullPath}`);
      this.$router.push('/');
    }
  }
};
</script>

<style lang="scss" scoped>
  $sideBarWidth: 210px;

  .el-header, .el-main, .el-footer{
    padding: 0;
  }
  .app-header {
    height: 100%;
    margin: 0 auto;
    line-height: 58px;
    padding: 0 20px;
    background-color: white;
    .logo {
      width: 138px;
      margin-top: 13px;
    }
    .tab-wrapper {
      float: right;
      color: #666666;
      font-size: 14px;
      .divide {
        padding: 0 10px;
      }
      .link {
        color: #7FB0F3;
        cursor: pointer;
        text-decoration: none;
      }
      .account-drop {
        padding: 0 20px;
        cursor: pointer;
        border-left: 1px solid #BFC8D9;
        border-right: 1px solid #BFC8D9;
      }
      .locale-drop {
        margin-left: 20px;
        cursor: pointer;
      }
    }
  }
  .app-content {
    position: relative;
    .sidebar-container {
      transition: width 0.28s;
      width: $sideBarWidth !important;
      background-color: #273245;
      height: 100%;
      position: fixed;
      top: 60px;
      bottom: 0;
      left: 0;
      z-index: 1001;
      overflow: hidden;
      .el-menu-manage {
        border: none;
      }
    }
    .main-container {
      min-height: 100%;
      position: fixed;
      overflow: auto;
      top: 60px;
      right: 0;
      bottom: 0;
      left: 0;
      transition: margin-left .28s;
      margin-left: $sideBarWidth;
    }
  }
</style>
