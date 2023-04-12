<template>
  <div class="main-container">

    <div class="left-container">
      <el-tabs v-if="isTabView" v-model="tabView">
        <el-tab-pane :label="$t('common.express.tab_list.title_1')" name="recommend" /> <!-- $i18n - 推荐 -->
        <!-- <el-tab-pane label="7x24" name="sevenXday"></el-tab-pane> -->
        <!-- <el-tab-pane v-if="false" label="自定义" name="recommendChild"></el-tab-pane> -->
        <el-tab-pane :label="$t('common.express.tab_list.title_2')" name="information" /> <!-- $i18n - 资讯 -->
        <el-tab-pane :label="$t('common.express.tab_list.title_3')" name="journalism" /> <!-- $i18n - 新闻 -->
      </el-tabs>
      <div />
      <keep-alive>
        <component :is="tabView" :user-info="userInfo" @isTabViewFun="isTabViewFun" />
      </keep-alive>
    </div>

    <div :style="rightTopStyle" class="right-box">
      <div class="right-suspensionTop">
        <ul class="clearfix icon_yuedu">
          <img src="@/assets/images/common/express/icon_read.png" alt="">
          <li>{{ $t('common.express.right_suspend.title') }}</li> <!-- $i18n - 热门阅读 -->
        </ul>
        <ul class="clearfix read-tab">
          <!-- <li :class="{ redClick: isRedClickStyle === 'month' }" @click="rightTabClick('month')">月榜</li>
          <li :class="{ redClick: isRedClickStyle === 'week' }" @click="rightTabClick('week')">周榜</li>
          <li :class="{ redClick: isRedClickStyle === 'day' }" @click="rightTabClick('day')">日榜</li> -->
        </ul>
        <ul class="clearfix read-list">
          <li v-for="(item,index) in rigthPopularReading" :key="index">
            <ul class="text-content clearfix">
              <li style="cursor:pointer;" @click="hrefSonContent(item.id)">{{ index+1 }}.</li>
              <li style="cursor:pointer;" @click="hrefSonContent(item.id)">{{ item.title }}</li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="right-suspensionBottom">
        <img v-if="rightTopStyle.position==='static'" src="@/assets/images/common/express/icon_top.png" alt="" @click="goTop()">
        <img v-else src="@/assets/images/common/express/icon_top_hover.png" alt="" @click="goTop()">
      </div>
    </div>

  </div>
</template>

