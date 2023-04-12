<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="账户ID">
        <el-input v-model="searchForm.accountId" clearable placeholder="请输入账户ID"></el-input>
      </el-form-item>
      <el-form-item label="证劵信息ID">
        <el-input v-model="searchForm.stockinfoId" clearable placeholder="请输入证劵信息ID"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="currentLoading" :data="currentData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="accountId" label="账户ID" />
      <el-table-column prop="currentTimestamp" label="时间" :formatter="dateFormat" />
      <el-table-column prop="businessFlag" label="业务标志" />
      <el-table-column prop="stockinfoId" label="证券信息ID" />
      <el-table-column prop="occurDirect" label="发生方向" />
      <el-table-column prop="originalAmount" label="原始数量" />
      <el-table-column prop="occurAmount" label="发生数量" />
      <el-table-column prop="lastAmount" label="最新数量" />
      <el-table-column prop="forzenOriginalAmount" label="冻结原始数量" />
      <el-table-column prop="occurForzenAmount" label="冻结发生数量" />
      <el-table-column prop="forzenLastAmount" label="冻结最新数量" />
      <el-table-column prop="transactionId" label="唯一交易ID" />
      <el-table-column prop="depositAccount" label="充值入金账号" />
      <el-table-column prop="withdrawAccount" label="提现出金账号" />
      <el-table-column prop="fee" label="手续费" />
      <el-table-column prop="netfee" label="网络手续费" />
      <el-table-column prop="status" label="状态" />
      <el-table-column prop="originalBusinessId" label="原业务ID" />
      <el-table-column prop="remark" label="备注" />
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
  name: 'AssetCurrent',
  data() {
    return {
      currentLoading: '',
      currentDialog: false,
      currentData: [],
      searchForm: {
        'accountId': '',
        'stockinfoId': ''
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
    dictFormat: function(row, column) {
      const dictArray = this[column.property + 'Data'];
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row[column.property]) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.currentLoading = true;
      this.$http({
        url: '/asset/current/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.currentData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.currentLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    }
  }
};
</script>

<style lang="scss">

</style>
