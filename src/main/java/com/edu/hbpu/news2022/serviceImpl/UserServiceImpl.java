package com.edu.hbpu.news2022.serviceImpl;

import com.edu.hbpu.news2022.entity.User;
import com.edu.hbpu.news2022.mapper.UserMapper;
import com.edu.hbpu.news2022.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public void disable(User u){
        userMapper.disable(u);
    }
}
