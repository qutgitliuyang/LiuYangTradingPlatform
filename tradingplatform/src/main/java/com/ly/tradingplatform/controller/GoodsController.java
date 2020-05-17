package com.ly.tradingplatform.controller;

import com.ly.tradingplatform.pojo.Category;
import com.ly.tradingplatform.pojo.Good;
import com.ly.tradingplatform.service.GoodService;
import com.ly.tradingplatform.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class GoodsController {
    @Autowired
    GoodService goodService ;

    @GetMapping("/api/goods")
    @CrossOrigin
    public List<Good> list() throws Exception {
        return goodService.list();
    }


    @GetMapping("/api/admin/content/goods")
    @CrossOrigin
    public List<Good> adminList() throws Exception {
        return goodService.list();
    }

    @PostMapping("/api/goods")
    @CrossOrigin
    public Good addOrUpdate(@RequestBody Good good) throws Exception {
        goodService.addOrUpdate(good);
        return good;
    }

    @PostMapping("/api/delete")
    @CrossOrigin
    public void delete(@RequestBody Good good) throws Exception {
        goodService.deleteById(good.getId());
    }


    @GetMapping("/api/categories/{cid}/goods")
    @CrossOrigin
    public List<Good> listByCategory(@PathVariable("cid") int cid) throws Exception {
        if (0 != cid) {
            return goodService.listByCategory(cid);
        } else {
            return list();
        }
    }

    @GetMapping("/api/search")
    @CrossOrigin
    public List<Good> searchResult(@RequestParam("keywords") String keywords) {
        // 关键词为空时查询出所有书籍
        if ("".equals(keywords)) {
            return goodService.list();
        } else {
            return goodService.Search(keywords);
        }
    }

    @CrossOrigin
    @PostMapping("api/covers")
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "D:/workspace/img";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8443/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    @GetMapping("/api/good/{id}")
    public Good getOneGood(@PathVariable("id") int id) {
        return goodService.findById(id);
    }
}
