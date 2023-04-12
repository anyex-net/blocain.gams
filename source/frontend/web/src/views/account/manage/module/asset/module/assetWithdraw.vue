<template>
  <div class="app-container">
    <div class="asset-withdraw-wrapper">
      <span class="asset-title">{{ $t('account.manage.asset.withdraw.withdraw') }}<!--提现出金--></span>
      <div class="tab-form">
        <div class="form">
          <el-form ref="assetWithdrawForm" :model="assetWithdrawForm" label-width="150px" size="mini" :rules="assetWithdrawRules">
            <el-form-item :label="$t('account.manage.asset.withdraw.currency')" prop="currency"><!--货币-->
              <el-select v-model="assetWithdrawForm.currency" :placeholder="$t('account.manage.asset.withdraw.select_currency')" value=""><!--请选择货币-->
                <dictionary dict-code="baseCurrency"></dictionary>
              </el-select>
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.withdraw.transfer_method')" prop="transferType"><!--转账方式-->
              <el-select v-model="assetWithdrawForm.transferType" :placeholder="$t('account.manage.asset.withdraw.choose_method')" value=""><!--请选择转账方式-->
                <dictionary dict-code="transferType"></dictionary>
              </el-select>
            </el-form-item>
            <el-form-item label="SWIFT CODE" prop="swiftCode">
              <el-input v-model="assetWithdrawForm.swiftCode" :placeholder="$t('account.manage.asset.deposit.required')" />
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.withdraw.bene_bank')" prop="beneBank"><!--收款银行-->
              <el-select v-model="assetWithdrawForm.beneBank" :placeholder="$t('account.manage.asset.withdraw.choose_bene_bank')" value=""><!--请选择收款银行-->
                <dictionary dict-code="beneBank"></dictionary>
              </el-select>
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.withdraw.bank_account')" prop="accountNumber"><!--银行账号-->
              <el-input v-model="assetWithdrawForm.accountNumber" :placeholder="$t('account.manage.asset.deposit.required')" />
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.withdraw.withdrawal_amount')" prop="amount"><!--提现金额-->
              <el-input v-model="assetWithdrawForm.amount" :placeholder="$t('account.manage.asset.deposit.required')" />
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.withdraw.postscript')" prop="remark"><!--附言-->
              <el-input v-model="assetWithdrawForm.remark" maxlength="80" type="textarea" show-word-limit autosize />
            </el-form-item>
            <el-button type="danger" @click="doSubmit('assetWithdrawForm')">{{ $t('account.manage.asset.withdraw.confirm') }}<!--确认--></el-button>
          </el-form>
        </div>
        <p style="font-size: 16px;">{{ $t('account.manage.asset.withdraw.remarks') }}<!--备注--></p>
        <ul class="assetWithdraw-other">
          <li class="assetWithdraw-other-txt">{{ $t('account.manage.asset.withdraw.remarks_1') }}<!--1、提取资金使用的银行账户开户人必须与开户同名，不支持提取到他人的银行账户。--></li>
          <li class="assetWithdraw-other-txt">{{ $t('account.manage.asset.withdraw.remarks_2') }}
            <!--2、提取资金请求的时间是工作日 9:00 - 15:00，接收提款申请的截止时间是工作日上午11:00（支票转账、电汇转账）或
            15:00（银证转账方式），超过截止时间的提款申请将在次工作日处理。-->
          </li>
          <li class="assetWithdraw-other-txt">{{ $t('account.manage.asset.withdraw.remarks_3') }}<!--3、节假日及休市期间银行不处理资金业务，可能会影响提款资金的到账时间。以下为预估时间，以实际到账时间为准，国瑞证券拥有解释权。--></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
