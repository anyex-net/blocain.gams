<template>
  <div class="app-container">
    <div class="reportList-title">
      <span class="reportList-title-txt1">媒体报道</span>
    </div>
    <div class="reportList-wrapper">
      <div class="reportList-container">
        <div class="reportList-img"></div>
        <router-link to="/about/reportDetail">
          <div class="reportList-content">
            <div class="reportList-summary">国瑞证券公司顺利拿下券商资质</div>
            <div class="reportList-detail">
              10月24日消息，知名美港股券国瑞证券（Tiger Brokers）宣布推出开放API并上线了美股量化平台，帮
              助个人开发者及机构用户解决量化交易难题，同时为普通用户大幅降低量化工具的使用门槛。
            </div>
            <div class="reportList-info">
              <span>财经网</span>
              <span class="reportList-date">2018-6-10</span>
            </div>
          </div>
        </router-link>
      </div>
      <div class="reportList-container">
        <div class="reportList-img"></div>
        <router-link to="/about/reportDetail">
          <div class="reportList-content">
            <div class="reportList-summary">国瑞证券公司顺利拿下券商资质</div>
            <div class="reportList-detail">
              10月24日消息，知名美港股券国瑞证券（Tiger Brokers）宣布推出开放API并上线了美股量化平台，帮
              助个人开发者及机构用户解决量化交易难题，同时为普通用户大幅降低量化工具的使用门槛。
            </div>
            <div class="reportList-info">
              <span>财经网</span>
              <span class="reportList-date">2018-6-10</span>
            </div>
          </div>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ReportList'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .reportList-title {
      padding: 13px;
      background-color: #F6F6F6;
      border-top-right-radius: 10px;
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
    .reportList-wrapper {
      padding: 30px;
      overflow: hidden;
      .reportList-container {
        height: 220px;
      }
      .reportList-img {
        float: left;
        width: 300px;
        height: 180px;
        border-radius: 4px;
        background-color: #357AEC;
      }
      .reportList-content {
        float: left;
        width: 620px;
        height: 180px;
        margin-left: 20px;
        .reportList-summary {
          font-size: 16px;
          font-weight: bold;
          color: #434343;
        }
        .reportList-detail {
          font-size: 14px;
          color: #999999;
          margin-top: 15px;
          line-height: 20px;
        }
        .reportList-info {
          margin-top: 30px;
          font-size: 14px;
          color: #999999;
          .reportList-date {
            margin-left: 30px;
          }
          .reportList-more {
            float: right;
            margin-right: 15px;
            margin-top: -5px;
            padding: 8px 15px;
            border-radius: 14px;
            font-size: 12px;
            color: #FEFEFE;
            cursor: pointer;
            background-color: #FF3E4D;
          }
        }
      }

    }
  }
</style>
