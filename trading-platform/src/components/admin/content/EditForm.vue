<template>
  <div style="text-align: left">
    <el-button class="add-button" type="success" @click="dialogFormVisible = true">发布商品</el-button>
    <el-dialog
      title="添加/修改商品"
      :visible.sync="dialogFormVisible"
      @close="clear">
      <el-form v-model="form" style="text-align: left" ref="dataForm">
        <el-form-item label="商品名" :label-width="formLabelWidth" prop="name">
          <el-input v-model="form.name" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="卖家" :label-width="formLabelWidth" prop="seller">
          <el-input v-model="form.seller" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="价格" :label-width="formLabelWidth" prop="price">
          <el-input v-model="form.price" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出售日期" :label-width="formLabelWidth" prop="date">
          <el-input v-model="form.date" autocomplete="off" placeholder="图片 URL"></el-input>
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
            <el-option label="闲置服饰" value="2"></el-option>
            <el-option label="宿舍用品" value="3"></el-option>
            <el-option label="零食小吃" value="4"></el-option>
            <el-option label="代步工具" value="5"></el-option>
            <el-option label="其他好物" value="6"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="状态" :label-width="formLabelWidth" prop="status">
          <el-select v-model="form.status" placeholder="请选择分类">
            <el-option label="出售中" value="1"></el-option>
            <el-option label="已购买" value="2"></el-option>
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
        status: '',
        category: {
          id: '',
          name: ''
        }
      },
      formLabelWidth: '120px'
    }
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
        status: '',
        category: {
          id: '',
          name: ''
        }
      }
    },
    onSubmit () {
      this.$axios
        .post('/goods', {
          id: this.form.id,
          img: this.form.img,
          name: this.form.name,
          seller: this.form.seller,
          date: this.form.date,
          introduction: this.form.introduction,
          price: this.form.price,
          status: this.form.status,
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
  .add-button {
    margin: 18px 0 0 25px;
  }
</style>
