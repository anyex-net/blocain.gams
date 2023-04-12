<template>
  <div class="problemCollection">
    <!-- 搜索表单 -->
    <el-form :model="formSearch" :inline="true" class="formSearch">
      <el-row>
        <el-col :span="6">
          <el-form-item label="问题关键词">
            <el-input v-model="formSearch.content" placeholder="请输入关键词描述"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="解答情况">
            <el-select v-model="formSearch.status" placeholder="状态" clearable>
              <el-option label="已解答" value="1"></el-option>
              <el-option label="未解答" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="提问时间">
            <el-date-picker
              v-model="formSearch.time"
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
          <el-form-item>
            <el-button type="primary" class="el-icon-search" @click="getProblemTable">查 询</el-button>
            <el-button type="success" class="el-icon-download" @click="exportToExcel">导 出</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-button type="danger" size="mini" class="deleteProblem" @click="deleteProblem()">删 除</el-button>
    <!-- 表格展示 -->
    <el-table :data="problemCollectionData" style="width: 100%" border fit @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"> </el-table-column>
      <el-table-column label="序号" type="index" width="50" align="center"></el-table-column>
      <el-table-column prop="content" label="描述" width="400" :show-overflow-tooltip="true"></el-table-column>
      <el-table-column prop="createDate" label="收藏日期" width="180" :formatter="dateFormat"> </el-table-column>
      <el-table-column prop="createBy" label="收藏人"> </el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          <span>{{ scope.row.status| typeFilter }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200px">
        <template slot-scope="scope">
          <el-button v-if="!scope.row.status" size="mini" type="success" @click="answer(scope.$index, scope.row)">解 答</el-button>
          <el-button v-if="scope.row.status" size="mini" @click="answerDetails(scope.$index, scope.row)">查 看</el-button>
          <!-- <el-button type="danger" size="mini" @click="deleteProblem(scope.$index, scope.row)">删 除</el-button> -->
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
    <!-- 解答弹框 -->
    <el-dialog title="问题解答" :visible.sync="answerDialog" width="50%" class="answerDialog">
      <div>问题描述</div>
      <div>{{ answerData.content }}</div>
      <el-form v-model="answerForm">
        <el-form-item class="content">
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
            v-model="answerForm.content"
            :options="editorOption"
            @change="onEditorChange($event)"
          >
          </quill-editor>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="answerDialog = false">取 消</el-button>
        <el-button type="primary" @click="comfirmAnswer()">确 定</el-button>
      </span>
    </el-dialog>
    <el-dialog title="问题解答" :visible.sync="answerDetailsDialog" width="50%" class="answerDetailsDialog">
      <div>问题描述</div>
      <div>{{ answerPre.content }}</div>
      <div>解答情况   解答人：{{ answerPre.createBy }}</div>
      <div>解答情况</div>
      <div v-html="answerPre.answer"></div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="answerDetailsDialog = false">关 闭</el-button>
      </span>
    </el-dialog>
    <el-dialog title="删除问题" :visible.sync="confirmAnswerDialog" width="30%">
      <span>是否确认删除已选中的问题?</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="confirmAnswerDialog = false">取 消</el-button>
        <el-button type="primary" @click="handleConfirmAnswer()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
<script>
export default {
  filters: {
    typeFilter(val) {
      switch (val) {
        case 'en_US':
          val = '英文';
          break;
        case 'zh_CN':
          val = '中文';
          break;
        case 0:
          val = '未解答';
          break;
        case 1:
          val = '解答';
          break;
      }
      return val;
    }
  },
  data() {
    return {
      formSearch: {
        content: null,
        status: null,
        time: null
      },
      problemCollectionData: [
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
      answerDialog: false,
      answerForm: {
        content: null
      },
      confirmAnswerDialog: false,
      editorOption: { // 富文本配置
        // placeholder: '请输入',
        // theme: 'snow', // or 'bubble'
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'link'], // toggled buttons
            [{ header: 1 }, { header: 2 }], // 值字体大小
            [{ color: [] }],
            ['blockquote', 'code-block', 'image']
          ],
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
      },
      answerPre: {},
      answerDetailsDialog: false,
      answerData: '',
      idList: [],
      createBy: '',
      fileName: null,
      loading: null
    };
  },
  created() {
    this.getProblemTable();
  },
  methods: {
    beforeUpload(val) {
      this.loading = this.$loading({
        lock: true,
        text: 'Loading',
        spinner: 'el-icon-loading',
        background: 'rgba(0, 0, 0, 0.7)'
      });
      this.$http({
        url: '/common/feedback/generateUploadObjectPresignedUrl',
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
        url: '/common/feedback/saveRichTextPicture',
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
    getProblemTable() {
      if (this.formSearch.time) {
        // 处理注册时间
        this.formSearch.startDate = this.formSearch.time[0];
        this.formSearch.endDate = this.formSearch.time[1];
      } else {
        this.formSearch.startDate = null;
        this.formSearch.endDate = null;
      }
      const formSearchCopy = JSON.parse(JSON.stringify(this.formSearch));
      formSearchCopy.feedbackType = 'Online';
      delete formSearchCopy.time;
      this.$http({
        url: '/common/feedback/data',
        method: 'post',
        params: Object.assign(this.pageParams, formSearchCopy)
      }).then(res => {
        if (res.code === 200) {
          this.problemCollectionData = res.rows;
          this.total = res.total;
          this.pageSize = res.rows.length;
          this.numLast = (res.pages - 1) * 10 + res.rows.length;
        } else {
          this.$message(res.message);
        }
      }).catch(error => this.$message.error(error));
    },
    handleSelectionChange(val) {
      val.map(item => {
        this.idList.push(item.id);
      });
    },
    deleteProblem() {
      this.confirmAnswerDialog = true;
    },
    handleConfirmAnswer() {
      this.$http({
        url: '/common/feedback/del',
        method: 'post',
        params: { ids: this.idList.join() }
      }).then(res => {
        if (res.code === 200) {
          this.confirmAnswerDialog = false;
          this.getProblemTable();
        } else {
          this.$message(res.message);
        }
      }).catch(error => this.$message.error(error));
    },
    exportToExcel() {
      const list = this.problemCollectionData; // 此处为表格数据
      // 手动添加序号至表格中
      for (let i = 0; i < list.length; i++) {
        list[i].index = i + 1;
      }
      list.map(item => {
        item.createDate = this.$verify.NowDate4(item.createDate);
        switch (item.status) {
          case 0:
            item.status = '未解答';
            break;
          case 1:
            item.status = '解答';
            break;
        }
        return item;
      });
      // list.createDate = this.$verify.NowDate4(list.createDate);
      const { export_json_to_excel } = require('../../../account/accountManagement/js/Export2Excel');
      const tHeader = [
        '序号',
        '描述',
        '收藏日期',
        '收藏人',
        '状态'
      ];
      const filterVal = [
        'index',
        'content',
        'createDate',
        'createBy',
        'status'
      ];

      const data = this.formatJson(filterVal, list);
      export_json_to_excel(tHeader, data, '收藏问题列表');
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map(v => filterVal.map(j => v[j]));
    },
    handleCurrentChange(val) {
      this.getProblemTable();
      this.num = (val - 1) * 10 + 1;
      this.pageParams.page = val;
    },
    answer(index, row) {
      this.answerForm.content = '';
      this.answerData = row;
      this.answerDialog = true;
    },
    answerDetails(index, row) {
      this.answerDetailsDialog = true;
      this.answerPre = row;
      // this.createBy = row.createBy.userName;
    },
    onEditorChange(val) {
      val.quill.deleteText(2000, 4);
      this.answerForm.content = val.html;
    },
    comfirmAnswer() {
      this.$http({
        url: '/common/feedback/save',
        method: 'post',
        params: { content: this.answerData.content, status: 1, lang: 'zh_CN', answer: this.answerForm.content, id: this.answerData.id }
      })
        .then(res => {
          if (res.code === 200) {
            console.log(res, '收藏问题');
            this.answerDialog = false;
            this.$message({
              message: '成功解答',
              type: 'success'
            });
            this.getProblemTable();
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
      // this.$http({
      //   url: '/common/newsBulletin/data',
      //   method: 'post',
      //   params: { content: this.answerForm.content }
      // }).then(res => {
      //   if (res.code === 200) {
      //     console.log(res, 'res解答成功');
      //   } else {
      //     this.$message(res.message);
      //   }
      // }).catch(error => this.$message.error(error));
    },
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
  .problemCollection{
    .formSearch {
      font-size: 0;
      margin-top: 20px;
      .el-form-item {
        display: flex;
        .el-form-item__label {
          width: 88px;
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
    .answerDialog{
      .content{
            .el-form-item__content{
                height: 250px !important;
                .quill-editor{
                    height: 180px !important;
                }
            }
          }
    }
    .deleteProblem{
      margin-bottom: 10px;
    }
  }
</style>
