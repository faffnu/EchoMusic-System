package com.fang.service.Impl;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.feign.MusicFeignClients;
import com.fang.response.ResultData;
import com.fang.service.SearchMusicService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchMusicServiceImpl implements SearchMusicService {

    @Resource
    private MusicFeignClients searchMusicFeignClients;

    @Override
    public ResultData<List<SongMusicDTO>> search(String music_name) {
        return searchMusicFeignClients.search(music_name);
    }
}
