<template>
  <div id="notices">
    <!-- 搜索表单 -->
    <el-form ref="formSearch" :inline="true" :model="formSearch" class="formSearch">
      <el-row>
        <el-col :span="6">
          <el-form-item label="类型">
            <el-select v-model="formSearch.type" placeholder="类型" clearable>
              <dictionary dict-code="newsAnnounceType" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="发布状态">
            <el-select v-model="formSearch.status" placeholder="状态" clearable>
              <el-option label="已发布" value="1"></el-option>
              <el-option label="待发布" value="0"></el-option>
              <el-option label="已下架" value="2"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="中/英文">
            <el-select v-model="formSearch.lang" placeholder="选择语言" clearable>
              <el-option label="中文" value="zh_CN"></el-option>
              <el-option label="英文" value="en_US"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item class="formSearchBtn">
            <el-button type="primary" icon="el-icon-search" @click="getNoticesTable()">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="发布位置">
            <el-select v-model="formSearch.clientType" placeholder="请选择发布位置" clearable>
              <dictionary dict-code="clientType" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="发布时间">
            <el-date-picker
              v-model="formSearch.cron"
              type="daterange"
              format="yyyy-MM-dd"
              value-format="timestamp"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="6" style="height:5px;">
        </el-col>
      </el-row>
    </el-form>
    <!-- 操作按钮 -->
    <el-row class="tableBtn">
      <el-button type="danger" @click="delectNotice()">删除</el-button>
      <el-button type="success" plain class="el-icon-circle-plus" @click="addNotice()">添加</el-button>
    </el-row>
    <!-- 消息公告信息表格 -->
    <el-table ref="noticesTable" :data="noticesTable" border fit class="noticesTable" highlight-current-row @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
      <el-table-column prop="title" label="标题" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="type" label="类型">
        <template slot-scope="scope">
          <span>{{ scope.row.type| typeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="status" label="发布状态">
        <template slot-scope="scope">
          <span>{{ scope.row.status| typeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="clientType" label="发布位置">
        <template slot-scope="scope">
          <span>{{ scope.row.clientType| typeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="lang" label="中文/英文">
        <template slot-scope="scope">
          <span>{{ scope.row.lang| typeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="releaseDate" label="发布时间" :formatter="dateFormat">
      </el-table-column>
      <el-table-column prop="publisher.userName" label="发布人"></el-table-column>
      <el-table-column label="操作" width="220px">
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.status===1"
            size="mini"
            type="danger"
            @click="unShelve(scope.$index, scope.row)"
          >下架</el-button>
          <el-button
            v-if="scope.row.status===0"
            size="mini"
            type="success"
            plain
            @click="putaway(scope.$index, scope.row)"
          >发布</el-button>
          <el-button v-if="scope.row.status!==1" size="mini" type="primary" @click="editNotice(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" @click="previewNotice(scope.$index, scope.row)">预览</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-row class="paginationDiv">
      <span>第 {{ num }} 至 {{ numLast }} 项，共{{ pageSize }}项</span>
      <el-pagination
        :current-page="pageParams.page"
        layout="total, prev, pager, next, jumper"
        :total="total"
        class="pagination"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </el-row>
    <!-- 新增编辑弹框 -->
    <el-dialog :title="noticeTitle" :visible.sync="editDialog" width="50%">
      <el-form ref="editNoticeForm" :model="editNoticeForm" class="editDialog" :rules="editNoticeFormRules">
        <el-row>
          <el-col :span="12">
            <el-form-item label="类型" prop="type">
              <el-select v-model="editNoticeForm.type" placeholder="类型" clearable>
                <dictionary dict-code="newsAnnounceType" />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="语言" prop="lang">
              <el-select v-model="editNoticeForm.lang" placeholder="选择语言" clearable>
                <el-option label="中文" value="zh_CN"></el-option>
                <el-option label="英文" value="en_US"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="标题" prop="title">
          <el-input v-model="editNoticeForm.title"></el-input>
        </el-form-item>
        <el-form-item label="内容" class="content" prop="content">
          <el-upload
            ref="upload"
            class="avatar-uploader"
            :name="fileName"
            :show-file-list="false"
            :before-upload="beforeUpload"
            style="height:0"
          >
          </el-upload>
          <quill-editor
            ref="myQuillEditor"
            v-model="editNoticeForm.content"
            :options="editorOption"
            @blur="onEditorBlur($event)"
            @focus="onEditorFocus($event)"
            @change="onEditorChange($event)"
          >
          </quill-editor>
        </el-form-item>
        <el-row>
          <el-col :span="12">
            <el-form-item label="发布状态" prop="status">
              <el-select v-model="editNoticeForm.status" placeholder="状态" clearable disabled>
                <el-option label="已发布" value="1"></el-option>
                <el-option label="待发布" value="0"></el-option>
                <el-option label="已下架" value="2"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="18">
            <el-form-item label="发布位置" prop="clientType">
              <el-radio-group v-model="editNoticeForm.clientType">
                <el-radio label="OfficialWebsite">官网</el-radio>
                <!-- <el-radio label="交易端"></el-radio> -->
              </el-radio-group>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="15">
            <div class="block">
              <span class="demonstration">预设发布</span>
              <el-date-picker
                ref="cron"
                v-model="editNoticeForm.cron"
                type="datetime"
                placeholder="选择日期时间"
                value-format="timestamp"
                :disabled="disabled"
                :picker-options="pickerRange"
                prop="cron"
              >
              </el-date-picker>
            </div>
          </el-col>
          <el-col :span="6">
            <el-form-item prop="isTimedJob">
              <el-checkbox v-model="editNoticeForm.isTimedJob" label="立即发布" @change="pulishNow()"></el-checkbox>
            </el-form-item></el-col>
        </el-row>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button size="medium " @click="editDialog = false">取 消</el-button>
        <el-button size="medium " type="primary" @click="confirmEdit">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 预览弹框 -->
    <el-dialog class="previewDialog" title="公告预览" :visible.sync="previewDialog" width="50%">
      <el-row>
        <el-col :span="4">标题</el-col>
        <el-col :span="20">{{ previewData.title ||'' }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4">时间</el-col>
        <el-col :span="20">{{ previewData.releaseDate||'' }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="4">内容</el-col>
        <el-col :span="20" class="contentPreview" v-html="previewData.content">
        </el-col>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button size="medium " @click="previewDialog = false">关 闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
import dictionary from '../../../components/dictionary/dictionary';
// import { quillEditor } from 'vue-quill-editor';
const toolbarOptions = [
  ['bold', 'italic', 'underline'], // toggled buttons'strike'
  [{ 'header': 2 }], // custom button values
  // [{ 'list': 'ordered' }, { 'list': 'bullet' }],
  [{ 'indent': '-1' }, { 'indent': '+1' }], // outdent/indent
  // [{ 'direction': 'rtl' }], // text direction
  // [{ 'size': ['small', false, 'large', 'huge'] }], // custom dropdown
  // [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
  // [{ 'color': [] }, { 'background': [] }], // dropdown with defaults from theme
  // [{ 'font': [] }],
  // [{ 'align': [] }],
  ['link', 'image']
  // ['clean']

];
export default {
  components: {
    dictionary // 字典表组件
  },
  filters: {
    typeFilter(val) {
      switch (val) {
        case 'en_US':
          val = '英文';
          break;
        case 'zh_CN':
          val = '中文';
          break;
        case 'OfficialWebsite':
          val = '官网';
          break;
        case 'announce':
          val = '公告';
          break;
        case 0:
          val = '待发布';
          break;
        case 1:
          val = '已发布';
          break;
        case 2:
          val = '已下架';
          break;
      }
      return val;
    }
  },
  data() {
    return {
      quillUpdateImg: false, // 根据图片上传状态来确定是否显示loading动画，刚开始是false,不显示
      editorOption: {
        placeholder: '',
        theme: 'snow', // or 'bubble'
        modules: {
          toolbar: {
            container: toolbarOptions,
            handlers: {
              'image': function(value) {
                if (value) {
                  // 触发input框选择图片文件
                  document.querySelector('.avatar-uploader input').click();
                } else {
                  this.quill.format('image', false);
                }
              }
            }
          }
        }
      },
      // editorOption: { // 富文本配置
      //   modules: {
      //     toolbar: [
      //       ['bold', 'italic', 'underline', 'link'], // toggled buttons
      //       [{ header: 1 }, { header: 2 }], // 值字体大小
      //       [{ color: [] }],
      //       ['blockquote', 'code-block', 'image']
      //     ]
      //   }
      // },// 有的图片服务器要求请求头需要有token
      fileName: null,
      disabled: false, // 禁用
      pickerRange: { // 设置时间选框范围
        disabledDate: time => {
          return time < new Date();
        }
      },
      formSearch: { // 表单搜索事件
        type: null,
        status: null,
        lang: null,
        createBy: null,
        clientType: null,
        cron: null
      },
      noticesTable: [ // 表格数据
      ],
      pageParams: {
        rows: 10,
        page: 1, // 当前页
        totalPage: 0
      },
      pageSize: null,
      num: 1, // 页码初始数
      numLast: 10,
      total: null, // 总页数
      editDialog: false,
      noticeTitle: null,
      editNoticeForm: { // 编辑表单数据
        type: null,
        lang: null,
        title: null,
        content: null,
        status: null,
        clientType: null,
        cron: null,
        isTimedJob: true
      },
      previewDialog: false,
      previewData: {},
      multipleSelection: [],
      editNoticeFormRules: {// 校验规则
        type: [
          { required: true, message: '请选择类型', trigger: 'change' }
        ],
        lang: [
          { required: true, message: '请选择语言', trigger: 'change' }
        ],
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 40, message: '长度不多于40字符', trigger: 'blur' }
        ],
        clientType: [
          { required: true, message: '请选择发布位置', trigger: 'change' }
        ]
      },
      loading: null
    };
  },
  created() {
    this.getNoticesTable();
  },
  methods: {
    getNoticesTable() { // 获取表格数据
      if (this.formSearch.cron) {
        // 处理注册时间
        this.formSearch.startDate = this.formSearch.cron[0];
        this.formSearch.endDate = this.formSearch.cron[1];
      } else {
        this.formSearch.startDate = null;
        this.formSearch.endDate = null;
      }
      const formSearchCopy = JSON.parse(JSON.stringify(this.formSearch));
      delete formSearchCopy.cron;
      this.$http({
        url: '/common/newsBulletin/data',
        method: 'post',
        params: Object.assign(this.pageParams, formSearchCopy)
      }).then(res => {
        if (res.code === 200) {
          this.noticesTable = res.rows;
          this.total = res.total;
          this.pageSize = res.rows.length;
          this.numLast = (res.pages - 1) * 10 + res.rows.length;
        } else {
          this.$message(res.message);
        }
      }).catch(error => this.$message.error(error));
    },
    handleCurrentChange(val) {
      this.getNoticesTable();
      this.num = (val - 1) * 10 + 1;
      this.pageParams.page = val;
    },
    handleSelectionChange(val) { // 处理多选事件
      this.multipleSelection = val;
    },
    editNotice(index, row) { // 打开修改公告弹框
      this.editNoticeForm = JSON.parse(JSON.stringify(row));
      this.editNoticeForm.status = this.editNoticeForm.status.toString();
      this.editNoticeForm.cron = this.editNoticeForm.cron * 60000;
      if (this.editNoticeForm.isTimedJob === false) {
        this.editNoticeForm.cron = '';
        this.disabled = true;
      } else {
        this.disabled = false;
      }
      this.editNoticeForm.isTimedJob = !this.editNoticeForm.isTimedJob;
      this.noticeTitle = '修改公告';
      this.editDialog = true;
    },
    previewNotice(index, row) { // 打开预览弹框
      this.previewDialog = true;
      const rowCopy = JSON.parse(JSON.stringify(row));
      if (rowCopy.releaseDate) {
        rowCopy.releaseDate = this.$verify.NowDate4(parseInt(rowCopy.releaseDate));
      }
      this.previewData = rowCopy;
    },
    confirmEdit() { // 提交新增修改公告
      const editNoticeForm = JSON.parse(JSON.stringify(this.editNoticeForm));
      if (editNoticeForm.cron) {
        editNoticeForm.cron = editNoticeForm.cron.toString();
      }
      editNoticeForm.isTimedJob = !this.editNoticeForm.isTimedJob;

      this.$refs.upload.submit();

      this.$refs.editNoticeForm.validate((valid) => {
        if (valid) {
          if (editNoticeForm.content === '') {
            this.$message.error('请输入公告内容');
            return false;
          }
          if (!editNoticeForm.cron && editNoticeForm.isTimedJob) {
            this.$message.error('请选择发布时间');
            return false;
          }
          if (editNoticeForm.cron) {
            if (editNoticeForm.cron <= new Date()) {
              this.$message.error('发布时间必须要大于当前时间');
              return false;
            }
          }
          this.$http({
            url: '/common/newsBulletin/save',
            method: 'post',
            // params: editNoticeForm
            data: editNoticeForm
          }).then(res => {
            if (res.code === 200) {
              this.editDialog = false;
              this.$message({
                message: '成功',
                type: 'success'
              });
              this.getNoticesTable();
            } else {
              this.$message(res.message);
            }
          }).catch(error => {
            this.$message.error(error);
          });
        }
      });
    },
    addNotice() { // 打开新增弹框
      this.disabled = false;
      this.editNoticeForm.status = '0';
      this.noticeTitle = '新增公告';
      this.editDialog = true;
      if (this.$refs.editNoticeForm) {
        this.$refs.editNoticeForm.resetFields();
      }
      Object.keys(this.editNoticeForm).map(key => {
        this.editNoticeForm[key] = '';
        return key;
      });
    },
    pulishNow() { // 立即发布
      if (this.editNoticeForm.isTimedJob) {
        this.disabled = true;
        this.editNoticeForm.cron = '';
      } else {
        this.disabled = false;
      }
    },
    delectNotice() {
    },
    unShelve(index, row) { // 下架
      this.$http({
        url: '/common/newsBulletin/updateStatus',
        method: 'post',
        params: { id: row.id, status: '2' }
      }).then(res => {
        if (res.code === 200) {
          this.editDialog = false;
          this.$message({
            message: '下架成功',
            type: 'success'
          });
          this.getNoticesTable();
        } else {
          this.$message(res.message);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    putaway(index, row) { // 上架
      this.$http({
        url: '/common/newsBulletin/updateStatus',
        method: 'post',
        params: { id: row.id, status: '1' }
      }).then(res => {
        if (res.code === 200) {
          this.editDialog = false;
          this.getNoticesTable();
          this.$message({
            message: '发布成功',
            type: 'success'
          });
        } else {
          this.$message(res.message);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    onEditorBlur(val) {
      // console.log(val);
    },
    onEditorFocus(val) {
      // console.log(val);
    },
    onEditorChange(val) {
      val.quill.deleteText(1200, 4);
      // console.log('---内容改变事件---');
      this.editNoticeForm.content = val.html;
    },
    beforeUpload(val) {
      this.loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      this.$http({
        url: '/common/newsBulletin/generateUploadObjectPresignedUrl',
        method: 'get',
        params: { fileName: val.name }
      }).then(res => {
        if (res.code === 200) {
          this.$http({
            url: res.object.presignedUrl,
            method: 'put',
            data: val
          }).then(() => {
            this.getImgUrl(res.object.url);
          }).catch(error => {
            this.$message.error(error || '上传图片失败');
          });
        } else {
          this.$message(res.message);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    getImgUrl(url) { // 获取富文本图片路径
      // setTimeout(() => {
      //   this.loading.close();
      // }, 5000);
      this.$http({
        url: '/common/newsBulletin/saveRichTextPicture',
        method: 'post',
        params: { path: url }
      }).then(res => {
        if (res.code === 200) {
          const quill = this.$refs.myQuillEditor.quill;
          const length = quill.getSelection().index;
          quill.insertEmbed(length, 'image', res.object);
          quill.setSelection(length + 1);
          this.loading.close();
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    // 富文本图片上传失败
    // uploadError() {
    //   // loading动画消失
    //   this.loading = false;
    //   // this.$message.error('图片插入失败');
    // },
    dateFormat(row, column) { // 时间戳转为正常时间展示
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$verify.NowDate4(date);
    }
  }

};
</script>
<style lang="scss">
@import "newsbulletin.scss";
</style>
