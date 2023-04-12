<!--suppress ALL -->
<template>
  <div class="app-container">

    <el-button style="margin-bottom:10px;" type="success" size="mini" icon="el-icon-circle-plus" @click="userDialogAdd()">添加</el-button>

    <el-table v-loading="userLoading" :data="userData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="userName" label="登录名" />
      <el-table-column prop="trueName" label="姓名" />
      <el-table-column prop="idCard" label="身份证" />
      <el-table-column label="性别">
        <template slot-scope="scope">
          <span v-if="scope.row.gender === 0">男</span>
          <span v-if="scope.row.gender === 1">女</span>
        </template>
      </el-table-column>
      <el-table-column label="状态">
        <template slot-scope="scope">
          <span v-if="scope.row.active === true">启用</span>
          <span v-if="scope.row.active === false">禁用</span>
        </template>
      </el-table-column>
      <el-table-column width="200" label="所属机构">
        <template slot-scope="scope">
          <span v-for="data in scope.row.organizeList" :key="data.id">{{ data.orgName }}&nbsp;</span>
        </template>
      </el-table-column>
      <el-table-column width="200" label="所属角色">
        <template slot-scope="scope">
          <span v-for="data in scope.row.roleList" :key="data.id">{{ data.roleName }}&nbsp;</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <!--<el-button size="mini" type="primary" @click="doResetPwd(scope.$index, scope.row)">重置密码</el-button>-->
          <el-button size="mini" type="success" @click="userDialogEdit(scope.$index, scope.row)">编辑</el-button>
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

    <el-dialog title="用户信息" :visible.sync="userDialog" width="620px">
      <el-form ref="userForm" :inline="true" :model="userForm" :rules="userRules" label-width="80px" class="userForm">
        <el-form-item label="登录名" prop="userName">
          <el-input v-model="userForm.userName" />
        </el-form-item>
        <el-form-item label="姓名" prop="trueName">
          <el-input v-model="userForm.trueName" />
        </el-form-item>
        <el-form-item label="角色" prop="roleIds">
          <el-select v-model="userForm.roleIds" multiple placeholder="请选择角色" value="">
            <el-option v-for="data in roleData" :key="data.id" :label="data.roleName" :value="data.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="机构" prop="orgIds">
          <select-tree
            :value="userForm.orgIds"
            :options="orgData"
            :clearable="true"
            :accordion="true"
            :multiple="false"
            :props="{value:'id',label:'orgName',children:'children'}"
            @getValue="getValue($event)"
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="userForm.password" :placeholder="tips" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="userForm.gender" placeholder="请选择性别" value="">
            <el-option :key="0" label="男" :value="0" />
            <el-option :key="1" label="女" :value="1" />
          </el-select>
        </el-form-item>
        <el-form-item label="身份证" prop="idCard">
          <el-input v-model="userForm.idCard" />
        </el-form-item>
        <el-form-item label="状态" prop="active">
          <el-select v-model="userForm.active" placeholder="请选择状态" value="">
            <el-option :key="true" label="启用" :value="true" />
            <el-option :key="false" label="禁用" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item style="display:block;" label="用户描述" prop="dest">
          <el-input v-model="userForm.dest" style="width:485px;" type="textarea" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:80px;" type="primary" @click="doSubmit('userForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import selectTree from '@/components/selectTree';

export default {
  name: 'User',
  components: {
    selectTree
  },
  data() {
    return {
      userLoading: '',
      userDialog: false,
      userData: [],
      roleData: [],
      orgData: [],
      tips: '',
      userForm: {
        'id': '',
        'userName': '',
        'trueName': '',
        'orgIds': '',
        'roleIds': '',
        'gender': '',
        'idCard': '',
        'active': '',
        'dest': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      userRules: {
        userName: [
          { required: true, message: '请输入登录名', trigger: 'blur' }
        ],
        trueName: [
          { required: true, message: '请选择姓名', trigger: 'blur' }
        ],
        orgIds: [
          { required: false, message: '请选择所属机构', trigger: 'change' }
        ],
        roleIds: [
          { required: false, message: '请选择所属角色', trigger: 'change' }
        ],
        password: [
          { required: false }
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' }
        ],
        idCard: [
          { required: true, message: '请输入身份证', trigger: 'blur' }
        ],
        active: [
          { required: true, message: '请选择状态', trigger: 'change' }
        ],
        dest: [
          { required: false }
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
    getValue(value) {
      this.userForm.orgIds = value;
    },
    userDialogAdd: function(row, column) {
      this.tips = '';
      this.userForm = {
        'id': '',
        'userName': '',
        'trueName': '',
        'orgIds': '',
        'roleIds': '',
        'password': '',
        'gender': '',
        'idCard': '',
        'active': '',
        'dest': ''
      };
      this.userDialog = true;
    },
    userDialogEdit: function(row, column) {
      this.tips = '若无需修改，请忽略';
      this.$http({
        url: '/system/user/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          const orgIds = [];
          for (const i in res.object.organizeList) {
            orgIds.push(res.object.organizeList[i].id);
          }
          const roleIds = [];
          for (const i in res.object.roleList) {
            roleIds.push(res.object.roleList[i].id);
          }
          this.userForm = {
            'id': res.object.id,
            'userName': res.object.userName,
            'trueName': res.object.trueName,
            'orgIds': orgIds + '',
            'roleIds': roleIds,
            'password': '',
            'gender': res.object.gender,
            'idCard': res.object.idCard,
            'active': res.object.active,
            'dest': res.object.dest
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.userDialog = true;
    },
    doInitData: function() {
      this.$http({
        url: '/system/role/data',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          this.roleData = res.rows;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/system/organiz/data',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          this.orgData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.userLoading = true;
      this.$http({
        url: '/system/user/data',
        method: 'post',
        params: this.pageParams
      }).then(res => {
        if (res.code === 200) {
          this.userData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.userLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.userForm.orgIds = this.userForm.orgIds + '';
      this.userForm.roleIds = this.userForm.roleIds + '';
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/system/user/save',
            method: 'post',
            params: this.userForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.doInitData();
              this.doSearch();
            } else {
              this.$message.error(res.message || 'Has Error');
            }
          }).catch(error => {
            this.$message.error(error);
          });
          this.userDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该用户, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/system/user/del',
          method: 'post',
          params: {
            ids: column.id
          }
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.doInitData();
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
    doResetPwd: function(row, column) {
      this.$confirm('确认要重置密码, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/system/user/resetPwd',
          method: 'post',
          params: {
            id: column.id
          }
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.doInitData();
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
          message: '已取消重置'
        });
      });
    }
  }
};
</script>

<style lang="scss">
  .userForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
