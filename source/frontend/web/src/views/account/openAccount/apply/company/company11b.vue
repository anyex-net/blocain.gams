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
            ref="company11b"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="company11b"
            label-width="0px"
            class="demo-ruleForm"
          >
            <!-- <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>WT8</h3>

                </div>
                <div class="formNapeContent">

                </div>
              </div>
            </div> -->
            <div class="formContent">
              <div class="formNape1">
                <div class="formNapeTitle1">
                  <p class="titleP">Certificate of Foreign Intermediary, Foreign Flow-Through Entity, or Certain U.S. Branches for United States Tax Withholding and Reporting</p>
                  <h3 class="color666"><span>{{ $t('registerOpenAccount.openAccount.common.wt8.firm.top.replace') }}</span> W-8IMY <!-- $i18n - 替代 -->
                  </h3>
                  <p class="suiwuGL color666">{{ $t('registerOpenAccount.openAccount.common.wt8.firm.top.manage') }}</p> <!-- $i18n - 美国财政部国家税务管理局 -->
                  <div class="describe color6668">
                    <p>▸ Section references are to the Internal Revenue Code. </p>
                    <p>▸ Information about Form W-8IMY and its separate instructions is at <a href="#">www.irs.gov/formw8imy.</a> </p>
                    <p>▸ Give this form to the withholding agent or payer. Do not send to the IRS.</p>
                  </div>
                  <p class="rowTitel1 color666">Do not use this form for:</p>
                  <ul>
                    <li class="huifu  color6668">A beneficial owner solely claiming foreign status or treaty benefits — <i>Instead, use Form: W-8BEN or W-8BEN-E</i></li>
                    <li class="huifu  color6668">A hybrid entity claiming treaty benefits on its own behalf — <i>Instead, use Form: W-8BEN-E</i></li>
                    <li class="huifu  color6668">A foreign person claiming that income is effectively connected with the conduct of a trade or business in the United States — <i>Instead, use Form: W-8ECI</i></li>
                    <li class="huifu  color6668">A disregarded entity with a single foreign owner that is the beneficial owner of the income to which this form relates. Instead, the single foreign owner should use — <i>Instead, use Form: W-8BEN, W-8ECI, or W-8BEN-E</i></li>
                    <li class="huifu  color6668">A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private foundation, or government of a U.S. possession claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) — <i>Instead, use Form: W-8EXP</i></li>
                    <li class="huifu  color6668">U.S. entity or U.S. citizen or resident — <i>Instead, use Form: W-9</i></li>
                    <li class="huifu  color6668">A foreign person documenting itself for purposes of section 6050W — <i>Instead, use Form: W-8BEN, W-8BENE, or W-8ECI</i></li>
                  </ul>
                </div>
                <!-- part 1 -->
                <div class="formNapeContent1">
                  <div class="itemTitle">
                    <div class="left">Part I</div>
                    <div class="right">Identification of Entity</div>
                  </div>
                  <!-- 1 -->
                  <div class="itemRow color666">
                    <p>1 Name of individual or organization that is acting as intermediary</p>
                    <input v-model="company11b.part1.companyName" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.firm.part1.companyName')" :disabled="true" class="inputwt8"> <!-- $i18n - 公司名 -->
                  </div>
                  <!-- 2 -->
                  <div class="itemRow color666">
                    <p>2 Country of incorporation or organization</p>
                    <el-form-item>
                      <el-select v-model="company11b.part1.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.firm.part1.state')"> <!-- $i18n - 组织所在国家 -->
                        <dictionary dict-code="country" />
                      </el-select>
                    </el-form-item>
                  </div>
                  <!-- 3 -->
                  <div class="itemRow color666">
                    <p>3 Name of disregarded entity (if applicable), see instructions</p>
                    <input v-model="company11b.part1.shitiName" maxlength="30" type="text" :disabled="true" class="inputwt8">
                  </div>
                  <!-- 4 -->
                  <div class="itemRow clearfix">
                    <p class="color666">4 Chapter 3 Status (entity type) (Must check one box only.):</p>
                    <div class="width50 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu4a" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a1" :disabled="true" class="radio1" name="radio1">
                        <label for="paixu4a" class="xiaoshou label1 color6668">QI(including a QDD). Complete Part III.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4b" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a2" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4b" class="xiaoshou label1  color6668">Nonqualified intermediary. Complete Part IV.
                        </label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4c" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a3" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4c" class="xiaoshou label1  color6668">Territory financial institution. Complete Part V.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4d" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a4" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4d" class="xiaoshou label1  color6668">U.S. branch. Complete Part VI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4e" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a5" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4e" class="xiaoshou label1  color6668">Withholding foreign partnership. Complete Part VII.</label>
                      </div>
                    </div>
                    <div class="width50 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu4f" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a6" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4f" class="xiaoshou label1  color6668 ">Withholding foreign trust. Complete Part VII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4g" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a7" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4g" class="xiaoshou label1  color6668 ">Nonwithholding foreign partnership. Complete Part VIII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4h" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a8" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4h" class="xiaoshou label1  color6668 ">Nonwithholding foreign simple trust. Complete Part VIII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu4i" v-model="company11b.part1.radio1" maxlength="30" type="radio" value="a9" :disabled="true" class="radio1 " name="radio1">
                        <label for="paixu4i" class="xiaoshou label1  color6668 ">Nonwithholding foreign grantor trust. Complete Part VIII.</label>
                      </div>
                    </div>
                  </div>
                  <!-- 5 -->
                  <div class="itemRow  clearfix">
                    <p class="lineheight18 color666">5 Chapter 4 Status (FATCA status) (See instructions for details and complete the certification below for the entity's applicable status.) (Must check one box only):</p>
                    <div class="width50 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu5a" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b1" class="radio1" name="radio2" :disabled="true">
                        <label for="paixu5a" class="xiaoshou label1 color6668">Nonparticipating foreign financial institution(FFI)
                          (including an FFI related to a Reporting IGA FFI other than a deemed-compliant FFI, participating
                          FFI, or exempt beneficial owner). Complete Part IX (if applicable).
                        </label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5a" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b2" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5a" class="xiaoshou label1 color6668 ">Participating FFI.
                        </label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5b" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b3" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5b" class="xiaoshou label1  color6668">Reporting Model 1 FFI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5c" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b4" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5c" class="xiaoshou label1  color6668">Reporting Model 2 FFI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5d" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b5" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5d" class="xiaoshou label1  color6668">Registered deemed-compliant FFI (other than a reporting Model 1 FFI, sponsored FFI or nonreporting IGA FFI covered in Part XVIII).</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5e" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b6" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5e" class="xiaoshou label1  color6668">Territory financial institution. Complete Part V.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5f" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b7" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5f" class="xiaoshou label1 color6668 ">Sponsored FFI (other than a certified deemed-compliant sponsored, closely held investment vehicle). Complete Part X.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5g" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b8" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5g" class="xiaoshou label1  color6668">Certified deemed-compliant nonregistering local bank. Complete Part XII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5h" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b9" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5h" class="xiaoshou label1  color6668">Certified deemed-compliant FFI with only low-value accounts. Complete Part XIII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5i" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b10" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5i" class="xiaoshou label1 color6668 ">Certified deemed-compliant sponsored, closely held investment vehicle. Complete Part XIV.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5j" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b11" class="radio1" name="radio2" @click="ApplyColours('XV')">
                        <label for="paixu5j" class="xiaoshou label1 color6668 NOdisabled">Certified deemed-compliant limited life debt investment entity. Complete Part XV.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5k" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b12" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5k" class="xiaoshou label1 color6668 ">Certain investment entities that do not maintain financial accounts. Complete Part XVII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5l" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b13" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5l" class="xiaoshou label1  color6668">Owner-documented FFI. Complete Part XI.</label>
                      </div>
                    </div>
                    <div class="width50 ">
                      <div class="width100 rowTwo left22">
                        <input id="paixu5m" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b14" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5m" class="xiaoshou label1  color6668 ">Restricted Distributor. Complete Part XVI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5n" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b15" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5n" class="xiaoshou label1  color6668 ">Nonreporting IGA FFI. Complete Part XVIII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5o" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b16" class="radio1 " name="radio2" @click="ApplyColours('XIX')">
                        <label for="paixu5o" class="xiaoshou label1  color6668 NOdisabled">Exempt retirement plans. Complete Part XIX.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5p" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b17" class="radio1 " name="radio2" @click="ApplyColours('XX')">
                        <label for="paixu5p" class="xiaoshou label1  color6668 NOdisabled">Excepted nonfinancial group entity. Complete Part XX.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5q" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b18" class="radio1 " name="radio2" @click="ApplyColours('XXI')">
                        <label for="paixu5q" class="xiaoshou label1  color6668 NOdisabled">Excepted nonfinancial start-up company. Complete Part XXI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5r" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b19" class="radio1 " name="radio2" @click="ApplyColours('XXII')">
                        <label for="paixu5r" class="xiaoshou label1  color6668 NOdisabled">Excepted nonfinancial entity in liquidation or bankruptcy. Complete Part XXII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5s" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b20" class="radio1 " name="radio2" @click="ApplyColours('XXIII')">
                        <label for="paixu5s" class="xiaoshou label1  color6668 NOdisabled">Publicly traded NFFE or NFFE affiliate of a Publicly Traded Corporation. Complete Part XXIII.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5t" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b21" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5t" class="xiaoshou label1  color6668 ">Excepted territory NFFE. Complete Part XXIV.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5u" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b22" class="radio1 " name="radio2" @click="ApplyColours('XXV')">
                        <label for="paixu5u" class="xiaoshou label1  color6668 NOdisabled">Active NFFE. Complete Part XXV.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5v" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="b23" class="radio1 " name="radio2" @click="ApplyColours('XXVI')">
                        <label for="paixu5v" class="xiaoshou label1  color6668 NOdisabled">Passive NFFE. Complete Part XXVI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu5w" v-model="company11b.part1.radio2" maxlength="30" type="radio" value="bb24" class="radio1 " name="radio2" :disabled="true">
                        <label for="paixu5w" class="xiaoshou label1  color6668 ">Sponsored direct reporting NFFE. Complete Part XXVII.</label>
                      </div>
                    </div>
                  </div>
                  <div v-show="company11b.isTwoFive" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.hint') }}</div> <!-- $i18n - 请勾选 -->
                  <!-- 6 -->
                  <div class="itemRow color666">
                    <p class="lineheight18 ">6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                    <input v-model="company11b.part1.YongJiuAddress.chengshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.street')" :disabled="true" class="inputwt8"> <!-- $i18n - 街道 -->
                    <div class="top16">
                      <p>City or town, state or province. Include postal code where appropriate.</p>
                      <input v-model="company11b.part1.YongJiuAddress.shegnshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.site')" :disabled="true" class="inputwt8"> <!-- $i18n - 城市,省市,邮政 -->
                    </div>
                    <div class="top16">
                      <p>Country</p>
                      <el-form-item>
                        <el-select v-model="company11b.part1.YongJiuAddress.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.select')"> <!-- $i18n - 请选择 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </div>
                  </div>
                  <!-- 7 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">7 Mailing address (if different from above)</p>
                    <input v-model="company11b.part1.TongxunAddress.chengshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.street')" :disabled="true" class="inputwt8"> <!-- $i18n - 街道 -->
                    <div class="top16">
                      <p>City or town, state or province. Include postal code where appropriate.</p>
                      <input v-model="company11b.part1.TongxunAddress.shegnshi" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.site')" :disabled="true" class="inputwt8"> <!-- $i18n - 城市,省市,邮政 -->
                    </div>
                    <div class="top16">
                      <p>Country</p>
                      <el-form-item>
                        <el-select v-model="company11b.part1.TongxunAddress.state" :no-match-text="$t('account.manage.no_data')" filterable :disabled="true" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.select')"> <!-- $i18n - 请选择 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </div>
                  </div>
                  <!-- 8 -->
                  <div class="itemRow clearfix">
                    <p class="lineheight18 color666">8 U.S. taxpayer identification number (TIN), if required</p>
                    <input v-model="company11b.part1.payTaxesNumber.number" maxlength="11" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(company11b.part1.payTaxesNumber.number,'8')" @focus="CHprocessing(company11b.part1.payTaxesNumber.number,'8')">
                    <div v-show="company11b.isZHshow8" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                    <div class="width50 top10">
                      <div class="width100 rowTwo left22 ">
                        <input id="paixu8a" v-model="company11b.part1.payTaxesNumber.xuankuang" maxlength="30" type="radio" value="c1" class="radio1 " name="radio8">
                        <label for="paixu8a" class="xiaoshou label1  color6668 NOdisabled">QI-EIN</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu8b" v-model="company11b.part1.payTaxesNumber.xuankuang" maxlength="30" type="radio" value="c2" class="radio1 " name="radio8" :disabled="true">
                        <label for="paixu8b" class="xiaoshou label1  color6668 ">WP-EIN</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu8c" v-model="company11b.part1.payTaxesNumber.xuankuang" maxlength="30" type="radio" value="c3" class="radio1 " name="radio8" :disabled="true">
                        <label for="paixu8c" class="xiaoshou label1  color6668 ">WT-EIN</label>
                      </div>
                    </div>
                    <div class="width50 top10">
                      <div class="width100 rowTwo left22">
                        <input id="paixu8d" v-model="company11b.part1.payTaxesNumber.xuankuang" maxlength="30" type="radio" value="c4" class="radio1 " name="radio8">
                        <label for="paixu8d" class="xiaoshou label1  color6668 NOdisabled">EIN</label>
                      </div>
                    </div>
                  </div>

                  <!-- 9 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">9 GIIN (if applicable - "XXXXXX.XXXXX.XX.XXX")</p>
                    <input v-model="company11b.part1.GIIN.number" maxlength="30" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(company11b.part1.GIIN.number,'9')" @focus="CHprocessing(company11b.part1.GIIN.number,'9')">
                  </div>
                  <div v-show="company11b.isZHshow9" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                  <!-- 10 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">10 Reference number(s) (see instructions)</p>
                    <input v-model="company11b.part1.Reference.number" maxlength="30" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(company11b.part1.Reference.number,'10')" @focus="CHprocessing(company11b.part1.Reference.number,'10')">
                  </div>
                  <div v-show="company11b.isZHshow10" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                </div>
                <!-- Part II -->
                <div class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part II</div>
                    <div class="right">Disregarded Entity or Branch Receiving Payment. (Complete only if a disregarded
                      entity with a GIIN or a branch of an FFI in a country other than the FFI's country of residence.
                      See instructions.)
                    </div>
                  </div>
                  <!-- 11 -->
                  <div class="itemRow clearfix">
                    <p class="lineheight18 color666">11 Chapter 4 Status (FATCA status) of disregarded entity or branch receiving payment.</p>
                    <div class="width50 top10">
                      <div class="width100 rowTwo left22">
                        <input id="paixu11a" v-model="company11b.part2.FATCAstatus" maxlength="30" type="radio" value="d1" class="radio1 " name="radio11">
                        <label for="paixu11a" class="xiaoshou label1  color6668 NOdisabled">Branch treated as nonparticipating FFI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu11b" v-model="company11b.part2.FATCAstatus" maxlength="30" type="radio" value="d2" class="radio1 " name="radio11">
                        <label for="paixu11b" class="xiaoshou label1  color6668 NOdisabled">Reporting Model 1 FFI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu11c" v-model="company11b.part2.FATCAstatus" maxlength="30" type="radio" value="d3" class="radio1 " name="radio11">
                        <label for="paixu11c" class="xiaoshou label1  color6668 NOdisabled">U.S. Branch.</label>
                      </div>
                    </div>
                    <div class="width50 top10">
                      <div class="width100 rowTwo left22">
                        <input id="paixu11d" v-model="company11b.part2.FATCAstatus" maxlength="30" type="radio" value="d4" class="radio1 " name="radio11">
                        <label for="paixu11d" class="xiaoshou label1  color6668 NOdisabled">Participating FFI.</label>
                      </div>
                      <div class="width100 top10 rowTwo left22">
                        <input id="paixu11e" v-model="company11b.part2.FATCAstatus" maxlength="30" type="radio" value="d5" class="radio1 " name="radio11">
                        <label for="paixu11e" class="xiaoshou label1  color6668 NOdisabled">Reporting Model 2 FFI.</label>
                      </div>
                    </div>
                    <div v-show="company11b.isshow12" class="CORred top15" style="margin-top:106px;">{{ $t('registerOpenAccount.openAccount.common.wt8.common.selectOne') }}</div> <!-- $i18n - 最少选择一个 -->
                  </div>
                  <!-- 12 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">12 Address of branch (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                    <input v-model="company11b.part2.filialeAddress.chengshi" maxlength="30" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(company11b.part2.filialeAddress.chengshi,'12a')" @focus="CHprocessing(company11b.part2.filialeAddress.chengshi,'12a')">
                    <div v-show="company11b.isZHshow12a" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                    <div class="top16">
                      <p>City or town, state or province. Include postal code where appropriate.</p>
                      <input v-model="company11b.part2.filialeAddress.shegnshi" maxlength="30" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(company11b.part2.filialeAddress.shegnshi,'12b')" @focus="CHprocessing(company11b.part2.filialeAddress.shegnshi,'12b')">
                    </div>
                    <div v-show="company11b.isZHshow12b" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                    <div class="top16">
                      <p>Country</p>
                      <el-form-item>
                        <el-select v-model="company11b.part2.filialeAddress.state" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.select')"> <!-- $i18n - 请选择 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                    </div>
                  </div>
                  <!-- 13 -->
                  <div class="itemRow color666">
                    <p class="lineheight18">13 GIIN (if any)</p>
                    <input v-model="company11b.part2.GIIN.GIINvalue" maxlength="30" type="text" class="inputwt8 inputwt8Huifu" @blur="CHprocessing(company11b.part2.GIIN.GIINvalue,'13')" @focus="CHprocessing(company11b.part2.GIIN.GIINvalue,'13')">
                  </div>
                  <div v-show="company11b.isZHshow13" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.noChinese') }}</div> <!-- $i18n - 请不要填写中文 -->
                </div>
                <!-- Part VIII -->
                <div class="formNapeContent1">
                  <!-- style="height:34px" -->
                  <div class="itemTitle ">
                    <div class="left">Part VIII</div>
                    <div class="right">Nonwithholding Foreign Partnership, Simple Trust, or Grantor Trust
                    </div>
                  </div>
                  <!-- 14 -->
                  <!-- style="height:146px" -->
                  <div class="itemRow">
                    <div class="width100  ">
                      <input id="paixu14a" v-model="company11b.PartVIII.shiti" maxlength="30" type="checkbox" value="e1" class="checkbox1 " name="checkbox14">
                      <label for="paixu14a" class="xiaoshou label2  color666 "><strong class="strong">21a</strong> I certify that the entity identified in Part I:</label>
                      <ul>
                        <li class="left56 top10 lineheight18 color6668">Is a nonwithholding foreign partnership, a nonwithholding foreign simple trust, or a nonwithholding
                          foreign grantor trust and is providing this form for payments that are not effectively connected,
                          or are not treated as effectively connected, with the conduct of a trade or business in the United
                          States; and
                        </li>
                        <li class="left56 top10 lineheight18 color6668">
                          Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required for purposes of chapters 3 and 4, that is subject to the certifications made on this form.
                        </li>
                      </ul>
                    </div>
                    <div class="width100  rowTwo1 left57" style="margin-top:20px;width:90%;">
                      <input id="paixu14b" v-model="company11b.PartVIII.shiti" maxlength="30" type="checkbox" value="e2" class="checkbox1 " name="checkbox14">
                      <label for="paixu14b" class="xiaoshou label2  color666 "><strong class="strong">21b</strong> I certify that the entity identified in Part I is a foreign partnership that is a partner in a lower-tier partnership and is providing this Form W-8IMY for purposes of section 1446.</label>
                    </div>
                  </div>
                  <div v-show="company11b.isshow13" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.selectOne') }}</div> <!-- $i18n - 最少选择一个 -->
                </div>
                <!--根据条件 渲染部分 -->
                <!-- 渲染--Part XV -->
                <div v-if="company11b.isshow=='XV'" class="formNapeContent1">
                  <!-- style="height:34px" -->
                  <div class="itemTitle ">
                    <div class="left">Part XV</div>
                    <div class="right">Certified Deemed-Compliant Limited Life Debt Investment Entity
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXVa" v-model="company11b.Part5isList.PartXV.fuxuan1" maxlength="30" type="checkbox" value="f1" class="checkbox1" name="checkboxXV">
                      <label for="paixuXVa" class="xiaoshou label2   color666"><strong class="strong">28</strong> I certify that the FFI identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Was in existence as of January 17, 2013;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Issued all classes of its debt or equity interests to investors on or before January 17, 2013 pursuant to a trust indenture or similar agreement; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under Regulations section 1.1471- 5(f)(2)(iv)).
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow28" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.shuru') }}</div> <!-- $i18n - 请输入 -->
                </div>
                <!-- 渲染--Part XIX. -->
                <div v-if="company11b.isshow=='XIX'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XIX</div>
                    <div class="right">Exempt Retirement Plans
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <p class="color666">Check box 32a, b, c, d, e, or f, whichever applies.</p>
                      <div class="top20">
                        <input id="paixuXIXa" v-model="company11b.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="j1" class="checkbox1 " name="checkboxXIX">
                        <label for="paixuXIXa" class="xiaoshou label2  color666 "><strong class="strong">32a</strong> I certify that the entity identified in Part I:</label>
                        <ul class="left67">
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is established in a country with which the United States has an income tax treaty in force;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is operated principally to administer or provide pension or retirement benefits; and
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is entitled to treaty benefits on income that the fund derives from U.S. sources (or would be entitled to benefits if it derived any such income) as a resident of the other country which satisfies any applicable limitation on benefits requirement.
                          </li>
                        </ul>
                      </div>
                      <div class="top20">
                        <input id="paixuXIXb" v-model="company11b.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="j2" class="checkbox1 " name="checkboxXIX">
                        <label for="paixuXIXb" class="xiaoshou label2  color666 "><strong class="strong">32b</strong> I certify that the entity identified in Part I:</label>
                        <ul class="left67">
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former employees of one or more employers in consideration for services rendered;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            No single beneficiary has a right to more than 5% of the FFI's assets;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operated; and
                          </li>
                        </ul>
                      </div>
                      <div class="top20  ">
                        <input id="paixuXIXc" v-model="company11b.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="j3" class="checkbox1 " name="checkboxXIX">
                        <label for="paixuXIXc" class="xiaoshou label2  color666 "><strong class="strong">32c</strong> I certify that the entity identified in Part I:</label>
                        <ul class="left67">
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to
                            beneficiaries that are former employees of one or more employers in consideration for services rendered;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Has fewer than 50 participants;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is sponsored by one or more employers, each of which is not an investment entity or passive NFFE;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Employee and employer contributions to the fund (disregarding transfers of assets from other plans described in this part, retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A)) are limited by reference to earned income and compensation of the employee, respectively;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Participants that are not residents of the country in which the fund is established or operated are not entitled to more than 20 percent of the fund's assets; and
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operates.
                            <ul class="left22 rowTwo3 top6">
                              <li>
                                1. Is generally exempt from tax on investment income under the laws of the country in which it is established or operates due to its status as a retirement or pension plan;
                              </li>
                              <li>
                                2. Receives at least 50% of its total contributions from sponsoring employers (disregarding transfers of assets from other plans described in this part, retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, other retirement funds described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A));
                              </li>
                              <li>
                                3. Either does not permit or penalizes distributions or withdrawals made before the occurrence of specified events related to retirement, disability, or death (except rollover distributions to accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts), to retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or to other retirement funds described in this part or in an applicable Model 1 or Model 2 IGA); or
                              </li>
                              <li>
                                4. Limits contributions by employees to the fund by reference to earned income of the employee or may not exceed $50,000 annually.
                              </li>
                            </ul>
                          </li>
                        </ul>
                      </div>
                      <div class="top20 rowTwo1 left57">
                        <input id="paixuXIXd" v-model="company11b.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="j4" class="checkbox1 " name="checkboxXIX">
                        <label for="paixuXIXd" class="xiaoshou label2  color666 "><strong class="strong">32d</strong>  I certify that the entity identified in Part I is formed pursuant to a pension plan that would meet the requirements of section 401(a), other than the requirement that the plan be funded by a trust created or organized in the United States.</label>
                      </div>
                      <div class="top20 rowTwo1 left57">
                        <input id="paixuXIXe" v-model="company11b.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="j5" class="checkbox1 " name="checkboxXIX">
                        <label for="paixuXIXe" class="xiaoshou label2  color666 "><strong class="strong">32e</strong> I certify that the entity identified in Part I is established exclusively to earn income for the benefit of one or more retirement funds described in this part or in an applicable Model 1 or Model 2 IGA, accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts), or retirement and pension accounts described in an applicable Model 1 or Model 2 IGA.</label>
                      </div>
                      <div class="top20">
                        <input id="paixuXIXf" v-model="company11b.Part5isList.PartXIX.fuxuan1" maxlength="30" type="checkbox" value="j6" class="checkbox1 " name="checkboxXIX">
                        <label for="paixuXIXf" class="xiaoshou label2  color666 "><strong class="strong">32f</strong> I certify that the entity identified in Part I:</label>
                        <ul class="left67">
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are current or former employees of the sponsor (or persons designated by such employees); or
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are not current or former employees of such sponsor, but are in consideration of personal services performed for the sponsor.
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow32" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.shuru') }}</div> <!-- $i18n - 请输入 -->
                </div>
                <!-- 渲染--Part XX -->
                <div v-if="company11b.isshow=='XX'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XX</div>
                    <div class="right">Excepted Nonfinancial Group Entity
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXX" v-model="company11b.Part5isList.PartXX.fuxuan1" maxlength="30" type="checkbox" value="k1" class="checkbox1 " name="checkboxXX">
                      <label for="paixuXX" class="xiaoshou label2   color666"><strong class="strong">33</strong> I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is a holding company, treasury center, or captive finance company and substantially all of the entity's activities are functions described in Regulations section 1.1471-5(e)(5)(i)(C) through (E);
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is a member of a nonfinancial group described in Regulations section 1.1471-5(e)(5)(i)(B);
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund or any investment vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow33" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.shuru') }}</div> <!-- $i18n - 请输入 -->
                </div>
                <!-- 渲染--Part XXI -->
                <div v-if="company11b.isshow=='XXI'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXI</div>
                    <div class="right">Excepted Nonfinancial Start-Up Company
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXXI" v-model="company11b.Part5isList.PartXXI.fuxuan1" maxlength="30" type="checkbox" value="l1" class="checkbox1 " name="checkboxXXI">
                      <label for="paixuXXI" class="xiaoshou label2   color666"><strong class="strong">34</strong>  I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Was formed on (or in the case of a new line of business, the date of board resolution approving the new line of business)
                          <input v-model="company11b.Part5isList.PartXXI.input1" maxlength="30" type="text" placeholder="MM/DD/YYYY" class="inputwt8 top6">
                          <!-- <div v-show="company11b.isDataShow" class="CORred">日期不得超过申请日期之前2年</div>
                          <div v-show="company11b.isDataShow2" class="CORred">请输入</div> -->
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business other than that of a financial institution or passive NFFE; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow34" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.shuru') }}</div> <!-- $i18n - 请输入 -->
                </div>
                <!-- 渲染--Part XXII -->
                <div v-if="company11b.isshow=='XXII'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXII</div>
                    <div class="right">Excepted Nonfinancial Entity in Liquidation or Bankruptcy
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <input id="paixuXXII" v-model="company11b.Part5isList.PartXXII.fuxuan1" maxlength="30" type="checkbox" value="m1" class="checkbox1 " name="checkboxXXII">
                      <label for="paixuXXII" class="xiaoshou label2   color666"><strong class="strong">35</strong>  I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Filed a plan of liquidation, filed a plan or reorganization, or filed for bankruptcy on the following date:
                          <el-form-item prop="Part5isList.PartXXII.input1">
                            <el-input v-model="company11b.Part5isList.PartXXII.input1" maxlength="30" type="text" placeholder="MM/DD/YYYY" class=" top6" />
                          </el-form-item>
                          <!-- <div v-show="company11b.isDataShow1" class="CORred">日期不得超过申请日期之前3年</div>
                          <div v-show="company11b.isDataShow2" class="CORred">请输入</div> -->
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Has not been engaged during the past 5 years in business as a financial institution or acted as a passive NFFE;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Has provided, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than three years.
                        </li>

                      </ul>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow35" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.shuru') }}</div> <!-- $i18n - 请输入 -->
                </div>
                <!-- 渲染--Part XXIII -->
                <div v-if="company11b.isshow=='XXIII'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXIII</div>
                    <div class="right">Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <p class="color666">Check box 36a or 36b, whichever applies.</p>
                      <input id="paixuXXIIIa" v-model="company11b.Part5isList.PartXXIII.fuxuan1" maxlength="30" type="checkbox" value="n1" class="checkbox1 " name="checkboxXXIIIa">
                      <label for="paixuXXIIIa" class="xiaoshou label2   color666"><strong class="strong">36a</strong> I certify that:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The entity identified in Part I is a foreign corporation that is not a financial institution; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The stock of such corporation is regularly traded on one or more established securities markets, including
                          <input v-model="company11b.Part5isList.PartXXIII.input1" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.shuru')" class="inputwt8 top6"> <!-- $i18n - 请输入 -->
                        </li>
                      </ul>
                      <div class="top10">
                        <input id="paixuXXIIIb" v-model="company11b.Part5isList.PartXXIII.fuxuan1" maxlength="30" type="checkbox" value="n2" class="checkbox1 " name="checkboxXXIIIb">
                        <label for="paixuXXIIIb" class="xiaoshou label2   color666"><strong class="strong">36b</strong> I certify that:</label>
                        <ul class="left67">
                          <li class="left56 huifu top10 lineheight18 color6668">
                            The entity identified in Part I is a foreign corporation that is not a financial institution;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established securities market;
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            The name of the entity, the stock of which is regularly traded on an established securities market, is
                            <input v-model="company11b.Part5isList.PartXXIII.input2" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.shuru')" class="inputwt8 top6"> <!-- $i18n - 请输入 -->
                            ; and
                          </li>
                          <li class="left56 huifu top10 lineheight18 color6668">
                            The name of the securities market on which the stock is regularly traded is
                            <input v-model="company11b.Part5isList.PartXXIII.input3" maxlength="30" type="text" :placeholder="$t('registerOpenAccount.openAccount.common.wt8.common.shuru')" class="inputwt8 top6"> <!-- $i18n - 请输入 -->
                          </li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow36" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.shuru') }}</div> <!-- $i18n - 请输入 -->
                </div>
                <!-- 渲染--Part XXV -->
                <div v-if="company11b.isshow=='XXV'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXV</div>
                    <div class="right">Active NFFE
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <!-- <p class="color666">Check box 36a or 36b, whichever applies.</p> -->
                      <input id="paixuXXV" v-model="company11b.Part5isList.PartXXV.fuxuan1" maxlength="30" type="checkbox" value="o1" class="checkbox1 " name="checkboxXXVa">
                      <label for="paixuXXV" class="xiaoshou label2   color666"><strong class="strong">38</strong> I certify that:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          The entity identified in Part I is a foreign entity that is not a financial institution;
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Less than 50% of such entity's gross income for the preceding calendar year is passive income; and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of passive assets measured quarterly). See the instructions for the definition of passive income.
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow38" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.selectOne') }}</div> <!-- $i18n - 最少选择一个 -->
                </div>
                <!-- 渲染--Part XXVI -->
                <div v-if="company11b.isshow=='XXVI'" class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXVI</div>
                    <div class="right">Passive NFFE
                    </div>
                  </div>
                  <div class="itemRow">
                    <div class="width100 ">
                      <!-- <p class="color666">Check box 36a or 36b, whichever applies.</p> -->
                      <input id="paixuXXVIa" v-model="company11b.Part5isList.PartXXVI.fuxuan1" maxlength="30" type="checkbox" value="p1" class="checkbox1 " name="checkboxXXVI">
                      <label for="paixuXXVIa" class="xiaoshou label2   color666"><strong class="strong">39</strong> I certify that the entity identified in Part I:</label>
                      <ul class="left67">
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is a foreign entity that is not a financial institution (this category includes an entity organized in a possession of the United States that engages (or holds itself out as being engaged) primarily in the business of investing, reinvesting, or trading in securities, partnership interests, commodities, notional principal contracts, insurance or annuity contracts, or any interest in such security, partnership interest, commodity, notional principal contract, insurance contract or annuity contract); and
                        </li>
                        <li class="left56 huifu top10 lineheight18 color6668">
                          Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required.
                        </li>
                      </ul>
                    </div>
                  </div>
                  <div v-show="company11b.isDataShow39" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.selectOne') }}</div> <!-- $i18n - 最少选择一个 -->
                </div>
                <!--Part XXVIII -->
                <div class="formNapeContent1">
                  <div class="itemTitle clearfix">
                    <div class="left">Part XXVIII</div>
                    <div class="right">Certification
                    </div>
                  </div>
                  <!-- 14 -->
                  <div class="itemRow clearfix">
                    <p class="lineheight18 color6668">Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income for which I am providing this form or any withholding agent that can disburse or make payments of the amounts for which I am providing this form.</p>
                    <p class="top20 color666">I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</p>
                    <h4 class="top30a">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</h4> <!-- $i18n - 签名 -->
                    <p class="top16 lineheight24 color666">{{ $t('registerOpenAccount.openAccount.common.signature.signature4') }}</p> <!-- $i18n - 本人认可，前述信息及账户申请流程中提供的所有 ... -->
                    <ul class="account top20">
                      <li class="color666">{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</li> <!-- $i18n - 账户名称: -->
                      <li style="margin-left:20px" class="color6668">{{ company11b.signature.accountName }}</li>
                    </ul>
                    <br>
                    <ul class="account top12">
                      <li class="color666">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</li> <!-- $i18n - 日期: -->
                      <li style="margin-left:48px" class="color6668">{{ company11b.signature.date }}</li>
                    </ul>
                    <br>
                    <p class="top30a color666">{{ company11b.signature.accountName1 }}</p>
                    <div class="top10">
                      <span class="color666">{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</span> <!-- $i18n - 签名: -->
                      <input v-model="company11b.signature.UserSignature" maxlength="128" type="text" class="inputwt8 inputwt8Huifu left22" style="width:500px;">
                    </div>
                  </div>
                  <div v-show="company11b.isSignature" class="CORred top15">{{ $t('registerOpenAccount.common.formPrompt.signatureHint') }}</div> <!-- $i18n - 签名与上方不一致 或 为空 -->
                  <div class="statement rowTwo2 left25P ">
                    <input id="paixu14a1" v-model="company11b.signature.tongyi" maxlength="30" type="checkbox" value="true" class="checkbox1 " name="checkbox1" checked>
                    <label for="paixu14a1" class="xiaoshou label2  color666">{{ $t('registerOpenAccount.openAccount.common.wt8.common.check') }}</label> <!-- $i18n - 勾选此框即代表您同意所有税务表格 ... -->
                  </div>
                  <div v-show="company11b.isTongyi" class="CORred top15">{{ $t('registerOpenAccount.openAccount.common.wt8.common.hint') }}</div> <!-- $i18n - 请勾选 -->
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitFormTime('company11b')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
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
      // Wt8Type: 'partnerShip',
      Wt8Type: 'partnership (must have at least 2 owners)',
      company11b: {
        part1: {
          companyName: '',
          state: '',
          shitiName: '',
          radio1: 'a7',
          radio2: '',
          YongJiuAddress: {
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          TongxunAddress: { // 邮寄
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          payTaxesNumber: { // 纳税识别号
            number: '',
            xuankuang: ''
          },
          GIIN: { // 9
            number: ''
          },
          Reference: { // 参考编号
            number: ''
          }
        },
        part2: {
          FATCAstatus: '', // FATCAstatus状态
          filialeAddress: {// 12 分公司地址
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          GIIN: {
            GIINvalue: ''
          }
        },
        PartVIII: {
          shiti: []
        },
        // 根据5部分渲染出来表格
        newPart5isListTS: null,
        Part5isList: {
          PartXV: {
            fuxuan1: []
          },
          PartXIX: {
            fuxuan1: []
          },
          PartXX: {
            fuxuan1: []
          },
          PartXXI: {
            fuxuan1: [],
            input1: ''
          },
          PartXXII: {
            fuxuan1: [],
            input1: ''
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
          }
        },
        signature: {
          accountName: '',
          date: '',
          accountName1: '',
          UserSignature: '',
          tongyi: false
        },
        isshow: 'XV',
        isshow12: false,
        isshow13: false,

        isTwoFive: false, // 第一部分 第五小节

        isDataShow28: false, // 渲染部分
        isDataShow32: false,
        isDataShow33: false,
        isDataShow34: false,
        isDataShow35: false,
        isDataShow36: false,
        isDataShow38: false,
        isDataShow39: false,

        isTongyi: false,
        isSignature: false,
        isZHshow8: false, // 中文判断
        isZHshow9: false,
        isZHshow10: false,
        isZHshow12a: false,
        isZHshow12b: false,
        isZHshow13: false
      },
      formdata: {},
      newValue: null,
      rules: {
        'Part5isList.PartXXII.input1': [
          { required: true, message: this.$i18n.t('registerOpenAccount.common.formPrompt.enter'), trigger: 'blur' }
        ]
      }
    };
  },
  created() {
    this.company11b.newPart5isListTS = JSON.parse(JSON.stringify(this.company11b.Part5isList));
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
        url: '/account/accountCoWbenContract/findByAccountId',
        method: 'get',
        params: { type: this.Wt8Type, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.newValue = JSON.parse(JSON.stringify(res.object[i]));
                // 第一部分
                this.company11b.part1.companyName = res.object[i].organizationName;
                this.company11b.part1.state = res.object[i].organizationCountry;
                this.company11b.part1.radio1 = res.object[i].chapterThreeStatus;
                this.company11b.part1.radio2 = res.object[i].chapterFourStatus;
                this.company11b.part1.shitiName = res.object[i].disregardedEntityName;
                this.company11b.part1.payTaxesNumber.number = res.object[i].usaTin;
                this.company11b.part1.payTaxesNumber.xuankuang = res.object[i].ein;
                this.company11b.part1.GIIN.number = res.object[i].gginIf;
                this.company11b.part1.Reference.number = res.object[i].referenceNumber;
                // 第二部分
                this.company11b.part2.filialeAddress.chengshi = res.object[i].branchAddress;
                this.company11b.part2.filialeAddress.shegnshi = res.object[i].branchCity;
                this.company11b.part2.filialeAddress.state = res.object[i].branchCountry;
                this.company11b.part2.FATCAstatus = res.object[i].ffi;
                this.company11b.part2.GIIN.GIINvalue = res.object[i].giinXxx;
                // VIII
                if (res.object[i].nonwithholdingA.indexOf(',') !== -1) {
                  const nonwithholdingA = res.object[i].nonwithholdingA.split(',');
                  for (let i = 0; i < nonwithholdingA.length; i++) {
                    this.company11b.PartVIII.shiti.push(nonwithholdingA[i]);
                  }
                } else {
                  this.company11b.PartVIII.shiti.push(res.object[i].nonwithholdingA);
                }
                // 签名
                this.company11b.signature.tongyi = res.object[i].status;
                // 渲染部分
                const checked = JSON.parse(res.object[i].checked);
                if (checked.type === 'b11') {
                  this.company11b.isshow = 'XV';
                  this.company11b.Part5isList.PartXV = checked.Part5isList.PartXV;
                } else if (checked.type === 'b16') {
                  this.company11b.isshow = 'XIX';
                  this.company11b.Part5isList.PartXIX = checked.Part5isList.PartXIX;
                } else if (checked.type === 'b17') {
                  this.company11b.isshow = 'XX';
                  this.company11b.Part5isList.PartXX = checked.Part5isList.PartXX;
                } else if (checked.type === 'b18') {
                  this.company11b.isshow = 'XXI';
                  this.company11b.Part5isList.PartXXI = checked.Part5isList.PartXXI;
                } else if (checked.type === 'b19') {
                  this.company11b.isshow = 'XXII';
                  this.company11b.Part5isList.PartXXII = checked.Part5isList.PartXXII;
                } else if (checked.type === 'b20') {
                  this.company11b.isshow = 'XXIII';
                  this.company11b.Part5isList.PartXXIII = checked.Part5isList.PartXXIII;
                } else if (checked.type === 'b22') {
                  this.company11b.isshow = 'XXV';
                  this.company11b.Part5isList.PartXXV = checked.Part5isList.PartXXV;
                } else if (checked.type === 'b23') {
                  this.company11b.isshow = 'XXVI';
                  this.company11b.Part5isList.PartXXVI = checked.Part5isList.PartXXVI;
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
        url: '/account/accountCoInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.company11b.part1.companyName = res.object.institutionalName;
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
        url: '/account/accountCoAddress/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.company11b.part1.state = res.object[0].country;
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
        url: '/account/accountCoAddress/findByAccountId/institutional',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    this.company11b.part1.YongJiuAddress.chengshi = res.object[i].address.replace(/-#-/g, ',');
                  } else {
                    this.company11b.part1.YongJiuAddress.chengshi = res.object[i].address;
                  }
                  this.company11b.part1.YongJiuAddress.shegnshi = res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode;
                  this.company11b.part1.YongJiuAddress.state = res.object[i].country;
                } else if (res.object[i].addressType === 'mail') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    this.company11b.part1.TongxunAddress.chengshi = res.object[i].address.replace(/-#-/g, ',');
                  } else {
                    this.company11b.part1.TongxunAddress.chengshi = res.object[i].address;
                  }
                  this.company11b.part1.TongxunAddress.shegnshi = res.object[i].city + ' ' + res.object[i].rovince + ' ' + res.object[i].zipCode;
                  this.company11b.part1.TongxunAddress.state = res.object[i].country;
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
        url: '/account/accountCoPersonal/findByAccountId',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.company11b.signature.accountName = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.company11b.signature.date = this.$verify.NowDate3();
              this.company11b.signature.accountName1 = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.company11b.signature.UserSignature = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
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
            _this.company11b.isZHshow8 = !data;
          } else if (index === '9') {
            _this.company11b.isZHshow9 = !data;
          } else if (index === '10') {
            _this.company11b.isZHshow10 = !data;
          } else if (index === '12a') {
            _this.company11b.isZHshow12a = !data;
          } else if (index === '12b') {
            _this.company11b.isZHshow12b = !data;
          } else if (index === '13') {
            _this.company11b.isZHshow13 = !data;
          }
        };
        this.$verify.notChineseSD(value, yanzhengCallback);
      } else {
        if (index === '8') {
          this.company11b.isZHshow8 = false;
        } else if (index === '9') {
          this.company11b.isZHshow9 = false;
        } else if (index === '10') {
          this.company11b.isZHshow10 = false;
        } else if (index === '12a') {
          this.company11b.isZHshow12a = false;
        } else if (index === '12b') {
          this.company11b.isZHshow12b = false;
        } else if (index === '13') {
          this.company11b.isZHshow13 = false;
        }
      }
    },
    // 第一部分5小节点击事件,完成第五部分渲染部分
    ApplyColours(part) {
      this.company11b.Part5isList = JSON.parse(JSON.stringify(this.company11b.newPart5isListTS));
      this.company11b.isDataShow28 = false; // 渲染部分逻辑
      this.company11b.isDataShow32 = false;
      this.company11b.isDataShow33 = false;
      this.company11b.isDataShow34 = false;
      this.company11b.isDataShow35 = false;
      this.company11b.isDataShow36 = false;
      this.company11b.isDataShow38 = false;
      this.company11b.isDataShow39 = false;
      this.company11b.isshow = part;
    },
    submitFormTime(formName = 'company11b') {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.submitForm();
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    submitForm() {
      // 判断part1 第五小节
      if (this.company11b.part1.radio2 === '') {
        this.company11b.isTwoFive = true;
      } else {
        this.company11b.isTwoFive = false;
      }
      // 判断part1 12的逻辑
      if (this.company11b.part2.FATCAstatus === '') {
        this.company11b.isshow12 = true;
      } else {
        this.company11b.isshow12 = false;
      }
      // 判断 PartVIII 逻辑
      if (this.company11b.PartVIII.shiti.length === 0) {
        this.company11b.isshow13 = true;
      } else {
        this.company11b.isshow13 = false;
      }
      // 判断 根据5部分渲染出来的逻辑
      if (this.company11b.isshow === 'XV') {
        if (this.company11b.Part5isList.PartXV.fuxuan1.length !== 0) {
          this.company11b.isDataShow28 = false;
        } else {
          this.company11b.isDataShow28 = true;
        }
      } else if (this.company11b.isshow === 'XIX') {
        if (this.company11b.Part5isList.PartXIX.fuxuan1.length !== 0) {
          this.company11b.isDataShow32 = false;
        } else {
          this.company11b.isDataShow32 = true;
        }
      } else if (this.company11b.isshow === 'XX') {
        if (this.company11b.Part5isList.PartXX.fuxuan1.length !== 0) {
          this.company11b.isDataShow33 = false;
        } else {
          this.company11b.isDataShow33 = true;
        }
      } else if (this.company11b.isshow === 'XXI') {
        if (this.company11b.Part5isList.PartXXI.fuxuan1.length !== 0) {
          this.company11b.isDataShow34 = false;
        } else {
          this.company11b.isDataShow34 = true;
        }
      } else if (this.company11b.isshow === 'XXII') {
        if (this.company11b.Part5isList.PartXXII.fuxuan1.length !== 0) {
          this.company11b.isDataShow35 = false;
        } else {
          this.company11b.isDataShow35 = true;
        }
      } else if (this.company11b.isshow === 'XXIII') {
        if (this.company11b.Part5isList.PartXXIII.fuxuan1.length !== 0) {
          this.company11b.isDataShow36 = false;
        } else {
          this.company11b.isDataShow36 = true;
        }
      } else if (this.company11b.isshow === 'XXV') {
        if (this.company11b.Part5isList.PartXXV.fuxuan1.length !== 0) {
          this.company11b.isDataShow38 = false;
        } else {
          this.company11b.isDataShow38 = true;
        }
      } else if (this.company11b.isshow === 'XXVI') {
        if (this.company11b.Part5isList.PartXXVI.fuxuan1.length !== 0) {
          this.company11b.isDataShow39 = false;
        } else {
          this.company11b.isDataShow39 = true;
        }
      }
      if (this.company11b.signature.accountName1 !== this.company11b.signature.UserSignature || this.company11b.signature.UserSignature === '') {
        this.company11b.isSignature = true;
      } else {
        this.company11b.isSignature = false;
      }
      // 勾选同意
      if (!this.company11b.signature.tongyi) {
        this.company11b.isTongyi = true;
      } else {
        this.company11b.isTongyi = false;
      }
      if (this.company11b.isshow12 === false &&
          this.company11b.isshow13 === false &&
          this.company11b.isTwoFive === false &&
          this.company11b.isDataShow28 === false && // 渲染部分逻辑
          this.company11b.isDataShow32 === false &&
          this.company11b.isDataShow33 === false &&
          this.company11b.isDataShow34 === false &&
          this.company11b.isDataShow35 === false &&
          this.company11b.isDataShow36 === false &&
          this.company11b.isDataShow38 === false &&
          this.company11b.isDataShow39 === false &&
          this.company11b.isZHshow8 === false && // 中文判断
          this.company11b.isZHshow9 === false &&
          this.company11b.isZHshow10 === false &&
          this.company11b.isZHshow12a === false &&
          this.company11b.isZHshow12b === false &&
          this.company11b.isZHshow13 === false &&
          this.company11b.isTongyi === false &&
          this.company11b.isSignature === false

      ) {
        this.saveEleven();
      } else {
        this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.formError')); // $i18n - 表单验证未通过
      }
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/company10' });
    },
    saveEleven() {
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
          organizationName: this.company11b.part1.companyName,
          organizationCountry: this.company11b.part1.state,
          chapterThreeStatus: this.company11b.part1.radio1,
          chapterFourStatus: this.company11b.part1.radio2,
          disregardedEntityName: this.company11b.part1.shitiName,
          usaTin: this.company11b.part1.payTaxesNumber.number,
          ein: this.company11b.part1.payTaxesNumber.xuankuang,
          gginIf: this.company11b.part1.GIIN.number,
          referenceNumber: this.company11b.part1.Reference.number,
          // 第二部分
          branchAddress: this.company11b.part2.filialeAddress.chengshi,
          branchCity: this.company11b.part2.filialeAddress.shegnshi,
          branchCountry: this.company11b.part2.filialeAddress.state,
          ffi: this.company11b.part2.FATCAstatus,
          giinXxx: this.company11b.part2.GIIN.GIINvalue,
          // VIII
          nonwithholdingA: this.company11b.PartVIII.shiti + '',
          // 渲染部分
          checked: JSON.stringify(
            {
              type: this.company11b.part1.radio2,
              Part5isList: this.company11b.Part5isList
            }
          ),
          // 签名
          signature: this.company11b.signature.UserSignature,
          signatureTime: this.$verify.NowDate(this.company11b.signature.date),
          status: this.company11b.signature.tongyi ? 1 : 0,
          type: this.Wt8Type
        };
      } else {
        // 更新
        // 第一部分
        this.newValue.organizationName = this.company11b.part1.companyName;
        this.newValue.organizationCountry = this.company11b.part1.state;
        this.newValue.chapterThreeStatus = this.company11b.part1.radio1;
        this.newValue.chapterFourStatus = this.company11b.part1.radio2;
        this.newValue.disregardedEntityName = this.company11b.part1.shitiName;
        this.newValue.usaTin = this.company11b.part1.payTaxesNumber.number;
        this.newValue.ein = this.company11b.part1.payTaxesNumber.xuankuang;
        this.newValue.gginIf = this.company11b.part1.GIIN.number;
        this.newValue.referenceNumber = this.company11b.part1.Reference.number;
        // 第二部分
        this.newValue.branchAddress = this.company11b.part2.filialeAddress.chengshi;
        this.newValue.branchCity = this.company11b.part2.filialeAddress.shegnshi;
        this.newValue.branchCountry = this.company11b.part2.filialeAddress.state;
        this.newValue.ffi = this.company11b.part2.FATCAstatus;
        this.newValue.giinXxx = this.company11b.part2.GIIN.GIINvalue;
        // VIII
        this.newValue.nonwithholdingA = this.company11b.PartVIII.shiti + '';
        // 渲染部分
        this.newValue.checked = JSON.stringify(
          {
            type: this.company11b.part1.radio2,
            Part5isList: this.company11b.Part5isList
          }
        );
        // 签名
        this.newValue.signature = this.company11b.signature.UserSignature;
        this.newValue.signatureTime = this.$verify.NowDate(this.company11b.signature.date);
        this.newValue.status = this.company11b.signature.tongyi ? 1 : 0;
        this.newValue.type = this.Wt8Type;
        data = this.newValue;
      }
      this.$http({
        url: '/openAccount/company/saveEleven',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/company12' }); // 暂时所有国家都可以到crs

            // if (this.$store.state.user.applyData.xinxi.country === 'CN') {
            //   this.$verify.routerPermissions({ path: '/openAccount/company12' });
            // } else {
            //   this.$verify.routerPermissions({ path: '/openAccount/company14' });
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

