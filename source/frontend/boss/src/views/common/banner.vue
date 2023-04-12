<template>
  <div class="app-container">

    <el-button style="margin-bottom:10px;" type="success" size="mini" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>

    <el-table v-loading="bannerLoading" :data="bannerData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column prop="title" label="标题" />
      <el-table-column prop="summary" label="摘要" />
      <el-table-column prop="detailLink" label="详情链接" />
      <el-table-column label="发布状态">
        <template slot-scope="scope">
          <span v-if="scope.row.status === true">已发布</span>
          <span v-if="scope.row.status === false">待发布</span>
        </template>
      </el-table-column>
      <el-table-column label="语言类型">
        <template slot-scope="scope">
          <span v-if="scope.row.lang === 'zh_CN'">简体中文</span>
          <span v-if="scope.row.lang === 'zh_HK'">繁体中文</span>
          <span v-if="scope.row.lang === 'en_US'">English</span>
        </template>
      </el-table-column>
      <el-table-column label="图片预览" width="150px">
        <template slot-scope="scope">
          <img style="width:100%;height:120px;" :src="scope.row.pictUrl">
        </template>
      </el-table-column>
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <!--<el-button size="mini" type="success" @click="dialogEdit(scope.$index, scope.row)">编辑</el-button>-->
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

    <el-dialog title="Banner信息" :visible.sync="bannerDialog" width="620px">
      <el-form ref="bannerForm" :inline="true" :model="bannerForm" :rules="bannerRules" label-width="80px" class="bannerForm">
        <el-form-item label="标题" prop="title">
          <el-input v-model="bannerForm.title" />
        </el-form-item>
        <el-form-item label="摘要" prop="summary">
          <el-input v-model="bannerForm.summary" />
        </el-form-item>
        <el-form-item label="图片URL" prop="pictUrl">
          <el-input v-model="bannerForm.pictUrl" style="width:485px;" readonly />
        </el-form-item>
        <el-form-item style="display:block;" label="上传图片">
          <el-upload
            ref="upload"
            class="upload-demo"
            action="uploadUrl"
            :data="uploadParams"
            :file-list="fileList"
            :http-request="doUpload"
            :on-change="doChange"
            list-type="picture"
            style="width:485px;"
            :limit="1"
            :auto-upload="false"
          >
            <el-button slot="trigger" size="small" type="primary">选取图片</el-button>
            <el-button style="margin-left: 10px;" size="small" type="success" @click="doBegin()">上传到服务器</el-button>
          </el-upload>
        </el-form-item>
        <el-form-item label="发布状态" prop="status">
          <el-select v-model="bannerForm.status" placeholder="请选择发布状态" value="">
            <el-option :key="false" label="待发布" :value="false" />
            <el-option :key="true" label="已发布" :value="true" />
          </el-select>
        </el-form-item>
        <el-form-item label="语言类型" prop="lang">
          <el-select v-model="bannerForm.lang" placeholder="请选择语言类型" value="">
            <el-option key="zh_CN" label="简体中文" value="zh_CN" />
            <el-option key="zh_HK" label="繁体中文" value="zh_HK" />
            <el-option key="en_US" label="English" value="en_US" />
          </el-select>
        </el-form-item>
        <el-form-item label="详情链接" prop="detailLink">
          <el-input v-model="bannerForm.detailLink" />
        </el-form-item>
        <el-form-item label="排序号" prop="sortNum">
          <el-input v-model="bannerForm.sortNum" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button style="margin-left:80px;" type="primary" @click="doSubmit('bannerForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'Banner',
  data() {
    return {
      fileList: [],
      pictUrl: '',
      uploadUrl: '',
      uploadParams: {
        'key': '',
        'name': '',
        'acl': '',
        'callback': '',
        'policy': '',
        'AWSAccessKeyId': 'AKIAW3DUHMSESBXIK3FT',
        'success_action_status': '200',
        'signature': ''
      },
      bannerLoading: '',
      bannerDialog: false,
      bannerData: [],
      bannerForm: {
        'id': '',
        'title': '',
        'summary': '',
        'status': '',
        'lang': '',
        'detailLink': '',
        'pictUrl': '',
        'sortNum': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      bannerRules: {
        title: [
          { required: true, message: '请输入Banner标题', trigger: 'blur' }
        ],
        summary: [
          { required: true, message: '请输入Banner摘要', trigger: 'blur' }
        ],
        pictUrl: [
          { required: true, message: '请上传Banner图片', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择发布状态', trigger: 'change' }
        ],
        lang: [
          { required: true, message: '请选择语言类型', trigger: 'change' }
        ],
        detailLink: [
          { required: true, message: '请输入详情链接', trigger: 'blur' }
        ],
        sortNum: [
          { required: true, message: '请输入排序号', trigger: 'blur' }
        ]
      }
    };
  },
  mounted: function() {
    this.doSearch();
  },
  methods: {
    dialogAdd: function() {
      this.bannerForm = {
        'id': '',
        'title': '',
        'summary': '',
        'status': '',
        'lang': '',
        'detailLink': '',
        'pictUrl': '',
        'sortNum': ''
      };
      if (this.$refs.bannerForm) {
        this.$refs.bannerForm.resetFields();
      }
      if (this.$refs.upload) {
        this.$refs.upload.clearFiles();
      }
      this.bannerDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.bannerForm) {
        this.$refs.bannerForm.resetFields();
      }
      this.$http({
        url: '/common/banner/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.bannerForm = {
            'id': res.object.id,
            'title': res.object.title,
            'summary': res.object.summary,
            'status': res.object.status,
            'lang': res.object.lang,
            'detailLink': res.object.detailLink,
            'pictUrl': res.object.pictUrl,
            'sortNum': res.object.sortNum
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      if (this.$refs.upload) {
        this.$refs.upload.clearFiles();
      }
      this.bannerDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.bannerLoading = true;
      this.$http({
        url: '/common/banner/data',
        method: 'post',
        params: this.pageParams
      }).then(res => {
        if (res.code === 200) {
          this.bannerData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.bannerLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/common/banner/save',
            method: 'post',
            params: this.bannerForm
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
          this.bannerDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该条Banner么, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/common/banner/del',
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
    doChange: function(file, fileList) {
      this.fileList = fileList;
      this.uploadParams.key = file.name;
      this.uploadParams.name = file.name;
      this.$http({
        url: '/common/banner/generateUploadObjectPresignedUrl',
        method: 'get',
        params: {
          // dir: 'BossFile/Banner',
          fileName: file.name
        }
      }).then(res => {
        if (res.code === 200) {
          this.uploadUrl = res.object.presignedUrl;
          this.pictUrl = res.object.url;
          this.bannerForm.pictUrl = '';
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doBegin() {
      if (this.fileList.length === 0) {
        this.$message.error('请选取一张图片');
      } else {
        this.$refs.upload.submit();
      }
    },
    doUpload(data) {
      // if (this.uploadParams.name === '') {
      //   this.$message.error('请选取一张图片');
      // } else {
      //   this.$http({
      //     url: '/common/upload/policy',
      //     method: 'get'
      //   }).then(res => {
      //     if (res.code === 200) {
      //       this.uploadParams.policy = res.object.policy;
      //       this.uploadParams.signature = res.object.signature;
      //       this.uploadParams.acl = res.object.acl;
      //       this.$refs.upload.submit();
      //     }
      //   }).catch(error => {
      //     this.$message.error(error);
      //   });
      // }
      this.fileList[0].percentage = 50;
      this.fileList[0].status = 'uploading';
      this.$http({
        url: this.uploadUrl,
        method: 'put',
        data: data.file
      }).then(() => {
        this.fileList[0].percentage = 100;
        this.fileList[0].status = 'success';
        this.bannerForm.pictUrl = this.pictUrl;
        this.$refs.bannerForm.validateField('pictUrl');
      }).catch(error => {
        this.$message.error(error);
      });
    }
  }
};
</script>

<style lang="scss">
  .bannerForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
