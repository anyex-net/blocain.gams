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

    <el-table v-loading="stockWarrantLoading" :data="stockWarrantData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="stockCode" label="证券编码" />
      <el-table-column prop="stockSymbol" label="证券代码" />
      <el-table-column prop="warrantClass" label="权证类别">
        <template slot-scope="scope">
          <span v-if="scope.row.warrantClass === 'warrant'">普通权证</span>
          <span v-if="scope.row.warrantClass === 'cbbc'">牛熊证</span>
        </template>
      </el-table-column>
      <el-table-column label="权证类型">
        <template slot-scope="scope">
          <span v-if="scope.row.warrantType === 'call'">认购权证</span>
          <span v-if="scope.row.warrantType === 'put'">认沽权证</span>
        </template>
      </el-table-column>
      <el-table-column prop="exercisePrice" label="行权价" />
      <el-table-column prop="ibSymbol" label="IB平台代码" />
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

    <el-dialog title="权证信息" :visible.sync="stockWarrantDialog" width="660px">
      <el-form ref="stockWarrantForm" :inline="true" :model="stockWarrantForm" :rules="stockWarrantRules" label-width="100px" class="stockWarrantForm">
        <el-form-item label="权证代码" prop="stockInfoId">
          <el-select v-model="stockWarrantForm.stockInfoId" placeholder="请选择权证信息" value="">
            <el-option v-for="data in stockWarrantInfoData" :key="data.id" :label="data.stockCode" :value="data.id">
              <span style="float:left;">{{ data.stockCode }}</span>
              <span style="float:right">{{ data.stockNameCn }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="权证类别" prop="warrantClass">
          <el-select v-model="stockWarrantForm.warrantClass" placeholder="请选择权证类别" value="">
            <el-option label="普通权证" value="warrant" />
            <el-option label="牛熊证" value="cbbc" />
          </el-select>
        </el-form-item>
        <el-form-item label="权证类型" prop="warrantType">
          <el-select v-model="stockWarrantForm.warrantType" placeholder="请选择权证类型" value="">
            <el-option label="认购权证" value="call" />
            <el-option label="认沽权证" value="put" />
          </el-select>
        </el-form-item>
        <el-form-item label="行权价" prop="exercisePrice">
          <el-input v-model="stockWarrantForm.exercisePrice" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:100px;" type="primary" @click="doSubmit('stockWarrantForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
// import { formatJson } from '@/utils/common';
import { checkNum } from '../../utils/validate.js';
export default {
  name: 'StockWarrant',
  data() {
    return {
      stockWarrantLoading: '',
      stockWarrantDialog: false,
      stockWarrantData: [],
      assetTypeData: [],
      baseCurrencyData: [],
      stockTypeData: [],
      exchangeData: [],
      stockWarrantInfoData: [],
      stockWarrantForm: {
        'stockInfoId': '',
        'id': '',
        'warrantType': '',
        'warrantClass': '',
        'exercisePrice': '',
        'stockCode': '',
        'stockSymbol': ''
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
      stockWarrantRules: {
        stockInfoId: [
          { required: true, message: '请选择权证信息', trigger: 'change' }
        ],
        warrantType: [
          { required: true, message: '请选择权证类型', trigger: 'change' }
        ],
        warrantClass: [
          { required: true, message: '请选择权证类别', trigger: 'change' }
        ],
        exercisePrice: [
          { required: true, message: '请输入行权价', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
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
      if (date === undefined) {
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
    dialogAdd: function() {
      this.stockWarrantForm = {
        'stockInfoId': '',
        'id': '',
        'warrantType': '',
        'warrantClass': '',
        'exercisePrice': '',
        'stockCode': ''
      };
      if (this.$refs.stockWarrantForm) {
        this.$refs.stockWarrantForm.resetFields();
      }
      this.stockWarrantDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.stockWarrantForm) {
        this.$refs.stockWarrantForm.resetFields();
      }
      this.$http({
        url: '/stock/stockWarrant/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockWarrantForm = {
            'id': res.object.id,
            'stockInfoId': res.object.stockInfoId,
            'warrantType': res.object.warrantType,
            'warrantClass': res.object.warrantClass,
            'exercisePrice': res.object.exercisePrice
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.stockWarrantDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.stockWarrantLoading = true;
      this.$http({
        url: '/stock/stockWarrant/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.stockWarrantData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.stockWarrantLoading = false;
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
        url: '/stock/info/findListByStockType',
        method: 'get',
        params: {
          stockType: 'warrant'
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockWarrantInfoData = res.object;
          for (var i = 0; i < Object.keys(res.object).length; i++) {
            this.stockWarrantInfoData[i] = { name: '权证代码', stockCode: res.object[i].stockCode, id: res.object[i].id, stockNameCn: res.object[i].stockNameCn };
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
            url: '/stock/stockWarrant/save',
            method: 'post',
            params: this.stockWarrantForm
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
          this.stockWarrantDialog = false;
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
          url: '/stock/stockWarrant/del',
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
  .stockWarrantForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
