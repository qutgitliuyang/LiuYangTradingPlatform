<template>
  <div style="margin-top: 40px">
    <div class="articles-area">
      <el-card style="text-align: left">
        <div v-for="article in articles.slice((currentPage-1)*pageSize,currentPage*pageSize)" :key="article.id">
          <div style="float:left;width:85%;height: 150px;">
            <router-link class="article-link" :to="{path:'articles/articleDetail',query:{id: article.id}}"><span style="font-size: 20px"><strong>{{article.articleTitle}}</strong></span></router-link>
            <el-divider content-position="left">{{article.articleDate}}</el-divider>
            <router-link class="article-link" :to="{path:'articles/articleDetail',query:{id: article.id}}"><p>{{article.articleAbstract}}</p></router-link>
          </div>
          <el-image
            style="margin:18px 0 0 30px;width:100px;height: 100px"
            :src="article.articleCover"
            fit="cover"></el-image>
          <el-divider></el-divider>
        </div>
      </el-card>
    </div>
    <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      :current-page="currentPage"
      @current-change="handleCurrentChange"
      :page-size="pageSize"
      :total="articles.length">
    </el-pagination>
  </div>
</template>

<script>
export default {
  name: 'Articles',
  data () {
    return {
      articles: [],
      currentPage: 1,
      pageSize: 4,
      total: 0
    }
  },
  mounted () {
    this.loadArticles()
  },
  methods: {
    loadArticles () {
      var _this = this
      this.$axios.get('/article').then(resp => {
        if (resp && resp.status === 200) {
          _this.articles = resp.data.result
        }
      })
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
      console.log(this.currentPage)
    }
  }
}
</script>

<style scoped>
  .articles-area {
    width: 990px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }

  .article-link {
    text-decoration: none;
    color: #606266;
  }

  .article-link:hover {
    color: #409EFF;
  }
</style>
