package com.edu.hbpu.news2022.mapper;

import com.edu.hbpu.news2022.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("SELECT c.commentId,c.content,c.time,u.userName,u.image FROM `comment` c \n"
            +"LEFT JOIN `user` u ON c.uid=u.uid WHERE c.newsId=#{newsId} ORDER BY c.commentId")
    List<Comment> getByNewsId(Integer newsId);
}
