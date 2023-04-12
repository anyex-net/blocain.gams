<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(8/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="company10"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="company10"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.security.title') }}</h3> <!-- $i18n - 安全官 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <p
                    class="zhuce3ji"
                  >{{ $t('registerOpenAccount.openAccount.common.security.describe') }}</p> <!-- $i18n - 作为账户申请人，您是账户的指定安全官。安全官 ... -->
                  <div class="shuliang">
                    <div class="left1 zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.security.number') }} {{ company10.num.AQGshuliang }}</div> <!-- $i18n - 当前安全官数量: -->
                    <!-- <div class="right1"> -->
                    <!-- <div class="addAnquanguan xiaoshou" @click="addSecurity()">{{$t('registerOpenAccount.openAccount.common.security.btnAddAnquan')}}</div> $i18n - 添加安全官 -->
                    <!-- </div> -->
                  </div>
                  <div class="gudongTable">
                    <table border="1" cellspacing="0" cellpadding="0">
                      <thead>
                        <tr>
                          <th>{{ $t('registerOpenAccount.openAccount.common.security.name') }}</th> <!-- $i18n - 姓名 -->
                          <th>{{ $t('registerOpenAccount.openAccount.common.security.username') }}</th> <!-- $i18n - 用户名 -->
                          <th>{{ $t('registerOpenAccount.openAccount.common.security.status') }}</th> <!-- $i18n - 状态 -->
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item,index) in company10.tableTbady" :key="index">
                          <td>{{ item.compellation }}</td>
                          <td>{{ item.username }}</td>
                          <td>
                            {{ item.zhuangtai22 }}
                          </td>
                          <td>
                            <div v-if="item.caozuo!=1" class="xiaoshou deleteBtn" @click="removebiaoRow(index)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                          </td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div v-if="company10.isanquanguanshenghe">
                    <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.security.audit') }}</p> <!-- $i18n - 为提高安全性，您可以要求一位安全专员 ... -->
                    <el-form-item :label="$t('registerOpenAccount.openAccount.common.security.staff')"> <!-- $i18n - 审核要求的安全官员 -->
                      <el-select v-model="company10.anquanyuanSL.anquanNumber">
                        <el-option label="0 " value="0" />
                        <el-option label="1 " value="1" />
                        <el-option label="2 " value="2" />
                      </el-select>
                    </el-form-item>
                  </div>
                  <div class="formHint " style="background-color:#f6f6f6;">
                    <ul>
                      <li style="color:#666;">{{ $t('registerOpenAccount.openAccount.common.security.attention') }}</li> <!-- $i18n - 请注意在您完成此页、账户开立后，将来如果要 ... -->
                    </ul>
                    <div class="gantanhao" style="color:#ccc;">!</div>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div v-if="company10.isbottomBox" class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('company10')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }}</el-button>
                  </el-form-item>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>
      <!-- 模态框 1-->
      <div v-if="company10.ifmodalBox" class="modalBox">
        <div class="contentBOX">
          <div class="contentBOXdignwei contentBOXMoL">
            <el-form
              ref="company10"
              status-icon
              :rules="rules"
              :model="company10"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="biaoTitle">{{ $t('registerOpenAccount.openAccount.common.security.btnAddAnquan') }}</div> <!-- $i18n - 添加安全官 -->
                <!-- 注册拥有人 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <!-- <h3>个人信息</h3> -->
                  </div>
                  <div class="formNapeContent">
                    <el-form-item prop="gerenGudong.companyName">
                      <el-select v-model="company10.gerenGudong.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                        <dictionary dict-code="appellationType" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="gerenGudong.call">
                      <el-input v-model="company10.gerenGudong.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.middle">
                      <el-input v-model="company10.gerenGudong.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 中间名,不适用于中国客户 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.surname">
                      <el-input v-model="company10.gerenGudong.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.suffix">
                      <el-select v-model="company10.gerenGudong.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                        <el-option label="Jr " value="Jr" />
                        <el-option label="Sr " value="Sr" />
                        <el-option label="I " value="I" />
                        <el-option label="II " value="II" />
                        <el-option label="III " value="III" />
                        <el-option label="IV " value="IV" />
                        <el-option label="V " value="V" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="gerenGudong.username">
                      <el-input v-model="company10.gerenGudong.username" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.info.username')" /> <!-- $i18n - 用户名 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.psword" class="percentage">
                      <el-input v-model="company10.gerenGudong.psword" maxlength="16" type="password" :placeholder="$t('registerOpenAccount.openAccount.common.info.password')" /> <!-- $i18n - 密码 -->
                      <div v-if="intensityStatus !== 0" class="icon-percent">{{ intensityStatus }}</div>
                    </el-form-item>
                    <el-form-item prop="gerenGudong.psword1">
                      <el-input v-model="company10.gerenGudong.psword1" maxlength="16" type="password" :placeholder="$t('registerOpenAccount.openAccount.common.info.email')" /> <!-- $i18n - 确认密码 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.email">
                      <el-input v-model="company10.gerenGudong.email" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.info.ownership')" /> <!-- $i18n - 电邮地址 -->
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <li class="isyes" :class="{ dianji: !company10.isGuyuan}" @click="company10.isGuyuan=false">{{ $t('registerOpenAccount.openAccount.common.info.employee') }}</li> <!-- $i18n - 雇员 -->
                        <li class="isno" :class="{ dianji: company10.isGuyuan}" @click="company10.isGuyuan=true">{{ $t('registerOpenAccount.openAccount.common.info.Femployee') }}</li> <!-- $i18n - 非雇员 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.info.entity') }}</li> <!-- $i18n - 实体关系 -->
                      </ul>
                    </div>
                    <el-form-item v-if="company10.isGuyuan" prop="gerenGudong.guyuanGuanxi">
                      <el-input v-model="company10.gerenGudong.guyuanGuanxi" maxlength="32" :placeholder="$t('registerOpenAccount.openAccount.common.info.relation')" /> <!-- $i18n - 与您的关系 -->
                    </el-form-item>
                  </div>
                </div>
                <!-- 保存,关闭 -->
                <div class="isBtn right1">
                  <ul>
                    <li class="isyes Motaiteshuchuli" @click="preserve1('company10')">{{ $t('registerOpenAccount.common.btn.save') }}</li> <!-- $i18n - 保存 -->
                    <li class="isno Motaiteshuchuli1" @click="shut()">{{ $t('registerOpenAccount.common.btn.close') }}</li> <!-- $i18n - 关闭 -->
                  </ul>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import dictionary from '../../../../../components/dictionary/dictionary';
