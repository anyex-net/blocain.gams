<template>
  <div id="app-operationbox">

    <!-- 搜索 区域 -->
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="类型">
        <el-select v-model="searchForm.type" clearable placeholder="类型">
          <el-option label="全部" :value="''"></el-option>
          <el-option v-for="(item,index) in searchForm.typeList" :key="index" :label="item.dictDest" :value="item.dictValue"></el-option>
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
      <el-form-item label="来源">
        <el-input v-model="searchForm.source" clearable placeholder="请输入"></el-input>
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
      <el-form-item label="置顶">
        <el-select v-model="searchForm.isTop" clearable placeholder="置顶">
          <el-option label="全部" :value="''"></el-option>
          <el-option label="是" :value="1"></el-option>
          <el-option label="否" :value="0"></el-option>
        </el-select>
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
          <!-- <el-checkbox v-model="checkAllStatus" name="type" @change="checkAllChange">全选</el-checkbox> -->
          <el-form-item style="float: right;">
            <!-- <el-button type="danger" icon="el-icon-delete" >删除</el-button> -->
            <el-button type="success" icon="el-icon-circle-plus" @click="addBtn()">添加</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div>
        <el-form :inline="true" size="mini">
          <el-table v-loading="expressLoading" :data="expressData" style="width:100%;margin-bottom:20px;" border row-key="id">
            <el-table-column label="序号" width="50">
              <template slot-scope="scope">
                <!-- <el-form-item style="margin-bottom: 0px;">
                  <el-checkbox-group v-model="checkRowList" @change="checkedCitiesChange">
                    <el-checkbox :label="scope.row.id" name="type">{{ scope.$index+1 }}</el-checkbox>
                  </el-checkbox-group>
                </el-form-item> -->
                {{ scope.$index+1 }}
              </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" show-overflow-tooltip />
            <el-table-column prop="type" :formatter="typeFormat" label="类型" show-overflow-tooltip />
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
                <span v-if="scope.row.lang === ''">全部</span>
                <span v-if="scope.row.lang === 'en_US'">English</span>
              </template>
            </el-table-column>
            <el-table-column prop="isTop" label="置顶">
              <template slot-scope="scope">
                <span v-if="scope.row.isTop === 1">是</span>
                <span v-if="scope.row.isTop === 0">否</span>
              </template>
            </el-table-column>
            <el-table-column prop="source" label="来源" show-overflow-tooltip />
            <el-table-column prop="readingQuantity" label="阅读量" show-overflow-tooltip />
            <el-table-column prop="createDate" width="160" :formatter="dateFormat" label="发布时间" />
            <el-table-column prop="updateDate" width="160" :formatter="dateFormat" label="更新时间" />
            <el-table-column prop="createUser.userName" label="发布人" />
            <el-table-column label="操作" width="220">
              <template slot-scope="scope">
                <el-button v-if="scope.row.status === 0 || scope.row.status === 2" size="mini" type="success" @click="issueOrSoldOutBtn(scope.row.id, 1)">发布</el-button>
                <el-button v-if="scope.row.status === 1" size="mini" type="danger" @click="issueOrSoldOutBtn(scope.row.id, 2)">下架</el-button>
                <el-button size="mini" @click="compileBtn(scope.$index, scope.row)">编辑</el-button>
                <el-button size="mini" @click="previewOpen(scope.$index, scope.row)">预览</el-button>
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

    <!-- 预览 模态框 -->
    <el-dialog :visible.sync="previewDialog" width="500px" class="modalbox-preview">
      <ul class="clearfix">
        <li>标题</li>
        <li>{{ previewData.title }}</li>
      </ul>
      <ul class="clearfix">
        <li>来源</li>
        <li>{{ previewData.source }}</li>
      </ul>
      <ul class="clearfix">
        <li>时间</li>
        <li>{{ previewData.createDate }}</li>
      </ul>
      <ul class="clearfix content">
        <li>内容</li>
        <li>
          <div v-html="previewData.content">
          </div>
        </li>
      </ul>
      <div class="clearfix">
        <div class="quitBtn">
          <el-button size="mini" type="info" @click="previewClose()">退出</el-button>
        </div>
      </div>
    </el-dialog>

    <!-- 编辑-添加-模态框 -->
    <el-dialog :visible.sync="compileDialog" width="500px" class="modalbox-compileOrAdd">
      <el-form ref="modalboxCompileOrAdd" :rules="rules" :model="modalboxCompileOrAdd" :inline="true" size="mini">
        <ul class="clearfix">
          <li>标题</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="title">
              <el-input v-model="modalboxCompileOrAdd.title" maxlength="120" placeholder="请输入"></el-input>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix">
          <li>类型</li>
          <li>
            <el-form-item class="el-modalbox-layout" style="width:100px" prop="type">
              <el-select v-model="modalboxCompileOrAdd.type" placeholder="请选择">
                <!-- <el-option label="全部" :value="''"></el-option> -->
                <el-option v-for="(item,index) in searchForm.typeList" :key="index" :label="item.dictDest" :value="item.dictValue"></el-option>
              </el-select>
            </el-form-item>
            <ul class="goHalves clearfix">
              <li>
                语言
              </li>
              <li style="width: 100px;">
                <el-form-item class="el-modalbox-layout" prop="lang">
                  <el-select v-model="modalboxCompileOrAdd.lang" placeholder="请选择">
                    <!-- <el-option key="all" label="全部" :value="''" /> -->
                    <el-option key="zh_CN" label="简体中文" value="zh_CN" />
                    <el-option key="en_US" label="English" value="en_US" />
                  </el-select>
                </el-form-item>
              </li>
            </ul>
          </li>
        </ul>
        <ul class="clearfix">
          <li>来源</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="source">
              <el-input v-model="modalboxCompileOrAdd.source" maxlength="30" placeholder="请输入"></el-input>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix">
          <li>摘要</li>
          <li>
            <el-form-item class="el-modalbox-layout" prop="summary">
              <el-input v-model="modalboxCompileOrAdd.summary" maxlength="120" type="textarea" :autosize="{ minRows: 1, maxRows: 4}" placeholder="请输入"></el-input>
            </el-form-item>
          </li>
        </ul>
        <ul class="clearfix" style="margin-bottom: 20px;">
          <li>内容</li>
          <li>
            <!-- <el-form-item  class="el-modalbox-layout">
              <el-input v-model="modalboxCompileOrAdd.content" placeholder="请输入"></el-input>
            </el-form-item> -->
            <div class="edit_container">
              <quill-editor
                ref="myQuillEditor"
                v-model="modalboxCompileOrAdd.content"
                :options="editorOption"
                @change="onEditorChange($event)"
              >
              </quill-editor>
            </div>
            <p v-if="modalboxCompileOrAdd.content === ''" style="margin-top:5px; color:#F56C6C">请输入</p>
          </li>
        </ul>
        <ul class="clearfix" style="margin-bottom: 20px;">
          <li>首页图片</li>
          <li>
            <el-upload
              ref="upload"
              class="upload-demo"
              action="uploadUrl"
              :data="uploadParams"
              :http-request="imgUpload"
              :on-change="doChange"
              :before-upload="beforeUpload"
              :file-list="fileList"
              list-type="picture"
              :multiple="false"
              :limit="1"
              :auto-upload="false"
            >
              <el-button slot="trigger" size="small" type="primary">选择图片</el-button>
              <span slot="tip" style="margin-left: 10px" class="el-upload__tip">只能上传jpg/png文件，且不超过1mb</span>
            </el-upload>
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
          <li>是否置顶</li>
          <li>
            <el-form-item prop="isTop">
              <el-select v-model="modalboxCompileOrAdd.isTop" placeholder="请选择">
                <el-option label="是" :value="1"></el-option>
                <el-option label="否" :value="0"></el-option>
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
  name: 'Express',
  data() {
    return {
      queryType: 'all', // all 全部列表  dim 模糊查询
      fileList: [
        // {
        //   name: 'food.jpeg',
        //   url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
        // }
      ],
      uploadUrl: '',
      pictUrl: '',
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
      richTextUpload: {
        cursorPosition: 0,
        compileDialogChild: false,
        fileList: [],
        uploadUrl: '',
        pictUrl: '',
        key: '',
        name: ''
      },
      expressLoading: false,
      previewDialog: false,
      compileDialog: false,

      searchForm: {
        type: '',
        typeList: [],
        status: '',
        lang: '',
        source: '',
        createDate: '',
        isTop: ''
      },
      checkAllStatus: false,
      checkRowList: [0],
      expressData: [ // 表格数据
        // {
        //   "id": "290733158217945088",
        //   "title": "今天我很牛逼",
        //   "type": "热门",
        //   "source": "黄承基21号",
        //   "summary": "卧槽牛逼啊",
        //   "pictUrl": "",
        //   "content": "我真的很难受,我真的很难受,遗忘的不舍得",
        //   "lang": "zh_CN",
        //   "status": 0,
        //   "isTop": 1,
        //   "readingQuantity": "0",
        //   "thumbsUp": "0",
        //   "createBy": "200000000000",
        //   "createDate": 1574060817475,
        //   "updateBy": "",
        //   "updateDate": 1574060817475,
        //   "createDateStart": "",
        //   "createDateEnd": "",
        //   "createUser": ""
        // }
      ],
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 1
      },
      previewData: {
        title: '标题',
        source: '来源',
        createDate: '时间',
        content: '内容'
      },
      modalboxCompileOrAdd: {
        id: '',
        title: '',
        type: '',
        lang: '',
        source: '',
        summary: '',
        content: '',
        pictUrl: '',
        status: '',
        isTop: 0
      },
      statusTS: '',
      rules: {
        title: [
          { required: true, message: '请输入', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        lang: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        source: [
          { required: false, message: '请输入', trigger: 'blur' }
        ],
        summary: [
          { required: true, message: '请输入', trigger: 'blur' }
        ],
        status: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
        isTop: [
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
  mounted: function() {
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
        url: '/common/express/richTextPicture',
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
    // 富文本图片上传区域代码  =>结束

    pagingDoSearch(page) {
      this.pageParams.page = page;
      this.doSearch();
    },
    doSearch: function() { // 获取bable列表 all-获取全部 dim-模糊查询
      this.expressLoading = true;
      const data = JSON.parse(JSON.stringify(this.pageParams));
      if (this.queryType === 'dim') {
        const searchFormTS = {
          type: this.searchForm.type,
          status: this.searchForm.status,
          lang: this.searchForm.lang,
          source: this.searchForm.source,
          createDateStart: '',
          createDateEnd: '',
          isTop: this.searchForm.isTop
        };
        searchFormTS.createDateStart = this.searchForm.createDate ? Number(this.searchForm.createDate[0]) : '';
        searchFormTS.createDateEnd = this.searchForm.createDate ? Number(this.searchForm.createDate[1]) : '';
        Object.assign(data, searchFormTS);
      }
      this.$http({
        url: '/common/express/data',
        method: 'post',
        params: data
      }).then(res => {
        if (res.code === 200) {
          this.expressData = JSON.parse(JSON.stringify(res.rows));
          this.pageParams.totalPage = res.totalPage;
          this.expressLoading = false;
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
          dictCode: 'expressType',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.searchForm.typeList = res.object;
          this.doSearch();
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },

    typeFormat(row, column) { // 类型过滤器
      let newDictDest = '';
      this.searchForm.typeList.forEach(element => {
        if (element.dictValue === row.type) {
          newDictDest = element.dictDest;
        }
      });
      return newDictDest;
    },
    dateFormat(row, column) { // 日期过滤器 >> 'YYYY-MM-DD HH:mm:ss'
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },

    issueOrSoldOutBtn(id, status) { // 发布or下架
      this.$http({
        url: '/common/express/updateExpressStatus',
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
        this.expressData.forEach(item => {
          listTS.push(item.id);
        });
      }
      this.checkRowList = val ? listTS : [];
    },
    checkedCitiesChange(value) { // 全选全不选 - item-Change
      this.checkAllStatus = value.length > 0 && value.length === this.expressData.length;
    },

    addBtn() { // 添加
      this.compileDialog = true;
      this.imageEventBinding();

      this.modalboxCompileOrAdd = {
        id: '',
        title: '',
        type: '',
        lang: '',
        source: '',
        summary: '',
        content: '',
        pictUrl: '',
        status: 0,
        isTop: 0
      };
      if (this.$refs['modalboxCompileOrAdd']) {
        this.$refs['modalboxCompileOrAdd'].resetFields();
      }
      this.fileList = [];
    },
    compileBtn(index, row) { // 编辑
      this.compileDialog = true;
      this.imageEventBinding();
      this.modalboxCompileOrAdd = {
        id: this.expressData[index].id,
        title: this.expressData[index].title,
        type: this.expressData[index].type,
        lang: this.expressData[index].lang,
        source: this.expressData[index].source,
        summary: this.expressData[index].summary,
        content: this.expressData[index].content,
        pictUrl: this.expressData[index].pictUrl,
        status: this.expressData[index].status,
        isTop: this.expressData[index].isTop
      };
      if (this.expressData[index].pictUrl) {
        this.fileList = [
          {
            name: this.expressData[index].pictUrl,
            url: this.expressData[index].pictUrl
          }
        ];
      } else {
        this.fileList = [];
      }
      if (this.$refs['modalboxCompileOrAdd']) {
        this.$refs['modalboxCompileOrAdd'].resetFields();
      }
    },
    onEditorChange(val) {
      val.quill.deleteText(1300, 4);
    },
    addOrCompileModalClose() { // 添加or编辑-模态框关闭
      this.compileDialog = false;
      this.uploadParams.name = '';
    },
    addOrCompileModalSave(formName, status = 1) { // 添加or编辑模态框 保存-发布
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if (!this.modalboxCompileOrAdd.content) {
            return false;
          } else {
            this.statusTS = status;
            if (this.fileList.length !== 0) {
              const fileName = this.modalboxCompileOrAdd.id + '-' + this.uploadParams.name;
              this.generateUploadObjectPresignedUrl(fileName).then(res => {
                const thirdParty = this.modalboxCompileOrAdd.pictUrl;
                this.modalboxCompileOrAdd.pictUrl = this.pictUrl;
                if (this.uploadParams.name) { // 判断是否是已上传的图片
                  this.$refs.upload.submit();
                } else {
                  this.modalboxCompileOrAdd.pictUrl = thirdParty;
                  this.addOrCompileModalInterface().then(() => {
                    this.$message.success('成功');
                  });
                }
              });
            } else {
              this.addOrCompileModalInterface().then(() => {
                this.$message.success('成功');
              });
            }
          }
        } else {
          return false;
        }
      });
    },
    addOrCompileModalInterface() { // 添加or编辑-java接口
      return new Promise((resolve, reject) => {
        this.modalboxCompileOrAdd.status = this.statusTS;
        const data = this.modalboxCompileOrAdd;
        this.$http({
          url: '/common/express/save',
          method: 'post',
          // params: this.modalboxCompileOrAdd
          data
        }).then(res => {
          if (res.code === 200) {
            this.addOrCompileModalClose();
            this.doSearch();
            resolve(res);
          } else {
            this.$message.error(res.message || res.code);
          }
        }).catch(err => {
          this.$message.error(err || 'error');
        });
      });
    },
    doChange(file, fileList) { // 文件状态更新
      this.fileList = fileList;
      this.uploadParams.key = file.name;
      this.uploadParams.name = file.name;
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
          url: '/common/express/generateUploadObjectPresignedUrl',
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
    imgUpload(data) { // 图片上传
      this.fileList[0].percentage = 50;
      this.fileList[0].status = 'uploading';
      this.$http({
        url: this.uploadUrl,
        method: 'put',
        data: data.file
      }).then(() => {
        this.addOrCompileModalInterface().then(() => {
          this.fileList[0].percentage = 100;
          this.fileList[0].status = 'success';
          this.$refs.modalboxCompileOrAdd.validateField('pictUrl');
          this.$message.success('成功');
        });
      }).catch(error => {
        this.$message.error('上传图片失败' || error);
      });
    },

    previewOpen(index) { // 预览-打开
      this.previewDialog = true;
      this.previewData = this.expressData[index];
      this.previewData.createDate = this.$moment(this.previewData.createDate).format('YYYY-MM-DD HH:mm:ss');
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
</style>

<style lang="scss">
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
