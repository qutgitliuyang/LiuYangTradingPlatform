<template>
  <div>
    <el-dialog
      title="修改用户信息"
      :visible.sync="dialogFormVisible">
      <el-form v-model="selectedUser" style="text-align: left" ref="dataForm">
        <el-form-item label="用户名" label-width="120px" prop="username">
          <label>{{selectedUser.username}}</label>
        </el-form-item>
        <el-form-item label="真实名称" label-width="120px" prop="name">
          <el-input v-model="selectedUser.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" label-width="120px" prop="phone">
          <el-input v-model="selectedUser.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" label-width="120px" prop="adress">
          <el-input v-model="selectedUser.adress" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit(selectedUser)">确 定</el-button>
      </div>
    </el-dialog>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="users"
        stripe
        :default-sort = "{order: 'ascending'}"
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          prop="username"
          label="用户名"
          fit>
        </el-table-column>
        <el-table-column
          prop="name"
          label="真实名称"
        >
        </el-table-column>
        <el-table-column
          prop="phone"
          label="电话"
        >
        </el-table-column>
        <el-table-column
          prop="adress"
          label="地址"
        >
        </el-table-column>
        <el-table-column
          label="操作"
          width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="editUser(scope.row)"
              type="text"
              size="small">
              编辑资料
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'PersonalInformation',
  data () {
    return {
      dialogFormVisible: false,
      users: [],
      selectedUser: []
    }
  },
  mounted () {
    this.loadUser()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
  },
  methods: {
    loadUser () {
      var _this = this
      this.$axios.get('/user/' + this.$route.query.id).then(resp => {
        if (resp && resp.status === 200) {
          _this.users = resp.data.result
        }
      })
    },
    editUser (user) {
      this.dialogFormVisible = true
      this.selectedUser = user
    },
    onSubmit (user) {
      // 根据视图绑定的角色 id 向后端传送角色信息
      this.$axios.put('/user/edit', {
        username: user.username,
        name: user.name,
        phone: user.phone,
        adress: user.adress
      }).then(resp => {
        if (resp && resp.status === 200) {
          this.$alert('用户信息修改成功')
          this.dialogFormVisible = false
          // 修改角色后重新请求用户信息，实现视图更新
          this.loadUser()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
