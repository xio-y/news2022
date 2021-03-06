package com.edu.hbpu.news2022.controller;


import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.hbpu.news2022.config.MailService;
import com.edu.hbpu.news2022.entity.User;
import com.edu.hbpu.news2022.service.UserService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/news2022/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @GetMapping("/list")
    public List<User> list(){
        return userService.list();
    }

    @GetMapping("/page")
    public IPage<User> page(Page<User> page){
        return userService.page(page);
    }

    @GetMapping("/disable")
    void disable(User u){
        userService.disable(u);
    }
    @PostMapping("/checkUsername")
    String checkUsername(@RequestBody User u){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("username",u.getUsername());
        if(userService.getOne(wrapper)!=null) return "exist";
        return "error";
    }
    @Value("${web.uploadPath}")
    String uploadPath;
    @PostMapping("/regist")
    String regist(User u, MultipartFile imgFile){
        String checkcode=u.getCheckcode();
        String code= (String) redisTemplate.opsForValue().get(u.getEmail());
        System.out.println(checkcode+"  "+code+"  "+u.getEmail());
        if(!checkcode.equals(code)) return "failed";
        if(imgFile.isEmpty()) return "failed";
        String fileName=imgFile.getOriginalFilename();
        String newFileName= UUID.fastUUID().toString(true)+"."+ FileNameUtil.extName(fileName);
        File filePath=new File(uploadPath,newFileName);
        try{
            imgFile.transferTo(filePath);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        u.setImage(newFileName);
        u.setRegTime(LocalDateTime.now());
        u.setType(1);
        userService.save(u);
        return "success";
    }

    @PostMapping("/login")
    User login(@RequestBody User u){
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.select("username,image,uid,type").eq("username",u.getUsername()).eq("password",u.getPassword());
        User user=userService.getOne(wrapper);
        if(user!=null){
            if(user.getType()==0) return null;
            else this.rocketMQTemplate.convertAndSend("login-log",user);
        }
        return user;
    }

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    MailService mailService;
    @PostMapping("/sendMail")
    void sendMail(@RequestBody User u){
        String email=u.getEmail();
        String str= RandomUtil.randomString(3);
        redisTemplate.opsForValue().set(email,str, Duration.ofMinutes(2));
        mailService.send(email,"验证码",str);
    }
}

