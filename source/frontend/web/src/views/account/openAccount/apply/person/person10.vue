<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(10/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person10"
            autocomplete="off"
            status-icon
            :model="person10"
            label-width="200px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div v-if="person10.isAllUpload !== 1" class="formNape" style="margin-bottom: 10px;">
                <div class="formNapeTitle">
                  <div>
                    <h3>
                      <ul class="statusTitle">
                        <li>
                          <span>{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.title') }}</span> <!-- $i18n - 申请状态 : -->
                          <span v-if="person10.isAllUpload !== 0 && person10.isAllUpload !== 1">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.prove') }}</span> <!-- $i18n - 未完成，等待其它证明文件 -->
                          <span v-if="person10.isAllUpload === 0">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.status') }}</span> <!-- $i18n - 完成开户,待审核 -->
                        </li>
                        <li>{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.describe') }}</li>  <!-- $i18n - 收到所有所需文件后，申请审核通常需1-2个工作日。 -->
                      </ul>
                    </h3>
                  </div>
                </div>
                <div class="formNapeContent " style="margin-top: 40px;">
                  <div class="other-files" style="line-height:18px;">
                    <h3 class="zhuce2ji" style="font-weight: bold;">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.qita') }}</h3> <!-- $i18n - 其他文件 -->
                    <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.describe2a') }}</p> <!-- $i18n - 就快完成了！ -->
                    <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.describe2b') }}</p> <!-- $i18n - 我们仅要求您向我们发送以下文件。 -->
                  </div>
                </div>
              </div>
              <div v-else class="formNape" style="margin-bottom: 10px;">
                <div class="formNapeTitle">
                  <div>
                    <h3>
                      <ul class="statusTitle">
                        <li>
                          <span>{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.title') }}</span> <!-- $i18n - 申请状态 : -->
                          <span>{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.accomplishStatus') }}</span> <!-- $i18n - 已完成审核 -->
                        </li>
                        <li>{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.describeB') }}</li> <!-- $i18n - 将以邮件方式通知您交易账号 ... -->
                      </ul>
                    </h3>
                  </div>
                </div>
                <div class="formNapeContent " style="margin-top: 40px;">
                  <div class="other-files" style="line-height:18px;">
                    <h3 class="zhuce2ji" style="font-weight: bold;">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.accomplishHint2') }}</h3> <!-- $i18n - 现在您可以 -->
                    <br>
                    <a target="_blank" href="https://www.clientam.com/sso/Login?partnerID=MOF2020" class="zhuce3ji" style="color:rgba(87, 153, 255, 1);">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.accomplishLinkAccount') }}</a> <!-- $i18n - 前往账户管理 -->
                    <br>
                    <router-link target="_blank" :to="{name:'Download'}" class="zhuce3ji" style="color:rgba(87, 153, 255, 1);">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.accomplishLinkDownload') }}</router-link> <!-- $i18n - 下载客户端 -->
                    <br>
                    <a target="_blank" href="http://www.clientam.com/php/whiteLabel/welcome.php?id=MOF2020" class="zhuce3ji" style="color:rgba(87, 153, 255, 1);">{{ $t('registerOpenAccount.openAccount.common.filesUpload.status.accomplishLinkGuide') }}</a> <!-- $i18n - 阅读用户指南 -->
                  </div>
                </div>
              </div>
              <div v-if="person10.isAllUpload !== 1" class="form-content">
                <!-- 电子上传 -->
                <div class="agreement1">
                  <div
                    class="agreement-top xiaoshou"
                    @click="person10.electronicMessage.isUnfold=!person10.electronicMessage.isUnfold"
                  >
                    <div class="zhuce2ji CORred left20 left1">{{ $t('registerOpenAccount.openAccount.common.filesUpload.dianziFile.title') }}</div> <!-- $i18n - 上载电子复印件 (建议的) -->
                    <div class="left20 zhuce2ji right1 right20 JYxialaanniu" :class="{ JYxialaanniuhover: person10.electronicMessage.isUnfold}" />
                  </div>
                  <div v-show="person10.electronicMessage.isUnfold" class="fileStateTable">
                    <div class="top20">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.dianziFile.describe1a') }}</p> <!-- $i18n - 获得及时服务，我们建议上载您的文件。 我们不接受手写文件。 -->
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.describe1b') }}</p> <!-- $i18n - 可接受的文件格式包括：PDF, JPG, PNG -->
                    </div>
                    <div class="gudongTable">
                      <table border="1" cellspacing="0" cellpadding="0">
                        <thead>
                          <tr>
                            <th class="COR666" style="width:624px">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.file') }}</th> <!-- $i18n - 文件 -->
                            <th class="COR666" style="width:16%">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.status') }}</th> <!-- $i18n - 状态 -->
                            <th class="COR666" style="width:10%">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.detail') }}</th> <!-- $i18n - 细节 -->
                          </tr>
                        </thead>
                        <tbody :class="{ tbodyEN: lang === 'en_US' }">
                          <tr v-for="(item,index) in person10.electronicMessage.tableList" :key="index">
                            <td class="textAlignL " style="text-align: left; padding-left:20px;line-height: 21px;">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.provide') }}- {{ item.userNameClient }} {{ item.fileTypeDestClient }}</td> <!-- $i18n - 请提供 -->
                            <td>
                              <span>{{ item.stateTextClient }}</span>
                              <span v-if="person10.isAllUpload !== 0 && person10.isAllUpload !== 1" class="shagnchuanbtn" @click="UploadBtn(index)">{{ $t('registerOpenAccount.common.btn.upload') }}</span> <!-- $i18n - 上传 -->
                              <span v-if="item.ifExamineClient" class="shagnchuanbtn" @click="getExaminefile(item.filePathClient, true)">{{ $t('registerOpenAccount.common.btn.examine') }}</span> <!-- $i18n - 查看 -->
                            </td>
                            <td class="CORred xiaoshou" @click="getExaminefile(item.filePath)">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.more') }}</td> <!-- $i18n - 更多信息 -->
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
                <!-- 邮寄，传真上传 -->
                <div class="agreement1 top20">
                  <div
                    class="agreement-top xiaoshou"
                    @click="person10.emailMessage.isUnfold=!person10.emailMessage.isUnfold"
                  >
                    <div class="zhuce2ji CORred left20 left1">{{ $t('registerOpenAccount.openAccount.common.filesUpload.emailFile.title') }}</div> <!-- $i18n - 电子邮件、传真或邮寄 -->
                    <div class="left20 zhuce2ji right1 right20 JYxialaanniu" :class="{ JYxialaanniuhover: person10.emailMessage.isUnfold}" />
                  </div>
                  <div v-show="person10.emailMessage.isUnfold" class="fileStateTable">
                    <div class="top20">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.emailFile.describe1a') }} {{ person10.emailMessage.email }}</p> <!-- $i18n - 请将您的电子文件通过电子邮件发送到 -->
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.emailFile.describe1b') }}</p> <!-- $i18n - 我们不接受手写文件。 -->
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.describe1b') }}</p> <!-- $i18n - 可接受的文件格式包括：PDF, JPG, PNG -->
                    </div>
                    <div class="gudongTable">
                      <table border="1" cellspacing="0" cellpadding="0">
                        <thead>
                          <tr>
                            <th class="COR666">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.file') }}</th> <!-- $i18n - 文件 -->
                            <th class="COR666" style="width:3%">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.detail') }}</th> <!-- $i18n - 细节 -->
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="(item,index) in person10.emailMessage.tableList" :key="index">
                            <td class="textAlignL" style="text-align: left; padding-left:20px">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.provide') }}- {{ item.userNameClient }} {{ item.fileTypeDestClient }}</td> <!-- $i18n - 请提供 -->
                            <td class="CORred xiaoshou" @click="getExaminefile(item.filePath)">{{ $t('registerOpenAccount.openAccount.common.filesUpload.common.more') }}</td> <!-- $i18n - 更多信息 -->
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                </div>
              </div>

              <!-- 模态框 -->
              <!-- <div v-if="isfileModal" class="Modalframe"> -->
              <div v-if="person10.isfileModal" class="Modalframe">
                <div class="MFcontent">
                  <div class="MFform">
                    <el-upload
                      ref="upload"
                      class="upload-demo"
                      drag
                      :action="person10.presignedUrl"
                      :name="person10.fileName"
                      :http-request="customUpload"
                      :before-upload="beforeAvatarUpload"
                      :before-remove="beforeRemove"
                      :file-list="fileList"
                      :on-change="doChange"
                      :limit="1"
                      :auto-upload="false"
                    >
                      <i class="el-icon-upload" />
                      <div class="el-upload__text">{{ $t('registerOpenAccount.openAccount.common.filesUpload.modal.describe1a') }}<em>{{ $t('registerOpenAccount.openAccount.common.filesUpload.modal.describe1b') }}</em></div> <!-- $i18n - 将文件拖到此处，或 --> <!-- $i18n - 选取文件 -->
                      <div slot="tip" class="el-upload__tip">
                        {{ $t('registerOpenAccount.openAccount.common.filesUpload.modal.describe1c') }}  <!-- $i18n - 只能上传jpg/png/pdf文件，且不超过1mb -->
                      </div>
                    </el-upload>
                    <div class="fileStateMotaiBtn">
                      <div class="left1 xiaoshou" @click="UploadBtnAWS()">{{ $t('registerOpenAccount.common.btn.addServer') }}</div> <!-- $i18n - 上传服务器 -->
                      <div class="left1 xiaoshou" @click="removeModalBox()">{{ $t('registerOpenAccount.common.btn.close') }}</div> <!-- $i18n - 关闭 -->
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      lang: this.$Cookies.get('locale'),
      fileList: [

      ],
      isTokenHint: false, // 接口token过期提示
      person10: {
        isAllUpload: '', // 是否完成开户状态
        isfileModal: false, // 是否show模态框
        isBeBeingUpload: false, // 是否正在上传中
        fileIndex: 0, // 当前点击上传的下标
        fileSuffix: '', // 文件后缀
        presignedUrl: 'https://mof-web.s3-ap-northeast-1.amazonaws.com/', // 传到aws桶
        url: '', // 传给后端
        fileName: '',
        electronicMessage: { // 电子文件
          isUnfold: true,
          tableList: [
            // {
            //   userNameClient: '用户名',
            //   fileTypeDestClient: '上传的文件类型',
            //   stateTextClient: '未上传',
            //   ifExamineClient: false,
            //   filePathClient: 'www.baidu.com aws上传查看的文件路径'
            // }
          ]
        },
        emailMessage: { // 邮寄传真文件
          isUnfold: true,
          email: 'info@mofsecurities.com',
          tableList: [
            // {
            //   userNameClient: '用户名',
            //   fileTypeDestClient: '文件类型',
            //   stateTextClient: '未上传',
            //   ifExamineClient: false,
            //   filePathClient: 'www.baidu.com aws上传查看的文件路径'
            // }
          ]
        }
      }
    };
  },

  created() {
    this.openAccount();
  },
  methods: {
    printPdf() { // 调用浏览器的打印功能打印指定区域
      window.print();
    },
    openAccount() {
      this.$store.dispatch('user/getInfo').then(res => {
        if (res.code === 200) {
          // 0 待审核 1 已审核 其他为未审核
          this.person10.isAllUpload = res.object.registerStatus;
          this.signature();
        } else if (res.code === 2003) {
          this.$verify.staleDated(this);
        } else {
          this.$message.error(res.message || 'Has Error');
        }
      }).catch((error) => {
        this.$message.error(error.message || 'Has Error');
      });
    },

    signature() { // 获取用账户名称-姓名
      this.$http({
        url: '/account/accountPerInfo/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.getTableList(res.object[0].lastName + ' ' + res.object[0].firstName);
            }
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error(error.message || 'Has Error');
        });
    },
    getTableList(userNameClient) { // 获取表格list
      this.$http({
        url: '/openAccount/personal/getFileIdentification',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            for (let i = 0; i < res.object.accountCommonFileList.length; i++) {
              res.object.accountCommonFileList[i].userNameClient = userNameClient;
              res.object.accountCommonFileList[i].fileTypeDestClient = res.object.accountCommonFileList[i].fileName;
              res.object.accountCommonFileList[i].stateTextClient = this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.hint3'); // <!-- $i18n - 未上传 -->
              res.object.accountCommonFileList[i].fileTypeID = '';
              res.object.accountCommonFileList[i].ifExamineClient = false;
              this.person10.electronicMessage.tableList.push(JSON.parse(JSON.stringify(res.object.accountCommonFileList[i])));
              this.gerFileIdentification(i);
            }
            this.person10.emailMessage.tableList = this.person10.electronicMessage.tableList;
          }
        } else if (res.code === 2003) {
          if (!this.isTokenHint) {
            this.isTokenHint = true;
            this.$verify.staleDated(this);
          }
        } else {
          this.$message.error('' + res.code + '');
        }
      }).catch(error => {
        this.$message.error(error.message || 'Has Error');
      });
    },
    gerFileIdentification(index) { // 获取上传的文件路径
      this.$http({
        url: '/account/accountPerFile/findByAccountId/fileIdentification',
        method: 'get',
        params: {
          fileIdentification: this.person10.electronicMessage.tableList[index].dictValue,
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          this.filesUploadStatus();
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person10.electronicMessage.tableList[index].fileTypeID = res.object[0].id;
              this.person10.electronicMessage.tableList[index].filePathClient = res.object[0].filePath;
              this.person10.electronicMessage.tableList[index].stateTextClient = this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.common.uploadSuccessful'); // <!-- $i18n - 上传成功 -->
              this.person10.electronicMessage.tableList[index].ifExamineClient = true;
              this.filesUploadStatus();
            }
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error(error.message || 'Has Error');
        });
    },
    filesUploadStatus() { // 文件状态,是否全部上传
      let status = 0;
      if (this.person10.electronicMessage.tableList.length !== 0) {
        for (let i = 0; i < this.person10.electronicMessage.tableList.length; i++) {
          if (this.person10.electronicMessage.tableList[i].ifExamineClient) {
            status++;
          }
        }
      }
      if (status === this.person10.electronicMessage.tableList.length && this.person10.isAllUpload !== 1) {
        this.$http({
          url: '/openAccount/personal/openAccountFinish',
          method: 'post',
          params: { status: 1, lang: this.lang }
        })
          .then(res => {
            if (res.code === 200) {
              this.person10.isAllUpload = 0;
              this.$message.success(this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.status.hint1')); // <!-- $i18n - 文件已全部上传 -->
            } else if (res.code === 2003) {
              if (!this.isTokenHint) {
                this.isTokenHint = true;
                this.$verify.staleDated(this);
              }
            } else {
              this.$message.error('' + res.code + '');
            }
          })
          .catch(error => {
            this.$message.error(error.message || 'Has Error');
          });
      }
    },

    beforeAvatarUpload(file) { // // ---- 上传文件之前
      const testmsg = file.name.substring(file.name.lastIndexOf('.') + 1);
      const isLt2M = file.size / 1024 / 1024 < 5;
      let status = true;
      if (testmsg !== 'jpg' && testmsg !== 'png' && testmsg !== 'pdf') {
        status = false;
        this.$message({
          message: this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.describe1d'), // $i18n - 上传文件只能是 jpg、png、pdf格式!
          type: 'warning'
        });
      }
      if (!isLt2M) {
        status = false;
        this.$message({
          message: this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.describe1e'), // $i18n - 上传文件大小不能超过 1MB!
          type: 'warning'
        });
      }
      this.person10.fileSuffix = testmsg;
      return status;
    },
    beforeRemove(file, fileList) { // ---- 删除文件之前
      if (this.person10.isBeBeingUpload === true) {
        this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.hint1')); // $i18n - 正在上传中
        return false;
      } else {
        return this.$confirm(`${this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.isShiftOut')} ${file.name}？`); // $i18n - 确定移除
      }
    },
    doChange(file, fileList) { // 文件状态更新
      this.fileList = fileList;
    },
    customUpload(param) { // http-request 自定义上传
      this.fileList[0].percentage = 10;
      this.fileList[0].status = 'uploading';
      this.person10.isBeBeingUpload = true;

      this.generateUploadObjectPresignedUrl().then(res => {
        this.fileList[0].percentage = 30;
        this.person10.presignedUrl = res.object.presignedUrl;
        this.person10.url = res.object.url;
        this.UploadAWS(param);
      });
    },
    generateUploadObjectPresignedUrl() { // 获取上传文件加密后的路径
      return new Promise((resolve, reject) => {
        this.person10.fileName = this.$store.state.user.applyData.xinxi.id + '-' + this.person10.electronicMessage.tableList[this.person10.fileIndex].id + '.' + this.person10.fileSuffix;
        this.$http({
          url: 'openAccount/personal/generateUploadObjectPresignedUrl',
          method: 'get',
          params: {
            dir: 'AccountFile',
            fileName: this.person10.fileName,
            IEgetIssue: this.$verify.timestampRandom()
          }
        })
          .then(res => {
            if (res.code === 200) {
              if (this.$verify.resStatus(res)) {
                resolve(res);
              }
            } else if (res.code === 2003) {
              if (!this.isTokenHint) {
                this.isTokenHint = true;
                this.$verify.staleDated(this);
              }
            } else {
              this.$message.error('' + res.code + '');
            }
          }).catch(error => {
            this.$message.error(error.message || 'Has Error');
          });
      });
    },
    UploadAWS(param) { // 文件上传到aws桶
      this.$http({
        url: this.person10.presignedUrl,
        method: 'put',
        data: param.file
      })
        .then(res => {
          this.fileList[0].percentage = 70;
          this.UploadAfterEnd(this.person10.fileIndex);
        }).catch(error => {
          this.person10.isBeBeingUpload = false;
          this.$message.error(error.message || this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.common.uploadDefeated')); // $i18n - 上传失败
          this.fileInitialize();
        });
    },
    UploadAfterEnd(index) { // 上传文件信息至后端
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      const data = {
        accountId,
        createdBy,
        createdDate,
        fileName: this.person10.electronicMessage.tableList[index].fileName,
        filePath: this.person10.url,
        id: this.person10.electronicMessage.tableList[this.person10.fileIndex].fileTypeID,
        fileIdentification: this.person10.electronicMessage.tableList[index].dictValue
      };
      this.$http({
        url: '/openAccount/personal/saveTen',
        method: 'put',
        async: false,
        data
      }).then(res => {
        if (res.code === 200) {
          this.fileList[0].percentage = 100;
          this.person10.isBeBeingUpload = false;
          this.$message.success(this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.common.uploadSuccessful')); // $i18n - 上传成功
          this.fileInitialize(true);
          this.gerFileIdentification(index);
        } else if (res.code === 2003) {
          if (!this.isTokenHint) {
            this.isTokenHint = true;
            this.$verify.staleDated(this);
          }
        } else {
          this.person10.isBeBeingUpload = false;
          this.fileInitialize(false);
          this.$message.error(res);
        }
      }).catch(error => {
        this.person10.isBeBeingUpload = false;
        this.$message.error(error.message || 'Has Error');
        this.fileInitialize(false);
      });
    },

    UploadBtn(index) { // 上传btn点击
      this.person10.isfileModal = true;
      this.person10.fileIndex = index;
    },
    UploadBtnAWS() { // 模态框上传服务器btn点击
      if (this.$refs.upload.uploadFiles.length !== 0) {
        if (this.person10.isBeBeingUpload === true) {
          this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.hint1')); // $i18n - 正在上传中
        } else {
          this.$refs.upload.submit();
        }
      } else {
        this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.hint2')); // $i18n - 请选择文件
      }
    },
    fileInitialize(isremoveModalBox = false) { // 文件列表初始化+进度条初始化
      this.fileList = [];
      this.person10.isfileModal = !isremoveModalBox;
    },
    removeModalBox() { // 关闭模态框
      if (this.person10.isBeBeingUpload === true) {
        this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.filesUpload.modal.hint1')); // $i18n - 正在上传中
      } else {
        this.fileInitialize(true);
      }
    },
    getExaminefile(url, isGetAdvanceSignature = false) { // 查看文件
      if (isGetAdvanceSignature) {
        this.$verify.preSignedUrl(url).then(res => {
          window.open(res.object);
        }).catch(error => {
          this.$message.error(error.message || 'Has Error');
        });
      } else {
        window.open(url);
      }
    }
  }
};
</script>

<style lang="scss"  scoped>

.form-btn .btn-left:hover {
  cursor: pointer;
}
.statusTitle {
  li:nth-child(1) {
    margin-bottom: 18px;
    span:nth-child(1) {
      font-size: 16px;
      font-weight: bold;
      color: #333;
    }
    span:nth-child(n+2) {
      color: #FF5949 ;
      font-size: 18px ;
    }
  }
  li:nth-child(2) {
    line-height: 24px;
    font-size: 16px;
    color: #999;
    margin-bottom: 14px;
  }
}
.fileStateTable {
  padding: 0px 20px;
}
</style>

