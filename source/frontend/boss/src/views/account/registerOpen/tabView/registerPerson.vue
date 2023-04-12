<template>
  <div>
    <!-- {{this.userInfo.accountType}} -->
    <div v-if="userInfo.accountType==='limitedLiablity'" class="registerPersonContent">
      <div v-for="(item,index) in limitedLiablity.shareholder" :key="index">
        <div v-if="item.personageType==='individualShareholder'" class="individual">
          <div class="personalInformation">
            <h3>{{ item.personalType }}</h3>
            <ul>
              <li>类型1</li>
              <li>{{ item.personalType }}</li>
            </ul>
            <ul v-if="item.personalTypeTwo">
              <li>类型2</li>
              <li>{{ item.personalTypeTwo }}</li>
            </ul>
            <ul>
              <li>称呼</li>
              <li>{{ item.call }}</li>
            </ul>
            <ul>
              <li>姓名</li>
              <li>{{ item.name }}</li>
            </ul>
            <ul>
              <li>用户名</li>
              <li>{{ item.userName }}</li>
            </ul>
            <ul>
              <li>电邮地址</li>
              <li>{{ item.emailAddress }}</li>
            </ul>
            <ul>
              <li>所有权百分比</li>
              <li>{{ item.PercentageOfOwnership + '%' }}</li>
            </ul>
            <ul>
              <li>出生日期</li>
              <li>{{ item.dateBirth }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>居住地址（非办公地址）</h3>
            <ul>
              <li>居住国家</li>
              <li>{{ item.residentialState }}</li>
            </ul>
            <ul>
              <li>地址</li>
              <li>{{ item.residentialAddress }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>联系信息</h3>
            <ul v-for="(item1,index1) in item.residentialRelationMessage" :key="'residentialRelationMessage-'+index1">
              <li>联系信息{{ index1+1 }}</li>
              <li>{{ item1.contactNumberType.dictDest }} {{ item1.country.cnName }} {{ item1.contactNumber }}</li>
            </ul>
            <ul>
              <li>不同国籍(选)</li>
              <li>{{ item.differentResidentialState.cnName }}</li>
            </ul>
            <ul>
              <li>不同出生国(选)</li>
              <li>{{ item.differentResidentialBirthState.cnName }}</li>
            </ul>
            <ul>
              <li>美国纳税人</li>
              <li>{{ item.AmericanTaxpayer ? '是' : '否' }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>身份证明</h3>
            <ul>
              <li>签发国家</li>
              <li>{{ item.signState.cnName }}</li>
            </ul>
            <ul>
              <li>身份证类型</li>
              <li>{{ item.identityCardType.dictDest }}</li>
            </ul>
            <ul>
              <li>证件号码</li>
              <li>{{ item.certificateNumber }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul v-for="(item1,index1) in item.residentialTaxresidence" :key="'residentialTaxresidence-'+index1">
              <li>税务居住地{{ index+1 }}</li>
              <li>{{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
                {{ item1.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
        </div>
        <div v-if="item.personageType==='legalPersonShareholder'" class="individual">
          <div class="personalInformation">
            <h3>{{ item.personalType }}</h3>
            <ul>
              <li>类型1</li>
              <li>{{ item.personalType }}</li>
            </ul>
            <ul v-if="item.personalTypeTwo">
              <li>类型2</li>
              <li>{{ item.personalTypeTwo }}</li>
            </ul>
            <ul>
              <li>实体名称</li>
              <li>{{ item.theEntityName }}</li>
            </ul>
            <ul>
              <li>所有权百分比</li>
              <li>{{ item.PercentageOfOwnership + '%' }}</li>
            </ul>
          </div>
          <!-- 实体名称 所有权百分比已放在签署方信息里面 -->
          <div class="personalInformation">
            <h3>签署方信息</h3>
            <ul>
              <li>称呼</li>
              <li>{{ item.call }}</li>
            </ul>
            <ul>
              <li>姓名</li>
              <li>{{ item.name }}</li>
            </ul>
            <ul>
              <li>用户名</li>
              <li>{{ item.userName }}</li>
            </ul>
            <ul>
              <li>电邮地址</li>
              <li>{{ item.emailAddress }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>主要经营地点</h3>
            <ul>
              <li>国家</li>
              <li>{{ item.residentialState }}</li>
            </ul>
            <ul>
              <li>地址</li>
              <li>{{ item.residentialAddress }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>联系信息</h3>
            <ul v-for="(item1,index1) in item.residentialRelationMessage" :key="'residentialRelationMessage-'+index1">
              <li>联系信息{{ index1+1 }}</li>
              <li>{{ item1.contactNumberType.dictDest }} {{ item1.country.cnName }} {{ item1.contactNumber }}</li>
            </ul>
            <ul>
              <li>成立国家</li>
              <li>{{ item.setUpState.cnName }}</li>
            </ul>
            <ul>
              <li>是否实体是否在监管交易所公开上市交易？</li>
              <li>{{ item.whetherPubliclyTraded }}</li>
            </ul>
            <ul>
              <li>交易所(选)</li>
              <li>{{ item.exchange }}</li>
            </ul>
            <ul>
              <li>代码(选)</li>
              <li>{{ item.code }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul v-for="(item1,index1) in item.residentialTaxresidence" :key="'residentialTaxresidence-'+index1">
              <li>税务居住地{{ index1+1 }}</li>
              <li>{{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
                {{ item1.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div v-else-if="userInfo.accountType==='partnerShip'" class="registerPersonContent">
      <div v-for="(item,index) in partnerShip.shareholder" :key="index">
        <div v-if="item.personageType==='individualShareholder'" class="individual">
          <div class="personalInformation">
            <h3>{{ item.personalType }}</h3>
            <ul>
              <li>类型1</li>
              <li>{{ item.personalType }}</li>
            </ul>
            <ul v-if="item.personalTypeTwo">
              <li>类型2</li>
              <li>{{ item.personalTypeTwo }}</li>
            </ul>
            <ul>
              <li>称呼</li>
              <li>{{ item.call }}</li>
            </ul>
            <ul>
              <li>姓名</li>
              <li>{{ item.name }}</li>
            </ul>
            <ul>
              <li>用户名</li>
              <li>{{ item.userName }}</li>
            </ul>
            <ul>
              <li>电邮地址</li>
              <li>{{ item.emailAddress }}</li>
            </ul>
            <ul>
              <li>所有权百分比</li>
              <li>{{ item.PercentageOfOwnership + '%' }}</li>
            </ul>
            <ul>
              <li>出生日期</li>
              <li>{{ item.dateBirth }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>居住地址（非办公地址）</h3>
            <ul>
              <li>居住国家</li>
              <li>{{ item.residentialState }}</li>
            </ul>
            <ul>
              <li>地址</li>
              <li>{{ item.residentialAddress }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>联系信息</h3>
            <ul v-for="(item1,index1) in item.residentialRelationMessage" :key="'residentialRelationMessage-'+index1">
              <li>联系信息{{ index1+1 }}</li>
              <li>{{ item1.contactNumberType.dictDest }} {{ item1.country.cnName }} {{ item1.contactNumber }}</li>
            </ul>
            <ul>
              <li>不同国籍(选)</li>
              <li>{{ item.differentResidentialState.cnName }}</li>
            </ul>
            <ul>
              <li>不同出生国(选)</li>
              <li>{{ item.differentResidentialBirthState.cnName }}</li>
            </ul>
            <ul>
              <li>美国纳税人</li>
              <li>{{ item.AmericanTaxpayer ? '是' : '否' }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>身份证明</h3>
            <ul>
              <li>签发国家</li>
              <li>{{ item.signState.cnName }}</li>
            </ul>
            <ul>
              <li>身份证类型</li>
              <li>{{ item.identityCardType.dictDest }}</li>
            </ul>
            <ul>
              <li>证件号码</li>
              <li>{{ item.certificateNumber }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul v-for="(item1,index1) in item.residentialTaxresidence" :key="'residentialTaxresidence-'+index1">
              <li>税务居住地{{ index1+1 }}</li>
              <li>{{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
                {{ item1.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
        </div>
        <div v-if="item.personageType==='legalPersonShareholder'" class="individual">
          <div class="personalInformation">
            <h3>{{ item.personalType }}</h3>
            <ul>
              <li>类型1</li>
              <li>{{ item.personalType }}</li>
            </ul>
            <ul v-if="item.personalTypeTwo">
              <li>类型2</li>
              <li>{{ item.personalTypeTwo }}</li>
            </ul>
            <ul>
              <li>实体名称</li>
              <li>{{ item.theEntityName }}</li>
            </ul>
            <ul>
              <li>所有权百分比</li>
              <li>{{ item.PercentageOfOwnership + '%' }}</li>
            </ul>
          </div>
          <!-- 实体名称 所有权百分比已放在签署方信息里面 -->
          <div class="personalInformation">
            <h3>签署方信息</h3>
            <ul>
              <li>称呼</li>
              <li>{{ item.call }}</li>
            </ul>
            <ul>
              <li>姓名</li>
              <li>{{ item.name }}</li>
            </ul>
            <ul>
              <li>用户名</li>
              <li>{{ item.userName }}</li>
            </ul>
            <ul>
              <li>电邮地址</li>
              <li>{{ item.emailAddress }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>主要经营地点</h3>
            <ul>
              <li>国家</li>
              <li>{{ item.residentialState }}</li>
            </ul>
            <ul>
              <li>地址</li>
              <li>{{ item.residentialAddress }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>联系信息</h3>
            <ul v-for="(item1,index1) in item.residentialRelationMessage" :key="'residentialRelationMessage-'+index1">
              <li>联系信息{{ index1+1 }}</li>
              <li>{{ item1.contactNumberType.dictDest }} {{ item1.country.cnName }} {{ item1.contactNumber }}</li>
            </ul>
            <ul>
              <li>成立国家</li>
              <li>{{ item.setUpState.cnName }}</li>
            </ul>
            <ul>
              <li>是否实体是否在监管交易所公开上市交易？</li>
              <li>{{ item.whetherPubliclyTraded }}</li>
            </ul>
            <ul>
              <li>交易所(选)</li>
              <li>{{ item.exchange }}</li>
            </ul>
            <ul>
              <li>代码(选)</li>
              <li>{{ item.code }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul v-for="(item1,index1) in item.residentialTaxresidence" :key="'residentialTaxresidence-'+index1">
              <li>税务居住地{{ index1+1 }}</li>
              <li>{{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
                {{ item1.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
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
      limitedLiablity: {
        shareholder: [
        ]
      },
      partnerShip: {
        shareholder: [

        ]
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

  },
  mounted() {
    this.Port();
  },
  methods: {
    // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')}
    // 请求接口后缀  G 个人 Y 有限 H 合伙
    Port() {
      if (this.userInfo.accountType === 'limitedLiablity') {
        this.limitedLiablity.shareholder = [];
        this.companyPersonageMessageCommonY('individualShareholder').then(res => {
          const obj = {
            personageType: 'individualShareholder',
            // 个人信息
            call: 1111,
            name: 2222,
            userName: 3333,
            emailAddress: 444,
            PercentageOfOwnership: 5555,
            dateBirth: 666,
            // 居住地址
            residentialState: 777,
            residentialAddress: 888,
            // 联系信息
            residentialRelationMessage: [],
            tradeName: 999,
            differentResidentialState: 101010,
            differentResidentialBirthState: 111111,
            AmericanTaxpayer: 121212,
            // 身份证明
            signState: 131313,
            identityCardType: 141414,
            certificateNumber: null,
            // 税务居住地
            residentialTaxresidence: []
          };
          for (let i = 0; i < res.object.length; i++) {
            if (res.object[i].accountCoPersonal.personalType.dictDest === '个人股东') {
              obj.call = res.object[i].accountCoPersonal.appellation.dictDest;
              obj.name = `${res.object[i].accountCoPersonal.lastName} ${res.object[i].accountCoPersonal.middleName} ${res.object[i].accountCoPersonal.firstName} ${res.object[i].accountCoPersonal.suffix}`;
              obj.userName = res.object[i].accountCoPersonal.userName;
              obj.emailAddress = res.object[i].accountCoPersonal.email;
              obj.PercentageOfOwnership = res.object[i].accountCoPersonal.prcentageOfOwnership;
              // obj.dateBirth = this.$verify.NowDate2(Number(res.object[i].accountCoPersonal.birthday));
              obj.dateBirth = res.object[i].accountCoPersonal.birthday;
              obj.residentialState = res.object[i].accountCoAddressList[0].country.cnName;
              res.object[i].accountCoAddressList[0].address = res.object[i].accountCoAddressList[0].address.replace(/-#-/g, ' ');
              obj.residentialAddress = `${res.object[i].accountCoAddressList[0].address} ${res.object[i].accountCoAddressList[0].city} ${res.object[i].accountCoAddressList[0].rovince} ${res.object[i].accountCoAddressList[0].zipCode}`;

              obj.residentialRelationMessage = res.object[i].accountCoContactList;
              // obj.tradeName = 'shangyemingcheng';
              obj.differentResidentialState = res.object[i].accountCoAddressList[0].otherCountry;
              obj.differentResidentialBirthState = res.object[i].accountCoAddressList[0].otherBirthCountry;
              obj.AmericanTaxpayer = !!res.object[i].accountCoPersonal.isAmericanTaxpayer;

              if (res.object[i].accountCoIdentityList[0]) {
                obj.signState = res.object[i].accountCoIdentityList[0].issuingCountry;
                obj.identityCardType = res.object[i].accountCoIdentityList[0].idCardType;
                obj.certificateNumber = res.object[i].accountCoIdentityList[0].idNumber;
              }

              obj.residentialTaxresidence = res.object[i].accountCoTaxesList;
              obj.personalType = res.object[i].accountCoPersonal.personalType.dictDest;

              if (res.object[i].personalTypeTwo) {
                obj.personalTypeTwo = res.object[i].accountCoPersonal.personalTypeTwo.dictDest;
              }

              this.limitedLiablity.shareholder.push(
                JSON.parse(JSON.stringify(obj))
              );
            }
          }
        });
        this.companyPersonageMessageCommonY('legalPersonShareholder').then(res => {
          const obj = {
            personageType: 'legalPersonShareholder',
            // 签署方信息
            call: 1111,
            name: 2222,
            userName: 3333,
            emailAddress: 444,
            theEntityName: 666,
            PercentageOfOwnership: 5555,
            // 主要经营地点
            residentialState: 777,
            residentialAddress: 888,
            // 联系信息
            residentialRelationMessage: [],
            setUpState: 999,
            whetherPubliclyTraded: 101010,
            exchange: 111111,
            code: 121212,
            // 税务居住地
            residentialTaxresidence: []
          };
          for (let i = 0; i < res.object.length; i++) {
            if (res.object[i].accountCoPersonal.personalType.dictDest === '法人股东') {
              obj.call = res.object[i].accountCoPersonal.appellation.dictDest;
              obj.name = `${res.object[i].accountCoPersonal.lastName} ${res.object[i].accountCoPersonal.middleName} ${res.object[i].accountCoPersonal.firstName} ${res.object[i].accountCoPersonal.suffix}`;
              obj.userName = res.object[i].accountCoPersonal.userName;
              obj.emailAddress = res.object[i].accountCoPersonal.email;
              obj.theEntityName = res.object[i].accountCoPersonal.entityName;
              obj.PercentageOfOwnership = res.object[i].accountCoPersonal.prcentageOfOwnership;

              obj.residentialState = res.object[i].accountCoAddressList[0].country.cnName;
              res.object[i].accountCoAddressList[0].address = res.object[i].accountCoAddressList[0].address.replace(/-#-/g, ' ');
              obj.residentialAddress = `${res.object[i].accountCoAddressList[0].address} ${res.object[i].accountCoAddressList[0].city} ${res.object[i].accountCoAddressList[0].rovince} ${res.object[i].accountCoAddressList[0].zipCode}`;

              obj.residentialRelationMessage = res.object[i].accountCoContactList;
              if (res.object[i].accountCoContactList[0]) {
                obj.setUpState = res.object[i].accountCoContactList[0].foundingCountry;
                obj.exchange = res.object[i].accountCoContactList[0].exchange;
                obj.code = res.object[i].accountCoContactList[0].code;
                let status0 = res.object[i].accountCoContactList[0].status;
                if (status0) {
                  status0 = '是';
                } else {
                  status0 = '否';
                }
                obj.whetherPubliclyTraded = status0;
              }
              obj.personalType = res.object[i].accountCoPersonal.personalType.dictDest;
              if (res.object[i].personalTypeTwo) {
                obj.personalTypeTwo = res.object[i].accountCoPersonal.personalTypeTwo.dictDest;
              }

              obj.residentialTaxresidence = res.object[i].accountCoTaxesList;
              this.limitedLiablity.shareholder.push(
                JSON.parse(JSON.stringify(obj))
              );
            }
          }
        });
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.partnerShip.shareholder = [];
        this.companyPersonageMessageCommonH('individualShareholder').then(res => {
          const obj = {
            personageType: 'individualShareholder',
            // 个人信息
            call: 1111,
            name: 2222,
            userName: 3333,
            emailAddress: 444,
            PercentageOfOwnership: 5555,
            dateBirth: 666,
            // 居住地址
            residentialState: 777,
            residentialAddress: 888,
            // 联系信息
            residentialRelationMessage: [],
            tradeName: 999,
            differentResidentialState: 101010,
            differentResidentialBirthState: 111111,
            AmericanTaxpayer: 121212,
            // 身份证明
            signState: 131313,
            identityCardType: 141414,
            certificateNumber: null,
            // 税务居住地
            residentialTaxresidence: []
          };
          for (let i = 0; i < res.object.length; i++) {
            if (res.object[i].accountParPersonal.personalType.dictDest === '个人股东') {
              obj.call = res.object[i].accountParPersonal.appellation.dictDest;
              obj.name = `${res.object[i].accountParPersonal.lastName} ${res.object[i].accountParPersonal.middleName} ${res.object[i].accountParPersonal.firstName} ${res.object[i].accountParPersonal.suffix}`;
              obj.userName = res.object[i].accountParPersonal.userName;
              obj.emailAddress = res.object[i].accountParPersonal.email;
              obj.personalType = res.object[i].accountParPersonal.personalType.dictDest;
              if (res.object[i].personalTypeTwo) {
                obj.personalTypeTwo = res.object[i].accountParPersonal.personalTypeTwo.dictDest;
              }
              obj.PercentageOfOwnership = res.object[i].accountParPersonal.prcentageOfOwnership;
              // obj.dateBirth = this.$verify.NowDate2(Number(res.object[i].accountParPersonal.birthday));
              obj.dateBirth = res.object[i].accountParPersonal.birthday;
              obj.residentialState = res.object[i].accountParAddressList[0].country.cnName;
              res.object[i].accountParAddressList[0].address = res.object[i].accountParAddressList[0].address.replace(/-#-/g, ' ');
              obj.residentialAddress = `${res.object[i].accountParAddressList[0].address} ${res.object[i].accountParAddressList[0].city} ${res.object[i].accountParAddressList[0].rovince} ${res.object[i].accountParAddressList[0].zipCode}`;

              obj.residentialRelationMessage = res.object[i].accountParContactList;
              // obj.tradeName = 'shangyemingcheng';
              obj.differentResidentialState = res.object[i].accountParAddressList[0].otherCountry;
              obj.differentResidentialBirthState = res.object[i].accountParAddressList[0].otherBirthCountry;
              obj.AmericanTaxpayer = !!res.object[i].accountParPersonal.isAmericanTaxpayer;
              if (res.object[i].accountParIdentityList[0]) {
                obj.signState = res.object[i].accountParIdentityList[0].issuingCountry;
                obj.identityCardType = res.object[i].accountParIdentityList[0].idCardType;
                obj.certificateNumber = res.object[i].accountParIdentityList[0].idNumber;
              }
              obj.residentialTaxresidence = res.object[i].accountParTaxesList;
              this.partnerShip.shareholder.push(
                JSON.parse(JSON.stringify(obj))
              );
            }
          }
        });
        this.companyPersonageMessageCommonH('legalPersonShareholder').then(res => {
          const obj = {
            personageType: 'legalPersonShareholder',
            // 签署方信息
            call: '',
            name: '',
            userName: '',
            emailAddress: '',
            theEntityName: '',
            PercentageOfOwnership: '',
            // 主要经营地点
            residentialState: '',
            residentialAddress: '',
            // 联系信息
            residentialRelationMessage: [],
            setUpState: '',
            whetherPubliclyTraded: '',
            exchange: '',
            code: '',
            // 税务居住地
            residentialTaxresidence: []
          };

          for (let i = 0; i < res.object.length; i++) {
            if (res.object[i].accountParPersonal.personalType.dictDest === '法人股东') {
              obj.call = res.object[i].accountParPersonal.appellation.dictDest;
              obj.name = `${res.object[i].accountParPersonal.lastName} ${res.object[i].accountParPersonal.middleName} ${res.object[i].accountParPersonal.firstName} ${res.object[i].accountParPersonal.suffix}`;
              obj.userName = res.object[i].accountParPersonal.userName;
              obj.emailAddress = res.object[i].accountParPersonal.email;
              obj.theEntityName = res.object[i].accountParPersonal.entityName;
              obj.PercentageOfOwnership = res.object[i].accountParPersonal.prcentageOfOwnership;

              obj.residentialState = res.object[i].accountParAddressList[0].country.cnName;
              res.object[i].accountParAddressList[0].address = res.object[i].accountParAddressList[0].address.replace(/-#-/g, ' ');
              obj.residentialAddress = `${res.object[i].accountParAddressList[0].address} ${res.object[i].accountParAddressList[0].city} ${res.object[i].accountParAddressList[0].rovince} ${res.object[i].accountParAddressList[0].zipCode}`;

              obj.residentialRelationMessage = res.object[i].accountParContactList;
              if (res.object[i].accountParContactList[0]) {
                obj.setUpState = res.object[i].accountParContactList[0].foundingCountry;
                let status0 = res.object[i].accountParContactList[0].status;
                if (status0) {
                  status0 = '是';
                } else {
                  status0 = '否';
                }
                obj.whetherPubliclyTraded = status0;
                obj.exchange = res.object[i].accountParContactList[0].exchange;
                obj.code = res.object[i].accountParContactList[0].code;
              }
              obj.personalType = res.object[i].accountParPersonal.personalType.dictDest;
              if (res.object[i].personalTypeTwo) {
                obj.personalTypeTwo = res.object[i].accountParPersonal.personalTypeTwo.dictDest;
              }

              obj.residentialTaxresidence = res.object[i].accountParTaxesList;
              this.partnerShip.shareholder.push(
                JSON.parse(JSON.stringify(obj))
              );
            }
          }
        });
      }
    },

    companyPersonageMessageCommonY(personalType) { // 公司个人信息 公共
      // var parmasArray = ['personalType','setPersonalTypeTwo']
      // parmasArray.map((item)=>{
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/account/registerOpen/limitedLiablity/findPerInfo',
          method: 'get',
          params: { 'accountId': this.userInfo.id, personalType: personalType, 'lang': this.$Cookies.get('locale') }
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
      // })
    },
    companyPersonageMessageCommonH(personalType) { // 公司个人信息 公共
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/account/registerOpen/partnerShip/findPerInfo',
          method: 'get',
          params: { accountId: this.userInfo.id, personalType: personalType, lang: this.$Cookies.get('locale') }
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
    }
  }
};
</script>
<style lang="scss" scoped>
  .registerPersonContent {
    .individual{
      margin-bottom: 20px;
      margin-right: 20px;
      border: 1px solid #e6e6e6;
      border-radius: 4px;
    }
    .personalInformation{
      padding-bottom: 20px;
      background: #fff;
      margin: 0 20px;
      border-radius: 4px;
    }
    ul {
      margin-top: 0;
      padding:10px 0;
      padding-left: 0;
      overflow:hidden;
      margin-bottom: 0px;
      &:hover{
        background: rgb(246, 248, 249);
      }
      // margin-top: 15px;
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
    ul:first-child {
      margin-top: 40px;
    }
  }

</style>
