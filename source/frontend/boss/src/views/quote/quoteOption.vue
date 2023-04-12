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

    <el-table v-loading="quoteOptionLoading" :data="quoteOptionData" style="width:100%;margin-bottom:20px;" border row-key="id">
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
      <el-table-column prop="buyVolumeOutside" label="买盘(外盘)" />
      <el-table-column prop="sellVolumeOutside" label="卖盘(内盘)" />
      <el-table-column prop="positionVolume" label="持仓量" />
      <el-table-column prop="positionDailyIncrease" label="日增仓" />
      <el-table-column prop="exercisePrice" label="行权价" />
      <el-table-column prop="remainingDays" label="剩余日" />
      <el-table-column prop="bid" label="买一价" />
      <el-table-column prop="ask" label="卖一价" />
      <el-table-column prop="bidSize" label="买入量" />
      <el-table-column prop="askSize" label="卖出量" />
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

    <el-dialog title="期权行情" :visible.sync="quoteOptionDialog" width="660px">
      <el-form ref="quoteOptionForm" :inline="true" :model="quoteOptionForm" :rules="quoteOptionRules" label-width="100px" class="quoteOptionForm">
        <el-form-item label="证券信息" prop="stockinfoId">
          <el-select v-model="quoteOptionForm.stockinfoId" placeholder="请选择证券信息" value="">
            <el-option v-for="data in stockInfoData" :key="data.id" :label="data.stockCode" :value="data.id">
              <span style="float:left;">{{ data.stockCode }}</span>
              <span style="float:right">{{ data.stockNameCn }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="交易场所" prop="exchangeId">
          <el-select v-model="quoteOptionForm.exchangeId" placeholder="请选择上市场所" value="">
            <el-option v-for="data in exchangeData" :key="data.id" :label="data.exchangeName" :value="data.id">
              <span style="float:left;">{{ data.exchangeCode }}</span>
              <span style="float:right">{{ data.exchangeName }}</span>
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="行情日期" prop="quoteDate">
          <el-date-picker v-model="quoteOptionForm.quoteDate" type="date" value-format="timestamp" placeholder="选择行情日期" style="width:100%;"></el-date-picker>
        </el-form-item>
        <el-form-item label="昨收盘价" prop="prevClose">
          <el-input v-model="quoteOptionForm.prevClose" />
        </el-form-item>
        <el-form-item label="最新价" prop="last">
          <el-input v-model="quoteOptionForm.last" />
        </el-form-item>
        <el-form-item label="开盘价" prop="open">
          <el-input v-model="quoteOptionForm.open" />
        </el-form-item>
        <el-form-item label="最高价" prop="high">
          <el-input v-model="quoteOptionForm.high" />
        </el-form-item>
        <el-form-item label="最低价" prop="low">
          <el-input v-model="quoteOptionForm.low" />
        </el-form-item>
        <el-form-item label="涨跌幅" prop="chg">
          <el-input v-model="quoteOptionForm.chg">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="涨跌额" prop="change">
          <el-input v-model="quoteOptionForm.change" />
        </el-form-item>
        <el-form-item label="成交量" prop="volume">
          <el-input v-model="quoteOptionForm.volume" />
        </el-form-item>
        <el-form-item label="成交额" prop="turnover">
          <el-input v-model="quoteOptionForm.turnover" />
        </el-form-item>
        <el-form-item label="委比" prop="committeeThan">
          <el-input v-model="quoteOptionForm.committeeThan" />
        </el-form-item>
        <el-form-item label="量比" prop="turnoverRate">
          <el-input v-model="quoteOptionForm.turnoverRate" />
        </el-form-item>
        <el-form-item label="换手率" prop="quantityRelative">
          <el-input v-model="quoteOptionForm.quantityRelative">
            <span slot="suffix">%</span>
          </el-input>
        </el-form-item>
        <el-form-item label="买盘(外盘)" prop="buyVolumeOutside">
          <el-input v-model="quoteOptionForm.buyVolumeOutside" />
        </el-form-item>
        <el-form-item label="卖盘(内盘)" prop="sellVolumeOutside">
          <el-input v-model="quoteOptionForm.sellVolumeOutside" />
        </el-form-item>
        <el-form-item label="持仓量" prop="positionVolume">
          <el-input v-model="quoteOptionForm.positionVolume" />
        </el-form-item>
        <el-form-item label="日增仓" prop="positionDailyIncrease">
          <el-input v-model="quoteOptionForm.positionDailyIncrease" />
        </el-form-item>
        <el-form-item label="行权价" prop="exercisePrice">
          <el-input v-model="quoteOptionForm.exercisePrice" />
        </el-form-item>
        <el-form-item label="剩余日" prop="remainingDays">
          <el-input v-model="quoteOptionForm.remainingDays" />
        </el-form-item>
        <el-form-item label="买一价" prop="bid">
          <el-input v-model="quoteOptionForm.bid" />
        </el-form-item>
        <el-form-item label="卖一价" prop="ask">
          <el-input v-model="quoteOptionForm.ask" />
        </el-form-item>
        <el-form-item label="买入量" prop="bidSize">
          <el-input v-model="quoteOptionForm.bidSize" />
        </el-form-item>
        <el-form-item label="卖出量" prop="askSize">
          <el-input v-model="quoteOptionForm.askSize" />
        </el-form-item>
        <el-divider></el-divider>
        <el-form-item>
          <el-button type="primary" @click="doSubmit('quoteOptionForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

  </div>
</template>

<script>
import { checkNum, isInteger, isBtnZeroToThousand } from '../../utils/validate.js';
export default {
  name: 'QuoteOption',
  data() {
    return {
      quoteOptionLoading: '',
      quoteOptionDialog: false,
      quoteOptionData: [],
      stockInfoData: [],
      exchangeData: [],
      quoteOptionForm: {
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
        'committeeThan': '',
        'quantityRelative': '',
        'turnoverRate': '',
        'buyVolumeOutside': '',
        'sellVolumeOutside': '',
        'positionVolume': '',
        'positionDailyIncrease': '',
        'exercisePrice': '',
        'remainingDays': '',
        'bid': '',
        'ask': '',
        'bidSize': '',
        'askSize': ''
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
      quoteOptionRules: {
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
        buyVolumeOutside: [
          { required: false, message: '请输入买盘(外盘)', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        sellVolumeOutside: [
          { required: false, message: '请输入卖盘(内盘)', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        positionVolume: [
          { required: false, message: '请输入持仓量', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        positionDailyIncrease: [
          { required: false, message: '请输入日增仓', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        exercisePrice: [
          { required: false, message: '请输入行权价', trigger: 'blur' }, { validator: checkNum, trigger: 'blur' }
        ],
        remainingDays: [
          { required: false, message: '请输入剩余日', trigger: 'blur' }, { validator: isInteger, trigger: 'blur' }
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
      this.quoteOptionForm = {
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
        'committeeThan': '',
        'quantityRelative': '',
        'turnoverRate': '',
        'buyVolumeOutside': '',
        'sellVolumeOutside': '',
        'positionVolume': '',
        'positionDailyIncrease': '',
        'exercisePrice': '',
        'remainingDays': '',
        'bid': '',
        'ask': '',
        'bidSize': '',
        'askSize': ''
      };
      if (this.$refs.quoteOptionForm) {
        this.$refs.quoteOptionForm.resetFields();
      }
      this.quoteOptionDialog = true;
    },
    dialogEdit: function(row, column) {
      if (this.$refs.quoteOptionForm) {
        this.$refs.quoteOptionForm.resetFields();
      }
      this.$http({
        url: '/quote/option/findBy',
        method: 'get',
        params: {
          'id': column.id
        }
      }).then(res => {
        if (res.code === 200) {
          this.quoteOptionForm = {
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
            'committeeThan': res.object.committeeThan,
            'quantityRelative': res.object.quantityRelative,
            'turnoverRate': res.object.turnoverRate,
            'buyVolumeOutside': res.object.buyVolumeOutside,
            'sellVolumeOutside': res.object.sellVolumeOutside,
            'positionVolume': res.object.positionVolume,
            'positionDailyIncrease': res.object.positionDailyIncrease,
            'exercisePrice': res.object.exercisePrice,
            'remainingDays': res.object.remainingDays,
            'bid': res.object.bid,
            'ask': res.object.ask,
            'bidSize': res.object.bidSize,
            'askSize': res.object.askSize
          };
        }
      }).catch(error => {
        this.$message.error(error);
      });
      this.quoteOptionDialog = true;
    },
    doSearch: function(page) {
      this.pageParams.page = page;
      this.quoteOptionLoading = true;
      this.$http({
        url: '/quote/option/data',
        method: 'post',
        params: Object.assign(this.pageParams, this.searchForm)
      }).then(res => {
        if (res.code === 200) {
          this.quoteOptionData = res.rows;
          this.pageParams.totalPage = res.totalPage;
          this.quoteOptionLoading = false;
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
          stockType: 'option'
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
            url: '/quote/option/save',
            method: 'post',
            params: this.quoteOptionForm
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
          this.quoteOptionDialog = false;
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
          url: '/quote/option/del',
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
  .quoteOptionForm.el-form--inline .el-form-item__content {
    width: 196px;
  }
</style>
