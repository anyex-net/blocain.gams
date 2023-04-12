<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini" :rules="searchRules">
      <el-form-item label="账户ID" prop="accountId">
        <el-input v-model="searchForm.accountId" clearable placeholder="请输入账户ID"></el-input>
      </el-form-item>
      <el-form-item label="IB账户代码">
        <el-input v-model="searchForm.accountCode" clearable placeholder="请输入IB账户代码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="ibAccountMarketValueLoading" :data="ibAccountMarketValueData" style="width:100%;margin-bottom:20px;" border row-key="id" highlight-current-row>
      <el-table-column prop="accountId" label="账户ID" width="170px" :fixed="true" />
      <el-table-column prop="accountName" label="账户名" :fixed="true" />
      <el-table-column prop="accountCode" label="IB账户代码" width="120px" :fixed="true" />
      <el-table-column prop="currency" label="币种" :fixed="true" />
      <el-table-column prop="totalCashBalance" label="总计现金" :fixed="true" />
      <el-table-column prop="cashBalance" label="已结算现金" />
      <el-table-column prop="accruedCash" label="应计现金 " />
      <el-table-column prop="stockMarketValue" label="股票" />
      <el-table-column prop="optionMarketValue" label="期权" />
      <el-table-column prop="futureOptionValue" label="期货期权" />
      <el-table-column prop="futuresPnl" label="期货" />
      <el-table-column prop="netLiquidationByCurrency" label="净清算折算货币" />
      <el-table-column prop="fundValue" label="基金" />
      <el-table-column prop="netDividend" label="应计股息" />
      <el-table-column prop="mutualFundValue" label="共同基金" />
      <el-table-column prop="moneyMarketFundValue" label="货币市场基金" />
      <el-table-column prop="corporateBondValue" label="公司债" />
      <el-table-column prop="tBondValue" label="长期国债" />
      <el-table-column prop="tBillValue" label="短期国债" />
      <el-table-column prop="warrantValue" label="权证 " />
      <el-table-column prop="fxCashBalance" label="外汇现金" />
      <el-table-column prop="accountOrGroup" label="账户或分组" />
      <el-table-column prop="realCurrency" label="实际币种" :formatter="dictFormat" />
      <el-table-column prop="issuerOptionValue" label="发行方期权" />
      <el-table-column prop="unrealizedPnl" label="未实现盈亏" />
      <el-table-column prop="realizedPnl" label="已实现盈亏" />
      <el-table-column prop="exchangeRate" label="汇率" />
      <el-table-column prop="updateDate" :formatter="dateFormat" width="200" label="更新时间" />
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="dialogEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="doDelete(scope.$index, scope.row)">删除</el-button>
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

    <el-dialog title="当日盈亏" :visible.sync="ibAccountMarketValueDialog" width="660px">
      <el-form ref="ibAccountMarketValueForm" :inline="true" :model="ibAccountMarketValueForm" :rules="ibAccountMarketValuePnLRules" label-width="100px" class="ibAccountMarketValueForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="ibAccountMarketValueForm.accountId" />
        </el-form-item>
        <el-form-item label="IB账户代码" prop="accountCode">
          <el-input v-model="ibAccountMarketValueForm.accountCode" />
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select v-model="ibAccountMarketValueForm.currency" placeholder="请选择币种" value="" style="width: 180px;">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="总计现金" prop="totalCashBalance">
          <el-input v-model="ibAccountMarketValueForm.totalCashBalance" />
        </el-form-item>
        <el-form-item label="已结算现金" prop="cashBalance">
          <el-input v-model="ibAccountMarketValueForm.cashBalance" />
        </el-form-item>
        <el-form-item label="应计现金" prop="accruedCash">
          <el-input v-model="ibAccountMarketValueForm.accruedCash" />
        </el-form-item>
        <el-form-item label="股票" prop="stockMarketValue">
          <el-input v-model="ibAccountMarketValueForm.stockMarketValue" />
        </el-form-item>
        <el-form-item label="期权" prop="optionMarketValue">
          <el-input v-model="ibAccountMarketValueForm.optionMarketValue" />
        </el-form-item>
        <el-form-item label="期货期权" prop="futureOptionValue">
          <el-input v-model="ibAccountMarketValueForm.futureOptionValue" />
        </el-form-item>
        <el-form-item label="期货" prop="futuresPnl">
          <el-input v-model="ibAccountMarketValueForm.futuresPnl" />
        </el-form-item>
        <el-form-item label="净清算折算货币" prop="netLiquidationByCurrency">
          <el-input v-model="ibAccountMarketValueForm.netLiquidationByCurrency" />
        </el-form-item>
        <el-form-item label="基金" prop="fundValue">
          <el-input v-model="ibAccountMarketValueForm.fundValue" />
        </el-form-item>
        <el-form-item label="应计股息" prop="netDividend">
          <el-input v-model="ibAccountMarketValueForm.netDividend" />
        </el-form-item>
        <el-form-item label="共同基金" prop="mutualFundValue">
          <el-input v-model="ibAccountMarketValueForm.mutualFundValue" />
        </el-form-item>
        <el-form-item label="货币市场基金" prop="moneyMarketFundValue">
          <el-input v-model="ibAccountMarketValueForm.moneyMarketFundValue" />
        </el-form-item>
        <el-form-item label="总计现金" prop="totalCashBalance">
          <el-input v-model="ibAccountMarketValueForm.totalCashBalance" />
        </el-form-item>
        <el-form-item label="公司债" prop="corporateBondValue">
          <el-input v-model="ibAccountMarketValueForm.corporateBondValue" />
        </el-form-item>
        <el-form-item label="长期国债" prop="tBondValue">
          <el-input v-model="ibAccountMarketValueForm.tBondValue" />
        </el-form-item>
        <el-form-item label="短期国债" prop="tBillValue">
          <el-input v-model="ibAccountMarketValueForm.tBillValue" />
        </el-form-item>
        <el-form-item label="权证" prop="warrantValue">
          <el-input v-model="ibAccountMarketValueForm.warrantValue" />
        </el-form-item>
        <el-form-item label="外汇现金" prop="fxCashBalance">
          <el-input v-model="ibAccountMarketValueForm.fxCashBalance" />
        </el-form-item>
        <el-form-item label="账户或分组" prop="accountOrGroup">
          <el-input v-model="ibAccountMarketValueForm.accountOrGroup" />
        </el-form-item>
        <el-form-item label="实际币种" prop="realCurrency">
          <el-select v-model="ibAccountMarketValueForm.realCurrency" placeholder="请选择实际币种" value="" style="width: 180px;">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发行方期权" prop="issuerOptionValue">
          <el-input v-model="ibAccountMarketValueForm.issuerOptionValue" />
        </el-form-item>
        <el-form-item label="未实现盈亏" prop="unrealizedPnl">
          <el-input v-model="ibAccountMarketValueForm.unrealizedPnl" />
        </el-form-item>
        <el-form-item label="已实现盈亏" prop="realizedPnl">
          <el-input v-model="ibAccountMarketValueForm.realizedPnl" />
        </el-form-item>
        <el-form-item label="汇率" prop="exchangeRate">
          <el-input v-model="ibAccountMarketValueForm.exchangeRate" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('ibAccountMarketValueForm')">提交</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isNum, isInteger } from '../../utils/validate.js';
