<template>
  <div>
    <el-option
      v-for="(item,index) in dictList"
      :key="index"
      :value="dictCode==='country'?item.sCode:item.dictValue"
      :label="dictCode==='country'?lang === 'en_US'? item.enName:item.cnName :item.dictDest"
    />
  </div>
</template>
<script>
export default {
  props: {
    dictCode: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      lang: this.$Cookies.get('locale'),
      dictList: []
    };
  },
  created() {
    this.doInitData();
  },
  methods: {
    doInitData() {
      const url = this.dictCode === 'country' ? '/common/region/findAll' : '/common/dictionary/findByCode';
      this.$http({
        url,
        method: 'get',
        params: { dictCode: this.dictCode, lang: this.$Cookies.get('locale'), IEgetIssue: this.$verify.timestampRandom() }
      }).then(res => {
        if (res.code === 200) {
          this.dictList = res.object;
        }
      });
    }
  }
};
</script>

