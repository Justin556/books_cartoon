package com.app.books.mapper;

import com.app.books.entity.User;
import com.app.books.entity.UserCurrencyLog;
import com.app.books.entity.UserRetailLevel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface UserMapper {
    /**
     * 新增用户
     * @param user
     */
    @Insert("INSERT INTO t_user(\n" +
            "create_time,\n" +
            "user_name,\n" +
            "password,\n" +
            "user_source,\n" +
            "proxy_id\n" +
            ") \n" +
            "VALUES(\n" +
            "#{createTime},\n" +
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
            "proxy_id as proxyId, \n" +
            "user_name as userName, \n" +
            "user_source as userSource, \n" +
            "password," +
            "portrait \n" +
            "from t_user where id = #{userId}")
    User findUserById(Integer userId);

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
}
