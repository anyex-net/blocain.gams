<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini" :rules="searchRules">
      <el-form-item label="账户ID" prop="accountId">
        <el-input v-model="searchForm.accountId" clearable placeholder="请输入账户ID"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="depositLoading" :data="depositData" style="width:100%;margin-bottom:20px;" border row-key="id">
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
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status === 'unconfirmed'" size="mini" type="success" @click="dialogConfirm(scope.$index, scope.row)">审核</el-button>
          <!--          <el-button v-if="scope.row.status === 'unconfirmed'" size="mini" type="success" @click="dialogEdit(scope.$index, scope.row)">编辑</el-button>-->
        </template>
      </el-table-column>
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

    <el-dialog title="充值入金" :visible.sync="depositDialog" width="700px">
      <el-form ref="depositForm" :inline="true" :model="depositForm" :rules="depositRules" label-width="120px" class="depositForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="depositForm.accountId" />
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select v-model="depositForm.currency" placeholder="请选择币种" value="">
            <el-option v-for="data in currencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="汇出银行" prop="remittingBank">
          <el-select v-model="depositForm.remittingBank" placeholder="请选择汇出银行" value="">
            <el-option v-for="data in remittingBankData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="汇出银行账号" prop="accountNumber">
          <el-input v-model="depositForm.accountNumber" />
        </el-form-item>
        <el-form-item label="充值入金数量" prop="amount">
          <el-input v-model="depositForm.amount" />
        </el-form-item>
        <el-form-item label="手续费" prop="fee">
          <el-input v-model="depositForm.fee" />
        </el-form-item>
        <el-form-item label="SWIFT码" prop="swiftCode">
          <el-input v-model="depositForm.swiftCode" />
        </el-form-item>
        <el-form-item label="唯一交易ID" prop="transactionId">
          <el-input v-model="depositForm.transactionId" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="depositForm.remark" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:120px;" type="primary" @click="doSubmit('depositForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="充值审核" :visible.sync="confirmDialog" width="700px">
      <el-form ref="confirmForm" :inline="true" :model="depositForm" label-width="120px" class="depositForm">
        <el-form-item label="账户ID">
          <div class="confirmTxt">{{ depositForm.accountId }}</div>
        </el-form-item>
        <el-form-item label="币种">
          <div class="confirmTxt">{{ depositForm.currency }}</div>
        </el-form-item>
        <el-form-item label="汇出银行">
          <div class="confirmTxt">
            <div v-for="data in remittingBankData" :key="data.id">
              <span v-if="depositForm.remittingBank === data.dictValue">{{ data.dictDest }}</span>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="汇出银行账号">
          <div class="confirmTxt">{{ depositForm.accountNumber }}</div>
        </el-form-item>
        <el-form-item label="充值入金数量">
          <div class="confirmTxt">{{ depositForm.amount }}</div>
        </el-form-item>
        <el-form-item label="手续费">
          <div class="confirmTxt">{{ depositForm.fee }}</div>
        </el-form-item>
        <el-form-item label="SWIFT码">
          <div class="confirmTxt">{{ depositForm.swiftCode }}</div>
        </el-form-item>
        <el-form-item label="唯一交易ID">
          <div class="confirmTxt">{{ depositForm.transactionId }}</div>
        </el-form-item>
        <el-form-item label="备注">
          <div style="min-height: 42px;text-align: center;border: 1px solid #DCDFE6;border-radius: 5px;">{{ depositForm.remark }}</div>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:120px;" type="primary" @click="doConfirm()">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isInteger, isNum } from '../../utils/validate.js';
export default {
  name: 'AssetDeposit',
  data() {
    return {
      depositLoading: '',
      depositDialog: false,
      confirmDialog: false,
      depositData: [],
      currencyData: [],
      remittingBankData: [],
      transferTypeData: [],
      statusData: [],
      depositForm: {
        'id': '',
        'accountId': '',
        'currency': '',
        'remittingBank': '',
        'accountNumber': '',
        'amount': '',
        'fee': '',
        'swiftCode': '',
        'transactionId': '',
        'status': '',
        'depositDate': '',
        'depositConfirmDate': '',
        'transferType': ''
      },
      searchForm: {
        'accountId': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      depositRules: {
        accountId: [
          { required: true, message: '请输入账户ID', trigger: 'blur' }
        ],
        currency: [
          { required: true, message: '请选择币种', trigger: 'change' }
        ],
        remittingBank: [
          { required: true, message: '请输入汇出银行', trigger: 'blur' }
        ],
        accountNumber: [
          { required: true, message: '请输入汇出银行账号', trigger: 'blur' }
        ],
        amount: [
          { required: true, message: '请输入充值入金数量', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
        ],
        fee: [
          { required: true, message: '请输入手续费', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        swiftCode: [
          { required: true, message: '请输入SWIFT码', trigger: 'blur' }
        ],
        transactionId: [
          { required: true, message: '请输入唯一交易ID', trigger: 'blur' }
        ]
      },
      searchRules: {
        accountId: [
          { required: false, trigger: 'blur' }, { validator: isNum, trigger: 'blur' }
        ]
      }
    };
  },
  mounted: function() {
    this.doSearch();
    this.doInitData();
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
    dialogConfirm: function(row, column) {
      this.$http({
        url: '/asset/deposit/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.depositForm = {
            'id': res.object.id,
            'accountId': res.object.accountId,
            'currency': res.object.currency,
            'remittingBank': res.object.remittingBank,
            'accountNumber': res.object.accountNumber,
            'amount': res.object.amount,
            'fee': res.object.fee,
            'swiftCode': res.object.swiftCode,
            'transactionId': res.object.transactionId,
            'status': res.object.status,
            'depositDate': res.object.depositDate,
            'transferType': res.object.transferType,
            'remark': res.object.remark
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.confirmDialog = true;
    },
    dialogEdit: function(row, column) {
      this.$http({
        url: '/asset/deposit/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.depositForm = {
            'id': res.object.id,
            'accountId': res.object.accountId,
            'currency': res.object.currency,
            'remittingBank': res.object.remittingBank,
            'accountNumber': res.object.accountNumber,
            'amount': res.object.amount,
            'fee': res.object.fee,
            'swiftCode': res.object.swiftCode,
            'transactionId': res.object.transactionId,
            'status': res.object.status,
            'depositDate': res.object.depositDate,
            'transferType': res.object.transferType,
            'remark': res.object.remark
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.depositDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          this.depositLoading = true;
          this.$http({
            url: '/asset/deposit/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.depositData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.depositLoading = false;
            }
          }).catch(error => {
            this.$message.error(error);
          });
        }
      });
    },
    doInitData: function() {
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
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/asset/deposit/save',
            method: 'post',
            params: this.depositForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.doSearch();
            } else {
              this.$message.error(res.message || 'Has Error');
            }
          }).catch(error => {
            this.$message.error(error);
          });
          this.depositDialog = false;
        }
      });
    },
    doConfirm: function() {
      this.depositForm.status = 'confirmed';
      this.depositForm.depositConfirmDate = new Date().valueOf();
      this.$http({
        url: '/asset/deposit/check',
        method: 'post',
        params: this.depositForm
      }).then(res => {
        if (res.code === 200) {
          this.$message.success(res.message);
          this.doSearch();
        } else {
          this.$message.error(res.message || 'Has Error');
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.confirmDialog = false;
    }
  }
};
</script>

<style lang="scss">
  .depositForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
  .depositForm .confirmTxt {
    text-align: center;
    border: 1px solid #DCDFE6;
    min-height: 42px;
    border-radius: 5px;
  }
</style>
