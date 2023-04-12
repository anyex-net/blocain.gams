<template>
  <div class="accountManger">
    <div v-if="isShow">
      <!-- 查询条件表单 -->
      <el-form
        ref="formSearch"
        :inline="true"
        :model="formSearch"
        class="formSearch"
        :rules="rules"
      >
        <el-row>
          <el-col :span="6">
            <el-form-item label="账户名">
              <el-input v-model="formSearch.accountName" placeholder="账户名"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="客户名称">
              <el-input v-model="formSearch.customerName" placeholder="客户名称"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="手机号">
              <el-input v-model="formSearch.mobile" placeholder="手机号"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="国家">
              <el-select v-model="formSearch.country" filterable placeholder="国家" clearable>
                <el-option
                  v-for="(item,index) in countryOptions"
                  :key="index"
                  :label="item.cnName"
                  :value="item.sCode"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <el-form-item label="状态">
              <el-select v-model="formSearch.status" placeholder="状态" clearable>
                <el-option label="正常" value="0"></el-option>
                <el-option label="冻结" value="1"></el-option>
                <el-option label="未激活" value="-1"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="客户类型" prop="accountType">
              <el-select v-model="formSearch.accountType" placeholder="客户类型" clearable>
                <el-option label="个人" value="personal"></el-option>
                <el-option label="有限责任公司" value="limitedLiablity"></el-option>
                <el-option label="合伙公司" value="partnerShip"></el-option>
                <!-- <el-option label="未开户" value="noAccount"></el-option> -->
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="6" class="daterange">
            <el-form-item label="注册时间">
              <el-date-picker
                v-model="formSearch.accountCreateDate"
                type="daterange"
                format="yyyy-MM-dd"
                value-format="timestamp"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
              ></el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="IB账户">
              <el-input v-model="formSearch.ibAccountCode" placeholder="IB账户"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item class="formSearchBtn">
          <el-button type="primary" icon="el-icon-search" @click="queryAccount('formSearch')">查询</el-button>
          <el-button type="success" icon="el-icon-download" @click="exportToExcel">导出</el-button>
        </el-form-item>
      </el-form>
      <!-- 用户账号信息表格 -->
      <el-table
        ref="accountTable"
        :data="accountTable"
        border
        fit
        class="accountTable"
        highlight-current-row
      >
        <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
        <el-table-column prop="accountId" label="账户ID"></el-table-column>
        <el-table-column prop="accountName" label="账户名"></el-table-column>
        <el-table-column prop="customerName" label="客户名称"></el-table-column>
        <el-table-column prop="accounType" label="客户类型"></el-table-column>
        <el-table-column prop="country" label="国家地区"></el-table-column>
        <el-table-column prop="region" label="区号"></el-table-column>
        <el-table-column prop="mobile" label="手机号码"></el-table-column>
        <el-table-column prop="email" label="邮箱" width="250px"></el-table-column>
        <el-table-column prop="accountPortfolioTypeDest" label="账号类型"></el-table-column>
        <el-table-column prop="status" label="状态"></el-table-column>
        <el-table-column prop="createDate" label="注册时间"></el-table-column>
        <el-table-column label="操作" width="200px">
          <template slot-scope="scope">
            <el-button
              v-if="scope.row.status==='正常'"
              size="mini"
              type="danger"
              @click="handleFreeze(scope.$index, scope.row)"
            >冻结</el-button>
            <el-button
              v-if="scope.row.status==='冻结'"
              size="mini"
              type="success"
              plain
              @click="handleCancleFreeze(scope.$index, scope.row)"
            >解冻</el-button>
            <!-- <el-button
              size="mini"
              @click="handleReset(scope.$index, scope.row)"
            >重置密码</el-button>-->
            <el-button size="mini" @click="rowClick(scope.$index, scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 重置密码弹框 -->
      <!-- <el-dialog title="重置密码" :visible.sync="resetPasswordsDialog">
        <el-form ref="userForm" :model="userForm" :rules="userRules">
          <el-form-item label="用户名称" :label-width="formLabelWidth">
            <el-input v-model="userForm.accountName" autocomplete="off" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="新密码" :label-width="formLabelWidth" prop="newPassword">
            <el-input v-model.trim="userForm.newPassword" autocomplete="off" type="password"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" :label-width="formLabelWidth" prop="confirmPassword">
            <el-input v-model.trim="userForm.confirmPassword" autocomplete="off" type="password"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="resetPasswordsDialog = false">取 消</el-button>
          <el-button type="primary" @click="confirmSendUser('userForm')">确 定</el-button>
        </div>
      </el-dialog>-->
      <!-- 冻结弹框 -->
      <el-dialog title="冻结账户" :visible.sync="freezeDialog" width="30%">
        <span>是否确认冻结该账户?</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="freezeDialog = false">取 消</el-button>
          <el-button type="primary" @click="confirmFreeze">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 解冻弹框 -->
      <el-dialog title="解冻账户" :visible.sync="noFreezeDialog" width="30%">
        <span>是否确认解冻该账户?</span>
        <span slot="footer" class="dialog-footer">
          <el-button @click="noFreezeDialog = false">取 消</el-button>
          <el-button type="primary" @click="confirmNoFreeze">确 定</el-button>
        </span>
      </el-dialog>
      <!-- 分页 -->
      <div class="paginationDiv">
        <span>第 {{ num }} 至 {{ numLast }} 项，共{{ pageSize }}项</span>
        <el-pagination
          :current-page="pageParams.page"
          layout="total, prev, pager, next, jumper"
          :total="total"
          class="pagination"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div>
    </div>
    <router-view v-else :account-info="accountInfo" @show="show"></router-view>
  </div>
