<template>
  <div class="app-container">
    <img class="offshoreAccount-banner" :src="getImgUrl('/common/banner_img_offshoreAccount'+'_'+$i18n.locale)" />
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.content.offshore.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.content.offshore.title') }}<!--保险服务--></span>
    </div>
    <div class="offshoreAccount-wrapper">
      <div class="wrapper-title">
        <div style="width:40px;height:4px;background:rgba(255,62,77,1);margin: auto;"></div>
        <p class="title-txt">
          {{ $t('common.content.offshore.txt') }}<!--国瑞首页-->
        </p>
      </div>
      <div class="wrapper-content">
        <p class="context-title">{{ $t('common.content.offshore.title_1') }}<!--离岸账户优势--></p>
        <el-row :gutter="20">
          <el-col v-for="data in cardData" :key="data.title" :span="8" class="card-container">
            <el-card class="card-wrapper">
              <div class="card-txt">
                <img class="card-img" :src="getImgUrl(data.image)">
                <div style="height: 150px;">
                  <div style="width:30px;height:3px;background:rgba(255,62,77,1);margin: 15px 10px 0 10px;"></div>
                  <p class="card-title">{{ $t('common.content.offshore.' + data.title) }}</p>
                  <p class="card-description">{{ $t('common.content.offshore.' + data.description) }}</p>
                </div>
              </div>
            </el-card>
          </el-col>
        </el-row>
        <p class="content-txt">*{{ $t('common.content.offshore.about_more') }}</p>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'OffshoreAccount',
  data() {
    return {
      cardData: [
        {
          title: 'content_title_1',
          description: 'content_txt_1',
          image: '/content/offshoreAccountCard_img_1'
        },
        {
          title: 'content_title_2',
          description: 'content_txt_2',
          image: '/content/offshoreAccountCard_img_2'
        },
        {
          title: 'content_title_3',
          description: 'content_txt_3',
          image: '/content/offshoreAccountCard_img_3'
        }
      ]
    };
  },
  methods: {
    getImgUrl(image) {
      return require('@/assets/images' + image + '.png');
    }
  }
};
</script>

<style>
  .card-wrapper .el-card__body {
    padding: 0;
  }
</style>

<style lang="scss" scoped>
  .app-container {
    background-color: white;
    padding-bottom: 50px;
    .offshoreAccount-banner {
      width: 100%;
    }
    .line {
      width: 1180px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .offshoreAccount-wrapper {
      margin-top: 50px;
      .wrapper-title {
        height: 120px;
        width: 1180px;
        margin: auto;
        text-align: center;
        .title-txt {
          margin-top: 20px;
          font-size:14px;
          font-weight:400;
          color:rgba(153,153,153,1);
          line-height:22px;
        }
      }
      .wrapper-content {
        width: 1180px;
        margin: auto;
        .context-title {
          font-size:20px;
          font-weight:400;
          color:rgba(67,67,67,1);
          line-height:40px;
          text-align: center;
        }
        .card-wrapper {
          border: none;
          .card-img {
            width: 100%;
          }
          .card-txt {
            background: #FFFFFF;
          }
          .card-title {
            margin-top: 0;
            padding: 10px 10px 0;
            font-size: 18px;
          }
          .card-description {
            font-size:13px;
            font-weight:400;
            color:rgba(153,153,153,1);
            line-height:20px;
            padding: 0 10px;
          }
          .card-container {
            width: 20%;
          }
        }
        .content-txt {
          margin-top: 20px;
          color:rgba(153,153,153,1);
        }
      }
    }
  }

</style>
