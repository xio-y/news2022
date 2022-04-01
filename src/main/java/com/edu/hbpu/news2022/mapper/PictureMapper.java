package com.edu.hbpu.news2022.mapper;

import com.edu.hbpu.news2022.entity.Picture;
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
public interface PictureMapper extends BaseMapper<Picture> {

    @Select("select pid,pic from picture where newsid=#{newsid}")
    List<Picture> getPicListByNewsid(int newsid);

}
