<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(3/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person3"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="person3"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- 替代 表格 W-8BEN -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.person.person3.title.title') }}</h3> <!-- $i18n 替代 表格 W-8BEN -->
                </div>
                <div class="formNapeContent">
                  <p class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.person.person3.title.describe') }}</p> <!-- $i18n - 在美预扣税与报告受益人外籍身份证明（个人） -->
                  <ul>
                    <li class="zhuce3ji huifu">{{ $t('registerOpenAccount.openAccount.person.person3.title.describe1a') }}</li> <!-- $i18n 供非美国税务居民的个人使用。非美国 . . . -->
                    <li class="zhuce3ji huifu">{{ $t('registerOpenAccount.openAccount.person.person3.title.describe1b') }}</li> <!-- $i18n 有关W-8BEN表格的信息及说明参见ww . . . -->
                  </ul>
                  <div class="divder" />
                  <div class="formHint">
                    <ul>
                      <li>{{ $t('registerOpenAccount.openAccount.person.person3.title.Hint') }}</li> <!-- $i18n 您的信息可能被分享给账户所在国的税务 . . . -->
                    </ul>
                    <div class="gantanhao">!</div>
                  </div>
                </div>
              </div>
              <!-- 第一部分：受益所有人身份-->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.person.person3.identity.title') }}</h3> <!-- $i18n 第一部分：受益所有人身份 -->
                <!-- <p>替代 表格 W-8BEN</p> -->
                </div>
                <div class="formNapeContent">
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.name')"> <!-- $i18n 1. 个人受益方姓名 -->
                    <el-input v-model="person3.GeRenSYFname" maxlength="30" :disabled="true" />
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.nationality')"> <!-- $i18n 2. 国籍 -->
                    <el-select v-model="person3.nationality" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.common.formPrompt.state')"> <!-- $i18n 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.specific1')" style="margin-bottom: 7px;">  <!-- $i18n 3. 永久居住地址（街道、公寓或房 . . . -->
                    <el-input v-model="person3.YJplace" maxlength="64" :disabled="true" />
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.specific2')" style="    margin-bottom: 3px;"> <!-- $i18n 城市或城镇，州或省。包括其对应的邮政编码。 -->
                    <el-input v-model="person3.jutizhuzhi" maxlength="64" :disabled="true" />
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.state')">  <!-- $i18n 国家 -->
                    <el-select v-model="person3.SYnation" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.common.formPrompt.state')">  <!-- $i18n 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.mailingAddress1')" style="margin-bottom: 7px;" prop="mailing1">  <!-- $i18n 4. 邮寄地址(若与上方所填地址不同，请填写) -->
                    <el-input v-model="person3.mailing1" maxlength="64" :disabled="true" />
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.mailingAddress2')" style="margin-bottom: 3px;" prop="jutizhuzhi1">  <!-- $i18n 城市或城镇，州或省。包括其对应的邮政编码。 -->
                    <el-input v-model="person3.jutizhuzhi1" maxlength="64" :disabled="true" />
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.state')" prop="SYnation1">  <!-- $i18n 国家 -->
                    <el-select v-model="person3.SYnation1" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.common.formPrompt.state')">
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.taxpayerHaoma')" prop="taxpayerHaoma">  <!-- $i18n 5. 美国纳税人识别号码（SSN 或 ITIN），如有要求（参见说明） -->
                    <el-input v-model="person3.taxpayerHaoma" maxlength="30" :placeholder="$t('registerOpenAccount.common.formPrompt.choosable')" />  <!-- $i18n 选填 -->
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.overseasHaoma')" prop="OverseasHaoma"> <!-- $i18n 6. 海外税收识别号码（参见说明） -->
                    <el-input v-model="person3.OverseasHaoma" maxlength="30" :disabled="true" :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseFillIn')" />  <!-- $i18n 请填写 -->
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.explain')" prop="explain">  <!-- $i18n 7. 参考编号(参见说明) -->
                    <el-input v-model="person3.explain" maxlength="30" :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseFillIn')" />  <!-- $i18n 请填写 -->
                  </el-form-item>
                  <el-form-item :label="$t('registerOpenAccount.openAccount.person.person3.identity.dateBirth')">  <!-- $i18n 8. 出生日期（月份-日期-年份）（参见说明） -->
                    <el-input
                      v-model="person3.dateBirth"
                      maxlength="30"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.common.formPrompt.optional')"
                    />  <!-- $i18n 选填 -->
                  </el-form-item>
                </div>
              </div>
              <!-- 第二部分：美国税收协定优惠待遇声明 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.person.person3.treatment.title') }}</h3> <!-- $i18n 第二部分：美国税收协定优惠待遇声明 -->
                <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="zhuce2ji bottom10">{{ $t('registerOpenAccount.openAccount.person.person3.treatment.describe1a') }}</div> <!-- $i18n 9. 本人确认，受益所有人在美国与所属国的税收协定下，符合 -->
                  <el-form-item prop="conformToguojia">
                    <el-select v-model="person3.conformToguojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"> <!-- $i18n 请选择 -->
                      <dictionary dict-code="country" />
                    </el-select>
                    <div class="zhuce2ji top10">{{ $t('registerOpenAccount.openAccount.person.person3.treatment.describe1b') }}</div> <!-- $i18n 的居民定义。-->
                  </el-form-item>
                </div>
                <!-- 显示隐藏 -->
                <div v-if="person3.ifDefine" class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.person.person3.treatment.describe2') }}</div> <!-- $i18n 我们的记录显示您的一个地址不在上方 . . . -->
                <el-form-item v-show="person3.ifDefine" id="radioInline" label prop="DZexplain">
                  <el-radio-group v-model="person3.DZexplain">
                    <el-radio
                      v-for="(item1,index1) in person3.Thetaxnumber"
                      :key="index1"
                      style="margin-bottom:5px;"
                      :label="item1.dictValue"
                      class="radio"
                    >{{ item1.dictDest }}</el-radio>
                    <el-radio label="qita">
                      <input v-model="person3.DZqita" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.else')"> <!-- $i18n 其他-->
                    </el-radio>
                  </el-radio-group>
                </el-form-item>
              </div>
              <!-- 第三部分：声明 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.person.person3.statement.title') }}</h3> <!-- $i18n 第三部分：声明 -->
                <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <span style="line-height: 26px; ">
                    <p class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1a') }}</p> <!-- $i18n 查表格中的信息，据本人所知及所信其真实、准确且完整。根据伪证罪处罚条款，本人进一步证明 -->
                    <ul>
                      <li class="zhuce2ji huifu">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1b') }}</li> <!-- $i18n 本人是本表格相关之收入的受益所有人（或被授权为受益所有人签字），或 ... -->
                      <li class="zhuce2ji huifu">
                        <span class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1c') }}</span> <!-- $i18n 本表格相关收入 -->
                        <br>
                        <span class="zhuce3ji lineHeightBD29">&bull; {{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1d') }}</span> <!-- $i18n 与在美的贸易或商业活动无关， -->
                        <br>
                        <span class="zhuce3ji lineHeightBD29">&bull; {{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1e') }}</span> <!-- $i18n 虽为相关收入，但根据所得税协定不需要交纳税费，或者 -->
                        <br>
                        <span class="zhuce3ji lineHeightBD29">&bull; {{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1f') }}</span> <!-- $i18n 为合伙企业相关收入的合伙人收入， -->
                      </li>
                      <li class="zhuce2ji huifu">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1g') }}</li> <!-- $i18n 该表格第1行填写的人士是表格第9行（如有）所列协定国的居民，属于 ... -->
                      <li class="zhuce2ji huifu">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1h') }}</li> <!-- $i18n 对于经纪交易或易货交易，受益所有人为国税局（IRS）W-8BEN表格说明中定义的豁免外籍人士。 -->
                      <li class="zhuce2ji huifu jiacu">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1i') }}</li> <!-- $i18n 本人同意，如果此表格内的证明信息不再准确，本人会在30天内提交一份新的表格。 -->
                    </ul>
                  </span>
                  <div class="top30 ">
                    <p class="zhuce2ji" style="margin-bottom: 10px;">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1j') }}</p> <!-- $i18n 本人同意，上述每一项声明均准确无误，如有 ... -->
                    <el-form-item label prop="consent1">
                      <el-radio-group v-model="person3.consent1">
                        <el-radio label="1">{{ $t('registerOpenAccount.common.btn.yes') }}</el-radio> <!-- $i18n 是 -->
                        <el-radio label="0">{{ $t('registerOpenAccount.common.btn.no') }}</el-radio> <!-- $i18n 否 -->
                      </el-radio-group>
                    </el-form-item>
                    <p class="zhuce2ji" style="margin-bottom:  10px;">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1k') }}</p> <!-- $i18n 本人同意所有税务表 ... -->
                    <el-form-item label prop="consent2">
                      <el-radio-group v-model="person3.consent2">
                        <el-radio label="1">{{ $t('registerOpenAccount.common.btn.yes') }}</el-radio> <!-- $i18n 是 -->
                        <el-radio label="0">{{ $t('registerOpenAccount.common.btn.no') }}</el-radio> <!-- $i18n 否 -->
                      </el-radio-group>
                    </el-form-item>

                    <p class="zhuce3ji">
                      {{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1l') }}
                      <br> <!-- $i18n 如果您无法提供任何一 ... -->
                      <span class="zhuce3ji jiacu">{{ $t('registerOpenAccount.openAccount.person.person3.statement.describe1m') }}</span> <!-- $i18n 除了用以确立您非美国 ... -->
                    </p>
                  </div>
                  <div class="divder" />
                  <!-- 签名 -->
                  <div>
                    <h4 class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</h4> <!-- $i18n 签名 -->
                    <div
                      class="zhuce3ji"
                    >{{ $t('registerOpenAccount.openAccount.common.signature.signatureDB') }}</div> <!-- $i18n 输入签名并通过网络发送即代表您(i)确认该表格内的 ... -->
                    <div class="zhuce2ji">
                      <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</span> <!-- $i18n 账户名称 -->
                      {{ person3.zhanghuName }}
                      <br>
                      <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</span> <!-- $i18n 日期 -->
                      {{ person3.riqi }}
                    </div>
                  </div>
                  <div class="signature" style="margin-top: 60px;">
                    <ul class="bottom10 clearfix">
                      <li class="left1 right10">{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                      <li class="left1">{{ person3.signatureZhanshi }}</li>
                    </ul>
                    <el-form-item prop="signature">
                      <el-input v-model="person3.signature" maxlength="128" />
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('person3')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
import dictionary from '../../../../../components/dictionary/dictionary';
export default {
  components: {
    dictionary
  },
  data() {
    // 非中文验证
    var notChinese = (rule, value, callback) => {
      if (value !== '') {
        const _rule = rule;
        const _callback = callback;
        this.$verify.notChinese(_rule, value, _callback);
      } else {
        callback();
      }
    };
    var validatorTaxpayerHaoma = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 表单验证
    var validateConsent1 = (rule, value, callback) => {
      if (value === '1') {
        callback();
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint'))); // $i18n - 您必须选择是或确定才能继续
      }
    };
    var validateConsent2 = (rule, value, callback) => {
      if (value === '1') {
        callback();
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint'))); // $i18n - 您必须选择是或确定才能继续
      }
    };
    var validateSignature = (rule, value, callback) => {
      if (value !== '') {
        if (this.person3.signatureZhanshi === this.person3.signature) {
          callback();
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.signatureHint1'))); // $i18n - 签名不一致
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };
    var validateDefine = (rule, value, callback) => {
      const _this = this;
      if (value !== '') {
        if (this.$store.state.user.applyData.xinxi.country === 'CN' && value === 'CN') {
          this.person3.ifDefine = false;
          callback();
        } else {
          this.$verify.State(value, this.person3.Thetaxnumber, this.person3.ThetaxnumberList, _this, this.lang);
          this.person3.ifDefine = true;
          callback();
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };
    var validateConsentQita = (rule, value, callback) => {
      if (this.person3.ifDefine) {
        if (value !== '') {
          if (value === 'qita') {
            if (this.person3.DZqita !== '') {
              callback();
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.elseHint'))); // $i18n - 您已选择其他,请在输入框输 ...
            }
          } else {
            callback();
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else {
        callback();
      }
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      person3: {
        // 第一部分：受益所有人身份
        GeRenSYFname: '',
        nationality: '',
        YJplace: '',
        jutizhuzhi: '',
        SYnation: '',
        mailing1: '',
        jutizhuzhi1: '',
        SYnation1: '',
        taxpayerHaoma: '',
        OverseasHaoma: '',
        explain: '',
        dateBirth: '',
        // 第二部分：美国税收协定优惠待遇声明
        conformToguojia: '',
        conformToguojia1: '',
        DZexplain: '',
        DZqita: '',
        state: '',
        ifDefine: true,
        Thetaxnumber: [],
        ThetaxnumberList: [],
        consent1: '',
        consent2: '',
        zhanghuName: '',
        riqi: '',
        signatureZhanshi: '',
        signature: ''
      },
      newValue: null,
      rules: {
        mailing1: [{ validator: notChinese, trigger: 'change' }],
        jutizhuzhi1: [{ validator: notChinese, trigger: 'change' }],
        SYnation1: [{ validator: notChinese, trigger: 'change' }],
        taxpayerHaoma: [{ validator: validatorTaxpayerHaoma, trigger: 'blur' }],
        OverseasHaoma: [{ validator: validatorTaxpayerHaoma, trigger: 'blur' }],
        explain: [{ validator: notChinese, trigger: 'change' }],

        conformToguojia: [
          { validator: validateDefine, trigger: 'change' }
        ],
        DZexplain: [{ validator: validateConsentQita, trigger: 'change' }],
        consent1: [
          { validator: validateConsent1, trigger: 'change' }
        ],
        consent2: [
          { validator: validateConsent2, trigger: 'change' }
        ],
        signature: [
          { validator: validateSignature, trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    // 获取之前页面数据填入至页面
    this.personal();
    this.agtPersonalAddress();
    this.getPersonaltaxresidence();
    // 获取字典表
    this.getdictionary();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取之前页面数据填入至页面
    personal() { // 个人信息
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
            if (this.$verify.resStatus(res)) {
              // 姓名
              this.person3.GeRenSYFname = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person3.signatureZhanshi = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person3.zhanghuName = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person3.signature = res.object[0].lastName + ' ' + res.object[0].firstName;
              this.person3.riqi = this.$verify.NowDate3();
              this.person3.dateBirth = res.object[0].birthday;
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
    agtPersonalAddress() { // 个人地址
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
                  this.person3.nationality = res.object[i].country;
                  this.person3.SYnation = res.object[i].country;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const siteTS = res.object[i].address.split('-#-');
                    this.person3.YJplace = siteTS[0] + ' ' + siteTS[1];
                  }
                  this.person3.jutizhuzhi = res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode;
                } else if (res.object[i].addressType === 'mail') {
                  this.person3.SYnation1 = res.object[i].country;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const siteTS = res.object[i].address.split('-#-');
                    this.person3.mailing1 = siteTS[0] + ' ' + siteTS[1];
                  }
                  this.person3.jutizhuzhi1 = res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode;
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
    yetFillingIn() {
      this.$http({
        url: '/account/accountPerWbenContract/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = JSON.parse(JSON.stringify(res.object[0]));
              // 第一部分
              // this.person3.mailing1 = res.object[0].emailAddress;
              // this.person3.jutizhuzhi1 = res.object[0].mailCode;
              // this.person3.SYnation1 = res.object[0].otherNationality;
              this.person3.taxpayerHaoma = res.object[0].usaItin;
              this.person3.OverseasHaoma = res.object[0].overseasTaxNumber;
              this.person3.explain = res.object[0].referenceNumber;
              // 第二部分
              let status = 0;
              this.person3.conformToguojia = res.object[0].usaCountryAgreement;
              if (res.object[0].explainAddress !== '') {
                for (let i = 0; i < this.person3.ThetaxnumberList.length; i++) {
                  if (res.object[0].explainAddress === this.person3.ThetaxnumberList[i].dictValue) {
                    status++;
                  }
                }
                if (status === 0) {
                  this.person3.DZexplain = 'qita';
                  this.person3.DZqita = res.object[0].explainAddress;
                } else {
                  this.person3.DZexplain = res.object[0].explainAddress;
                }
              }
              // 第三部分
              this.person3.consent1 = res.object[0].consentStatus;
              this.person3.consent2 = res.object[0].consentTax;
              this.person3.GeRenSYFname = res.object[0].signature;
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
    // 获取个人税务居住地信息
    getPersonaltaxresidence() {
      this.$http({
        url: '/account/accountPerTaxes/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].taxResidenceCountry === 'CN' || res.object[0].taxResidenceCountry === 'HK' || res.object[0].taxResidenceCountry === 'MO' || res.object[0].taxResidenceCountry === 'TW') {
                this.getPersonalidentifiable();
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
    // 获取个人身份证明信息
    getPersonalidentifiable() {
      this.$http({
        url: '/account/accountPerIdentity/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person3.OverseasHaoma = res.object[0].idNumber;
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
    // 获取税务编号
    getdictionary() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'taxIdentificationNumber',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            this.person3.Thetaxnumber = res.object;
            this.person3.ThetaxnumberList = JSON.parse(JSON.stringify(res.object));
            this.yetFillingIn();
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
    submitForm(formName) {
      // this.$verify.routerPermissions({ path: '/openAccount/person4' });
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
        } else {
          return false;
        }
      });
    },
    // 提交表单数据
    submitFormData() {
      const updatedBy = this.$store.state.user.applyData.xinxi.id; // 账户id  更新人
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id; // 账户id  创建人
      const accountName = this.$store.state.user.applyData.xinxi.accountName; // 账户名
      const createdDate = this.$verify.NowDate(); // 创建时间
      const signatureTime = this.$verify.NowDate(); // 签名时间
      let data = null;
      let explainAddress = null;
      if (this.person3.DZexplain === 'qita') {
        explainAddress = this.person3.DZqita;
      } else {
        explainAddress = this.person3.DZexplain;
      }
      if (this.newValue == null) {
        data = {
          accountId,
          accountName,
          createdBy,
          createdDate,
          updatedBy,
          // 第一部分
          emailAddress: this.person3.mailing1,
          mailCode: this.person3.jutizhuzhi1,
          otherNationality: this.person3.SYnation1,
          usaItin: this.person3.taxpayerHaoma,
          overseasTaxNumber: this.person3.OverseasHaoma,
          referenceNumber: this.person3.explain,
          otherBirthday: this.person3.dateBirth,
          // 第二部分
          usaCountryAgreement: this.person3.conformToguojia,
          explainAddress,
          // 第三部分
          consentStatus: this.person3.consent1,
          consentTax: this.person3.consent2,
          signature: this.person3.GeRenSYFname,
          signatureTime
        };
      } else {
        this.newValue.emailAddress = this.person3.mailing1;
        this.newValue.mailCode = this.person3.jutizhuzhi1;
        this.newValue.otherNationality = this.person3.SYnation1;
        this.newValue.usaItin = this.person3.taxpayerHaoma;
        this.newValue.overseasTaxNumber = this.person3.OverseasHaoma;
        this.newValue.referenceNumber = this.person3.explain;
        this.newValue.otherBirthday = this.person3.dateBirth;
        this.newValue.usaCountryAgreement = this.person3.conformToguojia;
        this.newValue.explainAddress = explainAddress;
        this.newValue.consentStatus = this.person3.consent1;
        this.newValue.consentTax = this.person3.consent2;
        this.newValue.signature = this.person3.GeRenSYFname;
        data = this.newValue;
      }
      // 数据请求
      this.$http({
        url: '/openAccount/personal/saveThree',
        method: 'put',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person4' });
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
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/person2' });
    }

  }
};
</script>

<style  scoped>
.form-content.deal {
  padding: 20px;
  border: 1px solid #000;
}
.jiaoyi {
  height: 20px;
  width: 100%;
  line-height: 14px;
  margin-bottom: 20px;
}
.jiaoyi:hover {
  color: red;
  cursor: pointer;
}
.jiaoyi span {
  float: left;
}
.jiaoyi i {
  float: right;
}
.bottom-xian {
  /* margin: 47px -1px; */
  overflow: hidden;
  background-color: #f0f0f0;
  border-bottom: 1px solid #f3f3f3;
  margin-bottom: 10px;
}
.deal-text {
  font-size: 12px;
  color: #606266;
  height: 28px;
}

/* 表单label 设置 */
.form-content div:nth-child(n + 2) .el-form-item__label {
  line-height: 22px;
}
.form-content div.signature .el-form-item__label {
  line-height: 120px;
}
#qianmingTop {
    padding-top: 12px;
    position: absolute;
    padding-left: 40px;
    margin-bottom: 13px;
}
</style>

