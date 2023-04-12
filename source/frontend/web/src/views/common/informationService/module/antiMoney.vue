<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.information.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right" />
      <span>{{ $t('common.information.title') }}<!--综合信息服务--></span>
      <i class="el-icon-arrow-right" />
      <span>{{ $t('common.information.anti_money.content_title') }}<!--反洗钱新规--></span>
    </div>
    <p class="tittle">{{ $t('common.information.anti_money.content_title') }}<!--反洗钱新规--></p>
    <div class="disclaimer-txt">
      <div class="content">
        <div style="margin-bottom: 25px;">
          <span class="content-title">{{ $t('common.information.anti_money.content_title') }}<!--反洗钱新规--></span>
        </div>
        <div class="content-line">
          <span class="content-txt" @click="dialogVisible1 = true">{{ $t('common.information.anti_money.content_title') }}<!--反洗钱新规--></span>
          <div class="content-pdf">PDF</div>
          <el-button type="text" class="content-more" @click="dialogVisible1 = true">{{ $t('common.information.read_more') }}<!--查看详情--></el-button>
          <el-dialog
            :visible.sync="dialogVisible1"
            width="1024px"
          >
            <embed class="pdf" src="/static/pdf/Anti-Money Laundering Procedures - MOF Securities Limited.pdf">
          </el-dialog>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'AntiMoney',
  data() {
    return {
      dialogVisible1: false
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    }
  }
};
</script>

<style lang="scss" scoped>
  .app-container {
    min-height: 550px;
    .line {
      width: 1200px;
      margin: 0 auto;
      padding-top: 20px;
    }

    .tittle {
      width: 300px;
      font-size: 24px;
      font-weight: 400;
      text-align: center;
      padding-top: 70px;
      margin: auto;
    }
    .pdf {
      width: 100%;
      height: 600px;
    }

    .disclaimer-txt {
      width: 1150px;
      margin: 30px auto 60px;
      background-color: white;
      padding: 0 30px;
      border-radius: 5px;

      .content {
        padding: 28px 0;

        .content-id {
          color: #ff0000;
          font-size: 15px;
          font-weight: 400;
          padding: 10px;
        }

        .content-title {
          font-size: 16px;
          padding-bottom: 10px;
        }

        .content-txt {
          color: #999999;
          cursor:pointer;
        }

        .content-pdf {
          display: inline-block;
          border: 1px solid rgba(153, 153, 153, 1);
          border-radius: 2px;
          font-size: 9px;
          color: rgba(153, 153, 153, 1);
          text-align: center;
          margin-left: 10px;
          padding: 2px;
          position: absolute;
        }
        .content-line {
          margin: 10px 0;
          height: 20px;
        }
        .content-more {
          float: right;
          color: #000;
          padding: 0;
        }
      }
    }
  }
</style>
