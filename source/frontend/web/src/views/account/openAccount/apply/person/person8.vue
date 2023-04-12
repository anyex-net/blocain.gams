<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(8/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person8"
            autocomplete="off"
            status-icon
            :model="person8"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.verificationRequest.title') }}</h3> <!-- $i18n - 申请审核 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.verificationRequest.describe') }}</p> <!-- $i18n - 请在下方检查您的账户信息。一旦点击“保存并 ... -->
                </div>
                <div class="formNapeContent" />
              </div>
              <!-- 个人信息 -->
              <div class="formNape">
                <div class="formNapeTitle bianjifuBOX" style="height: 12px;">
                  <h3 style="  font-weight: 200;color: rgb(35, 35, 35);font-size:18px;margin-bottom: -9px;" class="left1">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.applyMessage') }}</h3> <!-- $i18n - 申请信息 -->
                  <!-- <div @click="compile('person1')" class="right1 bianji xiaoshou">编辑</div> -->
                </div>
                <div class="formNapeContent">
                  <!-- 渲染内容 -->
                  <div>
                    <div class="form-content">
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.clientType') }}&nbsp;&nbsp;</span> <!-- $i18n - 客户类型: -->
                        <span class="rightCN-EN">{{ person8.kehuleixing }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.username') }}&nbsp;&nbsp;</span> <!-- $i18n - 用户名: -->
                        <span class="rightCN-EN">{{ person8.username }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.accountName') }}&nbsp;&nbsp;</span> <!-- $i18n - 账户名称: -->
                        <span class="rightCN-EN">{{ person8.fullName }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.site') }}&nbsp;&nbsp;</span> <!-- $i18n - 地址: -->
                        <span class="rightCN-EN">{{ person8.dizhi }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.hefaSite') }}&nbsp;&nbsp;</span> <!-- $i18n - 合法居住地 -->
                        <span class="rightCN-EN">{{ person8.juzhudi }}</span>
                      </el-form-item>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 资产与收入 -->
              <div class="formNape">
                <div class="formNapeTitle bianjifuBOX">
                  <h3 style="  font-weight: 200;color: rgb(35, 35, 35);font-size:18px;margin-bottom: -9px;">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.assetsIncome') }}</h3> <!-- $i18n - 资产与收入 -->
                  <!-- <div @click="compile('person2')" class="right1 bianji xiaoshou">编辑</div> -->
                </div>
                <div class="formNapeContent">
                  <div>
                    <div class="form-content">
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.netAsset') }}&nbsp;&nbsp;</span> <!-- $i18n - 净资产: -->
                        <span class="rightCN-EN">{{ person8.jingzichang }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.netCurrentAssets') }}&nbsp;&nbsp;</span> <!-- $i18n - 净流动资产: -->
                        <span class="rightCN-EN">{{ person8.liudong }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.netAnnualIncome') }}&nbsp;&nbsp;</span> <!-- $i18n - 净年收入: -->
                        <span class="rightCN-EN">{{ person8.yearShouru }}</span>
                      </el-form-item>
                      <el-form-item class="audit">
                        <span class="leftCN" :class="{ leftEN: lang === 'en_US' }">{{ $t('registerOpenAccount.openAccount.common.verificationRequest.investmentObjective') }}&nbsp;&nbsp;</span> <!-- $i18n - 投资目标: -->
                        <span class="rightCN-EN">{{ person8.InvestmentTarget + '' }}</span>
                      </el-form-item>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('person4')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      isTokenHint: false,
      lang: this.$Cookies.get('locale'),
      person8: {
        // 申请信息
        kehuleixing: '',
        username: '',
        userhaoma: '',
        fullName: '',
        dizhi: '',
        juzhudi: '',
        // 资产与收入
        jingzichang: '',
        liudong: '',
        yearShouru: '',
        InvestmentTarget: [],
        InvestmentTargetList: []
      },
      formdata: {}
    };
  },
  created() {
    this.accountType();
    this.getPersonalDetails();
    this.getPersonalAddress();
    this.getProperty();
    this.getInvestmentTargetType();
    this.person8.username = this.$store.state.user.applyData.xinxi.accountName;
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的数据
    accountType() {
      this.$http({ // 账户类型
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'accountType',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            this.person8.kehuleixing = res.object[0].dictDest;
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
    getPersonalDetails() { // 获取个人信息
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
            this.person8.fullName = res.object[0].lastName + ' ' + res.object[0].firstName; // 账户名称
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
    getPersonalAddress() { // 获取个人地址信息
      const _this = this;
      this.$http({
        url: '/account/accountPerAddress/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') { // 个人地址类型
                  let address = '';
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    address = dizhi[0] + ' ' + dizhi[1];
                  } else {
                    address = res.object[i].address;
                  }
                  this.$verify.State1(res.object[i].country, _this, this.lang).then(country => {
                    this.person8.dizhi = address + ' ' + res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode + ' ' + country;
                  });
                  this.$verify.State1(this.$store.state.user.applyData.xinxi.country, _this, this.lang).then(res => {
                    this.person8.juzhudi = res;
                  });
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
    getProperty() { // 收入和资产值
      this.$http({
        url: '/account/accountPerAsset/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person8.jingzichang = res.object[0].anualNetIncome + ' ' + 'USD';
              this.person8.liudong = res.object[0].netAssetValue + ' ' + 'USD';
              this.person8.yearShouru = res.object[0].netCurrentAssets + ' ' + 'USD';
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
    getInvestmentObjective() { // 获取投资目标
      this.$http({
        url: '/account/accountPerInvestTarget/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].investmentTarget.indexOf(',') !== -1) {
                const dataTS = res.object[0].investmentTarget.split(',');
                for (let i = 0; i < dataTS.length; i++) {
                  for (let j = 0; j < this.person8.InvestmentTargetList.length; j++) {
                    if (dataTS[i] === this.person8.InvestmentTargetList[j].dictValue) {
                      this.person8.InvestmentTarget.push(this.person8.InvestmentTargetList[j].dictDest);
                    }
                  }
                }
              } else {
                for (let j = 0; j < this.person8.InvestmentTargetList.length; j++) {
                  if (res.object[0].investmentTarget === this.person8.InvestmentTargetList[j].dictValue) {
                    this.person8.InvestmentTarget.push(this.person8.InvestmentTargetList[j].dictDest);
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
    // 获取字典表
    // 投资目标类型
    getInvestmentTargetType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'investmentTargetType',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person8.InvestmentTargetList = res.object;
              this.getInvestmentObjective();
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
    // 保存继续
    submitForm() {
      this.submitFormData();
    },
    // 提交的表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      this.$http({
        url: '/openAccount/personal/saveEight',
        method: 'post',
        params: { accountId, lang: this.lang }
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person9' });
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
      this.$verify.routerPermissions({ path: '/openAccount/person8' });
    },
    beforepage() {
      this.getOneState();
    },
    getOneState() { // 根据第一页国家进行跳转
      this.$http({
        url: '/account/accountPerAddress/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            for (let i = 0; i < res.object.length; i++) {
              if (res.object[i].addressType === 'personalAddress') {
                this.$verify.routerPermissions({ path: '/openAccount/person7' }); // 暂时所有国家都可以到crs
                // if (res.object[i].country === 'CN') {
                //   this.$verify.routerPermissions({ path: '/openAccount/person7' });
                // } else {
                //   this.$verify.routerPermissions({ path: '/openAccount/person6' });
                // }
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
    }
  }
};
</script>

<style scoped>
.form-content span {
  color: rgb(184, 184, 184);
}

/* 编辑 页面跳转 */
#bodys1 .formContent .formNape .formNapeTitle.bianjifuBOX {
  position: relative;
}
#bodys1 .formContent .formNape .formNapeTitle.bianjifuBOX .bianji{
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
#ZC-regiMain .el-form-item {
    margin-bottom: 0px;
}
</style>

