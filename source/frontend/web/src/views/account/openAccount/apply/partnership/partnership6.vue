<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(6/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership6"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="partnership6"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.partnership.supervise.title') }}</h3> <!-- $i18n - 经济商监管许可信息 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="supervision">
                  <ul>
                    <transition name="fade">
                      <li class="isyes" :class="{ dianji: partnership6.occObj.ifOcc,fade:true}" @click="partnership6.occObj.ifOcc=true">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                    </transition>
                    <transition name="fade">
                      <li
                        class="isno"
                        :class="{ dianji: !partnership6.occObj.ifOcc,fade:true}"
                        @click="partnership6.occObj.ifOcc=false"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                    </transition>
                    <li>{{ $t('registerOpenAccount.openAccount.partnership.supervise.btnMember') }}</li> <!-- $i18n - 你的组织是否为期权清算公司(OCC)的会员？ -->
                  </ul>
                  <el-form-item v-if="partnership6.occObj.ifOcc" prop="occObj.occ">
                    <el-input
                      v-model="partnership6.occObj.occ"
                      type="text"
                      :placeholder="$t('registerOpenAccount.openAccount.partnership.supervise.member1')"
                    /> <!-- $i18n - 输入你的OCC会员号码 -->
                  </el-form-item>
                </div>
                <!-- :prop="'manageSite.'+index+'.state1'" :rules="rules.state1" -->
                <div class="regulator">
                  <h4>{{ $t('registerOpenAccount.openAccount.partnership.supervise.munber') }}</h4> <!-- $i18n - 列出您在注册过的最多1家监管机构： -->
                  <p>{{ $t('registerOpenAccount.openAccount.partnership.supervise.supervise') }}</p> <!-- $i18n - 监管机构#1 -->
                  <div v-for="(item,index) in partnership6.regulator" :key="index">
                    <div v-for="(item1,index1) in partnership6.regulator[index].regulatorSon" :key="index1">
                      <el-form-item v-if="item1.ifshow||index1===0" :show-message="false" :prop="`regulator.${index}.regulatorSon.${index1}.title`" :rules="rules.title">
                        <el-radio-group v-model="item1.title">
                          <el-radio :label="item1.titleList.value" @click.native="radioClick1(index,index1)">{{ item1.titleList.dest }}</el-radio>
                        </el-radio-group>
                      </el-form-item>
                      <el-form-item v-if="item1.ifshow1" :prop="`regulator.${index}.regulatorSon.${index1}.input`" :rules="rules.input">
                        <el-input v-model="item1.input" :placeholder="item1.placeholderTS" />
                      </el-form-item>
                    </div>
                    <div v-if="item.ishint" style="color: red">
                      {{ $t('registerOpenAccount.common.btn.no') }}
                    </div> <!-- $i18n - 请输入 -->
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button
                      class="right-btn BTbtn"
                      plain
                      @click="submitForm('partnership6')"
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
</template>

<script>
export default {
  data() {
    // 非中文验证
    var notChinese = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    // 监管机构单选框验证
    var validatorRegulator = (rule, value, callback) => {
      let index = 0;
      for (let i = 0; i < this.partnership6.regulator.length; i++) {
        for (let j = 0; j < this.partnership6.regulator[i].regulatorSon.length; j++) {
          if (this.partnership6.regulator[i].regulatorSon[0].title) {
            index++;
            this.partnership6.regulator[i].ishint = false;
          } else {
            this.partnership6.regulator[i].ishint = true;
            index--;
          }
        }
      }
      if (index <= 0) {
        callback(new Error());
      } else {
        callback();
      }
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      partnership6: {
        occObj: {
          ifOcc: false,
          occ: ''
        },
        reIndex: 0,
        regulator: [
          // {
          //   ishint: false,
          //   regulatorSon: [
          //     {
          //       title: '',
          //       titleList: {
          //         dest: 'Other',
          //         value: '111a'
          //       },
          //       input: '',
          //       ifshow: true, // 单选框的显示隐藏
          //       ifshow1: false, // input的显示隐藏
          //       placeholderTS: '必填'
          //     },
          //     {
          //       title: '',
          //       titleList: {
          //         dest: 'Financial/Investment Advisor',
          //         value: '111b'
          //       },
          //       input: '',
          //       ifshow: false,
          //       ifshow1: false,
          //       placeholderTS: '注册号码：'
          //     },
          //     {
          //       title: '',
          //       titleList: {
          //         dest: 'position',
          //         value: '111c'
          //       },
          //       input: '',
          //       ifshow: false,
          //       ifshow1: false,
          //       placeholderTS: '注册职位：'
          //     }
          //   ]
          // }
        ],
        dictList: []
      },
      newValue: null,
      rules: {
        'occObj.occ': [
          { validator: notChinese, trigger: 'blur' }
        ],
        title: [{ validator: validatorRegulator, trigger: 'change' }],
        input: [
          { validator: notChinese, trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    // 获取国家字典表
    this.doInitData();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取第五页数据
    accountParBrokerPermission() {
      this.$http({
        url: '/account/accountParBrokerPermission/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].registerAdviser !== '1') {
                for (let i = 0; i < res.object.length; i++) {
                  if (res.object[i].registerCountry.indexOf(',') !== -1) {
                    const registerCountry = res.object[i].registerCountry.split(',');
                    for (let i = 0; i < registerCountry.length; i++) {
                      let sCode = null;
                      let cnName = null;
                      for (let j = 0; j < this.partnership6.dictList.length; j++) {
                        if (registerCountry[i] === this.partnership6.dictList[j].sCode) {
                          sCode = this.partnership6.dictList[j].sCode;
                          cnName = this.partnership6.dictList[j].cnName;
                        }
                      }
                      this.partnership6.regulator.push(
                        {
                          ishint: false,
                          regulatorSon: [
                            {
                              title: '',
                              titleList: {
                                dest: cnName,
                                value: sCode
                              },
                              input: '',
                              ifshow: true, // 单选框的显示隐藏
                              ifshow1: false, // input的显示隐藏
                              placeholderTS: this.$i18n.t('registerOpenAccount.common.formPrompt.required') // $i18n - 必填
                            },
                            {
                              title: '',
                              titleList: {
                                dest: 'Financial/Investment Advisor',
                                value: '111b'
                              },
                              input: '',
                              ifshow: false,
                              ifshow1: false,
                              placeholderTS: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.munber2') // $i18n - 注册号码：
                            },
                            {
                              title: '',
                              titleList: {
                                dest: 'position',
                                value: '111c'
                              },
                              input: '',
                              ifshow: false,
                              ifshow1: false,
                              placeholderTS: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.position') // $i18n - 注册职位：
                            }
                          ]
                        },
                      );
                    }
                  } else {
                    let sCode = null;
                    let cnName = null;
                    for (let j = 0; j < this.partnership6.dictList.length; j++) {
                      if (res.object[0].registerCountry === this.partnership6.dictList[j].sCode) {
                        sCode = this.partnership6.dictList[j].sCode;
                        cnName = this.partnership6.dictList[j].cnName;
                      }
                    }
                    this.partnership6.regulator.push(
                      {
                        ishint: false,
                        regulatorSon: [
                          {
                            title: '',
                            titleList: {
                              dest: cnName,
                              value: sCode
                            },
                            input: '',
                            ifshow: true, // 单选框的显示隐藏
                            ifshow1: false, // input的显示隐藏
                            placeholderTS: this.$i18n.t('registerOpenAccount.common.formPrompt.required') // $i18n - 必填
                          },
                          {
                            title: '',
                            titleList: {
                              dest: 'Financial/Investment Advisor',
                              value: '111b'
                            },
                            input: '',
                            ifshow: false,
                            ifshow1: false,
                            placeholderTS: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.munber2') // $i18n - 注册号码：
                          },
                          {
                            title: '',
                            titleList: {
                              dest: 'position',
                              value: '111c'
                            },
                            input: '',
                            ifshow: false,
                            ifshow1: false,
                            placeholderTS: this.$i18n.t('registerOpenAccount.openAccount.partnership.supervise.position') // $i18n - 注册职位：
                          }
                        ]
                      },
                    );
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
    // 获取用户已填入的信息
    accountParBrokerInfo() {
      this.$http({
        url: '/account/accountParBrokerInfo/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object;
              for (let i = 0; i < res.object.length; i++) {
                this.partnership6.occObj.ifOcc = res.object[i].status;
                this.partnership6.occObj.occ = res.object[i].occMemberNumber;
                if (this.partnership6.regulator.length !== 0) {
                  for (let j = 0; j < this.partnership6.regulator[i].regulatorSon.length; j++) {
                    if (j === 0) {
                      this.partnership6.regulator[i].regulatorSon[j].title = res.object[i].regulator;
                      this.partnership6.regulator[i].regulatorSon[j].input = res.object[i].regulatorInfo;
                      this.radioClick1(i, j); // 函数代码块解决 @click.native= 二次点击处理 所以这里调用两次
                      this.radioClick1(i, j);
                    } else if (j === 1) {
                      if (res.object[i].registerNumber) {
                        this.partnership6.regulator[i].regulatorSon[j].input = res.object[i].registerNumber;
                        this.partnership6.regulator[i].regulatorSon[j].ifshow1 = true;
                        this.partnership6.regulator[i].regulatorSon[j].title = '111b'; // 手动赋值
                      }
                    } else if (j === 2) {
                      if (res.object[i].registerPosition) {
                        this.partnership6.regulator[i].regulatorSon[j].input = res.object[i].registerPosition;
                        this.partnership6.regulator[i].regulatorSon[j].ifshow1 = true;
                        this.partnership6.regulator[i].regulatorSon[j].title = '111c'; // 手动赋值
                      }
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
    // 获取字典表
    doInitData() {
      this.$http({
        url: '/common/region/findAll',
        method: 'get',
        params: { dictCode: this.dictCode, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          this.partnership6.dictList = res.object;
          this.accountParBrokerPermission();
          this.accountParBrokerInfo();
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
    // index 外层regulator下标 index1 内层regulatorSon下标
    radioClick1(index, index1) {
      if (this.partnership6.reIndex === 1) { // @click.native= 二次点击处理
        this.partnership6.reIndex = 0;
        if (index1 === 0) {
          for (let i = 0; i < this.partnership6.regulator[index].regulatorSon.length; i++) {
            if (i === 0) {
              this.partnership6.regulator[index].regulatorSon[i].ifshow1 = true; // 0的ifshow 一直显示
            } else {
              this.partnership6.regulator[index].regulatorSon[i].ifshow = true;
            }
          }
        } else {
          if (this.partnership6.regulator[index].regulatorSon[index1].title) {
            this.partnership6.regulator[index].regulatorSon[index1].ifshow1 = false;
            this.partnership6.regulator[index].regulatorSon[index1].title = '';
          } else {
            this.partnership6.regulator[index].regulatorSon[index1].ifshow1 = true;
            this.partnership6.regulator[index].regulatorSon[index1].title = '';
          }
        }
      } else {
        this.partnership6.reIndex++;
      }
    },
    // 验证表单 跳转页面
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.formError1')); // $i18n - 验证未通过
        }
      });
    },
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      const updatedDate = this.$verify.NowDate();

      let data = [];
      let regulator = null;
      let regulatorInfo = null;
      let registerNumber = null;
      let registerPosition = null;
      if (this.newValue === null) {
        if (this.partnership6.regulator.length === 0) {
          data.push(
            {
              accountId,
              createdBy,
              createdDate,
              updatedDate,
              status: this.partnership6.occObj.ifOcc,
              occMemberNumber: this.partnership6.occObj.ifOcc ? this.partnership6.occObj.occ : null,
              regulator,
              regulatorInfo,
              registerNumber,
              registerPosition
            }
          );
        } else {
          for (let i = 0; i < this.partnership6.regulator.length; i++) {
            for (let j = 0; j < this.partnership6.regulator[i].regulatorSon.length; j++) {
              if (j === 0) {
                regulator = this.partnership6.regulator[i].regulatorSon[j].title;
                regulatorInfo = this.partnership6.regulator[i].regulatorSon[j].input;
              } else if (j === 1) {
                if (this.partnership6.regulator[i].regulatorSon[j].ifshow1) {
                  registerNumber = this.partnership6.regulator[i].regulatorSon[j].input;
                } else {
                  registerNumber = null;
                }
              } else if (j === 2) {
                if (this.partnership6.regulator[i].regulatorSon[j].ifshow1) {
                  registerPosition = this.partnership6.regulator[i].regulatorSon[j].input;
                } else {
                  registerNumber = null;
                }
              }
            }
            data.push(
              {
                accountId,
                createdBy,
                createdDate,
                updatedDate,
                status: this.partnership6.occObj.ifOcc,
                occMemberNumber: this.partnership6.occObj.ifOcc ? this.partnership6.occObj.occ : null,
                regulator,
                regulatorInfo,
                registerNumber,
                registerPosition
              }
            );
          }
        }
      } else {
        if (this.partnership6.regulator.length === 0) {
          this.newValue[0].status = this.partnership6.occObj.ifOcc;
          this.newValue[0].occMemberNumber = this.partnership6.occObj.ifOcc ? this.partnership6.occObj.occ : null;
          this.newValue[0].regulator = regulator;
          this.newValue[0].regulatorInfo = regulatorInfo;
          this.newValue[0].registerNumber = registerNumber;
          this.newValue[0].registerPosition = registerPosition;
        } else {
          for (let i = 0; i < this.partnership6.regulator.length; i++) {
            for (let j = 0; j < this.partnership6.regulator[i].regulatorSon.length; j++) {
              if (j === 0) {
                regulator = this.partnership6.regulator[i].regulatorSon[j].title;
                regulatorInfo = this.partnership6.regulator[i].regulatorSon[j].input;
              } else if (j === 1) {
                if (this.partnership6.regulator[i].regulatorSon[j].ifshow1) {
                  registerNumber = this.partnership6.regulator[i].regulatorSon[j].input;
                } else {
                  registerNumber = null;
                }
              } else if (j === 2) {
                if (this.partnership6.regulator[i].regulatorSon[j].ifshow1) {
                  registerPosition = this.partnership6.regulator[i].regulatorSon[j].input;
                } else {
                  registerPosition = null;
                }
              }
            }
            this.newValue[i].status = this.partnership6.occObj.ifOcc;
            this.newValue[i].occMemberNumber = this.partnership6.occObj.ifOcc ? this.partnership6.occObj.occ : null;
            this.newValue[i].regulator = regulator;
            this.newValue[i].regulatorInfo = regulatorInfo;
            this.newValue[i].registerNumber = registerNumber;
            this.newValue[i].registerPosition = registerPosition;
          }
        }
        data = this.newValue;
      }
      this.$http({
        url: '/openAccount/partnerShip/saveSix',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership7' });
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
      this.$verify.routerPermissions({ path: '/openAccount/partnership5' });
    }
  }
};
</script>
<style scoped>
.el-form-item {
  margin-bottom: -6px;
}
.el-form-item .el-form-item {
  margin-bottom: 14px;
}
.formNapeContent {
  padding-bottom: 10px;
  border-bottom: 1px solid #6666664a;
}
.supervision {
  border-bottom: 1px solid #6666664a;
}
/* .supervision input {
  width: 98%;
  height: 25px;
  margin-bottom: 20px;
  border-width: 0.5px;
  border-color: #6666664a;
} */
.supervision ul {
  height: 60px;
}
.supervision ul li {
  float: left;
}
.supervision ul li:nth-of-type(3) {
  height: 40px;
  line-height: 40px;
  color: #666;
}
.supervision ul .isyes {
  width: 100px;
  height: 38px;
  background-color: rgb(255, 255, 255);
  border: 1px solid #ff5949;
  border-radius: 5px;
  margin-right: 10px;
  text-align: center;
  line-height: 40px;
  color: #666;
}
.supervision ul .isno {
  width: 100px;
  height: 38px;
  background-color: rgb(255, 255, 255);
  border: 1px solid #ff5949;
  border-radius: 5px;
  margin-right: 10px;
  text-align: center;
  line-height: 40px;
  color: #666;
}
.supervision ul .isyes:hover {
  background-color: rgb(255, 135, 124);
  color: rgb(255, 255, 255);
}
.supervision ul .isno:hover {
  background-color: rgb(255, 135, 124);
  color: rgb(255, 255, 255);
}
.supervision ul .dianji {
  background-color: #ff5949;
  color: rgb(255, 255, 255);
}
/* 监管机构 */
#ZC-regiMain .regulator h4{
  color: #999;
  font-size: 16px;
  line-height: 28px;
}
#ZC-regiMain .regulator>p {
  color: #999;
  font-size: 16px;
  line-height: 28px;
}
</style>
