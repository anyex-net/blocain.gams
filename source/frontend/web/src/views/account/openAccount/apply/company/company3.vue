<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(3/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="company3"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="company3"
            label-width="200px"
            class="demo-ruleForm"
          >
            <div class="formContent">

              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.taxClassify.title') }}</h3> <!-- $i18n - 税收分类 -->
                  <!-- <div class="suishouTishi" @mouseenter="enter()" @mouseleave="leave()" /> -->
                  <!-- <div v-show="company3.issuishoutishi" class="suishouTishi1">
                    <p>提示文本信息</p>
                  </div> -->
                  <!-- $i18n - 出于美国所得税的目的，美国国税局要求我们获取有关 -->
                  <!-- $i18n - 状态的认证信息。这可能会影响美国所得税预扣费率且可帮助我们识别哪些人士必须提供这些认证信息。请回答以下问题来进行认证。 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.taxClassify.describe1a') }} {{ company3.gongsi1 }} {{ $t('registerOpenAccount.openAccount.common.taxClassify.describe1b') }}</p>

                </div>
                <div class="formNapeContent">
                  <!-- $i18n - 请确定 -->
                  <!-- $i18n - 被征收美国个人所得税的方式。 -->
                  <div class="zhuce3ji" style="margin-bottom:8px;">{{ $t('registerOpenAccount.openAccount.common.taxClassify.way1a') }} {{ company3.gongsi1 }} {{ $t('registerOpenAccount.openAccount.common.taxClassify.way1b') }}</div>
                  <el-form-item prop="taxType">
                    <el-radio-group v-model="company3.taxType">
                      <div v-for="(item,index) in company3.taxTypeList" :key="index">
                        <el-radio class="zhuce3ji" :label="item.dictValue" @change="company3.isdaiyu=index==0?false:true">{{ item.dictDest }}</el-radio>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
                <transition name="fade">
                  <div v-show="company3.isdaiyu" class="formNapeContent fade">
                    <div class="zhuce3ji" style="margin-bottom:8px;">{{ company3.xinxi }} {{ $t('registerOpenAccount.openAccount.common.taxClassify.daiyuIssue') }}</div> <!-- $i18n - 是否作为混合实体要求美国的税收协定优惠待遇 ？ -->
                    <el-form-item prop="daiyu">
                      <el-radio-group v-model="company3.daiyu">
                        <el-radio class="zhuce3ji" :label="false">{{ $t('registerOpenAccount.openAccount.common.taxClassify.no') }}</el-radio> <!-- $i18n - 否 -->
                        <br>
                        <el-radio class="zhuce3ji" :label="true">{{ $t('registerOpenAccount.openAccount.common.taxClassify.yes') }}</el-radio> <!-- $i18n - 是(不常见) -->

                        <br>
                      </el-radio-group>
                    </el-form-item>
                  </div>
                </transition>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('company3')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
    // 税收待遇验证-职位
    var validateDaiyu = (rule, value, callback) => {
      if (this.company3.isdaiyu === true) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          callback();
        }
      } else {
        callback();
      }
    };

    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      company3: {
        taxType: '',
        taxTypeList: [],
        issuishoutishi: false,
        gongsi1: '',
        isdaiyu: false,
        daiyu: '',
        condition: 0
      },
      formdata: {},
      newValue: {
        accountCoTaxClassification: null
      },
      rules: {
        // 机构信息
        taxType: [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        daiyu: [
          { validator: validateDaiyu, trigger: 'change' }
        ]
      }
    };
  },
  created() {
    this.taxType();
    this.accountCoInstitutional();
    this.accountCoTaxClassification();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountCoInstitutional() { // 获取公司名
      this.$http({
        url: '/account/accountCoInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.company3.gongsi1 = res.object.institutionalName;
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
    accountCoTaxClassification() { // 获取公司税收分类
      this.$http({
        url: '/account/accountCoTaxClassification/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.accountCoTaxClassification = res.object;
              this.company3.isdaiyu = res.object.isPreferentia !== '';
              this.company3.daiyu = res.object.isPreferentia;
              this.company3.taxType = res.object.taxType;
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
    // 字典表获取
    // -- 税收分类
    taxType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.company3.taxTypeList = res.object;
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
    // 移入移出
    enter() {
      this.company3.issuishoutishi = true;
    },
    leave() {
      this.company3.issuishoutishi = false;
    },
    // 验证表单
    submitForm(formName) {
      // this.$verify.routerPermissions({ path: "/openAccount/company4" });
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.submitFormData();
        } else {
          return false;
        }
      });
    },

    // 提交表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      let data = null; // 1.公司税收分类
      // newAccountCoPersonal:null,

      if (this.newValue.accountCoTaxClassification === null) { // 1.公司税收分类
        data = {
          accountId,
          createdBy,
          createdDate,
          isPreferentia: this.company3.isdaiyu ? this.company3.daiyu : null,
          taxType: this.company3.taxType
        };
      } else {
        this.newValue.accountCoTaxClassification.isPreferentia = this.company3.isdaiyu ? this.company3.daiyu : null;
        this.newValue.accountCoTaxClassification.taxType = this.company3.taxType;
        data = this.newValue.accountCoTaxClassification;
      }
      this.$http({
        url: '/openAccount/company/saveThree',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/company4' });
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
      this.$verify.routerPermissions({ path: '/openAccount/company2' });
    }
  }
};
</script>

<style scoped>
    #ZC-regiMain .formNapeTitle {
        position: relative;
    }
    #ZC-regiMain .suishouTishi {
        width: 16px;
        height: 16px;
        background: url("../../../../../assets/images/account/openAccount/icon-wenhao1.jpg") center no-repeat;
        background-size: 100%;
        position: absolute;
        left: 85px;
        top: 1px;
    }
    #ZC-regiMain .suishouTishi1 {
            width: 500px;
    height: 300px;
    background-color: rgb(255, 255, 255);
    position: absolute;
    left: 136px;
    top: -37px;
    border-radius: 5px;
    border: 1px solid rgb(226, 226, 226);
    box-shadow:5px 5px 10px rgb(228, 228, 228);
    padding: 20px;
    z-index: 1;
    }

</style>
