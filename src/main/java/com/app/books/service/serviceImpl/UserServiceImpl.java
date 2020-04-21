package com.app.books.service.serviceImpl;

import com.app.books.entity.User;
import com.app.books.entity.UserRetailLevel;
import com.app.books.mapper.UserMapper;
import com.app.books.service.UserService;
import com.app.books.utils.Md5Utils;
import com.app.books.vo.RegisterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(RegisterParams registerParams) {
        User user = new User();
        user.setUserName(registerParams.getUserName());
        user.setPassword(Md5Utils.MD5(registerParams.getPassword()));
        if (registerParams.getAgentId() != null && registerParams.getParentId() == null){//用户来源是代理
            user.setUserSource(0);
            user.setProxyId(registerParams.getAgentId());
        }else if(registerParams.getParentId() != null && registerParams.getAgentId() == null){//用户来源是分销
            user.setUserSource(1);
        }
        user.setCreateTime(new Date());
        userMapper.insertUser(user);
        if (registerParams.getParentId() != null && registerParams.getAgentId() == null) {//用户来源是分销
            Integer userId = userMapper.findUserIdByUserName(registerParams.getUserName());
            //更新用户分销关系表
            userMapper.insertUserRetailLevel(new UserRetailLevel(new Date(), registerParams.getParentId(), userId));
        }
    }
}
