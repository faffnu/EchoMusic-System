package com.fang.mapper;

import com.fang.entity.dto.MusicAndSingerDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲和歌手中间表 Mapper
 * @author Fang
 */
@Repository
public interface MusicAndSingerMapper {
    /**
     * 根据音乐id获取歌手
     *
     * @param music_id 音乐id
     * @return 音乐和歌手信息
     */
    List<MusicAndSingerDTO> selectByMusicId(@Param("music_id") String music_id);
}
