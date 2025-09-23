package com.fang.service;

import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SearchMusicService {
    public ResultData<List<SongMusicDTO>> search(@RequestParam("music_name") String music_name);
}
