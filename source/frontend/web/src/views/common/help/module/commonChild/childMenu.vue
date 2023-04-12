<template>
  <div class="navMenu">

    <label v-for="(item,index) in menuList" :key="index">
      <div>
        <el-menu-item v-if="item.children===''" :key="item.id" :index="item.id">
          <!-- <i :class="item.iconCls"></i> -->
          <span slot="title">{{ item.dictDest }}</span>
        </el-menu-item>
      </div>

      <el-submenu v-if="item.children" :key="item.id" :index="item.id">
        <template slot="title">
          <i :class="item.iconCls" />
          <span> {{ item.dictDest }}</span>
        </template>
        <ChildMenu :menu-list="item.children" />
      </el-submenu>
    </label>
  </div>
</template>

<script>
export default {
  name: 'ChildMenu',
  // eslint-disable-next-line vue/require-prop-types
  props: ['menuList'],
  data() {
    return {
      dadd: null,
      menuData: {}
    };
  },
  created() {
  },
  methods: {
  }
};
</script>

<style scoped>
    a{
      text-decoration:none
    }
</style>
