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

    <el-table v-loading="withdrawLoading" :data="withdrawData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="accountId" label="账户ID" />
      <el-table-column prop="accountName" label="账户名" />
      <el-table-column prop="currency" label="币种" :formatter="dictFormat" />
      <el-table-column prop="beneBank" label="收款银行" :formatter="dictFormat" />
      <el-table-column prop="accountNumber" label="收款银行账号" />
      <el-table-column prop="transferType" label="转账方式" :formatter="dictFormat" />
      <el-table-column prop="amount" label="提现出金数量" />
      <el-table-column prop="fee" label="提现手续费" />
      <el-table-column prop="swiftCode" label="SWIFT码" />
      <el-table-column prop="transactionId" label="唯一交易ID" />
      <el-table-column prop="status" label="状态" :formatter="dictFormat" />
      <el-table-column prop="withdrawDate" label="提现出金申请时间" :formatter="dateFormat" />
      <el-table-column prop="withdrawConfirmDate" label="提现出金确认时间" :formatter="dateFormat" />
      <el-table-column prop="remark" label="备注" />
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button v-if="scope.row.status === 'unconfirmed'" size="mini" type="success" @click="dialogConfirm(scope.$index, scope.row)">审核</el-button>
          <!--          <el-button v-if="scope.row.status === 'unconfirmed'" size="mini" type="danger" @click="dialogEdit(scope.$index, scope.row)">编辑</el-button>-->
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

    <el-dialog title="提现审核" :visible.sync="confirmDialog" width="700px">
      <el-form ref="confirmForm" :inline="true" :model="withdrawForm" :rules="withdrawRules" label-width="120px" class="withdrawForm">
        <el-form-item label="账户ID">
          <div class="confirmTxt">{{ withdrawForm.accountId }}</div>
        </el-form-item>
        <el-form-item label="币种">
          <div class="confirmTxt">{{ withdrawForm.currency }}</div>
        </el-form-item>
        <el-form-item label="收款银行">
          <div class="confirmTxt">
            <div v-for="data in beneBankData" :key="data.id">
              <span v-if="withdrawForm.beneBank === data.dictValue">{{ data.dictDest }}</span>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="收款银行账号">
          <div class="confirmTxt">{{ withdrawForm.accountNumber }}</div>
        </el-form-item>
        <el-form-item label="提现出金数量">
          <div class="confirmTxt">{{ withdrawForm.amount }}</div>
        </el-form-item>
        <el-form-item label="手续费">
          <div class="confirmTxt">{{ withdrawForm.fee }}</div>
        </el-form-item>
        <el-form-item label="SWIFT码">
          <div class="confirmTxt">{{ withdrawForm.swiftCode }}</div>
        </el-form-item>
        <el-form-item label="唯一交易ID">
          <div class="confirmTxt">{{ withdrawForm.transactionId }}</div>
        </el-form-item>
        <el-form-item label="备注">
          <div class="confirmTxt">{{ withdrawForm.remark }}</div>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:120px;" type="primary" @click="doConfirm()">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
    <el-dialog title="提现编辑" :visible.sync="editDialog" width="700px">
      <el-form ref="withdrawForm" :inline="true" :model="withdrawForm" :rules="withdrawRules" label-width="120px" class="withdrawForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="withdrawForm.accountId"></el-input>
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select v-model="withdrawForm.currency" placeholder="请选择币种" value="">
            <el-option v-for="data in currencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收款银行" prop="beneBank">
          <el-select v-model="withdrawForm.beneBank" placeholder="请选择收款银行" value="">
            <el-option v-for="data in beneBankData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="收款银行账号" prop="accountNumber">
          <el-input v-model="withdrawForm.accountNumber"></el-input>
        </el-form-item>
        <el-form-item label="提现出金数量" prop="amount">
          <el-input v-model="withdrawForm.amount"></el-input>
        </el-form-item>
        <el-form-item label="手续费" prop="fee">
          <el-input v-model="withdrawForm.fee"></el-input>
        </el-form-item>
        <el-form-item label="SWIFT码" prop="swiftCode">
          <el-input v-model="withdrawForm.swiftCode"></el-input>
        </el-form-item>
        <el-form-item label="唯一交易ID" prop="transactionId">
          <el-input v-model="withdrawForm.transactionId"></el-input>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="withdrawForm.remark" type="textarea"></el-input>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:120px;" type="primary" @click="doSubmit('withdrawForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isInteger, isNum } from '../../utils/validate.js';
export default {
  name: 'AssetWithdraw',
  data() {
    return {
      withdrawLoading: '',
      confirmDialog: false,
      editDialog: false,
      withdrawData: [],
      baseCurrencyData: [],
      currencyData: [],
      transferTypeData: [],
      beneBankData: [],
      statusData: [],
      withdrawForm: {
        'id': '',
        'accountId': '',
        'currency': '',
        'beneBank': '',
        'accountNumber': '',
        'amount': '',
        'fee': '',
        'swiftCode': '',
        'transactionId': '',
        'status': '',
        'withdrawDate': '',
        'withdrawConfirmDate': '',
        'transferType': '',
        'remark': ''
      },
      searchForm: {
        'accountId': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      withdrawRules: {
        accountId: [
          { required: true, message: '请输入账户ID', trigger: 'blur' }
        ],
        currency: [
          { required: true, message: '请选择币种', trigger: 'change' }
        ],
        beneBank: [
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
        url: '/asset/withdraw/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.withdrawForm = {
            'id': res.object.id,
            'accountId': res.object.accountId,
            'currency': res.object.currency,
            'beneBank': res.object.beneBank,
            'accountNumber': res.object.accountNumber,
            'amount': res.object.amount,
            'fee': res.object.fee,
            'swiftCode': res.object.swiftCode,
            'transactionId': res.object.transactionId,
            'status': res.object.status,
            'withdrawDate': res.object.withdrawDate,
            'withdrawConfirmDate': res.object.withdrawConfirmDate,
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
        url: '/asset/withdraw/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.withdrawForm = {
            'id': res.object.id,
            'accountId': res.object.accountId,
            'currency': res.object.currency,
            'beneBank': res.object.beneBank,
            'accountNumber': res.object.accountNumber,
            'amount': res.object.amount,
            'fee': res.object.fee,
            'swiftCode': res.object.swiftCode,
            'transactionId': res.object.transactionId,
            'status': res.object.status,
            'withdrawDate': res.object.withdrawDate,
            'withdrawConfirmDate': res.object.withdrawConfirmDate,
            'transferType': res.object.transferType,
            'remark': res.object.remark
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.editDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          this.withdrawLoading = true;
          this.$http({
            url: '/asset/withdraw/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.withdrawData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.withdrawLoading = false;
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
          dictCode: 'withdrawStatus',
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
            url: '/asset/withdraw/save',
            method: 'post',
            params: this.withdrawForm
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
          this.editDialog = false;
        }
      });
    },
    doConfirm: function() {
      this.withdrawForm.status = 'confirmed';
      this.withdrawForm.withdrawConfirmDate = new Date().valueOf();
      this.$http({
        url: '/asset/withdraw/check',
        method: 'post',
        params: this.withdrawForm
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
  .withdrawForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
  .withdrawForm .confirmTxt {
    text-align: center;
    border: 1px solid #DCDFE6;
    min-height: 42px;
    border-radius: 5px;
  }
</style>
