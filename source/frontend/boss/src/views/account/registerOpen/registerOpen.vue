<template>
  <div id="main-registerOpen">
    <div v-if="isShow2">
      <!-- 搜索表单 -->
      <el-form
        ref="registerOpen"
        :model="registerOpen"
        label-position="right"
        label-width="80px"
        class="registerOpenForm"
      >
        <el-form-item label="账户名" class="topInquire">
          <el-input v-model="registerOpen.accountName" placeholder="请输入账户名"></el-input>
        </el-form-item>
        <el-form-item label="申请状态" class="topInquire" style="z-index=1">
          <el-select v-model="registerOpen.registerStatus" placeholder="请选择">
            <el-option label="未审核" :value="0"></el-option>
            <el-option label="已审核" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item class="topInquire222" style="float: left;">
          <el-button
            type="primary"
            icon="el-icon-search"
            class="inquireBtn"
            @click.native="queryList"
          >查询</el-button>
        </el-form-item>
      </el-form>
      <!-- 开户表格 -->
      <el-table
        ref="interfaceTable"
        v-loading="loading"
        :data="tableData"
        tooltip-effect="dark"
        border
        highlight-current-row
      >
        <el-table-column align="center" type="index" label="序号" width="60"></el-table-column>
        <el-table-column align="left" prop="id" label="账户ID"></el-table-column>
        <el-table-column align="left" prop="accountName" label="账户名"></el-table-column>
        <el-table-column align="left" prop="accountType.dictDest" label="客户类型"></el-table-column>
        <el-table-column align="left" prop="country.cnName" label="国家地区"></el-table-column>
        <el-table-column align="left" prop="region" label="区号"></el-table-column>
        <el-table-column align="left" prop="mobile" label="手机号码"></el-table-column>
        <el-table-column align="left" prop="email" label="邮箱" width="240"></el-table-column>
        <el-table-column align="left" prop="accountPortfolioType.dictDest" label="账户类型"></el-table-column>
        <el-table-column align="left" prop="createDate" :formatter="dateFormat" label="提交时间"></el-table-column>
        <el-table-column align="left" prop="operate" label="操作" width="240">
          <template slot-scope="scope">
            <el-button
              v-show="isShow"
              style="height: 22px;padding: 0 10px;"
              size="mini"
              type="success"
              @click="approvedRegister(scope.row.id)"
            >通过</el-button>
            <el-button
              style="height: 22px;padding: 0 10px;"
              size="mini"
              @click="detailsRegister(scope.$index, scope.row)"
            >查看</el-button>
            <el-button
              v-show="!isShow"
              style="height: 22px;padding: 0 10px;"
              size="mini"
              @click="accountInformation(scope.$index, scope.row)"
            >开户资料</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 分页 -->
      <div class="paginationDiv">
        <span>第 {{ num }} 至 {{ numLast }} 项，共{{ pageSize }}项</span>
        <el-pagination
          :current-page="pageParams.page"
          layout="total, prev, pager, next, jumper"
          :total="total"
          class="pagination"
          @current-change="doSearch"
        >
        </el-pagination>
      </div>
      <!-- 查看开户资料弹框 -->
      <el-dialog
        title="PDF"
        :visible.sync="accountDialogVisible"
        width="500px"
        class="accountDialogVisible"
      >
        <el-row>
          <el-col :span="18">
            {{ pdfType }}开户资料
          </el-col>
          <el-col :span="6">
            <div v-if="accountType==='个人'">
              <a @click="getAccountInformation('personal/pdf/preview')">预览</a>
              <a @click="getAccountInformation('personal/pdf/download')">下载</a>
            </div>
            <div v-else-if="accountType==='有限责任公司'">
              <a @click="getAccountInformation('limitedLiablity/pdf/preview')">预览</a>
              <a @click="getAccountInformation('limitedLiablity/pdf/download')">下载</a>
            </div>
            <div v-else>
              <a @click="getAccountInformation('partnerShip/pdf/preview')">预览</a>
              <a @click="getAccountInformation('partnerShip/pdf/download')">下载</a>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="18">
            {{ pdfType }}W-8资料
          </el-col>
          <el-col :span="6">
            <div v-if="accountType==='个人'">
              <a @click="getAccountInformation('personal/w8Pdf/preview')">预览</a>
              <a @click="getAccountInformation('personal/w8Pdf/download')">下载</a>
            </div>
            <div v-else-if="accountType==='有限责任公司'">
              <a @click="getAccountInformation('limitedLiablity/w8Pdf/preview')">预览</a>
              <a @click="getAccountInformation('limitedLiablity/w8Pdf/download')">下载</a>
            </div>
            <div v-else>
              <a @click="getAccountInformation('partnerShip/w8Pdf/preview')">预览</a>
              <a @click="getAccountInformation('partnerShip/w8Pdf/download')">下载</a>
            </div>
          </el-col>
        </el-row>
        <div v-if="accountType==='个人'">
          <el-row>
            <el-col :span="18">
              {{ pdfType }}CRS资料
            </el-col>
            <el-col :span="6">
              <!-- <div v-if="accountType==='个人'"> -->
              <a @click="getAccountInformation('personal/crsPdf/preview')">预览</a>
              <a @click="getAccountInformation('personal/crsPdf/download')">下载</a>
            </el-col>
          </el-row>
        </div>
        <div v-else>
          <el-row>
            <el-col :span="18">
              {{ pdfType }}CRS-E资料
            </el-col>
            <el-col :span="6">
              <div v-if="accountType==='有限责任公司'">
                <a @click="getAccountInformation('limitedLiablity/crsEntityPdf/preview')">预览</a>
                <a @click="getAccountInformation('limitedLiablity/crsEntityPdf/download')">下载</a>
              </div>
              <div v-else>
                <a @click="getAccountInformation('partnerShip/crsEntityPdf/preview')">预览</a>
                <a @click="getAccountInformation('partnerShip/crsEntityPdf/download')">下载</a>
              </div>
            </el-col>
          </el-row>
          <div v-for="(item,indexId) in crsId" :key="indexId">
            <el-row>
              <el-col :span="18">
                {{ pdfType }}CRS-CP{{ indexId+1 }}资料
              </el-col>
              <el-col :span="6">
                <div v-if="accountType==='有限责任公司'">
                  <a @click="getAccountInformation('limitedLiablity/crsCpPdf/preview',item.id)">预览</a>
                  <a @click="getAccountInformation('limitedLiablity/crsCpPdf/download',item.id)">下载</a>
                </div>
                <div v-else>
                  <a @click="getAccountInformation('partnerShip/crsCpPdf/preview',item.id)">预览</a>
                  <a @click="getAccountInformation('partnerShip/crsCpPdf/download',item.id)">下载</a>
                </div>
              </el-col>
            </el-row>
          </div>
        </div>
      </el-dialog>
    </div>

    <router-view v-if="!isShow2" :user-info="userInfo"></router-view>
  </div>
