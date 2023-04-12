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
            ref="company8"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="company8"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- 注册拥有人 -->
              <div class="formNape">
                <div v-show="company8.isSuishouType=='1'" class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.enterprise.title') }}</h3> <!-- $i18n - 注册拥有人 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.enterprise.describe') }}</p> <!-- $i18n - 请提供您公司持有10%或更高所有权的 ... -->
                </div>
                <div v-show="company8.isSuishouType=='2' || company8.isSuishouType=='3'" class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.enterprise.title') }}</h3> <!-- $i18n - 注册拥有人 -->
                  <!-- $i18n - 根据美国国税局税收条款， -->
                  <!-- $i18n - 被归类为 -->
                  <!-- $i18n - ，因此我们需要收集 -->
                  <!-- $i18n - 的完整所有权结构，以便正确地报告您账户收到的款项并预扣税款。 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.describe1a') }} {{ company8.youxiangongsi }} {{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.describe1b') }} {{ company8.suishouFenlei }} {{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.describe1c') }} {{ company8.youxiangongsi }} {{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.describe1d') }}</p>
                  <p>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.describe2') }}</p> <!-- $i18n - 请确认下面表格中所有 持有人 of 机构。总 ... -->
                </div>
                <div class="formNapeContent">
                  <div v-show="company8.isSuishouType=='2' || company8.isSuishouType=='3'">
                    <div class="jingshiK">
                      <p class="pLH zhuce2ji" style="margin-bottom:4px;">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.warning1') }}</p> <!-- $i18n - 警告：如果您的持有人未能登录 ... -->
                    </div>
                    <p class="zhuce3ji" style="margin-bottom:4px;">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.warning2') }}</p> <!-- $i18n - 重要事项！请记下分配给 ... -->
                    <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.warning3') }}</p> <!-- $i18n - 您需要以安全方式将这些密码告知每位持有人 -->
                  </div>
                  <div class="jubuTianjia" style="height:40px;margin-top: 20px;">
                    <div class="xiaoshou" @click="addGerengudong()">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.btnAddPer') }}</div> <!-- $i18n - 添加个人股东 -->
                    <div class="xiaoshou" @click="addFarengudong()">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.btnAddLegal') }}</div> <!-- $i18n - 添加法人股东 -->
                  </div>
                  <div v-show="company8.rukou.ifbiaoge" style="margin-top:30px;" class="gudongTable">
                    <table border="1" cellspacing="0" cellpadding="0">
                      <thead>
                        <tr>
                          <th>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.name') }}</th> <!-- $i18n - 姓名 -->
                          <th>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.ownership') }}</th> <!-- $i18n - 所有权/权利 -->
                          <!-- <th>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.username') }}</th> $i18n - 用户名 -->
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item,index) in company8.tableTbady" :key="index">
                          <td>{{ item.compellation }}</td>
                          <td>{{ item.suoyouquan }}%</td>
                          <!-- <td>{{ item.username }}</td> -->
                          <td>
                            <div class="xiaoshou deleteBtn" @click="removeBiaoGe(index)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                          </td>
                        </tr>

                        <tr>
                          <td>{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.total') }}</td> <!-- $i18n - 所有权合计 -->
                          <td>{{ company8.suoyouquanZongshu }}%</td>
                          <!-- <td /> -->
                          <td />
                        </tr>

                      </tbody>
                    </table>
                  </div>
                  <div v-if="shitiTishi === '1'" class="shitiTishi">
                    {{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.hint1') }}
                  </div> <!-- $i18n - 实体被识别为无视实体,因此只能有一 ... -->
                  <div v-else-if="shitiTishi === '2'" class="shitiTishi">
                    {{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.hintb') }}
                  </div> <!-- $i18n - 通常,仅拥有一名持有人的实体会被归为无视 ... -->
                </div>
              </div>
              <!-- 提交按钮 -->
              <div v-show="company8.rukou.ifbottom" class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('company8')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }}</el-button>
                  </el-form-item>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>
      <!-- 模态框 1-->
      <div v-if="company8.rukou.ifmodalBox" class="modalBox">
        <div class="contentBOX">
          <div class="contentBOXdignwei contentBOXMoL">
            <el-form
              ref="company8"
              status-icon
              :rules="rules"
              :model="company8"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="biaoTitle">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.btnAddPer') }}</div> <!-- $i18n - 添加个人股东 -->
                <!-- 注册拥有人 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.info.title') }}</h3> <!-- $i18n - 个人信息 -->
                  </div>
                  <div class="formNapeContent ">
                    <el-form-item prop="gerenGudong.companyName">
                      <el-select v-model="company8.gerenGudong.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                        <dictionary dict-code="appellationType" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="gerenGudong.call">
                      <el-input v-model="company8.gerenGudong.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.middle">
                      <el-input v-model="company8.gerenGudong.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 不适用于中国客户 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.surname">
                      <el-input v-model="company8.gerenGudong.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.suffix">
                      <el-select v-model="company8.gerenGudong.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                        <el-option label="Jr " value="Jr" />
                        <el-option label="Sr " value="Sr" />
                        <el-option label="I " value="I" />
                        <el-option label="II " value="II" />
                        <el-option label="III " value="III" />
                        <el-option label="IV " value="IV" />
                        <el-option label="V " value="V" />
                      </el-select>
                    </el-form-item>
                    <!-- <el-form-item prop="gerenGudong.username"> -->
                    <!-- <el-input v-model="company8.gerenGudong.username" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.info.username')" /> $i18n - 用户名 -->
                    <!-- </el-form-item> -->
                    <!-- <el-form-item prop="gerenGudong.psword" class="percentage"> -->
                    <!-- <el-input v-model="company8.gerenGudong.psword" maxlength="16" type="password" :placeholder="$t('registerOpenAccount.openAccount.common.info.password')" /> $i18n - 密码 -->
                    <!-- <div v-show="intensityStatus !== 0" class="icon-percent">{{ intensityStatus }}</div> -->
                    <!-- </el-form-item> -->
                    <!-- <el-form-item prop="gerenGudong.psword1"> -->
                    <!-- <el-input v-model="company8.gerenGudong.psword1" maxlength="16" type="password" :placeholder="$t('registerOpenAccount.openAccount.common.info.password1')" /> $i18n - 确认密码 -->
                    <!-- </el-form-item> -->
                    <el-form-item prop="gerenGudong.email">
                      <el-input v-model="company8.gerenGudong.email" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.info.email')" /> <!-- $i18n - 电邮地址 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.suoyouquanBi" class="percentage">
                      <el-input v-model="company8.gerenGudong.suoyouquanBi" maxlength="3" :placeholder="$t('registerOpenAccount.openAccount.common.info.ownership')" /> <!-- $i18n - 所有权百分比 -->
                      <div class="icon-percent">%</div>
                    </el-form-item>
                    <div class="block">
                      <el-form-item prop="gerenGudong.birth">
                        <el-date-picker
                          v-model="company8.gerenGudong.birth"
                          value-format="yyyy-MM-dd"
                          type="date"
                          :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                        /> <!-- $i18n - 出生日期 -->
                      </el-form-item>
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
                      <el-form-item prop="juzhudizhi.state1">
                        <el-select v-model="company8.juzhudizhi.state1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.dizhi">
                        <el-input
                          v-model="company8.juzhudizhi.dizhi"
                          maxlength="64"
                          :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                        /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                      </el-form-item>
                      <el-form-item>
                        <el-input v-model="company8.juzhudizhi.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.chengshi">
                        <el-input v-model="company8.juzhudizhi.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.province">
                        <el-input v-model="company8.juzhudizhi.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.youbian">
                        <el-input v-model="company8.juzhudizhi.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                      </el-form-item>
                    </div>
                  </div>
                </div>
                <!-- 联系信息 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.contact.title') }}</h3> <!-- $i18n - 联系信息 -->
                  </div>
                  <div v-for="(item,index) in company8.contact" :key="index" class="formNapeContent">
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
                          <el-select v-model="item.state2" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.contact.state')"> <!-- $i18n - 国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                      <el-col :span="11">
                        <el-form-item
                          style="margin-bottom: 2px;"
                          :prop="'contact.'+index+'.phoneNumber'"
                          :rules="rules.phoneNumber"
                        >
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
                    <div v-show="company8.contact.length !== 5" class="add" @click="addelement1()">{{ $t('registerOpenAccount.openAccount.common.contact.addOtherNumbers') }}</div> <!-- $i18n - 添加其他号码 -->
                    <div v-show="company8.contact.length !== 1" class="cancel" @click="removeelement1()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                  <div class="isBtn">
                    <ul>
                      <li
                        class="isyes"
                        :class="{ dianji: company8.contact[0].isguojiButong}"
                        @click="company8.contact[0].isguojiButong=true"
                      >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      <li
                        class="isno"
                        :class="{ dianji: !company8.contact[0].isguojiButong}"
                        @click="company8.contact[0].isguojiButong=false"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentNationalities1') }}</li> <!-- $i18n - 您的国籍是否与上方所选国家不同？ -->
                    </ul>
                  </div>
                  <el-form-item v-show="company8.contact[0].isguojiButong" prop="contact[0].guoji">
                    <el-select v-model="company8.contact[0].guoji" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <li
                        class="isyes"
                        :class="{ dianji: company8.contact[0].ischushengguoButong}"
                        @click="company8.contact[0].ischushengguoButong=true"
                      >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      <li
                        class="isno"
                        :class="{ dianji: !company8.contact[0].ischushengguoButong}"
                        @click="company8.contact[0].ischushengguoButong=false"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentBirthStateDescribe1') }}</li> <!-- $i18n - 您的出生国是否与上方所选国家不同? -->
                    </ul>
                  </div>
                  <el-form-item
                    v-show="company8.contact[0].ischushengguoButong"
                    prop="contact[0].chushengguo"
                  >
                    <el-select v-model="company8.contact[0].chushengguo" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.butongBirthState')"> <!-- $i18n - 出生国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <li
                        class="isyes"
                        :class="{ dianji: company8.contact[0].isMeiguoNasuiren}"
                        @click="company8.contact[0].isMeiguoNasuiren=true"
                      >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      <li
                        class="isno"
                        :class="{ dianji: !company8.contact[0].isMeiguoNasuiren}"
                        @click="company8.contact[0].isMeiguoNasuiren=false"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.contact.taxpayer') }}</li> <!-- $i18n - 该个人是美国纳税人吗? -->
                    </ul>
                  </div>
                </div>
                <!-- 身份证明 -->
                <div v-show="!company8.contact[0].isMeiguoNasuiren" class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.identity.title') }}</h3> <!-- $i18n - 身份证明 -->
                  </div>
                  <div class="formNapeContent">
                    <el-form-item prop="identification.SFsignGuojia">
                      <el-select v-model="company8.identification.SFsignGuojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.identity.state')"> <!-- $i18n - 签发国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="identification.SFzhengjianleixing">
                      <el-select
                        v-model="company8.identification.SFzhengjianleixing"
                        :no-match-text="$t('account.manage.no_data')"
                        :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"
                      > <!-- $i18n - 身份证类型 -->
                        <dictionary dict-code="cardType" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="identification.SFzhengjianhao">
                      <el-input v-model="company8.identification.SFzhengjianhao" maxlength="63" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                    </el-form-item>
                  </div>
                </div>
                <!-- 税务居住地 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                  </div>
                  <div
                    v-for="(item,index) in company8.placeAbode"
                    :key="index"
                    class="formNapeContent"
                  >
                    <el-form-item :prop="'placeAbode.'+index+'.SWstate3'" :rules="rules.SWstate3">
                      <el-select v-model="item.SWstate3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item
                      :prop="'placeAbode.'+index+'.IDNumber'"
                      :rules="rules.IDNumber"
                    >
                      <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <li
                          class="isyes"
                          :class="{ dianji: !item.isSuiwuNO}"
                          @click="item.isSuiwuNO=false"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: item.isSuiwuNO}"
                          @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.IDNumber')"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        <li class="istext zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
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
                    <div v-show="company8.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加税务居住地 -->
                    <div v-show="company8.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                </div>
                <!-- 保存,关闭 -->
                <div class="isBtn right1">
                  <ul>
                    <li class="isyes Motaiteshuchuli" @click="preserve1('company8')">{{ $t('registerOpenAccount.common.btn.save') }}</li> <!-- $i18n - 保存 -->
                    <li class="isno Motaiteshuchuli1" style="margin-right:0px;" @click="shut1()">{{ $t('registerOpenAccount.common.btn.close') }}</li> <!-- $i18n - 关闭 -->
                  </ul>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>
      <!-- 模态框 2-->
      <div v-if="company8.rukou.ifmodalBox1" class="modalBox">
        <div class="contentBOX">
          <div class="contentBOXdignwei contentBOXMoL">
            <el-form
              ref="company8"
              status-icon
              :rules="rules"
              :model="company8"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="biaoTitle">{{ $t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.btnAddLegal') }}</div> <!-- $i18n - 添加法人股东 -->
                <!-- 实体名称 -->
                <div class="formNape">
                  <div class="formNapeContent">
                    <el-form-item prop="InstanceXinxi.name">
                      <el-input v-model="company8.InstanceXinxi.name" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.modal.name')" /> <!-- $i18n - 实体名称 -->
                    </el-form-item>
                    <el-form-item prop="InstanceXinxi.percentage" class="percentage">
                      <el-input v-model="company8.InstanceXinxi.percentage" maxlength="3" :placeholder="$t('registerOpenAccount.openAccount.common.RegisteredOwner.parEntity.modal.percentage')" /> <!-- $i18n - 所有权百分比 -->
                      <div class="icon-percent">%</div>
                    </el-form-item>
                  </div>
                </div>
                <!-- 主要经营地点 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title1') }}</h3> <!-- $i18n - 主要经营地点 -->
                  </div>
                  <div class="formNapeContent">
                    <div>
                      <el-form-item prop="juzhudizhi.state1">

                        <el-select v-model="company8.juzhudizhi.state1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.dizhi">
                        <el-input
                          v-model="company8.juzhudizhi.dizhi"
                          maxlength="64"
                          :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                        /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                      </el-form-item>
                      <el-form-item>
                        <el-input v-model="company8.juzhudizhi.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.chengshi">
                        <el-input v-model="company8.juzhudizhi.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.province">
                        <el-input v-model="company8.juzhudizhi.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                      </el-form-item>
                      <el-form-item prop="juzhudizhi.youbian">
                        <el-input v-model="company8.juzhudizhi.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                      </el-form-item>
                    </div>
                  </div>
                </div>
                <!-- 联系信息 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.contact.title') }}</h3> <!-- $i18n - 联系信息 -->
                  </div>
                  <div v-for="(item,index) in company8.contact1" :key="index" class="formNapeContent">
                    <el-form-item>
                      <el-col :span="6">
                        <el-form-item style="margin-bottom: 2px;" :prop="'contact1.'+index+'.time'" :rules="rules.time">

                          <el-select v-model="item.time" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.contact.type')"> <!-- $i18n - 手机类型 -->
                            <dictionary dict-code="phoneNumberType" />
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                      <el-col :span="6">
                        <el-form-item style="margin-bottom: 2px;" :prop="'contact1.'+index+'.state2'" :rules="rules.state2">
                          <el-select v-model="item.state2" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.contact.state')"> <!-- $i18n - 国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                      </el-col>
                      <el-col class="line" :span="0.7" style="margin-right:14px">&nbsp;</el-col>
                      <el-col :span="11">
                        <el-form-item
                          style="margin-bottom: 2px;"
                          :prop="'contact1.'+index+'.phoneNumber'"
                          :rules="rules.phoneNumber"
                        >
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
                    <div v-show="company8.contact1.length !== 5" class="add" @click="addelement2()">{{ $t('registerOpenAccount.openAccount.common.contact.addOtherNumbers') }}</div> <!-- $i18n - 添加其他号码 -->
                    <div v-show="company8.contact1.length !== 1" class="cancel" @click="removeelement2()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                  <div class="top15">
                    <el-form-item prop="contact1[0].guojia">
                      <el-select v-model="company8.contact1[0].guojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.contact.setUp')"> <!-- $i18n - 成立国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                  </div>
                  <div class="isBtn">
                    <ul>
                      <li
                        class="isyes"
                        :class="{ dianji: company8.contact1[0].isGongkaiJiaoyi}"
                        @click="company8.contact1[0].isGongkaiJiaoyi=true"
                      >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      <li
                        class="isno"
                        :class="{ dianji: !company8.contact1[0].isGongkaiJiaoyi}"
                        @click="company8.contact1[0].isGongkaiJiaoyi=false"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      <li class="istext zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.contact.deal') }}</li> <!-- $i18n - 实体是否在监管交易所公开上市交易？ -->
                    </ul>
                  </div>
                  <div v-show="company8.contact1[0].isGongkaiJiaoyi">
                    <el-form-item
                      prop="contact1[0].jiaoyisuo"
                    >
                      <el-input v-model="company8.contact1[0].jiaoyisuo" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.contact.exchange')" /> <!-- $i18n - 交易所 -->
                    </el-form-item>
                    <el-form-item
                      prop="contact1[0].daima"
                    >
                      <el-input v-model="company8.contact1[0].daima" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.contact.code')" /> <!-- $i18n - 代码 -->
                    </el-form-item>
                  </div>
                </div>
                <!-- 税务居住地 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                  </div>
                  <div
                    v-for="(item,index) in company8.placeAbode"
                    :key="index"
                    class="formNapeContent"
                  >
                    <el-form-item :prop="'placeAbode.'+index+'.SWstate3'" :rules="rules.SWstate3">
                      <el-select v-model="item.SWstate3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item
                      :prop="'placeAbode.'+index+'.IDNumber'"
                      :rules="rules.IDNumber"
                    >
                      <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                    </el-form-item>
                    <div class="isBtn">
                      <ul>
                        <li
                          class="isyes"
                          :class="{ dianji: !item.isSuiwuNO}"
                          @click="item.isSuiwuNO=false"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        <li
                          class="isno"
                          :class="{ dianji: item.isSuiwuNO}"
                          @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.IDNumber')"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                      </ul>
                    </div>
                    <transition name="fade">
                      <div v-if="item.isSuiwuNO" class="textcontent1 fade">
                        <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.taxes.describe') }}：
                        </span> <!-- $i18n - 美国国税局规则要求我们收 ... -->
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
                    <div v-show="company8.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加其他税务居住地 -->
                    <div v-show="company8.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                </div>
                <!-- 签署方信息 -->
                <div class="formNape">
                  <div class="formNapeTitle">
                    <h3>{{ $t('registerOpenAccount.openAccount.common.info.title2') }}</h3> <!-- $i18n - 签署方信息 -->
                  </div>
                  <div class="formNapeContent">
                    <el-form-item prop="gerenGudong.companyName">
                      <el-select v-model="company8.gerenGudong.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                        <dictionary dict-code="appellationType" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="gerenGudong.call">
                      <el-input v-model="company8.gerenGudong.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.middle">
                      <el-input v-model="company8.gerenGudong.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 中间名,不适用于中国客户 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.surname">
                      <el-input v-model="company8.gerenGudong.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                    </el-form-item>
                    <el-form-item prop="gerenGudong.suffix">
                      <el-select v-model="company8.gerenGudong.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                        <el-option label="Jr " value="Jr" />
                        <el-option label="Sr " value="Sr" />
                        <el-option label="I " value="I" />
                        <el-option label="II " value="II" />
                        <el-option label="III " value="III" />
                        <el-option label="IV " value="IV" />
                        <el-option label="V " value="V" />
                      </el-select>
                    </el-form-item>
                    <!-- <el-form-item prop="gerenGudong.username"> -->
                    <!-- <el-input v-model="company8.gerenGudong.username" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.info.username')" /> $i18n - 用户名 -->
                    <!-- </el-form-item> -->
                    <!-- <el-form-item prop="gerenGudong.psword" class="percentage"> -->
                    <!-- <el-input v-model="company8.gerenGudong.psword" maxlength="16" type="password" :placeholder="$t('registerOpenAccount.openAccount.common.info.password')" /> $i18n - 密码 -->
                    <!-- <div v-show="intensityStatus !== 0" class="icon-percent">{{ intensityStatus }}</div> -->
                    <!-- </el-form-item> -->
                    <!-- <el-form-item prop="gerenGudong.psword1"> -->
                    <!-- <el-input v-model="company8.gerenGudong.psword1" maxlength="16" type="password" :placeholder="$t('registerOpenAccount.openAccount.common.info.password1')" /> $i18n - 确认密码 -->
                    <!-- </el-form-item> -->
                    <el-form-item prop="gerenGudong.email">
                      <el-input v-model="company8.gerenGudong.email" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.info.email')" /> <!-- $i18n - 电邮地址 -->
                    </el-form-item>
                  </div>
                </div>
                <!-- 保存,关闭 -->
                <div class="isBtn right1">
                  <ul>
                    <li class="isyes Motaiteshuchuli" @click="preserve2('company8')">{{ $t('registerOpenAccount.common.btn.save') }}</li> <!-- $i18n - 保存 -->
                    <li class="isno Motaiteshuchuli1" @click="shut2()">{{ $t('registerOpenAccount.common.btn.close') }}</li> <!-- $i18n - 关闭 -->
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
export default {
  components: {
    dictionary
  },
  data() {
    // 个人信息 - 个人
    var validateGeren1 = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 用户名验证
    var validateGerenYHming = (rule, value, callback) => {
      const _this = this;
      this.$verify.Username(rule, value, callback, _this);
    };
    // 密码验证
    var validateGerenYHmima = (rule, value, callback) => {
      const mate = this.company8.gerenGudong.username;
      this.$verify.Psword(rule, value, mate, callback).then(res => {
        callback();
        this.$refs.company8.validateField('gerenGudong.psword1');
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
    // 密码验证--是否一致
    var validateGerenYHmima1 = (rule, value, callback) => {
      const mate = this.company8.gerenGudong.psword;
      this.$verify.PswordT(rule, value, mate, callback);
    };
    // 邮箱验证
    var validateEmail = (rule, value, callback) => {
      this.$verify.Postbox(rule, value, callback);
    };
    // 所有权验证 --个人
    var validateGrsuoyouquan = (rule, value, callback) => {
      const num = /^[0-9]*$/;
      const sum = this.company8.suoyouquanZongshu;
      if (this.company8.isSuishouType === '3') {
        if (value !== '') {
          if (num.test(value)) {
            if (Number(value) === 100) {
              callback();
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership'))); // $i18n - 无视实体所有人必须拥有100%所有权
            }
          } else {
            callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.NumberValidator'))); // $i18n - 请输入正确信息,且必须为数字
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else {
        this.$verify.Ownership(rule, value, sum, callback);
      }
    };
    // 所有权验证 --法人
    var validateGrsuoyouquan1 = (rule, value, callback) => {
      const num = /^[0-9]*$/;
      const sum = this.company8.suoyouquanZongshu;
      if (this.company8.isSuishouType === '3') {
        if (value !== '') {
          if (num.test(value)) {
            if (Number(value) === 100) {
              callback();
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership'))); // $i18n - 无视实体所有人必须拥有100%所有权
            }
          } else {
            callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.NumberValidator'))); // $i18n - 请输入正确信息,且必须为数字
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else {
        this.$verify.Ownership(rule, value, sum, callback);
      }
    };
    // 数字验证
    var shuziyanzheng = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    // 税务号
    var theTaxNo = (rule, value, callback) => {
      if (this.company8.rukou.ifmodalBox) {
        if (this.company8.contact[0].isMeiguoNasuiren === false) {
          if (value !== 'N.A.') {
            if (this.company8.identification.SFsignGuojia !== 'CN' ||
                this.company8.identification.SFzhengjianleixing !== 'idCard' ||
                this.company8.identification.SFzhengjianhao === '') {
              this.$verify.notChinese(rule, value, callback);
            } else {
              this.company8.placeAbode.forEach(item => {
                if (item.IDNumber === value) {
                  if (item.SWstate3 === 'CN') {
                    if (this.company8.identification.SFzhengjianhao === value) {
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
        } else {
          this.$verify.notChinese(rule, value, callback);
        }
      } else {
        this.$verify.notChinese(rule, value, callback);
      }
    };
    // 电话号码验证
    var dianhuahaoma = (rule, value, callback) => {
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
    // 出生日期
    var validateBirth = (rule, value, callback) => {
      this.$verify.Birth(rule, value, callback);
    };
    // 国家-税务居住地-动态渲染
    var validateZJhaoma = (rule, value, callback) => {
      const _this = this;
      if (value === '') {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      } else {
        for (let i = 0; i < this.company8.placeAbode.length; i++) {
          this.$verify.State(this.company8.placeAbode[i].SWstate3, this.company8.placeAbode[i].taxNumList, this.company8.taxNumList1, _this, this.lang);
        }
        callback();
      }
    };
    // 纳税识别号
    var validateGeren5 = (rule, value, callback) => {
      const array1 = this.company8.placeAbode;
      this.$verify.SWnasuihao(rule, value, array1, callback);
    };

    // 不同国籍
    var validateGeren2 = (rule, value, callback) => {
      const mate = this.company8.contact[0].isguojiButong;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    //  不同出生国
    var validateGeren3 = (rule, value, callback) => {
      const mate = this.company8.contact[0].ischushengguoButong;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    //  是否美国纳税人
    var validateGeren4 = (rule, value, callback) => {
      const mate = this.company8.contact[0].isMeiguoNasuiren === false;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    //  是否美国纳税人-身份证明验证
    var validateGeren41 = (rule, value, callback) => {
      const mate = this.company8.contact[0].isMeiguoNasuiren === false;
      this.$verify.stairIf(rule, value, mate, callback);
    };

    //  添加法人 --不同之处地方验证
    var validateGeren7 = (rule, value, callback) => {
      const mate = this.company8.contact[0].isGongkaiJiaoyi;
      this.$verify.stairIf(rule, value, mate, callback);
    };

    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      intensityStatus: 0,
      shitiTishi: false,
      company8: {
        guDongstate: '',
        condition: 0,
        youxiangongsi: '',
        suishouFenlei: '',
        isSuishouType: '1', // 根据税收分类展示不同数据  1合伙 2,合伙企业,无视实体
        iszhanghutishi: false, // 用户名提示状态
        personalTypeG: null, // 个人类型 --> 个人股东
        personalTypeF: null, // 个人类型 --> 法人股东
        addressType: null, // 地址类型 --> 居住地址
        rukou: { // 表格显示隐藏
          ifmodalBox: false, // 个人
          ifmodalBox1: false, // 法人
          ifbiaoge: false, // 表格
          ifbottom: true // 保存&继续
        },
        // 表格展示
        tableTbady: [

        ],
        suoyouquanZongshu: 0,

        // 实体名称-公司
        InstanceXinxi: {
          name: '',
          percentage: ''
        },
        // 个人信息-个人  签署方信息-公司
        gerenGudong: {
          companyName: '',
          call: '',
          middle: '',
          surname: '',
          suffix: '',
          birth: '', // 公司没有
          toucun: [],
          guanyuanZW: '',
          username: '',
          psword: '',
          psword1: '',
          email: '',
          suoyouquanBi: '' // 公司没有
        },
        // 居住地址-个人    主要经营地点-公司
        juzhudizhi: {
          state1: '',
          dizhi: '',
          dizhi1: '',
          chengshi: '',
          province: '',
          youbian: ''
        },
        // 联系信息-个人
        contact: [
          {
            time: '',
            state2: '',
            phoneNumber: '',
            isguojiButong: false,
            guoji: '',
            ischushengguoButong: false,
            chushengguo: '',
            isMeiguoNasuiren: false
          }
        ],
        //  联系信息-公司
        contact1: [
          {
            time: '',
            state2: '',
            phoneNumber: '',
            guojia: '',
            isGongkaiJiaoyi: '',
            jiaoyisuo: '',
            daima: ''
          }
        ],
        // 身份证明-个人
        identification: {
          SFsignGuojia: '',
          SFzhengjianleixing: '',
          SFzhengjianhao: ''
        },
        // 税务居住地-个人  税务居住地-公司
        placeAbode: [
          {
            SWstate3: '',
            IDNumber: '',
            IDNumber1: '',
            suibie: '',
            isSuiwuNO: false, // is 税务识别号
            taxNumList: null, // 页面上使用
            suiwuQita: ''
          }
        ],
        taxNumList1: null, // 页面上使用-暂存
        // 所有权大于10% 受益人
        dayu10: [
        ],
        // 所有权大于25% 受益人
        dayu25: [
        ]
      },
      company8TS: null, // conpany8初始数据暂存 完成表单清空功能
      formdata: {},
      rules: {
        // 个人信息
        'gerenGudong.companyName': [{
          validator: validateGeren1, trigger: 'change'
        }],
        'gerenGudong.call': [{
          validator: validateGeren1, trigger: 'blur'
        }],
        'gerenGudong.middle': [{
          validator: notChinese1, trigger: 'blur'
        }],
        'gerenGudong.surname': [{
          validator: validateGeren1, trigger: 'blur'
        }],
        'gerenGudong.suffix': [{
          validator: notChinese1, trigger: 'change'
        }],
        'gerenGudong.username': [{
          validator: validateGerenYHming, trigger: 'blur'
        }],
        'gerenGudong.psword': [{
          validator: validateGerenYHmima, trigger: 'blur'
        }],
        'gerenGudong.psword1': [{
          validator: validateGerenYHmima1, trigger: 'blur'
        }],
        'gerenGudong.email': [{
          validator: validateEmail, trigger: 'blur'
        }],
        'gerenGudong.suoyouquanBi': [{
          validator: validateGrsuoyouquan, trigger: 'blur'
        }],
        'gerenGudong.birth': [{
          validator: validateBirth, trigger: 'change'
        }],
        // 居住地址
        'juzhudizhi.state1': [{
          validator: validateGeren1, trigger: 'change'
        }],
        'juzhudizhi.dizhi': [{
          validator: validateGeren1, trigger: 'blur'
        }],

        'juzhudizhi.chengshi': [{
          validator: validateGeren1, trigger: 'blur'
        }],
        'juzhudizhi.province': [{
          validator: validateGeren1, trigger: 'blur'
        }],
        'juzhudizhi.youbian': [{
          validator: shuziyanzheng, trigger: 'blur'
        }],
        // 联系信息
        time: [{
          validator: validateGeren1, trigger: 'change'
        }],
        state2: [{
          validator: validateGeren1, trigger: 'change'
        }],
        phoneNumber: [{
          validator: dianhuahaoma, trigger: 'blur'
        }],
        // ------不同国籍
        'contact[0].guoji': [{
          validator: validateGeren2, trigger: 'change'
        }],
        // ------不同出生国
        'contact[0].chushengguo': [{
          validator: validateGeren3, trigger: 'change'
        }],
        // -----身份证明 取决于是否美国纳税人
        'identification.SFsignGuojia': [{
          validator: validateGeren4, trigger: 'change'
        }],
        'identification.SFzhengjianleixing': [{
          validator: validateGeren4, trigger: 'change'
        }],
        'identification.SFzhengjianhao': [{
          validator: validateGeren41, trigger: 'blur'
        }],
        // 税务居住地
        SWstate3: [{
          validator: validateZJhaoma, trigger: 'change'
        }],
        IDNumber: [{
          validator: theTaxNo, trigger: 'blur'
        }],
        suibie: [{
          validator: validateGeren5, trigger: 'change'
        }],

        // 公司--不同
        // 实体名称-所有权
        'InstanceXinxi.name': [{
          validator: validateGeren1, trigger: 'blur'
        }],
        'InstanceXinxi.percentage': [{
          validator: validateGrsuoyouquan1, trigger: 'blur'
        }],
        // 联系信息 - 成立国家
        'contact1[0].guojia': [{
          validator: validateGeren1, trigger: 'change'
        }],
        // 联系信息 - 交易所
        'contact1[0].jiaoyisuo': [{
          validator: validateGeren7, trigger: 'blur'
        }],
        // 联系信息 - 代码
        'contact1[0].daima': [{
          validator: validateGeren7, trigger: 'blur'
        }]
      }
    };
  },
  watch: {
    company8: {// 深度监听，可监听到对象、数组的变化
      handler(val, oldVal) {
        let zongBaifenbi = 0;
        for (let i = 0; i < this.company8.tableTbady.length; i++) {
          zongBaifenbi += parseInt(this.company8.tableTbady[i].suoyouquan);
        }
        this.company8.suoyouquanZongshu = zongBaifenbi;
      },
      deep: true
    }
  },
  created() {
    this.personalType();
    this.addressType();
    this.accountCoTaxClassification();
    this.taxIdentificationNumber();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountCoPersonalG() { // 个人股东-信息
      this.$http({
        url: '/account/accountCoPersonal/findByAccountId',
        method: 'get',
        params: { personalType: this.company8.personalTypeG, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.company8.tableTbady.push({
                  compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                  suoyouquan: res.object[i].prcentageOfOwnership,
                  username: res.object[i].userName,
                  id: res.object[i].id
                });
                res.object[i].gudongIndex = '1';
              }
              this.company8.rukou.ifbiaoge = true;
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
    accountCoPersonalF() { // 法人--信息
      this.$http({
        url: '/account/accountCoPersonal/findByAccountId',
        method: 'get',
        params: { personalType: this.company8.personalTypeF, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.company8.tableTbady.push({
                  compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                  suoyouquan: res.object[i].prcentageOfOwnership,
                  username: res.object[i].userName,
                  id: res.object[i].id
                });
                res.object[i].gudongIndex = '1';
              }
              this.company8.rukou.ifbiaoge = true;
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
    accountCoTaxClassification() { // 获取公司税收分类
      this.$http({
        url: '/account/accountCoTaxClassification/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object.taxType === 'enterprise') {
                this.company8.isSuishouType = '1';
                this.company8.suishouFenlei = this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.enterprise'); // $i18n - 企业
              } else if (res.object.taxType === 'partnership (must have at least 2 owners)') {
                this.company8.isSuishouType = '2';
                this.company8.suishouFenlei = this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.partnership'); // $i18n - 合伙企业
              } else if (res.object.taxType === 'disregardEntities (must only have 1 owner)') {
                this.company8.isSuishouType = '3';
                this.company8.suishouFenlei = this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.Ignore'); // $i18n - 无视实体
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
    accountCoInstitutional() { // 获取公司名
      this.$http({
        url: '/account/accountCoInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.company8.youxiangongsi = res.object.institutionalName;
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
            // this.company8.personalTypeG = res.object[2].dictValue;
            // this.company8.personalTypeF = res.object[3].dictValue;
            this.company8.personalTypeG = 'individualShareholder';
            this.company8.personalTypeF = 'legalPersonShareholder';
            this.company8.tableTbady = [];
            this.accountCoPersonalG();
            this.accountCoPersonalF();
            // this.accountCoInstitutional(res.object[1].dictValue);
            this.accountCoInstitutional('authorizedPerson');
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
            this.company8.placeAbode[0].taxNumList = JSON.parse(JSON.stringify(res.object));
            this.company8.taxNumList1 = JSON.parse(JSON.stringify(res.object));
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
            this.company8.addressType = res.object[0].dictValue;
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
    // 删除表格-行
    removeBiaoGe(index) {
      const id = this.company8.tableTbady[index].id;
      this.company8.tableTbady.splice(index, 1);
      this.$http({
        url: '/openAccount/company/removeEight',
        method: 'put',
        params: { id }
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
    // 添加个人股东-点击事件
    addGerengudong() {
      this.company8.rukou.ifmodalBox = true;
      this.company8.rukou.ifbottom = false;
      this.company8TS = JSON.parse(JSON.stringify(this.company8));
    },
    // 添加法人股东-点击事件
    addFarengudong() {
      this.company8.rukou.ifmodalBox1 = true;
      this.company8.rukou.ifbottom = false;
      this.company8TS = JSON.parse(JSON.stringify(this.company8));
    },
    // 联系添加-个人
    addelement1() {
      this.company8.contact.push({
        time: '',
        state: '',
        phoneNumber: ''
      });
    },
    removeelement1() {
      this.company8.contact.pop(this.company8.contact);
    },
    // is纳税识别号-验证证件号码
    taxIsBtn(VerifyPath) {
      let num = 1;
      const timerId = setInterval(() => {
        num--;
        this.$refs.company8.validateField(VerifyPath);
        if (num === 0) {
          clearInterval(timerId);
        }
      }, 100);
      return true;
    },
    // 联系添加-公司
    addelement2() {
      this.company8.contact1.push({
        time: '',
        state: '',
        phoneNumber: ''
      });
    },
    removeelement2() {
      this.company8.contact1.pop(this.company8.contact1);
    },
    // 税务添加
    addelement3() {
      this.company8.placeAbode.push({
        SWstate3: '',
        IDNumber: '',
        IDNumber1: '',
        suibie: '',
        isSuiwuNO: false, // is 税务识别号
        suiwuQita: '',
        taxNumList: JSON.parse(JSON.stringify(this.company8.placeAbode[0].taxNumList)) // 页面上使用
      });
    },
    removeelement3() {
      this.company8.placeAbode.pop(this.company8.placeAbode);
    },
    //  个人股东--模态框--保存
    preserve1(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.company8.rukou.ifmodalBox = false;
          this.company8.rukou.ifbiaoge = true;
          this.company8.rukou.ifbottom = true;
          // 表格展示
          this.arrPush(1);
        }
      });
    },
    shut1() {
      const company8TS = JSON.parse(JSON.stringify(this.company8TS));
      this.company8.InstanceXinxi = company8TS.InstanceXinxi;
      this.company8.gerenGudong = company8TS.gerenGudong;
      this.company8.juzhudizhi = company8TS.juzhudizhi;
      this.company8.contact = company8TS.contact;
      this.company8.contact1 = company8TS.contact1;
      this.company8.identification = company8TS.identification;
      this.company8.placeAbode = company8TS.placeAbode;
      this.company8.rukou.ifmodalBox = false;
      this.company8.rukou.ifbottom = true;
    },
    //  公司股东--模态框--保存
    preserve2(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.company8.rukou.ifmodalBox1 = false;
          this.company8.rukou.ifbiaoge = true;
          this.company8.rukou.ifbottom = true;
          // 表格展示
          this.arrPush(2);
        }
      });
    },
    shut2() {
      const company8TS = JSON.parse(JSON.stringify(this.company8TS));
      this.company8.InstanceXinxi = company8TS.InstanceXinxi;
      this.company8.gerenGudong = company8TS.gerenGudong;
      this.company8.juzhudizhi = company8TS.juzhudizhi;
      this.company8.contact = company8TS.contact;
      this.company8.contact1 = company8TS.contact1;
      this.company8.identification = company8TS.identification;
      this.company8.placeAbode = company8TS.placeAbode;
      this.company8.rukou.ifmodalBox1 = false;
      this.company8.rukou.ifbottom = true;
    },
    // 提交页面跳转
    submitForm() {
      this.shitiTishi = false;
      if (this.company8.isSuishouType === '1') { // 企业
        if (this.company8.suoyouquanZongshu !== 0) {
          if (this.company8.suoyouquanZongshu <= 100) {
            this.PageCountSubmission();
          } else {
            this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership1')); // $i18n - 所有权总数必须 <= 100%
          }
        } else {
          this.PageCountSubmission();
        }
      } else if (this.company8.isSuishouType === '2') { // 合伙企业
        if (this.company8.tableTbady.length >= 2) {
          if (this.company8.suoyouquanZongshu === 100) {
            this.PageCountSubmission();
          } else {
            this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership2')); // $i18n - 所有权总数必须 = 100%
          }
        } else if (this.company8.tableTbady.length < 2) {
          this.shitiTishi = '2';
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership4')); // $i18n - 持有人必须 >= 2
        }
      } else if (this.company8.isSuishouType === '3') { // 无视实体
        if (this.company8.tableTbady.length === 1) {
          if (this.company8.suoyouquanZongshu === 100) {
            this.PageCountSubmission();
          } else {
            this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership2')); // $i18n - 所有权总数必须 = 100%
          }
        } else if (this.company8.tableTbady.length > 1) {
          this.shitiTishi = '1';
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.RegisteredOwner.hint.ownership3')); // $i18n - 持有人必须 = 1
        }
      }
    },
    PageCountSubmission() {
      this.$http({
        url: '/openAccount/company/saveEightPage',
        method: 'post',
        params: { accountId: this.$store.state.user.applyData.xinxi.id }
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/company9' });
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
    // 个人股东 法人股东 保存数据push到提交股东数组
    arrPush(gdType) {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      const companyRegisteredOwnerInfoModelList = [];
      let accountCoPersonal = {}; // 1.公司个人信息
      let accountCoAddressList = []; // 2.公司地址信息
      const accountCoContactList = []; // 3.公司联系信息
      let accountCoIdentity = {}; // 4.公司身份信息
      const accountCoTaxesList = []; // 5.公司税务居住地
      if (gdType === 1) {
        accountCoPersonal = { // 1.公司个人信息
          accountId,
          createdBy,
          createdDate,
          appellation: this.company8.gerenGudong.companyName,
          lastName: this.company8.gerenGudong.call,
          middleName: this.company8.gerenGudong.middle,
          firstName: this.company8.gerenGudong.surname,
          suffix: this.company8.gerenGudong.suffix,
          position: null,
          birthday: this.$verify.NowDate2(this.company8.gerenGudong.birth),
          birthCountry: this.company8.juzhudizhi.state1,
          userName: this.company8.gerenGudong.username,
          password: this.company8.gerenGudong.psword,
          email: this.company8.gerenGudong.email,
          prcentageOfOwnership: this.company8.gerenGudong.suoyouquanBi,
          nationality: this.company8.juzhudizhi.state1,
          personalType: this.company8.personalTypeG,
          isBusinessManager: false,
          isAmericanTaxpayer: this.company8.contact[0].isMeiguoNasuiren
        };
        accountCoAddressList = [ // 2.公司地址信息
          {
            accountId,
            createdBy,
            createdDate,
            address: this.company8.juzhudizhi.dizhi1 ? this.company8.juzhudizhi.dizhi + '-#-' + this.company8.juzhudizhi.dizhi1 : this.company8.juzhudizhi.dizhi,
            addressType: this.company8.addressType,
            city: this.company8.juzhudizhi.chengshi,
            country: this.company8.juzhudizhi.state1,
            otherCountry: this.company8.contact[0].isguojiButong ? this.company8.contact[0].guoji : null,
            otherBirthCountry: this.company8.contact[0].ischushengguoButong ? this.company8.contact[0].chushengguo : null,
            rovince: this.company8.juzhudizhi.province,
            zipCode: this.company8.juzhudizhi.youbian
          }
        ];
        for (let i = 0; i < this.company8.contact.length; i++) { // 3.公司联系信息
          accountCoContactList.push(
            {
              accountId,
              createdBy,
              createdDate,
              contactNumber: this.company8.contact[i].phoneNumber,
              contactNumberType: this.company8.contact[i].time,
              country: this.company8.contact[i].state2
            }
          );
        }
        accountCoIdentity = { // 4.公司身份信息
          accountId,
          createdBy,
          createdDate,
          idCardType: !this.company8.contact[0].isMeiguoNasuiren ? this.company8.identification.SFzhengjianleixing : null,
          idNumber: !this.company8.contact[0].isMeiguoNasuiren ? this.company8.identification.SFzhengjianhao : null,
          issuingCountry: !this.company8.contact[0].isMeiguoNasuiren ? this.company8.identification.SFsignGuojia : null
        };
        let noTaxIdentificationNumber = null;
        for (let i = 0; i < this.company8.placeAbode.length; i++) { // 5.公司税务居住地
          if (this.company8.placeAbode[i].isSuiwuNO) {
            if (this.company8.placeAbode[i].suibie === 'qita') {
              noTaxIdentificationNumber = this.company8.placeAbode[i].suiwuQita;
            } else {
              noTaxIdentificationNumber = this.company8.placeAbode[i].suibie;
            }
          }
          accountCoTaxesList.push(
            {
              accountId,
              createdBy,
              createdDate,
              differentRegistrationNumber: null,
              noTaxIdentificationNumber,
              taxResidenceCountry: this.company8.placeAbode[i].SWstate3,
              taxResidenceIdCardNumber: this.company8.placeAbode[i].IDNumber
            }
          );
        }
        companyRegisteredOwnerInfoModelList.push(
          {
            accountCoPersonal,
            accountCoAddressList,
            accountCoContactList,
            accountCoIdentity,
            accountCoTaxesList
          }
        );
      } else if (gdType === 2) {
        accountCoPersonal = { // 1.公司个人信息
          accountId,
          createdBy,
          createdDate,
          entityName: this.company8.InstanceXinxi.name,
          appellation: this.company8.gerenGudong.companyName,
          lastName: this.company8.gerenGudong.call,
          middleName: this.company8.gerenGudong.middle,
          firstName: this.company8.gerenGudong.surname,
          suffix: this.company8.gerenGudong.suffix,
          position: null,
          birthday: null,
          birthCountry: this.company8.juzhudizhi.state1,
          userName: this.company8.gerenGudong.username,
          password: this.company8.gerenGudong.psword,
          email: this.company8.gerenGudong.email,
          prcentageOfOwnership: this.company8.InstanceXinxi.percentage,
          nationality: this.company8.juzhudizhi.state1,
          personalType: this.company8.personalTypeF,
          isBusinessManager: false,
          isAmericanTaxpayer: false
        };
        accountCoAddressList = [ // 2.公司地址信息
          {
            accountId,
            createdBy,
            createdDate,
            address: this.company8.juzhudizhi.dizhi1 ? this.company8.juzhudizhi.dizhi + '-#-' + this.company8.juzhudizhi.dizhi1 : this.company8.juzhudizhi.dizhi,
            addressType: this.company8.addressType,
            city: this.company8.juzhudizhi.chengshi,
            country: this.company8.juzhudizhi.state1,
            otherCountry: this.company8.contact1[0].isguojiButong ? this.company8.contact1[0].guoji : null,
            otherBirthCountry: this.company8.contact1[0].ischushengguoButong ? this.company8.contact1[0].chushengguo : null,
            rovince: this.company8.juzhudizhi.province,
            zipCode: this.company8.juzhudizhi.youbian
          }
        ];
        for (let i = 0; i < this.company8.contact1.length; i++) { // 3.公司联系信息
          accountCoContactList.push(
            {
              accountId,
              createdBy,
              createdDate,
              contactNumber: this.company8.contact1[i].phoneNumber,
              contactNumberType: this.company8.contact1[i].time,
              country: this.company8.contact1[i].state2,
              foundingCountry: this.company8.contact1[0].guojia,
              status: this.company8.contact1[0].isGongkaiJiaoyi,
              exchange: this.company8.contact1[0].isGongkaiJiaoyi ? this.company8.contact1[0].jiaoyisuo : null,
              code: this.company8.contact1[0].isGongkaiJiaoyi ? this.company8.contact1[0].daima : null
            }
          );
        }
        accountCoIdentity = { // 4.公司身份信息
          accountId,
          createdBy,
          createdDate,
          idCardType: null,
          idNumber: null,
          issuingCountry: null
        };
        let noTaxIdentificationNumber = null;
        for (let i = 0; i < this.company8.placeAbode.length; i++) { // 5.公司税务居住地
          if (this.company8.placeAbode[i].isSuiwuNO) {
            if (this.company8.placeAbode[i].suibie === 'qita') {
              noTaxIdentificationNumber = this.company8.placeAbode[i].suiwuQita;
            } else {
              noTaxIdentificationNumber = this.company8.placeAbode[i].suibie;
            }
          }
          accountCoTaxesList.push(
            {
              accountId,
              createdBy,
              createdDate,
              differentRegistrationNumber: null,
              noTaxIdentificationNumber,
              taxResidenceCountry: this.company8.placeAbode[i].SWstate3,
              taxResidenceIdCardNumber: this.company8.placeAbode[i].IDNumber
            }
          );
        }
        companyRegisteredOwnerInfoModelList.push(
          {
            accountCoPersonal,
            accountCoAddressList,
            accountCoContactList,
            accountCoIdentity,
            accountCoTaxesList
          }
        );
      }
      this.submitFormData(companyRegisteredOwnerInfoModelList);
      const company8TS = JSON.parse(JSON.stringify(this.company8TS));
      this.company8.InstanceXinxi = company8TS.InstanceXinxi;
      this.company8.gerenGudong = company8TS.gerenGudong;
      this.company8.juzhudizhi = company8TS.juzhudizhi;
      this.company8.contact = company8TS.contact;
      this.company8.contact1 = company8TS.contact1;
      this.company8.identification = company8TS.identification;
      this.company8.placeAbode = company8TS.placeAbode;
    },
    // 表单数据提交
    submitFormData(companyRegisteredOwnerInfoModelList) {
      const data = {
        companyRegisteredOwnerInfoModelList
      };
      this.$http({
        url: '/openAccount/company/saveEight',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$message.success(this.$i18n.t('registerOpenAccount.common.formPrompt.addWin')); // $i18n - 添加成功
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
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/company7' });
    }
  }
};
</script>
<style scoped>

.jubuTianjia > div {
  float: left;
  width: 48%;
  height: 40px;
  background-color: rgb(255, 255, 255);
  border: 1px solid rgb(235, 235, 235);
  border-radius: 5px;
  line-height: 40px;
  text-align: center;
  color: #666;
}
.jubuTianjia > div:nth-child(2) {
  float: right;
}
.jubuTianjia > div:hover {
  background-color: rgb(247, 247, 247);
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent {
    margin-top: 100px;
}
.shitiTishi {
  padding: 20px;
  line-height:20px;
  border: 1px solid rgb(184, 184, 184);
  background-color: rgb(255, 235, 235);
  margin-top: 30px;
  color: rgb(221, 87, 74);
}
</style>

