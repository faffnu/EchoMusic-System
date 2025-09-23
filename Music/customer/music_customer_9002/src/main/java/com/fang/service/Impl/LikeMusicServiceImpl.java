package com.fang.service.Impl;

import com.fang.entity.dto.LikeMusicDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.response.ResultData;
import com.fang.service.LikeMusicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LikeMusicServiceImpl implements LikeMusicService {

    @Resource
    private MusicFeignClients likeMusicFeignClients;

    @Override
    public ResultData<Map<String, Object>> insertLikeMusic(String user_id, String song_id) {
        return likeMusicFeignClients.insertLikeMusic(user_id, song_id);
    }

    @Override
    public List<LikeMusicDTO> selectLikeMusicByUserId(String token) {
        return likeMusicFeignClients.selectLikeMusicByUserId(token);
    }

    @Override
    public ResultData<Map<String, Object>> deleteLikeMusic(String token, String songId) {
        return likeMusicFeignClients.deleteLikeMusic(token, songId);
    }
}
