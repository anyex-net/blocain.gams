<template>
  <div class="app-container">
    <img class="foreignExchange-banner" :src="getImgUrl('/content/banner_img_foreignExchange'+'_'+$i18n.locale+'.png')" />
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.content.foreign_exchange.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.content.foreign_exchange.title') }}<!--外汇服务--></span>
    </div>
    <div class="foreignExchange-wrapper">
      <p class="wrapper-title">{{ $t('common.content.foreign_exchange.content_title') }}<!--了解详情--></p>
      <div>
        <table class="table-body">
          <tr>
            <th class="tr-th">{{ $t('common.content.foreign_exchange.bank') }}<!--银行--></th>
            <th class="tr-th">{{ $t('common.content.foreign_exchange.location') }}<!--总部所在地--></th>
          </tr>
          <tr v-for="data in tableData" :key="data.key" class="tr-body">
            <td class="td-value"><a :href="data.route" style="color: #000" target="_blank">{{ $t('common.content.foreign_exchange.' + data.key) }}</a></td>
            <td class="td-value">{{ $t('common.content.foreign_exchange.' + data.value) }}</td>
          </tr>
        </table>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'ForeignExchange',
  data() {
    return {
      tableData: [
        { key: 'bank_1', value: 'Canada', route: 'https://www.td.com' },
        { key: 'bank_2', value: 'Canada', route: 'https://www.rbcroyalbank.com' },
        { key: 'bank_3', value: 'Germany', route: 'https://www.dzbank.com' },
        { key: 'bank_4', value: 'Singapore', route: 'https://www.dbs.com' },
        { key: 'bank_5', value: 'Singapore', route: 'https://www.ocbc.com' },
        { key: 'bank_6', value: 'Sweden', route: 'https://www.handelsbanken.com' },
        { key: 'bank_7', value: 'Singapore', route: 'https://www.uobgroup.com' },
        { key: 'bank_8', value: 'Germany', route: 'https://www.apobank.de' },
        { key: 'bank_9', value: 'Sweden', route: 'https://www.swedbank.com' },
        { key: 'bank_10', value: 'Switzerland', route: 'https://www.group.pictet/' },
        { key: 'bank_11', value: 'Australia', route: 'https://www.commbank.com.au' },
        { key: 'bank_12', value: 'Canada', route: 'https://www.scotiabank.com' },
        { key: 'bank_13', value: 'Australia', route: 'https://www.anz.com.au' },
        { key: 'bank_14', value: 'Sweden', route: 'https://www.nordea.com' }
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

<style lang="scss" scoped>
  .app-container {
    background-color: #FFFFFF;
    padding-bottom: 40px;
    .foreignExchange-banner {
      width: 100%;
    }
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .foreignExchange-wrapper {
      width: 1200px;
      margin: 0 auto;
      padding-top: 40px;
      .wrapper-title {
        font-size: 18px;
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
        }
        .td-value {
          flex: 1 0;
          line-height: 20px;
          padding: 7px;
          border: 1px solid #dcdcdc;
          text-align: center;
        }
      }
    }
  }
</style>
