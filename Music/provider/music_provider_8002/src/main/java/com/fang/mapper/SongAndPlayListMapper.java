package com.fang.mapper;

import com.fang.entity.dto.SongAndPlayListDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单和歌曲中间 Mapper
 *
 * @author Fang
 */
@Repository
public interface SongAndPlayListMapper {

    /**
     * 根据id查询歌曲
     * @param id id
     * @return List<SongAndPlayListDTO>
     */
    List<SongAndPlayListDTO> selectByMusicId(@Param("id") String id);
}
