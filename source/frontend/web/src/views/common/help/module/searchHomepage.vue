<template>
  <div>
    <!-- <img class="help-banner" src="@/assets/images/common/banner_image_express.png"/> -->
    <div class="help-banner">
      <div class="title">
        <h2>{{ $t('common.help.home.welcome_speech') }}</h2> <!-- $i18n - 欢迎来到帮助中心 -->
        <p class="describe">{{ $t('common.help.home.offer_help') }}</p> <!-- $i18n - 我们为您提供更多帮助 -->
      </div>
      <ul class="search-box clearfix">
        <li>
          <img src="@/assets/images/common/help/icon_search.png" alt="">
          <input v-model="searchTerms" maxlength="16" type="text" :placeholder="$t('common.help.common_message.search_input_placeholder')" @keyup.enter="searchBtn(searchTerms)"> <!-- $i18n - 请输入关键词 -->
        </li>
        <!-- <li @click="searchBtn(searchTerms)">{{ $t('common.help.home.search_input_btn') }}</li> $i18n - 搜索 -->
        <li onclick="alert(@/assets/images/common/help/icon_search.png)">{{ $t('common.help.home.search_input_btn') }}</li>
      </ul>
    </div>
    <div class="classify-seek">
      <div class="content">
        <h2>{{ $t('common.help.home.classify_find_title') }}</h2> <!-- $i18n - 分类查找 -->
        <ul class="clearfix contentImg-list">
          <li @click="classifyClick('9200000080001')">
            <img src="@/assets/images/common/help/icon_seek_open_account.png" alt="">
            <span>{{ $t('common.help.home.classify_find_item1') }}</span> <!-- $i18n - 开户相关 -->
          </li>
          <li @click="classifyClick('9200000080003')">
            <img src="@/assets/images/common/help/icon_seek_capital.png" alt="">
            <span>{{ $t('common.help.home.classify_find_item2') }}</span> <!-- $i18n - 资金相关 -->
          </li>
          <li @click="classifyClick('9200000080004')">
            <img src="@/assets/images/common/help/icon_seek_charge.png" alt="">
            <span>{{ $t('common.help.home.classify_find_item3') }}</span> <!-- $i18n - 收费相关 -->
          </li>
          <li @click="classifyClick('9200000080002')">
            <img src="@/assets/images/common/help/icon_seek_deal.png" alt="">
            <span>{{ $t('common.help.home.classify_find_item4') }}</span> <!-- $i18n - 交易相关 -->
          </li>
        </ul>
      </div>
    </div>
    <div class="issue">
      <div class="content">
        <h2>{{ $t('common.help.home.familiar_issue') }}</h2> <!-- $i18n - 常见问题 -->
        <div v-for="(item,index) in familiarIssueList" :key="index" class="issue-items" @click="familiarIssueBtn(item)">
          <h6>Q: {{ item.problemLevelTwo }}</h6>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      lang: this.$i18n.locale,
      searchTerms: '',
      familiarIssueList: [
        // {
        //   "id": "293574842278612992",
        //   "problemLevelOne": "问题（一级）",
        //   "problemLevelTwo": "问题（二级）",
        //   "problemType": "问题类型",
        //   "problemSolving": "问题解答",
        //   "pictUrl": "",
        //   "status": 1,
        //   "lang": "en_US",
        //   "isHot": true,
        //   "createBy": "200000000000",
        //   "createDate": 1574738327755,
        //   "updateBy": "200000000000",
        //   "updateDate": 1574738327755,
        //   "createDateStart": "",
        //   "createDateEnd": ""
        // }
      ],
      pageParams: {
        'rows': 3,
        'page': 1,
        'totalPage': 1
      }
    };
  },
  beforeCreate() {

  },
  created() {
    this.familiarIssueFun();
  },
  mounted() {

  },
  methods: {
    searchBtn(val) { // 搜索btn点击
      const newVal = val.replace(/(^\s*)|(\s*$)/g, '');
      if (!newVal) {
        this.$message.error(this.$i18n.t('common.help.common_message.error_1')); // $i18n - 请输入需要检索的内容
        return;
      }
      this.$router.push({ path: 'searchResult', query: { val: newVal }});
    },
    // if (res.object[i].address.indexOf('-#-') !== -1) {
    // const dizhi = res.object[i].address.split('-#-');
    classifyClick(type, issue = '') { // 分来查找
      this.$router.push(
        {
          name: 'ClassifyFindChild',
          query: { type, issue }
        }
      );
    },
    familiarIssueFun() {
      const data = {
        lang: this.lang,
        isHot: true
      };
      Object.assign(data, this.pageParams);
      this.$http({
        url: '/common/helpCenter/data',
        method: 'post',
        params: data
      }).then(res => {
        if (res.code === 200) {
          this.familiarIssueList = res.rows;
        } else {
          this.$message.error(res.message || res.code);
        }
      }).catch(error => {
        this.$message.error(error);
      });
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
<style lang="scss" scoped>
  $BGcolor:#FF5949;
  $Cor999:#999;
  $Corfff:#fff;
  $Cor43:#434343;
  $CorF6:#f6f6f6;
  .help-banner {
    width: 100%;
    // max-height: 350px;
    height: 350px;
    background-color: rgb(109, 109, 109);
    background: url('~@/assets/images/common/help/help_banner.png') no-repeat;
    background-size: 100%;
    overflow: hidden;
    .title {
      text-align: center;
      margin-top: 4.1%;
      margin-bottom: 2%;
      color: #fff;
      h2 {
        font-size: 50px;
        margin-bottom: 1.6%;
      }
      .describe {
        font-size: 24px;
      }
    }
    .search-box {
      width: 800px;
      height: 40px;
      background-color: red;
      margin: 0 auto;
      border-radius: 5px;
      font-size: 0px;
      margin-bottom: 3%;
      li {
        float: left;
      }
      li:first-child {
        width: 700px;
        height: 100%;
        background-color: #fff;
        border-radius: 5px 0px 0px 5px;
        position: relative;
        img {
          width: 14px;
          height: 14px;
          background-size: 10%;
          position: absolute;
          top: 13px;
          left: 10px;
        }
        input {
          width: 100%;
          height: 100%;
          border: none;
          padding: 0 10px 0 30px;
          box-sizing: border-box;
          border-radius: 5px 0px 0px 5px;
        }
      }
      li:last-child {
        width: 100px;
        height: 100%;
        background-color: red;
        color: #fff;
        border-radius: 0px 5px 5px 0px;
        text-align: center;
        line-height: 40px;
        cursor:pointer;
      }
    }
  }
  .classify-seek {
    .content {
      width: 1200px;
      margin: 0 auto;
      padding: 60px 0;
      h2 {
        text-align: center;
        font-size: 30px;
        color: #434343;
        margin-top: 30px;
        margin-bottom: 30px;
      }
      ul.contentImg-list {
        li {
          float: left;
          width: 285px;
          height: 200px;
          background-color: #fff;
          margin-right: 20px;
          margin-bottom: 20px;
          border-radius: 5px;
          cursor:pointer;
          &:nth-child(4n) {
            margin-right: 0px;
          }
          display: flex;
          flex-direction: column;
          align-items: center;
          justify-content: center;
          span {
            margin-top: 20px;
            color: $Cor43;
          }
        }
      }
    }
  }

  .issue {
    background-color: #fff;
    .content {
      width: 1200px;
      margin: 0 auto;
      padding: 60px 0;
      h2 {
        text-align: center;
        font-size: 30px;
        color: #434343;
        margin-top: 30px;
        margin-bottom: 30px;
      }
      .issue-items {
        border: 1px solid rgb(206, 206, 206);
        border-radius: 4px;
        margin-bottom: 20px;
        h6 {
          padding-left: 20px;
          line-height: 40px;
          cursor:pointer;
        }
      }
    }
  }

</style>
