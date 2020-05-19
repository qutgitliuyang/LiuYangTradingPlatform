<template>
  <div>
      <div class="info" style="width: 800px;height: 500px;margin-left: auto;margin-right: auto;">
        <div class="title">
          <h1>请确认您的购买</h1>
          <div style="margin-bottom: 30px">
            <span class="product-name">商品名：{{good.name}}</span>
            <span class="product-seller" style="margin-left: 20px">卖家：{{good.seller}}</span>
            <span class="product-cost" style="margin-left: 20px">售价：￥{{good.price}}</span>
          </div>
          <div>
            <el-button style="color: #E5511D;border-color: #F0CAB6;background: #FFE4D0" @click="payGood(good)"><i class="el-icon-s-finance">确认购买</i></el-button>
            <el-button @click="backGoods">返回商品页</el-button>
          </div>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  name: 'GoodPay',
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
        _this.user.id = resp.data.result.id
        _this.user.username = resp.data.result.username
      })
    },
    payGood () {
      this.payUpdateOrder()
      this.payUpdateGood()
    },
    payUpdateGood () {
      this.$axios
        .post('/goods/pay', {
          id: this.good.id,
          img: this.good.img,
          name: this.good.name,
          seller: this.good.seller,
          date: this.good.date,
          introduction: this.good.introduction,
          price: this.good.price,
          category: this.good.category,
          status: 2
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.$alert('商品购买成功，即将返回商品首页', '提示', {
              confirmButtonText: '确定',
              callback: action => {
                this.$router.push({path: '/goods'})
              }
            })
          }
        })
    },
    payUpdateOrder () {
      this.$axios
        .post('/orders', {
          goodName: this.good.name,
          goodPrice: this.good.price,
          goodSeller: this.good.seller,
          goodImg: this.good.img,
          buyerId: this.user.id,
          buyerUsername: this.user.username,
          date: new Date()
        })
    },
    backGoods () {
      this.$router.push({path: '/goods'})
    }
  }
}
</script>

<style scoped>

</style>
