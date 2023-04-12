<template>
  <div class="moreInformation">
    <!-- 个人 -->
    <div v-if="accountType==='personal'">
      <h3>身份证明</h3>
      <hr>
      <div v-for="(item,index) in identityList" :key="index+2">
        <el-row>
          <el-col :span="5">
            <div>签发国家</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.issuingCountry.cnName }}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>身份证类型</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.idCardType.dictDest }}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>证件号码</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.idNumber }}</div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div>
      <h3>税务居住地</h3>
      <hr>
      <div v-for="(item,index) in taxesList" :key="index">
        <el-row>
          <el-col :span="5">
            <div>国家和证件号码{{ index+1 }}</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.taxResidenceCountry.cnName }} {{ item.taxResidenceIdCardNumber }}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>有没有纳税识别号{{ index+1 }}</div>
          </el-col>
          <el-col
            v-if="item.noTaxIdentificationNumber.dictDest=='undefined'||item.noTaxIdentificationNumber.dictDest==''"
            :span="19"
          >
            <div>有</div>
          </el-col>
          <el-col v-else :span="19">
            <div>没有</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>选项{{ index+1 }}</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.noTaxIdentificationNumber.dictDest }}</div>
          </el-col>
        </el-row>
      </div>
    </div>
    <!-- 公司企业 -->
    <div v-if="accountType!=='personal'">
      <h3>被授权人</h3>
      <hr>
      <el-row>
        <el-col :span="5">
          <div>称呼</div>
        </el-col>
        <el-col :span="19">
          <div>{{ appellation }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>姓</div>
        </el-col>
        <el-col :span="19">
          <div>{{ personal.lastName }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>中间名(选)</div>
        </el-col>
        <el-col :span="19">
          <div>{{ personal.middleName }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>名</div>
        </el-col>
        <el-col :span="19">
          <div>{{ personal.firstName }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>后缀（选）</div>
        </el-col>
        <el-col :span="19">
          <div>{{ personal.suffix }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>出生日期</div>
        </el-col>
        <el-col :span="19">
          <div>{{ personal.birthday }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>公司头衔</div>
        </el-col>
        <el-col :span="19">
          <div>{{ companyPosition }}</div>
        </el-col>
      </el-row>
      <el-row v-for="(item,index) in addressList" :key="index+&quot;addressList&quot;">
        <el-col :span="5">
          <div>地址{{ index+1 }}</div>
        </el-col>
        <el-col :span="19">
          <div>{{ item.country.anName }}{{ item.rovince }}{{ item.city }}{{ item.address }},{{ item.zipCode }}</div>
        </el-col>
      </el-row>
      <el-row v-for="(item,index) in contactList" :key="index+&quot;contactList&quot;">
        <el-col :span="5">
          <div>联系信息{{ index+1 }}</div>
        </el-col>
        <el-col :span="19">
          <div>{{ item.contactNumberType.dictDest }}{{ item.country.cnName }}{{ item.contactNumber }}</div>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="5">
          <div>邮箱</div>
        </el-col>
        <el-col :span="19">
          <div>{{ personal.email }}</div>
        </el-col>
      </el-row>
    </div>
    <!-- 财务信息 -->
    <div>
      <h3>财务信息</h3>
      <hr>
      <div v-for="(item,index) in assetList" :key="index+1">
        <el-row>
          <el-col :span="5">
            <div>年度净资产（USD)</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.anualNetIncome }}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>资产净值（USD)</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.netAssetValue }}</div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="5">
            <div>流动资产净值（USD）</div>
          </el-col>
          <el-col :span="19">
            <div>{{ item.netCurrentAssets }}</div>
          </el-col>
        </el-row>
      </div>
      <div
        v-for="(item,index) in investmentTargetList"
        :key="index+&quot;investmentTargetList&quot;"
      >
        <el-row>
          <el-col :span="5">
            <div>投资目标</div>
          </el-col>
          <!-- <div > -->
          <el-col :span="19">
            <span
              v-for="(item1,index1) in item.investmentTarget"
              :key="index1+&quot;nvestmentTarget&quot;"
              class="investmentTarget"
            >{{ item1.dictDest }}</span>
          </el-col>
          <!-- </div> -->
        </el-row>
      </div>
    </div>
    <!-- 交易许可与经验 -->
    <div>
      <h3>交易经验&许可</h3>
      <hr>
      <el-row v-for="(item,index) in tradeExperienceList" :key="'tradingBusiness-'+index">
        <el-col :span="5">
          <div v-if="item.tradingLocation">{{ item.tradingLocation[0].tansactionType }}</div>
        </el-col>
        <el-col :span="19">
          <div>
            <div>
              <!--SingleStorkFutures个人期货  MutualFund共同基金   NextShares   CurrencyCoverter货币转换  只有地点可选 -->
              <span
                v-if="item.tradingLocation[0].tansactionType!=='SingleStorkFutures'||item.tradingLocation[0]==='MutualFund'
                  ||item.tradingLocation[0].tansactionType==='NextShares'||item.tradingLocation[0].tansactionType!=='CurrencyCoverter'"
              >
                <span v-if="item.tradeExperience">{{ item.tradeExperience.dictDest }}</span>
                <span v-if="item.tradingExperience">{{ item.tradingExperience.dictDest }}</span>
                <span
                  v-if="item.yearTransactionsPerNumber"
                >{{ item.yearTransactionsPerNumber.dictDest }}</span>
                <span v-if="item.knowledgeLevel.dictDest">{{ item.knowledgeLevel.dictDest }}</span>
              </span>
            </div>
            <i v-if="item.transactionAuthority&&(item.tansactionType.dictDest==='期权')">受限期权交易</i>
            <!-- <br> -->
            <div class="describeTwo">
              <span
                v-for="(item1,index1) in item.tradingLocation"
                :key="index1"
                class="tansactionType"
              >{{ item1.cnName }}</span>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