<script>
import recommend from './navItems/recommend'; // 推荐
import sevenXday from './navItems/sevenXday';
import information from './navItems/information'; // 资讯
import journalism from './navItems/journalism'; // 新闻
export default {
  name: 'List',
  components: {
    recommend,
    sevenXday,
    information,
    journalism
  },
  props: {
    userInfo: {
      type: Object,
      default: () => {}
    }
  },
  data() {
    return {
      lang: this.$i18n.locale,
      tabView: 'recommend',
      isTabView: true,
      // isRedClickStyle: 'month',
      rigthPopularReading: [],

      rightTopStyle: {
        top: '6px',
        position: 'static'
      },
      pageParams: {
        'rows': 5,
        'page': 1,
        'totalPage': 1
      }
    };
  },
  computed: {

  },
  created() {
    // this.rightTabClick();
    this.hotReading();
  },
  mounted: function() {
    window.addEventListener('scroll', this.handleScroll);
    window.onresize = () => {
      const rightTS = (window.innerWidth - 1200 - 16) / 2;
      this.rightTopStyle.right = rightTS + 'px';
    };
  },
  methods: {
    handleScroll() { // 右边fixed style赋值
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
      if (scrollTop > 120) {
        this.rightTopStyle.position = 'fixed';
        const rightTS = (window.innerWidth - 1200 - 16) / 2;
        this.rightTopStyle.right = rightTS + 'px';
      } else {
        this.rightTopStyle.position = 'static';
      }
    },
    goTop() { // 回到顶部
      (document.documentElement.scrollTop = 0) || (document.body.scrollTop = 0) || (window.pageYOffset = 0);
      this.rightTopStyle.position = 'static';
    },
    hotReading(timeType = 'month') {
      const data = {
        lang: this.lang,
        timeType,
        IEgetIssue: this.$verify.timestampRandom()
      };
      Object.assign(data, this.pageParams);
      this.$http({
        url: '/common/express/hotReading',
        method: 'get',
        params: data
      })
        .then(res => {
          if (res.code === 200) {
            this.rigthPopularReading = res.object;
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
    // rightTabClick(type = 'month') { // 右边悬浮框tap栏切换
    //   if (type === 'month') {
    //     this.isRedClickStyle = 'month';
    //     this.hotReading('month');
    //   } else if (type === 'week') {
    //     this.isRedClickStyle = 'week';
    //     this.hotReading('week');
    //   } else if (type === 'day') {
    //     this.isRedClickStyle = 'day';
    //     this.hotReading('day');
    //   }
    // },
    isTabViewFun(status) {
      this.isTabView = status;
    },
    hrefSonContent(id) { // 内容点击至详情页
      const path = this.$router.history.current.path.indexOf('recommendChild') !== -1 ? 'recommendChild' : 'list/recommendChild';
      const { href } = this.$router.resolve({ path, query: { message: id }});
      window.open(href, '_blank');
    }
  }
};
</script>

<style lang="scss">
  blockquote {
    border-left: 4px solid #ccc;
    margin-bottom: 5px;
    margin-top: 5px;
    padding-left: 16px
  }
</style>
<style lang="scss" scoped>
$BGcolor:#FF5949;
$Cor999:#999;
$Corfff:#fff;
$Cor43:#434343;
$CorF6:#f6f6f6;

div {
  margin: 0;
  padding: 0;
}
.main-container {
  white-space:normal; // this it is
  word-break:break-all; //or word-wrap:break-word;
  word-wrap: break-word; // 长英文字母或长数字自动换行
  width: 1200px;
  margin: 60px auto;
  font-size: 0;
  >div {
    float: left;
  }
  .left-container {
    width: 880px;
    min-height: 600px;
    display: inline-block;
    box-sizing: border-box;
    margin-right: 20px;
    margin-bottom: 60px;
    background-color: $Corfff;
    padding: 10px 30px 100px 30px;
    border-radius: 3px;
  }
  .right-box {
    width: 300px;
    min-height: 200px;

  }
  .right-suspensionTop {
    width: 100%;
    height: 100%;
    display: inline-block;
    box-sizing: border-box;
    background-color: $Corfff;
    padding: 20px;
    border-radius: 3px;
    z-index: 1;

    >ul.icon_yuedu {
      img {
        float: left;
        width: 10px;
        height: 14px;
        vertical-align:text-top;
        background-size: 100%;
      }
      li {
        float: left;
        margin-left: 4px;
      }
      margin-bottom: 30px;
    }
    >.read-tab {
      width: 100%;
      // padding-bottom: 10px;
      border-bottom: 1px solid $CorF6;
      margin-bottom: 20px;
      .redClick {
        color: $BGcolor;
      }
      li {
        float: left;
        margin-right: 20px;
        cursor:pointer;
      }
    }
    >.read-list {
      color: $Cor999;
      >li {
        padding-bottom: 17px;
        &:not(:nth-child(n+4)) {
          // color: $BGcolor;
          li:first-child {
            color: $BGcolor;
          }
        }
        ul.text-content {
          display: flex;
          line-height: 20px;
          li:first-child {
            width: 20px;
          }
          li:last-child {
            flex: 1;
          }
        }
      }
    }
  }
  .right-suspensionBottom {
    width: 46px;
    min-height: 46px;
    float: right;
    margin-top: 100px;

    img {
      cursor:pointer;
      background-size: 100%;
    }
  }
}

</style>
