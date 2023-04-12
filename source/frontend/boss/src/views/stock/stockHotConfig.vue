<template>
  <div class="app-container">

    <el-button style="margin-bottom:10px;" type="success" size="mini" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>

    <el-table v-loading="stockHotConfigLoading" :data="stockHotConfigData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="stockInfo.stockCode" label="证劵编码" />
      <el-table-column prop="stockInfo.stockSymbol" label="证劵代码" />
      <el-table-column prop="stockInfo.stockIsin" label="ISIN码" />
      <el-table-column prop="stockInfo.stockNameEn" label="证券英文名称" />
      <el-table-column prop="stockInfo.stockNameCn" label="证券中文名称" />
      <el-table-column prop="stockInfo.stockType" label="证券类型" :formatter="stockTypeFormat" />
      <el-table-column prop="remark" label="备注" />
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

    <el-dialog title="证劵热门信息" :visible.sync="stockHotConfigDialog" width="500px">
      <el-form ref="stockHotConfigForm" :model="stockHotConfigForm" :rules="stockHotConfigRules" label-width="100px" class="stockHotConfigForm">
        <el-form-item label="证劵信息" prop="stockinfoId">
          <el-select v-model="stockHotConfigForm.stockinfoId" placeholder="请选择证劵信息" value="" filterable>
            <el-option v-for="data in stockInfoData" :key="data.id" :label="data.stockCode" :value="data.id">
              <span style="float:left;">{{ data.stockCode }}</span>
              <span style="float:right;">{{ data.stockNameCn }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="stockHotConfigForm.remark" type="textarea" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('stockHotConfigForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'StockHotConfig',
  data() {
    return {
      stockHotConfigLoading: '',
      stockHotConfigDialog: false,
      stockHotConfigData: [],
      stockInfoData: [],
      stockTypeData: [],
      stockHotConfigForm: {
        'id': '',
        'stockinfoId': '',
        'remark': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      stockHotConfigRules: {
        stockinfoId: [
          { required: true, message: '请选择证券信息ID', trigger: 'change' }
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
    stockTypeFormat: function(row) {
      const dictArray = this.stockTypeData;
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row.stockInfo.stockType) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    dialogAdd: function() {
      this.stockHotConfigForm = {
        'id': '',
        'stockinfoId': '',
        'remark': ''
      };
      if (this.$refs.stockHotConfigForm) {
        this.$refs.stockHotConfigForm.resetFields();
      }
      this.stockHotConfigDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.stockHotConfigForm) {
        this.$refs.stockHotConfigForm.resetFields();
      }
      this.$http({
        url: '/stock/hotConfig/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockHotConfigForm = {
            'id': res.object.id,
            'stockinfoId': res.object.stockinfoId,
            'remark': res.object.remark
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.stockHotConfigDialog = true;
    },
    doInitData: function() {
      this.$http({
        url: '/stock/info/findAll',
        method: 'get'
      }).then(res => {
        if (res.code === 200) {
          this.stockInfoData = res.object;
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
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.stockHotConfigLoading = true;
      this.$http({
        url: '/stock/hotConfig/data',
        method: 'post',
        params: this.pageParams
      }).then(res => {
        if (res.code === 200) {
          this.stockHotConfigData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.stockHotConfigLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/stock/hotConfig/save',
            method: 'post',
            params: this.stockHotConfigForm
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
          this.stockHotConfigDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该证劵热门信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/stock/hotConfig/del',
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
  .stockHotConfigForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
