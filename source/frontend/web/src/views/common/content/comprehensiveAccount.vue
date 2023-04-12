<template>
  <div class="app-container">
    <img class="help-banner" :src="getImgUrl('/common/banner_img_comprehensiveAccount'+'_'+$i18n.locale+'.png')" />
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.content.comprehensive.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"></i>
      <span>{{ $t('common.content.comprehensive.title') }}<!--综合账户--></span>
    </div>
    <div class="offshoreAccount-wrapper">
      <div class="wrapper-title">
        <p class="title-txt">
          {{ $t('common.content.comprehensive.txt') }}
        </p>
      </div>
    </div>
    <div style="margin-bottom: 180px;">
      <div class="steps">
        <div
          v-for="(item,index) in stepArray"
          :key="index"
          :class="[stepArray.length===2 ? 'width-2' : stepArray.length===3?'width-3':'width-4', '']"
        >
          <div class="width-100 mt-b">
            <p
              class="title"
              :class="[index<currentVal ? 'title-1' : 'title', '']">
              {{ $t('common.content.comprehensive.' + item) }}
            </p>
          </div>
          <div v-if="errorStep<=0" class="width-100">
            <div
              class
              :class="[index<currentVal ? 'color-pick circle' : 'color-no-pick circle-no-border', '']"></div>
            <p
              v-show="index!==0"
              class="line-1"
              :class="[index<currentVal ? 'color-pick' : 'color-no-pick', '']"></p>
            <p
              v-show="index!==stepArray.length-1"
              class="line-2"
              :class="[index<currentVal-1 ? 'color-pick' : 'color-no-pick', '']"></p>
            <div v-if="index<currentVal" class="step-content">
              {{ $t('common.content.comprehensive.content_txt_1') }}
            </div>
          </div>
          <div v-else class="width-100">
            <div
              class
              :class="[index<errorStep ? 'color-pick circle' : index===errorStep?'color-pick circle-error' :'color-no-pick circle-no-border', '']"></div>
            <p
              v-show="index!==0"
              class="line-1"
              :class="[index<errorStep ? 'color-pick' :index===errorStep?'color-error-pick-1' :'color-no-pick', '']"></p>
            <p
              v-show="index!==stepArray.length-1"
              class="line-2"
              :class="[index<errorStep-1 ? 'color-pick' :index===errorStep-1?'color-error-pick-2' :'color-no-pick', '']"></p>
          </div>
        </div>
      </div>
    </div>
    <p class="context-title">{{ $t('common.content.comprehensive.content_item_title') }}<!--综合账户优势--></p>
    <div style="">
      <img class="help-banner" src="@/assets/images/common/comprehensiveAccount_bg.png" style="float:left;">
      <div ref="firstWrapper"></div>
      <div class="wrapper-content" style="position: absolute;">
        <el-row style="min-width: 1180px; margin: 0 auto;width: 70%;">
          <el-col :span="8">
            <div class="content-box1">
              <span class="box1-num">01</span>
              <div class="box1-right">
                <p :class="[$i18n.locale === 'en_US' ? 'box-title' : 'box-title1' ]">{{ $t('common.content.comprehensive.content_item_title_1') }}<!--货币兑换服务--></p>
                <p class="box-txt">{{ $t('common.content.comprehensive.content_item_txt_1') }}</p>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="content-box1">
              <span class="box1-num">02</span>
              <div class="box1-right">
                <p :class="[$i18n.locale === 'en_US' ? 'box-title' : 'box-title1' ]">{{ $t('common.content.comprehensive.content_item_title_2') }}<!--理财策划--></p>
                <p class="box-txt">{{ $t('common.content.comprehensive.content_item_txt_2') }}</p>
              </div>
            </div>
          </el-col>
          <el-col :span="8">
            <div class="content-box1">
              <span class="box1-num">03</span>
              <div class="box1-right">
                <p :class="[$i18n.locale === 'en_US' ? 'box-title' : 'box-title1' ]">{{ $t('common.content.comprehensive.content_item_title_3') }}<!--证券买卖服务--></p>
                <p class="box-txt">{{ $t('common.content.comprehensive.content_item_txt_3') }}</p>
              </div>
            </div>
          </el-col>
        </el-row>
        <el-row style="min-width: 1180px; margin: 20px auto;width: 70%;">
          <el-col :span="12">
            <div class="content-box1">
              <span class="box1-num">04</span>
              <div class="box1-right2">
                <p :class="[$i18n.locale === 'en_US' ? 'box-title' : 'box-title1' ]">{{ $t('common.content.comprehensive.content_item_title_4') }}<!--保险--></p>
                <p class="box-txt">{{ $t('common.content.comprehensive.content_item_txt_4') }}</p>
              </div>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="content-box1">
              <span class="box1-num">05</span>
              <div class="box1-right2">
                <p :class="[$i18n.locale === 'en_US' ? 'box-title' : 'box-title1' ]">{{ $t('common.content.comprehensive.content_item_title_5') }}<!--投资--></p>
                <p class="box-txt">{{ $t('common.content.comprehensive.content_item_txt_5') }}</p>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'ComprehensiveAccount',
  props: {
    currentVal: {
      type: Number,
      default: 1
    },
    errorStep: {
      type: Number,
      default: 0
    },
    stepArray: {
      type: Array,
      default: function() {
        // 一站式账户 理财易 投资 融资服务 认购新股 免费理财策划
        return ['content_title_1', 'content_title_2', 'content_title_3', 'content_title_4', 'content_title_5', 'content_title_6'];
      }
    }
  },
  mounted() {
    const _this = this;
    _this.doResize();
    window.onresize = function() {
      _this.doResize();
    };
  },
  methods: {
    doResize() {
      this.$refs.firstWrapper.style.height = (window.innerWidth * 0.14 - 150) + 'px';
    },
    getImgUrl(image) {
      return require('@/assets/images' + image);
    }
  }
};
</script>

