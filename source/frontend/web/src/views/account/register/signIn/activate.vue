
<template>
  <div>
    <div id="ZC-regiMain">
      <div class="contentBOX" style="width: 80%;height: 300px;margin-top: 50px;">
        <el-form
          autocomplete="off"
          status-icon
          label-width="200px"
          class="demo-ruleForm"
        >
          <div class="formContent">

            <div class="formNape">
              <div class="formNapeTitle">
                <ul>
                  <li>{{ accountState }}</li>
                  <li v-show="isshoudong">{{ $t('registerOpenAccount.register.activate.countDown') }}</li>  <!-- $i18n - 倒计时 -->
                  <span v-show="isshoudong" class="liTherr">{{ accountState1 }}</span>
                </ul>
                <p v-show="isshoudong" class="xiaoshou shoudong" style="color: rgb(202, 75, 75);" @click="shoudongtiaozhuan()">{{ $t('registerOpenAccount.register.activate.skip') }}</p> <!-- $i18n - 手动跳转 -->
              </div>
              <div class="formNapeContent" />
            </div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      verificationCode: '',
      activationCode: '',
      accountState: '',
      accountState1: 5,
      // timer: '',
      // value: 0
      isshoudong: true,
      timerId: null
    };
  },
  created() {
    this.accountState = this.$i18n.t('registerOpenAccount.register.activate.ActivateInterface'); // $i18n - 激活界面
    this.activateAccount();
  },
  //   清空定时器
  beforeDestroy() {
    clearInterval(this.timerId);
  },

  methods: {
    Request() {
      let name; let value; let str = location.href; let num = str.indexOf('?'); // 取得整个地址栏
      str = str.substr(num + 1); // 取得所有参数 stringvar.substr(start [, length ]
      const arr = str.split('&'); // 各个参数放到数组里
      for (let i = 0; i < arr.length; i++) {
        num = arr[i].indexOf('=');
        if (num > 0) {
          name = arr[i].substring(0, num);
          value = arr[i].substr(num + 1);
          this[name] = value;
        }
      }
      return this[name];
    },
    // 激活账户
    activateAccount() {
      const Request1 = this.Request();
      this.$http({
        url: '/register/accountActivation',
        method: 'post',
        params: {
          code: Request1
        }
      })
        .then(res => {
          if (res.code === 200) {
            this.accountState = this.$i18n.t('registerOpenAccount.register.activate.succeed');
            this.isshoudong = true;
            this.timerTask();
          } else {
            this.$message.error('' + res.code + '');
            this.accountState = this.$i18n.t('registerOpenAccount.register.activate.defeated');
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
          this.accountState = this.$i18n.t('registerOpenAccount.register.activate.defeated');
        });
    },
    // 阿拉伯数字问题
    ChineseToNumber(chnStr) {
      const chnNameValue = {
        十: { value: 10, secUnit: false },
        百: { value: 100, secUnit: false },
        千: { value: 1000, secUnit: false },
        万: { value: 10000, secUnit: true },
        亿: { value: 100000000, secUnit: true }
      };
      const chnNumChar = this.accountState1;
      let rtn = 0;
      let section = 0;
      let number = 0;
      let secUnit = false;
      const str = chnStr.split('');

      for (var i = 0; i < str.length; i++) {
        var num = chnNumChar[str[i]];
        if (typeof num !== 'undefined') {
          number = num;
          if (i === str.length - 1) {
            section += number;
          }
        } else {
          var unit = chnNameValue[str[i]].value;
          secUnit = chnNameValue[str[i]].secUnit;
          if (secUnit) {
            section = (section + number) * unit;
            rtn += section;
            section = 0;
          } else {
            section += (number * unit);
          }
          number = 0;
        }
      }
      return rtn + section;
    },

    // 倒计时
    timerTask() {
      let time11 = 5;
      this.timerId = setInterval(() => {
        time11--;
        this.$set(this, 'accountState1', time11);

        if (time11 === 0) {
          this.$router.push({
            path: '/login'
          });
        }
      }, 1000);
    },
    // 手动跳转
    shoudongtiaozhuan() {
      this.$router.push({
        path: '/login'
      });
    }

  }

};
</script>
<style scoped>

#ZC-regiMain ul  {
    height: 54px;
    width: 800px;
    margin-left: 140px;
    margin-top: 50px;
}
#ZC-regiMain ul li {
    float: left;
    font-size: 16px;
    color: #666;
    font-weight: bold;

}
#ZC-regiMain ul li:nth-child(2) {
    margin-left: 100px;
    color: #999;

}
#ZC-regiMain ul .liTherr {
    font-size: 39px;
    font-weight: 100;
    line-height: 34px;
    color: rgb(202, 75, 75);
    line-height: 18px;
    margin-left: 18px;
}
.shoudong {
    margin-left: 140px;
}

</style>
