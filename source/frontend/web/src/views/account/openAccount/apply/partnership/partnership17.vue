<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(15/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership17"
            autocomplete="off"
            status-icon
            :model="partnership17"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.verificationRequest.title') }}</h3> <!-- $i18n - 申请审核 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.verificationRequest.describe') }}</p> <!-- $i18n - 请在下方检查您的账户信息。一旦点击“保存并继续”，您的申请便将提交，届时您便只能通过致电客户服务进行更改。 -->
                </div>
                <div class="formNapeContent" />
              </div>
              <!-- 个人信息 -->
              <div class="formNape">
                <div class="formNapeTitle bianjifuBOX" style="height: 27px;">
                  <h3 style="  font-weight: 200;color:#666;font-size:18px;margin-bottom: -9px;" class="left1">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.applyMessage') }}</h3> <!-- $i18n - applyMessage -->
                <!-- <div @click="compile('company1')" class="right1 bianji xiaoshou">编辑</div> -->
                </div>
                <div class="formNapeContent" style="margin-top:0px;">
                  <!-- 渲染内容 -->
                  <div>
                    <div class="form-content">
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.clientType') }}&nbsp;&nbsp;</span> <!-- $i18n - 客户类型: -->
                        <span class="rightCN-EN">{{ partnership17.kehuleixing }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.username') }}&nbsp;&nbsp;</span> <!-- $i18n - 用户名: -->
                        <span class="rightCN-EN">{{ partnership17.username }}</span>
                      </el-form-item>
                      <!-- <el-form-item>
                      <span style="folat:left;color: #606266;margin-left: 21px;">账户号码:&nbsp&nbsp</span>
                      <span>{{partnership17.userhaoma}}</span>
                    </el-form-item> -->
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.accountName') }}&nbsp;&nbsp;</span> <!-- $i18n - 账户名称: -->
                        <span class="rightCN-EN">{{ partnership17.fullName }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.site') }}&nbsp;&nbsp;</span> <!-- $i18n - 地址: -->
                        <span class="rightCN-EN">{{ partnership17.dizhi }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.hefaSite') }}&nbsp;&nbsp;</span> <!-- $i18n - 合法居住地 -->
                        <span class="rightCN-EN">{{ partnership17.juzhudi }}</span>
                      </el-form-item>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 资产与收入 -->
              <div class="formNape">
                <div class="formNapeTitle bianjifuBOX" style="height: 27px;">
                  <h3 style="  font-weight: 200;color:#666;font-size:18px;margin-bottom: -9px;">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.assetsIncome') }}</h3> <!-- $i18n - 资产与收入 -->
                <!-- <div @click="compile('company7')" class="right1 bianji xiaoshou">编辑</div> -->
                </div>
                <div class="formNapeContent" style="margin-top:0px;">
                  <div>
                    <div class="form-content">
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.netAsset') }}&nbsp;&nbsp;</span> <!-- $i18n - 净资产: -->
                        <span class="rightCN-EN">{{ partnership17.netAssetValue }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.netCurrentAssets') }}&nbsp;&nbsp;</span> <!-- $i18n - 净流动资产: -->
                        <span class="rightCN-EN">{{ partnership17.netCurrentAssets }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.netAnnualIncome') }}&nbsp;&nbsp;</span> <!-- $i18n - 净年收入: -->
                        <span class="rightCN-EN">{{ partnership17.anualNetIncome }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.investmentObjective') }}&nbsp;&nbsp;</span> <!-- $i18n - 投资目标: -->
                        <span class="rightCN-EN">{{ partnership17.InvestmentTarget + '' }}</span>
                      </el-form-item>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('partnership17')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      partnership17: {
        // 申请信息
        kehuleixing: '',
        username: '',
        userhaoma: '',
        fullName: '',
        dizhi: '',
        juzhudi: '',

        // 资产与收入
        netAssetValue: '',
        netCurrentAssets: '',
        anualNetIncome: '',
        InvestmentTarget: [],
        InvestmentTargetList: []
      },
      newValue: null
    };
  },
  created() {
    this.personalInformation();
    this.accountType();
    this.getSignature();
    this.accountCoAddress();
    this.accountCoAsset();
    this.investmentTargetType();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取个人信息
    personalInformation() { // 客户类型
      this.partnership17.username = this.$store.state.user.applyData.xinxi.accountName;
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
              this.partnership17.fullName = res.object[0].institutionalName;
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
    accountCoAddress() { // 地址 合法国家
      const _this = this;
      this.$http({
        url: '/account/accountParAddress/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              const dizhiTS = `${res.object[0].address.replace(/-#-/g, ' ')} ${res.object[0].rovince}  ${res.object[0].city}  ${res.object[0].zipCode}`;
              this.$verify.State1(res.object[0].country, _this, this.lang).then(res => {
                this.partnership17.dizhi = dizhiTS + ' ' + res;
              });
              this.$verify.State1(this.$store.state.user.applyData.xinxi.country, _this, this.lang).then(res => {
                this.partnership17.juzhudi = res;
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
    accountCoAsset() { // 收入和资产值
      this.$http({
        url: '/account/accountParAsset/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership17.anualNetIncome = res.object[0].anualNetIncome + ' ' + 'USD';
              this.partnership17.netAssetValue = res.object[0].netAssetValue + ' ' + 'USD';
              this.partnership17.netCurrentAssets = res.object[0].netCurrentAssets + ' ' + 'USD';
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
    accountCoInvestmentTarget() { // 公司投资目标信息
      this.$http({
        url: '/account/accountParInvestmentTarget/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].investmentTarget.indexOf(',') !== -1) {
                const dataTS = res.object[0].investmentTarget.split(',');
                for (let i = 0; i < dataTS.length; i++) {
                  for (let j = 0; j < this.partnership17.InvestmentTargetList.length; j++) {
                    if (dataTS[i] === this.partnership17.InvestmentTargetList[j].dictValue) {
                      this.partnership17.InvestmentTarget.push(this.partnership17.InvestmentTargetList[j].dictDest);
                    }
                  }
                }
              } else {
                for (let j = 0; j < this.partnership17.InvestmentTargetList.length; j++) {
                  if (res.object[0].investmentTarget === this.partnership17.InvestmentTargetList[j].dictValue) {
                    this.partnership17.InvestmentTarget.push(this.partnership17.InvestmentTargetList[j].dictDest);
                  }
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
          this.$message.error('' + error + '');
        });
    },
    // 投资目标类型
    investmentTargetType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'investmentTargetType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership17.InvestmentTargetList = res.object;
              this.accountCoInvestmentTarget();
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
    // 获取账户类型-客户类型
    accountType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'accountType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            for (let i = 0; i < res.object.length; i++) {
              if (res.object[i].dictValue === 'limitedLiablity') {
                this.partnership17.kehuleixing = res.object[i].dictDest;
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
    // 编辑页面回退
    compile(location) {
      const path = '/openAccount/' + location;
      this.$verify.routerPermissions({ path });
    },
    submitForm() {
      this.submitFormData();
    },
    // 表单数据提交
    submitFormData() {
      this.$http({
        url: '/openAccount/partnerShip/saveSeventeen',
        method: 'post',
        params: { accountId: this.$store.state.user.applyData.xinxi.id, lang: this.lang }
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership18' });
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
      this.$verify.routerPermissions({ path: '/openAccount/partnership16' });
    }
  }
};
</script>

<style scoped>
#ZC-regiMain  .el-form-item {
  margin-bottom: 0px;
}
.form-content span {
  color: rgb(184, 184, 184);
}
/* 编辑 页面跳转 */
#ZC-regiMain .formContent .formNape .formNapeTitle.bianjifuBOX {
  position: relative;
}
#ZC-regiMain .formContent .formNape .formNapeTitle.bianjifuBOX .bianji{
  position: absolute;
  width: 64px;
  height: 40px;
  background-color: #E7E7E7;
  text-align: center;
  line-height: 40px;
  border-radius: 5px;
  color: #999;
  right: 0;
  top: 0;
  z-index: 1;
}
</style>

