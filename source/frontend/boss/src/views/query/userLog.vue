<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini" :rules="searchRules">
      <el-form-item label="用户ID" prop="userId">
        <el-input v-model="searchForm.userId" clearable placeholder="请输入用户ID"></el-input>
      </el-form-item>
      <el-form-item label="用户名称">
        <el-input v-model="searchForm.userName" clearable placeholder="请输入用户名称"></el-input>
      </el-form-item>
      <el-form-item label="来访IP">
        <el-input v-model="searchForm.ipAddr" clearable placeholder="请输入来访IP"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="userLogLoading" :data="userLogData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="userId" label="用户ID" />
      <el-table-column prop="userName" label="用户名称" />
      <el-table-column prop="ipAddr" label="来访IP" />
      <el-table-column prop="url" label="请求地址" />
      <el-table-column prop="content" label="操作说明" />
      <el-table-column prop="runTime" label="执行时间(秒) " />
      <el-table-column prop="createDate" :formatter="dateFormat" label="创建时间" />
    </el-table>
    <el-pagination
      style="text-align:center;"
      background
      layout="prev, pager, next"
      :hide-on-single-page="true"
      :page-size="pageParams.rows"
      :page-count="pageParams.totalPage"
      :current-page="pageParams.page"
      @current-change="doSearch"
    />

  </div>
</template>

<script>
import { isNum } from '../../utils/validate.js';
export default {
  name: 'UserLog',
  data() {
    return {
      userLogLoading: '',
      userLogData: [],
      userLogForm: {
        'userId': '',
        'userName': '',
        'ipAddr': '',
        'url': '',
        'content': '',
        'runTime': '',
        'createDate': ''
      },
      searchForm: {
        'userId': '',
        'userName': '',
        'ipAddr': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      searchRules: {
        userId: [
          { required: false, trigger: 'blur' }, { validator: isNum, trigger: 'blur' }
        ]
      }
    };
  },
  mounted: function() {
    this.doSearch();
  },
  methods: {
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          this.userLogLoading = true;
          this.$http({
            url: '/query/userLog/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.userLogData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.userLogLoading = false;
            }
          }).catch(error => {
            this.$message.error(error);
          });
        }
      });
    }
  }
};

</script>

<style lang="scss">
  .userLogForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
