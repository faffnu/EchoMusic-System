package com.fang.service;

import com.fang.entity.dto.SingerDTO;
import com.fang.entity.dto.SongMusicDTO;
import com.fang.response.ResultData;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SongMusicService {
    public ResultData<Object> updateSongMusicById(String id);

    public ResultData<List<SongMusicDTO>> newMusicBoard();

    public ResultData<List<SongMusicDTO>> hotMusicBoard();

    public ResultData<List<SingerDTO>> getAllSingers();

    public ResultData<List<SongMusicDTO>> getSongsBySingerId(String singerId);
}
