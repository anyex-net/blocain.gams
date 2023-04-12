<template>
  <div class="accountOverview">
    <!-- 上部分 -->
    <div class="container">
      <el-row>
        <el-col :span="2">用户名：</el-col>
        <el-col :span="22">{{ userName }}</el-col>
      </el-row>
      <el-row>
        <el-col :span="2">
          <span>IB账户：</span>
        </el-col>
        <!-- <el-col :span="5"><span>{{ ibAccountCode }}</span></el-col> -->
        <el-col :span="5">
          <span>{{ ibAccountCode }}</span>
          <a v-if="!ibAccountCode" class="modifier" @click="updateIBAccount">修改</a>
        </el-col>
        <el-col :span="2">
          <span>客户类型：</span>
        </el-col>
        <el-col :span="9">
          <span>{{ accountType }}</span>
        </el-col>
        <!-- <el-col :span="6"><span>资产净值</span></el-col> -->
      </el-row>
      <el-row>
        <el-col :span="2">
          <span>状态</span>
        </el-col>
        <el-col :span="5">
          <span>
            <div v-if="state==='正常'" class="circle"></div>
            <div v-if="state==='冻结'||state==='注销'" class="circleGray"></div>
            {{ state }}
          </span>
        </el-col>
        <el-col :span="2">
          <span>账户类型：</span>
        </el-col>
        <el-col v-if="customerType" :span="9">
          <div class="customerType">{{ customerType }}</div>
        </el-col>
        <el-col v-else :span="9">
          <div class="customerType">无</div>
        </el-col>
        <!-- <el-col :span="6"><span class="assetNumber">578778.45</span></el-col> -->
      </el-row>
      <el-row>
        <el-col :span="2">
          <span>手机账号：</span>
        </el-col>
        <el-col :span="5">
          <span>{{ region }}-{{ phoneNum }}</span>
        </el-col>
        <el-col :span="2">
          <span>邮箱账号：</span>
        </el-col>
        <el-col :span="9">
          <span>{{ email }}</span>
        </el-col>
        <!-- <el-col :span="6"><span>可用资金</span></el-col> -->
      </el-row>
      <el-row>
        <el-col :span="2">注册时间：</el-col>
        <el-col :span="16">{{ createDate }}</el-col>
        <!-- <el-col :span="6"><span class="number">578778.45</span></el-col> -->
      </el-row>
      <!-- <el-aside width="490px">
        <h1>{{ userName }}</h1>
        <el-row>
          <el-col :span="5"><span>IB账户</span></el-col>
          <el-col :span="5"><span>{{ ibAccountCode }}</span></el-col>
          <el-col :span="14"><a class="modifier" @click="updateIBAccount">修改</a></el-col>
        </el-row>
        <el-row>
          <el-col :span="5"><span>状态</span></el-col>
          <el-col :span="19"><span>
            <div v-if="state==='正常'" class="circle"></div>
            <div v-if="state==='冻结'||state==='注销'" class="circleGray"></div>
            {{ state }}</span></el-col>
        </el-row>
        <el-row>
          <el-col :span="5"><span>手机账号</span></el-col>
          <el-col :span="19"><span>{{ region }}-{{ phoneNum }}</span></el-col>
        </el-row>
        <el-row>
          <el-col :span="5"><span>邮箱账号</span></el-col>
          <el-col :span="19"><span>{{ email }}</span></el-col>
        </el-row>
      </el-aside>-->
      <!-- <el-container>
        <el-header style="height:160px">
          <div class="head">
            <el-row>
              <el-col :span="12">
                <span>资产净值</span>
              </el-col>
              <el-col :span="12">
                <span>可用资金</span>
              </el-col>
            </el-row>
          </div>

          <div class="head">
            <el-row>
              <el-col :span="12">
                <span>
                  <span class="usd">{{ baseCurrency }}</span>
                  <span class="number">578778.45</span>
                </span>
              </el-col>
              <el-col :span="12">
                <span>
                  <span class="usd">{{ baseCurrency }}</span>
                  <span class="number">578778.45</span>
                </span>
              </el-col>
            </el-row>
          </div>
        </el-header>
        <el-main>
          <el-row>
            <el-col :span="12">
              <div class="mleft">
                <span>客户类型</span>
                <span>{{ accountType }}</span>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div class="mleft">
                <span>账户类型</span>
                <span>{{ customerType }}</span>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <div class="mleft">
                <span>注册时间</span>
                <span>{{ createDate }}</span>
              </div>
            </el-col>
      </el-row>-->
      <!-- </el-main>
      </el-container>-->
    </div>
    <!-- 下部分 -->
    <div class="bottom">
      <h3>基本信息</h3>
      <div class="bottomLi">
        <span>邮箱</span>
        <span>{{ email }}</span>
      </div>
      <div class="bottomLi">
        <span>国家/地区</span>
        <span>{{ country }}</span>
      </div>
      <div v-if="accountType==='个人'" class="bottomLi">
        <span>称呼</span>
        <span>{{ appellation }}</span>
      </div>
      <div v-if="accountType==='个人'" class="bottomLi">
        <span>姓名</span>
        <span>{{ customerName }}</span>
      </div>
      <div v-if="accountType!=='个人'" class="bottomLi">
        <span>公司名称</span>
        <span>{{ accountName }}</span>
      </div>
      <div v-if="accountType==='个人'" class="bottomLi">
        <span>出生日期</span>
        <span>{{ birthday }}</span>
      </div>
      <div v-if="accountType==='个人'" class="bottomLi">
        <span>性别</span>
        <span>{{ gender }}</span>
      </div>
      <div v-if="accountType==='个人'">
        <div v-for="(item,index) in addressList " :key="index+'addressList'" class="bottomLi">
          <span>地址({{ index+1 }})</span>
          <span>
            {{ item.country.cnName }}
            {{ item.rovince }}
            {{ item.city }}
            {{ item.address }}
            {{ item.zipCode }}
          </span>
        </div>
      </div>

      <div v-if="accountType!=='个人'" class="bottomLi">
        <span>网址</span>
        <span>{{ Url }}</span>
      </div>
      <div v-if="accountType==='个人'">
        <div v-for="(item,index) in mailingAddress " :key="index+'mailingAddress'" class="bottomLi">
          <!-- 个人所有 -->
          <span>邮寄地址(选)</span>
          <span>
            {{ item.country.cnName }}
            {{ item.rovince }}
            {{ item.city }}
            {{ item.address }}
            {{ item.zipCode }}
          </span>
        </div>
      </div>
      <div v-if="accountType!=='个人'">
        <div v-for="(item,index) in addressList " :key="index+'addressList1'" class="bottomLi">
          <!-- 公司企业所有 -->
          <span>主要经营地点({{ index+1 }})</span>
          <span>
            {{ item.country.cnName }}
            {{ item.rovince }}
            {{ item.city }}
            {{ item.address }}
            {{ item.zipCode }}
          </span>
        </div>
      </div>

      <div v-for="(item,index) in contactList " :key="index+'contactList'" class="bottomLi">
        <span>联系信息({{ index+1 }})</span>
        <span>
          {{ item.contactNumberType.dictDest }}
          {{ item.country.cnName }}
          {{ item.contactNumber }}
        </span>
      </div>
      <div
        v-for="(item,index) in wealthSourceList "
        :key="index+'wealthSourceList'"
        class="bottomLi"
      >
        <span>财富来源({{ index+1 }})</span>
        <span>{{ item.incomeType.dictDest }}</span>
        <span v-if="item.percentageOfFunds">{{ item.percentageOfFunds }}%</span>
        <span>{{ item.sourceDesc }}</span>
      </div>
    </div>
    <!-- 修改IB账号弹框 -->
    <el-dialog title="绑定IB账户" :visible.sync="resetIBAccountDialog">
      <el-form ref="userForm" :model="userForm" :rules="userRules">
        <el-form-item label="账户名称" :label-width="formLabelWidth">
          <el-input v-model="userForm.accountName" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <!-- <el-form-item label="基础货币" :label-width="formLabelWidth">
          <el-input v-model="userForm.baseCurrency" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>-->
        <el-form-item label="基础货币" class="baseCurrency">
          <el-select v-model="userForm.baseCurrency" filterable placeholder="基础货币" clearable>
            <el-option
              v-for="(item,index) in baseCurrencyList"
              :key="index"
              :label="item.dictDest"
              :value="item.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="IB账号" :label-width="formLabelWidth" prop="IBAccount">
          <el-input
            v-model.trim="userForm.IBAccount"
            autocomplete="off"
            type="text"
            placeholder="请输入IB账号"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="resetIBAccountDialog = false">取 消</el-button>
        <el-button type="primary" @click="confirmSendIB('userForm')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  props: {
    accountInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    var validateIBAccount = (rule, value, callback) => {
      if (value.length > 10) {
        callback(new Error('账户长度在10位以内'));
      } else if (value === '') {
        callback(new Error('请输入要绑定的IB账户'));
      } else {
        callback();
      }
    };
    return {
      userName: null, // 用户名
      state: null,
      phoneNum: null,
      email: null,
      customerType: null,
      accountType: null, // 客户类型
      // baseCurrency: null,
      baseCurrencyList: [],
      createDate: null,
      accountId: null,
      country: null,
      gender: null,
      customerName: null,
      birthday: null,
      addressList: [],
      mailingAddress: [],
      Url: null,
      contactList: [],
      wealthSourceList: [], // 财富来源
      appellation: null,
      ibAccountCode: null,
      resetIBAccountDialog: false, // 修改IB账户
      accountName: null, // 公司名称
      userForm: {
        // 新绑定的IB账户信息
        accountName: null,
        IBAccount: null,
        baseCurrency: null
      },
      userRules: {
        IBAccount: [
          { validator: validateIBAccount, required: true, trigger: 'blur' }
          //   { validator: validateIBAccount, required: true, message: '请输入的IB账号', trigger: 'blur' }
        ]
      },
      formLabelWidth: '120px'
    };
  },
  created() {
    // console.log(this.accountInfo,'overthis.accountInfo')
    this.init();
    this.getBasicInformation();
    this.getIbAccountCode();
    this.getBaseCurrency();
  },
  methods: {
    init() {
      const accountInfo = this.accountInfo;
      this.userName = accountInfo.accountName;
      this.userForm.accountName = accountInfo.accountName;
      this.state = accountInfo.status;
      this.phoneNum = accountInfo.mobile;
      this.region = accountInfo.region;
      this.email = accountInfo.email;
      this.customerType = accountInfo.accountPortfolioTypeDest; // 账号类型
      this.accountType = accountInfo.accounType; // 客户类型
      // this.baseCurrency = accountInfo.baseCurrency;
      // this.userForm.baseCurrency = accountInfo.baseCurrency;
      debugger;
      var date = new Date(accountInfo.createDate); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + '-';
      var M =
        (date.getMonth() + 1 < 10
          ? '0' + (date.getMonth() + 1)
          : date.getMonth() + 1) + '-';
      var D = date.getDate() + ' ';
      var h =
        (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
      var m =
        (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) +
        ':';
      var s =
        date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds();
      this.createDate = Y + M + D + h + m + s;
      this.accountId = accountInfo.accountId;
      this.accountName = accountInfo.accountName;
      this.unid = accountInfo.unid;
    },
    getBasicInformation() {
      this.$http({
        url: '/account/management/basicInformation',
        method: 'get',
        params: { accountId: this.accountId, unid: this.unid, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            //  console.log(res,'zuixin')
            const data = res.object;
            this.country = data.country.cnName;
            this.appellation = data.appellation.dictDest;
            this.customerName = data.customerName;
            this.birthday = data.birthday;
            // if (data.birthday !== '') {
            //   var birthday = new Date(data.birthday * 1); // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
            //   var Y = birthday.getFullYear() + '-';
            //   var M =
            //     (birthday.getMonth() + 1 < 10
            //       ? '0' + (birthday.getMonth() + 1)
            //       : birthday.getMonth() + 1) + '-';
            //   var D = birthday.getDate() + ' ';
            //   this.birthday = Y + M + D;
            // }
            this.gender = data.gender.dictDest;
            data.addressList = data.addressList.map(item => {
              item.address = item.address.replace(/-#-/, ' ');
              return item;
            });
            data.addressList = data.addressList.map(item => {
              if (item.addressType === 'mail') {
                this.mailingAddress.push(item);
              } else if (item.addressType === 'personalAddress') {
                this.addressList.push(item);
              }
            });
            this.Url = data.url;
            this.contactList = data.contactList;
            this.wealthSourceList = data.wealthSourceList;
            // this.ibAccountCode = data.ibAccountCode;
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message.error(error);
        });
    },
    getIbAccountCode() {
      this.$http({
        url: '/account/manage/ib/getIbAccountInfo',
        method: 'get',
        params: { accountId: this.accountId }
      })
        .then(res => {
          if (res.code === 200) {
            this.ibAccountCode = res.object.ibAccountCode;
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
    },
    updateIBAccount() {
      // 修改IB账号
      this.resetIBAccountDialog = true;
      this.userForm.IBAccount = '';
      if (this.$refs['userForm']) {
        this.$refs['userForm'].clearValidate();
      }
    },
    confirmSendIB(userForm) {
      this.$refs[userForm].validate(valid => {
        if (valid) {
          this.resetIBAccountDialog = false;
          this.$http({
            url: '/account/manage/ib/updateIbAccountInfo',
            method: 'post',
            params: {
              accountId: this.accountId,
              ibAccountCode: this.userForm.IBAccount,
              baseCurrency: this.userForm.baseCurrency
            }
          })
            .then(res => {
              if (res.code === 200) {
                this.ibAccountCode = this.userForm.IBAccount;
                this.$message('已成功绑定IB账户');
              } else {
                this.$message(res.message);
              }
            })
            .catch(error => {
              this.$message(error);
            });
        } else {
          return false;
        }
      });
    },
    getBaseCurrency() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'baseCurrency', lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.baseCurrencyList = res.object;
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
    }
  }
};
</script>
<style lang="scss" scoped>
.accountOverview {
  .bottomLi:hover {
    background: #eee;
  }
  .container {
    border: 1px solid #e6e6e6;
    border-radius: 4px;
    padding: 20px;
    background-color: #fff;
  }
  .number {
    font-size: 24px;
  }
  .circle {
    margin-right: 8px;
    width: 10px;
    height: 10px;
    border-radius: 10px;
    background-color: rgb(0, 204, 0);
    display: inline-block;
  }
  .circleGray {
    margin-right: 8px;
    width: 10px;
    height: 10px;
    border-radius: 10px;
    background-color: gray;
    display: inline-block;
  }
  .modifier {
    text-decoration-line: underline;
    font-size: 12px;
    color: #666;
    &:hover {
      cursor: pointer;
      color: red;
    }
  }
}
</style>

<style lang="scss">
.accountOverview {
  padding: 10px;
  .el-row:hover {
    background-color: #eee;
  }
  .el-row {
    display: block;
    line-height: 30px;
    font-size: 14px;
    text-align: left;
  }
  .bottom {
    border: 1px solid #e6e6e6;
    border-radius: 4px;
    padding: 20px;
    background-color: #fff;
    margin-top: 20px;
    .bottomLi {
      font-size: 14px;
      width: 100%;
      overflow: hidden;
      line-height: 30px;
      display: block;
      span {
        &:nth-child(1) {
          float: left;
          width: 200px;
        }
        &:nth-child(2) {
          float: left;
        }
      }
    }
  }
  .baseCurrency {
    display: flex;
    .el-form-item__label {
      width: 120px;
    }
    .el-form-item__content {
      flex: 1;
    }
    .el-select {
      display: block;
    }
  }
  .el-main {
    background-color: #fff;
    color: #333;
    text-align: center;
    line-height: 160px;
    border: 1px solid #e6e6e6;
    border-radius: 5px;
  }
}
</style>
