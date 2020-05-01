package com.app.books.controller;

import com.app.books.config.LoginRequired;
import com.app.books.entity.Suggest;
import com.app.books.entity.User;
import com.app.books.entity.UserCurrencyLog;
import com.app.books.mapper.BalanceMapper;
import com.app.books.mapper.BookCurrencyMapper;
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
import java.io.IOException;
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
    private BookCurrencyMapper bookCurrencyMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SettingMapper settingMapper;
    @Autowired
    private BalanceMapper balanceMapper;

    @ApiOperation(value = "注册")
    @PutMapping("register")
    public Result register(@RequestBody RegisterParams registerParams){
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
    @LoginRequired
    public Result quit(HttpServletRequest request){
        String token = request.getHeader("token");
        redisUtil.del(token);
        return Result.success();
    }

    @ApiOperation(value = "签到")
    @GetMapping("signIn")
    @LoginRequired
    public Result signIn(HttpServletRequest request){
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user = userMapper.findUserById(userId);
        Integer signToGive = settingMapper.getWebSite().getSignToGive();
        userService.signIn(signToGive, user);
        return Result.success();
    }

    @ApiOperation(value = "个人中心")
    @GetMapping("personalCenter")
    @LoginRequired
    public Result personalCenter(HttpServletRequest request){
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user = userMapper.findUserById(userId);
        user.setSignCurrency(settingMapper.getWebSite().getSignToGive());//签到将赠送的书币
        user.setCommission(balanceMapper.getCommissionSum(userId));
        //校验今天是否已签到
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date lastSignIn = userMapper.getLastDateOfSignIn(userId);
        if (lastSignIn == null) {
            user.setIsSignIn(0);//未签到
        }else {
            if (sdf.format(new Date()).equals(sdf.format(lastSignIn))) {//如果是同一天，说明今天已签到
                user.setIsSignIn(1);
            }else {
                user.setIsSignIn(0);
            }
        }
        return Result.success(user);
    }


    @ApiOperation(value = "上传图片")
    @PostMapping("upload")
    @LoginRequired
    public Result upload(HttpServletRequest request,String portrait) throws IOException {
        userMapper.upload(portrait, (Integer) redisUtil.get(request.getHeader("token")));
        return Result.success();
    }

    @ApiOperation(value = "建议反馈")
    @PostMapping("suggest")
    @LoginRequired
    public Result suggest(HttpServletRequest request, String msg, String tel) throws IOException {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        String userName = userMapper.findUserById(userId).getUserName();
        Suggest suggest = new Suggest();
        suggest.setCreateTime(new Date());
        suggest.setMsg(msg);
        suggest.setTel(tel);
        suggest.setUserId(userId);
        suggest.setUserName(userName);
        userMapper.addSuggest(suggest);
        return Result.success();
    }
    /**
     * 充值书币列表
     */
    @GetMapping("chongBookCurrencyList")
    @ApiOperation(value = "充值书币列表")
    public Result chongBookCurrencyList()
    {
        return Result.success(bookCurrencyMapper.findRechargeConfig());
    }

    /**
     * 充值VIP列表
     */
    @GetMapping("chongVIPList")
    @ApiOperation(value = "充值VIP列表")
    public Result chongVIPList()
    {
        return Result.success(bookCurrencyMapper.findVIPConfig());
    }

    /**
     * 充值VIP
     */
    @GetMapping("chongVIP")
    @ApiOperation(value = "充值VIP")
    @LoginRequired
    public Result chongVIP(HttpServletRequest request, Integer amount)
    {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        User user=userMapper.findUserById(userId);
        if(user.getBalance().intValue()<amount){
            return Result.error("余额不足");
        }
        if(user.getIsVip()==1&&user.getIsVip().equals(1)){
            bookCurrencyMapper.xuUserVIP(amount,userId);
        }else{
            bookCurrencyMapper.updateUserVIP(amount,userId);
        }


        return Result.success();
    }


    /**
     * 充值书币
     */
    @GetMapping("chongBookCurrency")
    @ApiOperation(value = "充值书币")
    @LoginRequired
    public Result chongBookCurrency(HttpServletRequest request, Integer amount)
    {
        Integer userId = (Integer) redisUtil.get(request.getHeader("token"));
        if(userMapper.findUserById(userId).getBalance().intValue()<amount){
            return Result.error("余额不足");
        }
        Integer givingNum = bookCurrencyMapper.findRechargeConfigAmount(amount);
        bookCurrencyMapper.updateUser(amount,amount*100+givingNum,userId);
        UserCurrencyLog userCurrencyLog = new UserCurrencyLog();
        userCurrencyLog.setCurrencyType(2);
        userCurrencyLog.setUserId(userId);
        userCurrencyLog.setCurrency(amount*100+givingNum);
        String userName = userMapper.findUserById(userId).getUserName();
        userCurrencyLog.setUserName(userName);
        userMapper.insertUserCurrencyLog(userCurrencyLog);
        return Result.success();
    }
}
