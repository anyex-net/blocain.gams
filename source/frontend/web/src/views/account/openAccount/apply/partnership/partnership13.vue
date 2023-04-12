<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(11/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership13"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="partnership13"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- main-top -->
              <div class="formNapeCRS">
                <h3>{{ $t('registerOpenAccount.openAccount.common.crs.par.top.title') + '#' + pageIndex }}</h3> <!-- $i18n - 共同汇报标准（CRS）控权人自我证明表格（CRS-CP） -->
                <div class="topContentCRS">
                  <h4>{{ $t('registerOpenAccount.openAccount.common.crs.par.top.describe1') }}</h4> <!-- $i18n - 客户须知：填写此表格前 ，请先阅读本节。 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.crs.par.top.describe2') }}</p> <!-- $i18n - 作为财务机构，我们是不允许给予客户税务咨询 -->
                  <p class="pCRS2">
                    {{ $t('registerOpenAccount.openAccount.common.crs.par.top.describe3') }}
                  </p> <!-- $i18n - 如果您对此表格或您的税务居民身份定义有任何 ... -->
                  <p class="pCRS2 wangzhiCRS">
                    (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                  </p>
                  <!-- <p class="pCRS3">
                    请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。
                  </!-->
                  <p class="pCRS3">
                    {{ $t('registerOpenAccount.openAccount.common.crs.par.top.describe5') }}
                  </p> <!-- $i18n - 您可能会被要求提供更多证明文件，以核实此表格上之内容。 -->
                  <p class="pCRS3 lineHeightBD22">
                    {{ $t('registerOpenAccount.openAccount.common.crs.par.top.describe6') }}
                  </p> <!-- $i18n - 这是由账户持有人向申报财务机构提供的自我 ... -->
                  <p class="pCRS3">
                    {{ $t('registerOpenAccount.openAccount.common.crs.par.top.describe7') }}
                  </p> <!-- $i18n - 除不适用或特别注明外，必须填写这份表格所有部分。在栏 ... -->
                </div>
              </div>
              <!-- main-content1 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.par.part1.title') }}</div> <!-- $i18n - 第一部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.par.part1.describets') }}</div> <!-- $i18n - 控权人的身份识辨资料 -->
                </div>
                <div class="ContentCRS">
                  <h5>{{ $t('registerOpenAccount.openAccount.common.crs.par.part1.minTitle1ts') }}</h5> <!-- $i18n - 1、控权人的姓名 -->
                  <el-form-item prop="one.personal.call">
                    <el-select v-model="partnership13.one.personal.call" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                      <dictionary dict-code="appellationType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.lastName">
                    <el-input v-model="partnership13.one.personal.lastName" maxlength="30" :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                  </el-form-item>
                  <el-form-item prop="one.personal.firstName">
                    <el-input v-model="partnership13.one.personal.firstName" maxlength="30" :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                  </el-form-item>
                  <el-form-item prop="one.personal.identityType">
                    <el-select v-model="partnership13.one.personal.identityType" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"> <!-- $i18n - 身份认证类型 -->
                      <dictionary dict-code="cardType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.papersNum">
                    <el-input v-model="partnership13.one.personal.papersNum" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                  </el-form-item>
                  <el-form-item prop="one.personal.dateBirth">
                    <el-date-picker
                      v-model="partnership13.one.personal.dateBirth"
                      type="date"
                      :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                    /> <!-- $i18n - 出生日期 -->
                  </el-form-item>
                </div>
                <div class="ContentCRS">
                  <h5>{{ $t('registerOpenAccount.openAccount.common.crs.par.part1.minTitle2') }}</h5> <!-- $i18n - 2、现时住址 -->
                  <el-form-item prop="one.location.reality.state">
                    <el-select v-model="partnership13.one.location.reality.state" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site">
                    <el-input v-model="partnership13.one.location.reality.site" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')" /> <!-- $i18n - 地址（如果空间不足请转下行） -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site1">
                    <el-input v-model="partnership13.one.location.reality.site1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.city">
                    <el-input v-model="partnership13.one.location.reality.city" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.province">
                    <el-input v-model="partnership13.one.location.reality.province" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.postalCode">
                    <el-input v-model="partnership13.one.location.reality.postalCode" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li class="isyes" :class="{ dianji: partnership13.one.location.ifcommunicate,fade:true}" @click="partnership13.one.location.ifcommunicate=true">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li class="isno" :class="{ dianji: !partnership13.one.location.ifcommunicate,fade:true}" @click="partnership13.one.location.ifcommunicate=false">{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.isMailingAddress2') }}</li> <!-- $i18n - 您的通讯地址是否与上方所填地址不同 -->
                    </ul>
                  </div>
                  <div v-if="partnership13.one.location.ifcommunicate">
                    <el-form-item prop="one.location.communicate.state">
                      <el-select v-model="partnership13.one.location.communicate.state" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site">
                      <el-input v-model="partnership13.one.location.communicate.site" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')" /> <!-- $i18n - 出生日地址（如果空间不足请转下行）期 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site1">
                      <el-input v-model="partnership13.one.location.communicate.site1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.city">
                      <el-input v-model="partnership13.one.location.communicate.city" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.province">
                      <el-input v-model="partnership13.one.location.communicate.province" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.postalCode">
                      <el-input v-model="partnership13.one.location.communicate.postalCode" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- main-content2 -->
              <div class="formNapeCRS ">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.par.part2.title') }}</div> <!-- $i18n - 第二部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.par.part2.describe') }}</div> <!-- $i18n - 阁下为控权人的实体账户持有人 -->
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">{{ $t('registerOpenAccount.openAccount.common.crs.par.part2.describe1') }} <!-- $i18n - 填写阁下为控权人的实体账户持有人的名称。 -->
                  </h5>
                  <el-form-item
                    v-for="(item,index) in partnership13.two.TaxNumber"
                    :key="index"
                    :prop="'two.TaxNumber.'+index+'.name'"
                    :rules="rules.name"
                  >
                    <el-input v-model="item.name" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.crs.par.part2.describe2')" /> <!-- $i18n - 实体账户持有人的名称（公司名） -->
                  </el-form-item>
                  <div class="addBtn" style="margin-top:0px;">
                    <div v-show="partnership13.two.TaxNumber.length !== 5" class="add" @click="addElement(partnership13.two.TaxNumber,1)">{{ $t('registerOpenAccount.openAccount.common.crs.par.part2.addName') }}</div> <!-- $i18n - 添加名称 -->
                    <div v-show="partnership13.two.TaxNumber.length !== 1" class="cancel" @click="removeElement(partnership13.two.TaxNumber,1)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                </div>
              </div>
              <!-- main-content3 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.par.part3.title') }}</div> <!-- $i18n - 第三部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.par.part3.describe') }}</div> <!-- $i18n - 居留司法管辖区及税务编号或具有等同 ... -->
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">
                    {{ $t('registerOpenAccount.openAccount.common.crs.par.part3.describe1') }}
                  </h5> <!-- $i18n - 请提供以下数据，列明(i)账户持有人的居留司法管 ... -->
                  <!-- <li class="C9">如账户持有人的居留司法管辖区超过三个，请另纸填写。</li> -->
                  <li class="C9">{{ $t('registerOpenAccount.openAccount.common.crs.par.part3.describe2') }}</li> <!-- $i18n - 如账户持有人是香港税务居民，税务编号是账户持有人的香港身份证号码。 -->
                  <div v-for="(item,index) in partnership13.three.Tex" :key="index">
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li class="isyes" :class="{ dianji: item.ifTaxNumber,fade:true}" @click="item.ifTaxNumber=true">{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        </transition>
                        <transition name="fade">
                          <li class="isno" :class="{ dianji: !item.ifTaxNumber,fade:true}" @click="item.ifTaxNumber=false">{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        </transition>
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber1') }}</li> <!-- $i18n - 您是否有税务编号？ -->
                      </ul>
                    </div>
                    <div v-if="item.ifTaxNumber" style="margin-bottom:10px;">
                      <el-form-item
                        style="margin-bottom:0px;"
                        :prop="'three.Tex.'+index+'.TaxinputT'"
                        :rules="rules.TaxinputT"
                      >
                        <el-input v-model="item.TaxinputT" maxlength="30" placeholder />
                      </el-form-item>
                    </div>
                    <div v-if="!item.ifTaxNumber">
                      <el-form-item
                        id="radioInline"
                        style="margin-bottom:0px;"
                        :prop="'three.Tex.'+index+'.TaxNumberT'"
                        :rules="rules.TaxNumberT"
                      >
                        <el-radio-group v-model="item.TaxNumberT">
                          <div v-for="(item1,index1) in item.TaxNumberTlist" :key="index1">
                            <el-radio :label="item1.dictValue" @change="item.TwoIfShow= index1 === 1? true:false">{{ item1.dictDest }}</el-radio>
                            <br>
                            <div v-if="index1 === 1&&item.TwoIfShow ===true" style="margin-bottom:15px;">
                              <el-form-item
                                style="margin-bottom:0px;margin-left:28px;"
                                :prop="'three.Tex.'+index+'.TaxinputF'"
                                :rules="rules.TaxinputF"
                              >
                                <el-input v-model="item.TaxinputF" maxlength="30" placeholder />
                              </el-form-item>
                            </div>
                          </div>

                        </el-radio-group>
                      </el-form-item>
                    </div>
                  </div>
                  <div v-if="false" class="addBtn" style="margin-top:0px;">
                    <div class="add" @click="addElement(partnership13.three.Tex,2)">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加其他税务居住地 -->
                    <div class="cancel" @click="removeElement(partnership13.three.Tex)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                </div>
              </div>
              <!-- main-content4 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.par.part4.title') }}</div> <!-- $i18n - 第四部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.par.part4.describe') }}</div> <!-- $i18n - 控权人类别 -->
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">
                    {{ $t('registerOpenAccount.openAccount.common.crs.par.part4.describe1') }} <!-- $i18n - 就第二部份所载的每个实体，请在适当的方格填上剔号，指出控权人就每个实体所属的控权人类别。 -->
                  </h5>
                  <!-- <li class="C9">{{ $t('registerOpenAccount.openAccount.common.crs.par.part4.describe2') }}</li> $i18n - 如账户持有人的居留司法管辖区超过三个，请另纸填写。 -->
                  <!-- <li class="C9"> -->
                  <!-- {{ $t('registerOpenAccount.openAccount.common.crs.par.part4.describe3') }} $i18n - 如账户持有人是香港税务居民，税务编号是账户持有人的香港身份证号码。 -->
                  <!-- </li> -->
                  <div v-for="(item,index) in partnership13.four.entityList" :key="index">
                    <h5 v-if="partnership13.two.TaxNumber[index].name" style="margin:18px 0px">
                      {{ `${$t('registerOpenAccount.openAccount.common.crs.par.part4.entity')} ${partnership13.two.TaxNumber[index].name}` }}
                    </h5> <!-- $i18n - 实体 -->
                    <el-form-item v-if="partnership13.two.TaxNumber[index].name!==''" id="radioInline" :prop="'four.entityList.'+index+'.entityRadio1'" :rules="rules.entityRadio1">
                      <el-radio-group v-model="item.entityRadio1">
                        <el-radio :label="partnership13.four.RtitleList[0].dictValue" @change="item.ifCheck1='1'">{{ partnership13.four.RtitleList[0].dictDest }}</el-radio>
                        <el-form-item v-if="item.ifCheck1==='1'" id="radioInline" class="el-form-itemBT12" :prop="'four.entityList.'+index+'.legalCheck1'" :rules="rules.legalCheck1">
                          <el-radio-group v-model="item.legalCheck1">
                            <div v-for="(item1,index1) in partnership13.four.legalList" :key="index1">
                              <el-radio :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-radio>
                            </div>
                          </el-radio-group>
                        </el-form-item>
                        <br>
                        <el-radio :label="partnership13.four.RtitleList[1].dictValue" @change="item.ifCheck1='2'">{{ partnership13.four.RtitleList[1].dictDest }}</el-radio>
                        <el-form-item v-if="item.ifCheck1==='2'" id="radioInline" class="el-form-itemBT12" :prop="'four.entityList.'+index+'.trustCheck1'" :rules="rules.trustCheck1">
                          <el-radio-group v-model="item.trustCheck1">
                            <div v-for="(item1,index1) in partnership13.four.trustList" :key="index1">
                              <el-radio :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-radio>
                            </div>
                          </el-radio-group>
                        </el-form-item>
                        <br>
                        <el-radio :label="partnership13.four.RtitleList[2].dictValue" @change="item.ifCheck1='3'">{{ partnership13.four.RtitleList[2].dictDest }}</el-radio>
                        <el-form-item v-if="item.ifCheck1==='3'" id="radioInline" class="el-form-itemBT12" :prop="'four.entityList.'+index+'.lawCheck1'" :rules="rules.lawCheck1">
                          <el-radio-group v-model="item.lawCheck1">
                            <div v-for="(item1,index1) in partnership13.four.lawList" :key="index1">
                              <el-radio :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-radio>
                            </div>
                          </el-radio-group>
                        </el-form-item>
                        <br>
                      </el-radio-group>
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- main-content5 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.par.part5.title') }}</div> <!-- $i18n - 第五部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part5.describe') }}</div> <!-- $i18n - 声明及签署 -->
                </div>
                <div class="ContentCRS ContentCRSFive">
                  <p>
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe1') }}
                  </p> <!-- $i18n - 本人声明就本人所知所信，本表格内所填报 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfStatement">
                    <el-radio-group v-model="partnership13.statement.selfStatement">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe2') }}
                  </p> <!-- $i18n - 本人知悉及同意，财务机构可根据《税务条例》（第1 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfKnow">
                    <el-radio-group v-model="partnership13.statement.selfKnow">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe3') }}
                  </p> <!-- $i18n - 本人承诺，如情况有所改变，以致影响本表格第1部 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfPromiset">
                    <el-radio-group v-model="partnership13.statement.selfPromiset">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe4') }}
                  </p> <!-- $i18n - 本人同意所有税务表格均用过电子形式而非纸质 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfAgree">
                    <el-radio-group v-model="partnership13.statement.selfAgree">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <!-- <h5 style="margin-top: 10px;" class="AgreementAgreed"> 本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。
                  </h5> -->
                  <div class="signature">
                    <p class="Stitle">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</p> <!-- $i18n - 签名 -->
                    <p class="Sdescribe">
                      {{ $t('registerOpenAccount.openAccount.common.signature.signatureDB') }}
                    </p> <!-- $i18n - 输入签名并通过网络发送即代表您(i)确认该表格内的 ... -->
                    <ul>
                      <li>{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</li> <!-- $i18n - 账户名称: -->
                      <li>{{ partnership13.statement.accountTitle1 }}</li>
                    </ul>

                    <ul>
                      <li style="margin-right: 48px;">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</li> <!-- $i18n - 日期: -->
                      <li>{{ partnership13.statement.time }}</li>
                    </ul>
                    <p class="shili">{{ partnership13.statement.accountTitle2 }}</p>
                    <ul class="inputQM">
                      <li>{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                      <li>
                        <el-form-item prop="statement.accountTitle3">
                          <el-input v-model="partnership13.statement.accountTitle3" maxlength="128" />
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
                    <el-button class="right-btn BTbtn" plain @click="submitForm('partnership13')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
    var twoLeibie5 = (rule, value, callback) => {
      const mate = value === true;
      this.$verify.Signature(rule, value, mate, callback);
    };
    // 签名验证
    var Signature = (rule, value, callback) => {
      const mate = this.partnership13.statement.accountTitle2 === value;
      this.$verify.Signature(rule, value, mate, callback);
    };
    // 出生日期
    var validateBirth = (rule, value, callback) => {
      this.$verify.Birth(rule, value, callback);
    };
    return {
      lang: this.$Cookies.get('locale'),
      pageIndex: 0,
      isTokenHint: false,
      partnership13: {
        controllerID: '',
        controllerIDList: [],
        one: {
          personal: {
            call: '',
            lastName: '',
            firstName: '',
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
              name
            }
          ]
        },
        three: {
          Tex: [
            {
              ifTaxNumber: true,
              TaxinputT: '',
              TaxinputF: '',
              TaxNumberT: '1',
              TaxNumberTlist: [],
              TwoIfShow: false
            }
          ]
        },
        four: {
          entityList: [
            {
              ifDisabled: null,
              ifCheck1: null,
              entityRadio1: '',
              legalCheck1: '',
              trustCheck1: '',
              lawCheck1: ''
            }
          ],
          RtitleList: [
            {
              dictDest: '',
              dictValue: 'legalPerson'
            },
            {
              dictDest: '',
              dictValue: 'controllingPersonType'
            },
            {
              dictDest: '',
              dictValue: 'Legal Arrangementother than Trust'
            }
          ], // 单选框list
          legalList: [], // 复选框list
          trustList: [],
          lawList: []
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
      newValue: {
        accountParAddressList: null,
        accountParCrsControl: null,
        accountParCrsControlNumList: null
      },
      rules: {
        //  1.账户持有人
        'one.personal.call': [{ validator: notChinese, trigger: 'change' }],
        'one.personal.lastName': [{ validator: notChinese, trigger: 'blur' }],
        'one.personal.firstName': [{ validator: notChinese, trigger: 'blur' }],
        'one.personal.identityType': [{ validator: notChinese, trigger: 'change' }],
        'one.personal.papersNum': [{ validator: notChinese, trigger: 'blur' }],
        'one.personal.dateBirth': [{ validator: validateBirth, trigger: 'change' }],

        // 2.1、现时住址
        'one.location.reality.site': [{ validator: notChinese, trigger: 'blur' }],
        'one.location.reality.site1': [{ validator: notChinese1, trigger: 'blur' }],
        'one.location.reality.city': [{ validator: notChinese, trigger: 'blur' }],
        'one.location.reality.province': [{ validator: notChinese, trigger: 'blur' }],
        'one.location.reality.state': [{ validator: notChinese, trigger: 'change' }],
        'one.location.reality.postalCode': [{ validator: notChinese, trigger: 'blur' }],
        // 2.2、通讯地址
        'one.location.communicate.site': [{ validator: notChinese, trigger: 'blur' }],
        'one.location.communicate.site1': [{ validator: notChinese1, trigger: 'blur' }],
        'one.location.communicate.city': [{ validator: notChinese, trigger: 'blur' }],
        'one.location.communicate.province': [{ validator: notChinese, trigger: 'blur' }],
        'one.location.communicate.state': [{ validator: notChinese, trigger: 'change' }],
        'one.location.communicate.postalCode': [{ validator: notChinese, trigger: 'blur' }],

        // 3.第二部分
        name: [{ validator: notChinese, trigger: 'blur' }],

        // 4.第三部分
        TaxinputT: [{ validator: notChinese, trigger: 'blur' }],
        TaxNumberT: [{ validator: notChinese, trigger: 'change' }],
        TaxinputF: [{ validator: notChinese, trigger: 'blur' }],

        // 4.第四部分
        entityRadio1: [{ required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'), trigger: 'change' }], // $i18n - 请选择
        legalCheck1: [{ required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'), trigger: 'change' }], // $i18n - 请选择
        trustCheck1: [{ required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'), trigger: 'change' }], // $i18n - 请选择
        lawCheck1: [{ required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'), trigger: 'change' }], // $i18n - 请选择
        // 6,声明及签署
        'statement.selfStatement': [
          { validator: twoLeibie5, message: this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint2'), trigger: 'change' } // $i18n - 请选择是才能继续操作
        ],
        'statement.selfKnow': [
          { validator: twoLeibie5, message: this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint2'), trigger: 'change' } // $i18n - 请选择是才能继续操作
        ],
        'statement.selfPromiset': [
          { validator: twoLeibie5, message: this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint2'), trigger: 'change' } // $i18n - 请选择是才能继续操作
        ],
        'statement.selfAgree': [
          { validator: twoLeibie5, message: this.$i18n.t('registerOpenAccount.common.formPrompt.confirmHint2'), trigger: 'change' } // $i18n - 请选择是才能继续操作
        ],
        'statement.accountTitle3': [{ validator: Signature, trigger: 'blur' }]
      }
    };
  },
  created() {
    this.getSignature();
    this.accountParCrsEntity();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    accountParCrsEntity() { // 获取当前控权人id
      this.$http({
        url: '/account/accountParCrsEntity/findCrsEntity',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.accountParCrsEntityContrList(res.object[0].crsCpStatusId);
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
    accountParCrsEntityContrList(id) { // 获取当前控权人list
      this.$http({
        url: '/account/accountParCrsEntityContr/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership13.controllerIDList = res.object;
              const session = JSON.parse(JSON.stringify(this.pageStatus('get')));
              session.crsI = session.crsI ? session.crsI : id;
              let index = 0;
              if (session.crsI === '') {
                index = 0;
              } else {
                for (let i = 0; i < res.object.length; i++) {
                  if (res.object[i].id === session.crsI) {
                    index = i;
                    if (session.pageStatus === 'present') {
                      index = i;
                    } else if (session.pageStatus === 'previous') {
                      index--;
                    } else if (session.pageStatus === 'next') {
                      index++;
                    }
                  }
                }
              }
              this.pageIndex = 0;
              this.pageIndex = index + 1;
              this.partnership13.controllerID = res.object[index].id;
              this.partnership13.one.personal.firstName = res.object[index].firstName;
              this.partnership13.one.personal.lastName = res.object[index].lastName;

              // 字典表
              this.controllingPersonType();
              this.taxNumberType();

              this.pageStatus('set', 'present', false);
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
    accountParAddressList() {
      this.$http({
        url: '/account/accountParAddress/findCrsAddress',
        method: 'get',
        params: { lang: this.lang, parCrsEntityContrId: this.partnership13.controllerID, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // -现住
              this.newValue.accountParAddressList = JSON.parse(JSON.stringify(res.object));
              if (res.object[0].address.indexOf('-#-') !== -1) {
                const address = res.object[0].address.split('-#-');
                this.partnership13.one.location.reality.site = address[0];
                this.partnership13.one.location.reality.site1 = address[1];
              } else {
                this.partnership13.one.location.reality.site = res.object[0].address;
              }
              this.partnership13.one.location.reality.city = res.object[0].city;
              this.partnership13.one.location.reality.province = res.object[0].rovince;
              this.partnership13.one.location.reality.state = res.object[0].country;
              this.partnership13.one.location.reality.postalCode = res.object[0].zipCode;
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
    accountParCrsControl() {
      this.$http({
        url: '/account/accountParCrsControl/findByAccountId',
        method: 'get',
        params: { lang: this.lang, parCrsEntityContrId: this.partnership13.controllerID, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.accountParCrsControl = JSON.parse(JSON.stringify(res.object[0]));
              // 第一部分
              // -账户持有人个人信息
              this.partnership13.one.personal.call = res.object[0].appellation;
              // this.partnership13.one.personal.lastName = res.object[0].lastName;
              // this.partnership13.one.personal.firstName = res.object[0].firstName;
              this.partnership13.one.personal.identityType = res.object[0].idCardType;
              this.partnership13.one.personal.papersNum = res.object[0].idNumber;
              this.partnership13.one.personal.dateBirth = new Date(res.object[0].birthday);

              // -邮寄
              this.partnership13.one.location.ifcommunicate = res.object[0].otherAddressStatus;
              if (res.object[0].otherAddressStatus) {
                if (res.object[0].otherAddresses.indexOf('-#-') !== -1) {
                  const address = res.object[0].otherAddresses.split('-#-');
                  this.partnership13.one.location.communicate.site = address[0];
                  this.partnership13.one.location.communicate.site1 = address[1];
                } else {
                  this.partnership13.one.location.communicate.site = res.object[0].otherAddresses;
                }
                this.partnership13.one.location.communicate.city = res.object[0].otherCity;
                this.partnership13.one.location.communicate.province = res.object[0].otherProvince;
                this.partnership13.one.location.communicate.state = res.object[0].otherCountry;
                this.partnership13.one.location.communicate.postalCode = res.object[0].otherZipCode;
              }

              // 第三部分
              if (res.object[0].noTaxNumber === '') {
                this.partnership13.three.Tex[0].TaxinputT = res.object[0].yesTaxNumber;
              } else {
                this.partnership13.three.Tex[0].ifTaxNumber = false;
                if (res.object[0].noTaxNumber === 'The controller cannot obtain the tax number. If you choose this reason, please explain below why the controlling party can not obtain the tax number.') {
                  this.partnership13.three.Tex[0].TwoIfShow = true;
                  this.partnership13.three.Tex[0].TaxinputF = res.object[0].reason;
                } else {
                  this.partnership13.three.Tex[0].TwoIfShow = false;
                  this.partnership13.three.Tex[0].TaxinputF = '';
                }
                this.partnership13.three.Tex[0].TaxNumberT = res.object[0].noTaxNumber;
              }
              // 第五部分
              this.partnership13.statement.selfStatement = res.object[0].authenticData;
              this.partnership13.statement.selfKnow = res.object[0].agreeTaxOrdinance;
              this.partnership13.statement.selfPromiset = res.object[0].provenItself;
              this.partnership13.statement.selfAgree = res.object[0].consent;
              this.partnership13.statement.time = this.$verify.NowDate3();
              this.partnership13.statement.accountTitle3 = res.object[0].signature;
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
    accountParCrsControlNumList() {
      this.$http({
        url: '/account/accountParCrsControlNum/findByAccountId',
        method: 'get',
        params: { lang: this.lang, parCrsEntityContrId: this.partnership13.controllerID, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.accountCoCrsControlNumList = res.object;
              const possessorArr = [];
              const entityArr = [];
              for (let i = 0; i < res.object.length; i++) {
                // 第二部分
                const possessorObj = {
                  name: null
                };
                possessorObj.name = res.object[i].entityAccountName;
                possessorArr.push(JSON.parse(JSON.stringify(possessorObj)));

                // 第四部分
                const entityObj = {
                  ifDisabled: null,
                  ifCheck1: null,
                  entityRadio1: '',
                  legalCheck1: '',
                  trustCheck1: '',
                  lawCheck1: ''
                };
                entityObj.entityRadio1 = res.object[i].entityCategory;

                if (res.object[i].entityCategory === 'legalPerson') {
                  entityObj.legalCheck1 = res.object[i].controlCategory;
                  entityObj.ifCheck1 = '1';
                } else if (res.object[i].entityCategory === 'trust') {
                  entityObj.trustCheck1 = res.object[i].controlCategory;
                  entityObj.ifCheck1 = '2';
                } else if (res.object[i].entityCategory === 'Legal Arrangementother than Trust') {
                  entityObj.lawCheck1 = res.object[i].controlCategory;
                  entityObj.ifCheck1 = '3';
                }

                entityArr.push(JSON.parse(JSON.stringify(entityObj)));
              }

              this.partnership13.two.TaxNumber = JSON.parse(JSON.stringify(possessorArr));
              this.partnership13.four.entityList = JSON.parse(JSON.stringify(entityArr));
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
    // 声明 签名
    getSignature() {
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: 'authorizedPerson', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership13.statement.accountTitle1 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership13.statement.time = this.$verify.NowDate3();
              this.partnership13.statement.accountTitle2 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership13.statement.accountTitle3 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
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
    // 控权人
    controllingPersonType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership13.four.RtitleList = JSON.parse(JSON.stringify(res.object));
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_1();
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 控权人-子
    controllingPersonType_1() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_1', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership13.four.legalList = JSON.parse(JSON.stringify(res.object));
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_2();
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 控权人-子
    controllingPersonType_2() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_2', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership13.four.trustList = JSON.parse(JSON.stringify(res.object));
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_3();
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 控权人-子
    controllingPersonType_3() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_3', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership13.four.lawList = JSON.parse(JSON.stringify(res.object));
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
          this.accountParAddressList();
          this.accountParCrsControl();
          this.accountParCrsControlNumList();
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 税收编号
    taxNumberType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership13.three.Tex[0].TaxNumberTlist = res.object;
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
      if (index === 1) { // 第二部分
        obj = {
          name: ''
        };

        arr.push(JSON.parse(JSON.stringify(obj)));
        this.partnership13.four.entityList.push( // 根据第二部分控权人 添加第四部分数组
          {
            ifDisabled: null,
            ifCheck1: null,
            entityRadio1: '',
            legalCheck1: '',
            trustCheck1: '',
            lawCheck1: ''
          }
        );
      } else if (index === 2) {
        obj = {
          ifTaxNumber: true,
          TaxinputT: '',
          TaxinputF: '',
          TaxNumberT: '1',
          TaxNumberTlist: this.partnership13.three.Tex[0].TaxNumberTlist,
          TwoIfShow: false
        };
        if (arr.length >= 2) {
          return false;
        } else {
          arr.push(JSON.parse(JSON.stringify(obj)));
        }
      }
    },
    // 删除元素
    // arr 需要操作的数组
    removeElement(arr, index) {
      if (arr.length === 1) {
        return false;
      } else {
        if (index === 1) { // 根据第二部分控权人 删除第四部分数组
          this.partnership13.four.entityList.pop();
        }
        arr.pop();
      }
    },
    // 保存,下一步
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.formError1')); // $i18n - 验证未通过
          return false;
        }
      });
    },
    // 提交表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      const parCrsEntityContrId = this.partnership13.controllerID;

      let entityAccountName = ''; // 第二部分 控权人名称
      for (let i = 0; i < this.partnership13.two.TaxNumber.length; i++) {
        if (i === 0) {
          entityAccountName = this.partnership13.two.TaxNumber[i].name;
        } else {
          entityAccountName = entityAccountName + ',' + this.partnership13.two.TaxNumber[i].name;
        }
      }
      let yesTaxNumber = null; // 第三部分 税务编号
      let noTaxNumber = null; // 第三部分 税务编号
      let reason = null;
      for (let i = 0; i < this.partnership13.three.Tex.length; i++) {
        if (this.partnership13.three.Tex[i].ifTaxNumber === true) {
          yesTaxNumber = this.partnership13.three.Tex[i].TaxinputT;
        } else {
          noTaxNumber = this.partnership13.three.Tex[i].TaxNumberT;
          reason = this.partnership13.three.Tex[i].TaxinputF;
        }
      }

      const accountParCrsControlNumList = [];
      let entityCategory = null;
      let controlCategory = null;
      let entityNumber = null;
      let entityAccountName1 = null;
      for (let i = 0; i < this.partnership13.four.entityList.length; i++) {
        const index = i + 1;
        entityNumber = `entity${index}`;
        entityCategory = this.partnership13.four.entityList[i].entityRadio1;
        if (this.partnership13.four.entityList[i].entityRadio1 === 'legalPerson') {
          controlCategory = this.partnership13.four.entityList[i].legalCheck1;
        } else if (this.partnership13.four.entityList[i].entityRadio1 === 'trust') {
          controlCategory = this.partnership13.four.entityList[i].trustCheck1;
        } else if (this.partnership13.four.entityList[i].entityRadio1 === 'Legal Arrangementother than Trust') {
          controlCategory = this.partnership13.four.entityList[i].lawCheck1;
        }
        entityAccountName1 = this.partnership13.two.TaxNumber[i].name;
        accountParCrsControlNumList.push(
          {
            accountId,
            parCrsEntityContrId,
            entityCategory,
            controlCategory,
            entityNumber,
            entityAccountName: entityAccountName1
          }
        );
      }
      const data = {
        accountParAddressList: null,
        accountParCrsControl: null,
        accountParCrsControlNumList: null
      };
      if (this.newValue.accountParAddressList === null) {
        data.accountParAddressList = [{
          accountId,
          parCrsEntityContrId,
          createdBy,
          createdDate,

          addressType: 'controllingPersonAddress',
          address: this.partnership13.one.location.reality.site1 ? this.partnership13.one.location.reality.site + '-#-' + this.partnership13.one.location.reality.site1 : this.partnership13.one.location.reality.site,
          city: this.partnership13.one.location.reality.city,
          rovince: this.partnership13.one.location.reality.province,
          country: this.partnership13.one.location.reality.state,
          zipCode: this.partnership13.one.location.reality.postalCode
        }];
      } else {
        this.newValue.accountParAddressList[0].address = this.partnership13.one.location.reality.site1 ? this.partnership13.one.location.reality.site + '-#-' + this.partnership13.one.location.reality.site1 : this.partnership13.one.location.reality.site;
        this.newValue.accountParAddressList[0].city = this.partnership13.one.location.reality.city;
        this.newValue.accountParAddressList[0].rovince = this.partnership13.one.location.reality.province;
        this.newValue.accountParAddressList[0].country = this.partnership13.one.location.reality.state;
        this.newValue.accountParAddressList[0].zipCode = this.partnership13.one.location.reality.postalCode;
        data.accountParAddressList = this.newValue.accountParAddressList;
      }
      if (this.newValue.accountParCrsControl === null) {
        data.accountParCrsControl = {
          accountId,
          parCrsEntityContrId,
          createdBy,
          createdDate,
          // -账户持有人个人信息
          appellation: this.partnership13.one.personal.call,
          lastName: this.partnership13.one.personal.lastName,
          firstName: this.partnership13.one.personal.firstName,
          idCardType: this.partnership13.one.personal.identityType,
          idNumber: this.partnership13.one.personal.papersNum,
          birthday: this.$verify.NowDate2(this.partnership13.one.personal.dateBirth),
          // 第一部分
          // -邮寄
          otherAddressStatus: this.partnership13.one.location.ifcommunicate,
          otherAddresses: this.partnership13.one.location.communicate.site1 ? this.partnership13.one.location.communicate.site + '-#-' + this.partnership13.one.location.communicate.site1 : this.partnership13.one.location.communicate.site,
          otherCity: this.partnership13.one.location.communicate.city,
          otherProvince: this.partnership13.one.location.communicate.province,
          otherCountry: this.partnership13.one.location.communicate.state,
          otherZipCode: this.partnership13.one.location.communicate.postalCode,
          // 第二部分
          entityAccountName,
          // 第三部分
          yesTaxNumber,
          noTaxNumber,
          reason,
          // 第五部分
          authenticData: this.partnership13.statement.selfStatement,
          agreeTaxOrdinance: this.partnership13.statement.selfKnow,
          provenItself: this.partnership13.statement.selfPromiset,
          consent: this.partnership13.statement.selfAgree,
          signatureDate: this.$verify.NowDate(this.partnership13.statement.time),
          signature: this.partnership13.statement.accountTitle3
        };
      } else {
        this.newValue.accountParCrsControl.appellation = this.partnership13.one.personal.call;
        this.newValue.accountParCrsControl.lastName = this.partnership13.one.personal.lastName;
        this.newValue.accountParCrsControl.firstName = this.partnership13.one.personal.firstName;
        this.newValue.accountParCrsControl.idCardType = this.partnership13.one.personal.identityType;
        this.newValue.accountParCrsControl.idNumber = this.partnership13.one.personal.papersNum;
        this.newValue.accountParCrsControl.birthday = this.$verify.NowDate2(this.partnership13.one.personal.dateBirth);

        this.newValue.accountParCrsControl.otherAddressStatus = this.partnership13.one.location.ifcommunicate;
        this.newValue.accountParCrsControl.otherAddresses = this.partnership13.one.location.communicate.site1 ? this.partnership13.one.location.communicate.site + '-#-' + this.partnership13.one.location.communicate.site1 : this.partnership13.one.location.communicate.site;
        this.newValue.accountParCrsControl.otherCity = this.partnership13.one.location.communicate.city;
        this.newValue.accountParCrsControl.otherProvince = this.partnership13.one.location.communicate.province;
        this.newValue.accountParCrsControl.otherCountry = this.partnership13.one.location.communicate.state;
        this.newValue.accountParCrsControl.otherZipCode = this.partnership13.one.location.communicate.postalCode;
        this.newValue.accountParCrsControl.entityAccountName = entityAccountName;
        this.newValue.accountParCrsControl.yesTaxNumber = yesTaxNumber;
        this.newValue.accountParCrsControl.noTaxNumber = noTaxNumber;
        this.newValue.accountParCrsControl.reason = reason;
        this.newValue.accountParCrsControl.authenticData = this.partnership13.statement.selfStatement;
        this.newValue.accountParCrsControl.agreeTaxOrdinance = this.partnership13.statement.selfKnow;
        this.newValue.accountParCrsControl.provenItself = this.partnership13.statement.selfPromiset;
        this.newValue.accountParCrsControl.consent = this.partnership13.statement.selfAgree;
        this.newValue.accountParCrsControl.signatureDate = this.$verify.NowDate(this.partnership13.statement.time);
        this.newValue.accountParCrsControl.signature = this.partnership13.statement.accountTitle3;
        data.accountParCrsControl = this.newValue.accountParCrsControl;
      }
      if (this.newValue.accountParCrsControlNumList === null) {
        data.accountParCrsControlNumList = accountParCrsControlNumList;
      } else {
        const index = this.newValue.accountParCrsControlNumList.length;
        for (let i = 0; i < accountParCrsControlNumList.length; i++) {
          if (accountParCrsControlNumList.length < index) {
            this.newValue.accountParCrsControlNumList.splice(accountParCrsControlNumList.length);
            if (i < index) {
              this.newValue.accountParCrsControlNumList[i].entityCategory = accountParCrsControlNumList[i].entityCategory;
              this.newValue.accountParCrsControlNumList[i].controlCategory = accountParCrsControlNumList[i].controlCategory;
              this.newValue.accountParCrsControlNumList[i].entityNumber = accountParCrsControlNumList[i].entityNumber;
              this.newValue.accountParCrsControlNumList[i].entityAccountName = accountParCrsControlNumList[i].entityAccountName;
            }
          } else {
            if (i < index) {
              this.newValue.accountParCrsControlNumList[i].entityCategory = accountParCrsControlNumList[i].entityCategory;
              this.newValue.accountParCrsControlNumList[i].controlCategory = accountParCrsControlNumList[i].controlCategory;
              this.newValue.accountParCrsControlNumList[i].entityNumber = accountParCrsControlNumList[i].entityNumber;
              this.newValue.accountParCrsControlNumList[i].entityAccountName = accountParCrsControlNumList[i].entityAccountName;
            } else {
              this.newValue.accountParCrsControlNumList.push(
                JSON.parse(JSON.stringify(accountParCrsControlNumList[i]))
              );
            }
          }
        }
        data.accountParCrsControlNumList = this.newValue.accountParCrsControlNumList;
      }
      this.$http({
        url: '/openAccount/partnerShip/saveThirteen',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            let indexTS = '';
            this.partnership13.controllerIDList.forEach((item, index) => {
              if (item.id === this.partnership13.controllerID) {
                indexTS = index;
              }
            });
            if (indexTS === this.partnership13.controllerIDList.length - 1) {
              this.pageStatus('set', 'present', false);
              this.$verify.routerPermissions({ path: '/openAccount/partnership14' });
            } else {
              this.pageStatus('set', 'next', true);
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
          this.$message.error(error);
        });
    },
    beforepage() {
      let indexTS = '';
      this.partnership13.controllerIDList.forEach((item, index) => {
        if (item.id === this.partnership13.controllerID) {
          indexTS = index;
        }
      });
      if (indexTS === 0) {
        this.pageStatus('set', 'present', false);
        this.$verify.routerPermissions({ path: '/openAccount/partnership12' });
      } else {
        this.pageStatus('set', 'previous', true);
      }
    },
    pageStatus(type = 'get', status = 'present', isReload = false) {
      if (type === 'get') {
        const session = {
          pageStatus: this.$store.state.user.openAccount.pageStatus || 'present',
          crsI: this.$store.state.user.openAccount.crsI || ''
        };
        return session;
      } else {
        this.$store.commit('user/SET_OPENACCOUNT', { pageStatus: status });
        this.$store.commit('user/SET_OPENACCOUNT', { crsI: this.partnership13.controllerID });
        if (isReload) {
          location.reload();
        }
      }
    }
  }
};
</script>

<style scoped>
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRS > li {
    list-style: none;
    color: #666;
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRS > li.C9 {
    list-style:disc;
    color: #999;
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRS .el-form-itemBT12 {
    margin-bottom: 6px;
    margin-left: 27px;
}

  #ZC-regiMain .el-form-item__error{
    color: #F56C6C;
    font-size: 12px;
    line-height: 19px;
    padding-top: 0px;
    position: static;
    top: 100%;
    left: 0;
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRSFive .el-radio {
  margin-bottom: 0px;
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRSFive .AgreementAgreed{
  margin-top: 11px;
}
/* #ZC-regiMain  .el-form-item__error {
  position: relative;
} */
</style>
