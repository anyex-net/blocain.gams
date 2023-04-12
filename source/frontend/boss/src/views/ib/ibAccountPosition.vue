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

    <el-table v-loading="ibAccountPositionLoading" :data="ibAccountPositionData" style="width:100%;margin-bottom:20px;" border row-key="id" highlight-current-row>
      <el-table-column prop="accountId" label="账户ID" width="170px" :fixed="true" />
      <el-table-column prop="accountName" label="账户名" :fixed="true" />
      <el-table-column prop="accountCode" label="IB账户代码" width="120px" :fixed="true" />
      <el-table-column prop="symbol" label="金融产品代码" width="120px" :fixed="true" />
      <el-table-column prop="secType" label="金融产品类型" width="120px" :fixed="true" />
      <el-table-column prop="currency" label="币种" />
      <el-table-column prop="exchange" label="交易所 " />
      <el-table-column prop="financialInstrument" label="金融产品描述" />
      <el-table-column prop="companyName" label="公司名称" />
      <el-table-column prop="position" label="持仓头寸" />
      <el-table-column prop="unsettledPosition" label="未结算持仓头寸" />
      <el-table-column prop="loanableQty" label="可借数量" />
      <el-table-column prop="privateLocate" label="PrivateLocate" />
      <el-table-column prop="deltaDollars" label="Delta美元" />
      <el-table-column prop="marketPrice" label="市价" />
      <el-table-column prop="marketValue" label="市场价值" />
      <el-table-column prop="averageCost" label="平均价格" />
      <el-table-column prop="unrealizedPnl" label="未实现盈亏" />
      <el-table-column prop="realizedPnl" label="已实现盈亏" />
      <el-table-column prop="liquidateLast" label="最后清算" />
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

    <el-dialog title="当日盈亏" :visible.sync="ibAccountPositionDialog" width="660px">
      <el-form ref="ibAccountPositionForm" :inline="true" :model="ibAccountPositionForm" label-width="100px" class="ibAccountPositionForm" :rules="ibAccountPositionRules">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="ibAccountPositionForm.accountId" />
        </el-form-item>
        <el-form-item label="IB账户代码" prop="accountCode">
          <el-input v-model="ibAccountPositionForm.accountCode" />
        </el-form-item>
        <el-form-item label="金融产品代码" prop="symbol">
          <el-input v-model="ibAccountPositionForm.symbol" />
        </el-form-item>
        <el-form-item label="金融产品类型" prop="secType">
          <el-input v-model="ibAccountPositionForm.secType" />
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select v-model="ibAccountPositionForm.currency" placeholder="请选择币种" value="" style="width: 180px;">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易所" prop="exchange">
          <el-select v-model="ibAccountPositionForm.exchange" placeholder="请选择交易所" value="" style="width: 180px;">
            <el-option v-for="data in exchangeData" :key="data.id" :label="data.exchangeName" :value="data.id">
              <span style="float:left;">{{ data.exchangeCode }}</span>
              <span style="float:right">{{ data.exchangeName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="金融产品描述" prop="financialInstrument">
          <el-input v-model="ibAccountPositionForm.financialInstrument" />
        </el-form-item>
        <el-form-item label="公司名称" prop="companyName">
          <el-input v-model="ibAccountPositionForm.companyName" />
        </el-form-item>
        <el-form-item label="持仓头寸" prop="position">
          <el-input v-model="ibAccountPositionForm.position" />
        </el-form-item>
        <el-form-item label="未结算持仓头寸" prop="unsettledPosition">
          <el-input v-model="ibAccountPositionForm.unsettledPosition" />
        </el-form-item>
        <el-form-item label="可借数量" prop="loanableQty">
          <el-input v-model="ibAccountPositionForm.loanableQty" />
        </el-form-item>
        <el-form-item label="privateLocate" prop="privateLocate">
          <el-input v-model="ibAccountPositionForm.privateLocate" />
        </el-form-item>
        <el-form-item label="Delta美元" prop="deltaDollars">
          <el-input v-model="ibAccountPositionForm.deltaDollars" />
        </el-form-item>
        <el-form-item label="市价" prop="marketPrice">
          <el-input v-model="ibAccountPositionForm.marketPrice" />
        </el-form-item>
        <el-form-item label="市场价值" prop="marketValue">
          <el-input v-model="ibAccountPositionForm.marketValue" />
        </el-form-item>
        <el-form-item label="持仓头寸" prop="position">
          <el-input v-model="ibAccountPositionForm.position" />
        </el-form-item>
        <el-form-item label="平均价格" prop="averageCost">
          <el-input v-model="ibAccountPositionForm.averageCost" />
        </el-form-item>
        <el-form-item label="未实现盈亏" prop="unrealizedPnl">
          <el-input v-model="ibAccountPositionForm.unrealizedPnl" />
        </el-form-item>
        <el-form-item label="已实现盈亏" prop="realizedPnl">
          <el-input v-model="ibAccountPositionForm.realizedPnl" />
        </el-form-item>
        <el-form-item label="最后清算" prop="liquidateLast">
          <el-input v-model="ibAccountPositionForm.liquidateLast" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('ibAccountPositionForm')">提交</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { checkNum, isNum, isInteger } from '../../utils/validate.js';
export default {
  name: 'IbAccountPosition',
  data() {
    return {
      ibAccountPositionLoading: '',
      ibAccountPositionDialog: false,
      ibAccountPositionData: [],
      baseCurrencyData: [],
      exchangeData: [],
      ibAccountPositionForm: {
        id: '',
        accountId: '',
        accountCode: '',
        symbol: '',
        secType: '',
        currency: '',
        financialInstrument: '',
        companyName: '',
        exchange: '',
        position: '',
        unsettledPosition: '',
        loanableQty: '',
        privateLocate: '',
        deltaDollars: '',
        marketPrice: '',
        marketValue: '',
        averageCost: '',
        unrealizedPnl: '',
        realizedPnl: '',
        liquidateLast: ''
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
      ibAccountPositionRules: {
        accountId: [
          { required: true, message: '请输入账户ID', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
        ],
        accountCode: [
          { required: true, message: '请输入IB对应AccountCode', trigger: 'blur' }
        ],
        symbol: [
          { required: true, message: '请输入金融产品代码', trigger: 'blur' }
        ],
        secType: [
          { required: true, message: '请输入金融产品类型', trigger: 'blur' }
        ],
        currency: [
          { required: true, message: '请选择币种', trigger: 'change' }
        ],
        financialInstrument: [
          { required: false, message: '请输入金融产品描述', trigger: 'blur' }
        ],
        companyName: [
          { required: false, message: '请输入公司名称', trigger: 'blur' }
        ],
        exchange: [
          { required: false, message: '请选择交易所', trigger: 'change' }
        ],
        position: [
          { required: false, message: '请输入持仓头寸', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        unsettledPosition: [
          { required: false, message: '请输入未结算持仓头寸', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        loanableQty: [
          { required: false, message: '请输入可借数量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        privateLocate: [
          { required: false, message: '请输入PrivateLocate', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        deltaDollars: [
          { required: false, message: '请输入Delta美元', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        marketPrice: [
          { required: false, message: '请输入市价', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        marketValue: [
          { required: false, message: '请输入市场价值', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        averageCost: [
          { required: false, message: '请输入平均价格', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        unrealizedPnl: [
          { required: false, message: '请输入未实现盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        realizedPnl: [
          { required: false, message: '请输入已实现盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        liquidateLast: [
          { required: false, message: '请输入最后清算', trigger: 'blur' }
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
    exchangeFormat: function(row, column) {
      const exchangeArray = this.exchangeData;
      let exchangeName = '';
      exchangeArray.forEach((data) => {
        if (row[column.property] === data.id) {
          exchangeName = data.exchangeName;
        }
      });
      return exchangeName;
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
        url: '/stock/exchange/findAll',
        method: 'get'
      }).then(res => {
        if (res.code === 200) {
          this.exchangeData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
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
      this.ibAccountPositionForm = {
        id: '',
        accountId: '',
        accountCode: '',
        symbol: '',
        secType: '',
        currency: '',
        exchange: '',
        financialInstrument: '',
        companyName: '',
        position: '',
        unsettledPosition: '',
        loanableQty: '',
        privateLocate: '',
        deltaDollars: '',
        marketPrice: '',
        marketValue: '',
        averageCost: '',
        unrealizedPnl: '',
        realizedPnl: '',
        liquidateLast: ''
      };
      if (this.$refs.ibAccountPositionForm) {
        this.$refs.ibAccountPositionForm.resetFields();
      }
      this.ibAccountPositionDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.ibAccountPositionForm) {
        this.$refs.ibAccountPositionForm.resetFields();
      }
      this.$http({
        url: '/ib/ibAccountPosition/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.ibAccountPositionForm = {
            id: res.object.id,
            accountId: res.object.accountId,
            accountCode: res.object.accountCode,
            symbol: res.object.symbol,
            secType: res.object.secType,
            currency: res.object.currency,
            exchange: res.object.exchange,
            financialInstrument: res.object.financialInstrument,
            companyName: res.object.companyName,
            position: res.object.position,
            unsettledPosition: res.object.unsettledPosition,
            loanableQty: res.object.loanableQty,
            privateLocate: res.object.privateLocate,
            deltaDollars: res.object.deltaDollars,
            marketPrice: res.object.marketPrice,
            marketValue: res.object.marketValue,
            averageCost: res.object.averageCost,
            unrealizedPnl: res.object.unrealizedPnl,
            realizedPnl: res.object.realizedPnl,
            liquidateLast: res.object.liquidateLast
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.ibAccountPositionDialog = true;
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/ib/ibAccountPosition/save',
            method: 'post',
            params: this.ibAccountPositionForm
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
          this.ibAccountPositionDialog = false;
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
          url: '/ib/ibAccountPosition/del',
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
          this.ibAccountPositionLoading = true;
          this.$http({
            url: '/ib/ibAccountPosition/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.ibAccountPositionData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.ibAccountPositionLoading = false;
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
