<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.information.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right" />
      <span>{{ $t('common.information.title') }}<!--综合信息服务--></span>
      <i class="el-icon-arrow-right" />
      <span>{{ $t('common.information.download') }}<!--表格下载--></span>
    </div>
    <p class="tittle">{{ $t('common.information.download') }}<!--表格下载--></p>
    <div class="disclaimer-txt">
      <div class="content">
        <div style="margin-bottom: 15px;">
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
      <div class="content">
        <div style="margin-bottom: 15px;">
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
      <div class="content">
        <div style="margin-bottom: 15px;">
          <span class="content-title">{{ $t('common.information.FATCA.content_title') }}<!--FATCA资讯--></span>
        </div>
        <div v-for="data in FATCAData" :key="data.title" class="content-line">
          <span class="content-txt" @click="chosePDF3(data.id)">{{ $t('common.information.FATCA.content_' + data.title) }}</span>
          <div class="content-pdf">PDF</div>
          <el-dialog
            :visible.sync="dialogVisible3"
            width="1024px"
          >
            <embed v-show="PDF3_1" class="pdf" src="/static/pdf/fw8ben.pdf">
            <embed v-show="PDF3_2" class="pdf" src="/static/pdf/fw8bene.pdf">
            <embed v-show="PDF3_3" class="pdf" src="/static/pdf/fw9.pdf">
            <embed v-show="PDF3_4" class="pdf" src="/static/pdf/iw8ben表格W-8BEN的说明.pdf">
            <embed v-show="PDF3_5" class="pdf" src="/static/pdf/iw8bene表格W-8BEN-E的说明.pdf">
            <embed v-show="PDF3_6" class="pdf" src="/static/pdf/iw9.pdf">
          </el-dialog>
          <el-button type="text" class="content-more" @click="chosePDF3(data.id)">{{ $t('common.information.read_more') }}<!--查看详情--></el-button>

        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'InformationDownload',
  data() {
    return {
      dialogVisible1: false,
      dialogVisible2: false,
      dialogVisible3: false,
      PDF2_1: false,
      PDF2_2: false,
      PDF2_3: false,
      PDF3_1: false,
      PDF3_2: false,
      PDF3_3: false,
      PDF3_4: false,
      PDF3_5: false,
      PDF3_6: false,
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
      ],
      FATCAData: [
        {
          id: '3_1', title: 'txt_1'
        },
        {
          id: '3_2', title: 'txt_2'
        },
        {
          id: '3_3', title: 'txt_3'
        },
        {
          id: '3_4', title: 'txt_4'
        },
        {
          id: '3_5', title: 'txt_5'
        },
        {
          id: '3_6', title: 'txt_6'
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
    },
    chosePDF3(id) {
      this.PDF3_1 = false;
      this.PDF3_2 = false;
      this.PDF3_3 = false;
      this.PDF3_4 = false;
      this.PDF3_5 = false;
      this.PDF3_6 = false;
      this.dialogVisible3 = true;
      if (id === '3_1') {
        this.PDF3_1 = true;
      } else if (id === '3_2') {
        this.PDF3_2 = true;
      } else if (id === '3_3') {
        this.PDF3_3 = true;
      } else if (id === '3_4') {
        this.PDF3_4 = true;
      } else if (id === '3_5') {
        this.PDF3_5 = true;
      } else if (id === '3_6') {
        this.PDF3_6 = true;
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
      margin: 20px auto 60px;
      background-color: white;
      padding: 0 30px;
      border-radius: 5px;

      .content {
        border-bottom: 1px solid rgba(201, 201, 201, 0.38);
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
