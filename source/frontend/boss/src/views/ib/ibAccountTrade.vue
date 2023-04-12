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

    <el-table v-loading="ibAccountTradeLoading" :data="ibAccountTradeData" style="width:100%;margin-bottom:20px;" border row-key="id" highlight-current-row>
      <el-table-column prop="accountId" label="账户ID" width="170px" />
      <el-table-column prop="accountName" label="账户名" />
      <el-table-column prop="accountCode" label="IB账户代码" width="120px" />
      <el-table-column prop="symbol" label="金融产品代码" />
      <el-table-column prop="secType" label="金融产品类型" />
      <el-table-column prop="currency" label="币种" />
      <el-table-column prop="exchange" label="交易所 " />
      <el-table-column prop="execId" label="成交执行ID" />
      <el-table-column prop="orderId" label="订单ID" />
      <el-table-column prop="side" label="方向" />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="shares" label="数量" />
      <el-table-column prop="avgPrice" label="成交均价" />
      <el-table-column prop="execTime" label="执行时间" />
      <el-table-column prop="createDate" :formatter="dateFormat" width="200" label="创建时间" />
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

    <el-dialog title="当日盈亏" :visible.sync="ibAccountTradeDialog" width="660px">
      <el-form ref="ibAccountTradeForm" :inline="true" :model="ibAccountTradeForm" :rules="ibAccountTradePnLRules" label-width="100px" class="ibAccountTradeForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="ibAccountTradeForm.accountId" />
        </el-form-item>
        <el-form-item label="IB账户代码" prop="accountCode">
          <el-input v-model="ibAccountTradeForm.accountCode" />
        </el-form-item>
        <el-form-item label="金融产品代码" prop="symbol">
          <el-input v-model="ibAccountTradeForm.symbol" />
        </el-form-item>
        <el-form-item label="金融产品类型" prop="secType">
          <el-input v-model="ibAccountTradeForm.secType" />
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select v-model="ibAccountTradeForm.currency" placeholder="请选择币种" value="" style="width: 180px;">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易所" prop="exchange">
          <el-select v-model="ibAccountTradeForm.exchange" placeholder="请选择交易所" value="" style="width: 180px;">
            <el-option v-for="data in exchangeData" :key="data.id" :label="data.exchangeName" :value="data.id">
              <span style="float:left;">{{ data.exchangeCode }}</span>
              <span style="float:right">{{ data.exchangeName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成交执行ID" prop="execId">
          <el-input v-model="ibAccountTradeForm.execId" />
        </el-form-item>
        <el-form-item label="订单ID" prop="orderId">
          <el-input v-model="ibAccountTradeForm.orderId" />
        </el-form-item>
        <el-form-item label="方向" prop="side">
          <el-input v-model="ibAccountTradeForm.side" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="ibAccountTradeForm.price" />
        </el-form-item>
        <el-form-item label="数量" prop="shares">
          <el-input v-model="ibAccountTradeForm.shares" />
        </el-form-item>
        <el-form-item label="成交均价" prop="avgPrice">
          <el-input v-model="ibAccountTradeForm.avgPrice" />
        </el-form-item>
        <el-form-item label="执行时间" prop="execTime">
          <el-date-picker v-model="ibAccountTradeForm.execTime" type="datetime" value-format="yyyyMMdd  HH:mm:ss" placeholder="选择执行时间" style="width: 180px;"></el-date-picker>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('ibAccountTradeForm')">提交</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isNum, isInteger } from '../../utils/validate.js';
export default {
  name: 'IbAccountTrade',
  data() {
    return {
      ibAccountTradeLoading: '',
      ibAccountTradeDialog: false,
      ibAccountTradeData: [],
      baseCurrencyData: [],
      exchangeData: [],
      ibAccountTradeForm: {
        id: '',
        accountId: '',
        accountCode: '',
        symbol: '',
        secType: '',
        currency: '',
        exchange: '',
        execId: '',
        orderId: '',
        side: '',
        price: '',
        shares: '',
        avgPrice: '',
        execTime: ''
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
      ibAccountTradePnLRules: {
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
        exchange: [
          { required: true, message: '请选择交易所', trigger: 'change' }
        ],
        execId: [
          { required: false, message: '请输入成交执行ID', trigger: 'blur' }
        ],
        orderId: [
          { required: false, message: '请输入订单ID', trigger: 'blur' }
        ],
        side: [
          { required: false, message: '请输入方向', trigger: 'blur' }
        ],
        price: [
          { required: false, message: '请输入价格', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        shares: [
          { required: false, message: '请输入数量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        avgPrice: [
          { required: false, message: '请输入成交均价', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
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
      this.ibAccountTradeForm = {
        id: '',
        accountId: '',
        accountCode: '',
        symbol: '',
        secType: '',
        currency: '',
        exchange: '',
        execId: '',
        orderId: '',
        side: '',
        price: '',
        shares: '',
        avgPrice: '',
        execTime: ''
      };
      if (this.$refs.ibAccountDailyPnLForm) {
        this.$refs.ibAccountDailyPnLForm.resetFields();
      }
      this.ibAccountTradeDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.ibAccountDailyPnLForm) {
        this.$refs.ibAccountDailyPnLForm.resetFields();
      }
      this.$http({
        url: '/ib/ibAccountTrade/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.ibAccountTradeForm = {
            id: res.object.id,
            accountId: res.object.accountId,
            accountCode: res.object.accountCode,
            symbol: res.object.symbol,
            secType: res.object.secType,
            currency: res.object.currency,
            exchange: res.object.exchange,
            execId: res.object.execId,
            orderId: res.object.orderId,
            side: res.object.side,
            price: res.object.price,
            shares: res.object.shares,
            avgPrice: res.object.avgPrice,
            execTime: res.object.execTime
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.ibAccountTradeDialog = true;
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/ib/ibAccountTrade/save',
            method: 'post',
            params: this.ibAccountTradeForm
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
          this.ibAccountTradeDialog = false;
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
          url: '/ib/ibAccountTrade/del',
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
          this.ibAccountTradeLoading = true;
          this.$http({
            url: '/ib/ibAccountTrade/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.ibAccountTradeData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.ibAccountTradeLoading = false;
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
