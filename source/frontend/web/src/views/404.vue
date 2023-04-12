<template>
  <div class="error-content">
    <img class="error-img" src="@/assets/images/common/error_404.png" alt="" />
    <div class="error-txt">Please check that the URL you entered is correct.</div>
    <router-link class="error-btn" to="/">Back to home</router-link>
  </div>
</template>

<script>
export default {
  name: 'Page404'
};
</script>

<style lang="scss" scoped>
  .error-content {
    margin: 150px auto;
    text-align: center;
  }
  .error-content .error-txt {
    font-size: 2rem;
    color: #acadaf;
    margin-top: -55px;
  }
  .error-content .error-btn {
    background-color: #FF4057;
    margin-top: 30px;
    padding: 10px 25px;
    border-radius: 2px;
    color: #ffffff;
    display: inline-block;
  }
</style>

