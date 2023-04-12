<template>
  <div class="app-container">
    <!-- <img class="express-banner" src="@/assets/images/common/banner_image_express.png"/> -->
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'Express'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .express-banner {
      width: 100%;
    }
  }
</style>
