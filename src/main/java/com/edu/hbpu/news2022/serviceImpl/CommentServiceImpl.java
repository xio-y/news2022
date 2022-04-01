package com.edu.hbpu.news2022.serviceImpl;

import com.edu.hbpu.news2022.entity.Comment;
import com.edu.hbpu.news2022.mapper.CommentMapper;
import com.edu.hbpu.news2022.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    //a
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<Comment> getByNewsId(Integer newsId) {
        return commentMapper.getByNewsId(newsId);
    }
}
