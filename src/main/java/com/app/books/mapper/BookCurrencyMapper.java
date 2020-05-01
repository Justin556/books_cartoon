package com.app.books.mapper;

import com.app.books.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
    Integer findRechargeConfigAmount(Integer amount);

    /**
     * @return
     */
    @Update("UPDATE t_user\n" +
            "set balance=balance-#{balance},book_currency=book_currency+#{bookCurrency}\n" +
            "where id=#{userId}")
    void updateUser(Integer balance,Integer bookCurrency,Integer userId);

    /**
     * @return
     */
    @Select("select vip_year as vipYear from t_web_site")
    List<WebSite> findVIPConfig();

    /**
     * @return
     */
    @Update("UPDATE t_user\n" +
            "            set balance=balance-#{balance},is_vip=1,vip_start_time=NOW(),vip_end_time=DATE_ADD(NOW(), INTERVAL 1 YEAR)\n" +
            "            where id=#{userId}")
    void updateUserVIP(Integer balance,Integer userId);

}
