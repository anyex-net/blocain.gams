<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.agreement.disclaimer.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.agreement.disclaimer.title') }}<!--免责声明--></span>
    </div>
    <p class="tittle">
      {{ $t('common.agreement.disclaimer.title') }}<!--免责声明-->
    </p>
    <div class="disclaimer-txt">
      <p>
        {{ $t('common.agreement.disclaimer.txt_1') }}</p><br>
      <p>
        {{ $t('common.agreement.disclaimer.txt_2') }}</p><br>
      <p>{{ $t('common.agreement.disclaimer.txt_3') }}</p><br>
      <p>{{ $t('common.agreement.disclaimer.txt_4') }}</p><br>
      <p>{{ $t('common.agreement.disclaimer.txt_5') }}</p><br>
      <p>{{ $t('common.agreement.disclaimer.txt_6') }}</p><br>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Disclaimer'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .tittle {
      width: 300px;
      font-size:24px;
      font-weight:400;
      text-align: center;
      padding-top: 50px;
      margin: auto;
    }
  }
  .disclaimer-txt {
    width:1130px;
    min-height: 500px;
    margin: 20px auto 60px;
    font-size:14px;
    font-weight:300;
    line-height:22px;
    background-color: white;
    padding: 30px;
    border-radius: 5px;
    color: #666666;
  }
</style>
