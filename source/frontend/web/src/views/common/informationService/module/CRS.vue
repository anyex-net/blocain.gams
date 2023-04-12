<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.information.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right" />
      <span>{{ $t('common.information.title') }}<!--综合信息服务--></span>
      <i class="el-icon-arrow-right" />
      <span>{{ $t('common.information.CRS.content_title') }}<!--CRS资讯--></span>
    </div>
    <p class="tittle">{{ $t('common.information.CRS.content_title') }}<!--CRS资讯--></p>
    <div class="disclaimer-txt">
      <div class="content">
        <div style="margin-bottom: 25px;">
          <span class="content-title">{{ $t('common.information.CRS.content_title') }}<!--CRS资讯--></span>
        </div>
        <div v-for="data in CRSData" :key="data.title" class="content-line">
          <span class="content-txt" @click="chosePDF(data.id)">{{ $t('common.information.CRS.content_' + data.title) }}</span>
          <div class="content-pdf">PDF</div>
          <el-dialog
            :visible.sync="dialogVisible2"
            width="1024px"
          >
            <embed v-show="PDF2_1" class="pdf" src="/static/pdf/共同汇报标准（CRS）个人自我证明表格（CRS-I）.pdf">
            <embed v-show="PDF2_2" class="pdf" src="/static/pdf/共同汇报标准（CRS）控制人自我证明表格（CRS-CP）.pdf">
            <embed v-show="PDF2_3" class="pdf" src="/static/pdf/共同汇报标准（CRS）实体自我证明表格（CRS-E）.pdf">
          </el-dialog>
          <el-button type="text" class="content-more" @click="chosePDF(data.id)">{{ $t('common.information.read_more') }}<!--查看详情--></el-button>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'CRS',
  data() {
    return {
      dialogVisible2: false,
      PDF2_1: false,
      PDF2_2: false,
      PDF2_3: false,
      CRSData: [
        {
          id: '2_1', title: 'txt_1'
        },
        {
          id: '2_2', title: 'txt_2'
        },
        {
          id: '2_3', title: 'txt_3'
        }
      ]
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    chosePDF(id) {
      this.PDF2_1 = false;
      this.PDF2_2 = false;
      this.PDF2_3 = false;
      this.dialogVisible2 = true;
      if (id === '2_1') {
        this.PDF2_1 = true;
      } else if (id === '2_2') {
        this.PDF2_2 = true;
      } else if (id === '2_3') {
        this.PDF2_3 = true;
      }
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
