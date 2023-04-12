<template>
  <div class="app-container">
    <img class="cornucopia-banner" :src="getImgUrl('/content/banner_img_cornucopia'+'_'+$i18n.locale+'.png')" />
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.content.cornucopia.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.content.cornucopia.title') }}<!--国瑞“聚宝盆”--></span>
    </div>
    <div class="cornucopia-wrapper">
      <p class="wrapper-title">{{ $t('common.content.cornucopia.content_title') }}<!--了解详情--></p>
      <div>
        <table class="table-body">
          <tr>
            <th class="tr-th">{{ $t('common.content.cornucopia.bank') }}<!--银行--></th>
            <th class="tr-th">{{ $t('common.content.cornucopia.website') }}<!--网址--></th>
          </tr>
          <tr v-for="data in tableData" :key="data.key" class="tr-body">
            <td class="td-key">{{ $t('common.content.cornucopia.' + data.key) }}<!--银行--></td>
            <td class="td-value"><a :href="data.value" target="_blank" style="color: #548BFC"> {{ data.value }}</a></td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Cornucopia',
  data() {
    return {
      tableData: [
        { key: 'bank_1', value: 'https://www.icbc.com.cn' },
        { key: 'bank_2', value: 'https://www.abchina.com/cn' },
        { key: 'bank_3', value: 'https://www.boc.cn' },
        { key: 'bank_4', value: 'https://www.ccb.com' },
        { key: 'bank_5', value: 'https://www.bankcomm.com' },
        { key: 'bank_6', value: 'https://www.cmbchina.com' },
        { key: 'bank_7', value: 'https://www.spdb.com.cn' },
        { key: 'bank_8', value: 'https://www.citicbank.com' },
        { key: 'bank_9', value: 'https://www.hxb.com.cn' },
        { key: 'bank_10', value: 'https://www.cebbank.com' },
        { key: 'bank_11', value: 'https://www.cib.com.cn' },
        { key: 'bank_12', value: 'https://www.cgbchina.com.cn' },
        { key: 'bank_13', value: 'https://www.cmbc.com.cn' },
        { key: 'bank_14', value: 'https://www.bank.pingan.com' }
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
    .cornucopia-banner {
      width: 100%;
    }
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .cornucopia-wrapper {
      width: 1200px;
      margin: 0 auto;
      padding-top: 40px;
      .wrapper-title {
        font-size: 20px;
        margin-bottom: 20px;
        text-align: center;
      }
      .table-body {
        width: 100%;
        margin-bottom: 10px;
        border-radius: 5px;
        .tr-th {
          width: 250px;
          background: #404857;
          color: #FFFFFF;
          padding: 15px 30px;
          text-align: center;
          border-bottom: 1px solid #0000002e;
          font-size: 14px;
        }
        .td-key {
          flex: 1 0;
          line-height: 20px;
          padding: 7px;
          border: 1px solid #dcdcdc;
          text-align: center;
          font-size: 14px;
        }
        .td-value {
          flex: 1 0;
          line-height: 20px;
          padding: 7px;
          border: 1px solid #dcdcdc;
          text-align: center;
          font-size: 14px;
          color: #548BFC;
        }
      }
    }
  }

</style>
