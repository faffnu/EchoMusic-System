package com.fang.mapper;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲 Mapper
 *
 * @author Fang
 */
@Repository
public interface SongMusicMapper {
    /**
     * 根据音乐的id查询对应的歌手和音乐信息
     *
     * @param music_id 歌曲id
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectSongMusicById(@Param("music_id") String music_id);

    /**
     * 根据id查询歌曲信息
     *
     * @param music_id 歌曲id
     * @return SongMusicDTO
     */
    SongMusicDTO selectMusicById(@Param("music_id") String music_id);

    /**
     * 根据歌曲名进行搜索
     *
     * @param music_name 歌曲名字
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> searchMusicByName(@Param("music_name") String music_name);

    /**
     * 根据id，播放量+1
     *
     * @param id 歌曲id
     * @return Boolean
     */
    Boolean updatePlayCountById(@Param("id") String id);

    /**
     * 查询所有新歌
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectMusicByNewMusic();

    /**
     * 查询所有歌曲播放量大于50
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectMusicByPlayCount();

    /**
     * 获取所有歌手信息
     * @return List<SingerDTO>
     */
    List<SingerDTO> selectAllSingers();

    /**
     * 根据歌手ID获取该歌手的全部歌曲
     * @param singerId 歌手ID
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectSongsBySingerId(@Param("singerId") String singerId);
}
