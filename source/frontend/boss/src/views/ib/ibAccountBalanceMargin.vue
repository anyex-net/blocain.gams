<template>
  <div class="app-container">
    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini" :rules="searchRules">
      <el-form-item label="账户ID" prop="accountId">
        <el-input v-model="searchForm.accountId" clearable placeholder="请输入账户ID"></el-input>
      </el-form-item>
      <el-form-item label="IB账户代码">
        <el-input v-model="searchForm.accountCode" clearable placeholder="请输入IB账户代码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="ibAccountBalanceMarginLoading" :data="ibAccountBalanceMarginData" style="width:100%;margin-bottom:20px;" border row-key="id" highlight-current-row>
      <el-table-column prop="accountId" label="账户ID" :fixed="true" width="170px" />
      <el-table-column prop="accountName" label="账户名" :fixed="true" />
      <el-table-column prop="accountCode" label="IB账户代码" width="120px" :fixed="true" />
      <el-table-column prop="accountType" label="账户类型" :fixed="true" />
      <el-table-column prop="accountReady" label="账户就绪" :fixed="true" />
      <el-table-column prop="currency" label="币种" :fixed="true" />
      <el-table-column prop="cushion" label="Cushion " />
      <el-table-column prop="lookAheadNextChange" label="前瞻下一变化" />
      <el-table-column prop="nlvAndMarginInReview" label="净清算值和保证金审查" />
      <el-table-column prop="accruedCash" label="应计现金" />
      <el-table-column prop="accruedDividend" label="应计股息" />
      <el-table-column prop="availableFunds" label="可用资金" />
      <el-table-column prop="billable" label="Billable" />
      <el-table-column prop="buyingPower" label="购买力" />
      <el-table-column prop="equityWithLoanValue" label="含贷款价值的资产" />
      <el-table-column prop="excessLiquidity" label="剩余流动性" />
      <el-table-column prop="fullAvailableFunds" label="全部可用资金" />
      <el-table-column prop="fullExcessLiquidity" label="全部过剩流动性" />
      <el-table-column prop="fullInitMarginReq" label="全部初始保证金要求" />
      <el-table-column prop="fullMaintMarginReq" label="全部维持保证金要求 " />
      <el-table-column prop="grossPositionValue" label="总头寸价值" />
      <el-table-column prop="guarantee" label="担保" />
      <el-table-column prop="indianStockHaircut" label="印度股票" />
      <el-table-column prop="initMarginReq" label="初始保证金要求" />
      <el-table-column prop="lookAheadAvailableFunds" label="前瞻可用资金" />
      <el-table-column prop="lookAheadExcessLiquidity" label="前瞻剩余流动性" />
      <el-table-column prop="lookAheadInitMarginReq" label="前瞻初始保证金要求" />
      <el-table-column prop="lookAheadMaintMarginReq" label="前瞻维持保证金要求" />
      <el-table-column prop="maintMarginReq" label="维持保证金要求" />
      <el-table-column prop="netLiquidation" label="净清算 " />
      <el-table-column prop="netLiquidationUncertainty" label="净清算不确定" />
      <el-table-column prop="pasharesValue" label="PAShares_Value" />
      <el-table-column prop="postExpirationExcess" label="到期后剩余" />
      <el-table-column prop="postExpirationMargin" label="到期后保证金" />
      <el-table-column prop="totalCashvalue" label="总现金价值" />
      <el-table-column prop="debitCardPendingCharges" label="待支付的借记卡费用" />
      <el-table-column prop="updateDate" :formatter="dateFormat" width="200" label="更新时间" />
      <el-table-column label="操作" width="150">
        <template slot-scope="scope">
          <el-button size="mini" type="success" @click="dialogEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="doDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      style="text-align:center;"
      background
      layout="prev, pager, next"
      :hide-on-single-page="true"
      :page-size="pageParams.rows"
      :page-count="pageParams.totalPage"
      :current-page="pageParams.page"
      @current-change="doSearch"
    />

    <el-dialog title="当日盈亏" :visible.sync="ibAccountBalanceMarginDialog" width="660px">
      <el-form ref="ibAccountBalanceMarginForm" :inline="true" :model="ibAccountBalanceMarginForm" :rules="ibAccountBalanceMarginPnLRules" label-width="100px" class="ibAccountBalanceMarginForm">
        <el-form-item label="账户ID" prop="accountId">
          <el-input v-model="ibAccountBalanceMarginForm.accountId" />
        </el-form-item>
        <el-form-item label="账户类型" prop="accountType">
          <el-input v-model="ibAccountBalanceMarginForm.accountType" />
        </el-form-item>
        <el-form-item label="IB账户代码" prop="accountCode">
          <el-input v-model="ibAccountBalanceMarginForm.accountCode" />
        </el-form-item>
        <el-form-item label="账户就绪" prop="accountReady">
          <el-input v-model="ibAccountBalanceMarginForm.accountReady" />
        </el-form-item>
        <el-form-item label="币种" prop="currency">
          <el-select v-model="ibAccountBalanceMarginForm.currency" placeholder="请选择币种" value="" style="width: 180px;">
            <el-option v-for="data in baseCurrencyData" :key="data.id" :label="data.dictDest" :value="data.dictValue">
              <span style="float:left;">{{ data.dictValue }}</span>
              <span style="float:right">{{ data.dictDest }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="Cushion" prop="cushion">
          <el-input v-model="ibAccountBalanceMarginForm.cushion" />
        </el-form-item>
        <el-form-item label="前瞻下一变化" prop="lookAheadNextChange">
          <el-input v-model="ibAccountBalanceMarginForm.lookAheadNextChange" />
        </el-form-item>
        <el-form-item label="净清算值和保证金审查" prop="nlvAndMarginInReview">
          <el-input v-model="ibAccountBalanceMarginForm.nlvAndMarginInReview" />
        </el-form-item>
        <el-form-item label="应计现金" prop="accruedCash">
          <el-input v-model="ibAccountBalanceMarginForm.accruedCash" />
        </el-form-item>
        <el-form-item label="应计股息" prop="accruedDividend">
          <el-input v-model="ibAccountBalanceMarginForm.accruedDividend" />
        </el-form-item>
        <el-form-item label="可用资金" prop="availableFunds">
          <el-input v-model="ibAccountBalanceMarginForm.availableFunds" />
        </el-form-item>
        <el-form-item label="Billable" prop="billable">
          <el-input v-model="ibAccountBalanceMarginForm.billable" />
        </el-form-item>
        <el-form-item label="购买力" prop="buyingPower">
          <el-input v-model="ibAccountBalanceMarginForm.buyingPower" />
        </el-form-item>
        <el-form-item label="含贷款价值的资产" prop="equityWithLoanValue">
          <el-input v-model="ibAccountBalanceMarginForm.equityWithLoanValue" />
        </el-form-item>
        <el-form-item label="剩余流动性" prop="excessLiquidity">
          <el-input v-model="ibAccountBalanceMarginForm.excessLiquidity" />
        </el-form-item>
        <el-form-item label="全部可用资金" prop="fullAvailableFunds">
          <el-input v-model="ibAccountBalanceMarginForm.fullAvailableFunds" />
        </el-form-item>
        <el-form-item label="全部过剩流动性" prop="fullExcessLiquidity">
          <el-input v-model="ibAccountBalanceMarginForm.fullExcessLiquidity" />
        </el-form-item>
        <el-form-item label="全部初始保证金要求" prop="fullInitMarginReq">
          <el-input v-model="ibAccountBalanceMarginForm.fullInitMarginReq" />
        </el-form-item>
        <el-form-item label="全部维持保证金要求" prop="fullMaintMarginReq">
          <el-input v-model="ibAccountBalanceMarginForm.fullMaintMarginReq" />
        </el-form-item>
        <el-form-item label="总头寸价值" prop="grossPositionValue">
          <el-input v-model="ibAccountBalanceMarginForm.grossPositionValue" />
        </el-form-item>
        <el-form-item label="担保" prop="guarantee">
          <el-input v-model="ibAccountBalanceMarginForm.guarantee" />
        </el-form-item>
        <el-form-item label="印度股票" prop="indianStockHaircut">
          <el-input v-model="ibAccountBalanceMarginForm.indianStockHaircut" />
        </el-form-item>
        <el-form-item label="初始保证金要求" prop="initMarginReq">
          <el-input v-model="ibAccountBalanceMarginForm.initMarginReq" />
        </el-form-item>
        <el-form-item label="前瞻可用资金" prop="lookAheadAvailableFunds">
          <el-input v-model="ibAccountBalanceMarginForm.lookAheadAvailableFunds" />
        </el-form-item>
        <el-form-item label="前瞻剩余流动性" prop="lookAheadExcessLiquidity">
          <el-input v-model="ibAccountBalanceMarginForm.lookAheadExcessLiquidity" />
        </el-form-item>
        <el-form-item label="前瞻初始保证金要求" prop="lookAheadInitMarginReq">
          <el-input v-model="ibAccountBalanceMarginForm.lookAheadInitMarginReq" />
        </el-form-item>
        <el-form-item label="前瞻维持保证金要求" prop="lookAheadMaintMarginReq">
          <el-input v-model="ibAccountBalanceMarginForm.lookAheadMaintMarginReq" />
        </el-form-item>
        <el-form-item label="维持保证金要求" prop="maintMarginReq">
          <el-input v-model="ibAccountBalanceMarginForm.maintMarginReq" />
        </el-form-item>
        <el-form-item label="净清算" prop="netLiquidation">
          <el-input v-model="ibAccountBalanceMarginForm.netLiquidation" />
        </el-form-item>
        <el-form-item label="净清算不确定" prop="netLiquidationUncertainty">
          <el-input v-model="ibAccountBalanceMarginForm.netLiquidationUncertainty" />
        </el-form-item>
        <el-form-item label="PAShares_Value" prop="pasharesValue">
          <el-input v-model="ibAccountBalanceMarginForm.pasharesValue" />
        </el-form-item>
        <el-form-item label="到期后剩余" prop="postExpirationExcess">
          <el-input v-model="ibAccountBalanceMarginForm.postExpirationExcess" />
        </el-form-item>
        <el-form-item label="到期后保证金" prop="postExpirationMargin">
          <el-input v-model="ibAccountBalanceMarginForm.postExpirationMargin" />
        </el-form-item>
        <el-form-item label="总现金价值" prop="totalCashvalue">
          <el-input v-model="ibAccountBalanceMarginForm.totalCashvalue" />
        </el-form-item>
        <el-form-item label="待支付的借记卡费用" prop="debitCardPendingCharges">
          <el-input v-model="ibAccountBalanceMarginForm.debitCardPendingCharges" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('ibAccountBalanceMarginForm')">提交</el-button>
        </el-form-item>

      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isNum, isInteger } from '../../utils/validate.js';
