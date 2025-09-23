package com.fang.mapper;

import com.fang.entity.dto.SongMusicDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 新歌 Mapper
 *
 * @author Fang
 */
@Repository
public interface NewMusicMapper {
    /**
     * 查询新歌,获取6条数据
     *
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectNewMusic();

    /**
     * 根据歌曲的id查询歌曲信息，以及歌手信息
     *
     * @param music_id 音乐id
     * @return 歌曲信息及歌手
     */
    List<SongMusicDTO> selectByMusicId(@Param("music_id") String music_id);


    /**
     * 根据类型获取6个新歌
     *
     * @param type 类型
     * @return 歌曲和歌手
     */
    List<SongMusicDTO> selectMusicByTypeAnd(@Param("type") String type);

    /**
     * 查询歌曲类型
     *
     * @return List
     */
    List<SongMusicDTO> selectByType();
}
