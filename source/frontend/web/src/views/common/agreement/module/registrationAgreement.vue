<template>
  <div class="app-container">
    <div class="line">
      <router-link to="/index">
        <span>{{ $t('common.agreement.registration_agreement.home') }}<!--国瑞首页--></span>
      </router-link>
      <i class="el-icon-arrow-right"/>
      <span>{{ $t('common.agreement.registration_agreement.title') }}<!--注册协议--></span>
    </div>
    <p class="tittle">{{ $t('common.agreement.registration_agreement.title') }}<!--注册协议--></p>
    <div class="disclaimer-txt">
      <div v-for="data in contentData" :key="data.id" class="content">
        <span class="content-id">{{ data.id }}</span>
        <span class="content-title">{{ $t('common.agreement.registration_agreement.' + data.title) }}</span>
        <div class="content-pdf">PDF</div>
        <el-button type="text" class="content-more" @click="chosePDF(data.id)">{{ $t('common.agreement.registration_agreement.read_more') }}<!--查看详情--></el-button>
        <el-dialog
          :visible.sync="dialogVisible"
          width="1024px"
        >
          <embed v-if="PDF1" class="pdf" src="/static/pdf/Notice Regarding NFA's BASIC System.pdf">
          <embed v-if="PDF2" class="pdf" src="/static/pdf/US Margin Trading Risk Disclosure.pdf">
          <embed v-if="PDF3" class="pdf" src="/static/pdf/客户协议美国.pdf">
          <embed v-if="PDF4" class="pdf" src="/static/pdf/美国股票止损定单披露.pdf">
          <embed v-if="PDF5" class="pdf" src="/static/pdf/日内交易风险披露声明.pdf">
          <embed v-if="PDF6" class="pdf" src="/static/pdf/市后交易风险.pdf">
          <embed v-if="PDF7" class="pdf" src="/static/pdf/投资组合保证金风险披露.pdf">
          <embed v-if="PDF8" class="pdf" src="/static/pdf/香港风险披露 HONG KONG RISK DISCLOSURE STATEMENT.pdf">
          <embed v-if="PDF9" class="pdf" src="/static/pdf/有关您IB账户的重要法律条款.pdf">
          <embed v-if="PDF10" class="pdf" src="/static/pdf/账户交易控制及所有权证明.pdf">
          <embed v-if="PDF11" class="pdf" src="/static/pdf/Forex and Multi-Currency Risk Disclosure.pdf">
          <embed v-if="PDF12" class="pdf" src="/static/pdf/MOF Securities Privacy Policy.pdf">
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RegistrationAgreement',
  data() {
    return {
      dialogVisible: false,
      PDF1: false,
      PDF2: false,
      PDF3: false,
      PDF4: false,
      PDF5: false,
      PDF6: false,
      PDF7: false,
      PDF8: false,
      PDF9: false,
      PDF10: false,
      PDF11: false,
      PDF12: false,
      contentData: [
        { id: '01', title: 'pdf_1' }, // Notice Regarding NFA's BASIC System
        { id: '02', title: 'pdf_2' }, // US Margin Trading Risk Disclosure
        { id: '03', title: 'pdf_3' }, // 客户协议美国
        { id: '04', title: 'pdf_4' }, // 美国股票止损定单披露
        { id: '05', title: 'pdf_5' }, // 日内交易风险披露声明
        { id: '06', title: 'pdf_6' }, // 市后交易风险
        { id: '07', title: 'pdf_7' }, // 投资组合保证金风险披露
        { id: '08', title: 'pdf_8' }, // 香港风险披露
        { id: '09', title: 'pdf_9' }, // 有关您IB账户的重要法律条款
        { id: '10', title: 'pdf_10' }, // 账户交易控制及所有权证明
        { id: '11', title: 'pdf_11' }, // Forex and Multi-Currency Risk Disclosure
        { id: '12', title: 'pdf_12' } // MOF Securities Privacy Policy
      ]
    };
  },
  methods: {
    chosePDF(id) {
      this.PDF1 = false;
      this.PDF2 = false;
      this.PDF3 = false;
      this.PDF4 = false;
      this.PDF5 = false;
      this.PDF6 = false;
      this.PDF7 = false;
      this.PDF8 = false;
      this.PDF9 = false;
      this.PDF10 = false;
      this.PDF11 = false;
      this.PDF12 = false;
      this.dialogVisible = true;
      if (id === '01') {
        this.PDF1 = true;
      } else if (id === '02') {
        this.PDF2 = true;
      } else if (id === '03') {
        this.PDF3 = true;
      } else if (id === '04') {
        this.PDF4 = true;
      } else if (id === '05') {
        this.PDF5 = true;
      } else if (id === '06') {
        this.PDF6 = true;
      } else if (id === '07') {
        this.PDF7 = true;
      } else if (id === '08') {
        this.PDF8 = true;
      } else if (id === '09') {
        this.PDF9 = true;
      } else if (id === '10') {
        this.PDF10 = true;
      } else if (id === '11') {
        this.PDF11 = true;
      } else if (id === '12') {
        this.PDF12 = true;
      }
    }
  }
};
</script>

<style lang="scss" scoped>
  .app-container {
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
      padding-top: 50px;
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

        .content-more {
          float: right;
          color: #000;
          padding: 0;
        }
      }
    }
  }
</style>
