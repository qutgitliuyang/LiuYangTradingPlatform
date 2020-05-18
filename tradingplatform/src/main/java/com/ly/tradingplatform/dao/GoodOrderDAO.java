package com.ly.tradingplatform.dao;

import com.ly.tradingplatform.pojo.GoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodOrderDAO extends JpaRepository<GoodOrder,Integer> {
    List<GoodOrder> findAllByBuyerId(int buyId);
}
