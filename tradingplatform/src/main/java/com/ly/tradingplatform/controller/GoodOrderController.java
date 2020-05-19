package com.ly.tradingplatform.controller;

import com.ly.tradingplatform.pojo.GoodOrder;
import com.ly.tradingplatform.service.GoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class GoodOrderController {
    @Autowired
    GoodOrderService goodOrderService;

    @GetMapping("/api/orders/{buyId}")
    @CrossOrigin
    public List<GoodOrder> list(@PathVariable("buyId") int buyId) throws Exception {
        return goodOrderService.list(buyId);
    }

    @PostMapping("/api/orders")
    @CrossOrigin
    public GoodOrder addOrUpdate(@RequestBody GoodOrder goodOrder) throws Exception {
        goodOrderService.addOrUpdate(goodOrder);
        return goodOrder;
    }
}
