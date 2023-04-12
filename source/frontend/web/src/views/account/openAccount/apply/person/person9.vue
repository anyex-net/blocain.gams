<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(9/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person9"
            autocomplete="off"
            status-icon
            :model="person9"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.title') }}</h3> <!-- $i18n - 打击洗钱及恐怖份子资金筹集(AML/CTF)风险问卷（个人） -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.describe') }}</p> <!-- $i18n - 客户须知：根据相关法律法规，如调查发现 ... -->
                </div>
                <div class="formNapeContent" />
              </div>
              <!-- 个人信息 -->
              <div class="formNape">
                <div class="formNapeContent">
                  <!-- 渲染内容 -->
                  <div>
                    <div class="form-content identityInformation">
                      <ul>
                        <li>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.name') }}</li> <!-- $i18n - 账户名称 : -->
                        <li>{{ person9.identityInformation.accountName }}</li>
                      </ul>
                      <br>
                      <ul>
                        <li>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.prove') }}</li> <!-- $i18n - 身份证明 : -->
                        <li>{{ person9.identityInformation.identificationPaper }}</li>
                      </ul>
                      <br>
                      <ul>
                        <li>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.region') }}</li> <!-- $i18n - 所在地区 : -->
                        <li>{{ person9.identityInformation.location }}</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 个人信息 -->
              <div class="formNape">
                <!-- <div class="formNapeTitle bianjifuBOX" style="height: 12px;">
                  <h3 style="  font-weight: 200;color: rgb(35, 35, 35);font-size:18px;margin-bottom: -9px;" class="left1">个人信息</h3>
                </div> -->
                <div class="formNapeContent">
                  <!-- 渲染内容 -->
                  <div>
                    <div class="form-content ifBtnStyle">
                      <!-- 1 -->
                      <div v-for="(item,index) in person9.questionnaireList" :key="index">
                        <div class="isBtn">
                          <ul ref="istext">
                            <transition name="fade">
                              <li
                                class="isyes"
                                :class="{ dianji: item.ifbtn,fade:true}"
                                @click="item.ifbtn = true"
                              >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                            </transition>
                            <transition name="fade">
                              <li
                                class="isno"
                                :class="{ dianji: !item.ifbtn,fade:true}"
                                @click="item.ifbtn = false"
                              >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                            </transition>
                            <li class="istext">{{ item.dictDest }}</li>
                          </ul>
                          <el-form-item v-if="item.ifbtn" style="margin-bottom: 22px;" :prop="'questionnaireList.'+index+'.fillInput'" :rules="rules.fillInput">
                            <el-input v-model="item.fillInput" maxlength="30" :placeholder="$t('registerOpenAccount.common.formPrompt.enter')" /> <!-- $i18n - 请输入 -->
                          </el-form-item>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('person9')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }} </el-button>
                  </el-form-item>
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
  data() {
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      person9: {
        identityInformation: {
          accountName: '',
          identificationPaper: '',
          location: ''
        },
        questionnaireList: [
          // {
          //   dictDest: '您是否有政府人物或其相关者？',
          //   dictValue: '1',
          //   ifbtn: false,
          //   fillInput: ''
          // }
        ]
      },
      newValue: null,
      rules: {
        fillInput: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }] // $i18n - 请输入
      }
    };
  },
  created() {

  },
  mounted() {
    this.getPersonalidentifiable();
    this.getSignature();
    this.issueList();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    getAccountNameMessage() { // 获取用户已填入的信息
      this.$http({
        url: '/account/accountPerAmlCtf/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            this.newValue = JSON.parse(JSON.stringify(res.object));
            for (let i = 0; i < res.object.length; i++) {
              this.person9.questionnaireList[i].ifbtn = res.object[i].status;
              this.person9.questionnaireList[i].fillInput = res.object[i].supplementaryInfo;
            }
          }
        } else if (res.code === 2003) {
          if (!this.isTokenHint) {
            this.isTokenHint = true;
            this.$verify.staleDated(this);
          }
        } else {
          this.$message.error(res.message || res.code);
        }
      })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    issueList() { // 获取问卷列表
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'personalAml',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      }).then(res => {
        if (res.code === 200) {
          for (let i = 0; i < res.object.length; i++) {
            res.object[i].ifbtn = false;
            res.object[i].fillInput = '';
          }
          this.person9.questionnaireList = res.object;
          this.getAccountNameMessage();
        } else if (res.code === 2003) {
          if (!this.isTokenHint) {
            this.isTokenHint = true;
            this.$verify.staleDated(this);
          }
        } else {
          this.$message.error(res.message || res.code);
        }
      })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    getPersonalidentifiable() { // 获取个人身份证明信息
      this.$http({
        url: '/account/accountPerIdentity/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              const _this = this;
              this.$verify.State1(this.$store.state.user.applyData.xinxi.country, _this, this.lang).then(res => {
                this.person9.identityInformation.location = res;
              });
              this.$verify.State1(res.object[0].issuingCountry, _this, this.lang).then(succeed => {
                this.identityType(succeed, res.object[0].idCardType, res.object[0].idNumber);
              });
            }
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    identityType(issuingCountry, idCardType, idNumber) {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'cardType',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      }).then(res => {
        if (res.code === 200) {
          for (let i = 0; i < res.object.length; i++) {
            if (res.object[i].dictValue === idCardType) {
              idCardType = res.object[i].dictDest;
              this.person9.identityInformation.identificationPaper = issuingCountry + ' , ' + idCardType + ' , ' + idNumber;
            }
          }
        } else if (res.code === 2003) {
          if (!this.isTokenHint) {
            this.isTokenHint = true;
            this.$verify.staleDated(this);
          }
        } else {
          this.$message.error(res.message || res.code);
        }
      });
    },
    getSignature() { // 获取声明签名
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
              this.person9.identityInformation.accountName = res.object[0].lastName + ' ' + res.object[0].firstName;
            }
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 验证表单 跳转页面
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint'));
        }
      });
    },
    // 提交表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      let data = [];
      if (this.newValue === null) {
        for (let i = 0; i < this.person9.questionnaireList.length; i++) {
          data.push({
            accountId,
            createdBy,
            createdDate,
            issue: this.person9.questionnaireList[i].dictValue,
            status: this.person9.questionnaireList[i].ifbtn,
            supplementaryInfo: this.person9.questionnaireList[i].ifbtn ? this.person9.questionnaireList[i].fillInput : null
          });
        }
      } else {
        for (let i = 0; i < this.person9.questionnaireList.length; i++) {
          this.newValue[i].issue = this.person9.questionnaireList[i].dictValue;
          this.newValue[i].status = this.person9.questionnaireList[i].ifbtn;
          this.newValue[i].supplementaryInfo = this.person9.questionnaireList[i].ifbtn ? this.person9.questionnaireList[i].fillInput : null;
        }
        data = this.newValue;
      }
      this.$http({
        url: '/openAccount/personal/saveNine',
        method: 'put',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person10' });
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/person8' });
    }
  }
};
</script>
<style lang="scss" scoped>
   #ZC-regiMain .formNape .identityInformation {
     ul {
       display: block;
       height: 20px;
       li {
         float: left;
         font-size: 16px;
       }
       li:nth-child(1) {
         color: #666;
       }
       li:nth-child(2) {
         color: #999;
         margin-left: 12px;
       }
     }
   }
</style>
