<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(13/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="company15"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="company15"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.integration.title') }}</h3> <!-- $i18n - 一体化投资管理账户功能 -->
                  <p class="title-centent zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.integration.describe') }}</p> <!-- $i18n - 为您的账户添加以下选项，以更好地 ... -->
                </div>
                <div class="formNapeContent">
                  <div class="show-title">
                    <h4 class="zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.integration.minTitle') }}</h4> <!-- $i18n - 股票收益增长计划 -->
                    <div
                      class="title-centent zhuce3ji"
                    >{{ $t('registerOpenAccount.openAccount.common.integration.minDescribe') }}</div> <!-- $i18n - 国瑞证券的股票收益提升计划可帮助您 ... -->
                    <!-- 点击弹出模态框 -->
                    <!-- <div class="gengduo">更多信息</div> -->
                    <div class="participation">
                      <div
                        class="isimg"
                        :class="{ active1a: company15.isstate.isshow }"
                        @click="company15.isstate.isshow=!company15.isstate.isshow"
                      />
                      <div :class="{ active1b: company15.isstate.isshow, active1b2: true }">{{ $t('registerOpenAccount.openAccount.common.integration.participate') }}</div> <!-- $i18n - 参与 -->
                      <div v-show="!company15.isstate.isshow" class="tishi11">{{ $t('registerOpenAccount.openAccount.common.integration.hint') }}</div> <!-- $i18n - 请参与计划 -->
                    </div>
                  </div>
                </div>
                <!-- 显示隐藏 -->
                <div v-show="company15.isstate.isshow" class="form-content">
                  <!-- 文本域 -->
                  <div class="show-textcontent">
                    <h4>{{ $t('registerOpenAccount.openAccount.common.integration.protocol') }}</h4> <!-- $i18n - 股票收益增长计划 协议 -->
                    <div>
                      <div class="text1 publictext" style="overflow: auto;padding: 20px;box-sizing: border-box;">
                        <div>
                          <h1 style="line-height:27pt; margin:15pt 0pt 3.75pt"><span
                            style="color:#4d6e9d; font-family:Arial; font-size:27pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Important
                            Characteristics and Risks of Participating in MOF Securities Limited Fully-Paid Securities Lending
                            Program</span></h1>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >You
                            should read this document and the</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >MOF
                            Securities</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >
                            Master Securities Lending Agreement for Fully-Paid Lending</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >carefully
                            before participating in </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            Fully-Paid Securities Lending Program.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Introduction</span>
                          </p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                            Securities Limited ("</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >")
                            offers eligible customers the ability to lend certain of their fully paid and excess margin securities
                            to </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >for
                            on-lending to other </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >customers
                            or to other market participants who wish to use these shares for short selling or other
                            purposes.</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >"Fully-paid
                            securities" are securities in your account that have been completely paid for. "Excess-margin
                            securities" are securities that have not been completely paid for, but whose market value exceeds 140%
                            of your margin debit balance. In this disclosure and in the relevant agreements, we collectively refer
                            to fully-paid and excess margin securities as "Fully-Paid Securities" or "Fully-Paid Shares"</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >.
                            Lending your Fully-Paid Shares may be a way to increase the yield on your portfolio, because some shares
                            are in high demand in the securities lending market and borrowers are willing to pay for the use of your
                            shares.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >There
                            are two ways to participate:</span></p>
                          <ol type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Stock
                                Yield Enhancement Program:</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >&#xa0;</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >In
                                the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                Stock Yield Enhancement Program, you permit </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                to borrow any Fully-Paid Securities in your account and loan these securities out in the securities
                                lending market. </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                will have the discretion to initiate loans of your securities. You will not be asked to approve each
                                loan before it is initiated, but you can sell your shares at any time or terminate your
                                participation in the Program. </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                will pay you interest on the cash collateral posted to your account to secure the loan. Ordinarily
                                the interest rate </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                pays you will approximate a percentage of the net income received by </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                for lending your securities. </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                net income may be less than the gross income received by </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                for relending your securities because of certain deductions and charges, as explained below.
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                may make certain assumptions in computing the net amounts.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 7.5pt 36pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span>
                          </p>
                          <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Self-Directed
                                Fully-Paid Securities Lending Program:</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >&#xa0;</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >If
                                self-directed fully-paid securities lending marketplaces develop, </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                may offer the ability for clients to control their own securities lending activities.</span></li>
                          </ol>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Basic
                            Mechanics of a Fully-Paid Lending Transaction</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >When
                            the lending transaction takes place, your securities will be removed from your account. In return,
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            will deposit cash collateral into your account to secure the amount of the loan. The current industry
                            convention for the collateral calculation with respect to U.S. stocks is to multiply the rounded
                            security price times the number of shares by 102%. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            marks-to-market all positions nightly to reflect changes in security prices and makes corresponding
                            adjustments to the collateral. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            reserves the right to adjust to U.S. industry convention should that change or to raise or lower the
                            collateral amount based on local laws or market custom outside the United States; however </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            will never collateralize the stock loan for less than 100% of the value. For example, customer A
                            enrolled in the Stock Yield Enhancement Program and </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            subsequently borrowed 5,000 shares of XYZ from this customer. XYZ's closing price is $22.15. The cash
                            collateral is calculated by rounding $22.15 * 1.02 = $22.59 up to the nearest dollar, which is $23,
                            making the collateral calculation $23 * 5000 = $115,000.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Whether
                            you initiate the lending transactions yourself through a Self-Directed Fully-Paid Securities Lending
                            Program, or you allow </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            to borrow from you through the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            Stock Yield Enhancement Program, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            will be the counterparty borrower to all of the loans you make. That is, as a customer, you are not
                            transacting directly with the securities lending market. You are transacting with </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >,
                            which may then transact on the relevant market. For all transactions in which you are lending your
                            Fully-Paid Shares, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            will be responsible for providing the collateral to you on stock loans and paying interest on such
                            collateral.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Securities
                            Loaned Out By You May Not Be Protected by SIPC</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >The
                            provisions of the Securities Investor Protection Act of 1970 may not protect you as a lender with
                            respect to securities loan transactions in which you lend your Fully-Paid Securities to </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >.
                            Therefore, the collateral delivered to you (and indicated on your account statement) may constitute the
                            only source of satisfaction of </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >'s
                            obligation in the event that </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >fails
                            to return the securities.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Securities
                            Loaned Out by You Are Typically Used to Facilitate Short Sales</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >The
                            type of securities that are generally attractive to borrowers in the securities lending market, and
                            which generate the highest income potential, are "hard to borrow" securities. When you lend your
                            Fully-Paid Securities, it is likely that such securities will be used to facilitate one or more short
                            sales where the borrower is selling shares in hopes that the stock will decline in value (the short
                            seller later re-purchases the stock to pay back the stock loan). Since you are holding the shares "long"
                            in your account, the activity of short sellers potentially could affect the long-term value of your
                            holdings.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; font-weight:bold; text-transform:none"
                          >NOTE:
                            If you do not want your fully-paid securities used to facilitate short sales, you should NOT participate
                            in </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; font-weight:bold; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; font-weight:bold; text-transform:none"
                          >'s
                            Fully Paid Securities Lending Programs.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >You
                            Continue to Own Loaned Shares and Have Market Risk on Those Shares</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >When
                            you lend your shares, you continue to own the shares and you continue to have the market exposure
                            inherent in ownership of the shares (i.e., if the share price increases while you own the shares but are
                            lending them out, your equity in the position will increase. If the price goes down, your equity will
                            decrease).</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >You
                            Can Sell Your Loaned Shares At Any Time</span><br><br><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Even
                            though you have loaned your shares out, you can sell those shares at any time, just like any other
                            shares in your </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            account. You do not have to wait for the shares to be returned to sell them. Even if the shares are not
                            returned on time to settle your sale of the shares, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >will
                            be responsible for settling the sale, not you, and you will receive the proceeds from the sale of the
                            shares on the normal settlement date for the sale.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Loan
                            Rates (and therefore the Interest Rate You Will Receive) Are Subject to Frequent Change and Can Go Down
                            (or Up) by 50% or More</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Rates
                            for "hard to borrow" and other shares change frequently, even daily, in the securities lending market
                            and this can reduce (or increase) the interest </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            pays on your collateral. Likewise, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            may change the rate it pays you compared to the income that </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            receives when it lends your securities to third parties. If you have permitted </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            to borrow your Fully-Paid Securities through the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            Stock Yield Enhancement Program, you will not have direct control over when to initiate or terminate
                            loans of specific shares (including based on rate changes). However, you can always terminate your
                            participation in the program (which will terminate all of your lending transactions) if you are unhappy
                            with the interest rates you are receiving or the nature or frequency of rate changes. Please note,
                            though, that if you terminate your participation in the Stock Yield Enhancement Program, you may not be
                            permitted to re-join the program, or you may have to wait a certain length of time to re-join.</span>
                          </p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Potential
                            Adverse Tax Consequences from Receiving Cash Payments in Lieu of Dividends on Loaned Shares</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >&#xa0;</span><span
                            style="color:#444444; font-family:Arial; font-size:6.67pt; font-style:normal; font-weight:bold; text-transform:none; vertical-align:super"
                          >1</span>
                          </p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >When
                            you lend your Fully-Paid Securities, you are entitled to receive the amount of all dividends and
                            distributions made on or in respect of the loaned securities. However, these cash payments may be
                            considered "in lieu of" dividends. If you are a U.S. taxpayer, cash payments in lieu of dividends do not
                            qualify for the same tax treatment as "qualified dividends" and are taxed as normal ordinary income (up
                            to 39.6%) instead of the preferential qualified dividend rate of 20% (U.S. federal income tax rates
                            quoted here are for 2016 and are subject to change).</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            may be required to withhold tax on payments in lieu of dividends on U.S. and other country stocks and
                            interest paid to you unless an exception applies. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            intends to treat the payments to you on the collateral under the program as interest although there can
                            be no assurance the tax authorities will agree.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >If
                            you permit </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            to borrow securities from you through the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            Stock Yield Enhancement Program and you are a U.S. taxpayer, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            may recall loaned shares from the borrower prior to a dividend, so as to reduce potential negative tax
                            consequences to you. However, it is solely within </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            discretion to recall a loan and </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            makes no guarantee it will recall a loan prior to a dividend. With respect to other corporate actions
                            affecting loaned shares, non-cash distributions that you are entitled to receive in connection with
                            ownership of loaned securities will be added to the loaned securities on the date of distribution and
                            will be transferred to you at termination of the loan.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Special
                            tax considerations may arise if shares of master limited partnerships or publicly traded partnerships
                            are loaned out under the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Stock
                            Yield Enhancement Program or Self-Directed Fully-Paid Securities Lending Program. You are encouraged to
                            consult the issuer's prospectus or your tax advisor for further information.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >
                            is the Counterparty to All Fully-Paid Lending Transactions with You. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >
                            or Its Affiliates May Earn a Spread in Rates and May Profit or Lose in Connection with the Transaction
                            or Other Transactions in the Same Securities. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >
                            May Pay Part of the Loan Income to Third Parties, Which Will Reduce the Rate You Receive for the
                            Duration of the Loan.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >will
                            be the counterparty (borrower) when you lend your shares. Any transactions that </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            or may not do with the shares are completely independent of your loan transaction to </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >.
                            Thus, after </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >borrows
                            shares from you, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            or may not then lend those shares on in the marketplace, or lend them to or through an affiliate or
                            third party. Likewise, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            terminate a loan with you and return shares to you while at the same time </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >continues
                            to lend shares of the same stock out to the marketplace.</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >In
                            short: </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >'s
                            obligation to you is to pay you interest on your cash collateral at the specified rate on ongoing loan
                            transactions until such transactions are terminated by you or by </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >.
                            Nothing in the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >Fully-Paid
                            Lending Program restricts </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:italic; text-transform:none"
                          >'s
                            ability to conduct stock lending and borrowing transactions with third parties, who may profit or lose
                            in connection with the transactions</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >.</span>
                          </p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            borrow shares from you and then lend those shares to one of its affiliates for the affiliate's own
                            purposes (including short selling). In the United States, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >typically
                            also uses an affiliate as a "conduit" to the securities lending markets. This means that </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            lend the shares to the affiliate, which will then lend the shares out to other parties in the securities
                            lending market.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliates or third parties may earn a "spread" on securities lending transactions with your stock.
                            This means that the rate you receive from </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            be less than the rate </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliate receives from a third party (or that </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >receives
                            from the affiliate if the affiliate is the ultimate borrower) on those same shares. If </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            affiliate is acting as a conduit, there will be a minimum 5 basis point (0.05%) reduction in the
                            interest rate you receive compared to market rates.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            pay part of the net loan income it earns on shares borrowed from you to third parties such as your
                            financial advisor or introducing brokers who may introduce your account to </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >.
                            These payments may reduce the interest rate you receive for the entire duration of the loan.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >There
                            Is No Guarantee That You Will Receive the Best Loan Rates for Your Shares</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >The
                            securities lending market is not a standardized or transparent market. Securities lending transactions
                            generally take place "over the counter" rather than on organized exchanges where prices and transactions
                            are transparent. There are no rules or mechanisms that guarantee or require that any given participant
                            in the marketplace will receive the best rate for lending shares, and </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >cannot
                            and does not guarantee it will pay the highest rate for borrowing your shares. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliates through which it conducts securities lending transactions may not have access to the
                            markets or counterparties that are offering the most favorable rates, or may be unaware of the most
                            favorable rates. As noted previously, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliates or third parties may earn a "spread" on the rate, such that the rate you receive is worse
                            than the rate </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliates receive.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >There
                            Is No Guarantee That Your Fully-Paid Shares Will Be Loaned Out</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >There
                            is no guarantee that you will be able to lend (or that </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >will
                            want to or be able to borrow) your Fully-Paid Shares. There may not be a market to lend your Fully-Paid
                            Shares at a rate that is advantageous, or </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >may
                            not have access to a market with willing borrowers. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >,
                            or other </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >customers
                            or </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            affiliates, might have shares that may be loaned out that will satisfy available borrowing interest and,
                            therefore, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            may not borrow shares from you. There is no rule or requirement, nor is there anything in the applicable
                            agreements between you and </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >,
                            that requires </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >to
                            borrow shares from you or requires </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >to
                            place your interest in lending shares ahead of </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            own interests, or those of other </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >customers
                            or those of </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            affiliates. If </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >is
                            managing your lending transactions through the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Stock
                            Yield Enhancement Program, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >cannot
                            and does not guarantee that all of your Fully-Paid Shares that possibly could be loaned out will be
                            loaned out.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Loans
                            May Be Terminated At Any Time By </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF
                          </span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >When
                            you lend your Fully-Paid Shares, the loan may be terminated and the shares returned to your </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >account
                            at any time for any reason. The loan may be terminated because a party that borrowed the shares from
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >(after
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >borrowed
                            them from you) chose to return the shares, or because you or </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >received
                            a rerate request and rejected the rerate or did not respond to the rerate request. </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >also
                            has the right to terminate its borrowing of shares from you even if </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >continues
                            to lend the same stock through the securities lending market. When the loan is terminated, shares will
                            be returned to your account, the cash collateral will be removed from your account and </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >will
                            stop paying interest on the collateral. If you permit </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >to
                            borrow securities through the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            Stock Yield Enhancement Program, you will not have direct control over when to initiate or terminate
                            loans of specific shares. Please note, however, you can always terminate your participation in the
                            program (which will terminate all of your lending transactions).</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Selling
                            Your Shares or Borrowing Against Them or Withdrawing Cash Collateral Beyond</span><br><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >a
                            Certain Amount Will Terminate the Loan Transaction</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >If
                            you sell the Fully-Paid Shares you have lent out, or if you borrow against the shares or withdraw cash
                            collateral (such that the securities become margin securities and are no longer fully-paid or excess
                            margin securities), the loan will terminate and </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >will
                            cease paying interest on your cash collateral.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Commissions
                            and Other Charges</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >If
                            you permit </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >to
                            borrow securities from you through the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >Stock
                            Yield Enhancement Program, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >will
                            credit interest on your cash collateral daily. The rate may be changed from time to time in </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            sole discretion and different rates may apply between customers based on a variety of factors, including
                            the size of the customers' loan portfolios, the types of Fully-Paid Securities available in the
                            customers' accounts, and other factors.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >As
                            noted above, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliates or third parties may also earn a "spread" on the rate, such that the rate you receive
                            will be based on a net income after deduction for charges by </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >or
                            its affiliates. If </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >'s
                            affiliate is acting as a conduit, there will be a minimum 5 basis point (0.05%) reduction in the
                            interest rate </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >pays.
                            Likewise, as noted, </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >
                            may pay part of the net income (for shares you lend) to third parties such as introducing brokers who
                            may introduce your account to </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >.
                            These payments may reduce the interest rate you receive. You may always terminate your participation in
                            the program if you are unhappy with the rates you are receiving.</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Interest
                            Treatment on Cash Collateral</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >The
                            interest paid by </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >under
                            the program is the only interest payment you will receive on the cash collateral credited to your
                            account when you lend Fully-Paid Shares to </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF</span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >.
                            The interest treatment on collateral may change from the above depending on the securities lending
                            market and the collateral method. Please refer to the </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >MOF
                          </span><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >website.</span>
                          </p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Voting
                            Rights</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >The
                            borrower of securities (and not you, as lender) has the right to vote, or to provide any consent or to
                            take any similar action with respect to the loaned securities if the record date or deadline for such
                            vote, consent or other action falls during the term of the loan.</span></p>
                          <p style="margin:0pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <p style="margin:3.75pt 0pt 0pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                          >NOTES</span>
                          </p>
                          <ol type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:8pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:8pt; font-style:normal; text-transform:none"
                              >does
                                not provide any investment, tax or trading advice. The information in this paragraph is general
                                information only and does not take into account your personal circumstances. You should speak to an
                                independent tax expert to understand the tax implication to you (if any) as a result of
                                participating in the program.</span></li>
                          </ol>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                        </div>
                      </div>
                      <div class="text3 publictext" style="overflow: auto;padding: 20px;box-sizing: border-box;">
                        <div>
                          <h1 style="line-height:27pt; margin:15pt 0pt 3.75pt"><span
                            style="color:#4d6e9d; font-family:Arial; font-size:27pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Agreement
                            and Limited Power Of Attorney For Participation in </span><span
                            style="color:#4d6e9d; font-family:Arial; font-size:27pt; font-style:normal; font-weight:bold; text-transform:none"
                          >MOF
                            Securities </span><span
                            style="color:#4d6e9d; font-family:Arial; font-size:27pt; font-style:normal; font-weight:bold; text-transform:none"
                          >Stock
                            Yield Enhancement Program ("Agreement and Power of Attorney")</span></h1>
                          <ol type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >General:</span>
                              <ol type="1" style="margin:0pt; padding-left:0pt">
                                <li
                                  style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                                >
                                  <span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                    Securities Limited</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    ("</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >"
                                    or "</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                    Securities</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >")
                                    offers eligible customers the ability to lend out certain of their fully-paid and excess
                                    margin securities</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  /><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >(collectively
                                    "Fully-Paid Securities" or "Fully-Paid Shares") to </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >,
                                    who may "on-lend" these securities to other </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    customers or to other market participants who wish to use these shares for short selling or
                                    other purposes. "Fully-paid securities" are U.S. or global securities in a customer's
                                    account that have been completely paid for. "Excess-margin securities" are U.S. or global
                                    securities that have not been completely paid for, but whose market value exceeds 140% of
                                    the customer's margin debit balance.</span></li>
                              </ol>
                            </li>
                          </ol>
                          <p style="margin:0pt 0pt 10.5pt 72pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span>
                          </p>
                          <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                wishes to participate in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                Stock Yield Enhancement Program, under which Customer will grant discretion to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities Limited</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                to initiate, borrow and terminate Fully-Paid Securities loans between Customer and </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >.
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >will
                                pay interest on the cash collateral posted to Customer's account.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                represents that Customer has received and read the separate disclosure document provided by
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >entitled
                                Important Characteristics and Risks of Participating in </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Fully-Paid
                                Securities Lending Programs, and that Customer has determined that the benefits and risks of
                                participating in the program are consistent with Customer's financial circumstances, investment
                                objectives and risk tolerance.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >In
                                order to participate in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Stock
                                Yield Enhancement Program, Customer must execute this Agreement and Power of Attorney and Customer
                                must also execute the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                Agreement and the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Master
                                Securities Lending Agreement for Fully-Paid Lending. All of the terms of the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                Agreement and the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Master
                                Securities Lending Agreement for Fully-Paid Lending are applicable to transactions between Customer
                                and </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >pursuant
                                to the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Stock
                                Yield Enhancement Program, but if this Agreement and Power of Attorney is inconsistent with any
                                terms in those agreements, this Agreement is controlling and takes precedence.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Limited
                                Power of Attorney/Discretionary Authorization for </span><span
                                  style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                                >MOF
                                  Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >
                                to Initiate, Borrow and Terminate Loans of Customer Fully-Paid Securities Between Customer as Lender
                                and </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >as
                                Borrower:</span>
                              <ol type="1" style="margin:0pt; padding-left:0pt">
                                <li
                                  style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                                >
                                  <span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >The
                                    undersigned Customer hereby authorizes </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                    Securities </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >as
                                    Customer's agent and attorney-in-fact to use its discretion to examine the Fully-Paid
                                    Securities in Customer's account and to take all necessary steps to initiate, borrow and
                                    terminate loans of Fully-Paid Securities between Customer as Lender and </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >as
                                    Borrower pursuant to the </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                    Securities </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >Master
                                    Securities Lending Agreement for Fully-Paid Lending.</span></li>
                              </ol>
                            </li>
                          </ol>
                          <p style="margin:0pt 0pt 10.5pt 72pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span>
                          </p>
                          <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >The
                                undersigned Customer grants </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >sole
                                discretion to evaluate factors that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >considers
                                relevant in determining whether any of the Fully-Paid Securities in Customer's account can be loaned
                                to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                on terms that are mutually advantageous to Customer and to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >,
                                taking into account various factors affecting the market and the potential transaction, such as
                                potential size and duration of the loan, the nature of the security and of various market factors
                                affecting the security, prevailing market rates, other </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >customers'
                                positions and lending interests, identity and availability of potential secondary borrowers of the
                                shares from </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >in
                                the securities lending markets, and other conditions relevant to the potential loan.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >The
                                undersigned Customer grants </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >sole
                                discretion to determine the terms and rates at which </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >will
                                borrow Fully-Paid Shares from Customer, taking into account factors such as prevailing rates in the
                                market for loans of various sizes, rates that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                be paid by its affiliates or third parties for </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >lending
                                the shares on to the securities lending markets, payments that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                make to third parties (such as Customer's financial advisor or introducing brokers who introduce
                                accounts to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >),
                                other </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >customers',
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                affiliates' or third parties' needs for the securities, and other relevant factors. Customer
                                authorizes </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >to
                                change the rate </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >will
                                pay on the cash collateral at </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                sole discretion based on changes in the above factors. Rates may change frequently (as often as
                                daily) due to the nature of the securities lending markets and may involve changes downward (or
                                upward) by 50% or more.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 12pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >This
                                Power of Attorney and Discretionary Authorization shall continue and remain in full force and effect
                                unless and until Customer terminates Customer's participation in the program by providing a written
                                notice of termination through the means provided on the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >website.</span><br><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt"
                              >&#xa0;</span></li>
                          </ol>
                          <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Interest
                                Paid To Customer and Rate Spreads:</span>
                              <ol type="1" style="margin:0pt; padding-left:0pt">
                                <li
                                  style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                                >
                                  <span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >will
                                    pay Customer interest on the cash collateral posted to the Customer's account when it
                                    borrows Fully-Paid Shares. Ordinarily </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    will pay Customer a rate that approximates a specified percentage of the net income earned
                                    by </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >for
                                    relending the securities. The net income received by </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >and
                                    used to calculate Customer's interest rate may be less than the gross income received by
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >for
                                    relending the securities because of certain deductions and charges, including payments to
                                    affiliates and third parties.</span></li>
                              </ol>
                            </li>
                          </ol>
                          <p style="margin:0pt 0pt 10.5pt 72pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span>
                          </p>
                          <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                understands and agrees that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                borrow shares from Customer or third parties and then lend those shares to one of its affiliates,
                                such as its market making affiliate Timber Hill LLC ("Timber Hill"), for the affiliate's own
                                purposes (including short selling). In the United States, </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                typically also uses Timber Hill as a "conduit" to the securities lending markets. This means that
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                lend the shares to Timber Hill, which will then lend the shares out to other parties in the
                                securities lending market.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                understands that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                pay part of the net income earned from lending a Customer's shares to third parties such as
                                Customer's financial advisor or introducing brokers who may introduce accounts to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >.
                                These payments may reduce the interest rate </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >will
                                pay on Customer's collateral for the entire duration of the loan.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Rights
                                and Obligations of Customer and </span><span
                                  style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                                >MOF
                                </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >:</span>
                              <ol type="1" style="margin:0pt; padding-left:0pt">
                                <li
                                  style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                                >
                                  <span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >When
                                    a loan is initiated or terminated by </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >,
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >shall
                                    confirm this to the Customer on the Customer's daily </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                    Securities </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >statement.
                                    Customer agrees to log in to the </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >website
                                    and to review Customer's daily statements and the confirmations contained therein.</span>
                                </li>
                              </ol>
                            </li>
                          </ol>
                          <p style="margin:0pt 0pt 10.5pt 72pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span>
                          </p>
                          <ol start="2" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                may sell Fully-Paid Shares that have been loaned to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >at
                                any time, and </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >will
                                be responsible for terminating the loan, settling the sale of the shares, and providing the proceeds
                                of the sale to Customer by the normal settlement date for the sale.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="3" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Nothing
                                in this Agreement guarantees that Customer will receive the best possible income for Customer's
                                Fully-Paid Shares. Customer understands and agrees that the securities lending market is not a
                                standardized or transparent market, that there are no rules or mechanisms that guarantee or require
                                that any given participant in the marketplace will receive the best rate for lending shares, and
                                that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >cannot
                                and does not guarantee it will pay the highest rate for borrowing Customer's shares.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="4" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Nothing
                                in this Agreement obligates </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >to
                                borrow specific Fully-Paid Shares, or any Fully-Paid Shares. </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >cannot
                                and does not guarantee that all of Customer's Fully-Paid Shares that possibly could be loaned out
                                will be loaned out. There may not be a market to lend Customer's Fully-Paid Shares at a rate that is
                                advantageous, or </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                not have access to a market with willing borrowers. </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >,
                                or other </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                customers or </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                affiliates might have shares that may be loaned out that will satisfy available borrowing interest
                                and therefore </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                not borrow shares from Customer. Customer understands and agrees that nothing in this Agreement
                                requires </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >to
                                place Customer's interest in lending shares ahead of </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                own interests, or those of other </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                customers or those of </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                affiliates.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="5" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                may terminate this Agreement and Customer's participation in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                Stock Yield Enhancement Program at any time upon written notice to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >through
                                the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >website.
                                After Customer's notice of termination, all outstanding loans of Fully-Paid Securities will be
                                terminated and the shares returned to Customer by the deadlines specified in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                Master Securities Lending Agreement for Fully-Paid Lending (generally, at most three business days
                                from termination).</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 72pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="6" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 12pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                acknowledges that if Customer signs this Agreement and Power Of Attorney and participates in the
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Stock
                                Yield Enhancement Program, Customer will not have the ability or right to approve specific loans
                                before or after they are initiated, will not have the ability to approve or reject interest rate
                                changes and will not have the right to terminate specific loans (except if Customer sells the shares
                                that are being loaned or if Customer terminates Customer's participation in the Program). If
                                Customer is dissatisfied with a particular loan or loans or the rate(s) Customer is receiving or the
                                income earned by </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >,
                                Customer may terminate participation in the Program at any time as specified in the preceding
                                paragraph.</span></li>
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 12pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              /><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                terminate this Agreement and Power of Attorney and Customer's participation in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Stock
                                Yield Enhancement Program at any time upon written notice to Customer. After such termination, all
                                outstanding loans of Fully-Paid Securities will be terminated and the shares returned to Customer by
                                the deadlines specified in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                Master Securities Lending Agreement for Fully-Paid Lending (generally, at most three business days
                                from termination).</span></li>
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 12pt 69.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                is entitled to receive the amount of all dividends and distributions made on or in respect of loaned
                                securities. These cash payments may be characterized as payments "in lieu of" dividends. For U.S.
                                taxpayers, payments in lieu of dividends do not qualify for the preferential qualified dividend
                                income rate (20% for 2016 but subject to change) and are taxed as normal ordinary income (up to
                                39.6%). </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                may be required to withhold tax on payments in lieu of dividends and interest paid on the collateral
                                unless an exception applies. </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may,
                                but is not required to, recall loaned shares from the borrower prior to a dividend, so as to reduce
                                potential negative tax consequences. Customer is responsible for evaluating the tax consequences of
                                participating in the </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Stock
                                Yield Enhancement Program and seeking the advice of a tax professional if needed.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 7.5pt 36pt"><span
                            style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                          >&#xa0;</span>
                          </p>
                          <ol start="5" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Limited
                                Role of </span><span
                                  style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                                >MOF
                                  Securities</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >
                                - No Trading or Investment Advice or Discretion by </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >:</span>
                              <ol type="1" style="margin:0pt; padding-left:0pt">
                                <li
                                  style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 12pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                                >
                                  <span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >The
                                    power of attorney and discretionary authorization granted by Customer to </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    in this Agreement and Power of Attorney is limited to the initiation, borrowing and
                                    termination of loans of Fully-Paid Securities as described herein and pursuant to the
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                    Securities </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    Master Securities Lending Agreement for Fully-Paid Lending.</span></li>
                                <li
                                  style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 12pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                                >
                                  <span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    SHALL HAVE NO DISCRETION TO BUY OR SELL SECURITIES OR TO MAKE OTHER INVESTMENT DECISIONS FOR
                                    CUSTOMER'S ACCOUNT. </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >
                                    IS NOT OBLIGATED TO AND WILL NOT PROVIDE ANY TRADING OR INVESTMENT OR TAX ADVICE OR
                                    RECOMMENDATIONS TO CUSTOMER. THE DECISION WHETHER TO BUY OR HOLD OR SELL FULLY-PAID SHARES
                                    OR SECURITIES OF ANY KIND IS SOLELY CUSTOMER'S RESPONS</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >ILITY.
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >'S
                                    INITIATION OR TERMINATION OF A LOAN OF FULLY-PAID SECURITIES IS NOT A RECOMMENDATION AS TO
                                    THE LONG-TERM VALUE OF THE SECURITIES AND LOANED SECURITIES MAY RISE OR FALL IN VALUE, WHICH
                                    IS SOLELY THE RESPONS</span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >MOF
                                  </span><span
                                    style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                                  >ILITY
                                    OF CUSTOMER.</span></li>
                              </ol>
                            </li>
                          </ol>
                          <p style="margin:0pt 0pt 0pt 36pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="6" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Securities
                                Loaned Out By Customer May Not Be Protected by SIPC:</span><br><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >The
                                provisions of the Securities Investor Protection Act of 1970 may not protect Customer as a lender
                                with respect to securities loan transactions in which Customer lends to </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >
                                Customer's Fully-Paid Securities. Therefore, the collateral delivered to Customer (and indicated on
                                Customer's account statement) by </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >may
                                constitute the only source of satisfaction of </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                obligation in the event that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >fails
                                to return the securities.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 36pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="7" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Bankruptcy
                                or Insolvency:</span><br><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >In
                                the event that </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >:
                                (1) applies for or consents to a receiver, custodian, trustee or liquidator of itself or its
                                property; (2) admits in writing its inability, or becomes generally unable, to pay its debts as such
                                debts become due; (3) makes a general assignment for the benefit of its creditors; or (4) files, or
                                has filed against it, a petition for a Chapter 11 bankruptcy filing or a protective decree under
                                Section 5 of the Securities Investor Protection Act of 1970 (SIPA), this Agreement and all
                                outstanding loans shall be terminated and Customer shall have the right to return of Customer's
                                loaned shares or the right to the collateral securing such loaned shares as specified in the
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Master
                                Securities Lending Agreement for Fully-Paid Lending.</span></li>
                          </ol>
                          <p style="margin:0pt 0pt 12pt 36pt"><span style="font-family:Calibri; font-size:10.5pt">&#xa0;</span></p>
                          <ol start="8" type="1" style="margin:0pt; padding-left:0pt">
                            <li
                              style="color:#444444; font-family:Arial; font-size:12pt; margin:5pt 0pt 5pt 33.01pt; padding-left:2.99pt; text-align:justify; text-indent:0pt"
                            >
                              <span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; font-weight:bold; text-transform:none"
                              >Arbitration:</span><br><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >The
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                                Securities </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >Customer
                                Agreement contains arbitration clauses requiring Customer and </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF
                              </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >to
                                arbitrate any disputes. Such arbitration clauses are incorporated herein by reference. Customer
                                acknowledge and agrees that all disputes regarding this Agreement and Power Of Attorney, Customer's
                                account(s) or </span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >MOF</span><span
                                style="color:#444444; font-family:Arial; font-size:10.5pt; font-style:normal; text-transform:none"
                              >'s
                                provision of services to Customer are subject to arbitration.</span></li>
                          </ol>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                        </div>
                      </div>
                      <div class="text3 publictext" style="overflow: auto;padding: 20px;box-sizing: border-box;">
                        <div>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Master Securities Lending Agreement for </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF Securities Limited</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > Fully-Paid Lending Program</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >This Master Securities Lending Agreement ("Agreement") is
                            entered into by and between </span><span style="font-family:Calibri; font-size:10.5pt">MOF Securities
                            Limited</span><span style="font-family:Calibri; font-size:10.5pt"> ("</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >") and the undersigned party or parties
                            ("Counterparty").</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >1.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Applicability.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >From time to time the parties hereto may enter into
                            transactions in which one party ("Lender") will lend to the other party ("Borrower") certain Securities
                            (as defined herein) against a transfer of Collateral (as defined herein). Each such transaction shall be
                            referred to herein as a "Loan" and, unless otherwise agreed in writing, shall be governed by this
                            Agreement, including any supplemental terms or conditions contained in an Annex or Schedule hereto and
                            in any other annexes identified herein or therein as applicable hereunder); provided however that
                            Securities borrowed by Counterparty from </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >, and Securities borrowed by </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > from Counterparty, pursuant to a margin account agreement
                            between </span><span style="font-family:Calibri; font-size:10.5pt">MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > and Counterparty shall not be subject to this Agreement.
                            Capitalized terms not otherwise defined herein shall have the meanings provided in Section 25.</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >2.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Loans of Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Subject to the terms and conditions of this Agreement,
                            Borrower or Lender may, from time to time, seek to initiate a transaction in which Lender will lend
                            Securities to Borrower. Borrower and Lender shall agree on the terms of each Loan (which terms may be
                            amended during the Loan), including the issuer of the Securities, the amount of Securities to be lent,
                            the basis of compensation, the amount of Collateral to be transferred by Borrower, and any additional
                            terms. Such agreement shall be confirmed (a) by a schedule and receipt listing the Loaned Securities
                            provided by Borrower to Lender in accordance with Section 3.2, (b) through any system that compares
                            Loans and in which Borrower and Lender are participants, or (c) in such other manner as may be agreed by
                            Borrower and Lender in writing. Such confirmation (the "Confirmation"), together with the Agreement,
                            shall constitute conclusive evidence of the terms agreed between Borrower and Lender with respect to the
                            Loan to which the Confirmation relates, unless with respect to the Confirmation specific objection is
                            made promptly after receipt thereof. In the event of any inconsistency between the terms of such
                            Confirmation and this Agreement, this Agreement shall prevail unless each party has executed such
                            Confirmation.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Notwithstanding any other provision in this Agreement
                            regarding when a Loan commences, unless otherwise agreed, a Loan hereunder shall not occur until the
                            Loaned Securities and the Collateral therefore have been transferred in accordance with Section
                            15.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >3.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Transfer of Loaned Securities.</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, Lender shall transfer Loaned
                            Securities to Borrower hereunder on or before the Cutoff Time on the date agreed to by Borrower and
                            Lender for the commencement of the Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, Borrower shall provide Lender,
                            for each Loan in which Lender is a Customer, with a schedule and receipt listing the Loaned Securities.
                            Such schedule and receipt may consist of (a) a schedule provided to Borrower by Lender and executed and
                            returned by Borrower when the Loaned Securities are received, (b) in the case of Securities transferred
                            through a Clearing Organization which provides transferors with a notice evidencing such transfer, such
                            notice, or (c) a confirmation or other document provided to Lender by Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Notwithstanding any other provision in this Agreement, the
                            parties hereto agree that they intend the Loans hereunder to be loans of Securities. If, however, any
                            Loan is deemed to be a loan of money by Borrower to Lender, then Borrower shall have, and Lender shall
                            be deemed to have granted, a security interest in the Loaned Securities and the proceeds thereof.</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >4.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Collateral.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, Borrower shall, prior to or
                            concurrently with the transfer of the Loaned Securities to Borrower, but in no case later than the Close
                            of Business on the day of such transfer, transfer to Lender Collateral with a Market Value at least
                            equal to the Margin Percentage of the Market Value of the Loaned Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower shall be deemed to have transferred Collateral to
                            Lender by crediting Lender's account carried by Borrower with Collateral with a Market Value at least
                            equal to the Margin Percentage of the Market Value of the Loaned Securities. The Collateral transferred
                            by Borrower to Lender, as adjusted pursuant to Section 9, shall be security for Borrower's obligations
                            in respect of such Loan and for any other obligations of Borrower to Lender hereunder. Borrower hereby
                            pledges with, assigns to, and grants Lender a continuing first priority security interest in, and a lien
                            upon, the Collateral, which shall attach upon the transfer of the Loaned Securities by Lender to
                            Borrower and which shall cease upon the transfer of the Loaned Securities by Borrower to Lender. Lender
                            will be deemed to have transferred Loaned Securities to Borrower on the date Borrower treats such
                            securities as having been borrowed pursuant to Exchange Act rule 15c3-3(b)(3) and therefore not subject
                            to the general possession or control requirements of Exchange Act rule 15c3-3(b). Borrower will be
                            deemed to have transferred Loaned Securities to Lender on the date Borrower treats such securities as
                            customer securities subject to the general possession or control requirements of Exchange Act Rule
                            15c3-3(b), without giving effect to Exchange Act rule 15c3-3(b)(3), without regard to whether such
                            securities are thereby returned to Lender or continue to be borrowed by Borrower pursuant to any
                            hypothecation agreement between Lender and Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Except as otherwise provided herein, upon transfer to
                            Lender of the Loaned Securities on the day a Loan is terminated pursuant to Section 6, Lender shall be
                            obligated to transfer, and hereby authorizes Borrower to effect the transfer of, the Collateral (as
                            adjusted pursuant to Section 9) to Borrower on such day or, if such day is not a day on which a transfer
                            of such Collateral may be effected under Section 15, the next day on which such a transfer may be
                            effected.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If Borrower transfers Collateral to Lender, as provided in
                            Section 4.1, and Lender does not transfer the Loaned Securities to Borrower, Borrower shall have the
                            absolute right to the return of the Collateral; and if Lender transfers Loaned Securities to Borrower
                            and Borrower does not transfer Collateral to Lender as provided in Section 4.1, Lender shall have the
                            absolute right to the return of the Loaned Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower may, upon reasonable notice to Lender (taking
                            into account all relevant factors, including industry practice, the type of Collateral to be
                            substituted, and the applicable method of transfer), substitute Collateral for Collateral securing any
                            Loan or Loans; provided, however, that such substituted Collateral shall (a) consist only of cash,
                            securities or other property that Borrower and Lender agreed would be acceptable Collateral prior to the
                            Loan or Loans and (b) have a Market Value such that the aggregate Market Value of such substituted
                            Collateral, together with all other Collateral for Loans in which the party substituting such Collateral
                            is acting as Borrower, shall equal or exceed the agreed upon Margin Percentage of the Market Value of
                            the Loaned Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >In the event Borrower and Lender agree to a Loan of
                            Securities collateralized by a Letter of Credit, in order to enable the Issuing Bank to identify Lender
                            and issue the Letter of Credit in favor of Lender, Lender hereby agrees that Borrower may provide
                            information in its possession concerning Lender's identity to the Issuing Bank. Prior to the expiration
                            of any letter of credit supporting Borrower's obligations hereunder, Borrower shall, no later than the
                            Extension Deadline, (a) obtain an extension of the expiration of such letter of credit, (b) replace such
                            letter of credit by providing Lender with a substitute letter of credit in an amount at least equal to
                            the amount of the letter of credit for which it is substituted, or (c) transfer such other Collateral to
                            Lender as may be acceptable to Lender.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >5.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Income for Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, Borrower agrees to pay Lender
                            interest on the cash Collateral securing each Loan ("Interest "), computed daily as further described in
                            the Borrower's Important Characteristics and Risks of Participating in Borrower's Fully-Paid Securities
                            Lending Program as it may be amended from time to time by Borrower. Interest on non-cash Collateral will
                            be paid as agreed upon by the parties.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, Interest payable hereunder shall
                            be payable within fifteen (15) Business Days following the last Business Day of the calendar month in
                            which such Interest was incurred.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >6.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Termination of the Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >(a) Unless otherwise agreed, either party may terminate a
                            Loan on a termination date established by notice given to the other party prior to the Close of Business
                            on a Business Day. Unless an earlier date is agreed by the Parties, the termination date established by
                            a termination notice shall be a date no earlier than the standard settlement date that would apply to a
                            purchase or sale of the Loaned Securities (in the case of a notice given by Lender) or the non-cash
                            Collateral securing the Loan (in the case of a notice given by Borrower) entered into at the time of
                            such notice, which date shall, unless Borrower and Lender agree to the contrary, be (a) in the case of
                            Government Securities, the next Business Day following such notice and (b) in the case of all other
                            Securities, the third Business Day following such notice.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >(b) Notwithstanding paragraph (a) and unless otherwise
                            agreed, Borrower may terminate a Loan on any Business Day, effective as of such Business Day, by
                            transferring the Loaned Securities to Lender on such Business Day. Borrower will be deemed to have
                            transferred Loaned Securities by the end of a Business Day if it treats such securities as customer
                            securities subject to the general possession or control requirements of Exchange Act Rule 15c3-3(b),
                            without giving effect to Exchange Act rule 15c3-3(b)(3), without regard to whether such securities are
                            thereby returned to Lender or may continue to be borrowed by Borrower pursuant to any hypothecation
                            agreement between Lender and Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >(c) The execution by Borrower of an order to sell the
                            Loaned Securities by Lender shall constitute notice of termination by Lender to Borrower. The
                            termination date established by such a sale of the Loaned Securities shall be the settlement date of
                            such sale of the Loaned Securities or any earlier date on which Borrower is deemed to have transferred
                            Loaned Securities to Lender under paragraph (b) of this Section.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, Borrower shall, on or before the
                            Cutoff Time on the termination date of a Loan, transfer the Loaned Securities to Lender; provided,
                            however, that upon such transfer by Borrower, Lender shall transfer the Collateral (as adjusted pursuant
                            to Section 9) to Borrower in accordance with Section 4.3.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >7.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Rights in Respect of Loaned Securities
                            and Collateral.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Except as set forth in Sections 8.1 and 8.2 and as
                            otherwise agreed by Borrower and Lender, until Loaned Securities are required to be redelivered to
                            Lender upon termination of a Loan hereunder, Borrower shall have all of the incidents of ownership of
                            the Loaned Securities, including the right to transfer the Loaned Securities to others. Lender hereby
                            waives the right to vote, or to provide any consent or to take any similar action with respect to, the
                            Loaned Securities in the event that the record date or deadline for such vote, consent or other action
                            falls during the term of the Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >8.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Distributions.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Lender shall be entitled to receive all Distributions made
                            on or in respect of the Loaned Securities which are not otherwise received by Lender, to the full extent
                            it would be so entitled if the Loaned Securities had not been lent to Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Any cash Distributions made on or in respect of the Loaned
                            Securities, which Lender is entitled to receive pursuant to Section 8.1, shall be paid by the transfer
                            of cash to Lender by Borrower, on the date any such Distribution is paid, in an amount equal to such
                            cash Distribution, so long as Lender is not in Default at the time of such payment. Non-cash
                            Distributions that Lender is entitled to receive pursuant to Section 8.1 shall be added to the Loaned
                            Securities on the date of distribution and shall be considered such for all purposes, except that if the
                            Loan has terminated, Borrower shall forthwith transfer the same to Lender.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower shall be entitled to receive all Distributions
                            made on or in respect of non-cash Collateral which are not otherwise received by Borrower, to the full
                            extent it would be so entitled if the Collateral had not been transferred to Lender.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Any cash Distributions made on or in respect of such
                            Collateral, which Borrower is entitled to receive pursuant to Section 8.3, shall be paid by the transfer
                            of cash to Borrower by Lender, on the date any such Distribution is paid, in an amount equal to such
                            cash Distribution, so long as Borrower is not in Default at the time of such payment. Non-cash
                            Distributions that Borrower is entitled to receive pursuant to Section 8.3 shall be added to the
                            Collateral on the date of distribution and shall be considered such for all purposes, except that if
                            each Loan secured by such Collateral has terminated, Lender shall forthwith transfer the same to
                            Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed by the parties:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If (i) Borrower is required to make a payment (a "Borrower
                            Payment") with respect to cash Distributions on Loaned Securities under Sections 8.1 and 8.2
                            ("Securities Distributions"), or (ii) Lender is required to make a payment (a "Lender Payment") with
                            respect to cash Distributions on Collateral under Sections 8.3 and 8.4 ("Collateral Distributions"), and
                            (iii) Borrower or Lender, as the case may be ("Payor"), shall be required by law to collect any
                            withholding or other tax, duty, fee, levy or charge required to be deducted or withheld from such
                            Borrower Payment or Lender Payment ("Tax"), then Payor shall (subject to subsections (b) and (c) below),
                            pay such additional amounts as may be necessary in order that the net amount of the Borrower Payment or
                            Lender Payment received by the Lender or Borrower, as the case may be ("Payee"), after payment of such
                            Tax equals the net amount of the Securities Distribution or Collateral Distribution that would have been
                            received if such Securities Distribution or Collateral Distribution had been paid directly to the
                            Payee.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >No additional amounts shall be payable to a Payee under
                            subsection (a) above to the extent that Tax would have been imposed on a Securities Distribution or
                            Collateral Distribution paid directly to the Payee.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >No additional amounts shall be payable to a Payee under
                            subsection (a) above to the extent that such Payee is entitled to an exemption from, or reduction in the
                            rate of, Tax on a Borrower Payment or Lender Payment subject to the provision of a certificate or other
                            documentation, but has failed timely to provide such certificate or other documentation.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Each party hereto shall be deemed to represent that, as of
                            the commencement of any Loan hereunder, no Tax would be imposed on any cash Distribution paid to it with
                            respect to (i) Loaned Securities subject to a Loan in which it is acting as Lender or (ii) Collateral
                            for any Loan in which it is acting as Borrower, unless such party has given notice to the contrary to
                            the other party hereto (which notice shall specify the rate at which such Tax would be imposed). Each
                            party agrees to notify the other of any change that occurs during the term of a Loan in the rate of any
                            Tax that would be imposed on any such cash Distributions payable to it.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >To the extent that, under the provisions of Sections 8.1
                            through 8.5, (a) a transfer of cash or other property by Borrower would give rise to a Margin Excess or
                            (b) a transfer of cash or other property by Lender would give rise to a Margin Deficit, Borrower or
                            Lender (as the case may be) shall not be obligated to make such transfer of cash or other property in
                            accordance with such Sections, but shall in lieu of such transfer immediately credit the amounts that
                            would have been transferable under such Sections to the account of Lender or Borrower (as the case may
                            be)..</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >9.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Mark to Market.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If Lender is a Customer, Borrower shall daily mark to
                            market any Loan hereunder and in the event that at the Close of Trading on any Business Day the Market
                            Value of the Collateral for any Loan to Borrower shall be less than 100% of the Market Value of all the
                            outstanding Loaned Securities subject to such Loan, Borrower shall transfer additional Collateral no
                            later than the Close of Business on the next Business Day so that the Market Value of such additional
                            Collateral, when added to the Market Value of the other Collateral for such Loan, shall equal at least
                            100% of the Market Value of the Loaned Securities. As agreed by the parties or if Borrower determines in
                            its discretion that applicable laws or market custom so require, Borrower may deposit additional
                            collateral greater than 100% of the market value of the Loaned Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >In addition to any rights of Lender under Section 9.1, if
                            at any time the aggregate Market Value of all Collateral for Loans by Lender shall be less than the
                            Margin Percentage of the Market Value of all the outstanding Loaned Securities subject to such Loans (a
                            "Margin Deficit"), Borrower shall transfer additional Collateral no later than the Close of Business on
                            the next Business Day so that the Market Value of such additional Collateral, when added to the Market
                            Value of the other Collateral for such Loan, shall equal or exceed the Margin Percentage of the Market
                            Value of the Loaned Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Subject to Borrower's obligations under Section 9.1, if at
                            any time the Market Value of all Collateral for Loans to Borrower shall be greater than the Margin
                            Percentage of the Market Value of all the outstanding Loaned Securities subject to such Loans (a "Margin
                            Excess"), Lender hereby authorizes Borrower to transfer to Borrower such amount of the Collateral
                            selected by Borrower so that the Market Value of the Collateral for such Loans, after deduction of such
                            amounts, shall thereupon not exceed the Margin Percentage of the Market Value of the Loaned
                            Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender may agree, with respect to one or more
                            Loans hereunder, to mark the values to market pursuant to Sections 9.2 and 9.3 by separately valuing the
                            Loaned Securities lent and the Collateral given in respect thereof on a Loan-by-Loan basis.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender may agree, with respect to any or all
                            Loans hereunder, that the respective rights of Lender and Borrower under Sections 9.2 and 9.3 may be
                            exercised only where a Margin Excess or Margin Deficit exceeds a specified dollar amount or a specified
                            percentage of the Market Value of the Loaned Securities under such Loans (which amount or percentage
                            shall be agreed to by Borrower and Lender prior to entering into any such Loans).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >10.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Representations.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The parties to this Agreement hereby make the following
                            representations and warranties, which shall continue during the term of any Loan hereunder:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Each party hereto represents and warrants that (a) it has
                            the power to execute and deliver this Agreement, to enter into the Loans contemplated hereby and to
                            perform its obligations hereunder, (b) it has taken all necessary action to authorize such execution,
                            delivery and performance, and (c) this Agreement constitutes a legal, valid and binding obligation
                            enforceable against it in accordance with its terms.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Each party hereto represents and warrants that it has not
                            relied on the other for any tax or accounting advice concerning this Agreement and that it has made its
                            own determination as to the tax and accounting treatment of any Loan and any dividends, remuneration or
                            other funds received hereunder.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Each party hereto represents and warrants that it is
                            acting for its own account unless it expressly specifies otherwise in writing and complies with Section
                            11.1(b).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >To the extent applicable, Borrower represents and warrants
                            that it has, or will have at the time of transfer of any Collateral, the right to grant a first priority
                            security interest therein subject to the terms and conditions hereof.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower represents and warrants that it (or the person to
                            whom it relends the Loaned Securities) is borrowing or will borrow Loaned Securities that are Equity
                            Securities for the purpose of making delivery of such Loaned Securities in the case of short sales,
                            failure to receive securities required to be delivered, or as otherwise permitted pursuant to Regulation
                            T as in effect from time to time.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender may agree, as provided in Section
                            24.2, that Borrower shall not be deemed to have made the representation or warranty in subsection (a)
                            with respect to any Loan. By entering into any such agreement, Lender shall be deemed to have
                            represented and warranted to Borrower (which representation and warranty shall be deemed to be repeated
                            on each day during the term of the Loan) that Lender is either (i) an "exempted borrower" within the
                            meaning of Regulation T or (ii) a member of a national securities exchange or a broker or dealer
                            registered with the U.S. Securities and Exchange Commission that is entering into such Loan to finance
                            its activities as a market maker or an underwriter.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Lender represents and warrants that it has, or will have
                            at the time of transfer of any Loaned Securities, the right to transfer the Loaned Securities subject to
                            the terms and conditions hereof.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >11.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Covenants.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Each party agrees either (a) to be liable as principal
                            with respect to its obligations hereunder or (b) to execute and comply fully with the provisions of
                            Annex I (the terms and conditions of which Annex are incorporated herein and made a part hereof).</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >12.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Events of Default.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >All Loans hereunder may, at the option of the
                            non-defaulting party (which option shall be deemed to have been exercised immediately upon the
                            occurrence of an Act of Insolvency), be terminated immediately upon the occurrence of any one or more of
                            the following events (individually, a "Default"):</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if any Loaned Securities shall not be transferred to
                            Lender upon termination of the Loan as required by Section 6;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if any Collateral shall not be transferred to Borrower
                            upon termination of the Loan as required by Sections 4.3 and 6;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if either party shall fail to transfer Collateral as
                            required by Section 9;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if either party (a) shall fail to transfer to the other
                            party amounts in respect of Distributions required to be transferred by Section 8, (b) shall have been
                            notified of such failure by the other party prior to the Close of Business on any day, and (c) shall not
                            have cured such failure by the Cutoff Time on the next day after such Close of Business on which a
                            transfer of cash may be effected in accordance with Section 15;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if an Act of Insolvency occurs with respect to either
                            party;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if any representation made by either party in respect of
                            this Agreement or any Loan or Loans hereunder shall be incorrect or untrue in any material respect
                            during the term of any Loan hereunder;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if either party notifies the other of its inability to or
                            its intention not to perform its obligations hereunder or otherwise disaffirms, rejects or repudiates
                            any of its obligations hereunder; or</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >if either party (a) shall fail to perform any material
                            obligation under this Agreement not specifically set forth in clauses 12.1 through 12.7, above,
                            including but not limited to the payment of Interest as required by Section 5, and the payment of
                            transfer taxes as required by Section 14, (b) shall have been notified of such failure by the other
                            party prior to the Close of Business on any day, and (c) shall not have cured such failure by the Cutoff
                            Time on the next day after such Close of Business on which a transfer of cash may be effected in
                            accordance with Section 15.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The non-defaulting party shall (except upon the occurrence
                            of an Act of Insolvency) give notice as promptly as practicable to the defaulting party of the exercise
                            of its option to terminate all Loans hereunder pursuant to this Section 12.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >13.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Remedies.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Upon the occurrence of a Default under Section 12
                            entitling Lender to terminate all Loans hereunder, Lender shall have the right, in addition to any other
                            remedies provided herein, (a) to purchase a like amount of Loaned Securities ("Replacement Securities")
                            in the principal market for such Loaned Securities in a commercially reasonable manner, (b) to sell any
                            Collateral in the principal market for such Collateral in a commercially reasonable manner and (c) to
                            apply and set off the Collateral and any proceeds thereof (including any amounts drawn under a letter of
                            credit supporting any Loan) against the payment of the purchase price for such Replacement Securities
                            and any amounts due to Lender under Sections 5, 8, 14 and 16. In the event that Lender shall exercise
                            such rights, Borrower's obligation to return a like amount of the Loaned Securities shall terminate.
                            Lender may similarly apply the Collateral and any proceeds thereof to any other obligation of Borrower
                            under this Agreement, including Borrower's obligations with respect to Distributions paid to Borrower
                            (and not forwarded to Lender) in respect of Loaned Securities. In the event that (i) the purchase price
                            of Replacement Securities (plus all other amounts, if any, due to Lender hereunder) exceeds (ii) the
                            amount of the Collateral, Borrower shall be liable to Lender for the amount of such excess together with
                            interest thereon at a rate equal to (A) in the case of purchases of Foreign Securities, L</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >OR, (B) in the case of purchases of any other Securities
                            (or other amounts, if any, due to Lender hereunder), the Federal Funds Rate or (C) such other rate as
                            may be specified in Schedule B, in each case as such rate fluctuates from day to day, from the date of
                            such purchase until the date of payment of such excess. As security for Borrower's obligation to pay
                            such excess, Lender shall have, and Borrower hereby grants, a security interest in any property of
                            Borrower then held by or for Lender and a right of setoff with respect to such property and any other
                            amount payable by Lender to Borrower. The purchase price of Replacement Securities purchased under this
                            Section 13.1 shall include, and the proceeds of any sale of Collateral shall be determined after
                            deduction of, broker's fees and commissions and all other reasonable costs, fees and expenses related to
                            such purchase or sale (as the case may be). In the event Lender exercises its rights under this Section
                            13.1, Lender may elect in its sole discretion, in lieu of purchasing all or a portion of the Replacement
                            Securities or selling all or a portion of the Collateral, to be deemed to have made, respectively, such
                            purchase of Replacement Securities or sale of Collateral for an amount equal to the price therefor on
                            the date of such exercise obtained from a generally recognized source or the last bid quotation from
                            such a source at the most recent Close of Trading. Subject to Section 18, upon the satisfaction of all
                            obligations hereunder, any remaining Collateral shall be returned to Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Upon the occurrence of a Default under Section 12
                            entitling Borrower to terminate all Loans hereunder, Borrower shall have the right, in addition to any
                            other remedies provided herein, (a) to purchase a like amount of Collateral ("Replacement Collateral")
                            in the principal market for such Collateral in a commercially reasonable manner, (b) to sell a like
                            amount of the Loaned Securities in the principal market for such Loaned Securities in a commercially
                            reasonable manner and (c) to apply and set off the Loaned Securities and any proceeds thereof against
                            (i) the payment of the purchase price for such Replacement Collateral, (ii) Lender's obligation to
                            return any cash or other Collateral, and (iii) any amounts due to Borrower under Sections 5, 8 and 16.
                            In such event, Borrower may treat the Loaned Securities as its own and Lender's obligation to return a
                            like amount of the Collateral shall terminate; provided, however, that Lender shall immediately return
                            any letters of credit supporting any Loan upon the exercise or deemed exercise by Borrower of its
                            termination rights under Section 12. Borrower may similarly apply the Loaned Securities and any proceeds
                            thereof to any other obligation of Lender under this Agreement, including Lender's obligations with
                            respect to Distributions paid to Lender (and not forwarded to Borrower) in respect of Collateral. In the
                            event that (i) the sales price received from such Loaned Securities is less than (ii) the purchase price
                            of Replacement Collateral (plus the amount of any cash or other Collateral not replaced by Borrower and
                            all other amounts, if any, due to Borrower hereunder), Lender shall be liable to Borrower for the amount
                            of any such deficiency, together with interest on such amounts at a rate equal to (A) in the case of
                            Collateral consisting of Foreign Securities, L</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >OR, (B) in the case of Collateral consisting of any other
                            Securities (or other amounts due, if any, to Borrower hereunder), the Federal Funds Rate or (C) such
                            other rate as may be specified in Schedule B, in each case as such rate fluctuates from day to day, from
                            the date of such sale until the date of payment of such deficiency. As security for Lender's obligation
                            to pay such deficiency, Borrower shall have, and Lender hereby grants, a security interest in any
                            property of Lender then held by or for Borrower and a right of setoff with respect to such property and
                            any other amount payable by Borrower to Lender. The purchase price of any Replacement Collateral
                            purchased under this Section shall include, and the proceeds of any sale of Loaned Securities shall be
                            determined after deduction of, broker's fees and commissions and all other reasonable costs, fees and
                            expenses related to such purchase or sale (as the case may be). In the event Borrower exercises its
                            rights under this Section 13.2, Borrower may elect in its sole discretion, in lieu of purchasing all or
                            a portion of the Replacement Collateral or selling all or a portion of the Loaned Securities, to be
                            deemed to have made, respectively, such purchase of Replacement Collateral or sale of Loaned Securities
                            for an amount equal to the price therefor on the date of such exercise obtained from a generally
                            recognized source or the last bid quotation from such a source at the most recent Close of Trading.
                            Subject to Section 18, upon the satisfaction of all Lender's obligations hereunder, any remaining Loaned
                            Securities (or remaining cash proceeds thereof) shall be returned to Lender.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Unless otherwise agreed, the parties acknowledge and agree
                            that (a) the Loaned Securities and any Collateral consisting of Securities are of a type traded in a
                            recognized market, (b) in the absence of a generally recognized source for prices or bid or offer
                            quotations for any security, the non-defaulting party may establish the source therefor in its sole
                            discretion, and (c) all prices and bid and offer quotations shall be increased to include accrued
                            interest to the extent not already included therein (except to the extent contrary to market practice
                            with respect to the relevant Securities).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >In addition to its rights hereunder, the non-defaulting
                            party shall have any rights otherwise available to it under any other agreement or applicable law. In
                            addition to any other remedies to which a non-defaulting party may be entitled under the Agreement, the
                            defaulting party shall, with respect to an individual Loan or with respect to a class of Loans, be
                            liable to the non-defaulting party for (a) the amount of all reasonable legal or other expenses incurred
                            by the non-defaulting party in connection with or as a result of a Default, (b) damages in an amount
                            equal to the cost (including all payments, expenses and commissions) of entering into replacement
                            transactions and entering into or terminating hedge transactions in connection with or as a result of a
                            Default, and (c) any other loss, damage, cost or expense directly arising or resulting from the
                            occurrence of a Default in respect of a Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >14.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Transfer Taxes.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >All transfer taxes with respect to the transfer of the
                            Loaned Securities by Lender to Borrower and by Borrower to Lender upon termination of the Loan and with
                            respect to the transfer of Collateral by Borrower to Lender and by Lender to Borrower upon termination
                            of the Loan or pursuant to Section 4.5 or Section 9 shall be paid by Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >15.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Transfers.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >All transfers by either Borrower or Lender of Loaned
                            Securities or Collateral consisting of "financial assets" (within the meaning of the UCC) hereunder
                            shall be by (a) in the case of certificated securities, physical delivery of certificates representing
                            such securities together with duly executed stock and bond transfer powers, as the case may be, with
                            signatures guaranteed by a bank or a member firm of the New York Stock Exchange, Inc., (b) registration
                            of an uncertificated security in the transferee's name by the issuer of such uncertificated security,
                            (c) the crediting by a Clearing Organization of such financial assets to the transferee's "securities
                            account" (within the meaning of the UCC) maintained with such Clearing Organization, (d) such other
                            means as specified in this Agreement, including but not limited to those means specified in Sections
                            3.2(c) and 6.1(b), or (e) such other means as Borrower and Lender may agree.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >All transfers of cash hereunder shall be by (a) wire
                            transfer in immediately available, freely transferable funds, (b) crediting Lender's account carried by
                            Borrower or (c) such other means as Borrower and Lender may agree.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >All transfers of letters of credit from Borrower to Lender
                            shall be made by physical delivery to Lender of an irrevocable letter of credit issued by a "bank" as
                            defined in Section 3(a)(6)(A)-(C) of the Exchange Act. Transfers of letters of credit from Lender to
                            Borrower shall be made by causing such letters of credit to be returned or by causing the amount of such
                            letters of credit to be reduced to the amount required after such transfer.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >A transfer of Securities, cash or letters of credit may be
                            effected under this Section 15 on any day except (a) a day on which the transferee is closed for
                            business at its primary place of business or (b) a day on which a Clearing Organization or wire transfer
                            system is closed, if the facilities of such Clearing Organization or wire transfer system are required
                            to effect such transfer.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >For the avoidance of doubt, the parties agree and
                            acknowledge that the term "securities," as used herein (except in this Section 15), shall include any
                            "security entitlements" with respect to such securities (within the meaning of the UCC). In every
                            transfer of "financial assets" (within the meaning of the UCC) hereunder, the transferor shall take all
                            steps necessary (a) to effect a delivery to the transferee under Section 8-301 of the UCC, or to cause
                            the creation of a security entitlement in favor of the transferee under Section 8-501 of the UCC, (b) to
                            enable the transferee to obtain "control" (within the meaning of Section 8-106 of the UCC), and (c) to
                            provide the transferee with comparable rights under any applicable foreign law or regulation.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >16.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Contractual Currency.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender agree that (a) any payment in respect
                            of a Distribution under Section 8 shall be made in the currency in which the underlying Distribution of
                            cash was made, (b) any return of cash shall be made in the currency in which the underlying transfer of
                            cash was made, and (c) any other payment of cash in connection with a Loan under this Agreement shall be
                            in the currency agreed upon by Borrower and Lender in connection with such Loan (the currency
                            established under clause (a), (b) or (c) hereinafter referred to as the "Contractual Currency").
                            Notwithstanding the foregoing, the payee of any such payment may, at its option, accept tender thereof
                            in any other currency; provided, however, that, to the extent permitted by applicable law, the
                            obligation of the payor to make such payment will be discharged only to the extent of the amount of
                            Contractual Currency that such payee may, consistent with normal banking procedures, purchase with such
                            other currency (after deduction of any premium and costs of exchange) on the banking day next succeeding
                            its receipt of such currency.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If for any reason the amount in the Contractual Currency
                            received under Section 16.1, including amounts received after conversion of any recovery under any
                            judgment or order expressed in a currency other than the Contractual Currency, falls short of the amount
                            in the Contractual Currency due in respect of this Agreement, the party required to make the payment
                            will (unless a Default has occurred and such party is the non-defaulting party) as a separate and
                            independent obligation and to the extent permitted by applicable law, immediately pay such additional
                            amount in the Contractual Currency as may be necessary to compensate for the shortfall.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If for any reason the amount in the Contractual Currency
                            received under Section 16.1 exceeds the amount in the Contractual Currency due in respect of this
                            Agreement, then the party receiving the payment will (unless a Default has occurred and such party is
                            the non-defaulting party) refund promptly the amount of such excess.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >17.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >ERISA.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Lender shall, if any of the Securities transferred to the
                            Borrower hereunder for any Loan have been or shall be obtained, directly or indirectly, from or using
                            the assets of any Plan, so notify Borrower in writing upon the execution of this Agreement or upon
                            initiation of such Loan under Section 2.1. If Lender so notifies Borrower, then Borrower and Lender
                            shall conduct the Loan in accordance with the terms and conditions of Department of Labor Prohibited
                            Transaction Exemption 81-6 (46 Fed. Reg. 7527, Jan. 23, 1981; as amended, 52 Fed. Reg. 18754, May 19,
                            1987), or any successor thereto (unless Borrower and Lender have agreed prior to entering into a Loan
                            that such Loan will be conducted in reliance on another exemption, or without relying on any exemption,
                            from the prohibited transaction provisions of Section 406 of the Employee Retirement Income Security Act
                            of 1974, as amended, and Section 4975 of the Internal Revenue Code of 1986, as amended). Without
                            limiting the foregoing and notwithstanding any other provision of this Agreement, if the Loan will be
                            conducted in accordance with Prohibited Transaction Exemption 81-6, then:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower represents and warrants to Lender that it is
                            either (a) a bank subject to federal or state supervision, (b) a broker-dealer registered under the
                            Exchange Act or (c) exempt from registration under Section 15(a)(1) of the Exchange Act as a dealer in
                            Government Securities.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower represents and warrants that, during the term of
                            any Loan hereunder, neither Borrower nor any affiliate of Borrower has any discretionary authority or
                            control with respect to the investment of the assets of the Plan involved in the Loan or renders
                            investment advice (within the meaning of 29 C.F.R. Section 2510.3-21(c)) with respect to the assets of
                            the Plan involved in the Loan. Lender agrees that, prior to or at the commencement of any Loan
                            hereunder, it will communicate to Borrower information regarding the Plan sufficient to identify to
                            Borrower any person or persons that have discretionary authority or control with respect to the
                            investment of the assets of the Plan involved in the Loan or that render investment advice (as defined
                            in the preceding sentence) with respect to the assets of the Plan involved in the Loan. In the event
                            Lender fails to communicate and keep current during the term of any Loan such information, Lender rather
                            than Borrower shall be deemed to have made the representation and warranty in the first sentence of this
                            Section 17.2.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower shall mark to market daily each Loan hereunder
                            pursuant to Section 9.1 as is required if Lender is a Customer.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender agree that:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >the term "Collateral" shall mean cash, securities issued
                            or guaranteed by the United States government or its agencies or instrumentalities, or irrevocable bank
                            letters of credit issued by a person other than Borrower or an affiliate thereof;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >prior to the making of any Loans hereunder, Borrower shall
                            provide Lender with (i) the most recent available audited statement of Borrower's financial condition
                            and (ii) the most recent available unaudited statement of Borrower's financial condition (if more recent
                            than the most recent audited statement), and each Loan made hereunder shall be deemed a representation
                            by Borrower that there has been no material adverse change in Borrower's financial condition subsequent
                            to the date of the latest financial statements or information furnished in accordance herewith;</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >the Loan may be terminated by Lender at any time,
                            whereupon Borrower shall deliver the Loaned Securities to Lender within the lesser of (i) the customary
                            delivery period for such Loaned Securities, (ii) five Business Days, and (iii) the time negotiated for
                            such delivery between Borrower and Lender; provided, however, that Borrower and Lender may agree to a
                            longer period only if permitted by Prohibited Transaction Exemption 81-6; and</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >the Collateral transferred shall be security only for
                            obligations of Borrower to the Plan with respect to Loans, and shall not be security for any obligation
                            of Borrower to any agent or affiliate of the Plan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >18.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Single Agreement.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender acknowledge that, and have entered
                            into this Agreement in reliance on the fact that, all Loans hereunder constitute a single business and
                            contractual relationship and have been entered into in consideration of each other. Accordingly,
                            Borrower and Lender hereby agree that payments, deliveries and other transfers made by either of them in
                            respect of any Loan shall be deemed to have been made in consideration of payments, deliveries and other
                            transfers in respect of any other Loan hereunder, and the obligations to make any such payments,
                            deliveries and other transfers may be applied against each other and netted. In addition, Borrower and
                            Lender acknowledge that, and have entered into this Agreement in reliance on the fact that, all Loans
                            hereunder have been entered into in consideration of each other. Accordingly, Borrower and Lender hereby
                            agree that (a) each shall perform all of its obligations in respect of each Loan hereunder, and that a
                            default in the performance of any such obligation by Borrower or by Lender (the "Defaulting Party") in
                            any Loan hereunder shall constitute a default by the Defaulting Party under all such Loans hereunder,
                            and (b) the non-defaulting party shall be entitled to set off claims and apply property held by it in
                            respect of any Loan hereunder against obligations owing to it in respect of any other Loan with the
                            Defaulting Party.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >19.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >APPLICABLE LAW.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >THIS AGREEMENT SHALL BE GOVERNED AND CONSTRUED IN
                            ACCORDANCE WITH THE LAWS OF THE STATE OF NEW YORK WITHOUT GIVING EFFECT TO THE CONFLICT OF LAW
                            PRINCIPLES THEREOF.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >20.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Waiver.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The failure of a party to this Agreement to insist upon
                            strict adherence to any term of this Agreement on any occasion shall not be considered a waiver or
                            deprive that party of the right thereafter to insist upon strict adherence to that term or any other
                            term of this Agreement. All waivers in respect of a Default must be in writing.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >21.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Survival of Remedies.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >All remedies hereunder and all obligations with respect to
                            any Loan shall survive the termination of the relevant Loan, return of Loaned Securities or Collateral
                            and termination of this Agreement.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >22.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Notices and Other Communications.</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Any and all notices, statements, demands or other
                            communications hereunder may be given by </span><span style="font-family:Calibri; font-size:10.5pt">MOF
                            Securities Limited</span><span style="font-family:Calibri; font-size:10.5pt"> to the undersigned party
                            by telephone, mail, facsimile, e-mail, electronic message, telegraph, messenger or otherwise at the
                            phone and facsimile numbers provided by the undersigned party and maintained by </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF Securities Limited</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > in its books and records for such party. Any and all
                            notices, statements, demands or other communications hereunder may be given by the undersigned party to
                          </span><span style="font-family:Calibri; font-size:10.5pt">MOF Securities Limited</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > in writing electronically via the secure electronic
                            message center maintained by Interactive Brokers for the account of the undersigned party. Any notice,
                            statement, demand or other communication hereunder will be deemed effective on the day and at the time
                            on which it is received or, if not received, on the day and at the time on which its delivery was in
                            good faith attempted; provided, however, that any notice by a party to the other party by telephone
                            shall be deemed effective only if (a) such notice is followed by written confirmation thereof and (b) at
                            least one of the other means of providing notice that are specifically listed above has previously been
                            attempted in good faith by the notifying party.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >23.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >MANDATORY ARBITRATION.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >THE PARTIES HEREBY AGREE THAT ANY DISPUTE, CONTROVERSY OR
                            CLAIM BETWEEN THE PARTIES ARISING OUT OF THIS AGREEMENT OR ANY LOAN HEREUNDER SHALL BE SUBJECT TO THE
                            MANDATORY ARBITRATION PROVISION CONTAINED IN ANY CUSTOMER ACCOUNT OR SIMILAR AGREEMENT ENTERED INTO
                            BETWEEN SUCH PARTIES.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >24.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Miscellaneous.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Except as specified in Section 1 or as otherwise agreed by
                            the parties, this Agreement supersedes any other agreement between the parties hereto concerning loans
                            of Securities between Borrower and Lender. This Agreement shall not be assigned by either party without
                            the prior written consent of the other party and any attempted assignment without such consent shall be
                            null and void. Subject to the foregoing, this Agreement shall be binding upon and shall inure to the
                            benefit of Borrower and Lender and their respective heirs, representatives, successors and assigns. This
                            Agreement may be terminated by either party upon notice to the other, subject only to fulfillment of any
                            obligations then outstanding. This Agreement shall not be modified, except by an instrument in writing
                            signed by the party against whom enforcement is sought. The parties hereto acknowledge and agree that,
                            in connection with this Agreement and each Loan hereunder, time is of the essence. Each provision and
                            agreement herein shall be treated as separate and independent from any other provision herein and shall
                            be enforceable notwithstanding the unenforceability of any such other provision or agreement.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Any agreement between Borrower and Lender pursuant to
                            Section 10.5(b) or Section 25.37 shall be made (a) in writing, (b) orally, if confirmed promptly in
                            writing or through any system that compares Loans and in which Borrower and Lender are participants, or
                            (c) in such other manner as may be agreed by Borrower and Lender in writing.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:normal"
                          >25.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Definitions.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >For the purposes hereof:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Act of Insolvency" shall mean, with respect to any party,
                            (a) the commencement by such party as debtor of any case or proceeding under any bankruptcy, insolvency,
                            reorganization, liquidation, moratorium, dissolution, delinquency or similar law, or such party's
                            seeking the appointment or election of a receiver, conservator, trustee, custodian or similar official
                            for such party or any substantial part of its property, or the convening of any meeting of creditors for
                            purposes of commencing any such case or proceeding or seeking such an appointment or election, (b) the
                            commencement of any such case or proceeding against such party, or another seeking such an appointment
                            or election, or the filing against a party of an application for a protective decree under the
                            provisions of the Securities Investor Protection Act of 1970, which (i) is consented to or not timely
                            contested by such party, (ii) results in the entry of an order for relief, such an appointment or
                            election, the issuance of such a protective decree or the entry of an order having a similar effect, or
                            (iii) is not dismissed within 15 days, (c) the making by such party of a general assignment for the
                            benefit of creditors, or (d) the admission in writing by such party of such party's inability to pay
                            such party's debts as they become due.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Bankruptcy Code" shall have the meaning assigned in
                            Section 26.1</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Borrower" shall have the meaning assigned in Section
                            1.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Borrower Payment" shall have the meaning assigned in
                            Section 8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Broker-Dealer" shall mean any person that is a broker
                            (including a municipal securities broker), dealer, municipal securities dealer, government securities
                            broker or government securities dealer as defined in the Exchange Act, regardless of whether the
                            activities of such person are conducted in the United States or otherwise require such person to
                            register with the U.S. Securities and Exchange Commission or other regulatory body.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Business Day" shall mean, with respect to any Loan
                            hereunder, a day on which regular trading occurs in the principal market for the Loaned Securities
                            subject to such Loan, provided, however, that for purposes of determining the Market Value of any
                            Securities hereunder, such term shall mean a day on which regular trading occurs in the principal market
                            for the Securities whose value is being determined. Notwithstanding the foregoing, (a) for purposes of
                            Section 9, "Business Day" shall mean any day on which regular trading occurs in the principal market for
                            any Loaned Securities or for any Collateral consisting of Securities under any outstanding Loan
                            hereunder and "next Business Day" shall mean the next day on which a transfer of Collateral may be
                            effected in accordance with Section 15, and (b) in no event shall a Saturday or Sunday be considered a
                            Business Day.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Clearing Organization" shall mean (a) The Depository
                            Trust Company, or, if agreed to by Borrower and Lender, such other "securities intermediary" (within the
                            meaning of the UCC) at which Borrower (or Borrower's agent) and Lender (or Lender's agent) maintain
                            accounts, or (b) a Federal Reserve Bank, to the extent that it maintains a book-entry system.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Close of Business" shall mean 4:00 p.m. (New York City
                            time).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Close of Trading" shall mean, with respect to any
                            Security, the end of the primary trading session established by the principal market for such Security
                            on a Business Day, unless otherwise agreed by the parties.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Collateral" shall mean, whether now owned or hereafter
                            acquired and to the extent permitted by applicable law, (a) any property which Borrower and Lender agree
                            prior to the Loan shall be acceptable collateral and which is transferred to Lender pursuant to Sections
                            4 or 9 (including as collateral, for definitional purposes, any letters of credit mutually acceptable to
                            Lender and Borrower), (b) any property substituted therefor pursuant to Section 4.5, (c) all accounts in
                            which such property is deposited and all securities and the like in which any cash collateral is
                            invested or reinvested, and (d) any proceeds of any of the foregoing; provided, however, that if Lender
                            is a Customer, "Collateral" shall (subject to Section 17.4(a), if applicable) be limited to cash, U.S.
                            Treasury bills and notes, an irrevocable letter of credit issued by a "bank" (as defined in Section
                            3(a)(6)(A)-(C) of the Exchange Act), and any other property permitted to serve as collateral securing a
                            loan of securities under Rule 15c3-3 under the Exchange Act or any comparable regulation of the
                            Secretary of the Treasury under Section 15C of the Exchange Act (to the extent that Borrower is subject
                            to such Rule or comparable regulation) pursuant to exemptive, interpretive or no-action relief or
                            otherwise. If any new or different Security shall be exchanged for any Collateral by recapitalization,
                            merger, consolidation or other corporate action, such new or different Security shall, effective upon
                            such exchange, be deemed to become Collateral in substitution for the former Collateral for which such
                            exchange is made. For purposes of return of Collateral by Lender or purchase or sale of Securities
                            pursuant to Section 13, such term shall include Securities of the same issuer, class and quantity as the
                            Collateral initially transferred by Borrower to Lender, as adjusted pursuant to the preceding
                            sentence.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Collateral Distributions" shall have the meaning assigned
                            in Section 8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Confirmation" shall have the meaning assigned in Section
                            2.1.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Contractual Currency" shall have the meaning assigned in
                            Section 16.1.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Customer" shall mean any person that is a customer of
                            Borrower under Rule 15c3-3 under the Exchange Act or any comparable regulation of the Secretary of the
                            Treasury under Section 15C of the Exchange Act (to the extent that Borrower is subject to such Rule or
                            comparable regulation).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Cutoff Time" shall mean a time on a Business Day by which
                            a transfer of cash, securities or other property must be made by Borrower or Lender to the other, as
                            shall be agreed by Borrower and Lender in Schedule B, or shall be as specified in the policies and
                            procedures described on </span><span style="font-family:Calibri; font-size:10.5pt">MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >'s website or as agreed otherwise orally or in writing or,
                            in the absence of the above, as shall be determined in accordance with market practice.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Default" shall have the meaning assigned in Section
                            12.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Defaulting Party" shall have the meaning assigned in
                            Section 18.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Distribution" shall mean, with respect to any Security at
                            any time, any distribution made on or in respect of such Security, including, but not limited to: (a)
                            cash and all other property, (b) stock dividends, (c) Securities received as a result of split ups of
                            such Security and distributions in respect thereof, (d) interest payments, (e) all rights to purchase
                            additional Securities, and (f) any cash or other consideration paid or provided by the issuer of such
                            Security in exchange for any vote, consent or the taking of any similar action in respect of such
                            Security (regardless of whether the record date for such vote, consent or other action falls during the
                            term of the Loan). In the event that the holder of a Security is entitled to elect the type of
                            distribution to be received from two or more alternatives, such election shall be made by Lender, in the
                            case of a Distribution in respect of the Loaned Securities, and by Borrower, in the case of a
                            Distribution in respect of Collateral.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Equity Security" shall mean any security (as defined in
                            the Exchange Act) other than a "nonequity security," as defined in Regulation T.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Exchange Act" shall mean the Securities Exchange Act of
                            1934, as amended.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Extension Deadline" shall mean, with respect to a letter
                            of credit, the Cutoff Time on the Business Day preceding the day on which the letter of credit
                            expires.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"FDIA" shall have the meaning assigned in Section
                            26.4.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"FDICIA" shall have the meaning assigned in Section
                            26.5.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Federal Funds Rate" shall mean the rate of interest
                            (expressed as an annual rate), as published in Federal Reserve Statistical Release H.15 (519) or any
                            publication substituted therefor, charged for federal funds (dollars in immediately available funds
                            borrowed by banks on an overnight unsecured basis) on that day or, if that day is not a banking day in
                            New York City, on the next preceding banking day.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Foreign Securities" shall mean, unless otherwise agreed,
                            Securities that are principally cleared and settled outside the United States.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Government Securities" shall mean government securities
                            as defined in Section 3(a)(42)(A)-(C) of the Exchange Act.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Interest" shall have the meaning assigned in Section
                            5.1.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Lender" shall have the meaning assigned in Section
                            1.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Lender Payment" shall have the meaning assigned in
                            Section 8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"L</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >OR" shall mean for any date, the offered rate for deposits
                            in U.S. dollars for a period of three months which appears on the Reuters Screen L</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >O page as of 11:00 a.m., London time, on such date (or, if
                            at least two such rates appear, the arithmetic mean of such rates).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Loan" shall have the meaning assigned in Section
                            1.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Loaned Security" shall mean any Security transferred in a
                            Loan hereunder until such Security (or an identical Security) is transferred back to Lender hereunder,
                            except that, if any new or different Security shall be exchanged for any Loaned Security by
                            recapitalization, merger, consolidation or other corporate action, such new or different Security shall,
                            effective upon such exchange, be deemed to become a Loaned Security in substitution for the former
                            Loaned Security for which such exchange is made. For purposes of return of Loaned Securities by Borrower
                            or purchase or sale of Securities pursuant to Section 13, such term shall include Securities of the same
                            issuer, class and quantity as the Loaned Securities, as adjusted pursuant to the preceding
                            sentence.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Margin Deficit" shall have the meaning assigned in
                            Section 9.2.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Margin Excess" shall have the meaning assigned in Section
                            9.3.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Margin Notice Deadline" shall mean the time agreed to by
                            the parties in the relevant Confirmation, Schedule B hereto or otherwise as the deadline for giving
                            notice requiring same-day satisfaction of mark-to-market obligations as provided in Section 9 hereof
                            (or, in the absence of any such agreement, the deadline for such purposes established in accordance with
                            market practice).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Margin Percentage" shall mean, with respect to any Loan
                            as of any date, 100%, unless (a) Borrower and Lender agree otherwise, as provided in Section 24.2, or
                            Borrower in its discretion determines that applicable laws or market custom require greater THAN 100%
                            and (b) Lender is not a Customer. Notwithstanding the previous sentence, in the event that the writing
                            or other confirmation evidencing the agreement described in clause (a) does not set out such percentage
                            with respect to any such Loan, the Margin Percentage shall not be a percentage less than the percentage
                            obtained by dividing (i) the Market Value of the Collateral required to be transferred by Borrower to
                            Lender with respect to such Loan at the commencement of the Loan by (ii) the Market Value of the Loaned
                            Securities required to be transferred by Lender to Borrower at the commencement of the Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Market Value" shall have the meaning set forth in Annex
                            II or otherwise agreed to by Borrower and Lender in writing. Notwithstanding the previous sentence, in
                            the event that the meaning of Market Value has not been set forth in Annex II or in any other writing,
                            as described in the previous sentence, Market Value shall be reasonably determined by </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF Securities Limited</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > in accordance with its standard practices for valuing
                            Securities. The determinations of Market Value provided for in Annex II or in any other writing
                            described in this Section 25.38 shall apply for all purposes under this Agreement, except for purposes
                            of Section 13.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Payee" shall have the meaning assigned in Section
                            8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Payor" shall have the meaning assigned in Section
                            8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Plan" shall mean: (a) any "employee benefit plan" as
                            defined in Section 3(3) of the Employee Retirement Income Security Act of 1974 which is subject to Part
                            4 of Subtitle B of Title I of such Act; (b) any "plan" as defined in Section 4975(e)(1) of the Internal
                            Revenue Code of 1986; or (c) any entity the assets of which are deemed to be assets of any such
                            "employee benefit plan" or "plan" by reason of the Department of Labor's plan asset regulation, 29
                            C.F.R. Section 2510.3-101.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Regulation T" shall mean Regulation T of the Board of
                            Governors of the Federal Reserve System, as in effect from time to time.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Retransfer" shall mean, with respect to any Collateral,
                            to pledge, repledge, hypothecate, rehypothecate, lend, relend, sell or otherwise transfer such
                            Collateral, or to re-register any such Collateral evidenced by physical certificates in any name other
                            than Borrower's.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Securities" shall mean securities or, if agreed by the
                            parties in writing, other assets.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Securities Distributions" shall have the meaning assigned
                            in Section 8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Tax" shall have the meaning assigned in Section
                            8.5(a).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"UCC" shall mean the New York Uniform Commercial
                            Code.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >26.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Intent.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The parties recognize that each Loan hereunder is a
                            "securities contract," as such term is defined in Section 741 of Title 11 of the United States Code (the
                            "Bankruptcy Code"), as amended (except insofar as the type of assets subject to the Loan would render
                            such definition inapplicable).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >It is understood that each and every transfer of funds,
                            securities and other property under this Agreement and each Loan hereunder is a "settlement payment" or
                            a "margin payment," as such terms are used in Sections 362(b)(6) and 546(e) of the Bankruptcy
                            Code</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >It is understood that the rights given to Borrower and
                            Lender hereunder upon a Default by the other constitute the right to cause the liquidation of a
                            securities contract and the right to set off mutual debts and claims in connection with a securities
                            contract, as such terms are used in Sections 555 and 362(b)(6) of the Bankruptcy Code.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The parties agree and acknowledge that if a party hereto
                            is an "insured depository institution," as such term is defined in the Federal Deposit Insurance Act, as
                            amended ("FDIA"), then each Loan hereunder is a "securities contract" and "qualified financial
                            contract," as such terms are defined in the FDIA and any rules, orders or policy statements thereunder
                            (except insofar as the type of assets subject to the Loan would render such definitions
                            inapplicable).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >It is understood that this Agreement constitutes a
                            "netting contract" as defined in and subject to Title IV of the Federal Deposit Insurance Corporation
                            Improvement Act of 1991 ("FDICIA") and each payment obligation under any Loan hereunder shall constitute
                            a "covered contractual payment entitlement" or "covered contractual payment obligation," respectively,
                            as defined in and subject to FDICIA (except insofar as one or both of the parties is not a "financial
                            institution" as that term is defined in FDICIA).</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Except to the extent required by applicable law or
                            regulation or as otherwise agreed, Borrower and Lender agree that Loans hereunder shall in no event be
                            "exchange contracts" for purposes of the rules of any securities exchange and that Loans hereunder shall
                            not be governed by the buy-in or similar rules of any such exchange, registered national securities
                            association or other self-regulatory organization.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >27.</span><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >DISCLOSURE RELATING TO CERTAIN FEDERAL
                            PROTECTIONS.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >WITHOUT WAIVING ANY RIGHTS GIVEN TO LENDER HEREUNDER, IT
                            IS UNDERSTOOD AND AGREED THAT THE PROVISIONS OF THE SECURITIES INVESTOR PROTECTION ACT OF 1970 MAY NOT
                            PROTECT LENDER WITH RESPECT TO LOANED SECURITIES HEREUNDER AND THAT, THEREFORE, THE COLLATERAL DELIVERED
                            TO LENDER MAY CONSTITUTE THE ONLY SOURCE OF SATISFACTION OF BORROWER'S OBLIGATIONS IN THE EVENT BORROWER
                            FAILS TO RETURN THE LOANED SECURITIES.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >LENDER ACKNOWLEDGES THAT, IN CONNECTION WITH LOANS OF
                            GOVERNMENT SECURITIES AND AS OTHERWISE PERMITTED BY APPLICABLE LAW, SOME SECURITIES PROVIDED BY BORROWER
                            AS COLLATERAL UNDER THIS AGREEMENT MAY NOT BE GUARANTEED BY THE UNITED STATES.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Executed and Agreed By:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF Securities Limited</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >By providing this Agreement to eligible </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > Customers who are applying to participate in </span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >MOF</span><span
                            style="font-family:宋体; font-size:10.5pt"
                          >’</span><span style="font-family:Calibri; font-size:10.5pt">s
                            Fully Paid Lending Program, </span><span style="font-family:Calibri; font-size:10.5pt">MOF</span><span
                            style="font-family:Calibri; font-size:10.5pt"
                          > agrees to the terms and conditions specified
                            herein.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >COUNTERPARTY:</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Annex I-A</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Party acting as Agent" hereby does not apply.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Annex II</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Market Value Shall not apply.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Annex III</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Term Loans</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >This Annex sets forth additional terms and conditions
                            governing Loans designated as "Term Loans" in which Lender lends to Borrower a specific amount of Loaned
                            Securities ("Term Loan Amount") against a pledge of cash Collateral by Borrower for an agreed upon rate
                            of Interest until a scheduled termination date ("Termination Date"). Unless otherwise defined,
                            capitalized terms used but not defined in this Annex shall have the meanings assigned in the Securities
                            Loan Agreement of which it forms a part (such agreement, together with this Annex and any other annexes,
                            schedules or exhibits, referred to as the "Agreement").</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The terms of this Annex shall apply to Loans of Equity
                            Securities only if they are designated as Term Loans in a Confirmation therefor provided pursuant to the
                            Agreement and executed by each party, in a schedule to the Agreement or in this Annex. All Loans of
                            Securities other than Equity Securities shall be "Term Loans" subject to this Annex, unless otherwise
                            agreed in a Confirmation or other writing.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The Confirmation for a Term Loan shall set forth, in
                            addition to any terms required to be set forth therein under the Agreement, the Term Loan Amount, rate
                            of Interest and the Termination Date. Lender and Borrower agree that, except as specifically provided in
                            this Annex, each Term Loan shall be subject to all terms and conditions of the Agreement, including,
                            without limitation, any provisions regarding the parties' respective rights to terminate a Loan.</span>
                          </p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >In the event that either party exercises its right under
                            the Agreement to terminate a Term Loan on a date (the "Early Termination Date") prior to the Termination
                            Date, Lender and Borrower shall, unless otherwise agreed, use their best efforts to negotiate in good
                            faith a new Term Loan (the "Replacement Loan") of comparable or other Securities, which shall be
                            mutually agreed upon by the parties, with a Market Value equal to the Market Value of the Term Loan
                            Amount under the terminated Term Loan (the "Terminated Loan") as of the Early Termination Date. Such
                            agreement shall, in accordance with Section 2 of this Annex, be confirmed in a new Confirmation at the
                            commencement of the Replacement Loan and be executed by each party. Each Replacement Loan shall be
                            subject to the same terms as the corresponding Terminated Loan, other than with respect to the
                            commencement date and the identity of the Loaned Securities. The Replacement Loan shall commence on the
                            date on which the parties agree which Securities shall be the subject of the Replacement Loan and shall
                            be scheduled to terminate on the scheduled Termination Date of the Terminated Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender agree that, except as provided in
                            Section 5 of this Annex, if the parties enter into a Replacement Loan, the Collateral for the related
                            Terminated Loan need not be returned to Borrower and shall instead serve as Collateral for such
                            Replacement Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If the parties are unable to negotiate and enter into a
                            Replacement Loan for some or all of the Term Loan Amount on or before the Early Termination Date, (a)
                            the party requesting termination of the Terminated Loan shall pay to the other party a Breakage Fee
                            computed in accordance with Section 6 of this Annex with respect to that portion of the Term Loan Amount
                            for which a Replacement Loan is not entered into and (b) upon the transfer by Borrower to Lender of the
                            Loaned Securities subject to the Terminated Loan, Lender shall transfer to Borrower Collateral for the
                            Terminated Loan in accordance with and to the extent required under the Agreement, provided that no
                            Default has occurred with respect to Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >For purposes of this Annex, the term "Breakage Fee" shall
                            mean a fee agreed by Borrower and Lender in the Confirmation or otherwise orally or in writing. In the
                            absence of any such agreement, the term "Breakage Fee" shall mean, with respect to Loans of Government
                            Securities, a fee equal to the sum of (a) the cost to the non-terminating party (including all payments,
                            expenses and commissions) of entering into replacement transactions and entering into or terminating
                            hedge transactions in connection with or as a result of the termination of the Terminated Loan, and (b)
                            any other loss, damage, cost or expense directly arising or resulting from the termination of the
                            Terminated Loan that is incurred by the non- terminating party (other than consequential losses or costs
                            for lost profits or lost opportunities), as determined by the non-terminating party in a commercially
                            reasonable manner, and (c) any other amounts due and payable by the terminating party to the
                            non-terminating party under the Agreement on the Early Termination Date.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Schedule A</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Names and Addresses for Communications Between Parties
                            Shall not apply.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Schedule B Defined Terms and Supplemental
                            Provisions</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >None.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Annex I-A</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >"Party acting as Agent" hereby does not apply.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Annex II</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Market Value</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Shall not apply</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Annex III</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt; font-weight:bold"
                          >Term Loans</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >This Annex sets forth additional terms and conditions
                            governing Loans designated as "Term Loans" in which Lender lends to Borrower a specific amount of Loaned
                            Securities ("Term Loan Amount") against a pledge of cash Collateral by Borrower for an agreed upon Cash
                            Collateral Fee until a scheduled termination date ("Termination Date"). Unless otherwise defined,
                            capitalized terms used but not defined in this Annex shall have the meanings assigned in the Securities
                            Loan Agreement of which it forms a part (such agreement, together with this Annex and any other annexes,
                            schedules or exhibits, referred to as the "Agreement").</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The terms of this Annex shall apply to Loans of Equity
                            Securities only if they are designated as Term Loans in a Confirmation therefor provided pursuant to the
                            Agreement and executed by each party, in a schedule to the Agreement or in this Annex. All Loans of
                            Securities other than Equity Securities shall be "Term Loans" subject to this Annex, unless otherwise
                            agreed in a Confirmation or other writing.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >The Confirmation for a Term Loan shall set forth, in
                            addition to any terms required to be set forth therein under the Agreement, the Term Loan Amount, the
                            Cash Collateral Fee and the Termination Date. Lender and Borrower agree that, except as specifically
                            provided in this Annex, each Term Loan shall be subject to all terms and conditions of the Agreement,
                            including, without limitation, any provisions regarding the parties' respective rights to terminate a
                            Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >In the event that either party exercises its right under
                            the Agreement to terminate a Term Loan on a date (the "Early Termination Date") prior to the Termination
                            Date, Lender and Borrower shall, unless otherwise agreed, use their best efforts to negotiate in good
                            faith a new Term Loan (the "Replacement Loan") of comparable or other Securities, which shall be
                            mutually agreed upon by the parties, with a Market Value equal to the Market Value of the Term Loan
                            Amount under the terminated Term Loan (the "Terminated Loan") as of the Early Termination Date. Such
                            agreement shall, in accordance with Section 2 of this Annex, be confirmed in a new Confirmation at the
                            commencement of the Replacement Loan and be executed by each party. Each Replacement Loan shall be
                            subject to the same terms as the corresponding Terminated Loan, other than with respect to the
                            commencement date and the identity of the Loaned Securities. The Replacement Loan shall commence on the
                            date on which the parties agree which Securities shall be the subject of the Replacement Loan and shall
                            be scheduled to terminate on the scheduled Termination Date of the Terminated Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Borrower and Lender agree that, except as provided in
                            Section 5 of this Annex, if the parties enter into a Replacement Loan, the Collateral for the related
                            Terminated Loan need not be returned to Borrower and shall instead serve as Collateral for such
                            Replacement Loan.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >If the parties are unable to negotiate and enter into a
                            Replacement Loan for some or all of the Term Loan Amount on or before the Early Termination Date, (a)
                            the party requesting termination of the Terminated Loan shall pay to the other party a Breakage Fee
                            computed in accordance with Section 6 of this Annex with respect to that portion of the Term Loan Amount
                            for which a Replacement Loan is not entered into and (b) upon the transfer by Borrower to Lender of the
                            Loaned Securities subject to the Terminated Loan, Lender shall transfer to Borrower Collateral for the
                            Terminated Loan in accordance with and to the extent required under the Agreement, provided that no
                            Default has occurred with respect to Borrower.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >For purposes of this Annex, the term "Breakage Fee" shall
                            mean a fee agreed by Borrower and Lender in the Confirmation or otherwise orally or in writing. In the
                            absence of any such agreement, the term "Breakage Fee" shall mean, with respect to Loans of Government
                            Securities, a fee equal to the sum of (a) the cost to the non-terminating party (including all fees,
                            expenses and commissions) of entering into replacement transactions and entering into or terminating
                            hedge transactions in connection with or as a result of the termination of the Terminated Loan, and (b)
                            any other loss, damage, cost or expense directly arising or resulting from the termination of the
                            Terminated Loan that is incurred by the non-terminating party (other than consequential losses or costs
                            for lost profits or lost opportunities), as determined by the non-terminating party in a commercially
                            reasonable manner, and (c) any other amounts due and payable by the terminating party to the
                            non-terminating party under the Agreement on the Early Termination Date.</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Schedule A</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Names and Addresses for Communications Between
                            Parties</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Shall not apply</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Schedule B</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:center; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >Defined Terms and Supplemental Provisions</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >&#xa0;</span></p>
                          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span
                            style="font-family:Calibri; font-size:10.5pt"
                          >None.</span></p>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- 签名 -->
                  <div>
                    <h4 class="zhuce2ji " style="padding-left: 0px;">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</h4> <!-- $i18n - 签名 -->
                    <div
                      class="zhuce3ji"
                    >{{ $t('registerOpenAccount.openAccount.common.signature.signature3') }}</div> <!-- $i18n - 签署本协议代表您接受此类协议的条款与条件。 -->
                    <div class="zhuce2ji">
                      <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</span> <!-- $i18n - 账户名称: -->
                      {{ company15.signature.zhanghuName }}
                      <br>
                      <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</span> <!-- $i18n - 日期: -->
                      {{ company15.signature.riqi }}
                    </div>
                  </div>
                  <div class="signature" style="margin-top: 60px;">
                    <ul class="bottom10 clearfix">
                      <li class="left1 right10">{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                      <li class="left1">{{ company15.signature.signatureZhanshi }}</li>
                    </ul>
                    <el-form-item class="zhuce3ji" prop="signature">
                      <el-input v-model="company15.signature.signature" maxlength="128" />
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('company15')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }}</el-button>
                  </el-form-item>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    var validateSignature = (rule, value, callback) => {
      const mate = this.company15.signature.signatureZhanshi === this.company15.signature.signature;
      this.$verify.Signature(rule, value, mate, callback);
    };
    return {
      lang: this.$Cookies.get('locale'),
      isTokenHint: false,
      company15: {
        signature: {
          istongyi: '',
          zhanghuName: '',
          riqi: '',
          signatureZhanshi: '',
          signature: ''
        },
        isstate: {
          isshow: false
        }
      },
      formdata: {},
      newValue: null,
      rules: {
        signature: [{
          validator: validateSignature, trigger: 'blur'
        }]
      }
    };
  },
  created() {
    this.personalType();
    this.accountCoInvestmentAccount();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取用户已填入的信息
    accountCoInvestmentAccount() {
      this.$http({
        url: '/account/accountCoInvestmentAccount/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.newValue = res.object[0];
              this.company15.isstate.isshow = res.object[0].isStockIncomeGrowthPlan;
            }
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 账户声明
    // 声明 签名
    getSignature(personalType) {
      this.$http({
        url: '/account/accountCoPersonal/findByAccountId',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.company15.signature.zhanghuName = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.company15.signature.riqi = this.$verify.NowDate3();
              this.company15.signature.signatureZhanshi = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.company15.signature.signature = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
            }
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 个人类型
    personalType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            // this.getSignature(res.object[1].dictValue);
            this.getSignature('authorizedPerson');
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    // 保存继续
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.submitFormData();
          // if (this.company15.isstate.isshow === true) {
          //   this.submitFormData();
          // } else {
          //   this.company15.isstate.isshow = false;
          // }
        } else {
          this.company15.isstate.isshow = true;
          return false;
        }
      });
    },
    // 提交表单数据
    submitFormData() {
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();

      let data = null;
      if (this.newValue === null) {
        data = {
          accountId,
          createdBy,
          createdDate,
          isStockIncomeGrowthPlan: this.company15.isstate.isshow,
          accountName: this.company15.signature.zhanghuName,
          signatureDate: this.$verify.NowDate(this.company15.signature.riqi),
          signature: this.company15.signature.signature
        };
      } else {
        this.newValue.isStockIncomeGrowthPlan = this.company15.isstate.isshow;
        this.newValue.accountName = this.company15.signature.zhanghuName;
        this.newValue.signatureDate = this.$verify.NowDate(this.company15.signature.riqi);
        this.newValue.signature = this.company15.signature.signature;
        data = JSON.parse(JSON.stringify(this.newValue));
      }
      this.$http({
        url: '/openAccount/company/saveFifteen',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/company16' });
          } else if (res.code === 2003) {
            if (!this.isTokenHint) {
              this.isTokenHint = true;
              this.$verify.staleDated(this);
            }
          } else {
            this.$message.error(res.message || res.code);
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/company14' });
    }
  }
};
</script>

<style lang="scss" scoped>
h4 {
  padding-left: 10px;
  font-size: 16px;
  line-height: 44px;
}
.show-title {
  /* margin: 30px 20px 30px 100px; */
  position: relative;
}
.show-title > h4 {
  padding-left: 0px;
}
.show-title > .title-centent {
  width: 500px;
}

.show-title .gengduo {
  line-height: 20px;
  border-radius: 2px;
  width: 70px;
  height: 22px;
  background-color: rgb(77, 110, 157);
  color: rgb(255, 255, 255);
  text-align: center;
  margin-top: 10px;
}
.show-title .gengduo:hover {
  cursor: pointer;
  background-color: rgb(0, 0, 0);
}

.form-content .show-textcontent h4 {
  color: #4d6e9d;
  padding: 0;
}
.form-content .show-textcontent .publictext {
  width: 100%;
  height: 400px;
  border: 1px solid #000;
  margin-bottom: 10px;
}

.form-content .iscontent {
  margin: 20px;
}
.form-content .iscontent > p:nth-child(2) {
  color: #4d6e9d;
  cursor: pointer;
}

/* 表单label 设置 */
.form-content div:nth-child(n + 2) .el-form-item__label {
  line-height: 22px;
}
.form-content div.signature .el-form-item__label {
  line-height: 120px;
}
</style>

