package com.fang.service;

import com.fang.entity.dto.SongMusicDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewMusicService {
    /**
     * 查询新歌,获取6条数据
     *
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectNewMusic();

    /**
     * 根据歌曲类型查询新歌
     *
     * @param type 类型
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectMusicByType(@Param("type") String type);

    /**
     * 查询歌曲类型
     *
     * @return List
     */
    List<SongMusicDTO> selectMusicType();

    /**
     * 根据歌曲id进行查询
     *
     * @param music_id 歌曲id
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectByMusicId(String music_id);
}
