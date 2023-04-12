<template>
  <div>
    <div>
      <div id="ZC-regiMain">
        <div class="topBG">
          <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(5/17) -' }}</div>
          <div class="PrintBOX clearfix">
            <div class="iconPrint" @click="printPdf()" />
          </div>
        </div>
        <div class="contentBOX">
          <div class="contentBOXdignwei">
            <el-form
              ref="partnership5"
              autocomplete="off"
              status-icon
              :rules="rules"
              :model="partnership5"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.partnership.supervise.title') }}</h3> <!-- $i18n - 经济商监管许可信息 -->
                    <p
                      style="color:#000000a1"
                    >{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe1') }}</p> <!-- $i18n - 要成为国瑞证券平台的介绍经纪商，您的公司 ... -->
                  </div>
                  <div class="formNapeContent">
                    <p class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe2') }}</p> <!-- $i18n - 国瑞证券提供两种类型的顾问账户 : -->
                    <ul>
                      <li
                        style="color:#000000a1; margin-bottom:10px"
                        class="zhuce3ji"
                      >{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe3') }}</li> <!-- $i18n - 列出您公司获取许可或注册从而为客户提供经纪服务的国家。 -->
                      <li style="color:#000000a1" class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe4') }}</li> <!-- $i18n - 只需列出您的公司获取许可及您的客户实际通过国瑞证券进行交易的国家。 -->
                    </ul>
                  </div>
                  <div style="margin-top:10px; color:#000a1">
                    <a class="zhuce2ji" style="text-decoration:underline;cursor:pointer;">{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe5') }}</a> <!-- $i18n - 仅列出您实际获批或注册的国家。 -->
                    <span
                      class="zhuce2ji"
                      style="color:#000000a1"
                    >{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe6') }}</span> <!-- $i18n - 比如，若您的公司获取了某个国家的许可，但您的客户不会与IB进行交易，那么请不要列出这个国家。 -->
                  </div>
                  <div class="top6">
                    <el-form-item prop="counselorOne">
                      <el-radio-group v-model="partnership5.counselorOne">
                        <div v-for="(item,index) in partnership5.counselorList" :key="index">
                          <el-radio :label="item.value" @change="partnership5.ifCounselorSon = index">{{ item.dist }}</el-radio>
                        </div>
                        <br>
                      </el-radio-group>
                    </el-form-item>
                    <div
                      v-if="partnership5.ifHint"
                      class="hint"
                    >{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe7') }}</div> <!-- $i18n - 您已表明您的公司并未在任何国家获得许或经过注册提供经纪服务。如情况属实 -->
                    <el-form-item v-if="partnership5.ifCounselorSon===1" prop="twoCounselorSon">
                      <span style="font-weight: bold; color:#000000ad">{{ $t('registerOpenAccount.openAccount.partnership.supervise.describe8') }}</span> <!-- $i18n - 主要注册/许可国家 -->
                      <el-select v-model="partnership5.twoCounselorSon" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.common.formPrompt.optional2')"> <!-- $i18n - 请选择(必填) -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <div v-if="partnership5.ifCounselorSon===2">
                      <transition name="fade">
                        <el-form-item class="fade" prop="threeCounselorSon1">
                          <div class="zhuce3ji" style="color:#000000a1">{{ $t('registerOpenAccount.openAccount.partnership.supervise.title') }}</div> <!-- $i18n - 您最多可选择15个国家。 -->
                          <el-select v-model="partnership5.threeCounselorSon1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.partnership.supervise.describe8')"> <!-- $i18n - 主要注册/许可国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                      </transition>

                      <el-form-item
                        v-for="(item,index) in partnership5.threeCounselorSon2"
                        :key="index"

                        :prop="'threeCounselorSon2.'+index+'.counselorSon'"
                        :rules="rules.counselorSon"
                      >
                        <el-select v-model="item.counselorSon" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.partnership.supervise.describe10')"> <!-- $i18n - 注册/许可国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </div>

                    <transition name="fade">
                      <div
                        v-show="partnership5.ifCounselorSon===2"
                        class="addBtn"
                        style="width: 960px;height: 41px;"
                      >
                        <div v-show="partnership5.threeCounselorSon2.length !== 5" class="add" @click="addElement(partnership5.threeCounselorSon2)">{{ $t('registerOpenAccount.openAccount.partnership.supervise.addState') }}</div> <!-- $i18n - 添加注册/许可国家 -->
                        <div v-show="partnership5.threeCounselorSon2.length !== 1" class="cancel" @click="removeElement(partnership5.threeCounselorSon2)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                      </div>
                    </transition>
                  </div>
                </div>
                <!-- 提交按钮 -->
                <div class="formbtn">
                  <div class="formbtn-content">
                    <el-form-item>
                      <el-button
                        class="right-btn BTbtn"
                        plain
                        @click="submitForm('partnership5')"
                      >{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
  </div>
</template>

<script>
import dictionary from '../../../../../components/dictionary/dictionary';
export default {
  components: {
    dictionary
  },
  data() {
    // 非中文验证
    var notChinese = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      partnership5: {
        counselorList: [
          {
            dist: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.son1'),
            value: '1'
          },
          {
            dist: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.son2'),
            value: '2'
          },
          {
            dist: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.son3'),
            value: '3'
          }
        ],
        counselorOne: '',
        counselorTwo: '',
        counselorThree: '',
        ifHint: false,
        ifCounselorSon: 0,
        twoCounselorSon: '',
        threeCounselorSon1: '',
        threeCounselorSon2: [
          {
            counselorSon: ''
          }
        ],
        cancelBtn: false
      },
      newValue: null,
      rules: {
        counselorOne: [
          { validator: notChinese, trigger: 'change' }
        ],
        twoCounselorSon: [
          { validator: notChinese, trigger: 'change' }
        ],
        threeCounselorSon1: [
          { validator: notChinese, trigger: 'change' }
        ],
        counselorSon: [
          { validator: notChinese, trigger: 'change' }
        ]
      }
    };
  },
  created() {
    this.accountParBrokerPermission();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountParBrokerPermission() {
      this.$http({
        url: '/account/accountParBrokerPermission/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object[0];
              this.partnership5.counselorOne = res.object[0].registerAdviser;
              for (let i = 0; i < this.partnership5.counselorList.length; i++) {
                if (this.partnership5.counselorList[i].value === res.object[0].registerAdviser) {
                  this.partnership5.ifCounselorSon = i;
                }
              }
              if (res.object[0].registerAdviser === '2') {
                this.partnership5.twoCounselorSon = res.object[0].registerCountry;
              } else if (res.object[0].registerAdviser === '3') {
                if (res.object[0].registerCountry.indexOf(',') !== -1) {
                  const registerCountry = res.object[0].registerCountry.split(',');
                  for (let i = 0; i < registerCountry.length; i++) {
                    if (i === 0) {
                      this.partnership5.threeCounselorSon1 = registerCountry[i];
                    } else if (i === 1) {
                      this.partnership5.threeCounselorSon2[0].counselorSon = registerCountry[i];
                    } else {
                      this.partnership5.threeCounselorSon2.push(
                        {
                          counselorSon: registerCountry[i]
                        }
                      );
                    }
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

    /**
     * 添加元素
     * index 识别号  --手动判断更改obj的值 --暂无
     * index 需要添加的数组限制
     * arr 需要操作的数组
     */
    addElement(arr) {
      const obj = {
        counselorSon: ''
      };
      arr.push(JSON.parse(JSON.stringify(obj)));
    },
    // 删除元素
    // arr 需要操作的数组
    removeElement(arr) {
      arr.pop();
    },
    // 验证表单 跳转页面
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.btn.delete')); // $i18n - 验证未通过
        }
      });
    },
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      const updatedDate = this.$verify.NowDate();

      let data = null;
      let registerCountry = null; // 国家
      if (this.partnership5.counselorOne === '2') {
        registerCountry = this.partnership5.twoCounselorSon;
      } else if (this.partnership5.counselorOne === '3') {
        registerCountry = this.partnership5.threeCounselorSon1;
        for (let i = 0; i < this.partnership5.threeCounselorSon2.length; i++) {
          registerCountry = registerCountry + ',' + this.partnership5.threeCounselorSon2[i].counselorSon;
        }
      }
      if (this.newValue === null) {
        data = {
          accountId,
          createdBy,
          createdDate,
          updatedDate,
          registerAdviser: this.partnership5.counselorOne,
          registerCountry
        };
      } else {
        this.newValue.registerAdviser = this.partnership5.counselorOne;
        this.newValue.registerCountry = registerCountry;
        data = this.newValue;
      }
      this.$http({
        url: '/openAccount/partnerShip/saveFive',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership6' });
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
      this.$verify.routerPermissions({ path: '/openAccount/partnership4' });
    }
  }
};
</script>

<style >
.lineHeightBD20 > li {
  list-style: disc;
}
</style>

<style scoped>
.el-radio {
  line-height: 28px;
  font-size: 16px;
  color: #999;
}
.hint {
  height: 50px;
  line-height: 50px;
  padding-left: 12px;
  color: rgb(143, 69, 69);
  background-color: rgb(226, 211, 211);
  letter-spacing: 2px;
}
</style>
