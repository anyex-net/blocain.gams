<template>
  <div>
    <div v-if="userInfo.accountType==='limitedLiablity'" class="registerPersonContent">
      <div v-for="(item,index) in limitedLiablity.shareholder" :key="index">
        <!-- 需要循环遍历添加25% 10%的情况-->
        <div v-if="item.personageType==='individualShareholder'" class="individual">
          <div class="personalInformation">
            <h3>{{ item.personalType }}</h3>
            <ul v-if="item.personalType">
              <li>类型1</li>
              <li>{{ item.personalType }}</li>
            </ul>
            <ul v-if="personalTypeTwo">
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
            <ul v-if="item.personalType==='25%或以上的受益人'&&item.personalType==='10%或以上的受益人'">
              <li>用户名</li>
              <li>{{ item.userName }}</li>
            </ul>
            <ul v-if="item.personalType==='25%或以上的受益人'&&item.personalType==='10%或以上的受益人'&&item.emailAddress">
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
            <ul
              v-for="(item1,index1) in item.residentialRelationMessage"
              :key="'residentialRelationMessage-'+index1"
            >
              <li>联系信息{{ index1+1 }}</li>
              <li>{{ item1.contactNumberType.dictDest }} {{ item1.country.cnName }} {{ item1.contactNumber }}</li>
            </ul>
            <ul>
              <li>不同国籍(选)</li>
              <li>{{ item.differentResidentialState }}</li>
            </ul>
            <ul>
              <li>不同出生国(选)</li>
              <li>{{ item.differentResidentialBirthState }}</li>
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
              <li>{{ item.signState }}</li>
            </ul>
            <ul>
              <li>身份证类型</li>
              <li>{{ item.identityCardType }}</li>
            </ul>
            <ul>
              <li>证件号码</li>
              <li>{{ item.certificateNumber }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul
              v-for="(item1,index1) in item.residentialTaxresidence"
              :key="'residentialTaxresidence-'+index1"
            >
              <li>税务居住地{{ index+1 }}</li>
              <li>
                {{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
                {{ item1.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
        </div>
        <!-- 经营负责人 -->
        <div v-if="item.personageType==='legalPersonShareholder'" class="individual">
          <div class="personalInformation">
            <h3>经营负责人</h3>
            <ul item.personalType>
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
              <li>职位</li>
              <li>{{ item.position }}</li>
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
            <ul>
              <li>不同国籍(选)</li>
              <li>{{ item.differentResidentialState }}</li>
            </ul>
            <ul>
              <li>不同出生国(选)</li>
              <li>{{ item.differentResidentialBirthState }}</li>
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
              <li>{{ item.signState }}</li>
            </ul>
            <ul>
              <li>身份证类型</li>
              <li>{{ item.identityCardType }}</li>
            </ul>
            <ul>
              <li>证件号码</li>
              <li>{{ item.certificateNumber }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul
              v-for="(item1,index1) in item.residentialTaxresidence"
              :key="'residentialTaxresidence-'+index1"
            >
              <li>税务居住地{{ index+1 }}</li>
              <li>
                {{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
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
            <ul v-if="item.personalType">
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
            <ul v-if="item.personalType!=='25%或以上的受益人'&&item.personalType!=='10%或以上的受益人'">
              <li>用户名</li>
              <li>{{ item.userName }}</li>
            </ul>
            <ul v-if="item.personalType!=='25%或以上的受益人'&&item.personalType!=='10%或以上的受益人'&&item.emailAddress">
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
            <ul
              v-for="(item1,index1) in item.residentialRelationMessage"
              :key="'residentialRelationMessage-'+index1"
            >
              <li>联系信息{{ index1+1 }}</li>
              <li>{{ item1.contactNumberType.dictDest }} {{ item1.country.cnName }} {{ item1.contactNumber }}</li>
            </ul>
            <ul>
              <li>不同国籍(选)</li>
              <li>{{ item.differentResidentialState }}</li>
            </ul>
            <ul>
              <li>不同出生国(选)</li>
              <li>{{ item.differentResidentialBirthState }}</li>
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
              <li>{{ item.signState }}</li>
            </ul>
            <ul>
              <li>身份证类型</li>
              <li>{{ item.identityCardType }}</li>
            </ul>
            <ul>
              <li>证件号码</li>
              <li>{{ item.certificateNumber }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul
              v-for="(item1,index1) in item.residentialTaxresidence"
              :key="'residentialTaxresidence-'+index1"
            >
              <li>税务居住地{{ index+1 }}</li>
              <li>
                {{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
                {{ item1.noTaxIdentificationNumber.dictDest }}
              </li>
            </ul>
          </div>
        </div>
        <div v-if="item.personageType==='legalPersonShareholder'" class="individual">
          <div class="personalInformation">
            <h3>经营负责人</h3>
            <ul v-if="item.personalType">
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
            <ul v-if="item.emailAddress">
              <li>电邮地址</li>
              <li>{{ item.emailAddress }}</li>
            </ul>
            <!-- <ul>
              <li>实体名称</li>
              <li>{{item.theEntityName}}</li>
            </ul>-->
            <ul>
              <li>职位</li>
              <li>{{ item.position }}</li>
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
            <ul>
              <li>不同国籍(选)</li>
              <li>{{ item.differentResidentialState }}</li>
            </ul>
            <ul>
              <li>不同出生国(选)</li>
              <li>{{ item.differentResidentialBirthState }}</li>
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
              <li>{{ item.signState }}</li>
            </ul>
            <ul>
              <li>身份证类型</li>
              <li>{{ item.identityCardType }}</li>
            </ul>
            <ul>
              <li>证件号码</li>
              <li>{{ item.certificateNumber }}</li>
            </ul>
          </div>
          <div class="personalInformation">
            <h3>税务居住地</h3>
            <ul
              v-for="(item1,index1) in item.residentialTaxresidence"
              :key="'residentialTaxresidence-'+index1"
            >
              <li>税务居住地{{ index+1 }}</li>
              <li>
                {{ item1.taxResidenceCountry.cnName }} {{ item1.taxResidenceIdCardNumber }}
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
        shareholder: []
      },
      partnerShip: {
        shareholder: []
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
        this.companyPersonageMessageCommonY('individualShareholder').then(
          res => {
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
              certificateNumber: 151515,
              // 税务居住地
              residentialTaxresidence: []
            };
            for (var key in res.object) {
              for (let i = 0; i < res.object[key].length; i++) {
                if (
                  (res.object[key][i].accountCoPersonal.personalType
                    .dictDest === '个人股东' &&
                    !res.object[key][i].accountCoPersonal.personalTypeTwo
                      .dictDest) ||
                  (res.object[key][i].accountCoPersonal.personalType
                    .dictDest === '25%或以上的受益人' &&
                    !res.object[key][i].accountCoPersonal.personalTypeTwo
                      .dictDest) ||
                  (res.object[key][i].accountCoPersonal.personalType
                    .dictDest === '10%或以上的受益人' &&
                    !res.object[key][i].accountCoPersonal.personalTypeTwo
                      .dictDest)
                ) {
                  obj.call =
                    res.object[key][i].accountCoPersonal.appellation.dictDest;
                  obj.name = `${res.object[key][i].accountCoPersonal.lastName} ${res.object[key][i].accountCoPersonal.middleName} ${res.object[key][i].accountCoPersonal.firstName} ${res.object[key][i].accountCoPersonal.suffix}`;
                  obj.userName = res.object[key][i].accountCoPersonal.userName;
                  obj.emailAddress = res.object[key][i].accountCoPersonal.email;
                  obj.PercentageOfOwnership =
                    res.object[key][i].accountCoPersonal.prcentageOfOwnership;
                  // obj.dateBirth = this.$verify.NowDate2(
                  //   Number(res.object[key][i].accountCoPersonal.birthday)
                  // );
                  obj.dateBirth = res.object[key][i].accountCoPersonal.birthday;
                  obj.residentialState =
                    res.object[key][i].accountCoAddressList[0].country.cnName;
                  res.object[key][
                    i
                  ].accountCoAddressList[0].address = res.object[key][
                    i
                  ].accountCoAddressList[0].address.replace(/-#-/g, ' ');
                  obj.residentialAddress = `${res.object[key][i].accountCoAddressList[0].address} ${res.object[key][i].accountCoAddressList[0].city} ${res.object[key][i].accountCoAddressList[0].rovince} ${res.object[key][i].accountCoAddressList[0].zipCode}`;

                  obj.residentialRelationMessage =
                    res.object[key][i].accountCoContactList;
                  // obj.tradeName = 'shangyemingcheng';
                  if (res.object[key][i].accountCoAddressList) {
                    obj.differentResidentialState =
                      res.object[key][
                        i
                      ].accountCoAddressList[0].otherCountry.cnName;
                    obj.differentResidentialBirthState =
                      res.object[key][
                        i
                      ].accountCoAddressList[0].otherBirthCountry.cnName;
                  }
                  obj.AmericanTaxpayer = !!res.object[key][i].accountCoPersonal.isAmericanTaxpayer;
                  if (res.object[key][i].accountCoIdentityList.length > 0) {
                    obj.signState =
                      res.object[key][
                        i
                      ].accountCoIdentityList[0].issuingCountry.cnName;
                    obj.identityCardType =
                      res.object[key][
                        i
                      ].accountCoIdentityList[0].idCardType.dictDest;
                    obj.certificateNumber =
                      res.object[key][i].accountCoIdentityList[0].idNumber;
                  }

                  obj.residentialTaxresidence =
                    res.object[key][i].accountCoTaxesList;

                  if (res.object[key][i].accountCoPersonal) {
                    obj.personalType =
                      res.object[key][
                        i
                      ].accountCoPersonal.personalType.dictDest;
                  }
                  if (res.object[key][i].accountCoPersonal.personalTypeTwo) {
                    obj.personalTypeTwo =
                      res.object[key][
                        i
                      ].accountCoPersonal.personalTypeTwo.dictDest;
                  }

                  this.limitedLiablity.shareholder.push(
                    JSON.parse(JSON.stringify(obj))
                  );
                }
              }
            }
          }
        );
        this.companyPersonageMessageCommonY('operationPrincipal').then(res => {
          const obj = {
            personageType: 'legalPersonShareholder',
            // 签署方信息
            call: 1111,
            name: 2222,
            userName: 3333,
            emailAddress: 444,
            theEntityName: 666,
            position: '',
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
            residentialTaxresidence: [],
            AmericanTaxpayer: null
          };
          for (var key in res.object) {
            for (let i = 0; i < res.object[key].length; i++) {
              if (
                res.object[key][i].accountCoPersonal.personalType.dictDest ===
                  '经营负责人' ||
                res.object[key][i].accountCoPersonal.personalTypeTwo
                  .dictDest === '经营负责人'
              ) {
                obj.call =
                  res.object[key][i].accountCoPersonal.appellation.dictDest;
                obj.name = `${res.object[key][i].accountCoPersonal.lastName} ${res.object[key][i].accountCoPersonal.middleName} ${res.object[key][i].accountCoPersonal.firstName} ${res.object[key][i].accountCoPersonal.suffix}`;
                obj.userName = res.object[key][i].accountCoPersonal.userName;
                obj.emailAddress = res.object[key][i].accountCoPersonal.email;
                obj.AmericanTaxpayer = !!res.object[key][i].accountCoPersonal.isAmericanTaxpayer;
                // obj.dateBirth = this.$verify.NowDate2(
                //   Number(res.object[key][i].accountCoPersonal.birthday)
                // ); // 出生日期
                obj.dateBirth = res.object[key][i].accountCoPersonal.birthday;
                obj.theEntityName =
                  res.object[key][i].accountCoPersonal.entityName;
                if (res.object[key][i].accountCoPersonal.companyPosition) {
                  const toucun =
                    res.object[key][i].accountCoPersonal.companyPosition;
                  for (let i = 0; i < toucun.length; i++) {
                    if (toucun[i].dictDest.indexOf(',') !== -1) {
                      var index = toucun[i].dictDest.lastIndexOf(',');
                      toucun[i].dictDest = toucun[i].dictDest.substring(
                        index + 1,
                        toucun[i].dictDest.length
                      );
                    }
                    if (toucun[i].dictDest === 'dongshi') {
                      obj.position = '董事';
                    } else if (toucun[i].dictDest === 'mishu') {
                      obj.position = '秘书';
                    } else {
                      obj.position = '其他官员' + ',' + toucun[i].dictDest;
                    }
                  }
                } else {
                  obj.position = res.object[key][i].accountCoPersonal.position; // 新增字段职位
                }

                obj.PercentageOfOwnership =
                  res.object[key][i].accountCoPersonal.prcentageOfOwnership;
                //   不同国籍
                if (res.object[key][i].accountCoAddressList) {
                  obj.differentResidentialState =
                    res.object[key][
                      i
                    ].accountCoAddressList[0].otherCountry.cnName;
                  obj.differentResidentialBirthState =
                    res.object[key][
                      i
                    ].accountCoAddressList[0].otherBirthCountry.cnName;
                }
                obj.residentialState =
                  res.object[key][i].accountCoAddressList[0].country.cnName;
                res.object[key][i].accountCoAddressList[0].address = res.object[
                  key
                ][i].accountCoAddressList[0].address.replace(/-#-/g, ' ');
                obj.residentialAddress = `${res.object[key][i].accountCoAddressList[0].address} ${res.object[key][i].accountCoAddressList[0].city} ${res.object[key][i].accountCoAddressList[0].rovince} ${res.object[key][i].accountCoAddressList[0].zipCode}`;

                obj.residentialRelationMessage =
                  res.object[key][i].accountCoContactList;
                if (res.object[key][i].accountCoContactList[0]) {
                  obj.setUpState =
                    res.object[key][i].accountCoContactList[0].foundingCountry;
                  obj.exchange =
                    res.object[key][i].accountCoContactList[0].exchange;
                  obj.code = res.object[key][i].accountCoContactList[0].code;
                  let status0 =
                    res.object[key][i].accountCoContactList[0].status;
                  if (status0) {
                    status0 = '是';
                  } else {
                    status0 = '否';
                  }
                  obj.whetherPubliclyTraded = status0;
                }

                if (res.object[key][i].accountCoPersonal) {
                  obj.personalType =
                    res.object[key][i].accountCoPersonal.personalType.dictDest;
                }
                if (res.object[key][i].accountCoPersonal.personalTypeTwo) {
                  obj.personalTypeTwo =
                    res.object[key][
                      i
                    ].accountCoPersonal.personalTypeTwo.dictDest;
                }
                if (res.object[key][i].accountCoIdentityList.length > 0) {
                  obj.signState =
                    res.object[key][
                      i
                    ].accountCoIdentityList[0].issuingCountry.cnName;
                  obj.identityCardType =
                    res.object[key][
                      i
                    ].accountCoIdentityList[0].idCardType.dictDest;
                  obj.certificateNumber =
                    res.object[key][i].accountCoIdentityList[0].idNumber;
                }
                obj.residentialTaxresidence =
                  res.object[key][i].accountCoTaxesList;
                this.limitedLiablity.shareholder.push(
                  JSON.parse(JSON.stringify(obj))
                );
              }
            }
          }
        });
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.partnerShip.shareholder = [];
        this.companyPersonageMessageCommonH('individualShareholder').then(
          res => {
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
              certificateNumber: 151515,
              // 税务居住地
              residentialTaxresidence: []
            };
            for (var key in res.object) {
              for (let i = 0; i < res.object[key].length; i++) {
                if (
                  (res.object[key][i].accountParPersonal.personalType
                    .dictDest === '个人股东' &&
                    !res.object[key][i].accountParPersonal.personalTypeTwo
                      .dictDest) ||
                  (res.object[key][i].accountParPersonal.personalType
                    .dictDest === '25%或以上的受益人' &&
                    !res.object[key][i].accountParPersonal.personalTypeTwo
                      .dictDest) ||
                  (res.object[key][i].accountParPersonal.personalType
                    .dictDest === '10%或以上的受益人' &&
                    !res.object[key][i].accountParPersonal.personalTypeTwo
                      .dictDest)
                ) {
                  obj.call =
                    res.object[key][i].accountParPersonal.appellation.dictDest;
                  obj.name = `${res.object[key][i].accountParPersonal.lastName} ${res.object[key][i].accountParPersonal.middleName} ${res.object[key][i].accountParPersonal.firstName} ${res.object[key][i].accountParPersonal.suffix}`;
                  obj.userName = res.object[key][i].accountParPersonal.userName;
                  obj.emailAddress =
                    res.object[key][i].accountParPersonal.email;
                  obj.personalType =
                    res.object[key][i].accountParPersonal.personalType.dictDest;
                  if (res.object[key][i].accountParPersonal) {
                    obj.personalType =
                      res.object[key][
                        i
                      ].accountParPersonal.personalType.dictDest;
                  }
                  if (res.object[key][i].accountParPersonal.personalTypeTwo) {
                    obj.personalTypeTwo =
                      res.object[key][
                        i
                      ].accountParPersonal.personalTypeTwo.dictDest;
                  }
                  obj.PercentageOfOwnership =
                    res.object[key][i].accountParPersonal.prcentageOfOwnership;
                  // obj.dateBirth = this.$verify.NowDate2(
                  //   Number(res.object[key][i].accountParPersonal.birthday)
                  // );
                  obj.dateBirth = res.object[key][i].accountParPersonal.birthday;
                  obj.residentialState =
                    res.object[key][i].accountParAddressList[0].country.cnName;
                  res.object[key][
                    i
                  ].accountParAddressList[0].address = res.object[key][
                    i
                  ].accountParAddressList[0].address.replace(/-#-/g, ' ');
                  obj.residentialAddress = `${res.object[key][i].accountParAddressList[0].address} ${res.object[key][i].accountParAddressList[0].city} ${res.object[key][i].accountParAddressList[0].rovince} ${res.object[key][i].accountParAddressList[0].zipCode}`;

                  obj.residentialRelationMessage =
                    res.object[key][i].accountParContactList;
                  // obj.tradeName = 'shangyemingcheng';
                  if (res.object[key][i].accountParAddressList) {
                    obj.differentResidentialState =
                      res.object[key][
                        i
                      ].accountParAddressList[0].otherCountry.cnName;
                    obj.differentResidentialBirthState =
                      res.object[key][
                        i
                      ].accountParAddressList[0].otherBirthCountry.cnName;
                  }
                  obj.AmericanTaxpayer = !!res.object[key][i].accountParPersonal.isAmericanTaxpayer;
                  if (res.object[key][i].accountParIdentityList.length > 0) {
                    obj.signState =
                      res.object[key][
                        i
                      ].accountParIdentityList[0].issuingCountry.cnName;
                  }
                  if (res.object[key][i].accountParIdentityList.length > 0) {
                    obj.identityCardType =
                      res.object[key][
                        i
                      ].accountParIdentityList[0].idCardType.dictDest;
                    obj.certificateNumber =
                      res.object[key][i].accountParIdentityList[0].idNumber;
                  }
                  obj.residentialTaxresidence =
                    res.object[key][i].accountParTaxesList;
                  this.partnerShip.shareholder.push(
                    JSON.parse(JSON.stringify(obj))
                  );
                }
              }
            }
          }
        );
        this.companyPersonageMessageCommonH('operationPrincipal').then(res => {
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
            residentialTaxresidence: [],
            personalTypeTwo: '',
            AmericanTaxpayer: null
          };
          for (var key in res.object) {
            for (let i = 0; i < res.object[key].length; i++) {
              if (
                res.object[key][i].accountParPersonal.personalType.dictDest ===
                  '经营负责人' ||
                res.object[key][i].accountParPersonal.personalTypeTwo
                  .dictDest === '经营负责人'
              ) {
                obj.call =
                  res.object[key][i].accountParPersonal.appellation.dictDest;
                obj.name = `${res.object[key][i].accountParPersonal.lastName} ${res.object[key][i].accountParPersonal.middleName} ${res.object[key][i].accountParPersonal.firstName} ${res.object[key][i].accountParPersonal.suffix}`;
                obj.userName = res.object[key][i].accountParPersonal.userName;
                obj.emailAddress = res.object[key][i].accountParPersonal.email;
                obj.AmericanTaxpayer = !!res.object[key][i].accountParPersonal.isAmericanTaxpayer;
                // obj.dateBirth = this.$verify.NowDate2(
                //   Number(res.object[key][i].accountParPersonal.birthday)
                // ); // 出生日期
                obj.dateBirth = res.object[key][i].accountParPersonal.birthday;
                // 不同国籍
                if (res.object[key][i].accountParPersonal) {
                  obj.differentResidentialState =
                    res.object[key][
                      i
                    ].accountParAddressList[0].otherCountry.cnName;
                  obj.differentResidentialBirthState =
                    res.object[key][
                      i
                    ].accountParAddressList[0].otherBirthCountry.cnName;
                }
                obj.theEntityName =
                  res.object[key][i].accountParPersonal.entityName;
                obj.PercentageOfOwnership =
                  res.object[key][i].accountParPersonal.prcentageOfOwnership;
                // obj.position = res.object[key][i].accountParPersonal.companyPosition[0].dictDest; // 新增字段职位
                if (res.object[key][i].accountParPersonal.companyPosition) {
                  const toucun =
                    res.object[key][i].accountParPersonal.companyPosition;
                  for (let i = 0; i < toucun.length; i++) {
                    if (toucun[i].dictDest.indexOf(',') !== -1) {
                      var index = toucun[i].dictDest.lastIndexOf(',');
                      toucun[i].dictDest = toucun[i].dictDest.substring(
                        index + 1,
                        toucun[i].dictDest.length
                      );
                    }
                    if (toucun[i].dictDest === 'dongshi') {
                      obj.position = '董事';
                    } else if (toucun[i].dictDest === 'mishu') {
                      obj.position = '秘书';
                    } else {
                      obj.position = '其他官员' + ',' + toucun[i].dictDest;
                    }
                  }
                } else {
                  obj.position = res.object[key][i].accountParPersonal.position; // 新增字段职位
                }

                obj.residentialState =
                  res.object[key][i].accountParAddressList[0].country.cnName;
                res.object[key][
                  i
                ].accountParAddressList[0].address = res.object[key][
                  i
                ].accountParAddressList[0].address.replace(/-#-/g, ' ');
                obj.residentialAddress = `${res.object[key][i].accountParAddressList[0].address} ${res.object[key][i].accountParAddressList[0].city} ${res.object[key][i].accountParAddressList[0].rovince} ${res.object[key][i].accountParAddressList[0].zipCode}`;

                obj.residentialRelationMessage =
                  res.object[key][i].accountParContactList;
                if (res.object[key][i].accountParContactList[0]) {
                  obj.setUpState =
                    res.object[key][i].accountParContactList[0].foundingCountry;
                  obj.whetherPubliclyTraded =
                    res.object[key][i].accountParContactList[0].status;
                  obj.exchange =
                    res.object[key][i].accountParContactList[0].exchange;
                  obj.code = res.object[key][i].accountParContactList[0].code;
                }

                if (res.object[key][i].accountParPersonal) {
                  obj.personalType =
                    res.object[key][i].accountParPersonal.personalType.dictDest;
                }
                if (res.object[key][i].accountParPersonal.personalTypeTwo) {
                  obj.personalTypeTwo =
                    res.object[key][
                      i
                    ].accountParPersonal.personalTypeTwo.dictDest;
                }
                if (res.object[key][i].accountParIdentityList.length > 0) {
                  obj.signState =
                    res.object[key][
                      i
                    ].accountParIdentityList[0].issuingCountry.cnName;

                  obj.identityCardType =
                    res.object[key][
                      i
                    ].accountParIdentityList[0].idCardType.dictDest;
                  obj.certificateNumber =
                    res.object[key][i].accountParIdentityList[0].idNumber;
                }

                obj.residentialTaxresidence =
                  res.object[key][i].accountParTaxesList;
                this.partnerShip.shareholder.push(
                  JSON.parse(JSON.stringify(obj))
                );
              }
            }
          }
        });
      }
    },
    companyPersonageMessageCommonY(personalType) {
      // 公司个人信息 公共
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/account/registerOpen/limitedLiablity/findBeneficiaryOwner',
          method: 'get',
          params: {
            accountId: this.userInfo.id,
            lang: this.$Cookies.get('locale')
          }
        })
          .then(res => {
            if (res.code === 200) {
              if (this.$verify.resStatus(res)) {
                resolve(res);
              }
            } else {
              this.$message.error(res.message || res.code);
            }
          })
          .catch(error => {
            this.$message.error(error);
          });
      });
    },
    companyPersonageMessageCommonH(personalType) {
      // 公司个人信息 公共
      return new Promise((resolve, reject) => {
        this.$http({
          url: '/account/registerOpen/partnerShip/findBeneficiaryOwner',
          method: 'get',
          params: {
            accountId: this.userInfo.id,
            lang: this.$Cookies.get('locale')
          }
        })
          .then(res => {
            if (res.code === 200) {
              if (this.$verify.resStatus(res)) {
                resolve(res);
              }
            } else {
              this.$message.error(res.message || res.code);
            }
          })
          .catch(error => {
            this.$message.error(error);
          });
      });
    }
  }
};
</script>
<style lang="scss" scoped>
.registerPersonContent {
  .individual {
    margin-bottom: 20px;
    margin-right: 20px;
    border: 1px solid #e6e6e6;
    border-radius: 4px;
  }
  .personalInformation {
    padding-bottom: 20px;
    background: #fff;
    margin: 0 20px;
    border-radius: 4px;
  }
  ul {
    margin-top: 0;
    padding: 10px 0;
    padding-left: 0;
    overflow: hidden;
    margin-bottom: 0px;
    &:hover {
      background: rgb(246, 248, 249);
    }
    li {
      float: left;
      list-style: none;
      color: #999;
      div.describe {
        i.describeOne {
          font-style: normal;
        }
        span.describeTwo {
          display: block;
          margin-top: 10px;
        }
      }
      div.describe:nth-child(n + 2) {
        margin-top: 20px;
      }
    }
    li:first-child {
      width: 20%;
      margin-right: 20px;
      overflow: hidden;
    }
    li:nth-child(n + 2) {
      width: 70%;
      word-wrap: break-word;
    }
  }
  ul:first-child {
    margin-top: 40px;
  }
}
</style>
