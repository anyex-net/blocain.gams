<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(1/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person1"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="person1"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- 个人信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.info.title') }}</h3> <!-- $i18n - 个人信息 -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="call">
                    <el-select v-model="person1.call" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                      <dictionary dict-code="appellationType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="surname">
                    <el-input v-model="person1.surname" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                  </el-form-item>
                  <el-form-item prop="name">
                    <el-input v-model="person1.name" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                  </el-form-item>
                  <el-form-item prop="birth">
                    <el-date-picker v-model="person1.birth" value-format="yyyy-MM-dd" type="date" :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')" /> <!-- $i18n - 出生日期 -->
                  </el-form-item>
                  <el-form-item prop="sex">
                    <el-select v-model="person1.sex" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.gender')"> <!-- $i18n - 性别 -->
                      <dictionary dict-code="gender" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="marriage">
                    <el-select v-model="person1.marriage" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.maritalStatus')"> <!-- $i18n - 婚姻状况 -->
                      <dictionary dict-code="maritalStatus" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="member">
                    <el-select v-model="person1.member" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.numberOfFamilyMembers')"> <!-- $i18n - 家庭成员数目 -->
                      <dictionary dict-code="familyNumbers" />
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 地址 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title') }}</h3> <!-- $i18n - 地址 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="form-content">
                    <el-form-item label-width="0" style="margin-left:0px;margin-bottom:12px;">
                      <el-select v-model="person1.name3" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="site">
                      <el-input v-model="person1.site" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')" /> <!-- $i18n - 地址（如果空间不足，请转下行） -->
                    </el-form-item>
                    <el-form-item prop="site1">
                      <el-input v-model="person1.site1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item prop="city">
                      <el-input v-model="person1.city" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item prop="province">
                      <el-input v-model="person1.province" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item prop="postalcode">
                      <el-input v-model="person1.postalcode " maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <li
                          class="isyes"
                          :class="{ dianji: person1.isstate.isdizhiBT }"
                          @click="person1.isstate.isdizhiBT=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li>  <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: !person1.isstate.isdizhiBT }"
                          @click="person1.isstate.isdizhiBT=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li>  <!-- $i18n - 否 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.isMailingAddress') }}</li>  <!-- $i18n - 您的邮寄地址是否与上方所填地址不同 -->
                      </ul>
                    </div>
                    <div v-show="person1.isstate.isdizhiBT">
                      <el-form-item prop="BTguojia">
                        <el-select
                          v-model="person1.BTguojia"
                          :no-match-text="$t('account.manage.no_data')"
                          filterable
                          :placeholder="$t('registerOpenAccount.common.formPrompt.chooseStateMust')"
                        > <!-- $i18n - 请选择国家(必填) -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="BTsite">
                        <el-input v-model="person1.BTsite" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')" /> <!-- $i18n - 地址（如果空间不足，请转下行） -->
                      </el-form-item>
                      <el-form-item prop="BTsite1">
                        <el-input v-model="person1.BTsite1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等  -->
                      </el-form-item>
                      <el-form-item prop="BTcity">
                        <el-input v-model="person1.BTcity" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                      </el-form-item>
                      <el-form-item prop="BTprovince">
                        <el-input v-model="person1.BTprovince" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                      </el-form-item>
                      <el-form-item prop="BTpostalcode">
                        <el-input v-model="person1.BTpostalcode" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                      </el-form-item>
                    </div>
                    <div class="isBtn">
                      <ul>
                        <li
                          class="isyes"
                          :class="{ dianji: person1.isstate.isguojiBT }"
                          @click="person1.isstate.isguojiBT=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: !person1.isstate.isguojiBT }"
                          @click="person1.isstate.isguojiBT=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentNationalities') }}</li> <!-- $i18n - 您有除上方外的不同国籍吗 -->
                      </ul>
                    </div>
                    <el-form-item v-show="person1.isstate.isguojiBT" prop="guojiabutong">
                      <el-select v-model="person1.guojiabutong" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.common.formPrompt.nationality')"> <!-- $i18n - 国籍 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <li
                          class="isyes"
                          :class="{ dianji: person1.isstate.ischushengguoBT }"
                          @click="person1.isstate.ischushengguoBT=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: !person1.isstate.ischushengguoBT }"
                          @click="person1.isstate.ischushengguoBT=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentBirthStateDescribe') }}</li> <!-- $i18n - 您有除上方外的不同的出生国家吗 -->
                      </ul>
                    </div>
                    <el-form-item
                      v-show="person1.isstate.ischushengguoBT"
                      prop="chushengguobutong"
                    >
                      <el-select v-model="person1.chushengguobutong" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.butongBirthState')"> <!-- $i18n - 不同出生国 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 联系信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.contact.title') }}</h3> <!-- $i18n - 联系信息 -->
                </div>
                <div
                  v-for="(item,index) in person1.contactMessage"
                  :key="index"
                  class="formNapeContent"
                >
                  <ul class="lianxixinxi">
                    <li>
                      <el-form-item :prop="'contactMessage.'+index+'.time'" :rules="rules.time">
                        <el-select v-model="item.time" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.contact.type')"> <!-- $i18n - 手机类型 -->
                          <dictionary dict-code="phoneNumberType" />
                        </el-select>
                      </el-form-item>
                    </li>
                    <li>
                      <el-form-item :prop="'contactMessage.'+index+'.state2'" :rules="rules.state2">
                        <el-select v-model="item.state2" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.contact.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </li>
                    <li>
                      <el-form-item
                        :prop="'contactMessage.'+index+'.phoneNumber'"
                        :rules="rules.phoneNumber"
                      >
                        <el-input v-model="item.phoneNumber" maxlength="11" :placeholder="$t('registerOpenAccount.openAccount.common.contact.contactNumber')" /> <!-- $i18n - 电话号码 -->
                      </el-form-item>
                    </li>
                  </ul>
                </div>
                <div
                  class="addBtn"
                  style="    width: 960px;
                               height: 41px;"
                >
                  <div v-show="person1.contactMessage.length !== 5" class="add" @click="addelement2()">{{ $t('registerOpenAccount.openAccount.common.contact.addOtherNumbers') }}</div> <!-- $i18n - addOtherNumbers -->
                  <div v-show="person1.contactMessage.length !== 1" class="cancel" @click="removeelement2()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 身份证明 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.identity.title') }}</h3> <!-- $i18n - 身份证明 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="form-content">
                    <el-form-item prop="signState">
                      <el-select v-model="person1.signState" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.identity.state')"> <!-- $i18n - 签发国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="identity">
                      <el-select v-model="person1.identity" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"> <!-- $i18n - 身份认证类型 -->
                        <dictionary dict-code="cardType" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="SFidNumber">
                      <el-input v-model="person1.SFidNumber" maxlength="63" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 税务居住地 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div
                    v-for="(item,index) in person1.TaxResidence"
                    :key="index"
                    class="form-content"
                  >
                    <el-form-item
                      :prop="'TaxResidence.'+ index + '.taxState'"
                      :rules="rules.taxState"
                    >
                      <el-select
                        v-model="item.taxState"
                        :no-match-text="$t('account.manage.no_data')"
                        filterable
                        :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"
                      > <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item
                      :prop="'TaxResidence.'+index+'.IDNumber'"
                      :rules="rules.IDNumber"
                    >
                      <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <li
                          class="isyes"
                          :class="{ dianji: !item.isSuiwuNO }"
                          @click="item.isSuiwuNO=false"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: item.isSuiwuNO }"
                          @click="item.isSuiwuNO=true && taxIsBtn('TaxResidence.'+index+'.IDNumber')"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                      </ul>
                    </div>
                    <el-form-item
                      v-show="item.isSuiwuNO"
                      id="radioInline"
                      :prop="'TaxResidence.'+ index + '.suibie'"
                      :rules="rules.suibie"
                    >
                      <el-radio-group v-model="item.suibie">
                        <el-radio
                          v-for="(item1,index1) in item.taxIdentification"
                          :key="index1"
                          :label="item1.dictValue"
                          class="radio"
                        >{{ item1.dictDest }}</el-radio>
                        <el-radio label="qita">
                          <input v-model="item.suiwuQita" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.else')"> <!-- $i18n - 其他 -->
                        </el-radio>
                      </el-radio-group>
                    </el-form-item>
                  </div>
                  <div
                    class="addBtn"
                    style="    width: 960px;
                                 height: 41px;"
                  >
                    <div v-show="person1.TaxResidence.length !== 5" class="add" @click="addelementSW()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加税务居住地 -->
                    <div v-show="person1.TaxResidence.length !== 1" class="cancel" @click="removeelementSW()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                </div>
              </div>
              <!-- 雇佣 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.employment.title') }}</h3> <!-- $i18n - 雇佣 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="workStatus.workStatusleixing">
                    <el-select v-model="person1.workStatus.workStatusleixing" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.employment.employmentStatus')"> <!-- $i18n - 就业状况 -->
                      <dictionary dict-code="employmentType" />
                    </el-select>
                  </el-form-item>
                  <div v-if="person1.isGuyong">
                    <el-form-item prop="workStatus.danwei">
                      <el-input v-model="person1.workStatus.danwei" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.employment.name')" /> <!-- $i18n - 完整的雇佣单位名称 -->
                    </el-form-item>
                    <el-form-item prop="workStatus.xingzhi">
                      <el-select v-model="person1.workStatus.xingzhi" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.employment.nature')"> <!-- $i18n - 商业性质 -->
                        <dictionary dict-code="businessNature" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="workStatus.zhiwei">
                      <el-select
                        v-model="person1.workStatus.zhiwei"
                        :no-match-text="$t('account.manage.no_data')"
                        :placeholder="$t('registerOpenAccount.openAccount.common.employment.position')"
                      > <!-- $i18n - 职位（请填写具体的岗位名称） -->
                        <dictionary dict-code="position" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="workStatus.guojia">
                      <el-select v-model="person1.workStatus.guojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="workStatus.dizhiS">
                      <el-input
                        v-model="person1.workStatus.dizhiS"
                        maxlength="64"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                      /> <!-- $i18n - 地址（如果空间不足，请转下行） -->
                    </el-form-item>
                    <el-form-item prop="workStatus.dizhiX">
                      <el-input v-model="person1.workStatus.dizhiX" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item prop="workStatus.chengshi">
                      <el-input v-model="person1.workStatus.chengshi" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item prop="workStatus.shengshi">
                      <el-input v-model="person1.workStatus.shengshi" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item prop="workStatus.youbian">
                      <el-input v-model="person1.workStatus.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 账户信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.portfolio.title') }}</h3> <!-- $i18n - 账户信息 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="form-content">
                    <el-form-item prop="workStatus1a">
                      <el-select v-model="person1.workStatus1a" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.portfolio.accountPortfolioType')"> <!-- $i18n - 账户类型 -->
                        <!-- <el-option v-for="(item,index) in person1.treasure111" :key="index" :label="item.portfolioTypeDescription" :value="item.dictionaryId"></el-option> -->
                        <dictionary dict-code="accountPortfolioType" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="workStatus1b">
                      <el-select v-model="person1.workStatus1b" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.portfolio.baseCurrency')"> <!-- $i18n - 基础货币 -->
                        <dictionary dict-code="baseCurrency" />
                      </el-select>
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 财富来源 -->
              <div class="formNape elerror">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.wealthSource.titel') }}</h3> <!-- $i18n - 财富来源 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.wealthSource.titelDescribe') }}</p> <!-- $i18n - 请选择所有财富来源并声明将使用哪种财富来源来注资账户。请说明注资账户的每种财富来源所占百分比。 -->
                </div>
                <div
                  v-for="(item,index) in person1.treasure"
                  v-show="person1.isshow.isCFtiantu"
                  :key="index"
                  style="margin-top: 20px;"
                >
                  <div v-if="person1.isshow.isCFleixing">
                    <el-form-item :prop="'treasure.'+index+'.leixing'" :rules="rules.leixing">
                      <el-select
                        v-model="item.leixing"
                        :no-match-text="$t('account.manage.no_data')"
                        :placeholder="$t('registerOpenAccount.openAccount.common.wealthSource.sourceIncome')"
                        @change="caifuTitel(index)"
                      > <!-- $i18n - sourceIncome -->
                        <el-option
                          v-for="(item1,index1) in person1.treasureList"
                          v-show="dropDownBoxDeWeigth(item1.dictValue,item.leixing)"
                          :key="index1"
                          :value="item1.dictValue"
                          :label="item1.dictDest"
                        />
                      </el-select>
                    </el-form-item>
                  </div>
                  <div v-if="item.isCFcontent" class="formNapeContent sourceWealth">
                    <div class="title" v-text="item.leixing1" />
                    <div class="isBtn topNone">
                      <ul style="margin-top: 0px;">
                        <li
                          class="isyes"
                          :class="{ dianji: item.isCFzhuzi }"
                          @click="isCFzhuziT(index)"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: !item.isCFzhuzi }"
                          @click="isCFzhuziF(index)"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.wealthSource.capitalSource') }}</li> <!-- $i18n - 该来源资金是否被用于注资您的账户？ -->
                      </ul>
                    </div>
                    <div v-if="item.isCFzhuzi" class="quanyi">
                      <div class="clearfix">
                        <div class="isContentTop">
                          <el-form-item :prop="'treasure.'+index+'.baifenbi'" :rules="rules.baifenbi">
                            <el-input v-model="item.baifenbi" maxlength="5" />
                          </el-form-item>
                          <div class="icon-percent">%</div>
                        </div>
                        <div
                          class="proportion"
                        >{{ $t('registerOpenAccount.openAccount.common.wealthSource.rootIn') }} {{ item.leixing1 }}</div> <!-- $i18n - 您的账户注资将会有多少比例来源于 -->
                      </div>
                      <div>
                        <el-form-item :prop="'treasure.'+index+'.zhushi'" :rules="rules.zhushi">
                          <el-input v-model="item.zhushi" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.wealthSource.annotation')" /> <!-- $i18n - 注释(在此提供更多详细信息) -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="addBtn">
                  <div v-show="person1.treasure.length !== 5" class="add" @click="addelement4()">{{ $t('registerOpenAccount.openAccount.common.wealthSource.addWealth') }}</div> <!-- $i18n - 添加财富来源 -->
                  <div v-show="person1.treasure.length !== 1" class="cancel" @click="removeelement4()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 安全问题 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.safeQuestion.title') }}</h3> <!-- $i18n - 安全问题 -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="safetyproblem.WTone">
                    <el-select v-model="person1.safetyproblem.WTone" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.issue1')"> <!-- $i18n - 安全问题一 -->
                      <el-option
                        v-for="(item,index) in person1.safetyproblem.safetyIssue"
                        :key="index"
                        :disabled="person1.anquan.wenti1==(index+1)||person1.anquan.wenti1a==(index+1)"
                        :label="item.dictDest"
                        :value="item.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="safetyproblem.answerOne">
                    <el-input v-model="person1.safetyproblem.answerOne" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.answer1')" /> <!-- $i18n - 答案一 -->
                  </el-form-item>
                  <el-form-item prop="safetyproblem.WTtwo">
                    <el-select v-model="person1.safetyproblem.WTtwo" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.issue2')"> <!-- $i18n - 安全问题二 -->
                      <el-option
                        v-for="(item,index) in person1.safetyproblem.safetyIssue"
                        :key="index"
                        :disabled="person1.anquan.wenti2==(index+1)||person1.anquan.wenti2a==(index+1)"
                        :label="item.dictDest"
                        :value="item.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="safetyproblem.answerTwo">
                    <el-input v-model="person1.safetyproblem.answerTwo" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.answer2')" /> <!-- $i18n - 答案二 -->
                  </el-form-item>
                  <el-form-item prop="safetyproblem.WTthree">
                    <el-select v-model="person1.safetyproblem.WTthree" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.issue3')"> <!-- $i18n - 安全问题三 -->
                      <el-option
                        v-for="(item,index) in person1.safetyproblem.safetyIssue"
                        :key="index"
                        :disabled="person1.anquan.wenti3==(index+1)||person1.anquan.wenti3a==(index+1)"
                        :label="item.dictDest"
                        :value="item.dictValue"
                      />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="safetyproblem.answerThree">
                    <el-input v-model="person1.safetyproblem.answerThree" maxlength="200" :placeholder="$t('registerOpenAccount.openAccount.common.safeQuestion.answer3')" /> <!-- $i18n - 答案三 -->
                  </el-form-item>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button
                      class="right-btn BTbtn"
                      plain
                      @click="submitForm('person1')"
                    >{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <!-- <el-button class="left-btn BTbtn" plain @click="beforepage()" >返回 </el-button> -->
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
    // 时间戳验证
    // var getTime = (rule, value, callback) => {
    //   this.$verify.notChinese1(rule, value, callback);
    // };
    // 性别验证
    var validatorSex = (rule, value, callback) => {
      const mate = this.person1.call;
      this.$verify.Gender(rule, value, mate, callback);
    };
    // 地址验证-邮寄
    var validatePassYJ = (rule, value, callback) => {
      const mate = this.person1.isstate.isdizhiBT;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 地址验证-邮寄--接上一行
    var validatePassYJone = (rule, value, callback) => {
      const mate = this.person1.isstate.isdizhiBT;
      const Passable = this.person1.BTsite1;
      this.$verify.stairIfone(rule, value, mate, callback, Passable);
    };
    // 地址验证-国籍
    var validatePassGJi = (rule, value, callback) => {
      const mate = this.person1.isstate.isguojiBT;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 地址验证-出生国家
    var validatePassCSguojia = (rule, value, callback) => {
      const mate = this.person1.isstate.ischushengguoBT;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 国家验证 税务居住地
    var validatePasscountry = (rule, value, callback) => {
      const _this = this;
      if (value === '') {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      } else {
        for (let i = 0; i < this.person1.TaxResidence.length; i++) {
          this.$verify.State(this.person1.TaxResidence[i].taxState, this.person1.TaxResidence[i].taxIdentification, this.person1.taxIdentificationValue, _this, this.lang);
        }
        callback();
      }
    };
    // 税务居住地-纳税识别号
    var validatePassSWnasuihao = (rule, value, callback) => {
      const array1 = this.person1.TaxResidence;
      this.$verify.SWnasuihao(rule, value, array1, callback);
    };
    // 雇佣验证
    var validatePassGY1 = (rule, value, callback) => {
      if (value !== '') {
        if (
          this.person1.workStatus.workStatusleixing === 'beEmployed' ||
          this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
        ) {
          this.person1.isGuyong = true;
        } else {
          this.person1.isGuyong = false;
        }
        callback();
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };
    var validatePassGY = (rule, value, callback) => {
      if (
        this.person1.workStatus.workStatusleixing === 'beEmployed' ||
        this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
      ) {
        if (value !== '') {
          this.$verify.notChinese(rule, value, callback);
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else {
        callback();
      }
    };
    // 税务号
    var theTaxNo = (rule, value, callback) => {
      if (value !== 'N.A.') {
        if (this.person1.SFidNumber === '' || this.person1.identity !== 'idCard' || this.person1.signState !== 'CN') {
          this.$verify.notChinese(rule, value, callback);
        } else {
          this.person1.TaxResidence.forEach(item => {
            if (item.IDNumber === value) {
              if (item.taxState === 'CN') {
                if (this.person1.SFidNumber === value) {
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
        callback();
      }
    };
    // 账户信息-账户类型-账户保证金 年龄满21限制
    var validateAccountMargin = (rule, value, callback) => {
      this.$refs.person1.validateField('birth');
      this.$verify.AccountMargin(rule, value, callback, this.person1.birth);
    };
    // 字母+数字
    var englishNumber = (rule, value, callback) => {
      this.$verify.EnglishNumber(rule, value, callback);
    };
    // 出生日期
    var validateBirth = (rule, value, callback) => {
      const val = this.person1.workStatus1a;
      this.$verify.Birth(rule, value, callback, val);
    };
    // 数字正则验证
    var validateShuziyanzheng = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    // 邮政正则验证
    var validateShuziyanzheng777 = (rule, value, callback) => {
      if (this.person1.isstate.isdizhiBT) {
        this.$verify.notChinese(rule, value, callback);
      } else {
        callback();
      }
    };
    // 财富来源类型验证
    var wealthType = (rule, value, callback) => {
      if (value !== '') {
        let dictDest = null;
        for (let i = 0; i < this.person1.treasureList.length; i++) {
          if (value === this.person1.treasureList[i].dictValue) {
            dictDest = this.person1.treasureList[i].dictDest;
          }
        }
        for (let i = 0; i < this.person1.treasure.length; i++) {
          if (value === this.person1.treasure[i].leixing) {
            this.person1.treasure[i].leixing1 = dictDest;
          }
        }
        callback();
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };
    // 财富来源百分比判断
    var validatePercentage = (rule, value, callback) => {
      const ChunNumber = /^[0-9]*$/;
      if (value !== '') {
        if (ChunNumber.test(value) === true) {
          if (value <= 100) {
            callback();
          } else {
            callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.notAllOwpercent'))); // $i18n - 不允许超过百分百
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.illegality'))); // $i18n - 请不要输入非法字符
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };
    // 电话正则验证-中国
    var validateZdianhua = (rule, value, callback) => {
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
    // 安全验证
    var validateSafety = (rule, value, callback) => {
      const obj = {
        safetyIssue: this.person1.safetyproblem.safetyIssue,
        anquan: this.person1.anquan,
        type1: 0
      };
      if (this.person1.safetyproblem.WTone === value) {
        obj.type1 = 1;
      } else if (this.person1.safetyproblem.WTtwo === value) {
        obj.type1 = 2;
      } else if (this.person1.safetyproblem.WTthree === value) {
        obj.type1 = 3;
      }
      this.$verify.Safety(rule, value, obj, callback);
    };

    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      person1: {
        yemianzhuangtai: false,
        // 个人信息
        call: '',
        callList: [],
        surname: '',
        name: '',
        name3: '',
        birth: '',
        birth1: '',
        sex: '',
        gender: [],
        marriage: '',
        member: '',
        IndividualType: '', // 个人类型

        // 无纳税识别号
        TIN1: '',
        TIN2: '',
        TIN3: '',

        // 地址
        site: '',
        site1: '',
        city: '',
        province: '',
        postalcode: '',
        BTguojia: '',
        BTsite: '',
        BTsite1: '',
        BTcity: '',
        BTprovince: '',
        BTpostalcode: '',
        guojiabutong: '',
        chushengguobutong: '',
        addressType: { // 地址类型
          dwell: null,
          mail: null,
          hire: null
        },
        // 联系信息
        contactMessage: [
          {
            time: '',
            state2: '',
            phoneNumber: ''
          }
        ],

        // 身份证明
        signState: '',
        identity: '',
        SFidNumber: '',
        // 税务居住地
        TaxResidence: [
          {
            taxState: '',
            taxState1111: '',
            IDNumber: '',
            IDNumber1: '',
            isratepaying: '',
            suibie: '',
            suiwuQita: '',
            isSuiwuNO: false,
            taxIdentification: []
          }
        ],
        taxIdentificationValue: [],
        // 雇佣
        workStatus: {
          workStatusleixing: '',
          hire: [],
          danwei: '',
          xingzhi: '',
          zhiwei: '',
          guojia: '',
          dizhiS: '',
          dizhiX: '',
          chengshi: '',
          shengshi: '',
          youbian: ''
        },

        // 账户信息

        workStatus1a: '',
        workStatus1b: '',
        treasure111: [],
        // 财富来源
        treasure: [],
        leixingNumber: null,
        treasureList: [],
        zongBaifenbi: 0, // 计算出来的总百分比

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
            //   safeQuestion: "答案一",
            //   dictionaryId: "id1"
            // },
            // {
            //   safeQuestion: "答案二",
            //   dictionaryId: "id2"
            // },
            // {
            //   safeQuestion: "答案三",
            //   dictionaryId: "id3"
            // }
          ]
        },

        isstate: {
          Telephone: false,
          isSuiwu: false,
          iscaifu: false,
          isbaifenbi: false,
          isTelephone: false,
          isdizhiBT: false,
          isguojiBT: false,
          ischushengguoBT: false
        },
        isGuyong: false,
        isshow: {
          isCFtiantu: false,
          isCFleixing: false
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
      newValue: {
        Personalperinfo: null, // 个人信息
        Personaladdress: null, // 个人地址
        Personalcontact: null, // 个人联系信息
        Personalidentifiable: null, // 个人身份证明信息
        Personaltaxresidence: null, // 个人税务居住地信息
        Personalemployment: null, // 个人雇佣信息
        Personalaccountportfolio: null, // 个人账户投资组合信息
        Personalatreasuredsources: null, // 个人财富来源信息
        Personalsafety: null // 个人安全问题信息
      },

      rules: {
        // 个人信息
        call: [{ validator: notChinese, trigger: 'change' }],
        surname: [{ validator: notChinese, trigger: 'blur' }],
        name: [{ validator: notChinese, trigger: 'blur' }],
        sex: [{ validator: validatorSex, trigger: 'change' }],
        marriage: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入
        member: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入
        birth: [{ validator: validateBirth, trigger: 'change' }],
        // 地址
        name3: [{ validator: notChinese, trigger: 'change' }],
        site: [{ validator: notChinese, trigger: 'blur' }],
        site1: [{ validator: notChinese1, trigger: 'blur' }],
        city: [{ validator: notChinese, trigger: 'blur' }],
        province: [{ validator: notChinese, trigger: 'blur' }],
        postalcode: [{ validator: validateShuziyanzheng, trigger: 'blur' }],

        BTguojia: [{ validator: validatePassYJ, trigger: 'change' }],
        BTsite: [{ validator: validatePassYJ, trigger: 'blur' }],
        BTsite1: [{ validator: validatePassYJone, trigger: 'blur' }],
        BTcity: [{ validator: validatePassYJ, trigger: 'blur' }],
        BTprovince: [{ validator: validatePassYJ, trigger: 'blur' }],
        BTpostalcode: [{ validator: validateShuziyanzheng777, trigger: 'blur' }],
        guojiabutong: [{ validator: validatePassGJi, trigger: 'change' }],
        chushengguobutong: [{ validator: validatePassCSguojia, trigger: 'change' }],

        // 联系信息
        time: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入
        state2: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入
        phoneNumber: [{ validator: validateZdianhua, trigger: 'blur' }],

        // 身份证明
        signState: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入
        identity: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入
        SFidNumber: [{ validator: englishNumber, trigger: 'blur' }],
        // 税务居住地
        taxState: [{ validator: validatePasscountry, trigger: 'change' }],
        IDNumber: [{ validator: theTaxNo, trigger: 'blur' }],
        suibie: [{ validator: validatePassSWnasuihao, trigger: 'change' }],

        // 雇佣
        'workStatus.workStatusleixing': [
          { validator: validatePassGY1, trigger: 'change' }
        ],
        'workStatus.danwei': [{ validator: validatePassGY, trigger: 'blur' }],
        'workStatus.xingzhi': [
          { validator: validatePassGY, trigger: 'change' }
        ],
        'workStatus.zhiwei': [{ validator: validatePassGY, trigger: 'change' }],
        'workStatus.guojia': [{ validator: validatePassGY, trigger: 'change' }],
        'workStatus.dizhiS': [{ validator: validatePassGY, trigger: 'blur' }],
        'workStatus.dizhiX': [{ validator: notChinese1, trigger: 'blur' }],
        'workStatus.chengshi': [{ validator: validatePassGY, trigger: 'blur' }],
        'workStatus.shengshi': [{ validator: validatePassGY, trigger: 'blur' }],
        'workStatus.youbian': [{ validator: validatePassGY, trigger: 'blur' }],

        // 账户类型
        workStatus1a: [{ validator: validateAccountMargin, trigger: 'change' }],
        workStatus1b: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.enter')}`, trigger: 'change' }], // $i18n - 请输入

        // 财富来源
        leixing: [{ validator: wealthType, trigger: 'change' }],
        baifenbi: [{ validator: validatePercentage, trigger: 'blur' }],
        zhushi: [{ validator: notChinese1, trigger: 'blur' }],

        // 安全问题
        'safetyproblem.WTone': [{ validator: validateSafety, trigger: 'change' }],
        'safetyproblem.answerOne': [{ validator: notChinese, trigger: 'blur' }],
        'safetyproblem.WTtwo': [{ validator: validateSafety, trigger: 'change' }],
        'safetyproblem.answerTwo': [{ validator: notChinese, trigger: 'blur' }],
        'safetyproblem.WTthree': [{ validator: validateSafety, trigger: 'change' }],
        'safetyproblem.answerThree': [{ validator: notChinese, trigger: 'blur' }]
      }
    };
  },
  watch: {
    person1: {
      // 深度监听，可监听到对象、数组的变化
      handler(val, oldVal) {
        let zongBaifenbi = 0;
        for (let i = 0; i < this.person1.treasure.length; i++) {
          if (this.person1.treasure[i].isCFzhuzi) {
            zongBaifenbi += parseInt(this.person1.treasure[i].baifenbi);
          }
        }
        this.person1.zongBaifenbi = zongBaifenbi;
      },
      deep: true
    }
  },
  created() {
    this.person1.name3 = this.$store.state.user.applyData.xinxi.country;
    // 获取字典表
    this.taxDT();
    this.wealthType();
    this.addressTypeDT();
    this.IndividualTypeDT();
    this.safetyDT();

    // 获取用户已填入的信息
    this.getAccountperinfo();
    this.getPersonalcontact();
    this.getPersonalidentifiable();
    this.getPersonalemploy();
    this.getPersonalaccountportfolio();
    this.getPersonalatreasuredsources();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    // 获取个人信息
    getAccountperinfo() {
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
              this.newValue.Personalperinfo = res.object;
              this.person1.call = res.object[0].appellation;
              this.person1.surname = res.object[0].lastName;
              this.person1.name = res.object[0].firstName;
              this.person1.birth = new Date(res.object[0].birthday);
              this.person1.sex = res.object[0].gender;
              this.person1.marriage = res.object[0].maritalStatus;
              this.person1.member = res.object[0].numberOfFamilyMembers;
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
    // 获取个人地址
    getPersonaladdress() {
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
              this.newValue.Personaladdress = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === this.person1.addressType.dwell) { // 居住地址
                // if (i === 0) { // 居住地址
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.person1.site = dizhi[0];
                    this.person1.site1 = dizhi[1];
                  } else {
                    this.person1.site = res.object[i].address;
                  }
                  this.person1.city = res.object[i].city;
                  this.person1.name3 = res.object[i].country;
                  this.person1.province = res.object[i].rovince;
                  this.person1.postalcode = res.object[i].zipCode;
                  if (res.object[i].otherCountry !== '') {
                    this.person1.isstate.isguojiBT = true;
                    this.person1.guojiabutong = res.object[i].otherCountry;
                  }
                  if (res.object[i].otherBirthCountry !== '') {
                    this.person1.isstate.ischushengguoBT = true;
                    this.person1.chushengguobutong = res.object[i].otherBirthCountry;
                  }
                } else if (res.object[i].addressType === this.person1.addressType.mail) { // 邮寄地址
                  this.person1.isstate.isdizhiBT = true;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.person1.BTsite = dizhi[0];
                    this.person1.BTsite1 = dizhi[1];
                  } else {
                    this.person1.BTsite = res.object[i].address;
                  }
                  this.person1.BTcity = res.object[i].city;
                  this.person1.BTguojia = res.object[i].country;
                  this.person1.BTprovince = res.object[i].rovince;
                  this.person1.BTpostalcode = res.object[i].zipCode;
                } else if (res.object[i].addressType === this.person1.addressType.hire) { // 雇佣
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.person1.workStatus.dizhiS = dizhi[0];
                    this.person1.workStatus.dizhiX = dizhi[1];
                  } else {
                    this.person1.workStatus.dizhiS = res.object[i].address;
                  }
                  this.person1.workStatus.chengshi = res.object[i].city;
                  this.person1.workStatus.guojia = res.object[i].country;
                  this.person1.workStatus.shengshi = res.object[i].rovince;
                  this.person1.workStatus.youbian = res.object[i].zipCode;
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
        }).catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 获取个人联系信息
    getPersonalcontact() {
      this.$http({
        url: '/account/accountPerContact/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.Personalcontact = res.object;

              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  this.person1.contactMessage[i].time =
                    res.object[i].contactNumberType;
                  this.person1.contactMessage[i].state2 =
                    res.object[i].country;
                  this.person1.contactMessage[i].phoneNumber =
                    res.object[i].contactNumber;
                } else {
                  this.person1.contactMessage.push({
                    time: res.object[i].contactNumberType,
                    state2: res.object[i].country,
                    phoneNumber: res.object[i].contactNumber
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
              this.newValue.Personalidentifiable = res.object;
              this.person1.signState = res.object[0].issuingCountry;
              this.person1.identity = res.object[0].idCardType;
              this.person1.SFidNumber = res.object[0].idNumber;
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
              this.newValue.Personaltaxresidence = res.object;
              const TaxResidence = {
                taxState: '',
                taxState1111: '',
                IDNumber: '',
                IDNumber1: '',
                isratepaying: '',
                suibie: '',
                suiwuQita: '',
                isSuiwuNO: false,
                taxIdentification: JSON.parse(JSON.stringify(this.person1.taxIdentificationValue))
              };
              this.person1.TaxResidence = [];

              for (let i = 0; i < res.object.length; i++) {
                TaxResidence.taxState = res.object[i].taxResidenceCountry;
                let index = 0;
                if (res.object[i].noTaxIdentificationNumber !== '') {
                  TaxResidence.IDNumber1 = '';
                  TaxResidence.isSuiwuNO = true;
                  for (let j = 0; j < this.person1.taxIdentificationValue.length; j++) {
                    if (res.object[i].noTaxIdentificationNumber === this.person1.taxIdentificationValue[j].dictValue) {
                      index++;
                      TaxResidence.suibie = res.object[i].noTaxIdentificationNumber;
                    }
                  }
                  if (index === 0) {
                    TaxResidence.suibie = 'qita';
                    TaxResidence.suiwuQita = res.object[i].noTaxIdentificationNumber;
                  } else {
                    TaxResidence.suiwuQita = '';
                  }
                } else {
                  TaxResidence.isSuiwuNO = false;
                  TaxResidence.IDNumber1 = res.object[i].taxResidenceIdCardNumber;
                }
                this.person1.TaxResidence.push(JSON.parse(JSON.stringify(TaxResidence)));
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
    // 获取个人雇佣信息
    getPersonalemploy() {
      this.$http({
        url: '/account/accountPerEmployment/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.Personalemployment = res.object;
              if (
                res.object[0].employmentStatus === 'beEmployed' ||
                res.object[0].employmentStatus === 'toEntrepreneurship'
              ) {
                this.person1.workStatus.xingzhi =
                  res.object[0].commercialNature;
                this.person1.workStatus.danwei = res.object[0].companyName;
                this.person1.workStatus.workStatusleixing =
                  res.object[0].employmentStatus;
                this.person1.workStatus.zhiwei = res.object[0].position;
              } else {
                this.person1.workStatus.workStatusleixing =
                  res.object[0].employmentStatus;
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
    // 获取账户投资组合信息
    getPersonalaccountportfolio() {
      this.$http({
        url: '/account/accountPerPortfolio/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.Personalaccountportfolio = res.object;
              this.person1.workStatus1a = res.object[0].accountPortfolioType;
              this.person1.workStatus1b = res.object[0].baseCurrency;
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
    // 获取个人财富来源
    getPersonalatreasuredsources() {
      this.$http({
        url: '/account/accountPerWealthSource/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.Personalatreasuredsources = res.object;
              let leixing = '';
              let leixing1 = '';
              let isZhuce = '';
              let baifenbi = '';
              let zhushi = '';
              let isCFzhuzi = false;
              let isCFcontent = false;

              for (let i = 0; i < res.object.length; i++) {
                leixing = res.object[i].incomeType;
                if (leixing) {
                  isCFcontent = true;
                } else {
                  isCFcontent = false;
                }
                if (res.object[i].isCapitalInjection === true) {
                  isCFzhuzi = true;
                } else {
                  isCFzhuzi = false;
                }
                isZhuce = res.object[i].isCapitalInjection;
                baifenbi = res.object[i].percentageOfFunds;
                zhushi = res.object[i].sourceDesc ? res.object[i].sourceDesc : '';
                if (leixing) {
                  for (let j = 0; j < this.person1.treasureList.length; j++) {
                    if (leixing === this.person1.treasureList[j].dictValue) {
                      leixing1 = this.person1.treasureList[j].dictDest;
                    }
                  }
                }
                this.person1.treasure.push({
                  leixing,
                  leixing1,
                  isZhuce,
                  baifenbi,
                  zhushi,
                  isCFzhuzi,
                  isCFcontent
                });
                this.person1.isshow.isCFleixing = true;
                this.person1.isshow.isCFtiantu = true;
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
    // 个人安全问题
    getPersonalsafety() {
      this.$http({
        url: '/account/accountPerSafeQuestion/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.Personalsafety = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  this.person1.safetyproblem.WTone = res.object[i].safeQuestion;
                  this.person1.safetyproblem.answerOne = res.object[i].answer;
                } else if (i === 1) {
                  this.person1.safetyproblem.WTtwo = res.object[i].safeQuestion;
                  this.person1.safetyproblem.answerTwo = res.object[i].answer;
                } else if (i === 2) {
                  this.person1.safetyproblem.WTthree = res.object[i].safeQuestion;
                  this.person1.safetyproblem.answerThree = res.object[i].answer;
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
    // 获取字典表  DT
    taxDT() { // -- 税务居住地
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxIdentificationNumber', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.person1.TaxResidence[0].taxIdentification = res.object;
            this.person1.taxIdentificationValue = JSON.parse(JSON.stringify(res.object));
            this.getPersonaltaxresidence();
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
    // 财富来源类型  personalWealthSource
    wealthType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalWealthSource', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          this.person1.treasureList = res.object;
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
    // -- 地址类型
    addressTypeDT() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'addressType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            for (let i = 0; i < res.object.length; i++) {
              this.person1.addressType.dwell = res.object[0].dictValue;
              this.person1.addressType.mail = res.object[1].dictValue;
              this.person1.addressType.hire = res.object[2].dictValue;
              this.getPersonaladdress();
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
    IndividualTypeDT() { // 个人类型
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            // this.person1.IndividualType = res.object[0].dictValue;
            this.person1.IndividualType = 'personalInfo';
            if (res.message == null) {
              return false;
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
    safetyDT() { // -- 安全问题
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'safeQuestionType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.person1.safetyproblem.safetyIssue = res.object;
            this.getPersonalsafety();
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
    // 联系电话增删
    addelement() {
      this.person1.contact.push({
        phonetype: '',
        relationState: '',
        number: ''
      });
      this.person1.isstate.isTelephone = true;
    },
    removeelement() {
      if (this.person1.contact.length === 1) {
        return false;
      }
      this.person1.contact.pop(this.person1.contact.length - 1);
    },
    // 税务增删
    addelementSW() {
      this.person1.TaxResidence.push({
        taxState: '',
        taxState1111: '',
        IDNumber: '',
        IDNumber1: '',
        isratepaying: '',
        suibie: '',
        suiwuQita: '',
        isSuiwuNO: false,
        taxIdentification: JSON.parse(JSON.stringify(this.person1.taxIdentificationValue))
      });
      this.person1.isstate.isSuiwu = true;
    },
    removeelementSW() {
      if (this.person1.TaxResidence.length === 1) {
        return false;
      }
      this.person1.TaxResidence.pop(this.person1.TaxResidence.length - 1);
    },
    // is纳税识别号-验证证件号码
    taxIsBtn(VerifyPath) {
      let num = 1;
      const timerId = setInterval(() => {
        num--;
        this.$refs.person1.validateField(VerifyPath);
        if (num === 0) {
          clearInterval(timerId);
        }
      }, 100);
      return true;
    },
    // 财富来源下拉框去重
    dropDownBoxDeWeigth(dictValue, wealth) { // dictValue下拉框的每一个项 wealth当前选中的下拉框选中项
      if (dictValue === wealth) { // 完成 1,当前选中的显示
        return true;
      }
      for (const i in this.person1.treasure) { // 2,其他下拉框选中的隐藏
        if (dictValue === this.person1.treasure[i].leixing) {
          return false;
        }
      }
      return true;
    },
    // 财富来源点击事件
    treasureBtn() {},
    // 财富来源添加
    addelement4() {
      if (this.person1.treasure.length < this.person1.treasureList.length) {
        this.person1.treasure.push({
          leixing: '',
          leixing1: '',
          isZhuce: '',
          baifenbi: '',
          zhushi: '',
          isCFcontent: false,
          isCFzhuzi: false
          // isCFzhuzi: false
        });
        this.person1.isshow.isCFleixing = true;
        this.person1.isshow.isCFtiantu = true;
      } else {
        return false;
      }
    },
    removeelement4() {
      if (this.person1.treasure.length === 1) {
        return false;
      } else {
        this.person1.treasure.pop(this.person1.treasure.length - 1);
      }
    },
    caifuTitel(index) {
      this.person1.treasure[index].isCFcontent = true;
      this.person1.leixingNumber = index;
    },
    isCFzhuziT(index) {
      this.person1.treasure[index].isCFzhuzi = true;
      this.person1.treasure[index].isCFzhuzi = true;
    },
    isCFzhuziF(index) {
      this.person1.treasure[index].isCFzhuzi = false;
      this.person1.treasure[index].isCFzhuzi = false;
    },
    // 联系添加
    addelement2() {
      if (this.person1.contactMessage.length === 5) {
        return false;
      } else {
        this.person1.contactMessage.push({
          time: '',
          state: '',
          phoneNumber: ''
        });
      }
    },
    removeelement2() {
      if (this.person1.contactMessage.length === 1) {
        return false;
      } else {
        if (this.person1.contactMessage === 2) {
          this.person1.contactMessage.pop(this.person1.contactMessage);
          if (this.person1.contactMessage.length === 1) {
            return false;
          }
        }

        this.person1.contactMessage.pop(this.person1.contactMessage);
      }
    },

    // 提交
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.person1.zongBaifenbi === 100) {
            this.submitFormData();
          } else {
            this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.treasureTotal')); // $i18n - 财富来源比例合计必须为100
          }
        } else {
          return false;
        }
      });
    },

    // 提交表单数据
    submitFormData() {
      const updatedBy = this.$store.state.user.applyData.xinxi.id;
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate(); // 创建时

      let accountPerInfo = {}; // 个人信息 -1
      let accountPerAddressList = []; // 地址 -2
      let accountPerContactList = []; // 联系方式 -3
      let accountPerIdentity = {}; // 身份信息 -4
      const accountPerTaxesList = []; // 税务居住地 -5
      let accountPerEmployment = {}; // guyong -6
      let accountPerPortfolio = {}; // 账户信息-投资组合 -7
      const accountPerWealthSourceList = []; // 财富来源 -8
      let accountPerSafeQuestionList = []; // anquan -9
      // 不同国籍 - 不同出生国
      let otherBirthCountry = null;
      let otherCountry = null;
      if (this.person1.isstate.ischushengguoBT) {
        otherBirthCountry = this.person1.chushengguobutong;
      }
      if (this.person1.isstate.isguojiBT) {
        otherCountry = this.person1.guojiabutong;
      }
      // }
      // 需提交的表单数据
      // -地址
      if (this.newValue.Personaladdress === null) {
        accountPerAddressList = [
          {
            accountId,
            createdBy,
            updatedBy,
            address: this.person1.site1
              ? this.person1.site + '-#-' + this.person1.site1
              : this.person1.site,
            addressType: this.person1.addressType.dwell,
            city: this.person1.city,
            country: this.person1.name3,
            otherBirthCountry,
            otherCountry,
            rovince: this.person1.province,
            zipCode: this.person1.postalcode
          }
        ];
        // 地址-邮寄地址判断
        if (this.person1.isstate.isdizhiBT) {
          accountPerAddressList.push({
            accountId,
            createdBy,
            updatedBy,
            address: this.person1.BTsite1
              ? this.person1.BTsite + '-#-' + this.person1.BTsite1
              : this.person1.BTsite,
            addressType: this.person1.addressType.mail,
            city: this.person1.BTcity,
            country: this.person1.BTguojia,
            otherBirthCountry,
            otherCountry,
            rovince: this.person1.BTprovince,
            zipCode: this.person1.BTpostalcode
          });
        }
        // 雇佣地址
        if (
          this.person1.workStatus.workStatusleixing === 'beEmployed' ||
          this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
        ) {
          accountPerAddressList.push({
            accountId,
            createdBy,
            updatedBy,
            address: this.person1.workStatus.dizhiX
              ? this.person1.workStatus.dizhiS + '-#-' + this.person1.workStatus.dizhiX : this.person1.workStatus.dizhiS,
            addressType: this.person1.addressType.hire,
            city: this.person1.workStatus.chengshi,
            country: this.person1.workStatus.guojia,
            rovince: this.person1.workStatus.shengshi,
            zipCode: this.person1.workStatus.youbian
          });
        }
      } else {
        let Personaladdress1 = null; // 居住地址
        let Personaladdress2 = null; // 邮寄地址
        let Personaladdress3 = null; // 雇佣地址
        for (let i = 0; i < this.newValue.Personaladdress.length; i++) {
          if (this.newValue.Personaladdress[i].addressType === this.person1.addressType.dwell) { // 居住
            Personaladdress1 = JSON.parse(JSON.stringify(this.newValue.Personaladdress[i]));
          } else if (this.newValue.Personaladdress[i].addressType === this.person1.addressType.mail) {
            Personaladdress2 = JSON.parse(JSON.stringify(this.newValue.Personaladdress[i]));
          } else if (this.newValue.Personaladdress[i].addressType === this.person1.addressType.hire) {
            Personaladdress3 = JSON.parse(JSON.stringify(this.newValue.Personaladdress[i]));
          }
        }
        this.newValue.Personaladdress = [];
        if (Personaladdress1 !== null) {
          Personaladdress1.address = this.person1.site1 ? this.person1.site + '-#-' + this.person1.site1 : this.person1.site;
          Personaladdress1.city = this.person1.city;
          Personaladdress1.country = this.person1.name3;
          Personaladdress1.otherBirthCountry = otherBirthCountry;
          Personaladdress1.otherCountry = otherCountry;
          Personaladdress1.rovince = this.person1.province;
          Personaladdress1.zipCode = this.person1.postalcode;
          this.newValue.Personaladdress.push(JSON.parse(JSON.stringify(Personaladdress1)));
        } else {
          Personaladdress1 = {
            accountId,
            createdBy,
            updatedBy,
            address: this.person1.site1
              ? this.person1.site + '-#-' + this.person1.site1
              : this.person1.site,
            addressType: this.person1.addressType.dwell,
            city: this.person1.city,
            country: this.person1.name3,
            otherBirthCountry,
            otherCountry,
            rovince: this.person1.province,
            zipCode: this.person1.postalcode
          };
          this.newValue.Personaladdress.push(JSON.parse(JSON.stringify(Personaladdress1)));
        }
        if (Personaladdress2 === null) {
          if (this.person1.isstate.isdizhiBT) {
            Personaladdress2 = {
              accountId,
              createdBy,
              updatedBy,
              address: this.person1.BTsite1
                ? this.person1.BTsite + '-#-' + this.person1.BTsite1
                : this.person1.BTsite,
              addressType: this.person1.addressType.mail,
              city: this.person1.BTcity,
              country: this.person1.BTguojia,
              otherBirthCountry,
              otherCountry,
              rovince: this.person1.BTprovince,
              zipCode: this.person1.BTpostalcode
            };
            this.newValue.Personaladdress.push(JSON.parse(JSON.stringify(Personaladdress2)));
          }
        } else {
          if (this.person1.isstate.isdizhiBT) {
            Personaladdress2.address = this.person1.BTsite1 ? this.person1.BTsite + '-#-' + this.person1.BTsite1 : this.person1.BTsite;
            Personaladdress2.city = this.person1.BTcity;
            Personaladdress2.country = this.person1.BTguojia;
            Personaladdress2.otherBirthCountry = otherBirthCountry;
            Personaladdress2.otherCountry = otherCountry;
            Personaladdress2.rovince = this.person1.BTprovince;
            Personaladdress2.zipCode = this.person1.BTpostalcode;
            this.newValue.Personaladdress.push(JSON.parse(JSON.stringify(Personaladdress2)));
          }
        }
        if (Personaladdress3 === null) {
          if (
            this.person1.workStatus.workStatusleixing === 'beEmployed' ||
            this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
          ) {
            Personaladdress3 = {
              accountId,
              createdBy,
              updatedBy,
              address: this.person1.workStatus.dizhiX ? this.person1.workStatus.dizhiS + '-#-' + this.person1.workStatus.dizhiX : this.person1.workStatus.dizhiS,
              addressType: this.person1.addressType.hire,
              city: this.person1.workStatus.chengshi,
              country: this.person1.workStatus.guojia,
              rovince: this.person1.workStatus.shengshi,
              zipCode: this.person1.workStatus.youbian
            };
            this.newValue.Personaladdress.push(JSON.parse(JSON.stringify(Personaladdress3)));
          }
        } else {
          if (
            this.person1.workStatus.workStatusleixing === 'beEmployed' ||
            this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
          ) {
            Personaladdress3.address = this.person1.workStatus.dizhiX ? this.person1.workStatus.dizhiS + '-#-' + this.person1.workStatus.dizhiX : this.person1.workStatus.dizhiS;
            Personaladdress3.city = this.person1.workStatus.chengshi;
            Personaladdress3.country = this.person1.workStatus.guojia;
            Personaladdress3.otherBirthCountry = otherBirthCountry;
            Personaladdress3.otherCountry = otherCountry;
            Personaladdress3.rovince = this.person1.workStatus.shengshi;
            Personaladdress3.zipCode = this.person1.workStatus.youbian;
          } else {
            Personaladdress3.address = null;
            Personaladdress3.city = null;
            Personaladdress3.country = null;
            Personaladdress3.otherBirthCountry = otherBirthCountry;
            Personaladdress3.otherCountry = otherCountry;
            Personaladdress3.rovince = null;
            Personaladdress3.zipCode = null;
          }
          this.newValue.Personaladdress.push(JSON.parse(JSON.stringify(Personaladdress3)));
        }
      }

      // 个人联系方式
      if (this.newValue.Personalcontact == null) {
        accountPerContactList = [];
        for (let i = 0; i < this.person1.contactMessage.length; i++) {
          accountPerContactList.push({
            accountId,
            contactNumber: this.person1.contactMessage[i].phoneNumber,
            contactNumberType: this.person1.contactMessage[i].time,
            country: this.person1.contactMessage[i].state2,
            createdBy,
            createdDate,
            updatedBy
          });
        }
      } else {
        const index = this.newValue.Personalcontact.length;
        for (let i = 0; i < this.person1.contactMessage.length; i++) {
          if (this.person1.contactMessage.length < index) {
            this.newValue.Personalcontact.splice(this.person1.contactMessage.length); // 返被删除的项  索引最大那项
            if (i < index) {
              this.newValue.Personalcontact[
                i
              ].contactNumber = this.person1.contactMessage[i].phoneNumber;
              this.newValue.Personalcontact[
                i
              ].country = this.person1.contactMessage[i].state2;
              this.newValue.Personalcontact[
                i
              ].contactNumberType = this.person1.contactMessage[i].time;
            }
          } else {
            if (i < index) {
              this.newValue.Personalcontact[
                i
              ].contactNumber = this.person1.contactMessage[i].phoneNumber;
              this.newValue.Personalcontact[
                i
              ].country = this.person1.contactMessage[i].state2;
              this.newValue.Personalcontact[
                i
              ].contactNumberType = this.person1.contactMessage[i].time;
            } else {
              this.newValue.Personalcontact.push({
                accountId,
                createdBy,
                createdDate,
                updatedBy,
                contactNumber: this.person1.contactMessage[i].phoneNumber,
                country: this.person1.contactMessage[i].state2,
                contactNumberType: this.person1.contactMessage[i].time
              });
            }
          }
        }
      }

      // 个人雇佣(就业)信息
      if (this.newValue.Personalemployment === null) {
        if (
          this.person1.workStatus.workStatusleixing === 'beEmployed' ||
          this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
        ) {
          accountPerEmployment = {
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            commercialNature: this.person1.workStatus.xingzhi,
            companyName: this.person1.workStatus.danwei,
            employmentStatus: this.person1.workStatus.workStatusleixing,
            position: this.person1.workStatus.zhiwei
          };
        } else {
          accountPerEmployment = {
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            commercialNature: null,
            companyName: null,
            employmentStatus: this.person1.workStatus.workStatusleixing,
            position: null
          };
        }
      } else {
        if (
          this.person1.workStatus.workStatusleixing === 'beEmployed' ||
          this.person1.workStatus.workStatusleixing === 'toEntrepreneurship'
        ) {
          for (let i = 0; i < this.newValue.Personalemployment.length; i++) {
            this.newValue.Personalemployment[0].commercialNature = this.person1.workStatus.xingzhi;
            this.newValue.Personalemployment[0].companyName = this.person1.workStatus.danwei;
            this.newValue.Personalemployment[0].employmentStatus = this.person1.workStatus.workStatusleixing;
            this.newValue.Personalemployment[0].position = this.person1.workStatus.zhiwei;
          }
        } else {
          this.newValue.Personalemployment[0].employmentStatus = this.person1.workStatus.workStatusleixing;
        }
      }

      // 身份信息
      if (this.newValue.Personalidentifiable === null) {
        accountPerIdentity = {
          accountId,
          createdBy,
          createdDate,
          updatedBy,
          idCardType: this.person1.identity,
          idNumber: this.person1.SFidNumber,
          issuingCountry: this.person1.signState
        };
      } else {
        for (let i = 0; i < this.newValue.Personalidentifiable.length; i++) {
          this.newValue.Personalidentifiable[0].idCardType = this.person1.identity;
          this.newValue.Personalidentifiable[0].idNumber = this.person1.SFidNumber;
          this.newValue.Personalidentifiable[0].issuingCountry = this.person1.signState;
        }
      }

      // 个人账户投资组合-
      if (this.newValue.Personalaccountportfolio === null) {
        accountPerPortfolio = {
          accountId,
          createdBy,
          createdDate,
          updatedBy,
          accountPortfolioType: this.person1.workStatus1a,
          baseCurrency: this.person1.workStatus1b
        };
      } else {
        for (let i = 0; i < this.newValue.Personalaccountportfolio.length; i++) {
          this.newValue.Personalaccountportfolio[0].accountPortfolioType = this.person1.workStatus1a;
          this.newValue.Personalaccountportfolio[0].baseCurrency = this.person1.workStatus1b;
        }
      }
      // 个人安全问题
      if (this.newValue.Personalsafety === null) {
        accountPerSafeQuestionList = [
          {
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            answer: this.person1.safetyproblem.answerOne,
            safeQuestion: this.person1.safetyproblem.WTone
          },
          {
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            answer: this.person1.safetyproblem.answerTwo,
            safeQuestion: this.person1.safetyproblem.WTtwo
          },
          {
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            answer: this.person1.safetyproblem.answerThree,
            safeQuestion: this.person1.safetyproblem.WTthree
          }
        ];
      } else {
        for (let i = 0; i < this.newValue.Personalsafety.length; i++) {
          if (i === 0) {
            this.newValue.Personalsafety[i].safeQuestion = this.person1.safetyproblem.WTone;
            this.newValue.Personalsafety[i].answer = this.person1.safetyproblem.answerOne;
          }
          if (i === 1) {
            this.newValue.Personalsafety[i].safeQuestion = this.person1.safetyproblem.WTtwo;
            this.newValue.Personalsafety[i].answer = this.person1.safetyproblem.answerTwo;
          }
          if (i === 2) {
            this.newValue.Personalsafety[i].safeQuestion = this.person1.safetyproblem.WTthree;
            this.newValue.Personalsafety[i].answer = this.person1.safetyproblem.answerThree;
          }
        }
      }
      // 个人税务居住地
      // 税务居住地判断  --判断税务号
      let noTaxIdentificationNumber = null;
      if (this.newValue.Personaltaxresidence == null) {
        for (let i = 0; i < this.person1.TaxResidence.length; i++) {
          if (this.person1.TaxResidence[i].isSuiwuNO === true) {
            if (this.person1.TaxResidence[i].suibie !== 'qita') {
              noTaxIdentificationNumber = this.person1.TaxResidence[i].suibie;
            } else {
              noTaxIdentificationNumber = this.person1.TaxResidence[i].suiwuQita;
            }
          } else {
            noTaxIdentificationNumber = null;
          }
          accountPerTaxesList.push({
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            noTaxIdentificationNumber,
            taxResidenceCountry: this.person1.TaxResidence[i].taxState,
            taxResidenceIdCardNumber: this.person1.TaxResidence[i].IDNumber
          });
        }
      } else {
        const index = this.newValue.Personaltaxresidence.length;
        for (let i = 0; i < this.person1.TaxResidence.length; i++) {
          if (this.person1.TaxResidence.length < index) {
            this.newValue.Personaltaxresidence.splice(this.person1.TaxResidence.length); // 返回最后一个被删除的
          } else {
            if (this.person1.TaxResidence[i].isSuiwuNO === true) {
              if (this.person1.TaxResidence[i].suibie !== 'qita') {
                noTaxIdentificationNumber = this.person1.TaxResidence[i].suibie;
              } else {
                noTaxIdentificationNumber = this.person1.TaxResidence[i].suiwuQita;
              }
            } else {
              noTaxIdentificationNumber = null;
            }
            if (i < index) {
              this.newValue.Personaltaxresidence[
                i
              ].taxResidenceCountry = this.person1.TaxResidence[i].taxState;
              this.newValue.Personaltaxresidence[
                i
              ].taxResidenceIdCardNumber = this.person1.TaxResidence[
                i
              ].IDNumber;

              this.newValue.Personaltaxresidence[
                i
              ].noTaxIdentificationNumber = noTaxIdentificationNumber;
            } else {
              this.newValue.Personaltaxresidence.push({
                accountId,
                createdBy,
                createdDate,
                taxResidenceCountry: this.person1.TaxResidence[i].taxState,
                taxResidenceIdCardNumber: this.person1.TaxResidence[i]
                  .IDNumber,
                noTaxIdentificationNumber
              });
            }
          }
        }
      }

      // 个人财富来源
      if (this.newValue.Personalatreasuredsources == null) { // caifu wei geng xin
        for (let i = 0; i < this.person1.treasure.length; i++) {
          accountPerWealthSourceList.push({
            accountId,
            createdBy,
            createdDate,
            updatedBy,
            incomeType: this.person1.treasure[i].leixing,
            isCapitalInjection: this.person1.treasure[i].isCFzhuzi,
            percentageOfFunds: this.person1.treasure[i].isCFzhuzi ? this.person1.treasure[i].baifenbi : null,
            sourceDesc: this.person1.treasure[i].isCFzhuzi ? this.person1.treasure[i].zhushi : null
          });
        }
      } else {
        const index = this.newValue.Personalatreasuredsources.length;
        for (let i = 0; i < this.person1.treasure.length; i++) {
          if (this.person1.treasure.length < index) {
            this.newValue.Personalatreasuredsources.splice(this.person1.treasure.length);
            if (i < index) {
              this.newValue.Personalatreasuredsources[i].incomeType = this.person1.treasure[i].leixing;
              this.newValue.Personalatreasuredsources[i].isCapitalInjection = this.person1.treasure[i].isCFzhuzi ? 1 : 0;
              this.newValue.Personalatreasuredsources[i].percentageOfFunds = this.person1.treasure[i].isCFzhuzi ? this.person1.treasure[i].baifenbi : null;
              this.newValue.Personalatreasuredsources[i].sourceDesc = this.person1.treasure[i].isCFzhuzi ? this.person1.treasure[i].zhushi : null;
            }
          } else {
            if (i < index) {
              this.newValue.Personalatreasuredsources[i].incomeType = this.person1.treasure[i].leixing;
              this.newValue.Personalatreasuredsources[i].isCapitalInjection = this.person1.treasure[i].isCFzhuzi ? 1 : 0;
              this.newValue.Personalatreasuredsources[i].percentageOfFunds = this.person1.treasure[i].isCFzhuzi ? this.person1.treasure[i].baifenbi : null;
              this.newValue.Personalatreasuredsources[i].sourceDesc = this.person1.treasure[i].isCFzhuzi ? this.person1.treasure[i].zhushi : null;
            } else {
              this.newValue.Personalatreasuredsources.push({
                accountId,
                createdBy,
                createdDate,
                updatedBy,
                incomeType: this.person1.treasure[i].leixing,
                isCapitalInjection: this.person1.treasure[i].isCFzhuzi ? 1 : 0,
                percentageOfFunds: this.person1.treasure[i].isCFzhuzi
                  ? this.person1.treasure[i].baifenbi
                  : null,
                sourceDesc: this.person1.treasure[i].isCFzhuzi
                  ? this.person1.treasure[i].zhushi
                  : null
              });
            }
          }
        }
      }

      // 个人信息
      if (this.newValue.Personalperinfo == null) {
        accountPerInfo = {
          accountId,
          createdBy,
          createdDate,
          updatedBy,
          appellation: this.person1.call,
          birthday: this.$verify.NowDate2(this.person1.birth), // 生日
          firstName: this.person1.name,
          gender: this.person1.sex,
          lastName: this.person1.surname,
          maritalStatus: this.person1.marriage,
          numberOfFamilyMembers: this.person1.member,
          otherCountriesOfBirth: otherBirthCountry, // 其他出生国家
          otherNationality: otherCountry, // 其他国籍
          personalType: this.person1.IndividualType
        };
      } else {
        for (let i = 0; i < this.newValue.Personalperinfo.length; i++) {
          this.newValue.Personalperinfo[0].appellation = this.person1.call;
          this.newValue.Personalperinfo[0].lastName = this.person1.surname;
          this.newValue.Personalperinfo[0].firstName = this.person1.name;
          this.newValue.Personalperinfo[0].birthday = this.$verify.NowDate2(this.person1.birth);
          this.newValue.Personalperinfo[0].gender = this.person1.sex;
          this.newValue.Personalperinfo[0].maritalStatus = this.person1.marriage;
          this.newValue.Personalperinfo[0].numberOfFamilyMembers = this.person1.member;
          this.newValue.Personalperinfo[0].otherBirthCountry = otherBirthCountry;
          this.newValue.Personalperinfo[0].otherCountry = otherCountry;
        }
      }
      const data = {
        accountPerInfo: this.newValue.Personalperinfo !== null ? this.newValue.Personalperinfo[0] : accountPerInfo,
        accountPerAddressList: this.newValue.Personaladdress !== null ? this.newValue.Personaladdress : accountPerAddressList,
        accountPerContactList: this.newValue.Personalcontact !== null ? this.newValue.Personalcontact : accountPerContactList,
        accountPerIdentity: this.newValue.Personalidentifiable !== null ? this.newValue.Personalidentifiable[0] : accountPerIdentity,
        accountPerTaxesList: this.newValue.Personaltaxresidence !== null ? this.newValue.Personaltaxresidence : accountPerTaxesList,
        accountPerEmployment: this.newValue.Personalemployment !== null ? this.newValue.Personalemployment[0] : accountPerEmployment,
        accountPerPortfolio: this.newValue.Personalaccountportfolio !== null ? this.newValue.Personalaccountportfolio[0] : accountPerPortfolio,
        accountPerWealthSourceList: this.newValue.Personalatreasuredsources !== null ? this.newValue.Personalatreasuredsources : accountPerWealthSourceList,
        accountPerSafeQuestionList: this.newValue.Personalsafety !== null ? this.newValue.Personalsafety : accountPerSafeQuestionList
      };
      console.log(
        JSON.stringify(data)
      );
      // 数据请求
      this.$http({
        url: '/openAccount/personal/saveOne',
        method: 'put',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person2' });
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
    }
  }
};
</script>

<style scoped>
.radio{
  display: block;
}
.el-form-item__content {
  margin-left: 0px;
}
</style>