</template>

<script>
export default {
  components: {
    // accountDetails
  },
  data() {
    // 校验规则
    var validateNewPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入新密码'));
      } else {
        if (value !== '') {
          this.$refs.userForm.validateField('confirmPassword');
          if (value.length > 16 || value.length < 8) {
            callback(new Error('密码长度在8-16位以内'));
          }
        }
        callback();
      }
    };
    var validateConfirmPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.userForm.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    // var validateAccountType = (rule, value, callback) => {
    //      if (this.formSearch.customerName) {
    //           callback(new Error('选择客户名称一定要同时选择客户类型'));
    //      } else {
    //           callback();
    //      }
    // };
    return {
      accountInfo: {}, // 每条表格数据
      countryOptions: [], // 国家集合
      formSearch: {
        // 表单搜索条件
        accountName: null, // 账户名
        customerName: null, // 客户名
        mobile: null,
        country: null,
        ibAccountCode: null,
        accountType: null,
        status: null,
        accountCreateDate: [],
        accountCreateDateEnd: null
      },
      accountTable: [
        // 用户账号信息
      ],
      //  resetPasswordsDialog: false,
      freezeDialog: false,
      noFreezeDialog: false,
      userForm: {
        accountName: null,
        newPassword: null,
        confirmPassword: null
      },
      formLabelWidth: '120px',
      userRules: {
        // 确认密码校验规则
        newPassword: [
          { validator: validateNewPass, trigger: 'blur', required: true }
        ],
        confirmPassword: [
          { validator: validateConfirmPass, trigger: 'blur', required: true }
        ]
      },
      rules: {
        accountType: [
          {
            required: false,
            message: '选择客户名称一定要同时选择客户类型!',
            trigger: 'blur'
          }
          // {validator: validateAccountType, trigger: 'blur', required: true}
        ]
      },
      num: 1, // 页码初始数
      numLast: 10,
      rowLength: 10, // 当前页总数据
      pageSize: 10, // 页码数
      pageParams: {
        rows: 10,
        page: 1, // 当前页
        totalPage: 0
      },
      pageParamsAll: {
        rows: 10000,
        page: 1, // 当前页
        totalPage: 0
      },
      total: null,
      // totalPage:null,
      isShow: true,
      accountAllTable: []
    };
  },
  watch: {
    $route: {
      handler: function(val, oldVal) {},
      // 深度观察监听
      deep: true
    }
  },
  created() {
    // this.getAccountTable();
    this.getCountryOptions();
    var url = window.location.href;
    if (url.indexOf('accountDetails') !== -1) {
      this.accountInfo = JSON.parse(sessionStorage.getItem('accountInfoKey'));
      this.isShow = false;
    } else {
      if (sessionStorage.getItem('formSearchKey')) {
        this.formSearch = JSON.parse(sessionStorage.getItem('formSearchKey'));
      }
      this.getAccountTable();
    }
  },
  methods: {
    queryAccount(formSearch) {
      sessionStorage.setItem('formSearchKey', JSON.stringify(this.formSearch)); // 存储搜索条件
      sessionStorage.removeItem('currentRowIndex');
      sessionStorage.removeItem('currentPage');
      this.num = 1;
      this.pageParams.page = 1;
      if (this.formSearch.customerName) {
        // 处理校验规则
        this.$refs.formSearch.rules.accountType[0].required = true;
        this.$refs[formSearch].validate(valid => {});
      } else {
        this.$refs.formSearch.rules.accountType[0].required = false;
        this.$refs.formSearch.clearValidate();
      }
      // this.getAccountTable();
      if (this.formSearch.accountCreateDate) {
        // 处理注册时间
        this.formSearch.accountCreateDateStart = this.formSearch.accountCreateDate[0];
        this.formSearch.accountCreateDateEnd = this.formSearch.accountCreateDate[1];
      } else {
        this.formSearch.accountCreateDateStart = null;
        this.formSearch.accountCreateDateEnd = null;
      }
      var formSearch2 = JSON.parse(JSON.stringify(this.formSearch));
      delete formSearch.accountCreateDate;
      this.$http({
        url: '/account/management/accountList',
        method: 'post',
        params: Object.assign(this.pageParams, formSearch2)
      })
        .then(res => {
          if (res.code === 200) {
            let accountTableList = JSON.parse(JSON.stringify(res.rows));
            this.total = res.total;
            this.accountTable = accountTableList;
            this.pageSize = res.rows.length;
            this.rowLength = res.rows.length;
            this.numLast = (res.pages - 1) * 10 + this.rowLength;
            accountTableList = accountTableList.map(item => {
              item.createDate = this.$verify.NowDate2(item.createDate);
              if (item.status === 0) {
                item.status = '正常';
              } else if (item.status === 1) {
                item.status = '冻结';
              } else {
                item.status = '未激活';
              }
              if (item.accounType === 'personal') {
                item.accounType = '个人';
              } else if (item.accounType === 'partnerShip') {
                item.accounType = '合伙企业';
              } else {
                item.accounType = '有限责任公司';
              }
              return item;
            });
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    exportToExcel() {
      if (this.formSearch.accountCreateDate) {
        this.formSearch.accountCreateDateStart = this.formSearch.accountCreateDate[0];
        this.formSearch.accountCreateDateEnd = this.formSearch.accountCreateDate[1];
      } else {
        this.formSearch.accountCreateDateStart = null;
        this.formSearch.accountCreateDateEnd = null;
      }
      const formSearch = JSON.parse(JSON.stringify(this.formSearch));
      delete formSearch.accountCreateDate;
      this.$http({
        url: '/account/management/accountList',
        method: 'post',
        params: Object.assign(this.pageParamsAll, formSearch)
      })
        .then(res => {
          if (res.code === 200) {
            let accountTableAllList = JSON.parse(JSON.stringify(res.rows));
            accountTableAllList = accountTableAllList.map(item => {
              item.createDate = this.$verify.NowDate2(item.createDate);
              if (item.status === 0) {
                item.status = '正常';
              } else if (item.status === 1) {
                item.status = '冻结';
              } else {
                item.status = '未激活';
              }
              if (item.accounType === 'personal') {
                item.accounType = '个人';
              } else if (item.accounType === 'partnerShip') {
                item.accounType = '合伙企业';
              } else {
                item.accounType = '有限责任公司';
              }
              return item;
            });
            for (var i = 0; i < accountTableAllList.length; i++) {
              accountTableAllList[i].index = i + 1;
            }
            this.accountAllTable = accountTableAllList;
            const { export_json_to_excel } = require('./js/Export2Excel');
            const tHeader = [
              '序号',
              '账户ID',
              '账户名',
              '客户名称',
              '客户类型',
              '国家地区',
              '区号',
              '手机号码',
              '邮箱',
              '账号类型',
              '状态',
              '注册时间'
            ];
            const filterVal = [
              'index',
              'accountId',
              'accountName',
              'customerName',
              'accounType',
              'country',
              'region',
              'mobile',
              'email',
              'accountPortfolioTypeDest',
              'status',
              'createDate'
            ];
            const list = this.accountAllTable;
            const data = this.formatJson(filterVal, list);
            export_json_to_excel(tHeader, data, '账户信息列表');
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
      // excel数据导出
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]));
    },
    rowClick(index, row) {
      sessionStorage.setItem('accountInfoKey', JSON.stringify(row));
      sessionStorage.setItem('currentRowIndex', index);
      this.accountInfo = row;
      this.accountId = row.accountId;
      this.accountName = row.accountName;
      this.$router.push({
        path: '/account/account/accountDetails',
        query: { accountId: this.accountId, accountName: this.accountName }
      });
    },
    handleFreeze(index, row) {
      this.freezeDialog = true;
      this.accountId = row.accountId;
    },
    handleCancleFreeze(index, row) {
      this.accountId = row.accountId;
      this.noFreezeDialog = true;
    },
    confirmFreeze() {
      this.$http({
        url: '/account/manage/account/frozenAccount',
        method: 'post',
        params: { accountId: this.accountId }
      })
        .then(res => {
          if (res.code === 200) {
            this.getAccountTable();
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
      this.freezeDialog = false;
    },
    handleCurrentChange(val) {
      sessionStorage.removeItem('currentRowIndex');
      this.getAccountTable();
      this.num = (val - 1) * 10 + 1;
      this.pageParams.page = val;
      sessionStorage.setItem('currentPage', this.pageParams.page);
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    confirmNoFreeze() {
      this.$http({
        url: '/account/manage/account/unfrozenAccount',
        method: 'post',
        params: { accountId: this.accountId }
      })
        .then(res => {
          if (res.code === 200) {
            this.getAccountTable();
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
      this.noFreezeDialog = false;
    },
    show() {
      this.isShow = true;
    },
    getAccountTable() {
      if (this.$route.params.key !== 'key') {
        sessionStorage.removeItem('currentRowIndex');
        sessionStorage.removeItem('currentPage');
        sessionStorage.removeItem('formSearchKey');
        for (var key in this.formSearch) {
          this.formSearch[key] = '';
        }
      }
      if (this.formSearch.accountCreateDate) {
        this.formSearch.accountCreateDateStart = this.formSearch.accountCreateDate[0];
        this.formSearch.accountCreateDateEnd = this.formSearch.accountCreateDate[1];
      } else {
        this.formSearch.accountCreateDateStart = null;
        this.formSearch.accountCreateDateEnd = null;
      }
      const formSearch = JSON.parse(JSON.stringify(this.formSearch));
      delete formSearch.accountCreateDate;
      // 获取当前页码数
      this.pageParams.page = +sessionStorage.getItem('currentPage');
      if (this.pageParams.page !== 0) {
        this.num = (this.pageParams.page - 1) * 10 + 1;
      }
      this.$http({
        url: '/account/management/accountList',
        method: 'post',
        params: Object.assign(this.pageParams, formSearch)
      })
        .then(res => {
          if (res.code === 200) {
            let accountTableList = JSON.parse(JSON.stringify(res.rows));
            this.total = res.total;
            this.accountTable = accountTableList;
            this.pageSize = res.rows.length;
            // 设置当前行高亮
            if (this.$refs.accountTable) {
              this.$refs.accountTable.setCurrentRow(
                accountTableList[sessionStorage.getItem('currentRowIndex')]
              );
            }
            this.rowLength = res.rows.length;
            this.numLast = (res.pages - 1) * 10 + this.rowLength;
            accountTableList = accountTableList.map(item => {
              item.createDate = this.$verify.NowDate2(item.createDate);
              if (item.status === 0) {
                item.status = '正常';
              } else if (item.status === 1) {
                item.status = '冻结';
              } else {
                item.status = '未激活';
              }
              if (item.accounType === 'personal') {
                item.accounType = '个人';
              } else if (item.accounType === 'partnerShip') {
                item.accounType = '合伙企业';
              } else {
                item.accounType = '有限责任公司';
              }
              return item;
            });
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getCountryOptions() {
      this.$http({
        url: '/common/region/findAll',
        method: 'get',
        params: { dictCode: 'country' }
      })
        .then(res => {
          if (res.code === 200) {
            this.countryOptions = res.object;
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.accountManger {
  background-color: #fff;
  padding: 0 20px;
  margin-top: 20px;
  .titleM {
    .title {
      border-left: 5px solid #ae1207;
      background: #fff;
      color: #ae1207;
      padding: 10px;
      margin: 0;
    }
  }
  .formSearchBtn {
    width: 100% !important;
    text-align: right;
    padding-right: 10px;
  }
  .paginationDiv {
    line-height: 26px;
    padding: 20px 0;
    span {
      line-height: 25px;
      font-size: 13px;
      color: #606266;
    }
    .pagination {
      display: block;
      float: right;
    }
  }
}
</style>
<style lang="scss">
.accountManger {
  .formSearch {
    font-size: 0;
    margin-top: 20px;
    .el-form-item {
      display: flex;
      .el-form-item__label {
        width: 80px;
        height: 28px !important;
        line-height: 28px;
      }
      .el-form-item__content {
        flex: 1;
        height: 28px !important;
        line-height: 28px;
        .el-select {
          width: 100%;
        }
        .el-input {
          height: 28px !important;
          line-height: 28px;
          .el-input__inner {
            height: 28px !important;
            line-height: 28px;
          }
          .el-select__caret {
            height: 28px !important;
            line-height: 28px;
          }
        }
      }
      .el-range__close-icon {
        line-height: 22px !important;
      }
      .el-date-editor {
        width: 100%;
        height: 28px;
        line-height: 28px;
        .el-range-separator {
          width: 12%;
        }
        .el-range-separator {
          line-height: 21px;
        }
        .el-range__icon {
          line-height: 20px !important;
        }
      }
      .el-button {
        height: 30px;
        padding: 0 !important;
        width: 80px;
      }
    }
  }
  .accountTable {
    background-color: rgb(246, 247, 249);
    border: 1px solid #ddd;
    .el-table__header-wrapper {
      tr {
        th {
          background-color: #f3f6f9 !important;
        }
      }
    }
  }
}
</style>

