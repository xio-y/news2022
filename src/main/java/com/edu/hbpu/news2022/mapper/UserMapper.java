package com.edu.hbpu.news2022.mapper;

import com.edu.hbpu.news2022.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update user set type=#{type} where uid=#{uid}")
    void disable(User u);
}
