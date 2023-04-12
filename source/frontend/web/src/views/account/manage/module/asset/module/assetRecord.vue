<template>
  <div class="app-container">
    <el-tabs ref="assetTab" v-model="activeName" class="record-tab" @tab-click="doTabSearch">
      <el-tab-pane :label="$t('account.manage.asset.record.deposit.deposit_history')" name="assetDeposit"><!--充值入金记录-->
        <div class="second-form">
          <div class="first-module">
            <el-form ref="assetDepositForm" :model="assetDepositForm" :inline="true" label-width="85px" size="mini" :rules="assetFormRules">
              <el-form-item :label="$t('account.manage.asset.record.deposit.from_date')" prop="startDate"><!--开始日期-->
                <el-col :span="2">
                  <el-date-picker
                    v-model="assetDepositForm.startDate"
                    type="date"
                    value-format="timestamp"
                    style="width:100%;"
                    :picker-options="startDatePicker"
                  /><!--选择开始日期-->
                </el-col>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.deposit.to_date')" prop="endDate1"><!--结束日期-->
                <el-col :span="2">
                  <el-date-picker
                    v-model="assetDepositForm.endDate1"
                    type="date"
                    value-format="timestamp"
                    format="yyyy-MM-dd"
                    style="width:100%;"
                    :picker-options="endDatePicker"
                  /><!--选择结束日期-->
                </el-col>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.deposit.time_type')" prop="dateField"><!--时间类型-->
                <el-select v-model="assetDepositForm.dateField" placeholder="" value="" size="mini" style="width: 190px" clearable><!--请选择时间类型-->
                  <el-option v-for="data in depositDateForm" :key="data.value" :label="$t('account.manage.asset.record.deposit.' + data.label)" :value="data.value" />
                </el-select>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.deposit.remittance_bank')" prop="remittingBank"><!--汇款银行-->
                <el-select v-model="assetDepositForm.remittingBank" placeholder="" value="" style="width: 190px;" clearable><!--请选择汇款银行-->
                  <el-option v-for="data in remittingBankData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
                    <span>{{ data.dictDest }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.deposit.amount')" prop="amount"><!--金额-->
                <el-input v-model="assetDepositForm.amount" clearable @keyup.enter.native="depositSearch()" />
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.deposit.currency')" prop="currency"><!--货币-->
                <el-select v-model="assetDepositForm.currency" placeholder="" value="" style="width: 190px" clearable><!--请选择货币-->
                  <dictionary dict-code="baseCurrency" />
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="danger" @click="depositSubmit('assetDepositForm')">{{ $t('account.manage.asset.record.deposit.search') }}<!--查询--></el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="second-module">
            <el-table
              v-loading="dataLoading"
              :empty-text="$t('account.manage.no_data')"
              class="record-table"
              :data="assetDepositData"
              style="width:100%;margin-bottom:20px;"
              row-key="id"
              :row-style="rowStyle"
              highlight-current-row
            >
              <el-table-column prop="currency" :label="$t('account.manage.asset.record.deposit.currency')" width="90px" /><!--货币-->
              <el-table-column prop="transferType" :label="$t('account.manage.asset.record.deposit.transfer_method')" :formatter="dictFormat" :width="[$i18n.locale === 'en_US' ? '170px' : '90px' ]" /><!--转账方式-->
              <el-table-column prop="remittingBank" :label="$t('account.manage.asset.record.deposit.remittance_bank')" width="100px" :formatter="dictFormat" /><!--汇款银行-->
              <el-table-column prop="accountNumber" :label="$t('account.manage.asset.record.deposit.bank_account')" width="120px" /><!--汇款银行账号-->
              <el-table-column prop="amount" :label="$t('account.manage.asset.record.deposit.remittance_amount')" width="120px" /><!--充值入金数量-->
              <el-table-column prop="fee" :label="$t('account.manage.asset.record.deposit.fee')" /><!--手续费-->
              <el-table-column prop="swiftCode" label="SWIFT Code" width="120px" />
              <el-table-column prop="transactionId" :label="$t('account.manage.asset.record.deposit.trader_id')" width="220px" /><!--唯一交易ID-->
              <el-table-column prop="depositDate" :label="$t('account.manage.asset.record.deposit.crate_time')" :formatter="dateFormat" width="180px" /><!--充值入金创建时间-->
              <el-table-column prop="status" :label="$t('account.manage.asset.record.deposit.status')" :formatter="dictFormat" :width="[$i18n.locale === 'en_US' ? '120px' : '' ]" /><!--状态-->
              <el-table-column prop="depositConfirmDate" :label="$t('account.manage.asset.record.deposit.confirm_time')" :formatter="dateFormat" width="180px" /><!--充值入金确认时间-->
              <el-table-column prop="remark" :label="$t('account.manage.asset.record.deposit.remarks')" width="150px" /><!--备注-->
              <el-table-column :label="$t('account.manage.asset.record.deposit.operation')" width="100"><!--操作-->
                <template v-if="scope.row.status === 'unconfirmed'" slot-scope="scope">
                  <el-button size="mini" type="danger" @click="doDepositDelete(scope.$index, scope.row)">{{ $t('account.manage.asset.record.deposit.cancel') }}<!--撤销--></el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <el-pagination
            style="padding: 0 20px 100px;"
            background
            layout="prev, pager, next, sizes, jumper"
            :hide-on-single-page="true"
            :page-size="depositpageParams.rows"
            :page-sizes="[10, 20, 30, 40]"
            :page-count="depositpageParams.totalPage"
            :current-page="depositpageParams.page"
            @size-change="depositHandleSizeChange"
            @current-change="depositSearch"
          />
        </div>
      </el-tab-pane>
      <el-tab-pane :label="$t('account.manage.asset.record.withdraw.withdraw_history')" name="assetWithdraw"><!--提现出金记录-->
        <div class="second-form">
          <div class="first-module">
            <el-form ref="assetWithdrawForm" :model="assetWithdrawForm" :inline="true" label-width="85px" size="mini" :rules="assetFormRules">
              <el-form-item :label="$t('account.manage.asset.record.withdraw.from_date')" prop="startDate"><!--开始日期-->
                <el-col :span="2">
                  <el-date-picker
                    v-model="assetWithdrawForm.startDate"
                    type="date"
                    value-format="timestamp"
                    style="width:100%;"
                    :picker-options="startDatePicker"
                  /><!--选择开始日期-->
                </el-col>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.withdraw.to_date')" prop="endDate1"><!--结束日期-->
                <el-col :span="2">
                  <el-date-picker
                    v-model="assetWithdrawForm.endDate1"
                    type="date"
                    value-format="timestamp"
                    format="yyyy-MM-dd"
                    style="width:100%;"
                    :picker-options="endDatePicker"
                  /><!--选择结束日期-->
                </el-col>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.withdraw.time_type')" prop="dateField"><!--时间类型-->
                <el-select v-model="assetWithdrawForm.dateField" placeholder="" value="" size="mini" style="width: 190px" clearable><!--请选择时间类型-->
                  <el-option v-for="data in withdrawDateForm" :key="data.value" :label="$t('account.manage.asset.record.withdraw.' + data.label)" :value="data.value" />
                </el-select>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.withdraw.beneficiary_bank')" prop="beneBank"><!--收款银行-->
                <el-select v-model="assetWithdrawForm.beneBank" placeholder="" value="" style="width: 190px;" clearable><!--请选择收款银行-->
                  <el-option v-for="data in beneBankData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
                    <span>{{ data.dictDest }}</span>
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.withdraw.amount')" prop="amount"><!--金额-->
                <el-input v-model="assetWithdrawForm.amount" clearable @keyup.enter.native="withdrawSearch()" />
              </el-form-item>
              <el-form-item :label="$t('account.manage.asset.record.withdraw.currency')" prop="currency"><!--货币-->
                <el-select v-model="assetWithdrawForm.currency" placeholder="" value="" style="width: 190px" clearable><!--请选择货币-->
                  <dictionary dict-code="baseCurrency" />
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-button type="danger" @click="withdrawSubmit('assetWithdrawForm')">{{ $t('account.manage.asset.record.withdraw.search') }}<!--查询--></el-button>
              </el-form-item>
            </el-form>
          </div>
          <div class="second-module">
            <el-table
              :empty-text="$t('account.manage.no_data')"
              class="record-table"
              :data="assetWithdrawData"
              row-key="id"
              :row-style="rowStyle"
              highlight-current-row
            >
              <el-table-column prop="currency" :label="$t('account.manage.asset.record.withdraw.currency')" width="90" /><!--货币-->
              <el-table-column prop="transferType" :label="$t('account.manage.asset.record.withdraw.transfer_method')" :width="[$i18n.locale === 'en_US' ? '170px' : '90px' ]" :formatter="dictFormat" /><!--转账方式-->
              <el-table-column prop="beneBank" :label="$t('account.manage.asset.record.withdraw.beneficiary_bank')" width="100px" :formatter="dictFormat" /><!--收款银行-->
              <el-table-column prop="accountNumber" :label="$t('account.manage.asset.record.withdraw.bank_account')" width="120px" /><!--收款银行账号-->
              <el-table-column prop="amount" :label="$t('account.manage.asset.record.withdraw.withdraw_amount')" width="120px" /><!--提现出金数量-->
              <el-table-column prop="fee" :label="$t('account.manage.asset.record.withdraw.fee')" /><!--手续费-->
              <el-table-column prop="swiftCode" label="SWIFT Code" width="120px" />
              <el-table-column prop="transactionId" :label="$t('account.manage.asset.record.withdraw.trader_id')" width="220px" /><!--唯一交易ID-->
              <el-table-column prop="withdrawDate" :label="$t('account.manage.asset.record.withdraw.crate_time')" :formatter="dateFormat" width="180px" /><!--提现出金申请时间-->
              <el-table-column prop="status" :label="$t('account.manage.asset.record.withdraw.status')" :formatter="dictFormat" :width="[$i18n.locale === 'en_US' ? '120px' : '' ]" /><!--状态-->
              <el-table-column prop="withdrawConfirmDate" :label="$t('account.manage.asset.record.withdraw.confirm_time')" :formatter="dateFormat" width="180px" /><!--提现出金确认时间-->
              <el-table-column prop="remark" :label="$t('account.manage.asset.record.withdraw.remarks')" width="150px" /><!--备注-->
              <el-table-column :label="$t('account.manage.asset.record.withdraw.operation')" width="100"><!--操作-->
                <template v-if="scope.row.status === 'unconfirmed'" slot-scope="scope">
                  <el-button size="mini" type="danger" @click="doWithdrawDelete(scope.$index, scope.row)">{{ $t('account.manage.asset.record.withdraw.cancel') }}<!--撤销--></el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
          <el-pagination
            style="padding: 0 20px 100px;"
            background
            layout="prev, pager, next, sizes, jumper"
            :hide-on-single-page="true"
            :page-size="withdrawpageParams.rows"
            :page-sizes="[10, 20, 30, 40]"
            :page-count="withdrawpageParams.totalPage"
            :current-page="withdrawpageParams.page"
            @size-change="withdrawHandleSizeChange"
            @current-change="withdrawSearch"
          />
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import { isBtnZero } from '@/utils/validate';
import dictionary from '@/components/dictionary/dictionary';
export default {
  name: 'AssetCurrent',
  components: {
    dictionary
  },
  data() {
    return {
      rowStyle: {
        height: 57 + 'px'
      },
      dataLoading: '',
      activeName: 'assetDeposit',
      baseCurrencyData: [],
      assetDepositData: [],
      assetWithdrawData: [],
      transferTypeData: [],
      remittingBankData: [],
      beneBankData: [],
      statusData: [],
      depositDateForm: [{
        value: 'depositDate',
        label: 'crate_time'
      }, {
        value: 'depositConfirmDate',
        label: 'confirm_time'
      }],
      withdrawDateForm: [{
        value: 'withdrawDate',
        label: 'crate_time'
      }, {
        value: 'withdrawConfirmDate',
        label: 'confirm_time'
      }],
      assetDepositForm: {
        currency: '',
        amount: '',
        accountNumber: '',
        remittingBank: '',
        startDate: '',
        endDate: '',
        dateField: ''
      },
      assetWithdrawForm: {
        accountId: '',
        currency: '',
        beneBank: '',
        accountNumber: '',
        amount: '',
        fee: '',
        swiftCode: '',
        transactionId: '',
        withdrawDate: '',
        status: '',
        withdrawConfirmDate: '',
        remark: '',
        startDate: '',
        endDate: '',
        dateField: ''
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
        ],
        amount: [
          { required: false, message: '请输入大于零的数字', validator: isBtnZero, trigger: 'blur' }
        ]
      },
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
  mounted: function() {
    this.depositSearch();
    this.withdrawSearch();
    this.doInitData();
  },
  methods: {
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
      if (this.$i18n.locale === 'en_US') {
        this.assetFormRules.endDate1[0].message = 'Please select To Date';
        this.assetFormRules.startDate[0].message = 'Please select From Date';
        this.assetFormRules.dateField[0].message = 'Please select Time Type';
        this.assetFormRules.amount[0].message = 'Please enter a number greater than zero';
      }
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'transferType',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.transferTypeData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'beneBank',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.beneBankData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'remittingBank',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.remittingBankData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'depositStatus',
          lang: this.$i18n.locale
        }
      }).then(res => {
        if (res.code === 200) {
          this.statusData = res.object;
        }
      }).catch(error => {
        console.log(error);
      });
    },
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
    depositSearch: function(page) {
      this.depositpageParams.page = page;
      this.dataLoading = true;
      this.$http({
        url: '/asset/deposit/list',
        method: 'post',
        params: Object.assign(this.depositpageParams, this.assetDepositForm)
      }).then(res => {
        if (res.code === 200) {
          this.assetDepositData = res.rows;
          this.depositpageParams.totalPage = res.totalPage;
          this.dataLoading = false;
        }
      }).catch(error => {
        console.log(error);
      });
    },
    withdrawSearch: function(page) {
      this.withdrawpageParams.page = page;
      this.dataLoading = true;
      this.$http({
        url: '/asset/withdraw/list',
        method: 'post',
        params: Object.assign(this.withdrawpageParams, this.assetWithdrawForm)
      }).then(res => {
        if (res.code === 200) {
          this.assetWithdrawData = res.rows;
          this.withdrawpageParams.totalPage = res.totalPage;
          this.dataLoading = false;
        }
      }).catch(error => {
        console.log(error);
      });
    },
    doTabSearch: function(data) {
      this.$refs['assetDepositForm'].resetFields();
      this.$refs['assetWithdrawForm'].resetFields();
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
    },
    doWithdrawDelete: function(row, column) {
      this.$confirm('确认撤销该条体现记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/asset/withdraw/cancel',
          method: 'post',
          params: {
            id: column.id
          }
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.withdrawSearch();
          } else {
            this.$message.error(res.message || 'Has Error');
          }
        }).catch(error => {
          this.$message.error(error);
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    doDepositDelete: function(row, column) {
      this.$confirm('确认撤销该条充值记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/asset/deposit/cancel',
          method: 'post',
          params: {
            id: column.id
          }
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.depositSearch();
          } else {
            this.$message.error(res.message || 'Has Error');
          }
        }).catch(error => {
          this.$message.error(error);
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
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
    }
  }
};
</script>
<style lang="scss">
  .record-tab {
    background-color: white;
    padding: 20px 40px;
    .el-pagination button, .el-pagination span:not([class*=suffix]) {
      line-height: 0;
    }
    .el-pagination.is-background .btn-next, .el-pagination.is-background .btn-prev, .el-pagination.is-background .el-pager li {
      background-color: #FFFFFF;
      border-radius: 5px;
      border: 1px solid #d9d9da;
    }
    .el-pagination.is-background .el-pager li:not(.disabled).active {
      border: none;
    }
    .el-pagination {
      .el-select {
        margin-left: 15px;
      }
      .el-pagination__jump {
        margin-left: 0;
      }
    }
    .el-tabs__active-bar {
      background-color: #434343;
    }
    .el-tabs__item.is-active {
      color: #434343;
    }
    .el-tabs__item:hover {
      color: #434343;
      cursor: pointer;
    }
    .el-tabs__item {
      font-size: 18px;
      font-weight: 400;
      color: #999999;
      margin-bottom: 10px;
    }
    .el-table .cell {
      text-align: center;
    }
    .el-table__body {
      border-spacing: 0 10px;
    }
    .record-table td {
      box-shadow: 20px 0 20px rgba(110, 163, 225, 0.24);
    }
    .record-table {
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
    .el-form-item.is-required:not(.is-no-asterisk) .el-form-item__label-wrap>.el-form-item__label:before, .el-form-item.is-required:not(.is-no-asterisk)>.el-form-item__label:before {
      content: '';
    }
  }
</style>
<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .el-select {
      width: 250px;
    }
    .record-table {
      width:100%;margin-bottom:20px;
    }
    .first-module {
      width: 600px;
      background-color: white;
      padding: 20px 0 30px;
      .el-col-2 {
        width: auto;
      }
      .el-button--danger {
        color: #FFF;
        background-color: #F56C6C;
        border-color: #F56C6C;
        width: 100px;
        height: 30px;
        margin-left: 85px;
        padding: 0;
        margin-top: 10px;
      }
    }
    .second-form {
      .el-input {
        width: 190px;
      }
    }
    .second-module {
      background-color: white;
      padding: 20px;
      border-top: 1px solid #9c9c9c38;
    }
  }
</style>
