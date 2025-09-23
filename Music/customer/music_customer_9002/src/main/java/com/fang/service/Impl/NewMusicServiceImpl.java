package com.fang.service.Impl;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.service.NewMusicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewMusicServiceImpl implements NewMusicService {
    @Resource
    private MusicFeignClients newMusicFeignClients;

    @Override
    public List<SongMusicDTO> newMusic() {
        return newMusicFeignClients.newMusic();
    }

    @Override
    public List<SongMusicDTO> newMusicByType(String type) {
        return newMusicFeignClients.newMusicByType(type);
    }

    @Override
    public List<SongMusicDTO> getType() {
        return newMusicFeignClients.getType();
    }
}
