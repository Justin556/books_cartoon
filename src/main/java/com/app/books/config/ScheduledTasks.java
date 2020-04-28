package com.app.books.config;

import com.app.books.entity.User;
import com.app.books.exception.CustomerException;
import com.app.books.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@Configurable
@EnableScheduling
public class ScheduledTasks {
    @Autowired
    private UserMapper userMapper;
    //每30秒执行一次
    /*@Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime(){
        System.out.println ("Scheduling Tasks Examples: The time is now " + dateFormat ().format (new Date ()));
    }*/

    //在固定时间执行
    @Scheduled(cron = "0 0 1 * * ?")
    public void reportCurrentByCron(){
        //System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + dateFormat ().format (new Date()));
        try {
            List<User> list = userMapper.getVipMaturityList();
            for (User user: list
            ) {
                userMapper.setVipFail(user.getId());
            }
            log.info("**********定时任务执行一次**********");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomerException("定时任务执行失效", 500);
        }
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
}
