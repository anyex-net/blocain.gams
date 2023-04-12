<template>
  <div class="app-container">
    <el-tabs v-model="activeName" class="deposit-tab" @tab-click="handleClick">
      <el-tab-pane :label="$t('account.manage.asset.deposit.tab_1')" name="first"><!--充值入金教程-->
        <div class="tab-1">
          <div class="steps">
            <div class="step">
              <div class="step-box">
                <div class="step-box-num">
                  <span class="step-box-num-style">1</span>
                </div>
                <span class="step-box-txt">{{ $t('account.manage.asset.deposit.foreign_exchange') }}<!--购汇--></span>
              </div>
              <span class="step-arrowhead"> >> </span>
            </div>
            <div class="step">
              <div class="step-box">
                <div class="step-box-num">
                  <span class="step-box-num-style">2</span>
                </div>
                <span class="step-box-txt">{{ $t('account.manage.asset.deposit.remit') }}<!--汇款--></span>
              </div>
              <span class="step-arrowhead"> >> </span>
            </div>
            <div class="step">
              <div class="step-box">
                <div class="step-box-num">
                  <span class="step-box-num-style">3</span>
                </div>
                <span class="step-box-txt">{{ $t('account.manage.asset.deposit.remittance_notice') }}<!--汇款通知--></span>
              </div>
            </div>
          </div>
          <div style="padding-bottom: 60px;">
            <div class="module">
              <div class="module-img">
                <p class="module-title">{{ $t('account.manage.asset.deposit.reminder') }}<!--汇款提示-->：</p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.reminder_1') }}<!--1.投资港股存入港币，投资美股存入美元；--></p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.reminder_2') }}<!--2.汇款使用的银行账户名必须与国瑞证券账户名相同，不可使用他人银行账户进行汇款，否则产生的退款费用需要客户自行承担；--></p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.reminder_3') }}<!--3.客户汇款后填写汇款通知，银行成功汇出不表示国瑞证券已收到款项，资金到达后需要进行资金的结算和审批。在节假日均不处理汇款业务，请预留好汇款处理时间。--></p>
              </div>
              <div class="module">
                <p class="module-title">{{ $t('account.manage.asset.deposit.foreign_exchange_title') }}<!--1. 购汇 (如不涉及可略过)--></p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.foreign_exchange_txt') }}<!--通过网上银行或手机银行，找到“外汇”“购汇”，按照当前汇率，把人民币兑换成港币或美元（现汇）--></p>
              </div>
            </div>
            <div class="module">
              <p class="module-title">{{ $t('account.manage.asset.deposit.remit_title') }}<!--2. 汇款 (将资金存入国瑞证券交通银行（香港）账户)--></p>
              <div class="module-img">
                <p class="module-title" style="color:#888888;">{{ $t('account.manage.asset.deposit.remittance_information') }}<!--汇款信息--></p>
                <p></p><span v-if="$i18n.locale === 'zh_CN'" class="module-txt" style="color:#000;">中国交通银行（香港）</span><span>Bank of Communications Hong Kong Branch</span>
                <p><span class="module-txt">{{ $t('account.manage.asset.deposit.SWIFT_code') }}<!--SWIFT代码-->：COMMHKHH</span>
                  <span class="module-txt" style="display: inline-block;margin-left: 80px;width: 120px;">{{ $t('account.manage.asset.deposit.bank_code') }}<!--银行编码-->：072</span>
                </p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.account_name') }}<!--账号名称-->：MOF Securities Limited</p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.USD_account') }}<!--美元收款账号-->: 027-834100135-8-01</p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.HKD_account') }}<!--港币收款账号-->: 027-834100135-8-02</p>
                <p class="module-txt">{{ $t('account.manage.asset.deposit.bank_address') }}<!--收款银行地址-->：Suite 604-611, 6/F, Tower 6,Gateway, Tsimshatsui Kowloon</p>
              </div>
              <p> {{ $t('account.manage.asset.deposit.example') }}<!--以永丰银行为例--></p>
              <p class="module-txt">{{ $t('account.manage.asset.deposit.example_1') }}<!--( 1 ) 登录银行账户后进入【付款转账】，进行单笔付款--></p>
              <p class="module-txt">{{ $t('account.manage.asset.deposit.example_2') }}<!--( 2 ) 选择好货币类型，在收款账号中输入收款账号和收款人户名--></p>
              <img class="img_bank" :src="getImgUrl('/asset/deposit_img_bank1'+'_'+$i18n.locale+'.png')" />
              <p class="module-txt">{{ $t('account.manage.asset.deposit.example_3') }}<!--( 3 ) 收款国别选择香港，然后输入SWIFT代码带出银行信息。填写需要入金的金额--></p>
              <img class="img_bank" :src="getImgUrl('/asset/deposit_img_bank2'+'_'+$i18n.locale+'.png')" />
              <p class="module-txt">{{ $t('account.manage.asset.deposit.example_4') }}<!--( 4 ) 请填写您的国瑞账户号码及账户名称，如账户号码为MT103，账户名为Apple Inc，请填写MT103 I Apple Inc（请务必填写汇款附言，否则会被退回）--></p>
              <img class="img_bank" :src="getImgUrl('/asset/deposit_img_bank3'+'_'+$i18n.locale+'.png')" />
            </div>
            <div class="module">
              <p class="module-title">{{ $t('account.manage.asset.deposit.notify') }}<!--3. 通知国瑞证券--></p>
              <p class="module-txt">{{ $t('account.manage.asset.deposit.notify_1') }}<!--完成汇款操作后，请在国瑞证券存入资金页面发起汇款通知。输入存入的币种及金额，以免延误您的入金到账时间。--></p>
              <p class="module-txt">{{ $t('account.manage.asset.deposit.notify_2') }}<!--通常等待1-3个工作日左右，您的汇款即可到账。--></p>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane :label="$t('account.manage.asset.deposit.tab_2')" name="second"><!--充值入金录入-->
        <div class="form-second">
          <el-form ref="assetDepositForm" :model="assetDepositForm" label-width="150px" size="mini" :rules="assetDepositRules">
            <el-form-item :label="$t('account.manage.asset.deposit.currency')" prop="currency"><!--货币-->
              <el-select v-model="assetDepositForm.currency" :placeholder="$t('account.manage.asset.deposit.select_currency')" value=""><!--请选择货币-->
                <dictionary dict-code="baseCurrency"></dictionary>
              </el-select>
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.deposit.transfer_method')" prop="transferType"><!--转账方式-->
              <el-select v-model="assetDepositForm.transferType" :placeholder="$t('account.manage.asset.deposit.choose_method')" value=""><!--请选择转账方式-->
                <dictionary dict-code="transferType"></dictionary>
              </el-select>
            </el-form-item>
            <el-form-item label="SWIFT CODE" prop="swiftCode"><!--SWIFT Code-->
              <el-input v-model="assetDepositForm.swiftCode" :placeholder="$t('account.manage.asset.deposit.required')" />
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.deposit.remittance_bank')" prop="remittingBank"><!--汇款银行-->
              <el-select v-model="assetDepositForm.remittingBank" :placeholder="$t('account.manage.asset.deposit.choose_remittance_bank')" value=""><!--请选择汇款银行-->
                <dictionary dict-code="remittingBank"></dictionary>
              </el-select>
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.deposit.bank_account')" prop="accountNumber"><!--银行账号-->
              <el-input v-model="assetDepositForm.accountNumber" :placeholder="$t('account.manage.asset.deposit.required')" />
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.deposit.remittance_amount')" prop="amount"><!--汇款金额-->
              <el-input v-model="assetDepositForm.amount" :placeholder="$t('account.manage.asset.deposit.required')" />
            </el-form-item>
            <el-form-item :label="$t('account.manage.asset.deposit.postscript')" prop="remark"><!--附言-->
              <el-input v-model="assetDepositForm.remark" maxlength="80" type="textarea" show-word-limit autosize />
            </el-form-item>
            <el-button type="danger" @click="doSubmit('assetDepositForm')">{{ $t('account.manage.asset.deposit.confirm') }}<!--确认--></el-button>
          </el-form>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { amountNumber, checkNum, accountNumber, SWIFTNumber } from '@/utils/validate';
