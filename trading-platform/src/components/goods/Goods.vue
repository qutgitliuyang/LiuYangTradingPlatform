<template>
  <div>
    <el-row style="height: 840px;">
      <div style="display: flex">
        <search-bar @onSearch="searchResult" ref="searchBar" ></search-bar>
        <edit-form @onSubmit="loadGoods()" ref="edit"></edit-form>
      </div>
      <el-tooltip effect="dark" placement="right"
                  v-for="item in goods.slice((currentPage-1)*pagesize,currentPage*pagesize)"
                  :key="item.id">
        <p slot="content" style="font-size: 14px;margin-bottom: 6px;">{{item.name}}</p>
        <p slot="content" style="font-size: 13px;margin-bottom: 6px">
          <span>{{item.seller}}</span> /
          <span>{{item.date}}</span> /
          <span>{{item.price}}</span>
        </p>
        <p slot="content" style="width: 300px" class="abstract">{{item.introduction}}</p>
        <el-card style="width: 135px;margin-bottom: 20px;height: 233px;float: left;margin-right: 15px" class="good"
                 bodyStyle="padding:10px" shadow="hover">
          <div class="cover">
            <router-link  :to="{path:'goods/goodsDetail',query:{id: item.id}}"><img :src="item.img" alt="封面"></router-link>
          </div>
          <div class="info">
            <div class="title">
              <a>{{item.name}}</a>
            </div>
          </div>
          <div class="author">{{item.seller}}</div>
        </el-card>
      </el-tooltip>

    </el-row>
    <el-row>
      <el-pagination
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        :total="goods.length">
      </el-pagination>
    </el-row>
  </div>
</template>

<script>
import EditForm from './EditForm'
import SearchBar from './SearchBar'
export default {
  name: 'Goods',
  components: {EditForm, SearchBar},
  data () {
    return {
      goods: [],
      currentPage: 1,
      pagesize: 17
    }
  },
  mounted: function () {
    this.loadGoods()
  },
  methods: {
    loadGoods () {
      var _this = this
      this.$axios.get('/goods').then(resp => {
        if (resp && resp.status === 200) {
          _this.goods = resp.data
        }
      })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
      console.log(this.currentPage)
    },
    searchResult () {
      var _this = this
      this.$axios
        .get('/search?keywords=' + this.$refs.searchBar.keywords, {
        }).then(resp => {
          if (resp && resp.status === 200) {
            _this.goods = resp.data
          }
        })
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
    }
  }
}
</script>

<style scoped>
  .cover {
    width: 115px;
    height: 172px;
    margin-bottom: 7px;
    overflow: hidden;
    cursor: pointer;
  }

  img {
    width: 115px;
    height: 172px;
    /*margin: 0 auto;*/
  }

  .title {
    font-size: 14px;
    text-align: left;
  }

  .author {
    color: #333;
    width: 102px;
    font-size: 13px;
    margin-bottom: 6px;
    text-align: left;
  }

  .abstract {
    display: block;
    line-height: 17px;
  }
  .el-icon-delete {
    cursor: pointer;
    float: right;
  }

  .switch {
    display: flex;
    position: absolute;
    left: 780px;
    top: 25px;
  }

  a {
    text-decoration: none;
  }

  a:link, a:visited, a:focus {
    color: #3377aa;
  }
</style>
