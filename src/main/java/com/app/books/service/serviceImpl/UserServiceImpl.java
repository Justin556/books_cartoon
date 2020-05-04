package com.app.books.service.serviceImpl;

import com.app.books.entity.User;
import com.app.books.entity.UserCurrencyLog;
import com.app.books.entity.UserRetailLevel;
import com.app.books.mapper.UserMapper;
import com.app.books.service.UserService;
import com.app.books.utils.JWTUtil;
import com.app.books.utils.Md5Utils;
import com.app.books.utils.RedisUtil;
import com.app.books.vo.RegisterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 注册
     * @param registerParams
     */
    @Override
    public void register(RegisterParams registerParams) {
        User user = new User();
        user.setUserName(registerParams.getUserName());
        user.setPassword(Md5Utils.MD5(registerParams.getPassword()));
        if (registerParams.getType() != null) {
            if (registerParams.getType() == 1){//用户来源是代理
                user.setUserSource(0);
                user.setProxyId(registerParams.getId());
            }else if(registerParams.getType() == 2){//用户来源是分销
                user.setUserSource(1);
            }
        }
        userMapper.insertUser(user);
        if (registerParams.getType() != null && registerParams.getType() == 2) {//用户来源是分销
            //更新用户分销关系表
            userMapper.insertUserRetailLevel(new UserRetailLevel(new Date(), registerParams.getId(), user.getId()));
        }
    }

    @Override
    public String login(String userName, String password) {
        User user = userMapper.findUserByUserNameAndPass(userName, Md5Utils.MD5(password));
        if (user == null) {//用户名或密码错误
            return "error";
        }
        String token = JWTUtil.sign(userName);
        redisUtil.set(token, user.getId());
        return token;
    }

    @Override
    public void signIn(Integer signToGive, User user) {
        userMapper.addBookCurrency(signToGive, user.getId());//用户增加书币
        UserCurrencyLog userCurrencyLog = new UserCurrencyLog();
        userCurrencyLog.setCreateTime(new Date());
        userCurrencyLog.setUserId(user.getId());
        userCurrencyLog.setUserName(user.getUserName());
        userCurrencyLog.setCurrencyType(1);
        userCurrencyLog.setCurrency(signToGive);
        userMapper.insertUserCurrencyLog(userCurrencyLog);//添加书币记录
    }
}
