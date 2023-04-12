<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(4/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership4"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="partnership4"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.regulate.title') }}</h3> <!-- $i18n - 规管信息 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <!-- item -->
                  <div
                    v-for="(item,index) in partnership4.regulatoryInfoList"
                    :key="index"
                    class="isBtn"
                  >
                    <ul ref="istext">
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: item.isbtnStyle,fade:true}"
                          @click="item.isbtnStyle=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !item.isbtnStyle,fade:true}"
                          @click="item.isbtnStyle=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ item.dictDest }}</li>
                    </ul>
                    <!-- 1 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue==='foreignBank (established and operated outside the United States)'" class="BtnContent">
                      <div class="isBtn">
                        <ul>
                          <transition name="fade">
                            <li
                              class="isyes"
                              :class="{ dianji: item.isbtnStyle1,fade:true}"
                              @click="item.isbtnStyle1=true"
                            >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          </transition>
                          <transition name="fade">
                            <li
                              class="isno"
                              :class="{ dianji: !item.isbtnStyle1,fade:true}"
                              @click="item.isbtnStyle1=false"
                            >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          </transition>
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.minTitle1') }}</li> <!-- $i18n - 公司是否在以下任一国家所发银行许可下运营： -->
                        </ul>
                        <div
                          class="zhuce3ji"
                        >{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.describe1a') }}</div> <!-- $i18n - 阿富汗, 安哥拉, 阿尔巴 ... -->
                      </div>
                      <div class="isBtn">
                        <ul>
                          <transition name="fade">
                            <li
                              class="isyes"
                              :class="{ dianji: item.isbtnStyle2,fade:true}"
                              @click="item.isbtnStyle2=true"
                            >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          </transition>
                          <transition name="fade">
                            <li
                              class="isno"
                              :class="{ dianji: !item.isbtnStyle2,fade:true}"
                              @click="item.isbtnStyle2=false"
                            >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          </transition>
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.minTitle2') }}</li> <!-- $i18n - 公司是否持有“离岸银行许可”运营？ -->
                        </ul>
                        <div
                          class="zhuce3ji guiguan4"
                        >{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.describe2a') }}</div> <!-- $i18n - 离岸银行许可”是供许可持有实体办理银行 ... -->
                      </div>
                      <div class="isBtn">
                        <ul>
                          <transition name="fade">
                            <li
                              class="isyes"
                              :class="{ dianji: item.isbtnStyle3,fade:true}"
                              @click="item.isbtnStyle3=true"
                            >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          </transition>
                          <transition name="fade">
                            <li
                              class="isno"
                              :class="{ dianji: !item.isbtnStyle3,fade:true}"
                              @click="item.isbtnStyle3=false"
                            >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          </transition>
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.minTitle3') }}</li> <!-- $i18n - 公司保证如果开始使用下列银行 ... -->
                        </ul>
                        <div>
                          <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.describe3a') }}</li> <!-- $i18n - 为“离岸银行执照”（如上方定义） -->
                          <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.describe3b') }}</li> <!-- $i18n - 由被反洗钱金融行动特别工作组（FATF）认 ... -->
                          <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.foreign.describe3c') }}</li> <!-- $i18n - 由美国财政部部长指明为因洗钱问题必须特殊对待的外国签发的。 -->
                        </div>
                      </div>
                    </div>
                    <!-- 16 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue=='are its shares traded on regulated exchanges?Is the organization a publicly held entity?'" class="BtnContent">
                      <div v-if="item.isbtnStyle" class="top20 fade">
                        <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.entity.describe1a') }}</p> <!-- $i18n - 最多列出5家挂牌交易所、挂 ... -->
                        <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.entity.describe1b') }}</p> <!-- $i18n - 先列出主要挂牌交易所。 -->
                        <el-form-item
                          v-for="(item1,index1) in partnership4.big.jiaoyitiaomu15"
                          :key="index1"
                        >
                          <el-col :span="7">
                            <el-form-item
                              :prop="'big.jiaoyitiaomu15.'+index1+'.jiaoyisuoDaima'"
                              :rules="rules.jiaoyisuoDaima"
                            >
                              <el-input v-model="item1.jiaoyisuoDaima" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.entity.code')" /> <!-- $i18n - 代码 -->
                            </el-form-item>
                          </el-col>
                          <el-col class="line" :span="0.7">&nbsp;</el-col>
                          <el-col :span="7">
                            <el-form-item
                              :prop="'big.jiaoyitiaomu15.'+index1+'.jiaoyisuoName'"
                              :rules="rules.jiaoyisuoName"
                            >
                              <el-input v-model="item1.jiaoyisuoName" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.entity.bourse')" /> <!-- $i18n - 交易所 -->
                            </el-form-item>
                          </el-col>
                        </el-form-item>
                      </div>
                      <div
                        if="item.isbtnStyle"
                        class="addBtn"
                        style="    width: 960px;
                                       height: 41px;"
                      >
                        <div v-show="partnership4.big.jiaoyitiaomu15.length !== 5" class="add" @click="addElement(partnership4.big.jiaoyitiaomu15,'1')">{{ $t('registerOpenAccount.openAccount.common.regulate.entity.btnBourse') }}</div> <!-- $i18n - 添加交易所条目 -->
                        <div v-show="partnership4.big.jiaoyitiaomu15.length !== 1" class="cancel" @click="removeElement(partnership4.big.jiaoyitiaomu15)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                      </div>
                    </div>
                    <!-- 17 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue=='whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?'" class="BtnContent">
                      <transition name="fade">
                        <div v-if="item.isbtnStyle" class="fade">
                          <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.dealer.details') }}</p> <!-- $i18n - 提供详情: -->
                          <el-form-item prop="big.jiaoyitiaomu16.xiangqing">
                            <el-input
                              v-model="partnership4.big.jiaoyitiaomu16.xiangqing"
                              maxlength="64"
                              :placeholder="$t('registerOpenAccount.common.formPrompt.required')"
                            /> <!-- $i18n - 必填 -->
                          </el-form-item>
                        </div>
                      </transition>
                    </div>
                    <!-- 18 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue==='is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?'" class="BtnContent">
                      <transition name="fade">
                        <div v-if="item.isbtnStyle" class="fade">
                          <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.affiliatedPerson.name') }}</p> <!-- $i18n - 交易所名称: -->
                          <el-form-item prop="big.jiaoyitiaomu17.jiaoyisuoName">
                            <el-input
                              v-model="partnership4.big.jiaoyitiaomu17.jiaoyisuoName"
                              maxlength="30"
                              :placeholder="$t('registerOpenAccount.common.formPrompt.required')"
                            /> <!-- $i18n - 必填 -->
                          </el-form-item>
                        </div>
                      </transition>
                      <transition name="fade">
                        <div v-if="item.isbtnStyle" class="fade">
                          <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.affiliatedPerson.details') }}</p> <!-- $i18n - 提供详情: -->
                          <el-form-item prop="big.jiaoyitiaomu17.xiangqing">
                            <el-input
                              v-model="partnership4.big.jiaoyitiaomu17.xiangqing"
                              maxlength="64"
                              :placeholder="$t('registerOpenAccount.common.formPrompt.required')"
                            /> <!-- $i18n - 必填 -->
                          </el-form-item>
                        </div>
                      </transition>
                    </div>
                    <!-- 19 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue==='has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?'" class="BtnContent">
                      <transition name="fade">
                        <div v-if="item.isbtnStyle">
                          <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.arrest.details') }}</p> <!-- $i18n - 提供详情: -->
                          <el-form-item prop="big.jiaoyitiaomu18.xiangqing">
                            <el-input
                              v-model="partnership4.big.jiaoyitiaomu18.xiangqing"
                              maxlength="64"
                              :placeholder="$t('registerOpenAccount.common.formPrompt.required')"
                            /> <!-- $i18n - 必填 -->
                          </el-form-item>
                        </div>
                      </transition>
                    </div>
                    <!-- 20 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue==='are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?'" class="BtnContent">
                      <transition name="fade">
                        <div v-if="item.isbtnStyle" class="fade">
                          <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.regulate.decision.code') }}</p> <!-- $i18n - 输入公司的股票代码，以逗号隔开: -->
                          <el-form-item prop="big.jiaoyitiaomu19.daima">
                            <el-input v-model="partnership4.big.jiaoyitiaomu19.daima" maxlength="30" :placeholder="$t('registerOpenAccount.common.formPrompt.required')" /> <!-- $i18n - 必填 -->
                          </el-form-item>
                        </div>
                      </transition>
                    </div>
                    <!-- 21 -->
                    <div v-show="item.isbtnStyle" v-if="item.dictValue==='whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?'" class="BtnContent">
                      <transition name="fade">
                        <div v-if="item.isbtnStyle" class="fade">
                          <el-form-item prop="big.jiaoyitiaomu20.guzhuXiang">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.guzhuXiang" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.name')" /> <!-- $i18n - 公司 名称 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.state">
                            <el-select v-model="partnership4.big.jiaoyitiaomu20.state" :no-match-text="$t('account.manage.no_data')" maxlength="30" filterable :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.state')"> <!-- $i18n - 公司 国家/地区 -->
                              <dictionary dict-code="country" />
                            </el-select>
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.site">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.site" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.site')" /> <!-- $i18n - 公司 地址（如果空间不足，请转下行） -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.site1">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.site1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.site1')" /> <!-- $i18n - 公司 （接上行） -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.city">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.city" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.city')" /> <!-- $i18n - 公司 城市 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.province">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.province" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.province')" /> <!-- $i18n - 公司 省/市 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.postalCode">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.postalCode" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.postcode')" /> <!-- $i18n - 公司 邮政编码 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.postalAddress">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.postalAddress" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.email')" /> <!-- $i18n - 电邮地址 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.phoneNumber">
                            <el-input v-model="partnership4.big.jiaoyitiaomu20.phoneNumber" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.number')" /> <!-- $i18n - 电话号码 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.zhuceName">
                            <el-input
                              v-model="partnership4.big.jiaoyitiaomu20.zhuceName"
                              maxlength="30"
                              :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.registerName')"
                            /> <!-- $i18n - 注册人姓名 -->
                          </el-form-item>
                          <el-form-item prop="big.jiaoyitiaomu20.zhuceGuanxi">
                            <el-input
                              v-model="partnership4.big.jiaoyitiaomu20.zhuceGuanxi"
                              maxlength="30"
                              :placeholder="$t('registerOpenAccount.openAccount.common.regulate.employee.relation')"
                            /> <!-- $i18n - 注册人与机构的关联/关系 -->
                          </el-form-item>
                        </div>
                      </transition>
                    </div>
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
                      @click="submitForm('partnership4')"
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
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 联系信息  -- 邮箱
    var validateLXyouxiang = (rule, value, callback) => {
      this.$verify.Postbox(rule, value, callback);
    };
    // 联系电话
    var validateLXdianhua = (rule, value, callback) => {
      if (value !== '') {
        if (value.length >= 6) {
          this.$verify.Number(rule, value, callback);
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.register.signIn.PhoneNumber'))); // $i18n - 手机号个数不能小于6位
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.verify.phone'))); // $i18n - 请填入手机号
      }
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      // afdsa:"",
      partnership4: {
        newData: null,
        regulatoryInfoList: [
          // {
          //   dictDest:"外国银行（在美国以外成立及运营）",
          //   dictValue:"133"
          // },
        ],
        big: {
          jiaoyitiaomu15: [
            {
              jiaoyisuoDaima: '',
              jiaoyisuoName: ''
            }
          ],
          jiaoyitiaomu16: {
            xiangqing: ''
          },
          jiaoyitiaomu17: {
            jiaoyisuoName: '',
            xiangqing: ''
          },
          jiaoyitiaomu18: {
            xiangqing: ''
          },
          jiaoyitiaomu19: {
            daima: ''
          },
          jiaoyitiaomu20: {
            guzhuXiang: '',
            state: '',
            site: '',
            site1: '',
            city: '',
            province: '',
            postalCode: '',
            postalAddress: '',
            phoneNumber: '',
            zhuceName: '',
            zhuceGuanxi: ''
          }
        }
      },
      newValue: {
        accountParInstitutional: null
      },
      rules: {
        // 16
        jiaoyisuoDaima: [
          { validator: notChinese, trigger: 'blur' }
        ],
        jiaoyisuoName: [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 17
        'big.jiaoyitiaomu16.xiangqing': [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 18
        'big.jiaoyitiaomu17.jiaoyisuoName': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu17.xiangqing': [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 19
        'big.jiaoyitiaomu18.xiangqing': [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 20
        'big.jiaoyitiaomu19.daima': [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 21
        'big.jiaoyitiaomu20.guzhuXiang': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.state': [
          { validator: notChinese, trigger: 'change' }
        ],
        'big.jiaoyitiaomu20.site': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.site1': [
          { validator: notChinese1, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.city': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.province': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.postalCode': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.postalAddress': [
          { validator: validateLXyouxiang, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.phoneNumber': [
          { validator: validateLXdianhua, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.zhuceName': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'big.jiaoyitiaomu20.zhuceGuanxi': [
          { validator: notChinese, trigger: 'blur' }
        ]

      }
    };
  },
  created() {
    this.regulatoryInfo();
  },
  mounted() {

  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountCoRegulatory() {
      this.$http({
        url: '/account/accountParRegulatory/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.accountParInstitutional = res.object;
              let infoDetails = null;
              for (let i = 0; i < res.object.length; i++) {
                this.partnership4.regulatoryInfoList.forEach(item => {
                  if (item.dictValue === res.object[i].infoType) {
                    item.dictValue = res.object[i].infoType;
                    item.isbtnStyle = res.object[i].whether;
                    if (res.object[i].infoDetails !== '') {
                      if (res.object[i].infoType === 'foreignBank (established and operated outside the United States)') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        item.isbtnStyle1 = infoDetails.min1;
                        item.isbtnStyle2 = infoDetails.min2;
                        item.isbtnStyle3 = infoDetails.min3;
                      } else if (res.object[i].infoType === 'are its shares traded on regulated exchanges?Is the organization a publicly held entity?') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        this.partnership4.big.jiaoyitiaomu15 = infoDetails.jiaoyitiaomu15;
                      } else if (res.object[i].infoType === 'whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        this.partnership4.big.jiaoyitiaomu16.xiangqing = infoDetails.xiangqing;
                      } else if (res.object[i].infoType === 'is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        this.partnership4.big.jiaoyitiaomu17.jiaoyisuoName = infoDetails.jiaoyisuoName;
                        this.partnership4.big.jiaoyitiaomu17.xiangqing = infoDetails.xiangqing;
                      } else if (res.object[i].infoType === 'has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        this.partnership4.big.jiaoyitiaomu18.xiangqing = infoDetails.xiangqing;
                      } else if (res.object[i].infoType === 'are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        this.partnership4.big.jiaoyitiaomu19.daima = infoDetails.daima;
                      } else if (res.object[i].infoType === 'whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?') {
                        infoDetails = JSON.parse(res.object[i].infoDetails);
                        this.partnership4.big.jiaoyitiaomu20.guzhuXiang = infoDetails.guzhuXiang;
                        this.partnership4.big.jiaoyitiaomu20.state = infoDetails.state;
                        this.partnership4.big.jiaoyitiaomu20.site = infoDetails.site;
                        this.partnership4.big.jiaoyitiaomu20.site1 = infoDetails.site1;
                        this.partnership4.big.jiaoyitiaomu20.city = infoDetails.city;
                        this.partnership4.big.jiaoyitiaomu20.province = infoDetails.province;
                        this.partnership4.big.jiaoyitiaomu20.postalCode = infoDetails.postalCode;
                        this.partnership4.big.jiaoyitiaomu20.postalAddress = infoDetails.postalAddress;
                        this.partnership4.big.jiaoyitiaomu20.phoneNumber = infoDetails.phoneNumber;
                        this.partnership4.big.jiaoyitiaomu20.zhuceName = infoDetails.zhuceName;
                        this.partnership4.big.jiaoyitiaomu20.zhuceGuanxi = infoDetails.zhuceGuanxi;
                      }
                    }
                  }
                });
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
    // -- 规管信息
    regulatoryInfo() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'regulatoryInfo', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            for (let i = 0; i < res.object.length; i++) {
              res.object[i].isbtnStyle = false;
              res.object[i].isbtnStyle1 = false;
              res.object[i].isbtnStyle2 = false;
              res.object[i].isbtnStyle3 = false;
            }
            this.partnership4.regulatoryInfoList = JSON.parse(
              JSON.stringify(res.object)
            );
            this.accountCoRegulatory();
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
    // 添加元素
    // index 识别号  --手动判断更改obj的值
    // arr 需要操作的数组
    addElement(arr, index) {
      let obj = null;
      if (index === '1') {
        // 第二部分 添加功能
        obj = {
          jiaoyisuoDaima: '',
          jiaoyisuoName: ''
        };
      }

      arr.push(JSON.parse(JSON.stringify(obj)));
    },
    // 删除元素
    // arr 需要操作的数组
    removeElement(arr) {
      if (arr.length === 1) {
        return false;
      } else {
        arr.pop();
      }
    },
    // 验证表单 跳转页面
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
          // this.$message.error('成功');
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

      let data = []; // 1.公司规管信息
      // newAccountCoPersonal:null,
      let infoDetails = null;
      if (this.newValue.accountParInstitutional === null) {
        // 1.公司规管信息  //暂定
        for (let i = 0; i < this.partnership4.regulatoryInfoList.length; i++) {
          if (this.partnership4.regulatoryInfoList[i].dictValue === 'foreignBank (established and operated outside the United States)') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  min1: this.partnership4.regulatoryInfoList[i].isbtnStyle1,
                  min2: this.partnership4.regulatoryInfoList[i].isbtnStyle2,
                  min3: this.partnership4.regulatoryInfoList[i].isbtnStyle3
                }
              ) : null;
          } else if (this.partnership4.regulatoryInfoList[i].dictValue === 'are its shares traded on regulated exchanges?Is the organization a publicly held entity?') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  jiaoyitiaomu15: this.partnership4.big.jiaoyitiaomu15
                }
              ) : null;
          } else if (this.partnership4.regulatoryInfoList[i].dictValue === 'whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  xiangqing: this.partnership4.big.jiaoyitiaomu16.xiangqing
                }
              ) : null;
          } else if (this.partnership4.regulatoryInfoList[i].dictValue === 'is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  jiaoyisuoName: this.partnership4.big.jiaoyitiaomu17.jiaoyisuoName,
                  xiangqing: this.partnership4.big.jiaoyitiaomu17.xiangqing
                }
              ) : null;
          } else if (this.partnership4.regulatoryInfoList[i].dictValue === 'has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  xiangqing: this.partnership4.big.jiaoyitiaomu18.xiangqing
                }
              ) : null;
          } else if (this.partnership4.regulatoryInfoList[i].dictValue === 'are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  daima: this.partnership4.big.jiaoyitiaomu19.daima
                }
              ) : null;
          } else if (this.partnership4.regulatoryInfoList[i].dictValue === 'whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?') {
            infoDetails = this.partnership4.regulatoryInfoList[i].isbtnStyle
              ? JSON.stringify(
                {
                  guzhuXiang: this.partnership4.big.jiaoyitiaomu20.guzhuXiang,
                  state: this.partnership4.big.jiaoyitiaomu20.state,
                  site: this.partnership4.big.jiaoyitiaomu20.site,
                  site1: this.partnership4.big.jiaoyitiaomu20.site1,
                  city: this.partnership4.big.jiaoyitiaomu20.city,
                  province: this.partnership4.big.jiaoyitiaomu20.province,
                  postalCode: this.partnership4.big.jiaoyitiaomu20.postalCode,
                  postalAddress: this.partnership4.big.jiaoyitiaomu20.postalAddress,
                  phoneNumber: this.partnership4.big.jiaoyitiaomu20.phoneNumber,
                  zhuceName: this.partnership4.big.jiaoyitiaomu20.zhuceName,
                  zhuceGuanxi: this.partnership4.big.jiaoyitiaomu20.zhuceGuanxi
                }
              ) : null;
          }
          data.push(
            {
              accountId,
              createdBy,
              createdDate,
              // id: this.partnership4.regulatoryInfoList[i].id,
              infoType: this.partnership4.regulatoryInfoList[i].dictValue,
              whether: this.partnership4.regulatoryInfoList[i].isbtnStyle,
              infoDetails
            }
          );
          infoDetails = null;
        }
      } else {
        for (let i = 0; i < this.newValue.accountParInstitutional.length; i++) {
          this.partnership4.regulatoryInfoList.forEach(item => {
            if (this.newValue.accountParInstitutional[i].infoType === item.dictValue) { // 如果项的value对应，说明id对应上每一项了
              this.newValue.accountParInstitutional[i].whether = item.isbtnStyle;
              if (this.newValue.accountParInstitutional[i].infoType === 'foreignBank (established and operated outside the United States)' &&
                    item.dictValue === 'foreignBank (established and operated outside the United States)'
              ) { // vlaue 如果是有子表单的 额外处理
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      min1: item.isbtnStyle1,
                      min2: item.isbtnStyle2,
                      min3: item.isbtnStyle3
                    }
                  ) : null;
              } else if (this.newValue.accountParInstitutional[i].infoType === 'are its shares traded on regulated exchanges?Is the organization a publicly held entity?' &&
                    item.dictValue === 'are its shares traded on regulated exchanges?Is the organization a publicly held entity?'
              ) {
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      jiaoyitiaomu15: this.partnership4.big.jiaoyitiaomu15
                    }
                  ) : null;
              } else if (this.newValue.accountParInstitutional[i].infoType === 'whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?' &&
                    item.dictValue === 'whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?'
              ) {
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      xiangqing: this.partnership4.big.jiaoyitiaomu16.xiangqing
                    }
                  ) : null;
              } else if (this.newValue.accountParInstitutional[i].infoType === 'is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?' &&
                    item.dictValue === 'is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?'
              ) {
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      jiaoyisuoName: this.partnership4.big.jiaoyitiaomu17.jiaoyisuoName,
                      xiangqing: this.partnership4.big.jiaoyitiaomu17.xiangqing
                    }
                  ) : null;
              } else if (this.newValue.accountParInstitutional[i].infoType === 'has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?' &&
                    item.dictValue === 'has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?'
              ) {
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      xiangqing: this.partnership4.big.jiaoyitiaomu18.xiangqing
                    }
                  ) : null;
              } else if (this.newValue.accountParInstitutional[i].infoType === 'are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?' &&
                     item.dictValue === 'are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?'
              ) {
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      daima: this.partnership4.big.jiaoyitiaomu19.daima
                    }
                  ) : null;
              } else if (this.newValue.accountParInstitutional[i].infoType === 'whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?' &&
                    item.dictValue === 'whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?'
              ) {
                this.newValue.accountParInstitutional[i].infoDetails = item.isbtnStyle
                  ? JSON.stringify(
                    {
                      guzhuXiang: this.partnership4.big.jiaoyitiaomu20.guzhuXiang,
                      state: this.partnership4.big.jiaoyitiaomu20.state,
                      site: this.partnership4.big.jiaoyitiaomu20.site,
                      site1: this.partnership4.big.jiaoyitiaomu20.site1,
                      city: this.partnership4.big.jiaoyitiaomu20.city,
                      province: this.partnership4.big.jiaoyitiaomu20.province,
                      postalCode: this.partnership4.big.jiaoyitiaomu20.postalCode,
                      postalAddress: this.partnership4.big.jiaoyitiaomu20.postalAddress,
                      phoneNumber: this.partnership4.big.jiaoyitiaomu20.phoneNumber,
                      zhuceName: this.partnership4.big.jiaoyitiaomu20.zhuceName,
                      zhuceGuanxi: this.partnership4.big.jiaoyitiaomu20.zhuceGuanxi
                    }
                  ) : null;
              }
            }
          });
        }
        data = this.newValue.accountParInstitutional;
      }
      this.$http({
        url: '/openAccount/partnerShip/saveFour',
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
      this.$verify.routerPermissions({ path: '/openAccount/partnership3' });
    }
  }
};
</script>

<style scoped>
.lineHeightBD20 > li {
  list-style: disc;
}

#ZC-regiMain .formNapeContent .isBtn ul {
  position: relative;
  /* margin-bottom:55px; */
}
#ZC-regiMain .formNapeContent .isBtn ul > li:nth-child(1) {
  position: absolute;
  /* left: 112px; */
  z-index: 1;
}
#ZC-regiMain .formNapeContent .isBtn ul > li:nth-child(2) {
  position: absolute;
  left: 112px;
  z-index: 1;
}
#ZC-regiMain .formNapeContent .isBtn ul > li:nth-child(3) {
  text-indent: 228px;
  position: relative;
}
/* #ZC-regiMain .formNapeContent .isBtn .BtnContent{
  width: 100px;
  height: 200PX;
  background-color: aqua;
} */
#ZC-regiMain .formNapeContent .isBtn .Marbottom {
  margin-bottom: 55px;
}
</style>
