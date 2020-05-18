package com.ly.tradingplatform.dao;
import com.ly.tradingplatform.pojo.Good;
import com.ly.tradingplatform.pojo.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoodDAO extends JpaRepository<Good,Integer> {
    List<Good> findAllByCategory(Category category);
    List<Good> findAllByNameLikeOrSellerLike(String keyword1, String keyword2);
    Good findById(int id);
    List<Good> findByStatus(int status);
}