import { amountNumber, accountNumber, checkNum, SWIFTNumber } from '@/utils/validate';
import dictionary from '@/components/dictionary/dictionary';
export default {
  name: 'AssetWithdraw',
  components: {
    dictionary
  },
  data() {
    return {
      activeName: 'first',
      baseCurrencyData: [],
      assetWithdrawForm: {
        'accountId': '',
        'beneBank': '',
        'accountNumber': '',
        'amount': '',
        'fee': '',
        'currency': '',
        'swiftCode': '',
        'transactionId': '',
        'withdrawDate': '',
        'withdrawConfirmDate': '',
        'remark': '',
        'status': 'unconfirmed',
        'transferType': ''
      },
      assetWithdrawRules: {}
    };
  },
  created: function() {
    this.doInit();
  },
  methods: {
    doInit() {
      if (this.$i18n.locale === 'zh_CN') {
        this.assetWithdrawRules = {
          currency: [
            { required: true, message: '请选择货币', trigger: 'change' }
          ],
          transferType: [
            { required: true, message: '请选择转账方式', trigger: 'change' }
          ],
          beneBank: [
            { required: true, message: '请选择收款银行', trigger: 'change' }
          ],
          accountNumber: [
            { required: true, message: '请输入银行账号', trigger: 'blur' }, { validator: accountNumber, trigger: 'blur', message: '请输入长度大于12并且小于32的银行账号' }
          ],
          amount: [
            { required: true, message: '请输入提现金额', trigger: 'blur' }, { validator: amountNumber, trigger: 'blur', message: '请输入大于0并小于100,000,000,000的数字' }, { validator: checkNum, trigger: 'blur', message: '请填写数字,最多2位小数' }
          ],
          swiftCode: [
            { required: true, message: '请输入SWIFT码', trigger: 'blur' }, { validator: SWIFTNumber, trigger: 'blur', message: '请输入长度大于0并且小于32的SWIFT CODE' }
          ]
        };
      } else if (this.$i18n.locale === 'en_US') {
        this.assetWithdrawRules = {
          currency: [
            { required: true, message: 'Please select Currency', trigger: 'change' }
          ],
          transferType: [
            { required: true, message: 'Please select the Transfer Method', trigger: 'change' }
          ],
          beneBank: [
            { required: true, message: 'Please select the Beneficiary Bank', trigger: 'change' }
          ],
          accountNumber: [
            { required: true, message: 'Please enter Bank Account', trigger: 'blur' }, { validator: accountNumber, trigger: 'blur', message: 'Must be between 12 and 32 characters in length' }
          ],
          amount: [
            { required: true, message: 'Please enter Withdrawal Amount', trigger: 'blur' }, { validator: amountNumber, trigger: 'blur', message: 'Numbers between 0 and 100,000,000,000' }, { validator: checkNum, trigger: 'blur', message: 'Numbers keep up to two decimal points' }
          ],
          swiftCode: [
            { required: true, message: 'Please enter the SWIFT CODE', trigger: 'blur' }, { validator: SWIFTNumber, trigger: 'blur', message: 'Must be between 0 and 32 characters in length' }
          ]
        };
      }
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/asset/withdraw',
            method: 'post',
            params: this.assetWithdrawForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.$refs['assetWithdrawForm'].resetFields();
            } else {
              this.$message.error(res.message || 'Has Error');
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
  .asset-withdraw-wrapper {
    background-color: white;
    padding: 20px 40px;
    .el-form-item--mini .el-form-item__label {
      margin-right: 30px;
    }
    .el-textarea {
      width: 250px;
      .el-input__count {
        line-height: 20px;
      }
    }
    .el-form-item--mini .el-form-item__error {
      margin-left: 30px;
    }
    .el-form-item.is-required:not(.is-no-asterisk) .el-form-item__label-wrap>.el-form-item__label:before, .el-form-item.is-required:not(.is-no-asterisk)>.el-form-item__label:before {
      content: '';
    }
  }
</style>

<style lang="scss" scoped>
  .app-container {
    padding: 20px 20px;
    .asset-withdraw-wrapper {
      padding: 30px 40px;
      background-color: #FFFFFF;
      margin-bottom: 30px;
      .asset-title {
        font-size: 18px;
        margin-left: -40px;
        padding-left: 38px;
        border-left: 2px solid;
      }
    }
    .tab-form {
      width: 450px;
      background-color: white;
      margin-top: 20px;
    }
    .form {
      width: 450px;
      background-color: white;
      padding-bottom: 30px;
      .form-title {
        font-size: 18px;
        margin-top: 0;
      }
      .el-input {
        width: 250px;
      }
      .el-button {
        padding: 0;
      }
      .el-button--danger {
        color: #FFF;
        background-color: #F56C6C;
        border-color: #F56C6C;
        width: 100px;
        height: 30px;
        margin-left: 180px;
      }
      .el-select {
        width: 250px;
      }
    }
    .assetWithdraw-other {
      width: 700px;
      .assetWithdraw-other-txt {
        font-size: 14px;
        font-weight: 400;
        color: #999999;
        line-height: 30px;
      }
    }
  }
</style>
