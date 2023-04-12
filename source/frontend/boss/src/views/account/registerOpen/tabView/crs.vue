<template>
  <div>
    <div v-if="userInfo.accountType==='personal'" class="crsContent">
      <el-form ref="personal" status-icon :model="personal" label-width="0px" class="demo-ruleForm">
        <div class="formContent">
          <div class="formNapeCRS">
            <h3>共同汇报标准（CRS）个人自我证明表格（CRS-I）</h3>
            <div class="topContentCRS">
              <h4>客户须知：填写此表格前 ，请先阅读本节。</h4>
              <p>作为财务机构，我们是不允许给予客户税务咨询 。</p>
              <p class="pCRS2">如果您对此表格或您的税务居民身份定义有任何疑问，请联系您的税务顾问或相关税务机关。您可以 在OECD自动讯息交换网站</p>
              <p class="pCRS2 wangzhiCRS">
                (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                <span>获取更多信息。</span>
              </p>
              <p class="pCRS3">请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。</p>
              <p class="pCRS3">您可能会被要求提供更多证明文件，以核实此表格上之内容。</p>
              <p
                class="pCRS3 lineHeightBD22"
              >这是由账户持有人向申报财务机构提供的自我证明表格，以作自动交换财务账户资料用途。申报财务机构可把收集所得的资料交给税务局，税务局会将资料转交到另一税务管辖区的税务当局。</p>
              <p class="pCRS3">除不适用或特别注明外，必须填写这份表格所有部分。在栏/部份标有星号（*）的项目为申报财务机构须向税务局申报的资料。</p>
            </div>
          </div>
          <!-- main-content1 -->
          <div class="formNapeCRS">
            <div class="Title">
              <div class="left">第一部分</div>
              <div class="right">个人账户持有人的身分识辨资料</div>
            </div>
            <div class="ContentCRS">
              <h5>1、账户持有人个人信息</h5>
              <el-form-item>
                <el-select v-model="personal.one.personal.call" :disabled="true" placeholder="称呼">
                  <dictionary dict-code="appellationType"></dictionary>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.personal.firstName"
                  maxlength="30"
                  :disabled="true"
                  placeholder="姓氏"
                />
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.personal.lastName"
                  maxlength="30"
                  :disabled="true"
                  placeholder="名字"
                />
              </el-form-item>
              <el-form-item>
                <el-select
                  v-model="personal.one.personal.identityType"
                  :disabled="true"
                  placeholder="身份证明类型"
                >
                  <dictionary dict-code="cardType"></dictionary>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.personal.papersNum"
                  maxlength="30"
                  :disabled="true"
                  placeholder="证件号码"
                />
              </el-form-item>
              <el-form-item>
                <el-date-picker
                  v-model="personal.one.personal.dateBirth"
                  type="date"
                  placeholder="出生日期"
                  :disabled="true"
                />
              </el-form-item>
            </div>
            <div class="ContentCRS">
              <h5>2、现时住址</h5>
              <el-form-item>
                <el-input
                  v-model="personal.one.location.reality.site"
                  maxlength="30"
                  :disabled="true"
                  placeholder="地址（如果空间不足请转下行）"
                />
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.location.reality.site1"
                  maxlength="30"
                  :disabled="true"
                  placeholder="( 接上行) 房间号, 楼层 公寓等"
                />
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.location.reality.city"
                  maxlength="30"
                  :disabled="true"
                  placeholder="城市"
                />
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.location.reality.province"
                  maxlength="30"
                  :disabled="true"
                  placeholder="省/市"
                />
              </el-form-item>
              <el-form-item>
                <el-select
                  v-model="personal.one.location.reality.state"
                  :disabled="true"
                  placeholder="国家"
                >
                  <dictionary dict-code="country"></dictionary>
                </el-select>
              </el-form-item>
              <el-form-item>
                <el-input
                  v-model="personal.one.location.reality.postalCode"
                  maxlength="30"
                  :disabled="true"
                  placeholder="邮政编码"
                />
              </el-form-item>
              <div class="isBtn">
                <ul>
                  <transition name="fade">
                    <li
                      class="isyes"
                      :class="{ dianji: personal.one.location.ifcommunicate,fade:true}"
                    >是</li>
                    <!-- <li
                            class="isyes"
                            :class="{ dianji: personal.one.location.ifcommunicate,fade:true}"
                            @click="personal.one.location.ifcommunicate=true"
                    >是</li>-->
                  </transition>
                  <transition name="fade">
                    <li
                      class="isno"
                      :class="{ dianji: !personal.one.location.ifcommunicate,fade:true}"
                    >否</li>
                    <!-- <li
                            class="isno"
                            :class="{ dianji: !personal.one.location.ifcommunicate,fade:true}"
                            @click="personal.one.location.ifcommunicate=false"
                    >否</li>-->
                  </transition>
                  <li class="istext">您的通讯是否与上方所填地址不同？</li>
                </ul>
              </div>
              <div v-if="personal.one.location.ifcommunicate">
                <el-form-item>
                  <el-input
                    v-model="personal.one.location.communicate.site"
                    maxlength="30"
                    placeholder="地址（如果空间不足请转下行）"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="personal.one.location.communicate.site1"
                    maxlength="30"
                    placeholder="( 接上行) 房间号, 楼层 公寓等"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="personal.one.location.communicate.city"
                    maxlength="30"
                    placeholder="城市"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="personal.one.location.communicate.province"
                    maxlength="30"
                    placeholder="省/市"
                  />
                </el-form-item>
                <el-form-item>
                  <el-select v-model="personal.one.location.communicate.state" placeholder="国家">
                    <dictionary dict-code="country"></dictionary>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="personal.one.location.communicate.postalCode"
                    maxlength="30"
                    placeholder="邮政编码"
                  />
                </el-form-item>
              </div>
            </div>
          </div>
          <!-- main-content2 -->
          <div class="formNapeCRS">
            <div class="Title">
              <div class="left">第二部分</div>
              <div class="right">居留司法管辖区及税务编号或具有等同功能的识辨编号(以下简称『税务编号』)*</div>
            </div>
            <div class="ContentCRS">
              <h5
                style="margin-bottom:15px"
              >提供以下资料，列明（i）账户持有人的居留司法管辖区，亦即账户持有人的税务管辖区（香港包括在内）及（ii）该居留司法管辖区发给账户持有人的税务编号。</h5>
              <li>如账户持有人是中国税务居民，税务编号是其中国身份证号码。</li>
              <div v-for="(item,index) in personal.two.TaxNumber" :key="index">
                <div class="isBtn">
                  <ul>
                    <transition name="fade">
                      <li class="isyes" :class="{ dianji: item.ifTaxNumberBtn,fade:true}">是</li>
                      <!-- <li
                          class="isyes"
                          :class="{ dianji: item.ifTaxNumberBtn,fade:true}"
                          @click="item.ifTaxNumberBtn=true"
                      >是</li>-->
                    </transition>
                    <transition name="fade">
                      <li class="isno" :class="{ dianji: !item.ifTaxNumberBtn,fade:true}">否</li>
                      <!-- <li
                          class="isno"
                          :class="{ dianji: !item.ifTaxNumberBtn,fade:true}"
                          @click="item.ifTaxNumberBtn=false"
                      >否</li>-->
                    </transition>
                    <li class="istext">您是否有税务编号？</li>
                  </ul>
                </div>
                <el-form-item v-if="item.ifTaxNumberBtn">
                  <el-input v-model="item.describeT" maxlength="30" placeholder="必填" />
                </el-form-item>
                <div v-if="!item.ifTaxNumberBtn">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="item.describeF">
                      <div v-for="(item1,index1) in item.thetaxnumberList" :key="index1">
                        <el-radio
                          :label="item1.dictValue"
                          @change="item.TwoIfShow= index1 === 1? true:false"
                        >{{ item1.dictDest }}</el-radio>
                        <br>
                        <div
                          v-if="index1 === 1&&item.TwoIfShow ===true"
                          style="margin-bottom:15px;"
                        >
                          <el-form-item style="margin-bottom:0px;margin-left:28px;">
                            <el-input v-model="item.describeFinput" maxlength="30" placeholder />
                          </el-form-item>
                        </div>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
              </div>
              <!-- <div class="addBtn" style="margin-top:0px;">
                  <div class="add" @click="addElementYA(personal.two.TaxNumber,1)">添加其他号码</div>
                  <div class="cancel" @click="removeElementYA(personal.two.TaxNumber)">取消</div>
              </div>-->
            </div>
          </div>
          <!-- main-content3 -->
          <div class="formNapeCRS elerror">
            <div class="Title">
              <div class="left">第三部分</div>
              <div class="right">声明及签署</div>
            </div>
            <div class="ContentCRS">
              <p>
                本人声明就本人所知所信，本表格内所填报的所有资料和声明均属真实 ，本表格内所填报的所有资料和声明均属真实、正确和完备 、正
                确和完备。
              </p>
              <el-form-item class="elFormItem">
                <el-radio-group v-model="personal.statement.selfStatement">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
              <p>
                本人知悉及同意，财务机构可根据《税务条例》（第112章）有关交换财务账户资料的法律条文，（i）收集本表格所载资料并可备存作
                自动交换财务账户资料用途及（ii）把该等资料和关于账户持有人及任何须申报账户的资料向香港特别行政区政府税务局申报，从而把资
                料转交到账户持有人的居留司法管辖区的税务当局。
              </p>
              <el-form-item class="elFormItem">
                <el-radio-group v-model="personal.statement.selfKnow">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
              <p>
                本人承诺，如情况有所改变，以致影响本表格第1部份所述的个人的税务居民身份，或引致本表格所载的资料不正确，本人会通知国瑞
                证券有限公司的相关机构，并会在情况发生改变后的30日内，向所述的相关机构提交一份已适当更新的自我证明表格。
              </p>
              <el-form-item class="elFormItem">
                <el-radio-group v-model="personal.statement.selfPromiset">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
              <p>本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</p>
              <el-form-item class="elFormItem">
                <el-radio-group v-model="personal.statement.selfAgree">
                  <el-radio :label="true">是</el-radio>
                  <el-radio :label="false">否</el-radio>
                </el-radio-group>
              </el-form-item>
              <h5 style="margin-top: 10px;">本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</h5>
              <div class="signature">
                <p class="Stitle">签名</p>
                <p class="Sdescribe">
                  输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括预先填写的信息）准确无误，如有不实，根据《税务条例》第80(2E)
                  条，如任何人在作出自我证明时，在明知一项陈述在要项上属具误导性、虚假或不正确，或罔顾一项陈述是否在要项上属具误导性、虚
                  假或不正确下，作出该项陈述，即属犯罪。一经定罪，可处第3级（即港币$10,000）罚款。(ii)同意数字签名等同于书面签名并且(iii)
                  同意使用电子记录作为您证书/协议的证明。
                </p>
                <ul>
                  <li>账户名称:</li>
                  <li>{{ personal.statement.accountTitle1 }}</li>
                </ul>
                <ul>
                  <li style="margin-right: 48px;">日期:</li>
                  <li>{{ personal.statement.time }}</li>
                </ul>
                <p class="shili">{{ personal.statement.accountTitle2 }}</p>
                <ul class="inputQM">
                  <li>签名</li>
                  <li>
                    <el-form-item>
                      <el-input
                        v-model="personal.statement.accountTitle3"
                        maxlength="30"
                        placeholder="签名"
                      />
                    </el-form-item>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </el-form>
    </div>
    <div v-else-if="userInfo.accountType==='limitedLiablity'" class="crsContent">
      <div id="crsContentYA">
        <el-form
          ref="limitedLiablityA"
          status-icon
          :model="limitedLiablityA"
          label-width="0px"
          class="demo-ruleForm"
        >
          <div class="formContent">
            <!-- main-top -->
            <div class="formNapeCRS">
              <h3>共同汇报标准（CRS）实体自我证明表格（共同汇报标准 (CRS - E）</h3>
              <div class="topContentCRS">
                <h4>客户须知：填写此表格前 ，请先阅读本节。</h4>
                <p>作为财务机构，我们是不允许给予客户税务咨询 。</p>
                <p class="pCRS2">如果您对此表格或您的税务居民身份定义有任何疑问，请联系您的税务顾问或相关税务机关。您可以 在OECD自动讯息交换网站</p>
                <p class="pCRS2 wangzhiCRS">
                  (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                  <span>获取更多信息。</span>
                </p>
                <!-- <p class="pCRS3">请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。</p> -->
                <p class="pCRS3">您可能会被要求提供更多证明文件，以核实此表格上之内容。</p>
                <p
                  class="pCRS3 lineHeightBD22"
                >这是由账户持有人向申报财务机构提供的自我证明表格，以作自动交换财务账户资料用途。申报财务机构可把收集所得的资料交给税务局，税务局会将资料转交到另一税务管辖区的税务当局。</p>
                <p class="pCRS3">除不适用或特别注明外，必须填写这份表格所有部分。在栏/部份标有星号（*）的项目为申报财务机构须向税务局申报的资料。</p>
              </div>
            </div>
            <!-- main-content1 -->
            <div class="formNapeCRS">
              <div class="Title">
                <div class="left">第一部分</div>
                <div class="right">实体账户持有人的身分识辨数据</div>
              </div>
              <div class="ContentCRS">
                <h5>1、客户数据</h5>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.client.name"
                    maxlength="30"
                    placeholder="实体或分支机构的法定名称"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.client.administer"
                    maxlength="30"
                    type="textarea"
                    :rows="2"
                    placeholder="实体成立为法团或设立所在的税务管辖区"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.client.ZCnumber"
                    maxlength="30"
                    placeholder="注册号码"
                  />
                </el-form-item>
              </div>
              <div class="ContentCRS">
                <h5>2、现时营业地址</h5>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.location.reality.site"
                    maxlength="30"
                    placeholder="地址（如果空间不足请转下行）"
                    :disabled="true"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.location.reality.site1"
                    maxlength="30"
                    :disabled="true"
                    placeholder="( 接上行) 房间号, 楼层 公寓等"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.location.reality.city"
                    maxlength="30"
                    :disabled="true"
                    placeholder="城市"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.location.reality.province"
                    maxlength="30"
                    :disabled="true"
                    placeholder="省/市"
                  />
                </el-form-item>
                <el-form-item>
                  <el-select
                    v-model="limitedLiablityA.one.location.reality.state"
                    :disabled="true"
                    placeholder="国家"
                  >
                    <dictionary dict-code="country" />
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="limitedLiablityA.one.location.reality.postalCode"
                    maxlength="30"
                    placeholder="邮政编码"
                    :disabled="true"
                  />
                </el-form-item>
                <div class="isBtn">
                  <ul>
                    <transition name="fade">
                      <li
                        class="isyes"
                        :class="{ dianji: limitedLiablityA.one.location.ifcommunicate,fade:true}"
                      >是</li>
                      <!-- <li
                        class="isyes"
                        :class="{ dianji: limitedLiablityA.one.location.ifcommunicate,fade:true}"
                        @click="limitedLiablityA.one.location.ifcommunicate=true"
                      >是</li>-->
                    </transition>
                    <transition name="fade">
                      <li
                        class="isno"
                        :class="{ dianji: !limitedLiablityA.one.location.ifcommunicate,fade:true}"
                      >否</li>
                      <!-- <li
                        class="isno"
                        :class="{ dianji: !limitedLiablityA.one.location.ifcommunicate,fade:true}"
                        @click="limitedLiablityA.one.location.ifcommunicate=false"
                      >否</li>-->
                    </transition>
                    <li class="istext">您的通讯是否与上方所填地址不同？</li>
                  </ul>
                </div>
                <div v-if="limitedLiablityA.one.location.ifcommunicate">
                  <el-form-item>
                    <el-input
                      v-model="limitedLiablityA.one.location.communicate.site"
                      maxlength="30"
                      placeholder="地址（如果空间不足请转下行）"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="limitedLiablityA.one.location.communicate.site1"
                      maxlength="30"
                      placeholder="( 接上行) 房间号, 楼层 公寓等"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="limitedLiablityA.one.location.communicate.city"
                      maxlength="30"
                      placeholder="城市"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="limitedLiablityA.one.location.communicate.province"
                      maxlength="30"
                      placeholder="省/市"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-select
                      v-model="limitedLiablityA.one.location.communicate.state"
                      placeholder="国家"
                    >
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="limitedLiablityA.one.location.communicate.postalCode"
                      maxlength="30"
                      placeholder="邮政编码"
                    />
                  </el-form-item>
                </div>
              </div>
            </div>
            <!-- main-content2 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第二部分</div>
                <div class="right">实体类别</div>
              </div>
              <div class="ContentCRS">
                <h5 style="margin-bottom:15px">请在适当的方格填上剔号，并提供有关资料。</h5>
                <!-- <li>财务机构</li> -->
                <el-form-item class="titleType">
                  <el-radio-group v-model="limitedLiablityA.two.titleType">
                    <el-radio label="Financial Institution">财务机构</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div v-if="limitedLiablityA.two.titleType === 'Financial Institution'" class="marginLeft30">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="limitedLiablityA.two.financeOrgani">
                      <div
                        v-for="(item,index) in limitedLiablityA.two.entityTypeList1"
                        :key="index"
                      >
                        <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                        <br>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
                <!-- <li>主动非财务实体</li> -->
                <el-form-item class="titleType">
                  <el-radio-group v-model="limitedLiablityA.two.titleType">
                    <el-radio label="Active NFE">主动非财务实体</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div v-if="limitedLiablityA.two.titleType === 'Active NFE'" class="marginLeft30">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="limitedLiablityA.two.activeOrgani.radio1">
                      <el-radio label="1">该非财务实体的股票经常在</el-radio>
                      <br>
                      <el-form-item style="margin-left:28px;margin-bottom: 6px;">
                        <el-input
                          v-model="limitedLiablityA.two.activeOrgani.input1"
                          maxlength="30"
                          placeholder
                        />
                      </el-form-item>
                      <p style="margin-left:28px;margin-bottom: 14px;color:#999;">(一个具规模证劵市场)进行买卖</p>
                      <br>
                      <el-radio label="2">请填写</el-radio>
                      <br>
                      <el-form-item style="margin-left:28px;margin-bottom: 6px;">
                        <el-input
                          v-model="limitedLiablityA.two.activeOrgani.input2"
                          maxlength="30"
                          placeholder
                        />
                      </el-form-item>
                      <p style="margin-left:28px;margin-bottom: 6px;color:#999;">的有关连实体，该有关连实体的股票经常在</p>
                      <el-form-item style="margin-left:28px;margin-bottom: 6px;">
                        <el-input
                          v-model="limitedLiablityA.two.activeOrgani.input3"
                          maxlength="30"
                          placeholder
                        />
                      </el-form-item>
                      <p style="margin-left:28px;margin-bottom: 14px;color:#999;">(一个具规模证劵市场)进行买卖</p>
                      <br>
                      <div
                        v-for="(item,index) in limitedLiablityA.two.entityTypeList2"
                        :key="index"
                      >
                        <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                        <br>
                      </div>
                      <br>
                    </el-radio-group>
                  </el-form-item>
                </div>
                <!-- <li>被动非财务实体</li> -->
                <el-form-item class="titleType">
                  <el-radio-group v-model="limitedLiablityA.two.titleType">
                    <el-radio label="Passive NFE">被动非财务实体</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div v-if="limitedLiablityA.two.titleType === 'Passive NFE'" class="marginLeft30">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="limitedLiablityA.two.passivityOrgani">
                      <div
                        v-for="(item,index) in limitedLiablityA.two.entityTypeList3"
                        :key="index"
                      >
                        <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                        <br>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
              </div>
            </div>
            <!-- main-content3 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第三部分</div>
                <div class="right">控权人(如实体账户持有人是被动非财务实体，填写此部)</div>
              </div>
              <div class="ContentCRS">
                <h5 style="margin-bottom:15px">
                  就账户持有人，填写所有控权人的姓名于以下列表内。就法人实体，如行使控制权的并非自然人，控权人会是该法人实体的高级管理人员。
                  每名控权人须分别填写一份共同汇报标准 (CRS) 控权人自我证明表格。
                </h5>
                <el-form-item
                  v-for="(item,index) in limitedLiablityA.three.controllerName"
                  :key="index"
                >
                  <el-input v-model="item.lastName+item.firstName" maxlength="30" placeholder />
                </el-form-item>
                <div class="addBtn" style="margin-top:0px;">
                  <div
                    class="add"
                    @click="addElementYA(limitedLiablityA.three.controllerName,1)"
                  >添加其他控权人</div>
                  <div
                    class="cancel"
                    @click="removeElementYA(limitedLiablityA.three.controllerName)"
                  >取消</div>
                </div>
              </div>
            </div>
            <!-- main-content4 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第四部分</div>
                <div class="right">居留司法管辖区及税务编号或具有等同功能的识辨编号(以下简称『税务编号』)*</div>
              </div>
              <div class="ContentCRS">
                <h5 style="margin-bottom:15px">
                  请提供以下数据，列明(i)账户持有人的居留司法管辖区，亦即账户持有人的税务管辖区(香港包括在内)及(ii)该居留司法管辖区发给账
                  户持有人的税务编号。列出所有(不限于 3 个)居留司法管辖区。
                </h5>
                <li class="C9">如账户持有人是香港税务居民，税务编号是其香港商业登记号码。</li>
                <li class="C9">控权人不能取得税务编号。如选取这一理由，请在下方解释控权人不能取得税务编号的原因。</li>
                <div v-for="(item,index) in limitedLiablityA.four.Tex" :key="index">
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: item.ifTaxNumber,fade:true}"
                          @click="item.ifTaxNumber=true"
                        >是</li>
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !item.ifTaxNumber,fade:true}"
                          @click="item.ifTaxNumber=false"
                        >否</li>
                      </transition>
                      <li class="istext">您是否有税务编号</li>
                    </ul>
                  </div>
                  <div v-if="item.ifTaxNumber" style="margin-bottom:15px;">
                    <el-form-item style="margin-bottom:0px;">
                      <el-input v-model="item.TaxinputT" maxlength="30" placeholder />
                    </el-form-item>
                  </div>
                  <div v-if="!item.ifTaxNumber">
                    <el-form-item style="margin-bottom:0px;">
                      <el-radio-group v-model="item.TaxNumberT">
                        <div v-for="(item1,index1) in item.TaxNumberTlist" :key="index1">
                          <el-radio
                            :label="item1.dictValue"
                            @change="item.TwoIfShow= index1 === 1? true:false"
                          >{{ item1.dictDest }}</el-radio>
                          <br>
                          <div
                            v-if="index1 === 1&&item.TwoIfShow ===true"
                            style="margin-bottom:15px;"
                          >
                            <el-form-item style="margin-bottom:0px;margin-left:28px;">
                              <el-input v-model="item.TaxinputF" maxlength="30" placeholder />
                            </el-form-item>
                          </div>
                        </div>
                      </el-radio-group>
                    </el-form-item>
                  </div>
                </div>
                <!-- <div class="addBtn" style="margin-top:0px;" >
                  <div class="add" @click="addElementYA(limitedLiablityA.four.Tex,2)">添加其他号码</div>
                  <div class="cancel" @click="removeElementYA(limitedLiablityA.four.Tex)">取消</div>
                </div>-->
              </div>
            </div>
            <!-- main-content5 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第五部分</div>
                <div class="right">声明及签署</div>
              </div>
              <div class="ContentCRS ContentCRSFive">
                <p>
                  本人声明就本人所知所信，本表格内所填报的所有资料和声明均属真实 ，本表格内所填报的所有资料和声明均属真实、正确和完备 、正
                  确和完备。
                </p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="limitedLiablityA.statement.selfStatement">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <p class="AgreementAgreed">
                  本人知悉及同意，财务机构可根据《税务条例》（第112章）有关交换财务账户资料的法律条文，（i）收集本表格所载资料并可备存作
                  自动交换财务账户资料用途及（ii）把该等资料和关于账户持有人及任何须申报账户的资料向香港特别行政区政府税务局申报，从而把资
                  料转交到账户持有人的居留司法管辖区的税务当局。
                </p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="limitedLiablityA.statement.selfKnow">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <p class="AgreementAgreed">
                  本人承诺，如情况有所改变，以致影响本表格第1部份所述的个人的税务居民身份，或引致本表格所载的资料不正确，本人会通知国瑞
                  证券有限公司的相关机构，并会在情况发生改变后的30日内，向所述的相关机构提交一份已适当更新的自我证明表格。
                </p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="limitedLiablityA.statement.selfPromiset">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <p class="AgreementAgreed">本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="limitedLiablityA.statement.selfAgree">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div class="signature">
                  <p class="Stitle">签名</p>
                  <p class="Sdescribe">
                    输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括预先填写的信息）准确无误，如有不实，根据《税务条例》第80(2E)
                    条，如任何人在作出自我证明时，在明知一项陈述在要项上属具误导性、虚假或不正确，或罔顾一项陈述是否在要项上属具误导性、虚
                    假或不正确下，作出该项陈述，即属犯罪。一经定罪，可处第3级（即港币$10,000）罚款。(ii)同意数字签名等同于书面签名并且(iii)
                    同意使用电子记录作为您证书/协议的证明。
                  </p>
                  <ul>
                    <li>账户名称:</li>
                    <li>{{ limitedLiablityA.statement.accountTitle1 }}</li>
                  </ul>
                  <ul>
                    <li style="margin-right: 48px;">日期:</li>
                    <li>{{ limitedLiablityA.statement.time }}</li>
                  </ul>
                  <p class="shili">{{ limitedLiablityA.statement.accountTitle2 }}</p>
                  <ul class="inputQM">
                    <li>签名:</li>
                    <li>
                      <el-form-item>
                        <el-input
                          v-model="limitedLiablityA.statement.accountTitle3"
                          maxlength="30"
                          placeholder="签名"
                        />
                      </el-form-item>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </el-form>
      </div>
      <div v-for="(limitB,index) in limitedLiablityBList" :key="index">
        <div id="crsContentYA" style="margin-top: 100px;">
          <el-form
            ref="limitB"
            status-icon
            :model="limitB"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- main-top -->
              <div class="formNapeCRS">
                <h3 :id="CRSCPId +(index+1)">共同汇报标准（CRS）控权人自我证明表格（CRS-CP#{{ index+1 }}）</h3>
                <div class="topContentCRS">
                  <h4>客户须知：填写此表格前 ，请先阅读本节。</h4>
                  <p>作为财务机构，我们是不允许给予客户税务咨询 。</p>
                  <p class="pCRS2">如果您对此表格或您的税务居民身份定义有任何疑问，请联系您的税务顾问或相关税务机关。您可以 在OECD自动讯息交换网站</p>
                  <p class="pCRS2 wangzhiCRS">
                    (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                    <span>获取更多信息。</span>
                  </p>
                  <!-- <p class="pCRS3">
                  请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。
                </!-->
                  <p class="pCRS3">您可能会被要求提供更多证明文件，以核实此表格上之内容。</p>
                  <p
                    class="pCRS3 lineHeightBD22"
                  >这是由账户持有人向申报财务机构提供的自我证明表格，以作自动交换财务账户资料用途。申报财务机构可把收集所得的资料交给税务局，税务局会将资料转交到另一税务管辖区的税务当局。</p>
                  <p class="pCRS3">除不适用或特别注明外，必须填写这份表格所有部分。在栏/部份标有星号（*）的项目为申报财务机构须向税务局申报的资料。</p>
                </div>
              </div>
              <!-- main-content1 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">第一部分</div>
                  <div class="right">控权人的身份识辨资料</div>
                </div>
                <div class="ContentCRS">
                  <h5>1、控权人的姓名</h5>
                  <el-form-item prop="one.personal.call">
                    <el-select
                      v-model="limitB.one.personal.call"
                      placeholder="称呼"
                      :disabled="true"
                    >
                      <dictionary dict-code="appellationType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.lastName">
                    <el-input
                      v-model="limitB.one.personal.lastName"
                      maxlength="30"
                      placeholder="姓氏"
                    />
                  </el-form-item>
                  <el-form-item prop="one.personal.firstName">
                    <el-input
                      v-model="limitB.one.personal.firstName"
                      maxlength="30"
                      placeholder="名字"
                    />
                  </el-form-item>
                  <el-form-item prop="one.personal.identityType">
                    <el-select
                      v-model="limitB.one.personal.identityType"
                      placeholder="身份证明类型"
                      :disabled="true"
                    >
                      <dictionary dict-code="cardType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.papersNum">
                    <el-input
                      v-model="limitB.one.personal.papersNum"
                      maxlength="30"
                      placeholder="证件号码"
                    />
                  </el-form-item>
                  <el-form-item prop="one.personal.dateBirth">
                    <el-date-picker
                      v-model="limitB.one.personal.dateBirth"
                      type="date"
                      placeholder="出生日期"
                    />
                  </el-form-item>
                </div>
                <div class="ContentCRS">
                  <h5>2、现时住址</h5>
                  <el-form-item prop="one.location.reality.site">
                    <el-input
                      v-model="limitB.one.location.reality.site"
                      maxlength="30"
                      :disabled="true"
                      placeholder="地址（如果空间不足请转下行）"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site1">
                    <el-input
                      v-model="limitB.one.location.reality.site1"
                      maxlength="30"
                      :disabled="true"
                      placeholder="( 接上行) 房间号, 楼层 公寓等"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.city">
                    <el-input
                      v-model="limitB.one.location.reality.city"
                      maxlength="30"
                      :disabled="true"
                      placeholder="城市"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.province">
                    <el-input
                      v-model="limitB.one.location.reality.province"
                      maxlength="30"
                      :disabled="true"
                      placeholder="省/市"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.state">
                    <el-select
                      v-model="limitB.one.location.reality.state"
                      :disabled="true"
                      placeholder="国家"
                    >
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.location.reality.postalCode">
                    <el-input
                      v-model="limitB.one.location.reality.postalCode"
                      maxlength="30"
                      :disabled="true"
                      placeholder="邮政编码"
                    />
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: limitB.one.location.ifcommunicate,fade:true}"
                          @click="limitB.one.location.ifcommunicate=true"
                        >是</li>
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !limitB.one.location.ifcommunicate,fade:true}"
                          @click="limitB.one.location.ifcommunicate=false"
                        >否</li>
                      </transition>
                      <li class="istext">您的通讯是否与上方所填地址不同？</li>
                    </ul>
                  </div>
                  <div v-if="limitB.one.location.ifcommunicate">
                    <el-form-item prop="one.location.communicate.site">
                      <el-input
                        v-model="limitB.one.location.communicate.site"
                        maxlength="30"
                        placeholder="地址（如果空间不足请转下行）"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site1">
                      <el-input
                        v-model="limitB.one.location.communicate.site1"
                        maxlength="30"
                        placeholder="( 接上行) 房间号, 楼层 公寓等"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.city">
                      <el-input
                        v-model="limitB.one.location.communicate.city"
                        maxlength="30"
                        placeholder="城市"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.province">
                      <el-input
                        v-model="limitB.one.location.communicate.province"
                        maxlength="30"
                        placeholder="省/市"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.state">
                      <el-select
                        v-model="limitB.one.location.communicate.state"
                        disabled
                        placeholder="国家"
                      >
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.postalCode">
                      <el-input
                        v-model="limitB.one.location.communicate.postalCode"
                        maxlength="30"
                        placeholder="邮政编码"
                      />
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- main-content2 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">第二部分</div>
                  <div class="right">阁下为控权人的实体账户持有人</div>
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">填写阁下为控权人的实体账户持有人的名称。</h5>
                  <el-form-item v-for="(item,limitBIndex) in limitB.two.TaxNumber" :key="limitBIndex">
                    <el-input v-model="item.name" maxlength="30" placeholder="实体账户持有人的名称（公司名）" />
                  </el-form-item>
                  <div class="addBtn" style="margin-top:0px;">
                    <div class="add" @click="addElementYB(limitB.two.TaxNumber,1)">添加其他号码</div>
                    <div class="cancel" @click="removeElementYB(limitB.two.TaxNumber,1)">取消</div>
                  </div>
                </div>
              </div>
              <!-- main-content3 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">第三部分</div>
                  <div class="right">居留司法管辖区及税务编号或具有等同功能的识辨编号(以下简称『税务编号』)*</div>
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">
                    请提供以下数据，列明(i)账户持有人的居留司法管辖区，亦即账户持有人的税务管辖区(香港包括在内)及(ii)该居留司法管辖区发给账
                    户持有人的税务编号。列出所有(不限于 3 个)居留司法管辖区。
                  </h5>
                  <!-- <li class="C9">如账户持有人的居留司法管辖区超过三个，请另纸填写。</li> -->
                  <li class="C9">如账户持有人是香港税务居民，税务编号是账户持有人的香港身份证号码。</li>
                  <div v-for="(item,limitBThreeIndex) in limitB.three.Tex" :key="limitBThreeIndex">
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li
                            class="isyes"
                            :class="{ dianji: item.ifTaxNumber,fade:true}"
                            @click="item.ifTaxNumber=true"
                          >是</li>
                        </transition>
                        <transition name="fade">
                          <li
                            class="isno"
                            :class="{ dianji: !item.ifTaxNumber,fade:true}"
                            @click="item.ifTaxNumber=false"
                          >否</li>
                        </transition>
                        <li class="istext">您是否有税务编号？</li>
                      </ul>
                    </div>
                    <div v-if="item.ifTaxNumber" style="margin-bottom:10px;">
                      <el-form-item style="margin-bottom:0px;">
                        <el-input v-model="item.TaxinputT" maxlength="30" placeholder />
                      </el-form-item>
                    </div>
                    <div v-if="!item.ifTaxNumber">
                      <el-form-item style="margin-bottom:0px;">
                        <el-radio-group v-model="item.TaxNumberT">
                          <div v-for="(item1,index1) in item.TaxNumberTlist" :key="index1">
                            <el-radio
                              :label="item1.dictValue"
                              @change="item.TwoIfShow= index1 === 1? true:false"
                            >{{ item1.dictDest }}</el-radio>
                            <br>
                            <div
                              v-if="index1 === 1&&item.TwoIfShow ===true"
                              style="margin-bottom:15px;"
                            >
                              <el-form-item style="margin-bottom:0px;margin-left:28px;">
                                <el-input v-model="item.TaxinputF" maxlength="30" placeholder />
                              </el-form-item>
                            </div>
                          </div>
                        </el-radio-group>
                      </el-form-item>
                    </div>
                  </div>
                  <div v-if="false" class="addBtn" style="margin-top:0px;">
                    <div class="add" @click="addElementYB(limitB.three.Tex,2)">添加其他号码</div>
                    <div class="cancel" @click="removeElementYB(limitB.three.Tex)">取消</div>
                  </div>
                </div>
              </div>
              <!-- main-content4 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">第四部分</div>
                  <div class="right">控权人类别</div>
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">就第二部份所载的每个实体，请在适当的方格填上剔号，指出控权人就每个实体所属的控权人类别。</h5>
                  <!-- <li class="C9">如账户持有人的居留司法管辖区超过三个，请另纸填写。</li> -->
                  <li class="C9">如账户持有人是香港税务居民，税务编号是账户持有人的香港身份证号码。</li>
                  <div v-for="(item,limitBFourIndex) in limitB.four.entityList" :key="limitBFourIndex">
                    <h5 style="margin:18px 0px">实体{{ item.entityAccountName }}</h5>
                    <el-form-item>
                      <el-radio-group v-model="item.entityRadio1">
                        <el-radio
                          :label="limitB.four.RtitleList[0].dictValue"
                          @change="item.ifCheck1='1'"
                        >{{ limitB.four.RtitleList[0].dictDest }}</el-radio>
                        <el-form-item
                          v-if="item.ifCheck1==='1'"
                          class="el-form-itemBT12 checkboxGroup"
                        >
                          <el-checkbox-group v-model="item.legalCheck1">
                            <div
                              v-for="(item1,index1) in limitB.four.legalList"
                              :key="index1"
                            >
                              <el-checkbox :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-checkbox>
                              <br>
                            </div>
                          </el-checkbox-group>
                        </el-form-item>
                        <br>
                        <el-radio
                          :label="limitB.four.RtitleList[1].dictValue"
                          @change="item.ifCheck1='2'"
                        >{{ limitB.four.RtitleList[1].dictDest }}</el-radio>
                        <el-form-item
                          v-if="item.ifCheck1==='2'"
                          class="el-form-itemBT12 checkboxGroup"
                        >
                          <el-checkbox-group v-model="item.trustCheck1">
                            <div
                              v-for="(item1,index1) in limitB.four.trustList"
                              :key="index1"
                            >
                              <el-checkbox :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-checkbox>
                              <br>
                            </div>
                          </el-checkbox-group>
                        </el-form-item>
                        <br>
                        <el-radio
                          :label="limitB.four.RtitleList[2].dictValue"
                          @change="item.ifCheck1='3'"
                        >{{ limitB.four.RtitleList[2].dictDest }}</el-radio>
                        <el-form-item
                          v-if="item.ifCheck1==='3'"
                          class="el-form-itemBT12 checkboxGroup"
                        >
                          <el-checkbox-group v-model="item.lawCheck1">
                            <div
                              v-for="(item1,index1) in limitB.four.lawList"
                              :key="index1"
                            >
                              <el-checkbox :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-checkbox>
                              <br>
                            </div>
                          </el-checkbox-group>
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
                  <div class="left">第五部分</div>
                  <div class="right">声明及签署</div>
                </div>
                <div class="ContentCRS ContentCRSFive">
                  <p>
                    本人声明就本人所知所信，本表格内所填报的所有资料和声明均属真实 ，本表格内所填报的所有资料和声明均属真实、正确和完备 、正
                    确和完备。
                  </p>
                  <el-form-item class="elFormItem" prop="statement.selfStatement">
                    <el-radio-group v-model="limitB.statement.selfStatement">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <p
                    class="AgreementAgreed"
                  >本人知悉及同意，财务机构可根据《税务条例》（第112章）有关交换财务账户资料的法律条文，（i）收集本表格所载资料并可备存作自动交换财务账户资料用途及（ii）把该等资料和关于账户持有人及任何须申报账户的资料向香港特别行政区政府税务局申报，从而把资料转交到账户持有人的居留司法管辖区的税务当局。</p>
                  <el-form-item class="elFormItem" prop="statement.selfKnow">
                    <el-radio-group v-model="limitB.statement.selfKnow">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <p
                    class="AgreementAgreed"
                  >本人承诺，如情况有所改变，以致影响本表格第1部份所述的个人的税务居民身份，或引致本表格所载的资料不正确，本人会通知国瑞证券有限公司的相关机构，并会在情况发生改变后的30日内，向所述的相关机构提交一份已适当更新的自我证明表格。</p>
                  <el-form-item class="elFormItem" prop="statement.selfPromiset">
                    <el-radio-group v-model="limitB.statement.selfPromiset">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</p>
                  <el-form-item class="elFormItem" prop="statement.selfAgree">
                    <el-radio-group v-model="limitB.statement.selfAgree">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <div class="signature">
                    <p class="Stitle">签名</p>
                    <p class="Sdescribe">
                      输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括预先填写的信息）准确无误，如有不实，根据《税务条例》第80(2E)
                      条，如任何人在作出自我证明时，在明知一项陈述在要项上属具误导性、虚假或不正确，或罔顾一项陈述是否在要项上属具误导性、虚
                      假或不正确下，作出该项陈述，即属犯罪。一经定罪，可处第3级（即港币$10,000）罚款。(ii)同意数字签名等同于书面签名并且(iii)
                      同意使用电子记录作为您证书/协议的证明。
                    </p>
                    <ul>
                      <li>账户名称:</li>
                      <li>{{ limitB.statement.accountTitle1 }}</li>
                    </ul>
                    <ul>
                      <li style="margin-right: 48px;">日期:</li>
                      <li>{{ limitB.statement.time }}</li>
                    </ul>
                    <p class="shili">{{ limitB.statement.accountTitle2 }}</p>
                    <ul class="inputQM">
                      <li>签名:</li>
                      <li>
                        <el-form-item prop="statement.accountTitle3">
                          <el-input
                            v-model="limitB.statement.accountTitle3"
                            maxlength="30"
                            placeholder="签名"
                          />
                        </el-form-item>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>
    </div>
    <div v-else-if="userInfo.accountType==='partnerShip'" class="crsContent">
      <div id="crsContentYA">
        <el-form
          ref="partnerShipA"
          status-icon
          :model="partnerShipA"
          label-width="0px"
          class="demo-ruleForm"
        >
          <div class="formContent">
            <!-- main-top -->
            <div class="formNapeCRS">
              <h3>共同汇报标准（CRS）实体自我证明表格（共同汇报标准 (CRS - E）</h3>
              <div class="topContentCRS">
                <h4>客户须知：填写此表格前 ，请先阅读本节。</h4>
                <p>作为财务机构，我们是不允许给予客户税务咨询 。</p>
                <p class="pCRS2">如果您对此表格或您的税务居民身份定义有任何疑问，请联系您的税务顾问或相关税务机关。您可以 在OECD自动讯息交换网站</p>
                <p class="pCRS2 wangzhiCRS">
                  (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                  <span>获取更多信息。</span>
                </p>
                <!-- <p class="pCRS3">请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。</p> -->
                <p class="pCRS3">您可能会被要求提供更多证明文件，以核实此表格上之内容。</p>
                <p
                  class="pCRS3 lineHeightBD22"
                >这是由账户持有人向申报财务机构提供的自我证明表格，以作自动交换财务账户资料用途。申报财务机构可把收集所得的资料交给税务局，税务局会将资料转交到另一税务管辖区的税务当局。</p>
                <p class="pCRS3">除不适用或特别注明外，必须填写这份表格所有部分。在栏/部份标有星号（*）的项目为申报财务机构须向税务局申报的资料。</p>
              </div>
            </div>
            <!-- main-content1 -->
            <div class="formNapeCRS">
              <div class="Title">
                <div class="left">第一部分</div>
                <div class="right">实体账户持有人的身分识辨数据</div>
              </div>
              <div class="ContentCRS">
                <h5>1、客户数据</h5>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.client.name"
                    maxlength="30"
                    placeholder="实体或分支机构的法定名称"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.client.administer"
                    maxlength="30"
                    type="textarea"
                    :rows="2"
                    placeholder="实体成立为法团或设立所在的税务管辖区"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.client.ZCnumber"
                    maxlength="30"
                    placeholder="注册号码"
                  />
                </el-form-item>
              </div>
              <div class="ContentCRS">
                <h5>2、现时营业地址</h5>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.location.reality.site"
                    maxlength="30"
                    placeholder="地址（如果空间不足请转下行）"
                    :disabled="true"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.location.reality.site1"
                    maxlength="30"
                    :disabled="true"
                    placeholder="( 接上行) 房间号, 楼层 公寓等"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.location.reality.city"
                    maxlength="30"
                    :disabled="true"
                    placeholder="城市"
                  />
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.location.reality.province"
                    maxlength="30"
                    :disabled="true"
                    placeholder="省/市"
                  />
                </el-form-item>
                <el-form-item>
                  <el-select
                    v-model="partnerShipA.one.location.reality.state"
                    :disabled="true"
                    placeholder="国家"
                  >
                    <dictionary dict-code="country" />
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-input
                    v-model="partnerShipA.one.location.reality.postalCode"
                    maxlength="30"
                    placeholder="邮政编码"
                    :disabled="true"
                  />
                </el-form-item>
                <div class="isBtn">
                  <ul>
                    <transition name="fade">
                      <li
                        class="isyes"
                        :class="{ dianji: partnerShipA.one.location.ifcommunicate,fade:true}"
                        @click="partnerShipA.one.location.ifcommunicate=true"
                      >是</li>
                    </transition>
                    <transition name="fade">
                      <li
                        class="isno"
                        :class="{ dianji: !partnerShipA.one.location.ifcommunicate,fade:true}"
                        @click="partnerShipA.one.location.ifcommunicate=false"
                      >否</li>
                    </transition>
                    <li class="istext">您的通讯是否与上方所填地址不同？</li>
                  </ul>
                </div>
                <div v-if="partnerShipA.one.location.ifcommunicate">
                  <el-form-item>
                    <el-input
                      v-model="partnerShipA.one.location.communicate.site"
                      maxlength="30"
                      placeholder="地址（如果空间不足请转下行）"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="partnerShipA.one.location.communicate.site1"
                      maxlength="30"
                      placeholder="( 接上行) 房间号, 楼层 公寓等"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="partnerShipA.one.location.communicate.city"
                      maxlength="30"
                      placeholder="城市"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="partnerShipA.one.location.communicate.province"
                      maxlength="30"
                      placeholder="省/市"
                    />
                  </el-form-item>
                  <el-form-item>
                    <el-select
                      v-model="partnerShipA.one.location.communicate.state"
                      placeholder="国家"
                    >
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item>
                    <el-input
                      v-model="partnerShipA.one.location.communicate.postalCode"
                      maxlength="30"
                      placeholder="邮政编码"
                    />
                  </el-form-item>
                </div>
              </div>
            </div>
            <!-- main-content2 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第二部分</div>
                <div class="right">实体类别</div>
              </div>
              <div class="ContentCRS">
                <h5 style="margin-bottom:15px">请在适当的方格填上剔号，并提供有关资料。</h5>
                <!-- <li>财务机构</li> -->
                <el-form-item class="titleType">
                  <el-radio-group v-model="partnerShipA.two.titleType">
                    <el-radio label="Financial Institution">财务机构</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div v-if="partnerShipA.two.titleType === 'Financial Institution'" class="marginLeft30">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="partnerShipA.two.financeOrgani">
                      <div v-for="(item,index) in partnerShipA.two.entityTypeList1" :key="index">
                        <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                        <br>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
                <!-- <li>主动非财务实体</li> -->
                <el-form-item class="titleType">
                  <el-radio-group v-model="partnerShipA.two.titleType">
                    <el-radio label="Active NFE">主动非财务实体</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div v-if="partnerShipA.two.titleType === 'Active NFE'" class="marginLeft30">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="partnerShipA.two.activeOrgani.radio1">
                      <el-radio label="1">该非财务实体的股票经常在</el-radio>
                      <br>
                      <el-form-item style="margin-left:28px;margin-bottom: 6px;">
                        <el-input
                          v-model="partnerShipA.two.activeOrgani.input1"
                          maxlength="30"
                          placeholder
                        />
                        <p style="margin-left:28px;color:#999;mrgin-top:0px;margin:0px;">(一个具规模证劵市场)进行买卖</p>
                      </el-form-item>

                      <br>
                      <el-radio label="2">请填写</el-radio>
                      <br>
                      <el-form-item style="margin-left:28px;margin-bottom: 6px;">
                        <el-input
                          v-model="partnerShipA.two.activeOrgani.input2"
                          maxlength="30"
                          placeholder
                        />
                        <p style="margin-left:28px;color:#999;mrgin-top:0px;margin:0px;">的有关连实体，该有关连实体的股票经常在</p>
                      </el-form-item>
                      <el-form-item style="margin-left:28px;margin-bottom: 6px;">
                        <el-input
                          v-model="partnerShipA.two.activeOrgani.input3"
                          maxlength="30"
                          placeholder
                        />
                        <p style="margin-left:28px;color:#999;mrgin-top:0px;margin:0px;">(一个具规模证劵市场)进行买卖</p>
                      </el-form-item>
                      <br>
                      <div v-for="(item,index) in partnerShipA.two.entityTypeList2" :key="index">
                        <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                        <br>
                      </div>
                      <br>
                    </el-radio-group>
                  </el-form-item>
                </div>
                <!-- <li>被动非财务实体</li> -->
                <el-form-item class="titleType">
                  <el-radio-group v-model="partnerShipA.two.titleType">
                    <el-radio label="Passive NFE">被动非财务实体</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div v-if="partnerShipA.two.titleType === 'Passive NFE'" class="marginLeft30">
                  <el-form-item style="margin-bottom:0px;">
                    <el-radio-group v-model="partnerShipA.two.passivityOrgani">
                      <div v-for="(item,index) in partnerShipA.two.entityTypeList3" :key="index">
                        <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                        <br>
                      </div>
                    </el-radio-group>
                  </el-form-item>
                </div>
              </div>
            </div>
            <!-- main-content3 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第三部分</div>
                <div class="right">控权人(如实体账户持有人是被动非财务实体，填写此部)</div>
              </div>
              <div class="ContentCRS">
                <h5 style="margin-bottom:15px">
                  就账户持有人，填写所有控权人的姓名于以下列表内。就法人实体，如行使控制权的并非自然人，控权人会是该法人实体的高级管理人员。
                  每名控权人须分别填写一份共同汇报标准 (CRS) 控权人自我证明表格。
                </h5>
                <el-form-item
                  v-for="(item,index) in partnerShipA.three.controllerName"
                  :key="index"
                >
                  <el-input v-model="item.lastName + item.firstName" maxlength="30" placeholder />
                </el-form-item>
                <div class="addBtn" style="margin-top:0px;">
                  <div
                    class="add"
                    @click="addElementYA(partnerShipA.three.controllerName,1)"
                  >添加其他控权人</div>
                  <div class="cancel" @click="removeElementYA(partnerShipA.three.controllerName)">取消</div>
                </div>
              </div>
            </div>
            <!-- main-content4 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第四部分</div>
                <div class="right">居留司法管辖区及税务编号或具有等同功能的识辨编号(以下简称『税务编号』)*</div>
              </div>
              <div class="ContentCRS">
                <h5 style="margin-bottom:15px">
                  请提供以下数据，列明(i)账户持有人的居留司法管辖区，亦即账户持有人的税务管辖区(香港包括在内)及(ii)该居留司法管辖区发给账
                  户持有人的税务编号。列出所有(不限于 3 个)居留司法管辖区。
                </h5>
                <li class="C9">如账户持有人是香港税务居民，税务编号是其香港商业登记号码。</li>
                <li class="C9">控权人不能取得税务编号。如选取这一理由，请在下方解释控权人不能取得税务编号的原因。</li>
                <div v-for="(item,index) in partnerShipA.four.Tex" :key="index">
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: item.ifTaxNumber,fade:true}"
                          @click="item.ifTaxNumber=true"
                        >是</li>
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !item.ifTaxNumber,fade:true}"
                          @click="item.ifTaxNumber=false"
                        >否</li>
                      </transition>
                      <li class="istext">您是否有税务编号</li>
                    </ul>
                  </div>
                  <div v-if="item.ifTaxNumber" style="margin-bottom:15px;">
                    <el-form-item style="margin-bottom:0px;">
                      <el-input v-model="item.TaxinputT" maxlength="30" placeholder />
                    </el-form-item>
                  </div>
                  <div v-if="!item.ifTaxNumber">
                    <el-form-item style="margin-bottom:0px;">
                      <el-radio-group v-model="item.TaxNumberT">
                        <div v-for="(item1,index1) in item.TaxNumberTlist" :key="index1">
                          <el-radio
                            :label="item1.dictValue"
                            @change="item.TwoIfShow= index1 === 1? true:false"
                          >{{ item1.dictDest }}</el-radio>
                          <br>
                          <div
                            v-if="index1 === 1&&item.TwoIfShow ===true"
                            style="margin-bottom:15px;"
                          >
                            <el-form-item style="margin-bottom:0px;margin-left:28px;">
                              <el-input v-model="item.TaxinputF" maxlength="30" placeholder />
                            </el-form-item>
                          </div>
                        </div>
                      </el-radio-group>
                    </el-form-item>
                  </div>
                </div>
                <!-- <div class="addBtn" style="margin-top:0px;" >
                  <div class="add" @click="addElementYA(partnerShipA.four.Tex,2)">添加其他号码</div>
                  <div class="cancel" @click="removeElementYA(partnerShipA.four.Tex)">取消</div>
                </div>-->
              </div>
            </div>
            <!-- main-content5 -->
            <div class="formNapeCRS elerror">
              <div class="Title">
                <div class="left">第五部分</div>
                <div class="right">声明及签署</div>
              </div>
              <div class="ContentCRS ContentCRSFive">
                <p>
                  本人声明就本人所知所信，本表格内所填报的所有资料和声明均属真实 ，本表格内所填报的所有资料和声明均属真实、正确和完备 、正
                  确和完备。
                </p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="partnerShipA.statement.selfStatement">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <p class="AgreementAgreed">
                  本人知悉及同意，财务机构可根据《税务条例》（第112章）有关交换财务账户资料的法律条文，（i）收集本表格所载资料并可备存作
                  自动交换财务账户资料用途及（ii）把该等资料和关于账户持有人及任何须申报账户的资料向香港特别行政区政府税务局申报，从而把资
                  料转交到账户持有人的居留司法管辖区的税务当局。
                </p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="partnerShipA.statement.selfKnow">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <p class="AgreementAgreed">
                  本人承诺，如情况有所改变，以致影响本表格第1部份所述的个人的税务居民身份，或引致本表格所载的资料不正确，本人会通知国瑞
                  证券有限公司的相关机构，并会在情况发生改变后的30日内，向所述的相关机构提交一份已适当更新的自我证明表格。
                </p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="partnerShipA.statement.selfPromiset">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <p class="AgreementAgreed">本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</p>
                <el-form-item class="elFormItem">
                  <el-radio-group v-model="partnerShipA.statement.selfAgree">
                    <el-radio :label="true">是</el-radio>
                    <el-radio :label="false">否</el-radio>
                  </el-radio-group>
                </el-form-item>
                <div class="signature">
                  <p class="Stitle">签名</p>
                  <p class="Sdescribe">
                    输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括预先填写的信息）准确无误，如有不实，根据《税务条例》第80(2E)
                    条，如任何人在作出自我证明时，在明知一项陈述在要项上属具误导性、虚假或不正确，或罔顾一项陈述是否在要项上属具误导性、虚
                    假或不正确下，作出该项陈述，即属犯罪。一经定罪，可处第3级（即港币$10,000）罚款。(ii)同意数字签名等同于书面签名并且(iii)
                    同意使用电子记录作为您证书/协议的证明。
                  </p>
                  <ul>
                    <li>账户名称:</li>
                    <li>{{ partnerShipA.statement.accountTitle1 }}</li>
                  </ul>
                  <ul>
                    <li style="margin-right: 48px;">日期:</li>
                    <li>{{ partnerShipA.statement.time }}</li>
                  </ul>
                  <p class="shili">{{ partnerShipA.statement.accountTitle2 }}</p>
                  <ul class="inputQM">
                    <li>签名:</li>
                    <li>
                      <el-form-item>
                        <el-input
                          v-model="partnerShipA.statement.accountTitle3"
                          maxlength="30"
                          placeholder="签名"
                        />
                      </el-form-item>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </el-form>
      </div>
      <div v-for="(partnerShipBItem,index) in partnerShipBList" :key="index">
        <div id="crsContentYA" style="margin-top: 100px;">
          <el-form
            ref="partnerShipB"
            status-icon
            :model="partnerShipBItem"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- main-top -->
              <div class="formNapeCRS">
                <h3 :id="CRSCPId +(index+1)">共同汇报标准（CRS）控权人自我证明表格（CRS-CP#{{ index+1 }}）</h3>
                <div class="topContentCRS">
                  <h4>客户须知：填写此表格前 ，请先阅读本节。</h4>
                  <p>作为财务机构，我们是不允许给予客户税务咨询 。</p>
                  <p class="pCRS2">如果您对此表格或您的税务居民身份定义有任何疑问，请联系您的税务顾问或相关税务机关。您可以 在OECD自动讯息交换网站</p>
                  <p class="pCRS2 wangzhiCRS">
                    (http://www.oecd.org/tax/automatic-exchange/crs-implementation-and-assistance/)
                    <span>获取更多信息。</span>
                  </p>
                  <!-- <p class="pCRS3">
                  请注意：如果客户为联名账户，各联名账户持有人必须各自填写一份自我证明表格。
                </!-->
                  <p class="pCRS3">您可能会被要求提供更多证明文件，以核实此表格上之内容。</p>
                  <p
                    class="pCRS3 lineHeightBD22"
                  >这是由账户持有人向申报财务机构提供的自我证明表格，以作自动交换财务账户资料用途。申报财务机构可把收集所得的资料交给税务局，税务局会将资料转交到另一税务管辖区的税务当局。</p>
                  <p class="pCRS3">除不适用或特别注明外，必须填写这份表格所有部分。在栏/部份标有星号（*）的项目为申报财务机构须向税务局申报的资料。</p>
                </div>
              </div>
              <!-- main-content1 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">第一部分</div>
                  <div class="right">控权人的身份识辨资料</div>
                </div>
                <div class="ContentCRS">
                  <h5>1、控权人的姓名</h5>
                  <el-form-item prop="one.personal.call">
                    <el-select
                      v-model="partnerShipBItem.one.personal.call"
                      placeholder="称呼"
                      :disabled="true"
                    >
                      <dictionary dict-code="appellationType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.lastName">
                    <el-input
                      v-model="partnerShipBItem.one.personal.lastName"
                      maxlength="30"
                      placeholder="姓氏"
                    />
                  </el-form-item>
                  <el-form-item prop="one.personal.firstName">
                    <el-input
                      v-model="partnerShipBItem.one.personal.firstName"
                      maxlength="30"
                      placeholder="名字"
                    />
                  </el-form-item>
                  <el-form-item prop="one.personal.identityType">
                    <el-select
                      v-model="partnerShipBItem.one.personal.identityType"
                      placeholder="身份证明类型"
                      :disabled="true"
                    >
                      <dictionary dict-code="cardType" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.personal.papersNum">
                    <el-input
                      v-model="partnerShipBItem.one.personal.papersNum"
                      maxlength="30"
                      placeholder="证件号码"
                    />
                  </el-form-item>
                  <el-form-item prop="one.personal.dateBirth">
                    <el-date-picker
                      v-model="partnerShipBItem.one.personal.dateBirth"
                      type="date"
                      placeholder="出生日期"
                    />
                  </el-form-item>
                </div>
                <div class="ContentCRS">
                  <h5>2、现时住址</h5>
                  <el-form-item prop="one.location.reality.site">
                    <el-input
                      v-model="partnerShipBItem.one.location.reality.site"
                      maxlength="30"
                      :disabled="true"
                      placeholder="地址（如果空间不足请转下行）"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.site1">
                    <el-input
                      v-model="partnerShipBItem.one.location.reality.site1"
                      maxlength="30"
                      :disabled="true"
                      placeholder="( 接上行) 房间号, 楼层 公寓等"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.city">
                    <el-input
                      v-model="partnerShipBItem.one.location.reality.city"
                      maxlength="30"
                      :disabled="true"
                      placeholder="城市"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.province">
                    <el-input
                      v-model="partnerShipBItem.one.location.reality.province"
                      maxlength="30"
                      :disabled="true"
                      placeholder="省/市"
                    />
                  </el-form-item>
                  <el-form-item prop="one.location.reality.state">
                    <el-select
                      v-model="partnerShipBItem.one.location.reality.state"
                      :disabled="true"
                      placeholder="国家"
                    >
                      <dictionary dict-code="country" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="one.location.reality.postalCode">
                    <el-input
                      v-model="partnerShipBItem.one.location.reality.postalCode"
                      maxlength="30"
                      :disabled="true"
                      placeholder="邮政编码"
                    />
                  </el-form-item>
                  <div class="isBtn">
                    <ul>
                      <transition name="fade">
                        <li
                          class="isyes"
                          :class="{ dianji: partnerShipBItem.one.location.ifcommunicate,fade:true}"
                          @click="partnerShipBItem.one.location.ifcommunicate=true"
                        >是</li>
                      </transition>
                      <transition name="fade">
                        <li
                          class="isno"
                          :class="{ dianji: !partnerShipBItem.one.location.ifcommunicate,fade:true}"
                          @click="partnerShipBItem.one.location.ifcommunicate=false"
                        >否</li>
                      </transition>
                      <li class="istext">您的通讯是否与上方所填地址不同？</li>
                    </ul>
                  </div>
                  <div v-if="partnerShipBItem.one.location.ifcommunicate">
                    <el-form-item prop="one.location.communicate.site">
                      <el-input
                        v-model="partnerShipBItem.one.location.communicate.site"
                        maxlength="30"
                        placeholder="地址（如果空间不足请转下行）"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.site1">
                      <el-input
                        v-model="partnerShipBItem.one.location.communicate.site1"
                        maxlength="30"
                        placeholder="( 接上行) 房间号, 楼层 公寓等"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.city">
                      <el-input
                        v-model="partnerShipBItem.one.location.communicate.city"
                        maxlength="30"
                        placeholder="城市"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.province">
                      <el-input
                        v-model="partnerShipBItem.one.location.communicate.province"
                        maxlength="30"
                        placeholder="省/市"
                      />
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.state">
                      <el-select
                        v-model="partnerShipBItem.one.location.communicate.state"
                        disabled
                        placeholder="国家"
                      >
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="one.location.communicate.postalCode">
                      <el-input
                        v-model="partnerShipBItem.one.location.communicate.postalCode"
                        maxlength="30"
                        placeholder="邮政编码"
                      />
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- main-content2 -->
              <div class="formNapeCRS">
                <div class="Title">
                  <div class="left">第二部分</div>
                  <div class="right">阁下为控权人的实体账户持有人</div>
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">填写阁下为控权人的实体账户持有人的名称。</h5>
                  <el-form-item v-for="(item,indexB) in partnerShipBItem.two.TaxNumber" :key="indexB">
                    <el-input v-model="item.name" maxlength="30" placeholder="实体账户持有人的名称（公司名）" />
                  </el-form-item>
                  <div class="addBtn" style="margin-top:0px;">
                    <div class="add" @click="addElementYB(partnerShipBItem.two.TaxNumber,1)">添加其他号码</div>
                    <div class="cancel" @click="removeElementYB(partnerShipBItem.two.TaxNumber,1)">取消</div>
                  </div>
                </div>
              </div>
              <!-- main-content3 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">第三部分</div>
                  <div class="right">居留司法管辖区及税务编号或具有等同功能的识辨编号(以下简称『税务编号』)*</div>
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">
                    请提供以下数据，列明(i)账户持有人的居留司法管辖区，亦即账户持有人的税务管辖区(香港包括在内)及(ii)该居留司法管辖区发给账
                    户持有人的税务编号。列出所有(不限于 3 个)居留司法管辖区。
                  </h5>
                  <!-- <li class="C9">如账户持有人的居留司法管辖区超过三个，请另纸填写。</li> -->
                  <li class="C9">如账户持有人是香港税务居民，税务编号是账户持有人的香港身份证号码。</li>
                  <div v-for="(item,index6) in partnerShipBItem.three.Tex" :key="index6">
                    <div class="isBtn">
                      <ul>
                        <transition name="fade">
                          <li
                            class="isyes"
                            :class="{ dianji: item.ifTaxNumber,fade:true}"
                            @click="item.ifTaxNumber=true"
                          >是</li>
                        </transition>
                        <transition name="fade">
                          <li
                            class="isno"
                            :class="{ dianji: !item.ifTaxNumber,fade:true}"
                            @click="item.ifTaxNumber=false"
                          >否</li>
                        </transition>
                        <li class="istext">您是否有税务编号？</li>
                      </ul>
                    </div>
                    <div v-if="item.ifTaxNumber" style="margin-bottom:10px;">
                      <el-form-item style="margin-bottom:0px;">
                        <el-input v-model="item.TaxinputT" maxlength="30" placeholder />
                      </el-form-item>
                    </div>
                    <div v-if="!item.ifTaxNumber">
                      <el-form-item style="margin-bottom:0px;">
                        <el-radio-group v-model="item.TaxNumberT">
                          <div v-for="(item1,index1) in item.TaxNumberTlist" :key="index1">
                            <el-radio
                              :label="item1.dictValue"
                              @change="item.TwoIfShow= index1 === 1? true:false"
                            >{{ item1.dictDest }}</el-radio>
                            <br>
                            <div
                              v-if="index1 === 1&&item.TwoIfShow ===true"
                              style="margin-bottom:15px;"
                            >
                              <el-form-item style="margin-bottom:0px;margin-left:28px;">
                                <el-input v-model="item.TaxinputF" maxlength="30" placeholder />
                              </el-form-item>
                            </div>
                          </div>
                        </el-radio-group>
                      </el-form-item>
                    </div>
                  </div>
                  <div v-if="false" class="addBtn" style="margin-top:0px;">
                    <div class="add" @click="addElementYB(partnerShipBItem.three.Tex,2)">添加其他号码</div>
                    <div class="cancel" @click="removeElementYB(partnerShipBItem.three.Tex)">取消</div>
                  </div>
                </div>
              </div>
              <!-- main-content4 -->
              <div class="formNapeCRS elerror">
                <div class="Title">
                  <div class="left">第四部分</div>
                  <div class="right">控权人类别</div>
                </div>
                <div class="ContentCRS">
                  <h5 style="margin-bottom:15px">就第二部份所载的每个实体，请在适当的方格填上剔号，指出控权人就每个实体所属的控权人类别。</h5>
                  <!-- <li class="C9">如账户持有人的居留司法管辖区超过三个，请另纸填写。</li> -->
                  <li class="C9">如账户持有人是香港税务居民，税务编号是账户持有人的香港身份证号码。</li>
                  <div v-for="(item,indexY) in partnerShipBItem.four.entityList" :key="indexY">
                    <h5 style="margin:18px 0px">
                      <span>实体{{ item.entityAccountName }}</span>
                    </h5>
                    <el-form-item>
                      <el-radio-group v-model="item.entityRadio1">
                        <el-radio
                          :label="partnerShipBItem.four.RtitleList[0].dictValue"
                          @change="item.ifCheck1='1'"
                        >{{ partnerShipBItem.four.RtitleList[0].dictDest }}</el-radio>
                        <el-form-item
                          v-if="item.ifCheck1==='1'"
                          class="el-form-itemBT12 checkboxGroup"
                        >
                          <el-checkbox-group v-model="item.legalCheck1">
                            <div v-for="(item1,index1) in partnerShipBItem.four.legalList" :key="index1">
                              <el-checkbox :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-checkbox>
                              <br>
                            </div>
                          </el-checkbox-group>
                        </el-form-item>
                        <br>
                        <el-radio
                          :label="partnerShipBItem.four.RtitleList[1].dictValue"
                          @change="item.ifCheck1='2'"
                        >{{ partnerShipBItem.four.RtitleList[1].dictDest }}</el-radio>
                        <el-form-item
                          v-if="item.ifCheck1==='2'"
                          class="el-form-itemBT12 checkboxGroup"
                        >
                          <el-checkbox-group v-model="item.trustCheck1">
                            <div v-for="(item1,index1) in partnerShipBItem.four.trustList" :key="index1">
                              <el-checkbox :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-checkbox>
                              <br>
                            </div>
                          </el-checkbox-group>
                        </el-form-item>
                        <br>
                        <el-radio
                          :label="partnerShipBItem.four.RtitleList[2].dictValue"
                          @change="item.ifCheck1='3'"
                        >{{ partnerShipBItem.four.RtitleList[2].dictDest }}</el-radio>
                        <el-form-item
                          v-if="item.ifCheck1==='3'"
                          class="el-form-itemBT12 checkboxGroup"
                        >
                          <el-checkbox-group v-model="item.lawCheck1">
                            <div v-for="(item1,index1) in partnerShipBItem.four.lawList" :key="index1">
                              <el-checkbox :label="item1.dictValue" name="type">{{ item1.dictDest }}</el-checkbox>
                              <br>
                            </div>
                          </el-checkbox-group>
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
                  <div class="left">第五部分</div>
                  <div class="right">声明及签署</div>
                </div>
                <div class="ContentCRS ContentCRSFive">
                  <p>
                    本人声明就本人所知所信，本表格内所填报的所有资料和声明均属真实 ，本表格内所填报的所有资料和声明均属真实、正确和完备 、正
                    确和完备。
                  </p>
                  <el-form-item class="elFormItem" prop="statement.selfStatement">
                    <el-radio-group v-model="partnerShipBItem.statement.selfStatement">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <p
                    class="AgreementAgreed"
                  >本人知悉及同意，财务机构可根据《税务条例》（第112章）有关交换财务账户资料的法律条文，（i）收集本表格所载资料并可备存作自动交换财务账户资料用途及（ii）把该等资料和关于账户持有人及任何须申报账户的资料向香港特别行政区政府税务局申报，从而把资料转交到账户持有人的居留司法管辖区的税务当局。</p>
                  <el-form-item class="elFormItem" prop="statement.selfKnow">
                    <el-radio-group v-model="partnerShipBItem.statement.selfKnow">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <p
                    class="AgreementAgreed"
                  >本人承诺，如情况有所改变，以致影响本表格第1部份所述的个人的税务居民身份，或引致本表格所载的资料不正确，本人会通知国瑞证券有限公司的相关机构，并会在情况发生改变后的30日内，向所述的相关机构提交一份已适当更新的自我证明表格。</p>
                  <el-form-item class="elFormItem" prop="statement.selfPromiset">
                    <el-radio-group v-model="partnerShipBItem.statement.selfPromiset">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <p class="AgreementAgreed">本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。</p>
                  <el-form-item class="elFormItem" prop="statement.selfAgree">
                    <el-radio-group v-model="partnerShipBItem.statement.selfAgree">
                      <el-radio :label="true">是</el-radio>
                      <el-radio :label="false">否</el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <!-- <h5 style="margin-top: 10px;" class="AgreementAgreed"> 本人同意所有税务表格均用过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。
                </h5>-->
                  <div class="signature">
                    <p class="Stitle">签名</p>
                    <p class="Sdescribe">
                      输入签名并通过网络发送即代表您(i)确认该表格内的信息（包括预先填写的信息）准确无误，如有不实，根据《税务条例》第80(2E)
                      条，如任何人在作出自我证明时，在明知一项陈述在要项上属具误导性、虚假或不正确，或罔顾一项陈述是否在要项上属具误导性、虚
                      假或不正确下，作出该项陈述，即属犯罪。一经定罪，可处第3级（即港币$10,000）罚款。(ii)同意数字签名等同于书面签名并且(iii)
                      同意使用电子记录作为您证书/协议的证明。
                    </p>
                    <ul>
                      <li>账户名称:</li>
                      <li>{{ partnerShipBItem.statement.accountTitle1 }}</li>
                    </ul>
                    <ul>
                      <li style="margin-right: 48px;">日期:</li>
                      <li>{{ partnerShipBItem.statement.time }}</li>
                    </ul>
                    <p class="shili">{{ partnerShipBItem.statement.accountTitle2 }}</p>
                    <ul class="inputQM">
                      <li>签名:</li>
                      <li>
                        <el-form-item prop="statement.accountTitle3">
                          <el-input
                            v-model="partnerShipBItem.statement.accountTitle3"
                            maxlength="30"
                            placeholder="签名"
                          />
                        </el-form-item>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>

    </div>
    <div v-if="partnerShipBList" class="guild">
      <ul v-for="(item,index) in partnerShipBList" :key="index">
        <li><a href="javaScript:void(0)" :class="{guildColor:guildColor[index]}" @click="toPosition($event,index)">crsCp{{ index+1 }}</a></li>
      </ul>
    </div>
    <div v-if="limitedLiablityBList" class="guild">
      <ul v-for="(item,index) in limitedLiablityBList" :key="index">
        <li><a href="javaScript:void(0)" :class="{guildColor:guildColor[index]}" @click="toPosition($event,index)">crsCp{{ index+1 }}</a></li>
      </ul>
    </div>
  </div>
</template>
<script>
// import dictionary from '../dictionary/dictionary';
import dictionary from '../../../../components/dictionary/dictionary';
export default {
  name: 'Regulate',
  components: {
    dictionary
  },
  props: {
    userInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      // crsType: 'bbb',
      personal: {
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
      limitedLiablityA: {
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
          titleType: '1',
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
              name: ''
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
      limitedLiablityB: {
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
              legalCheck1: [],
              trustCheck1: [],
              lawCheck1: []
            }
          ],
          RtitleList: [
            {
              dictDest: '法人',
              dictValue: 'legalPerson'
            },
            {
              dictDest: '信托',
              dictValue: 'controllingPersonType'
            },
            {
              dictDest: '除信托以外的法律安排',
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
      partnerShipA: {
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
          titleType: '1',
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
              name: ''
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
      partnerShipB: {
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
              legalCheck1: [],
              trustCheck1: [],
              lawCheck1: []
            }
          ],
          RtitleList: [
            {
              dictDest: '法人',
              dictValue: 'legalPerson'
            },
            {
              dictDest: '信托',
              dictValue: 'controllingPersonType'
            },
            {
              dictDest: '除信托以外的法律安排',
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
      limitedLiablityBList: [],
      partnerShipBList: [],
      CRSCPId: 'crsCp',
      guildColor: []
    };
  },
  watch: {
    userInfo: {
      // props
      handler(val, oldVal) {
        this.userInfo = val;
        this.Port();
      },
      deep: true
    }
  },
  created() {},
  mounted() {
    this.Port();
  },
  methods: {
    toPosition(val, index) {
      const id = val.target.innerText;
      document.getElementById(`${id}`).scrollIntoView(true);
      // this.guildColor.map((item, colorIndex) => {
      //   item[colorIndex] = false;
      //   return item;
      // });
      // this.guildColor[index] = true;
      // this.$forceUpdate();
    },
    // personal
    // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')}
    // 请求接口后缀  G 个人 Y 有限 H 合伙
    Port() {
      if (this.userInfo.accountType === 'personal') {
        this.getOnePSAinfoG();
        this.getOneITinfoG();
        this.getOnePSAsiteInfoG();
        this.getTaxlistG();
      } else if (this.userInfo.accountType === 'limitedLiablity') {
        this.accountCoAddressYA();
        this.entityTypeYA();
        // this.controllingPersonTypeYB();
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.accountCoAddressHA();
        this.entityTypeHA();
        // this.controllingPersonTypeHB();
        // }
      }
    },
    // 获取用户已填入数据-更新
    getinfoG() {
      this.$http({
        url: '/account/registerOpen/personal/findCrs',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].otherCity) {
                // 其他邮寄地址
                this.personal.one.location.ifcommunicate = true;
                this.personal.one.location.communicate.city =
                  res.object[0].otherCity;
                this.personal.one.location.communicate.state =
                  res.object[0].otherCountry;
                this.personal.one.location.communicate.province =
                  res.object[0].otherProvince;
                this.personal.one.location.communicate.postalCode =
                  res.object[0].otherZipCode;
                if (res.object[0].otherAddresses.indexOf('-#-') !== -1) {
                  const dizhi = res.object[0].otherAddresses.split('-#-');
                  this.personal.one.location.communicate.site = dizhi[0];
                  this.personal.one.location.communicate.site1 = dizhi[1];
                }
              }
              // // 第二部分
              if (res.object[0].noTaxNumber === '') {
                this.personal.two.TaxNumber[0].describeT =
                  res.object[0].yesTaxNumber;
              } else {
                this.personal.two.TaxNumber[0].ifTaxNumberBtn = false;
                this.personal.two.TaxNumber[0].describeF =
                  res.object[0].noTaxNumber;
                this.personal.two.TaxNumber[0].describeFinput =
                  res.object[0].reason;
                if (this.personal.two.TaxNumber[0].describeFinput !== '') {
                  this.personal.two.TaxNumber[0].TwoIfShow = true;
                }
              }
              // 第三部分
              this.personal.statement.selfStatement =
                res.object[0].authenticData;
              this.personal.statement.selfKnow =
                res.object[0].agreeTaxOrdinance;
              this.personal.statement.selfPromiset = res.object[0].provenItself;
              this.personal.statement.selfAgree = res.object[0].consent;
              this.personal.statement.accountTitle3 = res.object[0].signature;
              // 日期
              var date = new Date(+res.object[0].signatureDate); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
              var Y = date.getFullYear() + '-';
              var M =
                (date.getMonth() + 1 < 10
                  ? '0' + (date.getMonth() + 1)
                  : date.getMonth() + 1) + '-';
              var D = date.getDate() + ' ';
              var h =
                (date.getHours() < 10
                  ? '0' + date.getHours()
                  : date.getHours()) + ':';
              var m =
                (date.getMinutes() < 10
                  ? '0' + date.getMinutes()
                  : date.getMinutes()) + ':';
              var s =
                date.getSeconds() < 10
                  ? '0' + date.getSeconds()
                  : date.getSeconds();
              this.personal.statement.time = Y + M + D + h + m + s;
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getOnePSAinfoG() {
      // 获取第一页个人信息
      this.$http({
        url: '/account/registerOpen/personal/findPerInfo',
        method: 'get',
        // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // 姓名 称呼
              this.personal.one.personal.call =
                res.object[0].appellation.dictDest;
              this.personal.one.personal.firstName = res.object[0].lastName;
              this.personal.one.personal.lastName = res.object[0].firstName;

              this.personal.statement.accountTitle1 =
                this.personal.one.personal.firstName +
                ' ' +
                this.personal.one.personal.lastName;
              this.personal.statement.accountTitle2 =
                this.personal.one.personal.firstName +
                this.personal.one.personal.lastName;
              this.personal.statement.accountTitle3 =
                this.personal.one.personal.firstName +
                this.personal.one.personal.lastName;
              // 出生日期
              // this.personal.one.personal.dateBirth = this.$verify.NowDate2(
              //   Number(res.object[0].birthday)
              // );
              this.personal.one.personal.dateBirth = res.object[0].birthday;
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getOneITinfoG() {
      // 获取第一页身份证明
      this.$http({
        url: '/account/registerOpen/personal/findIdentity',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.one.personal.identityType =
                res.object[0].idCardType.dictDest;
              this.personal.one.personal.papersNum = res.object[0].idNumber;
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getOnePSAsiteInfoG() {
      // 获取第一页个人地址
      this.$http({
        url: '/account/registerOpen/personal/findAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  // 个人地址类型
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const dizhi = res.object[i].address.split('-#-');
                    this.personal.one.location.reality.site = dizhi[0];
                    this.personal.one.location.reality.site1 = dizhi[1];
                  } else {
                    this.personal.one.location.reality.site =
                      res.object[i].address; // 居住地址
                  }
                  this.personal.one.location.reality.city = res.object[i].city; // 城市
                  this.personal.one.location.reality.province =
                    res.object[i].rovince; // 省
                  this.personal.one.location.reality.postalCode =
                    res.object[i].zipCode; // 邮编
                  this.personal.one.location.reality.state =
                    res.object[i].country.cnName; // 国家
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getTaxlistG() {
      // 获取字典表 // 税务居住地
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.personal.two.TaxNumber[0].thetaxnumberList = JSON.parse(
              JSON.stringify(res.object)
            );
            this.personal.two.thetaxnumberList = JSON.parse(
              JSON.stringify(res.object)
            );
            this.getinfoG();
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 获取用户已填入的信息
    accountCoCrsControlYA() {
      // crs-e
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findCrsEntity',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // 第一部分
              this.limitedLiablityA.one.client.name = res.object[0].entityName;
              this.limitedLiablityA.one.client.administer =
                res.object[0].taxJurisdiction;
              this.limitedLiablityA.one.client.ZCnumber =
                res.object[0].registrationNumber;

              if (res.object[0].otherAddresses) {
                this.limitedLiablityA.one.location.ifcommunicate = true;
                if (res.object[0].otherAddresses.indexOf('-#-') !== -1) {
                  const address = res.object[0].otherAddresses.split('-#-');
                  this.limitedLiablityA.one.location.communicate.site =
                    address[0];
                  this.limitedLiablityA.one.location.communicate.site1 =
                    address[1];
                } else {
                  this.limitedLiablityA.one.location.communicate.site =
                    res.object[0].otherAddresses;
                }
                this.limitedLiablityA.one.location.communicate.city =
                  res.object[0].otherCity;
                this.limitedLiablityA.one.location.communicate.province =
                  res.object[0].otherProvince;
                this.limitedLiablityA.one.location.communicate.state =
                  res.object[0].otherCountry;
                this.limitedLiablityA.one.location.communicate.postalCode =
                  res.object[0].otherZipCode;
              }
              // 第二部分
              this.limitedLiablityA.two.titleType = res.object[0].entityNumber;
              if (res.object[0].entityNumber === 'Financial Institution') {
                this.limitedLiablityA.two.financeOrgani = res.object[0].entityCategory;
              } else if (res.object[0].entityNumber === 'Active NFE') {
                this.limitedLiablityA.two.activeOrgani.radio1 = res.object[0].entityCategory;
                if (res.object[0].entityInput.indexOf('-#-') !== -1) {
                  const dataTS = res.object[0].entityInput.split('-#-');
                  this.limitedLiablityA.two.activeOrgani.input2 = dataTS[0];
                  this.limitedLiablityA.two.activeOrgani.input3 = dataTS[1];
                } else {
                  this.limitedLiablityA.two.activeOrgani.input1 = res.object[0].entityInput;
                }
              } else if (res.object[0].entityNumber === 'Passive NFE') {
                this.limitedLiablityA.two.passivityOrgani = res.object[0].entityCategory;
              }
              // 第三部分
              this.limitedLiablityA.three.controllerName = res.object[0].accountCoCrsEntityContrs;
              this.controllingPersonTypeYB();
              // if (res.object[0].accountCoCrsEntityContrs.indexOf(',') !== -1) {
              //   const dataTS = res.object[0].accountCoCrsEntityContrs.split(',');
              //   for (let i = 0; i < dataTS.length; i++) {
              //     if (i === 0) {
              //       this.limitedLiablityA.three.controllerName[0].name =
              //         dataTS[0];
              //     } else {
              //       this.limitedLiablityA.three.controllerName.push({
              //         name: dataTS[i]
              //       });
              //     }
              //   }
              // } else {
              //   this.limitedLiablityA.three.controllerName[0].name =
              //     res.object[0].selfCertification;
              // }
              // 第四部分
              if (res.object[0].noTaxNumber === '') {
                this.limitedLiablityA.four.Tex[0].TaxinputT =
                  res.object[0].yesTaxNumber;
              } else {
                this.limitedLiablityA.four.Tex[0].ifTaxNumber = false;
                this.limitedLiablityA.four.Tex[0].TwoIfShow = true;
                this.limitedLiablityA.four.Tex[0].TaxinputF =
                  res.object[0].reason;
                this.limitedLiablityA.four.Tex[0].TaxNumberT =
                  res.object[0].noTaxNumber;
              }

              // 第五部分
              this.limitedLiablityA.statement.selfStatement =
                res.object[0].authenticData;
              this.limitedLiablityA.statement.selfKnow =
                res.object[0].agreeTaxOrdinance;
              this.limitedLiablityA.statement.selfPromiset =
                res.object[0].provenItself;
              this.limitedLiablityA.statement.selfAgree = res.object[0].consent;
              this.limitedLiablityA.statement.accountTitle1 =
                res.object[0].signature;
              this.limitedLiablityA.statement.accountTitle2 =
                res.object[0].signature;
              this.limitedLiablityA.statement.accountTitle3 =
                res.object[0].signature;
              // 日期
              var date = new Date(+res.object[0].signatureDate); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
              var Y = date.getFullYear() + '-';
              var M =
                (date.getMonth() + 1 < 10
                  ? '0' + (date.getMonth() + 1)
                  : date.getMonth() + 1) + '-';
              var D = date.getDate() + ' ';
              var h =
                (date.getHours() < 10
                  ? '0' + date.getHours()
                  : date.getHours()) + ':';
              var m =
                (date.getMinutes() < 10
                  ? '0' + date.getMinutes()
                  : date.getMinutes()) + ':';
              var s =
                date.getSeconds() < 10
                  ? '0' + date.getSeconds()
                  : date.getSeconds();
              this.limitedLiablityA.statement.time = Y + M + D + h + m + s;
              // this.limitedLiablityA.statement.time = this.$verify.NowDate3();
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 字典表获取
    // 实体类型
    entityTypeYA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            // this.limitedLiablityA.four.Tex[0].TaxNumberTlist = res.object;
            this.entityType_1YA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 实体类型-子
    entityType_1YA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_1', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityA.two.entityTypeList1 = res.object;
            this.entityType_2YA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 实体类型-子
    entityType_2YA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_2', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityA.two.entityTypeList2 = res.object;
            this.entityType_3YA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 实体类型-子
    entityType_3YA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_3', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityA.two.entityTypeList3 = res.object;
            this.taxNumberTypeYA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 税收编号
    taxNumberTypeYA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityA.four.Tex[0].TaxNumberTlist = res.object;
            this.accountCoCrsControlYA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 地址 - 被授权人地址
    accountCoAddressYA() {
      // 公司地址信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  this.limitedLiablityA.one.location.reality.state =
                    res.object[i].country.cnName;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const address = res.object[i].address.split('-#-');
                    this.limitedLiablityA.one.location.reality.site =
                      address[0];
                    this.limitedLiablityA.one.location.reality.site1 =
                      address[1];
                  } else {
                    this.limitedLiablityA.one.location.reality.site =
                      res.object[i].address;
                  }
                  this.limitedLiablityA.one.location.reality.city =
                    res.object[i].city;
                  this.limitedLiablityA.one.location.reality.province =
                    res.object[i].rovince;
                  this.limitedLiablityA.one.location.reality.postalCode =
                    res.object[i].zipCode;
                }
              }
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 添加元素
    // index 识别号  --手动判断更改obj的值
    // arr 需要操作的数组
    addElementYA(arr, index) {
      let obj = null;
      if (index === 1) {
        obj = {
          name: ''
        };
        if (arr.length >= 5) {
          return false;
        } else {
          arr.push(JSON.parse(JSON.stringify(obj)));
        }
      } else if (index === 2) {
        obj = {
          ifTaxNumber: true,
          TaxinputT: '',
          TaxinputF: '',
          TaxNumberT: '1',
          TaxNumberTlist: this.limitedLiablityA.four.Tex[0].TaxNumberTlist,
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
    removeElementYA(arr) {
      if (arr.length === 1) {
        return false;
      } else {
        arr.pop();
      }
    },

    accountCoCrsControlYB() {
      // 获取用户已填入的信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findCrsControl',
        method: 'get',
        params: { accountId: this.userInfo.id, crsEntityId: this.limitedLiablityA.three.controllerName[0].coCrsEntityId, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            // if (this.$verify.resStatus(res)) {
            // 第一部分
            // 个人信息
            res.object.map((item, index) => {
              this.limitedLiablityB.one.personal.call =
                item.accountCoCrsControls[0].appellation.dictDest;
              this.limitedLiablityB.one.personal.firstName =
                item.accountCoCrsControls[0].firstName;
              this.limitedLiablityB.one.personal.lastName =
                item.accountCoCrsControls[0].lastName;
              // this.limitedLiablityB.one.personal.dateBirth = this.$verify.NowDate1(
              //   Number(item.accountCoCrsControls[0].birthday)
              // );
              this.limitedLiablityB.one.personal.dateBirth = item.accountCoCrsControls[0].birthday;
              this.limitedLiablityB.one.personal.identityType =
                item.accountCoCrsControls[0].idCardType.dictDest;
              this.limitedLiablityB.one.personal.papersNum =
                item.accountCoCrsControls[0].idNumber;
              // // 现时住址
              item.accountCoCrsControls[0].accountCoAddressList.map(item => {
                this.limitedLiablityB.one.location.reality.state =
                  item.country.cnName;
                if (item.address.indexOf('-#-') !== -1) {
                  const address = item.address.split('-#-');
                  this.limitedLiablityB.one.location.reality.site = address[0];
                  this.limitedLiablityB.one.location.reality.site1 = address[1];
                } else {
                  this.limitedLiablityB.one.location.reality.site =
                    item.address;
                }
                this.limitedLiablityB.one.location.reality.city = item.city;
                this.limitedLiablityB.one.location.reality.province =
                  item.rovince;
                this.limitedLiablityB.one.location.reality.postalCode =
                  item.zipCode;
              });
              const other = item.accountCoCrsControls[0];
              if (other.otherCountry) {
                this.limitedLiablityB.one.location.ifcommunicate = true;
              } else {
                this.limitedLiablityB.one.location.ifcommunicate = false;
              }
              if (other.otherAddresses.indexOf('-#-') !== -1) {
                const otherAddresses = other.otherAddresses.split('-#-');
                this.limitedLiablityB.one.location.communicate.site =
                  otherAddresses[0];
                this.limitedLiablityB.one.location.communicate.site1 =
                    otherAddresses[1];
              } else {
                this.limitedLiablityB.one.location.communicate.site =
                    other.otherAddresses;
              }
              this.limitedLiablityB.one.location.communicate.city =
                  other.otherCity;
              this.limitedLiablityB.one.location.communicate.province =
                  other.otherProvince;
              this.limitedLiablityB.one.location.communicate.state =
                  other.otherCountry;
              this.limitedLiablityB.one.location.communicate.postalCode =
                  other.otherZipCode;
              // 第二部分
              const possessorArr = [];
              const entityArr = [];
              const possessorObj = {};
              const entityObj = {
                ifDisabled: null,
                ifCheck1: null,
                entityRadio1: '',
                legalCheck1: [],
                trustCheck1: [],
                lawCheck1: [],
                entityAccountName: null
              };
              // 第四部分
              item.accountCoCrsControlNums.map(item => {
                possessorObj.name = item.entityAccountName;
                possessorArr.push(JSON.parse(JSON.stringify(possessorObj)));
                entityObj.entityAccountName = item.entityAccountName;
                const arrTS = [];
                entityObj.entityRadio1 = item.entityCategory.dictValue;
                item.controlCategory.map(item => {
                  arrTS.push(item.dictValue);
                  return item;
                });

                if (item.entityCategory.dictValue === 'legalPerson') {
                  entityObj.legalCheck1 = JSON.parse(JSON.stringify(arrTS));
                  entityObj.ifCheck1 = '1';
                } else if (item.entityCategory.dictValue === 'trust') {
                  entityObj.trustCheck1 = JSON.parse(JSON.stringify(arrTS));
                  entityObj.ifCheck1 = '2';
                } else if (
                  item.entityCategory.dictValue ===
                  'Legal Arrangementother than Trust'
                ) {
                  entityObj.lawCheck1 = JSON.parse(JSON.stringify(arrTS));
                  entityObj.ifCheck1 = '3';
                }

                entityArr.push(JSON.parse(JSON.stringify(entityObj)));
                this.limitedLiablityB.two.TaxNumber = JSON.parse(
                  JSON.stringify(possessorArr)
                );
                return item;
              });

              this.limitedLiablityB.four.entityList = JSON.parse(
                JSON.stringify(entityArr)
              );
              // }
              // 第三部分
              if (item.accountCoCrsControls[0].noTaxNumber === '') {
                this.limitedLiablityB.three.Tex[0].TaxinputT =
                  item.accountCoCrsControls[0].yesTaxNumber;
              } else {
                this.limitedLiablityB.three.Tex[0].ifTaxNumber = false;
                this.limitedLiablityB.three.Tex[0].TwoIfShow = true;
                this.limitedLiablityB.three.Tex[0].TaxinputF =
                  item.accountCoCrsControls[0].reason;
                this.limitedLiablityB.three.Tex[0].TaxNumberT =
                  item.accountCoCrsControls[0].noTaxNumber;
              }
              // 第五部分
              this.limitedLiablityB.statement.selfStatement =
                item.accountCoCrsControls[0].authenticData;
              this.limitedLiablityB.statement.selfKnow =
                item.accountCoCrsControls[0].agreeTaxOrdinance;
              this.limitedLiablityB.statement.selfPromiset =
                item.accountCoCrsControls[0].provenItself;
              this.limitedLiablityB.statement.selfAgree = item.accountCoCrsControls[0].consent;
              // this.limitedLiablityB.statement.time = this.$verify.NowDate3();
              // 日期
              var date = new Date(+item.accountCoCrsControls[0].signatureDate); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
              var Y = date.getFullYear() + '-';
              var M =
                (date.getMonth() + 1 < 10
                  ? '0' + (date.getMonth() + 1)
                  : date.getMonth() + 1) + '-';
              var D = date.getDate() + ' ';
              var h =
                (date.getHours() < 10
                  ? '0' + date.getHours()
                  : date.getHours()) + ':';
              var m =
                (date.getMinutes() < 10
                  ? '0' + date.getMinutes()
                  : date.getMinutes()) + ':';
              var s =
                date.getSeconds() < 10
                  ? '0' + date.getSeconds()
                  : date.getSeconds();
              this.limitedLiablityB.statement.time = Y + M + D + h + m + s;
              this.limitedLiablityB.statement.accountTitle1 =
                item.accountCoCrsControls[0].signature;
              this.limitedLiablityB.statement.accountTitle2 =
                item.accountCoCrsControls[0].signature;
              this.limitedLiablityB.statement.accountTitle3 =
                item.accountCoCrsControls[0].signature;
              this.limitedLiablityBList.push(JSON.parse(JSON.stringify(this.limitedLiablityB)));
              return item;
            });
            // }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoCrsControlNumListYB() {
      // 获取用户已填入的信息 -第四部分
      // this.$http({
      //   url: '/account/registerOpen/limitedLiablity/findCrsControlNum',
      //   method: 'get',
      //   params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      // })
      //   .then(res => {
      //     if (res.code === 200) {
      //       console.log(res);

      //       if (this.$verify.resStatus(res)) {
      //         // this.newValue.accountCoCrsControlNumList = res.object;
      //         const possessorArr = [];
      //         const entityArr = [];
      //         for (let i = 0; i < res.object.length; i++) {
      //           // 第二部分
      //           const possessorObj = {
      //             name: null
      //           };
      //           possessorObj.name = res.object[i].entityAccountName;
      //           possessorArr.push(JSON.parse(JSON.stringify(possessorObj)));

      //           // 第四部分
      //           const entityObj = {
      //             ifDisabled: null,
      //             ifCheck1: null,
      //             entityRadio1: '',
      //             legalCheck1: [],
      //             trustCheck1: [],
      //             lawCheck1: [],
      //             entityAccountName: null
      //           };
      //           entityObj.entityAccountName = res.object[i].entityAccountName;
      //           const arrTS = [];
      //           entityObj.entityRadio1 = res.object[i].entityCategory.dictValue;
      //           res.object[i].controlCategory.map(item => {
      //             arrTS.push(item.dictValue);
      //             return item;
      //           });

      //           if (res.object[i].entityCategory.dictValue === 'legalPerson') {
      //             entityObj.legalCheck1 = JSON.parse(JSON.stringify(arrTS));
      //             entityObj.ifCheck1 = '1';
      //           } else if (res.object[i].entityCategory.dictValue === 'trust') {
      //             entityObj.trustCheck1 = JSON.parse(JSON.stringify(arrTS));
      //             entityObj.ifCheck1 = '2';
      //           } else if (
      //             res.object[i].entityCategory.dictValue ===
      //             'Legal Arrangementother than Trust'
      //           ) {
      //             entityObj.lawCheck1 = JSON.parse(JSON.stringify(arrTS));
      //             entityObj.ifCheck1 = '3';
      //           }

      //           entityArr.push(JSON.parse(JSON.stringify(entityObj)));
      //         }
      //         console.log(entityArr);

      //         this.limitedLiablityB.two.TaxNumber = JSON.parse(
      //           JSON.stringify(possessorArr)
      //         );
      //         this.limitedLiablityB.four.entityList = JSON.parse(
      //           JSON.stringify(entityArr)
      //         );
      //       }
      //     } else {
      //       this.$message.error(res.message || res.code);
      //     }
      //   })
      //   .catch(error => {
      //     this.$message.error(error);
      //   });
    },
    // 字典表获取
    // 控权人
    controllingPersonTypeYB() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityB.four.RtitleList = JSON.parse(
              JSON.stringify(res.object)
            );
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_1YB();
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 控权人-子
    controllingPersonType_1YB() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_1', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityB.four.legalList = JSON.parse(
              JSON.stringify(res.object)
            );
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_2YB();
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 控权人-子
    controllingPersonType_2YB() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_2', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityB.four.trustList = JSON.parse(
              JSON.stringify(res.object)
            );
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_3YB();
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 控权人-子
    controllingPersonType_3YB() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_3', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityB.four.lawList = JSON.parse(
              JSON.stringify(res.object)
            );
            this.taxNumberTypeYB();
            this.accountCoCrsControlYB();
            this.accountCoCrsControlNumListYB();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 税收编号
    taxNumberTypeYB() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.limitedLiablityB.three.Tex[0].TaxNumberTlist = res.object;
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 添加元素
    // index 识别号  --手动判断更改obj的值
    // arr 需要操作的数组
    addElementYB(arr, index) {
      let obj = null;
      if (index === 1) {
        // 第二部分
        obj = {
          name: ''
        };
        if (arr.length >= 5) {
          return false;
        } else {
          arr.push(JSON.parse(JSON.stringify(obj)));
          this.limitedLiablityB.four.entityList.push(
            // 根据第二部分控权人 添加第四部分数组
            {
              ifDisabled: null,
              ifCheck1: null,
              entityRadio1: '',
              legalCheck1: [],
              trustCheck1: [],
              lawCheck1: []
            }
          );
        }
      } else if (index === 2) {
        obj = {
          ifTaxNumber: true,
          TaxinputT: '',
          TaxinputF: '',
          TaxNumberT: '1',
          TaxNumberTlist: this.limitedLiablityB.three.Tex[0].TaxNumberTlist,
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
    removeElementYB(arr, index) {
      if (arr.length === 1) {
        return false;
      } else {
        if (index === 1) {
          // 根据第二部分控权人 删除第四部分数组
          this.limitedLiablityB.four.entityList.pop();
        }
        arr.pop();
      }
    },

    // 获取用户已填入的信息
    accountCoCrsControlHA() {
      this.$http({
        url: '/account/registerOpen/partnerShip/findParCrsEntity',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // 第一部分
              this.partnerShipA.one.client.name = res.object[0].entityName;
              this.partnerShipA.one.client.administer =
                res.object[0].taxJurisdiction;
              this.partnerShipA.one.client.ZCnumber =
                res.object[0].registrationNumber;

              if (res.object[0].otherAddresses) {
                this.partnerShipA.one.location.ifcommunicate = true;
                if (res.object[0].otherAddresses.indexOf('-#-') !== -1) {
                  const address = res.object[0].otherAddresses.split('-#-');
                  this.partnerShipA.one.location.communicate.site = address[0];
                  this.partnerShipA.one.location.communicate.site1 = address[1];
                } else {
                  this.partnerShipA.one.location.communicate.site =
                    res.object[0].otherAddresses;
                }
                this.partnerShipA.one.location.communicate.city =
                  res.object[0].otherCity;
                this.partnerShipA.one.location.communicate.province =
                  res.object[0].otherProvince;
                this.partnerShipA.one.location.communicate.state =
                  res.object[0].otherCountry;
                this.partnerShipA.one.location.communicate.postalCode =
                  res.object[0].otherZipCode;
              }
              // 第二部分
              // let entityCategoryStatus = 0; // entityCategory 状态
              // this.partnerShipA.two.titleType = res.object[0].entityNumber;
              // if (res.object[0].entityNumber === 'Financial Institution') {
              //   this.partnerShipA.two.financeOrgani = res.object[0].entityCategory;
              // } else if (res.object[0].entityNumber === 'Active NFE') {
              //   for ( let i = 0; i < this.partnerShipA.two.entityTypeList2.length; i++ ) {
              //     if ( res.object[0].entityCategory.indexOf( this.partnerShipA.two.entityTypeList2[i] ) !== -1 ) {
              //       entityCategoryStatus++;
              //     }
              //   }
              //   if (entityCategoryStatus !== 0) {
              //     this.partnerShipA.two.activeOrgani.radio1 = res.object[0].entityCategory;
              //   } else {
              //     if (res.object[0].entityInput.indexOf('-#-') !== -1) {
              //       const dataTS = res.object[0].entityInput.split('-#-');
              //       this.partnerShipA.two.activeOrgani.input2 = dataTS[0];
              //       this.partnerShipA.two.activeOrgani.input3 = dataTS[1];
              //     } else {
              //       this.partnerShipA.two.activeOrgani.input1 =
              //         res.object[0].entityInput;
              //     }
              //   }
              // } else if (res.object[0].entityNumber === 'Passive NFE') {
              //   this.partnerShipA.two.passivityOrgani =
              //     res.object[0].entityCategory;
              // }
              // let entityCategoryStatus = 0; // entityCategory 状态
              this.partnerShipA.two.titleType = res.object[0].entityNumber;
              if (res.object[0].entityNumber === 'Financial Institution') {
                this.partnerShipA.two.financeOrgani = res.object[0].entityCategory;
              } else if (res.object[0].entityNumber === 'Active NFE') {
                this.partnerShipA.two.activeOrgani.radio1 = res.object[0].entityCategory;
                if (res.object[0].entityInput.indexOf('-#-') !== -1) {
                  const dataTS = res.object[0].entityInput.split('-#-');
                  this.partnerShipA.two.activeOrgani.input2 = dataTS[0];
                  this.partnerShipA.two.activeOrgani.input3 = dataTS[1];
                } else {
                  this.partnerShipA.two.activeOrgani.input1 = res.object[0].entityInput;
                }
              } else if (res.object[0].entityNumber === 'Passive NFE') {
                this.partnerShipA.two.passivityOrgani = res.object[0].entityCategory;
              }
              // 第三部分
              this.partnerShipA.three.controllerName = res.object[0].accountParCrsEntityContrs;
              this.controllingPersonTypeHB();
              // if (res.object[0].selfCertification.indexOf(',') !== -1) {
              //   const dataTS = res.object[0].selfCertification.split(',');
              //   for (let i = 0; i < dataTS.length; i++) {
              //     if (i === 0) {
              //       this.partnerShipA.three.controllerName[0].name = dataTS[0];
              //     } else {
              //       this.partnerShipA.three.controllerName.push({
              //         name: dataTS[i]
              //       });
              //     }
              //   }
              // } else {
              //   this.partnerShipA.three.controllerName[0].name =
              //     res.object[0].selfCertification;
              // }
              // 第四部分
              if (res.object[0].noTaxNumber === '') {
                this.partnerShipA.four.Tex[0].TaxinputT =
                  res.object[0].yesTaxNumber;
              } else {
                this.partnerShipA.four.Tex[0].ifTaxNumber = false;
                this.partnerShipA.four.Tex[0].TwoIfShow = true;
                this.partnerShipA.four.Tex[0].TaxinputF = res.object[0].reason;
                this.partnerShipA.four.Tex[0].TaxNumberT =
                  res.object[0].noTaxNumber;
              }
              // 第五部分

              this.partnerShipA.statement.selfStatement =
                res.object[0].authenticData;
              this.partnerShipA.statement.selfKnow =
                res.object[0].agreeTaxOrdinance;
              this.partnerShipA.statement.selfPromiset =
                res.object[0].provenItself;
              this.partnerShipA.statement.selfAgree = res.object[0].consent;
              this.partnerShipA.statement.accountTitle1 =
                res.object[0].signature;
              this.partnerShipA.statement.accountTitle2 =
                res.object[0].signature;
              this.partnerShipA.statement.accountTitle3 =
                res.object[0].signature;
              // this.partnerShipA.statement.time = this.$verify.NowDate3();
              // 日期
              var date = new Date(+res.object[0].signatureDate); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
              var Y = date.getFullYear() + '-';
              var M =
                (date.getMonth() + 1 < 10
                  ? '0' + (date.getMonth() + 1)
                  : date.getMonth() + 1) + '-';
              var D = date.getDate() + ' ';
              var h =
                (date.getHours() < 10
                  ? '0' + date.getHours()
                  : date.getHours()) + ':';
              var m =
                (date.getMinutes() < 10
                  ? '0' + date.getMinutes()
                  : date.getMinutes()) + ':';
              var s =
                date.getSeconds() < 10
                  ? '0' + date.getSeconds()
                  : date.getSeconds();
              this.partnerShipA.statement.time = Y + M + D + h + m + s;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 字典表获取
    // 实体类型
    entityTypeHA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.entityType_1HA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 实体类型-子
    entityType_1HA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_1', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipA.two.entityTypeList1 = res.object;
            this.entityType_2HA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 实体类型-子
    entityType_2HA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_2', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipA.two.entityTypeList2 = res.object;
            this.entityType_3HA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 实体类型-子
    entityType_3HA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'entityType_3', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipA.two.entityTypeList3 = res.object;
            this.taxNumberTypeHA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 税收编号
    taxNumberTypeHA() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipA.four.Tex[0].TaxNumberTlist = res.object;
            this.accountCoCrsControlHA();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 地址 - 被授权人地址
    accountCoAddressHA() {
      // 公司地址信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  this.partnerShipA.one.location.reality.state =
                    res.object[i].country.cnName;
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const address = res.object[i].address.split('-#-');
                    this.partnerShipA.one.location.reality.site = address[0];
                    this.partnerShipA.one.location.reality.site1 = address[1];
                  } else {
                    this.partnerShipA.one.location.reality.site =
                      res.object[i].address;
                  }
                  this.partnerShipA.one.location.reality.city =
                    res.object[i].city;
                  this.partnerShipA.one.location.reality.province =
                    res.object[i].rovince;
                  this.partnerShipA.one.location.reality.postalCode =
                    res.object[i].zipCode;
                }
              }
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 添加元素
    // index 识别号  --手动判断更改obj的值
    // arr 需要操作的数组
    addElementHA(arr, index) {
      let obj = null;
      if (index === 1) {
        obj = {
          name: ''
        };
        if (arr.length >= 5) {
          return false;
        } else {
          arr.push(JSON.parse(JSON.stringify(obj)));
        }
      } else if (index === 2) {
        obj = {
          ifTaxNumber: true,
          TaxinputT: '',
          TaxinputF: '',
          TaxNumberT: '1',
          TaxNumberTlist: this.partnerShipA.four.Tex[0].TaxNumberTlist,
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
    removeElementHA(arr) {
      if (arr.length === 1) {
        return false;
      } else {
        arr.pop();
      }
    },

    accountCoCrsControlHB() {
      // 获取用户已填入的信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findCrsControl',
        method: 'get',
        params: { accountId: this.userInfo.id, crsEntityId: this.partnerShipA.three.controllerName[0].parCrsEntityId, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // 第一部分
              // 个人信息
              res.object.map((item, index) => {
                this.partnerShipB.one.personal.call =
                item.accountParCrsControls[0].appellation.dictDest;
                this.partnerShipB.one.personal.firstName =
                item.accountParCrsControls[0].firstName;
                this.partnerShipB.one.personal.lastName = item.accountParCrsControls[0].lastName;
                // this.partnerShipB.one.personal.dateBirth = this.$verify.NowDate1(
                //   Number(item.accountParCrsControls[0].birthday)
                // );
                this.partnerShipB.one.personal.dateBirth = item.accountParCrsControls[0].birthday;

                this.partnerShipB.one.personal.identityType =
                item.accountParCrsControls[0].idCardType.dictDest;
                this.partnerShipB.one.personal.papersNum = item.accountParCrsControls[0].idNumber;
                // 现时住址
                item.accountParCrsControls[0].accountParAddressModelList.map(item => {
                  this.partnerShipB.one.location.reality.state =
                  item.country.cnName;
                  if (item.address.indexOf('-#-') !== -1) {
                    const address = item.address.split('-#-');
                    this.partnerShipB.one.location.reality.site = address[0];
                    this.partnerShipB.one.location.reality.site1 = address[1];
                  } else {
                    this.partnerShipB.one.location.reality.site = item.address;
                  }
                  this.partnerShipB.one.location.reality.city = item.city;
                  this.partnerShipB.one.location.reality.province = item.rovince;
                  this.partnerShipB.one.location.reality.postalCode =
                  item.zipCode;
                });
                const other = item.accountParCrsControls[0];
                if (other.otherCountry) {
                  this.partnerShipB.one.location.ifcommunicate = true;
                } else {
                  this.partnerShipB.one.location.ifcommunicate = false;
                }
                if (other.otherAddresses.indexOf('-#-') !== -1) {
                  const otherAddresses = other.otherAddresses.split('-#-');
                  this.partnerShipB.one.location.communicate.site =
                    otherAddresses[0];
                  this.partnerShipB.one.location.communicate.site1 =
                    otherAddresses[1];
                } else {
                  this.partnerShipB.one.location.communicate.site =
                    other.otherAddresses;
                }
                this.partnerShipB.one.location.communicate.city =
                  other.otherCity;
                this.partnerShipB.one.location.communicate.province =
                  other.otherProvince;
                this.partnerShipB.one.location.communicate.state =
                  other.otherCountry;
                this.partnerShipB.one.location.communicate.postalCode =
                  other.otherZipCode;
                // 第二部分
                // const possessorArr = [];
                // const entityArr = [];
                // const possessorObj = {};
                // possessorObj.name = item.accountParCrsControlNums[0].entityAccountName;
                // possessorArr.push(JSON.parse(JSON.stringify(possessorObj)));
                const possessorArr = [];
                const entityArr = [];
                const possessorObj = {};
                const entityObj = {
                  ifDisabled: null,
                  ifCheck1: null,
                  entityRadio1: '',
                  legalCheck1: [],
                  trustCheck1: [],
                  lawCheck1: [],
                  entityAccountName: null
                };
                item.accountParCrsControlNums.map(item => {
                  possessorObj.name = item.entityAccountName;
                  possessorArr.push(JSON.parse(JSON.stringify(possessorObj)));

                  entityObj.entityAccountName = item.entityAccountName;
                  const arrTS = [];
                  entityObj.entityRadio1 = item.entityCategory.dictValue;
                  item.controlCategory.map(item => {
                    arrTS.push(item.dictValue);
                    return item;
                  });

                  if (item.entityCategory.dictValue === 'legalPerson') {
                    entityObj.legalCheck1 = JSON.parse(JSON.stringify(arrTS));
                    entityObj.ifCheck1 = '1';
                  } else if (item.entityCategory.dictValue === 'trust') {
                    entityObj.trustCheck1 = JSON.parse(JSON.stringify(arrTS));
                    entityObj.ifCheck1 = '2';
                  } else if (
                    item.entityCategory.dictValue ===
                  'Legal Arrangementother than Trust'
                  ) {
                    entityObj.lawCheck1 = JSON.parse(JSON.stringify(arrTS));
                    entityObj.ifCheck1 = '3';
                  }

                  entityArr.push(JSON.parse(JSON.stringify(entityObj)));
                  this.partnerShipB.two.TaxNumber = JSON.parse(
                    JSON.stringify(possessorArr)
                  );
                  return item;
                });
                // 第四部分
                // entityObj.entityAccountName = item.accountParCrsControlNums[0].entityAccountName;
                // const arrTS = [];
                // entityObj.entityRadio1 = item.accountParCrsControlNums[0].entityCategory.dictValue;
                // item.accountParCrsControlNums[0].controlCategory.map(item => {
                //   arrTS.push(item.dictValue);
                //   return item;
                // });

                // if (item.accountParCrsControlNums[0].entityCategory.dictValue === 'legalPerson') {
                //   entityObj.legalCheck1 = JSON.parse(JSON.stringify(arrTS));
                //   entityObj.ifCheck1 = '1';
                // } else if (item.accountParCrsControlNums[0].entityCategory.dictValue === 'trust') {
                //   entityObj.trustCheck1 = JSON.parse(JSON.stringify(arrTS));
                //   entityObj.ifCheck1 = '2';
                // } else if (
                //   item.accountParCrsControlNums[0].entityCategory.dictValue ===
                //   'Legal Arrangementother than Trust'
                // ) {
                //   entityObj.lawCheck1 = JSON.parse(JSON.stringify(arrTS));
                //   entityObj.ifCheck1 = '3';
                // }

                // entityArr.push(JSON.parse(JSON.stringify(entityObj)));
                // this.partnerShipB.two.TaxNumber = JSON.parse(
                //   JSON.stringify(possessorArr)
                // );
                this.partnerShipB.four.entityList = JSON.parse(
                  JSON.stringify(entityArr)
                );
                // 第三部分
                if (item.accountParCrsControls[0].noTaxNumber === '') {
                  this.partnerShipB.three.Tex[0].TaxinputT =
                  item.accountParCrsControls[0].yesTaxNumber;
                } else {
                  this.partnerShipB.three.Tex[0].ifTaxNumber = false;
                  this.partnerShipB.three.Tex[0].TwoIfShow = true;
                  this.partnerShipB.three.Tex[0].TaxinputF = item.accountParCrsControls[0].reason;
                  this.partnerShipB.three.Tex[0].TaxNumberT =
                  item.accountParCrsControls[0].noTaxNumber;
                }
                // 第五部分
                this.partnerShipB.statement.selfStatement =
                item.accountParCrsControls[0].authenticData;
                this.partnerShipB.statement.selfKnow =
                item.accountParCrsControls[0].agreeTaxOrdinance;
                this.partnerShipB.statement.selfPromiset =
                item.accountParCrsControls[0].provenItself;
                this.partnerShipB.statement.selfAgree = item.accountParCrsControls[0].consent;
                // 日期
                var date = new Date(+item.accountParCrsControls[0].signatureDate); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
                var Y = date.getFullYear() + '-';
                var M =
                (date.getMonth() + 1 < 10
                  ? '0' + (date.getMonth() + 1)
                  : date.getMonth() + 1) + '-';
                var D = date.getDate() + ' ';
                var h =
                (date.getHours() < 10
                  ? '0' + date.getHours()
                  : date.getHours()) + ':';
                var m =
                (date.getMinutes() < 10
                  ? '0' + date.getMinutes()
                  : date.getMinutes()) + ':';
                var s =
                date.getSeconds() < 10
                  ? '0' + date.getSeconds()
                  : date.getSeconds();
                this.partnerShipB.statement.time = Y + M + D + h + m + s;
                this.partnerShipB.statement.accountTitle1 =
                item.accountParCrsControls[0].signature;
                this.partnerShipB.statement.accountTitle2 =
                item.accountParCrsControls[0].signature;
                this.partnerShipB.statement.accountTitle3 =
                item.accountParCrsControls[0].signature;
                this.partnerShipBList.push(JSON.parse(JSON.stringify(this.partnerShipB)));
                return item;
              });
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoCrsControlNumListHB() {
      // 获取用户已填入的信息 -第四部分
      this.$http({
        url: '/account/registerOpen/partnerShip/findCrsControlNum',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // this.newValue.accountCoCrsControlNumList = res.object;
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
                  legalCheck1: [],
                  trustCheck1: [],
                  lawCheck1: [],
                  entityAccountName: null
                };
                entityObj.entityAccountName = res.object[i].entityAccountName;
                const arrTS = [];
                entityObj.entityRadio1 = res.object[i].entityCategory.dictValue;
                res.object[i].controlCategory.map(item => {
                  arrTS.push(item.dictValue);
                  return item;
                });

                if (res.object[i].entityCategory.dictValue === 'legalPerson') {
                  entityObj.legalCheck1 = JSON.parse(JSON.stringify(arrTS));
                  entityObj.ifCheck1 = '1';
                } else if (res.object[i].entityCategory.dictValue === 'trust') {
                  entityObj.trustCheck1 = JSON.parse(JSON.stringify(arrTS));
                  entityObj.ifCheck1 = '2';
                } else if (
                  res.object[i].entityCategory.dictValue ===
                  'Legal Arrangementother than Trust'
                ) {
                  entityObj.lawCheck1 = JSON.parse(JSON.stringify(arrTS));
                  entityObj.ifCheck1 = '3';
                }

                entityArr.push(JSON.parse(JSON.stringify(entityObj)));
              }
              this.partnerShipB.two.TaxNumber = JSON.parse(
                JSON.stringify(possessorArr)
              );
              this.partnerShipB.four.entityList = JSON.parse(
                JSON.stringify(entityArr)
              );
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 字典表获取
    // 控权人
    controllingPersonTypeHB() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipB.four.RtitleList = JSON.parse(
              JSON.stringify(res.object)
            );
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_1HB();
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 控权人-子
    controllingPersonType_1HB() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_1', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipB.four.legalList = JSON.parse(
              JSON.stringify(res.object)
            );
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_2HB();
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 控权人-子
    controllingPersonType_2HB() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_2', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipB.four.trustList = JSON.parse(
              JSON.stringify(res.object)
            );
          } else {
            this.$message.error(res.message || res.code);
          }
          this.controllingPersonType_3HB();
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 控权人-子
    controllingPersonType_3HB() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'controllingPersonType_3', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipB.four.lawList = JSON.parse(
              JSON.stringify(res.object)
            );
            this.taxNumberTypeHB();
            this.accountCoCrsControlHB();
            this.accountCoCrsControlNumListHB();
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 税收编号
    taxNumberTypeHB() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxNumberType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnerShipB.three.Tex[0].TaxNumberTlist = res.object;
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 添加元素
    // index 识别号  --手动判断更改obj的值
    // arr 需要操作的数组
    addElementHB(arr, index) {
      let obj = null;
      if (index === 1) {
        // 第二部分
        obj = {
          name: ''
        };
        if (arr.length >= 5) {
          return false;
        } else {
          arr.push(JSON.parse(JSON.stringify(obj)));
          this.limitedLiablityB.four.entityList.push(
            // 根据第二部分控权人 添加第四部分数组
            {
              ifDisabled: null,
              ifCheck1: null,
              entityRadio1: '',
              legalCheck1: [],
              trustCheck1: [],
              lawCheck1: []
            }
          );
        }
      } else if (index === 2) {
        obj = {
          ifTaxNumber: true,
          TaxinputT: '',
          TaxinputF: '',
          TaxNumberT: '1',
          TaxNumberTlist: this.limitedLiablityB.three.Tex[0].TaxNumberTlist,
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
    removeElementHB(arr, index) {
      if (arr.length === 1) {
        return false;
      } else {
        if (index === 1) {
          // 根据第二部分控权人 删除第四部分数组
          this.limitedLiablityB.four.entityList.pop();
        }
        arr.pop();
      }
    }
  }
};
</script>
<style lang="scss" scoped>
$BGcolor: #ff5949;
$CorFFF: #fff;
$Cor999: #999;
$Cor666: #666;
$Cor333: #333;
$CorF6: #f6f6f6;
$CorFe: #fefefe;
$CorEM: #3a8ee6;
.guild{
  position: fixed;
  top: 225px;
  right: 100px;
  // .guildColor{
  //   background-color: red;
  // }
}
.crsContent {
  pointer-events: none;
  width: 90%;
  margin-top: 20px;
  padding: 44px;
  border: 1px solid $Cor999;
  border-radius: 5px;
  .checkboxGroup {
    margin-left: 20px;
  }
  > div:first-child {
    margin-top: 40px;
  }
  .formNapeCRS {
    // CRS中每个item
    margin-top: 40px;
    margin-bottom: 40px;
    h3 {
      //top 区域
      font-size: 20px;
      font-weight: 400;
      color: rgba(67, 67, 67, 1);
      padding-bottom: 20px;
      border-bottom: 2px solid $Cor999;
      margin-bottom: 40px;
    }
    .topContentCRS {
      > h4 {
        color: #434343;
        font-size: 18px;
        margin-bottom: 20px;
      }
      > p:first-of-type {
        font-size: 16px;
        color: #434343;
      }
      .pCRS2 {
        margin-top: 10px;
        color: $Cor999;
      }
      .pCRS2.wangzhiCRS {
        margin-top: 3px;
        color: #4e99e5;
        span {
          color: $Cor999;
        }
      }
      .pCRS3 {
        color: $Cor999;
        margin-top: 13px;
        // line-height: 22px;
      }
    }

    .Title {
      //content 区域
      width: 100%;
      height: 34px;
      margin-bottom: 30px;
      border-top: 1px solid #434343;
      border-bottom: 1px solid #434343;
      > div {
        float: left;
        line-height: 34px;
        font-size: 16px;
      }
      .left {
        width: 180px;
        height: 34px;
        background-color: #434343;
        text-align: center;
        color: #f6f6f6;
      }
      .right {
        margin-left: 20px;
      }
    }
    .ContentCRS {
      h5 {
        font-size: 16px;
        color: #434343;
        margin-bottom: 20px;
        margin-top: 30px;
        line-height: 22px;
      }
      > li {
        list-style: disc;
        font-size: 16px;
        color: #999;
        margin: 15px 0px;
      }
      .el-radio {
        margin-bottom: 10px;
      }
      .isBtn .istext {
        font-size: 16px;
      }
      > p {
        font-size: 16px;
        color: #999;
        line-height: 22px;
      }
      .elFormItem {
        margin-bottom: 5px;
      }
      .signature {
        margin-top: 28px;
        font-size: 16px;

        .Stitle {
          color: #666;
          font-size: 16px;
          line-height: 22px;
          margin-bottom: 16px;
        }
        .Sdescribe {
          color: #999;
          margin-bottom: 20px;
          line-height: 22px;
        }
        ul {
          display: block;
          height: 14px;
          padding-left: 0px;
          li {
            font-size: 14px;
            color: #666;
            float: left;
          }
          li:first-of-type {
            color: #434343;
            margin-right: 20px;
          }
          margin-bottom: 12px;
        }
        .shili {
          margin-top: 20px;
        }
        .inputQM {
          line-height: 34px;
          margin-top: 10px;
          font-size: 16px;
          .el-form-item {
            width: 400px;
          }
        }
      }
    }
  }
}
#crsContentYA {
  .formNapeCRS {
    .ContentCRS {
      > li {
        list-style: none;
        color: #666;
      }
      > li.C9 {
        list-style: disc;
        color: #999;
      }
    }
  }
  .formContent .formNapeCRS .ContentCRSFive .el-radio {
    margin-bottom: 0px;
  }
  .formContent .formNapeCRS .ContentCRSFive .AgreementAgreed {
    margin-top: 11px;
  }
  .titleType {
    margin-bottom: 0px;
    margin-top: 10px;
  }
  .marginLeft30 {
    margin-left: 30px;
  }
}
</style>
