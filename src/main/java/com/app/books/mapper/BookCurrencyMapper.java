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
    @Select("select giving_num as givingNum,recharge_num as rechargeNum from t_recharge_config")
    List<Recharge> findRechargeConfig();


}
