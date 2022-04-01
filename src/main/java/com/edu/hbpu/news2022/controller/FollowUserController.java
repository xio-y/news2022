package com.edu.hbpu.news2022.controller;


import com.edu.hbpu.news2022.service.FollowUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news2022/followUser")
public class FollowUserController {
    @Autowired
    FollowUserService followUserService;

    @GetMapping("/followUser")
    boolean followUser(Long userId,Long followUserId){
        return followUserService.followUser(userId,followUserId);
    }
    @GetMapping("/disFollowUser")
    boolean disFollowUser(Long userId,Long followUserId){
        return  followUserService.disFollowUser(userId,followUserId);
    }
    @GetMapping("/isFollowUser")
    boolean isFollowUser(Long userId,Long followUserId){
        return  followUserService.isFollowUser(userId,followUserId);
    }
}
