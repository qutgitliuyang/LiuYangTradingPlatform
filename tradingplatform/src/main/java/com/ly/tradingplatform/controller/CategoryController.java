package com.ly.tradingplatform.controller;

import com.ly.tradingplatform.pojo.Category;
import com.ly.tradingplatform.pojo.Good;
import com.ly.tradingplatform.result.Result;
import com.ly.tradingplatform.result.ResultFactory;
import com.ly.tradingplatform.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/api/admin/content/categorys")
    @CrossOrigin
    public Result list(){
        return ResultFactory.buildSuccessResult(categoryService.list());
    }

    @PostMapping("/api/admin/content/categorys/add")
    public Result addRole(@RequestBody Category category) {
        categoryService.addOrUpdate(category);
        return ResultFactory.buildSuccessResult("添加分类成功");
    }

    @PutMapping("/api/admin/content/categorys/edit")
    public Result editRole(@RequestBody Category category) {
        categoryService.addOrUpdate(category);
        return ResultFactory.buildSuccessResult("修改分类信息成功");
    }

    @PostMapping("/api/admin/content/categorys/delete")
    @CrossOrigin
    public void delete(@RequestBody Category category) throws Exception {
        categoryService.deleteById(category.getId());
    }
}
