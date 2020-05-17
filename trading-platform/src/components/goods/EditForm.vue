<template>
  <div>
    <i class="el-button"  @click="dialogFormVisible = true">发布商品</i>
    <el-dialog
      title="添加/修改商品"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form v-model="form" style="text-align: left" ref="dataForm">
        <el-form-item label="商品名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="价格" :label-width="formLabelWidth" prop="price">
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="照片" :label-width="formLabelWidth" prop="img">
          <el-input v-model="form.img" autocomplete="off" placeholder="图片 URL"></el-input>
          <img-upload @onUpload="uploadImg" ref="imgUpload"></img-upload>
        </el-form-item>
        <el-form-item label="简介" :label-width="formLabelWidth" prop="introduction">
          <el-input type="textarea" v-model="form.introduction" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类" :label-width="formLabelWidth" prop="cid">
          <el-select v-model="form.category.id" placeholder="请选择分类">
            <el-option label="文学书籍" value="1"></el-option>
            <el-option label="流行" value="2"></el-option>
            <el-option label="文化" value="3"></el-option>
            <el-option label="生活" value="4"></el-option>
            <el-option label="经管" value="5"></el-option>
            <el-option label="科技" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="id" style="height: 0px">
          <el-input type="hidden" v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="height: 40px">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="onSubmit">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import ImgUpload from './ImgUpload'
export default {
  name: 'EditForm',
  components: {ImgUpload},
  data () {
    return {
      dialogFormVisible: false,
      form: {
        id: '',
        name: '',
        seller: '',
        date: '',
        introduction: '',
        price: '',
        img: '',
        category: {
          id: '',
          name: ''
        }
      },
      user: {
        id: '',
        username: '',
        name: ''
      },
      formLabelWidth: '120px'
    }
  },
  mounted: function () {
    this.getCurrentUser()
  },
  methods: {
    clear () {
      this.form = {
        id: '',
        name: '',
        seller: '',
        date: '',
        introduction: '',
        price: '',
        img: '',
        category: {
          id: '',
          name: ''
        }
      }
    },
    getCurrentUser () {
      var _this = this
      this.$axios.get('/getCurrentUser').then(resp => {
        _this.user.username = resp.data.result.username
      })
    },
    onSubmit () {
      this.$axios
        .post('/goods', {
          id: this.form.id,
          img: this.form.img,
          name: this.form.name,
          seller: this.user.username,
          date: new Date(),
          introduction: this.form.introduction,
          price: this.form.price,
          category: this.form.category
        }).then(resp => {
          if (resp && resp.status === 200) {
            this.dialogFormVisible = false
            this.$emit('onSubmit')
          }
        })
    },
    uploadImg () {
      this.form.img = this.$refs.imgUpload.url
    }
  }
}
</script>

<style scoped>
  .el-button {
    margin: 0 0 0 50px;
    font-size: 15px;
    float: right;
  }
</style>
