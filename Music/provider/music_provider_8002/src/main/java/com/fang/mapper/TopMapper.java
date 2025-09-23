package com.fang.mapper;

import com.fang.entity.dto.PlayListDTO;
import org.springframework.stereotype.Repository;

/**
 * Top榜 Mapper
 *
 * @author Fang
 */
@Repository
public interface TopMapper {

    /**
     * 根据播放量查询榜单数据
     *
     * @return PlayListDTO
     */
    PlayListDTO selectTop();
}
