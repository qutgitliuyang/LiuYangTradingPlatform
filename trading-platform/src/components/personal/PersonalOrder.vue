<template>
  <div>
    <el-card style="margin: 18px 2%;width: 95%;height: 100%">
      <el-table
        :data="orders"
        stripe
        :default-sort = "{order: 'ascending'}"
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          label="订单ID"
          fit>
        </el-table-column>
        <el-table-column
          prop="goodImg"
          label="商品照片"
        >
          <template slot-scope="scope">
            <img  :src="scope.row.goodImg" alt="" style="width: 50px;height: 50px">
          </template>
        </el-table-column>
        <el-table-column
          prop="goodName"
          label="商品名称"
        >
        </el-table-column>
        <el-table-column
          prop="goodPrice"
          label="商品价格"
        >
        </el-table-column>
        <el-table-column
          prop="goodSeller"
          label="卖家"
        >
        </el-table-column>
        <el-table-column
          prop="date"
          label="订单日期"
        >
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 20px 0;float: left">
        <el-button>取消选择</el-button>
        <el-button>批量删除</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'PersonalOrder',
  data () {
    return {
      orders: [],
      user: {
        id: '',
        username: '',
        name: ''
      }
    }
  },
  mounted () {
    this.loadOrders()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 200
    }
  },
  methods: {
    loadOrders () {
      var _this = this
      this.$axios.get('/orders/' + this.$route.query.id).then(resp => {
        if (resp && resp.status === 200) {
          _this.orders = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
