<template>
  <el-row :gutter="20" style="width: 1520px">
    <el-col :span="8">
      <div class="img" style="margin-left: 50px">
        <img :src="good.img" alt="照片" style="width: 320px;height: 320px">
        <div class="product-buy-cart">
          <div class="product-add-cart" style="float: left;margin-left: 65px">
             <el-button style="color: #E5511D;border-color: #F0CAB6;background: #FFE4D0" @click="buyGood(good)"><i class="el-icon-s-finance">立即购买</i></el-button>
          </div>
          <div class="product-buy"><el-button style="color: #FFF;border-color: #F40;;background: #F40"><i class="el-icon-shopping-cart-2">加入购物车</i></el-button></div>
        </div>
      </div>
    </el-col>
    <el-col :span="14">
      <div class="info" style="width: 800px;height: 500px">
        <div class="title">
          <h1 class="product-name">{{good.name}}</h1>
          <div>
            <span class="product-seller" style="float: left; margin-left: 30px">卖家：{{good.seller}}</span>
            <span class="product-cost">售价：￥{{good.price}}</span>
            <span class="product-date" style="float: right; margin-right: 30px">{{good.date}}</span>
            <div class="product-introduction" style="margin-top: 50px">{{good.introduction}}</div>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="2">
      <div><edit-form ref="edit"></edit-form></div>
      <div><i class="el-button" style="width: 103px;margin-left: 4px" v-if="user.username === good.seller ? true : false " @click="editGood(good)">修改商品</i></div>
      <div><i class="el-button" style="width: 103px;margin-left: 4px" v-if="user.username === good.seller ? true : false " @click="deleteGood(good.id)">删除商品</i></div>
    </el-col>
  </el-row>
</template>

<script>
import EditForm from './EditForm'
export default {
  name: 'GoodDetails',
  components: {EditForm},
  data () {
    return {
      good: [],
      user: {
        id: '',
        username: '',
        name: ''
      }
    }
  },
  mounted: function () {
    this.loadGood()
    this.getCurrentUser()
  },
  methods: {
    loadGood () {
      var _this = this
      this.$axios.get('/good/' + this.$route.query.id).then(resp => {
        if (resp && resp.status === 200) {
          _this.good = resp.data
        }
      })
    },
    getCurrentUser () {
      var _this = this
      this.$axios.get('/getCurrentUser').then(resp => {
        _this.user.username = resp.data.result.username
      })
    },
    editGood (good) {
      this.$refs.edit.dialogFormVisible = true
      this.$refs.edit.form = {
        id: good.id,
        img: good.img,
        name: good.name,
        price: good.price,
        introduction: good.introduction,
        category: {
          id: good.category.id.toString(),
          name: good.category.name
        }
      }
    },
    deleteGood (id) {
      this.$confirm('此操作将永久删除该商品, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/delete', {id: id}).then(resp => {
            if (resp && resp.status === 200) {
              this.loadGoods()
            }
          })
      }
      ).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
      // alert(id)
    },
    buyGood () {
      this.$router.push({path: '/goods/goodsPay?id=' + this.good.id})
    }
  }
}
</script>

<style scoped>

</style>
