package com.edu.hbpu.news2022.mapper;

import com.edu.hbpu.news2022.entity.News;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

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
public interface NewsMapper extends BaseMapper<News> {

    @Select("SELECT n.*,k.content AS kindName,u.userName,u.image FROM news n \r\n" +
                "LEFT JOIN kind k ON n.kindId=k.kindId \r\n" +
                "LEFT JOIN user u ON n.uid=u.uid WHERE newsId=#{newsid}")
    @Results({
            @Result(property ="picList",column ="newsid",
                        many = @Many(select ="com.edu.hbpu.news2022.mapper.PictureMapper.getPicListByNewsid"))
    })
    News getById(int newsid);

    @Select("SELECT n.title,n.newsid,n.pictures,n.video,n.time,n.source,u.userName,u.image FROM news n "
            + "LEFT JOIN user u ON n.uid=u.uid where n.kindId=#{kindId} and n.type=3" )
    List<News> getVideosByKindId(Integer kindId);

}
