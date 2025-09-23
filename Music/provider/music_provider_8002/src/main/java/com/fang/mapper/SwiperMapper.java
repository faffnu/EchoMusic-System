package com.fang.mapper;

import com.fang.entity.Swiper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 轮播图 Mapper
 */
@Repository
public interface SwiperMapper {
    /**
     * 查询所有轮播图
     * @return List<Swiper>
     */
    List<Swiper> selectAll();
}
