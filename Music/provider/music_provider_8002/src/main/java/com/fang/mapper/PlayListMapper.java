package com.fang.mapper;

import com.fang.entity.dto.PlayListDTO;
import com.fang.entity.dto.SongMusicDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单 Mapper
 *
 * @author Fang
 */
@Repository
public interface PlayListMapper {
    /**
     * 获取5个热门推荐歌单
     *
     * @return List<PlayListDTO>
     */
    List<PlayListDTO> selectPlayListByPlayCount();

    /**
     * 根据歌单id查询对应的歌曲
     *
     * @param playListId 歌单id
     * @return List<SongMusicDTO>
     */
    List<SongMusicDTO> selectSongMusicByPlayListId(@Param("play_list_id") String playListId);

    /**
     * 获取歌单类型
     *
     * @return List<String>
     */
    List<String> selectPlayListByPlayListType();

    /**
     * 根据歌单类型查询歌单，返回5条数据
     *
     * @param type 类型
     * @return List<PlayListDTO>
     */
    List<PlayListDTO> selectPlayListByTag(@Param("type") String type);

    /**
     * 获得所有歌单
     *
     * @return List<PlayListDTO>
     */
    List<PlayListDTO> selectPlayListAll();

    /**
     * 根据歌单id播放量加1
     *
     * @param id 歌单id
     * @return Boolean
     */
    Boolean updatePlayCountById(@Param("id") String id);
}
