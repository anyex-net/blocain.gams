<template>
  <div class="feedback">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
      <el-breadcrumb-item :to="{ params:{key: 'key'} }">问题反馈</el-breadcrumb-item>
      <el-breadcrumb-item>{{ tabViewCn }}</el-breadcrumb-item>
    </el-breadcrumb>
    <el-tabs v-model="tabView" type="card" class="navTableCheck" @tab-click="tabClick">
      <el-tab-pane label="问题收藏" name="problemCollection"></el-tab-pane>
      <el-tab-pane label="留言反馈" name="messageCollection"></el-tab-pane>
    </el-tabs>
    <keep-alive>
      <component :is="tabView"></component>
    </keep-alive>
  </div>
</template>
<script>
import problemCollection from './tabViews/problemCollection';
import messageCollection from './tabViews/messageCollection';
export default {
  components: {
    problemCollection,
    messageCollection
  },
  data() {
    return {
      tabView: 'problemCollection',
      tabViewCn: '账户概述'
    };
  },
  methods: {
    tabClick(tab) {
      this.tabViewCn = tab.label;
    }
  }
};
</script>
<style lang="scss">
.feedback {
  padding: 20px;
  .navTableCheck {
    .el-tabs__nav {
      background: #f3f6f9;
      border-radius: 0px;
      .el-tabs__item {
        padding: 0 30px;
        &:hover {
          color: #ae1207;
        }
      }
    }
    .is-top .is-active {
      background: #fff !important;
      border-top: 3px solid rgb(174, 18, 7);
      box-sizing: content-box;
      color: #ae1207;
    }
    .el-tabs__content {
      display: none;
    }
  }
  .breadcrumb {
    margin-bottom: 40px;
    font-size: 14px;
    span {
      &:nth-child(1) {
        span {
          font-weight: 700 !important;
        }
      }
    }
  }
}
</style>

