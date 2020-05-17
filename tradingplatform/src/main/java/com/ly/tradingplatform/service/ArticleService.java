package com.ly.tradingplatform.service;

import com.ly.tradingplatform.dao.ArticleDAO;
import com.ly.tradingplatform.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    ArticleDAO articleDAO;

    public List<Article> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return articleDAO.findAll(sort);
    }

    public Article findById(int id) {
        return articleDAO.findById(id);
    }

    public void addOrUpdate(Article article) {
        articleDAO.save(article);
    }

    public void delete(int id) {
        articleDAO.deleteById(id);
    }
}
