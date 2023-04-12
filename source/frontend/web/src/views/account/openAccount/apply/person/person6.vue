<template>
  <div>
    <div id="ZC-regiMain" style="overflow-y:scroll;overflow-x: hidden;">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(6/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person6"
            autocomplete="off"
            status-icon
            :model="person6"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.integration.title') }}</h3> <!-- $i18n - 一体化投资管理账户功能 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.integration.describe') }}</p> <!-- $i18n - 为您的账户添加以下选项，以更好地利用您的经 ... -->
                </div>
                <div class="formNapeContent">
                  <div class="show-title">
                    <div class="register-plan-img" style="margin-top: 15px;" />
                    <div style="margin-left: 274px;;"><h4 class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.integration.minTitle') }}</h4> <!-- $i18n - 股票收益增长计划 -->
                      <div class="title-centent zhuce3ji">
                        {{ $t('registerOpenAccount.openAccount.common.integration.minDescribe') }}
                      </div> <!-- $i18n - 国瑞券的股票收益提升计划可帮助您创造额 ... -->
                      <!-- 点击弹出模态框 -->
                      <!-- <div class="gengduo" style="line-height: 20px;"><a href="https://www.interactivebrokers.com.hk/cn/index.php?f=14775&conf=am#sf_stockYield" target="_Blank">更多信息</a></div> -->
                      <div class="participation">
                        <!-- <div class="isimg"></div> -->
                        <span class="zhuce2ji">{{ person6.state1 }}</span>
                      </div></div>
                  </div>
                </div>
              </div>

              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('person6')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      person6: {
        state1: '',
        status: false
      }
    };
  },
  created() {
    this.getinfo();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入数据-更新
    getinfo() {
      this.$http({
        url: '/account/accountPerInvestAccount/findByAccountId',
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
                if (res.object[i].stockIncomeGrowthPlan === true) {
                  this.person6.status = res.object[i].stockIncomeGrowthPlan;
                  this.person6.state1 = this.$i18n.t('registerOpenAccount.openAccount.common.integration.hasAgreed'); // $i18n - 已同意
                } else {
                  this.person6.state1 = this.$i18n.t('registerOpenAccount.openAccount.common.integration.noHasAgreed'); // $i18n - 未同意
                  this.person6.status = res.object[i].stockIncomeGrowthPlan;
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
    submitForm() {
      this.submitFormData();
    },
    submitFormData() {
      // 数据请求
      this.$http({
        url: '/openAccount/personal/saveSix',
        method: 'post',
        params: { accountId: this.$store.state.user.applyData.xinxi.id, lang: this.lang }

      })
        .then(res => {
          if (res.code === 200) {
            this.getOneState();
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
    getOneState() {
      this.$verify.routerPermissions({ path: '/openAccount/person7' }); // 暂时所有国家都可以到crs
      // 根据国家 判断 是否显示crs
      // if (this.$store.state.user.applyData.xinxi.country === 'CN') {
      //   this.$verify.routerPermissions({ path: '/openAccount/person7' });
      // } else {
      //   this.$verify.routerPermissions({ path: '/openAccount/person8' });
      // }
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/person5' });
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

