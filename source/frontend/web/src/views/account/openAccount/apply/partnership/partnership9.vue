<template>
  <div>
    <div id="ZC-regiMain">
      <div class="topBG">
        <div class="titleIMG">{{ '-' + $t('registerOpenAccount.header.openAccount') + '(7/17) -' }}</div>
        <div class="PrintBOX clearfix">
          <div class="iconPrint" @click="printPdf()" />
        </div>
      </div>
      <div class="contentBOX">
        <div class="contentBOXdignwei">
          <el-form
            ref="partnership9"
            autocomplete="off"
            status-icon
            :rules="rules"
            :model="partnership9"
            label-width="0px"
            class="demo-ruleForm"
          >
            <div class="formContent">
              <div class="formNape">
                <div class="formNapeTitle">
                  <h3>{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.title') }}</h3> <!-- $i18n - 有关法律实体客户受益所有人的认证 -->
                  <p>{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe') }}</p> <!-- $i18n - 请仔细阅读以下说明： -->
                </div>
                <div class="formNapeContent">
                  <h4 style="margin-bottom:6px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.quiz1') }}</h4> <!-- $i18n - 本表格有什么用？ -->
                  <span
                    class="zhuce3ji"
                  >{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe1') }}</span> <!-- $i18n - 为打击金融犯罪，监管机构要求某些金融机构获 ... -->
                  <h4 style="margin-bottom:6px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.quiz2') }}</h4> <!-- $i18n - 哪些人必须填写本表格？ -->
                  <span
                    class="zhuce3ji"
                  >{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe2') }}</span> <!-- $i18n - 本表格必须由代表法律实体开立新账户的 ... -->
                  <h4 style="margin-bottom:6px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.quiz3') }}</h4> <!-- $i18n - 我需要提供什么信息？ -->
                  <ul>
                    <li class="huifu zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe3a') }}</li> <!-- $i18n - 申请本账户的个人的姓名、职务以及要求的证明。 -->
                    <li
                      class="huifu zhuce3ji"
                    >{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe3b') }}</li> <!-- $i18n - 对法律实体客户负有重大经营责任的个人（如 ... -->
                    <li class="huifu zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe3c') }}</li> <!-- $i18n - 除特例情况外，请提供拥有开户法律实 ... -->
                  </ul>
                  <!-- 认证 -->
                  <div class="renzheng clearfix">
                    <h4 style="margin-bottom:6px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.renzheng') }}</h4> <!-- $i18n - 认证 -->
                    <div class="left1">
                      <div class="zhuce3ji" style="line-height: 26px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe4a') }}</div> <!-- $i18n - 开户自然人的姓名及职务： -->
                      <div class="zhuce3ji" style="line-height: 26px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.attestation.describe4b') }}</div> <!-- $i18n - 开户法律实体的名称及地址： -->
                    </div>
                    <div class="right1 zhuce3ji zimuNewline">
                      {{ partnership9.rezheng.xing }} {{ partnership9.rezheng.zhongjianming }} {{ partnership9.rezheng.ming }} {{ partnership9.rezheng.suffix }} {{ partnership9.rezheng.companyPosition }}
                      <br>
                      {{ partnership9.rezheng.companyName }}
                      <br>
                      {{ partnership9.rezheng.location }}
                      <br>
                      {{ partnership9.rezheng.chenshi }}, {{ partnership9.rezheng.shengshi }}, {{ partnership9.rezheng.postcode }}
                      <br>
                      {{ partnership9.rezheng.state }}
                    </div>
                  </div>
                  <!-- 经营权 -->
                  <h4
                    class="top20"
                    style="margin-bottom:18px;font-size: 16px;
                    font-weight: bold;
                    color: #333;"
                  >{{ $t('registerOpenAccount.openAccount.common.beneficial.run.title') }}</h4> <!-- $i18n - 有经营权的个人的身份证明 ... -->
                  <span
                    class="zhuce3ji"
                  >{{ $t('registerOpenAccount.openAccount.common.beneficial.run.describe') }}</span> <!-- $i18n - 提供对开户法律实体负有重大管理责任的个 ... -->
                  <div v-show="partnership9.isJingyinren" class="gudongTable">
                    <table border="1" cellspacing="0" cellpadding="0">
                      <thead>
                        <tr>
                          <th>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.name') }}</th> <!-- $i18n - 姓名 -->
                          <th>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.ownership') }}</th> <!-- $i18n - 所有权/权利 -->
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item,index) in partnership9.tableTbady" :key="index">
                          <td>{{ item.compellation }}</td>
                          <td>{{ item.suoyouquan }}</td>
                          <td>
                            <div v-if="item.delBtn" class="xiaoshou deleteBtn" @click="removePersonalTypeJ(item.id)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                          </td>
                        </tr>

                        <!-- <tr>
                          <td>所有权合计</td>
                          <td>0%</td>
                          <td />
                        </tr> -->
                      </tbody>
                    </table>
                  </div>
                  <div
                    v-if="partnership9.tableTbady.length === 0"
                    class="addBtn"
                    style="    width: 960px;
                    height: 41px;margin-top:10px;"
                  >
                    <div class="add" @click="addelement(1)">{{ $t('registerOpenAccount.openAccount.common.beneficial.run.btnAddRun') }}</div> <!-- $i18n - 添加经营负责人 -->
                  </div>
                  <!-- 列示有25%  -->
                  <h4 style="margin-bottom:6px;margin-top:24px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.title') }}</h4> <!-- $i18n - 列示有25%或以上所有权的受益所有人 -->
                  <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.describe1') }}</span> <!-- $i18n - 请提供直接或间接、通过任何合同、安排、谅解 ... -->
                  <ul>
                    <li class="huifu zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.describe2a') }}</li> <!-- $i18n - 以上列出的负责经营实体的人士 ... -->
                    <li
                      class="huifu zhuce3ji"
                    >{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.describe2b') }}</li> <!-- $i18n - 本表格上列出的受益所有人必须为自然 ... -->
                  </ul>
                  <ul class="left25">
                    <li class="zhuce3ji CORccc">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.perli1') }}</li> <!-- $i18n - * 账户持有人是“ABC公司” -->
                    <li class="zhuce3ji CORccc">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.perli2') }}</li> <!-- $i18n - * ABC公司50%由John Smith所有，另外50%由XYZ公司所有。 -->
                    <li class="zhuce3ji CORccc">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.perli3') }}</li> <!-- $i18n - * XYZ公司50%由Sally Smith所有，另外50%由Mary Brown所有。 -->
                  </ul>
                  <h3 class="left20 top10 zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.apply') }}</h3> <!-- $i18n - 以下自然人将被列入ABC公司的账户申请表： -->
                  <ul class="left25">
                    <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.owner1') }}</li> <!-- $i18n - * John Smith作为ABC公司50%的直接所有人。 -->
                    <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.owner2') }}</li> <!-- $i18n - * Sally Smith作为ABC公司25%的间接所 ... -->
                    <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.owner3') }}</li> <!-- $i18n - * Mary Brown作为ABC公司25%的间接 ... -->
                  </ul>
                  <p class="zhuce3ji" style="margin-top:10px;margin-bootom:10px">
                    {{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.notice') }}
                  </p> <!-- $i18n - 注：如果某信托在开立该账户的法律实体 ... -->
                  <el-form-item id="radioInline">
                    <el-radio-group v-model="partnership9.isOne25shouyi">
                      <el-radio class="zhuce3ji" label="1">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.explain1') }}</el-radio> <!-- $i18n - 是的，有一位或多位拥有开户法 ... -->
                      <br>
                      <el-radio
                        class="zhuce3ji"
                        label="2"
                      >{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.explain2') }}</el-radio> <!-- $i18n - 不，我保证，据我所知没有直接或间接拥有开户法律实体 ... -->
                      <br>
                    </el-radio-group>
                  </el-form-item>
                  <div v-show="partnership9.isbiaoge25" class="gudongTable">
                    <table border="1" cellspacing="0" cellpadding="0">
                      <thead>
                        <tr>
                          <th>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.name') }}</th> <!-- $i18n - 姓名 -->
                          <th>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.ownership') }}</th> <!-- $i18n - 所有权/权利 -->
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item,index) in partnership9.tableTbady25" :key="index">
                          <td>{{ item.compellation }}</td>
                          <td>{{ item.suoyouquan }}</td>
                          <td>
                            <div v-if="item.delBtn" class="xiaoshou deleteBtn" @click="removePersonalTypeJ(item.id)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                          </td>
                        </tr>

                        <tr>
                          <td>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.sum') }}</td> <!-- $i18n - 所有权合计 -->
                          <td>{{ partnership9.suoyouquanZongshu1 }}%</td>
                          <td />
                        </tr>
                      </tbody>
                    </table>
                  </div>
                  <div
                    v-if="ownershipSum < 75"
                    class="addBtn"
                    style="    width: 960px;
                                 height: 41px;margin-top:10px;"
                  >
                    <div class="add" @click="addelement(2)">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit25.btnAdd25') }}</div> <!-- $i18n - 或以上所有人 -->
                  </div>
                  <!-- 列示有10% -->
                  <h4 style="margin-bottom:6px;margin-top:24px;">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.title') }}</h4> <!-- $i18n - 列示有10%或以上所有权的受益所有人 -->
                  <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.describe1') }}</p> <!-- $i18n - 请提供直接或间接、通过任何合 ... -->
                  <ul>
                    <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.describe2a') }}</li> <!-- $i18n - 您无需在下方列出的已在上方列出且有25%及以上所有权的人士。 -->
                    <li class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.describe2b') }}</li> <!-- $i18n - 如果被列为负责管理实体的个人有10%或以上的所有 ... -->
                  </ul>
                  <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.notice') }}</p> <!-- $i18n - 注：如果某信托在开立该账户的法律实体中持股10%或 ... -->
                  <p class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.explain') }}</p> <!-- $i18n - 如果没有直接或间接拥有开户实体10%或以上 ... -->
                  <el-form-item id="radioInline" style="margin-bottom:0px">
                    <el-radio-group v-model="partnership9.isOne25shouyi1">
                      <el-radio
                        class="zhuce3ji"
                        label="1"
                      >{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.explain2a') }}</el-radio> <!-- $i18n - 是的，有一位或多位拥有实体10%所有 ... -->
                      <br>
                      <el-radio
                        class="zhuce3ji"
                        label="2"
                      >{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.explain2b') }}</el-radio> <!-- $i18n - 不，我保证，据我所知没有直接或间接 ... -->
                      <br>
                    </el-radio-group>
                  </el-form-item>
                  <div v-show="partnership9.isbiaoge10" class="gudongTable">
                    <table border="1" cellspacing="0" cellpadding="0">
                      <thead>
                        <tr>
                          <th>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.name') }}</th> <!-- $i18n - 姓名 -->
                          <th>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.ownership') }}</th> <!-- $i18n - 所有权/权利 -->
                          <th />
                        </tr>
                      </thead>
                      <tbody>
                        <tr v-for="(item,index) in partnership9.tableTbady10" :key="index">
                          <td>{{ item.compellation }}</td>
                          <td>{{ item.suoyouquan }}</td>
                          <td>
                            <div v-if="item.delBtn" class="xiaoshou deleteBtn" @click="removePersonalTypeJ(item.id)">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                          </td>
                        </tr>

                        <tr>
                          <td>{{ $t('registerOpenAccount.openAccount.common.beneficial.common.sum') }}</td> <!-- $i18n - 所有权合计 -->
                          <td>{{ partnership9.suoyouquanZongshu2 }}%</td>
                          <td />
                        </tr>
                      </tbody>
                    </table>
                  </div>

                  <div
                    v-if="ownershipSum < 90"
                    class="addBtn"
                    style="    width: 960px;
                               height: 41px;"
                  >
                    <div class="add" @click="addelement(3)">{{ $t('registerOpenAccount.openAccount.common.beneficial.benefit10.btnAdd10') }}</div> <!-- $i18n - 添加10%或以上所有人 -->
                  </div>
                  <div>
                    <h4 class="zhuce2ji paddingleft0">{{ $t('registerOpenAccount.openAccount.common.signature.signature') }}</h4> <!-- $i18n - 签名 -->
                    <p
                      class="zhuce2ji"
                      style="margin-bottom:6px;"
                    >{{ $t('registerOpenAccount.openAccount.common.signature.signature2') }}</p> <!-- $i18n - 我声明，输入我的姓名并通过互联 ... -->
                    <div class="zhuce2ji">
                      <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.zhanghuName') }}</span> <!-- $i18n - 账户名称: -->
                      {{ partnership9.shengming.zhanghuName }}
                      <br>
                      <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.signature.date') }}</span> <!-- $i18n - 日期: -->
                      {{ partnership9.shengming.riqi }}
                    </div>
                  </div>
                  <div class="signature" style="margin-top: 60px;">
                    <ul class="bottom10 clearfix">
                      <li class="left1 right10">{{ $t('registerOpenAccount.openAccount.common.signature.signature1') }}</li> <!-- $i18n - 签名: -->
                      <li class="left1">{{ partnership9.shengming.signatureZhanshi }}</li>
                    </ul>
                    <el-form-item class="zhuce3ji" prop="shengming.signature">
                      <el-input v-model="partnership9.shengming.signature" maxlength="128" />
                    </el-form-item>
                  </div>
                </div>
              </div>
              <!-- 提交按钮 -->
              <div v-show="partnership9.isbottomBox" class="formbtn">
                <div class="formbtn-content">
                  <el-form-item>
                    <el-button class="right-btn BTbtn" plain @click="submitForm('partnership9')">{{ $t('registerOpenAccount.footer.btnYes') }}</el-button>
                    <el-button class="left-btn BTbtn" plain @click="beforepage()">{{ $t('registerOpenAccount.footer.btnNo') }}</el-button>
                  </el-form-item>
                </div>
              </div>
            </div>
          </el-form>
        </div>
      </div>

      <!-- 模态框  经营人-->
      <div v-if="partnership9.ifmodalBox" class="modalBox">
        <div class="contentBOX">
          <div class="contentBOXdignwei contentBOXMoL">
            <el-form
              ref="partnership9"
              status-icon
              :rules="rules"
              :model="partnership9"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="biaoTitle">{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.titleRun') }}</div> <!-- $i18n - 添加经营负责人 -->
                <!-- 人-选择 -->
                <div class="formNape">
                  <div class="formNapeContent">
                    <p>{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.describe1') }}</p> <!-- $i18n - 提供准确的信息，因为我们须使用数据库服 ... -->
                    <el-form-item v-if="partnership9.ismodalBox1Cot" prop="xuanzeren1.beneficiaryName">
                      <el-select v-model="partnership9.xuanzeren1.beneficiaryName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"> <!-- $i18n - 请选择 -->
                        <el-option v-for="(item,index) in partnership9.xuanzeren1.beneficiaryNameList" :key="index" :label="item.SYRname" :value="item.SYRvalue" @click.native="partnership9.ismodalBox1Cot=true" />
                        <el-option :label="$t('registerOpenAccount.openAccount.common.beneficial.modal.notListed')" value="NotListed" @click.native="partnership9.ismodalBox1Cot=false" /> <!-- $i18n - 未列出 -->
                      </el-select>
                    </el-form-item>
                    <el-form-item v-if="partnership9.ismodalBox1Cot" prop="xuanzeren1.zhiwei">
                      <el-input v-model="partnership9.xuanzeren1.zhiwei" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.beneficial.modal.position')" /> <!-- $i18n - 职位 -->
                    </el-form-item>
                  </div>
                </div>
                <div v-if="!partnership9.ismodalBox1Cot">
                  <!-- 个人信息 -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.info.title') }}</h3> <!-- $i18n - 个人信息 -->
                    </div>
                    <div class="formNapeContent">
                      <el-form-item prop="gerenGudong.companyName">
                        <el-select v-model="partnership9.gerenGudong.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                          <dictionary dict-code="appellationType" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="gerenGudong.call">
                        <el-input v-model="partnership9.gerenGudong.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.middle">
                        <el-input v-model="partnership9.gerenGudong.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 中间名,不适用于中国客户 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.surname">
                        <el-input v-model="partnership9.gerenGudong.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.suffix">
                        <el-select v-model="partnership9.gerenGudong.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                          <el-option label="Jr " value="Jr" />
                          <el-option label="Sr " value="Sr" />
                          <el-option label="I " value="I" />
                          <el-option label="II " value="II" />
                          <el-option label="III " value="III" />
                          <el-option label="IV " value="IV" />
                          <el-option label="V " value="V" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.gerenGudong.issuoyouquanBi}"
                            @click="partnership9.gerenGudong.issuoyouquanBi=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.gerenGudong.issuoyouquanBi}"
                            @click="partnership9.gerenGudong.issuoyouquanBi=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.ownership') }}</li> <!-- $i18n - 经营负责人是10%或25%以上所有权的人士吗 -->
                        </ul>
                      </div>
                      <el-form-item
                        v-if="partnership9.gerenGudong.issuoyouquanBi"
                        prop="gerenGudong.suoyouquanBi"
                        class="percentage"
                      >
                        <el-input v-model="partnership9.gerenGudong.suoyouquanBi" maxlength="3" :placeholder="$t('registerOpenAccount.openAccount.common.info.ownership')" /> <!-- $i18n - 所有权百分比 -->
                        <div class="icon-percent" style="left: 95.8%;">%</div>
                      </el-form-item>
                      <el-form-item prop="gerenGudong.zhiwei44">
                        <el-input v-model="partnership9.gerenGudong.zhiwei44" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.position.post')" /> <!-- $i18n - 职位 -->
                      </el-form-item>
                      <div class="block">
                        <el-form-item prop="gerenGudong.birth">
                          <el-date-picker
                            v-model="partnership9.gerenGudong.birth"
                            value-format="yyyy-MM-dd"
                            type="date"
                            :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                          /> <!-- $i18n - 出生日期 -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 居住地址（非办公地址） -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title2') }}</h3> <!-- $i18n - 居住地址（非办公地址） -->
                    </div>
                    <div class="formNapeContent">
                      <div>
                        <el-form-item prop="juzhudizhi.state1">
                          <el-select v-model="partnership9.juzhudizhi.state1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.dizhi">
                          <el-input
                            v-model="partnership9.juzhudizhi.dizhi"
                            maxlength="64"
                            :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                          /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                        </el-form-item>
                        <el-form-item>
                          <el-input v-model="partnership9.juzhudizhi.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.chengshi">
                          <el-input v-model="partnership9.juzhudizhi.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.province">
                          <el-input v-model="partnership9.juzhudizhi.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.youbian">
                          <el-input v-model="partnership9.juzhudizhi.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                        </el-form-item>
                      </div>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.isguojiButong}"
                            @click="partnership9.juzhudizhi.isguojiButong=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.isguojiButong}"
                            @click="partnership9.juzhudizhi.isguojiButong=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentNationalities1') }}</li> <!-- $i18n - 您的国籍是否与上方所选国家不同？ -->
                        </ul>
                      </div>
                      <el-form-item v-show="partnership9.juzhudizhi.isguojiButong" prop="juzhudizhi.guoji">
                        <el-select v-model="partnership9.juzhudizhi.guoji" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.ischushengguoButong}"
                            @click="partnership9.juzhudizhi.ischushengguoButong=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.ischushengguoButong}"
                            @click="partnership9.juzhudizhi.ischushengguoButong=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentBirthStateDescribe1') }}</li> <!-- $i18n - 您的出生国是否与上方所选国家不同? -->
                        </ul>
                      </div>
                      <el-form-item
                        v-show="partnership9.juzhudizhi.ischushengguoButong"
                        prop="juzhudizhi.chushengguo"
                      >
                        <el-select v-model="partnership9.juzhudizhi.chushengguo" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.butongBirthState')"> <!-- $i18n - 出生国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.isMeiguoNasuiren}"
                            @click="partnership9.juzhudizhi.isMeiguoNasuiren=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.isMeiguoNasuiren}"
                            @click="partnership9.juzhudizhi.isMeiguoNasuiren=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.taxpayer') }}</li> <!-- $i18n - 该个人是美国纳税人吗? -->
                        </ul>
                      </div>
                    </div>
                  </div>
                  <!-- 身份证明 -->
                  <div class="formNape">
                    <div v-show="!partnership9.juzhudizhi.isMeiguoNasuiren" class="formNape">
                      <div class="formNapeTitle">
                        <h3>{{ $t('registerOpenAccount.openAccount.common.identity.title') }}</h3> <!-- $i18n - 身份证明 -->
                      </div>
                      <div class="formNapeContent">
                        <el-form-item prop="identification.SFsignGuojia">
                          <el-select v-model="partnership9.identification.SFsignGuojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.identity.state')"> <!-- $i18n - v -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="identification.SFzhengjianleixing">
                          <el-select
                            v-model="partnership9.identification.SFzhengjianleixing"
                            :no-match-text="$t('account.manage.no_data')"
                            :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"
                          > <!-- $i18n - 身份证类型 -->
                            <dictionary dict-code="cardType" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="identification.SFzhengjianhao">
                          <el-input v-model="partnership9.identification.SFzhengjianhao" maxlength="63" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 税务居住地 -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                    </div>
                    <div
                      v-for="(item,index) in partnership9.placeAbode"
                      :key="index"
                      class="formNapeContent"
                    >
                      <el-form-item :prop="'placeAbode.'+index+'.SWstate3'" :rules="rules.SWstate3">
                        <el-select v-model="item.SWstate3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item
                        :prop="'placeAbode.'+index+'.IDNumber'"
                        :rules="rules.IDNumber"
                      >
                        <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: !item.isSuiwuNO}"
                            @click="item.isSuiwuNO=false"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: item.isSuiwuNO}"
                            @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.IDNumber')"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                        </ul>
                      </div>
                      <div v-show="item.isSuiwuNO" class="textcontent1">
                        <transition name="fade">
                          <div v-if="item.isSuiwuNO" class="textcontent1 fade">
                            <span class="zhuce3ji">
                              {{ $t('registerOpenAccount.openAccount.common.taxes.describe') }}
                            </span> <!-- $i18n - 美国国税局规则要求我们收集您所有纳税居住地的纳税 ... -->
                            <el-form-item id="radioInline" :prop="'placeAbode.'+index+'.suibie'" :rules="rules.suibie">
                              <el-radio-group v-model="item.suibie">
                                <div v-for="(item1,index1) in item.taxNumList" :key="index1">
                                  <el-radio clase="zhuce3ji" :label="item1.dictValue">{{ item1.dictDest }}</el-radio>
                                </div>
                                <br>
                                <el-radio class="zhuce3ji" label="qita">
                                  {{ `${$t('registerOpenAccount.openAccount.common.taxes.else')}` }} <!-- $i18n - 其他 -->
                                  <input v-model="item.suiwuQita" maxlength="200" type="text" :placeholder="$t('registerOpenAccount.common.formPrompt.enter')"> <!-- $i18n - 请输入 -->
                                </el-radio>
                                <br>
                              </el-radio-group>
                            </el-form-item>
                          </div>
                        </transition>
                      </div>
                    </div>
                    <div
                      class="addBtn"
                      style="    width: 960px;
                                   height: 41px;margin-top:10px;"
                    >
                      <div v-show="partnership9.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加税务居住地 -->
                      <div v-show="partnership9.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                    </div>
                  </div>
                </div>
                <!-- 保存,关闭 -->
                <div class="isBtn right1">
                  <ul>
                    <li class="isyes Motaiteshuchuli" @click="save('partnership9',1)">{{ $t('registerOpenAccount.common.btn.save') }}</li> <!-- $i18n - 保存 -->
                    <li class="isno Motaiteshuchuli1" @click="shut(1)">{{ $t('registerOpenAccount.common.btn.close') }}</li> <!-- $i18n - 关闭 -->
                  </ul>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>
      <!-- 模态框  添加股东25-->
      <div v-if="partnership9.ifmodalBox1" class="modalBox">
        <div class="contentBOX">
          <div class="contentBOXdignwei contentBOXMoL">
            <el-form
              ref="partnership9"
              status-icon
              :rules="rules"
              :model="partnership9"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="biaoTitle">{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.title25') }}</div> <!-- $i18n - 添加25%或以上所有人 -->
                <!-- 人-选择 -->
                <div class="formNape">
                  <div class="formNapeContent">
                    <p>{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.describe1') }}</p> <!-- $i18n - 提供准确的信息，因为我们须使用数 ... -->
                    <el-form-item v-if="partnership9.ismodalBox2Cot" prop="xuanzeren1.beneficiaryName">
                      <el-select
                        v-model="partnership9.xuanzeren1.beneficiaryName"
                        :no-match-text="$t('account.manage.no_data')"
                        :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"
                      > <!-- $i18n - 请选择 -->
                        <el-option
                          :label="$t('registerOpenAccount.openAccount.common.beneficial.modal.notListed')"
                          value="NotListed"
                          @click.native="partnership9.ismodalBox2Cot=false"
                        /> <!-- $i18n - 未列出 -->
                      </el-select>
                    </el-form-item>
                    <!-- <el-form-item prop="xuanzeren1.zhiwei" v-if="partnership9.ismodalBox1Cot">
                      <el-input maxlength="18"  v-model="partnership9.xuanzeren1.zhiwei" placeholder="职位"></el-input>
                    </el-form-item>-->
                  </div>
                </div>
                <div v-if="!partnership9.ismodalBox2Cot">
                  <!-- 个人信息 -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.info.title') }}</h3> <!-- $i18n - 个人信息 -->
                    </div>
                    <div class="formNapeContent">
                      <el-form-item prop="gerenGudong.companyName">
                        <el-select v-model="partnership9.gerenGudong.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                          <dictionary dict-code="appellationType" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="gerenGudong.call">
                        <el-input v-model="partnership9.gerenGudong.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.middle">
                        <el-input v-model="partnership9.gerenGudong.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 中间名,不适用于中国客户 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.surname">
                        <el-input v-model="partnership9.gerenGudong.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.suffix">
                        <el-select v-model="partnership9.gerenGudong.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                          <el-option label="Jr " value="Jr" />
                          <el-option label="Sr " value="Sr" />
                          <el-option label="I " value="I" />
                          <el-option label="II " value="II" />
                          <el-option label="III " value="III" />
                          <el-option label="IV " value="IV" />
                          <el-option label="V " value="V" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="gerenGudong.suoyouquanBi" class="percentage">
                        <el-input v-model="partnership9.gerenGudong.suoyouquanBi" maxlength="3" :placeholder="$t('registerOpenAccount.openAccount.common.info.ownership')" /> <!-- $i18n - 所有权百分比 -->
                        <div class="icon-percent" style="left: 95.8%;">%</div>
                      </el-form-item>
                      <div class="block">
                        <el-form-item prop="gerenGudong.birth">
                          <el-date-picker
                            v-model="partnership9.gerenGudong.birth"
                            value-format="yyyy-MM-dd"
                            type="date"
                            :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                          /> <!-- $i18n - 出生日期 -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 居住地址（非办公地址） -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title2') }}</h3> <!-- $i18n - 居住地址（非办公地址） -->
                    </div>
                    <div class="formNapeContent">
                      <div>
                        <el-form-item prop="juzhudizhi.state1">
                          <el-select v-model="partnership9.juzhudizhi.state1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.dizhi">
                          <el-input
                            v-model="partnership9.juzhudizhi.dizhi"
                            maxlength="64"
                            :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                          /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                        </el-form-item>
                        <el-form-item>
                          <el-input v-model="partnership9.juzhudizhi.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.chengshi">
                          <el-input v-model="partnership9.juzhudizhi.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.province">
                          <el-input v-model="partnership9.juzhudizhi.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.youbian">
                          <el-input v-model="partnership9.juzhudizhi.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                        </el-form-item>
                      </div>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.isguojiButong}"
                            @click="partnership9.juzhudizhi.isguojiButong=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.isguojiButong}"
                            @click="partnership9.juzhudizhi.isguojiButong=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentNationalities1') }}</li> <!-- $i18n - 您的国籍是否与上方所选国家不同？ -->
                        </ul>
                      </div>
                      <el-form-item v-show="partnership9.juzhudizhi.isguojiButong" prop="juzhudizhi.guoji">
                        <el-select v-model="partnership9.juzhudizhi.guoji" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.ischushengguoButong}"
                            @click="partnership9.juzhudizhi.ischushengguoButong=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.ischushengguoButong}"
                            @click="partnership9.juzhudizhi.ischushengguoButong=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentBirthStateDescribe1') }}</li> <!-- $i18n - 您的出生国是否与上方所选国家不同? -->
                        </ul>
                      </div>
                      <el-form-item
                        v-show="partnership9.juzhudizhi.ischushengguoButong"
                        prop="juzhudizhi.chushengguo"
                      >
                        <el-select v-model="partnership9.juzhudizhi.chushengguo" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.butongBirthState')"> <!-- $i18n - 出生国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.isMeiguoNasuiren}"
                            @click="partnership9.juzhudizhi.isMeiguoNasuiren=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.isMeiguoNasuiren}"
                            @click="partnership9.juzhudizhi.isMeiguoNasuiren=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.taxpayer') }}</li> <!-- $i18n - 该个人是美国纳税人吗? -->
                        </ul>
                      </div>
                    </div>
                  </div>
                  <!-- 身份证明 -->
                  <div class="formNape">
                    <div v-show="!partnership9.juzhudizhi.isMeiguoNasuiren" class="formNape">
                      <div class="formNapeTitle">
                        <h3>{{ $t('registerOpenAccount.openAccount.common.identity.title') }}</h3> <!-- $i18n - 身份证明 -->
                      </div>
                      <div class="formNapeContent">
                        <el-form-item prop="identification.SFsignGuojia">
                          <el-select v-model="partnership9.identification.SFsignGuojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.identity.state')"> <!-- $i18n - 签发国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="identification.SFzhengjianleixing">
                          <el-select
                            v-model="partnership9.identification.SFzhengjianleixing"
                            :no-match-text="$t('account.manage.no_data')"
                            :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"
                          > <!-- $i18n - 身份证类型 -->
                            <dictionary dict-code="cardType" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="identification.SFzhengjianhao">
                          <el-input v-model="partnership9.identification.SFzhengjianhao" maxlength="63" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 税务居住地 -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                    </div>
                    <div
                      v-for="(item,index) in partnership9.placeAbode"
                      :key="index"
                      class="formNapeContent"
                    >
                      <el-form-item :prop="'placeAbode.'+index+'.SWstate3'" :rules="rules.SWstate3">
                        <el-select v-model="item.SWstate3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item
                        :prop="'placeAbode.'+index+'.IDNumber'"
                        :rules="rules.IDNumber"
                      >
                        <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: !item.isSuiwuNO}"
                            @click="item.isSuiwuNO=false"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: item.isSuiwuNO}"
                            @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.IDNumber')"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                        </ul>
                      </div>
                      <div v-show="item.isSuiwuNO" class="textcontent1">
                        <transition name="fade">
                          <div v-if="item.isSuiwuNO" class="textcontent1 fade">
                            <span class="zhuce3ji">
                              {{ $t('registerOpenAccount.openAccount.common.taxes.describe') }}
                            </span> <!-- $i18n - 美国国税局规则要求我们收集您所有纳税居 ... -->
                            <el-form-item id="radioInline" :prop="'placeAbode.'+index+'.suibie'" :rules="rules.suibie">
                              <el-radio-group v-model="item.suibie">
                                <div v-for="(item1,index1) in item.taxNumList" :key="index1">
                                  <el-radio clase="zhuce3ji" :label="item1.dictValue">{{ item1.dictDest }}</el-radio>
                                </div>
                                <br>
                                <el-radio class="zhuce3ji" label="qita">
                                  {{ `${$t('registerOpenAccount.openAccount.common.taxes.else')}` }} <!-- $i18n - 其他 -->
                                  <input v-model="item.suiwuQita" maxlength="200" type="text" :placeholder="$t('registerOpenAccount.common.formPrompt.enter')"> <!-- $i18n - 请输入 -->
                                </el-radio>
                                <br>
                              </el-radio-group>
                            </el-form-item>
                          </div>
                        </transition>
                      </div>
                    </div>
                    <div
                      class="addBtn"
                      style="    width: 960px;
                                   height: 41px;"
                    >
                      <div v-show="partnership9.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加税务居住地 -->
                      <div v-show="partnership9.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                    </div>
                  </div>
                </div>
                <!-- 保存,关闭 -->
                <div class="isBtn right1">
                  <ul>
                    <li class="isyes Motaiteshuchuli" @click="save('partnership9',2)">{{ $t('registerOpenAccount.common.btn.save') }}</li> <!-- $i18n - 保存 -->
                    <li class="isno Motaiteshuchuli1" @click="shut(2)">{{ $t('registerOpenAccount.common.btn.close') }}</li> <!-- $i18n - 关闭 -->
                  </ul>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>
      <!-- 模态框 1 添加股东10-->
      <div v-if="partnership9.ifmodalBox2" class="modalBox">
        <div class="contentBOX">
          <div class="contentBOXdignwei contentBOXMoL">
            <el-form
              ref="partnership9"
              status-icon
              :rules="rules"
              :model="partnership9"
              label-width="0px"
              class="demo-ruleForm"
            >
              <div class="formContent">
                <div class="biaoTitle">{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.title10') }}</div> <!-- $i18n - 添加10%或以上所有人 -->
                <!-- 人-选择 -->
                <div class="formNape">
                  <div class="formNapeContent">
                    <p>{{ $t('registerOpenAccount.openAccount.common.beneficial.modal.describe1') }}</p> <!-- $i18n - 提供准确的信息，因为我们须使用数据 ... -->
                    <el-form-item v-if="partnership9.ismodalBox3Cot" prop="xuanzeren1.beneficiaryName">
                      <el-select
                        v-model="partnership9.xuanzeren1.beneficiaryName"
                        :no-match-text="$t('account.manage.no_data')"
                        :placeholder="$t('registerOpenAccount.common.formPrompt.pleaseChoose')"
                      > <!-- $i18n - 请选择 ... -->
                        <el-option
                          :label="$t('registerOpenAccount.openAccount.common.beneficial.modal.notListed')"
                          value="NotListed"
                          @click.native="partnership9.ismodalBox3Cot=false"
                        /> <!-- $i18n - 未列出 ... -->
                      </el-select>
                    </el-form-item>
                    <!-- <el-form-item prop="xuanzeren1.zhiwei" v-if="partnership9.ismodalBox1Cot">
                      <el-input maxlength="18"  v-model="partnership9.xuanzeren1.zhiwei" placeholder="职位"></el-input>
                    </el-form-item>-->
                  </div>
                </div>
                <div v-if="!partnership9.ismodalBox3Cot">
                  <!-- 个人信息 -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.info.title') }}</h3> <!-- $i18n - 个人信息 -->
                    </div>
                    <div class="formNapeContent">
                      <el-form-item prop="gerenGudong.companyName">
                        <el-select v-model="partnership9.gerenGudong.companyName" :no-match-text="$t('account.manage.no_data')" :placeholder="$t('registerOpenAccount.openAccount.common.info.appellation')"> <!-- $i18n - 称呼 -->
                          <dictionary dict-code="appellationType" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="gerenGudong.call">
                        <el-input v-model="partnership9.gerenGudong.call" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.firstName')" /> <!-- $i18n - 姓 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.middle">
                        <el-input v-model="partnership9.gerenGudong.middle" maxlength="50" :placeholder="$t('registerOpenAccount.openAccount.common.info.middleName')" /> <!-- $i18n - 中间名,不适用于中国客户 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.surname">
                        <el-input v-model="partnership9.gerenGudong.surname" maxlength="18" :placeholder="$t('registerOpenAccount.openAccount.common.info.lastName')" /> <!-- $i18n - 名 -->
                      </el-form-item>
                      <el-form-item prop="gerenGudong.suffix">
                        <el-select v-model="partnership9.gerenGudong.suffix" :no-match-text="$t('account.manage.no_data')" clearable :placeholder="$t('registerOpenAccount.openAccount.common.info.suffix')"> <!-- $i18n - 后缀（不适用于中国客户） -->
                          <el-option label="Jr " value="Jr" />
                          <el-option label="Sr " value="Sr" />
                          <el-option label="I " value="I" />
                          <el-option label="II " value="II" />
                          <el-option label="III " value="III" />
                          <el-option label="IV " value="IV" />
                          <el-option label="V " value="V" />
                        </el-select>
                      </el-form-item>
                      <el-form-item prop="gerenGudong.suoyouquanBi" class="percentage">
                        <el-input v-model="partnership9.gerenGudong.suoyouquanBi" maxlength="30" :placeholder="$t('registerOpenAccount.openAccount.common.info.ownership')" /> <!-- $i18n - 所有权百分比 -->
                        <div class="icon-percent" style="left: 95.8%;">%</div>
                      </el-form-item>
                      <div class="block">
                        <el-form-item prop="gerenGudong.birth">
                          <el-date-picker
                            v-model="partnership9.gerenGudong.birth"
                            value-format="yyyy-MM-dd"
                            type="date"
                            :placeholder="$t('registerOpenAccount.openAccount.common.info.birthday')"
                          /> <!-- $i18n - 出生日期 -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 居住地址（非办公地址） -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.addre.title2') }}</h3> <!-- $i18n - 居住地址（非办公地址） -->
                    </div>
                    <div class="formNapeContent">
                      <div>
                        <el-form-item prop="juzhudizhi.state1">
                          <el-select v-model="partnership9.juzhudizhi.state1" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.dizhi">
                          <el-input
                            v-model="partnership9.juzhudizhi.dizhi"
                            maxlength="64"
                            :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteOne')"
                          /> <!-- $i18n - 地址 ( 如果空间不足，请转下行 ) -->
                        </el-form-item>
                        <el-form-item>
                          <el-input v-model="partnership9.juzhudizhi.dizhi1" maxlength="64" :placeholder="$t('registerOpenAccount.openAccount.common.addre.siteTwo')" /> <!-- $i18n - ( 接上行) 房间号, 楼层 公寓等 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.chengshi">
                          <el-input v-model="partnership9.juzhudizhi.chengshi" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.city')" /> <!-- $i18n - 城市 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.province">
                          <el-input v-model="partnership9.juzhudizhi.province" maxlength="24" :placeholder="$t('registerOpenAccount.openAccount.common.addre.province')" /> <!-- $i18n - 省/市 -->
                        </el-form-item>
                        <el-form-item prop="juzhudizhi.youbian">
                          <el-input v-model="partnership9.juzhudizhi.youbian" maxlength="20" :placeholder="$t('registerOpenAccount.openAccount.common.addre.postalcode')" /> <!-- $i18n - 邮政编码 -->
                        </el-form-item>
                      </div>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.isguojiButong}"
                            @click="partnership9.juzhudizhi.isguojiButong=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.isguojiButong}"
                            @click="partnership9.juzhudizhi.isguojiButong=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentNationalities1') }}</li> <!-- $i18n - 您的国籍是否与上方所选国家不同？ -->
                        </ul>
                      </div>
                      <el-form-item v-show="partnership9.juzhudizhi.isguojiButong" prop="juzhudizhi.guoji">
                        <el-select v-model="partnership9.juzhudizhi.guoji" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.ischushengguoButong}"
                            @click="partnership9.juzhudizhi.ischushengguoButong=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.ischushengguoButong}"
                            @click="partnership9.juzhudizhi.ischushengguoButong=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.differentBirthStateDescribe1') }}</li> <!-- $i18n - 您的出生国是否与上方所选国家不同? -->
                        </ul>
                      </div>
                      <el-form-item
                        v-show="partnership9.juzhudizhi.ischushengguoButong"
                        prop="juzhudizhi.chushengguo"
                      >
                        <el-select v-model="partnership9.juzhudizhi.chushengguo" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.addre.butongBirthState')"> <!-- $i18n - 出生国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: partnership9.juzhudizhi.isMeiguoNasuiren}"
                            @click="partnership9.juzhudizhi.isMeiguoNasuiren=true"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: !partnership9.juzhudizhi.isMeiguoNasuiren}"
                            @click="partnership9.juzhudizhi.isMeiguoNasuiren=false"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext">{{ $t('registerOpenAccount.openAccount.common.addre.taxpayer') }}</li> <!-- $i18n - 该个人是美国纳税人吗? -->
                        </ul>
                      </div>
                    </div>
                  </div>
                  <!-- 身份证明 -->
                  <div class="formNape">
                    <div v-show="!partnership9.juzhudizhi.isMeiguoNasuiren" class="formNape">
                      <div class="formNapeTitle">
                        <h3>{{ $t('registerOpenAccount.openAccount.common.identity.title') }}</h3> <!-- $i18n - 身份证明 -->
                      </div>
                      <div class="formNapeContent">
                        <el-form-item prop="identification.SFsignGuojia">
                          <el-select v-model="partnership9.identification.SFsignGuojia" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.identity.state')"> <!-- $i18n - 签发国家 -->
                            <dictionary dict-code="country" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="identification.SFzhengjianleixing">
                          <el-select
                            v-model="partnership9.identification.SFzhengjianleixing"
                            :no-match-text="$t('account.manage.no_data')"
                            :placeholder="$t('registerOpenAccount.openAccount.common.identity.cardType')"
                          > <!-- $i18n - 身份证类型 -->
                            <dictionary dict-code="cardType" />
                          </el-select>
                        </el-form-item>
                        <el-form-item prop="identification.SFzhengjianhao">
                          <el-input v-model="partnership9.identification.SFzhengjianhao" maxlength="63" :placeholder="$t('registerOpenAccount.openAccount.common.identity.number')" /> <!-- $i18n - 证件号码 -->
                        </el-form-item>
                      </div>
                    </div>
                  </div>
                  <!-- 税务居住地 -->
                  <div class="formNape">
                    <div class="formNapeTitle">
                      <h3>{{ $t('registerOpenAccount.openAccount.common.taxes.title') }}</h3> <!-- $i18n - 税务居住地 -->
                    </div>
                    <div
                      v-for="(item,index) in partnership9.placeAbode"
                      :key="index"
                      class="formNapeContent"
                    >
                      <el-form-item :prop="'placeAbode.'+index+'.SWstate3'" :rules="rules.SWstate3">
                        <el-select v-model="item.SWstate3" :no-match-text="$t('account.manage.no_data')" filterable :placeholder="$t('registerOpenAccount.openAccount.common.taxes.state')"> <!-- $i18n - 国家 -->
                          <dictionary dict-code="country" />
                        </el-select>
                      </el-form-item>
                      <el-form-item
                        :prop="'placeAbode.'+index+'.IDNumber'"
                        :rules="rules.IDNumber"
                      >
                        <el-input v-model="item.IDNumber = item.isSuiwuNO?'N.A.':item.IDNumber1" maxlength="32" :disabled="item.isSuiwuNO" :placeholder="$t('registerOpenAccount.openAccount.common.taxes.Number')" /> <!-- $i18n - 证件号码 -->
                      </el-form-item>
                      <div class="isBtn">
                        <ul>
                          <li
                            class="isyes"
                            :class="{ dianji: !item.isSuiwuNO}"
                            @click="item.isSuiwuNO=false"
                          >{{ $t('registerOpenAccount.common.btn.yes') }}</li> <!-- $i18n - 是 -->
                          <li
                            class="isno"
                            :class="{ dianji: item.isSuiwuNO}"
                            @click="item.isSuiwuNO=true && taxIsBtn('placeAbode.'+index+'.IDNumber')"
                          >{{ $t('registerOpenAccount.common.btn.no') }}</li> <!-- $i18n - 否 -->
                          <li class="istext zhuce2ji">{{ $t('registerOpenAccount.openAccount.common.taxes.isIdentifyNumber') }}</li> <!-- $i18n - 您有没有纳税识别号？ -->
                        </ul>
                      </div>
                      <div v-show="item.isSuiwuNO" class="textcontent1">
                        <transition name="fade">
                          <div v-if="item.isSuiwuNO" class="textcontent1 fade">
                            <span class="zhuce3ji">{{ $t('registerOpenAccount.openAccount.common.taxes.describe') }}
                            </span> <!-- $i18n - 美国国税局规则要求我们收集您所有纳税居住地的纳 ... -->
                            <el-form-item id="radioInline" :prop="'placeAbode.'+index+'.suibie'" :rules="rules.suibie">
                              <el-radio-group v-model="item.suibie">
                                <div v-for="(item1,index1) in item.taxNumList" :key="index1">
                                  <el-radio clase="zhuce3ji" :label="item1.dictValue">{{ item1.dictDest }}</el-radio>
                                </div>
                                <br>
                                <el-radio class="zhuce3ji" label="qita">
                                  {{ `${$t('registerOpenAccount.openAccount.common.taxes.else')}` }} <!-- $i18n - 其他 -->
                                  <input v-model="item.suiwuQita" maxlength="200" type="text" :placeholder="$t('registerOpenAccount.common.formPrompt.enter')"> <!-- $i18n - 请输入 -->
                                </el-radio>
                                <br>
                              </el-radio-group>
                            </el-form-item>
                          </div>
                        </transition>
                      </div>
                    </div>
                    <div
                      class="addBtn"
                      style="    width: 960px;
                                   height: 41px;"
                    >
                      <div v-show="partnership9.placeAbode.length !== 5" class="add" @click="addelement3()">{{ $t('registerOpenAccount.openAccount.common.taxes.addTaxResidence') }}</div> <!-- $i18n - 添加其他税务居住地 -->
                      <div v-show="partnership9.placeAbode.length !== 1" class="cancel" @click="removeelement3()">{{ $t('registerOpenAccount.common.btn.delete') }}</div> <!-- $i18n - 删除 -->
                    </div>
                  </div>
                </div>
                <!-- 保存,关闭 -->
                <div class="isBtn right1">
                  <ul>
                    <li class="isyes Motaiteshuchuli" @click="save('partnership9',3)">{{ $t('registerOpenAccount.common.btn.save') }}</li> <!-- $i18n - 保存 -->
                    <li class="isno Motaiteshuchuli1" @click="shut(3)">{{ $t('registerOpenAccount.common.btn.close') }}</li> <!-- $i18n - 关闭 -->
                  </ul>
                </div>
              </div>
            </el-form>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import dictionary from '../../../../../components/dictionary/dictionary';
export default {
  components: {
    dictionary
  },
  data() {
    // 非中文验证1
    var notChinese1 = (rule, value, callback) => {
      if (this.partnership9.ifmodalBox1 === true || this.partnership9.ifmodalBox2 === true || this.partnership9.ifmodalBox) {
        this.$verify.notChinese1(rule, value, callback);
      } else {
        callback();
      }
    };
    // 个人信息 - 个人
    var validateGeren1 = (rule, value, callback) => {
      const mate = this.partnership9.ifmodalBox1 === true || this.partnership9.ifmodalBox2 === true || this.partnership9.ifmodalBox;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 不同国籍
    var validateGeren2 = (rule, value, callback) => {
      const mate = this.partnership9.juzhudizhi.isguojiButong;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    //  不同出生国
    var validateGeren3 = (rule, value, callback) => {
      const mate = this.partnership9.juzhudizhi.ischushengguoButong;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    //  是否美国纳税人
    var validateGeren4 = (rule, value, callback) => {
      const mate = this.partnership9.juzhudizhi.isMeiguoNasuiren === false;
      this.$verify.stairIf(rule, value, mate, callback);
    };
    // 国家-税务居住地-动态渲染
    var validateZJhaoma = (rule, value, callback) => {
      const _this = this;
      if (value === '') {
        callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
      } else {
        for (let i = 0; i < this.partnership9.placeAbode.length; i++) {
          this.$verify.State(this.partnership9.placeAbode[i].SWstate3, this.partnership9.placeAbode[i].taxNumList, this.partnership9.taxNumList1, _this, this.lang);
        }
        callback();
      }
    };
    // 纳税识别号
    var validateGeren5 = (rule, value, callback) => {
      const array1 = this.partnership9.placeAbode;
      this.$verify.SWnasuihao(rule, value, array1, callback);
    };
    // 出生日期
    var validateBirth = (rule, value, callback) => {
      this.$verify.Birth(rule, value, callback);
    };
    // 签名
    var validateSignature = (rule, value, callback) => {
      if (this.partnership9.ifmodalBox1 === false && this.partnership9.ifmodalBox2 === false && this.partnership9.ifmodalBox === false) {
        if (this.partnership9.shengming.signatureZhanshi === this.partnership9.shengming.signature) {
          callback();
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.signatureHint1'))); // $i18n - 签名不一致
        }
      } else {
        callback();
      }
    };
    // 所有权
    var validateGeren7 = (rule, value, callback) => {
      const sumAddValue = this.ownershipSum + Number(value);
      const guize = /^[0-9]*$/;
      if (this.partnership9.ifmodalBox1 === true) {
        if (value !== '') {
          if (guize.test(value)) {
            if (value >= 25 && value <= 100) {
              if (sumAddValue <= 100) {
                callback();
              } else {
                callback(new Error(`${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1a')}${this.ownershipSum}${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1b')}`)); // $i18n - 总所有权不得超过100%，您已添加 // $i18n - %的股权
              }
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian2a'))); // $i18n - 请输入数字 比重须>=25%,且必须<=100%
            }
          } else {
            callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.illegality1'))); // $i18n - 请不要输入非法字符, 必须为数字
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else if (this.partnership9.ifmodalBox2 === true) {
        if (value !== '') {
          if (guize.test(value)) {
            if (value >= 10 && value < 25) {
              if (sumAddValue <= 100) {
                callback();
              } else {
                callback(new Error(`${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1a')}${this.ownershipSum}${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1b')}`)); // $i18n - 总所有权不得超过100%，您已添加 // $i18n - %的股权
              }
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian3a'))); // $i18n - 请输入数字 比重须>=10%,且必须<25%
            }
          } else {
            callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.illegality1'))); // $i18n - 请不要输入非法字符, 必须为数字
          }
        } else {
          callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.enter'))); // $i18n - 请输入
        }
      } else if (this.partnership9.ifmodalBox === true) {
        if (value !== '') {
          if (guize.test(value)) {
            if (value >= 10 && value <= 100) {
              if (sumAddValue <= 100) {
                callback();
              } else {
                callback(new Error(`${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1a')}${this.ownershipSum}${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1b')}`)); // $i18n - 总所有权不得超过100%，您已添加 // $i18n - %的股权
              }
            } else {
              callback(new Error(this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian4a'))); // $i18n - 请输入数字 比重须>=10%,且必须<=100%
            }
          } else {
            callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.illegality1'))); // $i18n - 请不要输入非法字符, 必须为数字
          }
        } else {
          callback();
        }
      }
    };
    // 数字正则验证
    var shuziZhengze = (rule, value, callback) => {
      this.$verify.notChinese(rule, value, callback);
    };
    // 职位非数字判断
    var validPosition = (rule, value, callback) => {
      this.$verify.NotNumber(rule, value, callback);
    };
    // 税务号
    var theTaxNo = (rule, value, callback) => {
      if (this.partnership9.juzhudizhi.isMeiguoNasuiren === false) {
        if (value !== 'N.A.') {
          if (this.partnership9.identification.SFsignGuojia !== 'CN' ||
              this.partnership9.identification.SFzhengjianleixing !== 'idCard' ||
              this.partnership9.identification.SFzhengjianhao === '') {
            this.$verify.notChinese(rule, value, callback);
          } else {
            this.partnership9.placeAbode.forEach(item => {
              if (item.IDNumber === value) {
                if (item.SWstate3 === 'CN') {
                  if (this.partnership9.identification.SFzhengjianhao === value) {
                    this.$verify.notChinese(rule, value, callback);
                  } else {
                    callback(new Error(this.$i18n.t('registerOpenAccount.common.formPrompt.identificationPaper'))); // $i18n - 纳税人识别号码应与身份证号码一致。
                  }
                } else {
                  this.$verify.notChinese(rule, value, callback);
                }
              }
            });
          }
        } else {
          this.$verify.notChinese(rule, value, callback);
        }
      } else {
        this.$verify.notChinese(rule, value, callback);
      }
      this.$verify.notChinese(rule, value, callback);
    };
    return {
      lang: this.$Cookies.get('locale'),
      ownershipSum: 0, // 总所有权
      isTokenHint: false,
      partnership9: {
        ifmodalBox: false, // 经营人 模态
        ismodalBox1Cot: true, // 新增内容显示隐藏
        ifmodalBox1: false, // 添加股东25 模态
        ismodalBox2Cot: true, // 添加股东25新增内容显示
        ifmodalBox2: false, // 添加股东10 模态
        ismodalBox3Cot: true, // 添加股东10新增内容显示

        isbiaodanjinyong1: true, // 添加股东25 radio 禁用
        isbiaodanjinyong2: true, // 添加股东10 radio 禁用

        isJingyinren: false, // 表格显示隐藏
        isbiaoge25: false, // 表格显示隐藏
        isbiaoge10: false, // 表格显示隐藏
        isbottomBox: true, // 保存&继续按钮 显示隐藏

        personalTypeJ: null, // 个人类型 经营负责人
        personalType25: null, // 个人类型 25股东
        personalType10: null, // 个人类型 10股东
        personalTypeGR: null, // 个人类型 个人类型
        personalTypeSQR: null, // 个人类型 被授权人
        addressType: null, // 地址类型 --> 居住地址

        authorizedPerson: null, // 被授权人信息
        // 经营人表格
        tableTbady: [],
        suoyouquanZongshu: '',
        // 25 表格
        tableTbady25: [],
        suoyouquanZongshu1: '',
        // 10 表格
        tableTbady10: [],
        suoyouquanZongshu2: '',
        // 认证
        rezheng: {
          xing: '',
          zhongjianming: '',
          ming: '',
          suffix: '',
          companyPosition: '',
          companyName: '',
          location: '',
          chenshi: '',
          shengshi: '',
          postcode: '',
          state: '',
          state1: ''
        },
        isOne25shouyi: '',
        isOne25shouyi1: '',
        shengming: {
          zhanghuName: '',
          riqi: '',
          signatureZhanshi: '',
          signature: ''
        },

        // 模态框 10 25 经营负责人
        // 个人信息-个人
        xuanzeren1: {
          beneficiaryName: '',
          beneficiaryNameList: [],
          zhiwei: ''
        },
        gerenGudong: {
          companyName: '',
          call: '',
          middle: '',
          surname: '',
          suffix: '',
          birth: '',
          suoyouquanBi: '',
          issuoyouquanBi: false,
          zhiwei44: ''
        },
        // 居住地址-个人
        juzhudizhi: {
          state1: '',
          dizhi: '',
          dizhi1: '',
          chengshi: '',
          province: '',
          youbian: '',
          isguojiButong: false,
          guoji: '',
          ischushengguoButong: false,
          chushengguo: '',
          isMeiguoNasuiren: false
        },
        // 身份证明-个人
        identification: {
          SFsignGuojia: '',
          SFzhengjianleixing: '',
          SFzhengjianhao: ''
        },
        // 税务居住地-个人  税务居住地-公司
        placeAbode: [
          {
            SWstate3: '',
            IDNumber: '',
            IDNumber1: '',
            suibie: '',
            isSuiwuNO: false, // is 税务识别号
            taxNumList: null, // 页面上使用
            suiwuQita: ''
          }
        ],
        taxNumList1: null // 页面上使用-暂存
      },
      partnership9TS: null, // conpany9初始数据暂存 完成表单清空功能
      status: null, // 页面状态 第一次为null 第二次更新为非null
      formdata: {},
      rules: {
        // 经营人-验证
        // "addjingyinren.beneficiaryName": [
        //   { required: true, message: "请选择经营负责人", trigger: "change" }
        // ],
        // "addjingyinren.zhiwei": [
        //   { required: true, message: "请填写职位", trigger: "blur" }
        // ],
        // 10 股权验证
        // 选择受益人
        'xuanzeren1.beneficiaryName': [
          {
            required: true,
            message: this.$i18n.t('registerOpenAccount.common.formPrompt.pleaseChoose'),
            trigger: 'change' // $i18n - 请选择
          }
        ],
        'shengming.signature': [
          {
            validator: validateSignature,
            trigger: 'blur'
          }
        ],
        'xuanzeren1.zhiwei': [
          { validator: validPosition, trigger: 'blur' }
        ],
        // 个人信息
        'gerenGudong.companyName': [
          {
            validator: validateGeren1,
            trigger: 'change'
          }
        ],
        'gerenGudong.call': [
          {
            validator: validateGeren1,
            trigger: 'blur'
          }
        ],
        'gerenGudong.middle': [
          {
            validator: notChinese1,
            trigger: 'blur'
          }
        ],
        'gerenGudong.surname': [
          {
            validator: validateGeren1,
            trigger: 'blur'
          }
        ],
        'gerenGudong.suffix': [
          {
            validator: notChinese1,
            trigger: 'change'
          }
        ],
        'gerenGudong.suoyouquanBi': [
          {
            validator: validateGeren7,
            trigger: 'blur'
          }
        ],
        'gerenGudong.zhiwei44': [
          {
            validator: validPosition,
            trigger: 'blur'
          }
        ],
        'gerenGudong.birth': [
          {
            validator: validateBirth,
            trigger: 'change'
          }
        ],
        // 居住地址
        'juzhudizhi.state1': [
          {
            validator: validateGeren1,
            trigger: 'change'
          }
        ],
        'juzhudizhi.dizhi': [
          {
            validator: validateGeren1,
            trigger: 'blur'
          }
        ],
        'juzhudizhi.dizhi1': [
          {
            validator: validateGeren1,
            trigger: 'blur'
          }
        ],
        'juzhudizhi.chengshi': [
          {
            validator: validateGeren1,
            trigger: 'blur'
          }
        ],
        'juzhudizhi.province': [
          {
            validator: validateGeren1,
            trigger: 'blur'
          }
        ],
        'juzhudizhi.youbian': [
          {
            validator: shuziZhengze,
            trigger: 'blur'
          }
        ],
        'juzhudizhi.guoji': [
          {
            validator: validateGeren2,
            trigger: 'change'
          }
        ],
        'juzhudizhi.chushengguo': [
          {
            validator: validateGeren3,
            trigger: 'change'
          }
        ],
        // 身份证明
        'identification.SFsignGuojia': [
          {
            validator: validateGeren4,
            trigger: 'change'
          }
        ],
        'identification.SFzhengjianleixing': [
          {
            validator: validateGeren4,
            trigger: 'change'
          }
        ],
        'identification.SFzhengjianhao': [
          {
            validator: validateGeren4,
            trigger: 'blur'
          }
        ],
        // 税务居住地
        SWstate3: [
          {
            validator: validateZJhaoma,
            trigger: 'change'
          }
        ],
        IDNumber: [
          {
            validator: theTaxNo,
            trigger: 'blur'
          }
        ],
        suibie: [
          {
            validator: validateGeren5,
            trigger: 'change'
          }
        ]
      }
    };
  },
  watch: {
    partnership9: {
      handler(val, oldVal) {
        let zongBaifenbi1 = 0; // 25股权 计算
        let zongBaifenbi2 = 0; // 10股权 计算
        for (let i = 0; i < this.partnership9.tableTbady25.length; i++) {
          zongBaifenbi1 += parseInt(this.partnership9.tableTbady25[i].suoyouquan);
        }
        this.partnership9.suoyouquanZongshu1 = zongBaifenbi1;
        for (let i = 0; i < this.partnership9.tableTbady10.length; i++) {
          zongBaifenbi2 += parseInt(this.partnership9.tableTbady10[i].suoyouquan);
        }
        this.partnership9.suoyouquanZongshu2 = zongBaifenbi2;
        this.ownershipSum = zongBaifenbi1 + zongBaifenbi2;
        // 监听表格length长度
        if (this.partnership9.isbiaoge25) {
          this.partnership9.isOne25shouyi = '1';
          this.partnership9.isbiaodanjinyong1 = false;
        } else {
          this.partnership9.isOne25shouyi = '2';
          this.partnership9.isbiaodanjinyong1 = true;
        }
        if (this.partnership9.isbiaoge10) {
          this.partnership9.isOne25shouyi1 = '1';
          this.partnership9.isbiaodanjinyong2 = false;
        } else {
          this.partnership9.isOne25shouyi1 = '2';
          this.partnership9.isbiaodanjinyong2 = true;
        }
        // if (this.partnership9.tableTbady10.length) {

        // }
      },
      deep: true
    }
  },
  created() {
    this.personalType();
    this.taxIdentificationNumber();
    this.getAuthorizedPerson();
    this.addressType();
  },
  methods: {
    printPdf() {
      window.print(); // 调用浏览器的打印功能打印指定区域
    },
    // 获取第二页被授权人信息 authorizedPerson
    getAuthorizedPerson() {
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: 'authorizedPerson', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership9.authorizedPerson = res.object[0];
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
    // 获取用户已填入的信息
    accountParPersonal() { // 第八页信息填入-股东--纯展示
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: this.partnership9.personalTypeGR, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                if (res.object[i].prcentageOfOwnership >= 25) {
                  this.partnership9.isbiaoge25 = true;
                  this.partnership9.tableTbady25.push({
                    compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                    suoyouquan: res.object[i].prcentageOfOwnership,
                    username: res.object[i].prcentageOfOwnership,
                    id: res.object[i].id,
                    delBtn: false
                  });
                } else if (res.object[i].prcentageOfOwnership >= 10) {
                  this.partnership9.isbiaoge10 = true;
                  this.partnership9.tableTbady10.push({
                    compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                    suoyouquan: res.object[i].prcentageOfOwnership,
                    username: res.object[i].prcentageOfOwnership,
                    id: res.object[i].id,
                    delBtn: false
                  });
                }
              }
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
    personalTypeTwo(personalType) { // 经营负责人已列出处理
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType, personalTypeTwo: 'operationPrincipal', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              for (let i = 0; i < res.object.length; i++) {
                this.partnership9.isJingyinren = true;
                this.partnership9.tableTbady.push({
                  compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                  // suoyouquan: res.object[i].prcentageOfOwnership !== 0 ? res.object[i].prcentageOfOwnership : res.object[i].position,
                  suoyouquan: res.object[i].position || res.object[i].prcentageOfOwnership,
                  username: res.object[i].prcentageOfOwnership,
                  id: res.object[i].id,
                  delBtn: true
                });
              }
            }
          }
        })
        .catch(error => {
          this.$message.error('' + error + '');
        });
    },
    accountParPersonalTwo(personalType) { // 第9页信息填入-股东--可删除
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              if (personalType === 'operationPrincipal') {
                for (let i = 0; i < res.object.length; i++) {
                  this.partnership9.isJingyinren = true;
                  this.partnership9.tableTbady.push({
                    compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                    // suoyouquan: res.object[i].prcentageOfOwnership !== 0 ? res.object[i].prcentageOfOwnership : res.object[i].position,
                    suoyouquan: res.object[i].position || res.object[i].prcentageOfOwnership,
                    username: res.object[i].prcentageOfOwnership,
                    id: res.object[i].id,
                    delBtn: true
                  });
                  if (res.object[i].prcentageOfOwnership !== '') { // 25
                    if (res.object[i].prcentageOfOwnership >= 25) {
                      this.partnership9.isbiaoge25 = true;
                      this.partnership9.tableTbady25.push({
                        compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                        suoyouquan: res.object[i].prcentageOfOwnership,
                        username: res.object[i].prcentageOfOwnership,
                        id: res.object[i].id,
                        delBtn: true
                      });
                    } else if (res.object[i].prcentageOfOwnership >= 10) { // 10
                      this.partnership9.isbiaoge10 = true;
                      this.partnership9.tableTbady10.push({
                        compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                        suoyouquan: res.object[i].prcentageOfOwnership,
                        username: res.object[i].prcentageOfOwnership,
                        id: res.object[i].id,
                        delBtn: true
                      });
                    }
                  }
                }
              } else if (personalType === '25%AboveBeneficiary') {
                for (let i = 0; i < res.object.length; i++) {
                  this.partnership9.isbiaoge25 = true;
                  this.partnership9.tableTbady25.push({
                    compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                    suoyouquan: res.object[i].prcentageOfOwnership,
                    username: res.object[i].prcentageOfOwnership,
                    id: res.object[i].id,
                    delBtn: true
                  });
                }
              } else if (personalType === '10%AboveBeneficiary') {
                for (let i = 0; i < res.object.length; i++) {
                  this.partnership9.isbiaoge10 = true;
                  this.partnership9.tableTbady10.push({
                    compellation: res.object[i].lastName + ' ' + res.object[i].middleName + ' ' + res.object[i].firstName,
                    suoyouquan: res.object[i].prcentageOfOwnership,
                    username: res.object[i].prcentageOfOwnership,
                    id: res.object[i].id,
                    delBtn: true
                  });
                }
              }
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
    // 声明 签名
    getSignature() {
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: this.partnership9.personalTypeSQR, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership9.shengming.zhanghuName = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership9.shengming.riqi = this.$verify.NowDate3();
              this.partnership9.shengming.signatureZhanshi = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
              this.partnership9.shengming.signature = res.object[0].lastName + ' ' + `${res.object[0].middleName ? res.object[0].middleName + ' ' : res.object[0].middleName + ''}` + res.object[0].firstName;
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
    // 认证
    accountParAddress() {
      // 地址信息
      this.$http({
        url: '/account/accountParAddress/findByAccountId/institutional',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership9.rezheng.location = res.object[0].address.replace(/-#-/g, ' ');
              this.partnership9.rezheng.chenshi = res.object[0].city;
              this.partnership9.rezheng.shengshi = res.object[0].rovince;
              this.partnership9.rezheng.postcode = res.object[0].zipCode;
              this.partnership9.rezheng.state = res.object[0].country;
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
    accountParInstitutional() {
      // 公司名
      this.$http({
        url: '/account/accountParInstitutional/findByAccountId',
        method: 'get',
        params: { lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              this.partnership9.rezheng.companyName = res.object[0].institutionalName;
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
    accountParPersonalSQR() {
      // 被授权人姓名
      this.$http({
        url: '/account/accountParPersonal/findByAccountId',
        method: 'get',
        params: { personalType: this.partnership9.personalTypeSQR, lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            if (this.$verify.resStatus(res)) {
              let companyPosition = '';
              this.partnership9.rezheng.xing = res.object[0].lastName;
              this.partnership9.rezheng.zhongjianming = res.object[0].middleName;
              this.partnership9.rezheng.ming = res.object[0].firstName;
              this.partnership9.rezheng.suffix = res.object[0].suffix;
              if (res.object[0].companyPosition.indexOf(',') !== -1) {
                companyPosition = res.object[0].companyPosition.split(',')[0];
              } else {
                companyPosition = res.object[0].companyPosition;
              }
              if (companyPosition === 'mishu') {
                this.partnership9.rezheng.companyPosition = this.$i18n.t('registerOpenAccount.openAccount.common.info.position.item2'); // $i18n - 秘书
              } else if (companyPosition === 'dongshi') {
                this.partnership9.rezheng.companyPosition = this.$i18n.t('registerOpenAccount.openAccount.common.info.position.item1'); // $i18n - 董事
              } else if (companyPosition === 'qitaguanyuan') {
                this.partnership9.rezheng.companyPosition = this.$i18n.t('registerOpenAccount.openAccount.common.info.position.item3'); // $i18n - 其他官员
              }
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
    // 字典表获取
    // 个人类型
    personalType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'personalType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership9.tableTbady = [];
            this.partnership9.tableTbady25 = [];
            this.partnership9.tableTbady10 = [];
            // this.partnership9.personalTypeJ = res.object[4].dictValue;
            // this.partnership9.personalType25 = res.object[6].dictValue;
            // this.partnership9.personalType10 = res.object[7].dictValue;
            // this.partnership9.personalTypeGR = res.object[2].dictValue;
            // this.partnership9.personalTypeSQR = res.object[1].dictValue;
            this.partnership9.personalTypeJ = 'operationPrincipal';
            this.partnership9.personalType25 = '25%AboveBeneficiary';
            this.partnership9.personalType10 = '10%AboveBeneficiary';
            this.partnership9.personalTypeGR = 'individualShareholder';
            this.partnership9.personalTypeSQR = 'authorizedPerson';
            this.accountParPersonal();
            this.getSignature();
            this.accountParAddress();
            this.accountParInstitutional();
            this.accountParPersonalSQR();
            this.personalTypeTwo(this.partnership9.personalTypeJ);
            this.accountParPersonalTwo(this.partnership9.personalTypeJ);
            this.accountParPersonalTwo(this.partnership9.personalType25);
            this.accountParPersonalTwo(this.partnership9.personalType10);
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
    // -- 纳税识别号类型
    taxIdentificationNumber() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'taxIdentificationNumber', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership9.placeAbode[0].taxNumList = JSON.parse(JSON.stringify(res.object));
            this.partnership9.taxNumList1 = JSON.parse(JSON.stringify(res.object));
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
    // -- 地址类型
    addressType() {
      this.$http({
        url: '/common/dictionary/findByCode',
        method: 'get',
        params: { dictCode: 'addressType', lang: this.lang, IEgetIssue: this.$verify.timestampRandom() }
      })
        .then(res => {
          if (res.code === 200) {
            this.partnership9.addressType = res.object[0].dictValue;
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
    // is纳税识别号-验证证件号码
    taxIsBtn(VerifyPath) {
      let num = 1;
      const timerId = setInterval(() => {
        num--;
        this.$refs.partnership9.validateField(VerifyPath);
        if (num === 0) {
          clearInterval(timerId);
        }
      }, 100);
      return true;
    },
    // 经营添加 25添加 10添加
    addelement(index) {
      this.partnership9TS = JSON.parse(JSON.stringify(this.partnership9));
      // 完成已填写的股东赋值到下拉框
      const beneficiaryNameListTS = [];
      let indexTS = 0;
      for (let i = 0; i < this.partnership9.tableTbady25.length; i++) {
        indexTS++;
        const obj = {
          SYRname: this.partnership9.tableTbady25[i].compellation,
          SYRvalue: indexTS,
          id: this.partnership9.tableTbady25[i].id,
          Type: this.partnership9.personalType25
        };
        beneficiaryNameListTS.push(JSON.parse(JSON.stringify(obj)));
      }
      for (let i = 0; i < this.partnership9.tableTbady10.length; i++) {
        indexTS++;
        const obj = {
          SYRname: this.partnership9.tableTbady10[i].compellation,
          SYRvalue: indexTS,
          id: this.partnership9.tableTbady10[i].id,
          Type: this.partnership9.personalType10
        };
        beneficiaryNameListTS.push(JSON.parse(JSON.stringify(obj)));
      }
      if (this.partnership9.authorizedPerson !== null) {
        indexTS++;
        const obj = {
          SYRname: this.partnership9.authorizedPerson.lastName + ' ' + this.partnership9.authorizedPerson.middleName + ' ' + this.partnership9.authorizedPerson.firstName,
          id: this.partnership9.authorizedPerson.id,
          SYRvalue: indexTS,
          Type: 'authorizedPerson'
        };
        beneficiaryNameListTS.push(JSON.parse(JSON.stringify(obj)));
      }
      this.partnership9.xuanzeren1.beneficiaryNameList = beneficiaryNameListTS;
      if (index === 1) {
        this.partnership9.ifmodalBox = true;
        this.partnership9.ismodalBox1Cot = true;
      } else if (index === 2) {
        this.partnership9.ifmodalBox1 = true;
        this.partnership9.ismodalBox2Cot = true;
      } else if (index === 3) {
        this.partnership9.ifmodalBox2 = true;
        this.partnership9.ismodalBox3Cot = true;
      }
      this.partnership9.isbottomBox = false;
    },
    removePersonalTypeJ(id) { // 股东删除功能
      this.$http({
        url: '/openAccount/partnerShip/removeNine',
        method: 'put',
        params: { id }
      })
        .then(res => {
          if (res.code === 200) {
            this.$message.success(this.$i18n.t('registerOpenAccount.common.formPrompt.deleteWin')); // $i18n - 删除成功
            this.personalType();
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
    // 税务添加
    addelement3() {
      this.partnership9.placeAbode.push({
        SWstate3: '',
        IDNumber: '',
        IDNumber1: '',
        suibie: '',
        isSuiwuNO: false, // is 税务识别号
        suiwuQita: '',
        taxNumList: JSON.parse(JSON.stringify(this.partnership9.taxNumList1)) // 页面上使用
      });
    },
    removeelement3() {
      this.partnership9.placeAbode.pop(this.partnership9.placeAbode);
    },
    operatorExtra(item) { // 经营负责人已列出处理
      this.$http({
        url: '/openAccount/partnerShip/updatePersonalByPrimaryKeySelective',
        method: 'post',
        params: { id: item.id, position: this.partnership9.xuanzeren1.zhiwei, personalTypeTwo: 'operationPrincipal' }
      })
        .then(res => {
          if (res.code === 200) {
            this.$message.success(this.$i18n.t('registerOpenAccount.common.formPrompt.addWin')); // $i18n - 添加成功
            this.personalType();
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
    // 模态框 -保存
    save(formName, index) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (index === 1) {
            if (this.partnership9.xuanzeren1.beneficiaryName === 'NotListed') {
              this.arrPush(this.partnership9.personalTypeJ);
            } else {
              this.partnership9.xuanzeren1.beneficiaryNameList.forEach(item => {
                if (this.partnership9.xuanzeren1.beneficiaryName === item.SYRvalue) {
                  this.operatorExtra(item);
                }
              });
              const partnership9TS = JSON.parse(JSON.stringify(this.partnership9TS));
              this.partnership9.xuanzeren1 = partnership9TS.xuanzeren1;
              this.partnership9.gerenGudong = partnership9TS.gerenGudong;
              this.partnership9.juzhudizhi = partnership9TS.juzhudizhi;
              this.partnership9.identification = partnership9TS.identification;
              this.partnership9.placeAbode = partnership9TS.placeAbode;
            }
            this.partnership9.ifmodalBox = false;
            this.partnership9.isJingyinren = true;
            this.partnership9.isbottomBox = true;
          } else if (index === 2) {
            this.arrPush(this.partnership9.personalType25);
            this.partnership9.ifmodalBox1 = false;
            this.partnership9.isbiaoge25 = true;
            this.partnership9.isbottomBox = true;
          } else if (index === 3) {
            this.arrPush(this.partnership9.personalType10);
            this.partnership9.ifmodalBox2 = false;
            this.partnership9.isbiaoge10 = true;
            this.partnership9.isbottomBox = true;
          }
        }
      });
    },
    // 交易人 25 10受益人 保存数据push到提交数组
    arrPush(gdType) { // gdType 个人类型
      const accountId = this.$store.state.user.applyData.xinxi.id; // 账户id
      const createdBy = this.$store.state.user.applyData.xinxi.id;
      const createdDate = this.$verify.NowDate();
      let accountParPersonal = {}; // 公司个人信息
      const accountParAddressList = []; // 公司地址信息
      let accountParIdentity = {}; // 公司身份信息
      const accountParTaxesList = []; // 公司税务居住地
      const partnerShipBeneficiaryOwnersInfoModelList = [];

      let isBusinessManager = false; // 是否 经营负责人 需要判断
      let prcentageOfOwnership = null; // 所有权百分比 需判断

      if (gdType === this.partnership9.personalTypeJ) { // 如果类型为经验负责人
        isBusinessManager = true;
        if (this.partnership9.gerenGudong.issuoyouquanBi) {
          prcentageOfOwnership = this.partnership9.gerenGudong.suoyouquanBi;
        } else {
          prcentageOfOwnership = 0;
        }
      } else if (gdType === this.partnership9.personalType25) { // 如果类型为25
        isBusinessManager = false;
        prcentageOfOwnership = this.partnership9.gerenGudong.suoyouquanBi;
      } else if (gdType === this.partnership9.personalType10) { // 如果类型为10
        isBusinessManager = false;
        prcentageOfOwnership = this.partnership9.gerenGudong.suoyouquanBi;
      }
      accountParPersonal = { // 公司个人信息
        accountId,
        createdBy,
        createdDate,
        appellation: this.partnership9.gerenGudong.companyName,
        birthCountry: this.partnership9.juzhudizhi.state1,
        birthday: this.$verify.NowDate2(this.partnership9.gerenGudong.birth),
        firstName: this.partnership9.gerenGudong.surname,
        middleName: this.partnership9.gerenGudong.middle,
        lastName: this.partnership9.gerenGudong.call,
        suffix: this.partnership9.gerenGudong.suffix,
        position: this.partnership9.gerenGudong.zhiwei44,

        isAmericanTaxpayer: this.partnership9.juzhudizhi.isMeiguoNasuiren,
        isBusinessManager, // 是否 经营负责人 需要判断
        nationality: this.partnership9.juzhudizhi.state1,
        personalType: gdType,
        prcentageOfOwnership // 所有权百分比 需判断
      };
      accountParAddressList.push({ // 公司地址信息
        accountId,
        createdBy,
        createdDate,
        address: this.partnership9.juzhudizhi.dizhi1 ? this.partnership9.juzhudizhi.dizhi + '-#-' + this.partnership9.juzhudizhi.dizhi1 : this.partnership9.juzhudizhi.dizhi,
        addressType: this.partnership9.addressType, // 地址类型需获取
        city: this.partnership9.juzhudizhi.chengshi,
        country: this.partnership9.juzhudizhi.state1,
        otherBirthCountry: this.partnership9.juzhudizhi.ischushengguoButong ? this.partnership9.juzhudizhi.chushengguo : null,
        otherCountry: this.partnership9.juzhudizhi.isguojiButong ? this.partnership9.juzhudizhi.guoji : null,
        rovince: this.partnership9.juzhudizhi.province,
        zipCode: this.partnership9.juzhudizhi.youbian
      });
      accountParIdentity = { // 公司身份信息
        accountId,
        createdBy,
        createdDate,
        idCardType: this.partnership9.identification.SFzhengjianleixing,
        issuingCountry: this.partnership9.identification.SFsignGuojia,
        idNumber: this.partnership9.identification.SFzhengjianhao
      };
      let noTaxIdentificationNumber = null;
      for (let i = 0; i < this.partnership9.placeAbode.length; i++) { // 公司税务居住地
        if (this.partnership9.placeAbode[i].isSuiwuNO) {
          if (this.partnership9.placeAbode[i].suibie === 'qita') {
            noTaxIdentificationNumber = this.partnership9.placeAbode[i].suiwuQita;
          } else {
            noTaxIdentificationNumber = this.partnership9.placeAbode[i].suibie;
          }
        }
        accountParTaxesList.push(
          {
            accountId,
            createdBy,
            createdDate,
            taxResidenceCountry: this.partnership9.placeAbode[i].SWstate3,
            taxResidenceIdCardNumber: this.partnership9.placeAbode[i].IDNumber,
            noTaxIdentificationNumber
          }
        );
      }
      partnerShipBeneficiaryOwnersInfoModelList.push(
        {
          accountParPersonal,
          accountParAddressList,
          accountParIdentity,
          accountParTaxesList
        }
      );

      // 清空表单数据
      const partnership9TS = JSON.parse(JSON.stringify(this.partnership9TS));
      this.partnership9.xuanzeren1 = partnership9TS.xuanzeren1;
      this.partnership9.gerenGudong = partnership9TS.gerenGudong;
      this.partnership9.juzhudizhi = partnership9TS.juzhudizhi;
      this.partnership9.identification = partnership9TS.identification;
      this.partnership9.placeAbode = partnership9TS.placeAbode;

      this.addExperiencedBenefitPerson(partnerShipBeneficiaryOwnersInfoModelList);
    },
    addExperiencedBenefitPerson(partnerShipBeneficiaryOwnersInfoModelList) {
      const data = {
        status: this.status,
        partnerShipBeneficiaryOwnersInfoModelList
      };
      this.$http({
        url: '/openAccount/partnerShip/saveNine',
        method: 'post',
        data
      })
        .then(res => {
          if (res.code === 200) {
            this.$message.success(this.$i18n.t('registerOpenAccount.common.formPrompt.addWin')); // $i18n - 添加成功
            this.personalType();
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
    // 模态框 -关闭
    shut(index) {
      if (index === 1) {
        this.partnership9.ifmodalBox = false;
      } else if (index === 2) {
        this.partnership9.ifmodalBox1 = false;
      } else if (index === 3) {
        this.partnership9.ifmodalBox2 = false;
      }
      this.partnership9.isbottomBox = true;
      // 清空表单数据
      const partnership9TS = JSON.parse(JSON.stringify(this.partnership9TS));
      this.partnership9.xuanzeren1 = partnership9TS.xuanzeren1;
      this.partnership9.gerenGudong = partnership9TS.gerenGudong;
      this.partnership9.juzhudizhi = partnership9TS.juzhudizhi;
      this.partnership9.identification = partnership9TS.identification;
      this.partnership9.placeAbode = partnership9TS.placeAbode;
    },
    // 提交
    submitForm(formName) {
      if (this.partnership9.tableTbady.length === 0) {
        this.$message.error(this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.title')); // $i18n - 最少添加一个经营负责人
      } else if (!(this.ownershipSum <= 100)) {
        // this.$message.error('总所有权不得超过100%，您已添加' + this.ownershipSum + '%的股权');
        this.$message.error(`${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1a')}${this.ownershipSum}${this.$i18n.t('registerOpenAccount.openAccount.common.beneficial.hint.guqian1b')}`);
      } else {
        if (this.partnership9.shengming.signatureZhanshi === this.partnership9.shengming.signature) {
          this.submitFormData();
        } else {
          this.$message.error(this.$i18n.t('registerOpenAccount.common.formPrompt.signatureHint')); // $i18n - 签名与上方不一致 或 为空
        }
      }
    },
    beforepage() {
      this.$verify.routerPermissions({ path: '/openAccount/partnership8' });
    },
    submitFormData() {
      this.$http({
        url: '/openAccount/partnerShip/saveNinePage',
        method: 'post',
        params: { accountId: this.$store.state.user.applyData.xinxi.id }
      })
        .then(res => {
          if (res.code === 200) {
            this.$verify.routerPermissions({ path: '/openAccount/partnership10' });
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
    }

  }
};
</script>
<style scoped>
#ZC-regiMain .renzheng {
  width: 100%;
}
#ZC-regiMain .renzheng > div {
  width: 50%;
  height: 50%;
}
#qianmingTop {
  padding-top: 12px;
  position: absolute;
  padding-left: 40px;
  margin-bottom: 13px;
  font-size: 16px;
}
</style>
