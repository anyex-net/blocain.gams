<template>
  <div class="registerOpenDetails">
    <div class="auditTabView">
      <div class="auditTabVie-content">
        <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
          <el-breadcrumb-item :to="{ name: 'RegisterOpen',params:{key: 'key'} }">开户管理</el-breadcrumb-item>
          <el-breadcrumb-item>{{ tabViewCn }}</el-breadcrumb-item>
        </el-breadcrumb>
        <!-- <div class="audit-title">表格审核</div> -->
        <el-tabs v-model="tabView" type="card" class="navTableCheck" @tab-click="tabClick">
          <el-tab-pane label="信息" name="message"></el-tab-pane>
          <el-tab-pane label="规管信息" name="regulate"></el-tab-pane>
          <el-tab-pane v-if="userInfo.accountType!=='personal'" label="注册拥有人" name="registerPerson"></el-tab-pane>
          <el-tab-pane
            v-if="userInfo.accountType!=='personal'"
            label="受益所有人"
            name="beneficialOwner"
          ></el-tab-pane>
          <el-tab-pane label="W-8" name="wt8"></el-tab-pane>
          <el-tab-pane label="CRS" name="crs"></el-tab-pane>
          <el-tab-pane label="附件" name="fileModule"></el-tab-pane>
        </el-tabs>
        <!-- <a class="returnUserList" @click="returnUserList">返回用户列表</a> -->
        <keep-alive>
          <component :is="tabView" :user-info="userInfo"></component>
        </keep-alive>
      </div>
    </div>
  </div>
</template>
<script>
import message from './tabView/message';
import regulate from './tabView/regulate';
import registerPerson from './tabView/registerPerson';
import wt8 from './tabView/wt8';
import crs from './tabView/crs';
import fileModule from './tabView/fileModule';
import beneficialOwner from './tabView/beneficialOwner';
export default {
  components: {
    message,
    regulate,
    registerPerson,
    wt8,
    crs,
    fileModule,
    beneficialOwner
  },
  props: {
    userInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      tabView: 'message',
      tabViewCn: '信息'
    };
  },
  methods: {
    tabClick(tab) {
      this.tabViewCn = tab.label;
    },
    // returnUserList() {
    //   this.$router.push({ path: '/account/registerOpen' });
    // },
    dateFormat(row, column) {
      // 日期格式化 时间戳 >> 'YYYY-MM-DD HH:mm:ss'
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    }
  }
};
</script>>
<style lang="scss" scoped>
.registerOpenDetails{
    padding-top: 0;
    .audit-title {
        margin-bottom: 10px;
    }
    .breadcrumb{
            margin-bottom: 40px;
            font-size: 14px;
        }
}
</style>
<style lang="scss">
.registerOpenDetails{
    .el-breadcrumb__inner a, .el-breadcrumb__inner.is-link{
        font-weight: 700 !important;
    }
}
</style>
