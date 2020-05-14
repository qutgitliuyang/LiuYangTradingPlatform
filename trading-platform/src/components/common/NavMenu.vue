<template>
  <el-menu
    :default-active="this.$route.path"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    style="min-width: 1300px">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      {{ item.navItem }}
    </el-menu-item>
    <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
    <span style="position: absolute;padding-top: 20px;right: 35%;font-size: 20px;font-weight: bold">Trading Platform - Your Goods Trading Website</span>
  </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/index', navItem: '首页'},
        {name: '/jotter', navItem: '笔记本'},
        {name: '/goods', navItem: '商品'},
        {name: '/admin', navItem: '个人中心'}
      ]
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$axios
        .get('/logout').then(resp => {
          if (resp.data.code === 200) {
            // 前后端状态保持一致
            _this.$store.commit('logout')
            _this.$router.replace('/login')
          }
        })
    }
  }
}
</script>

<style scoped>
  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }

  span {
    pointer-events: none;
  }
</style>
