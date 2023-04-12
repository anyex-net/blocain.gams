<template>
  <div>
    <div v-if="userInfo.accountType==='personal'" class="regulateContent">
      <div v-if="regulateData">
        <ul>
          <li>1.账户持有人或同一居所内居住的任何直系亲属是否为注册经纪-自营商； 证券或商品经纪公司的雇员、董事或所有人；银行、对冲基金交易所或其他金融服务公司的雇员？</li>
          <li>{{ isBoolean1 }}</li>
        </ul>
        <div v-if="regulateData[0].employer">
          <ul v-for="(item,index) in personal.regulateOneList" :key="'regulateOneList-'+index">
            <li>{{ item.companyNameTitle }}</li>
            <li>{{ item.companyNameValue }}</li>
          </ul>
        </div>
      </div>
      <div v-if="regulateData">
        <ul>
          <li>2.账户持有人是否是一家公共上市公司的董事，持10%股权的股东或决策人？</li>
          <li>{{ isBoolean2 }}</li>
        </ul>
        <div v-if="regulateData[0].accountHolderCompanyStock">
          <ul v-for="(item,index) in personal.regulateTwoList" :key="'regulateTwoList-'+index">
            <li>{{ item.companyNameTitle }}</li>
            <li>{{ item.companyNameValue }}</li>
          </ul>
        </div>

      </div>
    </div>
    <div v-else-if="userInfo.accountType==='limitedLiablity'" class="regulateContent">
      <div v-for="(item,index) in limitedLiablity.regulateList" :key="'regulateList-'+index">
        <ul>
          <li>{{ item.infoType.dictDest }} </li>
          <li>{{ item.whether?'是':'否' }}</li>
        </ul>
        <div v-if="item.infoType.dictValue==='foreignBank (established and operated outside the United States)'&&item.infoDetails">
          <div>
            <ul>
              <li>公司是否在以下任一国家所发银行许可下运营：</li>
              <li>{{ item.infoDetails.min1 ? '是' : '否' }}</li>
            </ul>
            <ul>
              <li>公司是否持有“离岸银行许可”运营？</li>
              <li>{{ item.infoDetails.min2 ? '是' : '否' }}</li>
            </ul>
            <ul>
              <li>公司保证如果开始使用下列银行执照运营，将立即通过邮件以书面的形式联系国瑞证券：</li>
              <li>{{ item.infoDetails.min3 ? '是' : '否' }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='are its shares traded on regulated exchanges?Is the organization a publicly held entity?'&&item.infoDetails">
          <div v-for="(item1,index1) in item.infoDetails.jiaoyitiaomu15" :key="index1">
            <ul>
              <li>代码</li>
              <li>{{ item1.jiaoyisuoDaima }}</li>
            </ul>
            <ul>
              <li>交易所</li>
              <li>{{ item1.jiaoyisuoName }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?'&&item.infoDetails">
          <div>
            <ul>
              <li>详情</li>
              <li>{{ item.infoDetails.xiangqing }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?'&&item.infoDetails">
          <div>
            <ul>
              <li>交易所名称</li>
              <li>{{ item.infoDetails.jiaoyisuoName }}</li>
            </ul>
            <ul>
              <li>详情</li>
              <li>{{ item.infoDetails.xiangqing }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?'&&item.infoDetails">
          <div>
            <ul>
              <li>详情</li>
              <li>{{ item.infoDetails.xiangqing }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?'&&item.infoDetails">
          <div>
            <ul>
              <li>公司的股票代码</li>
              <li>{{ item.infoDetails.daima }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?'&&item.infoDetails">
          <div>
            <ul>
              <li>公司名称</li>
              <li>{{ item.infoDetails.guzhuXiang }}</li>
            </ul>
            <ul>
              <li>公司 国家/地区</li>
              <li>{{ item.infoDetails.state }}</li>
            </ul>
            <ul>
              <li>公司地址</li>
              <li>{{ item.infoDetails.site }}{{ item.infoDetails.site1 }}{{ item.infoDetails.province }}{{ item.infoDetails.city }}</li>
            </ul>
            <ul>
              <li>邮政编码</li>
              <li>{{ item.infoDetails.postalCode }}</li>
            </ul>
            <ul>
              <li>电邮地址</li>
              <li>{{ item.infoDetails.postalAddress }}</li>
            </ul>
            <ul>
              <li>电话号码</li>
              <li>{{ item.infoDetails.phoneNumber }}</li>
            </ul>
            <ul>
              <li>注册人姓名</li>
              <li>{{ item.infoDetails.zhuceName }}</li>
            </ul>
            <ul>
              <li>注册人与机构的关联/关系</li>
              <li>{{ item.infoDetails.zhuceGuanxi }}</li>
            </ul>
          </div>
        </div>

      </div>
    </div>
    <div v-else-if="userInfo.accountType==='partnerShip'" class="regulateContent">
      <div v-for="(item,index) in partnerShip.regulateList" :key="'regulateList-'+index">
        <ul>
          <li>{{ item.infoType.dictDest }}</li>
          <li>{{ item.whether?'是':'否' }}</li>
        </ul>
        <div v-if="item.infoType.dictValue==='foreignBank (established and operated outside the United States)'&&item.infoDetails">
          <div>
            <ul>
              <li>公司是否在以下任一国家所发银行许可下运营：</li>
              <li>{{ item.infoDetails.min1 ? '是' : '否' }}</li>
            </ul>
            <ul>
              <li>公司是否持有“离岸银行许可”运营？</li>
              <li>{{ item.infoDetails.min2 ? '是' : '否' }}</li>
            </ul>
            <ul>
              <li>公司保证如果开始使用下列银行执照运营，将立即通过邮件以书面的形式联系国瑞证券：</li>
              <li>{{ item.infoDetails.min3 ? '是' : '否' }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='are its shares traded on regulated exchanges?Is the organization a publicly held entity?'&&item.infoDetails">
          <div v-for="(item1,index1) in item.infoDetails.jiaoyitiaomu15" :key="index1">
            <ul>
              <li>代码</li>
              <li>{{ item1.jiaoyisuoDaima }}</li>
            </ul>
            <ul>
              <li>交易所</li>
              <li>{{ item1.jiaoyisuoName }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='whether institutions need in accordance with article 1101 of the us futures association (NFA), registered in the commodity futures trading commission, or investment securities association (IDA) registered in Canada, or in other foreign registered as futures agents, brokers, commodity fund managers and commodity trading advisors, retail traders or leveraged trader?'&&item.infoDetails">
          <div>
            <ul>
              <li>详情</li>
              <li>{{ item.infoDetails.xiangqing }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='is the institution a member of the exchange, regulatory/self-regulatory authority, or a relevant or related person who is a member of the exchange?'&&item.infoDetails">
          <div>
            <ul>
              <li>交易所名称</li>
              <li>{{ item.infoDetails.jiaoyisuoName }}</li>
            </ul>
            <ul>
              <li>详情</li>
              <li>{{ item.infoDetails.xiangqing }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='has any principal, officer, authorized trader or other person listed in the account been arrested or convicted of a crime?'&&item.infoDetails">
          <div>
            <ul>
              <li>详情</li>
              <li>{{ item.infoDetails.xiangqing }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='are the account holders or any of their officers: I) directors of the listed company, ii) shareholders holding 10% of the shares, or iii) decision makers?'&&item.infoDetails">
          <div>
            <ul>
              <li>公司的股票代码</li>
              <li>{{ item.infoDetails.daima }}</li>
            </ul>
          </div>
        </div>
        <div v-if="item.infoType.dictValue==='whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?'&&item.infoDetails">
          <div>
            <ul>
              <li>公司名称</li>
              <li>{{ item.infoDetails.guzhuXiang }}</li>
            </ul>
            <ul>
              <li>公司 国家/地区</li>
              <li>{{ item.infoDetails.state }}</li>
            </ul>
            <ul>
              <li>公司地址</li>
              <li>{{ item.infoDetails.site }}{{ item.infoDetails.site1 }}{{ item.infoDetails.province }}{{ item.infoDetails.city }}</li>
            </ul>
            <ul>
              <li>邮政编码</li>
              <li>{{ item.infoDetails.postalCode }}</li>
            </ul>
            <ul>
              <li>电邮地址</li>
              <li>{{ item.infoDetails.postalAddress }}</li>
            </ul>
            <ul>
              <li>电话号码</li>
              <li>{{ item.infoDetails.phoneNumber }}</li>
            </ul>
            <ul>
              <li>注册人姓名</li>
              <li>{{ item.infoDetails.zhuceName }}</li>
            </ul>
            <ul>
              <li>注册人与机构的关联/关系</li>
              <li>{{ item.infoDetails.zhuceGuanxi }}</li>
            </ul>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Regulate',
  props: {
    userInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      rows: 1,
      // countryOptions: [], // 国家信息
      regulateData: '',
      isBoolean1: '',
      isBoolean2: '',
      personal: { // 个人信息
        regulateOneList: [],
        regulateTwoList: []
      },
      limitedLiablity: {// 有限公司
        regulateList: []
      },
      partnerShip: {// 合伙企业
        regulateList: []
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
  mounted() {
    this.Port();
  },
  methods: {
    // personal
    // params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale')}
    // 请求接口后缀  G 个人 Y 有限 H 合伙
    Port() {
      this.isBoolean1 = '';
      this.isBoolean2 = '';
      this.personal.regulateOneList = []; // 需要清空数据 ,组件会缓存数据
      this.personal.regulateTwoList = [];
      this.limitedLiablity.regulateList = [];
      this.partnerShip.regulateList = [];
      this.regulateData = '';
      if (this.userInfo.accountType === 'personal') {
        this.personalRegulationG();
      } else if (this.userInfo.accountType === 'limitedLiablity') {
        this.accountCoRegulatoryY();
      } else if (this.userInfo.accountType === 'partnerShip') {
        this.accountCoRegulatoryH();
      }
    },
    personalRegulationG() { // 获取个人规管信息
      this.$http({
        url: '/account/registerOpen/personal/findRegulatory',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (!res.object[0].employer && !res.object[0].familyMembersCompany && !res.object[0].relationshipAccountHolders.dictDest) {
                this.isBoolean1 = '否';
              } else {
                this.isBoolean1 = '是';
              }
              if (!res.object[0].accountHolderCompanyStock) {
                this.isBoolean2 = '否';
              } else {
                this.isBoolean2 = '是';
              }
              this.regulateData = res.object;
              if (res.object[0].employer) {
                var employer = {
                  companyNameTitle: '公司名称',
                  companyNameValue: res.object[0].employer
                };
              } else {
                employer = '';
              }
              if (res.object[0].relationshipAccountHolders) {
                var relationshipAccountHolders = {
                  companyNameTitle: '与账户持有人关系',
                  companyNameValue: res.object[0].relationshipAccountHolders.dictDest
                };
              } else {
                relationshipAccountHolders = '';
              }
              if (res.object[0].familyMembersCompany) {
                var familyMembersCompany = {
                  companyNameTitle: '与公司有关的家庭成员',
                  companyNameValue: res.object[0].familyMembersCompany
                };
              } else {
                familyMembersCompany = '';
              }

              this.personal.regulateOneList = [employer, relationshipAccountHolders, familyMembersCompany];
              if (res.object[0].accountHolderCompanyStock) {
                this.personal.regulateTwoList = [
                  {
                    companyNameTitle: '股票代码',
                    companyNameValue: res.object[0].accountHolderCompanyStock
                  }
                ];
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
    // getCountryOptions() { // 获取所有国家
    //   this.$http({
    //     url: '/common/region/findAll',
    //     method: 'get',
    //     params: { dictCode: 'country' }
    //   })
    //     .then(res => {
    //       if (res.code === 200) {
    //         this.countryOptions = res.object;
    //       } else {
    //         this.$message.error(res.message || res.code);
    //       }
    //     })
    //     .catch(error => {
    //       this.$message.error(error);
    //     });
    // },
    accountCoRegulatoryY() { // 获取公司规管信息
      this.$http({
        url: '/account/registerOpen/limitedLiablity/findRegulatory',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].infoDetails !== '') {
                  res.object[i].infoDetails = JSON.parse(res.object[i].infoDetails);
                }
              }
              res.object = res.object.map(item => {
                // this.getCountryOptions()
                if (item.infoType.dictCode === 'regulatoryInfo' && item.infoType.dictValue === 'whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?') {
                  this.$http({
                    url: '/common/region/data',
                    method: 'post',
                    params: { rows: 1, sCode: item.infoDetails.state }
                  }).then(res => {
                    item.infoDetails.state = res.rows[0].cnName;
                    // res.rows[0].cnName
                  });
                }
                return item;
              });
              this.limitedLiablity.regulateList = res.object;

              // console.log(res,'-----------this.limitedLiablity.regulateList');
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    accountCoRegulatoryH() { // 获取合伙企业规管信息
      this.$http({
        url: '/account/registerOpen/partnerShip/findRegulatory',
        method: 'get',
        params: { accountId: this.userInfo.id, lang: this.$Cookies.get('locale') }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].infoDetails !== '') {
                  res.object[i].infoDetails = JSON.parse(res.object[i].infoDetails);
                }
              }
              res.object = res.object.map(item => {
                if (item.infoType.dictCode === 'regulatoryInfo' && item.infoType.dictValue === 'whether any principal or authorized trader of the institution is a registered broker;An employee, director or owner of a securities or commodity brokerage company;Employees of Banks, hedge funds or other financial services companies?') {
                  this.$http({
                    url: '/common/region/data',
                    method: 'post',
                    params: { rows: 1, sCode: item.infoDetails.state }
                  }).then(res => {
                    item.infoDetails.state = res.rows[0].cnName;
                    // res.rows[0].cnName
                  });
                }

                // this.countryOptions.map(item1 => {
                //   if (item1.sCode === item.infoDetails.state) {
                //     item.infoDetails.state = item1.cnName;
                //   }
                //   return item1;
                // });
                return item;
              });
              this.partnerShip.regulateList = res.object;
              // console.log(res);
            }
          } else {
            this.$message.error(res.message || res.code);
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
  .regulateContent {
    ul {
      padding: 10px 0;
      overflow:hidden;
      margin-bottom: 0px;
      margin-top: 0px;
      margin-left: 10px;
      &:hover{
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
        word-wrap:break-word;
        text-align: right;
      }
      li:nth-child(n+3){
        width: 50%;
        word-wrap:break-word;
        text-align: right;
      }
    }
  }

</style>
