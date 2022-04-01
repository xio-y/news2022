package com.edu.hbpu.news2022.service;

public interface FollowUserService {
    boolean followUser(Long userId,Long followUserId);
    boolean disFollowUser(Long userId,Long followUserId);
    boolean isFollowUser(Long userId,Long followUserId);
}
