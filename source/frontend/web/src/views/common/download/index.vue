<template>
  <div class="app-container">
    <img class="download-banner" :src="getImgUrl('/common/banner_image_download'+'_'+$i18n.locale+'.png')">
    <div class="module-1">
      <div class="title">
        <div class="title-1">{{ $t('common.download.name') }}<!--国瑞证券--></div>
        <span class="title-2">{{ $t('common.download.name_txt') }}</span>
      </div>
      <div>
        <img class="img-main" src="../../../assets/images/common/download_main.png">
        <div class="title-txt">
          <p class="txt-1">{{ $t('common.download.content_title_1') }}<!--全球投资--></p>
          <p class="txt-2">{{ $t('common.download.content_txt_1') }}<!--关于国瑞--></p>
          <p class="txt-1">{{ $t('common.download.content_title_2') }}<!--超低佣金--></p>
          <p class="txt-2">{{ $t('common.download.content_txt_2') }}<!--关于国瑞--></p>
          <p class="txt-1">{{ $t('common.download.content_title_3') }}<!--极速体验--></p>
          <p class="txt-2">{{ $t('common.download.content_txt_3') }}<!--关于国瑞--></p>
        </div>
      </div>
    </div>
    <div class="background-2">
      <span class="txt">{{ $t('common.download.coming_soon') }}<!--敬请期待--></span>
    </div>
    <div class="TWS">
      <p class="TWS-title">GAMS</p>
      <div class="TWS-img" />
    </div>
    <div class="TWS-txt">
      <div class="TWS-txt_1">{{ $t('common.download.download_title') }}<!--专业的全球证券市场交易平台--></div>
    </div>
    <div class="download">
      <div class="down-ios">
        <img src="../../../assets/images/common/TWS_ios_download.png" @mouseenter="two_code = 1" @mouseleave="two_code = 0">
        <div v-show="two_code === 1" class="hover_two_code">
          <img src="../../../assets/images/common/download/ios.png">
        </div>
      </div>
      <div class="download-img-ios" />
      <div class="down-android">
        <img src="../../../assets/images/common/TWS_android_download.png" @mouseenter="two_code = 2" @mouseleave="two_code = 0">
        <div v-show="two_code === 2" class="hover_two_code">
          <img src="../../../assets/images/common/download/android.png">
        </div>
      </div>
      <div class="download-img-android" />
      <div class="down-pc">
        <img src="../../../assets/images/common/TWS_PC_download.png" @mouseenter="two_code = 3" @mouseleave="two_code = 0">
        <!-- <div v-show="two_code === 3" class="hover_two_code">
          <img src="../../../assets/images/common/download/code.png">
        </div> -->
      </div>
    </div>
    <el-row class="way">
      <el-col :span="8">
        <a :href="this.$i18n.locale === 'zh_CN' ? 'https://www.clientam.com/cn/handytrader/iphone/index.php' : 'https://www.clientam.com/en/handytrader/iphone/index.php'" target="blank">
          <el-button type="danger" plain>{{ $t('common.download.button_ios') }}<!--IOS 下载--></el-button>
        </a>
      </el-col>
      <el-col :span="8">
        <a :href="this.$i18n.locale === 'zh_CN' ? 'https://www.clientam.com/cn/handytrader/android/index.php' : 'https://www.clientam.com/en/handytrader/android/index.php'" target="blank">
          <el-button type="danger" plain>{{ $t('common.download.button_android') }}<!--Android 下载--></el-button>
        </a>
      </el-col>
      <el-col :span="8">
        <a href="https://www.clientam.com/en/index.php?f=16040&ns=T&conf=am&wbid=MOF2020" target="blank">
          <el-button type="danger" plain style="margin-left: -95px;">{{ $t('common.download.button_pc') }}<!--PC 下载--></el-button>
        </a>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  name: 'Download',
  data() {
    return {
      two_code: 0
    };
  },
  methods: {
    getImgUrl(image) {
      return require('@/assets/images' + image);
    }
  }
};
</script>
<style lang="scss">
  .el-button--danger.is-plain {
    margin-left: -80px;
    background-color:#ffffff;
    border-color: #4d4d4db8;
    color: #4d4d4db8;
  }
