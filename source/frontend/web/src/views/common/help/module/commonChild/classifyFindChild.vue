<template>
  <div>
    <div v-if="canIssueList" class="issue-list">
      <h2>{{ parentData.classDictDest }}</h2>
      <ul class="list-content">
        <li v-for="(item,index) in issueListData" :key="index" @click="issueListClick(index)">{{ item.problemLevelTwo }}</li>
      </ul>
    </div>
    <div v-else class="details">
      <h2>{{ issueListData[presentIndex].problemLevelTwo }}</h2>
      <ul class="list-content">
        <li v-html="issueListData[presentIndex].problemSolving" />
      </ul>
      <!-- <ul class="list-content">
        <li>
          1. 中国内地居民
        </li>
        <li>
          1.1 中国内地身份证
        </li>
        <li>
          1.2 内地临时身份证(三个月有效期内)
        </li>
      </ul>
      <ul class="list-content">
        <li>
          1. 中国内地居民
        </li>
        <li>
          1.1 中国内地身份证
        </li>
        <li>
          1.2 内地临时身份证(三个月有效期内)
        </li>
      </ul>
      <ul class="list-content">
        <li>
          1. 中国内地居民
        </li>
        <li>
          1.1 中国内地身份证
        </li>
        <li>
          1.2 内地临时身份证(三个月有效期内)
        </li>
      </ul> -->
    </div>
    <!-- 分页 -->
    <div v-if="canchildList === true">
      <el-pagination
        style="text-align:center;margin-top: 51px;"
        background
        layout="prev, pager, next"
        :hide-on-single-page="true"
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
  name: 'NavMenu',
  props: {
    childMenu: {
      type: Object,
      default: () => {}
    },
    ischildList: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      lang: this.$i18n.locale,
      parentData: {},
      issueListData: [],
      canIssueList: true,
      presentIndex: 0, // 当前的列表的index
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 1
      },
      canchildList: true
    };
  },
  watch: {
    childMenu: {
      handler(newVal, oldVal) {
        this.parentData = newVal;
        this.listDataFun();
      },
      deep: true
    },
    ischildList: {
      handler(newVal, oldVal) {
        this.canchildList = newVal;
      }
    }
  },
  created() {
    this.canchildList = !this.$route.query.itemVal;
  },
  mounted() {

  },
  methods: {
    pagingDoSearch(page) {
      this.pageParams.page = page;
      this.listDataFun();
    },
    listDataFun() { // 列表数据
      this.canIssueList = true;
      this.presentIndex = 0;
      const data = {
        lang: this.lang,
        problemLevelOne: this.parentData.problemLevelOne,
        problemType: this.parentData.problemType
      };
      Object.assign(data, this.pageParams);
      this.$http({
        url: '/common/helpCenter/classify/info',
        method: 'post',
        params: data
      }).then(res => {
        if (res.code === 200) {
          this.pageParams.totalPage = res.totalPage;
          this.issueListData = res.rows || '';
          if (this.$route.query.itemVal && this.issueListData !== '' && this.canchildList === false) { // 判断页面进入场景
            res.rows.forEach((item, index) => {
              if (item.id === this.$route.query.itemVal) {
                this.issueListClick(index);
              }
            });
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    issueListClick(index) {
      this.canIssueList = false;
      this.presentIndex = index;
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
    $CorBlue:#377dda;
    $Core7eaf0:#e7eaf0;
    a{
      text-decoration:none
    }
    .issue-list {
      h2 {
        font-size: 20px;
        color: $Cor43;
        font-weight: bold;
        margin-bottom: 40px;
      }
      ul.list-content {
        li {
          color: $CorBlue;
          font-size: 14px;
          padding: 16px 0;
          cursor: pointer;
          border-bottom: 1px solid $Core7eaf0;
        }
        li:first-child {
          padding-top: 0px;
        }
      }
    }
    .details {
      h2 {
        font-size: 20px;
        color: $Cor43;
        font-weight: bold;
        margin-bottom: 40px;
      }
      ul.list-content {
        margin-bottom: 30px;
        li {
          font-size: 14px;
          color: $Cor43;
          margin-bottom: 20px;
        }
        li:first-child {
          font-size: 16px;
          color: #333;
          font-weight: bold;
          margin-bottom: 20px;
        }
      }
    }
</style>
