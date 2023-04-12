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

    <el-table v-loading="positionLoading" :data="positionData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="accountId" label="账户ID" />
      <el-table-column prop="stockinfoId" label="证券信息ID" />
      <el-table-column prop="positionDirection" label="持仓方向" />
      <el-table-column prop="openAvgPrice" label="开仓均价" />
      <el-table-column prop="amount" label="数量" />
      <el-table-column prop="frozenAmount" label="冻结数量" />
      <el-table-column prop="periodInitAmount" label="期间期初数量" />
      <el-table-column prop="periodInflowAmount" label="期间流入数量" />
      <el-table-column prop="periodOutflowAmount" label="期间流出数量" />
      <el-table-column prop="realProfitLoss" label="已实现盈亏" />
      <el-table-column prop="unrealProfitLoss" label="未实现盈亏" />
      <el-table-column prop="leverageMultiples" label="杠杆倍数" />
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
  name: 'AssetPosition',
  data() {
    return {
      positionLoading: '',
      positionDialog: false,
      positionData: [],
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
      this.positionLoading = true;
      this.$http({
        url: '/asset/position/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.positionData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.positionLoading = false;
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
