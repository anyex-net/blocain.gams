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

    <el-table v-loading="ibAccountDailyPnLLoading" :data="ibAccountDailyPnLData" style="width:100%;margin-bottom:20px;" border row-key="id" highlight-current-row>
      <el-table-column prop="accountId" label="账户ID" width="170px" />
      <el-table-column prop="accountName" label="账户名" />
      <el-table-column prop="accountCode" label="IB账户代码" width="120px" />
      <el-table-column prop="dailyPnl" label="当日盈亏" />
      <el-table-column prop="unrealizedPnl" label="未实现盈亏" />
      <el-table-column prop="realizedPnl" label="已实现盈亏" />
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

    <el-dialog title="当日盈亏" :visible.sync="ibAccountDailyPnLDialog" width="660px">
      <el-form ref="ibAccountDailyPnLForm" :inline="true" :model="ibAccountDailyPnLForm" :rules="ibAccountDailyPnLRules" label-width="100px" class="ibAccountDailyPnLForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="ibAccountDailyPnLForm.accountId" />
        </el-form-item>
        <el-form-item label="IB账户代码" prop="accountCode">
          <el-input v-model="ibAccountDailyPnLForm.accountCode" />
        </el-form-item>
        <el-form-item label="当日盈亏" prop="dailyPnl">
          <el-input v-model="ibAccountDailyPnLForm.dailyPnl" />
        </el-form-item>
        <el-form-item label="未实现盈亏" prop="unrealizedPnl">
          <el-input v-model="ibAccountDailyPnLForm.unrealizedPnl" />
        </el-form-item>
        <el-form-item label="已实现盈亏" prop="realizedPnl">
          <el-input v-model="ibAccountDailyPnLForm.realizedPnl" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('ibAccountDailyPnLForm')">提交</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isNum, isInteger } from '../../utils/validate.js';
export default {
  name: 'IbAccountDailyPnL',
  data() {
    return {
      ibAccountDailyPnLLoading: '',
      ibAccountDailyPnLDialog: false,
      ibAccountDailyPnLData: [],
      baseCurrencyData: [],
      ibAccountDailyPnLForm: {
        id: '',
        accountId: '',
        accountCode: '',
        dailyPnl: '',
        unrealizedPnl: '',
        realizedPnl: '',
        createDate: ''
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
      ibAccountDailyPnLRules: {
        accountId: [
          { required: true, message: '请输入账户ID', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
        ],
        accountCode: [
          { required: true, message: '请输入IB对应AccountCode', trigger: 'blur' }
        ],
        dailyPnl: [
          { required: false, message: '请输入当日盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        unrealizedPnl: [
          { required: false, message: '请输入未实现盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        realizedPnl: [
          { required: false, message: '请输入已实现盈亏', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
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
      this.ibAccountDailyPnLForm = {
        id: '',
        accountId: '',
        accountCode: '',
        dailyPnl: '',
        unrealizedPnl: '',
        realizedPnl: ''
      };
      if (this.$refs.ibAccountDailyPnLForm) {
        this.$refs.ibAccountDailyPnLForm.resetFields();
      }
      this.ibAccountDailyPnLDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.ibAccountDailyPnLForm) {
        this.$refs.ibAccountDailyPnLForm.resetFields();
      }
      this.$http({
        url: '/ib/ibAccountDailyPnl/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.ibAccountDailyPnLForm = {
            id: res.object.id,
            accountId: res.object.accountId,
            accountCode: res.object.accountCode,
            dailyPnl: res.object.dailyPnl,
            unrealizedPnl: res.object.unrealizedPnl,
            realizedPnl: res.object.realizedPnl
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.ibAccountDailyPnLDialog = true;
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/ib/ibAccountDailyPnl/save',
            method: 'post',
            params: this.ibAccountDailyPnLForm
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
          this.ibAccountDailyPnLDialog = false;
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
          url: '/ib/ibAccountDailyPnl/del',
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
          this.IbAccountDailyPnLLoading = true;
          this.$http({
            url: '/ib/ibAccountDailyPnl/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.ibAccountDailyPnLData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.ibAccountDailyPnLLoading = false;
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
