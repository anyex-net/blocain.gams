<template>
  <div class="app-container">
    <div class="introduction-title">
      <span class="introduction-title-txt1">{{ $t('common.about.introduction.name') }}<!--公司简介--></span>
    </div>
    <div class="introduction-wrapper">
      <div class="introduction-detail">
        <ul>
          <li :class="[$i18n.locale === 'en_US' ? 'eng' : '']">{{ $t('common.about.introduction.txt_1') }}<!--公司简介--></li>
          <li :class="[$i18n.locale === 'en_US' ? 'eng' : '']">{{ $t('common.about.introduction.txt_2') }}<!--公司简介--></li>
          <li :class="[$i18n.locale === 'en_US' ? 'eng' : '']">{{ $t('common.about.introduction.txt_3') }}<!--公司简介--></li>
          <li :class="[$i18n.locale === 'en_US' ? 'eng' : '']">{{ $t('common.about.introduction.txt_4') }}<!--公司简介--></li>
        </ul>
      </div>
      <div class="introduction-decoration">
        <img src="@/assets/images/common/about_introduction.png" class="introduction-img" />
        <div class="introduction-border"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Introduction'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .eng {
      word-break: keep-all;
    }
    .introduction-title {
      padding: 13px;
      background-color: #F6F6F6;
      border-top-right-radius: 10px;
      .introduction-title-txt1 {
        font-size: 18px;
        color: #434343;
        margin-left: 15px;
      }
      .introduction-title-txt2 {
        font-size: 14px;
        color: #999999;
      }
    }
    .introduction-wrapper {
      padding: 30px;
      overflow: hidden;
      .introduction-detail {
        font-size: 14px;
        line-height: 25px;
        width: 550px;
        float: left;
        word-break: break-all;
        li {
          margin-bottom: 20px;
        }
        .txt {
          font-weight: bold;
        }
      }
      .introduction-decoration {
        position: relative;
        margin: 20px 40px 0;
        width: 280px;
        float: left;
        .introduction-img {
          width: 100%;
          position: relative;
          z-index: 2;
        }
        .introduction-border {
          position: absolute;
          right: -10px;
          bottom: -6px;
          width: 200px;
          height: 200px;
          background-color: #CCCCCC;
        }
      }
    }
  }
</style>
