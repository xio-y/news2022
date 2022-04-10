package com.edu.hbpu.news2022.serviceImpl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.edu.hbpu.news2022.entity.FollowUser;
import com.edu.hbpu.news2022.entity.User;
import com.edu.hbpu.news2022.mapper.UserMapper;
import com.edu.hbpu.news2022.service.FollowUserService;
import com.mongodb.client.result.DeleteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowUserServiceImpl implements FollowUserService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    private static final String FOllOW_USER_KEY="FOLLOW_USER_KEY_";

    @Override
    public boolean followUser(Long userId, Long followUserId) {
        FollowUser followUser=new FollowUser();
        followUser.setId(ObjectId.get());
        followUser.setUserId(userId);
        followUser.setFollowUserId(followUserId);
        followUser.setCreated(System.currentTimeMillis());
        mongoTemplate.save(followUser);
        redisTemplate.opsForHash().put(this.getFollowUserKey(userId),followUserId+"",1);
        return true;
    }
    private String getFollowUserKey(Long userId){
        return this.FOllOW_USER_KEY+userId;
    }

    @Override
    public boolean disFollowUser(Long userId, Long followUserId) {

        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("followUserId").is(followUserId));
        DeleteResult result=mongoTemplate.remove(query,FollowUser.class);
        if(result.getDeletedCount()>0){
            redisTemplate.opsForHash().delete(this.getFollowUserKey(userId),followUserId+"");
            return true;
        }
        return false;
    }

    @Override
    public boolean isFollowUser(Long userId, Long followUserId) {
        return redisTemplate.opsForHash().hasKey(this.getFollowUserKey(userId),followUserId+"");
    }

    @Autowired
    UserMapper userMapper;
    @Override
    public List<User> getFollowUsersByUid(Long userId) {
        Query query=Query.query(Criteria.where("userId").is(userId));
        List<FollowUser> list=mongoTemplate.find(query,FollowUser.class);
        List<User> userList=new ArrayList<>();
        for(FollowUser followUser:list){
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.select("uid,username,image").eq("uid",followUser.getFollowUserId());
            User user=userMapper.selectOne(wrapper);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> getUsersByFollowUid(Long followUserId) {
        Query query=Query.query(Criteria.where("followUserId").is(followUserId));
        List<FollowUser> list=mongoTemplate.find(query,FollowUser.class);
        List<User> userList=new ArrayList<>();
        for(FollowUser followUser:list){
            QueryWrapper<User> wrapper=new QueryWrapper<>();
            wrapper.select("uid,username,image").eq("uid",followUser.getUserId());
            User user=userMapper.selectOne(wrapper);
            userList.add(user);
        }
        return userList;
    }
}
