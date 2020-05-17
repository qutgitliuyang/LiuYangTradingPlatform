package com.ly.tradingplatform.dao;

import com.ly.tradingplatform.pojo.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleDAO extends JpaRepository<Article,Integer> {
    Article findById(int id);
}
