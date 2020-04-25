package com.app.books.controller;

import com.app.books.entity.User;
import com.app.books.entity.WebSite;
import com.app.books.mapper.SettingMapper;
import com.app.books.mapper.UserMapper;
import com.app.books.result.Result;
import com.app.books.service.UserService;
import com.app.books.utils.RedisUtil;
import com.app.books.vo.RegisterParams;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Api(tags = "用户-业务接口")
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SettingMapper settingMapper;

    @ApiOperation(value = "注册")
    @PutMapping("register")
    public Result register(@RequestBody @Valid RegisterParams registerParams){
        Integer userId = userMapper.findUserIdByUserName(registerParams.getUserName());
        if (userId != null){
            return Result.error("用户名已存在");
        }
        userService.register(registerParams);
        return Result.success();
    }

    @ApiOperation(value = "校验用户名是否已存在")
    @GetMapping("verifyUserName")
    public Result verifyUserName(String userName){
        Integer userId = userMapper.findUserIdByUserName(userName);
        if (userId != null){
            return Result.error("用户名已存在");
        }
        return Result.success();
    }

    @ApiOperation(value = "登录")
    @GetMapping("login")
    public Result login(String userName, String password){
        String msg = userService.login(userName, password);
        if ("error".equals(msg)) {//用户名或密码错误
            return Result.error("用户名或密码错误");
        }
        return Result.success(msg);
    }

    @ApiOperation(value = "退出登录")
    @GetMapping("quit")
    public Result quit(HttpServletRequest request){
        String token = request.getHeader("token");
        redisUtil.del(token);
        return Result.success();
    }

    @ApiOperation(value = "签到")
    @GetMapping("signIn")
    public Result signIn(HttpServletRequest request){
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user = userMapper.findUserById(userId);
        Integer signToGive = settingMapper.getWebSite().getSignToGive();
        userService.signIn(signToGive, user);
        return Result.success();
    }

    @ApiOperation(value = "个人中心")
    @GetMapping("personalCenter")
    public Result personalCenter(HttpServletRequest request){
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user = userMapper.findUserById(userId);
        //校验今天是否已签到
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lastSignIn = userMapper.getLastDateOfSignIn(userId);
        if (sdf.format(new Date()).equals(sdf.format(lastSignIn))) {//如果是同一天，说明今天已签到
            user.setIsSignIn(1);
            return Result.success(user);
        }
        user.setIsSignIn(0);//未签到
        return Result.success(user);
    }
}
