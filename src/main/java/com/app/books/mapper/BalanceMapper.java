package com.app.books.mapper;

import com.app.books.entity.UserBalanceLog;
import com.app.books.entity.UserCentLog;
import org.apache.ibatis.annotations.Insert;
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
            "commission,\n" +
            "(SELECT is_vip FROM t_user WHERE id = t_user_cent_log.user_id) as isVip,\n" +
            "(select portrait from t_user where id = t_user_cent_log.user_id) as portrait \n" +
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

    @Insert("insert into t_user_balance_log(create_time,order_fee,order_no,order_type,status,user_id,user_name)\n" +
            "values(NOW(),#{orderFee},#{orderNo},#{orderType},#{status},#{userId},#{userName})")
    void addUseBalanceLog(UserBalanceLog userBalanceLog);

    /**
     * 获取用户账号明细
     * @param userId
     */
    @Select("SELECT create_time as createTime,\n" +
            "order_fee as orderFee,\n" +
            "order_no as orderNo,\n" +
            "order_type as orderType,\n" +
            "user_id as userId,\n" +
            "user_name as userName\n" +
            "FROM t_user_balance_log WHERE user_id = #{userId}\n" +
            "ORDER BY create_time DESC")
    List<UserBalanceLog> getBalanceByUserId(Integer userId);
}
