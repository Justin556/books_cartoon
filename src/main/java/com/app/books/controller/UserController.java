package com.app.books.controller;

import com.app.books.entity.User;
import com.app.books.mapper.UserMapper;
import com.app.books.result.Result;
import com.app.books.service.UserService;
import com.app.books.vo.RegisterParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(tags = "用户-业务接口")
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @ApiOperation(value = "注册接口")
    @PutMapping("register")
    public Result register(@RequestBody @Valid RegisterParams registerParams){
        userService.register(registerParams);
        return Result.success();
    }

    @ApiOperation(value = "校验用户名是否已存在")
    @PutMapping("verifyUserName")
    public Result verifyUserName(String userName){
        Integer userId = userMapper.findUserIdByUserName(userName);
        if (userId != null){
            return Result.error("用户名已存在");
        }
        return Result.success();
    }

    @ApiOperation(value = "登录接口")
    @PutMapping("login")
    public Result login(String userName, String password){
        String msg = userService.login(userName, password);
        if ("error".equals(msg)) {//用户名或密码错误
            return Result.error("用户名或密码错误");
        }
        return Result.success(msg);
    }
}
