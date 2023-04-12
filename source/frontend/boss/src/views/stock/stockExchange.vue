<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="交易所编码">
        <el-input v-model="searchForm.exchangeCode" clearable placeholder="请输入交易所编码" maxlength="32"></el-input>
      </el-form-item>
      <el-form-item label="交易所名称">
        <el-input v-model="searchForm.exchangeName" clearable placeholder="请输入交易所名称" maxlength="64"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="stockExchangeLoading" :data="stockExchangeData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="exchangeCode" label="交易所编码" />
      <el-table-column prop="exchangeName" label="交易所名称" />
      <el-table-column prop="region" label="国家地区" :formatter="regionFormat" />
      <el-table-column prop="continent" label="所在大洲" :formatter="dictFormat" />
      <el-table-column prop="baseCurrency" label="基础币种" :formatter="dictFormat" />
      <el-table-column prop="tradeDayType" label="交易日类型" :formatter="dictFormat" />
      <el-table-column label="开放交易">
        <template slot-scope="scope">
          <span v-if="scope.row.isOpentrade === true">是</span>
          <span v-if="scope.row.isOpentrade === false">否</span>
        </template>
      </el-table-column>
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

    <el-dialog title="交易市场信息" :visible.sync="stockExchangeDialog" width="660px">
      <el-form ref="stockExchangeForm" :inline="true" :model="stockExchangeForm" :rules="stockExchangeRules" label-width="100px" class="stockExchangeForm">
        <el-form-item label="交易所编码" prop="exchangeCode">
          <el-input v-model="stockExchangeForm.exchangeCode" maxlength="32" />
        </el-form-item>
        <el-form-item label="交易所名称" prop="exchangeName">
          <el-input v-model="stockExchangeForm.exchangeName" maxlength="64" />
        </el-form-item>
        <el-form-item label="国家地区" prop="region">
          <el-select v-model="stockExchangeForm.region" placeholder="请选择国家地区" value="">
            <el-option v-for="data in regionData" :key="data.id" :label="data.cnName" :value="data.sCode">
              <span style="float:left;">{{ data.sCode }}</span>
              <span style="float:right">{{ data.cnName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所在大洲" prop="continent">
          <el-select v-model="stockExchangeForm.continent" placeholder="请选择所在大洲" value="">
            <el-option v-for="data in continentData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="基础币种" prop="baseCurrency">
          <el-select v-model="stockExchangeForm.baseCurrency" placeholder="请选择基础币种" value="">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易日类型" prop="tradeDayType">
          <el-select v-model="stockExchangeForm.tradeDayType" placeholder="请选择交易日类型" value="">
            <el-option v-for="data in tradeDayTypeData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开放交易" prop="isOpentrade">
          <el-select v-model="stockExchangeForm.isOpentrade" placeholder="请选择开放交易" value="">
            <el-option :key="true" label="是" :value="true"></el-option>
            <el-option :key="false" label="否" :value="false"></el-option>
          </el-select>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:100px" type="primary" @click="doSubmit('stockExchangeForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'StockExchange',
  data() {
    return {
      stockExchangeLoading: '',
      stockExchangeDialog: false,
      stockExchangeData: [],
      regionData: [],
      baseCurrencyData: [],
      continentData: [],
      tradeDayTypeData: [],
      stockExchangeForm: {
        'id': '',
        'exchangeCode': '',
        'exchangeName': '',
        'region': '',
        'continent': '',
        'baseCurrency': '',
        'tradeDayType': '',
        'isOpentrade': ''
      },
      searchForm: {
        'exchangeCode': '',
        'exchangeName': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      stockExchangeRules: {
        exchangeCode: [
          { required: true, message: '请输入交易所编码', trigger: 'blur' }
        ],
        exchangeName: [
          { required: true, message: '请输入交易所名称', trigger: 'blur' }
        ],
        region: [
          { required: true, message: '请选择国家地区', trigger: 'change' }
        ],
        continent: [
          { required: true, message: '请选择所在大洲', trigger: 'change' }
        ],
        baseCurrency: [
          { required: true, message: '请选择基础币种', trigger: 'change' }
        ],
        isOpentrade: [
          { required: true, message: '请选择是否开放交易', trigger: 'change' }
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
    regionFormat: function(row, column) {
      const regionArray = this[column.property + 'Data'];
      let regionValue = '';
      regionArray.forEach((data) => {
        if (data.sCode === row[column.property]) {
          regionValue = data.cnName;
        }
      });
      return regionValue;
    },
    dialogAdd: function() {
      this.stockExchangeForm = {
        'id': '',
        'exchangeCode': '',
        'exchangeName': '',
        'region': '',
        'continent': '',
        'baseCurrency': '',
        'tradeDayType': '',
        'isOpentrade': ''
      };
      if (this.$refs.stockExchangeForm) {
        this.$refs.stockExchangeForm.resetFields();
      }
      this.stockExchangeDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.stockExchangeForm) {
        this.$refs.stockExchangeForm.resetFields();
      }
      this.$http({
        url: '/stock/exchange/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockExchangeForm = {
            'id': res.object.id,
            'exchangeCode': res.object.exchangeCode,
            'exchangeName': res.object.exchangeName,
            'region': res.object.region,
            'continent': res.object.continent,
            'baseCurrency': res.object.baseCurrency,
            'tradeDayType': res.object.tradeDayType,
            'isOpentrade': res.object.isOpentrade
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.stockExchangeDialog = true;
    },
    doInitData: function() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'continent',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.continentData = res.object;
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
          dictCode: 'tradeDayType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.tradeDayTypeData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/common/region/findAll',
        method: 'get'
      }).then(res => {
        if (res.code === 200) {
          this.regionData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.stockExchangeLoading = true;
      this.$http({
        url: '/stock/exchange/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.stockExchangeData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.stockExchangeLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/stock/exchange/save',
            method: 'post',
            params: this.stockExchangeForm
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
          this.stockExchangeDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该证劵交易所, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/stock/exchange/del',
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
  .stockExchangeForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
