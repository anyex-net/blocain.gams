<template>
  <div>
    <div id="ZC-regiMain" style="overflow-y:scroll;overflow-x: hidden;">
      <div class="topBG">
        <div class="titleIMG">- {{ $t('registerOpenAccount.header.registerTitel') }} -</div> <!-- 注册开户 -->
      </div>
      <div class="contentBOX" style="height: 500px;">
        <div class="contentBOXdignwei" style="border: none;">
          <div class="formContent openType">
            <el-form autocomplete="off" label-width="0px" class="demo-ruleForm">
              <el-radio-group v-model="typeID">
                <div v-for="(item,index) in RegistrationType" :key="index">
                  <el-radio :label="item.dictValue">{{ item.dictDest }}</el-radio>
                </div>
              </el-radio-group>
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm()">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button> <!-- 保存&amp;继续 -->
                  </el-form-item>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>
      <el-dialog :title="$t('registerOpenAccount.register.openType.hint')" :visible.sync="dialogVisible" width="30%">  <!-- 提示 -->
        <span style="font-size:18px;color:#FF5949">{{ $t('registerOpenAccount.register.openType.clientType') }}</span>  <!-- 请选择客户类型 -->
        <span slot="footer" class="dialog-footer" />
      </el-dialog>
    </div>
  </div>
</template>

<script>
import cookies from 'js-cookie';
export default {

  data() {
    return {
      name: 'OpenType',
      dialogVisible: false,
      typeID: '',
      RegistrationType: [
      ]
    };
  },

  created() {
    cookies.set('locale', this.$i18n.locale);
    this.accountType();
  },
  mounted: function() {

  },
  methods: {
    accountType() {
      const lang = this.$Cookies.get('locale') || 'zh_CN';
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'accountType', lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.RegistrationType = res.object;
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    submitForm() {
      if (this.typeID) {
        this.$router.push({
          path: '/register/signIn',
          query: { typeID: this.typeID }
        });
      } else {
        this.dialogVisible = true;
        this.delayedRequest();
      }
    },
    delayedRequest() {
      let timingTime = 3;
      const timerId = setInterval(() => {
        timingTime--;
        if (timingTime === 0) {
          this.dialogVisible = false;
          clearInterval(timerId);
        }
      }, 500);
    }
  }
};
</script>

<style  scoped>
/* 按钮 */
.el-button--danger > span {
  color: #fff;
}
#ZC-regiMain .el-radio {
  /* 单选框行高 */
  margin-bottom: 12px;
  font-size: 20px;
  color: #999;
}
</style>

