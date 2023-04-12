<template>
  <router-view></router-view>
</template>

<script>
export default {
  name: 'Product'
};
</script>

<style lang="scss">
  .product-wrapper {
    .el-card__body {
      padding: 30px 20px;
      height: 40px;
    }
    .product-txt {
      font-size: 18px!important;
      letter-spacing: 0!important;
      border: none!important;
    }
    .product-card {
      height: 100px!important;
    }
    .line {
      border-bottom: 2px solid;
      width: 16px;
      margin-left: 62px;
    }
  }
</style>
