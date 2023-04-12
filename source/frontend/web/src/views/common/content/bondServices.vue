<template>
  <div class="app-container">
    <img class="bondServices-banner" :src="getImgUrl('/content/banner_img_bondServices'+'_'+$i18n.locale+'.png')" />
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.content.bonds.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.content.bonds.title') }}<!--债券服务--></span>
    </div>
    <div class="bondServices-wrapper">
      <p class="wrapper-title">{{ $t('common.content.bonds.content_title_1') }}<!--债券介绍--></p>
      <p class="wrapper-txt">{{ $t('common.content.bonds.content_title_1_txt') }}</p>
      <table class="table-body">
        <tr v-for="(data,index) in tableData" :key="index" class="tr-body">
          <td class="td-key">{{ $t('common.content.bonds.' + data.key) }}</td>
          <td :class="[index === tableData.length-1 ? 'td-last' : 'td-value']">{{ $t('common.content.bonds.' + data.value) }}</td>
        </tr>
      </table>
    </div>
    <div class="bondServices-wrapper">
      <p class="wrapper-title">{{ $t('common.content.bonds.content_title_2_title') }}<!--为什么要选择投资债券--></p>
      <div class="txt-body">
        <p>- {{ $t('common.content.bonds.content_title_2_txt_1') }}<!--比一般银行定期存款回报更高--></p>
        <p>- {{ $t('common.content.bonds.content_title_2_txt_2') }}<!--一般来说，优质的定息债券能带来比股票更稳定的回报--></p>
        <p>- {{ $t('common.content.bonds.content_title_2_txt_3') }}<!--可依不同投资取向选取不同风险/回报之债券--></p>
        <p>- {{ $t('common.content.bonds.content_title_2_txt_4') }}<!--政府票据有比银行更高的信用评级--></p>
        <p>- {{ $t('common.content.bonds.content_title_2_txt_5') }}<!--可提前提取款项而不罚息--></p>
        <p>- {{ $t('common.content.bonds.content_title_2_txt_6') }}<!--佣金收费低至总交易金额之0.25%--></p>
      </div>
    </div>
    <div class="bondServices-wrapper" style="color: #999999;">
      <p>{{ $t('common.content.bonds.risk_tip') }}: {{ $t('common.content.bonds.risk_tip_txt') }}<!--风险提示--></p>
      <p>{{ $t('common.content.bonds.credit_rating') }}: {{ $t('common.content.bonds.credit_rating_txt_1') }}<!--信用评级--></p>
      <p>{{ $t('common.content.bonds.credit_rating_txt_2') }}</p>
      <p>{{ $t('common.content.bonds.credit_rating_txt_3') }}</p>
    </div>
  </div>
</template>

<script>

export default {
  name: 'BondServices',
  data() {
    return {
      tableData: [
        { key: 'key_1', value: 'value_1' },
        { key: 'key_2', value: 'value_2' },
        { key: 'key_3', value: 'value_3' },
        { key: 'key_4', value: 'value_4' }
      ]
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
    background-color: #FFFFFF;
    padding-bottom: 40px;
    .bondServices-banner {
      width: 100%;
    }
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .bondServices-wrapper {
      width: 1200px;
      margin: 0 auto;
      padding-top: 40px;
      line-height: 22px;
      .wrapper-title {
        font-size: 18px;
        margin-bottom: 20px;
      }
      .wrapper-txt {
        font-size: 14px;
        color: #999999;
      }
      .table-body {
        width: 100%;
        box-shadow:-2px 8px 49px 1px rgba(39,130,242,0.1);
        margin-top: 40px;
        border-radius:5px;
        .tr-body {
          display: flex;
            .td-key {
              width: 250px;
              background: #404857;
              color: #FFFFFF;
              padding: 15px 30px;
              text-align: center;
              border-bottom: 1px solid #0000002e;
              font-size: 13px;
            }
            .td-value {
              flex: 1 0;
              line-height: 20px;
              padding: 10px 20px 10px 100px;
              border-bottom: 1px solid #dcdcdc;
            }
           .td-last {
            flex: 1 0;
            line-height: 20px;
            padding: 10px 20px 10px 100px;
          }
        }
      }
      .txt-body {
        box-shadow:-2px 8px 49px 1px rgba(39,130,242,0.1);
        padding: 10px 25px;
        line-height: 22px;
      }
    }
  }

</style>