export default {
  name: 'IbAccountMarketValue',
  data() {
    return {
      ibAccountMarketValueLoading: '',
      ibAccountMarketValueData: [],
      baseCurrencyData: [],
      exchangeData: [],
      ibAccountMarketValueDialog: false,
      ibAccountMarketValueForm: {
        id: '',
        accountId: '',
        accountCode: '',
        totalCashBalance: '',
        cashBalance: '',
        currency: '',
        accruedCash: '',
        stockMarketValue: '',
        optionMarketValue: '',
        futureOptionValue: '',
        futuresPnl: '',
        netLiquidationByCurrency: '',
        fundValue: '',
        netDividend: '',
        mutualFundValue: '',
        moneyMarketFundValue: '',
        corporateBondValue: '',
        tBondValue: '',
        tBillValue: '',
        warrantValue: '',
        fxCashBalance: '',
        accountOrGroup: '',
        realCurrency: '',
        issuerOptionValue: '',
        unrealizedPnl: '',
        realizedPnl: '',
        exchangeRate: ''
      },
      searchForm: {
        accountId: '',
        accountCode: ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      ibAccountMarketValuePnLRules: {
        accountId: [
          { required: true, message: '请输入账户ID', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
        ],
        accountCode: [
          { required: true, message: '请输入IB对应AccountCode', trigger: 'blur' }
        ],
        currency: [
          { required: true, message: '请选择币种', trigger: 'change' }
        ],
        totalCashBalance: [
          { required: false, message: '请输入总计现金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        cashBalance: [
          { required: false, message: '请输入已结算现金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        accruedCash: [
          { required: false, message: '请输入应计现金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        stockMarketValue: [
          { required: false, message: '请输入股票', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        optionMarketValue: [
          { required: false, message: '请输入期权', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        futureOptionValue: [
          { required: false, message: '请输入期货期权', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        futuresPnl: [
          { required: false, message: '请输入期货', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        netLiquidationByCurrency: [
          { required: false, message: '请输入净清算折算货币', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        fundValue: [
          { required: false, message: '请输入基金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        netDividend: [
          { required: false, message: '请输入应计股息', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        mutualFundValue: [
          { required: false, message: '请输入共同基金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        moneyMarketFundValue: [
          { required: false, message: '请输入货币市场基金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        corporateBondValue: [
          { required: false, message: '请输入公司债', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        tBondValue: [
          { required: false, message: '请输入长期国债', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        tBillValue: [
          { required: false, message: '请输入短期国债', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        warrantValue: [
          { required: false, message: '请输入权证', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        fxCashBalance: [
          { required: false, message: '请输入外汇现金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        accountOrGroup: [
          { required: false, message: '请输入账户或分组', trigger: 'blur' }
        ],
        realCurrency: [
          { required: false, message: '请输入实际币种', trigger: 'blur' }
        ],
        issuerOptionValue: [
          { required: false, message: '请输入发行方期权', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        unrealizedPnl: [
          { required: false, message: '请输入未实现盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        realizedPnl: [
          { required: false, message: '请输入已实现盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        exchangeRate: [
          { required: false, message: '请输入汇率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
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
      const dictArray = this.baseCurrencyData;
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row[column.property]) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
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
          this.baseCurrencyData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    dialogAdd() {
      this.ibAccountMarketValueForm = {
        id: '',
        accountId: '',
        accountCode: '',
        totalCashBalance: '',
        cashBalance: '',
        currency: '',
        accruedCash: '',
        stockMarketValue: '',
        optionMarketValue: '',
        futureOptionValue: '',
        futuresPnl: '',
        netLiquidationByCurrency: '',
        fundValue: '',
        netDividend: '',
        mutualFundValue: '',
        moneyMarketFundValue: '',
        corporateBondValue: '',
        tBondValue: '',
        tBillValue: '',
        warrantValue: '',
        fxCashBalance: '',
        accountOrGroup: '',
        realCurrency: '',
        issuerOptionValue: '',
        unrealizedPnl: '',
        realizedPnl: '',
        exchangeRate: ''
      };
      if (this.$refs.ibAccountMarketValueForm) {
        this.$refs.ibAccountMarketValueForm.resetFields();
      }
      this.ibAccountMarketValueDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.ibAccountMarketValueForm) {
        this.$refs.ibAccountMarketValueForm.resetFields();
      }
      this.$http({
        url: '/ib/ibAccountMarketValue/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.ibAccountMarketValueForm = {
            id: res.object.id,
            accountId: res.object.accountId,
            accountCode: res.object.accountCode,
            totalCashBalance: res.object.totalCashBalance,
            cashBalance: res.object.cashBalance,
            currency: res.object.currency,
            accruedCash: res.object.accruedCash,
            stockMarketValue: res.object.stockMarketValue,
            optionMarketValue: res.object.optionMarketValue,
            futureOptionValue: res.object.futureOptionValue,
            futuresPnl: res.object.futuresPnl,
            netLiquidationByCurrency: res.object.netLiquidationByCurrency,
            fundValue: res.object.fundValue,
            netDividend: res.object.netDividend,
            mutualFundValue: res.object.mutualFundValue,
            moneyMarketFundValue: res.object.moneyMarketFundValue,
            corporateBondValue: res.object.corporateBondValue,
            tBondValue: res.object.tBondValue,
            tBillValue: res.object.tBillValue,
            warrantValue: res.object.warrantValue,
            fxCashBalance: res.object.fxCashBalance,
            accountOrGroup: res.object.accountOrGroup,
            realCurrency: res.object.realCurrency,
            issuerOptionValue: res.object.issuerOptionValue,
            unrealizedPnl: res.object.unrealizedPnl,
            realizedPnl: res.object.realizedPnl,
            exchangeRate: res.object.exchangeRate
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.ibAccountMarketValueDialog = true;
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/ib/ibAccountMarketValue/save',
            method: 'post',
            params: this.ibAccountMarketValueForm
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
          this.ibAccountMarketValueDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/ib/ibAccountMarketValue/del',
          method: 'post',
          params: {
            ids: column.id
          }
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
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          this.ibAccountMarketValueLoading = true;
          this.$http({
            url: '/ib/ibAccountMarketValue/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.ibAccountMarketValueData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.ibAccountMarketValueLoading = false;
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

</style>