import dictionary from '@/components/dictionary/dictionary';
export default {
  name: 'AssetDeposit',
  components: { dictionary },
  data() {
    return {
      activeName: 'first',
      baseCurrencyData: [],
      transferTypeData: [],
      assetDepositForm: {
        'accountId': '',
        'amount': '',
        'remittingBank': '',
        'accountNumber': '',
        'swiftCode': '',
        'fee': '',
        'currency': '',
        'transactionId': '',
        'depositDate': '',
        'depositConfirmDate': '',
        'remark': '',
        'status': 'unconfirmed',
        'transferType': ''
      },
      assetDepositRules: {}
    };
  },
  created: function() {
    this.doInit();
  },
  methods: {
    getImgUrl(image) {
      return require('@/assets/images/account' + image);
    },
    handleClick() {
      this.$refs.assetDepositForm.resetFields();
    },
    doInit() {
      if (this.$i18n.locale === 'zh_CN') {
        this.assetDepositRules = {
          currency: [
            { required: true, message: '请选择货币', trigger: 'change' }
          ],
          transferType: [
            { required: true, message: '请选择转账方式', trigger: 'change' }
          ],
          remittingBank: [
            { required: true, message: '请选择汇款银行', trigger: 'change' }
          ],
          accountNumber: [
            { required: true, message: '请输入银行账号', trigger: 'blur' }, { validator: accountNumber, trigger: 'blur', message: '请输入长度大于12并且小于32的银行账号' }
          ],
          amount: [
            { required: true, message: '请输入汇款金额', trigger: 'blur' }, { validator: amountNumber, trigger: 'blur', message: '请输入大于0并小于100,000,000,000的数字' }, { validator: checkNum, trigger: 'blur', message: '请填写数字,最多2位小数' }
          ],
          swiftCode: [
            { required: true, message: '请输入SWIFT码', trigger: 'blur' }, { validator: SWIFTNumber, trigger: 'blur', message: '请输入长度大于0并且小于32的SWIFT CODE' }
          ]
        };
      } else if (this.$i18n.locale === 'en_US') {
        this.assetDepositRules = {
          currency: [
            { required: true, message: 'Please select Currency', trigger: 'change' }
          ],
          transferType: [
            { required: true, message: 'Please select the Transfer Method', trigger: 'change' }
          ],
          remittingBank: [
            { required: true, message: 'Please select the Remittance Bank', trigger: 'change' }
          ],
          accountNumber: [
            { required: true, message: 'Please enter Bank Account', trigger: 'blur' }, { validator: accountNumber, trigger: 'blur', message: 'Must be between 12 and 32 characters in length' }
          ],
          amount: [
            { required: true, message: 'Please enter Remittance Amount', trigger: 'blur' }, { validator: amountNumber, trigger: 'blur', message: 'Numbers between 0 and 100,000,000,000' }, { validator: checkNum, trigger: 'blur', message: 'Numbers keep up to two decimal points' }
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
            url: '/asset/deposit',
            method: 'post',
            params: this.assetDepositForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
              this.$refs['assetDepositForm'].resetFields();
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
  .deposit-tab {
    background-color: white;
    padding: 20px 40px;
    .el-tabs__nav-scroll {
      margin-left: 0;
    }
    .el-tabs__active-bar {
      background-color: #434343;
    }
    .el-tabs__item.is-active {
      color: #434343;
    }
    .el-tabs__item:hover {
      color: #434343;
      cursor: pointer;
    }
    .el-tabs__item {
      font-size: 18px;
      font-weight: 400;
      color: #999999;
      margin-bottom: 10px;
    }
    .el-form-item__content {
      width: 400px;
    }
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
    .steps {
      height: 50px;
      margin: 20px 0 20px;
      .step {
        min-width: 200px;
        float: left;
      }
      .step-box {
        height: 30px;
        padding: 12px 0 0 10px;
        background-color: #FFFFFF;
        float: left;
        border-radius: 6px;
        box-shadow: 0 3px 8px rgba(179, 183, 187, 0.24);
      }
        .step-box-num {
          width: 18px;
          height: 18px;
          background: rgba(255, 62, 77, 1);
          border-radius: 50%;
          float: left;
          text-align: center;
          .step-box-num-style {
            font-size:14px;
            font-weight:400;
            color:rgba(255,255,255,1);
            display: inline-block;
            margin-top: 2px;
          }
        }
        .step-box-txt {
          float: right;
          font-size: 16px;
          margin-right: 10px;
          padding-left: 40px;
        }
        .step-arrowhead {
          display: inline-block;
          width: 9px;
          height: 9px;
          font-size: 16px;
          font-weight: 400;
          color: rgba(153, 153, 153, 1);
          margin: 10px 35px;
        }
    }
    .module {
      margin-bottom: 20px;
      .module-title {
        font-size: 16px;
        line-height: 22px;
      }
      .module-txt {
        color: #888888;
        line-height: 22px;
        width: 800px;
      }
      .module-img {
        width:800px;
        background:#F1F3F7;
        margin-bottom: 20px;
        padding: 15px 60px 5px 20px;
      }
      .img_bank {
        margin-bottom: 10px;
      }
    }
    .form-second {
      width: 450px;
      background-color: white;
      margin-top: 20px;
      .el-input {
        width: 250px;
      }
      .el-button--danger {
        color: #FFF;
        background-color: #F56C6C;
        border-color: #F56C6C;
        width: 100px;
        height: 30px;
        margin-left: 180px;
        padding: 0;
      }
      .el-select {
        width: 250px;
      }
    }
  }
</style>
