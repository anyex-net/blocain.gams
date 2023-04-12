<template>
  <div class="app-container">
    <div class="contactUs-title">
      <span class="contactUs-title-txt1">{{ $t('common.about.contact_us.name') }}<!--联系我们--></span>
    </div>
    <div class="contactUs-wrapper">
      <div class="contactUs-map">
        <el-amap class="amap-box" :vid="'amap-vue'" :center="[114.185271,22.334434]" :lang="ncLocale"></el-amap>
      </div>
      <div class="contactUs-info">
        <ul>
          <li>{{ $t('common.about.contact_us.address') }}<!--联系我们-->：Level 12,One Peking,1 Peking Road Tsim Sha Hong Kong / Ugland House Grand Cayman KY1-1104 Cayman Islands</li>
          <li>{{ $t('common.about.contact_us.email') }}<!--联系我们-->：service@mofsecurities.com</li>
          <li>{{ $t('common.about.contact_us.hot_line') }}<!--联系我们-->：400-6859-990</li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ContactUs',
  computed: {
    ncLocale: function() {
      let locale = this.$i18n.locale;
      if (locale === 'zh_CN') {
        locale = 'cn';
      }
      if (locale === 'zh_HK') {
        locale = 'tw';
      }
      if (locale === 'en_US') {
        locale = 'en';
      }
      return locale;
    }
  }
};
</script>

<style lang="scss" scoped>
  .app-container {
    .contactUs-title {
      padding: 13px;
      background-color: #F6F6F6;
      border-top-right-radius: 10px;
      .contactUs-title-txt1 {
        font-size: 18px;
        color: #434343;
        margin-left: 15px;
      }
      .contactUs-title-txt2 {
        font-size: 14px;
        color: #999999;
      }
    }
    .contactUs-wrapper {
      padding: 30px;
      .contactUs-map {
        height: 400px;
      }
      .contactUs-info {
        margin-top: 20px;
        font-size: 14px;
        color: #434343;
        line-height: 30px;
      }
    }
  }
</style>
