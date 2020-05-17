package com.ly.tradingplatform.controller;

import com.ly.tradingplatform.pojo.Article;
import com.ly.tradingplatform.result.Result;
import com.ly.tradingplatform.result.ResultFactory;
import com.ly.tradingplatform.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody @Valid Article article) {
        articleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }

    @GetMapping("/api/article")
    public Result listArticles() {
        return ResultFactory.buildSuccessResult(articleService.list());
    }

    @GetMapping("/api/admin/content/articles")
    public Result listAdminArticles() {
        return ResultFactory.buildSuccessResult(articleService.list());
    }

    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(articleService.findById(id));
    }

    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        articleService.delete(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }

}
