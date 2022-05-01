package com.edu.hbpu.news2022.service;

import com.edu.hbpu.news2022.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
public interface UserService extends IService<User> {
    public void disable(User u);
}
