<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(16/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership18"
            autocomplete="off"
            status-icon
            :model="partnership18"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.title') }}</h3> <!-- $i18n - 打击洗钱及恐怖份子资金筹 ... -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.describe') }}</p> <!-- $i18n - 客户须知：根据相关法律法规，如调 ... -->
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
                        <li>{{ partnership18.identityInformation.accountName }}</li>
                      </ul>
                      <br>
                      <ul>
                        <li>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.authorizedPerson') }}</li> <!-- $i18n - 被授权人 : -->
                        <li>{{ partnership18.identityInformation.authorizedPerson }}</li>
                      </ul>
                      <br>
                      <ul>
                        <li>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.prove') }}</li> <!-- $i18n - 身份证明 : -->
                        <li>{{ partnership18.identityInformation.identificationPaper }}</li>
                      </ul>
                      <br>
                      <ul>
                        <li>{{ $t('registerOpenAccount.openAccount.common.RiskQuestionnaire.region') }}</li> <!-- $i18n - 所在地区 : -->
                        <li>{{ partnership18.identityInformation.location }}</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 个人信息 -->
              <div class="formNape">
                <div class="formNapeContent">
                  <!-- 渲染内容 -->
                  <div>
                    <div class="form-content ifBtnStyle">
                      <!-- 1 -->
                      <div v-for="(item,index) in partnership18.questionnaireList" :key="index">
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
                          <el-form-item v-if="item.ifbtn" :prop="'questionnaireList.'+index+'.fillInput'" :rules="rules.fillInput">
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
                    <el-button class="right-btn BTbtn" plain @click="submitForm('partnership18')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }}</el-button>
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
      partnership18: {
        identityInformation: {
          accountName: '',
          authorizedPerson: '',
          identificationPaper: '',
          location: ''
        },
        questionnaireList: [
          // {
          //   dictDest: '被授权人/公司的控股股东是否有政府人物或其相关者？',
          //   dictValue: '1',
          //   ifbtn: false,
          //   fillInput: ''
          // }
        ]
      },
      newValue: null,
      rules: {
        fillInput: [{ required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.enter'), trigger: 'change' }] // $i18n - 请输入
      }
    };
  },
  created() {

  },
  mounted() {
    this.accountParPersonal();
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
        url: '/account/accountParAmlCtf/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            this.newValue = JSON.parse(JSON.stringify(res.object));
            for (let i = 0; i < res.object.length; i++) {
              this.partnership18.questionnaireList[i].ifbtn = res.object[i].status;
              this.partnership18.questionnaireList[i].fillInput = res.object[i].supplementaryInfo;
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
        params: { dictCode: 'aml', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          for (let i = 0; i < res.object.length; i++) {
            res.object[i].ifbtn = false;
            res.object[i].fillInput = '';
          }
          this.partnership18.questionnaireList = res.object;
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
    accountParPersonal() { // 被授权人 - 第二页被授权人个人信息
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: 'authorizedPerson', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // let appellation = res.object[0].appellation;
              let appellation = ''; // 称呼 暂时为空
              if (appellation) {
                appellation = appellation + ' ';
              }
              const lastName = res.object[0].lastName;
              const middleName = res.object[0].middleName;
              const firstName = res.object[0].firstName;
              const suffix = res.object[0].suffix;
              this.partnership18.identityInformation.authorizedPerson = appellation + lastName + ' ' + middleName + ' ' + firstName + ' ' + suffix;
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
    getPersonalidentifiable() { // 身份证明 + 所在地区  - 第二页身份证明
      this.$http({
        url: '/account/accountParIdentity/findByAccountId/personal',
        method: 'get',
        params: { personalType: 'authorizedPerson', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              const _this = this;
              this.$verify.State1(this.$store.state.user.applyData.xinxi.country, _this, this.lang).then(res => {
                this.partnership18.identityInformation.location = res;
              });
              this.$verify.State1(res.object[0].issuingCountry, _this, this.lang).then(succeed => {
                this.identityType(succeed, res.object[0].idCardType, res.object[0].idNumber);
              });
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    identityType(issuingCountry, idCardType, idNumber) { // 身份证明
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'cardType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          for (let i = 0; i < res.object.length; i++) {
            if (res.object[i].dictValue === idCardType) {
              idCardType = res.object[i].dictDest;
              this.partnership18.identityInformation.identificationPaper = issuingCountry + ' , ' + idCardType + ' , ' + idNumber;
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
    getSignature() { // 账户名称 - 第一页机构信息
      this.$http({
        url: '/account/accountParInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership18.identityInformation.accountName = res.object[0].institutionalName;
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
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.formError')); // $i18n - 表单验证未通过
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
        for (let i = 0; i < this.partnership18.questionnaireList.length; i++) {
          data.push({
            accountId,
            createdBy,
            createdDate,
            issue: this.partnership18.questionnaireList[i].dictValue,
            status: this.partnership18.questionnaireList[i].ifbtn,
            supplementaryInfo: this.partnership18.questionnaireList[i].ifbtn ? this.partnership18.questionnaireList[i].fillInput : null
          });
        }
      } else {
        for (let i = 0; i < this.partnership18.questionnaireList.length; i++) {
          this.newValue[i].issue = this.partnership18.questionnaireList[i].dictValue;
          this.newValue[i].status = this.partnership18.questionnaireList[i].ifbtn;
          this.newValue[i].supplementaryInfo = this.partnership18.questionnaireList[i].ifbtn ? this.partnership18.questionnaireList[i].fillInput : null;
        }
        data = this.newValue;
      }
      this.$http({
        url: '/openAccount/partnerShip/saveEighteen',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership19' });
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
      this.$verify.routerPermissions({ path: '/openAccount/partnership17' });
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
  //  #ZC-regiMain .formNape .ifBtnStyle {
  //    ul {
  //      position: relative;
  //      li {
  //        position: absolute;
  //      }
  //      li:nth-child(1) {
  //        z-index: 1;
  //      }
  //      li:nth-child(2) {
  //        left: 112px;
  //        z-index: 1;
  //      }
  //      li:nth-child(3) {
  //        width: 100%;
  //        text-indent: 228px;
  //        word-wrap : break-word;
  //      }
  //    }
  //  }
    #ZC-regiMain .formNapeContent .isBtn .istext {
      /* width: ; */
    }
</style>
