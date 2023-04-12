<template>
  <div class="app-container">
    <img class="insuranceServices-banner" :src="getImgUrl('/content/banner_img_insuranceServices'+'_'+$i18n.locale+'.png')" />
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.content.insurance.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.content.insurance.title') }}<!--保险服务--></span>
    </div>
    <div class="insuranceServices-wrapper">
      <p class="wrapper-title">{{ $t('common.content.insurance.content_title') }}<!--精选保险方案--></p>
      <div class="wrapper-context">
        <img class="context-left" src="@/assets/images/content/insuranceServices/context.png">
        <div class="context-right">
          <p class="context-title">{{ $t('common.content.insurance.content_txt_1') }}<!--家庭雇佣保险--></p>
          <p class="context-txt">{{ $t('common.content.insurance.content_txt_2') }}</p>
        </div>
      </div>
    </div>
    <div class="insuranceServices-wrapper">
      <p class="wrapper-txt">{{ $t('common.content.insurance.content_txt_3') }}</p>
    </div>
    <div>
      <img class="insuranceServices-banner" :src="getImgUrl('/content/insuranceServices/banner'+'_'+$i18n.locale+'.png')">
    </div>
  </div>
</template>

<script>

export default {
  name: 'InsuranceServices',
  data() {
    return {
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

</style>

<style lang="scss" scoped>
  .app-container {
    background-color: white;
    .insuranceServices-banner {
      width: 100%;
    }
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .insuranceServices-wrapper {
      width: 1200px;
      margin: 0 auto;
      padding-top: 40px;
      .wrapper-title {
        text-align: center;
        font-size: 23px;
        margin-bottom: 30px;
      }
      .wrapper-context {
        height: 300px;
        width: 100%;
        background-color: white;
        box-shadow: 0 0 20px #82b4ec36;
        overflow: hidden;
        .context-left {
          float: left;
        }
        .context-right {
          width: 550px;
          float: right;
          padding: 20px;
          margin: 50px 100px 0 0;
          .context-title {
            font-size:16px;
            font-weight:bold;
            line-height:22px;
          }
          .context-txt {
            font-size:14px;
            font-weight:400;
            color: #666666;
            line-height:22px;
            margin-bottom: 30px;
          }
        }
      }
    }
    .wrapper-txt {
      text-align: center;
      color: #999999;
      line-height:24px;
      font-size: 15px;
      margin: 20px 0 30px;
    }
    .pic {
      width: 20%;
    }
  }

</style>
