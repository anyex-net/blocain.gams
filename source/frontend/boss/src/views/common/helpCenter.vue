<template>
  <div id="app-operationbox">

    <!-- 搜索 区域 -->
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="类型">
        <el-select v-model="searchForm.problemType" clearable placeholder="类型">
          <el-option label="全部" :value="''"></el-option>
          <el-option v-for="(item,index) in searchForm.problemTypeList" :key="index" :label="item.dictDest" :value="item.dictValue"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布状态">
        <el-select v-model="searchForm.status" clearable placeholder="发布状态">
          <el-option label="全部" :value="''"></el-option>
          <el-option label="待发布" :value="0"></el-option>
          <el-option label="已发布" :value="1"></el-option>
          <el-option label="已下架" :value="2"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="中/英文">
        <el-select v-model="searchForm.lang" clearable placeholder="请选择语言类型">
          <el-option key="all" label="全部" :value="''" />
          <el-option key="zh_CN" label="简体中文" value="zh_CN" />
          <el-option key="en_US" label="English" value="en_US" />
        </el-select>
      </el-form-item>
      <br>
      <el-form-item label="发布时间">
        <el-date-picker
          v-model="searchForm.createDate"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch(queryType = 'dim')">查询</el-button>
        <!-- <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button> -->
      </el-form-item>
    </el-form>

    <!-- 表格 区域 -->
    <div class="bottom-table">
      <div>
        <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini" style="width: 70%;">
          <el-checkbox v-model="checkAllStatus" name="type" @change="checkAllChange">全选</el-checkbox>
          <el-form-item style="float: right;">
            <el-button type="danger" icon="el-icon-delete" @click="removeData(checkRowList, true)">删除</el-button>
            <el-button type="success" icon="el-icon-circle-plus" @click="addBtn()">添加</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <el-form :inline="true" size="mini">
          <el-table v-loading="helpCenterLoading" :data="helpCenterData" style="width:100%;margin-bottom:20px;" border row-key="id">
            <el-table-column label="序号" width="90">
              <template slot-scope="scope">
                <el-form-item style="margin-bottom: 0px;">
                  <el-checkbox-group v-model="checkRowList" @change="checkedCitiesChange">
                    <el-checkbox :label="scope.row.id" name="type">{{ scope.$index+1 }}</el-checkbox>
                  </el-checkbox-group>
                </el-form-item>
              </template>
            </el-table-column>
            <el-table-column prop="problemLevelOne" :formatter="problemLevelOneFormat" label="问题(一级)" show-overflow-tooltip />
            <el-table-column prop="problemLevelTwo" label="问题(二级)" show-overflow-tooltip />
            <el-table-column prop="problemType" :formatter="problemTypeFormat" label="类型" show-overflow-tooltip />
            <el-table-column prop="status" label="发布状态">
              <template slot-scope="scope">
                <span v-if="scope.row.status === 0">待发布</span>
                <span v-if="scope.row.status === 1">已发布</span>
                <span v-if="scope.row.status === 2">已下架</span>
              </template>
            </el-table-column>
            <el-table-column prop="lang" label="中文/英文">
              <template slot-scope="scope">
                <span v-if="scope.row.lang === 'zh_CN'">简体中文</span>
                <span v-if="scope.row.lang === '+'">全部</span>
                <span v-if="scope.row.lang === 'en_US'">English</span>
              </template>
            </el-table-column>
            <el-table-column prop="createUser.userName" label="发布人" />
            <el-table-column prop="createDate" width="160" :formatter="dateFormat" label="发布时间" />
            <el-table-column prop="updateDate" width="160" :formatter="dateFormat" label="更新时间" />

            <el-table-column label="操作" width="220">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 0 || scope.row.status === 2" size="mini" type="success" @click="issueOrSoldOutBtn(scope.row.id, 1)">发布</el-button>
                <el-button v-if="scope.row.status === 1" size="mini" type="danger" @click="issueOrSoldOutBtn(scope.row.id, 2)">下架</el-button>
                <el-button size="mini" @click="compileBtn(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" @click="removeData(scope.row.id, false)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-form>
      </div>
    </div>

    <!-- 分页 -->
    <el-pagination
      style="text-align:center;"
      background
      layout="prev, pager, next"
      :hide-on-single-page="false"
      :page-size="pageParams.rows"
      :page-count="pageParams.totalPage"
      :current-page="pageParams.page"
      class="pagination"
      @current-change="pagingDoSearch"
    >
    </el-pagination>

    <!-- 编辑-添加-模态框 -->
    <el-dialog :visible.sync="compileDialog" width="500px" class="modalbox-compileOrAdd">
      <el-form ref="modalboxCompileOrAdd" class="modalbox-content-helpCenter" :rules="rules" :model="modalboxCompileOrAdd" :inline="true" size="mini">
        <ul class="clearfix">
          <li>语言</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="lang">
              <el-select v-model="modalboxCompileOrAdd.lang" placeholder="请选择">
                <!-- <el-option key="all" label="全部" :value="''" /> -->
                <el-option key="zh_CN" label="简体中文" value="zh_CN" />
                <el-option key="en_US" label="English" value="en_US" />
              </el-select>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix">
          <li>问题类型</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="problemType">
              <el-select v-model="modalboxCompileOrAdd.problemType" placeholder="请选择" @change="issueTypeChange">
                <!-- <el-option label="全部" :value="''"></el-option> -->
                <el-option v-for="(item,index) in searchForm.problemTypeList" :key="index" :label="item.dictDest" :value="item.dictValue"></el-option>
              </el-select>
              <el-button :disabled="true" size="mini" style="margin-left: 20px;">新增</el-button>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix">
          <li>问题(一级)</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="problemLevelOne">
              <el-select v-model="modalboxCompileOrAdd.problemLevelOne" placeholder="请选择">
                <!-- <el-option label="全部" :value="''"></el-option> -->
                <el-option v-for="(item,index) in searchForm.problemLevelOneList" :key="index" :label="item.dictDest" :value="item.dictValue"></el-option>
              </el-select>
              <el-button :disabled="true" size="mini" style="margin-left: 20px;">新增</el-button>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix">
          <li>问题(二级)</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="problemLevelTwo">
              <el-input v-model="modalboxCompileOrAdd.problemLevelTwo" maxlength="120" placeholder="请输入"></el-input>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix" style="margin-bottom: 20px;">
          <li>内容问答</li>
          <li>
            <!-- <el-form-item  class="el-modalbox-layout">
              <el-input v-model="modalboxCompileOrAdd.content" placeholder="请输入"></el-input>
            </el-form-item> -->
            <div class="edit_container">
              <quill-editor
                ref="myQuillEditor"
                v-model="modalboxCompileOrAdd.problemSolving"
                :options="editorOption"
                valuechanged
                @change="onEditorChange($event)"
              >
              </quill-editor>
            </div>
            <p v-if="modalboxCompileOrAdd.problemSolving === ''" style="margin-top:5px; color:#F56C6C">请输入</p>
          </li>
        </ul>
        <ul class="clearfix">
          <li>发布状态</li>
          <li>
            <el-form-item prop="status">
              <el-select v-model="modalboxCompileOrAdd.status" :disabled="true" placeholder="请选择">
                <!-- <el-option label="全部" :value="''"></el-option> -->
                <el-option label="待发布" :value="0"></el-option>
                <el-option label="已发布" :value="1"></el-option>
                <el-option label="已下架" :value="2"></el-option>
              </el-select>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix">
          <li>设为热门</li>
          <li>
            <el-form-item prop="isHot">
              <el-select v-model="modalboxCompileOrAdd.isHot" placeholder="请选择">
                <el-option label="是" :value="true"></el-option>
                <el-option label="否" :value="false"></el-option>
              </el-select>
            </el-form-item>
          </li>
        </ul>
        <div class="clearfix">
          <div class="btn">
            <el-button size="mini" type="success" @click="addOrCompileModalSave('modalboxCompileOrAdd', modalboxCompileOrAdd.status)">保存</el-button>
            <el-button v-if="modalboxCompileOrAdd.status!==1" size="mini" type="success" @click="addOrCompileModalSave('modalboxCompileOrAdd', 1)">发布</el-button>
            <el-button size="mini" type="info" @click="addOrCompileModalClose()">退出</el-button>
          </div>
        </div>
      </el-form>
      <el-dialog
        width="30%"
        :visible.sync="richTextUpload.compileDialogChild"
        append-to-body
        @close="handleClose"
      >
        <el-upload
          ref="richTextUpload"
          class="avatar-uploader richText-avatar-uploader"
          drag
          action=""
          :http-request="richTextImgUpload"
          :on-change="richTextDoChange"
          :before-upload="beforeUpload"
          :file-list="richTextUpload.fileList"
          list-type="picture"
          :multiple="false"
          :limit="1"
          :auto-upload="false"
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
        </el-upload>
        <div class="cancelBtn">
          <button @click="richTextUpload.compileDialogChild = false">取消</button>
          <button @click="richTextConfirmClick()">确定</button>
        </div>
      </el-dialog>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: 'HelpCenter',
  data() {
    return {
      queryType: 'all', // all 全部列表  dim 模糊查询
      helpCenterLoading: false,
      compileDialog: false,
      searchForm: {
        problemType: '',
        problemTypeList: [],
        status: '',
        lang: '',
        createDate: '',
        problemLevelOneList: []
      },
      checkAllStatus: false,
      checkRowList: [],
      helpCenterData: [ // 表格数据
        // {
        // "id": "293574842278612992",
        // "problemLevelOne": "问题（一级）",
        // "problemLevelTwo": "问题（二级）",
        // "problemType": "问题类型",
        // "problemSolving": "问题解答",
        // "pictUrl": "",
        // "status": 1,
        // "lang": "en_US",
        // "isHot": true,
        // "createBy": "200000000000",
        // "createDate": 1574738327755,
        // "updateBy": "200000000000",
        // "updateDate": 1574738327755,
        // "createDateStart": "",
        // "createDateEnd": ""
        // }
      ],
      richTextUpload: {
        cursorPosition: 0,
        compileDialogChild: false,
        fileList: [],
        uploadUrl: '',
        pictUrl: '',
        key: '',
        name: ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 1
      },
      modalboxCompileOrAdd: {
        id: '',
        lang: '',
        problemType: '',
        problemLevelOne: '',
        problemLevelTwo: '',
        problemSolving: '',
        status: 1,
        isHot: true
      },
      status: '',
      rules: {
        lang: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        problemType: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        problemLevelOne: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        problemLevelTwo: [
          { required: true, message: '请输入', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        isHot: [
          { required: true, message: '请选择', trigger: 'change' }
        ]
      },
      editorOption: { // 富文本配置
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'link'], // toggled buttons
            [{ header: 2 }], // 值字体大小
            [{ color: [] }],
            ['blockquote', 'image']
          ]
        }
      }
    };
  },
  created() {
  },
  mounted() {
    this.typeDictionary();
  },
  methods: {
    // 富文本图片上传区域代码  =>开始
    imageEventBinding() {
      this.$nextTick(function() {
        this.$refs.myQuillEditor.quill.getModule('toolbar').addHandler('image', this.imgHandler);
      });
    },
    richTextDoChange(file, fileList) {
      this.richTextUpload.key = file.name;
      this.richTextUpload.name = file.name;
      this.richTextUpload.fileList = fileList;
    },
    imgHandler() {
      this.richTextUpload.compileDialogChild = true;
      this.richTextUpload.cursorPosition = this.$refs.myQuillEditor.quill.getSelection().index;
    },
    richTextConfirmClick() {
      this.$refs.richTextUpload.submit();
    },
    richTextImgUpload(data) {
      this.richTextUpload.fileList[0].percentage = 20;
      this.richTextUpload.fileList[0].status = 'uploading';
      const fileName = this.modalboxCompileOrAdd.id + '-richText-' + this.richTextUpload.name;
      this.generateUploadObjectPresignedUrl(fileName).then(res => {
        this.richTextUpload.fileList[0].percentage = 50;
        this.$http({
          url: res.object.presignedUrl,
          method: 'put',
          data: data.file
        }).then(() => {
          this.richTextUpload.fileList[0].percentage = 80;
          this.getrichTextImgUrl(res.object.url);
        }).catch(() => {
          this.$message.error('上传图片失败');
        });
      });
    },
    getrichTextImgUrl(url) { // 获取富文本图片路径
      this.$http({
        url: '/common/helpCenter/richTextPicture',
        method: 'post',
        params: { path: url }
      }).then(res => {
        if (res.code === 200) {
          this.richTextUpload.fileList[0].percentage = 100;
          this.richTextUpload.fileList[0].status = 'success';
          this.$refs.myQuillEditor.quill.insertEmbed(this.richTextUpload.cursorPosition, 'image', res.object);
          this.richTextUpload.compileDialogChild = false;
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    handleClose() { // 富文本模态框关闭回调
      this.richTextUpload.cursorPosition = 0;
      this.richTextUpload.fileList = [];
      // return false;
    },
    beforeUpload(file, fileList) { // 文件上传之前
      const testmsg = file.name.substring(file.name.lastIndexOf('.') + 1);
      const isLt2M = file.size / 1024 / 1024 < 1;
      let status = true;
      if (testmsg !== 'jpg' && testmsg !== 'png') {
        status = false;
        this.$message({
          message: '上传文件只能是 jpg、png格式!',
          type: 'warning'
        });
      }
      if (!isLt2M) {
        status = false;
        this.$message({
          message: '上传文件大小不能超过 1MB!',
          type: 'warning'
        });
      }
      return status;
    },
    generateUploadObjectPresignedUrl(fileName) { // 获取aws路径
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/common/helpCenter/generateUploadObjectPresignedUrl',
          method: 'get',
          params: { fileName }
        }).then(res => {
          if (res.code === 200) {
            this.uploadUrl = res.object.presignedUrl;
            this.pictUrl = res.object.url;
            resolve(res);
          } else {
            this.$message.error(res.message || res.code);
          }
        }).catch(err => {
          this.$message.error(err || 'error');
        });
      });
    },
    // 富文本图片上传区域代码  =>结束

    pagingDoSearch(page) {
      this.pageParams.page = page;
      this.doSearch();
    },
    doSearch: function() {
      this.helpCenterLoading = true;
      const data = JSON.parse(JSON.stringify(this.pageParams));
      if (this.queryType === 'dim') {
        const searchFormTS = {
          problemType: this.searchForm.problemType,
          status: this.searchForm.status,
          lang: this.searchForm.lang,
          source: this.searchForm.source,
          createDateStart: '',
          createDateEnd: ''
        };
        searchFormTS.createDateStart = this.searchForm.createDate ? Number(this.searchForm.createDate[0]) : '';
        searchFormTS.createDateEnd = this.searchForm.createDate ? Number(this.searchForm.createDate[1]) : '';
        Object.assign(data, searchFormTS);
      }

      this.$http({
        url: '/common/helpCenter/data',
        method: 'post',
        params: data
      }).then(res => {
        if (res.code === 200) {
          this.helpCenterData = JSON.parse(JSON.stringify(res.rows));
          this.pageParams.totalPage = res.totalPage;
          this.helpCenterLoading = false;

          this.checkAllStatus = false; // 数据初始化
          this.checkRowList = [];
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },

    typeDictionary() { // 类型字典表
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'helpCenterType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.searchForm.problemTypeList = res.object;
          this.problemLevelOne();
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },

    problemLevelOne() { // 问题(一)字典表
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'problemLevelOne',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.searchForm.problemLevelOneList = res.object;
          this.doSearch();
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },

    issueTypeChange(val = this.modalboxCompileOrAdd.problemType, old = '') { // 根据类型获取问题一字典表
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: val,
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.searchForm.problemLevelOneList = res.object;
          if (old !== val) {
            this.modalboxCompileOrAdd.problemLevelOne = '';
          }
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },

    problemLevelOneFormat(row, column) { // 问题(一级)过滤器
      return row.problemLevelOneInfo.dictDest;
    },
    problemTypeFormat(row, column) { // 问题类型过滤器
      let newDictDest = '';
      this.searchForm.problemTypeList.forEach(element => {
        if (element.dictValue === row.problemType) {
          newDictDest = element.dictDest;
        }
      });
      return newDictDest;
    },
    dateFormat(row, column) { // 日期过滤器 时间戳 >> 'YYYY-MM-DD HH:mm:ss'
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },

    issueOrSoldOutBtn(id, status) { // 发布or下架
      this.$http({
        url: '/common/helpCenter/updateHelpCenterStatus',
        method: 'post',
        params: { id, status }
      }).then(res => {
        if (res.code === 200) {
          this.doSearch();
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(err => {
        this.$message.error(err || 'error');
      });
    },
    checkAllChange(val) { // 全选全不选 - all-Change
      const listTS = [];
      if (val) {
        this.helpCenterData.forEach(item => {
          listTS.push(item.id);
        });
      }
      this.checkRowList = val ? listTS : [];
    },
    checkedCitiesChange(value) { // 全选全不选 - item-Change
      this.checkAllStatus = value.length > 0 && value.length === this.helpCenterData.length;
    },

    addBtn() { // 添加
      this.compileDialog = true;
      this.imageEventBinding();
      this.modalboxCompileOrAdd = {
        id: '',
        lang: '',
        problemType: '',
        problemLevelOne: '',
        problemLevelTwo: '',
        problemSolving: '',
        status: 0,
        isHot: false
      };
      if (this.$refs['modalboxCompileOrAdd']) {
        this.$refs['modalboxCompileOrAdd'].resetFields();
      }
    },
    compileBtn(index, row) { // 编辑
      this.compileDialog = true;
      this.imageEventBinding();
      this.modalboxCompileOrAdd = {
        id: this.helpCenterData[index].id,
        lang: this.helpCenterData[index].lang,
        problemType: this.helpCenterData[index].problemType,
        problemLevelOne: this.helpCenterData[index].problemLevelOne,
        problemLevelTwo: this.helpCenterData[index].problemLevelTwo,
        problemSolving: this.helpCenterData[index].problemSolving,
        status: this.helpCenterData[index].status,
        isHot: this.helpCenterData[index].isHot
      };

      if (this.$refs['modalboxCompileOrAdd']) {
        this.$refs['modalboxCompileOrAdd'].resetFields();
      }
      this.issueTypeChange(undefined, this.helpCenterData[index].problemType);
    },
    onEditorChange(val) {
      val.quill.deleteText(1300, 4);
    },
    addOrCompileModalClose() { // 添加or编辑-模态框关闭
      this.compileDialog = false;
    },
    addOrCompileModalSave(formName, status = 1) { // 添加or编辑-模态框保存-发布
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.modalboxCompileOrAdd.problemSolving) {
            return false;
          } else {
            this.statusTS = status;
            this.addOrCompileModalInterface();
          }
        } else {
          return false;
        }
      });
    },
    addOrCompileModalInterface() { // 添加or编辑-java接口
      this.modalboxCompileOrAdd.status = this.statusTS;
      const data = this.modalboxCompileOrAdd;
      this.$http({
        url: '/common/helpCenter/save',
        method: 'post',
        // params: this.modalboxCompileOrAdd
        data
      }).then(res => {
        if (res.code === 200) {
          this.compileDialog = false;
          this.doSearch();
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(err => {
        this.$message.error(err || 'error');
      });
    },

    removeData(ids, canBatch = false) { // 表格row删除  data所需要的ids canBatch=>能否批量删除
      if (canBatch) {
        if (ids.length === 0) {
          this.$message(
            {
              type: 'info',
              message: '请选择需要删除的资源'
            }
          );
          return;
        }
      }
      // debugger;
      this.$confirm('确定删除该资源,是否继续', '提示', {
        confirmButtonText: '确定',
        concelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let newids = '';
        if (canBatch) {
          newids = ids + '';
        } else {
          newids = ids;
        }
        this.$http({
          url: '/common/helpCenter/del',
          method: 'post',
          params: { ids: newids }
        }).then(res => {
          if (res.code === 200) {
            this.doSearch();
          } else {
            this.$message.error(res.message || res.code);
          }
        }).catch(err => {
          this.$message.error(err || 'error');
        });
      }).catch(() => {
        this.$message(
          {
            type: 'info',
            message: '已取消删除'
          }
        );
      });
    },

    previewClose() { // 预览-关闭
      this.previewDialog = false;
    }
  }
};
</script>

<style lang="scss" scoped>
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
  .richText-avatar-uploader {
    padding: 0 40px;
    // .el-upload {
    //   display: block;
    //   .el-upload-dragger {
    //     margin: 0 auto;
    //   }
    // }
    .el-upload {
      width: 100%;
      .el-upload-dragger {
        width: 100%;
      }
    }
  }
  .cancelBtn {
    overflow: hidden;
    margin-top: 20px;
    button {
      float: right;
      margin-left: 10px;
    }
  }
</style>
