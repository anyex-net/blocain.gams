<template>
  <div class="app-container">
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'InformationService'
};
</script>

<style lang="scss" scoped>
  .app-container {
    .express-banner {
      width: 100%;
    }
  }
</style>