<style lang="scss">
  .app-container .color-no-pick {
    display: block!important;
  }
  .app-container .color-pick {
    display: block!important;
  }
</style>

<style lang="scss" scoped>
  .app-container {
    background-color: #FFFFFF;
    .help-banner {
      width: 100%;
    }
    .line {
      width: 1180px;
      margin: 0 auto;
      padding-top: 20px;
    }
    .offshoreAccount-wrapper {
      margin: 50px 0 0;
      .wrapper-title {
        height: 100px;
        width: 1000px;
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
    }
    .steps {
      display: flex;
      /*justify-content: space-evenly;*/
      align-items: flex-end;
      width: 100%;
      text-align: center;
      padding: 16px 0;
    }
    .step-content {
      font-size: 12px;
      width: 150px;
      position: absolute;
      margin-top: 40px;
      color: #FFFFFF;
      background-color: #FF6773;
      padding: 10px;
      line-height: 18px;
      border-radius: 5px;
    }
    .line-1 {
      width: 50%;
      height: 5px;
      position: absolute;
      z-index: 0;
      left: 0;
      top: 3px;
      box-shadow: 0 10px 10px rgba(55, 57, 59, 0.13);
    }
    .line-2 {
      width: 50%;
      height: 5px;
      position: absolute;
      z-index: 0;
      top: 3px;
      left: 50%;
    }
    .mt-b{
      margin-bottom: 4px;
    }
    .color-pick {
      background: #FF6773;
      /*border-color: rgba(0,122,255,.3);*/
    }
    .color-no-pick {
      background: #FF6773;
    }
    .color-error-pick-1 {
      background-image: linear-gradient(90deg, #b377f6 0%, #df3031 100%);
    }
    .color-error-pick-2 {
      background-image: linear-gradient(
          90deg,
          #007aff 0%,
          #8091ff 60%,
          #b377f6 100%
      );
    }
    .width-100 {
      width: 100%;
      position: relative;
      display: flex;
      justify-content: center;
    }
    .title {
      width: 100px;
      /*font-weight: bold;*/
      font-size:14px;
      font-weight:400;
      color:rgba(67,67,67,1);
      line-height:22px;
    }.title-1 {
       width: 100px;
       /*font-weight: bold;*/
       font-size:14px;
       font-weight:400;
       color:#FF6773;
       line-height:22px;
     }
    .width-2 {
      width: 50%;
    }
    .width-3 {
      width: 33.33%;
    }
    .width-4 {
      width: 25%;
    }
    .circle {
      width: 8px;
      height: 8px;
      /*border: 2px solid #fff;*/
      border-radius: 50%;
      /*background: #007AFF;*/
      z-index: 9;
    }
    .circle-error {
      width: 8px;
      height: 8px;
      /*border: 2px solid #fff;*/
      border-radius: 50%;
      background: #df3031;
      z-index: 9;
    }
    .circle-no-border {
      width: 8px;
      height: 8px;
      /*border: 2px solid #fff;*/
      border-radius: 50%;
      /*background: #007AFF;*/
      z-index: 9;
    }
    .circle-no-border::before {
      display: inline-block;
      content: "";
      width: 8px;
      height: 8px;
      background: #FFFFFF;
      position: absolute;
      top: -5px;
      z-index: 1;
      left: 50%;
      transform: translateX(-50%);
      border-radius: 50%;
      border: 5px solid #FF6773;
    }
    .circle::after {
      display: inline-block;
      content: "";
      width: 18px;
      height: 18px;
      background: #FF6773;
      position: absolute;
      top: -5px;
      z-index: 1;
      left: 50%;
      transform: translateX(-50%);
      border-radius: 50%;
    }
    .circle-error::after {
      display: inline-block;
      content: "";
      width: 12px;
      height: 12px;
      background: rgba(223, 48, 49, 0.15);
      position: absolute;
      top: -2px;
      z-index: 1;
      left: 50%;
      transform: translateX(-50%);
      border-radius: 50%;
    }
    .context-title {
      width: 1180px;
      margin: 0 auto 20px;
      font-size:20px;
      font-weight:400;
      color:rgba(67,67,67,1);
      line-height:40px;
      text-align: center;
    }
    .wrapper-content {
      width: 100%;
      margin: auto;
      .content-box1 {
        background-color: white;
        padding: 40px 15px;
        border-radius: 5px;
        min-width: 200px;
        margin-right: 20px;
        .box1-num {
          color: #f56c6c2b;
          font-size: 60px;
          font-weight: bold;
          display: inline-block;
        }
        .box1-right {
          display: inline-block;
          width: 240px;
          position: absolute;
          top: 40px;
          margin-left: 30px;
        }
        .box1-right2 {
          display: inline-block;
          width: 400px;
          position: absolute;
          top: 30px;
          margin-left: 40px;
        }
        .box-title {
          font-size: 18px;
          margin-top: -10px;
        }
        .box-title1 {
          font-size: 18px;
          // margin-top: -10px;
        }
        .box-txt {
          color: #999999;
          line-height: 18px;
        }
      }
    }
  }

</style>