// import { log } from 'util';
// import { stringify } from 'querystring';

export default {
  components: {
    dictionary
  },
  data() {
    // 非中文验证
    var notChinese = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 自定义校验
    var validateUsername = (rule, value, callback) => {
      const _this = this;
      this.$verify.Username(rule, value, callback, _this);
    };
    var validatePass = (rule, value, callback) => {
      const mate = this.company10.gerenGudong.username;
      this.$verify.Psword(rule, value, mate, callback).then(res => {
        callback();
        this.$refs.company10.validateField('gerenGudong.psword1');
        if (res === 1) {
          this.intensityStatus = this.$i18n.t('registerOpenAccount.common.psdStreng.weak'); // $i18n - 弱
        } else if (res === 2) {
          this.intensityStatus = this.$i18n.t('registerOpenAccount.common.psdStreng.centre'); // $i18n - 中
        } else if (res === 3) {
          this.intensityStatus = this.$i18n.t('registerOpenAccount.common.psdStreng.strong'); // $i18n - 强
        }
      }).catch(res => {
        this.isintensityStatus = res;
      });
    };
    var validatePass2 = (rule, value, callback) => {
      const mate = this.company10.gerenGudong.psword;
      this.$verify.PswordT(rule, value, mate, callback);
    };
    var validateEmail = (rule, value, callback) => {
      this.$verify.Postbox(rule, value, callback);
    };
    var validateGuyuan = (rule, value, callback) => {
      const mate = this.company10.isGuyuan;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      intensityStatus: 0,
      company10: {
        ifmodalBox: false, // 模态框 + content
        isbottomBox: true, // 保存&继续按钮 显示隐藏
        personalType: '', // 安全官类型
        isGuyuan: false,
        num: {
          AQGshuliang: 0
        },
        tableTbady: [

        ],
        // 个人信息-安全官
        gerenGudong: {
          companyName: '',
          call: '',
          middle: '',
          surname: '',
          suffix: '',
          username: '',
          psword: '',
          psword1: '',
          email: '',
          guyuanGuanxi: ''
        },
        gerenGudong1: null,
        isanquanguanshenghe: false,
        anquanyuanSL: {
          anquanNumber: '0'
        }
      },
      safetyList: [

      ],
      formdata: {},
      newValue: null,
      rules: {
        // 安全官-验证
        'gerenGudong.companyName': [
          { required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.enter'), trigger: 'change' } // $i18n - 请输入
        ],
        'gerenGudong.call': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'gerenGudong.middle': [
          { validator: notChinese1, trigger: 'blur' }
        ],
        'gerenGudong.surname': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'gerenGudong.suffix': [
          { validator: notChinese1, trigger: 'change' }
        ],
        'gerenGudong.username': [
          { validator: validateUsername, trigger: 'blur' }
        ],
        'gerenGudong.psword': [
          { validator: validatePass, trigger: 'blur' }
        ],
        'gerenGudong.psword1': [
          { validator: validatePass2, trigger: 'blur' }
        ],
        'gerenGudong.email': [
          { validator: validateEmail, trigger: 'blur' }
        ],
        'gerenGudong.guyuanGuanxi': [
          { validator: validateGuyuan, trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.company10TS = JSON.parse(JSON.stringify(this.company10));
    this.personalType();
  },
  mounted() {

  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 默认安全官
    defaultSafety(personalType) {
      this.$http({
        url: '/account/accountCoPersonal/findByAccountId',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.company10.tableTbady.push({
                  compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                  username: this.$store.state.user.applyData.xinxi.accountName,
                  zhuangtai22: 'Open', // 等待审核
                  caozuo: 1
                });
                res.object[i].displayArray = 1; // 展示 可删除,但不传递给后台的股东
                this.safetyList.push(
                  JSON.parse(JSON.stringify(res.object[i]))
                );
                this.company10.num.AQGshuliang++;
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
    // 获取用户已填入的信息
    accountCoPersonal(personalType) { // 个人信息 -安全官
      this.$http({
        url: '/account/accountCoSecurityOfficer/findByAccountId',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.company10.tableTbady.push({
                  compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                  username: res.object[i].userName,
                  zhuangtai22: 'Pending User', // 等待审核
                  caozuo: 2
                });
                res.object[i].displayArray = 1; // 展示 可删除,但不传递给后台的股东
                this.safetyList.push(
                  JSON.parse(JSON.stringify(res.object[i]))
                );
                this.company10.num.AQGshuliang++;
                this.company10.isanquanguanshenghe = true;
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
    // 字典表获取
    // 个人类型
    personalType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            // this.company10.personalType = res.object[5].dictValue;
            this.company10.personalType = 'securityOfficer';

            this.company10.isanquanguanshenghe = false;
            this.company10.num.AQGshuliang = 0;
            this.company10.tableTbady = [];
            this.safetyList = [];

            // this.defaultSafety(res.object[1].dictValue);
            this.defaultSafety('authorizedPerson');
            this.accountCoPersonal(this.company10.personalType);
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
    // 安全官添加
    addSecurity() {
      this.company10.ifmodalBox = true;
      this.company10.isbottomBox = false;
    },
    // 删除表格-行
    removebiaoRow(index) {
      this.$http({
        url: '/account/accountCoSecurityOfficer/remove',
        method: 'post',
        params: { id: this.safetyList[index].id }
      })
        .then(res => {
          if (res.code === 200) {
            this.$message.success(this.$i18n.t('registerOpenAccount.common.formPrompt.deleteWin')); // $i18n - 删除成功
            this.personalType();
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
    // 模态框-保存
    preserve1(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.arrPush();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.formError')); // $i18n - 表单验证未通过
        }
      });
    },
    // 模态框-关闭
    shut() {
      this.company10.ifmodalBox = false;
      this.company10.isbottomBox = true;
    },
    arrPush() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      const accountCoSecurityOfficerList = [];
      accountCoSecurityOfficerList.push(
        {
          accountId,
          createdBy,
          createdDate,
          appellation: this.company10.gerenGudong.companyName,
          lastName: this.company10.gerenGudong.call,
          middleName: this.company10.gerenGudong.middle,
          firstName: this.company10.gerenGudong.surname,
          suffix: this.company10.gerenGudong.suffix,
          userName: this.company10.gerenGudong.username,
          password: this.company10.gerenGudong.psword,
          email: this.company10.gerenGudong.email,
          etityRelationship: this.company10.isGuyuan ? this.company10.gerenGudong.guyuanGuanxi : null,
          authorizedRelationship: this.company10.isGuyuan ? this.company10.gerenGudong.guyuanGuanxi : null
        }
      );

      this.submitFormData(accountCoSecurityOfficerList);
      const company10TS = JSON.parse(JSON.stringify(this.company10TS));
      this.company10.gerenGudong = company10TS.gerenGudong;
      this.company10.ifmodalBox = false;
      this.company10.isbottomBox = true;
    },
    // 提交
    submitForm() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      if (this.safetyList.length > 0) {
        this.$http({
          url: '/openAccount/company/saveTenPage',
          method: 'post',
          params: { accountId }
        })
          .then(res => {
            if (res.code === 200) {
              this.accountCoTaxClassification();
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
      } else {
        this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.retry')); // $i18n - 请刷新页面或登录重试
      }
    },
    submitFormData(accountCoSecurityOfficerList) {
      const data = {
        accountId: this.$store.state.user.applyData.xinxi.id,
        accountCoSecurityOfficerList
      };
      this.$http({
        url: '/openAccount/company/saveTen',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$message.success(this.$i18n.t('registerOpenAccount.common.formPrompt.deleteWin')); // $i18n - 添加成功
            this.personalType();
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
              if (res.object.taxType === 'enterprise') { // 企业
                this.$verify.routerPermissions({ path: '/openAccount/company11a' });
              } else if (res.object.taxType === 'partnership (must have at least 2 owners)') { // 合伙企业
                this.$verify.routerPermissions({ path: '/openAccount/company11b' });
              } else {
                this.$verify.routerPermissions({ path: '/openAccount/company12' }); // 暂时所有国家都可以到crs
              }
              // } else if (res.object.taxType === 'disregardEntities (must only have 1 owner)') { // 无视实体
              //   if (this.$store.state.user.applyData.xinxi.country === 'CN') {
              //     this.$verify.routerPermissions({ path: '/openAccount/company12' });
              //   } else {
              //     this.$verify.routerPermissions({ path: '/openAccount/company14' });
              //   }
              // }
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
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/company9' });
    }
  }
};
</script>

<style scoped>
#ZC-regiMain .shuliang {
  width: 100%;
  height: 43px;
}
#ZC-regiMain .shuliang > div {
  width: 50%;
  height: 100%;
  line-height: 45px;
}
#ZC-regiMain .addAnquanguan {
  width: 100%;
  height: 100%;
  background-color: #fff;
  border: 1px solid #ececec;
  text-align: center;
}
#ZC-regiMain .addAnquanguan:hover {
  background-color: #fcfcfc;
}
</style>

