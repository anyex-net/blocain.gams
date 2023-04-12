<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(10/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership12"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="partnership12"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- main-top -->
              <div class="formNapeCRS">
                <h3>{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.title') }}</h3> <!-- $i18n - 共同汇报标准（CRS）实体自我证明表格 (CRS - E） -->
                <div class="topContentCRS">
                  <h4>{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.describe1') }}</h4> <!-- $i18n - 客户须知：填写此表格前 ，请先阅读本节。 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.describe2') }}</p> <!-- $i18n - 作为财务机构，我们是不允许给予客户税务咨询 。 -->
                  <p class="pCRS2">{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.describe3') }}</p> <!-- $i18n - 如果您对此表格或您的税务居民身份定义有 ... -->
                  <p class="pCRS2 wangzhiCRS">
                    (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                  </p>
                  <!-- <p class="pCRS3">请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。</p> -->
                  <p class="pCRS3">{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.describe5') }}</p> <!-- $i18n - 您可能会被要求提供更多证明文件，以核 ... -->
                  <p
                    class="pCRS3 lineHeightBD22"
                  >{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.describe6') }}</p> <!-- $i18n - 这是由账户持有人向申报财务机构提供的 ... -->
                  <p class="pCRS3">{{ $t('registerOpenAccount.openAccount.common.crs.firm.top.describe7') }}</p> <!-- $i18n - 除不适用或特别注明外，必须填写这份表格所有部 ... -->
                </div>
              </div>
              <!-- main-content1 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part1.title') }}</div> <!-- $i18n - 第一部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part1.describe') }}</div> <!-- $i18n - 实体账户持有人的身分识辨数据 -->
                </div>
                <div class="ContentCRS">
                  <h5>{{ $t('registerOpenAccount.openAccount.common.crs.firm.part1.minTitle1') }}</h5> <!-- $i18n - 1、客户数据 -->
                  <el-form-item prop="one.client.name">
                    <el-input v-model="partnership12.one.client.name" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.crs.firm.part1.describe1a')" /> <!-- $i18n - 实体或分支机构的法定名称 -->
                  </el-form-item>
                  <el-form-item prop="one.client.administer">
                    <el-input
                      v-model="partnership12.one.client.administer"
                      maxlength="30"
                      type="textarea"
                      :rows="2"
                      :placeholder="$t('registerOpenAccount.openAccount.common.crs.firm.part1.describe1b')"
                    /> <!-- $i18n - 实体成立为法团或设立所在的税务管辖区 -->
                  </el-form-item>
                  <el-form-item prop="one.client.ZCnumber">
                    <el-input v-model="partnership12.one.client.ZCnumber" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.crs.firm.part1.describe1c')" /> <!-- $i18n - 注册号码 -->
                  </el-form-item>
                </div>
                <div class="ContentCRS">
                  <h5>{{ $t('registerOpenAccount.openAccount.common.crs.firm.part1.minTitle2ts') }}</h5> <!-- $i18n - 2、现时营业地址 -->
                  <el-form-item prop="one.location.reality.state">
                    <el-select v-model="partnership12.one.location.reality.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site">
                    <el-input
                      v-model="partnership12.one.location.reality.site"
                      maxlength="64"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                      :disabled="true"
                    /> <!-- $i18n - 地址（如果空间不足请转下行） -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site1">
                    <el-input v-model="partnership12.one.location.reality.site1" maxlength="64" :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.city">
                    <el-input v-model="partnership12.one.location.reality.city" maxlength="30" :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.province">
                    <el-input v-model="partnership12.one.location.reality.province" maxlength="30" :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                  </el-form-item>
                  <el-form-item prop="one.location.reality.postalCode">
                    <el-input
                      v-model="partnership12.one.location.reality.postalCode"
                      maxlength="20"
                      :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')"
                      :disabled="true"
                    /> <!-- $i18n - 邮政编码 -->
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: partnership12.one.location.ifcommunicate,fade:true}"
                          @click="partnership12.one.location.ifcommunicate=true"
                        >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !partnership12.one.location.ifcommunicate,fade:true}"
                          @click="partnership12.one.location.ifcommunicate=false"
                        >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      </transition>
                      <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.isMailingAddress2') }}</li> <!-- $i18n - 您的通讯地址是否与上方所填地址不同 -->
                    </ul>
                  </div>
                  <div v-if="partnership12.one.location.ifcommunicate">
                    <el-form-item prop="one.location.communicate.state">
                      <el-select
                        v-model="partnership12.one.location.communicate.state"
                        :no-match-text="$t('account.manage.no_data')"
                        filterable
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"
                      > <!-- $i18n - 国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site">
                      <el-input
                        v-model="partnership12.one.location.communicate.site"
                        maxlength="64"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                      /> <!-- $i18n - 地址（如果空间不足请转下行） -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site1">
                      <el-input
                        v-model="partnership12.one.location.communicate.site1"
                        maxlength="64"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')"
                      /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.city">
                      <el-input v-model="partnership12.one.location.communicate.city" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.province">
                      <el-input
                        v-model="partnership12.one.location.communicate.province"
                        maxlength="30"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')"
                      /> <!-- $i18n - 省/市 -->
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.postalCode">
                      <el-input
                        v-model="partnership12.one.location.communicate.postalCode"
                        maxlength="20"
                        :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')"
                      /> <!-- $i18n - 邮政编码 -->
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- main-content2 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.title') }}</div> <!-- $i18n - 第二部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.describe') }}</div> <!-- $i18n - 实体类别 -->
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.describe1') }}</h5> <!-- $i18n - 请勾选正确的选项，并提供有关资料。 -->
                  <!-- <li>财务机构</li> -->
                  <el-form-item id="radioInline" class="titleType zimuNewline">
                    <el-radio-group v-model="partnership12.two.titleType">
                      <el-radio label="Financial Institution">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.finance') }}</el-radio> <!-- $i18n - 财务机构 -->

                      <div v-if="partnership12.two.titleType === 'Financial Institution'" class="marginLeft30">
                        <el-form-item id="radioInline" style="margin-bottom:0px;" prop="two.financeOrgani">
                          <el-radio-group v-model="partnership12.two.financeOrgani">
                            <div v-for="(item,index) in partnership12.two.entityTypeList1" :key="index">
                              <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>

                            </div>
                          </el-radio-group>
                        </el-form-item>
                      </div>
                      <br>
                      <el-radio label="Active NFE">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.initiative') }}</el-radio> <!-- $i18n - 主动非财务实体 -->
                      <div v-if="partnership12.two.titleType === 'Active NFE'" class="marginLeft30">
                        <el-form-item id="radioInline" style="margin-bottom:0px;" prop="two.activeOrgani.radio1">
                          <el-radio-group v-model="partnership12.two.activeOrgani.radio1">
                            <el-radio label="1">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.son1') }}</el-radio> <!-- $i18n - 该非财务实体的股票经常在 -->

                            <el-form-item style="margin-left:28px;margin-bottom: 6px;" prop="two.activeOrgani.input1">
                              <el-input v-model="partnership12.two.activeOrgani.input1" :disabled="partnership12.two.activeOrgani.radio1!=='1'" maxlength="30" placeholder />
                            </el-form-item>
                            <p style="margin-left:28px;margin-bottom: 14px;color:#999;">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.son2') }}</p> <!-- $i18n - (一个具规模证劵市场)进行买卖 -->

                            <el-radio label="2">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.son3') }}</el-radio> <!-- $i18n - 请填写 -->

                            <el-form-item style="margin-left:28px;margin-bottom: 6px;" prop="two.activeOrgani.input2">
                              <el-input v-model="partnership12.two.activeOrgani.input2" :disabled="partnership12.two.activeOrgani.radio1!=='2'" maxlength="30" placeholder />
                            </el-form-item>
                            <p style="margin-left:28px;margin-bottom: 6px;color:#999;">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.son4') }}</p> <!-- $i18n - 的有关连实体，该有关连实体的股票经常在 -->
                            <el-form-item style="margin-left:28px;margin-bottom: 6px;" prop="two.activeOrgani.input3">
                              <el-input v-model="partnership12.two.activeOrgani.input3" :disabled="partnership12.two.activeOrgani.radio1!=='2'" maxlength="30" placeholder />
                            </el-form-item>
                            <p style="margin-left:28px;margin-bottom: 14px;color:#999;">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.son5') }}</p> <!-- $i18n - (一个具规模证劵市场)进行买卖 -->

                            <div v-for="(item,index) in partnership12.two.entityTypeList2" :key="index">
                              <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                            </div>

                          </el-radio-group>
                        </el-form-item>
                      </div>
                      <br>
                      <el-radio label="Passive NFE">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part2.passivity') }}</el-radio> <!-- $i18n - 被动非财务实体 -->
                      <div v-if="partnership12.two.titleType === 'Passive NFE'" class="marginLeft30">
                        <el-form-item id="radioInline" style="margin-bottom:0px;" prop="two.passivityOrgani">
                          <el-radio-group v-model="partnership12.two.passivityOrgani">
                            <div v-for="(item,index) in partnership12.two.entityTypeList3" :key="index">
                              <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                            </div>
                          </el-radio-group>
                        </el-form-item>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
              </div>
              <!-- main-content3 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part3.title') }}</div> <!-- $i18n - 第三部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part3.describe') }}</div> <!-- $i18n - 控权人(如实体账户持有人是被动非财务实体，填写此部) -->
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">
                    {{ $t('registerOpenAccount.openAccount.common.crs.firm.part3.describe1') }}
                  </h5> <!-- $i18n - 就账户持有人，填写所有控权人的姓名于以下列表 ... -->
                  <div v-for="(item,index) in partnership12.three.controllerName" :key="index">
                    <el-form-item
                      :prop="'three.controllerName.'+index+'.lastName'"
                      :rules="rules.lastName"
                    >
                      <el-input v-model="item.lastName" :disabled="partnership12.two.titleType !== 'Passive NFE'" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" />
                    </el-form-item>
                    <el-form-item
                      :prop="'three.controllerName.'+index+'.firstName'"
                      :rules="rules.firstName"
                    >
                      <el-input v-model="item.firstName" :disabled="partnership12.two.titleType !== 'Passive NFE'" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" />
                    </el-form-item>
                  </div>

                  <div v-if="partnership12.two.titleType === 'Passive NFE'" class="addBtn" style="margin-top:0px;">
                    <div v-show="partnership12.three.controllerName.length !== 5" class="add" @click="addElement(partnership12.three.controllerName,1)">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part3.btnAdd') }}</div> <!-- $i18n - 添加其他控权人 -->
                    <div v-show="partnership12.three.controllerName.length !== 1" class="cancel" @click="removeElement(partnership12.three.controllerName)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                  </div>
                </div>
              </div>
              <!-- main-content4 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part4.title') }}</div> <!-- $i18n - 第四部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part4.describe') }}</div> <!-- $i18n - 居留司法管辖区及税务编号或具有 ... -->
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">
                    {{ $t('registerOpenAccount.openAccount.common.crs.firm.part4.describe1') }}
                  </h5> <!-- $i18n - 请提供以下数据，列明(i)账户持有人的居留司法管辖区，亦即 ... -->
                  <li class="C9">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part4.describe2') }}</li> <!-- $i18n - 如账户持有人是香港税务居民，税务编号是其香港商业登记号码。 -->
                  <li class="C9">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part4.describe3') }}</li> <!-- $i18n - 控权人不能取得税务编号。如选取这一理由，请在下方解释 ... -->
                  <div v-for="(item,index) in partnership12.four.Tex" :key="index">
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li
                            class="isyes"
                            :class="{ dianji: item.ifTaxNumber,fade:true}"
                            @click="item.ifTaxNumber=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                        </transition>
                        <transition name="fade">
                          <li
                            class="isno"
                            :class="{ dianji: !item.ifTaxNumber,fade:true}"
                            @click="item.ifTaxNumber=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                        </transition>
                        <li class="istext">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber1') }}</li> <!-- $i18n - 您是否有税务编号？ -->
                      </ul>
                    </div>
                    <div v-if="item.ifTaxNumber" style="margin-bottom:15px;">
                      <el-form-item
                        style="margin-bottom:0px;"
                        :prop="'four.Tex.'+index+'.TaxinputT'"
                        :rules="rules.TaxinputT"
                      >
                        <el-input v-model="item.TaxinputT" maxlength="30" placeholder />
                      </el-form-item>
                    </div>
                    <div v-if="!item.ifTaxNumber">
                      <el-form-item
                        id="radioInline"
                        style="margin-bottom:0px;"
                        :prop="'four.Tex.'+index+'.TaxNumberT'"
                        :rules="rules.TaxNumberT"
                      >
                        <el-radio-group v-model="item.TaxNumberT">
                          <div v-for="(item1,index1) in item.TaxNumberTlist" :key="index1">
                            <el-radio :label="item1.dictValue" @change="item.TwoIfShow= index1 === 1? true:false">{{ item1.dictDest }}</el-radio>
                            <div v-if="index1 === 1&&item.TwoIfShow ===true" style="margin-bottom:15px;">
                              <el-form-item
                                style="margin-bottom:0px;margin-left:28px;"
                                :prop="'four.Tex.'+index+'.TaxinputF'"
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
                  <!-- <div class="addBtn" style="margin-top:0px;" >
                    <div class="add" @click="addElement(partnership12.four.Tex,2)">添加其他号码</div>
                    <div class="cancel" @click="removeElement(partnership12.four.Tex)">删除</div>
                  </div> -->
                </div>
              </div>
              <!-- main-content5 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part5.title') }}</div> <!-- $i18n - 第五部分 -->
                  <div class="right">{{ $t('registerOpenAccount.openAccount.common.crs.firm.part5.describe') }}</div> <!-- $i18n - 声明及签署 -->
                </div>
                <div class="ContentCRS ContentCRSFive">
                  <p>
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe1') }}
                  </p> <!-- $i18n - 本人声明就本人所知所信，本表格内所填报的 -->
                  <el-form-item class="elFormItem" prop="statement.selfStatement">
                    <el-radio-group v-model="partnership12.statement.selfStatement">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe2') }}
                  </p> <!-- $i18n - 本人知悉及同意，财务机构可根据《税务条例》（第112章）有 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfKnow">
                    <el-radio-group v-model="partnership12.statement.selfKnow">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">
                    {{ $t('registerOpenAccount.openAccount.common.crs.statement.describe3') }}
                  </p> <!-- $i18n - 本人承诺，如情况有所改变，以致影响本表格第1部份所 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfPromiset">
                    <el-radio-group v-model="partnership12.statement.selfPromiset">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">{{ $t('registerOpenAccount.openAccount.common.crs.statement.describe4') }}</p> <!-- $i18n - 本人同意所有税务表格均用过电子形式而非 ... -->
                  <el-form-item class="elFormItem" prop="statement.selfAgree">
                    <el-radio-group v-model="partnership12.statement.selfAgree">
                      <el-radio :label="true">{{ $t('registerOpenAccount.openAccount.common.crs.statement.yes') }}</el-radio> <!-- $i18n - 是 -->
                      <el-radio :label="false">{{ $t('registerOpenAccount.openAccount.common.crs.statement.no') }}</el-radio> <!-- $i18n - 否 -->
                    </el-radio-group>
                  </el-form-item>
                  <div class="signature">
                    <p class="Stitle">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</p> <!-- $i18n - 签名 -->
                    <p class="Sdescribe">
                      {{ $t('registerOpenAccount.openAccount.common.signature.signatureDB') }}
                    </p> <!-- $i18n -  输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括 ... -->
                    <ul>
                      <li>{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</li> <!-- $i18n - 账户名称: -->
                      <li>{{ partnership12.statement.accountTitle1 }}</li>
                    </ul>

                    <ul>
                      <li style="margin-right: 48px;">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</li> <!-- $i18n - 日期: -->
                      <li>{{ partnership12.statement.time }}</li>
                    </ul>
                    <p class="shili">{{ partnership12.statement.accountTitle2 }}</p>
                    <ul class="inputQM">
                      <li>{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                      <li>
                        <el-form-item prop="statement.accountTitle3">
                          <el-input v-model="partnership12.statement.accountTitle3" maxlength="128" />
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
                      @click="submitForm('partnership12')"
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
    // 非中文验证
    var notChinese1 = (rule, value, callback) => {
      this.$verify.notChinese1(rule, value, callback);
    };
    // 第二部分验证
    var WealthEntitiesInput1 = (rule, value, callback) => {
      if (this.partnership12.two.activeOrgani.radio1 === '1') {
        if (value !== '') {
          callback();
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else {
        callback();
      }
    };
    var WealthEntitiesInput2 = (rule, value, callback) => {
      if (this.partnership12.two.activeOrgani.radio1 === '2') {
        if (value !== '') {
          callback();
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else {
        callback();
      }
    };
    var twoLeibie5 = (rule, value, callback) => {
      const mate = value === true;
      this.$verify.Signature(rule, value, mate, callback);
    };
    // 第三部分判断
    var validaPartThree = (rule, value, callback) => {
      if (this.partnership12.two.titleType === 'Passive NFE') {
        this.$verify.notChinese(rule, value, callback);
      } else {
        callback();
      }
    };
    // 签名验证
    var Signature = (rule, value, callback) => {
      const mate = this.partnership12.statement.accountTitle2 === value;
      this.$verify.Signature(rule, value, mate, callback);
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      partnership12: {
        one: {
          client: {
            name: '',
            administer: '',
            ZCnumber: ''
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
          titleType: 'Financial Institution',
          financeOrgani: '',
          activeOrgani: {
            radio1: '',
            input1: '',
            input2: '',
            input3: ''
          },
          passivityOrgani: '',
          entityTypeList1: [],
          entityTypeList2: [],
          entityTypeList3: []
        },
        three: {
          controllerName: [
            {
              firstName: '',
              lastName: ''
            }
          ]
        },
        four: {
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
        //  1.账户持有人
        'one.client.name': [{ validator: notChinese, trigger: 'blur' }],
        'one.client.administer': [{ validator: notChinese, trigger: 'blur' }],
        'one.client.ZCnumber': [{ validator: notChinese, trigger: 'blur' }],
        //   2.1 现实地址
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
        //   2.1 通讯地址
        'one.location.communicate.site': [
          { validator: notChinese, trigger: 'blur' }
        ],
        'one.location.communicate.site1': [
          { validator: notChinese1, trigger: 'blur' }
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
        // 3. 第二部分
        'two.financeOrgani': [
          { validator: notChinese, trigger: 'change' }
        ],
        'two.activeOrgani.radio1': [
          { validator: notChinese, trigger: 'change' }
        ],
        'two.activeOrgani.input1': [
          { validator: WealthEntitiesInput1, trigger: 'blur' }
        ],
        'two.activeOrgani.input2': [
          { validator: WealthEntitiesInput2, trigger: 'blur' }
        ],
        'two.activeOrgani.input3': [
          { validator: WealthEntitiesInput2, trigger: 'blur' }
        ],
        'two.passivityOrgani': [
          { validator: notChinese, trigger: 'change' }
        ],
        // 4. 第三部分
        lastName: [{ validator: validaPartThree, trigger: 'blur' }],
        firstName: [{ validator: validaPartThree, trigger: 'blur' }],
        // 5. 第四部分
        TaxinputT: [{ validator: notChinese, trigger: 'blur' }],
        TaxNumberT: [{ validator: notChinese, trigger: 'change' }],
        TaxinputF: [{ validator: notChinese, trigger: 'blur' }],
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
    this.accountCoAddress();

    // 字典表
    // this.entityType();
    this.entityType_1();
    this.entityType_2();
    this.entityType_3();
    this.taxNumberType();

    this.accountParCrsControlA();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountParCrsControlA() {
      this.$http({
        url: '/account/accountParCrsEntity/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object;
              // 第一部分
              this.partnership12.one.client.name = res.object.accountParCrsEntity.entityName;
              this.partnership12.one.client.administer = res.object.accountParCrsEntity.taxJurisdiction;
              this.partnership12.one.client.ZCnumber = res.object.accountParCrsEntity.registrationNumber;

              if (res.object.accountParCrsEntity.otherAddresses) {
                this.partnership12.one.location.ifcommunicate = true;
                if (res.object.accountParCrsEntity.otherAddresses.indexOf('-#-') !== -1) {
                  const address = res.object.accountParCrsEntity.otherAddresses.split('-#-');
                  this.partnership12.one.location.communicate.site = address[0];
                  this.partnership12.one.location.communicate.site1 = address[1];
                } else {
                  this.partnership12.one.location.communicate.site = res.object.accountParCrsEntity.otherAddresses;
                }
                this.partnership12.one.location.communicate.city = res.object.accountParCrsEntity.otherCity;
                this.partnership12.one.location.communicate.province = res.object.accountParCrsEntity.otherProvince;
                this.partnership12.one.location.communicate.state = res.object.accountParCrsEntity.otherCountry;
                this.partnership12.one.location.communicate.postalCode = res.object.accountParCrsEntity.otherZipCode;
              }
              // 第二部分
              this.partnership12.two.titleType = res.object.accountParCrsEntity.entityNumber;
              if (res.object.accountParCrsEntity.entityNumber === 'Financial Institution') {
                this.partnership12.two.financeOrgani = res.object.accountParCrsEntity.entityCategory;
              } else if (res.object.accountParCrsEntity.entityNumber === 'Active NFE') {
                this.partnership12.two.activeOrgani.radio1 = res.object.accountParCrsEntity.entityCategory;
                if (res.object.accountParCrsEntity.entityInput.indexOf('-#-') !== -1) {
                  this.partnership12.two.activeOrgani.input2 = res.object.accountParCrsEntity.entityInput.split('-#-')[0];
                  this.partnership12.two.activeOrgani.input3 = res.object.accountParCrsEntity.entityInput.split('-#-')[1];
                } else {
                  this.partnership12.two.activeOrgani.input1 = res.object.accountParCrsEntity.entityInput;
                }
              } else if (res.object.accountParCrsEntity.entityNumber === 'Passive NFE') {
                this.partnership12.two.passivityOrgani = res.object.accountParCrsEntity.entityCategory;
              }
              // 第三部分
              if (res.object.accountParCrsEntityContrList.length > 0) {
                this.partnership12.three.controllerName = JSON.parse(JSON.stringify(res.object.accountParCrsEntityContrList));
              }

              // 第四部分
              if (res.object.accountParCrsEntity.noTaxNumber === '') {
                this.partnership12.four.Tex[0].TaxinputT = res.object.accountParCrsEntity.yesTaxNumber;
              } else {
                this.partnership12.four.Tex[0].ifTaxNumber = false;
                if (res.object.accountParCrsEntity.noTaxNumber === 'The controller cannot obtain the tax number. If you choose this reason, please explain below why the controlling party can not obtain the tax number.') {
                  this.partnership12.four.Tex[0].TwoIfShow = true;
                  this.partnership12.four.Tex[0].TaxinputF = res.object.accountParCrsEntity.reason;
                } else {
                  this.partnership12.four.Tex[0].TaxinputF = '';
                  this.partnership12.four.Tex[0].TwoIfShow = false;
                }
                this.partnership12.four.Tex[0].TaxNumberT = res.object.accountParCrsEntity.noTaxNumber;
              }
              // 第五部分
              this.partnership12.statement.selfStatement = res.object.accountParCrsEntity.authenticData;
              this.partnership12.statement.selfKnow = res.object.accountParCrsEntity.agreeTaxOrdinance;
              this.partnership12.statement.selfPromiset = res.object.accountParCrsEntity.provenItself;
              this.partnership12.statement.selfAgree = res.object.accountParCrsEntity.consent;
              this.partnership12.statement.accountTitle3 = res.object.accountParCrsEntity.signature;
              this.partnership12.statement.time = this.$verify.NowDate3();
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
    // 实体类型
    entityType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            // this.partnership12.four.Tex[0].TaxNumberTlist = res.object;
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
    // 实体类型-子
    entityType_1() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_1', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership12.two.entityTypeList1 = res.object;
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
    // 实体类型-子
    entityType_2() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_2', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership12.two.entityTypeList2 = res.object;
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
    // 实体类型-子
    entityType_3() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_3', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership12.two.entityTypeList3 = res.object;
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
    // 税收编号
    taxNumberType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership12.four.Tex[0].TaxNumberTlist = res.object;
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
              this.partnership12.statement.accountTitle1 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership12.statement.time = this.$verify.NowDate3();
              this.partnership12.statement.accountTitle2 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership12.statement.accountTitle3 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
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
    // 地址 - 被授权人地址
    accountCoAddress() { // 机构信息
      this.$http({
        url: '/account/accountParAddress/findByAccountId/institutional',
        method: 'get',
        params: { personalType: 'authorizedPerson', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  this.partnership12.one.location.reality.state = res.object[i].country;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const address = res.object[i].address.split('-#-');
                    this.partnership12.one.location.reality.site = address[0];
                    this.partnership12.one.location.reality.site1 = address[1];
                  } else {
                    this.partnership12.one.location.reality.site = res.object[i].address;
                  }
                  this.partnership12.one.location.reality.city = res.object[i].city;
                  this.partnership12.one.location.reality.province = res.object[i].rovince;
                  this.partnership12.one.location.reality.postalCode = res.object[i].zipCode;
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

    // 添加元素
    // index 识别号  --手动判断更改obj的值
    // arr 需要操作的数组
    addElement(arr, index) {
      let obj = null;
      if (index === 1) {
        obj = {
          lastName: '',
          firstName: ''
        };
        arr.push(JSON.parse(JSON.stringify(obj)));
      } else if (index === 2) {
        obj = {
          ifTaxNumber: true,
          TaxinputT: '',
          TaxinputF: '',
          TaxNumberT: '1',
          TaxNumberTlist: this.partnership12.four.Tex[0].TaxNumberTlist,
          TwoIfShow: 1
        };
        if (arr.length > 1) {
          return false;
        } else {
          arr.push(JSON.parse(JSON.stringify(obj)));
        }
      }
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
          return false;
        }
      });
    },
    // 提交表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();

      let entityCategory = ''; // 第二部分 实体类别
      const entityNumber = this.partnership12.two.titleType;
      let entityInput = null;

      if (this.partnership12.two.titleType === 'Financial Institution') {
        entityCategory = this.partnership12.two.financeOrgani;
      } else if (this.partnership12.two.titleType === 'Active NFE') {
        entityCategory = this.partnership12.two.activeOrgani.radio1;
        if (this.partnership12.two.activeOrgani.radio1 === '1') {
          entityInput = this.partnership12.two.activeOrgani.input1;
        } else if (this.partnership12.two.activeOrgani.radio1 === '2') {
          entityInput = this.partnership12.two.activeOrgani.input2 + '-#-' + this.partnership12.two.activeOrgani.input3;
        }
      } else if (this.partnership12.two.titleType === 'Passive NFE') {
        entityCategory = this.partnership12.two.passivityOrgani;
      }
      // 第三部分
      const accountParCrsEntityContrList = []; // 第三部分
      if (this.partnership12.two.titleType === 'Passive NFE') {
        if (this.newValue === null) {
          for (let i = 0; i < this.partnership12.three.controllerName.length; i++) {
            accountParCrsEntityContrList.push(
              {
                accountId,
                createdBy,
                createdDate,
                firstName: this.partnership12.three.controllerName[i].firstName,
                lastName: this.partnership12.three.controllerName[i].lastName
              }
            );
          }
        } else {
          const newlength = this.newValue.accountParCrsEntityContrList.length - 1;
          for (let i = 0; i < this.partnership12.three.controllerName.length; i++) {
            if (i <= newlength) {
              this.newValue.accountParCrsEntityContrList[i].firstName = this.partnership12.three.controllerName[i].firstName;
              this.newValue.accountParCrsEntityContrList[i].lastName = this.partnership12.three.controllerName[i].lastName;
              accountParCrsEntityContrList.push(JSON.parse(JSON.stringify(this.newValue.accountParCrsEntityContrList[i])));
            } else {
              accountParCrsEntityContrList.push(
                {
                  accountId,
                  createdBy,
                  createdDate,
                  firstName: this.partnership12.three.controllerName[i].firstName,
                  lastName: this.partnership12.three.controllerName[i].lastName
                }
              );
            }
          }
        }
      }

      let yesTaxNumber = null; // 第四部分
      let noTaxNumber = null;
      let reason = null;
      for (let i = 0; i < this.partnership12.four.Tex.length; i++) {
        if (this.partnership12.four.Tex[i].ifTaxNumber === true) {
          yesTaxNumber = this.partnership12.four.Tex[i].TaxinputT;
        } else {
          noTaxNumber = this.partnership12.four.Tex[i].TaxNumberT;
          reason = this.partnership12.four.Tex[i].TaxinputF;
        }
      }
      let data = {};
      if (this.newValue === null) {
        data = {
          accountParCrsEntity: {
            accountId,
            createdBy,
            createdDate,
            // 第一部分
            entityName: this.partnership12.one.client.name,
            taxJurisdiction: this.partnership12.one.client.administer,
            registrationNumber: this.partnership12.one.client.ZCnumber,
            // 其他地址-通讯
            otherAddresses: this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.site1 ? this.partnership12.one.location.communicate.site + '-#-' + this.partnership12.one.location.communicate.site1 : this.partnership12.one.location.reality.site : null,
            otherCity: this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.city : null,
            otherProvince: this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.province : null,
            otherCountry: this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.state : null,
            otherZipCode: this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.postalCode : null,
            // 第二部分
            entityNumber,
            entityCategory,
            entityInput,
            // 第四部分
            yesTaxNumber,
            noTaxNumber,
            reason,
            // 第五部分
            authenticData: this.partnership12.statement.selfStatement,
            agreeTaxOrdinance: this.partnership12.statement.selfKnow,
            provenItself: this.partnership12.statement.selfPromiset,
            consent: this.partnership12.statement.selfAgree,
            signature: this.partnership12.statement.accountTitle3,
            signatureDate: this.$verify.NowDate(this.partnership12.statement.time)
          },
          // 第三部分
          accountParCrsEntityContrList
        };
      } else {
        this.newValue.accountParCrsEntity.entityName = this.partnership12.one.client.name;
        this.newValue.accountParCrsEntity.taxJurisdiction = this.partnership12.one.client.administer;
        this.newValue.accountParCrsEntity.registrationNumber = this.partnership12.one.client.ZCnumber;
        this.newValue.accountParCrsEntity.otherAddresses = this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.site1 ? this.partnership12.one.location.communicate.site + '-#-' + this.partnership12.one.location.communicate.site1 : this.partnership12.one.location.reality.site : null;
        this.newValue.accountParCrsEntity.otherCity = this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.city : null;
        this.newValue.accountParCrsEntity.otherProvince = this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.province : null;
        this.newValue.accountParCrsEntity.otherCountry = this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.state : null;
        this.newValue.accountParCrsEntity.otherZipCode = this.partnership12.one.location.ifcommunicate ? this.partnership12.one.location.communicate.postalCode : null;
        this.newValue.accountParCrsEntity.entityNumber = entityNumber;
        this.newValue.accountParCrsEntity.entityCategory = entityCategory;
        this.newValue.accountParCrsEntity.entityInput = entityInput;
        this.newValue.accountParCrsEntity.noTaxNumber = noTaxNumber;
        this.newValue.accountParCrsEntity.reason = reason;
        this.newValue.accountParCrsEntity.authenticData = this.partnership12.statement.selfStatement;
        this.newValue.accountParCrsEntity.agreeTaxOrdinance = this.partnership12.statement.selfKnow;
        this.newValue.accountParCrsEntity.provenItself = this.partnership12.statement.selfPromiset;
        this.newValue.accountParCrsEntity.consent = this.partnership12.statement.selfAgree;
        this.newValue.accountParCrsEntity.signature = this.partnership12.statement.accountTitle3;
        this.newValue.accountParCrsEntity.signatureDate = this.$verify.NowDate(this.partnership12.statement.time);
        // 第三部分
        this.newValue.accountParCrsEntityContrList = JSON.parse(JSON.stringify(accountParCrsEntityContrList));
        data = JSON.parse(JSON.stringify(this.newValue));
      }
      this.$http({
        url: '/openAccount/partnerShip/saveTwelve',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            if (accountParCrsEntityContrList.length > 0 && this.partnership12.two.titleType === 'Passive NFE') {
              this.accountParCrsEntityContrList();
              // this.$verify.routerPermissions({ path: '/openAccount/partnership13' });
            } else {
              this.$verify.routerPermissions({ path: '/openAccount/partnership14' });
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
    accountParCrsEntityContrList() { // 存储id 13页获取第一条控权人
      this.$http({
        url: '/account/accountParCrsEntityContr/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.$store.commit('user/SET_OPENACCOUNT', { crsI: res.object[0].id, pageStatus: 'present' });
              // this.$store.commit('user/SET_OPENACCOUNT', { pageStatus: 'present' });
              this.$verify.routerPermissions({ path: '/openAccount/partnership13' });
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

    beforepage() {
      this.accountCoTaxClassification();
    },
    accountCoTaxClassification() { // 获取公司税收分类
      this.$http({
        url: '/account/accountParTaxClassification/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].taxType === 'enterprise') { // 企业
                this.$verify.routerPermissions({ path: '/openAccount/partnership11a' });
              } else if (res.object[0].taxType === 'partnership (must have at least 2 owners)') { // 合伙企业
                this.$verify.routerPermissions({ path: '/openAccount/partnership11b' });
              } else if (res.object[0].taxType === 'disregardEntities (must only have 1 owner)') { // 无视实体
                this.$verify.routerPermissions({ path: '/openAccount/partnership10' });
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
    }
  }
};
</script>

<style scoped>
#ZC-regiMain
  .contentBOX
  .contentBOXdignwei
  .formContent
  .formNapeCRS
  .ContentCRS
  > li {
  list-style: none;
  color: #666;
}
#ZC-regiMain
  .contentBOX
  .contentBOXdignwei
  .formContent
  .formNapeCRS
  .ContentCRS
  > li.C9 {
  list-style: disc;
  color: #999;
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRSFive .el-radio {
  margin-bottom: 0px;
}
#ZC-regiMain .contentBOX .contentBOXdignwei .formContent .formNapeCRS .ContentCRSFive .AgreementAgreed{
  margin-top: 11px;
}
#ZC-regiMain  .titleType {
  margin-bottom:0px;
  margin-top:10px;
}
#ZC-regiMain  .marginLeft30 {
  margin-left: 30px;
}
/* #ZC-regiMain
    .contentBOX
  .contentBOXdignwei
  .formContent
  .formNapeCRS
  .ContentCRS
  .inputXiahua {
  width: 175%;
} */
</style>
