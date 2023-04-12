<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(2/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <div class="formContent">
            <el-form
              ref="partnership2"
              autocomplete="off"
              :model="partnership2"
              :rules="rules"
              label-width="0px"
              class="demo-ruleForm"
            >
              <!-- 被授权人 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.info.title1') }}</h3> <!-- $i18n - 被授权人 -->
                  <p style="margin-bottom:8px">{{ $t('registerOpenAccount.openAccount.common.info.describe1') }}</p> <!-- $i18n - 请针对填写此申请并获得授权代表开 ... -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.info.describe2') }}</p> <!-- $i18n - 请注意，您须提供公司决议或类似文件，授权开立账户 ... -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="beiCertigier.companyName">
                    <el-select v-model="partnership2.beiCertigier.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                      <dictionary dict-code="appellationType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="beiCertigier.call">
                    <el-input v-model="partnership2.beiCertigier.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                  </el-form-item>
                  <el-form-item prop="beiCertigier.middle">
                    <el-input v-model="partnership2.beiCertigier.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 中间名,不适用于中国客户 -->
                  </el-form-item>
                  <el-form-item prop="beiCertigier.surname">
                    <el-input v-model="partnership2.beiCertigier.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                  </el-form-item>
                  <el-form-item prop="beiCertigier.suffix">
                    <el-select v-model="partnership2.beiCertigier.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                      <el-option label="Jr " value="Jr" />
                      <el-option label="Sr " value="Sr" />
                      <el-option label="I " value="I" />
                      <el-option label="II " value="II" />
                      <el-option label="III " value="III" />
                      <el-option label="IV " value="IV" />
                      <el-option label="V " value="V" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="beiCertigier.birth">
                    <div class="block">
                      <el-date-picker
                        v-model="partnership2.beiCertigier.birth"
                        value-format="yyyy-MM-dd"
                        type="date"
                        :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                      /> <!-- $i18n - 出生日期 -->
                    </div>
                  </el-form-item>
                  <div class="top20">
                    <el-form-item prop="beiCertigier.toucun">
                      <h3 class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.info.position.title') }}</h3> <!-- $i18n - 确认您在申请公司持有以下哪个职位: -->
                      <el-radio-group v-model="partnership2.beiCertigier.toucun">
                        <el-radio label="dongshi" @change="partnership2.isshow.isGYzhiwei=false">{{ $t('registerOpenAccount.openAccount.common.info.position.item1') }}</el-radio> <!-- $i18n - 董事 -->
                        <br>
                        <el-radio label="mishu" @change="partnership2.isshow.isGYzhiwei=false">{{ $t('registerOpenAccount.openAccount.common.info.position.item2') }}</el-radio> <!-- $i18n - 秘书 -->
                        <br>
                        <el-radio label="qitaguanyuan" @change="partnership2.isshow.isGYzhiwei=true">{{ $t('registerOpenAccount.openAccount.common.info.position.item3') }}</el-radio> <!-- $i18n - 其他官员 -->
                        <br>
                      </el-radio-group>
                    </el-form-item>
                    <transition name="fade">
                      <el-form-item v-show="partnership2.isshow.isGYzhiwei" class="fade" prop="beiCertigier.guanyuanZW">
                        <el-select v-model="partnership2.beiCertigier.guanyuanZW" :no-match-text="$t('account.manage.no_data')" prop="identity" :placeholder="$t('registerOpenAccount.openAccount.common.info.position.post')"> <!-- $i18n - 职位 -->
                          <dictionary dict-code="otherOfficer" />
                        </el-select>
                      </el-form-item>
                    </transition>
                  </div>
                </div>
              </div>
              <!-- 居住地址（非办公地址） -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title2') }}</h3> <!-- $i18n - 居住地址（非办公地址） -->
                </div>
                <div class="formNapeContent">
                  <div>
                    <el-form-item prop="residential.state1">
                      <el-select v-model="partnership2.residential.state1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="residential.dizhi">
                      <el-input
                        v-model="partnership2.residential.dizhi"
                        maxlength="64"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                      /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                    </el-form-item>
                    <el-form-item prop="residential.dizhi1">
                      <el-input v-model="partnership2.residential.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item prop="residential.chengshi">
                      <el-input v-model="partnership2.residential.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item prop="residential.province">
                      <el-input v-model="partnership2.residential.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item prop="residential.youbian">
                      <el-input v-model="partnership2.residential.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li class="isyes" :class="{ dianji: partnership2.isshow.isguojiButong,fade:true}" @click="partnership2.isshow.isguojiButong=true">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        </transition>
                        <transition name="fade">
                          <li class="isno" :class="{ dianji: !partnership2.isshow.isguojiButong,fade:true}" @click="partnership2.isshow.isguojiButong=false">{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        </transition>
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentNationalities1') }}</li> <!-- $i18n - 您的国籍是否与上方所选国家不同？ -->
                      </ul>
                    </div>
                    <transition name="fade">
                      <el-form-item v-show="partnership2.isshow.isguojiButong" prop="residential.guoji1">
                        <el-select v-model="partnership2.residential.guoji1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.common.formPrompt.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </transition>
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li class="isyes" :class="{ dianji: partnership2.isshow.ischushengguoButong,fade:true}" @click="partnership2.isshow.ischushengguoButong=true">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        </transition>
                        <transition name="fade">
                          <li class="isno" :class="{ dianji: !partnership2.isshow.ischushengguoButong,fade:true}" @click="partnership2.isshow.ischushengguoButong=false">{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        </transition>
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentBirthStateDescribe1') }}</li> <!-- $i18n - 您的出生国是否与上方所选国家不同? -->
                      </ul>
                    </div>
                    <transition name="fade">
                      <el-form-item v-show="partnership2.isshow.ischushengguoButong" prop="residential.chushengguo">
                        <el-select v-model="partnership2.residential.chushengguo" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.butongBirthState')"> <!-- $i18n - 不同出生国 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </transition>
                  </div>

                </div>
              </div>
              <!-- 联系信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.contact.title') }}</h3> <!-- $i18n - 联系信息 -->
                </div>
                <div v-for="(item,index) in partnership2.contact" :key="index" class="formNapeContent">
                  <el-form-item v-if="item.isyouxiang" :prop="'contact.'+index+'.youxiang'" :rules="rules.youxiang">
                    <el-input v-model="item.youxiang" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.contact.email')" /> <!-- $i18n - 电邮地址 -->
                  </el-form-item>
                  <el-form-item>
                    <el-col :span="6">
                      <el-form-item style="margin-bottom: 2px;" :prop="'contact.'+index+'.time'" :rules="rules.time">
                        <el-select v-model="item.time" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.contact.type')"> <!-- $i18n - 手机类型 -->
                          <dictionary dict-code="phoneNumberType" />
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                    <el-col :span="6">
                      <el-form-item style="margin-bottom: 2px;" :prop="'contact.'+index+'.state2'" :rules="rules.state2">
                        <el-select v-model="item.state2" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.common.formPrompt.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </el-col>
                    <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                    <el-col :span="11">
                      <el-form-item style="margin-bottom: 2px;" :prop="'contact.'+index+'.phoneNumber'" :rules="rules.phoneNumber">
                        <el-input v-model="item.phoneNumber" maxlength="11" :placeholder="$t('registerOpenAccount.openAccount.common.contact.contactNumber')" /> <!-- $i18n - 电话号码 -->
                      </el-form-item>
                    </el-col>
                  </el-form-item>
                </div>
                <div
                  class="addBtn"
                  style="    width: 960px;
                               height: 41px;"
                >
                  <div v-show="partnership2.contact.length !== 5" class="add" @click="addelement2()">{{ $t('registerOpenAccount.openAccount.common.contact.addOtherNumbers') }}</div> <!-- $i18n - 添加其他号码 -->
                  <div v-show="partnership2.contact.length !== 1" class="cancel" @click="removeelement2()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 身份证明 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.identity.title') }}</h3> <!-- $i18n - 身份证明 -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="identification.SFsignGuojia">
                    <el-select v-model="partnership2.identification.SFsignGuojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.identity.state')"> <!-- $i18n - 签发国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="identification.SFzhengjianleixing">
                    <el-select v-model="partnership2.identification.SFzhengjianleixing" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"> <!-- $i18n - 身份证类型 -->
                      <dictionary dict-code="cardType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="identification.SFzhengjianhao">
                    <el-input v-model="partnership2.identification.SFzhengjianhao" maxlength="63" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                  </el-form-item>
                </div>
              </div>
              <!-- 税务居住地 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                </div>
                <div v-for="(item,index) in partnership2.placeAbode" :key="index" class="formNapeContent">
                  <el-form-item :prop="'placeAbode.'+index+'.SWstate3'" :rules="rules.SWstate3">
                    <el-select v-model="item.SWstate3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item :prop="'placeAbode.'+index+'.SWIDNumber'" :rules="rules.SWIDNumber">
                    <el-input v-model="item.SWIDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li class="isyes" :class="{ dianji: !item.isSuiwuNO,fade:true}" @click="item.isSuiwuNO=false">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li class="isno" :class="{ dianji: item.isSuiwuNO,fade:true}" @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.SWIDNumber')">{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                    </ul>
                  </div>
                  <transition name="fade">
                    <div v-if="item.isSuiwuNO" class="textcontent1 fade">
                      <span class="zhuce3ji">
                        {{ $t('registerOpenAccount.openAccount.common.taxes.describe') }}
                      </span> <!-- $i18n - 美国国税局规则要求我们收集您所有纳税 ... -->
                      <el-form-item id="radioInline" :prop="'placeAbode.'+index+'.suibie'" :rules="rules.suibie">
                        <el-radio-group v-model="item.suibie">
                          <div v-for="(item1,index1) in item.taxNumList" :key="index1">
                            <el-radio clase="zhuce3ji" :label="item1.dictValue">{{ item1.dictDest }}</el-radio>
                          </div>
                          <br>
                          <el-radio class="zhuce3ji" label="qita">
                            {{ `${$t('registerOpenAccount.openAccount.common.taxes.else')}` }} <!-- $i18n - 其他 -->
                            <input v-model="item.suiwuQita" maxlength="200" type="text" :placeholder="$t('registerOpenAccount.common.formPrompt.enter')"> <!-- $i18n - 请输入 -->
                          </el-radio>
                          <br>
                        </el-radio-group>
                      </el-form-item>
                    </div>
                  </transition>
                </div>
                <div
                  class="addBtn"
                  style="    width: 960px;
                               height: 41px;"
                >
                  <div v-show="partnership2.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加税务居住地 -->
                  <div v-show="partnership2.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 安全问题 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.safeQuestion.title') }}</h3> <!-- $i18n - 安全问题 -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="safetyproblem.WTone">
                    <el-select v-model="partnership2.safetyproblem.WTone" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.issue1')"> <!-- $i18n - 安全问题一 -->
                      <el-option
                        v-for="(item,index) in partnership2.safetyproblem.safetyIssue"
                        :key="index"
                        :disabled="partnership2.anquan.wenti1===(index+1)||partnership2.anquan.wenti1a===(index+1)"
                        :label="item.dictDest"
                        :value="item.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="safetyproblem.answerOne">
                    <el-input v-model="partnership2.safetyproblem.answerOne" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.answer1')" /> <!-- $i18n - 答案一 -->
                  </el-form-item>
                  <el-form-item prop="safetyproblem.WTtwo">
                    <el-select v-model="partnership2.safetyproblem.WTtwo" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.issue2')"> <!-- $i18n - 安全问题二 -->
                      <el-option
                        v-for="(item,index) in partnership2.safetyproblem.safetyIssue"
                        :key="index"
                        :disabled="partnership2.anquan.wenti2===(index+1)||partnership2.anquan.wenti2a===(index+1)"
                        :label="item.dictDest"
                        :value="item.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="safetyproblem.answerTwo">
                    <el-input v-model="partnership2.safetyproblem.answerTwo" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.answer2')" /> <!-- $i18n - 答案二 -->
                  </el-form-item>
                  <el-form-item prop="safetyproblem.WTthree">
                    <el-select v-model="partnership2.safetyproblem.WTthree" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.issue3')"> <!-- $i18n - 安全问题三 -->
                      <el-option
                        v-for="(item,index) in partnership2.safetyproblem.safetyIssue"
                        :key="index"
                        :disabled="partnership2.anquan.wenti3===(index+1)||partnership2.anquan.wenti3a===(index+1)"
                        :label="item.dictDest"
                        :value="item.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="safetyproblem.answerThree">
                    <el-input v-model="partnership2.safetyproblem.answerThree" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.answer3')" /> <!-- $i18n - 答案三 -->
                  </el-form-item>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('partnership2')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }}</el-button>
                  </el-form-item>
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
    // 非中文验证1
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 出生日期
    var validateBirth = (rule, value, callback) => {
      const val = this.partnership2.accountType;
      this.$verify.Birth(rule, value, callback, val);
    };
    // 被授权人验证-职位
    var validateGuanyuanZW = (rule, value, callback) => {
      const mate = this.partnership2.isshow.isGYzhiwei;
      this.$verify.stairIf(rule, value, mate, callback);
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
    // 证件号码-税务居住地
    var validateZJhaoma = (rule, value, callback) => {
      const _this = this;
      if (value === '') {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      } else {
        for (let i = 0; i < this.partnership2.placeAbode.length; i++) {
          // if (this.partnership2.placeAbode[i].SWstate3 === value) {
          this.$verify.State(this.partnership2.placeAbode[i].SWstate3, this.partnership2.placeAbode[i].taxNumList, this.partnership2.taxNumList1, _this, this.lang);
          // }
        }
        callback();
      }
    };
    // 居住地址（非办公地址） --国籍不同
    var validateGZguoji1 = (rule, value, callback) => {
      const mate = this.partnership2.isshow.isguojiButong;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 居住地址（非办公地址）  -- 出生国不同
    var validateGZchushengguo = (rule, value, callback) => {
      const mate = this.partnership2.isshow.ischushengguoButong;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 联系信息  -- 邮箱
    var validateLXyouxiang = (rule, value, callback) => {
      this.$verify.Postbox(rule, value, callback);
    };
    // 税务居住地  -- 纳税号
    var validateSWsuibie = (rule, value, callback) => {
      const array1 = this.partnership2.placeAbode;
      this.$verify.SWnasuihao(rule, value, array1, callback);
    };
    // 字母+数字
    var englishNumber = (rule, value, callback) => {
      this.$verify.EnglishNumber(rule, value, callback);
    };
    // 安全验证
    var validateSafety = (rule, value, callback) => {
      const obj = {
        safetyIssue: this.partnership2.safetyproblem.safetyIssue,
        anquan: this.partnership2.anquan,
        type1: 0
      };
      if (this.partnership2.safetyproblem.WTone === value) {
        obj.type1 = 1;
      } else if (this.partnership2.safetyproblem.WTtwo === value) {
        obj.type1 = 2;
      } else if (this.partnership2.safetyproblem.WTthree === value) {
        obj.type1 = 3;
      }
      this.$verify.Safety(rule, value, obj, callback);
    };
    // 税务号
    var theTaxNo = (rule, value, callback) => {
      if (value !== 'N.A.') {
        if (this.partnership2.identification.SFsignGuojia !== 'CN' ||
            this.partnership2.identification.SFzhengjianleixing !== 'idCard' ||
            this.partnership2.identification.SFzhengjianhao === '') {
          this.$verify.notChinese(rule, value, callback);
        } else {
          this.partnership2.placeAbode.forEach(item => {
            if (item.SWIDNumber === value) {
              if (item.SWstate3 === 'CN') {
                if (this.partnership2.identification.SFzhengjianhao === value) {
                  this.$verify.notChinese(rule, value, callback);
                } else {
                  callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.identificationPaper'))); // $i18n - 纳税人识别号码应与身份证号码一致。
                }
              } else {
                this.$verify.notChinese(rule, value, callback);
              }
            }
          });
        }
      } else {
        this.$verify.notChinese(rule, value, callback);
      }
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      partnership2: {
        accountType: '', // 第一页账户类型
        // 被授权人
        beiCertigier: {
          companyName: '',
          call: '',
          middle: '',
          surname: '',
          suffix: '',
          birth: '',
          isbirth: false,
          toucun: '',
          guanyuanZW: '',
          personalType: null
        },
        // 居住地址
        residential: {
          state1: '',
          dizhi: '',
          dizhi1: '',
          chengshi: '',
          province: '',
          youbian: '',
          guoji1: '',
          chushengguo: '',
          addressTypeName: '',
          dictionaryId: '',
          addressType: null
        },
        // 联系信息
        contact: [{
          youxiang: '',
          isyouxiang: true,
          time: '',
          state2: '',
          phoneNumber: ''
        }],

        // 身份证明
        identification: {
          SFsignGuojia: '',
          SFzhengjianleixing: '',
          SFzhengjianhao: ''
        },
        // 税务居住地
        placeAbode: [{
          SWstate3: '',
          SWIDNumber: '',
          IDNumber1: '',
          suibie: '',
          isSuiwuNO: false, // is 税务识别号
          taxNumList: null, // 页面上使用
          suiwuQita: ''
        }],
        taxNumList1: null, // 页面上使用-暂存
        // 安全问题
        safetyproblem: {
          WTone: '',
          WTtwo: '',
          WTthree: '',
          answerOne: '',
          answerTwo: '',
          answerThree: '',
          safetyIssue: [
            // {
            //   safeQuestion: '',
            //   dictionaryId: 'id1'
            // },
            // {
            //   safeQuestion: '',
            //   dictionaryId: 'id2'
            // },
            // {
            //   safeQuestion: '',
            //   dictionaryId: 'id3'
            // }
          ]
        },
        isshow: {
          isGYzhiwei: false,
          isguojiButong: false,
          ischushengguoButong: false
        },
        anquan: {
          wenti1: 0,
          wenti1a: 0,
          wenti2: 0,
          wenti2a: 0,
          wenti3: 0,
          wenti3a: 0
        }
      },
      formdata: {},
      newValue: {
        newAccountParAddressList: null,
        newAccountParContactList: null,
        newAccountParIdentity: null,
        newAccountParPersonal: null,
        newAccountParSafeQuestionList: null,
        newAccountParTaxesList: null
      },
      rules: {
        // 被授权人
        'beiCertigier.companyName': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        'beiCertigier.call': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'beiCertigier.middle': [
          { validator: notChinese1, trigger: 'blur' }
        ],
        'beiCertigier.surname': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'beiCertigier.suffix': [
          { validator: notChinese1, trigger: 'change' }
        ],
        'beiCertigier.birth': [
          { validator: validateBirth, trigger: 'change' }
        ],
        'beiCertigier.toucun': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        'beiCertigier.guanyuanZW': [
          { validator: validateGuanyuanZW, trigger: 'change' }
        ],
        // 居住地址（非办公地址）
        'residential.state1': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        'residential.dizhi': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'residential.dizhi1': [
          { validator: notChinese1, trigger: 'blur' }
        ],
        'residential.chengshi': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'residential.province': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'residential.youbian': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'residential.guoji1': [
          { validator: validateGZguoji1, trigger: 'change' }
        ],
        'residential.chushengguo': [
          { validator: validateGZchushengguo, trigger: 'change' }
        ],
        // 联系信息
        youxiang: [
          { validator: validateLXyouxiang, trigger: 'blur' }
        ],
        time: [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        state2: [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        phoneNumber: [
          { validator: validateLXdianhua, trigger: 'blur' }
        ],
        // 身份证明
        'identification.SFsignGuojia': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        'identification.SFzhengjianleixing': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        'identification.SFzhengjianhao': [
          { validator: englishNumber, trigger: 'blur' }
        ],
        // 税务居住地
        SWstate3: [
          { validator: validateZJhaoma, trigger: 'change' }
        ],
        SWIDNumber: [
          { validator: theTaxNo, trigger: 'blur' }
        ],
        suibie: [
          { validator: validateSWsuibie, trigger: 'change' }
        ],
        // 安全问题
        'safetyproblem.WTone': [
          { validator: validateSafety, trigger: 'change' }
        ],
        'safetyproblem.answerOne': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'safetyproblem.WTtwo': [
          { validator: validateSafety, trigger: 'change' }
        ],
        'safetyproblem.answerTwo': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'safetyproblem.WTthree': [
          { validator: validateSafety, trigger: 'change' }
        ],
        'safetyproblem.answerThree': [
          { validator: notChinese, trigger: 'blur' }
        ]

      }
    };
  },
  created() {
    this.personalType();
    this.addressType();
    this.safeQuestionType();
    this.taxIdentificationNumber();
    this.accountParAccountPortfolio();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取第一页账户类型 - 投资组合判断
    accountParAccountPortfolio() {
      this.$http({
        url: '/account/accountParAccountPortfolio/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership2.accountType =
                res.object[0].accountPortfolioType;
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
    accountParPersonal() { // 公司个人信息-被授权人
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: this.partnership2.beiCertigier.personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountParPersonal = res.object[0];
              this.partnership2.beiCertigier.companyName = res.object[0].appellation;
              this.partnership2.beiCertigier.call = res.object[0].lastName;
              this.partnership2.beiCertigier.middle = res.object[0].middleName;
              this.partnership2.beiCertigier.surname = res.object[0].firstName;
              this.partnership2.beiCertigier.suffix = res.object[0].suffix;
              if (res.object[0].companyPosition.indexOf(',') !== -1) {
                const toucun = res.object[0].companyPosition.split(',');
                for (let i = 0; i < toucun.length; i++) {
                  if (toucun[i] === 'dongshi' || toucun[i] === 'mishu') {
                    this.partnership2.beiCertigier.toucun = toucun[i];
                  } else if (toucun[i] === 'qitaguanyuan') {
                    this.partnership2.isshow.isGYzhiwei = !this.partnership2.isshow.isGYzhiwei;
                    this.partnership2.beiCertigier.toucun = toucun[i];
                    this.partnership2.beiCertigier.guanyuanZW = toucun[i + 1];
                  }
                }
              } else {
                this.partnership2.beiCertigier.toucun = res.object[0].companyPosition;
              }
              this.partnership2.beiCertigier.birth = new Date(res.object[0].birthday);
              this.partnership2.residential.state1 = res.object[0].birthCountry;
              this.partnership2.contact[0].youxiang = res.object[0].email;
              this.partnership2.residential.state1 = res.object[0].nationality;
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
    accountParAddress() { // 公司地址信息
      this.$http({
        url: '/account/accountParAddress/findByAccountId/personal',
        method: 'get',
        params: { personalType: this.partnership2.beiCertigier.personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountParAddressList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                this.partnership2.residential.state1 = res.object[i].country;
                if (res.object[i].address.indexOf('-#-') !== -1) {
                  const address = res.object[i].address.split('-#-');
                  this.partnership2.residential.dizhi = address[0];
                  this.partnership2.residential.dizhi1 = address[1];
                } else {
                  this.partnership2.residential.dizhi = res.object[i].address;
                }
                this.partnership2.residential.chengshi = res.object[i].city;
                this.partnership2.residential.province = res.object[i].rovince;
                this.partnership2.residential.youbian = res.object[i].zipCode;
                this.partnership2.residential.guoji1 = res.object[i].otherCountry ? res.object[i].otherCountry : null;
                this.partnership2.isshow.isguojiButong = !!res.object[i].otherCountry;
                this.partnership2.residential.chushengguo = res.object[i].otherBirthCountry ? res.object[i].otherBirthCountry : null;
                this.partnership2.isshow.ischushengguoButong = !!res.object[i].otherBirthCountry;
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
    accountParContact() { // 公司联系信息
      this.$http({
        url: '/account/accountParContact/findByAccountId/personal',
        method: 'get',
        params: { personalType: this.partnership2.beiCertigier.personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountParContactList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  this.partnership2.contact[i].time = res.object[i].contactNumberType;
                  this.partnership2.contact[i].state2 = res.object[i].country;
                  this.partnership2.contact[i].phoneNumber = res.object[i].contactNumber;
                } else {
                  this.partnership2.contact.push({
                    youxiang: '',
                    time: res.object[i].contactNumberType,
                    state2: res.object[i].country,
                    phoneNumber: res.object[i].contactNumber,
                    isyouxiang: false
                  });
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
    accountParIdentity() { // 公司身份信息
      this.$http({
        url: '/account/accountParIdentity/findByAccountId/personal',
        method: 'get',
        params: { personalType: 'authorizedPerson', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountParIdentity = res.object[0];
              this.partnership2.identification.SFzhengjianhao = res.object[0].idNumber;
              this.partnership2.identification.SFzhengjianleixing = res.object[0].idCardType;
              this.partnership2.identification.SFsignGuojia = res.object[0].issuingCountry;
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
    accountParTaxes() { // 公司税务居住地  // 纳税号需判断
      this.$http({
        url: '/account/accountParTaxes/findByAccountId/personal',
        method: 'get',
        params: { personalType: this.partnership2.beiCertigier.personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountParTaxesList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  this.partnership2.placeAbode[i].SWstate3 = res.object[i].taxResidenceCountry;
                  this.partnership2.placeAbode[i].IDNumber1 = '';
                  if (res.object[i].noTaxIdentificationNumber) {
                    let index = 0;
                    for (let j = 0; j < this.partnership2.placeAbode[0].taxNumList.length; j++) {
                      if (res.object[i].noTaxIdentificationNumber === this.partnership2.placeAbode[0].taxNumList[j].dictValue) {
                        index++;
                        this.partnership2.placeAbode[i].suibie = res.object[i].noTaxIdentificationNumber;
                      }
                    }
                    if (index === 0) {
                      this.partnership2.placeAbode[i].suibie = 'qita';
                      this.partnership2.placeAbode[i].suiwuQita = res.object[i].noTaxIdentificationNumber;
                    }
                    this.partnership2.placeAbode[i].isSuiwuNO = true;
                  } else {
                    this.partnership2.placeAbode[i].isSuiwuNO = false;
                    this.partnership2.placeAbode[i].IDNumber1 = res.object[i].taxResidenceIdCardNumber;
                  }
                } else {
                  let suibie = null;
                  let suiwuQita = null;
                  let IDNumber1 = null;
                  let isSuiwuNO = false;
                  IDNumber1 = '';
                  if (res.object[i].noTaxIdentificationNumber) {
                    IDNumber1 = '';
                    isSuiwuNO = true;
                    let index = 0;
                    for (let j = 0; j < this.partnership2.placeAbode[0].taxNumList.length; j++) {
                      if (res.object[i].noTaxIdentificationNumber === this.partnership2.placeAbode[0].taxNumList[j].dictValue) {
                        index++;
                        suibie = res.object[i].noTaxIdentificationNumber;
                      }
                    }
                    if (index === 0) {
                      suibie = 'qita';
                      suiwuQita = res.object[i].noTaxIdentificationNumber;
                    }
                  } else {
                    isSuiwuNO = false;
                    IDNumber1 = res.object[i].taxResidenceIdCardNumber;
                  }
                  this.partnership2.placeAbode.push({
                    SWstate3: res.object[i].taxResidenceCountry,
                    SWIDNumber: res.object[i].taxResidenceIdCardNumber,
                    IDNumber1,
                    suibie,
                    isSuiwuNO,
                    suiwuQita,
                    taxNumList: JSON.parse(JSON.stringify(this.partnership2.placeAbode[0].taxNumList)) // 页面上使用
                  });
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
    accountParSafeQuestion() { // 公司安全问题
      this.$http({
        url: '/account/accountParSafeQuestion/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountParSafeQuestionList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  this.partnership2.safetyproblem.WTone = res.object[i].safeQuestion;
                  this.partnership2.safetyproblem.answerOne = res.object[i].answer;
                } else if (i === 1) {
                  this.partnership2.safetyproblem.WTtwo = res.object[i].safeQuestion;
                  this.partnership2.safetyproblem.answerTwo = res.object[i].answer;
                } else if (i === 2) {
                  this.partnership2.safetyproblem.WTthree = res.object[i].safeQuestion;
                  this.partnership2.safetyproblem.answerThree = res.object[i].answer;
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
    // 字典表获取
    // -- 个人类型
    personalType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            // this.partnership2.beiCertigier.personalType = res.object[1].dictValue;
            this.partnership2.beiCertigier.personalType = 'authorizedPerson';
            this.accountParPersonal();
            this.accountParContact();
            this.accountParAddress();
            this.accountParIdentity();
            this.accountParTaxes();
            this.accountParSafeQuestion();
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
    // -- 纳税识别号类型
    taxIdentificationNumber() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxIdentificationNumber', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership2.placeAbode[0].taxNumList = JSON.parse(JSON.stringify(res.object));
            this.partnership2.taxNumList1 = JSON.parse(JSON.stringify(res.object));
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
    // -- 地址类型
    addressType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'addressType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership2.residential.addressType = res.object[0].dictValue;
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
    // -- 安全类型
    safeQuestionType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'safeQuestionType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership2.safetyproblem.safetyIssue = res.object;
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
    // is纳税识别号-验证证件号码
    taxIsBtn(VerifyPath) {
      let num = 1;
      const timerId = setInterval(() => {
        num--;
        this.$refs.partnership2.validateField(VerifyPath);
        if (num === 0) {
          clearInterval(timerId);
        }
      }, 100);
      return true;
    },
    // 联系添加
    addelement2() {
      this.partnership2.contact.push({
        youxiang: '',
        time: '',
        state2: '',
        phoneNumber: '',
        isyouxiang: false
      });
    },
    removeelement2() {
      this.partnership2.contact.pop(this.partnership2.contact);
    },
    // 添加税务
    addelement3() {
      this.partnership2.placeAbode.push({
        SWstate3: '',
        SWIDNumber: '',
        IDNumber1: '',
        suibie: '',
        isSuiwuNO: false, // is 税务识别号
        suiwuQita: '',
        taxNumList: JSON.parse(JSON.stringify(this.partnership2.placeAbode[0].taxNumList)) // 页面上使用
      });
      if (this.partnership2.placeAbode.length === 1) {
        return false;
      }
    },
    removeelement3() {
      if (this.partnership2.placeAbode.length === 1) {
        return false;
      } else {
        if (this.partnership2.placeAbode === 2) {
          this.partnership2.placeAbode.pop(this.partnership2.placeAbode);
          if (this.partnership2.placeAbode.length === 1) {
            return false;
          }
        }
        this.partnership2.placeAbode.pop(this.partnership2.placeAbode);
      }
    },
    // 验证表单 跳转页面
    submitForm(formName) {
      // this.$verify.routerPermissions({ path: "/openAccount/partnership3" });
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
      let accountParPersonal = {}; // 1.公司个人信息
      let accountParAddressList = {}; // 2.公司地址信息
      const accountParContactList = []; // 3.公司联系信息
      let accountParIdentity = {}; // 4.公司身份信息
      const accountParTaxesList = []; // 5.公司税务居住地
      let accountParSafeQuestionList = []; // 6.公司安全问题

      // 头寸判断-其他官员
      let companyPosition = '';
      if (this.partnership2.isshow.isGYzhiwei) {
        companyPosition = this.partnership2.beiCertigier.toucun + ',' + this.partnership2.beiCertigier.guanyuanZW;
      } else {
        companyPosition = this.partnership2.beiCertigier.toucun + '';
      }
      if (this.newValue.newAccountParPersonal === null) { // 1.公司个人信息
        accountParPersonal = {
          accountId,
          createdBy,
          createdDate,
          appellation: this.partnership2.beiCertigier.companyName,
          lastName: this.partnership2.beiCertigier.call,
          middleName: this.partnership2.beiCertigier.middle,
          firstName: this.partnership2.beiCertigier.surname,
          suffix: this.partnership2.beiCertigier.suffix,
          companyPosition,
          birthday: this.$verify.NowDate2(this.partnership2.beiCertigier.birth),
          birthCountry: this.partnership2.residential.state1,
          email: this.partnership2.contact[0].youxiang,
          prcentageOfOwnership: 0,
          // prcentageOfOwnership:null,
          // nationality:null,
          nationality: this.partnership2.residential.state1,
          personalType: this.partnership2.beiCertigier.personalType,
          isBusinessManager: false,
          isAmericanTaxpayer: false
        };
      } else {
        this.newValue.newAccountParPersonal.appellation = this.partnership2.beiCertigier.companyName;
        this.newValue.newAccountParPersonal.lastName = this.partnership2.beiCertigier.call;
        this.newValue.newAccountParPersonal.middleName = this.partnership2.beiCertigier.middle;
        this.newValue.newAccountParPersonal.firstName = this.partnership2.beiCertigier.surname;
        this.newValue.newAccountParPersonal.suffix = this.partnership2.beiCertigier.suffix;
        this.newValue.newAccountParPersonal.companyPosition = companyPosition;
        this.newValue.newAccountParPersonal.birthday = this.$verify.NowDate2(this.partnership2.beiCertigier.birth);
        this.newValue.newAccountParPersonal.birthCountry = this.partnership2.residential.state1;
        this.newValue.newAccountParPersonal.email = this.partnership2.contact[0].youxiang;
        this.newValue.newAccountParPersonal.prcentageOfOwnership = 0;
        this.newValue.newAccountParPersonal.nationality = this.partnership2.residential.state1;
        this.newValue.newAccountParPersonal.personalType = this.partnership2.beiCertigier.personalType;
      }
      if (this.newValue.newAccountParAddressList === null) { // 2.公司地址信息
        accountParAddressList = [
          {
            accountId,
            createdBy,
            createdDate,
            country: this.partnership2.residential.state1,
            address: this.partnership2.residential.dizhi1 ? this.partnership2.residential.dizhi + '-#-' + this.partnership2.residential.dizhi1 : this.partnership2.residential.dizhi,
            city: this.partnership2.residential.chengshi,
            rovince: this.partnership2.residential.province,
            zipCode: this.partnership2.residential.youbian,
            otherCountry: this.partnership2.isshow.isguojiButong ? this.partnership2.residential.guoji1 : null,
            otherBirthCountry: this.partnership2.isshow.ischushengguoButong ? this.partnership2.residential.chushengguo : null,
            addressType: this.partnership2.residential.addressType
          }
        ];
      } else {
        this.newValue.newAccountParAddressList[0].country = this.partnership2.residential.state1;
        this.newValue.newAccountParAddressList[0].address = this.partnership2.residential.dizhi1 ? this.partnership2.residential.dizhi + '-#-' + this.partnership2.residential.dizhi1 : this.partnership2.residential.dizhi;
        this.newValue.newAccountParAddressList[0].city = this.partnership2.residential.chengshi;
        this.newValue.newAccountParAddressList[0].rovince = this.partnership2.residential.province;
        this.newValue.newAccountParAddressList[0].zipCode = this.partnership2.residential.youbian;
        this.newValue.newAccountParAddressList[0].otherCountry = this.partnership2.isshow.isguojiButong ? this.partnership2.residential.guoji1 : null;
        this.newValue.newAccountParAddressList[0].otherBirthCountry = this.partnership2.isshow.ischushengguoButong ? this.partnership2.residential.chushengguo : null;
        this.newValue.newAccountParAddressList[0].addressType = this.partnership2.residential.addressType;
      }
      if (this.newValue.newAccountParContactList === null) { // 3.公司联系信息
        for (let i = 0; i < this.partnership2.contact.length; i++) {
          accountParContactList.push(
            {
              accountId,
              createdBy,
              createdDate,
              contactNumber: this.partnership2.contact[i].phoneNumber,
              country: this.partnership2.contact[i].state2,
              contactNumberType: this.partnership2.contact[i].time
            }
          );
        }
      } else {
        const index = this.newValue.newAccountParContactList.length;
        for (let i = 0; i < this.partnership2.contact.length; i++) {
          if (this.partnership2.contact.length < index) {
            this.newValue.newAccountParContactList.splice(this.partnership2.contact.length);
            if (i < index) {
              this.newValue.newAccountParContactList[i].contactNumber = this.partnership2.contact[i].phoneNumber;
              this.newValue.newAccountParContactList[i].country = this.partnership2.contact[i].state2;
              this.newValue.newAccountParContactList[i].contactNumberType = this.partnership2.contact[i].time;
            }
          } else {
            if (i < index) {
              this.newValue.newAccountParContactList[i].contactNumber = this.partnership2.contact[i].phoneNumber;
              this.newValue.newAccountParContactList[i].country = this.partnership2.contact[i].state2;
              this.newValue.newAccountParContactList[i].contactNumberType = this.partnership2.contact[i].time;
            } else {
              this.newValue.newAccountParContactList.push(
                {
                  accountId,
                  createdBy,
                  createdDate,
                  contactNumber: this.partnership2.contact[i].phoneNumber,
                  country: this.partnership2.contact[i].state2,
                  contactNumberType: this.partnership2.contact[i].time
                }
              );
            }
          }
        }
      }
      if (this.newValue.newAccountParIdentity === null) { // 4.公司身份信息
        accountParIdentity = {
          accountId,
          createdBy,
          createdDate,
          idNumber: this.partnership2.identification.SFzhengjianhao,
          idCardType: this.partnership2.identification.SFzhengjianleixing,
          issuingCountry: this.partnership2.identification.SFsignGuojia
        };
      } else {
        this.newValue.newAccountParIdentity.idNumber = this.partnership2.identification.SFzhengjianhao;
        this.newValue.newAccountParIdentity.idCardType = this.partnership2.identification.SFzhengjianleixing;
        this.newValue.newAccountParIdentity.issuingCountry = this.partnership2.identification.SFsignGuojia;
      }
      let noTaxIdentificationNumber = null;
      if (this.newValue.newAccountParTaxesList === null) { // 5.公司税务居住地
        for (let i = 0; i < this.partnership2.placeAbode.length; i++) {
          if (this.partnership2.placeAbode[i].isSuiwuNO === true) {
            noTaxIdentificationNumber = this.partnership2.placeAbode[i].suibie === 'qita' ? this.partnership2.placeAbode[i].suiwuQita : this.partnership2.placeAbode[i].suibie;
          } else {
            noTaxIdentificationNumber = null;
          }
          accountParTaxesList.push({
            accountId,
            createdBy,
            createdDate,
            taxResidenceCountry: this.partnership2.placeAbode[i].SWstate3,
            taxResidenceIdCardNumber: this.partnership2.placeAbode[i].SWIDNumber,
            noTaxIdentificationNumber
          });
        }
      } else {
        const index = this.newValue.newAccountParTaxesList.length;
        for (let i = 0; i < this.partnership2.placeAbode.length; i++) {
          if (this.partnership2.placeAbode.length < index) {
            this.newValue.newAccountParTaxesList.splice(this.partnership2.placeAbode.length);
            if (i < index) {
              if (this.partnership2.placeAbode[i].isSuiwuNO === true) {
                noTaxIdentificationNumber = this.partnership2.placeAbode[i].suibie === 'qita' ? this.partnership2.placeAbode[i].suiwuQita : this.partnership2.placeAbode[i].suibie;
              } else {
                noTaxIdentificationNumber = null;
              }
              this.newValue.newAccountParTaxesList[i].taxResidenceCountry = this.partnership2.placeAbode[i].SWstate3;
              this.newValue.newAccountParTaxesList[i].taxResidenceIdCardNumber = this.partnership2.placeAbode[i].SWIDNumber;
              this.newValue.newAccountParTaxesList[i].noTaxIdentificationNumber = noTaxIdentificationNumber;
            }
          } else {
            if (this.partnership2.placeAbode[i].isSuiwuNO === true) {
              noTaxIdentificationNumber = this.partnership2.placeAbode[i].suibie === 'qita' ? this.partnership2.placeAbode[i].suiwuQita : this.partnership2.placeAbode[i].suibie;
            } else {
              noTaxIdentificationNumber = null;
            }
            if (i < index) {
              this.newValue.newAccountParTaxesList[i].taxResidenceCountry = this.partnership2.placeAbode[i].SWstate3;
              this.newValue.newAccountParTaxesList[i].taxResidenceIdCardNumber = this.partnership2.placeAbode[i].SWIDNumber;
              this.newValue.newAccountParTaxesList[i].noTaxIdentificationNumber = noTaxIdentificationNumber;
            } else {
              this.newValue.newAccountParTaxesList.push(
                {
                  accountId,
                  createdBy,
                  createdDate,
                  taxResidenceCountry: this.partnership2.placeAbode[i].SWstate3,
                  taxResidenceIdCardNumber: this.partnership2.placeAbode[i].SWIDNumber,
                  noTaxIdentificationNumber
                }
              );
            }
          }
        }
      }
      if (this.newValue.newAccountParSafeQuestionList === null) { // 6.公司安全问题
        accountParSafeQuestionList = [
          {
            accountId,
            createdBy,
            createdDate,
            safeQuestion: this.partnership2.safetyproblem.WTone,
            answer: this.partnership2.safetyproblem.answerOne

          },
          {
            accountId,
            createdBy,
            createdDate,
            safeQuestion: this.partnership2.safetyproblem.WTtwo,
            answer: this.partnership2.safetyproblem.answerTwo
          },
          {
            accountId,
            createdBy,
            createdDate,
            safeQuestion: this.partnership2.safetyproblem.WTthree,
            answer: this.partnership2.safetyproblem.answerThree

          }
        ];
      } else {
        for (let i = 0; i < this.newValue.newAccountParSafeQuestionList.length; i++) {
          if (i === 0) {
            this.newValue.newAccountParSafeQuestionList[i].safeQuestion = this.partnership2.safetyproblem.WTone;
            this.newValue.newAccountParSafeQuestionList[i].answer = this.partnership2.safetyproblem.answerOne;
          } else if (i === 1) {
            this.newValue.newAccountParSafeQuestionList[i].safeQuestion = this.partnership2.safetyproblem.WTtwo;
            this.newValue.newAccountParSafeQuestionList[i].answer = this.partnership2.safetyproblem.answerTwo;
          } else if (i === 2) {
            this.newValue.newAccountParSafeQuestionList[i].safeQuestion = this.partnership2.safetyproblem.WTthree;
            this.newValue.newAccountParSafeQuestionList[i].answer = this.partnership2.safetyproblem.answerThree;
          }
        }
      }

      const data = {
        accountParPersonal: this.newValue.newAccountParPersonal === null ? accountParPersonal : this.newValue.newAccountParPersonal,
        accountParAddressList: this.newValue.newAccountParAddressList === null ? accountParAddressList : this.newValue.newAccountParAddressList,
        accountParContactList: this.newValue.newAccountParContactList === null ? accountParContactList : this.newValue.newAccountParContactList,
        accountParIdentity: this.newValue.newAccountParIdentity === null ? accountParIdentity : this.newValue.newAccountParIdentity,
        accountParTaxesList: this.newValue.newAccountParTaxesList === null ? accountParTaxesList : this.newValue.newAccountParTaxesList,
        accountParSafeQuestionList: this.newValue.newAccountParSafeQuestionList === null ? accountParSafeQuestionList : this.newValue.newAccountParSafeQuestionList
      };
      this.$http({
        url: '/openAccount/partnerShip/saveTwo',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership3' });
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
          this.$message.error(error);
        });
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/partnership1' });
    }
  }
};
</script>

<style scoped>
  .el-radio-group {
    margin-top: 10px;
  }
  .el-radio {
    margin-bottom: 7px;
  }
</style>

