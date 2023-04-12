<template>
  <div class="tradingRecord">
    <div class="trade-record-wrapper">
      <el-form class="block">
        <el-row :gutter="20">
          <el-col :span="5">
            <el-form-item label="开始日期" prop="execTimeStart">
              <el-date-picker
                v-model="searchForm.execTimeStart"
                type="date"
                value-format="timestamp"
                placeholder="开始日期"
                style="width:60%;"
                :picker-options="startDatePicker"
              /></el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="结束日期" prop="execTimeEnd">
              <el-date-picker
                v-model="searchForm.execTimeEnd"
                type="date"
                value-format="timestamp"
                placeholder="结束日期"
                style="width:60%;"
                :picker-options="endDatePicker"
              /></el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="产品代码"><!--产品代码-->
              <el-input v-model="searchForm.symbol" style="width: 60%;" clearable size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="5">
            <el-form-item label="方向"><!--方向-->
              <el-input v-model="searchForm.side" style="width: 60%;" clearable size="mini" />
            </el-form-item>
          </el-col>
          <el-col :span="2">
            <el-form-item>
              <el-button class="trade-button" type="danger" size="mini" @click="doSearch()">查询</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <el-table
        empty-text="无数据"
        class="trade-table"
        :data="tradeRecordData"
        row-key="id"
        highlight-current-row
        border
      >
        <el-table-column prop="accountId" label="账户ID" width="170" /><!--账户ID-->
        <el-table-column prop="accountCode" label="IB账户代码" /><!--IB账户代码-->
        <el-table-column prop="currency" label="币种" /><!--币种-->
        <el-table-column prop="symbol" label="产品代码" /><!--产品代码-->
        <el-table-column prop="secType" label="产品类型" /><!--产品类型-->
        <el-table-column prop="exchange" label="交易所" /><!--交易所-->
        <el-table-column prop="side" label="方向" /><!--方向-->
        <el-table-column prop="price" label="价格" /><!--价格-->
        <el-table-column prop="shares" label="数量" /><!--数量-->
        <el-table-column prop="avgPrice" label="成交均价" /><!--成交均价-->
        <el-table-column prop="execId" label="成交执行ID" width="200" /><!--成交执行ID-->
        <el-table-column prop="execTime" label="执行时间" width="150" /><!--执行时间-->
      </el-table>
      <el-pagination
        style="text-align:center;"
        background
        layout="prev, pager, next, sizes, jumper"
        :hide-on-single-page="true"
        :page-size="pageParams.rows"
        :page-sizes="[10, 20, 30, 40]"
        :page-count="pageParams.totalPage"
        :current-page="pageParams.page"
        @current-change="doSearch"
      />
    </div>
  </div>
</template>
<script>
export default {
  props: {
    accountInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      accountId: this.accountInfo.accountId,
      dataLoading: '',
      tradeRecordLoading: '',
      tradeRecordData: [],
      exchangeData: [],
      baseCurrencyData: [],
      startDatePicker: {
        disabledDate: time => {
          if (this.searchForm.execTimeEnd) {
            return (
              time.getTime() > new Date(this.searchForm.execTimeEnd).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      },
      endDatePicker: {
        disabledDate: time => {
          if (this.searchForm.execTimeStart) {
            return (
              time.getTime() > Date.now() ||
              time.getTime() < new Date(this.searchForm.execTimeStart).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      },
      value2: '',
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      searchForm: {
        symbol: '',
        side: '',
        execTimeStart: '',
        execTimeEnd: ''
      }
    };
  },
  mounted: function() {
    this.doSearch();
  },
  methods: {
    dictFormat: function(row, column) {
      const dictArray = this.baseCurrencyData;
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row[column.property]) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.dataLoading = true;
      this.$http({
        url: '/account/manage/ib/accountTrade/list',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm, { accountId: this.accountId })
      }).then(res => {
        if (res.code === 200) {
          this.tradeRecordData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.dataLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    }
  }
};
</script>
<style lang="scss">
  .tradingRecord {
    .el-input--prefix .el-input__inner {
      height: 28px;
      display: inline-block;
      margin-top: 8px;
    }
  }
</style>
<style lang="scss" scoped>
.tradingRecord {
  padding: 10px;
  .formSearchBtn {
    display: block !important;
    margin-left: 28px;
  }
  .paginationDiv {
    // width:1400px ;
    line-height: 26px;
    padding: 20px 0;
    span {
      line-height: 25px;
      font-size: 13px;
      color: #606266;
    }
    .pagination {
      display: block;
      float: right;
    }
  }
  .trade-button {
    margin-top: 5px;
  }
}
</style>
