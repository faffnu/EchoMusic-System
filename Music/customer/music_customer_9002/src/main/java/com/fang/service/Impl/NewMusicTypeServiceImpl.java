package com.fang.service.Impl;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.service.NewMusicTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewMusicTypeServiceImpl implements NewMusicTypeService {

    @Resource
    private MusicFeignClients newMusicTypeFeignClients;

    @Override
    public List<SongMusicDTO> getMusicByTag(String tag) {
        return newMusicTypeFeignClients.getMusicByTag(tag);
    }
}
