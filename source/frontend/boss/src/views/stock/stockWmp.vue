<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="证券编码">
        <el-input v-model="searchForm.stockCode" clearable placeholder="请输入证券编码"></el-input>
      </el-form-item>
      <el-form-item label="证券代码">
        <el-input v-model="searchForm.stockSymbol" clearable placeholder="请输入证券代码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
<!--        <el-button type="warning" icon="el-icon-download" @click="doDownload()">导出</el-button>-->
      </el-form-item>
    </el-form>

    <el-table v-loading="stockWmpLoading" :data="stockWmpData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="stockInfo.stockCode" label="证券编码" />
      <el-table-column prop="stockInfo.stockSymbol" label="证券代码" />
      <el-table-column prop="stockInfo.stockNameCn" label="证券名称" />
      <el-table-column prop="baseCurrency" label="币种" :formatter="dictFormat" />
      <el-table-column prop="fundType" label="基金类型" :formatter="dictFormat" />
      <el-table-column prop="expectedAnnualReturn" label="预期年化收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.expectedAnnualReturn || scope.row.expectedAnnualReturn === 0">{{ scope.row.expectedAnnualReturn | keepNumTwo | addPercentage }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="productLife" label="产品期限">
        <template slot-scope="scope">
          <span v-if="scope.row.productLife">{{ scope.row.productLife + '天' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="riskLevel" label="风险程度等级" :formatter="dictFormat" />
      <el-table-column prop="startSaleAmt" label="起售金额" />
      <el-table-column prop="listingDate" label="上市日期" :formatter="dateFormat" />
      <el-table-column prop="expireDate" label="到期日期" :formatter="dateFormat" />
      <el-table-column prop="stockInfo.ibSymbol" label="IB平台代码" />
      <el-table-column prop="isRecommend" label="是否推荐">
        <template slot-scope="scope">
          <span v-if="scope.row.isRecommend === true">是</span>
          <span v-if="scope.row.isRecommend === false">否</span>
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

    <el-dialog title="理财信息" :visible.sync="stockWmpDialog" width="660px">
      <el-form ref="stockWmpForm" :inline="true" :model="stockWmpForm" :rules="stockWmpRules" label-width="100px" class="stockWmpForm">
        <el-form-item label="理财代码" prop="stockInfoId">
          <el-select v-model="stockWmpForm.stockInfoId" placeholder="请选择理财信息" value="">
            <el-option v-for="data in stockWmpInfoData" :key="data.id" :label="data.stockCode" :value="data.id">
              <span style="float:left;">{{ data.stockCode }}</span>
              <span style="float:right">{{ data.stockNameCn }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="基金类型" prop="fundType">
          <el-select v-model="stockWmpForm.fundType" placeholder="请选择基金类型" value="">
            <el-option v-for="data in fundTypeData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="风险程度" prop="riskLevel">
          <el-select v-model="stockWmpForm.riskLevel" placeholder="请选择风险程度等级" value="">
            <el-option v-for="data in riskLevelData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="起售金额" prop="startSaleAmt">
          <el-input v-model="stockWmpForm.startSaleAmt"></el-input>
        </el-form-item>
        <el-form-item label="预期年化收益率" prop="expectedAnnualReturn">
          <el-input v-model="stockWmpForm.expectedAnnualReturn">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="产品期限" prop="productLife">
          <el-input v-model="stockWmpForm.productLife"></el-input>
        </el-form-item>
        <el-form-item label="上市日期" prop="listingDate">
          <el-date-picker v-model="stockWmpForm.listingDate" type="date" value-format="timestamp" placeholder="选择上市日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="到期日期" prop="expireDate">
          <el-date-picker v-model="stockWmpForm.expireDate" type="date" value-format="timestamp" placeholder="选择到期日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="是否推荐" prop="isRecommend">
          <el-select v-model="stockWmpForm.isRecommend" placeholder="请选择是否推荐" value="">
            <el-option :key="true" label="是" :value="true"></el-option>
            <el-option :key="false" label="否" :value="false"></el-option>
          </el-select>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:100px;" type="primary" @click="doSubmit('stockWmpForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
// import { formatJson } from '@/utils/common';
import { checkNum, isInteger, checkNumTwo } from '../../utils/validate.js';
export default {
  name: 'StockWmp',
  data() {
    return {
      stockWmpLoading: '',
      stockWmpDialog: false,
      stockWmpData: [],
      assetTypeData: [],
      baseCurrencyData: [],
      fundTypeData: [],
      riskLevelData: [],
      stockTypeData: [],
      exchangeData: [],
      stockWmpInfoData: [],
      stockWmpForm: {
        'id': '',
        'stockinfoId': '',
        'stockSymbol': '',
        'expectedAnnualReturn': '',
        'productLife': '',
        'riskLevel': '',
        'startSaleAmt': '',
        'stockType': '',
        'expireDate': '',
        'exchangeId': '',
        'baseCurrency': '',
        'companyCode': '',
        'companyName': '',
        'isRecommend': '',
        'listingDate': '',
        'cancelDate': '',
        'lastPrice': '',
        'isOpentrade': '',
        'ibSymbol': '',
        'fundType': ''
      },
      searchForm: {
        'stockCode': '',
        'stockSymbol': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      stockWmpRules: {
        stockInfoId: [
          { required: true, message: '请选择理财信息', trigger: 'change' }
        ],
        fundType: [
          { required: true, message: '请选择基金类型', trigger: 'change' }
        ],
        assetType: [
          { required: true, message: '请选择资产类型', trigger: 'change' }
        ],
        riskLevel: [
          { required: true, message: '请选择风险程度等级', trigger: 'change' }
        ],
        isRecommend: [
          { required: true, message: '请选择是否推荐', trigger: 'change' }
        ],
        startSaleAmt: [
          { required: true, message: '请输入起售金额', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        productLife: [
          { required: false, message: '请输入产品期限', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
        ],
        expectedAnnualReturn: [
          { required: true, message: '请输入年化收益率', trigger: 'blur' }, { validator: checkNumTwo, trigger: 'blur' }
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
      this.stockWmpForm = {
        'id': '',
        'stockinfoId': '',
        'stockSymbol': '',
        'expectedAnnualReturn': '',
        'productLife': '',
        'riskLevel': '',
        'startSaleAmt': '',
        'stockType': '',
        'expireDate': '',
        'exchangeId': '',
        'baseCurrency': '',
        'companyCode': '',
        'companyName': '',
        'isRecommend': '',
        'listingDate': '',
        'cancelDate': '',
        'lastPrice': '',
        'isOpentrade': '',
        'ibSymbol': '',
        'fundType': ''
      };
      if (this.$refs.stockWmpForm) {
        this.$refs.stockWmpForm.resetFields();
      }
      this.stockWmpDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.stockWmpForm) {
        this.$refs.stockWmpForm.resetFields();
      }
      this.$http({
        url: '/stock/stockWmp/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockWmpForm = {
            'id': res.object.id,
            'stockInfoId': res.object.stockInfoId,
            'expectedAnnualReturn': res.object.expectedAnnualReturn,
            'stockIsin': res.object.stockIsin,
            'stockSpellAbbr': res.object.stockSpellAbbr,
            'productLife': res.object.productLife,
            'riskLevel': res.object.riskLevel,
            'startSaleAmt': res.object.startSaleAmt,
            'assetType': res.object.assetType,
            'exchangeId': res.object.exchangeId,
            'baseCurrency': res.object.baseCurrency,
            'listingDate': res.object.listingDate,
            'expireDate': res.object.expireDate,
            'isRecommend': res.object.isRecommend,
            'isOpentrade': res.object.isOpentrade,
            'fundType': res.object.fundType,
            'ibSymbol': res.object.ibSymbol
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.stockWmpDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.stockWmpLoading = true;
      this.$http({
        url: '/stock/stockWmp/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.stockWmpData = res.rows;
          for (var i = 0; i < res.rows.length; i++) {
            this.stockWmpData[i].baseCurrency = res.rows[i].stockInfo.baseCurrency;
          }
          this.pageParams.totalPage = res.totalPage;
          this.stockWmpLoading = false;
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
          dictCode: 'fundType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.fundTypeData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'riskLevel',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.riskLevelData = res.object;
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
      this.$http({
        url: '/stock/info/findListByStockType',
        method: 'get',
        params: {
          stockType: 'wmp'
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockWmpInfoData = res.object;
          for (var i = 0; i < Object.keys(res.object).length; i++) {
            this.stockWmpInfoData[i] = { name: '理财代码', stockCode: res.object[i].stockCode, id: res.object[i].id, stockNameCn: res.object[i].stockNameCn };
          }
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/stock/stockWmp/save',
            method: 'post',
            params: this.stockWmpForm
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
          this.stockWmpDialog = false;
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
          url: '/stock/stockWmp/del',
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
    // doDownload: function() {
    //   import('@/vendor/Export2Excel').then(excel => {
    //     const tHeader = ['证券编码', '证券代码', 'ISIN码', '证券拼音缩写', '证券英文名称', '证券中文名称', '证券类型', '资产类型', '交易所名称', '基础币种', '发行人编码', '发行人名称', '最新价格'];
    //     const filterVal = ['stockCode', 'stockSymbol', 'stockIsin', 'stockSpellAbbr', 'stockNameEn', 'stockNameCn', 'stockType', 'assetType', 'stockExchange.exchangeName', 'companyCode', 'companyName', 'lastPrice'];
    //     const data = formatJson(filterVal, this.stockWmpData);
    //     excel.export_json_to_excel({
    //       header: tHeader,
    //       data,
    //       filename: 'table-list'
    //     });
    //   });
    // }
  }
};
</script>

<style lang="scss">
  .stockWmpForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
