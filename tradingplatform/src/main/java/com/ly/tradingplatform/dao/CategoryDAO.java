package com.ly.tradingplatform.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ly.tradingplatform.pojo.Category;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
