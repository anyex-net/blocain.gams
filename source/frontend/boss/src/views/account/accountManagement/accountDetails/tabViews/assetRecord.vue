<template>
  <div class="entryExitRecord">
    <el-tabs ref="assetTab" v-model="activeName" class="record-tab" @tab-click="doTabSearch">
      <el-tab-pane label="充值入金记录" name="assetDeposit">
        <!-- 搜索条件 -->
        <el-form ref="assetDepositForm" :inline="true" :model="assetDepositForm" class="formSearch" :rules="assetFormRules">
          <el-row>
            <el-col :span="5">
              <el-form-item label="时间类型" prop="dateField">
                <el-select v-model="assetDepositForm.dateField" placeholder="时间类型" clearable value="">
                  <el-option label="充值入金创建时间" value="depositDate"></el-option>
                  <el-option label="充值入金确认时间" value="depositConfirmDate"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="开始日期" prop="startDate"><!--开始日期-->
                <el-date-picker
                  v-model="assetDepositForm.startDate"
                  type="date"
                  value-format="timestamp"
                  style="width:100%;"
                  :picker-options="startDatePicker"
                /><!--选择开始日期-->
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="结束日期" prop="endDate1"><!--结束日期-->
                <el-date-picker
                  v-model="assetDepositForm.endDate1"
                  type="date"
                  value-format="timestamp"
                  format="yyyy-MM-dd"
                  style="width:100%;"
                  :picker-options="endDatePicker"
                /><!--选择结束日期-->
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="汇款银行">
                <el-select v-model="assetDepositForm.remittingBank " placeholder="汇款银行" clearable value="">
                  <el-option v-for="data in remittingBankData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
                    <span style="float:left;">{{ data.dictValue }}</span>
                    <span style="float:right">{{ data.dictDest }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button type="danger" style="margin-left: 20px;width: 56px;font-size: 12px;" @click="depositSubmit('assetDepositForm')">查询</el-button>
              </el-form-item>
            </el-col>
            <!--<el-col :span="6">
              <el-form-item label="汇款金额">
                <el-input v-model="assetDepositForm.amount" placeholder="汇款金额"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="货币">
                <el-select v-model="assetDepositForm.baseCurrency" placeholder="请选择基础币种" value="">
                  <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
                    <span style="float:left;">{{ data.dictValue }}</span>
                    <span style="float:right">{{ data.dictDest }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>-->
          </el-row>
        </el-form>
        <!-- 表格信息 -->
        <el-table :data="depositData" border style="width: 100%;" empty-text="无数据">
          <el-table-column label="序号" type="index" width="50"></el-table-column>
          <el-table-column prop="accountId" label="账户ID" />
          <el-table-column prop="accountName" label="账户名" />
          <el-table-column prop="currency" label="币种" :formatter="dictFormat" />
          <el-table-column prop="remittingBank" label="汇出银行" :formatter="dictFormat" />
          <el-table-column prop="accountNumber" label="汇出银行账号" />
          <el-table-column prop="transferType" label="转账方式" :formatter="dictFormat" />
          <el-table-column prop="amount" label="充值入金数量" />
          <el-table-column prop="fee" label="手续费" />
          <el-table-column prop="swiftCode" label="SWIFT码" />
          <el-table-column prop="transactionId" label="唯一交易ID" />
          <el-table-column prop="depositDate" label="充值入金申请时间" :formatter="dateFormat" />
          <el-table-column prop="status" label="状态" :formatter="dictFormat" />
          <el-table-column prop="depositConfirmDate" label="充值入账确认时间" :formatter="dateFormat" />
          <el-table-column prop="remark" label="备注" />
        </el-table>
        <!-- 分页 -->
        <el-pagination
          class="pagination"
          style="padding: 20px 20px;"
          layout="prev, pager, next, sizes, jumper"
          :hide-on-single-page="true"
          :page-size="depositpageParams.rows"
          :page-sizes="[10, 20, 30, 40]"
          :page-count="depositpageParams.totalPage"
          :current-page="depositpageParams.page"
          @size-change="depositHandleSizeChange"
          @current-change="depositSearch"
        ></el-pagination>
      </el-tab-pane>
      <el-tab-pane label="提现出金记录" name="assetWithdraw">
        <!-- 搜索条件 -->
        <el-form ref="assetWithdrawForm" :inline="true" :model="assetWithdrawForm" class="formSearch" :rules="assetFormRules">
          <el-row>
            <el-col :span="5">
              <el-form-item label="时间类型" prop="dateField">
                <el-select v-model="assetWithdrawForm.dateField" placeholder="时间类型" clearable value="">
                  <el-option label="提现出金创建时间" value="withdrawDate"></el-option>
                  <el-option label="提现出金确认时间" value="withdrawConfirmDate"></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="开始日期" prop="startDate"><!--开始日期-->
                <el-date-picker
                  v-model="assetWithdrawForm.startDate"
                  type="date"
                  value-format="timestamp"
                  style="width:100%;"
                  :picker-options="startDatePicker"
                /><!--选择开始日期-->
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="结束日期" prop="endDate1"><!--结束日期-->
                <el-date-picker
                  v-model="assetWithdrawForm.endDate1"
                  type="date"
                  value-format="timestamp"
                  format="yyyy-MM-dd"
                  style="width:100%;"
                  :picker-options="endDatePicker"
                /><!--选择结束日期-->
              </el-form-item>
            </el-col>
            <el-col :span="5">
              <el-form-item label="汇款银行">
                <el-select v-model="assetWithdrawForm.beneBank " placeholder="汇款银行" clearable value="">
                  <el-option v-for="data in beneBankData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
                    <span style="float:left;">{{ data.dictValue }}</span>
                    <span style="float:right">{{ data.dictDest }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="2">
              <el-form-item>
                <el-button type="danger" style="margin-left: 20px;width: 56px;font-size: 12px;" @click="withdrawSubmit('assetWithdrawForm')">查询</el-button>
              </el-form-item>
            </el-col>
            <!--<el-col :span="6">
              <el-form-item label="汇款金额">
                <el-input v-model="assetWithdrawForm.amount" placeholder="汇款金额"></el-input>
              </el-form-item>
            </el-col>
            <el-col :span="6">
              <el-form-item label="货币">
                <el-select v-model="assetWithdrawForm.baseCurrency" placeholder="请选择基础币种" value="">
                  <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
                    <span style="float:left;">{{ data.dictValue }}</span>
                    <span style="float:right">{{ data.dictDest }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>-->
          </el-row>
        </el-form>
        <!-- 表格信息 -->
        <el-table :data="withdrawData" border style="width: 100%;" empty-text="无数据">
          <el-table-column label="序号" type="index" width="50"></el-table-column>
          <el-table-column prop="accountId" label="账户ID" />
          <el-table-column prop="accountName" label="账户名" />
          <el-table-column prop="currency" label="币种" :formatter="dictFormat" />
          <el-table-column prop="beneBank" label="汇出银行" :formatter="dictFormat" />
          <el-table-column prop="accountNumber" label="汇出银行账号" />
          <el-table-column prop="transferType" label="转账方式" :formatter="dictFormat" />
          <el-table-column prop="amount" label="充值入金数量" />
          <el-table-column prop="fee" label="手续费" />
          <el-table-column prop="swiftCode" label="SWIFT码" />
          <el-table-column prop="transactionId" label="唯一交易ID" />
          <el-table-column prop="withdrawDate" label="提现出金申请时间" :formatter="dateFormat" />
          <el-table-column prop="status" label="状态" :formatter="dictFormat" />
          <el-table-column prop="withdrawConfirmDate" label="提现出金确认时间" :formatter="dateFormat" />
          <el-table-column prop="remark" label="备注" />
        </el-table>
        <!-- 分页 -->
        <el-pagination
          class="pagination"
          style="padding: 20px 20px;"
          layout="prev, pager, next, sizes, jumper"
          :hide-on-single-page="true"
          :page-size="withdrawpageParams.rows"
          :page-sizes="[10, 20, 30, 40]"
          :page-count="withdrawpageParams.totalPage"
          :current-page="withdrawpageParams.page"
          @size-change="withdrawHandleSizeChange"
          @current-change="withdrawSearch"
        ></el-pagination>
      </el-tab-pane>
    </el-tabs>
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
      activeName: 'assetDeposit',
      accountId: this.accountInfo.accountId,
      depositData: [],
      withdrawData: [],
      remittingBankData: [],
      transferTypeData: [],
      statusData: [],
      currencyData: [],
      beneBankData: [],
      depositpageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      withdrawpageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      assetDepositForm: {
        currency: '',
        amount: '',
        remittingBank: '',
        startDate: '',
        endDate: '',
        dateField: '',
        endDate1: ''
      },
      assetWithdrawForm: {
        currency: '',
        beneBank: '',
        accountNumber: '',
        amount: '',
        withdrawDate: '',
        withdrawConfirmDate: '',
        remark: '',
        startDate: '',
        endDate: '',
        dateField: '',
        endDate1: ''
      },
      assetFormRules: {
        dateField: [
          { required: false, message: '请选择时间类型', trigger: 'change' }
        ],
        endDate1: [
          { required: false, message: '请选择结束日期', trigger: 'change' }
        ],
        startDate: [
          { required: false, message: '请选择开始日期', trigger: 'change' }
        ]
      },
      startDatePicker: {
        disabledDate: time => {
          if (this.assetDepositForm.endDate1 || this.assetWithdrawForm.endDate1) {
            return (
              time.getTime() > new Date(this.assetDepositForm.endDate1).getTime() ||
              time.getTime() > new Date(this.assetWithdrawForm.endDate1).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      },
      endDatePicker: {
        disabledDate: time => {
          if (this.assetDepositForm.startDate || this.assetWithdrawForm.startDate) {
            return (
              time.getTime() > Date.now() ||
              time.getTime() < new Date(this.assetDepositForm.startDate).getTime() ||
              time.getTime() < new Date(this.assetWithdrawForm.startDate).getTime()
            );
          } else {
            return time.getTime() > Date.now();
          }
        }
      }
    };
  },
  mounted() {
    this.doInitData();
    this.depositSearch();
  },
  methods: {
    depositHandleSizeChange(val) {
      this.depositpageParams.rows = val;
      this.depositSearch();
    },
    withdrawHandleSizeChange(val) {
      this.withdrawpageParams.rows = val;
      this.withdrawSearch();
    },
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
    doInitData() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'baseCurrency',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.currencyData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'remittingBank',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.remittingBankData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'beneBank',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.beneBankData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'transferType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.transferTypeData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'depositStatus',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.statusData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    depositSearch: function(page) {
      this.depositpageParams.page = page;
      this.$http({
        url: '/account/manage/asset/deposit/list',
        method: 'post',
        params: Object.assign(this.depositpageParams, this.assetDepositForm, { accountId: this.accountId })
      }).then(res => {
        if (res.code === 200) {
          this.depositData = res.rows;
          this.depositpageParams.totalPage = res.totalPage;
          // this.depositLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    withdrawSearch: function(page) {
      this.withdrawpageParams.page = page;
      this.$http({
        url: '/account/manage/asset/withdraw/list',
        method: 'post',
        params: Object.assign(this.withdrawpageParams, this.assetWithdrawForm, { accountId: this.accountId })
      }).then(res => {
        if (res.code === 200) {
          this.withdrawData = res.rows;
          this.withdrawpageParams.totalPage = res.totalPage;
          // this.dataLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    depositSubmit: function(formName) {
      if (this.assetDepositForm.startDate === null) {
        this.assetDepositForm.endDate = null;
      }
      if (this.assetDepositForm.endDate1 || this.assetDepositForm.startDate || this.assetDepositForm.dateField) {
        this.assetFormRules.endDate1[0].required = true;
        this.assetFormRules.startDate[0].required = true;
        this.assetFormRules.dateField[0].required = true;
      } else {
        this.assetFormRules.endDate1[0].required = false;
        this.assetFormRules.startDate[0].required = false;
        this.assetFormRules.dateField[0].required = false;
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.assetDepositForm.startDate !== null) {
            this.assetDepositForm.endDate = this.assetDepositForm.endDate1 + 3600 * 1000 * 24;
          }
          this.depositSearch();
        }
      });
    },
    withdrawSubmit: function(formName) {
      if (this.assetWithdrawForm.startDate === null) {
        this.assetWithdrawForm.endDate = null;
      }
      if (this.assetWithdrawForm.endDate1 || this.assetWithdrawForm.startDate || this.assetWithdrawForm.dateField) {
        this.assetFormRules.endDate1[0].required = true;
        this.assetFormRules.startDate[0].required = true;
        this.assetFormRules.dateField[0].required = true;
      } else {
        this.assetFormRules.endDate1[0].required = false;
        this.assetFormRules.startDate[0].required = false;
        this.assetFormRules.dateField[0].required = false;
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (this.assetWithdrawForm.startDate !== null) {
            this.assetWithdrawForm.endDate = this.assetWithdrawForm.endDate1 + 3600 * 1000 * 24;
          }
          this.withdrawSearch();
        }
      });
    },
    handleCurrentChange() {

    },
    doTabSearch: function(data) {
      // this.$refs['assetDepositForm'].resetFields();
      // this.$refs['assetWithdrawForm'].resetFields();
      this.assetDepositForm = {
        'startDate': '',
        'endDate': '',
        'endDate1': '',
        'dateField': ''
      };
      this.assetWithdrawForm = {
        'startDate': '',
        'endDate': '',
        'endDate1': '',
        'dateField': ''
      };
      if (data.name === 'assetDeposit') {
        this.depositSearch();
      }
      if (data.name === 'assetWithdraw') {
        this.withdrawSearch();
      }
    }
  }
};
</script>
<style lang="scss">
  .el-table__empty-block {
    display: block!important;
  }
  .entryExitRecord {
    .el-input__icon {
      line-height: 20px;
    }
  }
</style>
<style lang="scss" scoped>
.entryExitRecord {
  .formSearchBtn {
    display: block !important;
    text-align: right;
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
  .record-tab {
    margin-bottom: 20px;
  }
}
</style>
