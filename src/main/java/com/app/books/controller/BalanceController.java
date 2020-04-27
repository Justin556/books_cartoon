package com.app.books.controller;

import com.app.books.config.LoginRequired;
import com.app.books.mapper.BalanceMapper;
import com.app.books.result.Result;
import com.app.books.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "资金账户-业务接口")
@RequestMapping("/account/")
public class BalanceController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BalanceMapper balanceMapper;

    @GetMapping("getCommissionList")
    @ApiOperation(value = "用户分销佣金列表")
    @LoginRequired
    public Result getCommissionList(HttpServletRequest request) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        return Result.success(balanceMapper.getCommissionList(userId));
    }

    @GetMapping("getCommissionSum")
    @ApiOperation(value = "用户分销总佣金")
    @LoginRequired
    public Result getCommissionSum(HttpServletRequest request) {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        return Result.success(balanceMapper.getCommissionSum(userId));
    }
}
