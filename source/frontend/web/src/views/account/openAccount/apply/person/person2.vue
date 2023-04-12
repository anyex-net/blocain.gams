<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG noprint">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(2/10) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <!--startprint-->
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="person2"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="person2"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <!-- 规管信息 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.person.person2.regulate.titel') }}</h3> <!-- $i18n - 规管信息 -->
                  <!-- <p>无法汇款单反馈</p> -->
                </div>
                <div class="formNapeContent">
                  <div class="isBtn">
                    <ul class="height50">
                      <li
                        class="zindex1"
                        :class="{ dianji: person2.isstate.ggisshowa }"
                        @click="ggisshow11()"
                      >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      <li
                        class="zindex1"
                        :class="{ dianji: !person2.isstate.ggisshowa }"
                        @click="ggisshow12()"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      <li class="istext relativeTeshu">
                        {{ $t('registerOpenAccount.openAccount.person.person2.regulate.describe1a') }}
                      </li> <!-- $i18n - 账户持有人或同一居所内居住的任何直系亲属是否为注册经纪-自营商；证券或商品经纪公司 . . . -->
                    </ul>
                  </div>
                  <div v-show="person2.isstate.ggisshowa">
                    <el-form-item prop="require">
                      <el-input v-model="person2.require" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.person.person2.regulate.companyName')" /> <!-- $i18n - 请输入公司名称 -->
                    </el-form-item>
                    <el-form-item prop="relation">
                      <el-select v-model="person2.relation" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.person.person2.regulate.relation')"> <!-- $i18n - 与账户持有人关系 -->
                        <dictionary dict-code="accountHolderRelationship" />
                      </el-select>
                    </el-form-item>
                    <el-form-item prop="familynum">
                      <el-input v-model="person2.familynum" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.person.person2.regulate.member')" /> <!-- $i18n - 与公司有关联的家庭成员 -->
                    </el-form-item>
                  </div>
                  <div class="isBtn">
                    <ul>
                      <li
                        class="zindex1"
                        :class="{ dianji: person2.isstate.ggisshowb }"
                        @click="ggisshow21()"
                      >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                      <li
                        class="zindex1"
                        :class="{ dianji: !person2.isstate.ggisshowb }"
                        @click="ggisshow22()"
                      >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                      <li class="istext relativeTeshu">{{ $t('registerOpenAccount.openAccount.person.person2.regulate.describe2') }}</li> <!-- $i18n - 账户持有人是否是一家公共上市公司的董事，持10%股权的股东或决策人？ -->
                    </ul>
                  </div>
                  <div v-show="person2.isstate.ggisshowb">
                    <div style="line-height: 26px; margin-top: 40px;color:#666;">{{ $t('registerOpenAccount.openAccount.person.person2.regulate.stockCode') }}</div> <!-- $i18n - 输入公司的股票代码，以逗号隔开： -->
                    <el-form-item label prop="GSstockcode">
                      <el-input v-model="person2.GSstockcode" maxlength="300" :placeholder="$t('registerOpenAccount.common.formPrompt.required')" /> <!-- $i18n - 必填 -->
                    </el-form-item>
                  </div>
                </div>
                <div style="color: red">
                  <p v-for="(item, index) in forObj(person2.ifPageLogicJudgment)" :key="index">{{ item }}</p>
                </div>
                <el-dialog class="errmodalBox" :title="$t('registerOpenAccount.openAccount.common.else.errorHint')" :visible.sync="dialogVisible" width="30%"> <!-- $i18n - 错误提示 -->
                  <span style="font-size:18px;color:#FF5949">
                    <div>
                      <p v-for="(item, index) in forObj(person2.ifPageLogicJudgment)" :key="index" class="describeText">{{ item }}</p>
                      <el-button style="float: right;" size="mini" @click="dialogVisible = false">{{ $t('registerOpenAccount.common.btn.close') }}</el-button> <!-- $i18n - 关闭 -->
                    </div>
                  </span>
                  <span slot="footer" class="dialog-footer" />
                </el-dialog>
              </div>
              <!-- 收入和资产值 -->
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.IncomeAssets.title') }}</h3> <!-- $i18n - 收入和资产值 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.IncomeAssets.describe') }}</p> <!-- $i18n - 请在下面提供财务信息，我个人合规部门将通过这些信息确定您是否有资格交易现在 . . . -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="asset.zichan1" :label="$t('registerOpenAccount.openAccount.common.IncomeAssets.yearIncome')" :class="{ LeftRightColumnsEN: lang === 'en_US', LeftRightColumns: true }"> <!-- $i18n - 年度净收入（USD） -->
                    <el-select v-model="person2.asset.zichan1" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"> <!-- $i18n - 请选择 -->
                      <el-option v-for="(item,index) in person2.asset.zichan1List" :key="index" :label="item.dictDest" :value="item.dictValue" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="asset.zichan2" :label="$t('registerOpenAccount.openAccount.common.IncomeAssets.assetValue')" :class="{ LeftRightColumnsEN: lang === 'en_US', LeftRightColumns: true }"> <!-- $i18n - 资产净值（USD） -->
                    <el-select v-model="person2.asset.zichan2" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"> <!-- $i18n - 请选择 -->
                      <el-option v-for="(item,index) in person2.asset.zichan2List" :key="index" :label="item.dictDest" :value="item.dictValue" />
                    </el-select>
                  </el-form-item>
                  <el-form-item prop="asset.zichan3" :label="$t('registerOpenAccount.openAccount.common.IncomeAssets.flowAsset')" :class="{ LeftRightColumnsEN: lang === 'en_US', LeftRightColumns: true }"> <!-- $i18n - 流动资产净值（USD） -->
                    <el-select v-model="person2.asset.zichan3" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"> <!-- $i18n - 请选择 -->
                      <el-option v-for="(item,index) in person2.asset.zichan3List" :key="index" :label="item.dictDest" :value="item.dictValue" />
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.investment.title') }}</h3> <!-- $i18n - 投资目标及交易意图 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.investment.describe') }}</p> <!-- $i18n - 您在下方的选择将决定您能够获 . . . -->
                </div>
                <div class="formNapeContent">
                  <el-form-item prop="touzimubiao">
                    <el-checkbox-group v-model="person2.touzimubiao">
                      <el-checkbox
                        v-for="(item,index) in person2.touzimubiaoList"
                        :key="index"
                        :label="item.dictValue"
                        @change="setInvestmentPresent(item.dictValue,person2.touzimubiao)"
                      >{{ item.dictDest }}</el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </div>
              </div>
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.title') }}</h3> <!-- $i18n - 交易许可与经验 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.describe') }}</p> <!-- $i18n - 您在下方的选择将决定您能够 . . . -->
                </div>
                <div class="formNapeContent">
                  <!-- 股票 -->
                  <div class="jiaoyixuke">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[0].isBox=!person2.JYxuke[0].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[0].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[0].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(1,'Stock')" />
                    </div>
                    <transition name="fade">
                      <div v-show="person2.JYxuke[0].isBox" class="jybottom fade">
                        <el-form-item label label-width="0px">
                          <div class="deal-text zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.experAndknowledge') }}</div> <!-- $i18n - 经验与交易知识 -->
                          <div class="bottom-xian" />
                          <el-col :span="5">
                            <el-form-item prop="JYxuke[0].ZSlevel">
                              <el-select v-model="person2.JYxuke[0].ZSlevel" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.knowledge')" @change="setTradingLicense('Stock')"> <!-- $i18n - 知识水平为零 -->
                                <dictionary dict-code="knowledge" />
                              </el-select>
                            </el-form-item>
                          </el-col>
                          <el-col :span="5" class="maleft">
                            <el-form-item prop="JYxuke[0].JYtrading">
                              <el-select v-model="person2.JYxuke[0].JYtrading" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.experience')" @change="setTradingLicense('Stock')"> <!-- $i18n - 交易经验 -->
                                <dictionary dict-code="tradingExperience" />
                              </el-select>
                            </el-form-item>
                          </el-col>
                          <el-col :span="5" class="maleft">
                            <el-form-item prop="JYxuke[0].MNtransactionnum">
                              <el-select
                                v-model="person2.JYxuke[0].MNtransactionnum"
                                :no-match-text="$t('account.manage.no_data')"
                                :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.yearNumbei')"
                                @change="setTradingLicense('Stock')"
                              > <!-- $i18n - 每年交易笔数 -->
                                <dictionary dict-code="yearTransactionsPerNumber" />
                              </el-select>
                            </el-form-item>
                          </el-col>
                          <el-col :span="6" class="zongjiaoyijuli">
                            <span class="zhuce3BG">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.sumNumbei') }}:</span> <!-- $i18n - 总交易笔数 -->
                          &nbsp;
                            <span>{{ person2.zongjiaoyiBishu1 }}</span>
                          </el-col>

                        </el-form-item>
                      </div>
                    </transition>
                    <transition name="fade">
                      <!-- 地点 -->
                      <div v-show="person2.JYxuke[0].isBox" transiton="fade" class="jyDidianBom pleaseEnter">
                        <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                        <!-- <div class="xian"></div> -->
                        <div class="bottom10">
                          <el-form-item label label-width="0px">
                            <el-checkbox
                              v-model="person2.JYxuke[0].suoyouYatai"
                              :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAsiaPacific')"
                              name="type"
                              @change="handleCheckAllChange1a(0,0,'Stock')"
                            /> <!-- $i18n - 所有亚太 -->
                          </el-form-item>
                          <el-form-item prop="JYxuke[0].yatai">
                            <el-checkbox-group
                              v-model="person2.JYxuke[0].yatai"
                              @change="handleCheckAllChange1b(0,0,'Stock')"
                            >
                              <el-checkbox v-for="(item,index) in person2.JYxuke[0].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                            </el-checkbox-group>
                          </el-form-item>
                        </div>
                        <div class="bottom10">
                          <el-form-item label label-width="0px">
                            <el-checkbox
                              v-model="person2.JYxuke[0].suoyouOuzhou"
                              :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allEurope')"
                              name="type"
                              @change="handleCheckAllChange1a(0,1,'Stock')"
                            /> <!-- $i18n - 所有欧洲 -->
                          </el-form-item>
                          <el-form-item prop="JYxuke[0].ouzhou">
                            <el-checkbox-group
                              v-model="person2.JYxuke[0].ouzhou"
                              @change="handleCheckAllChange1b(0,1,'Stock')"
                            >
                              <el-checkbox v-for="(item,index) in person2.JYxuke[0].ouzhouList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                            </el-checkbox-group>
                          </el-form-item>
                        </div>
                        <div class="bottom10">
                          <el-form-item label label-width="0px">
                            <el-checkbox
                              v-model="person2.JYxuke[0].suoyouBeimei"
                              :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAmerica')"
                              name="type"
                              @change="handleCheckAllChange1a(0,2,'Stock')"
                            /> <!-- $i18n - 所有北美 -->
                          </el-form-item>
                          <el-form-item prop="JYxuke[0].beimei">
                            <el-checkbox-group
                              v-model="person2.JYxuke[0].beimei"
                              @change="handleCheckAllChange1b(0,2,'Stock')"
                            >
                              <el-checkbox v-for="(item,index) in person2.JYxuke[0].beimeiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                            </el-checkbox-group>
                          </el-form-item>
                        </div>
                      </div>
                    </transition>
                  </div>
                  <!-- 债券 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[1].isBox=!person2.JYxuke[1].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[1].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[1].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(2,'Bond')" />
                    </div>
                    <div v-show="person2.JYxuke[1].isBox" class="jybottom">
                      <el-form-item label label-width="0px">
                        <div class="deal-text zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.experAndknowledge') }}</div> <!-- $i18n - 经验与交易知识 -->
                        <div class="bottom-xian" />
                        <el-col :span="5">
                          <el-form-item prop="JYxuke[1].ZSlevel">
                            <el-select v-model="person2.JYxuke[1].ZSlevel" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.knowledge')" @change="setTradingLicense('Bond')"> <!-- $i18n - 知识水平为零 -->
                              <dictionary dict-code="knowledge" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[1].JYtrading">
                            <el-select v-model="person2.JYxuke[1].JYtrading" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.experience')" @change="setTradingLicense('Bond')"> <!-- $i18n - 交易经验 -->
                              <dictionary dict-code="tradingExperience" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[1].MNtransactionnum">
                            <el-select
                              v-model="person2.JYxuke[1].MNtransactionnum"
                              :no-match-text="$t('account.manage.no_data')"
                              :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.yearNumbei')"
                              @change="setTradingLicense('Bond')"
                            > <!-- $i18n - 每年交易笔数 -->
                              <dictionary dict-code="yearTransactionsPerNumber" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="6" class="zongjiaoyijuli">
                          <span class="zhuce3BG">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.sumNumbei') }}:</span> <!-- $i18n - 总交易笔数 -->
                          &nbsp;
                          <span>{{ person2.zongjiaoyiBishu2 }}</span>
                        </el-col>

                      </el-form-item>
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[1].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                      <!-- <div class="xian"></div> -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[1].suoyouYatai"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAsiaPacific')"
                            name="type"
                            @change="handleCheckAllChange1a(1,0,'Bond')"
                          /> <!-- $i18n - 所有亚太 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[1].yatai">
                          <el-checkbox-group
                            v-model="person2.JYxuke[1].yatai"
                            @change="handleCheckAllChange1b(1,0,'Bond')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[1].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[1].suoyouOuzhou"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allEurope')"
                            name="type"
                            @change="handleCheckAllChange1a(1,1,'Bond')"
                          /> <!-- $i18n - 所有欧洲 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[1].ouzhou">
                          <el-checkbox-group
                            v-model="person2.JYxuke[1].ouzhou"
                            @change="handleCheckAllChange1b(1,1,'Bond')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[1].ouzhouList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[1].suoyouBeimei"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAmerica')"
                            name="type"
                            @change="handleCheckAllChange1a(1,2,'Bond')"
                          /> <!-- $i18n - 所有北美 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[1].beimei">
                          <el-checkbox-group
                            v-model="person2.JYxuke[1].beimei"
                            @change="handleCheckAllChange1b(1,2,'Bond')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[1].beimeiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>

                  </div>
                  <!-- 权证 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[2].isBox=!person2.JYxuke[2].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[2].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[2].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(3,'Warrant')" />
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[2].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                      <!-- <div class="xian"></div> -->
                      <div class="bottom10">
                        <el-form-item prop="JYxuke[2].yatai">
                          <el-checkbox-group
                            v-model="person2.JYxuke[2].yatai"
                            @change="TraSpecialHandling(person2.JYxuke[2].yatai,'Warrant')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[2].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 期权 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[3].isBox=!person2.JYxuke[3].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[3].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[3].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(4,'Options')" />
                    </div>
                    <div v-show="person2.JYxuke[3].isBox" class="jybottom">
                      <el-form-item label label-width="0px">
                        <div class="deal-text zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.experAndknowledge') }}</div> <!-- $i18n - 经验与交易知识 -->
                        <div class="bottom-xian" />
                        <el-col :span="5">
                          <el-form-item prop="JYxuke[3].ZSlevel">
                            <el-select v-model="person2.JYxuke[3].ZSlevel" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.knowledge')" @change="setTradingLicense('Options')"> <!-- $i18n - 知识水平为零 -->
                              <dictionary dict-code="knowledge" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[3].JYtrading">
                            <el-select v-model="person2.JYxuke[3].JYtrading" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.experience')" @change="setTradingLicense('Options')"> <!-- $i18n - 交易经验 -->
                              <dictionary dict-code="tradingExperience" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[3].MNtransactionnum">
                            <el-select
                              v-model="person2.JYxuke[3].MNtransactionnum"
                              :no-match-text="$t('account.manage.no_data')"
                              :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.yearNumbei')"
                              @change="setTradingLicense('Options')"
                            > <!-- $i18n - 每年交易笔数 -->
                              <dictionary dict-code="yearTransactionsPerNumber" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="6" class="zongjiaoyijuli">
                          <span class="zhuce3BG">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.sumNumbei') }}:</span> <!-- $i18n - 总交易笔数 -->
                          &nbsp;
                          <span>{{ person2.zongjiaoyiBishu4 }}</span>
                        </el-col>

                      </el-form-item>
                      <!-- <div class="">
                        <el-form-item>
                          <el-checkbox-group
                            v-model="person2.JYxuke[3].shouxianJY"
                          >
                            <el-checkbox :label="true" name="type">受交易权限</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div> -->
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[3].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                      <!-- <div class="xian"></div> -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[3].suoyouYatai"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAsiaPacific')"
                            name="type"
                            @change="handleCheckAllChange1a(3,0,'Options')"
                          /> <!-- $i18n - 所有亚太 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[3].yatai">
                          <el-checkbox-group
                            v-model="person2.JYxuke[3].yatai"
                            @change="handleCheckAllChange1b(3,0,'Options')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[3].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[3].suoyouOuzhou"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allEurope')"
                            name="type"
                            @change="handleCheckAllChange1a(3,1,'Options')"
                          /> <!-- $i18n - 所有欧洲 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[3].ouzhou">
                          <el-checkbox-group
                            v-model="person2.JYxuke[3].ouzhou"
                            @change="handleCheckAllChange1b(3,1,'Options')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[3].ouzhouList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[3].suoyouBeimei"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAmerica')"
                            name="type"
                            @change="handleCheckAllChange1a(3,2,'Options')"
                          /> <!-- $i18n - 所有北美 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[3].beimei">
                          <el-checkbox-group
                            v-model="person2.JYxuke[3].beimei"
                            @change="handleCheckAllChange1b(3,2,'Options')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[3].beimeiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 期货 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[4].isBox=!person2.JYxuke[4].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[4].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[4].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(5,'Futures')" />
                    </div>
                    <div v-show="person2.JYxuke[4].isBox" class="jybottom">
                      <el-form-item label label-width="0px">
                        <div class="deal-text zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.experAndknowledge') }}</div> <!-- $i18n - 经验与交易知识 -->
                        <div class="bottom-xian" />
                        <el-col :span="5">
                          <el-form-item prop="JYxuke[4].ZSlevel">
                            <el-select v-model="person2.JYxuke[4].ZSlevel" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.knowledge')" @change="setTradingLicense('Futures')"> <!-- $i18n - 知识水平为零 -->
                              <dictionary dict-code="knowledge" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[4].JYtrading">
                            <el-select v-model="person2.JYxuke[4].JYtrading" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.experience')" @change="setTradingLicense('Futures')"> <!-- $i18n - 交易经验 -->
                              <dictionary dict-code="tradingExperience" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[4].MNtransactionnum">
                            <el-select
                              v-model="person2.JYxuke[4].MNtransactionnum"
                              :no-match-text="$t('account.manage.no_data')"
                              :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.yearNumbei')"
                              @change="setTradingLicense('Futures')"
                            > <!-- $i18n - 每年交易笔数 -->
                              <dictionary dict-code="yearTransactionsPerNumber" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="6" class="zongjiaoyijuli">
                          <span class="zhuce3BG">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.sumNumbei') }}:</span> <!-- $i18n - 总交易笔数 -->
                          &nbsp;
                          <span>{{ person2.zongjiaoyiBishu5 }}</span>
                        </el-col>
                      </el-form-item>
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[4].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                      <!-- <div class="xian"></div> -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[4].suoyouYatai"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAsiaPacific')"
                            name="type"
                            @change="handleCheckAllChange1a(4,0,'Futures')"
                          /> <!-- $i18n - 所有亚太 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[4].yatai">
                          <el-checkbox-group
                            v-model="person2.JYxuke[4].yatai"
                            @change="handleCheckAllChange1b(4,0,'Futures')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[4].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[4].suoyouOuzhou"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allEurope')"
                            name="type"
                            @change="handleCheckAllChange1a(4,1,'Futures')"
                          /> <!-- $i18n - 所有欧洲 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[4].ouzhou">
                          <el-checkbox-group
                            v-model="person2.JYxuke[4].ouzhou"
                            @change="handleCheckAllChange1b(4,1,'Futures')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[4].ouzhouList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[4].suoyouBeimei"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAmerica')"
                            name="type"
                            @change="handleCheckAllChange1a(4,2,'Futures')"
                          /> <!-- $i18n - 所有北美 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[4].beimei">
                          <el-checkbox-group
                            v-model="person2.JYxuke[4].beimei"
                            @change="handleCheckAllChange1b(4,2,'Futures')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[4].beimeiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 期权期货 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[5].isBox=!person2.JYxuke[5].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[5].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[5].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(6,'ForwardOption')" />
                    </div>
                    <div v-show="person2.JYxuke[5].isBox" class="jybottom">
                      <el-form-item label label-width="0px">
                        <div class="deal-text zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.experAndknowledge') }}</div> <!-- $i18n - 经验与交易知识 -->
                        <div class="bottom-xian" />
                        <el-col :span="5">
                          <el-form-item prop="JYxuke[5].ZSlevel">
                            <el-select v-model="person2.JYxuke[5].ZSlevel" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.knowledge')" @change="setTradingLicense('ForwardOption')"> <!-- $i18n - 知识水平为零 -->
                              <dictionary dict-code="knowledge" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[5].JYtrading">
                            <el-select v-model="person2.JYxuke[5].JYtrading" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.experience')" @change="setTradingLicense('ForwardOption')"> <!-- $i18n - 交易经验 -->
                              <dictionary dict-code="tradingExperience" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="5" class="maleft">
                          <el-form-item prop="JYxuke[5].MNtransactionnum">
                            <el-select
                              v-model="person2.JYxuke[5].MNtransactionnum"
                              :no-match-text="$t('account.manage.no_data')"
                              :placeholder="$t('registerOpenAccount.openAccount.common.TradingAndExperience.yearNumbei')"
                              @change="setTradingLicense('ForwardOption')"
                            > <!-- $i18n - 每年交易笔数 -->
                              <dictionary dict-code="yearTransactionsPerNumber" />
                            </el-select>
                          </el-form-item>
                        </el-col>
                        <el-col :span="6" class="zongjiaoyijuli">
                          <span class="zhuce3BG">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.sumNumbei') }}:</span> <!-- $i18n - 总交易笔数 -->
                          &nbsp;
                          <span>{{ person2.zongjiaoyiBishu6 }}</span>
                        </el-col>
                      </el-form-item>
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[5].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                      <!-- <div class="xian"></div> -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[5].suoyouYatai"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAsiaPacific')"
                            name="type"
                            @change="handleCheckAllChange1a(5,0,'ForwardOption')"
                          /> <!-- $i18n - 所有亚太 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[5].yatai">
                          <el-checkbox-group
                            v-model="person2.JYxuke[5].yatai"
                            @change="handleCheckAllChange1b(5,0,'ForwardOption')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[5].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[5].suoyouOuzhou"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allEurope')"
                            name="type"
                            @change="handleCheckAllChange1a(5,1,'ForwardOption')"
                          /> <!-- $i18n - 所有欧洲 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[5].ouzhou">
                          <el-checkbox-group
                            v-model="person2.JYxuke[5].ouzhou"
                            @change="handleCheckAllChange1b(5,1,'ForwardOption')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[5].ouzhouList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[5].suoyouBeimei"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAmerica')"
                            name="type"
                            @change="handleCheckAllChange1a(5,2,'ForwardOption')"
                          /> <!-- $i18n - 所有北美 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[5].beimei">
                          <el-checkbox-group
                            v-model="person2.JYxuke[5].beimei"
                            @change="handleCheckAllChange1b(5,2,'ForwardOption')"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[5].beimeiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 个股期货 -->
                  <div v-if="person2.ifStockFutures" class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[6].isBox=!person2.JYxuke[6].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[6].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[6].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(7,'SingleStockFutures')" />
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[6].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- $i18n - 您交易的地点 -->
                      <!-- <div class="xian"></div> -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[6].suoyouOuzhou"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allEurope')"
                            name="type"
                            @change="handleCheckAllChange1a(6,1,'SingleStockFutures')"
                          /> <!-- $i18n - 所有欧洲 -->
                        </el-form-item>
                        <el-form-item>
                          <el-checkbox-group
                            v-model="person2.JYxuke[6].ouzhou"
                            @change="handleCheckAllChange1b(6,1)"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[6].ouzhouList" :key="index" :label="item.tansactionLocation" @change="setTradingLicense('SingleStockFutures')">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox
                            v-model="person2.JYxuke[6].suoyouBeimei"
                            :label="$t('registerOpenAccount.openAccount.common.TradingAndExperience.allAmerica')"
                            name="type"
                            @change="handleCheckAllChange1a(6,2,'SingleStockFutures')"
                          /> <!-- $i18n - 所有北美 -->
                        </el-form-item>
                        <el-form-item prop="JYxuke[6].beimei">
                          <el-checkbox-group
                            v-model="person2.JYxuke[6].beimei"
                            @change="handleCheckAllChange1b(6,2)"
                          >
                            <el-checkbox v-for="(item,index) in person2.JYxuke[6].beimeiList" :key="index" :label="item.tansactionLocation" @change="setTradingLicense('SingleStockFutures')">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 共同基金 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[7].isBox=!person2.JYxuke[7].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[7].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[7].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(8,'MutualFund')" />
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[7].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- 您交易的地点 -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox-group v-model="person2.JYxuke[7].ouzhou" size="small" @change="TraSpecialHandling(person2.JYxuke[7].ouzhou,'MutualFund')">
                            <el-checkbox v-for="(item,index) in person2.JYxuke[7].ouzhouList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- NextShares -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[8].isBox=!person2.JYxuke[8].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[8].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[8].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(9,'NextShares')" />
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[8].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p> <!-- 您交易的地点 -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox-group v-model="person2.JYxuke[8].yatai" size="small" @change="TraSpecialHandling(person2.JYxuke[8].yatai,'NextShares')">
                            <el-checkbox v-for="(item,index) in person2.JYxuke[8].beimeiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 货币转换 -->
                  <div class="jiaoyixuke top20">
                    <div
                      class="jyTop xiaoshou"
                      @click="person2.JYxuke[9].isBox=!person2.JYxuke[9].isBox"
                    >
                      <div class="zhuce2ji jiaoyiLeft jiaoyigongong">{{ person2.JYxuke[9].tansactionDist }}</div>
                      <div class="zhuce2ji jiaoyiright jiaoyigongong JYxialaanniu" :class="{ JYxialaanniuhover: person2.JYxuke[9].isBox}" />
                      <div class="emptyCN" :class="{ emptyEN: lang === 'en_US' }" @click="removeTrading(10,'CurrencyConverter')" />
                    </div>
                    <!-- 地点 -->
                    <div v-show="person2.JYxuke[9].isBox" class="jyDidianBom pleaseEnter">
                      <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.TradingAndExperience.PlaceExchange') }} :</p>  <!-- 您交易的地点 -->
                      <div class="bottom10">
                        <el-form-item label label-width="0px">
                          <el-checkbox-group v-model="person2.JYxuke[9].yatai" size="small" @change="TraSpecialHandling(person2.JYxuke[9].yatai,'CurrencyConverter')">
                            <el-checkbox v-for="(item,index) in person2.JYxuke[9].yataiList" :key="index" :label="item.tansactionLocation">{{ lang === 'en_US'?item.enName:item.cnName }}</el-checkbox>
                          </el-checkbox-group>
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('person2')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }} </el-button>
                  </el-form-item>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>
      <!--endprint-->
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
    // 规管信息验证-是否雇员
    var validateGuYuan = (rule, value, callback) => {
      const mate = this.person2.isstate.ggisshowa;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 规管信息验证-是否股东决策人
    var validateGuDong = (rule, value, callback) => {
      const mate = this.person2.isstate.ggisshowb;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 收入和资产值
    const validateProperty = (rule, value, callback, typeNum) => {
      const obj = {
        assetVal1: this.$verify.ClearZF(this.person2.asset.zichan1),
        assetVal2: this.$verify.ClearZF(this.person2.asset.zichan2),
        assetVal3: this.$verify.ClearZF(this.person2.asset.zichan3),
        tyep1: typeNum
      };
      this.$verify.Income(rule, value, obj, callback);
    };
    var validateProperty1 = (rule, value, callback) => {
      validateProperty(rule, value, callback, 1);
    };
    var validateProperty2 = (rule, value, callback) => {
      validateProperty(rule, value, callback, 2);
    };
    var validateProperty3 = (rule, value, callback) => {
      if (this.person2.asset.zichan2 !== '' && this.person2.asset.zichan1 !== '') {
        this.$refs.person2.validateField('asset.zichan2');
        this.$refs.person2.validateField('asset.zichan1');
      } else if (this.person2.asset.zichan2 !== '') {
        this.$refs.person2.validateField('asset.zichan2');
      } else if (this.person2.asset.zichan1 !== '') {
        this.$refs.person2.validateField('asset.zichan1');
      }
      validateProperty(rule, value, callback, 3);
    };
    // 交易经验 --股票...
    var validateJYgupiao1 = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[0].JYtrading,
        this.person2.JYxuke[0].MNtransactionnum,
        1
      );
      if (this.person2.JYxuke[0].JYtrading !== '' ||
            this.person2.JYxuke[0].MNtransactionnum !== '' ||
            this.person2.JYxuke[0].ZSlevel !== '' ||
            this.person2.JYxuke[0].yatai.length !== 0 ||
            this.person2.JYxuke[0].ouzhou.length !== 0 ||
            this.person2.JYxuke[0].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.Knowledge(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --股票地点...
    var validateJYgupiao2 = (rule, value, callback) => {
      if (this.person2.JYxuke[0].JYtrading !== '' ||
            this.person2.JYxuke[0].MNtransactionnum !== '' ||
            this.person2.JYxuke[0].ZSlevel !== '') {
        if (this.person2.JYxuke[0].yatai.length !== 0 ||
              this.person2.JYxuke[0].ouzhou.length !== 0 ||
              this.person2.JYxuke[0].beimei.length !== 0) {
          this.$verify.Knowledge(rule, value, callback);
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        }
      } else {
        callback();
      }
    };
    // 交易经验 --债券...
    var validateJYzhaijuan1 = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[1].JYtrading,
        this.person2.JYxuke[1].MNtransactionnum,
        2
      );
      if (this.person2.JYxuke[1].JYtrading !== '' ||
            this.person2.JYxuke[1].MNtransactionnum !== '' ||
            this.person2.JYxuke[1].ZSlevel !== '' ||
            this.person2.JYxuke[1].yatai.length !== 0 ||
            this.person2.JYxuke[1].ouzhou.length !== 0 ||
            this.person2.JYxuke[1].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.Knowledge(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --债券地点...
    var validateJYzhaijuan2 = (rule, value, callback) => {
      if (this.person2.JYxuke[1].JYtrading !== '' ||
            this.person2.JYxuke[1].MNtransactionnum !== '' ||
            this.person2.JYxuke[1].ZSlevel !== '') {
        if (this.person2.JYxuke[1].yatai.length !== 0 ||
              this.person2.JYxuke[1].ouzhou.length !== 0 ||
              this.person2.JYxuke[1].beimei.length !== 0) {
          callback();
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        }
      } else {
        this.$verify.Knowledge(rule, value, callback);
      }
    };
    // 交易经验 --权证地点...
    var validateJYquanzheng1 = (rule, value, callback) => {
      if (this.person2.JYxuke[2].yatai.length !== 0) {
        callback();
      } else {
        this.$verify.Knowledge(rule, value, callback);
      }
    };
    // 交易经验 --期权...
    var validateJYqiquan1 = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[3].JYtrading,
        this.person2.JYxuke[3].MNtransactionnum,
        4
      );
      if (this.person2.JYxuke[3].JYtrading !== '' ||
            this.person2.JYxuke[3].MNtransactionnum !== '' ||
            this.person2.JYxuke[3].ZSlevel !== '' ||
            this.person2.JYxuke[3].yatai.length !== 0 ||
            this.person2.JYxuke[3].ouzhou.length !== 0 ||
            this.person2.JYxuke[3].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.Knowledge(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期权...交易经验两年以上
    var validateJYqiquan1B = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[3].JYtrading,
        this.person2.JYxuke[3].MNtransactionnum,
        4
      );
      if (this.person2.JYxuke[3].JYtrading !== '' ||
            this.person2.JYxuke[3].MNtransactionnum !== '' ||
            this.person2.JYxuke[3].ZSlevel !== '' ||
            this.person2.JYxuke[3].yatai.length !== 0 ||
            this.person2.JYxuke[3].ouzhou.length !== 0 ||
            this.person2.JYxuke[3].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.ExperienceTrading(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期权地点...
    var validateJYqiquan2 = (rule, value, callback) => {
      if (this.person2.JYxuke[3].JYtrading !== '' ||
            this.person2.JYxuke[3].MNtransactionnum !== '' ||
            this.person2.JYxuke[3].ZSlevel !== '') {
        if (this.person2.JYxuke[3].yatai.length !== 0 ||
              this.person2.JYxuke[3].ouzhou.length !== 0 ||
              this.person2.JYxuke[3].beimei.length !== 0) {
          this.$verify.Knowledge(rule, value, callback);
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期货...
    var validateJYqihuo1 = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[4].JYtrading,
        this.person2.JYxuke[4].MNtransactionnum,
        5
      );
      if (this.person2.JYxuke[4].JYtrading !== '' ||
            this.person2.JYxuke[4].MNtransactionnum !== '' ||
            this.person2.JYxuke[4].ZSlevel !== '' ||
            this.person2.JYxuke[4].yatai.length !== 0 ||
            this.person2.JYxuke[4].ouzhou.length !== 0 ||
            this.person2.JYxuke[4].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.Knowledge(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期货...交易经验两年以上
    var validateJYqihuo1A = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[4].JYtrading,
        this.person2.JYxuke[4].MNtransactionnum,
        5
      );
      if (this.person2.JYxuke[4].JYtrading !== '' ||
            this.person2.JYxuke[4].MNtransactionnum !== '' ||
            this.person2.JYxuke[4].ZSlevel !== '' ||
            this.person2.JYxuke[4].yatai.length !== 0 ||
            this.person2.JYxuke[4].ouzhou.length !== 0 ||
            this.person2.JYxuke[4].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.ExperienceTrading(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期货地点...
    var validateJYqihuo2 = (rule, value, callback) => {
      if (this.person2.JYxuke[4].JYtrading !== '' ||
            this.person2.JYxuke[4].MNtransactionnum !== '' ||
            this.person2.JYxuke[4].ZSlevel !== '') {
        if (this.person2.JYxuke[4].yatai.length !== 0 ||
              this.person2.JYxuke[4].ouzhou.length !== 0 ||
              this.person2.JYxuke[4].beimei.length !== 0) {
          this.$verify.Knowledge(rule, value, callback);
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期权期货...
    var validateJYqiquanqihuo1 = (rule, value, callback) => {
      this.getZongjiaoyishu(
        this.person2.JYxuke[5].JYtrading,
        this.person2.JYxuke[5].MNtransactionnum,
        6
      );
      if (this.person2.JYxuke[5].JYtrading !== '' ||
            this.person2.JYxuke[5].MNtransactionnum !== '' ||
            this.person2.JYxuke[5].ZSlevel !== '' ||
            this.person2.JYxuke[5].yatai.length !== 0 ||
            this.person2.JYxuke[5].ouzhou.length !== 0 ||
            this.person2.JYxuke[5].beimei.length !== 0) {
        if (value === '') {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        } else {
          this.$verify.Knowledge(rule, value, callback);
        }
      } else {
        callback();
      }
    };
    // 交易经验 --期权期货地点...
    var validateJYqiquanqihuo2 = (rule, value, callback) => {
      if (this.person2.JYxuke[5].JYtrading !== '' ||
            this.person2.JYxuke[5].MNtransactionnum !== '' ||
            this.person2.JYxuke[5].ZSlevel !== '') {
        if (this.person2.JYxuke[5].yatai.length !== 0 ||
              this.person2.JYxuke[5].ouzhou.length !== 0 ||
              this.person2.JYxuke[5].beimei.length !== 0) {
          this.$verify.Knowledge(rule, value, callback);
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'))); // $i18n - 请选择
        }
      } else {
        callback();
      }
    };

    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      dialogVisible: false,
      person2: {
        investAccountType: '', // 投资账户类型
        ifPageLogicJudgment: {}, // 页面逻辑判断提示
        isstate: {
          ggisshowa: false,
          ggisshowb: false,
          isshow1: false,
          isshow2: false,
          isshow3: false,
          isshow4: false,
          isshow41: false,
          isshow5: false,
          isshow6: false,
          isshow7: false,
          isshow8: false,
          isshow9: false
        },
        // 规管信息
        ggisshow1: '',
        require: '',
        relation: '',
        familynum: '',
        ggisshow2: '',
        GSstockcode: '',
        ishan: false,

        condition: 0,
        jingyanzhuangtai: 0,
        asset: {
          zichan1: '',
          zichan1List: [],
          zichan2: '',
          zichan2List: [],
          zichan3: '',
          zichan3List: []
        },
        investmentPresent: null, // 投资目标当前值
        JYxukeList: [], // 交易许可类型最后选中的
        touzimubiao: [],
        touzimubiaoList: [],
        ifStockFutures: true, // 个股期货显示隐藏
        JYxuke: [
          { // 股票 1
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],
            ouzhouList: [],
            suoyouBeimei: false,
            beimei: [],
            beimeiList: [],
            tansactionType: 'Stock',
            tansactionDist: '',
            id: '' // 更新时所需要带的id
            // AsiaPacific
            // Europe
            // NorthAmerica
          },
          { // 债券 2
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],
            ouzhouList: [],
            suoyouBeimei: false,
            beimei: [],
            beimeiList: [],
            tansactionType: 'Bond',
            tansactionDist: '',
            id: '' // 更新时所需要带的id
          },
          { // 权证 3
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],

            suoyouBeimei: false,
            beimei: [],
            tansactionDist: '',
            tansactionType: 'Warrant',
            id: '' // 更新时所需要带的id
          },
          { // 期权 4
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            shouxianJY: false,
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],
            ouzhouList: [],
            suoyouBeimei: false,
            beimei: [],
            beimeiList: [],
            tansactionDist: '',
            tansactionType: 'Options',
            id: '' // 更新时所需要带的id
          },
          { // 期货 5
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],
            ouzhouList: [],
            suoyouBeimei: false,
            beimei: [],
            beimeiList: [],
            tansactionDist: '',
            tansactionType: 'Futures',
            id: '' // 更新时所需要带的id
          },
          { // 期权期货 6
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],
            ouzhouList: [],
            suoyouBeimei: false,
            beimei: [],
            beimeiList: [],
            tansactionDist: '',
            tansactionType: 'ForwardOption',
            id: '' // 更新时所需要带的id
          },
          { // 个股期货 7
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            suoyouOuzhou: false,
            ouzhou: [],
            ouzhouList: [],
            suoyouBeimei: false,
            beimei: [],
            beimeiList: [],
            tansactionDist: '',
            tansactionType: 'SingleStockFutures',
            id: '' // 更新时所需要带的id
          },
          { // 共同基金 8
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            suoyouOuzhou: false,
            ouzhou: [],
            suoyouBeimei: false,
            beimei: [],
            tansactionDist: '',
            tansactionType: 'MutualFund',
            id: '' // 更新时所需要带的id
          },
          { // NextShares 9
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            suoyouOuzhou: false,
            ouzhou: [],
            suoyouBeimei: false,
            beimei: [],
            tansactionDist: '',
            tansactionType: 'NextShares',
            id: '' // 更新时所需要带的id
          },
          { // 货币转换 10
            isBox: false,
            JYtrading: '',
            MNtransactionnum: '',
            zongjiaoyiBishu1: '',
            ZSlevel: '',
            suoyouYatai: false,
            yatai: [],
            yataiList: [],
            suoyouOuzhou: false,
            ouzhou: [],
            suoyouBeimei: false,
            beimei: [],
            tansactionDist: '',
            tansactionType: 'CurrencyConverter',
            id: '' // 更新时所需要带的id
          }
        ],
        zongjiaoyiBishu1: '',
        zongjiaoyiBishu2: '',
        zongjiaoyiBishu4: '',
        zongjiaoyiBishu5: '',
        zongjiaoyiBishu6: ''
      },
      formdata: {},
      newValue: {
        newAccountregulation: null,
        newAccountCoAsset: null,
        newAccountCoInvestmentTarget: null,
        newAccountCoTradeExperiencesList: null
      },
      rules: {
        // 规管信息
        require: [{ validator: validateGuYuan, trigger: 'change' }],
        relation: [{ validator: validateGuYuan, trigger: 'change' }],
        familynum: [{ validator: validateGuYuan, trigger: 'blur' }],
        GSstockcode: [{ validator: validateGuDong, trigger: 'blur' }],

        // // 收入资产值
        'asset.zichan1': [{ validator: validateProperty1, trigger: 'change' }],
        'asset.zichan2': [{ validator: validateProperty2, trigger: 'change' }],
        'asset.zichan3': [{ validator: validateProperty3, trigger: 'change' }],
        // // 投资目标及交易意图
        touzimubiao: [
          { required: true, message: `${this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose')}`, trigger: 'change' } // $i18n - 请选择
        ],
        // // 交易经验
        // // --股票
        'JYxuke[0].JYtrading': [{ validator: validateJYgupiao1, trigger: 'change' }],
        'JYxuke[0].MNtransactionnum': [{ validator: validateJYgupiao1, trigger: 'change' }],
        'JYxuke[0].ZSlevel': [{ validator: validateJYgupiao1, trigger: 'change' }],
        'JYxuke[0].yatai': [{ validator: validateJYgupiao2, trigger: 'change' }],
        'JYxuke[0].ouzhou': [{ validator: validateJYgupiao2, trigger: 'change' }],
        'JYxuke[0].beimei': [{ validator: validateJYgupiao2, trigger: 'change' }],
        // --债券
        'JYxuke[1].JYtrading': [{ validator: validateJYzhaijuan1, trigger: 'change' }],
        'JYxuke[1].MNtransactionnum': [{ validator: validateJYzhaijuan1, trigger: 'change' }],
        'JYxuke[1].ZSlevel': [{ validator: validateJYzhaijuan1, trigger: 'change' }],
        'JYxuke[1].yatai': [{ validator: validateJYzhaijuan2, trigger: 'change' }],
        'JYxuke[1].ouzhou': [{ validator: validateJYzhaijuan2, trigger: 'change' }],
        'JYxuke[1].beimei': [{ validator: validateJYzhaijuan2, trigger: 'change' }],
        // --权证
        'JYxuke[2].yatai': [{ validator: validateJYquanzheng1, trigger: 'change' }],
        // --期权
        'JYxuke[3].JYtrading': [{ validator: validateJYqiquan1B, trigger: 'change' }],
        'JYxuke[3].MNtransactionnum': [{ validator: validateJYqiquan1, trigger: 'change' }],
        'JYxuke[3].ZSlevel': [{ validator: validateJYqiquan1, trigger: 'change' }],
        'JYxuke[3].yatai': [{ validator: validateJYqiquan2, trigger: 'change' }],
        'JYxuke[3].ouzhou': [{ validator: validateJYqiquan2, trigger: 'change' }],
        'JYxuke[3].beimei': [{ validator: validateJYqiquan2, trigger: 'change' }],
        // --期货
        'JYxuke[4].JYtrading': [{ validator: validateJYqihuo1A, trigger: 'change' }],
        'JYxuke[4].MNtransactionnum': [{ validator: validateJYqihuo1, trigger: 'change' }],
        'JYxuke[4].ZSlevel': [{ validator: validateJYqihuo1, trigger: 'change' }],
        'JYxuke[4].yatai': [{ validator: validateJYqihuo2, trigger: 'change' }],
        'JYxuke[4].ouzhou': [{ validator: validateJYqihuo2, trigger: 'change' }],
        'JYxuke[4].beimei': [{ validator: validateJYqihuo2, trigger: 'change' }],
        // --期货期货
        'JYxuke[5].JYtrading': [{ validator: validateJYqiquanqihuo1, trigger: 'change' }],
        'JYxuke[5].MNtransactionnum': [{ validator: validateJYqiquanqihuo1, trigger: 'change' }],
        'JYxuke[5].ZSlevel': [{ validator: validateJYqiquanqihuo1, trigger: 'change' }],
        'JYxuke[5].yatai': [{ validator: validateJYqiquanqihuo2, trigger: 'change' }],
        'JYxuke[5].ouzhou': [{ validator: validateJYqiquanqihuo2, trigger: 'change' }],
        'JYxuke[5].beimei': [{ validator: validateJYqiquanqihuo2, trigger: 'change' }]
      }
    };
  },
  computed: {
    forObj() { // 判断是否有属性为空值 空值则隐藏
      return (obj) => {
        const newData = {};
        for (const key in obj) {
          if (obj.hasOwnProperty[key] !== '') {
            newData[key] = obj[key];
          }
        }
        return newData;
      };
    }
  },
  created() {
    this.getPersonalaccountportfolio();
    // 字典表获取
    this.netAssetValue();
    this.anualNetIncome();
    this.investmentTargetType();
    this.tansactionType();
    // 获取用户已填入的信息
    this.accountregulation();
    this.accountPerAsset();
    this.accountPerInvestTargets();
  },
  mounted() {

  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    // 获取个人规管信息
    accountregulation() {
      this.$http({
        url: '/account/accountPerRegulatory/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountregulation = res.object;
              if (res.object.length > 0) {
                if (res.object[0].employer) {
                  this.person2.isstate.ggisshowa = true;
                  this.person2.require = res.object[0].employer;
                  this.person2.relation =
                  res.object[0].relationshipAccountHolders;
                  this.person2.familynum = res.object[0].familyMembersCompany;
                }
                if (res.object[0].accountHolderCompanyStock) {
                  this.person2.isstate.ggisshowb = true;
                  this.person2.GSstockcode =
                  res.object[0].accountHolderCompanyStock;
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
    accountPerAsset() { // 收入和资产值
      this.$http({
        url: '/account/accountPerAsset/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoAsset = res.object;
              this.person2.asset.zichan1 = res.object[0].anualNetIncome;
              this.person2.asset.zichan2 = res.object[0].netAssetValue;
              this.person2.asset.zichan3 = res.object[0].netCurrentAssets;
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
    accountPerInvestTargets() { // 个人投资目标信息
      this.$http({
        url: '/account/accountPerInvestTarget/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoInvestmentTarget = res.object;
              const investmentTargetList = res.object[0].investmentTarget.split(',');
              for (let i = 0; i < investmentTargetList.length; i++) {
                this.person2.touzimubiao.push(
                  JSON.parse(JSON.stringify(investmentTargetList[i]))
                );
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
    accountCoTradeExperience() { // 个人交易经验信息
      this.$http({
        url: '/account/accountPerTradeExp/findByAccountId',
        method: 'get',
        params: {
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue.newAccountCoTradeExperiencesList = res.object.accountPerTradeExpList;
              for (let i = 0; i < res.object.accountPerTradeExpList.length; i++) { // 交易经验
                this.person2.JYxukeList.push(res.object.accountPerTradeExpList[i].tansactionType);
                for (let j = 0; j < this.person2.JYxuke.length; j++) {
                  if (res.object.accountPerTradeExpList[i].tansactionType === this.person2.JYxuke[j].tansactionType) {
                    if (res.object.accountPerTradeExpList[i].knowledgeLevel ||
                        res.object.accountPerTradeExpList[i].tradingExperience ||
                        res.object.accountPerTradeExpList[i].tradingLocation ||
                        res.object.accountPerTradeExpList[i].yearTransactionsPerNumber
                    ) {
                      this.person2.JYxuke[j].isBox = true;
                      this.person2.JYxuke[j].JYtrading = res.object.accountPerTradeExpList[i].tradingExperience;
                      this.person2.JYxuke[j].MNtransactionnum = res.object.accountPerTradeExpList[i].yearTransactionsPerNumber;
                      this.person2.JYxuke[j].zongjiaoyiBishu1 = res.object.accountPerTradeExpList[i].transactionsTotalNumber;
                      this.person2.JYxuke[j].ZSlevel = res.object.accountPerTradeExpList[i].knowledgeLevel;
                      this.person2.JYxuke[j].id = res.object.accountPerTradeExpList[i].id;
                    }
                  }
                }
              }
              for (let i = 0; i < res.object.accountCommonTradeLocations.length; i++) { // 交易地点
                for (let j = 0; j < this.person2.JYxuke.length; j++) {
                  if (res.object.accountCommonTradeLocations[i].tansactionType === this.person2.JYxuke[j].tansactionType) {
                    if (j === 0 || j === 1 || j === 3 || j === 4 || j === 5 || j === 6) {
                      if (res.object.accountCommonTradeLocations[i].tansactionRegion === 'AsiaPacific') { // 亚太
                        this.person2.JYxuke[j].yatai.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                        if (this.person2.JYxuke[j].yatai.length === this.person2.JYxuke[j].yataiList.length) {
                          this.person2.JYxuke[j].suoyouYatai = true;
                        }
                      } else if (res.object.accountCommonTradeLocations[i].tansactionRegion === 'Europe') { // 欧洲
                        this.person2.JYxuke[j].ouzhou.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                        if (this.person2.JYxuke[j].ouzhou.length === this.person2.JYxuke[j].ouzhouList.length) {
                          this.person2.JYxuke[j].suoyouOuzhou = true;
                        }
                      } else if (res.object.accountCommonTradeLocations[i].tansactionRegion === 'NorthAmerica') { // 北美
                        this.person2.JYxuke[j].beimei.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                        if (this.person2.JYxuke[j].beimei.length === this.person2.JYxuke[j].beimeiList.length) {
                          this.person2.JYxuke[j].suoyouBeimei = true;
                        }
                      }
                    } else if (j === 2) {
                      this.person2.JYxuke[j].yatai.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                    } else if (j === 7) {
                      this.person2.JYxuke[j].ouzhou.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                    } else if (j === 8) {
                      this.person2.JYxuke[j].yatai.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                    } else if (j === 9) {
                      this.person2.JYxuke[j].yatai.push(res.object.accountCommonTradeLocations[i].tansactionLocation);
                    }
                  }
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
              this.person2.investAccountType = res.object[0].accountPortfolioType;
              if (this.person2.investAccountType === 'cash') { // 现金账户
                this.person2.ifStockFutures = false;
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
    // 获取字典表数据
    // 资产净值 --年度资产净值
    netAssetValue() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'netAssetValue',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person2.asset.zichan2List = res.object;
              this.person2.asset.zichan3List = res.object;
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
    // 年度净收入
    anualNetIncome() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'anualNetIncome',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person2.asset.zichan1List = res.object;
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
    // 投资目标类型
    investmentTargetType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'investmentTargetType',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.person2.touzimubiaoList = res.object;
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
    // 交易类型
    tansactionType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'tansactionType',
          lang: this.lang,
          IEgetIssue: this.$verify.timestampRandom()
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < this.person2.JYxuke.length; i++) {
                this.person2.JYxuke[i].tansactionDist = res.object[i].dictDest;

                this.person2.JYxuke[i].tansactionType = res.object[i].dictValue;
                this.tradingPlace(res.object[i].dictValue, i);
              }
              this.accountCoTradeExperience();
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
    // 根据交易类型获取交易地点
    tradingPlace(tansactionType, index) {
      this.$http({
        url: '/account/accountCommonTradeLocation/findByTansactionType',
        method: 'get',
        params: { tansactionType, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (index === 0 || index === 1 || index === 3 || index === 4 || index === 5) {
                  if (res.object[i].tansactionRegion === 'AsiaPacific') {
                    this.person2.JYxuke[index].yataiList.push(JSON.parse(JSON.stringify(res.object[i])));
                  } else if (res.object[i].tansactionRegion === 'Europe') {
                    this.person2.JYxuke[index].ouzhouList.push(JSON.parse(JSON.stringify(res.object[i])));
                  } else if (res.object[i].tansactionRegion === 'NorthAmerica') {
                    this.person2.JYxuke[index].beimeiList.push(JSON.parse(JSON.stringify(res.object[i])));
                  }
                } else if (index === 2) {
                  this.person2.JYxuke[index].yataiList = JSON.parse(JSON.stringify(res.object));
                } else if (index === 6) {
                  if (res.object[i].tansactionRegion === 'Europe') {
                    this.person2.JYxuke[index].ouzhouList.push(JSON.parse(JSON.stringify(res.object[i])));
                  } else if (res.object[i].tansactionRegion === 'NorthAmerica') {
                    this.person2.JYxuke[index].beimeiList.push(JSON.parse(JSON.stringify(res.object[i])));
                  }
                } else if (index === 7) {
                  this.person2.JYxuke[index].ouzhouList = JSON.parse(JSON.stringify(res.object));
                } else if (index === 8) {
                  this.person2.JYxuke[index].beimeiList = JSON.parse(JSON.stringify(res.object));
                } else if (index === 9) {
                  this.person2.JYxuke[index].yataiList = JSON.parse(JSON.stringify(res.object));
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

    // // 交易经营 清空交易数据
    removeTrading(index, val) {
      this.person2.JYxuke[index - 1].JYtrading = '';
      this.person2.JYxuke[index - 1].MNtransactionnum = '';
      this.person2.JYxuke[index - 1].zongjiaoyiBishu1 = '';
      this.person2.JYxuke[index - 1].ZSlevel = '';
      this.person2.JYxuke[index - 1].suoyouYatai = false;
      this.person2.JYxuke[index - 1].yatai = [];
      this.person2.JYxuke[index - 1].suoyouOuzhou = false;
      this.person2.JYxuke[index - 1].ouzhou = [];
      this.person2.JYxuke[index - 1].suoyouBeimei = false;
      this.person2.JYxuke[index - 1].beimei = [];
      this.stopPropagation();
      this.delTradingLicense(val);
    },
    stopPropagation(e) {
      e = e || window.event;
      if (e.stopPropagation) {
        e.stopPropagation();
      } else {
        e.cancelBubble = true;
      }
    },

    // 整体判断逻辑补充
    setInvestmentPresent(val, arr) { // 判断投资目标逻辑
      this.$verify.investmentObjective(val, arr).then(succeed => {
        this.person2.touzimubiao = succeed;
      });
    },
    TraSpecialHandling(val, type2) { // 权证 共同基金 NextShares 货币转换 特殊处理-判断从拿到所选的交易许可类型数组中添加还是删除
      if (val.length !== 0) {
        this.setTradingLicense(type2);
      } else {
        this.delTradingLicense(type2);
      }
    },
    setTradingLicense(val) { // 拿到所选的交易许可类型-添加到数组
      var r = this.person2.JYxukeList.filter(function(x) {
        let status = true;
        if (x === val) {
          status = false;
        }
        return status;
      });
      this.person2.JYxukeList = r;
      this.person2.JYxukeList.push(val);
    },
    delTradingLicense(val) { // 所选的交易许可类型-从数组中删除
      var r = this.person2.JYxukeList.filter(function(x) {
        let status = true;
        if (x === val) {
          status = false;
        }
        return status;
      });
      this.person2.JYxukeList = r;
    },
    PageLogicJudgment() { // 整个页面的变化逻辑判断处理
      let status = true; // 是否通过状态
      this.person2.ifPageLogicJudgment = {}; // 页面html逻辑提示
      const zican1 = this.$verify.ClearZF(this.person2.asset.zichan1);
      const zican2 = this.$verify.ClearZF(this.person2.asset.zichan2);
      const zican3 = this.$verify.ClearZF(this.person2.asset.zichan3);

      const newAsset1 = zican1.split('-');
      const newAsset2 = zican2.split('-');
      const newAsset3 = zican3.split('-');
      if (zican1.indexOf('<') !== -1) {
        newAsset1[0] = 39999;
      }
      if (zican2.indexOf('<') !== -1) {
        newAsset2[0] = 19999;
      }
      if (zican3.indexOf('<') !== -1) {
        newAsset3[0] = 19999;
      }
      if (zican1.indexOf('>') !== -1) {
        newAsset1[0] = 1000000;
      }
      if (zican2.indexOf('>') !== -1) {
        newAsset2[0] = 50000000;
      }
      if (zican3.indexOf('>') !== -1) {
        newAsset3[0] = 50000000;
      }
      let isExperienceTradingProduct = false; // 是否有选择交易经验的产品
      this.person2.JYxukeList.forEach(item => {
        if (item === 'Options' || item === 'Stock' || item === 'Bond' || item === 'Futures' || item === 'ForwardOption') {
          isExperienceTradingProduct = true;
        }
      });
      if (!isExperienceTradingProduct) {
        this.$message.error(`${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.product1a')}`); // $i18n - 至少要选择股票,债券,期权,期货,期权期货的其中一种产品
        return;
      }
      /**
       * ifPageLogicJudgment 提示对象
       * TZ100000        '投资组合保证金账户,流动资产净值&资产净值都必须超过100,000'
       * assetLogic     '资产判断'
       * investLogic    '投资目标判断'
       * transaction    '交易许可判断'
       */
      if (this.person2.investAccountType === 'portfolio') { // 投资组合保证金判断--->流动资产&&资产净值都必须超过 100 000 === 必须开通期权
        if (Number(newAsset3[0]) >= 100000 && Number(newAsset2[0]) >= 100000) {
          this.person2.ifPageLogicJudgment['TZ100000'] = '';
        } else {
          this.person2.ifPageLogicJudgment['TZ100000'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1a')}`; // $i18n - 投资组合保证金账户,流动资产净值&资产净值都必须超过100,000
        }
        this.person2.ifPageLogicJudgment['transaction4'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.product1b')}`; // 默认没有开通期权 // $i18n - 投资组合保证金账户必须开通期权
        this.person2.JYxukeList.forEach(item => {
          if (item === 'Options') {
            this.person2.ifPageLogicJudgment['transaction4'] = '';
          }
        });
      } else {
        this.person2.ifPageLogicJudgment['TZ100000'] = '';
      }

      this.person2.JYxukeList.forEach(item => {
        if (item === 'SingleStockFutures') { // 个股期货
          this.person2.ifPageLogicJudgment['assetLogic1'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1b')}`; // $i18n - ①流动资产净值不低于USD 20,000.00，年净收入不低于USD 40,000.00；
          if (Number(newAsset3[0]) >= 20000 && Number(newAsset1[0]) >= 40000) {
            this.person2.ifPageLogicJudgment['assetLogic1'] = '';
          }

          this.person2.ifPageLogicJudgment['investLogic1'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.target1a')}`; // $i18n - 要获批交易个股期货，您的投资目标必须包含下列之一：交易利润, 对冲, 增长。
          this.person2.touzimubiao.forEach(item1 => {
            if (item1 === 'tradingProfits' || item1 === 'hedge' || item1 === 'increase') {
              this.person2.ifPageLogicJudgment['investLogic1'] = '';
            }
          });
          this.person2.ifPageLogicJudgment['transaction6'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.product1c')}`; // $i18n - 要获批个股期货,必须开通股票
          this.person2.JYxukeList.forEach(item1 => {
            if (item1 === 'Stock') {
              this.person2.ifPageLogicJudgment['transaction6'] = '';
            }
          });

          if (this.person2.ifPageLogicJudgment['transaction6'] === '') { // 未测
            if (this.person2.JYxuke[6].yatai.length !== 0) { // 个股勾选地点 开通股票相对应的个股
              this.person2.JYxuke[6].yatai.forEach(item => {
                const r = this.person2.JYxuke[0].yatai.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].yatai = r;
                this.person2.JYxuke[0].yatai.push(item);
              });
            }
            if (this.person2.JYxuke[6].ouzhou.length !== 0) {
              this.person2.JYxuke[6].ouzhou.forEach(item => {
                const r = this.person2.JYxuke[0].ouzhou.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].ouzhou = r;
                this.person2.JYxuke[0].ouzhou.push(item);
              });
            }
            if (this.person2.JYxuke[6].beimei.length !== 0) {
              this.person2.JYxuke[6].beimei.forEach(item => {
                const r = this.person2.JYxuke[0].beimei.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].beimei = r;
                this.person2.JYxuke[0].beimei.push(item);
              });
            }
          }
        } else if (item === 'Stock' || item === 'Bond' || item === 'MutualFund') { // 股票 债券 共同基金
          this.person2.ifPageLogicJudgment['assetLogic2'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1b')}`; // $i18n - ①流动资产净值不低于USD 20,000.00，年净收入不低于USD 40,000.00；
          this.person2.ifPageLogicJudgment['assetLogic3'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1c')}`; // $i18n - ②流动资产净值不低于USD 100,000.00
          if (Number(newAsset3[0]) >= 20000 && Number(newAsset1[0]) >= 40000) {
            this.person2.ifPageLogicJudgment['assetLogic2'] = '';
            this.person2.ifPageLogicJudgment['assetLogic3'] = '';
          } else if (Number(newAsset3[0]) >= 100000) {
            this.person2.ifPageLogicJudgment['assetLogic2'] = '';
            this.person2.ifPageLogicJudgment['assetLogic3'] = '';
          }

          this.person2.ifPageLogicJudgment['investLogic2'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.target1b')}`; // $i18n - 要获批交易股票,债券,共同基金(包含其一)，您的投资目标必须包含下列之一：投机, 交易利润, 对冲, 增长。
          this.person2.touzimubiao.forEach(item1 => {
            if (item1 === 'speculate' || item1 === 'tradingProfits' || item1 === 'hedge' || item1 === 'increase') {
              this.person2.ifPageLogicJudgment['investLogic2'] = '';
            }
          });
        } else if (item === 'Options') { // 期权
          if (Number(newAsset3[0]) >= 20000 && Number(newAsset1[0]) >= 40000) {
            this.person2.ifPageLogicJudgment['assetLogic1'] = '';
          } else {
            this.person2.ifPageLogicJudgment['assetLogic1'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1b')}`; // $i18n - ①流动资产净值不低于USD 20,000.00，年净收入不低于USD 40,000.00；
          }

          this.person2.ifPageLogicJudgment['investLogic4'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.target1c')}`; // $i18n - 要获批交易期权，您的投资目标必须包含下列之一：投机, 交易利润
          this.person2.touzimubiao.forEach(item1 => {
            if (item1 === 'speculate' || item1 === 'tradingProfits') {
              this.person2.ifPageLogicJudgment['investLogic4'] = '';
            }
          });

          this.person2.ifPageLogicJudgment['transaction5'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.product1d')}`; // $i18n - 要获批期权,必须开通股票
          this.person2.JYxukeList.forEach(item1 => {
            if (item1 === 'Stock') {
              this.person2.ifPageLogicJudgment['transaction5'] = '';
            }
          });
          if (this.person2.ifPageLogicJudgment['transaction5'] === '') {
            if (this.person2.JYxuke[3].yatai.length !== 0) { // 期权勾选地点 开通股票相对应的个股
              this.person2.JYxuke[3].yatai.forEach(item => {
                const r = this.person2.JYxuke[0].yatai.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].yatai = r;
                this.person2.JYxuke[0].yatai.push(item);
              });
            }
            if (this.person2.JYxuke[3].ouzhou.length !== 0) {
              this.person2.JYxuke[3].ouzhou.forEach(item => {
                const r = this.person2.JYxuke[0].ouzhou.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].ouzhou = r;
                this.person2.JYxuke[0].ouzhou.push(item);
              });
            }
            if (this.person2.JYxuke[3].beimei.length !== 0) {
              this.person2.JYxuke[3].beimei.forEach(item => {
                const r = this.person2.JYxuke[0].beimei.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].beimei = r;
                this.person2.JYxuke[0].beimei.push(item);
              });
            }
          }
        } else if (item === 'ForwardOption') { // 期权期货
          this.person2.ifPageLogicJudgment['assetLogic1'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1b')}`; // $i18n - ①流动资产净值不低于USD 20,000.00，年净收入不低于USD 40,000.00；
          if (Number(newAsset3[0]) >= 20000 && Number(newAsset1[0]) >= 40000) {
            this.person2.ifPageLogicJudgment['assetLogic1'] = '';
          }

          this.person2.ifPageLogicJudgment['investLogic2'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.target1d')}`; // $i18n - 要获批交易个股期货，您的投资目标必须包含下列之一：投机, 交易利润, 对冲, 增长。
          this.person2.touzimubiao.forEach(item1 => {
            if (item1 === 'speculate' || item1 === 'tradingProfits' || item1 === 'hedge' || item1 === 'increase') {
              this.person2.ifPageLogicJudgment['investLogic2'] = '';
            }
          });
          this.person2.ifPageLogicJudgment['transaction3'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.product1e')}`; // 是否存在期货 - 默认没有 // $i18n - 交易此商品(期权期货)必须开通期货
          this.person2.JYxukeList.forEach(item1 => {
            if (item1 === 'Futures') {
              this.person2.ifPageLogicJudgment['transaction3'] = '';
            }
          });
        } else if (item === 'Futures') { // 期货
          this.person2.ifPageLogicJudgment['assetLogic1'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.asset1b')}`; // $i18n - ①流动资产净值不低于USD 20,000.00，年净收入不低于USD 40,000.00；
          if (Number(newAsset3[0]) >= 20000 && Number(newAsset1[0]) >= 40000) {
            this.person2.ifPageLogicJudgment['assetLogic1'] = '';
          }

          this.person2.ifPageLogicJudgment['investLogic2'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.target1e')}`; // $i18n - 要获批交易期货，您的投资目标必须包含下列之一：投机, 交易利润, 对冲, 增长。
          this.person2.touzimubiao.forEach(item1 => {
            if (item1 === 'speculate' || item1 === 'tradingProfits' || item1 === 'hedge' || item1 === 'increase') {
              this.person2.ifPageLogicJudgment['investLogic2'] = '';
            }
          });

          this.person2.ifPageLogicJudgment['transaction7'] = `${this.$i18n.t('registerOpenAccount.openAccount.common.else.trading.product1f')}`; // $i18n - 要获批期货,必须开通股票
          this.person2.JYxukeList.forEach(item1 => {
            if (item1 === 'Stock') {
              this.person2.ifPageLogicJudgment['transaction7'] = '';
            }
          });
          if (this.person2.ifPageLogicJudgment['transaction7'] === '') { // 未测
            if (this.person2.JYxuke[4].yatai.length !== 0) { // 个股勾选地点 开通股票相对应的个股
              this.person2.JYxuke[4].yatai.forEach(item => {
                const r = this.person2.JYxuke[0].yatai.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].yatai = r;
                this.person2.JYxuke[0].yatai.push(item);
              });
            }
            if (this.person2.JYxuke[4].ouzhou.length !== 0) {
              this.person2.JYxuke[4].ouzhou.forEach(item => {
                const r = this.person2.JYxuke[0].ouzhou.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].ouzhou = r;
                this.person2.JYxuke[0].ouzhou.push(item);
              });
            }
            if (this.person2.JYxuke[4].beimei.length !== 0) {
              this.person2.JYxuke[4].beimei.forEach(item => {
                const r = this.person2.JYxuke[0].beimei.filter(function(x) {
                  let status = true;
                  if (x === item) {
                    status = false;
                  }
                  return status;
                });
                this.person2.JYxuke[0].beimei = r;
                this.person2.JYxuke[0].beimei.push(item);
              });
            }
          }
        }
      });
      for (const key in this.person2.ifPageLogicJudgment) {
        if (this.person2.ifPageLogicJudgment[key] !== '') {
          status = false;
        }
      }
      if (status) {
        this.dialogVisible = false;
        this.submitFormData();
      } else {
        this.dialogVisible = true;
        // this.$message.error('验证错误,请按规则填写');
      }
    },

    // 全选 全不选
    handleCheckAllChange1a(index, indexMin, type2) { // indexMin  0=yatai 1=ouzhou 2=beimei   index 数组下标
      const dataTS = [];
      if (indexMin === 0) {
        if (this.person2.JYxuke[index].suoyouYatai) {
          for (let i = 0; i < this.person2.JYxuke[index].yataiList.length; i++) {
            dataTS.push(JSON.parse(JSON.stringify(this.person2.JYxuke[index].yataiList[i].tansactionLocation)));
          }
          this.person2.JYxuke[index].yatai = dataTS;
        } else {
          this.person2.JYxuke[index].yatai = [];
        }
      } else if (indexMin === 1) {
        if (this.person2.JYxuke[index].suoyouOuzhou) {
          for (let i = 0; i < this.person2.JYxuke[index].ouzhouList.length; i++) {
            dataTS.push(JSON.parse(JSON.stringify(this.person2.JYxuke[index].ouzhouList[i].tansactionLocation)));
          }
          this.person2.JYxuke[index].ouzhou = dataTS;
        } else {
          this.person2.JYxuke[index].ouzhou = [];
        }
      } else if (indexMin === 2) {
        if (this.person2.JYxuke[index].suoyouBeimei) {
          for (let i = 0; i < this.person2.JYxuke[index].beimeiList.length; i++) {
            dataTS.push(JSON.parse(JSON.stringify(this.person2.JYxuke[index].beimeiList[i].tansactionLocation)));
          }
          this.person2.JYxuke[index].beimei = dataTS;
        } else {
          this.person2.JYxuke[index].beimei = [];
        }
      } else {
        return false;
      }
      if (!this.person2.JYxuke[index].suoyouYatai && !this.person2.JYxuke[index].suoyouOuzhou && !this.person2.JYxuke[index].suoyouBeimei) { // 处理 交易类型添加删除
        if (this.person2.JYxuke[index].yatai.length === 0 && this.person2.JYxuke[index].ouzhou.length === 0 && this.person2.JYxuke[index].beimei.length === 0) {
          this.delTradingLicense(type2);
        } else {
          this.setTradingLicense(type2);
        }
      } else {
        this.setTradingLicense(type2);
      }
    },
    handleCheckAllChange1b(index, indexMin, type2) { // indexMin 0=yatai 1=ouzhou 2=beimei   index 数组下标
      if (indexMin === 0) {
        this.person2.JYxuke[index].suoyouYatai = this.person2.JYxuke[index].yatai.length === this.person2.JYxuke[index].yataiList.length;
      } else if (indexMin === 1) {
        this.person2.JYxuke[index].suoyouOuzhou = this.person2.JYxuke[index].ouzhou.length === this.person2.JYxuke[index].ouzhouList.length;
      } else if (indexMin === 2) {
        this.person2.JYxuke[index].suoyouBeimei = this.person2.JYxuke[index].beimei.length === this.person2.JYxuke[index].beimeiList.length;
      } else {
        return false;
      }
      if (this.person2.JYxuke[index].yatai.length === 0 && this.person2.JYxuke[index].ouzhou.length === 0 && this.person2.JYxuke[index].beimei.length === 0) { // 处理 交易类型添加删除
        this.delTradingLicense(type2);
      } else {
        this.setTradingLicense(type2);
      }
    },

    // 规管信息 是否按钮
    ggisshow11() {
      this.person2.isstate.ggisshowa = true;
      this.person2.ggisshow1 = `${this.$i18n.t('registerOpenAccount.common.btn.yes')}`; // $i18n - 是
    },
    ggisshow12() {
      this.person2.isstate.ggisshowa = false;
      this.person2.ggisshow1 = `${this.$i18n.t('registerOpenAccount.common.btn.no')}`; // $i18n - 否
    },
    ggisshow21() {
      this.person2.isstate.ggisshowb = true;
    },
    ggisshow22() {
      this.person2.isstate.ggisshowb = false;
    },

    // 计算总交易数
    // experience 交易经验
    // Transaction 年交易数
    // 总交易数 = 交易经验*年交易数
    getZongjiaoyishu(experience, Transaction, index) {
      let jiaoyijingyanJisuan = '';
      let jiaoyijingyanJisuan1 = '';
      let nianjiaoyishuJisuan = '';
      let nianjiaoyishuJisuan1 = '';
      let zongjishu333 = '';
      if (experience === 'zero year trading experience') {
        jiaoyijingyanJisuan = 0;
        jiaoyijingyanJisuan1 = 0;
      } else if (experience === 'one year trading experience') {
        jiaoyijingyanJisuan = 1;
        jiaoyijingyanJisuan1 = 1;
      } else if (experience === 'two year trading experience') {
        jiaoyijingyanJisuan = 2;
        jiaoyijingyanJisuan1 = 2;
      } else if (experience === 'three year trading experience') {
        jiaoyijingyanJisuan = 3;
        jiaoyijingyanJisuan1 = 3;
      } else if (experience === 'four year trading experience') {
        jiaoyijingyanJisuan = 4;
        jiaoyijingyanJisuan1 = 4;
      } else if (experience === 'five year trading experience') {
        jiaoyijingyanJisuan = 5;
        jiaoyijingyanJisuan1 = 5;
      } else if (experience === 'six to ten year trading experience') {
        jiaoyijingyanJisuan = 6;
        jiaoyijingyanJisuan1 = 10;
      } else if (experience === 'greaterThanTen year trading experience') {
        jiaoyijingyanJisuan = 10;
        jiaoyijingyanJisuan1 = 10;
      }
      if (Transaction === 'zeroYearTransactionsPerNumber') {
        nianjiaoyishuJisuan = 0;
        nianjiaoyishuJisuan1 = 0;
      } else if (Transaction === 'oneToTenYearTransactionsPerNumber') {
        nianjiaoyishuJisuan = 1;
        nianjiaoyishuJisuan1 = 10;
      } else if (Transaction === 'elevenToTwentyFiveYearTransactionsPerNumber') {
        nianjiaoyishuJisuan = 11;
        nianjiaoyishuJisuan1 = 25;
      } else if (Transaction === 'twenty-sixFiftyToYearTransactionsPerNumber') {
        nianjiaoyishuJisuan = 26;
        nianjiaoyishuJisuan1 = 50;
      } else if (Transaction === 'fifty-oneOneHundred ToYearTransactionsPerNumber') {
        nianjiaoyishuJisuan = 51;
        nianjiaoyishuJisuan1 = 100;
      } else if (Transaction === 'greaterThanOneHundredYearTransactionsPerNumber') {
        nianjiaoyishuJisuan = 100;
        nianjiaoyishuJisuan1 = 100;
      }
      if (jiaoyijingyanJisuan === 10) {
        zongjishu333 =
          '>' +
          jiaoyijingyanJisuan * nianjiaoyishuJisuan;
        // + `${this.$i18n.t('registerOpenAccount.openAccount.common.TradingAndExperience.jiaoyiBishu')}`; // $i18n - 交易笔数
      } else if (nianjiaoyishuJisuan === 100) {
        zongjishu333 =
          '>' +
          jiaoyijingyanJisuan1 * nianjiaoyishuJisuan1;
        // + `${this.$i18n.t('registerOpenAccount.openAccount.common.TradingAndExperience.jiaoyiBishu')}`; // $i18n - 交易笔数
      } else {
        zongjishu333 =
          jiaoyijingyanJisuan * nianjiaoyishuJisuan +
          '-' +
          jiaoyijingyanJisuan1 * nianjiaoyishuJisuan1;
        // + `${this.$i18n.t('registerOpenAccount.openAccount.common.TradingAndExperience.jiaoyiBishu')}`; // $i18n - 交易笔数
      }

      // this.register4.zongjiaoyiBishu1 = jiaoyijingyanJisuan*nianjiaoyishuJisuan;
      if (index === 1) {
        this.$set(this.person2, 'zongjiaoyiBishu1', zongjishu333);
      } else if (index === 2) {
        this.$set(this.person2, 'zongjiaoyiBishu2', zongjishu333);
      } else if (index === 4) {
        this.$set(this.person2, 'zongjiaoyiBishu4', zongjishu333);
      } else if (index === 5) {
        this.$set(this.person2, 'zongjiaoyiBishu5', zongjishu333);
      } else if (index === 6) {
        this.$set(this.person2, 'zongjiaoyiBishu6', zongjishu333);
      }
      this.person2.JYxuke[index - 1].zongjiaoyiBishu1 = zongjishu333;
    },
    // 验证表单 跳转页面
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          // 判断经验是否为空
          if (this.person2.JYxuke[0].JYtrading !== '' ||
              this.person2.JYxuke[1].JYtrading !== '' ||
              this.person2.JYxuke[2].yatai.length > 0 ||
              this.person2.JYxuke[3].JYtrading !== '' ||
              this.person2.JYxuke[4].JYtrading !== '' ||
              this.person2.JYxuke[5].JYtrading !== '' ||
              this.person2.JYxuke[6].ouzhou.length > 0 || this.person2.JYxuke[6].beimei.length > 0 ||
              this.person2.JYxuke[7].suoyouOuzhou.length > 0 ||
              this.person2.JYxuke[8].yatai.length > 0 ||
              this.person2.JYxuke[9].yatai.length > 0
          ) {
            // this.submitFormData();
            this.PageLogicJudgment();
          } else {
            this.$message.error(`${this.$i18n.t('registerOpenAccount.common.formPrompt.trading1')}`); // $i18n - 请至少选择一种交易经验
            return;
          }
        } else {
          this.$message.error(`${this.$i18n.t('registerOpenAccount.common.formPrompt.fillError')}`); // $i18n - 表单未填写或填写错误
          return false;
        }
      });
    },
    // 提交表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const updatedBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();

      let accountPerRegulatories = {}; // 个人规管信息
      let accountPerAsset = {}; // 个人资产信息
      let accountPerInvestTargets = {}; // 个人投资目标信息
      const accountPerTradeExpList = []; // 个人交易经验信息

      if (this.newValue.newAccountregulation == null) { // 个人规管信息
        accountPerRegulatories = {
          accountId,
          createdBy,
          createdDate,
          updatedBy,
          employer: this.person2.isstate.ggisshowa ? this.person2.require : null,
          familyMembersCompany: this.person2.isstate.ggisshowa ? this.person2.familynum : null,
          relationshipAccountHolders: this.person2.isstate.ggisshowa ? this.person2.relation : null,
          accountHolderCompanyStock: this.person2.isstate.ggisshowb ? this.person2.GSstockcode : null
        };
      } else {
        this.newValue.newAccountregulation[0].employer = this.person2.isstate.ggisshowa ? this.person2.require : null;
        this.newValue.newAccountregulation[0].familyMembersCompany = this.person2.isstate.ggisshowa ? this.person2.familynum : null;
        this.newValue.newAccountregulation[0].relationshipAccountHolders = this.person2.isstate.ggisshowa ? this.person2.relation : null;
        this.newValue.newAccountregulation[0].accountHolderCompanyStock = this.person2.isstate.ggisshowb ? this.person2.GSstockcode : null;
      }

      if (this.newValue.newAccountCoAsset == null) { // 个人资产信息
        accountPerAsset = {
          accountId,
          createdBy,
          createdDate,
          anualNetIncome: this.person2.asset.zichan1,
          netAssetValue: this.person2.asset.zichan2,
          netCurrentAssets: this.person2.asset.zichan3
        };
      } else {
        for (let i = 0; i < this.newValue.newAccountCoAsset.length; i++) {
          this.newValue.newAccountCoAsset[0].anualNetIncome = this.person2.asset.zichan1;
          this.newValue.newAccountCoAsset[0].netAssetValue = this.person2.asset.zichan2;
          this.newValue.newAccountCoAsset[0].netCurrentAssets = this.person2.asset.zichan3;
        }
      }
      const investmentTarget = this.person2.touzimubiao + '';
      if (this.newValue.newAccountCoInvestmentTarget == null) { // 个人投资目标信息
        accountPerInvestTargets = {
          accountId,
          createdBy,
          createdDate,
          investmentTarget
        };
      } else {
        const newAccountCoInvestmentTarget = JSON.parse(JSON.stringify(this.newValue.newAccountCoInvestmentTarget));
        this.newValue.newAccountCoInvestmentTarget = [];
        for (let i = 0; i < newAccountCoInvestmentTarget.length; i++) {
          newAccountCoInvestmentTarget[i].investmentTarget = investmentTarget;
          this.newValue.newAccountCoInvestmentTarget.push(JSON.parse(JSON.stringify(newAccountCoInvestmentTarget[i])));
        }
      }

      if (this.newValue.newAccountCoTradeExperiencesList === null) { // 个人交易经验信息
        for (let i = 0; i < this.person2.JYxuke.length; i++) {
          if (this.person2.JYxuke[i].JYtrading !== '' ||
              this.person2.JYxuke[i].yatai.length > 0 ||
              this.person2.JYxuke[i].ouzhou.length > 0 ||
              this.person2.JYxuke[i].beimei.length > 0) {
            accountPerTradeExpList.push({
              accountId,
              createdBy,
              createdDate,
              tradingExperience: this.person2.JYxuke[i].JYtrading,
              yearTransactionsPerNumber: this.person2.JYxuke[i].MNtransactionnum,
              transactionsTotalNumber: this.person2.JYxuke[i].zongjiaoyiBishu1,
              knowledgeLevel: this.person2.JYxuke[i].ZSlevel,
              tradingLocation: this.person2.JYxuke[i].yatai.concat(this.person2.JYxuke[i].ouzhou).concat(this.person2.JYxuke[i].beimei) + '',
              tansactionType: this.person2.JYxuke[i].tansactionType
            });
          }
        }
      } else {
        const newAccountCoTradeExperiencesList = JSON.parse(JSON.stringify(this.newValue.newAccountCoTradeExperiencesList));
        this.newValue.newAccountCoTradeExperiencesList = [];
        let index = null;
        for (let i = 0; i < this.person2.JYxuke.length; i++) {
          for (let j = 0; j < newAccountCoTradeExperiencesList.length; j++) {
            if (this.person2.JYxuke[i].JYtrading !== '' ||
                this.person2.JYxuke[i].yatai.length > 0 ||
                this.person2.JYxuke[i].ouzhou.length > 0 ||
                this.person2.JYxuke[i].beimei.length > 0) {
              if (this.person2.JYxuke[i].tansactionType === newAccountCoTradeExperiencesList[j].tansactionType) {
                newAccountCoTradeExperiencesList[j].tradingExperience = this.person2.JYxuke[i].JYtrading;
                newAccountCoTradeExperiencesList[j].yearTransactionsPerNumber = this.person2.JYxuke[i].MNtransactionnum;
                newAccountCoTradeExperiencesList[j].transactionsTotalNumber = this.person2.JYxuke[i].zongjiaoyiBishu1;
                newAccountCoTradeExperiencesList[j].knowledgeLevel = this.person2.JYxuke[i].ZSlevel;
                newAccountCoTradeExperiencesList[j].tradingLocation = this.person2.JYxuke[i].yatai.concat(this.person2.JYxuke[i].ouzhou).concat(this.person2.JYxuke[i].beimei) + '';
                this.newValue.newAccountCoTradeExperiencesList.push(
                  JSON.parse(JSON.stringify(newAccountCoTradeExperiencesList[j]))
                );
                index = i;
              }
            }
          }
          if (i !== index) { // 判断 交易经验是否有添加
            if (this.person2.JYxuke[i].JYtrading !== '' ||
                this.person2.JYxuke[i].yatai.length > 0 ||
                this.person2.JYxuke[i].ouzhou.length > 0 ||
                this.person2.JYxuke[i].beimei.length > 0) {
              this.newValue.newAccountCoTradeExperiencesList.push({
                accountId,
                createdBy,
                createdDate,
                tradingExperience: this.person2.JYxuke[i].JYtrading,
                yearTransactionsPerNumber: this.person2.JYxuke[i].MNtransactionnum,
                transactionsTotalNumber: this.person2.JYxuke[i].zongjiaoyiBishu1,
                knowledgeLevel: this.person2.JYxuke[i].ZSlevel,
                tradingLocation: this.person2.JYxuke[i].yatai.concat(this.person2.JYxuke[i].ouzhou).concat(this.person2.JYxuke[i].beimei) + '',
                tansactionType: this.person2.JYxuke[i].tansactionType
              });
            }
          }
        }
      }

      const data = {
        accountPerRegulatories: this.newValue.newAccountregulation === null ? accountPerRegulatories : this.newValue.newAccountregulation[0],
        accountPerAsset: this.newValue.newAccountCoAsset === null ? accountPerAsset : this.newValue.newAccountCoAsset[0],
        accountPerInvestTargets: this.newValue.newAccountCoInvestmentTarget === null ? accountPerInvestTargets : this.newValue.newAccountCoInvestmentTarget[0],
        accountPerTradeExpList: this.newValue.newAccountCoTradeExperiencesList === null ? accountPerTradeExpList : this.newValue.newAccountCoTradeExperiencesList
      };
      this.$http({
        url: '/openAccount/personal/saveTwo',
        method: 'put',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/person3' });
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
      this.$verify.routerPermissions({ path: '/openAccount/person1' });
    }
  }
};
</script>
<style scoped>
#ZC-regiMain  .jyDidianBom .el-form-item {
  margin-bottom: 0px;
}
#ZC-regiMain  .jyDidianBom p {
  margin-bottom: 0px;
}

</style>
