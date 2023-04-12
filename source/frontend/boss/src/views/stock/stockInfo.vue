<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="证券编码">
        <el-input v-model="searchForm.stockCode" clearable placeholder="请输入证券编码"></el-input>
      </el-form-item>
      <el-form-item label="证券代码">
        <el-input v-model="searchForm.stockSymbol" clearable placeholder="请输入证券代码"></el-input>
      </el-form-item>
      <el-form-item label="交易所名称">
        <el-input v-model="searchForm.exchangeName" clearable placeholder="请输入交易所名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
        <el-button type="warning" icon="el-icon-download" @click="doDownload()">导出</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="stockInfoLoading" :data="stockInfoData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column :fixed="true" prop="stockCode" label="证券编码" />
      <el-table-column :fixed="true" prop="stockSymbol" label="证券代码" />
      <el-table-column prop="stockIsin" label="ISIN码" />
      <el-table-column prop="stockSpellAbbr" label="证券拼音缩写" />
      <el-table-column prop="stockNameEn" label="证券英文名称" />
      <el-table-column prop="stockNameCn" label="证券中文名称" />
      <el-table-column prop="stockType" label="证券类型" :formatter="dictFormat" />
      <el-table-column prop="assetType" label="资产类型" :formatter="dictFormat" />
      <el-table-column prop="stockExchange.exchangeName" label="交易所名称" />
      <el-table-column prop="baseCurrency" label="基础币种" :formatter="dictFormat" />
      <el-table-column prop="companyCode" label="发行人编码" />
      <el-table-column prop="companyName" label="发行人名称" />
      <el-table-column prop="offerDate" label="发行日期" :formatter="dateFormat" />
      <el-table-column prop="listDate" label="上市日期" :formatter="dateFormat" />
      <el-table-column prop="cancelDate" label="退市日期" :formatter="dateFormat" />
      <el-table-column prop="lastPrice" label="最新价格" />
      <el-table-column prop="ibSymbol" label="IB平台代码" />
      <el-table-column label="开放交易">
        <template slot-scope="scope">
          <span v-if="scope.row.isOpentrade === true">是</span>
          <span v-if="scope.row.isOpentrade === false">否</span>
        </template>
      </el-table-column>
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

    <el-dialog title="证劵信息" :visible.sync="stockInfoDialog" width="660px">
      <el-form ref="stockInfoForm" :inline="true" :model="stockInfoForm" :rules="stockInfoRules" label-width="100px" class="stockInfoForm">
        <el-form-item label="证券编码" prop="stockCode">
          <el-input v-model="stockInfoForm.stockCode" />
        </el-form-item>
        <el-form-item label="证券代码" prop="stockSymbol">
          <el-input v-model="stockInfoForm.stockSymbol" />
        </el-form-item>
        <el-form-item label="证券拼音缩写" prop="stockSpellAbbr">
          <el-input v-model="stockInfoForm.stockSpellAbbr" />
        </el-form-item>
        <el-form-item label="ISIN码" prop="stockIsin">
          <el-input v-model="stockInfoForm.stockIsin" />
        </el-form-item>
        <el-form-item label="证券英文名" prop="stockNameEn">
          <el-input v-model="stockInfoForm.stockNameEn" />
        </el-form-item>
        <el-form-item label="证券中文名" prop="stockNameCn">
          <el-input v-model="stockInfoForm.stockNameCn" />
        </el-form-item>
        <el-form-item label="证券类型" prop="stockType">
          <el-select v-model="stockInfoForm.stockType" placeholder="请选择证券类型" value="">
            <el-option v-for="data in stockTypeData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="资产类型" prop="assetType">
          <el-select v-model="stockInfoForm.assetType" placeholder="请选择资产类型" value="">
            <el-option v-for="data in assetTypeData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="上市场所" prop="exchangeId">
          <el-select v-model="stockInfoForm.exchangeId" placeholder="请选择上市场所" value="">
            <el-option v-for="data in exchangeData" :key="data.id" :label="data.exchangeName" :value="data.id">
              <span style="float:left;">{{ data.exchangeCode }}</span>
              <span style="float:right">{{ data.exchangeName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="基础币种" prop="baseCurrency">
          <el-select v-model="stockInfoForm.baseCurrency" placeholder="请选择基础币种" value="">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发行人编码" prop="companyCode">
          <el-input v-model="stockInfoForm.companyCode" />
        </el-form-item>
        <el-form-item label="发行人名称" prop="companyName">
          <el-input v-model="stockInfoForm.companyName" />
        </el-form-item>
        <el-form-item label="发行日期" prop="offerDate">
          <el-date-picker v-model="stockInfoForm.offerDate" type="date" value-format="timestamp" placeholder="选择上市日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="上市日期" prop="listDate">
          <el-date-picker v-model="stockInfoForm.listDate" type="date" value-format="timestamp" placeholder="选择上市日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="退市日期" prop="cancelDate">
          <el-date-picker v-model="stockInfoForm.cancelDate" type="date" value-format="timestamp" placeholder="选择上市日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="最新价格" prop="lastPrice">
          <el-input v-model="stockInfoForm.lastPrice" />
        </el-form-item>
        <el-form-item label="开放交易" prop="isOpentrade">
          <el-select v-model="stockInfoForm.isOpentrade" placeholder="请选择是否开放交易" value="">
            <el-option :key="true" label="是" :value="true"></el-option>
            <el-option :key="false" label="否" :value="false"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="IB平台代码" prop="ibSymbol">
          <el-input v-model="stockInfoForm.ibSymbol" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:100px;" type="primary" @click="doSubmit('stockInfoForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { formatJson } from '@/utils/common';
import { checkNum } from '../../utils/validate.js';
export default {
  name: 'StockInfo',
  data() {
    return {
      stockInfoLoading: '',
      stockInfoDialog: false,
      stockInfoData: [],
      assetTypeData: [],
      baseCurrencyData: [],
      stockTypeData: [],
      exchangeData: [],
      stockInfoForm: {
        'id': '',
        'stockCode': '',
        'stockSymbol': '',
        'stockIsin': '',
        'stockSpellAbbr': '',
        'stockNameEn': '',
        'stockNameCn': '',
        'stockType': '',
        'assetType': '',
        'exchangeId': '',
        'baseCurrency': '',
        'companyCode': '',
        'companyName': '',
        'offerDate': '',
        'listDate': '',
        'cancelDate': '',
        'lastPrice': '',
        'isOpentrade': '',
        'ibSymbol': ''
      },
      searchForm: {
        'stockCode': '',
        'stockSymbol': '',
        'exchangeName': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      stockInfoRules: {
        stockCode: [
          { required: true, message: '请输入证券编码', trigger: 'blur' }
        ],
        stockSymbol: [
          { required: true, message: '请输入证券代码', trigger: 'blur' }
        ],
        stockIsin: [
          { required: true, message: '请输入ISIN码', trigger: 'blur' }
        ],
        stockNameEn: [
          { required: true, message: '请输入证券英文名', trigger: 'blur' }
        ],
        stockNameCn: [
          { required: true, message: '请输入证券中文名', trigger: 'blur' }
        ],
        stockType: [
          { required: true, message: '请选择证券类型', trigger: 'change' }
        ],
        baseCurrency: [
          { required: true, message: '请选择基础币种', trigger: 'change' }
        ],
        assetType: [
          { required: true, message: '请选择资产类型', trigger: 'change' }
        ],
        exchangeId: [
          { required: true, message: '请选择上市场所', trigger: 'change' }
        ],
        lastPrice: [
          { required: true, message: '请输入最新价格', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        isOpentrade: [
          { required: true, message: '请选择是否交易', trigger: 'change' }
        ],
        ibSymbol: [
          { required: true, message: '请输入IB平台代码', trigger: 'blur' }
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
    dialogAdd: function() {
      this.stockInfoForm = {
        'id': '',
        'stockCode': '',
        'stockSymbol': '',
        'stockIsin': '',
        'stockSpellAbbr': '',
        'stockNameEn': '',
        'stockNameCn': '',
        'stockType': '',
        'assetType': '',
        'exchangeId': '',
        'baseCurrency': '',
        'companyCode': '',
        'companyName': '',
        'offerDate': '',
        'listDate': '',
        'cancelDate': '',
        'lastPrice': '',
        'isOpentrade': '',
        'ibSymbol': ''
      };
      if (this.$refs.stockInfoForm) {
        this.$refs.stockInfoForm.resetFields();
      }
      this.stockInfoDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.stockInfoForm) {
        this.$refs.stockInfoForm.resetFields();
      }
      this.$http({
        url: '/stock/info/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockInfoForm = {
            'id': res.object.id,
            'stockCode': res.object.stockCode,
            'stockSymbol': res.object.stockSymbol,
            'stockIsin': res.object.stockIsin,
            'stockSpellAbbr': res.object.stockSpellAbbr,
            'stockNameEn': res.object.stockNameEn,
            'stockNameCn': res.object.stockNameCn,
            'stockType': res.object.stockType,
            'assetType': res.object.assetType,
            'exchangeId': res.object.exchangeId,
            'baseCurrency': res.object.baseCurrency,
            'companyCode': res.object.companyCode,
            'companyName': res.object.companyName,
            'offerDate': res.object.offerDate,
            'listDate': res.object.listDate,
            'cancelDate': res.object.cancelDate,
            'lastPrice': res.object.lastPrice,
            'isOpentrade': res.object.isOpentrade,
            'ibSymbol': res.object.ibSymbol
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.stockInfoDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.stockInfoLoading = true;
      this.$http({
        url: '/stock/info/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.stockInfoData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.stockInfoLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doInitData: function() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'assetType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.assetTypeData = res.object;
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
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'stockType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockTypeData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
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
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/stock/info/save',
            method: 'post',
            params: this.stockInfoForm
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
          this.stockInfoDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该证劵信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/stock/info/del',
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
    doDownload: function() {
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = ['证券编码', '证券代码', 'ISIN码', '证券拼音缩写', '证券英文名称', '证券中文名称', '证券类型', '资产类型', '交易所名称', '基础币种', '发行人编码', '发行人名称', '最新价格'];
        const filterVal = ['stockCode', 'stockSymbol', 'stockIsin', 'stockSpellAbbr', 'stockNameEn', 'stockNameCn', 'stockType', 'assetType', 'stockExchange.exchangeName', 'baseCurrency', 'companyCode', 'companyName', 'lastPrice'];
        const data = formatJson(filterVal, this.stockInfoData);
        excel.export_json_to_excel({
          header: tHeader,
          data,
          filename: 'table-list'
        });
      });
    }
  }
};
</script>

<style lang="scss">
  .stockInfoForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
