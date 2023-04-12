<template>
  <div>
    <div class="at-present-position">
      <ul class="clearfix">
        <li>
          <el-breadcrumb separator=">" style="line-height: inherit;">
            <el-breadcrumb-item :to="{ name: 'SearchHomepage' }">{{ $t('common.help.search_result.crumbs_item_1') }}</el-breadcrumb-item> <!-- $i18n - 帮助中心 -->
            <el-breadcrumb-item>{{ $t('common.help.search_result.crumbs_item_2') }}</el-breadcrumb-item> <!-- $i18n - 搜索结果 -->
          </el-breadcrumb>
        </li>
        <li>
          <img style="cursor:pointer;" src="@/assets/images/common/help/icon_search.png" alt="" @click="searchBtn(searchTerms)">
          <input v-model="searchTerms" maxlength="16" type="text" :placeholder="$t('common.help.common_message.search_input_placeholder')" @keyup.enter="searchBtn(searchTerms)"> <!-- $i18n - 请输入关键词 -->
          <img v-show="searchTerms" style="cursor:pointer;" src="@/assets/images/common/help/icon_cancel.png" alt="" @click="searchTerms=''">
        </li>
      </ul>
    </div>
    <div class="main-content searchResult-em">
      <div class="content">
        <div v-if="listData.length > 0" class="result">{{ $t('common.help.search_result.result_describe.describe_1') }}<em class="red-em">{{ searchResult }}</em>{{ $t('common.help.search_result.result_describe.describe_2') }}{{ total }}{{ $t('common.help.search_result.result_describe.describe_3') }}</div> <!-- $i18n - 搜索到与 && 相关的结果有 && 条 -->
        <div v-else class="result">{{ $t('common.help.search_result.result_describe.describe_4') }}<em class="red-em">{{ searchResult }}</em>{{ $t('common.help.search_result.result_describe.describe_5') }}</div> <!-- $i18n - 未找到与 && 相关的结果 -->
        <ul>
          <li v-for="(item,index) in listData" :key="index" style="cursor:pointer;" @click="familiarIssueBtn(item)">
            <div class="title" v-html="item.problemLevelTwo" />
            <p v-html="item.searchContent" />
          </li>
          <!-- 分页 -->
          <el-pagination
            style="text-align: right;; margin-top: 40px;"
            background
            layout="prev, pager, next"
            :hide-on-single-page="false"
            :page-size="pageParams.rows"
            :page-count="pageParams.totalPage"
            :current-page="pageParams.page"
            class="pagination"
            @current-change="pagingDoSearch"
          />
        </ul>

      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      lang: this.$i18n.locale,
      pageSearchStatus: false,
      searchTerms: '',
      total: 0,
      searchResult: '',
      listData: [],
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 1
      }
    };
  },
  beforeCreate() {

  },
  created() {
    this.searchResult = this.$route.query.val || this.searchTerms;
    this.familiarIssue();
  },
  mounted() {

  },
  methods: {
    textFiltration(item) {
      // debugger;
      // item.problemLevelTwo = item.problemLevelTwo.replace(new RegExp(this.searchResult, 'g'), `<em class="red-em">${this.searchResult}</em>`);
      // item.problemSolving = item.problemSolving.replace(new RegExp(this.searchResult, 'g'), `<em class="red-em">${this.searchResult}</em>`);
      return item;
    },
    searchBtn(val) {
      this.pageSearchStatus = true;
      const newVal = val.replace(/(^\s*)|(\s*$)/g, '');
      if (newVal) {
        this.searchResult = this.searchTerms;
        this.familiarIssue(newVal);
      } else {
        this.$message.error(this.$i18n.t('common.help.common_message.error_1')); // $i18n - 请输入需要检索的内容
      }
    },
    familiarIssue(val = this.$route.query.val) {
      const data = {
        lang: this.lang,
        searchValue: val
      };
      Object.assign(data, this.pageParams);
      this.$http({
        url: '/common/helpCenter/search',
        method: 'post',
        params: data
      }).then(res => {
        if (res.code === 200) {
          this.total = res.total;
          this.pageParams.totalPage = res.totalPage;
          // res.rows[0].problemSolving = `"<strong style="color: rgb(194, 133, 255);"><em><u>问：地球为什么是圆的？</u></em></strong><p><br></p><blockquote><span style="color: rgb(194, 133, 255);">答：因为地心引力</span></blockquote><pre>标签标签标签标签标签标签标签标签标签标签标签标签标签标签标签↵</pre>"`;
          var results = res.rows.map(this.textFiltration);
          this.listData = results;
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    pagingDoSearch(page) {
      this.pageParams.page = page;
      this.familiarIssue(this.pageSearchStatus ? this.searchTerms : undefined);
    },
    familiarIssueBtn(item) { // 问题解答点击 =>内容页
      this.$router.push(
        {
          name: 'ClassifyFindChild',
          query: { type: item.parent.id, childrenInfo: item.parent.childrenInfo.id, itemVal: item.id }
        }
      );
    }
  }
};
</script>
<style lang="scss" >
  .searchResult-em .red-em{
    font-style: normal;
    color: red;
  }
  .searchResult-em li>.title .red-em{
    font-size: 18px;
  }
</style>
<style lang="scss" scoped>

  .at-present-position {
    width: 100%;
    height: 64px;
    ul {
      width: 1200px;
      margin: 0 auto;
      li:first-child {
        float: left;
        line-height: 64px;
      }
      li:last-child {
        float: right;
        width: 300px;
        height: 40px;
        background-color: #fff;
        box-sizing: border-box;
        border-radius: 5px;
        margin-top: 12px;
        padding: 0 16px;
        display: flex;
        align-items: center;
        img {
          width: 14px;
          height: 14px;
          padding-top: 1px;
        }
        input {
          height: 100%;
          border: none;
          padding: 0;
          outline:none;
          flex: 1;
          padding-left: 3px;
        }
      }
    }
  }

  .main-content {
    width: 100%;
    background-color: #fff;
    overflow: hidden;
    .content {
      width: 1200px;
      margin: 0 auto;
      .result {
        font-size: 14px;
        color: #434343;
        margin-top: 20px;
        margin-bottom: 40px;
      }
      >ul {
        padding-bottom: 80px;
        >li {
          margin-top: 30px;
          padding-bottom: 30px;
          border-bottom: 1px solid rgb(216, 216, 216);
          >.title {
            font-size: 18px;
            color: #377dda;
            font-weight: 400;
            margin-bottom: 20px;
          }
          >p {
            font-size: 14px;
            color: #999;
            line-height: 20px;
          }
        }
      }
    }
  }

</style>
