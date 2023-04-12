<template>
  <div class="noticeList">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
      <el-breadcrumb-item :to="{ path: '/index' }">国瑞首页</el-breadcrumb-item>
      <el-breadcrumb-item>最新公告</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row class="newNotice">最新公告</el-row>
    <div class="main">
      <div class="noticeTitle">
        <el-row class="title">【平台公告】{{ data.title }}</el-row>
        <el-row class="time">{{ data.releaseDate }}</el-row>
      </div>
      <div class="noticeContent">
        <p>亲爱的用户朋友们：</p>
        <p class="content" v-html="data.content" />
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: {}
    };
  },
  created() {
    this.getNotice();
  },
  methods: {
    getNotice() {
      this.$http({
        url: '/common/newsBulletin/findBy',
        method: 'get',
        params: { id: this.$route.query.id }
      })
        .then(res => {
          if (res.code === 200) {
            this.data = res.object;
            this.data.releaseDate = this.$verify.NowDate3(
              new Date(this.data.releaseDate)
            );
            console.log(res);
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
    }
  }
};
</script>
<style lang="scss">
.noticeList {
  margin-top: 20px;
  .noticeContent {
  .content {
    img {
      max-width: 100% !important;
    }
    em{
      font-style: italic !important;
      font-size: inherit !important;
    }
    u{
      font-size: inherit !important;
    }
    strong{
      font-size: inherit !important;
    }
  }
}
}
</style>
<style lang="scss" scoped>
.noticeList {
  width: 1200px;
  margin: 0 auto;
  .breadcrumb {
    margin: 20px 0px 46px 0px;
  }
  .newNotice {
    text-align: center;
    font-size: 24px;
    margin-bottom: 24px;
    color: #434343;
  }
  .main {
    background-color: #fff;
    .noticeTitle {
      padding: 40px 50px 24px 50px;
      text-align: center;
      border-bottom: 1px solid #e7eaf0;
      .title {
        font-size: 30px;
        padding-bottom: 24px;
        color: #434343;
        word-break: break-all;
      }
      .time {
        color: #434343;
      }
    }
    .noticeContent {
      padding: 40px;
      .content {
        word-break: break-all;
      }
      p {
        margin-bottom: 20px;
        color: #434343;
      }
    }
  }
}
</style>
