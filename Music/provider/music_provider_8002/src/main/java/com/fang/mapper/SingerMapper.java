package com.fang.mapper;

import com.fang.entity.dto.SingerDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手 Mapper
 *
 * @author Fang
 */
@Repository
public interface SingerMapper {
    /**
     * 根据歌手id查询歌手
     *
     * @param id 歌手id
     * @return List<SingerDTO>
     */
    List<SingerDTO> selectById(@Param("singerId") String id);
}
