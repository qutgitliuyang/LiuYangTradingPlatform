<template>
  <div>
    <el-dialog
      title="修改分类信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedCategory" style="text-align: left" ref="dataForm">
        <el-form-item label="分类名称" label-width="120px" prop="name">
          <el-input v-model="selectedCategory.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedCategory)">确 定</el-button>
      </div>
    </el-dialog>
    <el-row style="margin: 18px 0px 0px 18px ">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/admin/dashboard' }">管理中心</el-breadcrumb-item>
        <el-breadcrumb-item>内容管理</el-breadcrumb-item>
        <el-breadcrumb-item>分类管理</el-breadcrumb-item>
      </el-breadcrumb>
    </el-row>
    <Category-create @onSubmit="loadCategorys()"></Category-create>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="categorys"
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
          label="分类ID"
          fit>
        </el-table-column>
        <el-table-column
          prop="name"
          label="分类"
         >
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="200">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editCategory(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deleteCategory(scope.row.id)"
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
import CategoryCreate from './CategoryCreate'
export default {
  name: 'CategoryManagement',
  components: {CategoryCreate},
  data () {
    return {
      dialogFormVisible: false,
      categorys: [],
      selectedCategory: []
    }
  },
  mounted () {
    this.loadCategorys()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    loadCategorys () {
      var _this = this
      this.$axios.get('/admin/content/categorys').then(resp => {
        if (resp && resp.status === 200) {
          _this.categorys = resp.data.result
        }
      })
    },
    deleteCategory (id) {
      this.$confirm('此操作将永久删除该分类, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .post('/admin/content/categorys/delete', {id: id}).then(resp => {
            if (resp && resp.status === 200) {
              this.loadCategorys()
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
    editCategory (category) {
      this.dialogFormVisible = true
      this.selectedCategory = category
    },
    onSubmit (category) {
      // 根据视图绑定的角色 id 向后端传送角色信息
      this.$axios.put('/admin/content/categorys/edit', {
        id: category.id,
        name: category.name
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$alert('分类信息修改成功')
          this.dialogFormVisible = false
          this.loadCategorys()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
