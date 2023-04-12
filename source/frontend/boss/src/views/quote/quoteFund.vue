<template>
  <div class="app-container">

    <el-form ref="searchForm" :model="searchForm" :inline="true" size="mini">
      <el-form-item label="证券编码">
        <el-input v-model="searchForm.stockCode" clearable placeholder="请输入证券编码"></el-input>
      </el-form-item>
      <el-form-item label="交易所名称">
        <el-input v-model="searchForm.exchangeName" clearable placeholder="请输入交易所名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="doSearch()">查询</el-button>
        <el-button type="success" icon="el-icon-circle-plus" @click="dialogAdd()">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table v-loading="quoteFundLoading" :data="quoteFundData" style="width:100%;margin-bottom:20px;" border row-key="id">
      <el-table-column :fixed="true" prop="stockCode" label="证券编码" />
      <el-table-column :fixed="true" prop="exchangeId" label="交易所" :formatter="exchangeFormat" />
      <el-table-column :fixed="true" prop="quoteDate" label="行情日期" :formatter="dateFormat" />
      <el-table-column :fixed="true" prop="prevClose" label="昨收盘价" />
      <el-table-column :fixed="true" prop="last" label="最新价" />
      <el-table-column prop="open" label="开盘价" />
      <el-table-column prop="high" label="最高价" />
      <el-table-column prop="low" label="最低价" />
      <el-table-column prop="chg" label="涨跌幅">
        <template slot-scope="scope">
          <span v-if="scope.row.chg">{{ scope.row.chg + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="change" label="涨跌额" />
      <el-table-column prop="volume" label="成交量" />
      <el-table-column prop="turnover" label="成交额" />
      <el-table-column prop="committeeThan" label="委比" />
      <el-table-column prop="quantityRelative" label="量比" />
      <el-table-column prop="turnoverRate" label="换手率">
        <template slot-scope="scope">
          <span v-if="scope.row.turnoverRate">{{ scope.row.turnoverRate + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="bid" label="买一价" />
      <el-table-column prop="ask" label="卖一价" />
      <el-table-column prop="bidSize" label="买入量" />
      <el-table-column prop="askSize" label="卖出量" />
      <el-table-column prop="cumulativeNetValue" label="累计净值" />
      <el-table-column prop="returnDailyRate" label="日增长率">
        <template slot-scope="scope">
          <span v-if="scope.row.returnDailyRate">{{ scope.row.returnDailyRate + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return1week" label="近一周收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return1week">{{ scope.row.return1week + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return1month" label="近一月收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return1month">{{ scope.row.return1month + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return3month" label="近三月收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return3month">{{ scope.row.return3month + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return6month" label="近六月收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return6month">{{ scope.row.return6month + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return1year" label="近一年收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return1year">{{ scope.row.return1year + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return2year" label="近两年收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return2year">{{ scope.row.return2year + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="return3year" label="近三年收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.return3year">{{ scope.row.return3year + '%' }}</span>
        </template>
      </el-table-column>
      <el-table-column prop="returnYieldThisYear" label="今年以来收益率">
        <template slot-scope="scope">
          <span v-if="scope.row.returnYieldThisYear">{{ scope.row.returnYieldThisYear + '%' }}</span>
        </template>
      </el-table-column>
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

    <el-dialog title="基金行情" :visible.sync="quoteFundDialog" width="760px">
      <el-form ref="quoteFundForm" :inline="true" :model="quoteFundForm" :rules="quoteFundRules" label-width="150px" class="quoteFundForm">
        <el-form-item label="证券信息" prop="stockinfoId">
          <el-select v-model="quoteFundForm.stockinfoId" placeholder="请选择证券信息" value="">
            <el-option v-for="data in stockInfoData" :key="data.id" :label="data.stockCode" :value="data.id">
              <span style="float:left;">{{ data.stockCode }}</span>
              <span style="float:right">{{ data.stockNameCn }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易场所" prop="exchangeId">
          <el-select v-model="quoteFundForm.exchangeId" placeholder="请选择上市场所" value="">
            <el-option v-for="data in exchangeData" :key="data.id" :label="data.exchangeName" :value="data.id">
              <span style="float:left;">{{ data.exchangeCode }}</span>
              <span style="float:right">{{ data.exchangeName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行情日期" prop="quoteDate">
          <el-date-picker v-model="quoteFundForm.quoteDate" type="date" value-format="timestamp" placeholder="选择行情日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="昨收盘价" prop="prevClose">
          <el-input v-model="quoteFundForm.prevClose" />
        </el-form-item>
        <el-form-item label="最新价" prop="last">
          <el-input v-model="quoteFundForm.last" />
        </el-form-item>
        <el-form-item label="开盘价" prop="open">
          <el-input v-model="quoteFundForm.open" />
        </el-form-item>
        <el-form-item label="最高价" prop="high">
          <el-input v-model="quoteFundForm.high" />
        </el-form-item>
        <el-form-item label="最低价" prop="low">
          <el-input v-model="quoteFundForm.low" />
        </el-form-item>
        <el-form-item label="涨跌幅" prop="chg">
          <el-input v-model="quoteFundForm.chg">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="涨跌额" prop="change">
          <el-input v-model="quoteFundForm.change" />
        </el-form-item>
        <el-form-item label="成交量" prop="volume">
          <el-input v-model="quoteFundForm.volume" />
        </el-form-item>
        <el-form-item label="成交额" prop="turnover">
          <el-input v-model="quoteFundForm.turnover" />
        </el-form-item>
        <el-form-item label="委比" prop="committeeThan">
          <el-input v-model="quoteFundForm.committeeThan">
          </el-input>
        </el-form-item>
        <el-form-item label="量比" prop="quantityRelative">
          <el-input v-model="quoteFundForm.quantityRelative">
          </el-input>
        </el-form-item>
        <el-form-item label="换手率" prop="turnoverRate">
          <el-input v-model="quoteFundForm.turnoverRate">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="买一价" prop="bid">
          <el-input v-model="quoteFundForm.bid" />
        </el-form-item>
        <el-form-item label="卖一价" prop="ask">
          <el-input v-model="quoteFundForm.ask" />
        </el-form-item>
        <el-form-item label="买入量" prop="bidSize">
          <el-input v-model="quoteFundForm.bidSize" />
        </el-form-item>
        <el-form-item label="卖出量" prop="askSize">
          <el-input v-model="quoteFundForm.askSize" />
        </el-form-item>
        <el-form-item label="累计净值" prop="cumulativeNetValue">
          <el-input v-model="quoteFundForm.cumulativeNetValue" />
        </el-form-item>
        <el-form-item label="日增长率" prop="returnDailyRate">
          <el-input v-model="quoteFundForm.returnDailyRate">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近一周收益率" prop="return1week">
          <el-input v-model="quoteFundForm.return1week">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近一月收益率" prop="return1month">
          <el-input v-model="quoteFundForm.return1month">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近三月收益率" prop="return3month">
          <el-input v-model="quoteFundForm.return3month">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近六月收益率" prop="return6month">
          <el-input v-model="quoteFundForm.return6month">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近一年收益率" prop="return1year">
          <el-input v-model="quoteFundForm.return1year">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近两年收益率" prop="return2year">
          <el-input v-model="quoteFundForm.return2year">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="近三年收益率" prop="return3year">
          <el-input v-model="quoteFundForm.return3year">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="今年以来收益率" prop="returnYieldThisYear">
          <el-input v-model="quoteFundForm.returnYieldThisYear">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('quoteFundForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isBtnZeroToThousand } from '../../utils/validate.js';
export default {
  name: 'QuoteFund',
  data() {
    return {
      quoteFundLoading: '',
      quoteFundDialog: false,
      quoteFundData: [],
      stockInfoData: [],
      exchangeData: [],
      quoteFundForm: {
        'id': '',
        'stockinfoId': '',
        'exchangeId': '',
        'quoteDate': '',
        'prevClose': '',
        'last': '',
        'open': '',
        'high': '',
        'low': '',
        'chg': '',
        'change': '',
        'volume': '',
        'turnover': '',
        'bid': '',
        'ask': '',
        'bidSize': '',
        'askSize': '',
        'committeeThan': '',
        'cumulativeNetValue': '',
        'turnoverRate': '',
        'quantityRelative': '',
        'returnDailyRate': '',
        'return1week': '',
        'return1month': '',
        'return3month': '',
        'return6month': '',
        'return1year': '',
        'return2year': '',
        'return3year': '',
        'returnYieldThisYear': ''
      },
      searchForm: {
        'stockCode': '',
        'exchangeName': ''
      },
      pageParams: {
        'rows': 10,
        'page': 1,
        'totalPage': 0
      },
      quoteFundRules: {
        stockinfoId: [
          { required: true, message: '请选择证券信息', trigger: 'change' }
        ],
        exchangeId: [
          { required: true, message: '请选择上市场所', trigger: 'change' }
        ],
        quoteDate: [
          { required: true, message: '请选择行情日期', trigger: 'change' }
        ],
        prevClose: [
          { required: true, message: '请输入昨收盘价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        last: [
          { required: true, message: '请输入最新价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        open: [
          { required: false, message: '请输入开盘价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        high: [
          { required: false, message: '请输入最高价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        low: [
          { required: false, message: '请输入最低价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        chg: [
          { required: false, message: '请输入涨跌幅', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        change: [
          { required: false, message: '请输入涨跌额', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        volume: [
          { required: false, message: '请输入成交量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        turnover: [
          { required: false, message: '请输入成交额', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        committeeThan: [
          { required: false, message: '请输入委比', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        quantityRelative: [
          { required: false, message: '请输入量比', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        turnoverRate: [
          { required: false, message: '请输入换手率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        bid: [
          { required: false, message: '请输入买一价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        ask: [
          { required: false, message: '请输入卖一价', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        bidSize: [
          { required: false, message: '请输入买入量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        askSize: [
          { required: false, message: '请输入卖出量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        cumulativeNetValue: [
          { required: false, message: '请输入累计净值', trigger: 'blur' }, { validator: isBtnZeroToThousand, trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        returnDailyRate: [
          { required: false, message: '请输入日增长率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return1week: [
          { required: false, message: '请输入近一周收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return1month: [
          { required: false, message: '请输入近一月收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return3month: [
          { required: false, message: '请输入近三月收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return6month: [
          { required: false, message: '请输入近六月收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return1year: [
          { required: false, message: '请输入近一年收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return2year: [
          { required: false, message: '请输入近两年收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        return3year: [
          { required: false, message: '请输入近三年收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        returnYieldThisYear: [
          { required: false, message: '请输入今年以来收益率', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ] }
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
      return this.$moment(date).format('YYYY-MM-DD');
    },
    exchangeFormat: function(row, column) {
      const exchangeArray = this.exchangeData;
      let exchangeName = '';
      exchangeArray.forEach((data) => {
        if (row[column.property] === data.id) {
          exchangeName = data.exchangeName;
        }
      });
      return exchangeName;
    },
    dialogAdd: function() {
      this.quoteFundForm = {
        'id': '',
        'stockinfoId': '',
        'exchangeId': '',
        'quoteDate': '',
        'prevClose': '',
        'last': '',
        'open': '',
        'high': '',
        'low': '',
        'chg': '',
        'change': '',
        'volume': '',
        'turnover': '',
        'bid': '',
        'ask': '',
        'bidSize': '',
        'askSize': '',
        'cumulativeNetValue': '',
        'committeeThan': '',
        'turnoverRate': '',
        'quantityRelative': '',
        'returnDailyRate': '',
        'return1week': '',
        'return1month': '',
        'return3month': '',
        'return6month': '',
        'return1year': '',
        'return2year': '',
        'return3year': '',
        'returnYieldThisYear': ''
      };
      if (this.$refs.quoteFundForm) {
        this.$refs.quoteFundForm.resetFields();
      }
      this.quoteFundDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.quoteFundForm) {
        this.$refs.quoteFundForm.resetFields();
      }
      this.$http({
        url: '/quote/fund/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.quoteFundForm = {
            'id': res.object.id,
            'stockinfoId': res.object.stockinfoId,
            'exchangeId': res.object.exchangeId,
            'quoteDate': res.object.quoteDate,
            'prevClose': res.object.prevClose,
            'last': res.object.last,
            'open': res.object.open,
            'high': res.object.high,
            'low': res.object.low,
            'chg': res.object.chg,
            'change': res.object.change,
            'volume': res.object.volume,
            'turnover': res.object.turnover,
            'bid': res.object.bid,
            'ask': res.object.ask,
            'bidSize': res.object.bidSize,
            'askSize': res.object.askSize,
            'committeeThan': res.object.committeeThan,
            'turnoverRate': res.object.turnoverRate,
            'quantityRelative': res.object.quantityRelative,
            'cumulativeNetValue': res.object.cumulativeNetValue,
            'returnDailyRate': res.object.returnDailyRate,
            'return1week': res.object.return1week,
            'return1month': res.object.return1month,
            'return3month': res.object.return3month,
            'return6month': res.object.return6month,
            'return1year': res.object.return1year,
            'return2year': res.object.return2year,
            'return3year': res.object.return3year,
            'returnYieldThisYear': res.object.returnYieldThisYear
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.quoteFundDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.quoteFundLoading = true;
      this.$http({
        url: '/quote/fund/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.quoteFundData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.quoteFundLoading = false;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doInitData: function() {
      this.$http({
        url: '/stock/exchange/findAll',
        method: 'get'
      }).then(res => {
        if (res.code === 200) {
          this.exchangeData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.$http({
        url: '/stock/info/findListByStockType',
        method: 'get',
        params: {
          stockType: 'fund'
        }
      }).then(res => {
        if (res.code === 200) {
          this.stockInfoData = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doSubmit: function(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/quote/fund/save',
            method: 'post',
            params: this.quoteFundForm
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
          this.quoteFundDialog = false;
        }
      });
    },
    doDelete: function(row, column) {
      this.$confirm('确认删除该证劵信息, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: '/quote/fund/del',
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
    }
  }
};
</script>

<style lang="scss">
  .quoteFundForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
