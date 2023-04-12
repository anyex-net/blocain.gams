<template>
  <div>
    <div v-if="userInfo.accountType==='personal'" class="fileContent">
      <el-table
        v-loading="loading"
        :data="personal.tableData"
        tooltip-effect="dark"
        border
        highlight-current-row
        class="tableBox"
      >
        <!-- <el-table-column align="center" type="index" label="序号" ></el-table-column> -->
        <el-table-column align="left" prop="fileName" label="文件"></el-table-column>
        <el-table-column align="left" prop="status" label="状态" width="140"></el-table-column>
        <el-table-column align="left" prop="filePath" label="操作" width="240">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="buttonSize"
              type="danger"
              @click="browseG(scope.row.filePath)"
            >浏览</el-button>
          </template>
          <img :src="imgUrl" alt="" class="imgUrl">
        </el-table-column>
      </el-table>
    </div>
    <div v-else-if="userInfo.accountType==='limitedLiablity'" class="fileContent">
      <el-table
        v-loading="loading"
        :data="limitedLiablity.tableData"
        tooltip-effect="dark"
        border
        highlight-current-row
        class="tableBox"
      >
        <!-- <el-table-column align="center" type="index" label="序号" ></el-table-column> -->
        <el-table-column align="left" prop="fileName" label="文件"></el-table-column>
        <el-table-column align="left" prop="name" label="股东类型" width="140"></el-table-column>
        <el-table-column align="left" prop="status" label="状态" width="140"></el-table-column>
        <el-table-column align="left" prop="filePath" label="操作" width="240">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="buttonSize"
              type="danger"
              @click="browseG(scope.row.filePath)"
            >浏览</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-else-if="userInfo.accountType==='partnerShip'" class="fileContent">
      <el-table
        v-loading="loading"
        :data="partnerShip.tableData"
        tooltip-effect="dark"
        border
        highlight-current-row
        class="tableBox"
      >
        <!-- <el-table-column align="center" type="index" label="序号" ></el-table-column> -->
        <el-table-column align="left" prop="fileName" label="文件"></el-table-column>
        <el-table-column align="left" prop="name" label="股东类型" width="140"></el-table-column>
        <el-table-column align="left" prop="status" label="状态" width="140"></el-table-column>
        <el-table-column align="left" prop="filePath" label="操作" width="240">
          <template slot-scope="scope">
            <el-button
              size="mini"
              class="buttonSize"
              type="danger"
              @click="browseG(scope.row.filePath)"
            >浏览</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <img v-if="fileType==='png'||fileType==='jpg'" :src="imgUrl" alt="" class="imgUrl">
  </div>
</template>
<script>
export default {
  name: 'Regulate',
  props: {
    userInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      fileType: '',
      imgUrl: '',
      loading: false,
      personal: {
        tableData: []
      },
      limitedLiablity: {
        tableData: []
      },
      partnerShip: {
        tableData: []
      }
    };
  },
  watch: {
    userInfo: { // props
      handler(val, oldVal) {
        this.userInfo = val;

        this.Port();
      },
      deep: true
    }
  },
  created() {
  },
  mounted() {
    this.Port();
  },
  methods: {
    clearImg() {
      this.imgUrl = '';
    },
    // personal
    // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')}
    // 请求接口后缀  G 个人 Y 有限 H 合伙
    Port() {
      this.imgUrl = '';
      this.personal.tableData = [];
      if (this.userInfo.accountType === 'personal') {
        this.findFileListG();
      } else if (this.userInfo.accountType === 'limitedLiablity') {
        this.findFileListY();
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.findFileListH();
      }
    },
    findFileListG() { // 个人文件
      this.$http({
        url: '/account/registerOpen/personal/findFile',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            for (let i = 0; i < res.object.length; i++) {
              res.object[i].status = '已上传';
            }
            this.personal.tableData = res.object;
          }
        } else {
          this.$message.error('' + res.code + '');
        }
      }).catch(error => {
        this.$message.error('' + error + '');
      });
    },
    findFileListY() { // 公司文件
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findFile',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            for (let i = 0; i < res.object.length; i++) {
              res.object[i].status = '已上传';
              res.object[i].fileName = res.object[i].fileIdentification.dest;
              if (res.object[i].personal) {
                res.object[i].name = res.object[i].personal.personalType.dictDest || res.object[i].personal.personalTypeTwo.dictDest;
              }
            }
            this.limitedLiablity.tableData = res.object;
          }
        } else {
          this.$message.error('' + res.code + '');
        }
      }).catch(error => {
        this.$message.error('' + error + '');
      });
    },
    findFileListH() { // 合伙企业文件
      this.$http({
        url: '/account/registerOpen/partnerShip/findFile',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            for (let i = 0; i < res.object.length; i++) {
              res.object[i].status = '已上传';
              res.object[i].fileName = res.object[i].fileIdentification.dest;
              if (res.object[i].personal) {
                res.object[i].name = res.object[i].personal.personalType.dictDest || res.object[i].personal.personalTypeTwo.dictDest;
              }
            }
            this.partnerShip.tableData = res.object;
          }
        } else {
          this.$message.error('' + res.code + '');
        }
      }).catch(error => {
        this.$message.error('' + error + '');
      });
    },
    browseG(url) {
      var index = url.lastIndexOf('\.');
      this.fileType = url.substring(index + 1, url.length);

      // var regions = 'ap-northeast-1';
      // var s3 = 's3-' + regions + '.amazonaws.com/';
      // var bucketName = url.substring(0, url.indexOf('.s3-' + regions)).substring(8);
      // var fileName = url.substring(url.indexOf(s3) + s3.length, url.length);
      if (url === '') {
        this.$message.error('未上传文件');
      } else {
        this.$http({
          // url: '/common/upload/getPresignedUrl',
          url: 'account/registerOpen/getPresignedUrl',
          methods: 'get',
          params: { url: url }
        }).then(res => {
          if (res.message === 'success') {
            this.imgUrl = res.object;
            if (this.fileType === 'pdf') { window.open(res.object); }
          } else {
            this.$message(res.message);
          }
        }).catch(error => {
          this.$message(error);
        });
      }
    }
  }
};
</script>
<style lang="scss" scoped>
  .buttonSize {
    height: 24px;
    width: 44px;
  }
  .fileContent {
    width: 90%;
    margin-top: 20px;
    padding: 0 44px;
    // border: 1px solid $Cor999;
    border-radius: 5px;
    >div:first-child {
      margin-top: 40px;
    }
    ul {
      overflow:hidden;
      margin-bottom: 0px;
      margin-top: 26px;
      margin-left: 10px;
      li {
        float: left;
        list-style: none;
        color: #999;
      }
      li:first-child {
        width: 50%;
        margin-right: 20px;
        overflow: hidden;
      }
      li:nth-child(2) {
        width: 34%;
        word-wrap:break-word;
        text-align: right;
      }
      li:nth-child(n+3){
        width: 50%;
        word-wrap:break-word;
        text-align: right;
      }
    }
    ul:first-child {
      margin-top: 26px;
    }
  }
  .imgUrl{
    padding:20px 44px;
  }

</style>
