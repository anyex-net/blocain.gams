<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(9/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership11a"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="partnership11a"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape1">
                <div class="formNapeTitle1">
                  <p class="titleP">Certificate of Status of Beneficial Owner for United States Tax Withholding and Reporting (Entities)</p>
                  <h3 class="color666"><span>{{ $t('registerOpenAccount.openAccount.common.wt8.firm.top.replace') }}</span> W-8BEN-E <!-- $i18n - 替代 -->
                  </h3>
                  <p class="suiwuGL color666">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.top.manage') }}</p> <!-- $i18n - 美国财政部国家税务管理局 -->
                  <div class="describe color6668">
                    <p>▸ For use by entities. Individuals must use Form W-8BEN. </p>
                    <p>▸ Section references are to the Internal Revenue Code. </p>
                    <p>▸ Information about Form W-8BEN-E and its separate instructions is at <a href="#">www.irs.gov/formw8bene. </a> </p>
                    <p>▸ Give this form to the withholding agent or payer. Do not send to the IRS.</p>
                  </div>
                  <p class="rowTitel1 color666">Do NOT use this form for:</p>
                  <ul>
                    <li class="huifu  color6668">U.S. entity or U.S. citizen or resident —<i>Instead, use Form: W-9</i></li>
                    <li class="huifu  color6668">A foreign individual —<i>Instead, use Form: W-8BEN (Individual) or Form 8233</i></li>
                    <li class="huifu  color6668">A foreign individual or entity claiming that income is effectively connected with the conduct of trade or business within the U.S. (unless claiming treaty benefits) —<i>Instead, use Form: W-8ECI</i></li>
                    <li class="huifu  color6668">A foreign partnership, a foreign simple trust, or a foreign grantor trust (unless claiming treaty benefits) (see instructions for exceptions) —<i>Instead, use Form: W- 8IMY</i></li>
                    <li class="huifu  color6668">A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private foundation, or government of a U.S. possession claiming that income is effectively connected U.S. income or that is claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) (unless claiming treaty benefits) (see instructions for other exceptions) —<i>Instead, use Form: W-8ECI or W-8EXP</i></li>
                    <li class="huifu  color6668">Any person acting as an intermediary —<i>Instead, use Form: W-8IMY</i></li>

                  </ul>
                </div>
                <!-- part 1 -->
                <div class="formNapeContent1">
                  <div class="itemTitle">
                    <div class="left">Part I</div>
                    <div class="right">Identification of Beneficial Owner</div>
                  </div>
                  <!-- 1 -->
                  <div class="itemRow color666">
                    <p>1 Name of organization that is the beneficial owner</p>
                    <input v-model="partnership11a.part1.companyName" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.firm.part1.companyName')" :disabled="true" class="inputwt8"> <!-- $i18n - 公司名 -->
                  </div>
                  <!-- 2 -->
                  <div class="itemRow color666">
                    <p>2 Country of incorporation or organization</p>
                    <el-form-item>
                      <el-select v-model="partnership11a.part1.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.firm.part1.state')"> <!-- $i18n - 组织所在国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                  </div>
                  <!-- 3 -->
                  <div class="itemRow color666">
                    <p>3 Name of disregarded entity receiving the payment (if applicable, see instructions)</p>
                    <input v-model="partnership11a.part1.shitiName" maxlength="30" type="text" :disabled="true" class="inputwt8">
                  </div>
                  <!-- 4 -->
                  <div class="itemRow clearfix">
                    <p class="color666">4 Chapter 3 Status (entity type) (Must check one box only.):</p>
                    <div class="width33 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu4a" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a1" :disabled="true" class="radio1" name="radio1">
                        <label for="paixu4a" class="xiaoshou label1 color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.company') }}</label> <!-- $i18n - 股份有限公司 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4b" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a2" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4b" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entity') }}</label> <!-- $i18n - 无视实体 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4c" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a3" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4c" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.parQiye') }}</label> <!-- $i18n - 合伙企业 -->
                      </div>
                    </div>
                    <div class="width33 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu4d" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a4" :disabled="true" class="radio1" name="radio1">
                        <label for="paixu4d" class="xiaoshou label1 color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.trust') }}</label> <!-- $i18n - 简单信托 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4e" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a5" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4e" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.perTrust') }}</label> <!-- $i18n - 授予人信托 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4f" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a6" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4f" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.reTrust') }}</label> <!-- $i18n - 复合信托 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4g" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a7" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4g" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.estate') }}</label> <!-- $i18n - 财产 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4h" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a8" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4h" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.gover') }}</label> <!-- $i18n - 政府 -->
                      </div>
                    </div>
                    <div class="width33 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu4i" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a9" :disabled="true" class="radio1" name="radio1">
                        <label for="paixu4i" class="xiaoshou label1 color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.bank') }}</label> <!-- $i18n - 中央发行银行 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4j" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a10" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4j" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.tissue') }}</label> <!-- $i18n - 免税组织 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4k" v-model="partnership11a.part1.radio1" maxlength="30" type="radio" value="a11" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4k" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.private') }}</label> <!-- $i18n - 私人基金 -->
                      </div>
                    </div>
                    <div style="margin-top: 154px; " class="color666">
                      <p class="lineheight18 ">If you entered disregarded entity, partnership, simple trust, or grantor trust above, is the entity a
                        hybrid making a treaty claim? If "Yes" complete Part III.
                      </p>
                      <div class="rowTwo left22 " style="float: left;">
                        <input id="paixu4l" v-model="partnership11a.part1.radio1b" maxlength="30" type="radio" value="1" class="radio1 " name="radio1b">
                        <label for="paixu4l" class="xiaoshou label1 NOdisabled color6668">Yes
                        </label>
                      </div>
                      <div class="rowTwo left22 " style="float: left;">
                        <input id="paixu4m" v-model="partnership11a.part1.radio1b" maxlength="30" type="radio" value="0" class="radio1 " name="radio1b">
                        <label for="paixu4m" class="xiaoshou label1 NOdisabled  color6668">No
                        </label>
                      </div>
                    </div>
                  </div>
                  <!-- 5 -->
                  <div class="itemRow  clearfix">
                    <p class="lineheight18 color666">5 Chapter 4 Status (FATCA status) (See instructions for details and
                      complete the certification below for the entity's applicable status).
                    </p>
                    <div class="width50 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu5a" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b1" class="radio1" name="radio2" :disabled="true">
                        <label for="paixu5a" class="xiaoshou label1 color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys1') }}</label> <!-- $i18n - 未签署协议的外国金融机构【包 ... -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5b" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b2" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5b" class="xiaoshou label1 color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys2') }}</label> <!-- $i18n - 签署协议的外国金融机构。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5c" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b3" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5c" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys3') }}</label> <!-- $i18n - 在模式1下应申报的外国金融机构。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5d" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b4" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5d" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys4') }}</label> <!-- $i18n - 在模式2下应申报的外国金融机构。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5e" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b5" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5e" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys5') }} </label> <!-- $i18n - 注册视同合规的外国金融机构（排除在模式1下应申报的 ... -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5f" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b6" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5f" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys6') }}</label> <!-- $i18n - 受赞助外国金融机构。请完成第四部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5g" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b7" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5g" class="xiaoshou label1 color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys7') }}</label> <!-- $i18n - 声明视同合规的非注册本地银行。请完成第五部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5h" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b8" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5h" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys8') }}</label> <!-- $i18n - 声明视同合规的仅具有低价值账户的外国金融机构。请完成第六部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5i" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b9" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5i" class="xiaoshou label1  color6668">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys9') }}</label> <!-- $i18n - 声明视同合规的受赞助且被封闭式持有之投资工具。请完成第七部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5j" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b10" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='VIII'">
                        <label for="paixu5j" class="xiaoshou label1 color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys10') }}</label> <!-- $i18n - 声明视同合规的有限寿命债权投资实体。请完成第八部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5k" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b11" class="radio1" name="radio2" :disabled="true">
                        <label for="paixu5k" class="xiaoshou label1 color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys11') }}</label> <!-- $i18n - 某些无金融账户的投资实体。请完成第九部分。 -->
                      </div>
                    </div>
                    <div class="width50 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu5l" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b12" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5l" class="xiaoshou label1  color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys12') }}</label> <!-- $i18n - 在政府间协议下无需申报的外国金融机构。请完成第十二部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5m" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b13" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5m" class="xiaoshou label1  color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys13') }}</label> <!-- $i18n - 免税退休计划。请完成第十五部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5n" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b14" class="radio1 " :disabled="true" name="radio2">
                        <label for="paixu5n" class="xiaoshou label1  color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys14') }}</label> <!-- $i18n - 美国属地金融机构。请完成第十七部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5o" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b15" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XVIII'">
                        <label for="paixu5o" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys15') }}</label> <!-- $i18n - 免受扣缴的非金融集团实体。请完成第十八部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5p" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b16" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XIX'">
                        <label for="paixu5p" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys16') }}</label> <!-- $i18n - 免受扣缴的非金融新创公司。请完成第十九部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5q" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b17" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XX'">
                        <label for="paixu5q" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys17') }}</label> <!-- $i18n - 免受扣缴的处于清算或破产状态的非金融实体。请完成第二十部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5r" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b18" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XXIII'">
                        <label for="paixu5r" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys18') }}</label> <!-- $i18n - 公开交易的非金融外国实体或公开交易之公 ... -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5s" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b19" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5s" class="xiaoshou label1  color6668 ">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys19') }}</label> <!-- $i18n - 免受扣缴的美国属地非金融外国实体。完成第二十四部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5t" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b20" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XXV'">
                        <label for="paixu5t" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys20') }}</label> <!-- $i18n - 积极型非金融外国实体。请完成第二十五部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5u" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b21" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XXVI'">
                        <label for="paixu5u" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys21') }}</label> <!-- $i18n - 消极型非金融外国实体。请完成第二十六部分。 -->
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5v" v-model="partnership11a.part1.radio2" maxlength="30" type="radio" value="b22" class="radio1 " name="radio2" @click="partnership11a.isXuanRshow='XXVIII'">
                        <label for="paixu5v" class="xiaoshou label1  color6668 NOdisabled">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.part1.entitys22') }}</label> <!-- $i18n - 受赞助的直接申报非金融外国实体。请完成第二十八部分。 -->
                      </div>
                    </div>
                  </div>
                  <div v-show="partnership11a.isTwoFive" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.hint') }}</div> <!-- $i18n - 请勾选 -->
                  <!-- 6 -->
                  <div class="itemRow color666">
                    <p class="lineheight18 ">6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                    <input v-model="partnership11a.part1.YongJiuAddress.chengshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.street')" :disabled="true" class="inputwt8"> <!-- $i18n - 街道 -->
                    <div class="top16">
                      <p>City or town, state or province. Include postal code where appropriate.</p>
                      <input v-model="partnership11a.part1.YongJiuAddress.shegnshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.site')" :disabled="true" class="inputwt8"> <!-- $i18n - 城市,省市,邮政 -->
                    </div>
                    <div class="top16">
                      <p>Country</p>
                      <el-form-item>
                        <el-select v-model="partnership11a.part1.YongJiuAddress.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.select')"> <!-- $i18n - 请选择 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </div>
                  </div>
                  <!-- 7 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">7 Mailing address (if different from above)</p>
                    <input v-model="partnership11a.part1.TongxunAddress.chengshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.street')" :disabled="true" class="inputwt8"> <!-- $i18n - 街道 -->
                    <div class="top16">
                      <p>City or town, state or province. Include postal code where appropriate.</p>
                      <input v-model="partnership11a.part1.TongxunAddress.shegnshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.site')" :disabled="true" class="inputwt8"> <!-- $i18n - 城市,省市,邮政 -->
                    </div>
                    <div class="top16">
                      <p>Country</p>
                      <el-form-item>
                        <el-select v-model="partnership11a.part1.TongxunAddress.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.select')"> <!-- $i18n - 请选择 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </div>
                  </div>
                  <!-- 8 -->
                  <div class="itemRow clearfix">
                    <p class="lineheight18 color666">8 U.S. taxpayer identification number (TIN), if required</p>
                    <input v-model="partnership11a.part1.payTaxesNumber.number" maxlength="11" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(partnership11a.part1.payTaxesNumber.number,'8')" @focus="CHprocessing(partnership11a.part1.payTaxesNumber.number,'8')">
                  </div>
                  <div v-show="partnership11a.isZHshow8" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                  <!-- 9a -->
                  <div class="itemRow color666">
                    <p class="lineheight18">9a GIIN</p>
                    <input v-model="partnership11a.part1.GIIN.numberA" maxlength="20" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(partnership11a.part1.GIIN.numberA,'9a')" @focus="CHprocessing(partnership11a.part1.GIIN.numberA,'9a')">
                  </div>
                  <div v-show="partnership11a.isZHshow9a" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                  <!-- 9b -->
                  <div class="itemRow color666">
                    <p class="lineheight18">9b Foreign TIN</p>
                    <input v-model="partnership11a.part1.GIIN.numberB" maxlength="32" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(partnership11a.part1.GIIN.numberB,'9b')" @focus="CHprocessing(partnership11a.part1.GIIN.numberB,'9b')">
                  </div>
                  <div v-show="partnership11a.isZHshow9b" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                  <!-- 10 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">10 Reference number(s) (see instructions)</p>
                    <input v-model="partnership11a.part1.Reference.number" maxlength="10" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(partnership11a.part1.Reference.number,'10')" @focus="CHprocessing(partnership11a.part1.Reference.number,'10')">
                  </div>
                  <div v-show="partnership11a.isZHshow10" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                </div>
                <!-- Part III -->
                <div class="formNapeContent1">
                  <div class="itemTitle ">
                    <div class="left">Part III</div>
                    <div class="right">Claim of Tax Treaty Benefits (if applicable). (For chapter 3 purposes only.)
                    </div>
                  </div>
                  <!-- 13 -->
                  <div class="itemRow color666" style="margin-bottom: 85px;">
                    <div style="float: left;margin-right:150px;line-height:18px">{{ $t('registerOpenAccount.openAccount.common.wt8.common.discounts') }}</div> <!-- $i18n - 14 受益人是否有资格享受与美国达成之税务协定的优惠？ -->
                    <div class="rowTwo left22 " style="float: left;">
                      <input id="paixu14a" v-model="partnership11a.PartIII.radio14" maxlength="30" type="radio" value="c1" class="radio1 " name="radio14">
                      <label for="paixu14a" class="xiaoshou label1 NOdisabled color6668 NOdisabled">Yes
                      </label>
                    </div>
                    <div class="rowTwo left22 " style="float: left;">
                      <input id="paixu14b" v-model="partnership11a.PartIII.radio14" maxlength="30" type="radio" value="c2" class="radio1 " name="radio14">
                      <label for="paixu14b" class="xiaoshou label1 NOdisabled color6668 NOdisabled">No
                      </label>
                    </div>
                  </div>
                </div>
                <!--根据条件 渲染部分 -->
                <!-- 渲染--Part VIII -->
                <div v-show="partnership11a.isXuanRshow=='VIII'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part VIII</div>
                    <div class="right">Certified Deemed-Compliant Limited Life Debt Investment Entity
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuVIII" v-model="partnership11a.Part5isList.PartVIII.fuxuan1" maxlength="30" type="checkbox" value="XRf1" class="checkbox1" name="checkboxVIII">
                      <label for="paixuVIII" class="xiaoshou label2   color666"><strong class="strong">22</strong> I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Was in existence as of January 17, 2013;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Issued all classes of its debt or equity interests to investors on or before January 17, 2013, pursuant to a trust indenture or similar agreement; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under § 1.1471-5(f)(2)(iv)).
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XVIII -->
                <div v-show="partnership11a.isXuanRshow=='XVIII'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XVIII</div>
                    <div class="right">Excepted Nonfinancial Group Entity
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXVIII" v-model="partnership11a.Part5isList.PartXVIII.fuxuan1" maxlength="30" type="checkbox" value="XRf2" class="checkbox1" name="checkboxXVIII">
                      <label for="paixuXVIII" class="xiaoshou label2   color666"><strong class="strong">32</strong> I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is a holding partnerShip, treasury center, or captive finance partnerShip and substantially all of the entity's activities are functions described in §1.1471-5(e)(5)(i)(C) through (E);
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is a member of a nonfinancial group described in §1.1471-5(e)(5)(i) (B);
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XIX -->
                <div v-show="partnership11a.isXuanRshow=='XIX'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XIX</div>
                    <div class="right">Excepted Nonfinancial Start-Up partnerShip
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXIX" v-model="partnership11a.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="f1fa" class="checkbox1" name="checkboxXIX">
                      <label for="paixuXIX" class="xiaoshou label2   color666"><strong class="strong">33</strong> I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Was formed on (or, in the case of a new line of business, the date of board resolution approving the new line of business)
                          <input v-model="partnership11a.Part5isList.PartXIX.inputData" maxlength="30" type="text" placeholder="MM/DD/YYYY" class="inputwt8 inputwt8Huifu top6">
                          <p class="color6668 top6">(date must be less than 24 months prior to date of payment);</p>
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business other than that of a financial institution or passive NFFE;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is investing capital into assets with the intent to operate a business other than that of a financial institution; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XX -->
                <div v-if="partnership11a.isXuanRshow=='XX'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XX</div>
                    <div class="right">Excepted Nonfinancial Entity in Liquidation or Bankruptcy
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXX" v-model="partnership11a.Part5isList.PartXX.fuxuan1" maxlength="30" type="checkbox" value="f1af" class="checkbox1" name="checkboxXX">
                      <label for="paixuXX" class="xiaoshou label2   color666"><strong class="strong">34</strong> I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Filed a plan of liquidation, filed a plan of reorganization, or filed for bankruptcy on
                          <el-form-item prop="Part5isList.PartXX.inputData">
                            <el-input v-model="partnership11a.Part5isList.PartXX.inputData" maxlength="30" type="text" placeholder="MM/DD/YYYY" class="top6" />
                          </el-form-item>
                          <p class="color6668 top6">;</p>
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          During the past 5 years has not been engaged in business as a financial institution or acted as a passive NFFE;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Has, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than three years.
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XXIII -->
                <div v-show="partnership11a.isXuanRshow=='XXIII'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXIII</div>
                    <div class="right">Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <p class="color666">Check box 37a or 37b, whichever applies.</p>
                      <input id="paixuXXIIIa" v-model="partnership11a.Part5isList.PartXXIII.fuxuan1" maxlength="30" type="checkbox" value="f1fdsaa" class="checkbox1" name="checkboxXXIII">
                      <label for="paixuXXIIIa" class="xiaoshou label2   color666"><strong class="strong">37a</strong> I certify that:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The entity identified in Part I is a foreign corporation that is not a financial institution; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The stock of such corporation is regularly traded on one or more established securities markets, including
                          <input v-model="partnership11a.Part5isList.PartXXIII.input1" maxlength="30" type="text" class="inputwt8 inputwt8Huifu top6">
                          <p class="color6668 top6"> (name one securities exchange upon which the stock is regularly traded).</p>
                        </li>
                      </ul>
                      <input id="paixuXXIIIb" v-model="partnership11a.Part5isList.PartXXIII.fuxuan1" maxlength="30" type="checkbox" value="f1fdasf" class="checkbox1" name="checkboxXXIII">
                      <label for="paixuXXIIIb" class="xiaoshou label2   color666"><strong class="strong">37b</strong> I certify that:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The entity identified in Part I is a foreign corporation that is not a financial institution;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established securities market;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The name of the entity, the stock of which is regularly traded on an established securities market, is
                          <input v-model="partnership11a.Part5isList.PartXXIII.input2" maxlength="30" type="text" class="inputwt8 inputwt8Huifu top6">
                          <p class="color6668 top6"> ; and</p>
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The name of the securities market on which the stock is regularly traded is
                          <input v-model="partnership11a.Part5isList.PartXXIII.input3" maxlength="30" type="text" class="inputwt8 inputwt8Huifu top6">
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XXV -->
                <div v-show="partnership11a.isXuanRshow=='XXV'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXV</div>
                    <div class="right">Active NFFE
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXXV" v-model="partnership11a.Part5isList.PartXXV.fuxuan1" maxlength="30" type="checkbox" value="f1dfass" class="checkbox1" name="checkboxXXV">
                      <label for="paixuXXV" class="xiaoshou label2   color666"><strong class="strong">39</strong> I certify that:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The entity identified in Part I is a foreign entity that is not a financial institution;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Less than 50% of such entity's gross income for the preceding calendar year is passive income; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of passive assets measured quarterly) (see instructions for the definition of passive income).
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XXVI -->
                <div v-show="partnership11a.isXuanRshow=='XXVI'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXVI</div>
                    <div class="right">Passive NFFE
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 rowTwo2 left25P">
                      <input id="paixuXXVIa" v-model="partnership11a.Part5isList.PartXXVI.fuxuan1" maxlength="30" type="checkbox" value="f1fas" class="checkbox1" name="checkboxXXVI">
                      <label for="paixuXXVIa" class="xiaoshou label2   color666"><strong class="strong">40a</strong> I certify that the entity identified in Part I is a foreign entity that is not a financial institution (other than an investment entity organized in a possession of the United States) and is not certifying its status as a publicly traded NFFE (or affiliate), excepted territory NFFE, active NFFE, direct reporting NFFE, or sponsored direct reporting NFFE.</label>
                    </div>
                    <p class="color666 top16">Check box 40b or 40c, whichever applies.</p>
                    <div class="width100 rowTwo2 left25P">
                      <input id="paixuXXVIb" v-model="partnership11a.Part5isList.PartXXVI.fuxuan1" maxlength="30" type="checkbox" value="fdd1" class="checkbox1" name="checkboxXXVI">
                      <label for="paixuXXVIb" class="xiaoshou label2   color666"><strong class="strong">40b</strong>I further certify that the entity identified in Part I has no substantial U.S. owners (or, if applicable, no controlling U.S. persons), or </label>
                    </div>
                    <p class="color666 top16">Check box 40b or 40c, whichever applies.</p>
                    <div class="width100 rowTwo2 left25P">
                      <input id="paixuXXVIc" v-model="partnership11a.Part5isList.PartXXVI.fuxuan1" maxlength="30" type="checkbox" value="fsf1" class="checkbox1" name="checkboxXXVI">
                      <label for="paixuXXVIc" class="xiaoshou label2   color666"><strong class="strong">40c</strong>I further certify that the entity identified in Part I has provided the name, address, and TIN of each substantial U.S. owner (or, if applicable, controlling U.S. person) of the NFFE in Part XXIX.</label>
                    </div>
                  </div>
                </div>
                <!-- 渲染--Part XXVIII -->
                <div v-show="partnership11a.isXuanRshow=='XXVIII'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXVIII</div>
                    <div class="right">Sponsored Direct Reporting NFFE (see instructions for when this is permitted)
                    </div>
                  </div>
                  <div class="itemRow">
                    <p class="color666">42 Name of sponsoring entity:</p>
                    <input v-model="partnership11a.Part5isList.PartXXVIII.input1" maxlength="30" type="text" class="inputwt8 inputwt8Huifu">
                    <div class="width100 top10 rowTwo2 left25P">
                      <input id="paixuXXVIII" v-model="partnership11a.Part5isList.PartXXVIII.fuxuan1" maxlength="30" type="checkbox" value="ffaaf1" class="checkbox1" name="checkboxXXVIII">
                      <label for="paixuXXVIII" class="xiaoshou label2   color666"><strong class="strong">43</strong> I certify that the entity identified in Part I is a direct reporting NFFE that is sponsored by the entity identified on line 42.</label>
                    </div>
                  </div>
                </div>
                <div v-show="partnership11a.isshowXuanRTishi" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.selectOne') }}</div> <!-- $i18n - 最少选择一个 -->
                <!--Part XXX -->
                <div class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXX</div>
                    <div class="right">Certification
                    </div>
                  </div>
                  <!-- 14 -->
                  <div class="itemRow clearfix">
                    <p class="lineheight18 color6668">Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. I further certify under penalties of perjury that:</p>
                    <ul>
                      <li class="left22 huifu top10 lineheight18 color6668">
                        The entity identified on line 1 of this form is the beneficial owner of all the income to which this form relates, is using this form to certify its status for chapter 4 purposes, or is a merchant submitting this form for purposes of section 6050W,
                      </li>
                      <li class="left22 huifu top10 lineheight18 color6668">
                        The entity identified on line 1 of this form is not a U.S. person,
                      </li>
                      <li class="left22 huifu top10 lineheight18 color6668">
                        The income to which this form relates is: (a) not effectively connected with the conduct of a trade or business in the United States, (b) effectively connected but is not subject to tax under an income tax treaty, or (c) the partner's share of a partnership's effectively connected income, and
                      </li>
                      <li class="left22 huifu top10 lineheight18 color6668">
                        For broker transactions or barter exchanges, the beneficial owner is an exempt foreign person as defined in the instructions.
                      </li>
                    </ul>
                    <p class="lineheight18 color6668 top16">Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income of which the entity on line 1 is the beneficial owner or any withholding agent that can disburse or make payments of the income of which the entity on line 1 is the beneficial owner.</p>
                    <p class="top20 color666">I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</p>
                    <h4 class="top30a">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</h4> <!-- $i18n - 签名 -->
                    <p class="top16 lineheight24 color666">{{ $t('registerOpenAccount.openAccount.common.signature.signature4') }}</p> <!-- $i18n - 本人认可，前述信息及账户申请流程 ... -->
                    <ul class="account top20">
                      <li class="color666">{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</li> <!-- $i18n - 账户名称: -->
                      <li style="margin-left:20px" class="color6668">{{ partnership11a.signature.accountName }}</li>
                    </ul>
                    <br>
                    <ul class="account top12">
                      <li class="color666">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</li> <!-- $i18n - 日期: -->
                      <li style="margin-left:48px" class="color6668">{{ partnership11a.signature.date }}</li>
                    </ul>
                    <br>
                    <p class="top30a color666">{{ partnership11a.signature.accountName1 }}</p>
                    <div class="top10">
                      <span class="color666">{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</span> <!-- $i18n - 签名: -->
                      <input v-model="partnership11a.signature.UserSignature" maxlength="128" type="text" class="inputwt8 inputwt8Huifu left22" style="width:500px;">
                    </div>
                  </div>
                  <div v-show="partnership11a.isSignature" class="CORred top15">{{ $t('registerOpenAccount.common.formPrompt.signatureHint') }}</div> <!-- $i18n - 签名与上方不一致 或 为空 -->
                  <div class="statement rowTwo2 left25P">
                    <input id="paixu14a1" v-model="partnership11a.signature.tongyi" maxlength="30" type="checkbox" value="true" class="checkbox1 " name="checkbox1" checked>
                    <label for="paixu14a1" class="xiaoshou label2  color666">{{ $t('registerOpenAccount.openAccount.common.wt8.common.check') }}</label> <!-- $i18n - 勾选此框即代表您同意所有税务表格 ... -->
                  </div>
                  <div v-show="partnership11a.isTongyi" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.hint') }}</div> <!-- $i18n - 请勾选 -->
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitFormValidate('partnership11a')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      Wt8Type: 'enterprise',
      // Wt8Type: 'limitedLiablity',
      partnership11a: {
        part1: {
          companyName: '',
          state: '',
          shitiName: '',
          radio1: 'a1',
          radio1b: '0',
          radio2: '',
          YongJiuAddress: {
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          TongxunAddress: { // 通讯
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          payTaxesNumber: { // 8 纳税识别号
            number: ''
          },
          GIIN: { // 9
            numberA: '',
            numberB: ''
          },
          Reference: { // 参考编号
            number: ''
          }
        },
        // part2:{
        //   FATCAstatus:"",//FATCAstatus状态
        //   // filialeAddress:{//12 分公司地址
        //   //   chengshi:"",
        //   //   shegnshi:"",
        //   //   state:"",
        //   // },
        //   GIIN:{
        //     GIINvalue:"",
        //   }
        // },
        PartIII: {
          radio14: 'c2'
        },
        // 根据5部分渲染出来表格
        Part5isList: {
          PartVIII: {
            fuxuan1: []
          },
          PartXVIII: {
            fuxuan1: []
          },
          PartXIX: {
            fuxuan1: [],
            inputData: ''
          },
          PartXX: {
            fuxuan1: [],
            inputData: ''
          },
          PartXXIII: {
            fuxuan1: [],
            input1: '',
            input2: '',
            input3: ''
          },
          PartXXV: {
            fuxuan1: [],
            input1: ''
          },
          PartXXVI: {
            fuxuan1: [],
            input1: ''
          },
          PartXXVIII: {
            fuxuan1: [],
            input1: ''
          }
        },
        signature: {
          accountName: '',
          date: '',
          accountName1: '',
          UserSignature: '',
          tongyi: false
        },
        isXuanRshow: 'VIII',
        isshowXuanRTishi: false,
        isTwoFive: false, // 第二部分 第五小节
        isSignature: false,
        isTongyi: false,
        isZHshow8: false,
        isZHshow9a: false,
        isZHshow9b: false,
        isZHshow10: false
      },
      formdata: {},
      newValue: null,
      rules: {
        'Part5isList.PartXX.inputData': [
          { required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.enter'), trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.getOne();
    this.getTwo();
    this.accountCoWbenContract();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountCoWbenContract() { // 获取wt8
      this.$http({
        url: '/account/accountParWbenContract/findByAccountId',
        method: 'get',
        params: { type: this.Wt8Type, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.newValue = res.object[i];
                // 第一部分
                this.partnership11a.part1.companyName = res.object[i].organizationName;
                this.partnership11a.part1.state = res.object[i].organizationCountry;
                this.partnership11a.part1.radio1 = res.object[i].chapterThreeStatus;
                this.partnership11a.part1.radio2 = res.object[i].chapterFourStatus;
                this.partnership11a.part1.radio1b = res.object[i].whether;
                this.partnership11a.part1.shitiName = res.object[i].disregardedEntityName;
                this.partnership11a.part1.GIIN.numberB = res.object[i].foreignTin;
                this.partnership11a.part1.GIIN.numberA = res.object[i].giinXxx;
                this.partnership11a.part1.payTaxesNumber.number = res.object[i].usaTin;
                this.partnership11a.part1.Reference.number = res.object[i].referenceNumber;
                // 第三部分
                this.partnership11a.PartIII.radio14 = res.object[i].nonwithholdingA;
                // 签名
                this.partnership11a.signature.tongyi = res.object[i].status;
                // 动态渲染部分
                const checked = JSON.parse(res.object[i].checked);
                if (checked.type === 'b10') {
                  this.partnership11a.isXuanRshow = 'VIII';
                  this.partnership11a.Part5isList.PartVIII = checked.Part5isList.PartVIII;
                } else if (checked.type === 'b15') {
                  this.partnership11a.isXuanRshow = 'XVIII';
                  this.partnership11a.Part5isList.PartXVIII = checked.Part5isList.PartXVIII;
                } else if (checked.type === 'b16') {
                  this.partnership11a.isXuanRshow = 'XIX';
                  this.partnership11a.Part5isList.PartXIX = checked.Part5isList.PartXIX;
                } else if (checked.type === 'b17') {
                  this.partnership11a.isXuanRshow = 'XX';
                  this.partnership11a.Part5isList.PartXX = checked.Part5isList.PartXX;
                } else if (checked.type === 'b18') {
                  this.partnership11a.isXuanRshow = 'XXIII';
                  this.partnership11a.Part5isList.PartXXIII = checked.Part5isList.PartXXIII;
                } else if (checked.type === 'b20') {
                  this.partnership11a.isXuanRshow = 'XXV';
                  this.partnership11a.Part5isList.PartXXV = checked.Part5isList.PartXXV;
                } else if (checked.type === 'b21') {
                  this.partnership11a.isXuanRshow = 'XXVI';
                  this.partnership11a.Part5isList.PartXXVI = checked.Part5isList.PartXXVI;
                } else if (checked.type === 'b22') {
                  this.partnership11a.isXuanRshow = 'XXVIII';
                  this.partnership11a.Part5isList.PartXXVIII = checked.Part5isList.PartXXVIII;
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
    //  获取第一页信息进行渲染
    getOne() {
      this.accountCoInstitutional();
      this.accountCoAddress();
    },
    accountCoInstitutional() { // 获取公司名
      this.$http({
        url: '/account/accountParInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership11a.part1.companyName = res.object[0].institutionalName;
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
    accountCoAddress() { // 获取国家名
      this.$http({
        url: '/account/accountParAddress/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership11a.part1.state = res.object[0].country;
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
    //  获取第二页信息进行渲染
    getTwo() {
      this.personalType();
    },
    accountCoAddressTwo(personalType) { // 公司地址信息-第一页
      this.$http({
        url: '/account/accountParAddress/findByAccountId/institutional',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    this.partnership11a.part1.YongJiuAddress.chengshi = res.object[i].address.replace(/-#-/g, ',');
                  } else {
                    this.partnership11a.part1.YongJiuAddress.chengshi = res.object[i].address;
                  }
                  this.partnership11a.part1.YongJiuAddress.shegnshi = res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode;
                  this.partnership11a.part1.YongJiuAddress.state = res.object[i].country;
                } else if (res.object[i].addressType === 'mail') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    this.partnership11a.part1.TongxunAddress.chengshi = res.object[i].address.replace(/-#-/g, ',');
                  } else {
                    this.partnership11a.part1.TongxunAddress.chengshi = res.object[i].address;
                  }
                  this.partnership11a.part1.TongxunAddress.shegnshi = res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode;
                  this.partnership11a.part1.TongxunAddress.state = res.object[i].country;
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
    // 声明 签名
    getSignature(personalType) {
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership11a.signature.accountName = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership11a.signature.date = this.$verify.NowDate3();
              this.partnership11a.signature.accountName1 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership11a.signature.UserSignature = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
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
            // this.accountCoAddressTwo(res.object[1].dictValue);
            // this.getSignature(res.object[1].dictValue);
            this.accountCoAddressTwo('authorizedPerson');
            this.getSignature('authorizedPerson');
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
    // 非中文处理
    // @blur和@focus
    CHprocessing(value, index) {
      const _this = this;
      if (value !== '') {
        const yanzhengCallback = function(data) {
          if (index === '8') {
            _this.partnership11a.isZHshow8 = !data;
          } else if (index === '9a') {
            _this.partnership11a.isZHshow9a = !data;
          } else if (index === '9b') {
            _this.partnership11a.isZHshow9b = !data;
          } else {
            _this.partnership11a.isZHshow10 = !data;
          }
        };
        this.$verify.notChineseSD(value, yanzhengCallback);
      } else {
        if (index === '8') {
          this.partnership11a.isZHshow8 = false;
        } else if (index === '9a') {
          this.partnership11a.isZHshow9a = false;
        } else if (index === '9b') {
          this.partnership11a.isZHshow9b = false;
        } else if (index === '10') {
          this.partnership11a.isZHshow10 = false;
        }
      }
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/partnership10' });
    },
    submitFormValidate(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitForm();
        } else {
          return false;
        }
      });
    },
    submitForm() {
      // 判断 根据第二部分第五小节
      if (this.partnership11a.part1.radio2 === '') {
        this.partnership11a.isTwoFive = true;
      } else {
        this.partnership11a.isTwoFive = false;
      }
      // 判断 根据5部分渲染出来的逻辑
      if (this.partnership11a.isXuanRshow === 'VIII') {
        if (this.partnership11a.Part5isList.PartVIII.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XVIII') {
        if (this.partnership11a.Part5isList.PartXVIII.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XIX') {
        if (this.partnership11a.Part5isList.PartXIX.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XX') {
        if (this.partnership11a.Part5isList.PartXX.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XXIII') {
        if (this.partnership11a.Part5isList.PartXXIII.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XXV') {
        if (this.partnership11a.Part5isList.PartXXV.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XXVI') {
        if (this.partnership11a.Part5isList.PartXXVI.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      } else if (this.partnership11a.isXuanRshow === 'XXVIII') {
        if (this.partnership11a.Part5isList.PartXXVIII.fuxuan1.length === 0) {
          this.partnership11a.isshowXuanRTishi = true;
        } else {
          this.partnership11a.isshowXuanRTishi = false;
        }
      }
      if (this.partnership11a.signature.accountName1 !== this.partnership11a.signature.UserSignature || this.partnership11a.signature.UserSignature === '') {
        this.partnership11a.isSignature = true;
      } else {
        this.partnership11a.isSignature = false;
      }
      // 勾选同意
      if (!this.partnership11a.signature.tongyi) {
        this.partnership11a.isTongyi = true;
      } else {
        this.partnership11a.isTongyi = false;
      }
      if (this.partnership11a.isshowXuanRTishi === false &&
          this.partnership11a.isTwoFive === false &&
          this.partnership11a.isZHshow8 === false &&
          this.partnership11a.isZHshow9a === false &&
          this.partnership11a.isZHshow9b === false &&
          this.partnership11a.isZHshow10 === false &&
          this.partnership11a.isTongyi === false &&
          this.partnership11a.isSignature === false
      ) {
        // this.$verify.routerPermissions({ path: '/partnership12' });
        this.submitFormData();
      } else {
        alert(this.$i18n.t('registerOpenAccount.common.formPrompt.formError')); // $i18n - 表单验证未通过
      }
    },
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      let data = {};
      // 数据提交请求
      if (this.newValue === null) {
        data = {
          accountId,
          createdBy,
          createdDate,

          // 第一部分
          organizationName: this.partnership11a.part1.companyName,
          organizationCountry: this.partnership11a.part1.state,
          chapterThreeStatus: this.partnership11a.part1.radio1,
          chapterFourStatus: this.partnership11a.part1.radio2,
          whether: this.partnership11a.part1.radio1b,
          disregardedEntityName: this.partnership11a.part1.shitiName,
          foreignTin: this.partnership11a.part1.GIIN.numberB,
          giinXxx: this.partnership11a.part1.GIIN.numberA,
          usaTin: this.partnership11a.part1.payTaxesNumber.number,
          referenceNumber: this.partnership11a.part1.Reference.number,

          // 第三部分
          nonwithholdingA: this.partnership11a.PartIII.radio14,

          // 第4部分
          checked: JSON.stringify(
            {
              type: this.partnership11a.part1.radio2,
              Part5isList: this.partnership11a.Part5isList
            }
          ),
          // 签名
          signature: this.partnership11a.signature.UserSignature,
          signatureTime: this.$verify.NowDate(this.partnership11a.signature.date),
          type: this.Wt8Type,
          status: this.partnership11a.signature.tongyi ? 1 : 0
        };
      } else {
        // 更新
        // 第一部分
        this.newValue.organizationName = this.partnership11a.part1.companyName;
        this.newValue.organizationCountry = this.partnership11a.part1.state;
        this.newValue.chapterThreeStatus = this.partnership11a.part1.radio1;
        this.newValue.chapterFourStatus = this.partnership11a.part1.radio2;
        this.newValue.whether = this.partnership11a.part1.radio1b;
        this.newValue.disregardedEntityName = this.partnership11a.part1.shitiName;
        this.newValue.foreignTin = this.partnership11a.part1.GIIN.numberB;
        this.newValue.giinXxx = this.partnership11a.part1.GIIN.numberA;
        this.newValue.usaTin = this.partnership11a.part1.payTaxesNumber.number;
        this.newValue.referenceNumber = this.partnership11a.part1.Reference.number;

        // 第三部分
        this.newValue.nonwithholdingA = this.partnership11a.PartIII.radio14;

        // 第4部分
        this.newValue.checked = JSON.stringify(
          {
            type: this.partnership11a.part1.radio2,
            Part5isList: this.partnership11a.Part5isList
          }
        );
        // 签名
        this.newValue.signature = this.partnership11a.signature.UserSignature;
        this.newValue.signatureTime = this.$verify.NowDate(this.partnership11a.signature.date);
        this.newValue.type = this.Wt8Type;
        this.newValue.status = this.partnership11a.signature.tongyi ? 1 : 0;
        data = this.newValue;
      }
      this.$http({
        url: '/openAccount/partnerShip/saveEleven',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership12' }); // 暂时所有国家都可以到crs
            // if (this.$store.state.user.applyData.xinxi.country === 'CN') {
            //   this.$verify.routerPermissions({ path: '/openAccount/partnership12' });
            // } else {
            //   this.$verify.routerPermissions({ path: '/openAccount/partnership14' });
            // }
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
  #ZC-regiMain .formNape1 {
    width: 95.78%;
    background-color: #f6f6f6;
    padding: 50px 30px 200px 30px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1{
    margin-bottom: 60px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 p {
    text-align: center;
    /* line-height: 16px; */
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 .titleP {
    font-size: 18px;
    line-height: 30px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 h3 {
    margin-top: 30px;
    text-align: center;
    font-size: 24px;
    font-weight: bold;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 h3 span {
    font-size: 16px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 .suiwuGL {
    margin-top: 18px;
    text-align: center;
    font-size: 16px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 .describe {
    margin-top: 16px;
    font-size: 14px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 .describe a {
    color: rgb(19, 148, 223);
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 .rowTitel1 {
    text-align: left;
    margin-top: 50px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 ul {
    text-align: left;
    margin-top: 16px;
  }
  #ZC-regiMain .formNape1 .formNapeTitle1 ul li {
    margin-top: 8px;
    font-size: 14px;
    line-height: 18px;
  }
  #ZC-regiMain .formNapeContent1 {
    margin-top: 60px;
  }

  #ZC-regiMain .formNapeContent1  .itemTitle {
    min-height: 34px;
    border-top: 1px solid #000;
    border-bottom: 1px solid #000;
    display: flex;

  }
  #ZC-regiMain .formNapeContent1  .itemTitle .left {
    width: 180px;
    background-color: black;
    color: aliceblue;
    display: flex;
    align-items: center;
    justify-content: center;
  }
  #ZC-regiMain .formNapeContent1  .itemTitle .right {
    line-height: 23px;
    margin-top: 6px;
    flex: 1;
    padding-left: 20px;
  }
  #ZC-regiMain .formNapeContent1 .itemRow {
    margin-top: 30px;
    width: 100%;
  }
  #ZC-regiMain .formNapeContent1 .inputwt8 {
    width: 98.5%;
    height: 34px;
    padding: 0;
    padding-left: 10px;
    border: 1px solid rgb(216, 216, 216);
  }
  #ZC-regiMain .formNapeContent1 .inputwt8.inputwt8Huifu {
    background-color: #fff;
  }
  #ZC-regiMain .formNapeContent1 .statement {
    margin-top: 70px;
    padding: 20px 0px 20px 0px;
    border-top: 1px solid #000;
    border-bottom: 1px solid #000;
  }

  /* 声明 */
  #ZC-regiMain .itemRow .account>li {
    float: left;
  }

  input:disabled {
    background-color: #F5F7FA;
  }
  /* 单选框 自定义样式 */
  .radio1{
      display: none;
  }
  .label1{
      position: relative;
      margin-right: 34px;
      line-height: 19px;
  }
  .label1::before{
      display: inline-block;
      content: "";
      width: 16px;
      height: 16px;
      border-radius: 50%;
      background-color: #d8d8d8;
      margin-right: 6px;
      vertical-align: bottom;
  }
  .label1.NOdisabled::before{
    border: 1px solid #d8d8d8;
    background-color:rgb(247, 247, 247);
  }
  .radio1:checked+.label1::before{
      background-color: rgb(46, 143, 235);
  }
  /* 复选框 自定义样式 */
  .checkbox1{
      display: none;
  }
  .label2{
      position: relative;
      margin-right: 34px;
      line-height: 19px;
      color: rgba(102,102,102,0.8);
  }
  .label2::before{
      display: inline-block;
      content: "";
      width: 16px;
      height: 16px;
      background-color: #ffffff;
      border: 1px solid rgb(211, 211, 211);
      margin-right: 50px;
      vertical-align: bottom;
  }
  .checkbox1:checked+.label2::before{
      background-color: rgb(46, 143, 235);
  }

  .lineheight18{
    line-height: 18px;
  }
  .lineheight24{
    line-height: 24px;
  }

  /* 实现第一行以下部分的缩进 */
  .left14 {
    margin-left: 14px;
  }
  .rowTwo3{
    /* 给div大盒子加 */
    text-indent : -14px
  }
  .left22 {
    margin-left: 22px;
  }
  .rowTwo{
    /* 给div大盒子加 */
    text-indent : -22px
  }
  .left57 {
    margin-left: 94px;
  }
  .left67 {
    margin-left: 67px;
  }
  .rowTwo1{
    text-indent: -94px;
  }
  #ZC-regiMain .formNapeContent1 .left25P {
    padding-left: 69px;
  }
  .rowTwo2{
    text-indent : -69px
  }

  .width33 {
    width: 33.33%;
    float: left;
  }
  .width50 {
    width: 50%;
    float: left;
  }
  .width100 {
    width: 93%;
  }
  .top6 {
    margin-top: 6px;
  }
  .top10 {
    margin-top: 10px;
  }
  .top12 {
    margin-top: 12px;
  }
  .top16 {
    margin-top: 16px;
  }
  .top20 {
    margin-top: 20px;
  }
  .top30a {
    margin-top: 30px;
  }
  .left56 {
    margin-left: 94px;
  }
  .color666 {
    color: #666;
  }
  .color6668 {
    color: rgba(102,102,102,0.8);
  }
  .color666BG {
    color: #666;
  }
  .color6668BG {
    color: rgba(102,102,102,0.8);
  }
  .strong{
    font-weight: bold;
  }
</style>

