package com.fang.service.Impl;

import com.fang.entity.Swiper;
import com.fang.feign.MusicFeignClients;
import com.fang.service.SwiperService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SwiperServiceImpl implements SwiperService {

    @Resource
    private MusicFeignClients swiperFeignClients;

    @Override
    public List<Swiper> getSwiper() {
        return swiperFeignClients.getSwiper();
    }
}
