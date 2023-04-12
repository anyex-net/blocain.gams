<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="证券编码">
        <el-input v-model="searchForm.stockCode" clearable placeholder="请输入证券编码"></el-input>
      </el-form-item>
      <el-form-item label="交易所名称">
        <el-input v-model="searchForm.exchangeName" clearable placeholder="请输入交易所名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="quoteWarrantLoading" :data="quoteWarrantData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="stockCode" label="证券编码" />
      <el-table-column prop="exchangeId" label="交易所" :formatter="exchangeFormat" />
      <el-table-column prop="quoteDate" label="行情日期" :formatter="dateFormat" />
      <el-table-column prop="last" label="最新价" />
      <el-table-column prop="prevClose" label="昨收盘价" />
      <el-table-column prop="bid" label="买一价" />
      <el-table-column prop="ask" label="卖一价" />
      <el-table-column prop="bidSize" label="买入量" />
      <el-table-column prop="askSize" label="卖出量" />
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

    <el-dialog title="权证行情" :visible.sync="quoteWarrantDialog" width="660px">
      <el-form ref="quoteWarrantForm" :inline="true" :model="quoteWarrantForm" :rules="quoteWarrantRules" label-width="100px" class="quoteWarrantForm">

        <el-form-item label="证券信息" prop="stockinfoId">
          <el-select v-model="quoteWarrantForm.stockinfoId" placeholder="请选择证券信息" value="">
            <el-option v-for="data in stockInfoData" :key="data.id" :label="data.stockCode" :value="data.id">
              <span style="float:left;">{{ data.stockCode }}</span>
              <span style="float:right">{{ data.stockNameCn }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易场所" prop="exchangeId">
          <el-select v-model="quoteWarrantForm.exchangeId" placeholder="请选择上市场所" value="">
            <el-option v-for="data in exchangeData" :key="data.id" :label="data.exchangeName" :value="data.id">
              <span style="float:left;">{{ data.exchangeCode }}</span>
              <span style="float:right">{{ data.exchangeName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行情日期" prop="quoteDate">
          <el-date-picker v-model="quoteWarrantForm.quoteDate" type="date" value-format="timestamp" placeholder="选择行情日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="最新价" prop="last">
          <el-input v-model="quoteWarrantForm.last" />
        </el-form-item>
        <el-form-item label="昨收盘价" prop="prevClose">
          <el-input v-model="quoteWarrantForm.prevClose" />
        </el-form-item>
        <el-form-item label="买一价" prop="bid">
          <el-input v-model="quoteWarrantForm.bid" />
        </el-form-item>
        <el-form-item label="卖一价" prop="ask">
          <el-input v-model="quoteWarrantForm.ask" />
        </el-form-item>
        <el-form-item label="买入量" prop="bidSize">
          <el-input v-model="quoteWarrantForm.bidSize" />
        </el-form-item>
        <el-form-item label="卖出量" prop="askSize">
          <el-input v-model="quoteWarrantForm.askSize" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('quoteWarrantForm')">提交</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isBtnZeroToThousand } from '../../utils/validate.js';
export default {
  name: 'QuoteWarrant',
  data() {
    return {
      quoteWarrantLoading: '',
      quoteWarrantDialog: false,
      quoteWarrantData: [],
      stockInfoData: [],
      exchangeData: [],
      quoteWarrantForm: {
        'id': '',
        'stockinfoId': '',
        'stockCode': '',
        'exchangeId': '',
        'quoteDate': '',
        'prevClose': '',
        'last': '',
        'bid': '',
        'ask': '',
        'bidSize': '',
        'askSize': ''
      },
      searchForm: {
        'stockCode': '',
        'exchangeName': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      quoteWarrantRules: {
        stockinfoId: [
          { required: true, message: '请选择证券信息', trigger: 'change' }
        ],
        exchangeId: [
          { required: true, message: '请选择交易场所', trigger: 'change' }
        ],
        quoteDate: [
          { required: true, message: '请选择行情日期', trigger: 'change' }
        ],
        last: [
          { required: true, message: '请输入最新价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        prevClose: [
          { required: true, message: '请输入昨收盘价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        bid: [
          { required: false, message: '请输入买一价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        ask: [
          { required: false, message: '请输入卖一价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        bidSize: [
          { required: false, message: '请输入买入量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        askSize: [
          { required: false, message: '请输入卖出量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
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
      return this.$moment(date).format('YYYY-MM-DD');
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
    dialogAdd: function() {
      this.quoteWarrantForm = {
        'id': '',
        'stockinfoId': '',
        'stockCode': '',
        'exchangeId': '',
        'quoteDate': '',
        'last': '',
        'bid': '',
        'ask': '',
        'bidSize': '',
        'askSize': ''
      };
      if (this.$refs.quoteWarrantForm) {
        this.$refs.quoteWarrantForm.resetFields();
      }
      this.quoteWarrantDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.quoteWarrantForm) {
        this.$refs.quoteWarrantForm.resetFields();
      }
      this.$http({
        url: '/quote/quoteWarrant/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.quoteWarrantForm = {
            'id': res.object.id,
            'stockinfoId': res.object.stockinfoId,
            'stockCode': res.object.stockCode,
            'exchangeId': res.object.exchangeId,
            'quoteDate': res.object.quoteDate,
            'prevClose': res.object.prevClose,
            'last': res.object.last,
            'bid': res.object.bid,
            'ask': res.object.ask,
            'bidSize': res.object.bidSize,
            'askSize': res.object.askSize
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.quoteWarrantDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.quoteWarrantLoading = true;
      this.$http({
        url: '/quote/quoteWarrant/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.quoteWarrantData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.quoteWarrantLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
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
        url: '/stock/info/findListByStockType',
        method: 'get',
        params: {
          stockType: 'warrant'
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockInfoData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/quote/quoteWarrant/save',
            method: 'post',
            params: this.quoteWarrantForm
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
          this.quoteWarrantDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该权证行情, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/quote/quoteWarrant/del',
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
    }
  }
};
</script>

<style lang="scss">
  .quoteWarrantForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
