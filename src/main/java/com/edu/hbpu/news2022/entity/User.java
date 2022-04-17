package com.edu.hbpu.news2022.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private transient String checkcode;//注册邮箱验证码
    /**
     * 用户编号
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;

    /**
     * 用户名
     */
    @TableField("userName")
    private String username;

    public String getUsername() {
        return username;
    }

    /**
     * 密码
     */
    @TableField("password")
    private String password;

    /**
     * 头像
     */
    @TableField("image")
    private String image;

    /**
     * 注册时间
     */
    @TableField("regTime")
    private LocalDateTime regTime;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 用户类型
     */
    @TableField("type")
    private Integer type;


}
