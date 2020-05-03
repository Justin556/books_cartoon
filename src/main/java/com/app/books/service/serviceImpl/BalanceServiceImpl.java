package com.app.books.service.serviceImpl;

import com.app.books.entity.*;
import com.app.books.exception.CustomerException;
import com.app.books.mapper.AgentMapper;
import com.app.books.mapper.BalanceMapper;
import com.app.books.mapper.SettingMapper;
import com.app.books.mapper.UserMapper;
import com.app.books.service.BalanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {
    @Autowired
    private BalanceMapper balanceMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AgentMapper agentMapper;

    @Override
    @Transactional
    public void recharge(Integer userId, BigDecimal amount) {
        balanceMapper.updateUserBalance(userId, amount);//用户余额增加
        String orderNo = "NO" + System.currentTimeMillis();
        User user = userMapper.findUserById(userId);
        UserBalanceLog userBalanceLog = new UserBalanceLog();
        userBalanceLog.setOrderFee(amount);
        userBalanceLog.setOrderNo(orderNo);
        userBalanceLog.setOrderType("1");
        userBalanceLog.setUserId(userId);
        userBalanceLog.setUserName(user.getUserName());
        balanceMapper.addUseBalanceLog(userBalanceLog);
        if (user.getUserSource() != null && user.getUserSource() == 0){//如果上级是代理
            Agent agent = agentMapper.getAgentById(user.getProxyId());
            BigDecimal ratio = new BigDecimal(Float.toString(agent.getRatio()));//扣除比例
            BigDecimal separate = new BigDecimal(Float.toString(agent.getSeparate()));//分成比例
            BigDecimal ratioAmount = amount.multiply(ratio);//扣量
            BigDecimal separateAmount = separate.multiply(amount.subtract(ratioAmount));//分佣
            agentMapper.addBalance(user.getProxyId(), separateAmount);
            AgentBuckleLog agentBuckleLog = new AgentBuckleLog();
            agentBuckleLog.setProxyId(user.getProxyId());
            agentBuckleLog.setOrderNo(orderNo);
            agentBuckleLog.setUserId(userId);
            agentBuckleLog.setCMoney(amount);
            agentBuckleLog.setKMoney(ratioAmount);
            agentMapper.addAgentBuckleLog(agentBuckleLog);
            AgentBalanceLog agentBalanceLog = new AgentBalanceLog();
            agentBalanceLog.setProxyId(user.getProxyId());
            agentBalanceLog.setOrderNo(orderNo);
            agentBalanceLog.setMoney(amount);
            agentBalanceLog.setUserId(user.getId());
            agentMapper.addAgentBalanceLog(agentBalanceLog);
            AgentShareLog agentShareLog = new AgentShareLog();
            agentShareLog.setProxyId(user.getProxyId());
            agentShareLog.setOrderNo(orderNo);
            agentShareLog.setCMoney(amount);
            agentShareLog.setFMoney(separateAmount);
            agentShareLog.setUserId(userId);
            agentMapper.addAgentShareLog(agentShareLog);
        }else if (user.getUserSource() != null && user.getUserSource() == 1){//上级是分销
            findParent(userId, amount, orderNo, 1);
        }
    }

    public void findParent(Integer userId, BigDecimal amount, String orderNo, int i) {
        Integer parentId = userMapper.getParentIdByUserId(userId);
        if (parentId != null){
            RetailStore retailStore = userMapper.getRetailStore();
            String level = "";//用户分佣级别
            Float levelScale = null;//分成比例
            if (i==1){
                level = retailStore.getLevelOne();
                levelScale = retailStore.getLevelOneScale();
            }else if(i==2){
                level = retailStore.getLevelTwo();
                levelScale = retailStore.getLevelTwoScale();
            }else if (i==3){
                level = retailStore.getLevelThree();
                levelScale = retailStore.getLevelThreeScale();
            }else if(i==4){
                throw new CustomerException("success", 200);
            }

            UserCentLog userCentLog = new UserCentLog();
            userCentLog.setUserId(parentId);//获佣用户的id
            userCentLog.setUserName(userMapper.findUserById(parentId).getUserName());
            userCentLog.setOutUserId(userId);
            userCentLog.setOutUserName(userMapper.findUserById(userId).getUserName());
            userCentLog.setOutUserLevel(level);
            userCentLog.setOutUserLevelScale(levelScale);
            userCentLog.setOrderNo(orderNo);
            userCentLog.setOrderFee(amount);
            BigDecimal separate = new BigDecimal(Float.toString(levelScale));//分成比例
            BigDecimal commission = amount.multiply(separate);//分佣
            userCentLog.setCommission(commission);
            userMapper.addUserCentLog(userCentLog);
            userMapper.addUserBalance(parentId, commission);//父级的余额增加佣金的金额
            i++;
            findParent(parentId, amount, orderNo, i);
        }
    }

    @Override
    public PageInfo<UserCentLog> getCommissionPage(Integer userId, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<UserCentLog> list = balanceMapper.getCommissionList(userId);
        PageInfo<UserCentLog> pageInfo = new PageInfo<UserCentLog>(list);
        return pageInfo;
    }

    @Override
    public PageInfo<UserBalanceLog> getBalanceByUserId(Integer userId, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<UserBalanceLog> list = balanceMapper.getBalanceByUserId(userId);
        PageInfo<UserBalanceLog> pageInfo = new PageInfo<UserBalanceLog>(list);
        return pageInfo;
    }
}
