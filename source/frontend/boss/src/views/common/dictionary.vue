<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="字典名称">
        <el-input v-model="searchForm.dictName" clearable placeholder="请输入字典名称"></el-input>
      </el-form-item>
      <el-form-item label="字典编码">
        <el-input v-model="searchForm.dictCode" clearable placeholder="请输入字典编码"></el-input>
      </el-form-item>
      <el-form-item label="语言类型">
        <el-select v-model="searchForm.lang" clearable placeholder="请选择语言类型" value="">
          <el-option key="zh_CN" label="简体中文" value="zh_CN" />
          <el-option key="zh_HK" label="繁体中文" value="zh_HK" />
          <el-option key="en_US" label="English" value="en_US" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="dictLoading" :data="dictData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="dictName" label="字典名称" />
      <el-table-column prop="dictCode" label="字典编码" />
      <el-table-column prop="dictValue" label="字典键值" />
      <el-table-column prop="dictDest" label="键值描述" />
      <el-table-column label="语言类型">
        <template slot-scope="scope">
          <span v-if="scope.row.lang === 'zh_CN'">简体中文</span>
          <span v-if="scope.row.lang === 'zh_HK'">繁体中文</span>
          <span v-if="scope.row.lang === 'en_US'">English</span>
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

    <el-dialog title="数据字典" :visible.sync="dictDialog" width="500px">
      <el-form ref="dictForm" :model="dictForm" :rules="dictRules" label-width="80px" class="dictForm">
        <el-form-item label="字典名称" prop="dictName">
          <el-input v-model="dictForm.dictName" />
        </el-form-item>
        <el-form-item label="字典编码" prop="dictCode">
          <el-input v-model="dictForm.dictCode" />
        </el-form-item>
        <el-form-item label="字典键值" prop="dictValue">
          <el-input v-model="dictForm.dictValue" />
        </el-form-item>
        <el-form-item label="键值描述" prop="dictDest">
          <el-input v-model="dictForm.dictDest" />
        </el-form-item>
        <el-form-item label="语言类型" prop="lang">
          <el-select v-model="dictForm.lang" placeholder="请选择语言类型" value="">
            <el-option key="zh_CN" label="简体中文" value="zh_CN" />
            <el-option key="zh_HK" label="繁体中文" value="zh_HK" />
            <el-option key="en_US" label="English" value="en_US" />
          </el-select>
        </el-form-item>
        <el-form-item label="排序号" prop="sortNum">
          <el-input v-model="dictForm.sortNum" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('dictForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'Dictionary',
  data() {
    return {
      dictLoading: '',
      dictDialog: false,
      dictData: [],
      dictForm: {
        'id': '',
        'dictName': '',
        'dictCode': '',
        'dictValue': '',
        'dictDest': '',
        'lang': '',
        'sortNum': ''
      },
      searchForm: {
        'dictName': '',
        'dictCode': '',
        'lang': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      dictRules: {
        dictName: [
          { required: true, message: '请输入字典名称', trigger: 'blur' }
        ],
        dictCode: [
          { required: true, message: '请输入字典编码', trigger: 'blur' }
        ],
        dictValue: [
          { required: true, message: '请输入字典编码', trigger: 'blur' }
        ],
        dictDest: [
          { required: true, message: '请输入键值描述', trigger: 'blur' }
        ],
        lang: [
          { required: true, message: '请选择语言类型', trigger: 'change' }
        ]
      }
    };
  },
  mounted: function() {
    this.doSearch();
  },
  methods: {
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },
    dialogAdd: function() {
      this.dictForm = {
        'id': '',
        'dictName': '',
        'dictCode': '',
        'dictValue': '',
        'dictDest': '',
        'lang': '',
        'sortNum': ''
      };
      if (this.$refs.dictForm) {
        this.$refs.dictForm.resetFields();
      }
      this.dictDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.dictForm) {
        this.$refs.dictForm.resetFields();
      }
      this.$http({
        url: '/common/dictionary/findById',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.dictForm = {
            'id': res.object.id,
            'dictName': res.object.dictName,
            'dictCode': res.object.dictCode,
            'dictValue': res.object.dictValue,
            'dictDest': res.object.dictDest,
            'lang': res.object.lang,
            'sortNum': res.object.sortNum
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.dictDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.dictLoading = true;
      this.$http({
        url: '/common/dictionary/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.dictData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.dictLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/common/dictionary/save',
            method: 'post',
            params: this.dictForm
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
          this.dictDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该数据字典, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/common/dictionary/del',
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

<style lang="scss" scoped>

</style>
