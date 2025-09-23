package com.fang.service.impl;

import com.fang.entity.Swiper;
import com.fang.mapper.SwiperMapper;
import com.fang.service.SwiperService;
import com.fang.system.StaticUrl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SwiperServiceImpl implements SwiperService {

    @Resource
    private SwiperMapper swiperMapper;

    private final String uri = StaticUrl.getUrl();

    /**
     * 获取轮播图
     * 如果缓存里面没有，就添加到缓存里面，再查出来
     * 缓存里面是按照 swiper::方法名来进行缓存
     *
     * @return List<Swiper>
     */
    @Cacheable(cacheNames = "swiper", key = "#root.methodName")
    @Override
    public List<Swiper> selectSwiperAll() {
        List<Swiper> swiperList = swiperMapper.selectAll();
        swiperList.forEach(item -> item.setSwiperUrl(uri + item.getSwiperUrl()));
        return swiperList;
    }
}
