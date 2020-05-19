<template>
  <el-menu
    :default-active="this.$route.path"
    router
    mode="horizontal"
    background-color="white"
    text-color="#222"
    active-text-color="red"
    style="min-width: 1300px">
      <el-menu-item index="/index">首页</el-menu-item>
      <el-menu-item index="/articles">好物推荐</el-menu-item>
      <el-menu-item index="/goods">商品</el-menu-item>
      <el-submenu index="/personal">
        <template slot="title">个人中心</template>
        <router-link  :to="{path:'personal/information',query:{id: user.id}}"><el-menu-item>个人资料</el-menu-item></router-link>
        <router-link  :to="{path:'personal/order',query:{id: user.id}}"><el-menu-item>我的订单</el-menu-item></router-link>
      </el-submenu>
      <el-menu-item index="/admin">后台管理</el-menu-item>
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
        {name: '/articles', navItem: '好物推荐'},
        {name: '/goods', navItem: '商品'},
        {name: '/personal', navItem: '个人中心'},
        {name: '/admin', navItem: '后台管理'}
      ],
      user: {
        id: '',
        username: '',
        name: ''
      }
    }
  },
  mounted: function () {
    this.getCurrentUser()
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
    },
    getCurrentUser () {
      var _this = this
      this.$axios.get('/getCurrentUser').then(resp => {
        _this.user.id = resp.data.result.id
        _this.user.username = resp.data.result.username
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
