<template>
  <div class="app-container">
    <div class="trade-record-wrapper">
      <span class="trade-title">{{ $t('account.manage.invest.record.transaction_bill') }}<!--交易帐单--></span>
      <el-form class="block">
        <el-row :gutter="20">
          <el-col :span="1" style="margin: 15px 0;width: 70px;text-align: right">{{ $t('account.manage.invest.record.date') }}<!--时间段--></el-col>
          <el-col :span="4">
            <el-form-item>
              <el-date-picker
                v-model="searchForm.execTimeStart"
                type="date"
                value-format="timestamp"
                :placeholder="$t('account.manage.invest.record.from_date')"
                style="width:100%;"
                :picker-options="startDatePicker"
                size="small"
              /></el-form-item>
          </el-col>
          <el-col style="text-align: center; width: 30px; padding: 15px 0;" class="line" :span="1">-</el-col>
          <el-col :span="4">
            <el-form-item>
              <el-date-picker
                v-model="searchForm.execTimeEnd"
                type="date"
                value-format="timestamp"
                :placeholder="$t('account.manage.invest.record.to_date')"
                style="width:100%;"
                :picker-options="endDatePicker"
                size="small"
              /></el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item :label="$t('account.manage.invest.record.product_code')"><!--产品代码-->
              <el-input v-model="searchForm.symbol" style="width: 50%;" clearable />
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item :label="$t('account.manage.invest.record.action')"><!--方向-->
              <el-input v-model="searchForm.side" style="width: 50%;" clearable />
            </el-form-item>
          </el-col>
          <el-button class="trade-button" type="danger" size="mini" @click="doSearch()">{{ $t('account.manage.invest.record.search') }}<!--确定--></el-button>
        </el-row>
      </el-form>
      <el-divider />
      <el-table
        v-loading="dataLoading"
        :empty-text="$t('account.manage.no_data')"
        class="trade-table"
        :data="tradeRecordData"
        row-key="id"
        highlight-current-row
      >
        <el-table-column prop="accountId" :label="$t('account.manage.invest.record.account_id')" min-width="180" /><!--账户ID-->
        <el-table-column prop="accountCode" :label="$t('account.manage.invest.record.IB_account')" min-width="100" /><!--IB账户代码-->
        <el-table-column prop="currency" :label="$t('account.manage.invest.record.currency')" min-width="100" /><!--币种-->
        <el-table-column prop="symbol" :label="$t('account.manage.invest.record.symbol')" min-width="100" /><!--产品代码-->
        <el-table-column prop="secType" :label="$t('account.manage.invest.record.type')" min-width="100" /><!--产品类型-->
        <el-table-column prop="exchange" :label="$t('account.manage.invest.record.exchange')" min-width="100" /><!--交易所-->
        <el-table-column prop="side" :label="$t('account.manage.invest.record.action')" /><!--方向-->
        <el-table-column prop="price" :label="$t('account.manage.invest.record.price')" /><!--价格-->
        <el-table-column prop="shares" :label="$t('account.manage.invest.record.quantity')" min-width="100" /><!--数量-->
        <el-table-column prop="avgPrice" :label="$t('account.manage.invest.record.average_price')" min-width="90" /><!--成交均价-->
        <el-table-column prop="execId" :label="$t('account.manage.invest.record.order_id')" min-width="200px" /><!--成交执行ID-->
        <el-table-column prop="execTime" :label="$t('account.manage.invest.record.time')" min-width="160" /><!--执行时间-->
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
  name: 'TradeRecord',
  data() {
    return {
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
        url: '/ib/accountTrade/list',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.tradeRecordData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.dataLoading = false;
        }
      }).catch(error => {
        console.log(error);
      });
    }
  }
};
</script>
<style lang="scss">
  .trade-record-wrapper {
    .el-input__inner {
      height: 32px;
    }
    .el-table__body {
      border-spacing: 0 10px;
    }
    .el-table .cell {
      text-align: center;
    }
    .trade-table td {
      box-shadow: 20px 0 20px rgba(110, 163, 225, 0.24);
    }
    .trade-table {
      &::before {
        height: 0;
      }
      th {
        color: #8D8D8D;
        font-weight: 400;
      }
      td {
        background-color: #FFFFFF;
      }
      td, th {
        padding: 5px;
        border-bottom: none!important;
      }
    }
  }
</style>
<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .trade-record-wrapper {
      padding: 30px 40px;
      background-color: #FFFFFF;
      margin-bottom: 30px;
      .trade-title {
        font-size: 18px;
        margin-left: -40px;
        padding-left: 38px;
        border-left: 2px solid;
      }
      .block {
        margin-top: 50px;
        .trade-button {
          height: 32px;
          width: 100px;
          border-radius:5px;
          margin-top: 4px;
          margin-left: -40px;
        }
      }
      .trade-table {
        width:100%;margin-bottom:20px;
      }
    }
  }
</style>
