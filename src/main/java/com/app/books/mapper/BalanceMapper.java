package com.app.books.mapper;

import com.app.books.entity.UserCentLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface BalanceMapper {
    /**
     * 获取用户的分销分佣记录
     * @param userId
     * @return
     */
    @Select("SELECT create_time as createTime,\n" +
            "order_fee as orderFee,\n" +
            "order_no as orderNo,\n" +
            "out_user_id as outUserId,\n" +
            "out_user_level as outUserLevel,\n" +
            "out_user_name as outUserName,\n" +
            "user_id as userId,\n" +
            "user_name as userName,\n" +
            "commission\n" +
            "FROM t_user_cent_log WHERE user_id = #{userId}\n" +
            "ORDER BY create_time DESC")
    List<UserCentLog> getCommissionList(Integer userId);

    /**
     * 获取用户的总佣金
     * @param userId
     * @return
     */
    @Select("SELECT SUM(commission)\n" +
            "FROM t_user_cent_log WHERE user_id = #{userId}")
    BigDecimal getCommissionSum(Integer userId);

    /**
     * 更新用户余额
     * @param userId
     * @param amount
     */
    @Update("UPDATE t_user \n" +
            "SET balance = balance + #{amount}, update_time = NOW()\n" +
            "WHERE id = #{userId}")
    void updateUserBalance(Integer userId, BigDecimal amount);
}
