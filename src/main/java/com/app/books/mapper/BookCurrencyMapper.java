package com.app.books.mapper;

import com.app.books.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface BookCurrencyMapper {


    /**
     * @return
     */
    @Select("select id,giving_num as givingNum,recharge_num as rechargeNum from t_recharge_config")
    List<Recharge> findRechargeConfig();

    /**
     * @return
     */
    @Select("select giving_num from t_recharge_config where recharge_num=#{amount}")
    Integer findRechargeConfig(Integer amount);

    /**
     * @return
     */
    @Select("UPDATE t_user\n" +
            "set balance=balance-#{balance},book_currency=book_currency+#{bookCurrency}\n" +
            "where id=#{userId}")
    void updateUser(Integer balance,Integer bookCurrency,Integer userId);
}
