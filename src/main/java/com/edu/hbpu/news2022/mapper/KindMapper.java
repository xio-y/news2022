package com.edu.hbpu.news2022.mapper;

import com.edu.hbpu.news2022.entity.Kind;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lombok.Setter;
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
public interface KindMapper extends BaseMapper<Kind> {
    @Select("SELECT COUNT(n.newsId) as nums,k.content from news n "
            + "LEFT JOIN kind k on n.kindId=k.kindId GROUP BY n.kindId ")
    List<Kind> getNumsByKind();
}
