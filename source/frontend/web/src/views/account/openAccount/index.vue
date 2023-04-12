<template>
  <el-container>
    <el-header v-show="true" style="height: 60px;" class="noprint">
      <zcHeader />
    </el-header>
    <el-main>
      <zcMain />
    </el-main>
  </el-container>
</template>

<script>
import { zcHeader, zcMain } from './components';

export default {
  name: 'Register',
  components: {
    zcHeader,
    zcMain
  },
  created() {
    this.scrollTo1();
  },
  methods: {
    scrollTo1() { // 浏览器页面跳转 - 置顶
      this.$router.afterEach((to, from, next) => {
        window.scrollTo(0, 0);
      });
    }
  }
};
</script>

<style lang="scss" scoped>
  .el-main {
    margin-bottom: -5px;
  }
  .el-header, .el-main, .el-footer{
    padding: 0;
    overflow: hidden;
  }
</style>
