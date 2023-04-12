<template>
  <div class="app-container">
    <div class="development-title">
      <span class="development-title-txt1">{{ $t('common.about.development.name') }}<!--发展历程--></span>
    </div>
    <div class="development-wrapper">
      <el-timeline class="development-timeline">
        <el-timeline-item v-for="data in timeData" :key="data.id" :timestamp="data.time" placement="bottom" color="#5EA9F5">
          <div class="development-summary">{{ $t('common.about.development.' + data.title) }}</div>
          <div class="development-detail">{{ $t('common.about.development.' + data.content) }}</div>
        </el-timeline-item>
      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Development',
  data() {
    return {
      timeData: [
        {
          time: '2018/10', title: 'title_1', content: 'txt_1'
        },
        {
          time: '2018/9', title: 'title_2', content: 'txt_2'
        },
        {
          time: '2018/8', title: 'title_3', content: 'txt_3'
        },
        {
          time: '2018/7', title: 'title_4', content: 'txt_4'
        },
        {
          time: '2018/6', title: 'title_5', content: 'txt_5'
        }
      ]
    };
  }
};
</script>

<style lang="scss">
  .development-timeline {
    .el-timeline-item__tail {
      border-color: #E0EBF6;
    }
    .el-timeline-item__timestamp {
      font-size: 14px;
    }
  }
</style>

<style lang="scss" scoped>
  .app-container {
    .development-title {
      padding: 13px;
      background-color: #F6F6F6;
      border-top-right-radius: 10px;
      .development-title-txt1 {
        font-size: 18px;
        color: #434343;
        margin-left: 15px;
      }
      .development-title-txt2 {
        font-size: 14px;
        color: #999999;
      }
    }
    .development-wrapper {
      padding: 40px;
      .development-timeline {
        width: 800px;
        margin: 0 auto;
        .development-summary {
          font-size: 16px;
          font-weight: bold;
          color: #434343;
        }
        .development-detail {
          margin: 15px 0;
          font-size: 14px;
          color: #999999;
          line-height: 20px;
        }
      }
    }
  }
</style>
