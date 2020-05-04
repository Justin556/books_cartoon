package com.app.books.mapper;

import com.app.books.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("INSERT INTO t_user(\n" +
            "create_time,\n" +
            "user_name,\n" +
            "password,\n" +
            "user_source,\n" +
            "proxy_id\n" +
            ") \n" +
            "VALUES(\n" +
            "now(),\n" +
            "#{userName},\n" +
            "#{password},\n" +
            "#{userSource},\n" +
            "#{proxyId}\n" +
            ")")
    void insertUser(User user);

    /**
     * 根据用户名查找用户id
     * @param userName
     * @return
     */
    @Select("select id from t_user where user_name = #{userName}")
    Integer findUserIdByUserName(String userName);

    /**
     * 根据用户id查找用户对象
     * @param userId
     * @return
     */
    @Select("select id, \n" +
            "create_time as createTime, \n" +
            "ali_account as aliAccount, \n" +
            "ali_name as aliName, \n" +
            "balance, \n" +
            "book_currency as bookCurrency, \n" +
            "is_vip as isVip, \n" +
            "vip_start_time as vipStartTime, \n" +
            "vip_end_time as vipEndTime, \n" +
            "proxy_id as proxyId, \n" +
            "user_name as userName, \n" +
            "user_source as userSource, \n" +
            "password," +
            "portrait \n" +
            "from t_user where id = #{userId}")
    User findUserById(Integer userId);

    /**
     * 查询当前vip到期用户列表
     * @return
     */
    @Select("select id, \n" +
            "create_time as createTime, \n" +
            "ali_account as aliAccount, \n" +
            "ali_name as aliName, \n" +
            "balance, \n" +
            "book_currency as bookCurrency, \n" +
            "is_vip as isVip, \n" +
            "vip_start_time as vipStartTime, \n" +
            "vip_end_time as vipEndTime, \n" +
            "proxy_id as proxyId, \n" +
            "user_name as userName, \n" +
            "user_source as userSource, \n" +
            "password," +
            "portrait \n" +
            "from t_user where is_vip = 1 and Date (vip_end_time) < now()")
    List<User> getVipMaturityList();

    /**
     * 设置vip失效
     */
    @Update("update t_user set is_vip = 0 where id = #{userId}")
    void setVipFail(Integer userId);

    /**
     * 新增用户分销关系表
     * @param userRetailLevel
     */
    @Insert("INSERT INTO t_user_retail_level(create_time, parent_id, user_id)\n" +
            "VALUES(#{createTime},#{parentId},#{userId})")
    void insertUserRetailLevel(UserRetailLevel userRetailLevel);

    /**
     * 根据用户名和密码查找用户（校验用户名和密码是否正确）
     * @param userName
     * @return
     */
    @Select("select id, \n" +
            "create_time as createTime, \n" +
            "ali_account as aliAccount, \n" +
            "ali_name as aliName, \n" +
            "balance, \n" +
            "book_currency as bookCurrency, \n" +
            "is_vip as isVip, \n" +
            "proxy_id as proxyId, \n" +
            "user_name as userName, \n" +
            "user_source as userSource, \n" +
            "password \n" +
            "from t_user where user_name = #{userName} and password = #{password}")
    User findUserByUserNameAndPass(String userName, String password);

    /**
     * 签到送书币
     * @param signToGive
     * @param userId
     */
    @Update("update t_user set book_currency = book_currency + #{signToGive} where id = #{userId}")
    void addBookCurrency(Integer signToGive, Integer userId);

    /**
     * 打赏减书币
     * @param amount
     * @param userId
     */
    @Update("update t_user set book_currency = book_currency - #{amount} where id = #{userId}")
    void reduceBookCurrency(Integer amount, Integer userId);

    /**
     * 新增用户书币变动记录
     * @param userCurrencyLog
     */
    @Insert("insert into t_user_currency_log(create_time, currency, currency_type, other_user_id, user_id, user_name)\n" +
            "VALUES(#{createTime},#{currency},#{currencyType},#{otherUserId},#{userId},#{userName})")
    void insertUserCurrencyLog(UserCurrencyLog userCurrencyLog);

    /**
     * 获取当前用户最后一次签到的时间
     * @param userId
     * @return
     */
    @Select("SELECT create_time FROM t_user_currency_log\n" +
            "WHERE user_id = #{userId} AND currency_type = 1\n" +
            "ORDER BY create_time DESC\n" +
            "LIMIT 0,1")
    Date getLastDateOfSignIn(Integer userId);

    /**
     * 上传头像
     * @param portrait
     * @param userId
     */
    @Update("update t_user set portrait = #{portrait} where id = #{userId}")
    void upload(String portrait, Integer userId);

    /**
     * 添加反馈建议
     * @param suggest
     */
    @Insert("INSERT INTO t_suggest(create_time, msg, path, tel, user_id, user_name)\n" +
            "VALUES(#{createTime},#{msg},#{path},#{tel},#{userId},#{userName})")
    void addSuggest(Suggest suggest);

    /**
     * 获取分销的上级用户id
     * @param userId
     * @return
     */
    @Select("SELECT parent_id FROM t_user_retail_level WHERE user_id = #{userId}")
    Integer getParentIdByUserId(Integer userId);

    /**
     * 新增：用户分佣记录
     * @param userCentLog
     */
    @Insert("INSERT INTO t_user_cent_log(create_time, \n" +
            "order_fee, \n" +
            "order_no, \n" +
            "out_user_id, \n" +
            "out_user_level, \n" +
            "out_user_level_scale, \n" +
            "out_user_name, \n" +
            "status, \n" +
            "user_id,\n" +
            "user_name, \n" +
            "commission)\n" +
            "VALUES (NOW(),\n" +
            "#{orderFee},\n" +
            "#{orderNo},\n" +
            "#{outUserId},\n" +
            "#{outUserLevel},\n" +
            "#{outUserLevelScale},\n" +
            "#{outUserName},\n" +
            "#{status},\n" +
            "#{userId},\n" +
            "#{userName},\n" +
            "#{commission})")
    void addUserCentLog(UserCentLog userCentLog);

    /**
     * 增加用户的余额
     * @param userId
     * @param amount
     */
    @Update("UPDATE t_user SET update_time = NOW(), balance = balance + #{amount}  \n" +
            "WHERE id = #{userId}")
    void  addUserBalance(Integer userId, BigDecimal amount);

    /**
     * 获取分销配置数据
     * @return
     */
    @Select("SELECT create_time as createTime, \n" +
            "update_time as updateTime, \n" +
            "divided, \n" +
            "level_one as levelOne, \n" +
            "level_one_scale as levelOneScale, \n" +
            "level_three as levelThree, \n" +
            "level_three_scale as levelThreeScale, \n" +
            "level_two as levelTwo, \n" +
            "level_two_scale as levelTwoScale, \n" +
            "retail_store_name as retailStoreName\n" +
            "FROM t_retail_store \n" +
            "LIMIT 0,1")
    RetailStore getRetailStore();
}
