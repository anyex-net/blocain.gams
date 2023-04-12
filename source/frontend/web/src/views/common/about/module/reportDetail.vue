<template>
  <div class="app-container">
    <div class="reportDetail-title">
      <span class="reportList-title-txt1">媒体报道</span>
    </div>
    <div style="margin: 10px 0 10px 30px;">
      <router-link to="/about/reportList" style="color:#999999;">
        <i class="el-icon-arrow-left"></i>
        <span>返回媒体报道</span>
      </router-link>
    </div>
    <div class="reportDetail-wrapper">
      <div class="reportDetail-summary">国瑞证券公司顺利拿下券商资质</div>
      <div class="reportDetail-info">
        <span class="reportDetail-origin">财经网</span>
        <span class="reportDetail-date">2018-5-12</span>
      </div>
      <div class="reportDetail-detail">
        据主办方透露，本次评选面向全国广泛参评，领域全、维度多，范围涵盖银行、证券、保险、期货、基金、外汇、财富管理、信托、金融科技等九大领域。评选基于2018年度金融形势，从业务规模、盈利能力、资本质量、
        债务风险、经营增长、社会影响力的六个维度，甄选金融细分领域的年度机构、年度产品与年度团队。
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MediaReport'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .reportDetail-title {
      padding: 13px;
      background-color: #F6F6F6;
      border-top-right-radius: 10px;
      font-size: 16px;
      cursor: pointer;
      .reportList-title-txt1 {
        font-size: 18px;
        color: #434343;
        margin-left: 15px;
      }
      .reportList-title-txt2 {
        font-size: 14px;
        color: #999999;
      }
    }
    .reportDetail-wrapper {
      padding: 30px;
      .reportDetail-summary {
        font-size: 18px;
        font-weight: bold;
        color: #434343;
      }
      .reportDetail-info {
        margin-top: 15px;
        font-size: 14px;
        .reportDetail-origin {
          color: #FF3030;
        }
        .reportDetail-date {
          margin-left: 20px;
          color: #999999;
        }
      }
      .reportDetail-detail {
        margin-top: 15px;
        font-size: 14px;
        color: #434343;
        line-height: 22px;
      }
    }
  }
</style>
