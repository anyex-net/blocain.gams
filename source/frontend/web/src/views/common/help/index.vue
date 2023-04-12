<template>
  <div class="app-container">
    <div class="main-container">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {

    };
  },
  beforeCreate() {

  },
  created() {

  },
  mounted() {

  },
  methods: {

  }
};
</script>
<style lang="scss" scoped>
  .main-container {
    white-space:normal; // this it is
    word-break:break-all; //or word-wrap:break-word;
    word-wrap: break-word; // 长英文字母或长数字自动换行
  }
</style>
