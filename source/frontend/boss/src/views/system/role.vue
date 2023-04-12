<template>
  <div class="app-container">

    <el-button style="margin-bottom:10px;" type="success" size="mini" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>

    <el-table v-loading="roleLoading" :data="roleData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="roleCode" label="角色编码" />
      <el-table-column prop="roleName" label="角色名称" />
      <el-table-column prop="roleDest" label="角色描述" />
      <el-table-column label="是否需要绑定GA">
        <template slot-scope="scope">
          <span v-if="scope.row.needga === true">是</span>
          <span v-if="scope.row.needga === false">否</span>
        </template>
      </el-table-column>
      <el-table-column prop="createDate" :formatter="dateFormat" width="200" label="创建时间" />
      <el-table-column label="操作" width="250">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="dialogGot(scope.$index, scope.row)">授权</el-button>
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

    <el-dialog title="角色信息" :visible.sync="roleDialog" width="500px">
      <el-form ref="roleForm" :model="roleForm" :rules="roleRules" label-width="80px" class="roleForm">
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="roleForm.roleCode" />
        </el-form-item>
        <el-form-item label="角色名称" prop="roleCode">
          <el-input v-model="roleForm.roleName" />
        </el-form-item>
        <el-form-item label="绑定GA" prop="roleCode">
          <el-select v-model="roleForm.needga" placeholder="请选择是否绑定GA" value="">
            <el-option :key="true" label="是" :value="true" />
            <el-option :key="false" label="否" :value="false" />
          </el-select>
        </el-form-item>
        <el-form-item label="角色描述" prop="orgDest">
          <el-input v-model="roleForm.roleDest" type="textarea" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('roleForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <el-dialog title="角色授权" :visible.sync="grantDialog" width="400px">
      <select-tree
        style="width:100%"
        :value="grantForm.resourceIds"
        :options="resData"
        :clearable="true"
        :accordion="true"
        :multiple="true"
        :props="{value:'id',label:'resName',children:'children'}"
        @getValue="getValue($event)"
      />
      <el-divider></el-divider>
      <el-button type="primary" @click="doGot">提交</el-button>
    </el-dialog>

  </div>
</template>

<script>
import selectTree from '@/components/selectTree';

export default {
  name: 'Role',
  components: {
    selectTree
  },
  data() {
    return {
      roleLoading: '',
      roleDialog: false,
      grantDialog: false,
      roleData: [],
      resData: [],
      roleForm: {
        'id': '',
        'roleCode': '',
        'roleName': '',
        'needga': '',
        'roleDest': ''
      },
      grantForm: {
        'id': '',
        'resourceIds': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      roleRules: {
        roleCode: [
          { required: true, message: '请选择角色编码', trigger: 'blur' }
        ],
        roleName: [
          { required: true, message: '请选择角色名称', trigger: 'blur' }
        ],
        needga: [
          { required: false }
        ],
        roleDest: [
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
      this.grantForm.resourceIds = value;
    },
    dialogGot: function(row, column) {
      const resList = [];
      this.grantForm = {
        'id': column.id,
        'resourceIds': ''
      };
      this.$http({
        url: '/system/role/findByRoleId',
        method: 'get',
        params: {
          roleId: column.id
        }
      }).then(res => {
        if (res.code === 200) {
          for (const i in res.object) {
            resList.push(res.object[i].id);
          }
          this.grantForm.resourceIds = resList + '';
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.grantDialog = true;
    },
    dialogAdd: function() {
      this.roleForm = {
        'id': '',
        'roleCode': '',
        'roleName': '',
        'needga': '',
        'roleDest': ''
      };
      this.roleDialog = true;
    },
    dialogEdit: function(row, column) {
      this.$http({
        url: '/system/role/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.roleForm = {
            'id': res.object.id,
            'roleCode': res.object.roleCode,
            'roleName': res.object.roleName,
            'needga': res.object.needga,
            'roleDest': res.object.roleDest
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.roleDialog = true;
    },
    doGot: function() {
      this.$http({
        url: '/system/role/saveGrant',
        method: 'post',
        params: this.grantForm
      }).then(res => {
        if (res.code === 200) {
          this.$message.success(res.message);
        } else {
          this.$message.error(res.message || 'Has Error');
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.grantDialog = false;
    },
    doInitData: function() {
      this.$http({
        url: '/system/resource/data',
        method: 'post'
      }).then(res => {
        if (res.code === 200) {
          this.resData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.roleLoading = true;
      this.$http({
        url: '/system/role/data',
        method: 'post',
        params: this.pageParams
      }).then(res => {
        if (res.code === 200) {
          this.roleData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.roleLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/system/role/save',
            method: 'post',
            params: this.roleForm
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
          this.roleDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该角色, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/system/role/del',
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
    }
  }
};
</script>

<style lang="scss" scoped>

</style>
