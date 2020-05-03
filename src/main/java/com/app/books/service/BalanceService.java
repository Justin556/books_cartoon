package com.app.books.service;

import com.app.books.entity.Book;
import com.app.books.entity.UserBalanceLog;
import com.app.books.entity.UserCentLog;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;

public interface BalanceService {
    void recharge(Integer userId, BigDecimal amount);

    /**
     * 获取佣金列表分页
     * @param userId
     * @return
     */
    PageInfo<UserCentLog> getCommissionPage(Integer userId, Integer pageNumber, Integer pageSize);

    /**
     * 获取账户明细分页
     * @param userId
     * @return
     */
    PageInfo<UserBalanceLog> getBalanceByUserId(Integer userId, Integer pageNumber, Integer pageSize);
}
