<template>
  <div>
    <div class="at-present-position">
      <ul class="clearfix">
        <li>
          <el-breadcrumb separator=">" style="line-height: inherit;">
            <el-breadcrumb-item :to="{ name: 'SearchHomepage' }">{{ $t('common.help.search_result.crumbs_item_1') }}</el-breadcrumb-item> <!-- $i18n - 帮助中心 -->
            <el-breadcrumb-item>{{ nowMenuObj.dictDest }}</el-breadcrumb-item>
            <el-breadcrumb-item>{{ nowChildMenuObj.dictDest }}</el-breadcrumb-item>
          </el-breadcrumb>
        </li>
        <li>
          <img src="@/assets/images/common/help/icon_search.png" alt="" @click="searchBtn(searchTerms)">
          <input v-model="searchTerms" maxlength="16" type="text" :placeholder="$t('common.help.common_message.search_input_placeholder')" @keyup.enter="searchBtn(searchTerms)"> <!-- $i18n - 请输入关键词 -->
          <img v-if="searchTerms!==''" src="@/assets/images/common/help/icon_cancel.png" alt="">
        </li>
      </ul>
    </div>
    <div class="main-content searchResult-em">
      <div class="content">
        <div class="left-menu">
          <el-menu
            :default-active="nowChildMenuObj.id"
            class="el-menu-vertical-demo"
            :unique-opened="true"
            @select="handleSelect"
            @open="handleOpen"
            @close="handleClose"
          >
            <ChildMenu :menu-list="menuList" />
          </el-menu>
        </div>
        <div class="right-content">
          <router-view :child-menu="childMenuData" :ischild-list="ischildList" />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import ChildMenu from './commonChild/childMenu.vue';
export default {
  components: {
    ChildMenu
  },
  data() {
    return {
      lang: this.$i18n.locale,
      searchTerms: '',
      nowMenuObj: {

      },
      nowChildMenuObj: {
        id: '9200000080003',
        dictDest: '',
        dictValue: ''
      },
      ischildList: false,
      childMenuData: {},
      menuList: [
        // {
        // "id": "9200000080001",
        // "dictCode": "helpCenterType",
        // "dictName": "帮助中心类型",
        // "dictValue": "accountOpenClass",
        // "dictDest": "开户类",
        // "lang": "zh_CN",
        // "sortNum": 42,
        // "createBy": "200000000000",
        // "createDate": 200000000000,
        // "updateBy": "200000000000",
        // "updateDate": 200000000000,
        // "children": [{
        // "id": "9200000090001",
        // "dictCode": "accountOpenClass",
        // "dictName": "帮助中心类型_子节点",
        // "dictValue": "Advantages of Guorui securities",
        // "dictDest": "国瑞证券优势",
        // "lang": "zh_CN",
        // "sortNum": 43,
        // "createBy": "200000000000",
        // "createDate": 200000000000,
        // "updateBy": "200000000000",
        // "updateDate": 200000000000,
        // "children": ""
        // }, {
        // "id": "9200000090002",
        // "dictCode": "accountOpenClass",
        // "dictName": "帮助中心类型_子节点",
        // "dictValue": "Account opening process",
        // "dictDest": "开户流程",
        // "lang": "zh_CN",
        // "sortNum": 43,
        // "createBy": "200000000000",
        // "createDate": 200000000000,
        // "updateBy": "200000000000",
        // "updateDate": 200000000000,
        // "children": ""
        // } ]
        // }
      ]

    };
  },
  beforeCreate() {

  },
  created() {
    // this.menuList = config;
    this.menuData();
  },
  mounted() {

  },
  methods: {
    menuData() { // 侧边栏路由等数据
      this.$http({
        url: '/common/helpCenter/classifyTree',
        method: 'post',
        params: { lang: this.lang }
      }).then(res => {
        if (res.code === 200) {
          this.menuList = res.object || '';
          // 通过问题搜索页多携带个参数来 判断是否是从搜索页来的 是就携带菜单id和child的id去调用下面的方法
          if (this.$route.query.childrenInfo) {
            this.setNowMenuObj(this.$route.query.type, this.$route.query.childrenInfo);
          } else {
            this.setNowMenuObj(undefined, undefined);
          }
        }
      }).catch(error => {
        console.log(error);
      });
    },
    setNowMenuObj(id = this.$route.query.type, childId) { // 更新当前选中菜单obj 和 child菜单obj
      const arr = this.menuList;
      for (let i = 0; i < arr.length; i++) {
        if (arr[i].id === id) {
          this.nowMenuObj = arr[i];
          if (childId) {
            for (let j = 0; j < arr[i].children.length; j++) {
              if (arr[i].children[j].id === childId) {
                this.nowChildMenuObj.id = arr[i].children[j].id;
                this.nowChildMenuObj.dictDest = arr[i].children[j].dictDest;
                this.nowChildMenuObj.dictValue = arr[i].children[j].dictValue;
              }
            }
          } else {
            this.nowChildMenuObj.id = arr[i].children[0].id;
            this.nowChildMenuObj.dictDest = arr[i].children[0].dictDest;
            this.nowChildMenuObj.dictValue = arr[i].children[0].dictValue;
          }
        }
      }
      this.childMenuData = {
        problemType: this.nowMenuObj.dictValue,
        problemLevelOne: this.nowChildMenuObj.dictValue,
        classDictDest: this.nowChildMenuObj.dictDest
      };
    },
    handleOpen(key, keyPath) {
      console.log('sub-menu 展开的回调-----', key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log('sub-menu 展开的回调-----', key, keyPath);
    },
    handleSelect(key, keyPath) {
      if (keyPath.length > 1) {
        this.setNowMenuObj(keyPath[0], key);
      }
      console.log('菜单激活-----' + key, keyPath);
      this.ischildList = true;
    },
    searchBtn(val) { // 搜索btn点击 - 跳转搜索结果页
      const newVal = val.replace(/(^\s*)|(\s*$)/g, '');
      if (!newVal) {
        this.$message.error(this.$i18n.t('common.help.common_message.error_1')); // $i18n - 请输入需要检索的内容
        return;
      }
      this.$router.push({ name: 'SearchResult', query: { val: newVal }});
    }
  }
};
</script>
<style lang="scss" >
  .searchResult-em .red-em{
    font-style: normal;
    color: red;
  }
  .searchResult-em li>.title em{
    font-size: 18px;
  }
</style>
<style lang="scss" scoped>

  .at-present-position {
    width: 100%;
    height: 64px;
    ul {
      width: 1200px;
      height: 100%;
      margin: 0 auto;
      li:first-child {
        float: left;
        line-height: 64px;
        height: 100%;
        vertical-align: middle;
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
      min-height: 360px;
      margin: 0 auto;
      padding: 50px 0;
      display: flex;
      .left-menu {
        min-width: 200px;
        max-width: 400px;
      }
      .right-content {
        flex: 1;
        margin-left: 30px;
      }
    }
  }
</style>
