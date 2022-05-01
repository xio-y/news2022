package com.edu.hbpu.news2022.serviceImpl;

import com.edu.hbpu.news2022.entity.Kind;
import com.edu.hbpu.news2022.mapper.KindMapper;
import com.edu.hbpu.news2022.service.KindService;
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
public class KindServiceImpl extends ServiceImpl<KindMapper, Kind> implements KindService {
    @Autowired
    KindMapper kindMapper;

    @Override
    public List<Kind> getNumsByKind() {
        return kindMapper.getNumsByKind();
    }
}
