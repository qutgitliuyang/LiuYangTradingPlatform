<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>商品管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <edit-form @onSubmit="loadGoods()" ref="edit"></edit-form>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="goods"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
                <img :src="props.row.img" alt="封面" style="width: 100px; height: 100px">
              <el-form-item>
                <span>{{ props.row.introduction }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="商品"
          fit>
        </el-table-column>
        <el-table-column
          prop="category.name"
          label="分类"
          width="100">
        </el-table-column>
        <el-table-column
          prop="price"
          label="价格"
          fit>
        </el-table-column>
        <el-table-column
          prop="seller"
          label="卖家"
          fit>
        </el-table-column>
        <el-table-column
          prop="date"
          label="出版日期"
          width="120">
        </el-table-column>
        <!--<el-table-column-->
        <!--prop="abs"-->
        <!--label="摘要"-->
        <!--show-overflow-tooltip-->
        <!--fit>-->
        <!--</el-table-column>-->
        <el-table-column
          fixed="right"
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editGood(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteGood(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
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
import EditForm from './EditForm'
export default {
  name: 'GoodManagement',
  components: {EditForm},
  data () {
    return {
      goods: []
    }
  },
  mounted () {
    this.loadGoods()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    deleteGood (id) {
      this.$confirm('此操作将永久删除该书籍, 是否继续?', '提示', {
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
    editGood (item) {
      this.$refs.edit.dialogFormVisible = true
      this.$refs.edit.form = {
        id: item.id,
        img: item.img,
        name: item.name,
        seller: item.seller,
        date: item.date,
        price: item.price,
        introduction: item.introduction,
        category: {
          id: item.category.id.toString(),
          name: item.category.name
        }
      }
      // this.$refs.edit.category = {
      //   id: item.category.id.toString()
      // }
    },
    loadGoods () {
      var _this = this
      this.$axios.get('/goods').then(resp => {
        if (resp && resp.status === 200) {
          _this.goods = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
