<template>
  <div style="margin-top: 30px;">
    <div v-if="userData.title" class="top-title">
      <h2>{{ userData.title }}</h2>
      <ul class="describe clearfix">
        <li>{{ userData.source }}</li>
        <li v-text="filtrationTime(userData.createDate)" />
      </ul>
    </div>
    <div class="middle-content express-edit-container">
      <p v-html="userData.content" />
    </div>
    <div class="bottom-icon">
      <span :class="{numHover: !praiseStatus}">{{ userData.thumbsUp }}</span>
      <img v-if="praiseStatus" style="cursor:pointer;" class="praise-icon" src="@/assets/images/common/express/icon_praise_start .png" @click="thumbsUpOrCancel()">
      <img v-if="!praiseStatus" style="cursor:pointer;" class="praise-icon" src="@/assets/images/common/express/icon_praise_end.png" @click="thumbsUpOrCancel()">
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      lang: this.$i18n.locale,
      praiseStatus: true,
      userData: {
        // 'id': '290714425558700032',
        // 'title': '今天我很牛逼',
        // 'type': '热门',
        // 'source': '黄承基19号',
        // 'summary': '卧槽牛逼啊',
        // 'pictUrl': '',
        // 'content': '我真的很难受,我真的很难受,遗忘的不舍得',
        // 'lang': 'zh_CN',
        // 'status': 1,
        // 'isTop': true,
        // 'readingQuantity': '5',
        // 'thumbsUp': '0',
        // 'createBy': '200000000000',
        // 'createDate': 1574056351264,
        // 'updateBy': '200000000000',
        // 'updateDate': 1574412252894,
        // 'createDateStart': '',
        // 'createDateEnd': ''
      }
    };
  },
  computed: {
    filtrationTime() { // 时间过滤
      return (time) => {
        return this.$verify.NowDate3(new Date(Number(time)));
      };
    }
  },
  created() {
    this.praiseSetValue();
    this.isTabViewFun();
    this.detail();
    this.quantity();
  },
  mounted() {

  },
  methods: {
    praiseSetValue() {
      const storageData = this.getSetPraiseStatus('get', 'recommend_content_praise');
      if (storageData.indexOf(this.$route.query.message) !== -1) {
        this.praiseStatus = false;
      }
    },
    getSetPraiseStatus(status = 'get', KEY) { // 本地 存 取 点赞状态
    // this.getSetPraiseStatus('set', 'recommend_content_praise', true);
    // this.getSetPraiseStatus('get', 'recommend_content_praise');
      let data = [];
      data = JSON.parse(localStorage.getItem(KEY)) || [];
      if (status === 'set') {
        let isExist = false;
        let newindex = 0;
        data.forEach((item, index) => {
          if (item === this.$route.query.message) {
            isExist = true;
            newindex = index;
          }
        });
        if (!isExist && this.praiseStatus) {
          data.push(this.$route.query.message);
        } else if (isExist && !this.praiseStatus) {
          data.splice(newindex, 1);
        }

        localStorage.setItem(KEY, JSON.stringify(data));
        data = JSON.parse(localStorage.getItem(KEY)) || [];
      }
      return data;
    },

    isTabViewFun() {
      this.$emit('isTabViewFun', false);
    },

    quantity() { // 统计阅读量
      this.$http({
        url: '/common/express/reading/quantity',
        method: 'post',
        params: { lang: this.lang, id: this.$route.query.message, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },

    detail() { // 获取用户信息
      this.$http({
        url: '/common/express/detail',
        method: 'get',
        params: { lang: this.lang, id: this.$route.query.message, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.userData = res.object;
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },

    thumbsUpOrCancel() { // 点赞 取消点赞
      this.$http({
        url: '/common/express/thumbsUpOrCancel',
        method: 'post',
        params: { lang: this.lang, id: this.$route.query.message, thumbsUpType: this.praiseStatus, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.getSetPraiseStatus('set', 'recommend_content_praise');
            this.praiseStatus = !this.praiseStatus;
            this.detail();
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    }

  }
};
</script>

<style lang="scss">
  .express-edit-container {
    >p {
      h2 {
        strong {
          * {
            font-weight: 700;
          }
        }
        em {
          font-style: italic;
        }
        * {
          font-size: 20px;
        }
        >a {
          * {
            font-weight: 700;
          }
        }
      }
      a {
        text-decoration: underline;
        color: #06c;
      }
      * {
        white-space: normal;
        word-break: break-all;
      }
    }
  }
</style>

<style lang="scss" scoped>
$BGcolor:#FF5949;
$Cor999:#999;
$Corfff:#fff;
$Cor43:#434343;
$CorF6:#f6f6f6;
  .top-title {
    margin-bottom: 40px;
    h2 {
      font-size: 24px;
      color: #434343;
      margin-bottom: 30px;
    }
    >ul.describe {
      li {
        float: left;
        &:first-child {
          color: $BGcolor;
          margin-right: 25px;
        }
        &:last-child {
          list-style: disc;
          color: rgb(172, 172, 172);
        }
      }
    }
  }

  .middle-content {
    font-size: 16px;
    color: #434343;
    line-height: 20px;
  }

  .bottom-icon {
    margin-top: 100px;
    position: relative;
    span {
      position: absolute;
      left: 49.6%;
      top: 23px;
      color: #cecece;
    }
    span.numHover {
      color: $BGcolor;
    }
    .praise-icon {
      width: 40px;
      height:40px;
      display: block;
      margin: 0 auto;
    }
  }
</style>
