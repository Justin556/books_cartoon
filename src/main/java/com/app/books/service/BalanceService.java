package com.app.books.service;

import java.math.BigDecimal;

public interface BalanceService {
    void recharge(Integer userId, BigDecimal amount);
}
