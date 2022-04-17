package com.edu.hbpu.news2022.service;

import com.edu.hbpu.news2022.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hbpu
 * @since 2022-03-10
 */
public interface NewsService extends IService<News> {
    News getById(int newsid);

    public List<News> getVideosByKindId(Integer kindId);

}
