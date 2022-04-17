package com.edu.hbpu.news2022.serviceImpl;

import com.edu.hbpu.news2022.entity.News;
import com.edu.hbpu.news2022.mapper.NewsMapper;
import com.edu.hbpu.news2022.service.NewsService;
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
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {
    @Autowired
    NewsMapper newsMapper;

    @Override
    public News getById(int newsid) {
        return newsMapper.getById(newsid);
    }

    @Override
    public List<News> getVideosByKindId(Integer kindId) {
        return newsMapper.getVideosByKindId(kindId);
    }
}