</template>
<script>
// import registerOpenDetails from './registerOpenDetails';
export default {
  components: {
    // registerOpenDetails
  },
  data() {
    return {
      pdfType: null,
      accountDialogVisible: false,
      isShow: Boolean,
      loading: true,
      tabView: 'message',
      userInfo: {
        // 当前row的用户信息
        accountType: '',
        id: ''
      },
      registerOpen: {
        accountName: '',
        registerStatus: 0
      },
      tableData: [],
      pageParams: {
        rows: 10,
        page: 1,
        totalPage: 0
      },
      isShow2: true,
      num: 1, // 页码初始数
      numLast: 10,
      rowLength: 10, // 当前页总数据
      pageSize: 10, // 页码数
      total: null,
      pdfUrl: null,
      accountType: null,
      crsId: []
    };
  },
  created() {
    var url = window.location.href;
    if (url.indexOf('registerOpenDetails') !== -1) {
      this.userInfo = JSON.parse(sessionStorage.getItem('userInfoKey'));
      this.isShow2 = false;
      // this.isShow = JSON.parse(sessionStorage.removeItem('isShow'));
    } else {
      if (JSON.parse(sessionStorage.getItem('registerOpenKey'))) {
        this.registerOpen = JSON.parse(sessionStorage.getItem('registerOpenKey'));
      }
      // 页面第一次进来的时候需要设置搜索条件的默认值
      if (JSON.parse(sessionStorage.getItem('registerStatus')) === null) {
        this.registerOpen.registerStatus = 0;
      } else {
        this.registerOpen.registerStatus = JSON.parse(sessionStorage.getItem('registerStatus'));
      }
      // debugger;
      this.getInquireList();
    }
  },
  methods: {
    getCrsId(url) {
      const id = sessionStorage.getItem('idPdf').replace(/"/g, '');
      this.$http({
        url: url,
        method: 'get',
        params: { accountId: id }
      })
        .then(res => {
          if (res.code === 200) {
            debugger;
            this.crsId = res.object;
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    detailsRegister(index, row) { // 查看开户数据详情页面
      const row1 = JSON.parse(JSON.stringify(row));
      row1.accountType = row.accountType.dictValue;
      sessionStorage.setItem('userInfoKey', JSON.stringify(row1));
      sessionStorage.setItem('currentRowIndex', index);
      // sessionStorage.removeItem('registerOpenKey');
      this.userInfo = row1;
      const accountId = row1.id;
      const accountName = row1.accountName;
      this.$router.push({ path: '/account/registerOpen/registerOpenDetails', query: { accountId: accountId, accountName: accountName }});
      this.isShow2 = false;
    },
    accountInformation(index, row) {
      this.accountType = row.accountType.dictDest;
      sessionStorage.setItem('idPdf', JSON.stringify(row.id));
      this.accountDialogVisible = true;
      this.getCrsId();
      // if(row.accountType.dictDest==='个人'){
      //   this.pdfType = '个人'
      // }
      this.pdfType = row.accountType.dictDest;
      if (this.pdfType === '有限责任公司') {
        this.getCrsId('/account/registerOpen/limitedLiablity/findCrsEntityContrs');
      } else {
        this.getCrsId('/account/registerOpen/partnerShip/findCrsEntityContrs');
      }
    },
    getAccountInformation(url, crsId) {
      const id = sessionStorage.getItem('idPdf').replace(/"/g, '');
      this.$http({
        url: `account/registerOpen/${url}`,
        method: 'get',
        responseType: 'arraybuffer',
        params: { accountId: id, lang: 'en_US', crsEntityContrId: crsId }
      }).then(res => {
        // 下载pdf
        if (url === 'personal/pdf/download' || url === 'personal/w8Pdf/download' || url === 'personal/crsPdf/download' ||
            url === 'limitedLiablity/pdf/download' || url === 'limitedLiablity/w8Pdf/download' || url === 'limitedLiablity/crsEntityPdf/download' || url === 'limitedLiablity/crsCpPdf/download' ||
            url === 'partnerShip/pdf/download' || url === 'partnerShip/w8Pdf/download' || url === 'partnerShip/crsEntityPdf/download' || url === 'partnerShip/crsCpPdf/download') {
          this.pdfUrl = window.URL.createObjectURL(new Blob([res], { type: `application/pdf` }));
          let fname;
          if (url === 'personal/pdf/download' || url === 'limitedLiablity/pdf/download' || url === 'partnerShip/pdf/download') {
            fname = `开户资料`; // 下载文件的名字
          } else if (url === 'personal/crsPdf/download') {
            fname = `CRS资料`;
          } else if (url === 'limitedLiablity/crsEntityPdf/download' || url === 'partnerShip/crsEntityPdf/download') {
            fname = `CRS-E资料`; // 下载文件的名字
          } else if (url === 'limitedLiablity/crsCpPdf/download' || url === 'partnerShip/crsCpPdf/download') {
            fname = `CRS-CP资料`; // 下载文件的名字
          } else {
            fname = `W-8资料`;
          }
          const link = document.createElement('a');
          link.href = this.pdfUrl;
          link.setAttribute('download', fname);
          document.body.appendChild(link);
          link.click();
        } else {
          // 预览pdf
          const binaryData = [];
          binaryData.push(res);
          this.pdfUrl = window.URL.createObjectURL(new Blob(binaryData, { type: 'application/pdf' }));
          // window.open('/static/pdf/web/viewer.html?file=' + encodeURIComponent(this.pdfUrl));
          window.open(this.pdfUrl);
        }
      }).catch(error => {
        this.$message.error('' + error + '');
      });
    },
    dateFormat(row, column) {
      // 日期格式化 时间戳 >> 'YYYY-MM-DD HH:mm:ss'
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },
    getInquireList(page = this.pageParams.page) {
      // 获取列表
      this.pageParams.page = page;
      this.dictLoading = true;
      this.pageParams.page = +sessionStorage.getItem('currentPages');
      if (this.pageParams.page !== 0) {
        this.num = (this.pageParams.page - 1) * 10 + 1;
      }
      if (this.$route.params.key !== 'key') {
        sessionStorage.removeItem('currentRowIndex');
        sessionStorage.removeItem('currentPages');
        sessionStorage.removeItem('registerOpenKey');
        this.registerOpen.accountName = '';
      }
      // debugger;
      this.$http({
        url: '/account/registerOpen/findAccountList',
        method: 'post',
        params: Object.assign(this.pageParams, this.registerOpen)
      })
        .then(res => {
          if (res.code === 200) {
            this.tableData = res.rows;
            this.pageParams.totalPage = res.totalPage;
            this.numLast = (res.pages - 1) * 10 + res.rows.length;
            this.total = res.total;
            this.pageSize = res.rows.length;
            this.loading = false;
            if (this.registerOpen.registerStatus === 0) {
              this.isShow = true;
              sessionStorage.setItem('registerStatus', 0);
            } else {
              this.isShow = false;
              sessionStorage.setItem('registerStatus', 1);
            }
            if (this.$refs.interfaceTable) {
              this.$refs.interfaceTable.setCurrentRow(this.tableData[sessionStorage.getItem('currentRowIndex')]);
            }
            if (res.rows.length !== 0) {
              this.tabView = 'message';
            } else {
              this.tabView = '';
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    queryList() {
      // 模糊查询列表
      sessionStorage.setItem('registerOpenKey', JSON.stringify(this.registerOpen)); // 存储搜索条件
      sessionStorage.removeItem('currentRowIndex');
      sessionStorage.removeItem('currentPages');
      this.num = 1;
      this.pageParams.page = 1;
      this.$http({
        url: '/account/registerOpen/findAccountList',
        method: 'post',
        params: Object.assign(this.pageParams, this.registerOpen)
      })
        .then(res => {
          if (res.code === 200) {
            if (this.registerOpen.registerStatus === 0) {
              this.isShow = true;
              // sessionStorage.setItem('isShow', true);
              sessionStorage.setItem('registerStatus', 0);
            } else {
              this.isShow = false;
              // sessionStorage.setItem('isShow', false);
              sessionStorage.setItem('registerStatus', 1);
            }
            this.tableData = res.rows;
            this.pageParams.totalPage = res.totalPage;
            this.numLast = (res.pages - 1) * 10 + res.rows.length;
            this.total = res.total;
            this.pageSize = res.rows.length;
            this.loading = false;
            if (res.rows.length !== 0) {
              this.tabView = 'message';
            } else {
              this.tabView = '';
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    approvedRegister(id) {
      // 根据ID更新注册开户状态为通过审核
      this.$confirm('此操作将永久通过审核, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/account/registerOpen/approvedRegister',
          method: 'post',
          params: { id }
        })
          .then(res => {
            if (res.code === 200) {
              this.getInquireList();
            }
          })
          .catch(error => {
            this.$message.error(error);
          });
        this.$message({
          type: 'success',
          message: '已审核成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消审核'
        });
      });
    },
    doSearch(val) {
      sessionStorage.removeItem('currentRowIndex');
      this.num = (val - 1) * 10 + 1;
      this.pageParams.page = val;
      sessionStorage.setItem('currentPages', this.pageParams.page);
      // 当前页
      this.getInquireList(val);
    }
  }
};
</script>
<style lang="scss">
#main-registerOpen{
  padding: 20px;
  .registerOpenForm {
    .el-input__inner {
      line-height: 23px;
    }
  }
  .el-table__header-wrapper {
    tr {
      th {
        background-color: #f3f6f9 !important;
      }
    }
  }
  .accountDialogVisible{
    .el-dialog__header{
      border-bottom: 1px solid #dbdce0;
      .el-dialog__title{
        font-size: 14px;
        font-weight: 700;
      }
    }
    .el-row{
      font-size: 12px;
      padding-bottom: 16px;
    }
    .el-col-6{
      color:rgb(56, 169, 236);
      div a:hover{
        color: #ae1207;
      }
      :first-child{
        padding-right: 15px;;
      }
    }

  }
}
#main-registerOpen .navTableCheck {
  .el-tabs__nav {
    background: #f3f6f9;
    border-radius: 0px;
    .el-tabs__item {
      padding: 0 30px;
      &:hover {
        color: #ae1207;
      }
    }
  }
  .is-top .is-active {
    background: #fff !important;
    border-top: 3px solid rgb(174, 18, 7);
    box-sizing: content-box;
    color: #ae1207;
  }
}
</style>
<style lang="scss" scoped>
#main-registerOpen {
  .paginationDiv{
          line-height: 26px;
          padding:20px 0;
          span{
               line-height: 25px;
               font-size: 13px;
               color: #606266;
          }
          .pagination{
               display: block;
               float: right;
          }
     }
  .topInquire {
    float: left;
    margin-right: 10px;
  }
  .inquireBtn {
    margin-top: 4px;
    width: 73px;
    height: 30px;
  }
  .auditTabView {
    width: 100%;
    margin-left: 10px;
    margin-top: 50px;
    .auditTabVie-content {
      margin: 0 auto;
      .audit-title {
        margin-bottom: 10px;
      }
    }
  }
}
</style>
