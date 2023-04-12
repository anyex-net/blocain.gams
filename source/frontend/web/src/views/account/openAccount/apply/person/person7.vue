<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(7/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person7"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="person7"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- main-top -->
              <div class="formNapeCRS">
                <h3>{{ $t('registerOpenAccount.openAccount.person.crs.top.title') }}</h3> <!-- $i18n - 共同汇报标准（CRS）个人自我证明表格（CRS-I） -->
                <div class="topContentCRS">
                  <h4>{{ $t('registerOpenAccount.openAccount.person.crs.top.describe1') }}</h4> <!-- $i18n - 客户须知：填写此表格前 ，请先阅读本节。 -->
                  <p>{{ $t('registerOpenAccount.openAccount.person.crs.top.describe2') }}</p> <!-- $i18n - 作为财务机构，我们是不允许给予客户税务咨询 。 -->
                  <p class="pCRS2">{{ $t('registerOpenAccount.openAccount.person.crs.top.describe3') }}</p> <!-- $i18n - 如果您对此表格或您的税务居民身份定义有 ... -->
                  <p class="pCRS2 wangzhiCRS">
                    (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                  </p>
                  <p class="pCRS3">{{ $t('registerOpenAccount.openAccount.person.crs.top.describe5') }}</p> <!-- $i18n - 请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。 -->
                  <p class="pCRS3">{{ $t('registerOpenAccount.openAccount.person.crs.top.describe6') }}</p> <!-- $i18n - 您可能会被要求提供更多证明文件，以核实此表格上之内容。 -->
                  <p
                    class="pCRS3 lineHeightBD22"
                  >{{ $t('registerOpenAccount.openAccount.person.crs.top.describe7') }}</p> <!-- $i18n - 这是由账户持有人向申报财务机构提供的自 ... -->
                  <p class="pCRS3">{{ $t('registerOpenAccount.openAccount.person.crs.top.describe8') }}</p> <!-- $i18n - 除不适用或特别注明外，必须填写这份表格 ... -->
                </div>
              </div>
              <!-- main-content1 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.person.crs.part1.title') }}</div> <!-- $i18n - 第一部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.person.crs.part1.describe') }}</div> <!-- $i18n - 个人账户持有人的身分识辨资料 -->
                </div>
                <div class="ContentCRS">
                  <h5>{{ $t('registerOpenAccount.openAccount.person.crs.part1.minTitle1') }}</h5> <!-- $i18n - 1、账户持有人个人信息 -->
                  <el-form-item prop="one.personal.call">
                    <el-select
                      v-model="person7.one.personal.call"
                      :no-match-text="$t('account.manage.no_data')"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"
                    > <!-- $i18n - 称呼 -->
                      <dictionary dict-code="appellationType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.firstName">
                    <el-input
                      v-model="person7.one.personal.firstName"
                      maxlength="30"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')"
                    /> <!-- $i18n - 姓 -->
                  </el-form-item>
                  <el-form-item prop="one.personal.lastName">
                    <el-input
                      v-model="person7.one.personal.lastName"
                      maxlength="30"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')"
                    /> <!-- $i18n - 名 -->
                  </el-form-item>
                  <el-form-item prop="one.personal.identityType">
                    <el-select
                      v-model="person7.one.personal.identityType"
                      :no-match-text="$t('account.manage.no_data')"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"
                    > <!-- $i18n - 身份认证类型 -->
                      <dictionary dict-code="cardType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.papersNum">
                    <el-input
                      v-model="person7.one.personal.papersNum"
                      maxlength="30"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')"
                    /> <!-- $i18n - 证件号码 -->
                  </el-form-item>
                  <el-form-item prop="one.personal.dateBirth">
                    <el-date-picker
                      v-model="person7.one.personal.dateBirth"
                      type="date"
                      :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                      :disabled="true"
                    /> <!-- $i18n - 出生日期 -->
                  </el-form-item>
                </div>
                <div class="ContentCRS">
                  <h5>{{ $t('registerOpenAccount.openAccount.person.crs.part1.minTitle2') }}</h5> <!-- $i18n - 2、现时住址 -->
                  <el-form-item prop="one.location.reality.state">
                    <el-select
                      v-model="person7.one.location.reality.state"
                      :no-match-text="$t('account.manage.no_data')"
                      filterable
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"
                    > <!-- $i18n - 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site">
                    <el-input
                      v-model="person7.one.location.reality.site"
                      maxlength="64"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                    /> <!-- $i18n - 地址（如果空间不足请转下行） -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site1">
                    <el-input
                      v-model="person7.one.location.reality.site1"
                      maxlength="64"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')"
                    /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.city">
                    <el-input
                      v-model="person7.one.location.reality.city"
                      maxlength="30"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')"
                    /> <!-- $i18n - 城市 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.province">
                    <el-input
                      v-model="person7.one.location.reality.province"
                      maxlength="30"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                    /> <!-- $i18n - 省/市 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.postalCode">
                    <el-input
                      v-model="person7.one.location.reality.postalCode"
                      maxlength="20"
                      :disabled="true"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')"
                    /> <!-- $i18n - 邮政编码 -->
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: person7.one.location.ifcommunicate,fade:true}"
                          @click="person7.one.location.ifcommunicate=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !person7.one.location.ifcommunicate,fade:true}"
                          @click="person7.one.location.ifcommunicate=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.isMailingAddress2') }}</li> <!-- $i18n - 您的通讯地址是否与上方所填地址不同? -->
                    </ul>
                  </div>
                  <div v-if="person7.one.location.ifcommunicate">
                    <el-form-item prop="one.location.communicate.state">
                      <el-select v-model="person7.one.location.communicate.state" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site">
                      <el-input
                        v-model="person7.one.location.communicate.site"
                        maxlength="64"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                      /> <!-- $i18n - 地址（如果空间不足请转下行） -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site1">
                      <el-input
                        v-model="person7.one.location.communicate.site1"
                        maxlength="64"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')"
                      /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.city">
                      <el-input v-model="person7.one.location.communicate.city" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.province">
                      <el-input
                        v-model="person7.one.location.communicate.province"
                        maxlength="30"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')"
                      /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.postalCode">
                      <el-input
                        v-model="person7.one.location.communicate.postalCode"
                        maxlength="20"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')"
                      /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- main-content2 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.person.crs.part2.title') }}</div> <!-- $i18n - 第二部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.person.crs.part2.describe') }}</div> <!-- $i18n - 居留司法管辖区及税务编号或具 ... -->
                </div>
                <div class="ContentCRS">
                  <h5
                    style="margin-bottom:15px"
                  >{{ $t('registerOpenAccount.openAccount.person.crs.part2.describe1a') }}</h5> <!-- $i18n - 提供以下资料，列明（i）账户持有人的居留司法管 ... -->
                  <li>{{ $t('registerOpenAccount.openAccount.person.crs.part2.describe1b') }}</li> <!-- $i18n - 如账户持有人是中国税务居民，税务编号是其中国身份证号码。 -->
                  <div v-for="(item,index) in person7.two.TaxNumber" :key="index">
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li
                            class="isyes"
                            :class="{ dianji: item.ifTaxNumberBtn,fade:true}"
                            @click="item.ifTaxNumberBtn=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        </transition>
                        <transition name="fade">
                          <li
                            class="isno"
                            :class="{ dianji: !item.ifTaxNumberBtn,fade:true}"
                            @click="item.ifTaxNumberBtn=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        </transition>
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber1') }}</li> <!-- $i18n - 您是否有税务编号？ -->
                      </ul>
                    </div>
                    <el-form-item
                      v-if="item.ifTaxNumberBtn"
                      :prop="'two.TaxNumber.'+index+'.describeT'"
                      :rules="rules.describeT"
                    >
                      <el-input v-model="item.describeT" maxlength="30" :placeholder="$t('registerOpenAccount.common.formPrompt.required')" /> <!-- $i18n - 必填 -->
                    </el-form-item>
                    <div v-if="!item.ifTaxNumberBtn">
                      <el-form-item
                        id="radioInline"
                        style="margin-bottom:0px;"
                        :prop="'two.TaxNumber.'+index+'.describeF'"
                        :rules="rules.describeF"
                      >
                        <el-radio-group v-model="item.describeF">
                          <div v-for="(item1,index1) in item.thetaxnumberList" :key="index1">
                            <el-radio :label="item1.dictValue" @change="item.TwoIfShow = index1 === 1? true:false">{{ item1.dictDest }}</el-radio>
                            <div v-if="index1 === 1&&item.TwoIfShow ===true" style="margin-bottom:15px;">
                              <el-form-item
                                style="margin-bottom:0px;margin-left:28px;"
                                :prop="'two.TaxNumber.'+index+'.describeFinput'"
                                :rules="rules.describeFinput"
                              >
                                <el-input v-model="item.describeFinput" maxlength="30" placeholder />
                              </el-form-item>
                            </div>
                          </div>
                        </el-radio-group>
                      </el-form-item>
                    </div>
                  </div>
                  <!-- <div class="addBtn" style="margin-top:0px;">
                    <div class="add" @click="addElement(person7.two.TaxNumber,1)">添加其他号码</div>
                    <div class="cancel" @click="removeElement(person7.two.TaxNumber)">删除</div>
                  </div> -->
                </div>
              </div>
              <!-- main-content3 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.person.crs.statement.title') }}</div> <!-- $i18n - 第三部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.person.crs.statement.describe') }}</div> <!-- $i18n - 声明及签署 -->
                </div>
                <div class="ContentCRS">
                  <p>{{ $t('registerOpenAccount.openAccount.common.crs.statement.describe1') }}</p> <!-- $i18n - 本人声明就本人所知所信，本表格 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfStatement">
                    <el-radio-group v-model="person7.statement.selfStatement">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p>
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe2') }}
                  </p> <!-- $i18n - 本人知悉及同意，财务机构可根据《税务条例》（第112章）有 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfKnow">
                    <el-radio-group v-model="person7.statement.selfKnow">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p>
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe3') }}
                  </p> <!-- $i18n - 本人承诺，如情况有所改变，以致影响 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfPromiset">
                    <el-radio-group v-model="person7.statement.selfPromiset">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p>{{ $t('registerOpenAccount.openAccount.common.crs.statement.describe4') }}</p> <!-- $i18n - 本人同意所有税务表格均用过电子形式而 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfAgree">
                    <el-radio-group v-model="person7.statement.selfAgree">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <!-- <h5 style="margin-top: 10px;">本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</h5> -->
                  <div class="signature">
                    <p class="Stitle">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</p> <!-- $i18n - 签名 -->
                    <p class="Sdescribe">
                      {{ $t('registerOpenAccount.openAccount.common.signature.signatureDB') }}
                    </p> <!-- $i18n - 输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括预 ... -->
                    <ul>
                      <li>{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</li> <!-- $i18n - 账户名称: -->
                      <li>{{ person7.statement.accountTitle1 }}</li>
                    </ul>

                    <ul>
                      <li style="margin-right: 48px;">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</li> <!-- $i18n - 日期: -->
                      <li>{{ person7.statement.time }}</li>
                    </ul>
                    <p class="shili">{{ person7.statement.accountTitle2 }}</p>
                    <ul class="inputQM">
                      <li>{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                      <li>
                        <el-form-item prop="statement.accountTitle3">
                          <el-input v-model="person7.statement.accountTitle3" maxlength="128" />
                        </el-form-item>
                      </li>
                    </ul>
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
                      @click="submitForm('person7')"
                    >{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      this.$verify.notChinese(rule, value, callback);
    };
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 第三部分 是否验证
    var Whether = (rule, value, callback) => {
      if (value !== '') {
        if (value === true) {
          callback();
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint'))); // $i18n - 您必须选择是或确认才能继续
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
      }
    };
    // 签名验证
    var Signature = (rule, value, callback) => {
      const mate = this.person7.statement.accountTitle2 === value;
      this.$verify.Signature(rule, value, mate, callback);
    };
    return {
      isTokenHint: false,
      lang: this.$Cookies.get('locale'),
      person7: {
        one: {
          personal: {
            call: '',
            firstName: '',
            lastName: '',
            identityType: '',
            papersNum: '',
            dateBirth: ''
          },
          location: {
            ifcommunicate: false,
            reality: {
              site: '',
              site1: '',
              city: '',
              province: '',
              state: '',
              postalCode: ''
            },
            communicate: {
              site: '',
              site1: '',
              city: '',
              province: '',
              state: '',
              postalCode: ''
            }
          }
        },
        two: {
          TaxNumber: [
            {
              ifTaxNumberBtn: true,
              describeT: '',
              describeF: '1',
              describeFinput: '',
              ifdescribeTwe: false,
              thetaxnumberList: [],
              TwoIfShow: false
            }
          ],
          thetaxnumberList: []
        },
        statement: {
          selfStatement: '',
          selfKnow: '',
          selfPromiset: '',
          selfAgree: '',
          accountTitle1: '',
          time: '',
          accountTitle2: '',
          accountTitle3: ''
        }
      },
      newValue: null,
      rules: {
        //  1.账户持有人个人信息
        'one.personal.call': [{ validator: notChinese, trigger: 'change' }],
        'one.personal.firstName': [{ validator: notChinese, trigger: 'blur' }],
        'one.personal.lastName': [{ validator: notChinese, trigger: 'blur' }],
        'one.personal.identityType': [
          { validator: notChinese, trigger: 'change' }
        ],
        'one.personal.papersNum': [{ validator: notChinese, trigger: 'blur' }],
        'one.personal.dateBirth': [
          { required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'), trigger: 'change' } // $i18n - 请选择
        ],
        //  2.1、现时住址
        'one.location.reality.site': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.reality.site1': [
          { validator: notChinese1, trigger: 'blur' }
        ],
        'one.location.reality.city': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.reality.province': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.reality.state': [
          { validator: notChinese, trigger: 'change' }
        ],
        'one.location.reality.postalCode': [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 2.2,邮箱地址
        'one.location.communicate.site': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.communicate.site1': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.communicate.city': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.communicate.province': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.communicate.state': [
          { validator: notChinese, trigger: 'change' }
        ],
        'one.location.communicate.postalCode': [
          { validator: notChinese, trigger: 'blur' }
        ],
        // 3,第二部分
        describeT: [{ validator: notChinese, trigger: 'blur' }],
        describeF: [{ validator: notChinese, trigger: 'blur' }],
        describeFinput: [{ validator: notChinese, trigger: 'blur' }],
        // 4,声明及签署
        'statement.selfStatement': [
          { validator: Whether, trigger: 'change' }
        ],
        'statement.selfKnow': [
          { validator: Whether, trigger: 'change' }
        ],
        'statement.selfPromiset': [
          { validator: Whether, trigger: 'change' }
        ],
        'statement.selfAgree': [
          { validator: Whether, trigger: 'change' }
        ],
        'statement.accountTitle3': [{ validator: Signature, trigger: 'blur' }]
      }
    };
  },
  created() {
    this.getOnePSAinfo();
    this.getOneITinfo();
    this.getOnePSAsiteInfo();
    this.getTaxlist();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入数据-更新
    getinfo() {
      this.$http({
        url: '/account/accountPerCrs/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object[0];
              if (res.object[0].otherCity) { // 其他邮寄地址
                this.person7.one.location.ifcommunicate = true;
                this.person7.one.location.communicate.city = res.object[0].otherCity;
                this.person7.one.location.communicate.state = res.object[0].otherCountry;
                this.person7.one.location.communicate.province = res.object[0].otherProvince;
                this.person7.one.location.communicate.postalCode = res.object[0].otherZipCode;
                if (res.object[0].otherAddresses.indexOf('-#-') !== -1) {
                  const dizhi = res.object[0].otherAddresses.split('-#-');
                  this.person7.one.location.communicate.site = dizhi[0];
                  this.person7.one.location.communicate.site1 = dizhi[1];
                }
              }
              // // 第二部分
              if (res.object[0].noTaxNumber === '') {
                this.person7.two.TaxNumber[0].describeT = res.object[0].yesTaxNumber;
              } else {
                this.person7.two.TaxNumber[0].ifTaxNumberBtn = false;
                this.person7.two.TaxNumber[0].describeF = res.object[0].noTaxNumber;
                this.person7.two.TaxNumber[0].describeFinput = res.object[0].reason;
                if (this.person7.two.TaxNumber[0].describeFinput !== '') {
                  this.person7.two.TaxNumber[0].TwoIfShow = true;
                }
              }
              // 第三部分
              this.person7.statement.selfStatement = res.object[0].authenticData;
              this.person7.statement.selfKnow = res.object[0].agreeTaxOrdinance;
              this.person7.statement.selfPromiset = res.object[0].provenItself;
              this.person7.statement.selfAgree = res.object[0].consent;
              this.person7.statement.accountTitle3 = res.object[0].signature;
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
    // 获取第一页个人信息
    getOnePSAinfo() {
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
              // 姓名 称呼
              this.person7.one.personal.call = res.object[0].appellation;
              this.person7.one.personal.firstName = res.object[0].lastName;
              this.person7.one.personal.lastName = res.object[0].firstName;

              this.person7.statement.accountTitle1 = this.person7.one.personal.firstName + ' ' + this.person7.one.personal.lastName;
              this.person7.statement.accountTitle2 = this.person7.one.personal.firstName + ' ' + this.person7.one.personal.lastName;
              this.person7.statement.accountTitle3 = this.person7.one.personal.firstName + ' ' + this.person7.one.personal.lastName;
              this.person7.statement.time = this.$verify.NowDate3();
              // 出生日期
              this.person7.one.personal.dateBirth = new Date(res.object[0].birthday);
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
    // 获取第一个身份证明
    getOneITinfo() {
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
              this.person7.one.personal.identityType = res.object[0].idCardType;
              this.person7.one.personal.papersNum = res.object[0].idNumber;
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 获取第一页个人地址
    getOnePSAsiteInfo() {
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
                if (res.object[i].addressType === 'personalAddress') {
                  // 个人地址类型
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.person7.one.location.reality.site = dizhi[0];
                    this.person7.one.location.reality.site1 = dizhi[1];
                  } else {
                    this.person7.one.location.reality.site =
                      res.object[i].address; // 居住地址
                  }

                  this.person7.one.location.reality.city = res.object[i].city; // 城市
                  this.person7.one.location.reality.province =
                    res.object[i].rovince; // 省
                  this.person7.one.location.reality.postalCode =
                    res.object[i].zipCode; // 邮编

                  this.person7.one.location.reality.state =
                    res.object[i].country; // 国家
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
    getTaxlist() { // 税务居住地
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'taxNumberType',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            this.person7.two.TaxNumber[0].thetaxnumberList = JSON.parse(JSON.stringify(res.object));
            this.person7.two.thetaxnumberList = JSON.parse(JSON.stringify(res.object));
            this.getinfo();
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
          ifTaxNumberBtn: true,
          describeT: '',
          describeF: '1',
          describeFinput: '',
          ifdescribeTwe: false,
          thetaxnumberList: JSON.parse(JSON.stringify(this.person7.two.thetaxnumberList)),
          TwoIfShow: false
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
    // 保存,下一步
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

    // 提交表单数据
    submitFormData() {
      const updatedBy = this.$store.state.user.applyData.xinxi.id; // 账户id  更新人
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id; // 账户id  创建人
      const createdDate = this.$verify.NowDate(); // 创建时间
      let data = {};

      let yesTaxNumber = null; // 有税务编号 -第二部分
      let noTaxNumber = null; // 没有税务编号
      let reason = null; // 没有的原因
      for (let i = 0; i < this.person7.two.TaxNumber.length; i++) {
        if (this.person7.two.TaxNumber[i].ifTaxNumberBtn === true) {
          yesTaxNumber = this.person7.two.TaxNumber[i].describeT;
        } else {
          noTaxNumber = this.person7.two.TaxNumber[i].describeF;
          reason = this.person7.two.TaxNumber[i].describeFinput;
        }
      }
      let otherAddresses = null; // 地址
      if (this.person7.one.location.ifcommunicate) {
        if (this.person7.one.location.communicate.site1) {
          otherAddresses = this.person7.one.location.communicate.site + '-#-' + this.person7.one.location.communicate.site1;
        } else {
          otherAddresses = this.person7.one.location.communicate.site;
        }
      }

      if (this.newValue === null) {
        data = {
          accountId,
          createdBy,
          createdDate,
          updatedBy,
          // 第一部分
          otherAddresses,
          otherCity: this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.city : null,
          otherCountry: this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.state : null,
          otherProvince: this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.province : null,
          otherZipCode: this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.postalCode : null,
          // 第二部分 -- 渲染部分
          yesTaxNumber,
          noTaxNumber,
          reason,
          // 第三部分
          authenticData: this.person7.statement.selfStatement,
          agreeTaxOrdinance: this.person7.statement.selfKnow,
          provenItself: this.person7.statement.selfPromiset,
          consent: this.person7.statement.selfAgree,
          signature: this.person7.statement.accountTitle3,
          signatureDate: this.$verify.NowDate(this.person7.statement.time)
        };
      } else {
        this.newValue.otherAddresses = otherAddresses;
        this.newValue.otherCity = this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.city : null;
        this.newValue.otherCountry = this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.state : null;
        this.newValue.otherProvince = this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.province : null;
        this.newValue.otherZipCode = this.person7.one.location.ifcommunicate ? this.person7.one.location.communicate.postalCode : null;
        this.newValue.yesTaxNumber = yesTaxNumber;
        this.newValue.noTaxNumber = noTaxNumber;
        this.newValue.reason = reason;
        this.newValue.authenticData = this.person7.statement.selfStatement;
        this.newValue.agreeTaxOrdinance = this.person7.statement.selfKnow;
        this.newValue.provenItself = this.person7.statement.selfPromiset;
        this.newValue.signature = this.person7.statement.accountTitle3;
        this.newValue.signatureDate = this.$verify.NowDate(this.person7.statement.time);
        data = this.newValue;
      }
      // 数据请求
      this.$http({
        url: '/openAccount/personal/saveSeven',
        method: 'put',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person8' });
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
      this.$verify.routerPermissions({ path: '/openAccount/person6' });
    }
  }
};
</script>
<style scoped>
  #ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRS .el-radio {
    margin-bottom: 6px;
  }

</style>

