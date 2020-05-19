package com.ly.tradingplatform.mapper;

import com.ly.tradingplatform.pojo.GoodOrder;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodOrderMapper {

    @Select("SELECT * FROM good_order where buyer_Id = #{buyerId}")
    @Results({
            @Result(property = "goodName",  column = "good_Name"),
            @Result(property = "goodPrice", column = "good_Price"),
            @Result(property = "goodSeller", column = "good_Seller"),
            @Result(property = "goodImg", column = "good_Img"),
            @Result(property = "buyerId", column = "buyer_Id"),
            @Result(property = "buyerUsername", column = "buyer_Username"),
            @Result(property = "date", column = "date")
    })
    List<GoodOrder> findAllByBuyerId(int buyId);

    @Insert("INSERT INTO good_order(good_Name,good_Price,good_Seller,good_Img,buyer_Id,buyer_Username,date) " + "VALUES(#{goodName}, #{goodPrice}, #{goodSeller},#{goodImg},#{buyerId},#{buyerUsername},#{date})")
    void insert(GoodOrder goodOrder);

}
