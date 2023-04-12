<template>
  <div class="noticeList">
    <el-breadcrumb separator-class="el-icon-arrow-right" class="breadcrumb">
      <el-breadcrumb-item :to="{ path: '/index' }">国瑞首页</el-breadcrumb-item>
      <el-breadcrumb-item>最新公告</el-breadcrumb-item>
    </el-breadcrumb>
    <el-row class="newNotice">最新公告</el-row>
    <div class="notices">
      <el-row v-for="(item,index) in data" :key="index" class="noticeRow">
        <router-link target="_blank" :to="{path:'/notice',query: {id:item.id}}">
          <el-col class="col20" :span="20">
            <el-tooltip ref="tooltip" class="tooltip" effect="dark" :content="item.title" placement="top" :disabled="tooltipShow[index]">
              <div class="title">【平台公告】{{ item.title }}</div>
            </el-tooltip>
          </el-col>
          <el-col class="col4" :span="4">{{ item.releaseDate }}</el-col>
        </router-link>
      </el-row>

    </div>
  </div>
</template>
<script>
import cookies from 'js-cookie';
export default {
  data() {
    return {
      data: [],
      pageParams: {
        rows: 1000,
        page: 1, // 当前页
        totalPage: 0
      },
      tooltipShow: []
    };
  },
  created() {
    this.getNewNotice();
  },
  methods: {
    getNewNotice() {
      this.$http({
        url: '/common/newsBulletin/data',
        method: 'post',
        params: Object.assign(this.pageParams, { status: 1, clientType: 'OfficialWebsite', lang: cookies.get('locale') })
      }).then(res => {
        if (res.code === 200) {
          res.rows.map(item => {
            item.releaseDate = this.$verify.NowDate3(new Date(item.releaseDate));
            return item;
          });
          this.data = res.rows;
          this.$nextTick(() => {
            console.log(this.$refs.tooltip, 'this.$refs.tooltip');
            this.$refs.tooltip.map(item => {
              if (item.referenceElm.clientWidth < 923) {
                this.tooltipShow.push(true);
              } else {
                this.tooltipShow.push(false);
              }
              return item;
            });
          });
          // console.log(res);
        } else {
          this.$message(res.message);
        }
      }).catch(error => {
        this.$message.error(error);
      });
    }
  }
};
</script>
<style lang="scss" scoped>
    .noticeList{
        margin-top: 20px;
    }
</style>
<style lang="scss" scoped>
    .noticeList{
        width: 1200px;
        margin: 0 auto;
        .breadcrumb{
            margin: 20px 0px 46px 0px;
        }
        .newNotice{
            text-align: center;
            font-size: 24px;
            margin-bottom: 24px;
            color: #434343;
        }
        .notices{
            padding: 20px 0;
            background-color: #fff;
            margin-bottom: 80px;
            .noticeRow{
                padding: 0 44px 0 48px;
                border-bottom: 1px solid #E7EAF0;
                .col20{
                    // line-height: 72px;
                    display: inline-block;
                    text-align: left;
                    color: #666;
                    .title{
                      max-width: 100%;
                      display: inline-block;
                      overflow: hidden;
                      height: 72px;
                      line-height: 72px;
                      text-overflow: ellipsis;
                      white-space: nowrap;
                    }
                    .tooltip{
                      display: inline-block;
                    }
                }
                .col4{
                    text-align: right;
                    color: #666;
                    line-height: 72px;
                }
            }
            :last-child{
                border-bottom: 0px solid #E7EAF0;
            }

        }
    }
</style>
