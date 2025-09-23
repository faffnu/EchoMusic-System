package com.fang.mapper;

import com.fang.entity.dto.MusicUserDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户Mapper
 *
 * @author Fang
 */
@Repository
public interface MusicUserMapper {

    /**
     * 根据id查询用户
     *
     * @param music_id 用户id
     * @return MusicUserDTO
     */
    MusicUserDTO selectMusicUserById(@Param("music_id") String music_id);
}
