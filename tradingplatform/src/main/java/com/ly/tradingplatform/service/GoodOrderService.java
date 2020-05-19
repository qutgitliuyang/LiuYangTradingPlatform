package com.ly.tradingplatform.service;

import com.ly.tradingplatform.mapper.GoodOrderMapper;
import com.ly.tradingplatform.pojo.GoodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodOrderService {
    @Autowired
    private GoodOrderMapper goodOrderMapper;

    public List<GoodOrder> list(int buyId) {
        return goodOrderMapper.findAllByBuyerId(buyId);
    }

    public void addOrUpdate(GoodOrder goodOrder) {
        goodOrderMapper.insert(goodOrder);
    }

}
