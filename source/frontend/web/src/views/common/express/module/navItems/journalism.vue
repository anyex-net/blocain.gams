<template>
  <div>
    <router-view @isTabViewFun="isTabViewFun" />
    <div v-if="isBox">
      <div v-for="(item, index) in leftList" :key="index" class="list-items">
        <div style="cursor:pointer;" class="top-title" @click="hrefSonContent(item.id)">
          {{ item.title }}
        </div>
        <div class="bottom-content">
          <ul class="img-and-text clearfix">
            <img v-if="item.pictUrl" style="cursor:pointer;" :src="item.pictUrl" alt="" @click="hrefSonContent(item.id)">
            <li style="cursor:pointer;" @click="hrefSonContent(item.id)">{{ item.summary }}</li>
          </ul>
          <ul class="bottom-text clearfix">
            <li>
              <img src="@/assets/images/common/express/icon_information_read.png" alt="">
              <span>{{ item.readingQuantity }}{{ $t('common.express.tab_list.journalism.read') }}</span> <!-- $i18n - 阅读 -->
            </li>
            <li>
              <img src="@/assets/images/common/express/icon_information_date.png" alt="">
              <span v-text="filtrationTime(item.createDate)" />
            </li>
          </ul>
        </div>
      </div>
      <!-- 分页 -->
      <el-pagination
        style="text-align:center;margin-top: 51px;"
        background
        layout="prev, pager, next"
        :hide-on-single-page="false"
        :page-size="pageParams.rows"
        :page-count="pageParams.totalPage"
        :current-page="pageParams.page"
        class="pagination"
        @current-change="pagingDoSearch"
      />
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      lang: this.$i18n.locale,
      isBox: true,
      leftList: [
        // {
        // 	"id": "290714425558700032",
        // 	"title": "今天我很牛逼",
        // 	"type": "热门",
        // 	"source": "黄承基19号",
        // 	"summary": "卧槽牛逼啊",
        // 	"pictUrl": "",
        // 	"content": "我真的很难受,我真的很难受,遗忘的不舍得",
        // 	"lang": "zh_CN",
        // 	"status": 1,
        // 	"isTop": true,
        // 	"readingQuantity": "5",
        // 	"thumbsUp": "0",
        // 	"createBy": "200000000000",
        // 	"createDate": 1574056351264,
        // 	"updateBy": "200000000000",
        // 	"updateDate": 1574412252894,
        // 	"createDateStart": "",
        // 	"createDateEnd": ""
        // }
      ],
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 1
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
  beforeCreate() {

  },
  created() {
    this.detail();
  },
  mounted() {

  },
  methods: {
    detail() { // 列表数据请求
      const data = {
        lang: this.lang,
        type: 'journalism',
        IEgetIssue: this.$verify.timestampRandom()
      };
      Object.assign(data, this.pageParams);
      this.$http({
        url: '/common/express/data',
        method: 'post',
        params: data
      })
        .then(res => {
          if (res.code === 200) {
            this.leftList = res.rows;
            this.pageParams.totalPage = res.totalPage;
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
    isTabViewFun(status) { // 子=>父 事件
      this.isBox = status;
      this.$emit('isTabViewFun', status);
    },
    hrefSonContent(id) { // 内容点击至详情页
      const { href } = this.$router.resolve({ path: 'list/recommendChild', query: { message: id }});
      window.open(href, '_blank');
    },
    pagingDoSearch(page) {
      this.pageParams.page = page;
      this.detail();
    }
  }
};
</script>
<style lang="scss" scoped>
  $BGcolor:#FF5949;
  $Cor999:#999;
  $Corfff:#fff;
  $Cor43:#434343;
  $CorF6:#f6f6f6;
  .list-items {
    padding: 30px 0px;
    border-bottom: 1px solid $CorF6;

    .top-title {
      font-size: 18px;
      color: #434343;
      font-weight: 600;
      margin-bottom: 20px;
    }
    .bottom-content {
      font-size: 14px;
      color: $Cor999;
      ul.img-and-text {
        width: 100%;
        height: 105px;
        display: flex;
        overflow: hidden;
        img {
          width: 190px;
          height: 105px;
          margin-right: 20px;
        }
        li:last-child {
          line-height: 20px;
          flex: 1;
        }
      }
      ul.bottom-text {
        margin-top: 20px;
        li {
          float: left;
          img {
            width: 14px;
            height: 14px;
            background-size: 100%;
            display: inline-block;
            vertical-align:text-top;
            margin-right: 7px;
          }
          span {
            line-height: 14px;
          }
        }
        li:last-child {
          float: right;
        }
      }
    }
  }

</style>
