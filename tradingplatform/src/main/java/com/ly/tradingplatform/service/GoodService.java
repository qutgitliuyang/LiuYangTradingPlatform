package com.ly.tradingplatform.service;

import com.ly.tradingplatform.dao.GoodDAO;
import com.ly.tradingplatform.pojo.Good;
import com.ly.tradingplatform.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodService {
    @Autowired
    GoodDAO goodDAO;
    @Autowired
    CategoryService categoryService;

    public List<Good> list() {
        //Sort sort = Sort.by(Sort.Direction.DESC, "id");
        int status = 1;
        return goodDAO.findByStatus(status);
    }

    public List<Good> adminlist() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return goodDAO.findAll();
    }

    public void addOrUpdate(Good good) {
        goodDAO.save(good);
    }

    public void deleteById(int id) {
        goodDAO.deleteById(id);
    }

    public List<Good> listByCategory(int cid) {
        Category category = categoryService.get(cid);
        return goodDAO.findAllByCategory(category);
    }

    public List<Good> Search(String keywords) {
        return goodDAO.findAllByNameLikeOrSellerLike('%' + keywords + '%', '%' + keywords + '%');
    }

    public Good findById(int id) {
        return goodDAO.findById(id);
    }
}
