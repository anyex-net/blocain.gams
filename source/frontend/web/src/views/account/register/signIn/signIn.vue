<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">- {{ $t('registerOpenAccount.header.registerTitel') }} -</div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="signIn"
            autocomplete="off"
            :model="signIn"
            status-icon
            :rules="rules"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent pleaseEnter">
              <!-- 电邮地址 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.register.signIn.emailTitle') }}</h3> <!-- $i18n - 电邮地址-->
                </div>
                <div class="formNapeContent">
                  <div class="form-content">

                    <el-form-item prop="email">
                      <el-input v-model="signIn.email" maxlength="64" :placeholder="$t('registerOpenAccount.register.signIn.emailTitle')" /> <!--$i18n - 电邮地址-->
                    </el-form-item>
                    <ul class="areaCode">
                      <li>
                        <el-form-item prop="areaCode">
                          <el-select v-model="signIn.areaCode" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.register.signIn.areaCode')"> <!-- $i18n - 国际区号 -->
                            <el-option v-for="(item,index) in signIn.areaCodeList" :key="index" :label="item.lCode" :value="item.lCode">
                              <span style="float: left">+{{ item.lCode }}</span>
                              <span style="float: right;">{{ lang === 'en_US'?item.enName:item.cnName }}</span>
                            </el-option>
                          </el-select>
                        </el-form-item>
                      </li>
                      <li>
                        <el-form-item prop="mobile">
                          <el-input v-model="signIn.mobile" maxlength="11" :placeholder="$t('registerOpenAccount.register.signIn.cellPhoneNumber')" /> <!--$i18n - 手机号-->
                        </el-form-item>
                      </li>
                    </ul>

                  </div>
                </div>
              </div>
              <!-- 个人信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.register.signIn.usernameTitle') }}</h3> <!--$i18n - 注册您的用户名-->
                </div>

                <div class="formNapeContent">

                  <el-form-item prop="username" :class="{ bottom30: signIn.textisshow.textisshow1}">
                    <el-input v-model="signIn.username" maxlength="30" :placeholder="$t('registerOpenAccount.register.signIn.username')" /> <!--$i18n - 用户名-->
                  </el-form-item>

                  <el-form-item prop="psword" :class="{ bottom30: signIn.textisshow.textisshow2, percentage:true}">
                    <el-input
                      v-model="signIn.psword"
                      maxlength="16"
                      :placeholder="$t('registerOpenAccount.register.signIn.password')"
                      type="password"
                      autocomplete="off"
                    /> <!--$i18n - 密码-->
                    <div v-show="intensityStatus !== 0" class="icon-percent">{{ intensityStatus }}</div>
                  </el-form-item>

                  <el-form-item prop="psword1" :class="{ bottom30: signIn.textisshow.textisshow3}">
                    <el-input
                      v-model="signIn.psword1"
                      maxlength="16"
                      :placeholder="$t('registerOpenAccount.register.signIn.confirmPassword')"
                      type="password"
                      autocomplete="off"
                    />  <!--$i18n - 确定密码-->
                  </el-form-item>
                </div>

              </div>

              <!-- 国家 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.register.signIn.stateTitle') }}</h3>  <!--$i18n - 国家地区-->
                </div>
                <div class="formNapeContent">

                  <el-form-item prop="place" :class="{ bottom30: signIn.textisshow.textisshow4}">
                    <el-select v-model="signIn.place" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.register.signIn.stateDescribe')"> <!--$i18n - 居住国家-->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <li class="isyes" :class="{ dianji: signIn.isdwell}" @click="signIn.isdwell=true">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!--$i18n - 是-->
                      <li class="isno" :class="{ dianji: !signIn.isdwell}" @click="signIn.isdwell=false">{{ $t('registerOpenAccount.common.btn.no') }}</li> <!--$i18n - 否-->
                      <li class="istext">{{ $t('registerOpenAccount.register.signIn.stateBTDescribe') }}</li>  <!--$i18n - 永久居住国家是否与上方不同 ?-->
                    </ul>
                  </div>
                  <el-form-item v-show="signIn.isdwell" prop="place1">
                    <el-select v-model="signIn.place1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.register.signIn.nameHint')"> <!--$i18n - 永久居住国家是否与上方不同-->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <el-dialog :title="$t('registerOpenAccount.register.openType.hint')" :visible.sync="dialogVisible" width="30%"> <!-- $i18n - 提示 -->
                <span style="font-size:18px;color:#FF5949">{{ $t('registerOpenAccount.register.signIn.mailboxActivationTips') }}</span>
                <span slot="footer" class="dialog-footer" />
              </el-dialog>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button
                      v-loading.fullscreen.lock="pageRequestLoading"
                      element-loading-text="拼命加载中"
                      element-loading-background="rgba(0, 0, 0, 0.8)"
                      class="right-btn BTbtn"
                      plain
                      @click="submitForm('signIn')"
                    >
                      {{ $t('registerOpenAccount.footer.btnYes') }} <!--$i18n - 保存&继续-->
                    </el-button>
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
import dictionary from '../../../../components/dictionary/dictionary';
export default {
  name: 'SignIn',

  components: {
    dictionary
  },
  data() {
    // 自定义校验
    var validateUsername = (rule, value, callback) => {
      const _this = this;
      this.$verify.Username(rule, value, callback, _this);
    };
    var validatePass = (rule, value, callback) => {
      const mate = this.signIn.username;
      this.$verify.Psword(rule, value, mate, callback).then(res => {
        callback();
        this.$refs.signIn.validateField('psword1');
        if (res === 1) {
          this.intensityStatus = this.$i18n.t('registerOpenAccount.common.psdStreng.weak');
        } else if (res === 2) {
          this.intensityStatus = this.$i18n.t('registerOpenAccount.common.psdStreng.centre');
        } else if (res === 3) {
          this.intensityStatus = this.$i18n.t('registerOpenAccount.common.psdStreng.strong');
        }
      }).catch(error => {
        this.isintensityStatus = error;
      });
    };
    var validatePass2 = (rule, value, callback) => {
      const mate = this.signIn.psword;
      this.$verify.PswordT(rule, value, mate, callback);
    };
    var validateEmail = (rule, value, callback) => {
      const _this = this;
      this.$verify.Email(rule, value, callback, _this);
    };
    var validateareaCode = (rule, value, callback) => {
      if (value !== '') {
        callback();
        if (this.signIn.mobile) {
          this.$refs.signIn.validateField('mobile');
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
      }
    };
    var validateIsplace = (rule, value, callback) => {
      if (value === '') {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.chooseState'))); // $i18n - 请选择国家
      } else {
        this.$verify.notChinese(rule, value, callback);
      }
    };
    var validatePlace1 = (rule, value, callback) => {
      if (this.signIn.isdwell) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.chooseState'))); // $i18n - 请选择国家
        } else {
          if (this.signIn.place === this.signIn.place1) {
            callback(new Error(this.$i18n.t('registerOpenAccount.register.signIn.placeOfAbode'))); // $i18n - 居住地点请不要和上方相同
          } else {
            callback();
          }
        }
      } else {
        callback();
      }
    };
    var validateMobile = (rule, value, callback) => {
      const _this = this;
      this.$verify.cellPhoneNumber(rule, value, callback, _this, this.signIn.areaCode);
    };
    return {
      lang: this.$Cookies.get('locale'),
      pageRequestLoading: false,
      intensityStatus: 0,
      dialogVisible: false,
      signIn: {
        // 问题提示显示隐藏
        textisshow: {
          textisshow1: false,
          textisshow2: false,
          textisshow3: false,
          textisshow4: false
        },
        username: '',
        psword: '',
        psword1: '',
        email: '',
        areaCode: '',
        areaCodeList: [],
        mobile: '',
        place: '',
        isplace: '',
        place1: '', // 和上面居住地不一样 如果有的话
        dictionaryId: this.$route.query.accountType,
        isstate: {
          iszhanghutishi: false
        },
        isdwell: false
      },

      rules: {
        username: [{ validator: validateUsername, trigger: 'blur' }],
        psword: [{ validator: validatePass, trigger: 'blur' }],
        psword1: [{ validator: validatePass2, trigger: 'blur' }],
        email: [{ validator: validateEmail, trigger: 'blur' }],
        areaCode: [{ validator: validateareaCode, trigger: 'change' }],
        mobile: [{ validator: validateMobile, trigger: 'blur' }],
        place: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.chooseState')}`, trigger: 'change' }], // $i18n - 请选择国家
        isplace: [{ validator: validateIsplace, trigger: 'change' }],
        place1: [{ validator: validatePlace1, trigger: 'change' }]
      }
    };
  },
  created() {
    this.getAreaCodeList();
  },
  mounted() {
  },
  methods: {
    // 获取区号字典表
    getAreaCodeList() {
      this.$http({
        url: '/common/region/findAll',
        method: 'get',
        params: { dictCode: 'region', lang: this.$Cookies.get('locale'), IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          this.signIn.areaCodeList = res.object;
        }
      });
    },
    // 整个表单提交验证
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.submitFormData();
        } else {
          return false;
        }
      });
    },
    submitFormData() {
      this.pageRequestLoading = true;
      const data = {
        accountName: this.signIn.username,
        accountType: this.$route.query.typeID,
        country: this.signIn.place,
        mobile: this.signIn.mobile,
        email: this.signIn.email,
        region: this.signIn.areaCode,
        loginPwd: this.signIn.psword,
        nationality: this.signIn.isdwell ? this.signIn.place1 : null
      };
      this.$http({
        url: '/register/createAccount',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.pageRequestLoading = false;
            this.dialogVisible = true;
            this.delayedRequest();
          } else {
            this.pageRequestLoading = false;
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.pageRequestLoading = false;
          this.$message.error(error.message || 'Has Error');
        });
    },
    delayedRequest() {
      let timingTime = 6;
      const timerId = setInterval(() => {
        timingTime--;
        if (timingTime === 0) {
          this.dialogVisible = false;
          clearInterval(timerId);
          this.$router.push({ path: '/' });
        }
      }, 500);
    }
  }
};
</script>

<style lang="scss" scoped>
  .areaCode {
    overflow: hidden;
    display: flex;
    overflow: hidden;
    li:nth-child(1) {
      width: 140px;
      margin-right: 10px;
    }
    li:nth-child(2) {
      flex: 1;
    }
  }
</style>

