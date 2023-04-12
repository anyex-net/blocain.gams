<template>
  <div>
    <!-- 个人部分 -->
    <div v-if="userInfo.accountType==='personal'" class="wt8ContentG">
      <div>
        <ul v-for="(item,index) in personal.beneficiary" :key="'beneficiary-'+index">
          <li>{{ item.companyNameTitle }}</li>
          <li>{{ item.companyNameValue }}</li>
        </ul>
      </div>
      <div>
        <div>
          <ul
            v-for="(item,index) in personal.treatmentStatement"
            :key="'treatmentStatement-'+index"
          >
            <li>{{ item.companyNameTitle }}</li>
            <li>{{ item.companyNameValue }}</li>
          </ul>
        </div>
      </div>
    </div>
    <!-- 有限公司 -->
    <div v-else-if="userInfo.accountType==='limitedLiablity'" class="wt8ContentY">
      <div v-if="Wt8Type === 'limitedLiabLityA'">
        <el-form
          ref="limitedLiablityA"
          status-icon
          :model="limitedLiablityA"
          label-width="0px"
          class="demo-ruleForm"
        >
          <div class="formNape1">
            <div class="formNapeTitle1">
              <p
                class="titleP"
              >Certificate of Status of Beneficial Owner for United States Tax Withholding and Reporting (Entities)</p>
              <h3 class="color666">
                <span>替代</span> W-8BEN-E
              </h3>
              <p class="suiwuGL color666">美国财政部国家税务管理局</p>
              <div class="describe color6668">
                <p>▸ For use by entities. Individuals must use Form W-8BEN.</p>
                <p>▸ Section references are to the Internal Revenue Code.</p>
                <p>
                  ▸ Information about Form W-8BEN-E and its separate instructions is at
                  <a href="#">www.irs.gov/formw8bene.</a>
                </p>
                <p>▸ Give this form to the withholding agent or payer. Do not send to the IRS.</p>
              </div>
              <p class="rowTitel1 color666">Do NOT use this form for:</p>
              <ul>
                <li class="huifu color6668">
                  U.S. entity or U.S. citizen or resident —
                  <i>Instead, use Form: W-9</i>
                </li>
                <li class="huifu color6668">
                  A foreign individual —
                  <i>Instead, use Form: W-8BEN (Individual) or Form 8233</i>
                </li>
                <li class="huifu color6668">
                  A foreign individual or entity claiming that income is effectively connected with the conduct of trade or business within the U.S. (unless claiming treaty benefits) —
                  <i>Instead, use Form: W-8ECI</i>
                </li>
                <li class="huifu color6668">
                  A foreign partnership, a foreign simple trust, or a foreign grantor trust (unless claiming treaty benefits) (see instructions for exceptions) —
                  <i>Instead, use Form: W- 8IMY</i>
                </li>
                <li class="huifu color6668">
                  A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private foundation, or government of a U.S. possession claiming that income is effectively connected U.S. income or that is claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) (unless claiming treaty benefits) (see instructions for other exceptions) —
                  <i>Instead, use Form: W-8ECI or W-8EXP</i>
                </li>
                <li class="huifu color6668">
                  Any person acting as an intermediary —
                  <i>Instead, use Form: W-8IMY</i>
                </li>
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
                <input
                  v-model="limitedLiablityA.part1.companyName"
                  maxlength="30"
                  type="text"
                  placeholder="公司名"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 2 -->
              <div class="itemRow color666">
                <p>2 Country of incorporation or organization</p>
                <el-form-item>
                  <el-select
                    v-model="limitedLiablityA.part1.state"
                    :disabled="true"
                    placeholder="请选择"
                  >
                    <dictionary dict-code="country"></dictionary>
                  </el-select>
                </el-form-item>
              </div>
              <!-- 3 -->
              <div class="itemRow color666">
                <p>3 Name of disregarded entity receiving the payment (if applicable, see instructions)</p>
                <input
                  v-model="limitedLiablityA.part1.shitiName"
                  maxlength="30"
                  type="text"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 4 -->
              <div class="itemRow" style="overflow: hidden;">
                <p class="color666">4 Chapter 3 Status (entity type) (Must check one box only.):</p>
                <div class="width33">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4a"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a1"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4a" class="xiaoshou label1 color6668">股份有限公司</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4b"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a2"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4b" class="xiaoshou label1 color6668">无视实体</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4c"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a3"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4c" class="xiaoshou label1 color6668">合伙企业</label>
                  </div>
                </div>
                <div class="width33">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4d"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a4"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4d" class="xiaoshou label1 color6668">简单信托</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4e"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a5"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4e" class="xiaoshou label1 color6668">授予人信托</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4f"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a6"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4f" class="xiaoshou label1 color6668">复合信托</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4g"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a7"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4g" class="xiaoshou label1 color6668">财产</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4h"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a8"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4h" class="xiaoshou label1 color6668">政府</label>
                  </div>
                </div>
                <div class="width33">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4i"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a9"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4i" class="xiaoshou label1 color6668">中央发行银行</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4j"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a10"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4j" class="xiaoshou label1 color6668">免税组织</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4k"
                      v-model="limitedLiablityA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a11"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4k" class="xiaoshou label1 color6668">私人基金</label>
                  </div>
                </div>
                <div style="margin-top: 154px; " class="color666">
                  <p class="lineheight18">
                    If you entered disregarded entity, partnership, simple trust, or grantor trust above, is the entity a
                    hybrid making a treaty claim? If "Yes" complete Part III.
                  </p>
                  <div class="rowTwo left22" style="float: left;">
                    <input
                      id="paixu4l"
                      v-model="limitedLiablityA.part1.radio1b"
                      maxlength="30"
                      type="radio"
                      value="1"
                      class="radio1"
                      name="radio1b"
                    >
                    <label for="paixu4l" class="xiaoshou label1 NOdisabled color6668">Yes</label>
                  </div>
                  <div class="rowTwo left22" style="float: left;">
                    <input
                      id="paixu4m"
                      v-model="limitedLiablityA.part1.radio1b"
                      maxlength="30"
                      type="radio"
                      value="0"
                      class="radio1"
                      name="radio1b"
                    >
                    <label for="paixu4m" class="xiaoshou label1 NOdisabled color6668">No</label>
                  </div>
                </div>
              </div>
              <!-- 5 -->
              <div class="itemRow" style="overflow: hidden;">
                <p class="lineheight18 color666">
                  5 Chapter 4 Status (FATCA status) (See instructions for details and
                  complete the certification below for the entity's applicable status).
                </p>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5a"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b1"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5a"
                      class="xiaoshou label1 color6668"
                    >未签署协议的外国金融机构【包括受限制的外国金融机构或与在政府间协议下应申报的外国金融机构（而非注册视同合规外国金融机构或签署协议的外国金融机构）相关联的外国金融机构】。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5b"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b2"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5b" class="xiaoshou label1 color6668">签署协议的外国金融机构。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5c"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b3"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5c" class="xiaoshou label1 color6668">在模式1下应申报的外国金融机构。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5d"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b4"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5d" class="xiaoshou label1 color6668">在模式2下应申报的外国金融机构。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5e"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b5"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5e"
                      class="xiaoshou label1 color6668"
                    >注册视同合规的外国金融机构（排除在模式1下应申报的外国金融机构、受赞助外国金融机构或第十二部分中提到的在政府间协议下无需申报的外国金融机构）。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5f"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b6"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5f" class="xiaoshou label1 color6668">受赞助外国金融机构。请完成第四部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5g"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b7"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5g" class="xiaoshou label1 color6668">声明视同合规的非注册本地银行。请完成第五部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5h"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b8"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5h"
                      class="xiaoshou label1 color6668"
                    >声明视同合规的仅具有低价值账户的外国金融机构。请完成第六部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5i"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b9"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5i"
                      class="xiaoshou label1 color6668"
                    >声明视同合规的受赞助且被封闭式持有之投资工具。请完成第七部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5j"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b10"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='VIII'"
                    >
                    <label
                      for="paixu5j"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >声明视同合规的有限寿命债权投资实体。请完成第八部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5k"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b11"
                      class="radio1"
                      name="radio2"
                    >
                    <label for="paixu5k" class="xiaoshou label1 color6668">某些无金融账户的投资实体。请完成第九部分。</label>
                  </div>
                </div>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5l"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b12"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5l"
                      class="xiaoshou label1 color6668"
                    >在政府间协议下无需申报的外国金融机构。请完成第十二部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5m"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b13"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5m" class="xiaoshou label1 color6668">免税退休计划。请完成第十五部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5n"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b14"
                      class="radio1"
                      :disabled="true"
                      name="radio2"
                    >
                    <label for="paixu5n" class="xiaoshou label1 color6668">美国属地金融机构。请完成第十七部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5o"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b15"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XVIII'"
                    >
                    <label
                      for="paixu5o"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >免受扣缴的非金融集团实体。请完成第十八部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5p"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b16"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XIX'"
                    >
                    <label
                      for="paixu5p"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >免受扣缴的非金融新创公司。请完成第十九部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5q"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b17"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XX'"
                    >
                    <label
                      for="paixu5q"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >免受扣缴的处于清算或破产状态的非金融实体。请完成第二十部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5r"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b18"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XXIII'"
                    >
                    <label
                      for="paixu5r"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >公开交易的非金融外国实体或公开交易之公司的附属非金融外国实体。请完成第二十三部分，</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5s"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b19"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5s"
                      class="xiaoshou label1 color6668"
                    >免受扣缴的美国属地非金融外国实体。完成第二十四部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5t"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b20"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XXV'"
                    >
                    <label
                      for="paixu5t"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >积极型非金融外国实体。请完成第二十五部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5u"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b21"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XXVI'"
                    >
                    <label
                      for="paixu5u"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >消极型非金融外国实体。请完成第二十六部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5v"
                      v-model="limitedLiablityA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b22"
                      class="radio1"
                      name="radio2"
                      @click="limitedLiablityA.isXuanRshow='XXVIII'"
                    >
                    <label
                      for="paixu5v"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >受赞助的直接申报非金融外国实体。请完成第二十八部分。</label>
                  </div>
                </div>
              </div>
              <div v-show="limitedLiablityA.isTwoFive" class="CORred">请勾选</div>
              <!-- 6 -->
              <div class="itemRow color666">
                <p
                  class="lineheight18"
                >6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                <input
                  v-model="limitedLiablityA.part1.YongJiuAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="limitedLiablityA.part1.YongJiuAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="limitedLiablityA.part1.YongJiuAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 7 -->
              <div class="itemRow color666">
                <p class="lineheight18">7 Mailing address (if different from above)</p>
                <input
                  v-model="limitedLiablityA.part1.TongxunAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="limitedLiablityA.part1.TongxunAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="limitedLiablityA.part1.TongxunAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 8 -->
              <div class="itemRow" style="height: 66px;">
                <p
                  class="lineheight18 color666"
                >8 U.S. taxpayer identification number (TIN), if required</p>
                <input
                  v-model="limitedLiablityA.part1.payTaxesNumber.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(limitedLiablityA.part1.payTaxesNumber.number,'8')"
                  @focus="CHprocessingYA(limitedLiablityA.part1.payTaxesNumber.number,'8')"
                >
              </div>
              <div v-show="limitedLiablityA.isZHshow8" class="CORred">请不要填写中文</div>
              <!-- 9a -->
              <div class="itemRow color666">
                <p class="lineheight18">9a GIIN</p>
                <input
                  v-model="limitedLiablityA.part1.GIIN.numberA"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(limitedLiablityA.part1.GIIN.numberA,'9a')"
                  @focus="CHprocessingYA(limitedLiablityA.part1.GIIN.numberA,'9a')"
                >
              </div>
              <div v-show="limitedLiablityA.isZHshow9a" class="CORred">请不要填写中文</div>
              <!-- 9b -->
              <div class="itemRow color666">
                <p class="lineheight18">9b Foreign TIN</p>
                <input
                  v-model="limitedLiablityA.part1.GIIN.numberB"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(limitedLiablityA.part1.GIIN.numberB,'9b')"
                  @focus="CHprocessingYA(limitedLiablityA.part1.GIIN.numberB,'9b')"
                >
              </div>
              <div v-show="limitedLiablityA.isZHshow9b" class="CORred">请不要填写中文</div>
              <!-- 10 -->
              <div class="itemRow color666">
                <p class="lineheight18">10 Reference number(s) (see instructions)</p>
                <input
                  v-model="limitedLiablityA.part1.Reference.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(limitedLiablityA.part1.Reference.number,'10')"
                  @focus="CHprocessingYA(limitedLiablityA.part1.Reference.number,'10')"
                >
              </div>
              <div v-show="limitedLiablityA.isZHshow10" class="CORred">请不要填写中文</div>
            </div>
            <!-- Part III -->
            <div class="formNapeContent1">
              <div class="itemTitle">
                <div class="left">Part III</div>
                <div
                  class="right"
                >Claim of Tax Treaty Benefits (if applicable). (For chapter 3 purposes only.)</div>
              </div>
              <!-- 13 -->
              <div class="itemRow color666" style="margin-bottom: 85px;">
                <div
                  style="float: left;margin-right:150px;line-height:18px"
                >14 受益人是否有资格享受与美国达成之税务协定的优惠？</div>
                <div class="rowTwo left22" style="float: left;">
                  <input
                    id="paixu14a"
                    v-model="limitedLiablityA.PartIII.radio14"
                    maxlength="30"
                    type="radio"
                    value="c1"
                    class="radio1"
                    name="radio14"
                  >
                  <label for="paixu14a" class="xiaoshou label1 color6668">Yes</label>
                </div>
                <div class="rowTwo left22" style="float: left;">
                  <input
                    id="paixu14b"
                    v-model="limitedLiablityA.PartIII.radio14"
                    maxlength="30"
                    type="radio"
                    value="c2"
                    class="radio1"
                    name="radio14"
                  >
                  <label for="paixu14b" class="xiaoshou label1 color6668">No</label>
                </div>
              </div>
            </div>
            <!--根据条件 渲染部分 -->
            <!-- 渲染--Part VIII -->
            <div v-show="limitedLiablityA.isXuanRshow=='VIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part VIII</div>
                <div class="right">Certified Deemed-Compliant Limited Life Debt Investment Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuVIII"
                    v-model="limitedLiablityA.Part5isList.PartVIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="XRf1"
                    class="checkbox1"
                    name="checkboxVIII"
                  >
                  <label for="paixuVIII" class="xiaoshou label2 color666">
                    <strong class="strong">22</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="paddingLeft0">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Was in existence as of January 17, 2013;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Issued all classes of its debt or equity interests to investors on or before January 17, 2013, pursuant to a trust indenture or similar agreement; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under § 1.1471-5(f)(2)(iv)).</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XVIII -->
            <div v-show="limitedLiablityA.isXuanRshow=='XVIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XVIII</div>
                <div class="right">Excepted Nonfinancial Group Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXVIII"
                    v-model="limitedLiablityA.Part5isList.PartXVIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="XRf2"
                    class="checkbox1"
                    name="checkboxXVIII"
                  >
                  <label for="paixuXVIII" class="xiaoshou label2 color666">
                    <strong class="strong">32</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a holding company, treasury center, or captive finance company and substantially all of the entity's activities are functions described in §1.1471-5(e)(5)(i)(C) through (E);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a member of a nonfinancial group described in §1.1471-5(e)(5)(i) (B);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XIX -->
            <div v-show="limitedLiablityA.isXuanRshow=='XIX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XIX</div>
                <div class="right">Excepted Nonfinancial Start-Up Company</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXIX"
                    v-model="limitedLiablityA.Part5isList.PartXIX.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fa"
                    class="checkbox1"
                    name="checkboxXIX"
                  >
                  <label for="paixuXIX" class="xiaoshou label2 color666">
                    <strong class="strong">33</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Was formed on (or, in the case of a new line of business, the date of board resolution approving the new line of business)
                      <input
                        v-model="limitedLiablityA.Part5isList.PartXIX.inputData"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p
                        class="color6668 top6"
                      >(date must be less than 24 months prior to date of payment);</p>
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business other than that of a financial institution or passive NFFE;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is investing capital into assets with the intent to operate a business other than that of a financial institution; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XX -->
            <div v-show="limitedLiablityA.isXuanRshow=='XX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XX</div>
                <div class="right">Excepted Nonfinancial Entity in Liquidation or Bankruptcy</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXX"
                    v-model="limitedLiablityA.Part5isList.PartXX.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1af"
                    class="checkbox1"
                    name="checkboxXX"
                  >
                  <label for="paixuXX" class="xiaoshou label2 color666">
                    <strong class="strong">34</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Filed a plan of liquidation, filed a plan of reorganization, or filed for bankruptcy on
                      <input
                        v-model="limitedLiablityA.Part5isList.PartXX.inputData"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p class="color6668 top6">;</p>
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >During the past 5 years has not been engaged in business as a financial institution or acted as a passive NFFE;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Has, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than three years.</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXIII -->
            <div v-show="limitedLiablityA.isXuanRshow=='XXIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXIII</div>
                <div
                  class="right"
                >Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <p class="color666">Check box 37a or 37b, whichever applies.</p>
                  <input
                    id="paixuXXIIIa"
                    v-model="limitedLiablityA.Part5isList.PartXXIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fdsaa"
                    class="checkbox1"
                    name="checkboxXXIII"
                  >
                  <label for="paixuXXIIIa" class="xiaoshou label2 color666">
                    <strong class="strong">37a</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign corporation that is not a financial institution; and</li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The stock of such corporation is regularly traded on one or more established securities markets, including
                      <input
                        v-model="limitedLiablityA.Part5isList.PartXXIII.input1"
                        maxlength="30"
                        type="text"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p
                        class="color6668 top6"
                      >(name one securities exchange upon which the stock is regularly traded).</p>
                    </li>
                  </ul>
                  <input
                    id="paixuXXIIIb"
                    v-model="limitedLiablityA.Part5isList.PartXXIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fdasf"
                    class="checkbox1"
                    name="checkboxXXIII"
                  >
                  <label for="paixuXXIIIb" class="xiaoshou label2 color666">
                    <strong class="strong">37b</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign corporation that is not a financial institution;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established securities market;</li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The name of the entity, the stock of which is regularly traded on an established securities market, is
                      <input
                        v-model="limitedLiablityA.Part5isList.PartXXIII.input2"
                        maxlength="30"
                        type="text"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p class="color6668 top6">; and</p>
                    </li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The name of the securities market on which the stock is regularly traded is
                      <input
                        v-model="limitedLiablityA.Part5isList.PartXXIII.input3"
                        maxlength="30"
                        type="text"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXV -->
            <div v-show="limitedLiablityA.isXuanRshow=='XXV'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXV</div>
                <div class="right">Active NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXXV"
                    v-model="limitedLiablityA.Part5isList.PartXXV.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1dfass"
                    class="checkbox1"
                    name="checkboxXXV"
                  >
                  <label for="paixuXXV" class="xiaoshou label2 color666">
                    <strong class="strong">39</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign entity that is not a financial institution;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of such entity's gross income for the preceding calendar year is passive income; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of passive assets measured quarterly) (see instructions for the definition of passive income).</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXVI -->
            <div v-show="limitedLiablityA.isXuanRshow=='XXVI'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVI</div>
                <div class="right">Active NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100 rowTwo2 left25P">
                  <input
                    id="paixuXXVIa"
                    v-model="limitedLiablityA.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fas"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIa" class="xiaoshou label2 color666 xiaoshouPaixu">
                    <strong class="strong">40a</strong> I certify that the entity identified in Part I is a foreign entity that is not a financial institution (other than an investment entity organized in a possession of the United States) and is not certifying its status as a publicly traded NFFE (or affiliate), excepted territory NFFE, active NFFE, direct reporting NFFE, or sponsored direct reporting NFFE.
                  </label>
                </div>
                <p class="color666 top16">Check box 40b or 40c, whichever applies.</p>
                <div class="width100 rowTwo2 left25P">
                  <input
                    id="paixuXXVIb"
                    v-model="limitedLiablityA.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="fdd1"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIb" class="xiaoshou label2 color666">
                    <strong class="strong">40b</strong> I further certify that the entity identified in Part I has no substantial U.S. owners (or, if applicable, no controlling U.S. persons), or
                  </label>
                </div>
                <p class="color666 top16">Check box 40b or 40c, whichever applies.</p>
                <div class="width100 rowTwo2 left25P">
                  <input
                    id="paixuXXVIc"
                    v-model="limitedLiablityA.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="fsf1"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIc" class="xiaoshou label2 color666">
                    <strong class="strong">40c</strong> I further certify that the entity identified in Part I has provided the name, address, and TIN of each substantial U.S. owner (or, if applicable, controlling U.S. person) of the NFFE in Part XXIX.
                  </label>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXVIII -->
            <div v-show="limitedLiablityA.isXuanRshow=='XXVIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVIII</div>
                <div
                  class="right"
                >Sponsored Direct Reporting NFFE (see instructions for when this is permitted)</div>
              </div>
              <div class="itemRow">
                <p class="color666">42 Name of sponsoring entity:</p>
                <input
                  v-model="limitedLiablityA.Part5isList.PartXXVIII.input1"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                >
                <div class="width100 top10 rowTwo2 left25P">
                  <input
                    id="paixuXXVIII"
                    v-model="limitedLiablityA.Part5isList.PartXXVIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="ffaaf1"
                    class="checkbox1"
                    name="checkboxXXVIII"
                  >
                  <label for="paixuXXVIII" class="xiaoshou label2 color666 xiaoshouPaixu">
                    <strong class="strong">40a</strong> I certify that the entity identified in Part I is a foreign entity that is not a financial institution (other than an investment entity organized in a possession of the United States) and is not certifying its status as a publicly traded NFFE (or affiliate), excepted territory NFFE, active NFFE, direct reporting NFFE, or sponsored direct reporting NFFE.
                  </label>
                </div>
              </div>
            </div>
            <div v-show="limitedLiablityA.isshowXuanRTishi" class="CORred">最少选择一个</div>
            <!--Part XXX -->
            <div class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXX</div>
                <div class="right">Certification</div>
              </div>
              <!-- 14 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color6668"
                >Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. I further certify under penalties of perjury that:</p>
                <ul>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >The entity identified on line 1 of this form is the beneficial owner of all the income to which this form relates, is using this form to certify its status for chapter 4 purposes, or is a merchant submitting this form for purposes of section 6050W,</li>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >The entity identified on line 1 of this form is not a U.S. person,</li>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >The income to which this form relates is: (a) not effectively connected with the conduct of a trade or business in the United States, (b) effectively connected but is not subject to tax under an income tax treaty, or (c) the partner's share of a partnership's effectively connected income, and</li>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >For broker transactions or barter exchanges, the beneficial owner is an exempt foreign person as defined in the instructions.</li>
                </ul>
                <p
                  class="lineheight18 color6668 top16"
                >Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income of which the entity on line 1 is the beneficial owner or any withholding agent that can disburse or make payments of the income of which the entity on line 1 is the beneficial owner.</p>
                <p
                  class="top20 color666"
                >I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</p>
                <h4 class="top30a">签名</h4>
                <p
                  class="top16 lineheight24 color666"
                >本人认可，前述信息及账户申请流程中提供的所有其它信息均真实、准确，如该等信息发生重大变化，本人同意通过电子邮件通知国瑞证券。本人授权国瑞证券在必要时确认信息的准确性。</p>
                <ul class="account top20">
                  <li class="color666">账户名称:</li>
                  <li
                    style="margin-left:20px"
                    class="color6668"
                  >{{ limitedLiablityA.signature.accountName }}</li>
                </ul>
                <ul class="account top12">
                  <li class="color666">日期:</li>
                  <li
                    style="margin-left:48px"
                    class="color6668"
                  >{{ limitedLiablityA.signature.date }}</li>
                </ul>
                <p class="top30a color666">{{ limitedLiablityA.signature.accountName1 }}</p>
                <div class="top10">
                  <span class="color666">签名</span>
                  <input
                    v-model="limitedLiablityA.signature.UserSignature"
                    maxlength="30"
                    type="text"
                    class="inputwt8 inputwt8Huifu left22"
                    style="width:500px;"
                  >
                </div>
              </div>
              <div class="statement rowTwo2 left25P" style="margin-top: 482px;">
                <input
                  id="paixu14a1"
                  v-model="limitedLiablityA.signature.tongyi"
                  maxlength="30"
                  type="checkbox"
                  value="true"
                  class="checkbox1"
                  name="checkbox1"
                  checked
                >
                <label
                  for="paixu14a1"
                  class="xiaoshou label2 color666"
                >勾选此框即代表您同意所有税务表格均通过电子形式而非纸质形式收集和发送，
                  包括通过账户管理收集和发送。您也确认该表格中包含的信息以及有关您账户的其他财务信息可能会根据法律要求被报告至账户所在国家的税务部门及/或全球其他税务机构。</label>
              </div>
              <div v-show="limitedLiablityA.isTongyi" class="CORred">请勾选</div>
            </div>
          </div>
        </el-form>
      </div>
      <div v-else-if="Wt8Type === 'limitedLiabLityB'">
        <el-form
          ref="limitedLiablityB"
          status-icon
          :model="limitedLiablityB"
          label-width="0px"
          class="demo-ruleForm"
        >
          <div class="formNape1">
            <div class="formNapeTitle1">
              <p
                class="titleP"
              >Certificate of Foreign Intermediary, Foreign Flow-Through Entity, or Certain U.S. Branches for United States Tax Withholding and Reporting</p>
              <h3 class="color666">
                <span>替代</span> W-8IMY
              </h3>
              <p class="suiwuGL color666">美国财政部国家税务管理局</p>
              <div class="describe color6668">
                <p>▸ Section references are to the Internal Revenue Code.</p>
                <p>
                  ▸ Information about Form W-8IMY and its separate instructions is at
                  <a href="#">www.irs.gov/formw8imy.</a>
                </p>
                <p>▸ Give this form to the withholding agent or payer. Do not send to the IRS.</p>
              </div>
              <p class="rowTitel1 color666">Do not use this form for:</p>
              <ul>
                <li class="huifu color6668">
                  A beneficial owner solely claiming foreign status or treaty benefits —
                  <i>Instead, use Form: W-8BEN or W-8BEN-E</i>
                </li>
                <li class="huifu color6668">
                  A hybrid entity claiming treaty benefits on its own behalf —
                  <i>Instead, use Form: W-8BEN-E</i>
                </li>
                <li class="huifu color6668">
                  A foreign person claiming that income is effectively connected with the conduct of a trade or business in the United States —
                  <i>Instead, use Form: W-8ECI</i>
                </li>
                <li class="huifu color6668">
                  A disregarded entity with a single foreign owner that is the beneficial owner of the income to which this form relates. Instead, the single foreign owner should use —
                  <i>Instead, use Form: W-8BEN, W-8ECI, or W-8BEN-E</i>
                </li>
                <li class="huifu color6668">
                  A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private foundation, or government of a U.S. possession claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) —
                  <i>Instead, use Form: W-8EXP</i>
                </li>
                <li class="huifu color6668">
                  U.S. entity or U.S. citizen or resident —
                  <i>Instead, use Form: W-9</i>
                </li>
                <li class="huifu color6668">
                  A foreign person documenting itself for purposes of section 6050W —
                  <i>Instead, use Form: W-8BEN, W-8BENE, or W-8ECI</i>
                </li>
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
                <input
                  v-model="limitedLiablityB.part1.companyName"
                  maxlength="30"
                  type="text"
                  placeholder="公司名"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 2 -->
              <div class="itemRow color666">
                <p>2 Country of incorporation or organization</p>
                <el-form-item>
                  <el-select
                    v-model="limitedLiablityB.part1.state"
                    :disabled="true"
                    placeholder="请选择"
                  >
                    <dictionary dict-code="country"></dictionary>
                  </el-select>
                </el-form-item>
              </div>
              <!-- 3 -->
              <div class="itemRow color666">
                <p>3 Name of disregarded entity (if applicable), see instructions</p>
                <input
                  v-model="limitedLiablityB.part1.shitiName"
                  maxlength="30"
                  type="text"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 4 -->
              <div class="itemRow height152">
                <p class="color666">4 Chapter 3 Status (entity type) (Must check one box only.):</p>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4a"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a1"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4a"
                      class="xiaoshou label1 color6668"
                    >QI(including a QDD). Complete Part III.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4b"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a2"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4b"
                      class="xiaoshou label1 color6668"
                    >Nonqualified intermediary. Complete Part IV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4c"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a3"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4c"
                      class="xiaoshou label1 color6668"
                    >Territory financial institution. Complete Part V.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4d"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a4"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4d"
                      class="xiaoshou label1 color6668"
                    >U.S. branch. Complete Part VI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4e"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a5"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4e"
                      class="xiaoshou label1 color6668"
                    >Withholding foreign partnership. Complete Part VII.</label>
                  </div>
                </div>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4f"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a6"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4f"
                      class="xiaoshou label1 color6668"
                    >Withholding foreign trust. Complete Part VII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4g"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a7"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4g"
                      class="xiaoshou label1 color6668"
                    >Nonwithholding foreign partnership. Complete Part VIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4h"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a8"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4h"
                      class="xiaoshou label1 color6668"
                    >Nonwithholding foreign simple trust. Complete Part VIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4i"
                      v-model="limitedLiablityB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a9"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4i"
                      class="xiaoshou label1 color6668"
                    >Nonwithholding foreign grantor trust. Complete Part VIII.</label>
                  </div>
                </div>
              </div>
              <!-- 5 -->
              <div class="itemRow" style="height:640px">
                <p
                  class="lineheight18 color666"
                >5 Chapter 4 Status (FATCA status) (See instructions for details and complete the certification below for the entity's applicable status.) (Must check one box only):</p>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5a"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b1"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5a" class="xiaoshou label1 color6668">
                      Nonparticipating foreign financial institution(FFI)
                      (including an FFI related to a Reporting IGA FFI other than a deemed-compliant FFI, participating
                      FFI, or exempt beneficial owner). Complete Part IX (if applicable).
                    </label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5a"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b2"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5a" class="xiaoshou label1 color6668">Participating FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5b"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b3"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5b" class="xiaoshou label1 color6668">Reporting Model 1 FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5c"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b4"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5c" class="xiaoshou label1 color6668">Reporting Model 2 FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5d"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b5"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5d"
                      class="xiaoshou label1 color6668"
                    >Registered deemed-compliant FFI (other than a reporting Model 1 FFI, sponsored FFI or nonreporting IGA FFI covered in Part XVIII).</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5e"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b6"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5e"
                      class="xiaoshou label1 color6668"
                    >Territory financial institution. Complete Part V.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5f"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b7"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5f"
                      class="xiaoshou label1 color6668"
                    >Sponsored FFI (other than a certified deemed-compliant sponsored, closely held investment vehicle). Complete Part X.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5g"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b8"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5g"
                      class="xiaoshou label1 color6668"
                    >Certified deemed-compliant nonregistering local bank. Complete Part XII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5h"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b9"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5h"
                      class="xiaoshou label1 color6668"
                    >Certified deemed-compliant FFI with only low-value accounts. Complete Part XIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5i"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b10"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5i"
                      class="xiaoshou label1 color6668"
                    >Certified deemed-compliant sponsored, closely held investment vehicle. Complete Part XIV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5j"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b11"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XV')"
                    >
                    <label
                      for="paixu5j"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Certified deemed-compliant limited life debt investment entity. Complete Part XV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5k"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b12"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5k"
                      class="xiaoshou label1 color6668"
                    >Certain investment entities that do not maintain financial accounts. Complete Part XVII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5l"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b13"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5l"
                      class="xiaoshou label1 color6668"
                    >Owner-documented FFI. Complete Part XI.</label>
                  </div>
                </div>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5m"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b14"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5m"
                      class="xiaoshou label1 color6668"
                    >Restricted Distributor. Complete Part XVI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5n"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b15"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5n"
                      class="xiaoshou label1 color6668"
                    >Nonreporting IGA FFI. Complete Part XVIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5o"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b16"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XIX')"
                    >
                    <label
                      for="paixu5o"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Exempt retirement plans. Complete Part XIX.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5p"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b17"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XX')"
                    >
                    <label
                      for="paixu5p"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Excepted nonfinancial group entity. Complete Part XX.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5q"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b18"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXI')"
                    >
                    <label
                      for="paixu5q"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Excepted nonfinancial start-up company. Complete Part XXI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5r"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b19"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXII')"
                    >
                    <label
                      for="paixu5r"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Excepted nonfinancial entity in liquidation or bankruptcy. Complete Part XXII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5s"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b20"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXIII')"
                    >
                    <label
                      for="paixu5s"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Publicly traded NFFE or NFFE affiliate of a Publicly Traded Corporation. Complete Part XXIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5t"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b21"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5t"
                      class="xiaoshou label1 color6668"
                    >Excepted territory NFFE. Complete Part XXIV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5u"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b22"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXV')"
                    >
                    <label
                      for="paixu5u"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Active NFFE. Complete Part XXV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5v"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b23"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXVI')"
                    >
                    <label
                      for="paixu5v"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Passive NFFE. Complete Part XXVI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5w"
                      v-model="limitedLiablityB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="bb24"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5w"
                      class="xiaoshou label1 color6668"
                    >Sponsored direct reporting NFFE. Complete Part XXVII.</label>
                  </div>
                </div>
              </div>
              <!-- 6 -->
              <div class="itemRow color666">
                <p
                  class="lineheight18"
                >6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                <input
                  v-model="limitedLiablityB.part1.YongJiuAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="limitedLiablityB.part1.YongJiuAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="limitedLiablityB.part1.YongJiuAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 7 -->
              <div class="itemRow color666">
                <p class="lineheight18">7 Mailing address (if different from above)</p>
                <input
                  v-model="limitedLiablityB.part1.TongxunAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="limitedLiablityB.part1.TongxunAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="limitedLiablityB.part1.TongxunAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 8 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color666"
                >8 U.S. taxpayer identification number (TIN), if required</p>
                <input
                  v-model="limitedLiablityB.part1.payTaxesNumber.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(limitedLiablityB.part1.payTaxesNumber.number,'8')"
                  @focus="CHprocessingYB(limitedLiablityB.part1.payTaxesNumber.number,'8')"
                >
                <div v-show="limitedLiablityB.isZHshow8" class="CORred">请不要填写中文</div>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu8a"
                      v-model="limitedLiablityB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c1"
                      class="radio1"
                      name="radio8"
                    >
                    <label for="paixu8a" class="xiaoshou label1 color6668 NOdisabled">QI-EIN</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu8b"
                      v-model="limitedLiablityB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c2"
                      class="radio1"
                      name="radio8"
                      :disabled="true"
                    >
                    <label for="paixu8b" class="xiaoshou label1 color6668">WP-EIN</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu8c"
                      v-model="limitedLiablityB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c3"
                      class="radio1"
                      name="radio8"
                      :disabled="true"
                    >
                    <label for="paixu8c" class="xiaoshou label1 color6668">WT-EIN</label>
                  </div>
                </div>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu8d"
                      v-model="limitedLiablityB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c4"
                      class="radio1"
                      name="radio8"
                    >
                    <label for="paixu8d" class="xiaoshou label1 color6668 NOdisabled">EIN</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu8e"
                      v-model="limitedLiablityB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c5"
                      class="radio1"
                      name="radio8"
                    >
                    <label for="paixu8e" class="xiaoshou label1 color6668 NOdisabled">SSN or ITIN</label>
                  </div>
                </div>
              </div>
              <!-- 9 -->
              <div class="itemRow color666">
                <p class="lineheight18">9 GIIN (if applicable - "XXXXXX.XXXXX.XX.XXX")</p>
                <input
                  v-model="limitedLiablityB.part1.GIIN.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(limitedLiablityB.part1.GIIN.number,'9')"
                  @focus="CHprocessingYB(limitedLiablityB.part1.GIIN.number,'9')"
                >
              </div>
              <div v-show="limitedLiablityB.isZHshow9" class="CORred">请不要填写中文</div>
              <!-- 10 -->
              <div class="itemRow color666">
                <p class="lineheight18">10 Reference number(s) (see instructions)</p>
                <input
                  v-model="limitedLiablityB.part1.Reference.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(limitedLiablityB.part1.Reference.number,'10')"
                  @focus="CHprocessingYB(limitedLiablityB.part1.Reference.number,'10')"
                >
              </div>
            </div>
            <!-- Part II -->
            <div class="formNapeContent1">
              <div class="itemTitle" style="height:60px">
                <div class="left">Part II</div>
                <div class="right">
                  Disregarded Entity or Branch Receiving Payment. (Complete only if a disregarded
                  entity with a GIIN or a branch of an FFI in a country other than the FFI's country of residence.
                  See instructions.)
                </div>
              </div>
              <!-- 11 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color666"
                >11 Chapter 4 Status (FATCA status) of disregarded entity or branch receiving payment.</p>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu11a"
                      v-model="limitedLiablityB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d1"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11a"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Branch treated as nonparticipating FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu11b"
                      v-model="limitedLiablityB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d2"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11b"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Reporting Model 1 FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu11c"
                      v-model="limitedLiablityB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d3"
                      class="radio1"
                      name="radio11"
                    >
                    <label for="paixu11c" class="xiaoshou label1 color6668 NOdisabled">U.S. Branch.</label>
                  </div>
                </div>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu11d"
                      v-model="limitedLiablityB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d4"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11d"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Participating FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu11e"
                      v-model="limitedLiablityB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d5"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11e"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Reporting Model 2 FFI.</label>
                  </div>
                </div>
                <div
                  v-show="limitedLiablityB.isshow12"
                  class="CORred"
                  style="margin-top:106px;"
                >最少选择一个</div>
              </div>
              <!-- 12 -->
              <div class="itemRow color666">
                <p
                  class="lineheight18"
                >12 Address of branch (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                <input
                  v-model="limitedLiablityB.part2.filialeAddress.chengshi"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(limitedLiablityB.part2.filialeAddress.chengshi,'12a')"
                  @focus="CHprocessingYB(limitedLiablityB.part2.filialeAddress.chengshi,'12a')"
                >
                <div v-show="limitedLiablityB.isZHshow12a" class="CORred">请不要填写中文</div>
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="limitedLiablityB.part2.filialeAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    class="inputwt8 inputwt8Huifu"
                    @blur="CHprocessingYB(limitedLiablityB.part2.filialeAddress.shegnshi,'12b')"
                    @focus="CHprocessingYB(limitedLiablityB.part2.filialeAddress.shegnshi,'12b')"
                  >
                </div>
                <div v-show="limitedLiablityB.isZHshow12b" class="CORred">请不要填写中文</div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="limitedLiablityB.part2.filialeAddress.state"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 13 -->
              <div class="itemRow color666">
                <p class="lineheight18">13 GIIN (if any)</p>
                <input
                  v-model="limitedLiablityB.part2.GIIN.GIINvalue"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(limitedLiablityB.part2.GIIN.GIINvalue,'13')"
                  @focus="CHprocessingYB(limitedLiablityB.part2.GIIN.GIINvalue,'13')"
                >
              </div>
              <div v-show="limitedLiablityB.isZHshow13" class="CORred">请不要填写中文</div>
            </div>
            <!-- Part VIII -->
            <div class="formNapeContent1">
              <!-- style="height:34px" -->
              <div class="itemTitle">
                <div class="left">Part VIII</div>
                <div
                  class="right"
                >Nonwithholding Foreign Partnership, Simple Trust, or Grantor Trust</div>
              </div>
              <!-- 14 -->
              <!-- style="height:146px" -->
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixu14a"
                    v-model="limitedLiablityB.PartVIII.shiti"
                    maxlength="30"
                    type="checkbox"
                    value="e1"
                    class="checkbox1"
                    name="checkbox14"
                  >
                  <label for="paixu14a" class="xiaoshou label2 color666">
                    <strong class="strong">21a</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul>
                    <li class="left56 top10 lineheight18 color6668">
                      Is a nonwithholding foreign partnership, a nonwithholding foreign simple trust, or a nonwithholding
                      foreign grantor trust and is providing this form for payments that are not effectively connected,
                      or are not treated as effectively connected, with the conduct of a trade or business in the United
                      States; and
                    </li>
                    <li
                      class="left56 top10 lineheight18 color6668"
                    >Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required for purposes of chapters 3 and 4, that is subject to the certifications made on this form.</li>
                  </ul>
                </div>
                <div class="width100 rowTwo1 left57" style="margin-top:20px;width:90%;">
                  <input
                    id="paixu14b"
                    v-model="limitedLiablityB.PartVIII.shiti"
                    maxlength="30"
                    type="checkbox"
                    value="e2"
                    class="checkbox1"
                    name="checkbox14"
                  >
                  <label for="paixu14b" class="xiaoshou label2 color666">
                    <strong class="strong">21b</strong> I certify that the entity identified in Part I is a foreign partnership that is a partner in a lower-tier partnership and is providing this Form W-8IMY for purposes of section 1446.
                  </label>
                </div>
              </div>
              <div v-show="limitedLiablityB.isshow13" class="CORred">最少选择一个</div>
            </div>
            <!--根据条件 渲染部分 -->
            <!-- 渲染--Part XV -->
            <div v-show="limitedLiablityB.isshow=='XV'" class="formNapeContent1">
              <!-- style="height:34px" -->
              <div class="itemTitle">
                <div class="left">Part XV</div>
                <div class="right">Certified Deemed-Compliant Limited Life Debt Investment Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXVa"
                    v-model="limitedLiablityB.Part5isList.PartXV.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1"
                    class="checkbox1"
                    name="checkboxXV"
                  >
                  <label for="paixuXVa" class="xiaoshou label2 color666">
                    <strong class="strong">28</strong> I certify that the FFI identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Was in existence as of January 17, 2013;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Issued all classes of its debt or equity interests to investors on or before January 17, 2013 pursuant to a trust indenture or similar agreement; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under Regulations section 1.1471- 5(f)(2)(iv)).</li>
                  </ul>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow28" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XIX. -->
            <div v-show="limitedLiablityB.isshow=='XIX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XIX</div>
                <div class="right">Exempt Retirement Plans</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <p class="color666">Check box 32a, b, c, d, e, or f, whichever applies.</p>
                  <div class="top20">
                    <input
                      id="paixuXIXa"
                      v-model="limitedLiablityB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j1"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXa" class="xiaoshou label2 color666">
                      <strong class="strong">32a</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is established in a country with which the United States has an income tax treaty in force;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is operated principally to administer or provide pension or retirement benefits; and</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is entitled to treaty benefits on income that the fund derives from U.S. sources (or would be entitled to benefits if it derived any such income) as a resident of the other country which satisfies any applicable limitation on benefits requirement.</li>
                    </ul>
                  </div>
                  <div class="top20">
                    <input
                      id="paixuXIXb"
                      v-model="limitedLiablityB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j2"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXb" class="xiaoshou label2 color666">
                      <strong class="strong">32b</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former employees of one or more employers in consideration for services rendered;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >No single beneficiary has a right to more than 5% of the FFI's assets;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operated; and</li>
                    </ul>
                  </div>
                  <div class="top20">
                    <input
                      id="paixuXIXc"
                      v-model="limitedLiablityB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j3"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXc" class="xiaoshou label2 color666">
                      <strong class="strong">32c</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li class="left56 huifu top10 lineheight18 color6668">
                        Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to
                        beneficiaries that are former employees of one or more employers in consideration for services rendered;
                      </li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Has fewer than 50 participants;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is sponsored by one or more employers, each of which is not an investment entity or passive NFFE;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Employee and employer contributions to the fund (disregarding transfers of assets from other plans described in this part, retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A)) are limited by reference to earned income and compensation of the employee, respectively;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Participants that are not residents of the country in which the fund is established or operated are not entitled to more than 20 percent of the fund's assets; and</li>
                      <li class="left56 huifu top10 lineheight18 color6668">
                        Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operates.
                        <ul class="left22 rowTwo3 top6">
                          <li>1. Is generally exempt from tax on investment income under the laws of the country in which it is established or operates due to its status as a retirement or pension plan;</li>
                          <li>2. Receives at least 50% of its total contributions from sponsoring employers (disregarding transfers of assets from other plans described in this part, retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, other retirement funds described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A));</li>
                          <li>3. Either does not permit or penalizes distributions or withdrawals made before the occurrence of specified events related to retirement, disability, or death (except rollover distributions to accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts), to retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or to other retirement funds described in this part or in an applicable Model 1 or Model 2 IGA); or</li>
                          <li>4. Limits contributions by employees to the fund by reference to earned income of the employee or may not exceed $50,000 annually.</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="top20 rowTwo1 left57">
                    <input
                      id="paixuXIXd"
                      v-model="limitedLiablityB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j4"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXd" class="xiaoshou label2 color666">
                      <strong class="strong">32d</strong> I certify that the entity identified in Part I is formed pursuant to a pension plan that would meet the requirements of section 401(a), other than the requirement that the plan be funded by a trust created or organized in the United States.
                    </label>
                  </div>
                  <div class="top20 rowTwo1 left57">
                    <input
                      id="paixuXIXe"
                      v-model="limitedLiablityB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j5"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXe" class="xiaoshou label2 color666">
                      <strong class="strong">32e</strong> I certify that the entity identified in Part I is established exclusively to earn income for the benefit of one or more retirement funds described in this part or in an applicable Model 1 or Model 2 IGA, accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts), or retirement and pension accounts described in an applicable Model 1 or Model 2 IGA.
                    </label>
                  </div>
                  <div class="top20">
                    <input
                      id="paixuXIXf"
                      v-model="limitedLiablityB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j6"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXf" class="xiaoshou label2 color666">
                      <strong class="strong">32f</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are current or former employees of the sponsor (or persons designated by such employees); or</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are not current or former employees of such sponsor, but are in consideration of personal services performed for the sponsor.</li>
                    </ul>
                  </div>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow32" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XX -->
            <div v-show="limitedLiablityB.isshow=='XX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XX</div>
                <div class="right">Excepted Nonfinancial Group Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXX"
                    v-model="limitedLiablityB.Part5isList.PartXX.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="k1"
                    class="checkbox1"
                    name="checkboxXX"
                  >
                  <label for="paixuXX" class="xiaoshou label2 color666">
                    <strong class="strong">33</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a holding company, treasury center, or captive finance company and substantially all of the entity's activities are functions described in Regulations section 1.1471-5(e)(5)(i)(C) through (E);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a member of a nonfinancial group described in Regulations section 1.1471-5(e)(5)(i)(B);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund or any investment vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow33" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXI -->
            <div v-show="limitedLiablityB.isshow=='XXI'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXI</div>
                <div class="right">Excepted Nonfinancial Start-Up Company</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXXI"
                    v-model="limitedLiablityB.Part5isList.PartXXI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="l1"
                    class="checkbox1"
                    name="checkboxXXI"
                  >
                  <label for="paixuXXI" class="xiaoshou label2 color666">
                    <strong class="strong">34</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Was formed on (or in the case of a new line of business, the date of board resolution approving the new line of business)
                      <input
                        v-model="limitedLiablityB.Part5isList.PartXXI.input1"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 top6"
                      >
                      <!-- <div v-show="limitedLiablityB.isDataShow" class="CORred">日期不得超过申请日期之前2年</div>
                      <div v-show="limitedLiablityB.isDataShow2" class="CORred">请输入</div>-->
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business other than that of a financial institution or passive NFFE; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow34" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXII -->
            <div v-show="limitedLiablityB.isshow=='XXII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXII</div>
                <div class="right">Excepted Nonfinancial Entity in Liquidation or Bankruptcy</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXXII"
                    v-model="limitedLiablityB.Part5isList.PartXXII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="m1"
                    class="checkbox1"
                    name="checkboxXXII"
                  >
                  <label for="paixuXXII" class="xiaoshou label2 color666">
                    <strong class="strong">35</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Filed a plan of liquidation, filed a plan or reorganization, or filed for bankruptcy on the following date:
                      <input
                        v-model="limitedLiablityB.Part5isList.PartXXII.input1"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 top6"
                      >
                      <!-- <div v-show="limitedLiablityB.isDataShow1" class="CORred">日期不得超过申请日期之前3年</div>
                      <div v-show="limitedLiablityB.isDataShow2" class="CORred">请输入</div>-->
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Has not been engaged during the past 5 years in business as a financial institution or acted as a passive NFFE;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Has provided, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than three years.</li>
                  </ul>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow35" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXIII -->
            <div v-show="limitedLiablityB.isshow=='XXIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXIII</div>
                <div
                  class="right"
                >Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <p class="color666">Check box 36a or 36b, whichever applies.</p>
                  <input
                    id="paixuXXIIIa"
                    v-model="limitedLiablityB.Part5isList.PartXXIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="n1"
                    class="checkbox1"
                    name="checkboxXXIIIa"
                  >
                  <label for="paixuXXIIIa" class="xiaoshou label2 color666">
                    <strong class="strong">36a</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign corporation that is not a financial institution; and</li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The stock of such corporation is regularly traded on one or more established securities markets, including
                      <input
                        v-model="limitedLiablityB.Part5isList.PartXXIII.input1"
                        maxlength="30"
                        type="text"
                        placeholder="请选择"
                        class="inputwt8 top6"
                      >
                    </li>
                  </ul>
                  <div class="top10">
                    <input
                      id="paixuXXIIIb"
                      v-model="limitedLiablityB.Part5isList.PartXXIII.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="n2"
                      class="checkbox1"
                      name="checkboxXXIIIb"
                    >
                    <label for="paixuXXIIIb" class="xiaoshou label2 color666">
                      <strong class="strong">36b</strong> I certify that:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >The entity identified in Part I is a foreign corporation that is not a financial institution;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established securities market;</li>
                      <li class="left56 huifu top10 lineheight18 color6668">
                        The name of the entity, the stock of which is regularly traded on an established securities market, is
                        <input
                          v-model="limitedLiablityB.Part5isList.PartXXIII.input2"
                          maxlength="30"
                          type="text"
                          placeholder="请选择"
                          class="inputwt8 top6"
                        >
                        ; and
                      </li>
                      <li class="left56 huifu top10 lineheight18 color6668">
                        The name of the securities market on which the stock is regularly traded is
                        <input
                          v-model="limitedLiablityB.Part5isList.PartXXIII.input3"
                          maxlength="30"
                          type="text"
                          placeholder="请选择"
                          class="inputwt8 top6"
                        >
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow36" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXV -->
            <div v-show="limitedLiablityB.isshow=='XXV'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXV</div>
                <div class="right">Active NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <!-- <p class="color666">Check box 36a or 36b, whichever applies.</p> -->
                  <input
                    id="paixuXXV"
                    v-model="limitedLiablityB.Part5isList.PartXXV.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="o1"
                    class="checkbox1"
                    name="checkboxXXVa"
                  >
                  <label for="paixuXXV" class="xiaoshou label2 color666">
                    <strong class="strong">38</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign entity that is not a financial institution;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of such entity's gross income for the preceding calendar year is passive income; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of passive assets measured quarterly). See the instructions for the definition of passive income.</li>
                  </ul>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow38" class="CORred">最少选择一个</div>
            </div>
            <!-- 渲染--Part XXVI -->
            <div v-show="limitedLiablityB.isshow=='XXVI'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVI</div>
                <div class="right">Passive NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <!-- <p class="color666">Check box 36a or 36b, whichever applies.</p> -->
                  <input
                    id="paixuXXVIa"
                    v-model="limitedLiablityB.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="p1"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIa" class="xiaoshou label2 color666">
                    <strong class="strong">39</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a foreign entity that is not a financial institution (this category includes an entity organized in a possession of the United States that engages (or holds itself out as being engaged) primarily in the business of investing, reinvesting, or trading in securities, partnership interests, commodities, notional principal contracts, insurance or annuity contracts, or any interest in such security, partnership interest, commodity, notional principal contract, insurance contract or annuity contract); and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required.</li>
                  </ul>
                </div>
              </div>
              <div v-show="limitedLiablityB.isDataShow39" class="CORred">最少选择一个</div>
            </div>
            <!--Part XXVIII -->
            <div class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVIII</div>
                <div class="right">Certification</div>
              </div>
              <!-- 14 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color6668"
                >Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income for which I am providing this form or any withholding agent that can disburse or make payments of the amounts for which I am providing this form.</p>
                <p
                  class="top20 color666"
                >I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</p>
                <h4 class="top30a">签名</h4>
                <p
                  class="top16 lineheight24 color666"
                >本人认可，前述信息及账户申请流程中提供的所有其它信息均真实、准确，如该等信息发生重大变化，本人同意通过电子邮件通知国瑞证券。本人授权国瑞证券在必要时确认信息的准确性。</p>
                <ul class="account top20">
                  <li class="color666">账户名称:</li>
                  <li
                    style="margin-left:20px"
                    class="color6668"
                  >{{ limitedLiablityB.signature.accountName }}</li>
                </ul>
                <ul class="account top12">
                  <li class="color666">日期:</li>
                  <li
                    style="margin-left:48px"
                    class="color6668"
                  >{{ limitedLiablityB.signature.date }}</li>
                </ul>
                <p class="top30a color666">{{ limitedLiablityB.signature.accountName1 }}</p>
                <div class="top10">
                  <span class="color666">签名</span>
                  <input
                    v-model="limitedLiablityB.signature.UserSignature"
                    maxlength="30"
                    type="text"
                    class="inputwt8 inputwt8Huifu left22"
                    style="width:500px;"
                  >
                </div>
              </div>
              <div class="statement rowTwo2 left25P">
                <input
                  id="paixu14a1"
                  v-model="limitedLiablityB.signature.tongyi"
                  maxlength="30"
                  type="checkbox"
                  value="true"
                  class="checkbox1"
                  name="checkbox1"
                  checked
                >
                <label
                  for="paixu14a1"
                  class="xiaoshou label2 color666"
                >勾选此框即代表您同意所有税务表格均通过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。您也确认该表格中包含的信息以及有关您账户的其他财务信息可能会根据法律要求被报告至账户所在国家的税务部门及/或全球其他税务机构。</label>
              </div>
              <div v-show="limitedLiablityB.isTongyi" class="CORred">请勾选</div>
            </div>
          </div>
        </el-form>
      </div>
    </div>
    <!-- 合伙企业 -->
    <div v-else-if="userInfo.accountType==='partnerShip'" class="wt8ContentY">
      <div v-if="Wt8Type === 'partnerShipA'">
        <el-form
          ref="partnerShipA"
          status-icon
          :model="partnerShipA"
          label-width="0px"
          class="demo-ruleForm"
        >
          <div class="formNape1">
            <div class="formNapeTitle1">
              <p
                class="titleP"
              >Certificate of Status of Beneficial Owner for United States Tax Withholding and Reporting (Entities)</p>
              <h3 class="color666">
                <span>替代</span> W-8BEN-E
              </h3>
              <p class="suiwuGL color666">美国财政部国家税务管理局</p>
              <div class="describe color6668">
                <p>▸ For use by entities. Individuals must use Form W-8BEN.</p>
                <p>▸ Section references are to the Internal Revenue Code.</p>
                <p>
                  ▸ Information about Form W-8BEN-E and its separate instructions is at
                  <a href="#">www.irs.gov/formw8bene.</a>
                </p>
                <p>▸ Give this form to the withholding agent or payer. Do not send to the IRS.</p>
              </div>
              <p class="rowTitel1 color666">Do NOT use this form for:</p>
              <ul>
                <li class="huifu color6668">
                  U.S. entity or U.S. citizen or resident —
                  <i>Instead, use Form: W-9</i>
                </li>
                <li class="huifu color6668">
                  A foreign individual —
                  <i>Instead, use Form: W-8BEN (Individual) or Form 8233</i>
                </li>
                <li class="huifu color6668">
                  A foreign individual or entity claiming that income is effectively connected with the conduct of trade or business within the U.S. (unless claiming treaty benefits) —
                  <i>Instead, use Form: W-8ECI</i>
                </li>
                <li class="huifu color6668">
                  A foreign partnership, a foreign simple trust, or a foreign grantor trust (unless claiming treaty benefits) (see instructions for exceptions) —
                  <i>Instead, use Form: W- 8IMY</i>
                </li>
                <li class="huifu color6668">
                  A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private foundation, or government of a U.S. possession claiming that income is effectively connected U.S. income or that is claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) (unless claiming treaty benefits) (see instructions for other exceptions) —
                  <i>Instead, use Form: W-8ECI or W-8EXP</i>
                </li>
                <li class="huifu color6668">
                  Any person acting as an intermediary —
                  <i>Instead, use Form: W-8IMY</i>
                </li>
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
                <input
                  v-model="partnerShipA.part1.companyName"
                  maxlength="30"
                  type="text"
                  placeholder="公司名"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 2 -->
              <div class="itemRow color666">
                <p>2 Country of incorporation or organization</p>
                <el-form-item>
                  <el-select v-model="partnerShipA.part1.state" :disabled="true" placeholder="请选择">
                    <dictionary dict-code="country"></dictionary>
                  </el-select>
                </el-form-item>
              </div>
              <!-- 3 -->
              <div class="itemRow color666">
                <p>3 Name of disregarded entity receiving the payment (if applicable, see instructions)</p>
                <input
                  v-model="partnerShipA.part1.shitiName"
                  maxlength="30"
                  type="text"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 4 -->
              <div class="itemRow" style="overflow: hidden;">
                <p class="color666">4 Chapter 3 Status (entity type) (Must check one box only.):</p>
                <div class="width33">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4a"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a1"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4a" class="xiaoshou label1 color6668">股份有限公司</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4b"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a2"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4b" class="xiaoshou label1 color6668">无视实体</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4c"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a3"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4c" class="xiaoshou label1 color6668">合伙企业</label>
                  </div>
                </div>
                <div class="width33">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4d"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a4"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4d" class="xiaoshou label1 color6668">简单信托</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4e"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a5"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4e" class="xiaoshou label1 color6668">授予人信托</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4f"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a6"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4f" class="xiaoshou label1 color6668">复合信托</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4g"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a7"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4g" class="xiaoshou label1 color6668">财产</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4h"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a8"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4h" class="xiaoshou label1 color6668">政府</label>
                  </div>
                </div>
                <div class="width33">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4i"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a9"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4i" class="xiaoshou label1 color6668">中央发行银行</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4j"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a10"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4j" class="xiaoshou label1 color6668">免税组织</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4k"
                      v-model="partnerShipA.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a11"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label for="paixu4k" class="xiaoshou label1 color6668">私人基金</label>
                  </div>
                </div>
                <div style="margin-top: 154px; " class="color666">
                  <p class="lineheight18">
                    If you entered disregarded entity, partnership, simple trust, or grantor trust above, is the entity a
                    hybrid making a treaty claim? If "Yes" complete Part III.
                  </p>
                  <div class="rowTwo left22" style="float: left;">
                    <input
                      id="paixu4l"
                      v-model="partnerShipA.part1.radio1b"
                      maxlength="30"
                      type="radio"
                      value="1"
                      class="radio1"
                      name="radio1b"
                    >
                    <label for="paixu4l" class="xiaoshou label1 NOdisabled color6668">Yes</label>
                  </div>
                  <div class="rowTwo left22" style="float: left;">
                    <input
                      id="paixu4m"
                      v-model="partnerShipA.part1.radio1b"
                      maxlength="30"
                      type="radio"
                      value="0"
                      class="radio1"
                      name="radio1b"
                    >
                    <label for="paixu4m" class="xiaoshou label1 NOdisabled color6668">No</label>
                  </div>
                </div>
              </div>
              <!-- 5 -->
              <div class="itemRow" style="overflow: hidden;">
                <p class="lineheight18 color666">
                  5 Chapter 4 Status (FATCA status) (See instructions for details and
                  complete the certification below for the entity's applicable status).
                </p>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5a"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b1"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5a"
                      class="xiaoshou label1 color6668"
                    >未签署协议的外国金融机构【包括受限制的外国金融机构或与在政府间协议下应申报的外国金融机构（而非注册视同合规外国金融机构或签署协议的外国金融机构）相关联的外国金融机构】。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5b"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b2"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5b" class="xiaoshou label1 color6668">签署协议的外国金融机构。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5c"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b3"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5c" class="xiaoshou label1 color6668">在模式1下应申报的外国金融机构。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5d"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b4"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5d" class="xiaoshou label1 color6668">在模式2下应申报的外国金融机构。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5e"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b5"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5e"
                      class="xiaoshou label1 color6668"
                    >注册视同合规的外国金融机构（排除在模式1下应申报的外国金融机构、受赞助外国金融机构或第十二部分中提到的在政府间协议下无需申报的外国金融机构）。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5f"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b6"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5f" class="xiaoshou label1 color6668">受赞助外国金融机构。请完成第四部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5g"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b7"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5g" class="xiaoshou label1 color6668">声明视同合规的非注册本地银行。请完成第五部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5h"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b8"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5h"
                      class="xiaoshou label1 color6668"
                    >声明视同合规的仅具有低价值账户的外国金融机构。请完成第六部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5i"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b9"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5i"
                      class="xiaoshou label1 color6668"
                    >声明视同合规的受赞助且被封闭式持有之投资工具。请完成第七部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5j"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b10"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='VIII'"
                    >
                    <label
                      for="paixu5j"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >声明视同合规的有限寿命债权投资实体。请完成第八部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5k"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b11"
                      class="radio1"
                      name="radio2"
                    >
                    <label for="paixu5k" class="xiaoshou label1 color6668">某些无金融账户的投资实体。请完成第九部分。</label>
                  </div>
                </div>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5l"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b12"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5l"
                      class="xiaoshou label1 color6668"
                    >在政府间协议下无需申报的外国金融机构。请完成第十二部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5m"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b13"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5m" class="xiaoshou label1 color6668">免税退休计划。请完成第十五部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5n"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b14"
                      class="radio1"
                      :disabled="true"
                      name="radio2"
                    >
                    <label for="paixu5n" class="xiaoshou label1 color6668">美国属地金融机构。请完成第十七部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5o"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b15"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XVIII'"
                    >
                    <label
                      for="paixu5o"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >免受扣缴的非金融集团实体。请完成第十八部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5p"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b16"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XIX'"
                    >
                    <label
                      for="paixu5p"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >免受扣缴的非金融新创公司。请完成第十九部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5q"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b17"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XX'"
                    >
                    <label
                      for="paixu5q"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >免受扣缴的处于清算或破产状态的非金融实体。请完成第二十部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5r"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b18"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XXIII'"
                    >
                    <label
                      for="paixu5r"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >公开交易的非金融外国实体或公开交易之公司的附属非金融外国实体。请完成第二十三部分，</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5s"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b19"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5s"
                      class="xiaoshou label1 color6668"
                    >免受扣缴的美国属地非金融外国实体。完成第二十四部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5t"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b20"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XXV'"
                    >
                    <label
                      for="paixu5t"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >积极型非金融外国实体。请完成第二十五部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5u"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b21"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XXVI'"
                    >
                    <label
                      for="paixu5u"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >消极型非金融外国实体。请完成第二十六部分。</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5v"
                      v-model="partnerShipA.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b22"
                      class="radio1"
                      name="radio2"
                      @click="partnerShipA.isXuanRshow='XXVIII'"
                    >
                    <label
                      for="paixu5v"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >受赞助的直接申报非金融外国实体。请完成第二十八部分。</label>
                  </div>
                </div>
              </div>
              <div v-show="partnerShipA.isTwoFive" class="CORred">请勾选</div>
              <!-- 6 -->
              <div class="itemRow color666">
                <p
                  class="lineheight18"
                >6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                <input
                  v-model="partnerShipA.part1.YongJiuAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="partnerShipA.part1.YongJiuAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="partnerShipA.part1.YongJiuAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 7 -->
              <div class="itemRow color666">
                <p class="lineheight18">7 Mailing address (if different from above)</p>
                <input
                  v-model="partnerShipA.part1.TongxunAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="partnerShipA.part1.TongxunAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="partnerShipA.part1.TongxunAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 8 -->
              <div class="itemRow" style="height: 66px;">
                <p
                  class="lineheight18 color666"
                >8 U.S. taxpayer identification number (TIN), if required</p>
                <input
                  v-model="partnerShipA.part1.payTaxesNumber.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(partnerShipA.part1.payTaxesNumber.number,'8')"
                  @focus="CHprocessingYA(partnerShipA.part1.payTaxesNumber.number,'8')"
                >
              </div>
              <div v-show="partnerShipA.isZHshow8" class="CORred">请不要填写中文</div>
              <!-- 9a -->
              <div class="itemRow color666">
                <p class="lineheight18">9a GIIN</p>
                <input
                  v-model="partnerShipA.part1.GIIN.numberA"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(partnerShipA.part1.GIIN.numberA,'9a')"
                  @focus="CHprocessingYA(partnerShipA.part1.GIIN.numberA,'9a')"
                >
              </div>
              <div v-show="partnerShipA.isZHshow9a" class="CORred">请不要填写中文</div>
              <!-- 9b -->
              <div class="itemRow color666">
                <p class="lineheight18">9b Foreign TIN</p>
                <input
                  v-model="partnerShipA.part1.GIIN.numberB"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(partnerShipA.part1.GIIN.numberB,'9b')"
                  @focus="CHprocessingYA(partnerShipA.part1.GIIN.numberB,'9b')"
                >
              </div>
              <div v-show="partnerShipA.isZHshow9b" class="CORred">请不要填写中文</div>
              <!-- 10 -->
              <div class="itemRow color666">
                <p class="lineheight18">10 Reference number(s) (see instructions)</p>
                <input
                  v-model="partnerShipA.part1.Reference.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYA(partnerShipA.part1.Reference.number,'10')"
                  @focus="CHprocessingYA(partnerShipA.part1.Reference.number,'10')"
                >
              </div>
              <div v-show="partnerShipA.isZHshow10" class="CORred">请不要填写中文</div>
            </div>
            <!-- Part III -->
            <div class="formNapeContent1">
              <div class="itemTitle">
                <div class="left">Part III</div>
                <div
                  class="right"
                >Claim of Tax Treaty Benefits (if applicable). (For chapter 3 purposes only.)</div>
              </div>
              <!-- 13 -->
              <div class="itemRow color666" style="margin-bottom: 85px;">
                <div
                  style="float: left;margin-right:150px;line-height:18px"
                >14 受益人是否有资格享受与美国达成之税务协定的优惠？</div>
                <div class="rowTwo left22" style="float: left;">
                  <input
                    id="paixu14a"
                    v-model="partnerShipA.PartIII.radio14"
                    maxlength="30"
                    type="radio"
                    value="c1"
                    class="radio1"
                    name="radio14"
                  >
                  <label for="paixu14a" class="xiaoshou label1 color6668">Yes</label>
                </div>
                <div class="rowTwo left22" style="float: left;">
                  <input
                    id="paixu14b"
                    v-model="partnerShipA.PartIII.radio14"
                    maxlength="30"
                    type="radio"
                    value="c2"
                    class="radio1"
                    name="radio14"
                  >
                  <label for="paixu14b" class="xiaoshou label1 color6668">No</label>
                </div>
              </div>
            </div>
            <!--根据条件 渲染部分 -->
            <!-- 渲染--Part VIII -->
            <div v-show="partnerShipA.isXuanRshow=='VIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part VIII</div>
                <div class="right">Certified Deemed-Compliant Limited Life Debt Investment Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuVIII"
                    v-model="partnerShipA.Part5isList.PartVIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="XRf1"
                    class="checkbox1"
                    name="checkboxVIII"
                  >
                  <label for="paixuVIII" class="xiaoshou label2 color666">
                    <strong class="strong">22</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="paddingLeft0">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Was in existence as of January 17, 2013;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Issued all classes of its debt or equity interests to investors on or before January 17, 2013, pursuant to a trust indenture or similar agreement; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under § 1.1471-5(f)(2)(iv)).</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XVIII -->
            <div v-show="partnerShipA.isXuanRshow=='XVIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XVIII</div>
                <div class="right">Excepted Nonfinancial Group Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXVIII"
                    v-model="partnerShipA.Part5isList.PartXVIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="XRf2"
                    class="checkbox1"
                    name="checkboxXVIII"
                  >
                  <label for="paixuXVIII" class="xiaoshou label2 color666">
                    <strong class="strong">32</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a holding company, treasury center, or captive finance company and substantially all of the entity's activities are functions described in §1.1471-5(e)(5)(i)(C) through (E);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a member of a nonfinancial group described in §1.1471-5(e)(5)(i) (B);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XIX -->
            <div v-show="partnerShipA.isXuanRshow=='XIX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XIX</div>
                <div class="right">Excepted Nonfinancial Start-Up Company</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXIX"
                    v-model="partnerShipA.Part5isList.PartXIX.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fa"
                    class="checkbox1"
                    name="checkboxXIX"
                  >
                  <label for="paixuXIX" class="xiaoshou label2 color666">
                    <strong class="strong">33</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Was formed on (or, in the case of a new line of business, the date of board resolution approving the new line of business)
                      <input
                        v-model="partnerShipA.Part5isList.PartXIX.inputData"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p
                        class="color6668 top6"
                      >(date must be less than 24 months prior to date of payment);</p>
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business other than that of a financial institution or passive NFFE;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is investing capital into assets with the intent to operate a business other than that of a financial institution; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XX -->
            <div v-show="partnerShipA.isXuanRshow=='XX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XX</div>
                <div class="right">Excepted Nonfinancial Entity in Liquidation or Bankruptcy</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXX"
                    v-model="partnerShipA.Part5isList.PartXX.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1af"
                    class="checkbox1"
                    name="checkboxXX"
                  >
                  <label for="paixuXX" class="xiaoshou label2 color666">
                    <strong class="strong">34</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Filed a plan of liquidation, filed a plan of reorganization, or filed for bankruptcy on
                      <input
                        v-model="partnerShipA.Part5isList.PartXX.inputData"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p class="color6668 top6">;</p>
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >During the past 5 years has not been engaged in business as a financial institution or acted as a passive NFFE;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Has, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than three years.</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXIII -->
            <div v-show="partnerShipA.isXuanRshow=='XXIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXIII</div>
                <div
                  class="right"
                >Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <p class="color666">Check box 37a or 37b, whichever applies.</p>
                  <input
                    id="paixuXXIIIa"
                    v-model="partnerShipA.Part5isList.PartXXIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fdsaa"
                    class="checkbox1"
                    name="checkboxXXIII"
                  >
                  <label for="paixuXXIIIa" class="xiaoshou label2 color666">
                    <strong class="strong">37a</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign corporation that is not a financial institution; and</li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The stock of such corporation is regularly traded on one or more established securities markets, including
                      <input
                        v-model="partnerShipA.Part5isList.PartXXIII.input1"
                        maxlength="30"
                        type="text"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p
                        class="color6668 top6"
                      >(name one securities exchange upon which the stock is regularly traded).</p>
                    </li>
                  </ul>
                  <input
                    id="paixuXXIIIb"
                    v-model="partnerShipA.Part5isList.PartXXIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fdasf"
                    class="checkbox1"
                    name="checkboxXXIII"
                  >
                  <label for="paixuXXIIIb" class="xiaoshou label2 color666">
                    <strong class="strong">37b</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign corporation that is not a financial institution;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established securities market;</li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The name of the entity, the stock of which is regularly traded on an established securities market, is
                      <input
                        v-model="partnerShipA.Part5isList.PartXXIII.input2"
                        maxlength="30"
                        type="text"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                      <p class="color6668 top6">; and</p>
                    </li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The name of the securities market on which the stock is regularly traded is
                      <input
                        v-model="partnerShipA.Part5isList.PartXXIII.input3"
                        maxlength="30"
                        type="text"
                        class="inputwt8 inputwt8Huifu top6"
                      >
                    </li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXV -->
            <div v-show="partnerShipA.isXuanRshow=='XXV'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXV</div>
                <div class="right">Active NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXXV"
                    v-model="partnerShipA.Part5isList.PartXXV.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1dfass"
                    class="checkbox1"
                    name="checkboxXXV"
                  >
                  <label for="paixuXXV" class="xiaoshou label2 color666">
                    <strong class="strong">39</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign entity that is not a financial institution;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of such entity's gross income for the preceding calendar year is passive income; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of passive assets measured quarterly) (see instructions for the definition of passive income).</li>
                  </ul>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXVI -->
            <div v-show="partnerShipA.isXuanRshow=='XXVI'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVI</div>
                <div class="right">Active NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100 rowTwo2 left25P">
                  <input
                    id="paixuXXVIa"
                    v-model="partnerShipA.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1fas"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIa" class="xiaoshou label2 color666 xiaoshouPaixu">
                    <strong class="strong">40a</strong> I certify that the entity identified in Part I is a foreign entity that is not a financial institution (other than an investment entity organized in a possession of the United States) and is not certifying its status as a publicly traded NFFE (or affiliate), excepted territory NFFE, active NFFE, direct reporting NFFE, or sponsored direct reporting NFFE.
                  </label>
                </div>
                <p class="color666 top16">Check box 40b or 40c, whichever applies.</p>
                <div class="width100 rowTwo2 left25P">
                  <input
                    id="paixuXXVIb"
                    v-model="partnerShipA.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="fdd1"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIb" class="xiaoshou label2 color666">
                    <strong class="strong">40b</strong> I further certify that the entity identified in Part I has no substantial U.S. owners (or, if applicable, no controlling U.S. persons), or
                  </label>
                </div>
                <p class="color666 top16">Check box 40b or 40c, whichever applies.</p>
                <div class="width100 rowTwo2 left25P">
                  <input
                    id="paixuXXVIc"
                    v-model="partnerShipA.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="fsf1"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIc" class="xiaoshou label2 color666">
                    <strong class="strong">40c</strong> I further certify that the entity identified in Part I has provided the name, address, and TIN of each substantial U.S. owner (or, if applicable, controlling U.S. person) of the NFFE in Part XXIX.
                  </label>
                </div>
              </div>
            </div>
            <!-- 渲染--Part XXVIII -->
            <div v-show="partnerShipA.isXuanRshow=='XXVIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVIII</div>
                <div
                  class="right"
                >Sponsored Direct Reporting NFFE (see instructions for when this is permitted)</div>
              </div>
              <div class="itemRow">
                <p class="color666">42 Name of sponsoring entity:</p>
                <input
                  v-model="partnerShipA.Part5isList.PartXXVIII.input1"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                >
                <div class="width100 top10 rowTwo2 left25P">
                  <input
                    id="paixuXXVIII"
                    v-model="partnerShipA.Part5isList.PartXXVIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="ffaaf1"
                    class="checkbox1"
                    name="checkboxXXVIII"
                  >
                  <label for="paixuXXVIII" class="xiaoshou label2 color666 xiaoshouPaixu">
                    <strong class="strong">40a</strong> I certify that the entity identified in Part I is a foreign entity that is not a financial institution (other than an investment entity organized in a possession of the United States) and is not certifying its status as a publicly traded NFFE (or affiliate), excepted territory NFFE, active NFFE, direct reporting NFFE, or sponsored direct reporting NFFE.
                  </label>
                </div>
              </div>
            </div>
            <div v-show="partnerShipA.isshowXuanRTishi" class="CORred">最少选择一个</div>
            <!--Part XXX -->
            <div class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXX</div>
                <div class="right">Certification</div>
              </div>
              <!-- 14 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color6668"
                >Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. I further certify under penalties of perjury that:</p>
                <ul>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >The entity identified on line 1 of this form is the beneficial owner of all the income to which this form relates, is using this form to certify its status for chapter 4 purposes, or is a merchant submitting this form for purposes of section 6050W,</li>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >The entity identified on line 1 of this form is not a U.S. person,</li>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >The income to which this form relates is: (a) not effectively connected with the conduct of a trade or business in the United States, (b) effectively connected but is not subject to tax under an income tax treaty, or (c) the partner's share of a partnership's effectively connected income, and</li>
                  <li
                    class="left22 huifu top10 lineheight18 color6668"
                  >For broker transactions or barter exchanges, the beneficial owner is an exempt foreign person as defined in the instructions.</li>
                </ul>
                <p
                  class="lineheight18 color6668 top16"
                >Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income of which the entity on line 1 is the beneficial owner or any withholding agent that can disburse or make payments of the income of which the entity on line 1 is the beneficial owner.</p>
                <p
                  class="top20 color666"
                >I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</p>
                <h4 class="top30a">签名</h4>
                <p
                  class="top16 lineheight24 color666"
                >本人认可，前述信息及账户申请流程中提供的所有其它信息均真实、准确，如该等信息发生重大变化，本人同意通过电子邮件通知国瑞证券。本人授权国瑞证券在必要时确认信息的准确性。</p>
                <ul class="account top20">
                  <li class="color666">账户名称:</li>
                  <li
                    style="margin-left:20px"
                    class="color6668"
                  >{{ partnerShipA.signature.accountName }}</li>
                </ul>
                <ul class="account top12">
                  <li class="color666">日期:</li>
                  <li style="margin-left:48px" class="color6668">{{ partnerShipA.signature.date }}</li>
                </ul>
                <p class="top30a color666">{{ partnerShipA.signature.accountName1 }}</p>
                <div class="top10">
                  <span class="color666">签名</span>
                  <input
                    v-model="partnerShipA.signature.UserSignature"
                    maxlength="30"
                    type="text"
                    class="inputwt8 inputwt8Huifu left22"
                    style="width:500px;"
                  >
                </div>
              </div>
              <div class="statement rowTwo2 left25P" style="margin-top: 482px;">
                <input
                  id="paixu14a1"
                  v-model="partnerShipA.signature.tongyi"
                  maxlength="30"
                  type="checkbox"
                  value="true"
                  class="checkbox1"
                  name="checkbox1"
                  checked
                >
                <label
                  for="paixu14a1"
                  class="xiaoshou label2 color666"
                >勾选此框即代表您同意所有税务表格均通过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。您也确认该表格中包含的信息以及有关您账户的其他财务信息可能会根据法律要求被报告至账户所在国家的税务部门及/或全球其他税务机构。</label>
              </div>
              <div v-show="partnerShipA.isTongyi" class="CORred">请勾选</div>
            </div>
          </div>
        </el-form>
      </div>
      <div v-else-if="Wt8Type === 'partnerShipB'">
        <el-form
          ref="partnerShipB"
          status-icon
          :model="partnerShipB"
          label-width="0px"
          class="demo-ruleForm"
        >
          <div class="formNape1">
            <div class="formNapeTitle1">
              <p
                class="titleP"
              >Certificate of Foreign Intermediary, Foreign Flow-Through Entity, or Certain U.S. Branches for United States Tax Withholding and Reporting</p>
              <h3 class="color666">
                <span>替代</span> W-8IMY
              </h3>
              <p class="suiwuGL color666">美国财政部国家税务管理局</p>
              <div class="describe color6668">
                <p>▸ Section references are to the Internal Revenue Code.</p>
                <p>
                  ▸ Information about Form W-8IMY and its separate instructions is at
                  <a href="#">www.irs.gov/formw8imy.</a>
                </p>
                <p>▸ Give this form to the withholding agent or payer. Do not send to the IRS.</p>
              </div>
              <p class="rowTitel1 color666">Do not use this form for:</p>
              <ul>
                <li class="huifu color6668">
                  A beneficial owner solely claiming foreign status or treaty benefits —
                  <i>Instead, use Form: W-8BEN or W-8BEN-E</i>
                </li>
                <li class="huifu color6668">
                  A hybrid entity claiming treaty benefits on its own behalf —
                  <i>Instead, use Form: W-8BEN-E</i>
                </li>
                <li class="huifu color6668">
                  A foreign person claiming that income is effectively connected with the conduct of a trade or business in the United States —
                  <i>Instead, use Form: W-8ECI</i>
                </li>
                <li class="huifu color6668">
                  A disregarded entity with a single foreign owner that is the beneficial owner of the income to which this form relates. Instead, the single foreign owner should use —
                  <i>Instead, use Form: W-8BEN, W-8ECI, or W-8BEN-E</i>
                </li>
                <li class="huifu color6668">
                  A foreign government, international organization, foreign central bank of issue, foreign tax-exempt organization, foreign private foundation, or government of a U.S. possession claiming the applicability of section(s) 115(2), 501(c), 892, 895, or 1443(b) —
                  <i>Instead, use Form: W-8EXP</i>
                </li>
                <li class="huifu color6668">
                  U.S. entity or U.S. citizen or resident —
                  <i>Instead, use Form: W-9</i>
                </li>
                <li class="huifu color6668">
                  A foreign person documenting itself for purposes of section 6050W —
                  <i>Instead, use Form: W-8BEN, W-8BENE, or W-8ECI</i>
                </li>
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
                <input
                  v-model="partnerShipB.part1.companyName"
                  maxlength="30"
                  type="text"
                  placeholder="公司名"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 2 -->
              <div class="itemRow color666">
                <p>2 Country of incorporation or organization</p>
                <el-form-item>
                  <el-select v-model="partnerShipB.part1.state" :disabled="true" placeholder="请选择">
                    <dictionary dict-code="country"></dictionary>
                  </el-select>
                </el-form-item>
              </div>
              <!-- 3 -->
              <div class="itemRow color666">
                <p>3 Name of disregarded entity (if applicable), see instructions</p>
                <input
                  v-model="partnerShipB.part1.shitiName"
                  maxlength="30"
                  type="text"
                  :disabled="true"
                  class="inputwt8"
                >
              </div>
              <!-- 4 -->
              <div class="itemRow height152">
                <p class="color666">4 Chapter 3 Status (entity type) (Must check one box only.):</p>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4a"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a1"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4a"
                      class="xiaoshou label1 color6668"
                    >QI(including a QDD). Complete Part III.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4b"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a2"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4b"
                      class="xiaoshou label1 color6668"
                    >Nonqualified intermediary. Complete Part IV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4c"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a3"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4c"
                      class="xiaoshou label1 color6668"
                    >Territory financial institution. Complete Part V.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4d"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a4"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4d"
                      class="xiaoshou label1 color6668"
                    >U.S. branch. Complete Part VI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4e"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a5"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4e"
                      class="xiaoshou label1 color6668"
                    >Withholding foreign partnership. Complete Part VII.</label>
                  </div>
                </div>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu4f"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a6"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4f"
                      class="xiaoshou label1 color6668"
                    >Withholding foreign trust. Complete Part VII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4g"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a7"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4g"
                      class="xiaoshou label1 color6668"
                    >Nonwithholding foreign partnership. Complete Part VIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4h"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a8"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4h"
                      class="xiaoshou label1 color6668"
                    >Nonwithholding foreign simple trust. Complete Part VIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu4i"
                      v-model="partnerShipB.part1.radio1"
                      maxlength="30"
                      type="radio"
                      value="a9"
                      :disabled="true"
                      class="radio1"
                      name="radio1"
                    >
                    <label
                      for="paixu4i"
                      class="xiaoshou label1 color6668"
                    >Nonwithholding foreign grantor trust. Complete Part VIII.</label>
                  </div>
                </div>
              </div>
              <!-- 5 -->
              <div class="itemRow" style="height:640px">
                <p
                  class="lineheight18 color666"
                >5 Chapter 4 Status (FATCA status) (See instructions for details and complete the certification below for the entity's applicable status.) (Must check one box only):</p>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5a"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b1"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5a" class="xiaoshou label1 color6668">
                      Nonparticipating foreign financial institution(FFI)
                      (including an FFI related to a Reporting IGA FFI other than a deemed-compliant FFI, participating
                      FFI, or exempt beneficial owner). Complete Part IX (if applicable).
                    </label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5a"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b2"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5a" class="xiaoshou label1 color6668">Participating FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5b"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b3"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5b" class="xiaoshou label1 color6668">Reporting Model 1 FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5c"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b4"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label for="paixu5c" class="xiaoshou label1 color6668">Reporting Model 2 FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5d"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b5"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5d"
                      class="xiaoshou label1 color6668"
                    >Registered deemed-compliant FFI (other than a reporting Model 1 FFI, sponsored FFI or nonreporting IGA FFI covered in Part XVIII).</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5e"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b6"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5e"
                      class="xiaoshou label1 color6668"
                    >Territory financial institution. Complete Part V.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5f"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b7"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5f"
                      class="xiaoshou label1 color6668"
                    >Sponsored FFI (other than a certified deemed-compliant sponsored, closely held investment vehicle). Complete Part X.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5g"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b8"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5g"
                      class="xiaoshou label1 color6668"
                    >Certified deemed-compliant nonregistering local bank. Complete Part XII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5h"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b9"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5h"
                      class="xiaoshou label1 color6668"
                    >Certified deemed-compliant FFI with only low-value accounts. Complete Part XIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5i"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b10"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5i"
                      class="xiaoshou label1 color6668"
                    >Certified deemed-compliant sponsored, closely held investment vehicle. Complete Part XIV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5j"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b11"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XV')"
                    >
                    <label
                      for="paixu5j"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Certified deemed-compliant limited life debt investment entity. Complete Part XV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5k"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b12"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5k"
                      class="xiaoshou label1 color6668"
                    >Certain investment entities that do not maintain financial accounts. Complete Part XVII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5l"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b13"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5l"
                      class="xiaoshou label1 color6668"
                    >Owner-documented FFI. Complete Part XI.</label>
                  </div>
                </div>
                <div class="width50">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu5m"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b14"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5m"
                      class="xiaoshou label1 color6668"
                    >Restricted Distributor. Complete Part XVI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5n"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b15"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5n"
                      class="xiaoshou label1 color6668"
                    >Nonreporting IGA FFI. Complete Part XVIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5o"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b16"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XIX')"
                    >
                    <label
                      for="paixu5o"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Exempt retirement plans. Complete Part XIX.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5p"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b17"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XX')"
                    >
                    <label
                      for="paixu5p"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Excepted nonfinancial group entity. Complete Part XX.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5q"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b18"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXI')"
                    >
                    <label
                      for="paixu5q"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Excepted nonfinancial start-up company. Complete Part XXI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5r"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b19"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXII')"
                    >
                    <label
                      for="paixu5r"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Excepted nonfinancial entity in liquidation or bankruptcy. Complete Part XXII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5s"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b20"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXIII')"
                    >
                    <label
                      for="paixu5s"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Publicly traded NFFE or NFFE affiliate of a Publicly Traded Corporation. Complete Part XXIII.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5t"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b21"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5t"
                      class="xiaoshou label1 color6668"
                    >Excepted territory NFFE. Complete Part XXIV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5u"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b22"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXV')"
                    >
                    <label
                      for="paixu5u"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Active NFFE. Complete Part XXV.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5v"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="b23"
                      class="radio1"
                      name="radio2"
                      @click="ApplyColoursYB('XXVI')"
                    >
                    <label
                      for="paixu5v"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Passive NFFE. Complete Part XXVI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu5w"
                      v-model="partnerShipB.part1.radio2"
                      maxlength="30"
                      type="radio"
                      value="bb24"
                      class="radio1"
                      name="radio2"
                      :disabled="true"
                    >
                    <label
                      for="paixu5w"
                      class="xiaoshou label1 color6668"
                    >Sponsored direct reporting NFFE. Complete Part XXVII.</label>
                  </div>
                </div>
              </div>
              <!-- 6 -->
              <div class="itemRow color666">
                <p
                  class="lineheight18"
                >6 Permanent residence address (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                <input
                  v-model="partnerShipB.part1.YongJiuAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="partnerShipB.part1.YongJiuAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="partnerShipB.part1.YongJiuAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 7 -->
              <div class="itemRow color666">
                <p class="lineheight18">7 Mailing address (if different from above)</p>
                <input
                  v-model="partnerShipB.part1.TongxunAddress.chengshi"
                  maxlength="30"
                  type="text"
                  placeholder="街道"
                  :disabled="true"
                  class="inputwt8"
                >
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="partnerShipB.part1.TongxunAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    placeholder="城市,省市,邮政"
                    :disabled="true"
                    class="inputwt8"
                  >
                </div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select
                      v-model="partnerShipB.part1.TongxunAddress.state"
                      :disabled="true"
                      placeholder="请选择"
                    >
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 8 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color666"
                >8 U.S. taxpayer identification number (TIN), if required</p>
                <input
                  v-model="partnerShipB.part1.payTaxesNumber.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(partnerShipB.part1.payTaxesNumber.number,'8')"
                  @focus="CHprocessingYB(partnerShipB.part1.payTaxesNumber.number,'8')"
                >
                <div v-show="partnerShipB.isZHshow8" class="CORred">请不要填写中文</div>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu8a"
                      v-model="partnerShipB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c1"
                      class="radio1"
                      name="radio8"
                    >
                    <label for="paixu8a" class="xiaoshou label1 color6668 NOdisabled">QI-EIN</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu8b"
                      v-model="partnerShipB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c2"
                      class="radio1"
                      name="radio8"
                      :disabled="true"
                    >
                    <label for="paixu8b" class="xiaoshou label1 color6668">WP-EIN</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu8c"
                      v-model="partnerShipB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c3"
                      class="radio1"
                      name="radio8"
                      :disabled="true"
                    >
                    <label for="paixu8c" class="xiaoshou label1 color6668">WT-EIN</label>
                  </div>
                </div>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu8d"
                      v-model="partnerShipB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c4"
                      class="radio1"
                      name="radio8"
                    >
                    <label for="paixu8d" class="xiaoshou label1 color6668 NOdisabled">EIN</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu8e"
                      v-model="partnerShipB.part1.payTaxesNumber.xuankuang"
                      maxlength="30"
                      type="radio"
                      value="c5"
                      class="radio1"
                      name="radio8"
                    >
                    <label for="paixu8e" class="xiaoshou label1 color6668 NOdisabled">SSN or ITIN</label>
                  </div>
                </div>
              </div>
              <!-- 9 -->
              <div class="itemRow color666">
                <p class="lineheight18">9 GIIN (if applicable - "XXXXXX.XXXXX.XX.XXX")</p>
                <input
                  v-model="partnerShipB.part1.GIIN.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(partnerShipB.part1.GIIN.number,'9')"
                  @focus="CHprocessingYB(partnerShipB.part1.GIIN.number,'9')"
                >
              </div>
              <div v-show="partnerShipB.isZHshow9" class="CORred">请不要填写中文</div>
              <!-- 10 -->
              <div class="itemRow color666">
                <p class="lineheight18">10 Reference number(s) (see instructions)</p>
                <input
                  v-model="partnerShipB.part1.Reference.number"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(partnerShipB.part1.Reference.number,'10')"
                  @focus="CHprocessingYB(partnerShipB.part1.Reference.number,'10')"
                >
              </div>
            </div>
            <!-- Part II -->
            <div class="formNapeContent1">
              <div class="itemTitle" style="height:60px">
                <div class="left">Part II</div>
                <div class="right">
                  Disregarded Entity or Branch Receiving Payment. (Complete only if a disregarded
                  entity with a GIIN or a branch of an FFI in a country other than the FFI's country of residence.
                  See instructions.)
                </div>
              </div>
              <!-- 11 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color666"
                >11 Chapter 4 Status (FATCA status) of disregarded entity or branch receiving payment.</p>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu11a"
                      v-model="partnerShipB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d1"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11a"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Branch treated as nonparticipating FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu11b"
                      v-model="partnerShipB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d2"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11b"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Reporting Model 1 FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu11c"
                      v-model="partnerShipB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d3"
                      class="radio1"
                      name="radio11"
                    >
                    <label for="paixu11c" class="xiaoshou label1 color6668 NOdisabled">U.S. Branch.</label>
                  </div>
                </div>
                <div class="width50 top10">
                  <div class="width100 rowTwo left22">
                    <input
                      id="paixu11d"
                      v-model="partnerShipB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d4"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11d"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Participating FFI.</label>
                  </div>
                  <div class="width100 top10 rowTwo left22">
                    <input
                      id="paixu11e"
                      v-model="partnerShipB.part2.FATCAstatus"
                      maxlength="30"
                      type="radio"
                      value="d5"
                      class="radio1"
                      name="radio11"
                    >
                    <label
                      for="paixu11e"
                      class="xiaoshou label1 color6668 NOdisabled"
                    >Reporting Model 2 FFI.</label>
                  </div>
                </div>
                <div v-show="partnerShipB.isshow12" class="CORred" style="margin-top:106px;">最少选择一个</div>
              </div>
              <!-- 12 -->
              <div class="itemRow color666">
                <p
                  class="lineheight18"
                >12 Address of branch (street, apt. or suite no., or rural route). Do not use a P.O. box or in-care-of address (other than a registered address).</p>
                <input
                  v-model="partnerShipB.part2.filialeAddress.chengshi"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(partnerShipB.part2.filialeAddress.chengshi,'12a')"
                  @focus="CHprocessingYB(partnerShipB.part2.filialeAddress.chengshi,'12a')"
                >
                <div v-show="partnerShipB.isZHshow12a" class="CORred">请不要填写中文</div>
                <div class="top16">
                  <p>City or town, state or province. Include postal code where appropriate.</p>
                  <input
                    v-model="partnerShipB.part2.filialeAddress.shegnshi"
                    maxlength="30"
                    type="text"
                    class="inputwt8 inputwt8Huifu"
                    @blur="CHprocessingYB(partnerShipB.part2.filialeAddress.shegnshi,'12b')"
                    @focus="CHprocessingYB(partnerShipB.part2.filialeAddress.shegnshi,'12b')"
                  >
                </div>
                <div v-show="partnerShipB.isZHshow12b" class="CORred">请不要填写中文</div>
                <div class="top16">
                  <p>Country</p>
                  <el-form-item>
                    <el-select v-model="partnerShipB.part2.filialeAddress.state" placeholder="请选择">
                      <dictionary dict-code="country"></dictionary>
                    </el-select>
                  </el-form-item>
                </div>
              </div>
              <!-- 13 -->
              <div class="itemRow color666">
                <p class="lineheight18">13 GIIN (if any)</p>
                <input
                  v-model="partnerShipB.part2.GIIN.GIINvalue"
                  maxlength="30"
                  type="text"
                  class="inputwt8 inputwt8Huifu"
                  @blur="CHprocessingYB(partnerShipB.part2.GIIN.GIINvalue,'13')"
                  @focus="CHprocessingYB(partnerShipB.part2.GIIN.GIINvalue,'13')"
                >
              </div>
              <div v-show="partnerShipB.isZHshow13" class="CORred">请不要填写中文</div>
            </div>
            <!-- Part VIII -->
            <div class="formNapeContent1">
              <!-- style="height:34px" -->
              <div class="itemTitle">
                <div class="left">Part VIII</div>
                <div
                  class="right"
                >Nonwithholding Foreign Partnership, Simple Trust, or Grantor Trust</div>
              </div>
              <!-- 14 -->
              <!-- style="height:146px" -->
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixu14a"
                    v-model="partnerShipB.PartVIII.shiti"
                    maxlength="30"
                    type="checkbox"
                    value="e1"
                    class="checkbox1"
                    name="checkbox14"
                  >
                  <label for="paixu14a" class="xiaoshou label2 color666">
                    <strong class="strong">21a</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul>
                    <li class="left56 top10 lineheight18 color6668">
                      Is a nonwithholding foreign partnership, a nonwithholding foreign simple trust, or a nonwithholding
                      foreign grantor trust and is providing this form for payments that are not effectively connected,
                      or are not treated as effectively connected, with the conduct of a trade or business in the United
                      States; and
                    </li>
                    <li
                      class="left56 top10 lineheight18 color6668"
                    >Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required for purposes of chapters 3 and 4, that is subject to the certifications made on this form.</li>
                  </ul>
                </div>
                <div class="width100 rowTwo1 left57" style="margin-top:20px;width:90%;">
                  <input
                    id="paixu14b"
                    v-model="partnerShipB.PartVIII.shiti"
                    maxlength="30"
                    type="checkbox"
                    value="e2"
                    class="checkbox1"
                    name="checkbox14"
                  >
                  <label for="paixu14b" class="xiaoshou label2 color666">
                    <strong class="strong">21b</strong> I certify that the entity identified in Part I is a foreign partnership that is a partner in a lower-tier partnership and is providing this Form W-8IMY for purposes of section 1446.
                  </label>
                </div>
              </div>
              <div v-show="partnerShipB.isshow13" class="CORred">最少选择一个</div>
            </div>
            <!--根据条件 渲染部分 -->
            <!-- 渲染--Part XV -->
            <div v-show="partnerShipB.isshow=='XV'" class="formNapeContent1">
              <!-- style="height:34px" -->
              <div class="itemTitle">
                <div class="left">Part XV</div>
                <div class="right">Certified Deemed-Compliant Limited Life Debt Investment Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXVa"
                    v-model="partnerShipB.Part5isList.PartXV.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="f1"
                    class="checkbox1"
                    name="checkboxXV"
                  >
                  <label for="paixuXVa" class="xiaoshou label2 color666">
                    <strong class="strong">28</strong> I certify that the FFI identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Was in existence as of January 17, 2013;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Issued all classes of its debt or equity interests to investors on or before January 17, 2013 pursuant to a trust indenture or similar agreement; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is certified deemed-compliant because it satisfies the requirements to be treated as a limited life debt investment entity (such as the restrictions with respect to its assets and other requirements under Regulations section 1.1471- 5(f)(2)(iv)).</li>
                  </ul>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow28" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XIX. -->
            <div v-show="partnerShipB.isshow=='XIX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XIX</div>
                <div class="right">Exempt Retirement Plans</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <p class="color666">Check box 32a, b, c, d, e, or f, whichever applies.</p>
                  <div class="top20">
                    <input
                      id="paixuXIXa"
                      v-model="partnerShipB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j1"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXa" class="xiaoshou label2 color666">
                      <strong class="strong">32a</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is established in a country with which the United States has an income tax treaty in force;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is operated principally to administer or provide pension or retirement benefits; and</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is entitled to treaty benefits on income that the fund derives from U.S. sources (or would be entitled to benefits if it derived any such income) as a resident of the other country which satisfies any applicable limitation on benefits requirement.</li>
                    </ul>
                  </div>
                  <div class="top20">
                    <input
                      id="paixuXIXb"
                      v-model="partnerShipB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j2"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXb" class="xiaoshou label2 color666">
                      <strong class="strong">32b</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to beneficiaries that are former employees of one or more employers in consideration for services rendered;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >No single beneficiary has a right to more than 5% of the FFI's assets;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operated; and</li>
                    </ul>
                  </div>
                  <div class="top20">
                    <input
                      id="paixuXIXc"
                      v-model="partnerShipB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j3"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXc" class="xiaoshou label2 color666">
                      <strong class="strong">32c</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li class="left56 huifu top10 lineheight18 color6668">
                        Is organized for the provision of retirement, disability, or death benefits (or any combination thereof) to
                        beneficiaries that are former employees of one or more employers in consideration for services rendered;
                      </li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Has fewer than 50 participants;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is sponsored by one or more employers, each of which is not an investment entity or passive NFFE;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Employee and employer contributions to the fund (disregarding transfers of assets from other plans described in this part, retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A)) are limited by reference to earned income and compensation of the employee, respectively;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Participants that are not residents of the country in which the fund is established or operated are not entitled to more than 20 percent of the fund's assets; and</li>
                      <li class="left56 huifu top10 lineheight18 color6668">
                        Is subject to government regulation and provides annual information reporting about its beneficiaries to the relevant tax authorities in the country in which the fund is established or operates.
                        <ul class="left22 rowTwo3 top6">
                          <li>1. Is generally exempt from tax on investment income under the laws of the country in which it is established or operates due to its status as a retirement or pension plan;</li>
                          <li>2. Receives at least 50% of its total contributions from sponsoring employers (disregarding transfers of assets from other plans described in this part, retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, other retirement funds described in an applicable Model 1 or Model 2 IGA, or accounts described in Regulations section 1.1471-5(b)(2)(i)(A));</li>
                          <li>3. Either does not permit or penalizes distributions or withdrawals made before the occurrence of specified events related to retirement, disability, or death (except rollover distributions to accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts), to retirement and pension accounts described in an applicable Model 1 or Model 2 IGA, or to other retirement funds described in this part or in an applicable Model 1 or Model 2 IGA); or</li>
                          <li>4. Limits contributions by employees to the fund by reference to earned income of the employee or may not exceed $50,000 annually.</li>
                        </ul>
                      </li>
                    </ul>
                  </div>
                  <div class="top20 rowTwo1 left57">
                    <input
                      id="paixuXIXd"
                      v-model="partnerShipB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j4"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXd" class="xiaoshou label2 color666">
                      <strong class="strong">32d</strong> I certify that the entity identified in Part I is formed pursuant to a pension plan that would meet the requirements of section 401(a), other than the requirement that the plan be funded by a trust created or organized in the United States.
                    </label>
                  </div>
                  <div class="top20 rowTwo1 left57">
                    <input
                      id="paixuXIXe"
                      v-model="partnerShipB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j5"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXe" class="xiaoshou label2 color666">
                      <strong class="strong">32e</strong> I certify that the entity identified in Part I is established exclusively to earn income for the benefit of one or more retirement funds described in this part or in an applicable Model 1 or Model 2 IGA, accounts described in Regulations section 1.1471-5(b)(2)(i)(A) (referring to retirement and pension accounts), or retirement and pension accounts described in an applicable Model 1 or Model 2 IGA.
                    </label>
                  </div>
                  <div class="top20">
                    <input
                      id="paixuXIXf"
                      v-model="partnerShipB.Part5isList.PartXIX.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="j6"
                      class="checkbox1"
                      name="checkboxXIX"
                    >
                    <label for="paixuXIXf" class="xiaoshou label2 color666">
                      <strong class="strong">32f</strong> I certify that the entity identified in Part I:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are current or former employees of the sponsor (or persons designated by such employees); or</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >Is established and sponsored by a foreign government, international organization, central bank of issue, or government of a U.S. possession (each as defined in Regulations section 1.1471-6) or an exempt beneficial owner described in an applicable Model 1 or Model 2 IGA to provide retirement, disability, or death benefits to beneficiaries or participants that are not current or former employees of such sponsor, but are in consideration of personal services performed for the sponsor.</li>
                    </ul>
                  </div>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow32" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XX -->
            <div v-show="partnerShipB.isshow=='XX'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XX</div>
                <div class="right">Excepted Nonfinancial Group Entity</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXX"
                    v-model="partnerShipB.Part5isList.PartXX.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="k1"
                    class="checkbox1"
                    name="checkboxXX"
                  >
                  <label for="paixuXX" class="xiaoshou label2 color666">
                    <strong class="strong">33</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a holding company, treasury center, or captive finance company and substantially all of the entity's activities are functions described in Regulations section 1.1471-5(e)(5)(i)(C) through (E);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a member of a nonfinancial group described in Regulations section 1.1471-5(e)(5)(i)(B);</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not a depository or custodial institution (other than for members of the entity's expanded affiliated group); and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund or any investment vehicle with an investment strategy to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow33" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXI -->
            <div v-show="partnerShipB.isshow=='XXI'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXI</div>
                <div class="right">Excepted Nonfinancial Start-Up Company</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXXI"
                    v-model="partnerShipB.Part5isList.PartXXI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="l1"
                    class="checkbox1"
                    name="checkboxXXI"
                  >
                  <label for="paixuXXI" class="xiaoshou label2 color666">
                    <strong class="strong">34</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Was formed on (or in the case of a new line of business, the date of board resolution approving the new line of business)
                      <input
                        v-model="partnerShipB.Part5isList.PartXXI.input1"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 top6"
                      >
                      <!-- <div v-show="partnerShipB.isDataShow" class="CORred">日期不得超过申请日期之前2年</div>
                      <div v-show="partnerShipB.isDataShow2" class="CORred">请输入</div>-->
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is not yet operating a business and has no prior operating history or is investing capital in assets with the intent to operate a new line of business other than that of a financial institution or passive NFFE; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Does not function (or hold itself out) as an investment fund, such as a private equity fund, venture capital fund, leveraged buyout fund, or any investment vehicle whose purpose is to acquire or fund companies and then hold interests in those companies as capital assets for investment purposes.</li>
                  </ul>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow34" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXII -->
            <div v-show="partnerShipB.isshow=='XXII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXII</div>
                <div class="right">Excepted Nonfinancial Entity in Liquidation or Bankruptcy</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <input
                    id="paixuXXII"
                    v-model="partnerShipB.Part5isList.PartXXII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="m1"
                    class="checkbox1"
                    name="checkboxXXII"
                  >
                  <label for="paixuXXII" class="xiaoshou label2 color666">
                    <strong class="strong">35</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li class="left56 huifu top10 lineheight18 color6668">
                      Filed a plan of liquidation, filed a plan or reorganization, or filed for bankruptcy on the following date:
                      <input
                        v-model="partnerShipB.Part5isList.PartXXII.input1"
                        maxlength="30"
                        type="text"
                        placeholder="MM/DD/YYYY"
                        class="inputwt8 top6"
                      >
                      <!-- <div v-show="partnerShipB.isDataShow1" class="CORred">日期不得超过申请日期之前3年</div>
                      <div v-show="partnerShipB.isDataShow2" class="CORred">请输入</div>-->
                    </li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Has not been engaged during the past 5 years in business as a financial institution or acted as a passive NFFE;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is either liquidating or emerging from a reorganization or bankruptcy with the intent to continue or recommence operations as a nonfinancial entity; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Has provided, or will provide, documentary evidence such as a bankruptcy filing or other public documentation that supports its claim if it remains in bankruptcy or liquidation for more than three years.</li>
                  </ul>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow35" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXIII -->
            <div v-show="partnerShipB.isshow=='XXIII'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXIII</div>
                <div
                  class="right"
                >Publicly Traded NFFE or NFFE Affiliate of a Publicly Traded Corporation</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <p class="color666">Check box 36a or 36b, whichever applies.</p>
                  <input
                    id="paixuXXIIIa"
                    v-model="partnerShipB.Part5isList.PartXXIII.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="n1"
                    class="checkbox1"
                    name="checkboxXXIIIa"
                  >
                  <label for="paixuXXIIIa" class="xiaoshou label2 color666">
                    <strong class="strong">36a</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign corporation that is not a financial institution; and</li>
                    <li class="left56 huifu top10 lineheight18 color6668">
                      The stock of such corporation is regularly traded on one or more established securities markets, including
                      <input
                        v-model="partnerShipB.Part5isList.PartXXIII.input1"
                        maxlength="30"
                        type="text"
                        placeholder="请选择"
                        class="inputwt8 top6"
                      >
                    </li>
                  </ul>
                  <div class="top10">
                    <input
                      id="paixuXXIIIb"
                      v-model="partnerShipB.Part5isList.PartXXIII.fuxuan1"
                      maxlength="30"
                      type="checkbox"
                      value="n2"
                      class="checkbox1"
                      name="checkboxXXIIIb"
                    >
                    <label for="paixuXXIIIb" class="xiaoshou label2 color666">
                      <strong class="strong">36b</strong> I certify that:
                    </label>
                    <ul class="left67">
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >The entity identified in Part I is a foreign corporation that is not a financial institution;</li>
                      <li
                        class="left56 huifu top10 lineheight18 color6668"
                      >The entity identified in Part I is a member of the same expanded affiliated group as an entity the stock of which is regularly traded on an established securities market;</li>
                      <li class="left56 huifu top10 lineheight18 color6668">
                        The name of the entity, the stock of which is regularly traded on an established securities market, is
                        <input
                          v-model="partnerShipB.Part5isList.PartXXIII.input2"
                          maxlength="30"
                          type="text"
                          placeholder="请选择"
                          class="inputwt8 top6"
                        >
                        ; and
                      </li>
                      <li class="left56 huifu top10 lineheight18 color6668">
                        The name of the securities market on which the stock is regularly traded is
                        <input
                          v-model="partnerShipB.Part5isList.PartXXIII.input3"
                          maxlength="30"
                          type="text"
                          placeholder="请选择"
                          class="inputwt8 top6"
                        >
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow36" class="CORred">请输入</div>
            </div>
            <!-- 渲染--Part XXV -->
            <div v-show="partnerShipB.isshow=='XXV'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXV</div>
                <div class="right">Active NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <!-- <p class="color666">Check box 36a or 36b, whichever applies.</p> -->
                  <input
                    id="paixuXXV"
                    v-model="partnerShipB.Part5isList.PartXXV.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="o1"
                    class="checkbox1"
                    name="checkboxXXVa"
                  >
                  <label for="paixuXXV" class="xiaoshou label2 color666">
                    <strong class="strong">38</strong> I certify that:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >The entity identified in Part I is a foreign entity that is not a financial institution;</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of such entity's gross income for the preceding calendar year is passive income; and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Less than 50% of the assets held by such entity are assets that produce or are held for the production of passive income (calculated as a weighted average of the percentage of passive assets measured quarterly). See the instructions for the definition of passive income.</li>
                  </ul>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow38" class="CORred">最少选择一个</div>
            </div>
            <!-- 渲染--Part XXVI -->
            <div v-show="partnerShipB.isshow=='XXVI'" class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVI</div>
                <div class="right">Passive NFFE</div>
              </div>
              <div class="itemRow">
                <div class="width100">
                  <!-- <p class="color666">Check box 36a or 36b, whichever applies.</p> -->
                  <input
                    id="paixuXXVIa"
                    v-model="partnerShipB.Part5isList.PartXXVI.fuxuan1"
                    maxlength="30"
                    type="checkbox"
                    value="p1"
                    class="checkbox1"
                    name="checkboxXXVI"
                  >
                  <label for="paixuXXVIa" class="xiaoshou label2 color666">
                    <strong class="strong">39</strong> I certify that the entity identified in Part I:
                  </label>
                  <ul class="left67">
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is a foreign entity that is not a financial institution (this category includes an entity organized in a possession of the United States that engages (or holds itself out as being engaged) primarily in the business of investing, reinvesting, or trading in securities, partnership interests, commodities, notional principal contracts, insurance or annuity contracts, or any interest in such security, partnership interest, commodity, notional principal contract, insurance contract or annuity contract); and</li>
                    <li
                      class="left56 huifu top10 lineheight18 color6668"
                    >Is using this form to transmit withholding certificates and/or other documentation and has provided or will provide a withholding statement, as required.</li>
                  </ul>
                </div>
              </div>
              <div v-show="partnerShipB.isDataShow39" class="CORred">最少选择一个</div>
            </div>
            <!--Part XXVIII -->
            <div class="formNapeContent1">
              <div class="itemTitle" style="height:34px">
                <div class="left">Part XXVIII</div>
                <div class="right">Certification</div>
              </div>
              <!-- 14 -->
              <div class="itemRow" style="height:146px">
                <p
                  class="lineheight18 color6668"
                >Under penalties of perjury, I declare that I have examined the information on this form and to the best of my knowledge and belief it is true, correct, and complete. Furthermore, I authorize this form to be provided to any withholding agent that has control, receipt, or custody of the income for which I am providing this form or any withholding agent that can disburse or make payments of the amounts for which I am providing this form.</p>
                <p
                  class="top20 color666"
                >I agree that I will submit a new form within 30 days if any certification made on this form becomes incorrect.</p>
                <h4 class="top30a">签名</h4>
                <p
                  class="top16 lineheight24 color666"
                >本人认可，前述信息及账户申请流程中提供的所有其它信息均真实、准确，如该等信息发生重大变化，本人同意通过电子邮件通知国瑞证券。本人授权国瑞证券在必要时确认信息的准确性。</p>
                <ul class="account top20">
                  <li class="color666">账户名称:</li>
                  <li
                    style="margin-left:20px"
                    class="color6668"
                  >{{ partnerShipB.signature.accountName }}</li>
                </ul>
                <ul class="account top12">
                  <li class="color666">日期:</li>
                  <li style="margin-left:48px" class="color6668">{{ partnerShipB.signature.date }}</li>
                </ul>
                <p class="top30a color666">{{ partnerShipB.signature.accountName1 }}</p>
                <div class="top10">
                  <span class="color666">签名</span>
                  <input
                    v-model="partnerShipB.signature.UserSignature"
                    maxlength="30"
                    type="text"
                    class="inputwt8 inputwt8Huifu left22"
                    style="width:500px;"
                  >
                </div>
              </div>
              <div class="statement rowTwo2 left25P">
                <input
                  id="paixu14a1"
                  v-model="partnerShipB.signature.tongyi"
                  maxlength="30"
                  type="checkbox"
                  value="true"
                  class="checkbox1"
                  name="checkbox1"
                  checked
                >
                <label
                  for="paixu14a1"
                  class="xiaoshou label2 color666"
                >勾选此框即代表您同意所有税务表格均通过电子形式而非纸质形式收集和发送，包括通过账户管理收集和发送。您也确认该表格中包含的信息以及有关您账户的其他财务信息可能会根据法律要求被报告至账户所在国家的税务部门及/或全球其他税务机构。</label>
              </div>
              <div v-show="partnerShipB.isTongyi" class="CORred">请勾选</div>
            </div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>
