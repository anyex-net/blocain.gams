<template>
  <div>
    <div id="ZC-regiTop">
      <div class="tip1Login">
        <div class="tip1Login-cot w">
          <router-link to="/">
            <div class="logo xiaoshou" />
          </router-link>
          <div class="language">

            <el-dropdown class="drop" trigger="click" style="outline:none;margin-right:20px;" @command="changeLocale">
              <!-- command element-ui下拉框点击 -->
              <span class="el-dropdown-link xiaoshou">
                <span ref="lang">简体中文</span>
                <i class="el-icon-arrow-down el-icon--right" />
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item command="zh_CN">简体中文</el-dropdown-item>
                <!-- <el-dropdown-item command="zh_HK">繁体中文</el-dropdown-item> -->
                <el-dropdown-item command="en_US">English</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <el-dropdown class="drop el-dropdown-border" trigger="click">
              <span class="el-dropdown-link">
                <span class="el-icon-user-solid xiaoshou" />
                <span class="username xiaoshou">{{ name }}</span>
              </span>
              <el-dropdown-menu slot="dropdown">
                <router-link to="/">
                  <el-dropdown-item>
                    {{ $t('common.header.home') }}<!-- $i18n - 网站首页-->
                  </el-dropdown-item>
                </router-link>
                <el-dropdown-item divided>
                  <span style="display:block;" @click="logout"><!--退出-->{{ $t('common.header.logout') }}</span>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import cookies from 'js-cookie';
import { mapGetters } from 'vuex';
export default {
  name: 'OpenAnAccountHeader',
  data() {
    return {};
  },
  computed: {
    ...mapGetters([
      'name'
    ])
  },

  created() {
  },
  mounted: function() {
    this.changeLocale('');
  },
  methods: {
    changeLocale(value) {
      this.fullscreenLoading = !!value;
      const locale = value || this.$i18n.locale;
      if (locale === 'zh_CN') {
        this.$refs.lang.innerText = '简体中文';
      }
      if (locale === 'zh_HK') {
        this.$refs.lang.innerText = '繁体中文';
      }
      if (locale === 'en_US') {
        this.$refs.lang.innerText = 'English';
      }

      // this.$i18n.locale = locale;
      cookies.set('locale', locale);

      if (value !== '') {
        this.fullscreenLoading = false;
        this.reload();
      }
    },
    async reload() {
      // this.$verify.routerPermissions(this.$store.state.user.openAccount.currentPosition);
      location.reload();
    },
    async logout() {
      await this.$store.dispatch('user/logout');
      this.$router.push('/');
    }
  }
};
</script>

<style  scoped>
/* 按钮 */
.el-button--danger > span {
  color: #fff;
}
.el-radio {
  /* 单选框行高 */
  margin-bottom: 12px;
  font-size: 20px;
  color: #999;
}
.el-dropdown-border {
  margin-right: 20px;
  padding: 0px 20px;
  border-left: 1px solid #f1f3f7;
  margin-right: 18px;
  padding-right: 0;
}
</style>

