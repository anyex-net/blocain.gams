<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(14/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="company16"
            autocomplete="off"
            status-icon
            :model="company16"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.integration.title') }}</h3> <!-- $i18n - 一体化投资管理账户功能 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.integration.describe') }}</p> <!-- $i18n - 为您的账户添加以下选项，以更好地利用您的经纪商 ... -->
                </div>
                <div class="formNapeContent">
                  <div class="show-title">
                    <div class="register-plan-img" style="margin-top: 15px;" />
                    <div style="margin-left: 274px;;"><h4 class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.integration.minTitle') }}</h4> <!-- $i18n - 股票收益增长计划 -->
                      <div class="title-centent zhuce3ji">
                        {{ $t('registerOpenAccount.openAccount.common.integration.minDescribe') }}
                      </div> <!-- $i18n - 国瑞券的股票收益提升计划可帮助 ... -->
                      <!-- 点击弹出模态框 -->
                      <!-- <div class="gengduo" style="line-height: 20px;"><a href="https://www.interactivebrokers.com.hk/cn/index.php?f=14775&conf=am#sf_stockYield" target="_Blank">更多信息</a></div> -->
                      <div class="participation">
                        <!-- <div class="isimg"></div> -->
                        <span class="zhuce2ji">{{ company16.state1 }}</span>
                      </div></div>
                  </div>
                </div>
              </div>

              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('company16')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      company16: {
        state1: '',
        status: false
      }
    };
  },
  created() {
    this.accountCoInvestmentAccount();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    accountCoInvestmentAccount() {
      this.$http({
        url: '/account/accountCoInvestmentAccount/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object[0];
              if (res.object[0].isStockIncomeGrowthPlan === true) {
                this.company16.state1 = this.$i18n.t('registerOpenAccount.openAccount.common.integration.hasAgreed'); // $i18n - 已参与
                this.company16.status = true;
              } else {
                this.company16.state1 = this.$i18n.t('registerOpenAccount.openAccount.common.integration.noHasAgreed'); // $i18n - 未参与
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
    submitForm() {
      this.submitFormData();
      // if (this.company16.status) {
      //   this.submitFormData();
      // } else {
      //   this.$message.error('请返回上一页参与计划');
      // }
    },
    submitFormData() {
      this.$http({
        url: '/openAccount/company/saveSixteen',
        method: 'post',
        params: { accountId: this.$store.state.user.applyData.xinxi.id, lang: this.lang }
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/company17' });
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
      this.$verify.routerPermissions({ path: '/openAccount/company15' });
    }
  }
};
</script>

<style scoped>

h4 {
  padding-left: 10px;
  font-size: 16px;
  line-height: 44px;
}
.show-title {
    margin: 30px 0px 30px 0px ;
    position: relative;

}
.show-title .register-plan-img {
    width: 257px;
    height: 176px;
   background: url("../../../../../assets/images/account/openAccount/features-syep.jpg") no-repeat;
    background-size: 100%;
    float: left;
    margin-top: 20px;
}
.show-title h4 {
  padding-left: 0px;
}
.show-title .title-centent {
  width: 500px;
}
.show-title .participation  {
    position: absolute;
    right: 55px;
    top: 48px;

    color: #666;
    text-align: center;
}
.show-title .participation .isimg {
  cursor: pointer;
  width: 20px;
  height: 20px;
  border: 2px solid rgb(158, 158, 158);
  margin-left: 3px;
  margin-bottom: 5px;
}
.show-title .gengduo {
    border-radius: 2px;
    width: 55px;
    height: 22px;
    background-color: rgb(77, 110, 157);
    color: rgb(255, 255, 255);
    font-size: 12px;
    text-align: center;
    margin-top: 10px;
}
.show-title .gengduo a {
  color: rgb(255, 255, 255);
}
.show-title .gengduo:hover {
    cursor: pointer;
    background-color: rgb(0, 0, 0);
}
.show-title .participation .isimg:hover {
  border-color: #A02200;
}

.form-content .show-textcontent h4 {
    color: #4D6E9D;
    padding: 0;
}
.form-content .show-textcontent .publictext{
    width: 100%;
    height: 400px;
    border: 1px solid #000;
    margin-bottom: 10px;
}

.form-content .iscontent {
  margin: 20px;
}
.form-content .iscontent > p {
  font-size: 12px;
}
.form-content .iscontent > p:nth-child(2) {
  color: #4d6e9d;
  cursor: pointer;

}
</style>

