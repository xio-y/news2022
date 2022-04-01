package com.edu.hbpu.news2022.controller;


import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.hbpu.news2022.entity.News;
import com.edu.hbpu.news2022.entity.User;
import com.edu.hbpu.news2022.service.NewsService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
@RequestMapping("/news2022/news")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @GetMapping("getSwipeNews")
    public IPage<News> getSwipe(){
        Page<News> page=new Page<>(1,5);
        QueryWrapper<News> queryWrapper=new QueryWrapper<News>();
        queryWrapper.select("newsId","pictures").orderByAsc("newsId");
        return newsService.page(page,queryWrapper);
    }

    @GetMapping("/getPageNews")
    public IPage<News> getPage(Page<News> page){
        QueryWrapper<News> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("newsId","pictures","title","time","source").orderByDesc("newsId");
        return newsService.page(page,queryWrapper);
    }

    @GetMapping("/getById")
    public News getById(int newsId){
        return newsService.getById(newsId);
    }
    @GetMapping("/download")
    public byte[] download(String filename) {
        File file = new File(uploadPath + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_ATOM_XML);
        try {
            return FileUtils.readFileToByteArray(file);
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage().getBytes();
        }
    }

    @Value("${web.uploadPath}")
    String uploadPath;
    @PostMapping("/add")
    String add(News news, MultipartFile file){
        if(file.isEmpty()) return "failed";
        String fileName=file.getOriginalFilename();
        String newFileName= UUID.fastUUID().toString(true)
                +"."+ FileNameUtil.extName(fileName);
        File filePath=new File(uploadPath,newFileName);
        try{
            file.transferTo(filePath);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        news.setPictures(newFileName);
        news.setTime(LocalDateTime.now());
        newsService.save(news);
        return "success";
    }

    @GetMapping("getByKindId")
    List<News> getByKindId(int kindId){
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("newsId","pictures","title","time","source")
                .eq("kindId",kindId).orderByDesc("newsId");
        return newsService.list(queryWrapper);
    }
}

