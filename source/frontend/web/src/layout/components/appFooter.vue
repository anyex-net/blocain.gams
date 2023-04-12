<template>
  <div class="app-footer">
    <div class="ft-wrapper">
      <el-row :gutter="15">
        <el-col :span="5">
          <div class="ft-container">
            <p class="ft-title">{{ $t('common.footer.about_us') }}<!--关于我们--></p>
            <router-link to="/about/introduction">
              <p class="ft-txt">{{ $t('common.footer.company_profile') }}<!--公司简介--></p>
            </router-link>
            <router-link to="/about/development">
              <p class="ft-txt">{{ $t('common.footer.development_path') }}<!--发展历程--></p>
            </router-link>
            <router-link to="/about/contactUs">
              <p class="ft-txt">{{ $t('common.footer.contact_us') }}<!--联系我们--></p>
            </router-link>
          </div>
        </el-col>
        <el-col :span="5">
          <div class="ft-container">
            <p class="ft-title">{{ $t('common.footer.agreement') }}<!--协议--></p>
            <router-link to="/agreement/disclaimer"><p class="ft-txt">{{ $t('common.footer.disclaimer') }}<!--免责声明--></p></router-link>
            <router-link to="/agreement/registrationAgreement"><p class="ft-txt">{{ $t('common.footer.registration_agreement') }}<!--注册协议--></p></router-link>
          </div>
        </el-col>
        <el-col :span="5" :offset="2">
          <div class="ft-container">
            <p class="ft-title">{{ $t('common.footer.hot_line') }}<!--咨询电话--></p>
            <p class="ft-txt" style="font-size: 25px;color: #FFF;">400-6589-990</p>
            <p class="ft-txt">{{ $t('common.footer.service_time') }}<!--服务时间-->：7*24{{ $t('common.footer.hours') }}<!--小时--></p>
          </div>
        </el-col>
        <el-col :span="5" :offset="2">
          <div class="ft-container">
            <a href="https://www.facebook.com/mofsecurities/?modal=composer" target="_blank"><img class="ft-icon" src="@/assets/images/common/footer_face.png" /></a>
            <a href="https://www.linkedin.com/in/%E7%91%9E-%E5%9C%8B-429756192/" target="_blank"><img class="ft-icon" src="@/assets/images/common/footer_in.png" /></a>
            <a href="https://twitter.com/mof_securities" target="_blank"><img class="ft-icon" src="@/assets/images/common/footer_twitter.png" /></a>
            <img class="ft-icon" src="@/assets/images/common/footer_we.png" @mouseover="mouseOver" @mouseleave="mouseLeave" />
          </div>
          <img v-show="seen" class="ft-img" src="@/assets/images/common/footer_file-read.png"/>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <p class="ft-txt">{{ $t('common.footer.description_1') }}</p>
      <p class="ft-txt">{{ $t('common.footer.description_2') }}</p>
      <p class="ft-txt">{{ $t('common.footer.description_3') }}</p>
      <el-divider></el-divider>
      <p class="ft-txt">{{ $t('common.footer.description_4') }}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppFooter',
  data() {
    return {
      seen: false
    };
  },
  watch: {
    '$route': function() {
      document.body.scrollTop = 0;
      document.documentElement.scrollTop = 0;
    }
  },
  methods: {
    mouseOver() {
      this.seen = true;
    },
    mouseLeave() {
      this.seen = false;
    }
  }
};
</script>

<style>
  .app-footer .el-divider {
    background-color: #333333;
    margin: 20px 0;
  }
</style>

<style lang="scss" scoped>
  .app-footer {
    background: #000000;
    .ft-wrapper {
      width: 1200px;
      margin: 0 auto;
      height: 400px;
      .ft-txt {
        color: #CCCCCC;
        font-size: 14px;
        margin: 10px 0;
      }
      .ft-container {
        padding-top: 30px;
        .ft-title {
          color: #FEFEFE;
          font-size: 18px;
          margin-bottom: 30px;
        }
        .ft-icon {
          width: 30px;
          margin: 10px 2px;
          cursor: pointer;
        }
      }
      .ft-img {
        width: 100px;
        height: 100px;
        background-color: white;
        margin-left: 45px;
        position: absolute;
        z-index: 1000;
      }
    }
  }

</style>