// import moment from 'moment';
export default {
  // props: ['accountInfo'],
  props: {
    accountInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      // nowDate: null,
      userName: null, // 用户名
      email: null, // 邮箱
      customerType: null, // 客户名 公司名称
      accountType: null, // 客户类型
      accountId: null, // 账户id
      country: null, // 国家
      gender: null,
      customerName: null,
      birthday: null, // 出生日期
      addressList: [], // 被授权人地址
      mailingAddress: null,
      Url: null,
      contactList: [], // 被授权人联系信息
      wealthSourceList: [], // 财富来源
      appellation: null,
      taxesList: [], // 税务信息
      tradeExperienceList: [], // 交易经验
      assetList: [], // 财务信息,
      identityList: [], // 身份证明 个人才有
      personal: {}, // 被授权人信息 公司企业才有
      investmentTargetList: [], // 投资目标
      companyPosition: null // 被授权人职位
    };
  },
  created() {
    // console.log(this.accountInfo, "eiiuwieo");
    this.init();
    this.getmoreInformation();
    // this.setTime();
  },
  methods: {
    // nowTimes() {
    //   this.nowDate = moment(+new Date()).format('YYYYMMDD hh:mm:ss');
    // },
    // setTime() {
    //   this.clearTimeSet = setInterval(() => {
    //     this.nowTimes();
    //   }, 1000);
    // },
    // clear() {
    //   clearInterval(this.clearTimeSet);
    // },
    init() {
      const accountInfo = this.accountInfo;
      this.userName = accountInfo.accountName;
      this.email = accountInfo.email;
      this.customerType = accountInfo.accountPortfolioTypeDest; // 客户类型
      if (accountInfo.accounType === '个人') {
        this.accountType = 'personal';
      } else if (accountInfo.accounType === '合伙企业') {
        this.accountType = 'partnerShip';
      } else {
        this.accountType = 'limitedLiablity';
      }
      // this.accountType = accountInfo.accounType;
      this.accountId = accountInfo.accountId;
    },
    getmoreInformation() {
      this.$http({
        url: '/account/management/moreInformation',
        method: 'get',
        params: {
          accountId: this.accountId,
          accountType: this.accountType,
          lang: 'zh_CN'
        }
      })
        .then(res => {
          if (res.code === 200) {
            // this.countryOptions = res.object;
            const object = res.object;
            this.taxesList = object.taxesList;
            this.tradeExperienceList = object.tradeExperienceList;
            this.tradeExperienceList = this.tradeExperienceList.map(item => {
              let tansactionType = item.tradingLocation[0].tansactionType;
              switch (tansactionType) {
                case 'Stock':
                  tansactionType = '股票';
                  break;
                case 'Bond':
                  tansactionType = '债券';
                  break;
                case 'Warrant':
                  tansactionType = '权证';
                  break;
                case 'Options':
                  tansactionType = '期权';
                  break;
                case 'Futures':
                  tansactionType = '期货';
                  break;
                case 'ForwardOption':
                  tansactionType = '期权期货';
                  break;
                case 'MutualFund':
                  tansactionType = '共同基金';
                  break;
                case 'NextShares':
                  tansactionType = 'NextShares';
                  break;
                case 'CurrencyConverter':
                  tansactionType = '货币转换';
                  break;
                case 'SingleStockFutures':
                  tansactionType = '个股期货';
                  break;
              }
              item.tradingLocation[0].tansactionType = tansactionType;
              return item;
            });

            this.assetList = object.assetList;
            this.identityList = object.identityList;
            // 处理出生日期格式转换
            // if (object.personal.birthday !== '' && object.personal) {
            //   var birthday = new Date(object.personal.birthday * 1); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
            //   var Y = birthday.getFullYear() + '-';
            //   var M =
            //     (birthday.getMonth() + 1 < 10
            //       ? '0' + (birthday.getMonth() + 1)
            //       : birthday.getMonth() + 1) + '-';
            //   var D = birthday.getDate() + ' ';
            //   object.personal.birthday = Y + M + D;
            // }
            if (object.personal.companyPosition) {
              const toucun = object.personal.companyPosition;
              for (let i = 0; i < toucun.length; i++) {
                if (toucun[i].dictDest.indexOf(',') !== -1) {
                  var index = toucun[i].dictDest.lastIndexOf(',');
                  toucun[i].dictDest = toucun[i].dictDest.substring(
                    index + 1,
                    toucun[i].dictDest.length
                  );
                }
                if (toucun[i].dictDest === 'dongshi') {
                  this.companyPosition = '董事';
                } else if (toucun[i].dictDest === 'mishu') {
                  this.companyPosition = '秘书';
                } else {
                  this.companyPosition = '其他官员' + ',' + toucun[i].dictDest;
                }
              }
            }
            this.personal = object.personal;
            if (object.personal) {
              this.appellation = object.personal.appellation.dictDest;
            }
            this.investmentTargetList = object.investmentTargetList;
            // 处理地址拼接问题
            object.addressList = object.addressList.map(item => {
              item.address = item.address.replace(/-#-/, ' ');
              return item;
            });
            this.addressList = object.addressList;
            this.contactList = object.contactList;
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    }
  }
};
</script>

<style lang="scss" scoped>
.moreInformation {
  padding: 10px;
  background-color: #fff;
  > div {
    margin-bottom: 30px;
  }
  .investmentTarget {
    margin-right: 10px;
  }
  .tansactionType {
    margin-right: 10px;
  }
  .describeOne {
    span {
      margin-right: 50px;
    }
  }
}
</style>
<style lang="scss">
.moreInformation {
  h3 {
    color: #555;
  }
  .hover:hover {
    background-color: #eee;
  }
  .el-row:hover {
    background-color: #eee;
  }
  .el-row {
    font-size: 14px;
    color: #666;
    line-height: 30px;
    .el-col-19 {
      word-wrap: break-word;
    }
  }
}
</style>
