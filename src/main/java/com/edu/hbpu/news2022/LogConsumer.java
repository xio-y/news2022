package com.edu.hbpu.news2022;

import com.edu.hbpu.news2022.entity.Log;
import com.edu.hbpu.news2022.entity.User;
import com.edu.hbpu.news2022.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RocketMQMessageListener(topic = "login-log",consumerGroup ="news-log-consumer")
public class LogConsumer implements RocketMQListener<User>{
        @Autowired
        LogService logService;

        @Override
        public void onMessage(User u){
        log.info("Receive message: "+u);
        System.out.println("用户"+u.getUsername()+"登录");
        Log log = new Log();
        log.setUid(u.getUid());
        log.setTime(LocalDateTime.now());
        log.setOperation("登录");
        logService.save(log);
        }
}
