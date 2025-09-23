package com.fang.controller;

import com.fang.entity.Swiper;
import com.fang.service.impl.SwiperServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/provider/music")
@Tag(name = "获取轮播图", description = "获取轮播图")
@Slf4j
public class SwiperController {

    @Resource
    private SwiperServiceImpl swiperServiceImpl;

    @GetMapping("/getSwiper")
    @Operation(summary = "获取轮播图", description = "返回轮播图id和图片地址")
    public List<Swiper> getSwiper() {
        return swiperServiceImpl.selectSwiperAll();
    }
}
