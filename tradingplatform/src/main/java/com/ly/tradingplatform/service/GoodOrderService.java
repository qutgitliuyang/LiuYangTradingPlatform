package com.ly.tradingplatform.service;

import com.ly.tradingplatform.dao.GoodOrderDAO;
import com.ly.tradingplatform.pojo.GoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodOrderService {
    @Autowired
    GoodOrderDAO goodOrderDAO;

    public List<GoodOrder> list(int buyId) {
        return goodOrderDAO.findAllByBuyerId(buyId);
    }

    public void addOrUpdate(GoodOrder goodOrder) {
        goodOrderDAO.save(goodOrder);
    }

    public void deleteById(int id) {
        goodOrderDAO.deleteById(id);
    }

}
