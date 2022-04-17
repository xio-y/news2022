package com.edu.hbpu.news2022.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    String fromMail;
    public void send(String to,String subject,String content){
        SimpleMailMessage msg=new SimpleMailMessage();
        msg.setFrom(fromMail);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(content);
        javaMailSender.send(msg);
    }
}
