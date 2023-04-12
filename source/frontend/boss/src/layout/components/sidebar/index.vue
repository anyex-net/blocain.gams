<template>
  <div :class="{'has-logo':showLogo}">
    <sidebar-logo v-if="showLogo" :collapse="isCollapse" />
    <el-scrollbar wrap-class="scrollbar-wrapper">
      <img v-if="sidebar.opened" src="@/assets/images/logo.png" class="open-logo">
      <img v-else src="@/assets/images/logo.png" class="close-logo">
      <el-menu
        :default-active="activeMenu"
        :collapse="isCollapse"
        :background-color="variables.menuBg"
        :text-color="variables.menuText"
        :unique-opened="true"
        :active-text-color="variables.menuActiveText"
        :collapse-transition="false"
        mode="vertical"
      >
        <sidebar-item v-for="list in menuList" :key="list.id" :item="list" :base-path="list.path" />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import sidebarLogo from './sidebarLogo';
import sidebarItem from './sidebarItem';
import variables from '@/styles/variables.scss';

export default {
  components: { sidebarItem, sidebarLogo },
  data: function() {
    return {
      menuList: ''
    };
  },
  computed: {
    ...mapGetters([
      'sidebar'
    ]),
    routes() {
      return this.$router.options.routes;
    },
    activeMenu() {
      const route = this.$route;
      const { path } = route;
      return path;
    },
    showLogo() {
      return this.$store.state.settings.sidebarLogo;
    },
    variables() {
      return variables;
    },
    isCollapse() {
      return !this.sidebar.opened;
    }
  },
  mounted: function() {
    this.$http({
      url: '/menuTree',
      method: 'get'
    }).then(res => {
      this.menuList = res.object || '';
    }).catch(error => {
      this.$message.error(error);
    });
  }
};
</script>

<style lang="scss" scoped>
  .open-logo {
    width: 85%;
    margin: 15px auto;
    display: block;
  }
  .close-logo {
    margin: 15px 7px;
    display: block;
  }
</style>
