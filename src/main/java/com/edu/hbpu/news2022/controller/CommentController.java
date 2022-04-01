package com.edu.hbpu.news2022.controller;


import com.edu.hbpu.news2022.entity.Comment;
import com.edu.hbpu.news2022.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
@RestController
@RequestMapping("/news2022/comment")
public class CommentController {


    @Autowired
    CommentService commentService;
    @GetMapping("/getByNewsid")
    List<Comment> getByNewsId(Integer newsId) {
        return  commentService.getByNewsId(newsId);
    }

    @PostMapping("/add")
    void add(@RequestBody Comment c){
        c.setTime(LocalDateTime.now());
        commentService.save(c);
    }

}

