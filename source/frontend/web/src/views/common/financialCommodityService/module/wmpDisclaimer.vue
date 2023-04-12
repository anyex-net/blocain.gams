<template>
  <div class="app-container">
    <p class="tittle">
      {{ $t('common.financial.wmp_disclaimer.title') }}<!--免责声明-->
    </p>
    <div class="disclaimer-txt">
      <p>
        {{ $t('common.financial.wmp_disclaimer.txt_1') }}</p>
      <div style="color: #666666;">
        <p>{{ $t('common.financial.wmp_disclaimer.txt_2') }}</p>
        <p>{{ $t('common.financial.wmp_disclaimer.txt_3') }}</p><br>
      </div>
      <p>
        {{ $t('common.financial.wmp_disclaimer.txt_4') }}</p><br>
      <p>{{ $t('common.financial.wmp_disclaimer.txt_5') }}</p><br>
      <p>
        {{ $t('common.financial.wmp_disclaimer.txt_6') }}</p><br>
      <p>{{ $t('common.financial.wmp_disclaimer.txt_7') }}</p><br>
      <p>{{ $t('common.financial.wmp_disclaimer.txt_8') }}</p><br>
      <p>{{ $t('common.financial.wmp_disclaimer.txt_9') }}</p><br>
      <p>{{ $t('common.financial.wmp_disclaimer.txt_10') }}<span style="color: #FF3E4D;">{{ $t('common.financial.wmp_disclaimer.accept') }}</span>{{ $t('common.financial.wmp_disclaimer.txt_11') }}</p><br>
      <div class="content-button">
        <router-link to="wmp"><el-button type="danger" style="background: #FF3E4D;" class="button">{{ $t('common.financial.wmp_disclaimer.button_accept') }}</el-button></router-link>
        <router-link to="/index"><el-button type="info" class="button">{{ $t('common.financial.wmp_disclaimer.button_decline') }}</el-button></router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'WmpDisclaimer'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .tittle {
      width: 300px;
      font-size: 24px;
      font-weight: 400;
      text-align: center;
      padding-top: 50px;
      margin: auto;
    }
    .disclaimer-txt {
      width: 1130px;
      margin: 20px auto 60px;
      font-weight: 300;
      line-height: 24px;
      background-color: white;
      padding: 30px;
      border-radius: 5px;
    }
    .content-button {
      text-align: center;
      .button {
        width:120px;
        margin-left: 15px;
      }
    }
  }
</style>