export default {
  name: 'IbAccountBalanceMargin',
  data() {
    return {
      ibAccountBalanceMarginLoading: '',
      ibAccountBalanceMarginData: [],
      baseCurrencyData: [],
      ibAccountBalanceMarginDialog: false,
      ibAccountBalanceMarginForm: {
        id: '',
        accountId: '',
        accountType: '',
        accountCode: '',
        accountReady: '',
        cushion: '',
        currency: '',
        lookAheadNextChange: '',
        nlvAndMarginInReview: '',
        accruedCash: '',
        accruedDividend: '',
        availableFunds: '',
        billable: '',
        buyingPower: '',
        equityWithLoanValue: '',
        excessLiquidity: '',
        fullAvailableFunds: '',
        fullExcessLiquidity: '',
        fullInitMarginReq: '',
        fullMaintMarginReq: '',
        grossPositionValue: '',
        guarantee: '',
        indianStockHaircut: '',
        initMarginReq: '',
        lookAheadAvailableFunds: '',
        lookAheadExcessLiquidity: '',
        lookAheadInitMarginReq: '',
        lookAheadMaintMarginReq: '',
        maintMarginReq: '',
        netLiquidation: '',
        netLiquidationUncertainty: '',
        pasharesValue: '',
        postExpirationExcess: '',
        postExpirationMargin: '',
        totalCashvalue: '',
        debitCardPendingCharges: ''
      },
      searchForm: {
        accountId: '',
        accountCode: ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      ibAccountBalanceMarginPnLRules: {
        accountId: [
          { required: true, message: '请输入账户ID', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
        ],
        accountType: [
          { required: true, message: '请输入账户类型', trigger: 'blur' }
        ],
        accountCode: [
          { required: true, message: '请输入IB对应AccountCode', trigger: 'blur' }
        ],
        accountReady: [
          { required: true, message: '请输入账户就绪', trigger: 'blur' }
        ],
        currency: [
          { required: true, message: '请选择币种', trigger: 'change' }
        ],
        cushion: [
          { required: false, message: '请输入Cushion', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        nlvAndMarginInReview: [
          { required: false, message: '请输入净清算值和保证金审查', trigger: 'blur' }
        ],
        accruedCash: [
          { required: false, message: '请输入应计现金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        lookAheadNextChange: [
          { required: false, message: '请输入前瞻下一变化', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        availableFunds: [
          { required: false, message: '请输入可用资金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        billable: [
          { required: false, message: '请输入Billable', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        buyingPower: [
          { required: false, message: '请输入购买力', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        equityWithLoanValue: [
          { required: false, message: '含贷款价值的资产', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        excessLiquidity: [
          { required: false, message: '请输入剩余流动性', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        accruedDividend: [
          { required: false, message: '请输入应计股息', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        fullAvailableFunds: [
          { required: false, message: '请输入全部可用资金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        fullExcessLiquidity: [
          { required: false, message: '请输入全部过剩流动性', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        fullInitMarginReq: [
          { required: false, message: '请输入全部初始保证金要求', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        fullMaintMarginReq: [
          { required: false, message: '请输入全部维持保证金要求', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        grossPositionValue: [
          { required: false, message: '请输入总头寸价值', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        guarantee: [
          { required: false, message: '请输入担保', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        indianStockHaircut: [
          { required: false, message: '请输入印度股票', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        initMarginReq: [
          { required: false, message: '请输入初始保证金要求', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        lookAheadAvailableFunds: [
          { required: false, message: '请输入前瞻可用资金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        lookAheadExcessLiquidity: [
          { required: false, message: '请输入前瞻剩余流动性', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        lookAheadInitMarginReq: [
          { required: false, message: '请输入前瞻初始保证金要求', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        lookAheadMaintMarginReq: [
          { required: false, message: '请输入前瞻维持保证金要求', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        maintMarginReq: [
          { required: false, message: '请输入维持保证金要求', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        netLiquidation: [
          { required: false, message: '请输入净清算', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        netLiquidationUncertainty: [
          { required: false, message: '请输入净清算不确定', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        pasharesValue: [
          { required: false, message: '请输入PAShares_Value', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        postExpirationExcess: [
          { required: false, message: '请输入到期后剩余', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        postExpirationMargin: [
          { required: false, message: '请输入到期后保证金', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        totalCashvalue: [
          { required: false, message: '请输入总现金价值', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        debitCardPendingCharges: [
          { required: false, message: '请输入待支付的借记卡费用', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ]
      },
      searchRules: {
        accountId: [
          { required: false, trigger: 'blur' }, { validator: isNum, trigger: 'blur', message: '请输入数字' }
        ]
      }
    };
  },
  mounted: function() {
    this.doSearch();
    this.doInitData();
  },
  methods: {
    dateFormat: function(row, column) {
      const date = row[column.property];
      if (date === undefined || date === '') {
        return '';
      }
      return this.$moment(date).format('YYYY-MM-DD HH:mm:ss');
    },
    dictFormat: function(row, column) {
      const dictArray = this.baseCurrencyData;
      let dictValue = '';
      dictArray.forEach((data) => {
        if (data.dictValue === row[column.property]) {
          dictValue = data.dictDest;
        }
      });
      return dictValue;
    },
    doInitData: function() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: {
          dictCode: 'baseCurrency',
          lang: 'zh_CN'
        }
      }).then(res => {
        if (res.code === 200) {
          this.baseCurrencyData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    dialogAdd() {
      this.ibAccountBalanceMarginForm = {
        id: '',
        accountId: '',
        accountType: '',
        accountCode: '',
        accountReady: '',
        cushion: '',
        currency: '',
        lookAheadNextChange: '',
        nlvAndMarginInReview: '',
        accruedCash: '',
        accruedDividend: '',
        availableFunds: '',
        billable: '',
        buyingPower: '',
        equityWithLoanValue: '',
        excessLiquidity: '',
        fullAvailableFunds: '',
        fullExcessLiquidity: '',
        fullInitMarginReq: '',
        fullMaintMarginReq: '',
        grossPositionValue: '',
        guarantee: '',
        indianStockHaircut: '',
        initMarginReq: '',
        lookAheadAvailableFunds: '',
        lookAheadExcessLiquidity: '',
        lookAheadInitMarginReq: '',
        lookAheadMaintMarginReq: '',
        maintMarginReq: '',
        netLiquidation: '',
        netLiquidationUncertainty: '',
        pasharesValue: '',
        postExpirationExcess: '',
        postExpirationMargin: '',
        totalCashvalue: '',
        debitCardPendingCharges: ''
      };
      if (this.$refs.ibAccountBalanceMarginForm) {
        this.$refs.ibAccountBalanceMarginForm.resetFields();
      }
      this.ibAccountBalanceMarginDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.ibAccountBalanceMarginForm) {
        this.$refs.ibAccountBalanceMarginForm.resetFields();
      }
      this.$http({
        url: '/ib/ibAccountBalanceMargin/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.ibAccountBalanceMarginForm = {
            id: res.object.id,
            accountId: res.object.accountId,
            accountCode: res.object.accountCode,
            accountType: res.object.accountType,
            accountReady: res.object.accountReady,
            cushion: res.object.cushion,
            currency: res.object.currency,
            lookAheadNextChange: res.object.lookAheadNextChange,
            nlvAndMarginInReview: res.object.nlvAndMarginInReview,
            accruedCash: res.object.accruedCash,
            accruedDividend: res.object.accruedDividend,
            availableFunds: res.object.availableFunds,
            billable: res.object.billable,
            buyingPower: res.object.buyingPower,
            equityWithLoanValue: res.object.equityWithLoanValue,
            excessLiquidity: res.object.excessLiquidity,
            fullAvailableFunds: res.object.fullAvailableFunds,
            fullExcessLiquidity: res.object.fullExcessLiquidity,
            fullInitMarginReq: res.object.fullInitMarginReq,
            fullMaintMarginReq: res.object.fullMaintMarginReq,
            grossPositionValue: res.object.grossPositionValue,
            guarantee: res.object.guarantee,
            indianStockHaircut: res.object.indianStockHaircut,
            initMarginReq: res.object.initMarginReq,
            lookAheadAvailableFunds: res.object.lookAheadAvailableFunds,
            lookAheadExcessLiquidity: res.object.lookAheadExcessLiquidity,
            lookAheadInitMarginReq: res.object.lookAheadInitMarginReq,
            lookAheadMaintMarginReq: res.object.lookAheadMaintMarginReq,
            maintMarginReq: res.object.maintMarginReq,
            netLiquidation: res.object.netLiquidation,
            netLiquidationUncertainty: res.object.netLiquidationUncertainty,
            pasharesValue: res.object.pasharesValue,
            postExpirationExcess: res.object.postExpirationExcess,
            postExpirationMargin: res.object.postExpirationMargin,
            totalCashvalue: res.object.totalCashvalue,
            debitCardPendingCharges: res.object.debitCardPendingCharges
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.ibAccountBalanceMarginDialog = true;
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/ib/ibAccountBalanceMargin/save',
            method: 'post',
            params: this.ibAccountBalanceMarginForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.doSearch();
            } else {
              this.$message.error(res.message || 'Has Error');
            }
          }).catch(error => {
            this.$message.error(error);
          });
          this.ibAccountBalanceMarginDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/ib/ibAccountBalanceMargin/del',
          method: 'post',
          params: {
            ids: column.id
          }
        }).then(res => {
          if (res.code === 200) {
            this.$message.success(res.message);
            this.doSearch();
          } else {
            this.$message.error(res.message || 'Has Error');
          }
        }).catch(error => {
          this.$message.error(error);
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.$refs.searchForm.validate((valid) => {
        if (valid) {
          this.ibAccountBalanceMarginLoading = true;
          this.$http({
            url: '/ib/ibAccountBalanceMargin/data',
            method: 'post',
            params: Object.assign(this.pageParams, this.searchForm)
          }).then(res => {
            if (res.code === 200) {
              this.ibAccountBalanceMarginData = res.rows;
              this.pageParams.totalPage = res.totalPage;
              this.ibAccountBalanceMarginLoading = false;
            }
          }).catch(error => {
            this.$message.error(error);
          });
        }
      });
    }
  }
};
</script>

<style lang="scss">

</style>
