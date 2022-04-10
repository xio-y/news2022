package com.edu.hbpu.news2022.controller;


import com.edu.hbpu.news2022.entity.Chatmsg;
import com.edu.hbpu.news2022.service.ChatmsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WsController {
    @Autowired
    SimpMessagingTemplate messagingTemplate;
    @Autowired
    ChatmsgService chatmsgService;
    @MessageMapping("/chat")
    void chat(Chatmsg msg){
        msg.setDate(LocalDateTime.now());
        chatmsgService.save(msg);
        messagingTemplate.convertAndSendToUser(msg.getToUser(),"/queue/chat",msg);
    }
}
