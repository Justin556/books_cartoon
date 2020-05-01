package com.app.books.mapper;

import com.app.books.entity.Agent;
import com.app.books.entity.AgentBalanceLog;
import com.app.books.entity.AgentBuckleLog;
import com.app.books.entity.AgentShareLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface AgentMapper {
    /**
     * 通过id获取代理对象
     * @param agentId
     * @return
     */
    @Select("SELECT id,\n" +
            "create_time as createTime,\n" +
            "update_time as updateTime,\n" +
            "ali_account as aliAccount,\n" +
            "ali_name as aliName,\n" +
            "balance,\n" +
            "gqr_code as gqrCode,\n" +
            "name,\n" +
            "password,\n" +
            "phone,\n" +
            "proxy_nick_name as proxyNickName,\n" +
            "ratio,\n" +
            "separate,\n" +
            "status\n" +
            "FROM t_agent WHERE id= #{agentId}")
    Agent getAgentById(Integer agentId);

    /**
     * 代理增加余额
     * @param agentId
     * @param amount
     */
    @Update("UPDATE t_agent SET update_time = NOW(), balance = balance + #{amount}\n" +
            "WHERE id = #{agentId}")
    void addBalance(Integer agentId, BigDecimal amount);

    /**
     * 新增：代理扣量记录
     * @param agentBuckleLog
     */
    @Insert("INSERT INTO t_agent_buckle_log(create_time, c_money, k_money, order_no, proxy_id, user_id)\n" +
            "VALUES(NOW(),#{cMoney},#{kMoney},#{orderNo},#{proxyId},#{userId})")
    void addAgentBuckleLog(AgentBuckleLog agentBuckleLog);

    /**
     * 新增：代理下面用户充值记录
     * @param agentBalanceLog
     */
    @Insert("INSERT INTO t_agent_balance_log(create_time, money, order_no, proxy_id, status)\n" +
            "VALUES(NOW(),#{money},#{orderNo},#{proxyId},#{status})")
    void addAgentBalanceLog(AgentBalanceLog agentBalanceLog);

    /**
     * 新增：代理佣金分成记录
     * @param agentShareLog
     */
    @Insert("INSERT INTO t_agent_share_log(create_time, c_money, order_no, user_id, proxy_id, f_money)\n" +
            "VALUES(NOW(),#{cMoney},#{orderNo},#{userId},#{proxyId},#{fMoney})")
    void addAgentShareLog(AgentShareLog agentShareLog);
}
