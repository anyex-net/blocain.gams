<template>
  <div class="app-container">
    <div class="express-wrapper">
      <div class="express-title">
        <router-link to="/express">
          <span class="express-title-txt">
            <i class="el-icon-arrow-left" />
            <span><!--返回快讯-->{{ $t('common.express.return_list') }}</span>
          </span>
        </router-link>
      </div>
      <div class="express-container">
        <div class="express-summary">{{ expressDetail.summary }}</div>
        <div class="express-date">Date：{{ dateFormat(expressDetail.createDate) }}</div>
        <div class="express-content">{{ expressDetail.content }}</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Detail',
  props: {
    expressId: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      lang: this.$i18n.locale,
      expressDetail: ''
    };
  },
  mounted: function() {
    this.doInitData();
  },
  methods: {
    dateFormat(timestamp) {
      if (timestamp === undefined) {
        return '';
      }
      return this.$moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
    },
    doInitData() {
      this.$http({
        url: '/common/express/detail',
        method: 'get',
        params: {
          lang: this.lang,
          id: this.expressId
        }
      }).then(res => {
        if (res.code === 200) {
          this.expressDetail = res.object;
        }
      }).catch(error => {
        this.$message.error(error || 'Has Error');
      });
    }
  }
};
</script>

<style lang="scss" scoped>
  .app-container {
    .express-wrapper {
      width: 1200px;
      margin: 0 auto;
      padding: 20px;
      .express-title {
        margin-bottom: 20px;
        .express-title-txt {
          font-size: 16px;
          color: #999999;
          cursor: pointer;
        }
      }
      .express-container {
        margin-bottom: 30px;
        .express-summary {
          margin: 30px 0 10px;
          font-size: 20px;
          font-weight: bold;
          color: #434343;
        }
        .express-date {
          text-align: right;
          font-size: 14px;
          margin-bottom: 10px;
        }
        .express-content {
          font-size: 14px;
          color: #434343;
          line-height: 25px;
          word-wrap: break-word;
          white-space: pre-line;
        }
      }
    }
  }
</style>
