<template>
  <div>
    <!-- 个人 -->
    <div v-if="userInfo.accountType==='personal'">
      <div class="messageContent">
        <div class="personalInformation">
          <h3>个人信息</h3>
          <ul>
            <li>称呼</li>
            <li>{{ personal.call }}</li>
          </ul>
          <ul>
            <li>姓名</li>
            <li>{{ personal.name }}</li>
          </ul>
          <ul>
            <li>出生日期</li>
            <li>{{ personal.dateBirth }}</li>
          </ul>
          <ul>
            <li>性别</li>
            <li>{{ personal.sex }}</li>
          </ul>
          <ul>
            <li>婚姻状况</li>
            <li>{{ personal.maritalStatus }}</li>
          </ul>
          <ul>
            <li>家庭成员数目</li>
            <li>{{ personal.familyNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>地址</h3>
          <ul>
            <li>地址</li>
            <li>{{ personal.address }}</li>
          </ul>
          <ul>
            <li>邮寄地址(选)</li>
            <li>{{ personal.mailingAddress }}</li>
          </ul>
          <ul>
            <li>不同国籍(选)</li>
            <li>{{ personal.differentState.cnName }}</li>
          </ul>
          <ul>
            <li>不同出生国家(选)</li>
            <li>{{ personal.differentBirthState.cnName }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>联系信息</h3>
          <ul v-for="(item,index) in personal.RelationMessage" :key="'Message-'+index">
            <li>联系信息{{ index+1 }}</li>
            <li>{{ item.contactNumberType.dictDest }} {{ item.country.cnName }} {{ item.contactNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>身份证明</h3>
          <ul>
            <li>身份证明</li>
            <li>{{ personal.alidentifiable.issuingCountry.cnName }} {{ personal.alidentifiable.idCardType.dictDest }} {{ personal.alidentifiable.idNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>税务居住地</h3>
          <div v-for="(item,index) in personal.personaltaxresidence" :key="'tax-'+index">
            <ul>
              <li>税务居住地{{ index+1 }}</li>
              <li>{{ item.taxResidenceCountry.cnName }} {{ item.taxResidenceIdCardNumber }}
                {{ item.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
          <ul>
            <li>注册号码(选)</li>
            <li v-for="(item,index) in personal.personaltaxresidence" :key="'tax-'+index">{{ item.differentRegistrationNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>雇佣</h3>
          <ul>
            <li>雇佣</li>
            <li>{{ personal.hire.dictDest }}</li>
          </ul>
          <ul>
            <li>雇佣单位名称(选)</li>
            <li>{{ personal.hireUnitName }}</li>
          </ul>
          <ul>
            <li>商业性质(选)</li>
            <li>{{ personal.hireNature.dictDest }}</li>
          </ul>
          <ul>
            <li>职位(选)</li>
            <li>{{ personal.hirePosition }}</li>
          </ul>
          <ul>
            <li>地址(选)</li>
            <li>{{ personal.hireSite }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>账户信息</h3>
          <ul>
            <li>账户类型</li>
            <li>{{ personal.accountInvestmentType.dictDest }}</li>
          </ul>
          <ul>
            <li>基础货币</li>
            <li>{{ personal.accountInvestmentCurrency.dictDest }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>财富来源</h3>
          <ul v-for="(item,index) in personal.sourceOfWealth" :key="'Wealth-'+index">
            <li>财富来源{{ index+1 }}</li>
            <li>{{ item.incomeType.dictDest }} {{ item.percentageOfFunds ? item.percentageOfFunds + '%': '' }} {{ item.sourceDesc }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>收入和资产值</h3>
          <ul>
            <li>年度净收入</li>
            <li>{{ personal.asset.AnualNetIncome + ' USD' }}</li>
          </ul>
          <ul>
            <li>资产净值</li>
            <li>{{ personal.asset.netValue + ' USD' }}</li>
          </ul>
          <ul>
            <li>流动资产净值</li>
            <li>{{ personal.asset.CurrentAssets + ' USD' }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>投资目标及交易意图</h3>
          <ul>
            <li>投资目标</li>
            <li>{{ personal.investmentObjective + '' }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>交易许可与经验</h3>
          <ul>
            <li>交易许可与经验</li>
            <li>
              <div v-for="(item,index) in personal.tradingBusiness" :key="'tradingBusiness-'+index" class="describe">
                <i class="describeOne">
                  {{ item.tansactionType.dictDest + ',' }}
                  <span
                    v-if="item.tansactionType.dictDest==='股票'||item.tansactionType.dictDest==='债券'||item.tansactionType.dictDest==='期权'
                      ||item.tansactionType.dictDest==='期货'||item.tansactionType.dictDest==='期权期货'"
                  >
                    {{ item.tradingExperience.dictDest + ',' }}
                    {{ item.yearTransactionsPerNumber.dictDest + ',' }}
                    {{ item.knowledgeLevel.dictDest }}
                  </span>
                </i>
                <br>
                <div class="describeTwo">
                  <span v-for="(item1,index1) in item.tradingLocation" :key="index1">
                    {{ item1.cnName }}
                  </span>
                </div>
              </div>
            </li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>安全问题</h3>
          <div v-for="(item,index) in personal.safetyProblem" :key="'safetyProblem-'+index">
            <ul>
              <li>安全问题{{ index+1 }}</li>
              <li>{{ item.safeQuestion.dictDest }}</li>
            </ul>
            <ul>
              <li>答案{{ index+1 }}</li>
              <li>{{ item.answer }}</li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!-- 有限公司 -->
    <div v-else-if="userInfo.accountType==='limitedLiablity'" class="messageContent">
      <div class="personalInformation">
        <!-- 公司-信息 -->
        <h3>机构信息</h3>
        <ul>
          <li>有限公司名称</li>
          <li>{{ limitedLiablity.companyName }}</li>
        </ul>
        <ul>
          <li>业务描述</li>
          <li>{{ limitedLiablity.businessDescription }}</li>
        </ul>
        <ul>
          <li>商业名称(选)</li>
          <li>{{ limitedLiablity.tradeName }}</li>
        </ul>
        <ul>
          <li>网址</li>
          <li>{{ limitedLiablity.URL }}</li>
        </ul>
        <ul>
          <li>该组织</li>
          <li>
            <div v-for="(item,index) in limitedLiablity.theOrganization" :key="index" class="describe">
              {{ item.dictDest }}
            </div>
          </li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>主要经营地点</h3>
        <ul>
          <li>主要经营地点</li>
          <li>{{ limitedLiablity.address }}</li>
        </ul>
        <ul>
          <li>邮寄地址(选)</li>
          <li>{{ limitedLiablity.mailingAddress }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>联系信息</h3>
        <ul v-for="(item,index) in limitedLiablity.RelationMessage" :key="'Message-'+index">
          <li>联系信息{{ index+1 }}</li>
          <li>{{ item.contactNumberType.dictDest }} {{ item.country.cnName }} {{ item.contactNumber }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>税务居住地</h3>
        <div v-for="(item,index) in limitedLiablity.personaltaxresidence" :key="'tax-'+index">
          <ul>
            <li>税务居住地{{ index+1 }}</li>
            <li>{{ item.taxResidenceCountry.cnName }} {{ item.taxResidenceIdCardNumber }}
              {{ item.noTaxIdentificationNumber.dictDest }}
            </li>
          </ul>
        </div>
        <ul>
          <li>注册号码(选)</li>
          <li v-for="(item,index) in limitedLiablity.personaltaxresidence" :key="'tax-'+index">{{ item.differentRegistrationNumber }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>账户信息</h3>
        <ul>
          <li>账户信息</li>
          <li>{{ limitedLiablity.accountInvestmentType.dictDest }}</li>
        </ul>
        <ul>
          <li>基础货币</li>
          <li>{{ limitedLiablity.accountInvestmentCurrency.dictDest }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>财富来源</h3>
        <ul v-for="(item,index) in limitedLiablity.sourceOfWealth" :key="'Wealth-'+index">
          <li>财富来源{{ index+1 }}</li>
          <li>{{ item.incomeType.dictDest }} {{ item.percentageOfFunds ? item.percentageOfFunds+'%' : '' }} {{ item.sourceDesc }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>被授权人</h3>
        <!-- 被授权人-信息 -->
        <ul>
          <li>称呼</li>
          <li>{{ limitedLiablity.call.dictDest }}</li>
        </ul>
        <ul>
          <li>姓名</li>
          <li>{{ limitedLiablity.name }}</li>
        </ul>
        <ul>
          <li>出生日期</li>
          <li>{{ limitedLiablity.dateBirth }}</li>
        </ul>
        <ul>
          <li>职位</li>
          <li>{{ limitedLiablity.position }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>居住地址（非办公地址）</h3>
        <ul>
          <li>国家</li>
          <li>{{ limitedLiablity.residentialState }}</li>
        </ul>
        <ul>
          <li>地址</li>
          <li>{{ limitedLiablity.residentialAddress }}</li>
        </ul>
        <ul>
          <li>不同国籍(选)</li>
          <li>{{ limitedLiablity.differentResidentialState }}</li>
        </ul>
        <ul>
          <li>不同出生国(选)</li>
          <li>{{ limitedLiablity.differentResidentialBirthState }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>联系信息</h3>
        <ul>
          <li>邮箱地址</li>
          <li>{{ limitedLiablity.emailAddress }}</li>
        </ul>
        <ul v-for="(item,index) in limitedLiablity.residentialRelationMessage" :key="'residentialRelationMessage-'+index">
          <li>联系信息{{ index+1 }}</li>
          <li>{{ item.contactNumberType.dictDest }} {{ item.country.cnName }} {{ item.contactNumber }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>身份证明</h3>
        <ul>
          <li>签发国家</li>
          <li>{{ limitedLiablity.signState.cnName }}</li>
        </ul>
        <ul>
          <li>身份证类型</li>
          <li>{{ limitedLiablity.identityCardType }}</li>
        </ul>
        <ul>
          <li>证件号码</li>
          <li>{{ limitedLiablity.certificateNumber }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>税务居住地</h3>
        <div v-for="(item,index) in limitedLiablity.residentialTaxresidence" :key="'residentialTaxresidence-'+index">
          <ul>
            <li>税务居住地{{ index+1 }}</li>
            <li>{{ item.taxResidenceCountry.cnName }} {{ item.taxResidenceIdCardNumber }}
              {{ item.noTaxIdentificationNumber.dictDest }}
            </li>
          </ul>
        </div>
        <!-- <ul>
          <li>注册号码(选)</li>
          <li>{{ limitedLiablity.personaltaxresidence.differentRegistrationNumber }}</li>
        </ul> -->
      </div>
      <div class="personalInformation">
        <h3>安全问题</h3>
        <div v-for="(item,index) in limitedLiablity.safetyProblem" :key="'safetyProblem-'+index">
          <ul>
            <li>安全问题{{ index+1 }}</li>
            <li>{{ item.safeQuestion.dictDest }}</li>
          </ul>
          <ul>
            <li>答案{{ index+1 }}</li>
            <li>{{ item.answer }}</li>
          </ul>
        </div>
      </div>
      <div class="personalInformation">
        <h3>税收分类</h3>
        <ul v-if="limitedLiablity.TaxClassificationWay.dictDest">
          <li>被征收美国个人所得税的方式</li>
          <li>{{ limitedLiablity.TaxClassificationWay.dictDest }}</li>
        </ul>
        <ul v-if="limitedLiablity.isTaxClassificationTreatment">
          <li>是否作为混合实体要求美国的税收协定优惠待遇(选)</li>
          <li>{{ limitedLiablity.isTaxClassificationTreatment }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>收入和资产值</h3>
        <!-- 收入和资产  交易许可与经验-->
        <ul>
          <li>年度净收入</li>
          <li>{{ limitedLiablity.asset.AnualNetIncome + ' USD' }}</li>
        </ul>
        <ul>
          <li>资产净值</li>
          <li>{{ limitedLiablity.asset.netValue + ' USD' }}</li>
        </ul>
        <ul>
          <li>流动资产净值</li>
          <li>{{ limitedLiablity.asset.CurrentAssets + ' USD' }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>投资目标及交易意图</h3>
        <ul>
          <li>投资目标</li>
          <li class="investmentItem">{{ limitedLiablity.investmentObjective +'' }}</li>
        </ul>
      </div>
      <div class="personalInformation">
        <h3>交易许可与经验</h3>
        <ul>
          <li>交易许可与经验</li>
          <li>
            <div v-for="(item,index) in limitedLiablity.ExperienceInTrading" :key="'ExperienceInTrading-'+index" class="describe">
              <i class="describeOne">
                {{ item.tansactionType.dictDest + ',' }}
                <span
                  v-if="item.tansactionType.dictDest==='股票'||item.tansactionType.dictDest==='债券'||item.tansactionType.dictDest==='期权'
                    ||item.tansactionType.dictDest==='期货'||item.tansactionType.dictDest==='期权期货'"
                >
                  {{ item.tradeExperience.dictDest + ',' }}
                  {{ item.transactionsTotalNumber + ',' }}
                  {{ item.knowledgeLevel.dictDest }}
                </span>
              </i>
              <i v-if="item.transactionAuthority&&(item.tansactionType.dictDest===&quot;期权&quot;)">受限期权交易</i>
              <br>
              <div class="describeTwo">
                <span v-for="(item1,index1) in item.tradingLocation" :key="index1">
                  {{ item1.cnName }}
                </span>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    <!-- 合伙企业 -->
    <div v-else-if="userInfo.accountType==='partnerShip'">
      <div class="messageContent">
        <div class="personalInformation">
          <h3>机构信息</h3>
          <ul>
            <li>合伙企业名称</li>
            <li>{{ partnerShip.companyName }}</li>
          </ul>
          <ul>
            <li>业务描述</li>
            <li>{{ partnerShip.businessDescription }}</li>
          </ul>
          <ul>
            <li>商业名称(选)</li>
            <li>{{ partnerShip.tradeName }}</li>
          </ul>
          <ul>
            <li>网址</li>
            <li>{{ partnerShip.URL }}</li>
          </ul>
          <ul>
            <li>该组织</li>
            <li>
              <div v-for="(item,index) in partnerShip.theOrganization" :key="index" class="describe">
                {{ item.dictDest }}
              </div>
            </li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>主要经营地点</h3>
          <ul v-if="partnerShip.address">
            <li>主要经营地点</li>
            <li>{{ partnerShip.address }}</li>
          </ul>
          <ul v-if="partnerShip.mailingAddress">
            <li>邮寄地址(选)</li>
            <li>{{ partnerShip.mailingAddress }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>联系信息</h3>
          <ul v-for="(item,index) in partnerShip.RelationMessage" :key="'Message-'+index">
            <li>联系信息{{ index+1 }}</li>
            <li>{{ item.contactNumberType.dictDest }} {{ item.country.cnName }} {{ item.contactNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>税务居住地</h3>
          <div v-for="(item,index) in partnerShip.personaltaxresidence" :key="'tax-'+index">
            <ul>
              <li>税务居住地{{ index+1 }}</li>
              <li>{{ item.taxResidenceCountry.cnName }} {{ item.taxResidenceIdCardNumber }}
                {{ item.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
          <ul>
            <li>注册号码(选)</li>
            <li v-for="(item,index) in partnerShip.personaltaxresidence" :key="'tax-'+index">{{ item.differentRegistrationNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>账户信息</h3>
          <ul>
            <li>账户信息</li>
            <li>{{ partnerShip.accountInvestmentType.dictDest }}</li>
          </ul>
          <ul>
            <li>基础货币</li>
            <li>{{ partnerShip.accountInvestmentCurrency.dictDest }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>财富来源</h3>
          <ul v-for="(item,index) in partnerShip.sourceOfWealth" :key="'Wealth-'+index">
            <li>财富来源{{ index+1 }}</li>
            <li>{{ item.incomeType.dictDest }} {{ item.percentageOfFunds ? item.percentageOfFunds+'%' : '' }} {{ item.sourceDesc }}</li>
          </ul>
        </div>
        <!-- 被授权人-信息 -->
        <div class="personalInformation">
          <h3>被授权人</h3>
          <ul>
            <li>称呼</li>
            <li>{{ partnerShip.call.dictDest }}</li>
          </ul>
          <ul>
            <li>姓名</li>
            <li>{{ partnerShip.name }}</li>
          </ul>
          <ul>
            <li>出生日期</li>
            <li>{{ partnerShip.dateBirth }}</li>
          </ul>
          <ul>
            <li>职位</li>
            <li>{{ partnerShip.position }}</li>
          </ul>
        </div>

        <div class="personalInformation">
          <h3>居住地址（非办公地址)</h3>
          <ul>
            <li>国家</li>
            <li>{{ partnerShip.residentialState }}</li>
          </ul>
          <ul>
            <li>地址</li>
            <li>{{ partnerShip.residentialAddress }}</li>
          </ul>
          <ul v-if="partnerShip.differentResidentialState">
            <li>不同国籍(选)</li>
            <li>{{ partnerShip.differentResidentialState }}</li>
          </ul>
          <ul v-if="partnerShip.differentResidentialBirthState">
            <li>不同出生国(选)</li>
            <li>{{ partnerShip.differentResidentialBirthState }}</li>
          </ul>
          <ul>
            <li>邮箱地址</li>
            <li>{{ partnerShip.emailAddress }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>联系信息</h3>
          <ul v-for="(item,index) in partnerShip.residentialRelationMessage" :key="'residentialRelationMessage-'+index">
            <li>联系信息{{ index+1 }}</li>
            <li>{{ item.contactNumberType.dictDest }} {{ item.country.cnName }} {{ item.contactNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>身份证明</h3>
          <ul>
            <li>签发国家</li>
            <li>{{ partnerShip.signState.cnName }}</li>
          </ul>
          <ul>
            <li>身份证类型</li>
            <li>{{ partnerShip.identityCardType }}</li>
          </ul>
          <ul>
            <li>证件号码</li>
            <li>{{ partnerShip.certificateNumber }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>税务居住地</h3>
          <div v-for="(item,index) in partnerShip.residentialTaxresidence" :key="'residentialTaxresidence-'+index">
            <ul>
              <li>税务居住地{{ index+1 }}</li>
              <li>{{ item.taxResidenceCountry.cnName }} {{ item.taxResidenceIdCardNumber }}
                {{ item.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
          <!-- <ul>
            <li>注册号码(选)</li>
            <li>{{ partnerShip.personaltaxresidence.differentRegistrationNumber }}</li>
          </ul> -->
        </div>
        <div class="personalInformation">
          <h3>安全问题</h3>
          <div v-for="(item,index) in partnerShip.safetyProblem" :key="'safetyProblem-'+index">
            <ul>
              <li>安全问题{{ index+1 }}</li>
              <li>{{ item.safeQuestion.dictDest }}</li>
            </ul>
            <ul v-if="item.answer">
              <li>答案{{ index+1 }}</li>
              <li>{{ item.answer }}</li>
            </ul>
          </div>
        </div>
        <div class="personalInformation">
          <h3>税收分类</h3>
          <ul>
            <li>被征收美国个人所得税的方式</li>
            <li>{{ partnerShip.TaxClassificationWay.dictDest }}</li>
          </ul>
          <ul v-if="partnerShip.isTaxClassificationTreatment">
            <li>是否作为混合实体要求美国的税收协定优惠待遇(选)</li>
            <li>{{ partnerShip.isTaxClassificationTreatment }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>收入和资产值</h3>
          <!-- 收入和资产  交易许可与经验-->
          <ul>
            <li>年度净收入</li>
            <li>{{ partnerShip.asset.AnualNetIncome + ' USD' }}</li>
          </ul>
          <ul>
            <li>资产净值</li>
            <li>{{ partnerShip.asset.netValue + ' USD' }}</li>
          </ul>
          <ul>
            <li>流动资产净值</li>
            <li>{{ partnerShip.asset.CurrentAssets + ' USD' }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>投资目标及交易意图</h3>
          <ul>
            <li>投资目标</li>
            <li>{{ partnerShip.investmentObjective+'' }}</li>
          </ul>
        </div>
        <div class="personalInformation">
          <h3>交易许可与经验</h3>
          <ul>
            <li>交易许可与经验</li>
            <li>
              <div v-for="(item,index) in partnerShip.ExperienceInTrading" :key="'ExperienceInTrading-'+index" class="describe">
                <i class="describeOne">
                  {{ item.tansactionType.dictDest + ',' }}
                  <span
                    v-if="item.tansactionType.dictDest==='股票'||item.tansactionType.dictDest==='债券'||item.tansactionType.dictDest==='期权'
                      ||item.tansactionType.dictDest==='期货'||item.tansactionType.dictDest==='期权期货'"
                  >
                    {{ item.tradeExperience.dictDest + ',' }}
                    {{ item.transactionsTotalNumber + ',' }}
                    {{ item.knowledgeLevel.dictDest }}
                  </span>
                </i>
                <i v-if="item.transactionAuthority&&(item.tansactionType.dictDest===&quot;期权&quot;)">受限期权交易</i>
                <br>
                <div class="describeTwo">
                  <span v-for="(item1,index1) in item.tradingLocation" :key="index1">
                    {{ item1.cnName }}
                  </span>
                </div>
              </div>
            </li>
          </ul>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Message',
  props: {
    userInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      personal: {// 个人
        call: '',
        name: '',
        dateBirth: '',
        sex: '',
        maritalStatus: '',
        familyNumber: '',
        address: '',
        mailingAddress: '',
        differentState: '',
        differentBirthState: '',
        RelationMessage: [],
        alidentifiable: {
          issuingCountry: '',
          idCardType: {
            dictDest: ''
          },
          idNumber: ''
        },
        personaltaxresidence: [],
        hire: '',
        hireUnitName: '',
        hireNature: '',
        hirePosition: '',
        hireSite: '',
        accountInvestmentType: '',
        accountInvestmentCurrency: '',
        sourceOfWealth: [],
        asset: {
          AnualNetIncome: '',
          netValue: '',
          CurrentAssets: ''
        },
        investmentObjective: [],
        tradingBusiness: [],
        safetyProblem: []
      },
      limitedLiablity: {// 有限公司
        companyName: '',
        businessDescription: '',
        tradeName: '',
        URL: '',
        theOrganization: '',
        address: '',
        mailingAddress: '',
        RelationMessage: [],
        personaltaxresidence: [],
        accountInvestmentType: '',
        accountInvestmentCurrency: '',
        sourceOfWealth: '',
        // 被授权人信息
        call: '',
        name: '',
        dateBirth: '',
        position: '',
        residentialState: '',
        residentialAddress: '',
        differentResidentialState: '',
        differentResidentialBirthState: '',
        emailAddress: '',
        residentialRelationMessage: [],
        signState: '',
        identityCardType: '',
        certificateNumber: '',
        residentialTaxresidence: [],
        safetyProblem: [],
        TaxClassificationWay: '',
        isTaxClassificationTreatment: '',
        // 收入与资产 投资目标及交易经验
        asset: {
          AnualNetIncome: '',
          netValue: '',
          CurrentAssets: ''
        },
        investmentObjective: [],
        ExperienceInTrading: []
      },
      partnerShip: {// 合伙
        // 公司信息
        companyName: '',
        businessDescription: '',
        tradeName: '',
        URL: '',
        theOrganization: '',
        address: '',
        mailingAddress: '',
        RelationMessage: [],
        personaltaxresidence: [],
        accountInvestmentType: '',
        accountInvestmentCurrency: '',
        sourceOfWealth: '',
        // 被授权人信息
        call: '',
        name: '',
        dateBirth: '',
        position: '',
        residentialState: '',
        residentialAddress: '',
        differentResidentialState: '',
        differentResidentialBirthState: '',
        emailAddress: '',
        residentialRelationMessage: [],
        signState: '',
        identityCardType: '',
        certificateNumber: '',
        residentialTaxresidence: [],
        safetyProblem: [],
        TaxClassificationWay: '',
        isTaxClassificationTreatment: '',
        // 收入与资产 投资目标及交易经验
        asset: {
          AnualNetIncome: '',
          netValue: '',
          CurrentAssets: ''
        },
        investmentObjective: [],
        ExperienceInTrading: []
      }
    };
  },
  watch: {
    userInfo: { // props
      handler(val, oldVal) {
        this.userInfo = val;
        this.Port();
      },
      deep: true
    }
  },
  created() {
    this.Port();
  },
  mounted() {

  },
  methods: {
    // personal
    // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')}
    // 请求接口后缀  G 个人 Y 有限 H 合伙
    Port() {
      for (var key in this.personal) {
        if (this.personal[key] instanceof Array) {
          this.personal[key] = [];
        } else if (this.personal[key] instanceof Object) {
          for (var key1 in this.personal[key]) {
            this.personal[key][key1] = '';
          }
        } else {
          this.personal[key] = '';
        }
      }
      for (var key2 in this.limitedLiablity) {
        if (this.limitedLiablity[key2] instanceof Array) {
          this.limitedLiablity[key2] = [];
        } else if (this.limitedLiablity[key2] instanceof Object) {
          for (var key3 in this.limitedLiablity[key2]) {
            this.limitedLiablity[key2][key3] = '';
          }
        } else {
          this.limitedLiablity[key2] = '';
        }
      }
      for (var key4 in this.partnerShip) {
        if (this.partnerShip[key4] instanceof Array) {
          this.partnerShip[key4] = [];
        } else if (this.partnerShip[key4] instanceof Object) {
          for (var key5 in this.partnerShip[key4]) {
            this.partnerShip[key4][key5] = '';
          }
        } else {
          this.partnerShip[key4] = '';
        }
      }
      if (this.userInfo.accountType === 'personal') {
        this.personInformationG();
        this.personAddressG();
        this.personRelationG();
        this.personalidentifiableG();
        this.personaltaxresidenceG();
        this.personalemployG();
        this.personalaccountportfolioG();
        this.personalatreasuredsourcesG();
        this.personaPerAssetG();
        this.perInvestTargetsG();
        this.personalsafetyG();
        this.personalPerTradeExpG();
      } else if (this.userInfo.accountType === 'limitedLiablity') {
        this.accountCoInstitutionalY();
        this.accountCoAddressY();
        this.accountCoContactY();
        this.accountCoTaxesY();
        this.accountCoAccountPortfolioY();
        this.accountCoWealthSourceY();
        this.authorizedPersonY();
        this.accountCoSafeQuestionY();
        this.accountCoTaxClassificationY();
        this.accountCoAssetY();
        this.accountCoInvestmentTargetY();
        this.accountCoTradeExperienceY();
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.accountCoInstitutionalH();
        this.accountCoAddressH();
        this.accountCoContactH();
        this.accountCoTaxesH();
        this.accountCoAccountPortfolioH();
        this.accountCoWealthSourceH();
        this.authorizedPersonH();
        this.accountCoSafeQuestionH();
        this.accountCoTaxClassificationH();
        this.accountCoAssetH();
        this.accountCoInvestmentTargetH();
        this.accountCoTradeExperienceH();
      }
    },
    personInformationG() { // 个人信息
      this.$http({
        url: '/account/registerOpen/personal/findPerInfo',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            this.personal.call = res.object[0].appellation.dictDest;
            this.personal.name = res.object[0].lastName + ' ' + res.object[0].firstName;
            // this.personal.dateBirth = this.$verify.NowDate2(Number(res.object[0].birthday));
            this.personal.dateBirth = res.object[0].birthday;
            this.personal.sex = res.object[0].gender.dictDest;
            this.personal.maritalStatus = res.object[0].maritalStatus.dictDest;
            this.personal.familyNumber = res.object[0].numberOfFamilyMembers.dictDest;
          }
        } else {
          this.$message.error('' + res.code + '');
        }
      }).catch(error => {
        this.$message.error('' + error + '');
      });
    },
    personAddressG() { // 个人地址
      this.$http({
        url: '/account/registerOpen/personal/findAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      }).then(res => {
        if (res.code === 200) {
          if (this.$verify.resStatus(res)) {
            let site = '';
            for (let i = 0; i < res.object.length; i++) {
              site = '';
              if (res.object[i].addressType === 'personalAddress') {
                if (res.object[i].address.indexOf('-#-') !== -1) {
                  const siteTS = res.object[i].address.split('-#-');
                  site = siteTS[0] + ' ' + siteTS[1];
                } else {
                  site = res.object[i].address;
                }
                this.personal.address = `
                  ${res.object[i].country.cnName}
                  ${res.object[i].rovince}
                  ${res.object[i].city}
                  ${site}
                  ${res.object[i].zipCode}
                `;
              } else if (res.object[i].addressType === 'mail') {
                if (res.object[i].address.indexOf('-#-') !== -1) {
                  const siteTS = res.object[i].address.split('-#-');
                  site = siteTS[0] + ' ' + siteTS[1];
                } else {
                  site = res.object[i].address;
                }
                this.personal.mailingAddress = `
                  ${res.object[i].country.cnName}
                  ${res.object[i].rovince}
                  ${res.object[i].city}
                  ${site}
                  ${res.object[i].zipCode}
                `;
              } else if (res.object[i].addressType === 'employmentAddress') {
                if (res.object[i].address.indexOf('-#-') !== -1) {
                  const siteTS = res.object[i].address.split('-#-');
                  site = siteTS[0] + ' ' + siteTS[1];
                } else {
                  site = res.object[i].address;
                }
                this.personal.hireSite = `
                  ${res.object[i].country.cnName}
                  ${res.object[i].rovince}
                  ${res.object[i].city}
                  ${site}
                  ${res.object[i].zipCode}
                `;
              }
            }
            this.personal.differentState = res.object[0].otherCountry; // 不同国籍
            this.personal.differentBirthState = res.object[0].otherBirthCountry; // 不同出生国
          }
        } else {
          this.$message.error('' + res.code + '');
        }
      }).catch(error => {
        this.$message.error('' + error + '');
      });
    },
    personRelationG() { // 个人联系信息
      this.$http({
        url: '/account/registerOpen/personal/findContact',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.RelationMessage = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personalidentifiableG() { // 获取个人身份证明信息
      this.$http({
        url: '/account/registerOpen/personal/findIdentity',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.alidentifiable = res.object[0];
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personaltaxresidenceG() { // 获取个人税务居住地信息
      this.$http({
        url: '/account/registerOpen/personal/findTaxes',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.personaltaxresidence = JSON.parse(JSON.stringify(res.object));
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personalemployG() { // 获取个人雇佣信息
      this.$http({
        url: '/account/registerOpen/personal/findEmployment',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (
                res.object[0].employmentStatus.dictValue === 'beEmployed' ||
                res.object[0].employmentStatus.dictValue === 'toEntrepreneurship'
              ) {
                this.personal.hire = res.object[0].employmentStatus;
                this.personal.hireUnitName = res.object[0].companyName;
                this.personal.hireNature = res.object[0].commercialNature;
                this.personal.hirePosition = res.object[0].position.dictDest;
              } else {
                this.personal.hire = res.object[0].employmentStatus;
              }
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personalaccountportfolioG() { // 获取个人账户投资组合信息
      this.$http({
        url: '/account/registerOpen/personal/findPortfolio',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.accountInvestmentType = res.object[0].accountPortfolioType;
              this.personal.accountInvestmentCurrency = res.object[0].baseCurrency;
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personalatreasuredsourcesG() { // 获取个人财富来源
      this.$http({
        url: '/account/registerOpen/personal/findWealthSource',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.sourceOfWealth = res.object;
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personaPerAssetG() { // 收入和资产值
      this.$http({
        url: '/account/registerOpen/personal/findAsset',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.asset.AnualNetIncome = res.object[0].anualNetIncome;
              this.personal.asset.netValue = res.object[0].netAssetValue;
              this.personal.asset.CurrentAssets = res.object[0].netCurrentAssets;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    perInvestTargetsG() { // 个人投资目标信息
      this.$http({
        url: '/account/registerOpen/personal/findInvestTarget',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.investmentObjective = [];
              for (let i = 0; i < res.object[0].investmentTarget.length; i++) {
                this.personal.investmentObjective.push(res.object[0].investmentTarget[i].dictDest);
              }
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personalsafetyG() { // 个人安全问题
      this.$http({
        url: '/account/registerOpen/personal/findSafeQuestion',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.safetyProblem = res.object;
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    personalPerTradeExpG() { // 个人交易经验信息
      this.$http({
        url: '/account/registerOpen/personal/findTradeExp',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.personal.tradingBusiness = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoInstitutionalY() { // 公司机构信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutional',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.companyName = res.object[0].institutionalName;
              this.limitedLiablity.businessDescription = res.object[0].bsinessDescription;
              this.limitedLiablity.tradeName = res.object[0].businessName;
              this.limitedLiablity.URL = res.object[0].websiteUrl;
              this.limitedLiablity.theOrganization = res.object[0].istitutionalNature; // 未解决
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoAddressY() { // 公司地址
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              let site = '';
              for (let i = 0; i < res.object.length; i++) {
                site = '';
                if (res.object[i].addressType === 'personalAddress') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const siteTS = res.object[i].address.split('-#-');
                    site = siteTS[0] + ' ' + siteTS[1];
                  } else {
                    site = res.object[i].address;
                  }
                  this.limitedLiablity.address = `
                    ${res.object[i].country.cnName}
                    ${res.object[i].rovince}
                    ${res.object[i].city}
                    ${site}
                    ${res.object[i].zipCode}
                  `;
                } else if (res.object[i].addressType === 'mail') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const siteTS = res.object[i].address.split('-#-');
                    site = siteTS[0] + ' ' + siteTS[1];
                  } else {
                    site = res.object[i].address;
                  }
                  this.limitedLiablity.mailingAddress = `
                    ${res.object[i].country.cnName}
                    ${res.object[i].rovince}
                    ${res.object[i].city}
                    ${site}
                    ${res.object[i].zipCode}
                  `;
                }
              }
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoContactY() { // 联系信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInstitutionalContact',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.RelationMessage = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoTaxesY() { // 税务居住地
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findTaxes',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.personaltaxresidence = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoAccountPortfolioY() { // 账户信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findPortfolio',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.accountInvestmentType = res.object[0].accountPortfolioType;
              this.limitedLiablity.accountInvestmentCurrency = res.object[0].baseCurrency;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoWealthSourceY() { // 财富来源
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findWealthSource',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.sourceOfWealth = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    authorizedPersonY() { // 被授权人
      this.companyPersonageMessageCommonY('authorizedPerson').then(res => {
        // 个人信息
        this.limitedLiablity.call = res.object[0].accountCoPersonal.appellation;
        this.limitedLiablity.name = `
          ${res.object[0].accountCoPersonal.lastName}
          ${res.object[0].accountCoPersonal.middleName}
          ${res.object[0].accountCoPersonal.firstName}
          ${res.object[0].accountCoPersonal.suffix}
        `;
        // this.limitedLiablity.dateBirth = this.$verify.NowDate2(Number(res.object[0].accountCoPersonal.birthday));
        this.limitedLiablity.dateBirth = res.object[0].accountCoPersonal.birthday;
        if (res.object[0].accountCoPersonal.companyPosition) {
          const toucun = res.object[0].accountCoPersonal.companyPosition;
          for (let i = 0; i < toucun.length; i++) {
            if (toucun[i].dictDest.indexOf(',') !== -1) {
              var index = toucun[i].dictDest.lastIndexOf('\,');
              toucun[i].dictDest = toucun[i].dictDest.substring(index + 1, toucun[i].dictDest.length);
            }
            if (toucun[i].dictDest === 'dongshi') {
              this.limitedLiablity.position = '董事';
            } else if (toucun[i].dictDest === 'mishu') {
              this.limitedLiablity.position = '秘书';
            } else {
              this.limitedLiablity.position = '其他官员' + ',' + toucun[i].dictDest;
            }
          }
        }
        this.limitedLiablity.emailAddress = res.object[0].accountCoPersonal.email;
        // 居住地址
        this.limitedLiablity.residentialState = res.object[0].accountCoAddressList[0].country.cnName;
        let siteTS = '';
        if (res.object[0].accountCoAddressList[0].address.indexOf('-#-') !== -1) {
          siteTS = res.object[0].accountCoAddressList[0].address.split('-#-');
          siteTS = `
            ${siteTS[0]}
            ${siteTS[1]}
          `;
        } else {
          siteTS = res.object[0].accountCoAddressList[0].address;
        }
        this.limitedLiablity.residentialAddress = `
          ${res.object[0].accountCoAddressList[0].rovince}
          ${res.object[0].accountCoAddressList[0].city}
          ${siteTS}
          ${res.object[0].accountCoAddressList[0].zipCode}
        `;
        this.limitedLiablity.differentResidentialState = res.object[0].accountCoAddressList[0].otherCountry.cnName;
        this.limitedLiablity.differentResidentialBirthState = res.object[0].accountCoAddressList[0].otherBirthCountry.cnName;
        // 联系信息
        this.limitedLiablity.residentialRelationMessage = res.object[0].accountCoContactList;
        // 公司身份信息
        this.limitedLiablity.signState = res.object[0].accountCoIdentityList[0].issuingCountry;
        this.limitedLiablity.identityCardType = res.object[0].accountCoIdentityList[0].idCardType.dictDest;
        this.limitedLiablity.certificateNumber = res.object[0].accountCoIdentityList[0].idNumber;
        // 税务居住地
        this.limitedLiablity.residentialTaxresidence = res.object[0].accountCoTaxesList;
      });
    },
    accountCoSafeQuestionY() { // 公司安全问题
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findSafeQuestion',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.safetyProblem = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    companyPersonageMessageCommonY(personalType) { // 公司个人信息 公共
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/account/registerOpen/limitedLiablity/findPerInfo',
          method: 'get',
          params: { accountId: this.userInfo.id, personalType, lang: this.$Cookies.get('locale') }
        })
          .then(res => {
            if (res.code === 200) {
              if (this.$verify.resStatus(res)) {
                resolve(res);
              }
            } else {
              this.$message.error('' + res.code + '');
            }
          })
          .catch(error => {
            this.$message.error('' + error + '');
          });
      });
    },
    accountCoTaxClassificationY() { // 获取公司税收分类
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findTaxClassification',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.TaxClassificationWay = res.object[0].taxType;
              // this.limitedLiablity.isTaxClassificationTreatment = res.object[0].isPreferentia;
              if (res.object[0].isPreferentia === true) {
                this.limitedLiablity.isTaxClassificationTreatment = '是';
              } else if (res.object[0].isPreferentia === false) {
                this.limitedLiablity.isTaxClassificationTreatment = '否';
              } else {
                this.limitedLiablity.isTaxClassificationTreatment = '';
              }
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoAssetY() { // 收入和资产值
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findAsset',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.asset.AnualNetIncome = res.object[0].anualNetIncome;
              this.limitedLiablity.asset.netValue = res.object[0].netAssetValue;
              this.limitedLiablity.asset.CurrentAssets = res.object[0].netCurrentAssets;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoInvestmentTargetY() { // 公司投资目标信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findInvestmentTarget',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.investmentObjective = [];
              for (let i = 0; i < res.object[0].investmentTarget.length; i++) {
                this.limitedLiablity.investmentObjective.push(
                  res.object[0].investmentTarget[i].dictDest
                );
              }
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoTradeExperienceY() { // 公司交易经验信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findTradeExperience',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.limitedLiablity.ExperienceInTrading = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoInstitutionalH() { // 公司机构信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutional',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.companyName = res.object[0].institutionalName;
              this.partnerShip.businessDescription = res.object[0].bsinessDescription;
              this.partnerShip.tradeName = res.object[0].businessName;
              this.partnerShip.URL = res.object[0].websiteUrl;
              this.partnerShip.theOrganization = res.object[0].istitutionalNature;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoAddressH() { // 公司地址
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalAddress',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              let site = '';
              for (let i = 0; i < res.object.length; i++) {
                site = '';
                if (res.object[i].addressType === 'personalAddress') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const siteTS = res.object[i].address.split('-#-');
                    site = siteTS[0] + ' ' + siteTS[1];
                  } else {
                    site = res.object[i].address;
                  }
                  this.partnerShip.address = `
                    ${res.object[i].country.cnName}
                    ${res.object[i].rovince}
                    ${res.object[i].city}
                    ${site}
                    ${res.object[i].zipCode}
                  `;
                } else if (res.object[i].addressType === 'mail') {
                  if (res.object[i].address.indexOf('-#-') !== -1) {
                    const siteTS = res.object[i].address.split('-#-');
                    site = siteTS[0] + ' ' + siteTS[1];
                  } else {
                    site = res.object[i].address;
                  }
                  this.partnerShip.mailingAddress = `
                    ${res.object[i].country.cnName}
                    ${res.object[i].rovince}
                    ${res.object[i].city}
                    ${site}
                    ${res.object[i].zipCode}
                  `;
                }
              }
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoContactH() { // 联系信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalContact',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.RelationMessage = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoTaxesH() { // 税务居住地
      this.$http({
        url: '/account/registerOpen/partnerShip/findInstitutionalTaxes',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.personaltaxresidence = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoAccountPortfolioH() { // 账户信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findAccountPortfolio',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.accountInvestmentType = res.object[0].accountPortfolioType;
              this.partnerShip.accountInvestmentCurrency = res.object[0].baseCurrency;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoWealthSourceH() { // 财富来源
      this.$http({
        url: '/account/registerOpen/partnerShip/findWealthSource',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.sourceOfWealth = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    authorizedPersonH() { // 被授权人
      this.companyPersonageMessageCommonH('authorizedPerson').then(res => {
        // // 个人信息
        this.partnerShip.call = res.object[0].accountParPersonal.appellation;
        this.partnerShip.name = `
          ${res.object[0].accountParPersonal.lastName}
          ${res.object[0].accountParPersonal.middleName}
          ${res.object[0].accountParPersonal.firstName}
          ${res.object[0].accountParPersonal.suffix}
        `;
        // this.partnerShip.dateBirth = this.$verify.NowDate2(Number(res.object[0].accountParPersonal.birthday));
        this.partnerShip.dateBirth = res.object[0].accountParPersonal.birthday;
        if (res.object[0].accountParPersonal.companyPosition) {
          const toucun = res.object[0].accountParPersonal.companyPosition;
          for (let i = 0; i < toucun.length; i++) {
            if (toucun[i].dictDest.indexOf(',') !== -1) {
              var index = toucun[i].dictDest.lastIndexOf('\,');
              toucun[i].dictDest = toucun[i].dictDest.substring(index + 1, toucun[i].dictDest.length);
            }
            if (toucun[i].dictDest === 'dongshi') {
              this.partnerShip.position = '董事';
            } else if (toucun[i].dictDest === 'mishu') {
              this.partnerShip.position = '秘书';
            } else {
              this.partnerShip.position = '其他官员' + ',' + toucun[i].dictDest;
            }
          }
        }
        this.partnerShip.emailAddress = res.object[0].accountParPersonal.email;
        // // 居住地址
        this.partnerShip.residentialState = res.object[0].accountParAddressList[0].country.cnName;
        let siteTS = '';
        if (res.object[0].accountParAddressList[0].address.indexOf('-#-') !== -1) {
          siteTS = res.object[0].accountParAddressList[0].address.split('-#-');
          siteTS = `
            ${siteTS[0]}
            ${siteTS[1]}
          `;
        } else {
          siteTS = res.object[0].accountParAddressList[0].address;
        }
        this.partnerShip.residentialAddress = `
          ${res.object[0].accountParAddressList[0].rovince}
          ${res.object[0].accountParAddressList[0].city}
          ${siteTS}
          ${res.object[0].accountParAddressList[0].zipCode}
        `;
        this.partnerShip.differentResidentialState = res.object[0].accountParAddressList[0].otherCountry.cnName;
        this.partnerShip.differentResidentialBirthState = res.object[0].accountParAddressList[0].otherBirthCountry.cnName;
        // // 联系信息
        this.partnerShip.residentialRelationMessage = res.object[0].accountParContactList;
        // // 公司身份信息
        this.partnerShip.signState = res.object[0].accountParIdentityList[0].issuingCountry;
        this.partnerShip.identityCardType = res.object[0].accountParIdentityList[0].idCardType.dictDest;
        this.partnerShip.certificateNumber = res.object[0].accountParIdentityList[0].idNumber;
        // // 税务居住地
        this.partnerShip.residentialTaxresidence = res.object[0].accountParTaxesList;
      });
    },
    accountCoSafeQuestionH() { // 公司安全问题
      this.$http({
        url: '/account/registerOpen/partnerShip/findSafeQuestion',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.safetyProblem = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    companyPersonageMessageCommonH(personalType) { // 公司个人信息 公共
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/account/registerOpen/partnerShip/findPerInfo',
          method: 'get',
          params: { accountId: this.userInfo.id, personalType, lang: this.$Cookies.get('locale') }
        })
          .then(res => {
            if (res.code === 200) {
              if (this.$verify.resStatus(res)) {
                resolve(res);
              }
            } else {
              this.$message.error('' + res.code + '');
            }
          })
          .catch(error => {
            this.$message.error('' + error + '');
          });
      });
    },
    accountCoTaxClassificationH() { // 获取公司税收分类
      this.$http({
        url: '/account/registerOpen/partnerShip/findTaxClassification',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.TaxClassificationWay = res.object[0].taxType;
              if (res.object[0].isPreferentia === true) {
                this.partnerShip.isTaxClassificationTreatment = '是';
              } else if (res.object[0].isPreferentia === false) {
                this.partnerShip.isTaxClassificationTreatment = '否';
              } else {
                this.partnerShip.isTaxClassificationTreatment = '';
              }
              // this.partnerShip.isTaxClassificationTreatment = res.object[0].isPreferentia;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoAssetH() { // 收入和资产值
      this.$http({
        url: '/account/registerOpen/partnerShip/findAsset',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.asset.AnualNetIncome = res.object[0].anualNetIncome;
              this.partnerShip.asset.netValue = res.object[0].netAssetValue;
              this.partnerShip.asset.CurrentAssets = res.object[0].netCurrentAssets;
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoInvestmentTargetH() { // 公司投资目标信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findInvestmentTarget',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.investmentObjective = [];
              for (let i = 0; i < res.object[0].investmentTarget.length; i++) {
                this.partnerShip.investmentObjective.push(
                  res.object[0].investmentTarget[i].dictDest
                );
              }
            }
          } else {
            this.$message.error('' + res.code + '');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountCoTradeExperienceH() { // 公司交易经验信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findTradeExperience',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnerShip.ExperienceInTrading = res.object;
            }
          } else {
            this.$message.error('' + res.code + '');
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
  .messageContent {
    .investmentItem{
      margin-right: 20px;
    }
    .personalInformation{
      border: 1px solid #e6e6e6;
      margin-bottom: 20px;
      border-radius: 4px;
      padding-bottom: 10px;
      padding-left: 20px;
      padding-right: 20px;
      margin-right: 20px;
    }
    ul {
      overflow:hidden;
      margin-bottom: 0px;
      padding-left: 0px;
      margin-top: 0;
      padding: 10px 0;
      &:hover{
        background: rgb(246, 248, 249);
      }
      // margin-top: 26px;
      li {
        float: left;
        // display: inline-block;
        list-style: none;
        color: #999;
        div.describe {
          i.describeOne {
            font-style:normal
          }
          span.describeTwo {
            display: block;
            margin-top: 10px;
          }
          div.describeTwo {
            margin-top: 10px;
          }
        }
        div.describe:nth-child(n+2) {
          margin-top: 20px;
        }
      }
      li:first-child {
        width: 20%;
        margin-right: 20px;
        overflow: hidden;
      }
      li:nth-child(n+2){
        width: 70%;
        word-wrap:break-word;
      }
    }
  }

</style>
