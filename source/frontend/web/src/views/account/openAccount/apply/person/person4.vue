<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(4/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person4"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="person4"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- 协议与披露声明-->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.AgreementDisclose.title') }}</h3> <!-- $i18n - 协议 披露 -->
                <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="form-content">

                    <div v-for="(item,index) in person4.agreement" :key="index">
                      <div class="agreement">
                        <div class="agreement-top xiaoshou" @click="item.ispublish1=!item.ispublish1">
                          <div class="left20 zhuce2ji JYxialaanniu" />
                          <div class="zhuce2ji ">{{ lang === 'en_US'?item.enName:item.cnName }}</div>
                        </div>
                        <div v-show="item.ispublish1" class="iscontent left20 top20">
                          <p class="zhuce3ji">{{ item.agreementDescription }}</p>
                          <p class="zhuce3ji">
                            <a
                              class="piludizhi"
                              :href="item.path"
                              target="_Blank"
                            >{{ lang === 'en_US'?item.enName:item.cnName }}</a>
                          </p>
                        </div>
                      </div>
                      <div class="isBtn marginbottom1">
                        <ul>
                          <li class="isyes" :class="{ dianji: item.isBtnStyle}" @click="isBtnT(index)">{{ $t('registerOpenAccount.common.btn.consent') }}</li> <!-- $i18n - 同意 -->
                          <li class="isno" :class="{ dianji: !item.isBtnStyle}" @click="isBtnF(index)">{{ $t('registerOpenAccount.common.btn.noConsent') }}</li> <!-- $i18n - 不同意 -->
                        </ul>
                        <div v-show="item.isconsent" class="CORred">{{ $t('registerOpenAccount.openAccount.common.AgreementDisclose.AgreedContinue') }}</div> <!-- $i18n - 同意才能继续操作 -->
                      </div>
                    </div>

                    <!-- 签名 -->
                    <div>
                      <h4 class="zhuce2ji" style="padding-left: 0px;">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</h4> <!-- $i18n - 签名 -->
                      <div
                        class="zhuce3ji"
                      >{{ $t('registerOpenAccount.openAccount.common.signature.signatureDB') }}</div> <!-- $i18n - 输入签名并通过网络发送即代表您(i)确 ... -->
                      <div class="zhuce2ji">
                        <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</span> <!-- $i18n - 账户名称: -->
                        {{ person4.shengming.zhanghuName }}
                        <br>
                        <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</span> <!-- $i18n - 日期: -->
                        {{ person4.shengming.riqi }}
                      </div>
                    </div>
                    <div class="signature" style="margin-top: 60px;">
                      <ul class="bottom10 clearfix">
                        <li class="left1 right10">{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                        <li class="left1">{{ person4.shengming.signatureZhanshi }}</li>
                      </ul>
                      <el-form-item class="zhuce3ji" label="">
                        <el-input v-model="person4.shengming.signature" maxlength="128" />
                      </el-form-item>
                      <div v-show="person4.qianming" class="CORred">{{ $t('registerOpenAccount.common.formPrompt.signatureHint') }}</div> <!-- $i18n - 签名与上方不一致 或 为空 -->
                    </div>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('person4')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      person4: {
        qianming: false,
        agreement: [
          // {
          //   // agreementDescription: "该文件论述了日内交易的固有风险。 "
          //   // agreementNumber: "fg01"
          //   // cnName: "日内交易风险披露声明"
          //   // enName: "日内交易风险披露声明"
          //   // id: "20000000001"
          //   // path: "https:/

          //   // ispublish1: false, // 链接content内容显示隐藏
          //   // isconsent: false, // 错误提示信息
          //   // isBtnStyle: true // btn按钮样式控制
          // },
        ],
        shengming: {
          zhanghuName: '',
          riqi: '',
          signatureZhanshi: '',
          signature: ''
        }
      },
      newValue: null,
      rules: {

      }
    };
  },
  watch: {
    person4: {// 深度监听，签名提示框显示隐藏
      handler(val, oldVal) {
        if (this.person4.shengming.signature === this.person4.shengming.signatureZhanshi) {
          this.person4.qianming = false;
        } else {
          this.person4.qianming = true;
        }
      },
      deep: true
    }
  },
  created() {
    this.getSignature();
    this.getPageOneinformation();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    getinfo() { // 获取用户已填入数据
      this.$http({
        url: '/account/accountPerAgreement/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object;
              if (res.object.length > 0) {
                for (let i = 0; i < res.object.length; i++) {
                  this.person4.agreement[i].isBtnStyle = res.object[i].status;
                  this.person4.shengming.signature = res.object[i].signature;
                  this.person4.shengming.riqi = this.$verify.NowDate3();
                }
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
          this.$message.error(error || 'Has Error');
        });
    },
    getPageOneinformation() { // 披露协议获取字典表
      this.$http({
        url: '/openAccount/personal/getAgreement',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            for (let i = 0; i < res.object.length; i++) {
              res.object[i].ispublish1 = false;
              res.object[i].isconsent = false;
              res.object[i].isBtnStyle = true;
              this.person4.agreement.push(
                JSON.parse(JSON.stringify(res.object[i]))
              );
            }
            // this.getinfo();
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
            if (res.object !== undefined) {
              // 姓名
              this.person4.shengming.zhanghuName = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person4.shengming.signatureZhanshi = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person4.shengming.signature = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person4.shengming.riqi = this.$verify.NowDate3();
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
    // 是否点击事件
    isBtnT(index) {
      this.person4.agreement[index].isconsent = false;
      this.person4.agreement[index].isBtnStyle = true;
    },
    isBtnF(index) {
      this.person4.agreement[index].isconsent = true;
      this.person4.agreement[index].isBtnStyle = false;
    },

    // 提交
    submitForm() {
      let index = 0;
      for (let i = 0; i < this.person4.agreement.length; i++) {
        if (this.person4.agreement[i].isBtnStyle === false) {
          this.person4.agreement[i].isconsent = true;
        } else {
          index++;
        }
      }
      if (index === this.person4.agreement.length) {
        if (this.person4.qianming === false) {
          this.submitFormData();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.EnterSignature')); // $i18n - 请填写
        }
      } else {
        this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint1')); // $i18n - 同意才能继续
      }
    },
    submitFormData() {
      const updatedBy = this.$store.state.user.applyData.xinxi.id; // 账户id  更新人
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id; // 账户id  创建人
      const accountName = this.$store.state.user.applyData.xinxi.accountName; // 账户名
      const createdDate = this.$verify.NowDate(); // 创建时间
      let data = [];
      if (this.newValue === null) {
        for (let i = 0; i < this.person4.agreement.length; i++) {
          data.push({
            accountId,
            accountName,
            createdBy,
            createdDate,
            updatedBy,
            status: this.person4.agreement[i].isBtnStyle,
            agreementNumber: this.person4.agreement[i].agreementNumber,
            signature: this.person4.shengming.signature,
            signatureTime: this.$verify.NowDate(this.person4.shengming.riqi)
          });
        }
      } else {
        for (let i = 0; i < this.person4.agreement.length; i++) {
          this.newValue[i].accountName = accountName;
          this.newValue[i].agreementNumber = this.person4.agreement[i].agreementNumber;
          this.newValue[i].signature = this.person4.shengming.signature;
          this.newValue[i].status = this.person4.agreement[i].isBtnStyle;
          this.newValue[i].signatureTime = this.$verify.NowDate(this.person4.shengming.riqi);
        }
        data = this.newValue;
      }
      // 数据请求
      this.$http({
        url: '/openAccount/personal/saveFour',
        method: 'put',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person5' });
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
          this.$message.error(error || 'Has Error');
        });
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/person3' });
    }
  }
};
</script>

<style  scoped>
.form-content.deal {
  padding: 20px;
  border: 1px solid #000;
}
.form-content .title-bd {
  background-color: #fcfcfc;
}

.form-content .panel {
  border: 1px solid #cccccc;
}
h4 {
  cursor: pointer;
  padding-left: 10px;

  font-size: 16px;
  line-height: 44px;
}
.form-content .iscontent {
  margin: 20px;
}
.form-content .iscontent > p {
  font-size: 12px;
}
.form-content .iscontent a {
  color: #4d6e9d;
  cursor: pointer;
}

/* 表单label 设置 */
.form-content div:nth-child(n + 2) .el-form-item__label {
  line-height: 22px;
}
.form-content div.signature .el-form-item__label {
  line-height: 120px;
}
.el-form-item {
  margin-left: 0px;
}
</style>

