package com.fang.mapper;

import com.fang.entity.dto.SongMusicDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 新歌类型 Mapper
 *
 * @author Fang
 */
@Repository
public interface NewMusicTypeMapper {

    /**
     * 根据新歌类型查询对应的歌曲和歌手,获得6条数据
     *
     * @param type 类型
     * @return 返回音乐数据和歌手数据
     */
    List<SongMusicDTO> selectByType(@Param("type") String type);
}
