package com.edu.hbpu.news2022;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootTest
class News2022ApplicationTests {

    @Test
    void contextLoads() {
    }

    private static final String FOllOW_USER_KEY="FOLLOW_USER_KEY_";
    @Autowired
    private RedisTemplate redisTemplate;
    private String getFollowUserKey(Long userId){
        return this.FOllOW_USER_KEY+userId;
    }
    @Test
    public void test(){
        redisTemplate.opsForHash().put("as","a","b");
        redisTemplate.opsForHash().put(this.getFollowUserKey(new Long(5)),3+"",1);
        System.out.println(this.redisTemplate.opsForHash().hasKey("as","a"));
    }


}
