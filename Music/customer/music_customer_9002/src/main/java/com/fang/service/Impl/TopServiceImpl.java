package com.fang.service.Impl;

import com.fang.entity.dto.PlayListDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.service.TopService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TopServiceImpl implements TopService {

    @Resource
    private MusicFeignClients topFeignClients;

    @Override
    public PlayListDTO selectTop() {
        return topFeignClients.selectTop();
    }
}
