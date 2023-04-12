<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini" :rules="searchRules">
      <el-form-item label="账户ID" prop="accountId">
        <el-input v-model="searchForm.accountId" clearable placeholder="请输入账户ID"></el-input>
      </el-form-item>
      <el-form-item label="IB账户代码">
        <el-input v-model="searchForm.ibAccountCode" clearable placeholder="请输入IB账户代码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="familyLoading" :data="familyData" style="width:100%;margin-bottom:20px;" border row-key="id" highlight-current-row>
      <el-table-column prop="accountId" label="账户ID" />
      <el-table-column prop="accountName" label="账户名" />
      <el-table-column prop="ibAccountCode" label="IB账户代码" />
      <el-table-column prop="ibFamilyCode" label="IB家族代码" />
      <el-table-column prop="baseCurrency" label="本位币" />
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

    <el-dialog title="IB账户家族信息" :visible.sync="familyDialog" width="660px" class="IBfamily">
      <el-form ref="familyForm" :model="familyForm" :inline="true" :rules="familyRules" label-width="100px" class="roleForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="familyForm.accountId" />
        </el-form-item>
        <el-form-item label="IB账户代码" prop="ibAccountCode">
          <el-input v-model="familyForm.ibAccountCode" />
        </el-form-item>
        <el-form-item label="IB家族代码" prop="ibFamilyCode">
          <el-input v-model="familyForm.ibFamilyCode" />
        </el-form-item>
        <el-form-item label="本位币" prop="baseCurrency">
          <el-select v-model="familyForm.baseCurrency" placeholder="请选择本位币" value="" style="width: 180px;">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="familyForm.remark" type="textarea" maxlength="20" placeholder="输入少于20字符" show-word-limit />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('familyForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { isNum, isInteger } from '../../utils/validate.js';
export default {
  name: 'IbAccountFamily',
  data() {
    return {
      familyLoading: '',
      familyDialog: false,
      familyData: [],
      baseCurrencyData: [],
      familyForm: {
        'id': '',
        'accountId': '',
        'ibAccountCode': '',
        'baseCurrency': '',
        'ibFamilyCode': '',
        'remark': ''
      },
      searchForm: {
        accountId: '',
        ibAccountCode: ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      familyRules: {
        accountId: [
          { required: true, message: '请输入账户ID' }, { validator: isInteger, trigger: 'blur' }
        ],
        ibAccountCode: [
          { required: true, message: '请输入IB账户代码', trigger: 'blur' }
        ],
        ibFamilyCode: [
          { required: false, message: '请输入IB家族代码', trigger: 'blur' }
        ],
        baseCurrency: [
          { required: true, message: '请选择本位币', trigger: 'change' }
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
    dialogAdd: function() {
      this.familyForm = {
        'id': '',
        'accountId': '',
        'ibAccountCode': '',
        'ibFamilyCode': '',
        'baseCurrency': '',
        'remark': ''
      };
      if (this.$refs.familyForm) {
        this.$refs.familyForm.resetFields();
      }
      this.familyDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.familyForm) {
        this.$refs.familyForm.resetFields();
      }
      this.$http({
        url: '/ib/ibAccountFamily/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.familyForm = {
            'id': res.object.id,
            'accountId': res.object.accountId,
            'ibAccountCode': res.object.ibAccountCode,
            'ibFamilyCode': res.object.ibFamilyCode,
            'baseCurrency': res.object.baseCurrency,
            'remark': res.object.remark
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.familyDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          this.familyLoading = true;
          this.$http({
            url: '/ib/ibAccountFamily/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.familyData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.familyLoading = false;
            }
          }).catch(error => {
            this.$message.error(error);
          });
        }
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/ib/ibAccountFamily/save',
            method: 'post',
            params: this.familyForm
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
          this.familyDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该IB的账户家族信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/ib/ibAccountFamily/del',
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
  .app-container {
    .IBfamily {
      .el-form-item__content {
        line-height: 20px;
      }
      .el-textarea__inner {
        padding: 5px 12px;
      }
    }
  }

</style>

