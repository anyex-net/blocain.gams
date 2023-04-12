<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="消息类型">
        <el-select v-model="searchForm.type" clearable placeholder="请选择消息类型" value="">
          <el-option key="email" label="邮件" value="email" />
          <el-option key="sms" label="短信" value="sms" />
        </el-select>
      </el-form-item>
      <el-form-item label="发送对象">
        <el-input v-model="searchForm.object" clearable placeholder="请输入发送对象"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="msgLogLoading" :data="msgLogData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column label="消息类型">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 'email'">邮件</span>
          <span v-if="scope.row.type === 'sms'">短信</span>
        </template>
      </el-table-column>
      <el-table-column prop="object" label="发送对象" />
      <el-table-column prop="content" label="内容" />
      <el-table-column label="发送状态">
        <template slot-scope="scope">
          <span v-if="scope.row.status === true">成功</span>
          <span v-if="scope.row.status === false">失败</span>
        </template>
      </el-table-column>
      <!--<el-table-column prop="runTime" label="执行时间(秒) " />-->
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
export default {
  name: 'MsgLogData',
  data() {
    return {
      msgLogLoading: '',
      msgLogData: [],
      msgLogForm: {
        'type': '',
        'object': '',
        'content': '',
        'status': '',
        'createDate': '',
        'runTime': ''
      },
      searchForm: {
        'type': '',
        'object': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
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
      this.msgLogLoading = true;
      this.$http({
        url: '/query/msgLog/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.msgLogData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.msgLogLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    }
  }
};

</script>

<style lang="scss">
  .msgLogForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
