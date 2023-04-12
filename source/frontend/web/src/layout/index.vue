<template>
  <el-container>
    <el-header>
      <app-header />
    </el-header>
    <el-main>
      <app-main />
    </el-main>
    <el-footer height="400px">
      <app-footer />
    </el-footer>
  </el-container>
</template>

<script>
import { appHeader, appMain, appFooter } from './components';

export default {
  name: 'Layout',
  components: {
    appHeader,
    appMain,
    appFooter
  },
  methods: {
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