<script>
import dictionary from '../dictionary/dictionary';
// import { userInfo } from 'os';
export default {
  // name: 'regulate',
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
      Wt8Type: '', // Wt8Type: 'limitedLiabLity',
      personal: { // 个人
        beneficiary: [
          {
            identifier: '1',
            companyNameTitle: '个人受益方姓名',
            companyNameValue: ''
          },
          {
            identifier: '2',
            companyNameTitle: '国籍',
            companyNameValue: ''
          },
          {
            identifier: '3',
            companyNameTitle:
              '永久居住地址（街道、公寓或房间号，或者乡邮投递路线）。请勿使用邮政信箱或转交地址。(选)',
            companyNameValue: ''
          },
          {
            identifier: '4',
            companyNameTitle: '城市或城镇，州或省。包括其对应的邮政编码。',
            companyNameValue: ''
          },
          {
            identifier: '5',
            companyNameTitle: '居住国家',
            companyNameValue: ''
          },
          {
            identifier: '6',
            companyNameTitle: '邮寄地址(若与上方所填地址不同，请填写)(选)',
            companyNameValue: ''
          },
          {
            identifier: '7',
            companyNameTitle: '城市或城镇，州或省。包括其对应的邮政编码。(选)',
            companyNameValue: ''
          },
          {
            identifier: '8',
            companyNameTitle: '邮寄国家(选)',
            companyNameValue: ''
          },
          {
            identifier: '9',
            companyNameTitle:
              '美国纳税人识别号码（SSN 或 ITIN），如有要求（参见说明）(选)',
            companyNameValue: ''
          },
          {
            identifier: '10',
            companyNameTitle: '海外税收识别号码（参见说明）(选)',
            companyNameValue: ''
          },
          {
            identifier: '11',
            companyNameTitle: '参考编号(参见说明)(选)',
            companyNameValue: ''
          },
          {
            identifier: '12',
            companyNameTitle: '出生日期（月份-日期-年份）（参见说明）',
            companyNameValue: ''
          }
        ],
        treatmentStatement: [
          {
            identifier: '13',
            companyNameTitle:
              '本人确认，受益所有人在美国与所属国的税收协定下，符合的居民定义。',
            companyNameValue: ''
          },
          {
            identifier: '14',
            companyNameTitle:
              '我们的记录显示您的一个地址不在上方列出的条约国之内。勾选下方相应的选项或就该地址提供解释。您可能需要提供该地址的证明文件。',
            companyNameValue: ''
          }
        ]
      },
      limitedLiablityA: { // 有限公司
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
          TongxunAddress: {
            // 通讯
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          payTaxesNumber: {
            // 8 纳税识别号
            number: ''
          },
          GIIN: {
            // 9
            numberA: '',
            numberB: ''
          },
          Reference: {
            // 参考编号
            number: ''
          }
        },
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
        isTongyi: false,
        isZHshow8: false,
        isZHshow9a: false,
        isZHshow9b: false,
        isZHshow10: false
      },
      limitedLiablityB: { //
        part1: {
          companyName: '',
          state: '',
          shitiName: '',
          radio1: '',
          radio2: '',
          YongJiuAddress: {
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          TongxunAddress: {
            // 邮寄
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          payTaxesNumber: {
            // 纳税识别号
            number: '',
            xuankuang: ''
          },
          GIIN: {
            // 9
            number: ''
          },
          Reference: {
            // 参考编号
            number: ''
          }
        },
        part2: {
          FATCAstatus: '', // FATCAstatus状态
          filialeAddress: {
            // 12 分公司地址
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

        isZHshow8: false, // 中文判断
        isZHshow9: false,
        isZHshow10: false,
        isZHshow12a: false,
        isZHshow12b: false,
        isZHshow13: false
      },
      partnerShipA: {
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
          TongxunAddress: {
            // 通讯
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          payTaxesNumber: {
            // 8 纳税识别号
            number: ''
          },
          GIIN: {
            // 9
            numberA: '',
            numberB: ''
          },
          Reference: {
            // 参考编号
            number: ''
          }
        },
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
        isTongyi: false,
        isZHshow8: false,
        isZHshow9a: false,
        isZHshow9b: false,
        isZHshow10: false
      },
      partnerShipB: {
        part1: {
          companyName: '',
          state: '',
          shitiName: '',
          radio1: '',
          radio2: '',
          YongJiuAddress: {
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          TongxunAddress: {
            // 邮寄
            chengshi: '',
            shegnshi: '',
            state: ''
          },
          payTaxesNumber: {
            // 纳税识别号
            number: '',
            xuankuang: ''
          },
          GIIN: {
            // 9
            number: ''
          },
          Reference: {
            // 参考编号
            number: ''
          }
        },
        part2: {
          FATCAstatus: '', // FATCAstatus状态
          filialeAddress: {
            // 12 分公司地址
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

        isZHshow8: false, // 中文判断
        isZHshow9: false,
        isZHshow10: false,
        isZHshow12a: false,
        isZHshow12b: false,
        isZHshow13: false
      }
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
    // personal
    // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')}
    // 请求接口后缀  G 个人 Y 有限 H 合伙
    Port() {
      this.personal.treatmentStatement.companyNameValue = [];
      if (this.userInfo.accountType === 'personal') {
        this.personal.beneficiary.map(item => {
          item.companyNameValue = '';
          return item;
        });
        this.personalMessageG();
        this.personalAddressG();
        this.personalwt8G();
      } else if (this.userInfo.accountType === 'limitedLiablity') {
        this.accountParTaxClassificationY();
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.accountParTaxClassificationH();
      }
    },
    accountParTaxClassificationY() { // 获取公司税收分类-展示wt8
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findTaxClassification',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (res.object[0].taxType.dictValue === 'enterprise') {
                // 企业
                this.getOneYA();
                this.getTwoYA();
                this.accountCoWbenContractYA();
                this.Wt8Type = 'limitedLiabLityA';
              } else if (
                res.object[0].taxType.dictValue ===
                'partnership (must have at least 2 owners)'
              ) {
                // 合伙企业
                this.getOneYB();
                this.getTwoYB();
                this.accountCoWbenContractYB();
                this.Wt8Type = 'limitedLiabLityB';
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
    accountParTaxClassificationH() { // 获取企业税收分类-展示wt8
      this.$http({
        url: '/account/registerOpen/partnerShip/findTaxClassification',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // debugger;
              if (res.object[0].taxType.dictValue === 'enterprise') {
                // 企业
                this.getOneHA();
                this.getTwoHA();
                this.accountCoWbenContractHA();
                this.Wt8Type = 'partnerShipA';
              } else if (
                res.object[0].taxType.dictValue ===
                'partnership (must have at least 2 owners)'
              ) {
                // 合伙企业
                this.getOneHB();
                this.getTwoHB();
                this.accountCoWbenContractHB();
                this.Wt8Type = 'partnerShipB';
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
    personalMessageG() { // 个人信息
      this.$http({
        url: '/account/registerOpen/personal/findPerInfo',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // 姓名
              for (let i = 0; i < this.personal.beneficiary.length; i++) {
                if (this.personal.beneficiary[i].identifier === '1') {
                  this.personal.beneficiary[i].companyNameValue =
                    res.object[0].lastName + res.object[0].firstName;
                } else if (this.personal.beneficiary[i].identifier === '12') {
                  // this.personal.beneficiary[i].companyNameValue = this.$verify.NowDate2(
                  //   this.$verify.NowDate1(Number(res.object[0].birthday))
                  // );
                  this.personal.beneficiary[i].companyNameValue = res.object[0].birthday;
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    personalAddressG() { // 个人地址
      this.$http({
        url: '/account/registerOpen/personal/findAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].addressType === 'personalAddress') {
                  // 个人地址类型
                  for (let j = 0; j < this.personal.beneficiary.length; j++) {
                    if (this.personal.beneficiary[j].identifier === '2') {
                      this.personal.beneficiary[j].companyNameValue =
                        res.object[i].country.cnName;
                    } else if (
                      this.personal.beneficiary[j].identifier === '3'
                    ) {
                      if (res.object[i].address.indexOf('-#-') !== -1) {
                        const siteTS = res.object[i].address.split('-#-');
                        this.personal.beneficiary[j].companyNameValue = `
                          ${siteTS[0]}
                          ${siteTS[1]}
                        `;
                      }
                    } else if (
                      this.personal.beneficiary[j].identifier === '4'
                    ) {
                      this.personal.beneficiary[j].companyNameValue = `
                          ${res.object[i].city}
                          ${res.object[i].rovince}
                          ${res.object[i].zipCode}
                        `;
                    } else if (
                      this.personal.beneficiary[j].identifier === '5'
                    ) {
                      this.personal.beneficiary[j].companyNameValue =
                        res.object[i].country.cnName;
                    }
                  }
                } else if (res.object[i].addressType === 'mail') {
                  for (let j = 0; j < this.personal.beneficiary.length; j++) {
                    if (this.personal.beneficiary[j].identifier === '6') {
                      if (res.object[i].address.indexOf('-#-') !== -1) {
                        const siteTS = res.object[i].address.split('-#-');
                        this.personal.beneficiary[j].companyNameValue = `
                          ${siteTS[0]}
                          ${siteTS[1]}
                        `;
                      }
                    } else if (
                      this.personal.beneficiary[j].identifier === '7'
                    ) {
                      this.personal.beneficiary[j].companyNameValue = `
                          ${res.object[i].city}
                          ${res.object[i].rovince}
                          ${res.object[i].zipCode}
                        `;
                    } else if (
                      this.personal.beneficiary[j].identifier === '8'
                    ) {
                      this.personal.beneficiary[j].companyNameValue =
                        res.object[i].country.cnName;
                    }
                  }
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    personalwt8G() { // 个人wt8
      this.$http({
        url: '/account/registerOpen/personal/findWbenContract',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              // // 第一部分
              for (let i = 0; i < this.personal.beneficiary.length; i++) {
                if (this.personal.beneficiary[i].identifier === '9') {
                  this.personal.beneficiary[i].companyNameValue =
                    res.object[0].usaItin;
                } else if (this.personal.beneficiary[i].identifier === '10') {
                  this.personal.beneficiary[i].companyNameValue =
                    res.object[0].overseasTaxNumber;
                } else if (this.personal.beneficiary[i].identifier === '11') {
                  this.personal.beneficiary[i].companyNameValue =
                    res.object[0].referenceNumber;
                } else if (this.personal.beneficiary[i].identifier === '12') {
                  // this.personal.beneficiary[i].companyNameValue = this.$verify.NowDate2(Number(res.object[0].otherBirthday));
                  this.personal.beneficiary[i].companyNameValue = res.object[0].otherBirthday;
                }
              }
              // // 第二部分
              for (
                let i = 0;
                i < this.personal.treatmentStatement.length;
                i++
              ) {
                if (this.personal.treatmentStatement[i].identifier === '13') {
                  this.personal.treatmentStatement[i].companyNameValue =
                    res.object[0].usaCountryAgreement.cnName;
                } else if (
                  this.personal.treatmentStatement[i].identifier === '14'
                ) {
                  if (res.object[0].explainAddress) {
                    this.personal.treatmentStatement[i].companyNameValue = res.object[0].explainAddress.dictDest;
                  }
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoWbenContractYA() { // 有限公司wt8 A
      // 获取wt8
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findWbenContract',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                // 第一部分
                this.limitedLiablityA.part1.companyName =
                  res.object[i].organizationName;
                this.limitedLiablityA.part1.state =
                  res.object[i].organizationCountry;
                this.limitedLiablityA.part1.radio1 =
                  res.object[i].chapterThreeStatus;
                this.limitedLiablityA.part1.radio2 =
                  res.object[i].chapterFourStatus;
                this.limitedLiablityA.part1.radio1b = res.object[i].whether;
                this.limitedLiablityA.part1.shitiName =
                  res.object[i].disregardedEntityName;
                this.limitedLiablityA.part1.GIIN.numberB =
                  res.object[i].foreignTin;
                this.limitedLiablityA.part1.GIIN.numberA =
                  res.object[i].giinXxx;
                this.limitedLiablityA.part1.payTaxesNumber.number =
                  res.object[i].usaTin;
                this.limitedLiablityA.part1.Reference.number =
                  res.object[i].referenceNumber;

                // 第一部分的第6.0点 地址取 wben中的地址
                // this.limitedLiablityA.part1.YongJiuAddress.chengshi =
                //   res.object[i].branchAddress;

                // this.limitedLiablityA.part1.YongJiuAddress.shegnshi =
                //   res.object[i].branchCity + '';

                // this.limitedLiablityA.part1.YongJiuAddress.state =
                //   res.object[i].branchCountry;
                // 第三部分
                this.limitedLiablityA.PartIII.radio14 =
                  res.object[i].nonwithholdingA;
                // 签名
                this.limitedLiablityA.signature.tongyi = res.object[i].status;
                // this.limitedLiablityA.signature.date = this.$verify.NowDate3();
                // 日期
                var date = new Date(+res.object[i].signatureTime);// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
                var Y = date.getFullYear() + '-';
                var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                var D = date.getDate() + ' ';
                var h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
                var m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
                var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
                this.limitedLiablityA.signature.date = Y + M + D + h + m + s;
                // 动态渲染部分
                const checked = JSON.parse(res.object[i].checked);
                if (checked.type === 'b10') {
                  this.limitedLiablityA.isXuanRshow = 'VIII';
                  this.limitedLiablityA.Part5isList.PartVIII =
                    checked.Part5isList.PartVIII;
                } else if (checked.type === 'b15') {
                  this.limitedLiablityA.isXuanRshow = 'XVIII';
                  this.limitedLiablityA.Part5isList.PartXVIII =
                    checked.Part5isList.PartXVIII;
                } else if (checked.type === 'b16') {
                  this.limitedLiablityA.isXuanRshow = 'XIX';
                  this.limitedLiablityA.Part5isList.PartXIX =
                    checked.Part5isList.PartXIX;
                } else if (checked.type === 'b17') {
                  this.limitedLiablityA.isXuanRshow = 'XX';
                  this.limitedLiablityA.Part5isList.PartXX =
                    checked.Part5isList.PartXX;
                } else if (checked.type === 'b18') {
                  this.limitedLiablityA.isXuanRshow = 'XXIII';
                  this.limitedLiablityA.Part5isList.PartXXIII =
                    checked.Part5isList.PartXXIII;
                } else if (checked.type === 'b20') {
                  this.limitedLiablityA.isXuanRshow = 'XXV';
                  this.limitedLiablityA.Part5isList.PartXXV =
                    checked.Part5isList.PartXXV;
                } else if (checked.type === 'b21') {
                  this.limitedLiablityA.isXuanRshow = 'XXVI';
                  this.limitedLiablityA.Part5isList.PartXXVI =
                    checked.Part5isList.PartXXVI;
                } else if (checked.type === 'b22') {
                  this.limitedLiablityA.isXuanRshow = 'XXVIII';
                  this.limitedLiablityA.Part5isList.PartXXVIII =
                    checked.Part5isList.PartXXVIII;
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getOneYA() { //  获取有限公司第一页信息进行渲染
      this.accountCoInstitutionalYA();
      this.accountCoAddressYA();
    },
    accountCoInstitutionalYA() { // 获取有限公司名称
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutional',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablityA.part1.companyName =
                res.object.institutionalName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoAddressYA() { // 获取有限公司国家名
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablityA.part1.state = res.object[0].country;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getTwoYA() { //  获取个第二页信息进行渲染
      this.personalTypeYA();
    },
    accountCoAddressTwoYA(personalType) { // 有限公司地址信息
      const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, personalType, lang }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                res.object[i].address = res.object[i].address.replace(/-#-/, '，');
                if (res.object[i].addressType === 'personalAddress') {
                  this.limitedLiablityA.part1.YongJiuAddress.chengshi =
                    res.object[i].address;
                  this.limitedLiablityA.part1.YongJiuAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.limitedLiablityA.part1.YongJiuAddress.state =
                    res.object[i].country.cnName;

                  this.limitedLiablityA.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.limitedLiablityA.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.limitedLiablityA.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
                } else if (res.object[i].addressType === 'mail') {
                  this.limitedLiablityA.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.limitedLiablityA.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.limitedLiablityA.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
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
    getSignatureYA(personalType) { // 声明 签名
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findPerInfo',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          personalType: 'authorizedPerson',
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablityA.signature.accountName = `
                ${res.object[0].accountCoPersonal.lastName}
                ${res.object[0].accountCoPersonal.middleName}
                ${res.object[0].accountCoPersonal.firstName}
              `;
              // this.limitedLiablityA.signature.date = this.$verify.NowDate3();
              this.limitedLiablityA.signature.accountName1 = `
                ${res.object[0].accountCoPersonal.lastName}
                ${res.object[0].accountCoPersonal.middleName}
                ${res.object[0].accountCoPersonal.firstName}
              `;
              this.limitedLiablityA.signature.UserSignature = `${res.object[0].accountCoPersonal.lastName} ${res.object[0].accountCoPersonal.middleName} ${res.object[0].accountCoPersonal.firstName}
              `;
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
    // -- 个人类型
    personalTypeYA() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.accountCoAddressTwoYA(res.object[1].dictValue);
            this.getSignatureYA(res.object[1].dictValue);
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 非中文处理
    // @blur和@focus
    CHprocessingYA(value, index) {
      const _this = this;
      if (value !== '') {
        const yanzhengCallback = function(data) {
          if (index === '8') {
            _this.limitedLiablityA.isZHshow8 = !data;
          } else if (index === '9a') {
            _this.limitedLiablityA.isZHshow9a = !data;
          } else if (index === '9b') {
            _this.limitedLiablityA.isZHshow9b = !data;
          } else {
            _this.limitedLiablityA.isZHshow10 = !data;
          }
        };
        this.$verify.notChineseSD(value, yanzhengCallback);
      } else {
        if (index === '8') {
          this.limitedLiablityA.isZHshow8 = false;
        } else if (index === '9a') {
          this.limitedLiablityA.isZHshow9a = false;
        } else if (index === '9b') {
          this.limitedLiablityA.isZHshow9b = false;
        } else if (index === '10') {
          this.limitedLiablityA.isZHshow10 = false;
        }
      }
    },
    accountCoWbenContractYB() { // 有限公司wt8 B
      // 获取wt8
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findWbenContract',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                // 第一部分
                this.limitedLiablityB.part1.companyName =
                  res.object[i].organizationName;
                this.limitedLiablityB.part1.state =
                  res.object[i].organizationCountry;
                this.limitedLiablityB.part1.radio1 =
                  res.object[i].chapterThreeStatus;
                this.limitedLiablityB.part1.radio2 =
                  res.object[i].chapterFourStatus;
                this.limitedLiablityB.part1.shitiName =
                  res.object[i].disregardedEntityName;
                this.limitedLiablityB.part1.payTaxesNumber.xuankuang =
                  res.object[i].ein;
                this.limitedLiablityB.part1.GIIN.number = res.object[i].gginIf;
                this.limitedLiablityB.part1.Reference.number =
                  res.object[i].referenceNumber;
                this.limitedLiablityB.part1.payTaxesNumber.number = res.object[i].usaTin;
                // 6.0 地址信息
                // this.limitedLiablityB.part1.YongJiuAddress.chengshi =
                //   res.object[i].branchAddress;

                // this.limitedLiablityB.part1.YongJiuAddress.shegnshi =
                //   res.object[i].branchCity + '';

                // this.limitedLiablityB.part1.YongJiuAddress.state =
                //   res.object[i].branchCountry;
                // 第二部分
                this.limitedLiablityB.part2.filialeAddress.chengshi =
                  res.object[i].branchAddress;
                this.limitedLiablityB.part2.filialeAddress.shegnshi =
                  res.object[i].branchCity;
                this.limitedLiablityB.part2.filialeAddress.state =
                  res.object[i].branchCountry;
                this.limitedLiablityB.part2.FATCAstatus = res.object[i].ffi;
                this.limitedLiablityB.part2.GIIN.GIINvalue =
                  res.object[i].giinXxx;
                // VIII
                if (res.object[i].nonwithholdingA.indexOf(',') !== -1) {
                  const nonwithholdingA = res.object[i].nonwithholdingA.split(
                    ','
                  );
                  for (let i = 0; i < nonwithholdingA.length; i++) {
                    this.limitedLiablityB.PartVIII.shiti.push(
                      nonwithholdingA[i]
                    );
                  }
                } else {
                  this.limitedLiablityB.PartVIII.shiti.push(
                    res.object[i].nonwithholdingA
                  );
                }
                // 签名
                // 日期
                var date = new Date(+res.object[i].signatureTime);// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
                var Y = date.getFullYear() + '-';
                var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                var D = date.getDate() + ' ';
                var h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
                var m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
                var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
                this.limitedLiablityA.signature.date = Y + M + D + h + m + s;
                this.limitedLiablityB.signature.tongyi = res.object[i].status;
                // 渲染部分
                const checked = JSON.parse(res.object[i].checked);
                if (checked.type === 'b11') {
                  this.limitedLiablityB.isshow = 'XV';
                  this.limitedLiablityB.Part5isList.PartXV =
                    checked.Part5isList.PartXV;
                } else if (checked.type === 'b16') {
                  this.limitedLiablityB.isshow = 'XIX';
                  this.limitedLiablityB.Part5isList.PartXIX =
                    checked.Part5isList.PartXIX;
                } else if (checked.type === 'b17') {
                  this.limitedLiablityB.isshow = 'XX';
                  this.limitedLiablityB.Part5isList.PartXX =
                    checked.Part5isList.PartXX;
                } else if (checked.type === 'b18') {
                  this.limitedLiablityB.isshow = 'XXI';
                  this.limitedLiablityB.Part5isList.PartXXI =
                    checked.Part5isList.PartXXI;
                } else if (checked.type === 'b19') {
                  this.limitedLiablityB.isshow = 'XXII';
                  this.limitedLiablityB.Part5isList.PartXXII =
                    checked.Part5isList.PartXXII;
                } else if (checked.type === 'b20') {
                  this.limitedLiablityB.isshow = 'XXIII';
                  this.limitedLiablityB.Part5isList.PartXXIII =
                    checked.Part5isList.PartXXIII;
                } else if (checked.type === 'b22') {
                  this.limitedLiablityB.isshow = 'XXV';
                  this.limitedLiablityB.Part5isList.PartXXV =
                    checked.Part5isList.PartXXV;
                } else if (checked.type === 'b23') {
                  this.limitedLiablityB.isshow = 'XXVI';
                  this.limitedLiablityB.Part5isList.PartXXVI =
                    checked.Part5isList.PartXXVI;
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getOneYB() {
      //  获取第一页信息进行渲染
      this.accountCoInstitutionalYB();
      this.accountCoAddressYB();
    },
    accountCoInstitutionalYB() {
      // 获取公司名
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutional',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablityB.part1.companyName =
                res.object.institutionalName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoAddressYB() {
      // 获取国家名
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablityB.part1.state = res.object[0].country.cnName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getTwoYB() {
      //  获取第二页信息进行渲染
      this.personalTypeYB();
    },
    accountCoAddressTwoYB(personalType) {
      // 公司地址信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                res.object[i].address = res.object[i].address.replace(/-#-/, '，');
                if (i === 0) { // 邮件地址
                  this.limitedLiablityB.part1.YongJiuAddress.chengshi =
                    res.object[i].address;
                  this.limitedLiablityB.part1.YongJiuAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.limitedLiablityB.part1.YongJiuAddress.state =
                    res.object[i].country.cnName;

                  this.limitedLiablityB.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.limitedLiablityB.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.limitedLiablityB.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
                } else if (i === 1) { // 通讯地址
                  this.limitedLiablityB.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.limitedLiablityB.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.limitedLiablityB.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
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
    getSignatureYB(personalType) {
      // 声明 签名
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findPerInfo',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          personalType: 'authorizedPerson',
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablityB.signature.accountName = `
                ${res.object[0].accountCoPersonal.lastName}
                ${res.object[0].accountCoPersonal.middleName}
                ${res.object[0].accountCoPersonal.firstName}
              `;
              // this.limitedLiablityB.signature.date = this.$verify.NowDate3();
              this.limitedLiablityB.signature.accountName1 = `
                ${res.object[0].accountCoPersonal.lastName}
                ${res.object[0].accountCoPersonal.middleName}
                ${res.object[0].accountCoPersonal.firstName}
              `;
              this.limitedLiablityB.signature.UserSignature = `${res.object[0].accountCoPersonal.lastName} ${res.object[0].accountCoPersonal.middleName} ${res.object[0].accountCoPersonal.firstName}
              `;
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
    // -- 个人类型
    personalTypeYB() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.accountCoAddressTwoYB(res.object[1].dictValue);
            this.getSignatureYB(res.object[1].dictValue);
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 非中文处理
    // @blur和@focus
    CHprocessingYB(value, index) {
      const _this = this;
      if (value !== '') {
        const yanzhengCallback = function(data) {
          if (index === '8') {
            _this.limitedLiablityB.isZHshow8 = !data;
          } else if (index === '9') {
            _this.limitedLiablityB.isZHshow9 = !data;
          } else if (index === '10') {
            _this.limitedLiablityB.isZHshow10 = !data;
          } else if (index === '12a') {
            _this.limitedLiablityB.isZHshow12a = !data;
          } else if (index === '12b') {
            _this.limitedLiablityB.isZHshow12b = !data;
          } else if (index === '13') {
            _this.limitedLiablityB.isZHshow13 = !data;
          }
        };
        this.verificationDFT.notChineseSD(value, yanzhengCallback);
      } else {
        if (index === '8') {
          this.limitedLiablityB.isZHshow8 = false;
        } else if (index === '9') {
          this.limitedLiablityB.isZHshow9 = false;
        } else if (index === '10') {
          this.limitedLiablityB.isZHshow10 = false;
        } else if (index === '12a') {
          this.limitedLiablityB.isZHshow12a = false;
        } else if (index === '12b') {
          this.limitedLiablityB.isZHshow12b = false;
        } else if (index === '13') {
          this.limitedLiablityB.isZHshow13 = false;
        }
      }
    },
    // 第一部分5小节点击事件,完成第五部分渲染部分
    ApplyColoursYB(part) {
      this.limitedLiablityB.Part5isList = JSON.parse(
        JSON.stringify(this.limitedLiablityB.newPart5isListTS)
      );
      this.limitedLiablityB.isshow = part;
    },
    // 合伙企业wt8 A
    accountCoWbenContractHA() {
      // 获取wt8
      this.$http({
        url: '/account/registerOpen/partnerShip/findWbenContract',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  // 第一部分
                  this.partnerShipA.part1.companyName =
                    res.object[i].organizationName;
                  this.partnerShipA.part1.state =
                    res.object[i].organizationCountry;
                  this.partnerShipA.part1.radio1 =
                    res.object[i].chapterThreeStatus;
                  this.partnerShipA.part1.radio2 =
                    res.object[i].chapterFourStatus;
                  this.partnerShipA.part1.radio1b = res.object[i].whether;
                  this.partnerShipA.part1.shitiName =
                    res.object[i].disregardedEntityName;
                  this.partnerShipA.part1.GIIN.numberB =
                    res.object[i].foreignTin;
                  this.partnerShipA.part1.GIIN.numberA = res.object[i].giinXxx;
                  this.partnerShipA.part1.payTaxesNumber.number =
                    res.object[i].usaTin;
                  this.partnerShipA.part1.Reference.number =
                    res.object[i].referenceNumber;
                  //  6.0地址
                  // this.partnerShipA.part1.YongJiuAddress.chengshi =
                  // res.object[i].branchAddress;

                  // this.partnerShipA.part1.YongJiuAddress.shegnshi =
                  // res.object[i].branchCity + '';

                  // this.partnerShipA.part1.YongJiuAddress.state =
                  // res.object[i].branchCountry;

                  // 第三部分
                  this.partnerShipA.PartIII.radio14 =
                    res.object[i].nonwithholdingA;
                  // 签名
                  this.partnerShipA.signature.tongyi = res.object[i].status;
                  // 日期
                  var date = new Date(+res.object[i].signatureTime);// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
                  var Y = date.getFullYear() + '-';
                  var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                  var D = date.getDate() + ' ';
                  var h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
                  var m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
                  var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
                  this.partnerShipA.signature.date = Y + M + D + h + m + s;
                  // 动态渲染部分
                  const checked = JSON.parse(res.object[i].checked);

                  if (checked.type === 'b10') {
                    this.partnerShipA.isXuanRshow = 'VIII';
                    this.partnerShipA.Part5isList.PartVIII =
                      checked.Part5isList.PartVIII;
                  } else if (checked.type === 'b15') {
                    this.partnerShipA.isXuanRshow = 'XVIII';
                    this.partnerShipA.Part5isList.PartXVIII =
                      checked.Part5isList.PartXVIII;
                  } else if (checked.type === 'b16') {
                    this.partnerShipA.isXuanRshow = 'XIX';
                    this.partnerShipA.Part5isList.PartXIX =
                      checked.Part5isList.PartXIX;
                  } else if (checked.type === 'b17') {
                    this.partnerShipA.isXuanRshow = 'XX';
                    this.partnerShipA.Part5isList.PartXX =
                      checked.Part5isList.PartXX;
                  } else if (checked.type === 'b18') {
                    this.partnerShipA.isXuanRshow = 'XXIII';
                    this.partnerShipA.Part5isList.PartXXIII =
                      checked.Part5isList.PartXXIII;
                  } else if (checked.type === 'b20') {
                    this.partnerShipA.isXuanRshow = 'XXV';
                    this.partnerShipA.Part5isList.PartXXV =
                      checked.Part5isList.PartXXV;
                  } else if (checked.type === 'b21') {
                    this.partnerShipA.isXuanRshow = 'XXVI';
                    this.partnerShipA.Part5isList.PartXXVI =
                      checked.Part5isList.PartXXVI;
                  } else if (checked.type === 'b22') {
                    this.partnerShipA.isXuanRshow = 'XXVIII';
                    this.partnerShipA.Part5isList.PartXXVIII =
                      checked.Part5isList.PartXXVIII;
                  }
                }
              }
            }
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getOneHA() {
      //  获取第一页信息进行渲染
      this.accountCoInstitutionalHA();
      this.accountCoAddressHA();
    },
    accountCoInstitutionalHA() {
      // 获取公司名
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutional',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShipA.part1.companyName =
                res.object[0].institutionalName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoAddressHA() {
      // 获取国家名
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShipA.part1.state = res.object[0].country.cnName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getTwoHA() {
      //  获取第二页信息进行渲染
      this.personalTypeHA();
    },
    accountCoAddressTwoHA(personalType) {
      // 公司地址信息
      const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, personalType, lang }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                res.object[i].address = res.object[i].address.replace(/-#-/, '，');
                if (res.object[i].addressType === 'personalAddress') {
                  this.partnerShipA.part1.YongJiuAddress.chengshi =
                    res.object[i].address;
                  this.partnerShipA.part1.YongJiuAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.partnerShipA.part1.YongJiuAddress.state =
                    res.object[i].country.cnName;

                  this.partnerShipA.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.partnerShipA.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.partnerShipA.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
                } else if (res.object[i].addressType === 'mail') {
                  this.partnerShipA.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.partnerShipA.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.partnerShipA.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
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
    // 声明 签名
    getSignatureHA(personalType) {
      this.$http({
        url: '/account/registerOpen/partnerShip/findPerInfo',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          personalType: 'authorizedPerson',
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShipA.signature.accountName = `
                ${res.object[0].accountParPersonal.lastName}
                ${res.object[0].accountParPersonal.middleName}
                ${res.object[0].accountParPersonal.firstName}
              `;
              // this.partnerShipA.signature.date = this.$verify.NowDate3();
              this.partnerShipA.signature.accountName1 = `
                ${res.object[0].accountParPersonal.lastName}
                ${res.object[0].accountParPersonal.middleName}
                ${res.object[0].accountParPersonal.firstName}
              `;
              this.partnerShipA.signature.UserSignature = `${res.object[0].accountParPersonal.lastName} ${res.object[0].accountParPersonal.middleName} ${res.object[0].accountParPersonal.firstName}
              `;
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
    // -- 个人类型
    personalTypeHA() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.accountCoAddressTwoHA(res.object[1].dictValue);
            this.getSignatureHA(res.object[1].dictValue);
          } else {
            this.$message.error(res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 非中文处理
    // @blur和@focus
    CHprocessingHA(value, index) {
      const _this = this;
      if (value !== '') {
        const yanzhengCallback = function(data) {
          if (index === '8') {
            _this.limitedLiablityA.isZHshow8 = !data;
          } else if (index === '9a') {
            _this.limitedLiablityA.isZHshow9a = !data;
          } else if (index === '9b') {
            _this.limitedLiablityA.isZHshow9b = !data;
          } else {
            _this.limitedLiablityA.isZHshow10 = !data;
          }
        };
        this.$verify.notChineseSD(value, yanzhengCallback);
      } else {
        if (index === '8') {
          this.limitedLiablityA.isZHshow8 = false;
        } else if (index === '9a') {
          this.limitedLiablityA.isZHshow9a = false;
        } else if (index === '9b') {
          this.limitedLiablityA.isZHshow9b = false;
        } else if (index === '10') {
          this.limitedLiablityA.isZHshow10 = false;
        }
      }
    },
    // 合伙企业wt8 B
    accountCoWbenContractHB() {
      // 获取wt8
      this.$http({
        url: '/account/registerOpen/partnerShip/findWbenContract',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (i === 0) {
                  // 第一部分
                  this.partnerShipB.part1.companyName =
                    res.object[i].organizationName;
                  this.partnerShipB.part1.state =
                    res.object[i].organizationCountry;
                  this.partnerShipB.part1.radio1 =
                    res.object[i].chapterThreeStatus;
                  this.partnerShipB.part1.radio2 =
                    res.object[i].chapterFourStatus;
                  this.partnerShipB.part1.shitiName =
                    res.object[i].disregardedEntityName;
                  this.partnerShipB.part1.payTaxesNumber.xuankuang =
                    res.object[i].ein;
                  this.partnerShipB.part1.GIIN.number = res.object[i].gginIf;
                  this.partnerShipB.part1.Reference.number =
                    res.object[i].referenceNumber;
                  // debugger;
                  this.partnerShipB.part1.payTaxesNumber.number =
                    res.object[i].usaTin;

                  //  6.0地址
                  // this.partnerShipB.part1.YongJiuAddress.chengshi =
                  //   res.object[i].branchAddress;

                  // this.partnerShipB.part1.YongJiuAddress.shegnshi =
                  //   res.object[i].branchCity + '';

                  // this.partnerShipB.part1.YongJiuAddress.state =
                  //   res.object[i].branchCountry;
                  // 第二部分
                  this.partnerShipB.part2.filialeAddress.chengshi =
                    res.object[i].branchAddress;
                  this.partnerShipB.part2.filialeAddress.shegnshi =
                    res.object[i].branchCity;
                  this.partnerShipB.part2.filialeAddress.state =
                    res.object[i].branchCountry;
                  this.partnerShipB.part2.FATCAstatus = res.object[i].ffi;
                  this.partnerShipB.part2.GIIN.GIINvalue =
                    res.object[i].giinXxx;
                  // VIII
                  if (res.object[i].nonwithholdingA.indexOf(',') !== -1) {
                    const nonwithholdingA = res.object[i].nonwithholdingA.split(
                      ','
                    );
                    for (let i = 0; i < nonwithholdingA.length; i++) {
                      this.partnerShipB.PartVIII.shiti.push(nonwithholdingA[i]);
                    }
                  } else {
                    this.partnerShipB.PartVIII.shiti.push(
                      res.object[i].nonwithholdingA
                    );
                  }
                  // 签名
                  this.partnerShipB.signature.tongyi = res.object[i].status;
                  // 日期
                  var date = new Date(+res.object[i].signatureTime);// 时间戳为10位需*1000，时间戳为13位的话不需乘1000
                  var Y = date.getFullYear() + '-';
                  var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
                  var D = date.getDate() + ' ';
                  var h = (date.getHours() < 10 ? '0' + (date.getHours()) : date.getHours()) + ':';
                  var m = (date.getMinutes() < 10 ? '0' + (date.getMinutes()) : date.getMinutes()) + ':';
                  var s = (date.getSeconds() < 10 ? '0' + (date.getSeconds()) : date.getSeconds());
                  this.partnerShipB.signature.date = Y + M + D + h + m + s;
                  // 渲染部分
                  const checked = JSON.parse(res.object[i].checked);
                  if (checked.type === 'b11') {
                    this.partnerShipB.isshow = 'XV';
                    this.partnerShipB.Part5isList.PartXV =
                      checked.Part5isList.PartXV;
                  } else if (checked.type === 'b16') {
                    this.partnerShipB.isshow = 'XIX';
                    this.partnerShipB.Part5isList.PartXIX =
                      checked.Part5isList.PartXIX;
                  } else if (checked.type === 'b17') {
                    this.partnerShipB.isshow = 'XX';
                    this.partnerShipB.Part5isList.PartXX =
                      checked.Part5isList.PartXX;
                  } else if (checked.type === 'b18') {
                    this.partnerShipB.isshow = 'XXI';
                    this.partnerShipB.Part5isList.PartXXI =
                      checked.Part5isList.PartXXI;
                  } else if (checked.type === 'b19') {
                    this.partnerShipB.isshow = 'XXII';
                    this.partnerShipB.Part5isList.PartXXII =
                      checked.Part5isList.PartXXII;
                  } else if (checked.type === 'b20') {
                    this.partnerShipB.isshow = 'XXIII';
                    this.partnerShipB.Part5isList.PartXXIII =
                      checked.Part5isList.PartXXIII;
                  } else if (checked.type === 'b22') {
                    this.partnerShipB.isshow = 'XXV';
                    this.partnerShipB.Part5isList.PartXXV =
                      checked.Part5isList.PartXXV;
                  } else if (checked.type === 'b23') {
                    this.partnerShipB.isshow = 'XXVI';
                    this.partnerShipB.Part5isList.PartXXVI =
                      checked.Part5isList.PartXXVI;
                  }
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
    getOneHB() {
      //  获取第一页信息进行渲染
      this.accountCoInstitutionalHB();
      this.accountCoAddressHB();
    },
    accountCoInstitutionalHB() {
      // 获取公司名
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutional',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShipB.part1.companyName =
                res.object[0].institutionalName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoAddressHB() {
      // 获取国家名
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShipB.part1.state = res.object[0].country.cnName;
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getTwoHB() {
      //  获取第二页信息进行渲染
      this.personalTypeHB();
    },
    accountCoAddressTwoHB(personalType) {
      // 公司地址信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalAddress',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                res.object[i].address = res.object[i].address.replace(/-#-/, '，');
                if (res.object[i].addressType === 'personalAddress') {
                  this.partnerShipB.part1.YongJiuAddress.chengshi =
                    res.object[i].address;
                  this.partnerShipB.part1.YongJiuAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.partnerShipB.part1.YongJiuAddress.state =
                    res.object[i].country.cnName;

                  this.partnerShipB.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.partnerShipB.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.partnerShipB.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
                } else if (res.object[i].addressType === 'mail') {
                  this.partnerShipB.part1.TongxunAddress.chengshi =
                    res.object[i].address;
                  this.partnerShipB.part1.TongxunAddress.shegnshi =
                    res.object[i].city +
                    '' +
                    res.object[i].rovince +
                    '' +
                    res.object[i].zipCode;
                  this.partnerShipB.part1.TongxunAddress.state =
                    res.object[i].country.cnName;
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
    getSignatureHB(personalType) {
      // 声明 签名
      this.$http({
        url: '/account/registerOpen/partnerShip/findPerInfo',
        method: 'get',
        params: {
          accountId: this.userInfo.id,
          personalType: 'authorizedPerson',
          lang: this.$Cookies.get('locale')
        }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShipB.signature.accountName = `
                ${res.object[0].accountParPersonal.lastName}
                ${res.object[0].accountParPersonal.middleName}
                ${res.object[0].accountParPersonal.firstName}
              `;
              this.partnerShipB.signature.date = this.$verify.NowDate3();
              this.partnerShipB.signature.accountName1 = `
                ${res.object[0].accountParPersonal.lastName}
                ${res.object[0].accountParPersonal.middleName}
                ${res.object[0].accountParPersonal.firstName}
              `;
              this.partnerShipB.signature.UserSignature = `${res.object[0].accountParPersonal.lastName} ${res.object[0].accountParPersonal.middleName} ${res.object[0].accountParPersonal.firstName}
              `;
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
    // -- 个人类型
    personalTypeHB() {
      // const lang = this.$Cookies.get('locale');
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.accountCoAddressTwoHB(res.object[1].dictValue);
            this.getSignatureHB(res.object[1].dictValue);
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    // 非中文处理
    // @blur和@focus
    CHprocessingHB(value, index) {
      const _this = this;
      if (value !== '') {
        const yanzhengCallback = function(data) {
          if (index === '8') {
            _this.partnerShipB.isZHshow8 = !data;
          } else if (index === '9') {
            _this.partnerShipB.isZHshow9 = !data;
          } else if (index === '10') {
            _this.partnerShipB.isZHshow10 = !data;
          } else if (index === '12a') {
            _this.partnerShipB.isZHshow12a = !data;
          } else if (index === '12b') {
            _this.partnerShipB.isZHshow12b = !data;
          } else if (index === '13') {
            _this.partnerShipB.isZHshow13 = !data;
          }
        };
        this.verificationDFT.notChineseSD(value, yanzhengCallback);
      } else {
        if (index === '8') {
          this.partnerShipB.isZHshow8 = false;
        } else if (index === '9') {
          this.partnerShipB.isZHshow9 = false;
        } else if (index === '10') {
          this.partnerShipB.isZHshow10 = false;
        } else if (index === '12a') {
          this.partnerShipB.isZHshow12a = false;
        } else if (index === '12b') {
          this.partnerShipB.isZHshow12b = false;
        } else if (index === '13') {
          this.partnerShipB.isZHshow13 = false;
        }
      }
    },
    // 第一部分5小节点击事件,完成第五部分渲染部分
    ApplyColoursHB(part) {
      this.partnerShipB.Part5isList = JSON.parse(
        JSON.stringify(this.partnerShipB.newPart5isListTS)
      );
      this.partnerShipB.isshow = part;
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
// .xiaoshouPaixu{
//   // padding-left: 45px;
// }
.wt8ContentG {
  ul {
    overflow: hidden;
    margin-bottom: 0px;
    margin-top: 0;
    padding: 10px 0;
    margin-left: 10px;
    &:hover {
      background: rgb(246, 248, 249);
    }
    li {
      float: left;
      // display: inline-block;
      list-style: none;
      color: #999;
    }
    li:first-child {
      width: 50%;
      margin-right: 20px;
      overflow: hidden;
    }
    li:nth-child(2) {
      width: 34%;
      word-wrap: break-word;
      text-align: right;
    }
    li:nth-child(n + 3) {
      width: 50%;
      word-wrap: break-word;
      text-align: right;
    }
  }
}
.wt8ContentY {
  pointer-events: none;
  width: 90%;
  margin-top: 20px;
  padding: 44px;
  border: 1px solid $Cor999;
  border-radius: 5px;
  .formNape1 {
    width: 95.78%;
    background-color: #f6f6f6;
    padding: 50px 30px 200px 30px;
    .formNapeTitle1 {
      margin-bottom: 60px;
      p {
        text-align: center;
      }
      .titleP {
        font-size: 18px;
        line-height: 30px;
      }
      h3 {
        margin-top: 30px;
        text-align: center;
        font-size: 24px;
        font-weight: bold;
        span {
          font-size: 16px;
        }
      }
      .suiwuGL {
        margin-top: 18px;
        text-align: center;
        font-size: 16px;
      }
      .describec {
        margin-top: 16px;
        font-size: 14px;
        a {
          color: rgb(19, 148, 223);
        }
      }
      .rowTitel1 {
        text-align: left;
        margin-top: 50px;
      }
      ul {
        text-align: left;
        margin-top: 16px;
        li {
          margin-top: 8px;
          font-size: 14px;
          line-height: 18px;
        }
      }
    }
    .formNapeContent1 {
      margin-top: 60px;
      .itemTitle {
        height: 34px;
        border-top: 1px solid #000;
        border-bottom: 1px solid #000;
        overflow: hidden;
        > div {
          float: left;
        }
        .left {
          text-align: center;
          width: 180px;
          height: 34px;
          background-color: black;
          color: aliceblue;
          line-height: 34px;
        }
        .right {
          line-height: 23px;
          margin-top: 6px;
          width: 77.9%;
          float: left;
          padding-left: 20px;
        }
      }
      .itemRow {
        margin-top: 30px;
        width: 100%;
      }
      .inputwt8 {
        width: 98.5%;
        height: 34px;
        padding: 0;
        padding-left: 10px;
        border: 1px solid rgb(216, 216, 216);
      }
      .inputwt8.inputwt8Huifu {
        background-color: #fff;
      }
      .statement {
        margin-top: 250px;
        padding: 20px 0px 20px 0px;
        border-top: 1px solid #000;
        border-bottom: 1px solid #000;
      }
      .account {
        padding-left: 0px;
        overflow: hidden;
      }
      .account > li {
        float: left;
      }
    }
  }
  /* 清除itemRow 高度影响 或行内加高度 */
  .height152 {
    height: 152px;
  }
  input:disabled {
    // WT8 B
    background-color: #f5f7fa;
  }
  /* 单选框 自定义样式 */
  .radio1 {
    display: none;
  }
  .label1 {
    position: relative;
    margin-right: 34px;
    line-height: 19px;
  }
  .label1::before {
    display: inline-block;
    content: "";
    width: 16px;
    height: 16px;
    border-radius: 50%;
    background-color: #d8d8d8;
    margin-right: 6px;
    vertical-align: bottom;
  }
  .label1.NOdisabled::before {
    border: 1px solid #d8d8d8;
    background-color: rgb(247, 247, 247);
  }
  .radio1:checked + .label1::before {
    background-color: rgb(46, 143, 235);
  }
  /* 复选框 自定义样式 */
  .checkbox1 {
    display: none;
  }
  .label2 {
    position: relative;
    margin-right: 34px;
    line-height: 19px;
    color: rgba(102, 102, 102, 0.8);
  }
  .label2::before {
    display: inline-block;
    position: absolute;
    left:-22px;
    content: "";
    width: 16px;
    height: 16px;
    background-color: #ffffff;
    border: 1px solid rgb(211, 211, 211);
    margin-right: 8px;
    vertical-align: bottom;
  }
  .checkbox1:checked + .label2::before {
    background-color: rgb(46, 143, 235);
  }

  .lineheight18 {
    line-height: 18px;
  }
  .lineheight24 {
    line-height: 24px;
  }

  /* 实现第一行以下部分的缩进 */
  .left14 {
    margin-left: 14px;
  }
  .rowTwo3 {
    /* 给div大盒子加 */
    text-indent: -14px;
  }
  .left22 {
    margin-left: 22px;
  }
  .rowTwo {
    /* 给div大盒子加 */
    text-indent: -22px;
  }
  .left57 {
    margin-left: 94px;
  }
  .left67 {
    margin-left: 67px;
  }
  .rowTwo1 {
    text-indent: -94px;
  }
  #ZC-regiMain .formNapeContent1 .left25P {
    padding-left: 69px;
  }
  // .rowTwo2 {
  //   // text-indent: -69px;
  // }
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
  .left67 {
    margin-left: 67px;
  }
  .paddingLeft0 {
    padding-left: 0;
  }
  .color666 {
    color: #666;
  }
  .color6668 {
    color: rgba(102, 102, 102, 0.8);
  }
  .color666BG {
    color: #666;
  }
  .color6668BG {
    color: rgba(102, 102, 102, 0.8);
  }
  .strong {
    font-weight: bold;
  }
}
</style>
