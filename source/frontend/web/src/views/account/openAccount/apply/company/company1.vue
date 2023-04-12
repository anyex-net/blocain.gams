<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(1/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <div class="formContent">
            <el-form
              ref="company1"
              autocomplete="off"
              :model="company1"
              :rules="rules"
              label-width="0px"
              class="demo-ruleForm"
            >
              <!-- 机构信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.organization.title') }}</h3> <!-- $i18n - 机构信息 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent pleaseEnter">
                  <el-form-item prop="AgencyInformation.companyName">
                    <el-input v-model="company1.AgencyInformation.companyName" maxlength="80" :placeholder="$t('registerOpenAccount.openAccount.common.organization.name1')" /> <!-- $i18n - 有限公司名称 -->
                  </el-form-item>
                  <el-form-item prop="AgencyInformation.description">
                    <el-input
                      v-model="company1.AgencyInformation.description"
                      maxlength="500"
                      type="textarea"
                      :placeholder="$t('registerOpenAccount.openAccount.common.organization.business')"
                    /> <!-- $i18n - 业务描述 -->
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: company1.isshow.istradeName,'fade':true}"
                          @click="company1.isshow.istradeName=true"
                        ><div>{{ $t('registerOpenAccount.common.btn.yes') }}</div></li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !company1.isshow.istradeName,'fade':true}"
                          @click="company1.isshow.istradeName=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.organization.isShangyeName') }}</li> <!-- $i18n - 该机构是否有有限责任公司名称以外的商业名称？ -->
                    </ul>
                  </div>
                  <transition name="fade">
                    <el-form-item
                      v-show="company1.isshow.istradeName"
                      prop="AgencyInformation.TradeName"
                      class="fade"
                    >
                      <el-input v-model="company1.AgencyInformation.TradeName" maxlength="500" :placeholder="$t('registerOpenAccount.openAccount.common.organization.shangyeName')" /> <!-- $i18n - 商业名称 -->
                    </el-form-item>
                  </transition>
                  <transition name="fade">
                    <el-form-item prop="AgencyInformation.wangzhi" class="fade">
                      <el-input v-model="company1.AgencyInformation.wangzhi" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.organization.webSite')" /> <!-- $i18n - 网址 -->
                    </el-form-item>
                  </transition>
                  <el-form-item id="radioInline" prop="AgencyInformation.gaizuzhi.ZZkey" style="margin-top:16px;">
                    <el-radio-group v-model="company1.AgencyInformation.gaizuzhi.ZZkey">
                      <div
                        v-for="(item,index) in company1.AgencyInformation.gaizuzhi.ZZkeyList"
                        :key="index"
                      >
                        <el-radio
                          :label="item.dictValue"
                          @change="subOrganizations(index)"
                        >
                          {{ item.dictDest }}
                          <el-form-item
                            v-show="company1.AgencyInformation.gaizuzhi.ifZZvalue==2"
                            v-if="index==2"
                            prop="AgencyInformation.gaizuzhi.ZZvalue"
                            class="gaizuzhi"
                          >
                            <el-checkbox-group v-model="company1.AgencyInformation.gaizuzhi.ZZvalue">
                              <el-checkbox
                                v-for="(item1,index1) in company1.AgencyInformation.gaizuzhi.ZZvalueList"
                                :key="index1"
                                name="type"
                                :label="item1.dictValue"
                                style="display: block;"
                              >{{ item1.dictDest }}</el-checkbox>
                            </el-checkbox-group>
                          </el-form-item>
                          <el-form-item
                            v-show="company1.AgencyInformation.gaizuzhi.ifZZvalue==3"
                            v-if="index==3"
                            prop="AgencyInformation.gaizuzhi.ZZvalue1"
                            class="gaizuzhi"
                          >
                            <el-checkbox-group
                              v-model="company1.AgencyInformation.gaizuzhi.ZZvalue1"
                            >
                              <el-checkbox
                                v-for="(item1,index1) in company1.AgencyInformation.gaizuzhi.ZZvalue1List"
                                :key="index1"
                                name="type"
                                :label="item1.dictValue"
                                style="display: block;"
                              >{{ item1.dictDest }}</el-checkbox>
                            </el-checkbox-group>
                          </el-form-item>
                        </el-radio>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
              </div>
              <!-- 主要经营地点 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title1') }}</h3> <!-- $i18n - 主要经营地点 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="formHint">
                    <ul>
                      <li>{{ $t('registerOpenAccount.openAccount.common.addre.describe1') }}</li> <!-- $i18n - 请您提供您日常经营所在地的地址证明。 -->
                      <li>{{ $t('registerOpenAccount.openAccount.common.addre.describe2') }}</li> <!-- $i18n - 如您在申请中将您注册代理商的地址作为您的主要 ... -->
                      <li>{{ $t('registerOpenAccount.openAccount.common.addre.describe3') }}</li> <!-- $i18n - 任何提供报表或签名信来证明您的主要办公地址的 ... -->
                    </ul>
                    <div class="gantanhao">!</div>
                  </div>

                  <div v-for="(item,index) in company1.manageSite" :key="index">
                    <el-form-item :prop="'manageSite.'+index+'.state1'" :rules="rules.state1">
                      <el-select v-model="item.state1" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')" :disabled="true" filterable> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item :prop="'manageSite.'+index+'.dizhi'" :rules="rules.dizhi">
                      <el-input v-model="item.dizhi" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')" /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                    </el-form-item>
                    <el-form-item :prop="'manageSite.'+index+'.dizhi1'" :rules="rules.dizhi1">
                      <el-input v-model="item.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item :prop="'manageSite.'+index+'.chengshi'" :rules="rules.chengshi">
                      <el-input v-model="item.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item :prop="'manageSite.'+index+'.province'" :rules="rules.province">
                      <el-input v-model="item.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item :prop="'manageSite.'+index+'.postcode'" :rules="rules.postcode">
                      <el-input v-model="item.postcode" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                  </div>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: company1.isshow.isJYdidian ,fade:true}"
                          @click="company1.isshow.isJYdidian=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !company1.isshow.isJYdidian,fade:true }"
                          @click="company1.isshow.isJYdidian=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.isMailingAddress1') }}</li> <!-- $i18n - 该机构有其他邮寄地址吗？ -->
                    </ul>
                  </div>
                  <transition name="fade">
                    <div v-show="company1.isshow.isJYdidian" class="fade">
                      <el-form-item prop="mailingAddress.YJguojiaBT">
                        <el-select
                          v-model="company1.mailingAddress.YJguojiaBT"
                          :no-match-text="$t('account.manage.no_data')"
                          filterable
                          prop="identity"
                          :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"
                        > <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="mailingAddress.YJsiteBT">
                        <el-input
                          v-model="company1.mailingAddress.YJsiteBT"
                          maxlength="64"
                          :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                        /> <!-- $i18n - 地址（如果空间不足，请转下行） -->
                      </el-form-item>
                      <el-form-item prop="mailingAddress.YJsiteBT1">
                        <el-input v-model="company1.mailingAddress.YJsiteBT1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                      </el-form-item>
                      <el-form-item prop="mailingAddress.YJcityBT">
                        <el-input v-model="company1.mailingAddress.YJcityBT" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                      </el-form-item>
                      <el-form-item prop="mailingAddress.YJprovinceBT">
                        <el-input v-model="company1.mailingAddress.YJprovinceBT" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                      </el-form-item>
                      <el-form-item prop="mailingAddress.YJpostalcodBT">
                        <el-input v-model="company1.mailingAddress.YJpostalcodBT" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                      </el-form-item>
                    </div>
                  </transition>
                </div>
              </div>
              <!-- 联系信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.contact.title') }}</h3> <!-- $i18n - 联系信息 -->
                </div>

                <div
                  v-for="(item,index) in company1.contactMessage"
                  :key="index"
                  class="formNapeContent"
                >
                  <transition name="fade">
                    <el-form-item class="fade">
                      <el-col :span="6">
                        <el-form-item style="margin-bottom: 2px;" :prop="'contactMessage.'+index+'.time'" :rules="rules.time">
                          <el-select v-model="item.time" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.contact.type')"> <!-- $i18n - 手机类型 -->
                            <dictionary dict-code="phoneNumberType" />
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                      <el-col :span="6">
                        <el-form-item
                          style="margin-bottom: 2px;"
                          :prop="'contactMessage.'+index+'.state2'"
                          :rules="rules.state2"
                        >
                          <el-select v-model="item.state2" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.contact.state')"> <!-- $i18n - 国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                      <el-col :span="11">
                        <el-form-item
                          style="margin-bottom: 2px;"
                          :prop="'contactMessage.'+index+'.phoneNumber'"
                          :rules="rules.phoneNumber"
                        >
                          <el-input v-model="item.phoneNumber" maxlength="11" :placeholder="$t('registerOpenAccount.openAccount.common.contact.contactNumber')" /> <!-- $i18n - 电话号码 -->
                        </el-form-item>
                      </el-col>
                    </el-form-item>
                  </transition>
                </div>

                <div
                  class="addBtn"
                  style="    width: 960px;
                               height: 41px;"
                >
                  <div v-show="company1.contactMessage.length !== 5" class="add" @click="addelement2()">{{ $t('registerOpenAccount.openAccount.common.contact.addOtherNumbers') }}</div> <!-- $i18n - 添加其他号码 -->
                  <div v-show="company1.contactMessage.length !== 1" class="cancel" @click="removeelement2()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 税务居住地 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div
                  v-for="(item,index) in company1.placeAbode"
                  :key="index"
                  class="formNapeContent"
                >
                  <el-form-item :prop="'placeAbode.'+index+'.state3'" :rules="rules.state3">
                    <el-select v-model="item.state3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item :prop="'placeAbode.'+index+'.IDNumber'" :rules="rules.IDNumber">
                    <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" />  <!-- $i18n - 证件号码 -->
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: !item.isSuiwuNO,fade:true}"
                          @click="item.isSuiwuNO=false"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: item.isSuiwuNO,fade:true}"
                          @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.IDNumber')"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                    </ul>
                  </div>
                  <div v-if="item.isSuiwuNO" class="textcontent1">
                    <span class="zhuce3ji" style="line-height: 24px;">
                      {{ $t('registerOpenAccount.openAccount.common.taxes.describe') }}
                    </span> <!-- $i18n - 美国国税局规则要求我们收集您所有纳税居住地 ... -->
                    <el-form-item
                      id="radioInline"
                      :prop="'placeAbode.'+index+'.suibie'"
                      :rules="rules.suibie"
                      style="margin-top:10px;"
                    >
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
                  <div v-show="item.isTianjia?true:false" class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: item.isZChaoma,fade:true}"
                          @click="item.isZChaoma=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !item.isZChaoma,fade:true}"
                          @click="item.isZChaoma=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.iszhuceNumber') }}</li> <!-- $i18n - 机构有不同的注册号码吗？ -->
                    </ul>
                  </div>
                  <div v-if="item.isZChaoma">
                    <el-form-item
                      :prop="'placeAbode.'+index+'.zhucehaoma'"
                      :rules="rules.zhucehaoma"
                    >
                      <el-input v-model="item.zhucehaoma" maxlength="32" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.zhuceNumber')" /> <!-- $i18n - 注册号码 -->
                    </el-form-item>
                  </div>
                </div>
                <div
                  class="addBtn"
                  style="    width: 960px;
                               height: 41px;"
                >
                  <div v-show="company1.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加其他税务居住地 -->
                  <div v-show="company1.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 账户信息  -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.portfolio.title') }}</h3> <!-- $i18n - 账户信息 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="accountMessage.accountType">
                    <el-select v-model="company1.accountMessage.accountType" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.portfolio.accountPortfolioType')"> <!-- $i18n - 账户类型 -->
                      <dictionary dict-code="accountPortfolioType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="accountMessage.monetaryBase">
                    <el-select v-model="company1.accountMessage.monetaryBase" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.portfolio.baseCurrency')"> <!-- $i18n - 基础货币 -->
                      <dictionary dict-code="baseCurrency" />
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 财富来源  -->
              <div class="formNape elerror">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.wealthSource.titel') }}</h3>  <!-- $i18n - 财富来源 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.wealthSource.titelDescribe') }}</p>  <!-- $i18n - 请选择所有财富来源并声明将使 ... -->
                </div>
                <div
                  v-for="(item,index) in company1.treasure"
                  v-show="company1.isshow.isCFtiantu"
                  :key="index"
                  style="margin-top: 20px;"
                >
                  <div v-show="company1.isshow.isCFleixing">
                    <el-form-item :prop="'treasure.'+index+'.leixing'" :rules="rules.leixing">
                      <el-select
                        v-model="item.leixing"
                        :no-match-text="$t('account.manage.no_data')"
                        :placeholder="$t('registerOpenAccount.openAccount.common.wealthSource.sourceIncome')"
                        @change="caifuTitel(index)"
                      >  <!-- $i18n - 收入来源 -->
                        <el-option
                          v-for="(item1,index1) in company1.treasureList"
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
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.wealthSource.capitalSource') }}</li>  <!-- $i18n - 该来源资金是否被用于注资您的账户？ -->
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
                        >{{ $t('registerOpenAccount.openAccount.common.wealthSource.rootIn') }} {{ item.leixing1 }}</div>  <!-- $i18n - 您的账户注资将会有多少比例来源于 -->
                      </div>
                      <div>
                        <el-form-item :prop="'treasure.'+index+'.zhushi'" :rules="rules.zhushi">
                          <el-input v-model="item.zhushi" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.wealthSource.annotation')" />  <!-- $i18n - 注释(在此提供更多详细信息) -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="addBtn">
                  <div v-show="company1.treasure.length !== 5" class="add" @click="addelement4()">{{ $t('registerOpenAccount.openAccount.common.wealthSource.addWealth') }}</div>  <!-- $i18n - 添加财富来源 -->
                  <div v-show="company1.treasure.length !== 1" class="cancel" @click="removeelement4()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button
                      class="right-btn BTbtn"
                      plain
                      @click="submitForm('company1')"
                    >{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <!-- <el-button class="left-btn BTbtn" plain @click="beforepage()" >{{ $t('registerOpenAccount.footer.btnNo') }}</el-button> -->
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
    // 非中文验证
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 地址验证-商业名称
    var validateCompanyName = (rule, value, callback) => {
      const mate = this.company1.isshow.istradeName;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 地址验证-该机构是...
    var validateJGZZvalue = (rule, value, callback) => {
      if (this.company1.AgencyInformation.gaizuzhi.ifZZvalue === 2) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - pleaseChoose
        } else {
          this.$verify.notChinese(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 地址验证-该机构是...
    var validateJGZZvalue1 = (rule, value, callback) => {
      if (this.company1.AgencyInformation.gaizuzhi.ifZZvalue === 3) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - pleaseChoose
        } else {
          this.$verify.notChinese(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 主要经营地点-其他邮寄...
    var validateYouJidizhi = (rule, value, callback) => {
      const mate = this.company1.isshow.isJYdidian;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 主要经营地点-其他邮寄...接上一行地址验证
    var validateYouJidizhi1 = (rule, value, callback) => {
      const mate = this.company1.isshow.isJYdidian;
      this.$verify.stairIfone(rule, value, mate, callback);
    };
    // 税务国家功能渲染
    var validateZJhaoma = (rule, value, callback) => {
      const _this = this;
      if (value === '') {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      } else {
        for (let i = 0; i < this.company1.placeAbode.length; i++) {
          this.$verify.State(this.company1.placeAbode[i].state3, this.company1.placeAbode[i].taxNumList, this.company1.taxNumList1, _this, this.lang);
        }
        callback();
      }
    };
    // 税务居住地验证 税务号...
    var validateSuiWusuibie1 = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    // 税务居住地验证-纳税识别号...
    var validateSuiWusuibie = (rule, value, callback) => {
      const array1 = this.company1.placeAbode;
      this.$verify.SWnasuihao(rule, value, array1, callback);
    };
    // 税务居住地验证-注册号码...
    var validateSuiWuzhucehaom = (rule, value, callback) => {
      if (this.company1.placeAbode[0].isZChaoma) {
        this.$verify.notChinese(rule, value, callback);
      } else {
        callback();
      }
    };
    // 财富来源类型验证
    var wealthType = (rule, value, callback) => {
      if (value !== '') {
        let dictDest = null;
        for (let i = 0; i < this.company1.treasureList.length; i++) {
          if (value === this.company1.treasureList[i].dictValue) {
            dictDest = this.company1.treasureList[i].dictDest;
          }
        }
        for (let i = 0; i < this.company1.treasure.length; i++) {
          if (value === this.company1.treasure[i].leixing) {
            this.company1.treasure[i].leixing1 = dictDest;
          }
        }
        callback();
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };
    // 财富来源-百分比...
    var validateCFbaifenbi = (rule, value, callback) => {
      const guize = /^[0-9]*$/;
      for (let i = 0; i < this.company1.treasure.length; i++) {
        if (this.company1.treasure[i].baifenbi === value) {
          if (this.company1.treasure[i].isCFzhuzi === true) {
            if (value !== '') {
              if (guize.test(value) === true) {
                if (value <= 100) {
                  callback();
                  // if (this.company1.zongBaifenbi === 100) {
                  //   callback();
                  // } else {
                  //   callback(new Error('资金来源合计必须为百分比'));
                  // }
                } else {
                  callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.notAllOwpercent'))); // $i18n - 不允许超过百分百
                }
              } else {
                callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.illegality'))); // $i18n - 请不要输入非法字符
              }
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
            }
          } else {
            callback();
          }
        }
      }
    };
    // 财富来源-注释...
    var validateCFzhushi = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };

    // 正则验证
    // --网址验证
    var validateWangzhi = (rule, value, callback) => {
      this.$verify.Wangzhi(rule, value, callback);
    };
    // 邮政正则验证-地址
    var validateYouzheng1 = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    // 邮政正则验证-邮寄地址
    var validateYouzheng2 = (rule, value, callback) => {
      if (this.company1.isshow.isJYdidian) {
        this.$verify.notChinese(rule, value, callback);
      } else {
        callback();
      }
    };
    // 电话号码正则验证-联系方式
    var validateYouzheng3 = (rule, value, callback) => {
      const guize = /^(\+?0?86\-?)?1[345789]\d{9}$/;
      const guize1 = /^[0-9]*$/;
      if (value !== '') {
        if (value.length >= 6) {
          for (let i = 0; i < this.company1.contactMessage.length; i++) {
            if (this.company1.contactMessage[i].phoneNumber === value) {
              if (this.company1.contactMessage[i].state2 === 'CN') {
                if (guize.test(value)) {
                  callback();
                } else {
                  callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.mainlandNumber'))); // $i18n - 这不是一个中国大陆号码
                }
              } else {
                if (guize1.test(value)) {
                  callback();
                } else {
                  callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enterNumber'))); // $i18n - 请输入正确号码
                }
              }
            }
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.register.signIn.PhoneNumber'))); // $i18n - 手机号个数不能小于6位
        }
      } else {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      }
    };

    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      TokenmodalBox: false,
      company1: {
        // 机构信息
        AgencyInformation: {
          companyName: '',
          description: '',
          isTradeName: '',
          TradeName: '',
          wangzhi: '',
          gaizuzhi: {
            ZZkey: '',
            ZZkeyList: [],
            ifZZvalue: null,
            ZZvalue: [],
            ZZvalueList: null,
            ZZvalue1: [],
            ZZvalue1List: null
          }
        },
        // 主要经营地点
        manageSite: [
          {
            state1: '',
            dizhi: '',
            dizhi1: '',
            chengshi: '',
            province: '',
            postcode: '',
            ispostcode: '',
            addressTypeName: '',
            dictionaryId: '',
            addressType: ''
          }
        ],
        //  -- 邮寄地址
        mailingAddress: {
          YJguojiaBT: '',
          YJsiteBT: '',
          YJsiteBT1: '',
          YJcityBT: '',
          YJprovinceBT: '',
          YJpostalcodBT: '',
          addressTypeName: '',
          dictionaryId: '',
          addressType: '',
          id: ''
        },

        // 联系信息
        contactMessage: [
          {
            time: '',
            state2: '',
            phoneNumber: ''
          }
        ],
        // 税务居住地
        placeAbode: [
          {
            state3: '',
            IDNumber: '',
            IDNumber1: '',
            suibie: '',
            isSuiwuNO: false,
            suiwuQita: '',
            isZChaoma: false,
            zhucehaoma: '',
            isTianjia: true,
            taxNumList: null // 页面上使用
          }
        ],
        taxNumList1: null, // 页面上使用-暂存
        // 账户信息
        accountMessage: {
          accountType: '',
          monetaryBase: ''
        },
        // 财富来源
        treasure: [],
        leixingNumber: null,
        treasureList: [],
        zongBaifenbi: 0, // 计算出来的总百分比
        // 显示隐藏
        isshow: {
          istradeName: false,
          isorganization: 0,
          isJYdidian: false,
          isCFtiantu: false,
          isCFleixing: false
          // iscfzhushi:0,
        }
      },
      formdata: {},
      newValue: {
        newAccountCoAccountPortfolio: null,
        newAccountCoAddressList: null,
        newAccountCoContactList: null,
        newAccountCoInstitutional: null,
        newAccountCoTaxesList: null,
        newAccountCoWealthSourceList: null
      },
      rules: {
        // { required: true, message: '请选择', trigger: 'change' }
        // 机构信息
        'AgencyInformation.companyName': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'AgencyInformation.description': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'AgencyInformation.TradeName': [
          { validator: validateCompanyName, trigger: 'blur' }
        ],
        'AgencyInformation.wangzhi': [
          { validator: validateWangzhi, trigger: 'blur' }
        ],
        'AgencyInformation.gaizuzhi.ZZkey': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n 请选择
        ],
        'AgencyInformation.gaizuzhi.ZZvalue': [
          { validator: validateJGZZvalue, trigger: 'change' }
        ],
        'AgencyInformation.gaizuzhi.ZZvalue1': [
          { validator: validateJGZZvalue1, trigger: 'change' }
        ],
        // 主要经营地点
        state1: [{ validator: notChinese, trigger: 'change' }],
        dizhi: [{ validator: notChinese, trigger: 'blur' }],
        dizhi1: [{ validator: notChinese1, trigger: 'blur' }],
        chengshi: [{ validator: notChinese, trigger: 'blur' }],
        province: [{ validator: notChinese, trigger: 'change' }],
        postcode: [{ validator: validateYouzheng1, trigger: 'blur' }],
        // -- 邮寄地址
        'mailingAddress.YJguojiaBT': [
          { validator: validateYouJidizhi, trigger: 'change' }
        ],
        'mailingAddress.YJsiteBT': [
          { validator: validateYouJidizhi, trigger: 'blur' }
        ],
        'mailingAddress.YJsiteBT1': [
          { validator: validateYouJidizhi1, trigger: 'blur' }
        ],
        'mailingAddress.YJcityBT': [
          { validator: validateYouJidizhi, trigger: 'blur' }
        ],
        'mailingAddress.YJprovinceBT': [
          { validator: validateYouJidizhi, trigger: 'blur' }
        ],
        'mailingAddress.YJpostalcodBT': [
          { validator: validateYouzheng2, trigger: 'blur' }
        ],

        //  联系信息
        time: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' }], // $i18n 请选择
        state2: [{ required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' }], // $i18n 请选择
        phoneNumber: [{ validator: validateYouzheng3, trigger: 'blur' }],
        // 税务居住地
        state3: [{ validator: validateZJhaoma, trigger: 'change' }],
        IDNumber: [{ validator: validateSuiWusuibie1, trigger: 'blur' }],
        suibie: [{ validator: validateSuiWusuibie, trigger: 'change' }],
        zhucehaoma: [{ validator: validateSuiWuzhucehaom, trigger: 'blur' }],
        // 账户信息
        'accountMessage.accountType': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n 请选择
        ],
        'accountMessage.monetaryBase': [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n 请选择
        ],
        // 财富来源
        leixing: [{ validator: wealthType, trigger: 'change' }],
        baifenbi: [{ validator: validateCFbaifenbi, trigger: 'blur' }],
        zhushi: [{ validator: validateCFzhushi, trigger: 'blur' }]
      }
    };
  },
  watch: {
    company1: {
      // 深度监听，可监听到对象、数组的变化
      handler(val, oldVal) {
        let zongBaifenbi = 0;
        for (let i = 0; i < this.company1.treasure.length; i++) {
          if (this.company1.treasure[i].isCFzhuzi) {
            zongBaifenbi += parseInt(this.company1.treasure[i].baifenbi);
          }
        }
        this.company1.zongBaifenbi = zongBaifenbi;
      },
      deep: true
    }
  },
  created() {
    this.institutionalNature();
    this.addressType();
    this.sourceRevenue();
    this.taxIdentificationNumber();

    this.accountCoAddress();
    this.accountCoContact();
    this.accountCoAccountPortfolio();

    this.company1.manageSite[0].state1 = this.$store.state.user.applyData.xinxi.country;
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountCoInstitutional() {
      // 机构信息
      this.$http({
        url: '/account/accountCoInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoInstitutional = res.object;
              this.company1.AgencyInformation.companyName =
                res.object.institutionalName;
              this.company1.AgencyInformation.description =
                res.object.bsinessDescription;
              this.company1.isshow.istradeName = !!res.object.businessName;
              this.company1.AgencyInformation.TradeName = res.object.businessName;
              this.company1.AgencyInformation.wangzhi = res.object.websiteUrl;
              if (res.object.istitutionalNature.indexOf(';') !== -1) {
                const istitutionalNature = res.object.istitutionalNature.split(';');
                this.company1.AgencyInformation.gaizuzhi.ZZkey = istitutionalNature[0];
                if (istitutionalNature[0] === this.company1.AgencyInformation.gaizuzhi.ZZkeyList[2].dictValue) {
                  this.company1.AgencyInformation.gaizuzhi.ifZZvalue = 2;
                  if (istitutionalNature[1].indexOf(',') !== -1) {
                    const istitutionalNature1 = istitutionalNature[1].split(',');
                    for (let i = 0; i < istitutionalNature1.length; i++) {
                      this.company1.AgencyInformation.gaizuzhi.ZZvalue.push(
                        istitutionalNature1[i]
                      );
                    }
                  } else {
                    this.company1.AgencyInformation.gaizuzhi.ZZvalue.push(
                      istitutionalNature[1]
                    );
                  }
                } else if (istitutionalNature[0] === this.company1.AgencyInformation.gaizuzhi.ZZkeyList[3].dictValue) {
                  for (let i = 0; i < istitutionalNature.length; i++) {
                    if (i !== 0) {
                      this.company1.AgencyInformation.gaizuzhi.ZZvalue1.push(
                        istitutionalNature[i]
                      );
                    }
                  }
                  this.company1.AgencyInformation.gaizuzhi.ifZZvalue = 3;
                }
              } else {
                this.company1.AgencyInformation.gaizuzhi.ZZkey = res.object.istitutionalNature;
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
    accountCoAddress() {
      // 地址信息
      this.$http({
        url: '/account/accountCoAddress/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoAddressList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  this.company1.manageSite[0].state1 = res.object[i].country;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.company1.manageSite[0].dizhi = dizhi[0];
                    this.company1.manageSite[0].dizhi1 = dizhi[1];
                  } else {
                    this.company1.manageSite[0].dizhi = res.object[i].address;
                  }
                  this.company1.manageSite[0].addressType =
                    res.object[i].addressType;
                  this.company1.manageSite[0].chengshi = res.object[i].city;
                  this.company1.manageSite[0].province = res.object[i].rovince;
                  this.company1.manageSite[0].postcode = res.object[i].zipCode;
                } else if (res.object[i].addressType === 'mail') {
                  this.company1.isshow.isJYdidian = true;
                  this.company1.mailingAddress.YJguojiaBT = res.object[i].country;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.company1.mailingAddress.YJsiteBT = dizhi[0];
                    this.company1.mailingAddress.YJsiteBT1 = dizhi[1];
                  } else {
                    this.company1.mailingAddress.YJsiteBT = res.object[i].address;
                  }
                  this.company1.mailingAddress.addressType =
                    res.object[i].addressType;
                  this.company1.mailingAddress.YJcityBT = res.object[i].city;
                  this.company1.mailingAddress.YJprovinceBT =
                    res.object[i].rovince;
                  this.company1.mailingAddress.YJpostalcodBT =
                    res.object[i].zipCode;
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
    accountCoContact() {
      // 联系信息
      this.$http({
        url: '/account/accountCoContact/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoContactList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  this.company1.contactMessage[i].phoneNumber =
                    res.object[i].contactNumber;
                  this.company1.contactMessage[i].state2 = res.object[i].country;
                  this.company1.contactMessage[i].time =
                    res.object[i].contactNumberType;
                } else {
                  this.company1.contactMessage.push({
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
    accountCoTaxes() {
      // 税务居住地
      this.$http({
        url: '/account/accountCoTaxes/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoTaxesList = res.object;
              const placeAbodeTS = {
                state3: '',
                IDNumber: '',
                IDNumber1: '',
                suibie: '',
                isSuiwuNO: false,
                suiwuQita: '',
                isZChaoma: false,
                zhucehaoma: '',
                isTianjia: true,
                taxNumList: JSON.parse(JSON.stringify(this.company1.taxNumList1)) // 页面上使用
              };
              this.company1.placeAbode = [];

              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  placeAbodeTS.state3 = res.object[i].taxResidenceCountry;
                  placeAbodeTS.IDNumber1 = res.object[i].taxResidenceIdCardNumber;
                  if (res.object[0].noTaxIdentificationNumber) {
                    placeAbodeTS.IDNumber1 = '';
                    placeAbodeTS.isSuiwuNO = true;
                    let index = 0;
                    for (let j = 0; j < placeAbodeTS.taxNumList.length; j++) {
                      if (res.object[0].noTaxIdentificationNumber === placeAbodeTS.taxNumList[j].dictValue) {
                        index++;
                        placeAbodeTS.suibie = res.object[0].noTaxIdentificationNumber;
                      }
                    }
                    if (index === 0) {
                      placeAbodeTS.suibie = 'qita';
                      placeAbodeTS.suiwuQita = res.object[i].noTaxIdentificationNumber;
                    } else {
                      placeAbodeTS.suiwuQita = '';
                    }
                  }
                  placeAbodeTS.zhucehaoma = res.object[i].differentRegistrationNumber;

                  placeAbodeTS.isZChaoma = res.object[i].differentRegistrationNumber;
                  // placeAbodeTS.isTianjia = res.object[i].differentRegistrationNumber;
                } else {
                  placeAbodeTS.state3 = res.object[i].taxResidenceCountry;
                  // placeAbodeTS.IDNumber = res.object[i].taxResidenceIdCardNumber;
                  placeAbodeTS.IDNumber1 = res.object[i].taxResidenceIdCardNumber;
                  if (res.object[i].noTaxIdentificationNumber) {
                    placeAbodeTS.IDNumber1 = '';
                    placeAbodeTS.isSuiwuNO = true;
                    let index = 0;
                    for (let j = 0; j < placeAbodeTS.taxNumList.length; j++) {
                      if (res.object[i].noTaxIdentificationNumber === placeAbodeTS.taxNumList[j].dictValue) {
                        index++;
                        placeAbodeTS.suibie = res.object[i].noTaxIdentificationNumber;
                      }
                    }
                    if (index === 0) {
                      placeAbodeTS.suibie = 'qita';
                      placeAbodeTS.suiwuQita = res.object[i].noTaxIdentificationNumber;
                    } else {
                      placeAbodeTS.suiwuQita = '';
                    }
                  }
                  placeAbodeTS.zhucehaoma = res.object[i].differentRegistrationNumber;
                  placeAbodeTS.isZChaoma = res.object[i].differentRegistrationNumber;
                  placeAbodeTS.isTianjia = false;
                  // this.company1.placeAbode.push(JSON.parse(JSON.stringify(placeAbodeTS)));
                }
                this.company1.placeAbode.push(JSON.parse(JSON.stringify(placeAbodeTS)));
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
    accountCoAccountPortfolio() {
      // 账户信息
      this.$http({
        url: '/account/accountCoAccountPortfolio/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoAccountPortfolio = res.object;
              this.company1.accountMessage.accountType =
                res.object.accountPortfolioType;
              this.company1.accountMessage.monetaryBase = res.object.baseCurrency;
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
    accountCoWealthSource() {
      // 财富来源
      this.$http({
        url: '/account/accountCoWealthSource/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoWealthSourceList = res.object;
              for (let i = 0; i < res.object.length; i++) {
                let leixing = '';
                let leixing1 = '';
                let isZhuce = '';
                let baifenbi = '';
                let zhushi = '';
                let isCFzhuzi = false;
                let isCFcontent = false;
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
                isZhuce = !!res.object[i].isCapitalInjection;
                baifenbi = res.object[i].percentageOfFunds;
                zhushi = res.object[i].sourceDesc ? res.object[i].sourceDesc : '';
                if (leixing) {
                  for (let j = 0; j < this.company1.treasureList.length; j++) {
                    if (leixing === this.company1.treasureList[j].dictValue) {
                      leixing1 = this.company1.treasureList[j].dictDest;
                    }
                  }
                }

                this.company1.treasure.push({
                  leixing,
                  leixing1,
                  isZhuce,
                  baifenbi,
                  zhushi,
                  isCFzhuzi,
                  isCFcontent
                });
                this.company1.isshow.isCFleixing = true;
                this.company1.isshow.isCFtiantu = true;
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
    // -- 机构性质
    institutionalNature() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'institutionalNature', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.company1.AgencyInformation.gaizuzhi.ZZkeyList = res.object;
            this.institutionalNature_1();
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
    // -- 机构性质-子节点
    institutionalNature_1() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'institutionalNature_1', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.company1.AgencyInformation.gaizuzhi.ZZvalueList = res.object;
            this.institutionalNature_2();
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
    // -- 机构性质-子节点
    institutionalNature_2() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'institutionalNature_2', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.company1.AgencyInformation.gaizuzhi.ZZvalue1List = res.object;
            this.accountCoInstitutional();
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
            this.company1.placeAbode[0].taxNumList = JSON.parse(JSON.stringify(res.object));
            this.company1.taxNumList1 = JSON.parse(JSON.stringify(res.object));
            this.accountCoTaxes();
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
            for (let i = 0; i < res.object.length; i++) {
              if (i === 0) {
                this.company1.manageSite[0].addressType = res.object[i].dictValue;
                // personalAddress
              } else if (i === 1) {
                this.company1.mailingAddress.addressType = res.object[i].dictValue;
                this.company1.mailingAddress.id = res.object[i].id;
                // mail
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
    // -- 财富来源
    sourceRevenue() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'wealthSource', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.company1.treasureList = res.object;
            this.accountCoWealthSource();
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
    // 该机构-子机构复选-显示隐藏
    subOrganizations(index) {
      this.company1.AgencyInformation.gaizuzhi.ifZZvalue = index;
    },
    // 财富来源下拉框去重
    dropDownBoxDeWeigth(dictValue, wealth) { // dictValue下拉框的每一个项 wealth当前选中的下拉框选中项
      if (dictValue === wealth) { // 完成 1,当前选中的显示
        return true;
      }
      for (const i in this.company1.treasure) { // 2,其他下拉框选中的隐藏
        if (dictValue === this.company1.treasure[i].leixing) {
          return false;
        }
      }
      return true;
    },
    // is纳税识别号-验证证件号码
    taxIsBtn(VerifyPath) {
      let num = 1;
      const timerId = setInterval(() => {
        num--;
        this.$refs.company1.validateField(VerifyPath);
        if (num === 0) {
          clearInterval(timerId);
        }
      }, 100);
      return true;
    },
    // 联系添加
    addelement2() {
      this.company1.contactMessage.push({
        time: '',
        state2: '',
        phoneNumber: ''
      });
    },
    removeelement2() {
      this.company1.contactMessage.pop(this.company1.contactMessage);
    },
    // 税务添加
    addelement3() {
      this.company1.placeAbode.push({
        state3: '',
        IDNumber: '',
        IDNumber1: '',
        suibie: '',
        isSuiwuNO: false,
        suiwuQita: '',
        isZChaoma: '',
        zhucehaoma: '',
        taxNumList: JSON.parse(JSON.stringify(this.company1.placeAbode[0].taxNumList)) // 页面上使用
      });
    },
    removeelement3() {
      this.company1.placeAbode.pop(this.company1.placeAbode);
    },
    // 财富来源添加
    caifuTitel(index) {
      this.company1.treasure[index].isCFcontent = true;
      this.company1.leixingNumber = index;
    },
    addelement4() {
      if (this.company1.treasure.length < this.company1.treasureList.length) {
        this.company1.treasure.push({
          leixing: '',
          leixing1: '',
          isZhuce: '',
          baifenbi: '',
          zhushi: '',
          isCFzhuzi: false,
          isCFcontent: false
        });
        this.company1.isshow.isCFleixing = true;
        this.company1.isshow.isCFtiantu = true;
      } else {
        return false;
      }
    },
    removeelement4() {
      if (this.company1.treasure.length === 1) {
        return false;
      } else {
        this.company1.treasure.pop(this.company1.treasure.length - 1);
      }
    },
    isCFzhuziT(index) {
      this.company1.treasure[index].isCFzhuzi = true;
    },
    isCFzhuziF(index) {
      this.company1.treasure[index].isCFzhuzi = false;
    },

    // 验证表单 跳转页面
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.company1.treasure.length !== 0) {
            if (this.company1.zongBaifenbi === 100) {
              this.submitFormData();
            } else {
              this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.treasureTotal')); // $i18n - 财富来源比例合计必须为100%
            }
          } else {
            this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.addCaifu')); // $i18n - 最少添加一项财富来源
          }
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
      const updatedBy = this.$store.state.user.applyData.xinxi.id;
      const updatedDate = this.$verify.NowDate();
      let accountCoInstitutional = {}; // 1.机构信息
      let accountCoAddressList = []; // 2.主要经营地点
      const accountCoContactList = []; // 3.联系信息
      const accountCoTaxesList = []; // 4.税务居住地
      let accountCoAccountPortfolio = {}; // 5.账户信息
      const accountCoWealthSourceList = []; // 6.财富来源
      // 1.机构信息
      let istitutionalNature = null;

      if (this.company1.AgencyInformation.gaizuzhi.ifZZvalue === 2) {
        istitutionalNature =
          this.company1.AgencyInformation.gaizuzhi.ZZkey +
          ';' +
          this.company1.AgencyInformation.gaizuzhi.ZZvalue;
      } else if (this.company1.AgencyInformation.gaizuzhi.ifZZvalue === 3) {
        istitutionalNature =
          this.company1.AgencyInformation.gaizuzhi.ZZkey +
          ';' +
          this.company1.AgencyInformation.gaizuzhi.ZZvalue1;
      } else {
        istitutionalNature = this.company1.AgencyInformation.gaizuzhi.ZZkey;
      }
      if (this.newValue.newAccountCoInstitutional == null) {
        accountCoInstitutional = {
          accountId,
          createdBy,
          createdDate,
          institutionalName: this.company1.AgencyInformation.companyName,
          bsinessDescription: this.company1.AgencyInformation.description,
          businessName: this.company1.isshow.istradeName
            ? this.company1.AgencyInformation.TradeName
            : null,
          websiteUrl: this.company1.AgencyInformation.wangzhi,
          istitutionalNature
        };
      } else {
        this.newValue.newAccountCoInstitutional.institutionalName = this.company1.AgencyInformation.companyName;
        this.newValue.newAccountCoInstitutional.bsinessDescription = this.company1.AgencyInformation.description;
        this.newValue.newAccountCoInstitutional.businessName = this.company1.isshow.istradeName ? this.company1.AgencyInformation.TradeName : null;
        this.newValue.newAccountCoInstitutional.websiteUrl = this.company1.AgencyInformation.wangzhi;
        this.newValue.newAccountCoInstitutional.istitutionalNature = istitutionalNature;
      }

      // 2.主要经营地点
      if (this.newValue.newAccountCoAddressList === null) {
        accountCoAddressList = [
          {
            accountId,
            createdBy,
            createdDate,
            country: this.company1.manageSite[0].state1,
            address: this.company1.manageSite[0].dizhi1
              ? this.company1.manageSite[0].dizhi +
                '-#-' +
                this.company1.manageSite[0].dizhi1
              : this.company1.manageSite[0].dizhi,
            addressType: this.company1.manageSite[0].addressType,
            city: this.company1.manageSite[0].chengshi,
            rovince: this.company1.manageSite[0].province,
            zipCode: this.company1.manageSite[0].postcode
          }
        ];
        if (this.company1.isshow.isJYdidian === true) {
          accountCoAddressList.push({
            accountId,
            createdBy,
            createdDate,
            country: this.company1.mailingAddress.YJguojiaBT,
            address: this.company1.mailingAddress.YJsiteBT1
              ? this.company1.mailingAddress.YJsiteBT +
                '-#-' +
                this.company1.mailingAddress.YJsiteBT1
              : this.company1.mailingAddress.YJsiteBT,
            addressType: this.company1.mailingAddress.addressType,
            city: this.company1.mailingAddress.YJcityBT,
            rovince: this.company1.mailingAddress.YJprovinceBT,
            zipCode: this.company1.mailingAddress.YJpostalcodBT
          });
        }
      } else {
        let dataTSpersonalAddress = null;
        let dataTSmail = null;
        for (let i = 0; i < this.newValue.newAccountCoAddressList.length; i++) {
          if (this.newValue.newAccountCoAddressList[i].addressType === 'personalAddress') {
            dataTSpersonalAddress = JSON.parse(JSON.stringify(this.newValue.newAccountCoAddressList[i]));
            dataTSpersonalAddress.country = this.company1.manageSite[0].state1;
            dataTSpersonalAddress.address = this.company1.manageSite[0].dizhi1 ? this.company1.manageSite[0].dizhi + '-#-' + this.company1.manageSite[0].dizhi1 : this.company1.manageSite[0].dizhi;
            dataTSpersonalAddress.addressType = this.company1.manageSite[0].addressType;
            dataTSpersonalAddress.city = this.company1.manageSite[0].chengshi;
            dataTSpersonalAddress.rovince = this.company1.manageSite[0].province;
            dataTSpersonalAddress.zipCode = this.company1.manageSite[0].postcode;
          } else if (this.newValue.newAccountCoAddressList[i].addressType === 'mail') {
            if (this.company1.isshow.isJYdidian) {
              dataTSmail = JSON.parse(JSON.stringify(this.newValue.newAccountCoAddressList[i]));
              dataTSmail.country = this.company1.mailingAddress.YJguojiaBT;
              dataTSmail.address = this.company1.mailingAddress.YJsiteBT1 ? this.company1.mailingAddress.YJsiteBT + '-#-' + this.company1.mailingAddress.YJsiteBT1 : this.company1.mailingAddress.YJsiteBT;
              dataTSmail.addressType = this.company1.mailingAddress.addressType;
              dataTSmail.city = this.company1.mailingAddress.YJcityBT;
              dataTSmail.rovince = this.company1.mailingAddress.YJprovinceBT;
              dataTSmail.zipCode = this.company1.mailingAddress.YJpostalcodBT;
            }
          }
        }
        if (dataTSmail === null) {
          if (this.company1.isshow.isJYdidian === true) {
            dataTSmail = {
              accountId,
              createdBy,
              createdDate,
              updatedBy,
              updatedDate,
              country: this.company1.mailingAddress.YJguojiaBT,
              address: this.company1.mailingAddress.YJsiteBT1
                ? this.company1.mailingAddress.YJsiteBT +
                     '-#-' +
                     this.company1.mailingAddress.YJsiteBT1
                : this.company1.mailingAddress.YJsiteBT,
              addressType: this.company1.mailingAddress.addressType,
              city: this.company1.mailingAddress.YJcityBT,
              rovince: this.company1.mailingAddress.YJprovinceBT,
              zipCode: this.company1.mailingAddress.YJpostalcodBT
            };
          }
        }
        this.newValue.newAccountCoAddressList = [];
        if (dataTSpersonalAddress !== null) {
          this.newValue.newAccountCoAddressList.push(JSON.parse(JSON.stringify(dataTSpersonalAddress)));
        }
        if (dataTSmail !== null) {
          this.newValue.newAccountCoAddressList.push(JSON.parse(JSON.stringify(dataTSmail)));
        }
      }
      // 3.联系信息
      if (this.newValue.newAccountCoContactList === null) {
        for (let i = 0; i < this.company1.contactMessage.length; i++) {
          accountCoContactList.push({
            accountId,
            createdBy,
            createdDate,
            contactNumber: this.company1.contactMessage[i].phoneNumber,
            country: this.company1.contactMessage[i].state2,
            contactNumberType: this.company1.contactMessage[i].time
          });
        }
      } else {
        const index = this.newValue.newAccountCoContactList.length;
        for (let i = 0; i < this.company1.contactMessage.length; i++) {
          if (this.company1.contactMessage.length < index) {
            this.newValue.newAccountCoContactList.splice(this.company1.contactMessage.length);
            if (i < index) {
              this.newValue.newAccountCoContactList[i].contactNumber = this.company1.contactMessage[i].phoneNumber;
              this.newValue.newAccountCoContactList[i].country = this.company1.contactMessage[i].state2;
              this.newValue.newAccountCoContactList[i].contactNumberType = this.company1.contactMessage[i].time;
            }
          } else {
            if (i < index) {
              this.newValue.newAccountCoContactList[i].contactNumber = this.company1.contactMessage[i].phoneNumber;
              this.newValue.newAccountCoContactList[i].country = this.company1.contactMessage[i].state2;
              this.newValue.newAccountCoContactList[i].contactNumberType = this.company1.contactMessage[i].time;
            } else {
              this.newValue.newAccountCoContactList.push(
                {
                  accountId,
                  createdBy,
                  createdDate,
                  updatedBy,
                  updatedDate,
                  contactNumber: this.company1.contactMessage[i].phoneNumber,
                  country: this.company1.contactMessage[i].state2,
                  contactNumberType: this.company1.contactMessage[i].time
                }
              );
            }
          }
        }
      }
      // 4.税务居住地
      let noTaxIdentificationNumber = null;
      if (this.newValue.newAccountCoTaxesList == null) {
        for (let i = 0; i < this.company1.placeAbode.length; i++) {
          if (this.company1.placeAbode[i].isSuiwuNO === true) {
            noTaxIdentificationNumber = this.company1.placeAbode[i].suibie === 'qita' ? this.company1.placeAbode[i].suiwuQita : this.company1.placeAbode[i].suibie;
          } else {
            noTaxIdentificationNumber = null;
          }
          accountCoTaxesList.push({
            accountId,
            createdBy,
            createdDate,
            taxResidenceCountry: this.company1.placeAbode[i].state3,
            taxResidenceIdCardNumber: this.company1.placeAbode[i].IDNumber,
            noTaxIdentificationNumber,
            differentRegistrationNumber: this.company1.placeAbode[i].isZChaoma
              ? this.company1.placeAbode[i].zhucehaoma
              : null
          });
        }
      } else {
        const index = this.newValue.newAccountCoTaxesList.length;
        for (let i = 0; i < this.company1.placeAbode.length; i++) {
          if (this.company1.placeAbode.length < index) {
            this.newValue.newAccountCoTaxesList.splice(this.company1.placeAbode.length);
            if (i < index) {
              this.newValue.newAccountCoTaxesList[i].taxResidenceCountry = this.company1.placeAbode[i].state3;
              this.newValue.newAccountCoTaxesList[i].taxResidenceIdCardNumber = this.company1.placeAbode[i].IDNumber;
              this.newValue.newAccountCoTaxesList[i].differentRegistrationNumber = this.company1.placeAbode[i].isZChaoma ? this.company1.placeAbode[i].zhucehaoma : null;
              if (this.company1.placeAbode[i].isSuiwuNO === true) {
                noTaxIdentificationNumber = this.company1.placeAbode[i].suibie === 'qita' ? this.company1.placeAbode[i].suiwuQita : this.company1.placeAbode[i].suibie;
              } else {
                noTaxIdentificationNumber = null;
              }
              this.newValue.newAccountCoTaxesList[i].noTaxIdentificationNumber = noTaxIdentificationNumber;
            }
          } else {
            if (i < index) {
              this.newValue.newAccountCoTaxesList[i].taxResidenceCountry = this.company1.placeAbode[i].state3;
              this.newValue.newAccountCoTaxesList[i].taxResidenceIdCardNumber = this.company1.placeAbode[i].IDNumber;
              this.newValue.newAccountCoTaxesList[i].differentRegistrationNumber = this.company1.placeAbode[i].isZChaoma ? this.company1.placeAbode[i].zhucehaoma : null;
              if (this.company1.placeAbode[i].isSuiwuNO === true) {
                noTaxIdentificationNumber = this.company1.placeAbode[i].suibie === 'qita' ? this.company1.placeAbode[i].suiwuQita : this.company1.placeAbode[i].suibie;
              } else {
                noTaxIdentificationNumber = null;
              }
              this.newValue.newAccountCoTaxesList[i].noTaxIdentificationNumber = noTaxIdentificationNumber;
            } else {
              this.newValue.newAccountCoTaxesList.push({
                accountId,
                createdBy,
                createdDate,
                taxResidenceCountry: this.company1.placeAbode[i].state3,
                taxResidenceIdCardNumber: this.company1.placeAbode[i].IDNumber,
                noTaxIdentificationNumber,
                differentRegistrationNumber: this.company1.placeAbode[i].isZChaoma
                  ? this.company1.placeAbode[i].zhucehaoma
                  : null
              });
            }
          }
        }
      }
      // 5.账户信息
      if (this.newValue.newAccountCoAccountPortfolio == null) {
        accountCoAccountPortfolio = {
          accountId,
          createdBy,
          createdDate,
          accountPortfolioType: this.company1.accountMessage.accountType,
          baseCurrency: this.company1.accountMessage.monetaryBase
        };
      } else {
        this.newValue.newAccountCoAccountPortfolio.accountPortfolioType = this.company1.accountMessage.accountType;
        this.newValue.newAccountCoAccountPortfolio.baseCurrency = this.company1.accountMessage.monetaryBase;
      }
      // 6.财富来源
      if (this.newValue.newAccountCoWealthSourceList == null) {
        for (let i = 0; i < this.company1.treasure.length; i++) {
          accountCoWealthSourceList.push({
            accountId,
            createdBy,
            createdDate,
            incomeType: this.company1.treasure[i].leixing,
            isCapitalInjection: !!this.company1.treasure[i].isCFzhuzi,
            percentageOfFunds: this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].baifenbi : null,
            sourceDesc: this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].zhushi : null
          });
        }
      } else {
        const index = this.newValue.newAccountCoWealthSourceList.length;
        for (let i = 0; i < this.company1.treasure.length; i++) {
          if (this.company1.treasure.length < index) {
            this.newValue.newAccountCoWealthSourceList.splice(this.company1.treasure.length);
            if (i < index) {
              this.newValue.newAccountCoWealthSourceList[i].incomeType = this.company1.treasure[i].leixing;
              this.newValue.newAccountCoWealthSourceList[i].isCapitalInjection = this.company1.treasure[i].isCFzhuzi;
              this.newValue.newAccountCoWealthSourceList[i].percentageOfFunds = this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].baifenbi : null;
              this.newValue.newAccountCoWealthSourceList[i].sourceDesc = this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].zhushi : null;
            }
          } else {
            if (i < index) {
              this.newValue.newAccountCoWealthSourceList[i].incomeType = this.company1.treasure[i].leixing;
              this.newValue.newAccountCoWealthSourceList[i].isCapitalInjection = this.company1.treasure[i].isCFzhuzi;
              this.newValue.newAccountCoWealthSourceList[i].percentageOfFunds = this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].baifenbi : null;
              this.newValue.newAccountCoWealthSourceList[i].sourceDesc = this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].zhushi : null;
            } else {
              this.newValue.newAccountCoWealthSourceList.push({
                accountId,
                createdBy,
                createdDate,
                incomeType: this.company1.treasure[i].leixing,
                isCapitalInjection: !!this.company1.treasure[i].isCFzhuzi,
                percentageOfFunds: this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].baifenbi : null,
                sourceDesc: this.company1.treasure[i].isCFzhuzi ? this.company1.treasure[i].zhushi : null
              });
            }
          }
        }
      }
      const data = {
        accountCoInstitutional: this.newValue.newAccountCoInstitutional !== null ? this.newValue.newAccountCoInstitutional : accountCoInstitutional,
        accountCoAddressList: this.newValue.newAccountCoAddressList !== null ? this.newValue.newAccountCoAddressList : accountCoAddressList,
        accountCoContactList: this.newValue.newAccountCoContactList !== null ? this.newValue.newAccountCoContactList : accountCoContactList,
        accountCoTaxesList: this.newValue.newAccountCoTaxesList !== null ? this.newValue.newAccountCoTaxesList : accountCoTaxesList,
        accountCoAccountPortfolio: this.newValue.newAccountCoAccountPortfolio !== null ? this.newValue.newAccountCoAccountPortfolio : accountCoAccountPortfolio,
        accountCoWealthSourceList: this.newValue.newAccountCoWealthSourceList !== null ? this.newValue.newAccountCoWealthSourceList : accountCoWealthSourceList
      };
      this.$http({
        url: '/openAccount/company/saveOne',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/company2' });
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
    }
  }
};
</script>

<style lang="scss" scoped>
#bodys1 .fuxuan1 {
  margin-top: -6px;
}
/* 复选框样式问题 */
// #ZC-regiMain .zhuce3ji {
//   line-height: 0px;
//   margin-bottom: 10px;
// }

#ZC-regiMain .el-form-item.gaizuzhi {
  margin-top: 4px;
  margin-bottom: 0px;
}
#ZC-regiMain .el-checkbox-group {
  line-height: 10px;
}
</style>

