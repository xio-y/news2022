package com.edu.hbpu.news2022.service;

import com.edu.hbpu.news2022.entity.User;

import java.util.List;

public interface FollowUserService {
    boolean followUser(Long userId,Long followUserId);
    boolean disFollowUser(Long userId,Long followUserId);
    boolean isFollowUser(Long userId,Long followUserId);
    List<User> getFollowUsersByUid(Long userId);
    List<User> getUsersByFollowUid(Long userId);

}
