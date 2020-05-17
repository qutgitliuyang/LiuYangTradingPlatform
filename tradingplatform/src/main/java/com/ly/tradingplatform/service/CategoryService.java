package com.ly.tradingplatform.service;
import com.ly.tradingplatform.pojo.Article;
import com.ly.tradingplatform.pojo.Category;
import com.ly.tradingplatform.dao.CategoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> list() {
        Sort sort = Sort.by(Sort.Direction.ASC, "id");
        return categoryDAO.findAll(sort);
    }

    public Category get(int id) {
        Category c= categoryDAO.findById(id).orElse(null);
        return c;
    }

    public void addOrUpdate(Category category) {
        categoryDAO.save(category);
    }


    public void deleteById(int id) {
        categoryDAO.deleteById(id);
    }
}