</style>

<style lang="scss" scoped>
  .app-container {
    background-color: #ffffff;
    .download-banner {
      width: 100%;
    }
    .background-2 {
      height: 45px;
      background: linear-gradient(129deg, #1F8EFF, #1F5EFF);
      padding-top: 25px;
      margin: auto;
      .txt {
        font-size: 24px;
        font-weight: 400;
        color: #FFFFFF;
        margin: auto;
      }
    }
    .module-1 {
      width: 1200px;
      height: 300px;
      margin: auto;
      .title {
        border-left: 6px solid #ff6571;
        margin: 20px;
        padding: 3px;
        .title-img {
          width: 6px;
          height: 30px;
          background: #ff6571;
          float: left;
          margin-right: 20px;
        }
      .title-1 {
        margin-left: 10px;
        font-size: 24px;
        font-weight: bold;
        display: inline-block;
      }
      .title-2 {
        font-size: 14px;
        font-weight: 400;
        margin-left: 30px;
        margin-top: 6px;
        display: inline-block;
      }
    }
  }
    .img-main {
      width: 650px;
      float: left;
      max-width:100%;
      max-height:100%;
      margin-right: 200px;
    }
    .title-txt {
      margin-top: 20px;
      line-height: 22px;
      width: 300px;
      height: 200px;
      float: left;
      .txt-1 {
        font-size: 18px;
        font-weight: bold;
      }
      .txt-2 {
        font-size: 14px;
        font-weight: 400;
        margin-bottom: 20px;
      }
    }
    .TWS {
      width: 300px;
      margin: auto;
      .TWS-title {
        width: 160px;
        height: 50px;
        font-size: 24px;
        font-weight: bold;
        margin: auto;
      }
      .TWS-img {
        width:27px;
        height:5px;
        background:#FF6571;
        margin-left: 93px;
        margin-bottom: 10px;
        margin-top: 3px;
      }
    }
    .TWS-txt {
      width: 480px;
      height: 30px;
      margin: auto;
      text-align: center;
    }
    .TWS-txt_1 {
      font-size: 16px;
      font-weight: 400;
      margin-left: -60px;
    }
    .download {
      width: 1100px;
      height: 330px;
      margin: auto;
      position: relative;
      .down-ios {
        width: 203px;
        height: 343px;
        float: left;
        position: absolute;
        z-index: 102
      }
      .down-android {
        width: 203px;
        height: 343px;
        position: absolute;
        z-index: 101;
        left: 405px
      }
      .down-pc {
        width: 521px;
        height: 332px;
        margin: auto;
        float: right;
        position: absolute;
        left: 650px;
        z-index: 100
      }
      .download-img-ios {
        width: 200px;
        height: 200px;
        background: #4C77D5;
        border-radius: 50%;
        margin: auto;
        position: absolute;
        top: 70px;
        z-index: 80
      }
      .download-img-android {
        width: 200px;
        height: 200px;
        background: #4C77D5;
        border-radius: 50%;
        margin: auto;
        position: absolute;
        top: 70px;
        left: 405px;
        z-index: 80
      }
    }
    .way {
      width: 1225px;
      margin: 0 auto;
      text-align: center;
      padding-bottom: 50px;
    }
  }
  .hover_two_code {
    top: 25%;
    left: 89%;
    width: 220px;
    height: 220px;
    border-radius: 10px;
    background: url('../../../assets/images/common/download/background.png') no-repeat;
    position: absolute;
    // box-shadow:4px 4px 10px #cacaca;
    padding-left: 5px;
    img {
      display: block;
      width: 120px;
      height: 120px;
      margin: 0 auto;
      margin-top: 49%;
      transform: translateY(-50%);
    }
  }
</style>
