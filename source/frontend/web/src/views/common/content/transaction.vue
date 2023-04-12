<template>
  <div class="app-container">
    <div class="transaction-wrapper">
      <img class="transaction-banner" :src="getImgUrl('/content/banner_img_transaction'+'_'+$i18n.locale+'.png')" />
      <div style="height: 520px;margin-bottom: 50px;">
        <div class="wrapper">
          <div class="wrapper-menu">
            <el-row :gutter="25">
              <el-col :span="6" class="menu-1">
                <img src="@/assets/images/content/icon-menu-1.png" height="30" />
                <span class="menu-txt">{{ $t('common.content.transaction.menu_txt_1') }}<!--智能自动选股--></span>
              </el-col>
              <el-col :span="6">
                <img src="@/assets/images/content/icon-menu-2.png" height="30" />
                <span class="menu-txt">{{ $t('common.content.transaction.menu_txt_2') }}<!--自动执行下单--></span>
              </el-col>
              <el-col :span="6">
                <img src="@/assets/images/content/icon-menu-3.png" height="30" />
                <span class="menu-txt">{{ $t('common.content.transaction.menu_txt_3') }}<!--自动调整仓位--></span>
              </el-col>
              <el-col :span="6">
                <img src="@/assets/images/content/icon-menu-4.png" height="30" />
                <span class="menu-txt">{{ $t('common.content.transaction.menu_txt_4') }}<!--佣金行业新低--></span>
              </el-col>
            </el-row>
          </div>
          <div class="line">
            <router-link to="/index">
              <span>{{ $t('common.content.transaction.home') }}<!--国瑞首页--></span>
            </router-link>
            <i class="el-icon-arrow-right"></i>
            <span>{{ $t('common.content.transaction.title') }}<!--美股智能化交易--></span>
          </div>
          <div class="content-back">
            <div class="wrapper">
              <div class="wrapper-left">
                <img class="img" src="@/assets/images/content/img-content.png" />
                <div style="width: 600px;float:left;margin: 130px 0 0 60px;">
                  <p class="content-txt-1">{{ $t('common.content.transaction.content_title') }}<!--国瑞为您提供美股智能化交易平台--></p>
                  <p class="content-txt-2">
                    {{ $t('common.content.transaction.content_txt') }}
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Transaction',
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
    padding-bottom: 40px;
    .transaction-banner {
      position: relative;
      width: 100%;
    }
    .transaction-wrapper {
      .wrapper {
        width: 100%;
        position: absolute;
        margin-top: -25px;
        .wrapper-menu {
          text-align: center;
          border-radius:5px;
          box-shadow: 0 10px 10px #82b4ec1a;
          width: 1120px;
          margin: 0 auto;
          background-color: #FFFFFF;
          padding: 20px 40px;
          .menu-1 {
            margin-left: -50px;
          }
          .menu-txt {
            display: inline-block;
            position: absolute;
            top: 10px;
            margin-left: 5px;
          }
        }
        .line {
          width: 1200px;
          margin: 0 auto;
          padding-top: 20px;
        }
        .content-back {
          width: 100%;
          height: 320px;
          position: relative;
          background-color: #F1F3F7;
          margin-top: 150px;
          .wrapper {
            height: 280px;
            width: 100%;
            position: absolute;
            margin-top: -85px;
            .wrapper-left {
              width: 1200px;
              margin: auto;
              .img {
                float: left;
              }
              .content-txt-1 {
                font-size:16px;
                font-weight:bold;
                line-height:26px;
              }
              .content-txt-2 {
                font-size:14px;
                font-weight:400;
                line-height:22px;
              }
            }
          }
        }
      }
    }

  }

</style>
